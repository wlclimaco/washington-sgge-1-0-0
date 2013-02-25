 -------------------------------------------------------------------------------------------------------------------------
 -- VERSION 1.1.0
 -------------------------------------------------------------------------------------------------------------------------
 DROP FUNCTION get_property_valid_value_by_name(character varying);
 DROP FUNCTION get_light_history(integer, integer, integer, integer);
 DROP FUNCTION get_light_history_pagination_total_rows(integer, integer);
 DROP FUNCTION get_monitored_processes (integer);
 DROP FUNCTION upsert_process_property(integer, integer, character varying);
 DROP FUNCTION upd_smartpoint_process(integer, integer, integer, integer);
 DROP FUNCTION get_smartpoints_by_schedule_to_map(integer, double precision, double precision, double precision, double precision, integer);
 DROP FUNCTION get_status_message_by_id(integer);
 DROP FUNCTION get_light_history(integer, integer, integer, integer);
 DROP FUNCTION upsert_user(character varying);
 DROP FUNCTION get_all_user();
 ALTER TABLE schedule ADD COLUMN latitude double precision;
 ALTER TABLE schedule ADD COLUMN longitude double precision; 
 DROP FUNCTION upd_light_status(integer, integer, character varying, integer, integer, integer, timestamp);
 DROP FUNCTION get_analytics_warnings_by_type(integer, integer, date, date, integer, character varying);
 DROP FUNCTION get_analytics_alarms_by_type(integer, integer, date, date, integer, character varying);
 DROP FUNCTION get_analytics_group_by_date_pk(integer, timestamp with time zone, character varying);
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_light_history_pagination_total_rows(p_light_id integer, p_tenant_id integer)
  RETURNS bigint AS
$$
DECLARE
    p_type_alarm int;   
    p_type_warning int;
    p_smartpoint_id int;
BEGIN
    p_type_alarm := 1;  
    p_type_warning := 2;
    p_smartpoint_id := (SELECT smartpoint_id FROM light WHERE light_id = p_light_id);
    
    
    RETURN 

    (SELECT count(*)
    
    FROM (

            SELECT  s.status_id
            FROM status_message s
            WHERE s.light_id = p_light_id 
                AND (s.status_id = p_type_alarm OR s.status_id = p_type_warning)
            
            UNION ALL

            SELECT  p.process_id
              FROM process p
                   ,smartpoint_process sp
             WHERE sp.smartpoint_id = p_smartpoint_id
                   AND sp.process_id  = p.process_id
                   AND p.tenant_id = p_tenant_id ) AS lh);

END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_light_history(IN p_light_id integer, IN p_tenant_id integer, IN p_start_row integer, IN p_page_size integer)
  RETURNS TABLE(id integer, create_date timestamp with time zone, "name" character varying, description character varying, create_user character varying) AS
$$
DECLARE
    p_type_alarm int;   
    p_type_warning int;
    p_smartpoint_id int;
BEGIN
    p_type_alarm := 1;  
    p_type_warning := 2;
    p_smartpoint_id := (SELECT smartpoint_id FROM light WHERE light_id = p_light_id);
    
    
    RETURN QUERY 

    SELECT process_id as id, date as create_date, history_name as name, history_desc as description, user_name as create_user

    FROM (

        SELECT ROW_NUMBER() OVER(ORDER BY date DESC) as RowNum
                ,lh.*

        FROM (

            SELECT  null AS process_id
                    ,s.message_date AS date
                    ,(CASE s.status_id WHEN p_type_alarm THEN CAST('Alarm' AS character varying) WHEN p_type_warning THEN CAST('Warning' AS character varying) END ) AS history_name
                    ,(SELECT s1.label_key
                        FROM status_subtype s1,
                             status_message_status_subtype s2,
                             status_message s3
                        WHERE s3.status_message_id = s.status_message_id
                        AND s1.status_subtype_id = s2.status_subtype_id
                        AND s2.status_message_id = s3.status_message_id) AS history_desc
                    ,s.create_user AS user_name
                        
            FROM status_message s
            WHERE s.light_id = p_light_id 
                AND (s.status_id = p_type_alarm OR s.status_id = p_type_warning)
            
            UNION ALL

            SELECT  p.process_id AS process_id
                   ,p.create_date AS date
                   ,p.description AS history_name
                   ,p.lc_action_description AS history_desc
                   ,p.create_user AS user_name
              FROM process p
                   ,smartpoint_process sp
             WHERE sp.smartpoint_id = p_smartpoint_id
                   AND sp.process_id  = p.process_id
                   AND p.tenant_id = p_tenant_id ) AS lh

        ) AS light_history

     WHERE RowNum BETWEEN p_start_row + 1 AND (p_start_row + p_page_size);
                  
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_light_history_header(IN p_light_id integer)
  RETURNS TABLE(alarms bigint, warnings bigint, power_failure bigint) AS
$$
DECLARE
    p_type_alarm int;   
    p_type_warning int;
    p_power_failure int;
BEGIN
    p_type_alarm := 1;  
    p_type_warning := 2;
    p_power_failure := 2;
    
    RETURN QUERY


    SELECT (SELECT COUNT(1) FROM status_message
                WHERE light_id = p_light_id 
                AND status_id = p_type_alarm) AS alarms
       ,(SELECT COUNT(1) FROM status_message
                WHERE light_id = p_light_id 
                AND status_id = p_type_warning) AS warnings
       ,(SELECT COUNT(1) FROM status_message_status_subtype smss
                ,status_message sm
                WHERE sm.light_id = p_light_id
                AND sm.status_id = p_type_alarm
                AND smss.status_message_id = sm.status_message_id
                AND smss.status_subtype_id = p_power_failure
                AND smss.status_message_id = sm.status_message_id) AS power_failure;
       
              
END
$$
  LANGUAGE plpgsql;
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_count_monitored_processes(IN p_tenant_id integer, p_create_user character varying)
  RETURNS TABLE(count_monitored bigint, count_processing bigint) AS
$$
DECLARE 
        p_monitored_instance boolean;
        p_first_level boolean;
        p_process_complete boolean;
BEGIN
        p_monitored_instance := true;
        p_first_level := true;
        p_process_complete := false;

        RETURN QUERY
         
        SELECT  (SELECT COUNT(1)
                FROM process
                WHERE tenant_id = p_tenant_id AND is_first_level = p_first_level AND is_monitored_instance = p_monitored_instance AND create_user = p_create_user) AS monitored_processes
                ,(SELECT COUNT(1) 
                FROM process
                WHERE tenant_id = p_tenant_id AND is_first_level = p_first_level AND is_monitored_instance = p_monitored_instance AND is_process_complete = p_process_complete AND create_user = p_create_user) AS processing_processes;
        
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_pole_id_by_light(IN p_light_id integer)
  RETURNS TABLE(property_id integer, label_key character varying, property_value character varying, modify_user character varying, modified_date timestamp with time zone, create_user character varying, create_date timestamp with time zone, data_type integer, property_name character varying, property_class smallint) AS
$$
DECLARE
    p_pole_id_property int;
BEGIN

    p_pole_id_property := 12;

    RETURN QUERY SELECT p.property_id
                  ,p.label_key
                  ,lp.property_value
                  ,lp.modify_user
                  ,lp.modified_date
                  ,lp.create_user
                  ,lp.create_date
                  ,p.data_type
                  ,p.property_name
                  ,p.property_class
    FROM property p
        ,light_property lp
    WHERE lp.light_id = p_light_id
      AND lp.property_id = p_pole_id_property 
      AND lp.property_id = p.property_id;
END
$$
  LANGUAGE plpgsql; 

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_analytics_energy_savings_by_date(IN p_date_type_id character varying, IN p_tenant_id integer, IN p_group_id integer, IN p_init_date date, IN p_end_date date, IN p_grouping_ids character varying)
  RETURNS TABLE(description character varying, date_time timestamp without time zone, amount numeric) AS
$$
DECLARE

        p_induction_type integer;
        p_induction_description character varying;
        p_led_type integer;
        p_led_description character varying;
        p_other_type integer;
        p_other_description character varying;
        p_all_description character varying;
        p_day_amount integer;
        p_month_amount integer;

BEGIN

        p_induction_type := 1;
        p_induction_description := 'sensus.mlc.lamp_type.induction';
        p_led_type := 2;
        p_led_description := 'sensus.mlc.lamp_type.led';
        p_other_type := 3;
        p_other_description := 'sensus.mlc.lamp_type.other';
        p_all_description := 'sensus.mlc.status_subtype.all';

        p_day_amount := 0;
        p_month_amount := 0;

        IF p_date_type_id IN ('1d','3d','1w','1m') THEN
            p_day_amount := (SELECT p_end_date - p_init_date ) + 1;
        ELSIF p_date_type_id IN ('3m') THEN 
                        p_month_amount := 2;
                ELSIF p_date_type_id IN ('ytd','1y') THEN  
            p_month_amount := (SELECT p_end_date - p_init_date ) / 30;
        END IF;

        IF p_date_type_id IN ('3m','ytd','1y') THEN

            p_init_date := date_trunc('MONTH',p_init_date);
            p_end_date := (SELECT p_init_date + interval '1 MONTH') - interval '1 day';

            FOR i IN 0..p_month_amount LOOP

               RETURN QUERY   
                                             
                       SELECT tb1.description AS description
                                      ,CAST(tb1.date_time AS timestamp without time zone) AS date_time
                                      ,ROUND(CAST(CAST(SUM(tb1.amount) AS real)as numeric),3) AS amount

                       FROM (
                                SELECT p_induction_description AS description
                                      ,p_init_date AS date_time
                                      ,get_analytics_energy_savings_total(p_tenant_id
                                                                          ,p_group_id
                                                                          ,p_init_date
                                                                          ,p_end_date
                                                                          ,p_induction_type
                                                                          ,p_grouping_ids) AS amount

                                UNION ALL
                                SELECT p_led_description AS description
                                          ,p_init_date AS date_time
                                          ,get_analytics_energy_savings_total(p_tenant_id
                                                                               ,p_group_id
                                                                               ,p_init_date
                                                                               ,p_end_date
                                                                               ,p_led_type
                                                                               ,p_grouping_ids) AS amount
                                UNION ALL
                                SELECT p_other_description AS description
                                                                  ,p_init_date AS date_time
                                                                  ,get_analytics_energy_savings_total(p_tenant_id
                                                                                                       ,p_group_id
                                                                                                       ,p_init_date
                                                                                                       ,p_end_date
                                                                                                       ,p_other_type
                                                                                                       ,p_grouping_ids) AS amount
                                 UNION ALL
                                 SELECT p_all_description AS description
                                                                  ,p_init_date AS date_time
                                                                  ,get_analytics_energy_savings_total(p_tenant_id
                                                                                                       ,p_group_id
                                                                                                       ,p_init_date
                                                                                                       ,p_end_date
                                                                                                       ,null
                                                                                                       ,p_grouping_ids) AS amount


                                UNION ALL                
                                SELECT p_induction_description AS description
                                                                ,p_init_date AS date_time
                                                                ,0 AS amount
                                FROM analytics_light_type 
                                
                                UNION ALL                
                                SELECT p_led_description AS description
                                                                ,p_init_date AS date_time
                                                                ,0 AS amount
                                FROM analytics_light_type 
                                
                                UNION ALL                
                                SELECT p_other_description AS description
                                                ,p_init_date AS date_time
                                                ,0 AS amount
                                                                FROM analytics_light_type 
                                UNION ALL                
                                SELECT p_all_description AS description
                                                   ,p_init_date AS date_time
                                                   ,0 AS amount
               
                            )AS tb1
                            GROUP BY tb1.description ,CAST(tb1.date_time AS timestamp without time zone) 
                    ORDER BY 2,1;      
               
                p_init_date := (SELECT p_init_date + interval '1 MONTH');
                p_end_date := (SELECT p_init_date + interval '1 MONTH') - interval '1 day';

            END LOOP;
                         
        ELSE 

            FOR i IN 1..p_day_amount LOOP 
             
            RETURN QUERY   
             
            SELECT tb1.description AS description
                    ,CAST(tb1.date_time as timestamp without time zone) AS date_time
                    ,ROUND(CAST(CAST(SUM(tb1.amount) AS real)as numeric),3) AS amount

               FROM (
                       
                    SELECT p_induction_description AS description
                          ,p_init_date AS date_time
                          ,get_analytics_energy_savings_total(
                               p_tenant_id
                              ,p_group_id
                              ,p_init_date
                              ,p_init_date
                              ,p_induction_type
                              ,p_grouping_ids) AS amount

                    UNION ALL
                    SELECT p_led_description AS description
                              ,p_init_date AS date_time
                              ,get_analytics_energy_savings_total(
                                    p_tenant_id
                                   ,p_group_id
                                   ,p_init_date
                                   ,p_init_date
                                   ,p_led_type
                                   ,p_grouping_ids) AS amount
                    UNION ALL
                    SELECT p_other_description AS description
                              ,p_init_date AS date_time
                              ,get_analytics_energy_savings_total(
                                    p_tenant_id
                                   ,p_group_id
                                   ,p_init_date
                                   ,p_init_date
                                   ,p_other_type
                                   ,p_grouping_ids) AS amount
                     UNION ALL
                     SELECT p_all_description AS description
                              ,p_init_date AS date_time
                              ,get_analytics_energy_savings_total(
                                    p_tenant_id
                                   ,p_group_id
                                   ,p_init_date
                                   ,p_init_date
                                   ,null
                                   ,p_grouping_ids) AS amount
       
                    ) AS tb1
                                            
                GROUP BY tb1.description,CAST(tb1.date_time AS timestamp without time zone)
                ORDER BY 2,1;      
                            
                p_init_date := (SELECT p_init_date + 1);

            END LOOP;
  
        END IF;
  
    RETURN;
   
END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_average(integer, integer, character varying, character varying, double precision);

CREATE OR REPLACE FUNCTION get_analytics_average(p_tenant_id integer, p_group_id integer, p_view_mode_id character varying, p_view_type_id character varying, p_carbon_credits_factor double precision, p_grouping_ids character varying)
  RETURNS double precision AS
$$
DECLARE
  
  p_total float;
  p_init_date date;
  p_end_date date;
  
  p_min_date date;
  
  p_divide_week float;
  p_divide_month float;
  p_days int;
  
  p_week int;
  p_month int;
  
BEGIN
  
  p_total := 0;
  
  -- numbers of days in one week
  p_week := 7;
  
  -- numbers of days in one month
  p_month := 30;
  
  -- default week rate when there is more than 3 months of information
  -- (3 months * 4 weeks per month) = 12
  p_divide_week := 12;
  
  -- default month rate when there is more than 3 months of information
  p_divide_month := 3;
  
  p_init_date := (SELECT CURRENT_DATE - INTERVAL '91 days');
  p_end_date := (SELECT CURRENT_DATE - INTERVAL '1 days');
  
  --alarms
  IF (p_view_type_id = '1') THEN
    
        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date 
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id   
                                                                                                                         FROM analytics_alarms
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
    
        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE 
                        p_days := (SELECT p_end_date - p_min_date);
                        p_total := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
                        p_divide_week := p_days / p_week;
                        p_divide_month := p_days / p_month;
        END IF;

  --warnings
  ELSIF (p_view_type_id = '2') THEN
        
        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date 
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id   
                                                                                                                         FROM analytics_warnings
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
    
        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE 
            p_days := (SELECT p_end_date - p_min_date);
            p_total := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
            p_divide_week := p_days / p_week;
            p_divide_month := p_days / p_month;
        END IF;
        
  --installed
  ELSIF (p_view_type_id = '3') THEN
  
        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date 
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id   
                                                                                                                         FROM analytics_installed
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
    
        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE 
            p_days := (SELECT p_end_date - p_min_date);
            p_total := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
            p_divide_week := p_days / p_week;
            p_divide_month := p_days / p_month;
        END IF;
                
  --consumption
  ELSIF (p_view_type_id = '4') THEN
    
                p_min_date := ( SELECT analytic_date
                                                                  FROM analytics_group_by_date 
                                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id   
                                                                                                                                         FROM analytics_consumption
                                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
    
        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
                  p_total := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE 
          p_days := (SELECT p_end_date - p_min_date);
          p_total := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
          p_divide_week := p_days / p_week;
          p_divide_month := p_days / p_month;
        END IF;

  --energy savings
  ELSIF (p_view_type_id = '5') THEN

        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date 
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id   
                                                                                                                         FROM analytics_consumption
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
        --
        -- for energy savings and carbon credits the end date must be yesterday, once conservation baseline proceudre did not run yet
        p_end_date := (SELECT cast(p_end_date - interval '1 day' as date));
    
        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE 
                        p_days := (SELECT p_end_date - p_min_date);
                        p_total := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
                        p_divide_week := p_days / p_week;
                        p_divide_month := p_days / p_month;
        END IF;
        
  --carbon credits
  ELSIF (p_view_type_id = '6') THEN
    
        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date 
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id   
                                                                                                                         FROM analytics_consumption
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
        --
        -- for energy savings and carbon credits the end date must be yesterday, once conservation baseline proceudre did not run yet
        p_end_date := (SELECT cast(p_end_date - interval '1 day' as date));
    
        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_init_date,p_end_date,p_carbon_credits_factor,NULL, p_grouping_ids));
        ELSE 
                        p_days := (SELECT p_end_date - p_min_date);
                        p_total := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_min_date,p_end_date,p_carbon_credits_factor,NULL, p_grouping_ids));          
                        p_divide_week := p_days / p_week;
                        p_divide_month := p_days / p_month;
        END IF;
    
  END IF;
  
  IF p_divide_week = 0 THEN
    p_divide_week := 1;
  END IF;
  
  IF p_divide_month = 0 THEN
    p_divide_month := 1;
  END IF;
  
  IF (p_view_mode_id = 'week') THEN
        RETURN coalesce((p_total / p_divide_week),0);
  ELSE
    RETURN coalesce((p_total / p_divide_month),0);
  END IF;

END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_change(integer, integer, character varying, character varying, double precision);

CREATE OR REPLACE FUNCTION get_analytics_change(p_tenant_id integer, p_group_id integer, p_view_mode_id character varying, p_view_type_id character varying, p_carbon_credits_factor double precision, p_grouping_ids character varying)
  RETURNS double precision AS
$BODY$
DECLARE
  
  p_last_period float;
  p_current_period float;
  p_total float;
  
  p_init_date_current date;
  p_init_date_last date;
  p_end_date_current date;
  p_end_date_last date;
  
BEGIN   
  
  IF (p_view_mode_id = 'week') THEN
    
        p_init_date_current := (SELECT CURRENT_DATE - 7);
        p_end_date_current := (SELECT CURRENT_DATE );
        
        p_init_date_last := (SELECT p_init_date_current - 7);
        p_end_date_last := (SELECT p_end_date_current - 7);
        
  ELSIF (p_view_mode_id = 'month') THEN
         
        p_init_date_current := (SELECT CURRENT_DATE - 30);
        p_end_date_current := (SELECT CURRENT_DATE);
        
        p_init_date_last := (SELECT p_init_date_current - 30);
        p_end_date_last := (SELECT p_end_date_current - 30);
          
  END IF;
  
  --alarms
  IF (p_view_type_id = '1') THEN
    p_current_period := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));
        
  --warnings
  ELSIF (p_view_type_id = '2') THEN
    p_current_period := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));
        
  --installed
  ELSIF (p_view_type_id = '3') THEN
    p_current_period := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));
        
  --consumption
  ELSIF (p_view_type_id = '4') THEN
    p_current_period := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));

  --energy savings
  ELSIF (p_view_type_id = '5') THEN
        
    --
    -- for energy savings and carbon credits the end date must be yesterday, once conservation baseline proceudre did not run yet        
    p_init_date_current := (SELECT p_init_date_current - interval '1 day');
    p_end_date_current := (SELECT p_end_date_current - interval '1 day');
        
    p_init_date_last := (SELECT p_init_date_last - interval '1 day');
    p_end_date_last := (SELECT p_end_date_last - interval '1 day');
        
    p_current_period := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));

  --consumption
  ELSIF (p_view_type_id = '6') THEN
  
      --
    -- for energy savings and carbon credits the end date must be yesterday, once conservation baseline proceudre did not run yet        
    p_init_date_current := (SELECT p_init_date_current - interval '1 day');
    p_end_date_current := (SELECT p_end_date_current - interval '1 day');
        
    p_init_date_last := (SELECT p_init_date_last - interval '1 day');
    p_end_date_last := (SELECT p_end_date_last - interval '1 day');
    
    p_current_period := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current, p_carbon_credits_factor,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,p_carbon_credits_factor,NULL, p_grouping_ids));
        
  END IF;
  
  p_total := 0;
  IF (COALESCE(p_last_period,0) = 0 AND COALESCE(p_current_period,0) = 0) THEN
    p_total := 0;
  ELSIF (COALESCE(p_last_period,0) = 0 AND COALESCE(p_current_period,0) > 0) THEN
    p_total := 100; 
  ELSIF (COALESCE(p_last_period,0) = 0 AND COALESCE(p_current_period,0) < 0) THEN
    p_total := -100;        
  ELSE
    p_total := ((p_current_period - p_last_period) / p_last_period) * 100;
  END IF;
  
  RETURN p_total;
  
END
$BODY$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_dashboard_header(integer, double precision, character varying);

CREATE OR REPLACE FUNCTION get_dashboard_header(IN p_tenant_id integer, IN p_carbon_credits_factor double precision, IN p_grouping_ids character varying)
  RETURNS TABLE(description character varying, date_time timestamp without time zone, amount double precision) AS
$$
DECLARE
  
    p_init_date date;
    p_end_date date;
  
    p_total_installed int;
    p_total_alarms int;
    p_total_warnigs int;

    p_total_consumption int;
    p_total_energy_saving int;
    p_total_carbon_credit int;
    p_total_baseline int;
    v_analytic_group_id int;

    w_array_date timestamp ARRAY;       
    w_array_total float ARRAY;
    w_array_type_column character varying ARRAY;

    p_type_alarm character varying; 
    p_type_warning character varying; 
        p_light_status int;
    
BEGIN

    p_type_alarm := '1';
    p_type_warning := '2';
        p_light_status := 81;
               
    w_array_type_column[0]:= 'Total Installed';
    w_array_type_column[1]:= 'Total Alarms';
    w_array_type_column[2]:= 'Total Warnings';

    w_array_type_column[3]:= 'Total Consumption';
    w_array_type_column[4]:= 'Total Eco-Mode';
    w_array_type_column[5]:= 'Total Carbon Credits';

    p_init_date := (SELECT CURRENT_DATE);  
    p_end_date := (SELECT CURRENT_DATE);
	
	v_analytic_group_id := NULL;
  
	IF(p_grouping_ids IS NULL) THEN
		SELECT ag.analytic_group_id FROM analytics_group ag WHERE ag.analytic_group_name = 'All' AND ag.tenant_id = p_tenant_id INTO v_analytic_group_id ;
	END IF;
        
    -- Total Installed  
	IF(p_grouping_ids IS NULL) THEN

		w_array_total[0] := (SELECT COUNT(1) FROM light WHERE tenant_id = p_tenant_id);
    
	ELSE

		w_array_total[0] := (SELECT COUNT(DISTINCT(l.light_id)) 
							   FROM light l
									,smartpoint_grouping smp_grp
							   WHERE l.tenant_id = p_tenant_id
								 AND l.smartpoint_id = smp_grp.smartpoint_id
								 AND smp_grp.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')) );
    END IF;	
	w_array_date[0] := p_init_date;

    -- Total Alarms 
    IF(p_grouping_ids IS NULL) THEN

		w_array_total[1] := (SELECT COUNT(1) 
							   FROM light_property lp
									,light         l
							  WHERE l.tenant_id       = p_tenant_id 
								AND l.light_id        = lp.light_id
								AND lp.property_id    = p_light_status
								AND lp.property_value = p_type_alarm);
	ELSE

		w_array_total[1] := (SELECT COUNT(DISTINCT(l.light_id)) 
								   FROM light_property lp
										,light l
										,smartpoint_grouping smp_grp
								  WHERE l.tenant_id = p_tenant_id 
									AND l.light_id        = lp.light_id
									AND l.smartpoint_id = smp_grp.smartpoint_id
									AND smp_grp.grouping_id IN (SELECT * FROM split(p_grouping_ids,','))
									AND lp.property_id    = p_light_status
									AND lp.property_value = p_type_alarm);
	
	END IF;
    w_array_date[1] := p_init_date;
    
    -- Total Warnigs
    IF(p_grouping_ids IS NULL) THEN
	
		w_array_total[2] := (SELECT COUNT(1) 
								   FROM light_property lp
										,light         l
								  WHERE l.tenant_id       = p_tenant_id 
									AND l.light_id        = lp.light_id
									AND lp.property_id    = p_light_status
									AND lp.property_value = p_type_warning);
	
	ELSE

		w_array_total[2] := (SELECT COUNT(DISTINCT(l.light_id))  
								   FROM light_property lp
										,light l
										,smartpoint_grouping smp_grp
								  WHERE l.tenant_id = p_tenant_id 
									AND l.light_id        = lp.light_id
									AND l.smartpoint_id = smp_grp.smartpoint_id
									AND smp_grp.grouping_id IN (SELECT * FROM split(p_grouping_ids,','))
									AND lp.property_id    = p_light_status
									AND lp.property_value = p_type_warning);
                                                                
	END IF;
    w_array_date[2] := p_init_date;

    --Total Consumption 
    IF(p_grouping_ids IS NULL) THEN

		p_init_date := ( SELECT DATE_TRUNC('DAY',MIN(agbd.analytic_date))
						   FROM analytics_consumption     ac
								,analytics_group_by_date  agbd
								,analytics_group          ag
						  WHERE ag.tenant_id = p_tenant_id
							AND ag.analytic_group_id = agbd.analytic_group_id
							AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id);
	
	ELSE
	
		p_init_date := ( SELECT DATE_TRUNC('DAY',MIN(agbd.analytic_date))
						   FROM analytics_consumption    ac
								,analytics_group_by_date  agbd
								,analytics_group          ag
						 WHERE ag.tenant_id         = p_tenant_id
						   AND ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,','))
						   AND ag.analytic_group_id = agbd.analytic_group_id
						   AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id);
    END IF;                                          
    w_array_total[3] := ((SELECT get_analytics_consumption_total(p_tenant_id, v_analytic_group_id, p_init_date , p_end_date , NULL, p_grouping_ids)));
    w_array_date[3] := p_init_date;

    --Total Eco-Mode
    p_end_date := (SELECT p_end_date - interval '1 day');
    
    w_array_total[4] := (SELECT get_analytics_eco_mode_total(p_tenant_id, v_analytic_group_id, p_init_date , p_end_date ,p_grouping_ids));    
    w_array_date[4] := p_init_date;
                                                        
    -- Total Carbon Credits
    w_array_total[5] := (SELECT get_analytics_carbon_credits_total(p_tenant_id, v_analytic_group_id, p_init_date , p_end_date ,p_carbon_credits_factor ,NULL, p_grouping_ids));
    w_array_date[5] := p_init_date;
        
    FOR i IN 0..5 LOOP
      RETURN QUERY SELECT w_array_type_column[i],w_array_date[i],w_array_total[i]; 
    END LOOP;
        
END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsert_process_property(p_property_id integer, p_process_id integer, p_property_value character varying)
  RETURNS integer AS
$$
BEGIN
    LOOP
        -- first try to update the key
        UPDATE process_property SET value = p_property_value WHERE (property_id = p_property_id) AND (process_id = p_process_id);
        IF found THEN
            RETURN p_process_id;
        END IF;
        -- not there, so try to insert the key
        -- if someone else inserts the same key concurrently,
        -- we could get a unique-key failure
        BEGIN
            INSERT INTO process_property (property_id, process_id, value)
                        VALUES  (p_property_id
                        , p_process_id
                        , p_property_value);
            RETURN p_process_id;
        EXCEPTION WHEN unique_violation THEN
            -- do nothing, and loop to try the UPDATE again
        END;
    END LOOP;
END;
$$
  LANGUAGE plpgsql;
  
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_smartpoint_process(p_process_id integer, p_smartpoint_id integer, p_process_result integer, p_failure_id integer)
  RETURNS integer AS
$$
BEGIN
        UPDATE smartpoint_process
        SET process_result = p_process_result
                ,failure_id =  p_failure_id
        WHERE smartpoint_id = p_smartpoint_id AND process_id = p_process_id;
        RETURN p_process_id;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_light_consumption(p_light_id integer, p_initial_date timestamp without time zone, p_end_date timestamp without time zone)
  RETURNS real AS
$$
DECLARE
        initialConsumption float4;
        finalConsumption float4;
        p_type_cumulative_consumption integer;
        v_end_date date;
BEGIN
        p_type_cumulative_consumption := 1;

            v_end_date := CAST( p_end_date + CAST('1 days' AS interval) AS DATE);
        
        finalConsumption := (SELECT value AS endValue FROM operational_data_value odv INNER JOIN status_message sm ON odv.status_message_id = sm.status_message_id
                                                        WHERE sm.light_id = p_light_id 
                                                        AND odv.create_date < v_end_date
                                                        AND odv.operational_data_type_id = p_type_cumulative_consumption 
                                                        ORDER BY odv.create_date desc LIMIT 1);
        
        initialConsumption := (SELECT value AS initialValue FROM operational_data_value odv INNER JOIN status_message sm ON odv.status_message_id = sm.status_message_id
                                                                WHERE sm.light_id = p_light_id 
                                                                AND odv.create_date >=  p_initial_date 
                                                                AND odv.operational_data_type_id = p_type_cumulative_consumption 
                                                                ORDER BY odv.create_date LIMIT 1);
        RETURN finalConsumption - initialConsumption;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_smartpoints_by_group_list_to_map(character varying, integer, double precision, double precision, double precision, double precision, integer);

CREATE OR REPLACE FUNCTION get_smartpoints_by_group_list_to_map(IN p_groupids character varying, IN p_tenant_id integer, IN p_bottomleftlat double precision, IN p_bottomleftlon double precision, IN p_toprightlat double precision, IN p_toprightlon double precision, IN p_maxdevicecount integer)
  RETURNS TABLE(light_id integer, flexnet_id integer, latitude character varying, longitude character varying, light_status character varying) AS
$$
DECLARE 

        p_sectors int;
        p_middleLatMin float;
        p_middleLatMax float;
        p_middleLonMin float;
        p_middleLonMax float;
        
        p_lat_property_id integer;
        p_lon_property_id integer;
        p_current_stat_property_id integer;
 
BEGIN
                
        p_lat_property_id := 16;
        p_lon_property_id := 17;
        p_current_stat_property_id := 81;

        p_sectors := 5;   
        
 IF p_groupids IS NOT NULL THEN
   
     IF(
		(SELECT COUNT(l.smartpoint_id)
		   FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
                  WHERE l.smartpoint_id = smg.smartpoint_id
                    AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
                    AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                    AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                    AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
                    AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon) > p_maxDeviceCount) AND (p_maxDeviceCount != 0) THEN
     
      
	          p_maxDeviceCount := (p_maxDeviceCount / p_sectors);
        
		-- 20% Lat Asc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id
			  AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		       ORDER BY lp_lat.property_value ASC LIMIT p_maxDeviceCount;
         
		-- 20% Lat Desc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id
			  AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		       ORDER BY lp_lat.property_value DESC LIMIT p_maxDeviceCount;

		-- 20% Long Asc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id
			  AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id                   
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		        ORDER BY lp_lon.property_value ASC LIMIT p_maxDeviceCount;
                      
		-- 20% Long Desc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon , light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id
			  AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id                    
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		       ORDER BY lp_lon.property_value DESC LIMIT p_maxDeviceCount;
                   
          
		-- 20% Middle Points
		  
		-- Min Lat
		p_middleLatMin :=  (SELECT MIN(cast(lp_lat.property_value as double precision))
					FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
					WHERE l.smartpoint_id = smg.smartpoint_id
					 AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
					 AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
					 AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
					 AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
					 AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);

					  
		-- Max Lat 
		p_middleLatMax :=  (SELECT MAX(cast(lp_lat.property_value as double precision))
				      FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
				    WHERE l.smartpoint_id = smg.smartpoint_id
				       AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
				       AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
				       AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
				       AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
				       AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);

		-- Min Long
		p_middleLonMin :=   (SELECT MIN(cast(lp_lon.property_value as double precision))
					 FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
					WHERE l.smartpoint_id = smg.smartpoint_id
					  AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
					  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
					  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
					  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
					  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);


		-- Max Long                                            
		p_middleLonMax :=    (SELECT MAX(cast(lp_lon.property_value as double precision))
				       FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
				     WHERE l.smartpoint_id = smg.smartpoint_id
				       AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
				       AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
				       AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
				       AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
				       AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);
		  
		  
		  -- Insert Middle Points 
		
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon , light_property lp_status, smartpoint smp
			 WHERE l.smartpoint_id = smg.smartpoint_id
			   AND l.smartpoint_id = smp.smartpoint_id
			   AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
				   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			   AND cast(lp_lat.property_value as double precision) BETWEEN p_middleLatMin AND p_middleLatMax
			   AND cast(lp_lon.property_value as double precision) BETWEEN p_middleLonMin AND p_middleLonMax
			   LIMIT p_maxDeviceCount;
	  ELSE 
		RETURN QUERY 
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon , light_property lp_status, smartpoint smp
			 WHERE l.smartpoint_id = smg.smartpoint_id
			   AND l.smartpoint_id = smp.smartpoint_id
			   AND smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
				   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			   AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			   AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		     ORDER BY smp.rni_id ASC;  
	  END IF;
 ELSE
    IF (
		(SELECT COUNT(l.smartpoint_id)
		   FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
                  WHERE l.smartpoint_id = smg.smartpoint_id  
                    AND l.tenant_id = p_tenant_id                  
                    AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                    AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                    AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
                    AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon) > p_maxDeviceCount) AND (p_maxDeviceCount != 0) THEN
     
      
	          p_maxDeviceCount := (p_maxDeviceCount / p_sectors);
        
		-- 20% Lat Asc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id	
			  AND l.tenant_id = p_tenant_id		  
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		    ORDER BY lp_lat.property_value ASC LIMIT p_maxDeviceCount;
         
		-- 20% Lat Desc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id
			  AND l.tenant_id = p_tenant_id			  
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		    ORDER BY lp_lat.property_value DESC LIMIT p_maxDeviceCount;

		-- 20% Long Asc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id	
			  AND l.tenant_id = p_tenant_id		  
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id                   
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		    ORDER BY lp_lon.property_value ASC LIMIT p_maxDeviceCount;
                      
		-- 20% Long Desc
		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon , light_property lp_status, smartpoint smp
			WHERE l.smartpoint_id = smg.smartpoint_id
			  AND l.smartpoint_id = smp.smartpoint_id	
			  AND l.tenant_id = p_tenant_id		  
			  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                          AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id                    
			  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		    ORDER BY lp_lon.property_value DESC LIMIT p_maxDeviceCount;
                   
          
		-- 20% Middle Points
		  
		-- Min Lat
		p_middleLatMin :=  (SELECT MIN(cast(lp_lat.property_value as double precision))
					FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
					WHERE l.smartpoint_id = smg.smartpoint_id
					 AND l.tenant_id = p_tenant_id					 
					 AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
					 AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
					 AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
					 AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);

					  
		-- Max Lat 
		p_middleLatMax :=   (SELECT MAX(cast(lp_lat.property_value as double precision))
					FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
					WHERE l.smartpoint_id = smg.smartpoint_id	
					  AND l.tenant_id = p_tenant_id				  
					  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
					  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
					  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
					  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);

		-- Min Long
		p_middleLonMin :=   (SELECT MIN(cast(lp_lon.property_value as double precision))
					FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
					WHERE l.smartpoint_id = smg.smartpoint_id
					  AND l.tenant_id = p_tenant_id					  
					  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
					  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
					  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
					  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);


		  -- Max Long                                            
		   p_middleLonMax :=   (SELECT MAX(cast(lp_lon.property_value as double precision))
					FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon
					WHERE l.smartpoint_id = smg.smartpoint_id
					  AND l.tenant_id = p_tenant_id					  
					  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
					  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
					  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
					  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon);
		  
		  
		  -- Insert Middle Points 

		RETURN QUERY
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon , light_property lp_status, smartpoint smp
			 WHERE l.smartpoint_id = smg.smartpoint_id
			   AND l.smartpoint_id = smp.smartpoint_id	
			   AND l.tenant_id = p_tenant_id		   
			   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
			   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			   AND cast(lp_lat.property_value as double precision) BETWEEN p_middleLatMin AND p_middleLatMax
			   AND cast(lp_lon.property_value as double precision) BETWEEN p_middleLonMin AND p_middleLonMax;
	  ELSE 
		RETURN QUERY 
			SELECT l.light_id, smp.rni_id,lp_lat.property_value,lp_lon.property_value,lp_status.property_value
			  FROM light l, smartpoint_grouping smg, light_property lp_lat, light_property lp_lon , light_property lp_status, smartpoint smp
			 WHERE l.smartpoint_id = smg.smartpoint_id
			   AND l.smartpoint_id = smp.smartpoint_id
			   AND l.tenant_id = p_tenant_id			   
			   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
			   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			   AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat
			   AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon
		      ORDER BY smp.rni_id ASC;  
	  END IF;   
 END IF;
END

$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
-- Function: get_smartpoints_by_schedule_to_map(integer, double precision, double precision, double precision, double precision, integer)

CREATE FUNCTION get_smartpoints_by_schedule_to_map(IN p_schedule_id integer, IN p_bottomleftlat double precision, IN p_bottomleftlon double precision, IN p_toprightlat double precision, IN p_toprightlon double precision, IN p_maxdevicecount integer)
  RETURNS TABLE(light_id integer, flexnet_id integer, latitude character varying, longitude character varying, light_status character varying) AS
$$
DECLARE 

        p_sectors int;
        p_middleLatMin float;
        p_middleLatMax float;
        p_middleLonMin float;
        p_middleLonMax float;
 
        p_lat_property_id integer;
        p_lon_property_id integer;
        p_current_stat_property_id integer;
 
BEGIN
                
        p_lat_property_id := 16;
        p_lon_property_id := 17;
        p_current_stat_property_id := 81;

        p_sectors := 5;
 
        IF (
        (SELECT COUNT(l.smartpoint_id)
        FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon
        WHERE l.smartpoint_id = sm.smartpoint_id
          AND sm.schedule_id = p_schedule_id
          AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
          AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
          AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
      AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon ) > p_maxDeviceCount) AND (p_maxDeviceCount != 0)THEN
     
       p_maxDeviceCount := (p_maxDeviceCount / p_sectors);

        -- 20% Lat Asc
        RETURN QUERY
			SELECT l.light_id,smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
			  FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
			 WHERE l.smartpoint_id = sm.smartpoint_id
			   AND sm.schedule_id = p_schedule_id
			   AND l.smartpoint_id = smp.smartpoint_id
			   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
			   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
			   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
			   AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
			   AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon 
		   ORDER BY lp_lat.property_value ASC LIMIT p_maxDeviceCount;
		 
        -- 20% Lat Desc
        RETURN QUERY 
                SELECT l.light_id,smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
                  FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
                 WHERE l.smartpoint_id = sm.smartpoint_id
                   AND sm.schedule_id = p_schedule_id
                   AND l.smartpoint_id = smp.smartpoint_id
                   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
                   AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
               AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon 
          ORDER BY lp_lat.property_value DESC LIMIT p_maxDeviceCount;

        -- 20% Long Asc
        RETURN QUERY            
                SELECT l.light_id,smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
                  FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
                 WHERE l.smartpoint_id = sm.smartpoint_id
                   AND l.smartpoint_id = smp.smartpoint_id
                   AND sm.schedule_id = p_schedule_id
                   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
                   AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
           AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon 
          ORDER BY lp_lon.property_value ASC LIMIT p_maxDeviceCount;
                      
        -- 20% Long Desc
        RETURN QUERY             
                SELECT l.light_id,smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
                  FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
                 WHERE l.smartpoint_id = sm.smartpoint_id
                   AND l.smartpoint_id = smp.smartpoint_id
                   AND sm.schedule_id = p_schedule_id
                   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                   AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
                   AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
           AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon 
              ORDER BY lp_lon.property_value DESC LIMIT p_maxDeviceCount;
           
          
        -- 20% Middle Points
         
          -- Min Lat
          p_middleLatMin :=  (SELECT MIN(cast(lp_lat.property_value as double precision))
                                FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon
                                WHERE l.smartpoint_id = sm.smartpoint_id
                                  AND sm.schedule_id = p_schedule_id
                                  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                                  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                                  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
                                  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon );
          -- Max Lat 
          p_middleLatMax :=   (SELECT MAX(cast(lp_lat.property_value as double precision))
                                FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon
                                WHERE l.smartpoint_id = sm.smartpoint_id
                                  AND sm.schedule_id = p_schedule_id
                                  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                                  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                                  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
                                  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon );
          -- Min Long
           p_middleLonMin :=   (SELECT MIN(cast(lp_lon.property_value as double precision))
                                FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon
                                WHERE l.smartpoint_id = sm.smartpoint_id
                                  AND sm.schedule_id = p_schedule_id
                                  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                                  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                                  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
                                  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon );
          -- Max Long                                            
           p_middleLonMax :=   (SELECT MAX(cast(lp_lon.property_value as double precision))
                                FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon
                                WHERE l.smartpoint_id = sm.smartpoint_id
                                  AND sm.schedule_id = p_schedule_id
                                  AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                                  AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
                                  AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
                                  AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon );
           
          
          
          -- Insert Middle Points 
    

         -- INSERT INTO meter_list
        RETURN QUERY               
                SELECT l.light_id,smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
                  FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
                 WHERE l.smartpoint_id = sm.smartpoint_id
                   AND l.smartpoint_id = smp.smartpoint_id
                   AND sm.schedule_id = p_schedule_id
                   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
           AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
                   AND cast(lp_lat.property_value as double precision) BETWEEN p_middleLatMin AND p_middleLatMax 
                   AND cast(lp_lon.property_value as double precision) BETWEEN p_middleLonMin AND p_middleLonMax  
                   LIMIT p_maxDeviceCount;
                    
  ELSE 
        
        RETURN QUERY    
                SELECT l.light_id,smp.rni_id,lp_lat.property_value,lp_lon.property_value, lp_status.property_value
                  FROM light l, schedule_membership sm, light_property lp_lat, light_property lp_lon, light_property lp_status, smartpoint smp
                 WHERE l.smartpoint_id = sm.smartpoint_id
                   AND l.smartpoint_id = smp.smartpoint_id
                   AND sm.schedule_id = p_schedule_id
                   AND lp_lat.property_id = p_lat_property_id AND lp_lat.light_id = l.light_id
                   AND lp_lon.property_id = p_lon_property_id AND lp_lon.light_id = l.light_id
           AND lp_status.property_id = p_current_stat_property_id AND lp_status.light_id = l.light_id
                   AND cast(lp_lat.property_value as double precision) BETWEEN p_bottomLeftLat AND p_topRightLat 
                   AND cast(lp_lon.property_value as double precision) BETWEEN p_bottomLeftLon AND p_topRightLon 
              ORDER BY smp.rni_id ASC;  
    
  END IF;
 
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_group_center(p_group_id integer)
  RETURNS void AS
$$ 
DECLARE
        v_lat_max FLOAT;
        v_lat_min FLOAT;
        v_lon_max FLOAT;
        v_lon_min FLOAT;
        v_middle_lat FLOAT;
        v_middle_long FLOAT;
BEGIN

        v_lat_max := ( SELECT MAX(lp_prt.property_value)
			FROM smartpoint_grouping AS smp_grp
			LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
			LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
			LEFT JOIN light_property AS lp_prt ON l.light_id = lp_prt.light_id
			WHERE lp_prt.property_id = 16 AND smp_grp.grouping_id = p_group_id );
			
        v_lat_min := ( SELECT MIN(lp_prt.property_value)
			FROM smartpoint_grouping AS smp_grp
			LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
			LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
			LEFT JOIN light_property AS lp_prt ON l.light_id = lp_prt.light_id
			WHERE lp_prt.property_id = 16 AND smp_grp.grouping_id = p_group_id );
			
        v_lon_max := ( SELECT MAX(lp_prt.property_value)
			FROM smartpoint_grouping AS smp_grp
			LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
			LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
			LEFT JOIN light_property AS lp_prt ON l.light_id = lp_prt.light_id
			WHERE lp_prt.property_id = 17 AND smp_grp.grouping_id = p_group_id );
			
        v_lon_min := (  SELECT MIN(lp_prt.property_value)
			FROM smartpoint_grouping AS smp_grp
			LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
			LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
			LEFT JOIN light_property AS lp_prt ON l.light_id = lp_prt.light_id
			WHERE lp_prt.property_id = 17 AND smp_grp.grouping_id = p_group_id );


        v_middle_lat  := cast((cast(v_lat_max as numeric) + cast(v_lat_min as numeric) ) / 2 as character varying);
        v_middle_long := cast((cast(v_lon_max as numeric) + cast(v_lon_min as numeric) ) / 2 as character varying);


         UPDATE grouping
        SET 
                longitude = v_middle_long, 
                latitude = v_middle_lat
        WHERE grouping_id=p_group_id;

END
$$
  LANGUAGE 'plpgsql';
----------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_schedule_center(p_schedule_id integer)
  RETURNS void AS
$$ 
DECLARE
        v_lat_max FLOAT;
        v_lat_min FLOAT;
        v_lon_max FLOAT;
        v_lon_min FLOAT;
        v_middle_lat FLOAT;
        v_middle_long FLOAT;
BEGIN

        v_lat_max := (SELECT MAX(latitude)  from vw_maps where schedule_id = p_schedule_id);
        v_lat_min := (SELECT MIN(latitude)  from vw_maps where schedule_id = p_schedule_id);
        v_lon_max := (SELECT MAX(longitude) from vw_maps where schedule_id = p_schedule_id);
        v_lon_min := (SELECT MIN(longitude) from vw_maps where schedule_id = p_schedule_id);


        v_middle_lat  := cast((cast(v_lat_max as numeric) + cast(v_lat_min as numeric) ) / 2 as character varying);
        v_middle_long := cast((cast(v_lon_max as numeric) + cast(v_lon_min as numeric) ) / 2 as character varying);


         UPDATE schedule
        SET 
                longitude = v_middle_long, 
                latitude = v_middle_lat
        WHERE schedule_id = p_schedule_id;

END
$$
  LANGUAGE plpgsql;

  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION del_old_status_message();

CREATE OR REPLACE FUNCTION del_old_status_message()
  RETURNS bigint AS
$$
DECLARE 

  p_date date;
  p_total integer;
        
BEGIN

    p_date := (SELECT CURRENT_DATE - INTERVAL '35 days');
	
    DELETE 
      FROM operational_data_value odv
     WHERE EXISTS (SELECT 1 
                     FROM status_message sm 
                    WHERE sm.status_message_id = odv.status_message_id 
                      AND sm.message_date < p_date);

	DELETE
	  FROM current_alarm_warning_message c
	 WHERE c.status_message_id IN 
	      (SELECT sm.status_message_id
			 FROM status_message sm 
			WHERE sm.status_message_id = c.status_message_id 
			  AND sm.message_date < p_date);
					  
    DELETE 
      FROM status_message_status_subtype smss
     WHERE EXISTS (SELECT 1 
                     FROM status_message sm 
                    WHERE sm.status_message_id = smss.status_message_id 
                      AND sm.message_date < p_date);

    DELETE 
      FROM status_message
     WHERE message_date < p_date;
   
    GET DIAGNOSTICS p_total = ROW_COUNT;
    
    RETURN p_total;

END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_alarms_total(integer, integer, date, date, integer, character varying);

CREATE OR REPLACE FUNCTION get_analytics_alarms_total(p_tenant_id integer, p_group_id integer, p_init_date date, p_end_date date, p_alarm_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
DECLARE

  p_ini_dt timestamp;
  p_end_dt timestamp;
    
BEGIN

  p_ini_dt := (SELECT cast(p_init_date || ' 00:00:00' as timestamp));
  p_end_dt := (SELECT cast(p_end_date || ' 23:59:59' as timestamp));
    
  IF(p_grouping_ids IS NULL) THEN
	
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;
	
	RETURN 
	   (SELECT COALESCE(SUM(aa.value),0)         AS amount
		  FROM analytics_alarms                  aa
			  ,analytics_group_by_date           agbd
					  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(p_alarm_type_id,aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 1))
		   );
	
  ELSE
  
	RETURN 
	   (SELECT COALESCE(SUM(aa.value),0)         AS amount
		  FROM analytics_alarms                  aa
			  ,analytics_group_by_date           agbd
					  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(p_alarm_type_id,aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 1))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))  
		   );
  END IF;
END
$$
  LANGUAGE plpgsql;
  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_warnings_total(integer, integer, date, date, integer, character varying);

CREATE OR REPLACE FUNCTION get_analytics_warnings_total(p_tenant_id integer, p_group_id integer, p_init_date date, p_end_date date, p_alarm_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
DECLARE

  p_ini_dt timestamp;
  p_end_dt timestamp;
  p_analytic_group_id int;
    
BEGIN
  
  p_ini_dt := (SELECT cast(p_init_date||' 00:00:00' as timestamp));
  p_end_dt := (SELECT cast(p_end_date||' 23:59:59' as timestamp));  
  
  IF(p_grouping_ids IS NULL) THEN
	
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	  RETURN 
	   (SELECT COALESCE(SUM(aw.value),0)         AS amount
		  FROM analytics_warnings                aw
			  ,analytics_group_by_date           agbd
			  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(p_alarm_type_id,aw.analytics_warning_subtype)
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 2))
		   );
  ELSE
  
 	  RETURN 
	   (SELECT COALESCE(SUM(aw.value),0)         AS amount
		  FROM analytics_warnings                aw
			  ,analytics_group_by_date           agbd
			  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(p_alarm_type_id,aw.analytics_warning_subtype)
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 2))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,','))) 
		   ); 
		   
    END IF;
END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_consumption_total(integer, integer, timestamp with time zone, timestamp with time zone, integer, character varying);

CREATE OR REPLACE FUNCTION get_analytics_consumption_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp with time zone, p_end_date timestamp with time zone, p_light_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$  
BEGIN
      
  IF(p_grouping_ids IS NULL) THEN
	
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;
  
	RETURN 
	   (SELECT COALESCE(SUM(ac.value),0)       AS amount
		  FROM analytics_consumption           ac
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = p_group_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date 			   BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id  = ac.analytic_group_by_date_id
		   AND ac.light_type_id                = COALESCE(p_light_type_id, ac.light_type_id)
	   );
  
  ELSE
  
	RETURN 
	   (SELECT COALESCE(SUM(ac.value),0)       AS amount
		  FROM analytics_consumption           ac
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date 			   BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id  = ac.analytic_group_by_date_id
		   AND ac.light_type_id                = COALESCE(p_light_type_id, ac.light_type_id)
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))     
	   );
  
  END IF;
   
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_conservation_baseline_total(integer, integer, date, date, integer, character varying);

CREATE OR REPLACE FUNCTION get_analytics_conservation_baseline_total(p_tenant_id integer, p_group_id integer, p_init_date date, p_end_date date, p_light_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
DECLARE
  
  p_ini_dt timestamp;
  p_end_dt timestamp;
  
BEGIN

  p_ini_dt := (SELECT cast(p_init_date||' 00:00:00' as timestamp));
  p_end_dt := (SELECT cast(p_end_date||' 23:59:59' as timestamp));
  
  IF(p_grouping_ids IS NULL) THEN
  
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;
  
	RETURN 
	   (SELECT COALESCE(SUM(ac.value),0)         AS amount
		  FROM analytics_conservation_baseline   ac
			  ,analytics_group_by_date           agbd
			  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = ac.analytic_group_by_date_id
		   AND ac.light_type_id                     = COALESCE(p_light_type_id, ac.light_type_id)
		);
  
  ELSE
  
	RETURN 
	   (SELECT COALESCE(SUM(ac.value),0)         AS amount
		  FROM analytics_conservation_baseline   ac
			  ,analytics_group_by_date           agbd
			  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = ac.analytic_group_by_date_id
		   AND ac.light_type_id                     = COALESCE(p_light_type_id, ac.light_type_id)
		   AND ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,','))
		);
		
   END IF;
END
 $$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_status_message_by_id(IN p_status_message_id integer)
  RETURNS TABLE(status_message_id integer, message_date timestamp with time zone, status integer) AS
$$
BEGIN

  RETURN QUERY 
        SELECT s.status_message_id
                  ,s.message_date
                  ,s.status_id
     FROM status_message s
    WHERE s.status_message_id = p_status_message_id;
END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_installed_by_date(character varying, integer, integer, date, date, character varying);

CREATE OR REPLACE FUNCTION get_analytics_installed_by_date(IN p_date_type_id character varying, IN p_tenant_id integer, IN p_group_id integer, IN p_init_date date, IN p_end_date date, p_grouping_ids character varying)
  RETURNS TABLE(description character varying, date_time timestamp without time zone, amount bigint) AS
$$
DECLARE

    p_all_label_key character varying;
    p_all_group_id integer;
    p_day_amount integer;
    p_month_amount integer; 
      p_ini_dt TIMESTAMP;
    p_end_dt TIMESTAMP;
    
BEGIN

    p_all_label_key = 'sensus.mlc.status_subtype.all';
    p_day_amount := 0;
    p_month_amount := 0;

    IF p_date_type_id IN ('1d','3d','1w','1m') THEN
        p_day_amount := (SELECT p_end_date - p_init_date ) + 1;
    ELSIF p_date_type_id IN ('3m') THEN 
                p_month_amount := 2;
        ELSIF p_date_type_id IN ('ytd','1y') THEN  
        p_month_amount := (SELECT p_end_date - p_init_date ) / 30;
    END IF;

    p_all_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
    
    IF p_group_id IS NULL THEN
		p_group_id := p_all_group_id;
    END IF;
    
    IF(p_grouping_ids IS NULL) THEN
    
		    IF p_date_type_id IN ('3m','ytd','1y') THEN
			
			p_ini_dt := (SELECT date_trunc('month',p_init_date));
			p_end_dt := (SELECT(SELECT p_init_date + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');
		   
			FOR i IN 0..p_month_amount LOOP
			   
			    RETURN QUERY 
			    SELECT tb1.description AS description
				  ,CAST(tb1.date_time AS timestamp without time zone) AS date_time
				  ,CAST(SUM(tb1.amount) AS bigint) AS amount
			      FROM (  SELECT alt.label_key                           AS description
					   ,DATE_TRUNC('MONTH',agbd.analytic_date) AS date_time
					   ,ai.value AS amount
				       FROM analytics_light_type           alt
					   ,analytics_installed            ai
					   ,analytics_group_by_date        agbd
					   ,analytics_group                ag
				      WHERE ag.tenant_id                   = p_tenant_id
					AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									 FROM analytics_group ag1 
									WHERE ag1.analytic_group_id != p_all_group_id
									  AND ag1.tenant_id = p_tenant_id
									  AND p_group_id IS NULL
									UNION ALL
								       SELECT p_group_id )
					AND ag.analytic_group_id           = agbd.analytic_group_id
					AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
					AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
					AND ai.light_type_id               = alt.light_type_id
				      UNION ALL
				     SELECT p_all_label_key                        AS description
					   ,DATE_TRUNC('MONTH',agbd.analytic_date)   AS date_time
					   ,ai.value AS amount
				       FROM analytics_installed            ai
					   ,analytics_group_by_date        agbd
					   ,analytics_group                ag
				      WHERE ag.tenant_id                   = p_tenant_id
					AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									 FROM analytics_group ag1 
									WHERE ag1.analytic_group_id != p_all_group_id
									  AND ag1.tenant_id = p_tenant_id
									  AND p_group_id IS NULL
									UNION ALL
								       SELECT p_group_id )
					AND ag.analytic_group_id           = agbd.analytic_group_id
					AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
					AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
				      UNION ALL                
				     SELECT label_key AS description
					   ,p_ini_dt AS date_time
					   ,0 AS amount
				       FROM analytics_light_type 
				      UNION ALL
				     SELECT p_all_label_key AS description
					   ,p_ini_dt AS date_time
					   ,0 AS amount
				  ) AS tb1
			    GROUP BY tb1.description,CAST(tb1.date_time AS timestamp without time zone)
			    ORDER BY 2,1;
			    
			    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 MONTH');
			    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');

			END LOOP;
		    
		    ELSIF p_date_type_id = '1d' THEN
		    
			p_ini_dt := (SELECT p_init_date);
			p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 HOUR') - INTERVAL '1 SECOND');
			    
			FOR i IN 0..23 LOOP
				    
			    RETURN QUERY 
				SELECT tb1.description
					  ,CAST(tb1.date_time AS timestamp without time zone) AS date_time
					  ,CAST(SUM(tb1.amount) AS bigint) AS amount
				FROM (
					SELECT label_key AS description
						,p_ini_dt AS date_time
						,0 AS amount
					FROM analytics_light_type
					UNION ALL
					SELECT p_all_label_key
					       ,p_ini_dt AS date_time
					       ,0 AS amount
					UNION ALL                                
					SELECT alt.label_key AS description
						,DATE_TRUNC('HOUR',agbd.analytic_date) AS date_time
						,ai.value AS amount
					FROM analytics_light_type alt
						,analytics_installed ai
						,analytics_group_by_date agbd
						,analytics_group ag
					WHERE ag.tenant_id = p_tenant_id
					   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									    FROM analytics_group ag1 
									   WHERE ag1.analytic_group_id != p_all_group_id
									     AND ag1.tenant_id = p_tenant_id
									     AND p_group_id IS NULL
									   UNION ALL
									  SELECT p_group_id )
						AND ag.analytic_group_id = agbd.analytic_group_id
						AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
						AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
						AND ai.light_type_id = alt.light_type_id
					UNION ALL
					SELECT p_all_label_key AS description
						,DATE_TRUNC('HOUR',agbd.analytic_date) AS date_time
						,ai.value AS amount
					FROM analytics_installed ai
						,analytics_group_by_date agbd
						,analytics_group ag
					WHERE ag.tenant_id = p_tenant_id
					   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									    FROM analytics_group ag1 
									   WHERE ag1.analytic_group_id != p_all_group_id
									     AND ag1.tenant_id = p_tenant_id
									     AND p_group_id IS NULL
									   UNION ALL
									  SELECT p_group_id )
						AND ag.analytic_group_id = agbd.analytic_group_id
						AND agbd.analytic_date between p_ini_dt and p_end_dt
						AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id) AS tb1					
				GROUP BY tb1.description, CAST(tb1.date_time AS timestamp without time zone)
				ORDER BY 2,1;
				
			    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 HOUR');
			    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 HOUR') - INTERVAL '1 SECOND');

			END LOOP;

		    ELSE

			p_ini_dt := (SELECT p_init_date);
			p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');
			
			FOR i IN 1..p_day_amount LOOP
			    
			    RETURN QUERY 
				SELECT tb1.description AS description
				      ,CAST(tb1.date_time AS timestamp without time zone) AS date_time
				      ,CAST(SUM(tb1.amount) AS bigint) AS amount
				  FROM (
			  
					 SELECT alt.label_key                           AS description
						  ,DATE_TRUNC('DAY',agbd.analytic_date)    AS date_time
						  ,ai.value AS amount
					   FROM analytics_light_type           alt
						  ,analytics_installed            ai
						  ,analytics_group_by_date        agbd
						  ,analytics_group                ag
					 WHERE ag.tenant_id                   = p_tenant_id
					   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									    FROM analytics_group ag1 
									   WHERE ag1.analytic_group_id != p_all_group_id
									     AND ag1.tenant_id = p_tenant_id
									     AND p_group_id IS NULL
									   UNION ALL
									  SELECT p_group_id )
					   AND ag.analytic_group_id           = agbd.analytic_group_id
					   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
					   AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
					   AND ai.light_type_id               = alt.light_type_id
					UNION ALL
					SELECT p_all_label_key                        AS description
						  ,DATE_TRUNC('DAY',agbd.analytic_date)   AS date_time
						  ,ai.value AS amount
					  FROM analytics_installed            ai
						  ,analytics_group_by_date        agbd
						  ,analytics_group                ag
					 WHERE ag.tenant_id                   = p_tenant_id
					   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									    FROM analytics_group ag1 
									   WHERE ag1.analytic_group_id != p_all_group_id
									     AND ag1.tenant_id = p_tenant_id
									     AND p_group_id IS NULL
									   UNION ALL
									  SELECT p_group_id )
					   AND ag.analytic_group_id           = agbd.analytic_group_id
					   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
					   AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
					UNION ALL                
					SELECT label_key AS description
						   ,p_ini_dt AS date_time
						   ,0 AS amount
					   FROM analytics_light_type 
					UNION ALL
					SELECT p_all_label_key AS description
						   ,p_ini_dt AS date_time
						   ,0 AS amount) AS tb1
				GROUP BY tb1.description,CAST(tb1.date_time AS timestamp without time zone)
				ORDER BY 2,1;

			    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 DAY');
			    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');
			   
			END LOOP;
			
		    END IF;
   ELSE
			IF p_date_type_id IN ('3m','ytd','1y') THEN
			
			p_ini_dt := (SELECT date_trunc('month',p_init_date));
			p_end_dt := (SELECT(SELECT p_init_date + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');
		   
			FOR i IN 0..p_month_amount LOOP
			   
			    RETURN QUERY 
			    SELECT tb2.description, tb2.date_time, CAST(sum(tb2.amount) AS bigint) AS amount
		              FROM(
				   SELECT tb1.description AS description
					  ,CAST(tb1.date_time AS timestamp without time zone) AS date_time
					  ,CAST(SUM(tb1.amount) AS bigint) AS amount
					  ,tb1.grouping_id
				     FROM (  SELECT alt.label_key                           AS description
						   ,DATE_TRUNC('MONTH',agbd.analytic_date) AS date_time
						   ,ai.value AS amount
						   ,COALESCE(ag.grouping_id,0) AS grouping_id
					       FROM analytics_light_type           alt
						   ,analytics_installed            ai
						   ,analytics_group_by_date        agbd
						   ,analytics_group                ag
					      WHERE ag.tenant_id                   = p_tenant_id
						AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
										 FROM analytics_group ag1 
										WHERE ag1.analytic_group_id != p_all_group_id
										  AND ag1.tenant_id = p_tenant_id
										  AND p_group_id IS NULL
										UNION ALL
									       SELECT p_group_id )
						AND ag.analytic_group_id           = agbd.analytic_group_id
						AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
						AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
						AND ai.light_type_id               = alt.light_type_id
					      UNION ALL
					     SELECT p_all_label_key                        AS description
						   ,DATE_TRUNC('MONTH',agbd.analytic_date)   AS date_time
						   ,ai.value AS amount
						   ,COALESCE(ag.grouping_id,0) AS grouping_id
					       FROM analytics_installed            ai
						   ,analytics_group_by_date        agbd
						   ,analytics_group                ag
					      WHERE ag.tenant_id                   = p_tenant_id
						AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
										 FROM analytics_group ag1 
										WHERE ag1.analytic_group_id != p_all_group_id
										  AND ag1.tenant_id = p_tenant_id
										  AND p_group_id IS NULL
										UNION ALL
									       SELECT p_group_id )
						AND ag.analytic_group_id           = agbd.analytic_group_id
						AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
						AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
					      UNION ALL                
					     SELECT label_key AS description
						   ,p_ini_dt AS date_time
						   ,0 AS amount
						   ,0 AS grouping_id
					       FROM analytics_light_type 
					      UNION ALL
					     SELECT p_all_label_key AS description
						   ,p_ini_dt AS date_time
						   ,0 AS amount
						   ,0 AS grouping_id) AS tb1
				   WHERE (grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
			           GROUP BY tb1.description,CAST(tb1.date_time AS TIMESTAMP WITHOUT TIME ZONE), tb1.grouping_id
			           ORDER BY 2,1) tb2
			    GROUP BY tb2.description, tb2.date_time 
		            ORDER BY 2,1;  
				    
				    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 MONTH');
				    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');

				END LOOP;
			    
			    ELSIF p_date_type_id = '1d' THEN
			    
				p_ini_dt := (SELECT p_init_date);
				p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 HOUR') - INTERVAL '1 SECOND');
				    
				FOR i IN 0..23 LOOP
					    
				    RETURN QUERY 
				    SELECT tb2.description, tb2.date_time, CAST(sum(tb2.amount) AS bigint) AS amount
		                      FROM(
					SELECT tb1.description
						  ,CAST(tb1.date_time AS timestamp without time zone) AS date_time
						  ,CAST(SUM(tb1.amount) AS integer) AS amount
						  ,tb1.grouping_id
					FROM (

						SELECT label_key AS description
							,p_ini_dt AS date_time
							,0 AS amount
							,0 AS grouping_id
						FROM analytics_light_type

						UNION ALL
						SELECT p_all_label_key
						       ,p_ini_dt AS date_time
						       ,0
						       ,0 as grouping_id                                
						UNION ALL                                
						SELECT alt.label_key AS description
							,DATE_TRUNC('HOUR',agbd.analytic_date) AS date_time
							,ai.value AS amount
							,COALESCE(ag.grouping_id,0) AS grouping_id
						FROM analytics_light_type alt
							,analytics_installed ai
							,analytics_group_by_date agbd
							,analytics_group ag
						WHERE ag.tenant_id = p_tenant_id
						   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
										    FROM analytics_group ag1 
										   WHERE ag1.analytic_group_id != p_all_group_id
										     AND ag1.tenant_id = p_tenant_id
										     AND p_group_id IS NULL
										   UNION ALL
										  SELECT p_group_id )
							AND ag.analytic_group_id = agbd.analytic_group_id
							AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
							AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
							AND ai.light_type_id = alt.light_type_id
						
						UNION ALL

						SELECT p_all_label_key AS description
							,DATE_TRUNC('HOUR',agbd.analytic_date) AS date_time
							,ai.value AS amount
							,COALESCE(ag.grouping_id,0) AS grouping_id
						FROM analytics_installed ai
							,analytics_group_by_date agbd
							,analytics_group ag
						WHERE ag.tenant_id = p_tenant_id
						   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
										    FROM analytics_group ag1 
										   WHERE ag1.analytic_group_id != p_all_group_id
										     AND ag1.tenant_id = p_tenant_id
										     AND p_group_id IS NULL
										   UNION ALL
										  SELECT p_group_id )
							AND ag.analytic_group_id = agbd.analytic_group_id
							AND agbd.analytic_date between p_ini_dt and p_end_dt
							AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id) AS tb1
					WHERE (grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
			                GROUP BY tb1.description,CAST(tb1.date_time AS TIMESTAMP WITHOUT TIME ZONE), tb1.grouping_id
					ORDER BY 2,1) tb2
			            GROUP BY tb2.description, tb2.date_time 
				    ORDER BY 2,1;  
					
				    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 HOUR');
				    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 HOUR') - INTERVAL '1 SECOND');

				END LOOP;
					
			    ELSE
				
				p_ini_dt := (SELECT p_init_date);
				p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');
				
				FOR i IN 1..p_day_amount LOOP
				    
				    RETURN QUERY 
				    SELECT tb2.description, tb2.date_time, CAST(sum(tb2.amount) AS bigint) AS amount
		                      FROM(
					SELECT tb1.description AS description
					      ,CAST(tb1.date_time AS timestamp without time zone) AS date_time
					      ,CAST(SUM(tb1.amount) AS integer) AS amount
					      ,tb1.grouping_id
					FROM (
					SELECT alt.label_key                           AS description
						  ,DATE_TRUNC('DAY',agbd.analytic_date)    AS date_time
						  ,ai.value AS amount
						  ,COALESCE(ag.grouping_id,0) AS grouping_id
					  FROM analytics_light_type           alt
						  ,analytics_installed            ai
						  ,analytics_group_by_date        agbd
						  ,analytics_group                ag
					 WHERE ag.tenant_id                   = p_tenant_id
					   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									    FROM analytics_group ag1 
									   WHERE ag1.analytic_group_id != p_all_group_id
									     AND ag1.tenant_id = p_tenant_id
									     AND p_group_id IS NULL
									   UNION ALL
									  SELECT p_group_id )
					   AND ag.analytic_group_id           = agbd.analytic_group_id
					   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
					   AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
					   AND ai.light_type_id               = alt.light_type_id
					UNION ALL
					SELECT p_all_label_key                        AS description
						  ,DATE_TRUNC('DAY',agbd.analytic_date)   AS date_time
						  ,ai.value AS amount
						  ,COALESCE(ag.grouping_id,0) AS grouping_id
					  FROM analytics_installed            ai
						  ,analytics_group_by_date        agbd
						  ,analytics_group                ag
					 WHERE ag.tenant_id                   = p_tenant_id
					   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
									    FROM analytics_group ag1 
									   WHERE ag1.analytic_group_id != p_all_group_id
									     AND ag1.tenant_id = p_tenant_id
									     AND p_group_id IS NULL
									   UNION ALL
									  SELECT p_group_id )
					   AND ag.analytic_group_id           = agbd.analytic_group_id
					   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
					   AND agbd.analytic_group_by_date_id = ai.analytic_group_by_date_id
					UNION ALL                
					SELECT label_key AS description
						   ,p_ini_dt AS date_time
						   ,0 AS amount
						   ,0 AS grouping_id
					   FROM analytics_light_type 
					UNION ALL
					SELECT p_all_label_key AS description
						   ,p_ini_dt AS date_time
						   ,0 AS amount
						   ,0 AS grouping_id) AS tb1
					WHERE (grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
					GROUP BY tb1.description,CAST(tb1.date_time AS TIMESTAMP WITHOUT TIME ZONE), tb1.grouping_id
					ORDER BY 2,1) tb2
				   GROUP BY tb2.description, tb2.date_time 
				   ORDER BY 2,1;  

				    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 DAY');
				    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');
				   
				END LOOP;
				
			    END IF;

   END IF;
    
  RETURN;
  
END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_consumption_by_date(character varying, integer, integer, date, date, character varying);

CREATE OR REPLACE FUNCTION get_analytics_consumption_by_date(IN p_date_type_id character varying, IN p_tenant_id integer, IN p_group_id integer, IN p_init_date date, IN p_end_date date, IN p_grouping_ids character varying)
  RETURNS TABLE(description character varying, date_time timestamp without time zone, amount numeric) AS
$$
DECLARE

    p_all_label_key character varying;
    p_all_group_id integer;
    p_day_amount integer;
    p_month_amount integer;
    p_ini_dt TIMESTAMP;
    p_end_dt TIMESTAMP;
    
BEGIN
  
    p_all_label_key = 'sensus.mlc.status_subtype.all';
    p_day_amount := 0;
    p_month_amount := 0;
    
    IF p_date_type_id IN ('1d','3d','1w','1m') THEN
        p_day_amount := (SELECT p_end_date - p_init_date ) + 1;
    ELSIF p_date_type_id IN ('3m') THEN 
                p_month_amount := 2;
        ELSIF p_date_type_id IN ('ytd','1y') THEN         
        p_month_amount := (SELECT p_end_date - p_init_date ) / 30;
    END IF;

    p_all_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
    
    IF p_group_id IS NULL THEN
	   p_group_id := p_all_group_id;
    END IF;

   IF(p_grouping_ids IS NULL) THEN
   
	    IF p_date_type_id IN ('3m','ytd','1y') THEN

		p_ini_dt := (SELECT date_trunc('month',p_init_date));
		p_end_dt := (SELECT(SELECT p_init_date + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');
		
		FOR i IN 0..p_month_amount LOOP
		    
		    RETURN QUERY 
					      
			SELECT tb1.description AS description
				,CAST(tb1.date_time as timestamp without time zone) AS date_time
				,(COALESCE(ROUND(CAST(CAST(SUM(tb1.amount) AS real)as numeric),3),0)/1000) AS amount
			  FROM (
				SELECT alt.label_key AS description
					,DATE_TRUNC('MONTH',agbd.analytic_date) AS date_time
					,ac.value AS amount
				FROM analytics_light_type alt
					,analytics_consumption ac
					,analytics_group_by_date agbd
					,analytics_group ag
				WHERE ag.tenant_id = p_tenant_id
				   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
								    FROM analytics_group ag1 
								   WHERE ag1.analytic_group_id != p_all_group_id
								     AND ag1.tenant_id = p_tenant_id
								     AND p_group_id IS NULL
								   UNION ALL
								  SELECT p_group_id )
				    AND ag.analytic_group_id = agbd.analytic_group_id
				    AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
				    AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id
				    AND ac.light_type_id = alt.light_type_id
				UNION ALL
				SELECT p_all_label_key AS description
					,DATE_TRUNC('MONTH',agbd.analytic_date) AS date_time
					,ac.value AS amount
				FROM analytics_consumption ac
					,analytics_group_by_date agbd
					,analytics_group ag
				WHERE ag.tenant_id = p_tenant_id
				   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
								    FROM analytics_group ag1 
								   WHERE ag1.analytic_group_id != p_all_group_id
								     AND ag1.tenant_id = p_tenant_id
								     AND p_group_id IS NULL
								   UNION ALL
								  SELECT p_group_id )
				    AND ag.analytic_group_id = agbd.analytic_group_id
				    AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
				    AND agbd.analytic_group_by_date_id  = ac.analytic_group_by_date_id
				UNION ALL                
				SELECT label_key AS description
					,p_ini_dt AS date_time
					,0 AS amount
				FROM analytics_light_type 
				UNION ALL
				SELECT p_all_label_key
					,p_ini_dt
					,0 AS amount) AS tb1
			GROUP BY tb1.description ,CAST(tb1.date_time AS timestamp without time zone)
			ORDER BY 2,1;

		    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 MONTH');
		    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');

		END LOOP;
					    
	    ELSE         
		
		p_ini_dt := (SELECT p_init_date);
		p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');
		
		FOR i IN 1..p_day_amount LOOP
				
		    RETURN QUERY
				      
			SELECT tb1.description AS description
				,CAST(tb1.date_time as timestamp without time zone) AS date_time
				,(COALESCE(ROUND(CAST(CAST(SUM(tb1.amount) AS real)as numeric),3),0)/1000) AS amount						
			  FROM (			
				SELECT alt.label_key AS description
					,DATE_TRUNC('DAY',agbd.analytic_date) AS date_time
					,ac.value AS amount
				FROM analytics_light_type alt
					,analytics_consumption ac
					,analytics_group_by_date agbd
					,analytics_group ag
				WHERE ag.tenant_id = p_tenant_id
				   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
								    FROM analytics_group ag1 
								   WHERE ag1.analytic_group_id != p_all_group_id
								     AND ag1.tenant_id = p_tenant_id
								     AND p_group_id IS NULL
								   UNION ALL
								  SELECT p_group_id )
				    AND ag.analytic_group_id = agbd.analytic_group_id
				    AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
				    AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id
				    AND ac.light_type_id = alt.light_type_id
				 
				UNION ALL
				
				SELECT p_all_label_key AS description
					,DATE_TRUNC('DAY',agbd.analytic_date) AS date_time
					,ac.value AS amount
				  FROM analytics_consumption ac
					,analytics_group_by_date agbd
					,analytics_group ag
				WHERE ag.tenant_id = p_tenant_id
				   AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
								    FROM analytics_group ag1 
								   WHERE ag1.analytic_group_id != p_all_group_id
								     AND ag1.tenant_id = p_tenant_id
								     AND p_group_id IS NULL
								   UNION ALL
								  SELECT p_group_id )
					AND ag.analytic_group_id = agbd.analytic_group_id
					AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
					AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id
				 
				UNION ALL                
				SELECT label_key AS description
					,p_ini_dt AS date_time
					,0 AS amount
				FROM analytics_light_type 
				UNION ALL
				SELECT p_all_label_key AS description
					,p_ini_dt AS date_time
					,0 AS amount) AS tb1
			GROUP BY tb1.description ,CAST(tb1.date_time AS timestamp without time zone)
			ORDER BY 2,1;
		     
		    p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 DAY');
		    p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');

		END LOOP;

	    END IF;
  ELSE
      IF p_date_type_id IN ('3m','ytd','1y') THEN

	p_ini_dt := (SELECT date_trunc('month',p_init_date));
	p_end_dt := (SELECT(SELECT p_init_date + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');
		
	FOR i IN 0..p_month_amount LOOP
		    
	   RETURN QUERY 
           SELECT tb2.description, tb2.date_time, CAST(sum(tb2.amount) AS numeric) AS amount
	     FROM(
	       SELECT tb1.description AS description
                        ,CAST(tb1.date_time as timestamp without time zone) AS date_time
                        ,(COALESCE(ROUND(CAST(CAST(SUM(tb1.amount) AS real)as numeric),3),0)/1000) AS amount
                        ,tb1.grouping_id                
                FROM (
                        
                        SELECT alt.label_key AS description
                                ,DATE_TRUNC('MONTH',agbd.analytic_date) AS date_time
                                ,ac.value AS amount
                                ,COALESCE(ag.grouping_id,0) AS grouping_id
                        FROM analytics_light_type alt
                                ,analytics_consumption ac
                                ,analytics_group_by_date agbd
                                ,analytics_group ag
                        WHERE ag.tenant_id = p_tenant_id
                           AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
                                                            FROM analytics_group ag1 
                                                           WHERE ag1.analytic_group_id != p_all_group_id
                                                             AND ag1.tenant_id = p_tenant_id
                                                             AND p_group_id IS NULL
                                                           UNION ALL
                                                          SELECT p_group_id )
                            AND ag.analytic_group_id = agbd.analytic_group_id
                            AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
                            AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id
                            AND ac.light_type_id = alt.light_type_id
                        UNION ALL
                        SELECT p_all_label_key AS description
                                ,DATE_TRUNC('MONTH',agbd.analytic_date) AS date_time
                                ,ac.value AS amount
                                ,COALESCE(ag.grouping_id,0) AS grouping_id
                        FROM analytics_consumption ac
                                ,analytics_group_by_date agbd
                                ,analytics_group ag
                        WHERE ag.tenant_id = p_tenant_id
                           AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
                                                            FROM analytics_group ag1 
                                                           WHERE ag1.analytic_group_id != p_all_group_id
                                                             AND ag1.tenant_id = p_tenant_id
                                                             AND p_group_id IS NULL
                                                           UNION ALL
                                                          SELECT p_group_id )
                            AND ag.analytic_group_id = agbd.analytic_group_id
                            AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
                            AND agbd.analytic_group_by_date_id  = ac.analytic_group_by_date_id
                        UNION ALL                
                        SELECT label_key AS description
                                ,p_ini_dt AS date_time
                                ,0 AS amount
                                ,0 AS grouping_id
                        FROM analytics_light_type 
                        UNION ALL
                        SELECT p_all_label_key
                                ,p_ini_dt
                                ,0 
                                ,0 AS grouping_id) AS tb1
                WHERE (grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		GROUP BY tb1.description,CAST(tb1.date_time AS TIMESTAMP WITHOUT TIME ZONE), tb1.grouping_id
		ORDER BY 2,1) tb2
	   GROUP BY tb2.description, tb2.date_time 
	   ORDER BY 2,1;       
                
           p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 MONTH');
           p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 MONTH') - INTERVAL '1 SECOND');

        END LOOP;
                                    
     ELSE         
        
        p_ini_dt := (SELECT p_init_date);
        p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');
        
        FOR i IN 1..p_day_amount LOOP
                        
            RETURN QUERY
            SELECT tb2.description, tb2.date_time, CAST(sum(tb2.amount) AS numeric) AS amount
	      FROM(                  
                SELECT tb1.description AS description
                        ,CAST(tb1.date_time as timestamp without time zone) AS date_time
                        ,(COALESCE(ROUND(CAST(CAST(SUM(tb1.amount) AS real)as numeric),3),0)/1000) AS amount
                        ,tb1.grouping_id                                        
                FROM (
                
                        SELECT alt.label_key AS description
                                ,DATE_TRUNC('DAY',agbd.analytic_date) AS date_time
                                ,ac.value AS amount
                                ,COALESCE(ag.grouping_id,0) AS grouping_id
                        FROM analytics_light_type alt
                                ,analytics_consumption ac
                                ,analytics_group_by_date agbd
                                ,analytics_group ag
                        WHERE ag.tenant_id = p_tenant_id
                           AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
                                                            FROM analytics_group ag1 
                                                           WHERE ag1.analytic_group_id != p_all_group_id
                                                             AND ag1.tenant_id = p_tenant_id
                                                             AND p_group_id IS NULL
                                                           UNION ALL
                                                          SELECT p_group_id )
                            AND ag.analytic_group_id = agbd.analytic_group_id
                            AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
                            AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id
                            AND ac.light_type_id = alt.light_type_id
                         
                        UNION ALL
                        
                        SELECT p_all_label_key AS description
                                ,DATE_TRUNC('DAY',agbd.analytic_date) AS date_time
                                ,ac.value AS amount
                                ,COALESCE(ag.grouping_id,0) AS grouping_id
                        FROM analytics_consumption ac
                                ,analytics_group_by_date agbd
                                ,analytics_group ag
                        WHERE ag.tenant_id = p_tenant_id
                           AND ag.analytic_group_id IN (  SELECT ag1.analytic_group_id 
                                                            FROM analytics_group ag1 
                                                           WHERE ag1.analytic_group_id != p_all_group_id
                                                             AND ag1.tenant_id = p_tenant_id
                                                             AND p_group_id IS NULL
                                                           UNION ALL
                                                          SELECT p_group_id )
                                AND ag.analytic_group_id = agbd.analytic_group_id
                                AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
                                AND agbd.analytic_group_by_date_id = ac.analytic_group_by_date_id
                         
                        UNION ALL                
                        SELECT label_key AS description
                                ,p_ini_dt AS date_time
                                ,0 AS amount
                                ,0 AS grouping_id
                        FROM analytics_light_type 
                        UNION ALL
                        SELECT p_all_label_key AS description
                                ,p_ini_dt AS date_time
                                ,0 AS amount
                                ,0 AS grouping_id) AS tb1
                WHERE (grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
	        GROUP BY tb1.description,CAST(tb1.date_time AS TIMESTAMP WITHOUT TIME ZONE), tb1.grouping_id
	        ORDER BY 2,1) tb2
	   GROUP BY tb2.description, tb2.date_time 
	   ORDER BY 2,1;  
             
            p_ini_dt := (SELECT p_ini_dt + INTERVAL '1 DAY');
            p_end_dt := (SELECT (SELECT p_ini_dt + INTERVAL '1 DAY') - INTERVAL '1 SECOND');

        END LOOP;

    END IF;

   END IF;

   RETURN;
  
END
$$
  LANGUAGE plpgsql;

  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_tenant_by_server_name(p_server_name character varying)
  RETURNS TABLE(tenant_id integer, "name" character varying, description character varying, rni_code character varying, gateway_rni_location character varying,latitude double precision, longitude double precision) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.tenant_id
          ,t.name
          ,t.description
          ,t.rni_code
          ,t.gateway_rni_location
          ,t.latitude
          ,t.longitude
    FROM tenant t
    WHERE trim(t.server_name) = trim(p_server_name);
END
$$
  LANGUAGE plpgsql;
  
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION calculate_dashboard_resume(double precision, integer);

CREATE OR REPLACE FUNCTION calculate_dashboard_resume(p_carbon_credits_factor double precision, p_tenant_id integer, p_group_id integer)
  RETURNS character varying AS
$$
DECLARE
    p_cursor_conservation refcursor;
    p_rec_conservation RECORD;
    p_cursor_grouping_id refcursor;
    p_rec_grouping_id RECORD;

    p_grouping_id CHARACTER VARYING;
    p_lighting_alarm_id CHARACTER VARYING;
    p_lighting_warning_id CHARACTER VARYING;
    p_lighting_installed_id CHARACTER VARYING;
    p_conservation_consumption_id CHARACTER VARYING;
    p_conservation_energy_savings_id CHARACTER VARYING;
    p_conservation_carbon_credits_id CHARACTER VARYING;
	
    p_init_date TIMESTAMP;
    p_end_date TIMESTAMP;
    p_user CHARACTER VARYING;
    p_date TIMESTAMP;

    p_array_view_mode CHARACTER VARYING ARRAY;
BEGIN
    -- Defining procedure constants.
    p_user := 'sysuser';
    p_lighting_alarm_id := '1';
    p_lighting_warning_id := '2';
    p_lighting_installed_id := '3';
    p_conservation_consumption_id := '4';
    p_conservation_energy_savings_id := '5';
    p_conservation_carbon_credits_id := '6';

    p_date := CURRENT_TIMESTAMP;

    p_array_view_mode[0]:= 'week';
    p_array_view_mode[1]:= 'month';
	
		   FOR i IN 0..array_upper(p_array_view_mode, 1) LOOP

			   IF (p_array_view_mode[i] = 'week') THEN
					   p_init_date := (SELECT DATE_TRUNC('day',CURRENT_TIMESTAMP - INTERVAL '1 week'))::timestamp with time zone;
			   ELSIF (p_array_view_mode[i] = 'month') THEN
					   p_init_date := (SELECT DATE_TRUNC('day',CURRENT_TIMESTAMP - INTERVAL '1 month'))::timestamp with time zone;
			   END IF;

			   p_end_date := (SELECT DATE_TRUNC('day',CURRENT_TIMESTAMP) - INTERVAL '1 second');
			   
			   INSERT INTO dashboard_resume (tenant_id, view_mode, analytics_type, grouping_id, VALUE, average, change, trends, create_user, create_date)
			   SELECT
				   p_tenant_id AS tenant_id,
				   p_array_view_mode[i] AS view_mode,
				   p_lighting_alarm_id AS analytics_type,
				   p_group_id AS grouping_id,
				   get_analytics_alarms_total(p_tenant_id, NULL, p_init_date, p_end_date, NULL, CAST(p_group_id AS CHARACTER VARYING)) AS VALUE,
				   get_analytics_average(p_tenant_id, NULL, p_array_view_mode[i], p_lighting_alarm_id, p_carbon_credits_factor, CAST(p_group_id AS CHARACTER VARYING)) AS average,
				   get_analytics_change(p_tenant_id, NULL, p_array_view_mode[i], p_lighting_alarm_id, p_carbon_credits_factor, CAST(p_group_id AS CHARACTER VARYING)) AS change,
				   get_analytics_trend(p_tenant_id, p_array_view_mode[i], p_lighting_alarm_id, p_carbon_credits_factor, NULL, CAST(p_group_id AS CHARACTER VARYING)) AS trends,
				   p_user,
			   	   p_date
			   UNION ALL
			   SELECT
				   p_tenant_id,
				   p_array_view_mode[i],
				   p_lighting_warning_id,
				   p_group_id AS grouping_id,
				   get_analytics_warnings_total(p_tenant_id, NULL, p_init_date, p_end_date, NULL, CAST(p_group_id AS CHARACTER VARYING)),
				   get_analytics_average(p_tenant_id, NULL, p_array_view_mode[i], p_lighting_warning_id, p_carbon_credits_factor, CAST(p_group_id AS CHARACTER VARYING)),
				   get_analytics_change(p_tenant_id, NULL, p_array_view_mode[i], p_lighting_warning_id, p_carbon_credits_factor, CAST(p_group_id AS CHARACTER VARYING)),
				   get_analytics_trend(p_tenant_id, p_array_view_mode[i], p_lighting_warning_id, p_carbon_credits_factor, NULL, CAST(p_group_id AS CHARACTER VARYING)),
				   p_user,
				   p_date
			   UNION ALL
			   SELECT
				   p_tenant_id,
				   p_array_view_mode[i],
				   p_lighting_installed_id,
				   p_group_id AS grouping_id,
				   get_analytics_installed_total(p_tenant_id, NULL, p_init_date, p_end_date, NULL, CAST(p_group_id AS CHARACTER VARYING)),
				   get_analytics_average(p_tenant_id, NULL, p_array_view_mode[i], p_lighting_installed_id, p_carbon_credits_factor, CAST(p_group_id AS CHARACTER VARYING)),
				   get_analytics_change(p_tenant_id, NULL, p_array_view_mode[i], p_lighting_installed_id, p_carbon_credits_factor, CAST(p_group_id AS CHARACTER VARYING)),
				   get_analytics_trend(p_tenant_id, p_array_view_mode[i], p_lighting_installed_id, p_carbon_credits_factor, NULL, CAST(p_group_id AS CHARACTER VARYING)),
				   p_user,
		      		   p_date;
		   END LOOP;
		   INSERT INTO dashboard_resume_chart (tenant_id, grouping_id, status_subtype_id, amount)
		   SELECT p_tenant_id,
		          p_group_id, * FROM get_analytics_alarms_by_type (p_tenant_id, NULL, p_init_date, p_end_date, NULL, CAST(p_group_id AS CHARACTER VARYING))
		   UNION ALL
		   SELECT p_tenant_id,
		          p_group_id, * FROM get_analytics_warnings_by_type (p_tenant_id, NULL, p_init_date, p_end_date, NULL, CAST(p_group_id AS CHARACTER VARYING));


	RETURN 'success';
END;
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_trend(integer, character varying, character varying, double precision);

CREATE OR REPLACE FUNCTION get_analytics_trend(p_tenant_id integer, p_view_mode_id character varying, p_view_type_id character varying, p_carbon_credits_factor double precision, p_analytics_group_id integer, p_grouping_ids character varying)
  RETURNS character varying AS
$$
DECLARE
	p_init_date DATE;
	p_day_amount INT;
	p_return CHARACTER VARYING;
BEGIN
	p_return := '';

	IF (p_view_mode_id = 'week') THEN
		p_day_amount := 7;
	ELSIF (p_view_mode_id = 'month') THEN
		p_day_amount := 30;
  	END IF;

	p_init_date := (SELECT CURRENT_DATE - (p_day_amount));

	FOR i IN 0..p_day_amount - 1  LOOP

		-- Alarms.
		IF (p_view_type_id = '1') THEN
			p_return := p_return || (SELECT get_analytics_alarms_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Warnings.
		ELSIF (p_view_type_id = '2') THEN
			p_return := p_return || (SELECT get_analytics_warnings_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';        
		-- Installed.
		ELSIF (p_view_type_id = '3') THEN
			p_return := p_return || (SELECT get_analytics_installed_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Consumption.
		ELSIF (p_view_type_id = '4') THEN
			p_return := p_return || (SELECT get_analytics_consumption_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Energy savings.
		ELSIF (p_view_type_id = '5') THEN
			p_return := p_return || (SELECT get_analytics_energy_savings_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Carbon credits.
		ELSIF (p_view_type_id = '6') THEN
			p_return := p_return || (SELECT get_analytics_carbon_credits_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, p_carbon_credits_factor, NULL, p_grouping_ids)) || ',';
		END IF;

		p_init_date := (SELECT p_init_date + 1);

	END LOOP;

	p_return := (SELECT SUBSTRING(p_return FROM 0 FOR CHAR_LENGTH(p_return)));

	RETURN p_return;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------  
DROP FUNCTION get_analytics_installed_total(integer, integer, date, date, integer, character varying);

CREATE OR REPLACE FUNCTION get_analytics_installed_total(p_tenant_id integer, p_group_id integer, p_init_date date, p_end_date date, p_light_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
DECLARE
  
  p_ini_dt timestamp;
  p_end_dt timestamp;
  
BEGIN

  p_ini_dt := (SELECT cast(p_init_date ||' 00:00:00' as timestamp));
  p_end_dt := (SELECT cast(p_end_date ||' 23:59:59' as timestamp));

  IF(p_grouping_ids IS NULL) THEN
	
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;
  
	RETURN 
	   (SELECT COALESCE(SUM(ai.value),0)       AS amount
		  FROM analytics_installed             ai
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = p_group_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date              BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id  = ai.analytic_group_by_date_id
		   AND ai.light_type_id                = COALESCE(p_light_type_id, ai.light_type_id)
		   );
		   
  ELSE

	RETURN 
	   (SELECT COALESCE(SUM(ai.value),0)       AS amount
		  FROM analytics_installed             ai
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date              BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id  = ai.analytic_group_by_date_id
		   AND ai.light_type_id                = COALESCE(p_light_type_id, ai.light_type_id)
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		   );
  
  END IF;
END
$$
  LANGUAGE plpgsql;
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION trigger_analytics_consumption_add_operational_data_value();

CREATE OR REPLACE FUNCTION trigger_analytics_consumption_add_operational_data_value()
  RETURNS trigger AS
$$
DECLARE
    
    v_last_consumption double precision;
    v_delta_consumption double precision;
    v_op_data_type_consumption integer;
    v_property_light_source integer;
    v_refcursor refcursor;
    v_record RECORD;
    v_analytic_group_id integer;
    v_analytic_group_by_date_id integer;
    v_lamp_type varchar(4094);
    v_light_type_id integer;
    v_status_message RECORD;
    v_property_consumption integer;
    v_rni_id integer;
    v_new_date Date;
    
BEGIN

    v_op_data_type_consumption := 1;
    v_property_light_source := 51;
    v_property_consumption := 87;

	v_new_date := current_timestamp;

    IF (NEW.operational_data_type_id = v_op_data_type_consumption) THEN
    
        SELECT light_id ,tenant_id ,message_date
          INTO v_status_message
          FROM status_message 
         WHERE status_message_id = NEW.status_message_id;

        -- get the previcous consumption (right before the actual message)
        v_last_consumption := (SELECT value AS endValue 
                                 FROM operational_data_value odv 
                                     ,status_message sm 
                                WHERE sm.light_id = v_status_message.light_id 
                                  AND odv.create_date != v_new_date
                                  AND sm.status_message_id = odv.status_message_id
                                  AND odv.operational_data_type_id = v_op_data_type_consumption 
                                ORDER BY odv.create_date desc LIMIT 1);

       v_lamp_type := (SELECT property_value FROM light_property WHERE property_id = v_property_light_source AND light_id = v_status_message.light_id);

       IF v_lamp_type = 'I' THEN
	      v_lamp_type = 'INDUCTION';
       ELSIF v_lamp_type = 'L' THEN
	      v_lamp_type = 'LED';
       ELSE
	      v_lamp_type = 'OTHER';
       END IF;
      
      v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = v_lamp_type);
      
      v_refcursor := get_cursor_groups_for_light(v_status_message.light_id);

       v_delta_consumption := NEW.value - COALESCE(v_last_consumption,0);
              
        IF COALESCE(v_last_consumption,0) > 0 THEN         
          
            IF COALESCE(v_delta_consumption,0) > 0 THEN 
              LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;
                                                                                                    
                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name AND tenant_id = v_status_message.tenant_id);
                                                    
                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);
                                                                                    
                -- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
                PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);
                                                                                                    
              END LOOP;
                                    
              v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND analytics_group.tenant_id = v_status_message.tenant_id);

              v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);

              PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);

                          v_rni_id := (SELECT smp.rni_id FROM light l, smartpoint smp WHERE l.light_id = v_status_message.light_id AND smp.smartpoint_id = l.smartpoint_id);

                          -- Update the comsumption property value
              PERFORM upsert_light_property(v_property_consumption, v_rni_id, CAST(v_delta_consumption AS character varying), NEW.create_user);
                  
	         -------------------------UPDATE LIGHT DAILY CONSUMPTION---------------------------------
			 PERFORM upsert_light_daily_consumption(v_status_message.light_id, v_delta_consumption);				  
                  
            END IF;
	    ELSE		       	
			  
				LOOP
				FETCH v_refcursor INTO v_record;
				EXIT WHEN NOT FOUND;
																									
					-- Use group_name to retrieve analytic_group_id.
					v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name AND tenant_id = v_status_message.tenant_id);
														
					-- Get the analytic_group_by_date_id.
					v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);
																						
					-- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
					PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);
																									
				END LOOP;
									
				 v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND analytics_group.tenant_id = v_status_message.tenant_id);

			     v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);

				 PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);

					v_rni_id := (SELECT smp.rni_id FROM light l, smartpoint smp WHERE l.light_id = v_status_message.light_id AND smp.smartpoint_id = l.smartpoint_id);

						 -- Update the comsumption property value
				 PERFORM upsert_light_property(v_property_consumption, v_rni_id, CAST(v_delta_consumption AS character varying), NEW.create_user);
				  
				 -------------------------UPDATE LIGHT DAILY CONSUMPTION---------------------------------
				 PERFORM upsert_light_daily_consumption(v_status_message.light_id, NEW.value );
									
        END IF;
        	
    END IF;
    RETURN NEW;
END;
$$
  LANGUAGE plpgsql;
 
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_upd_light_property()
  RETURNS trigger AS
$$
        DECLARE
			v_property_light_source int;
			v_analytic_group_id int;
			v_analytic_group_by_date_id int;
			v_old_light_type_id int;
			v_new_light_type_id int;
			v_refcursor refcursor;
			v_record RECORD;
			v_tenant_id int;
			v_property_ac_voltage int;
			v_property_ac_current int;
			v_property_comsumption int;
			v_property_firmware int;
			v_user character varying;
			BEGIN
		
				v_property_light_source:= 51;
				v_property_ac_voltage:= 19;
				v_property_ac_current:= 20;
				v_property_comsumption:= 87;
				v_property_firmware:= 15;
				v_user:= NEW.create_user;
						
				IF (NEW.property_id = v_property_light_source )
				THEN
						BEGIN
										
								v_tenant_id := (SELECT tenant_id FROM light WHERE light_id = NEW.light_id);
																
								-- Get the analytic_group_by_date_id
								v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND tenant_id = v_tenant_id);
								v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user);
								
								
								IF (NEW.property_value = 'I')
								THEN
										v_new_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION');
								END IF;

								IF (NEW.property_value = 'L')
								THEN
										v_new_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'LED');
								END IF;
								
								IF (NEW.property_value = 'O')
								THEN
										v_new_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'OTHER');
								END IF;
								
								IF (OLD.property_value = 'I')
								THEN
										v_old_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION');
								END IF;

								IF (OLD.property_value = 'L')
								THEN
										v_old_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'LED');
								END IF;

								IF (OLD.property_value = 'O')
								THEN
										v_old_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'OTHER');
								END IF;
								
								PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_new_light_type_id, NEW.create_user,'+');
								
								PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_old_light_type_id, NEW.create_user,'-');
								
								v_refcursor := get_cursor_groups_for_light(NEW.light_id);
								
								LOOP
										FETCH v_refcursor INTO v_record;
										EXIT WHEN NOT FOUND;
										
										-- Use group_name to retrieve analytic_group_id.
										v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name);
										-- Get the analytic_group_by_date_id.
										v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user));
						
										-- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
										PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_new_light_type_id, NEW.create_user, '+');
										
										PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_old_light_type_id, NEW.create_user, '-');
												
								END LOOP;
						END;
				END IF;

				-- Update min and max values when voltage, current, or consumption is changed
				IF (NEW.property_id = v_property_ac_voltage OR NEW.property_id = v_property_ac_current OR NEW.property_id = v_property_comsumption)
				THEN
						BEGIN
								PERFORM upd_min_max(NEW.property_id, NEW.property_value, NEW.light_id);
						END;
				END IF;

				-- When the firmware version value is updated, insert it into valid value table
				IF (NEW.property_id = v_property_firmware) 
					THEN
						BEGIN
						IF v_user is null THEN
							v_user:= NEW.modify_user;
						END IF;
					
						PERFORM ins_firmware_version(NEW.property_id, NEW.property_value, v_user);
					END;
				END IF;
						
				RETURN null;
		END;
$$
  LANGUAGE plpgsql;
  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_min_max(p_property_id integer, p_property_value character varying, p_light_id integer)
  RETURNS void AS
$$
        DECLARE
                        v_property_ac_voltage int;
                        v_property_ac_current int;
                        v_property_comsumption int;
                                         
                        v_property_max int;
                        v_property_min int;
                                        
                        v_property_value_min character varying(4094);
                        v_property_value_max character varying(4094);
                               
        BEGIN
            v_property_ac_voltage:= 19;
                        v_property_ac_current:= 20;
                        v_property_comsumption:= 87;
                        
                        IF (p_property_id = v_property_ac_voltage) 
                        THEN 
                                v_property_min:= 83;
                                v_property_max:= 84; 
                        END IF;
                                
                        IF (p_property_id = v_property_ac_current) 
                        THEN 
                                v_property_min:= 85;
                                v_property_max:= 86;
                        END IF;
                                
                        IF (p_property_id = v_property_comsumption) 
                        THEN 
                                v_property_min:= 88;
                                v_property_max:= 89;
                                
                        END IF;
                                                                          
                        v_property_value_min:= (SELECT property_value FROM light_property WHERE light_id = p_light_id AND property_id = v_property_min);
                        v_property_value_max:= (SELECT property_value FROM light_property WHERE light_id = p_light_id AND property_id = v_property_max);

                        IF (CAST(p_property_value AS integer) < CAST(v_property_value_min AS integer))
                        THEN
                                BEGIN
                                        
                                        UPDATE light_property SET property_value = p_property_value WHERE light_id = p_light_id AND property_id = v_property_min;

                                END;
                        END IF;

                        IF (CAST(p_property_value AS integer) > CAST(v_property_value_max AS integer)) 
                        THEN
                                BEGIN
                                        
                                        UPDATE light_property SET property_value = p_property_value WHERE light_id = p_light_id AND property_id = v_property_max;
                                        
                                END;
                        END IF; 
                        
        END;
$$
  LANGUAGE plpgsql;
 
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_min_max(p_property_id integer, p_property_value character varying, p_light_id integer, p_create_user character varying)
  RETURNS void AS
$$
        DECLARE
                        v_property_ac_voltage int;
                        v_property_ac_current int;
                        v_property_comsumption int;
                                 
                        v_property_max int;
                        v_property_min int;
                               
        BEGIN
                        v_property_ac_voltage:= 19;
                        v_property_ac_current:= 20;
                        v_property_comsumption:= 87;
                
                        IF (p_property_id = v_property_ac_voltage) 
                        THEN 
                                v_property_min:= 83;
                                v_property_max:= 84; 
                        END IF;
                                
                        IF (p_property_id = v_property_ac_current) 
                        THEN 
                                v_property_min:= 85;
                                v_property_max:= 86;
                        END IF;
                                
                        IF (p_property_id = v_property_comsumption) 
                        THEN 
                                v_property_min:= 88;
                                v_property_max:= 89;
                                
                        END IF;
                                                  
                        INSERT INTO light_property (property_value, create_user, light_id, property_id) 
                                 VALUES (p_property_value, p_create_user, p_light_id, v_property_min)
                                                ,(p_property_value, p_create_user, p_light_id, v_property_max);

                
        END;
$$
  LANGUAGE plpgsql;
 
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : When lamp type added, add to the count for that lamp type and remove from the count for type ='Other' (for all groups the light belongs to)
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_ins_light_property()
  RETURNS trigger AS
$$
        DECLARE
                v_property_light_source int;
                v_analytic_group_id int;
                v_analytic_group_by_date_id int;
                v_light_type_other int;
                v_light_type_id int;
                v_tenant_id int;
                v_refcursor refcursor;
                v_record RECORD;
                v_property_ac_voltage int;
                v_property_ac_current int;
                v_property_consumption int;
                v_property_firmware int;
        BEGIN
                v_property_light_source:= 51;
                v_property_ac_voltage:= 19;
                v_property_ac_current:= 20;
                v_property_consumption:= 87;
                v_property_firmware:= 15;
                                                
                IF (NEW.property_id = v_property_light_source )
                THEN
                        BEGIN
                        
                                v_tenant_id := (SELECT tenant_id FROM light WHERE light_id = NEW.light_id);
                                                                
                                -- Get the analytic_group_by_date_id
                                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND tenant_id = v_tenant_id);
                                v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user);
                                                                                                
                                IF (NEW.property_value = 'I')
                                THEN
                                                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION');
                                END IF;

                                IF (NEW.property_value = 'L')
                                THEN
                                                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'LED');
                                END IF;
                                                                                                
                                IF (NEW.property_value = 'O')
                                THEN
                                                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'OTHER');
                                END IF;
                                
                                
                                -- Add 1 to count for type = lamp_type for current_date, Group='All'
                                -- first try to update the key
                                PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user,'+');

                                -- Add 1 to count for type = lamp_type for current_date, Group = <all groups light belong to>
                                v_refcursor := get_cursor_groups_for_light(NEW.light_id);
                                
                                LOOP
                                                FETCH v_refcursor INTO v_record;
                                                EXIT WHEN NOT FOUND;
                                                
                                                -- Use group_name to retrieve analytic_group_id.
                                                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name);
                                                -- Get the analytic_group_by_date_id.
                                                v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user));
                                
                                                -- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
                                                -- first try to update the key
                                                PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user,'+');

                                END LOOP;
                                                        
                        END;
                END IF;

                -- Insert min and max values when voltage, current, or consumption is inserted
                IF (NEW.property_id = v_property_ac_voltage OR NEW.property_id = v_property_ac_current OR NEW.property_id = v_property_consumption)
                THEN
                        BEGIN
                                PERFORM ins_min_max(NEW.property_id, NEW.property_value, NEW.light_id, NEW.create_user);
                        END;
                END IF;

				-- When a new firmware version value is inserted, insert it into valid value table
                IF (NEW.property_id = v_property_firmware) 
                THEN
					BEGIN
						PERFORM ins_firmware_version(NEW.property_id, NEW.property_value, NEW.create_user);
					END;
                END IF;
                                
                RETURN null;
    END;
$$
  LANGUAGE plpgsql;
  
  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_update_light_status_subtype()
  RETURNS trigger AS
$$
DECLARE

  p_property_id_status_subtype integer;
  p_rni_id integer;
  p_subtype_id integer;

BEGIN

    -- current light status subtype
    p_property_id_status_subtype := 82;
    
    p_rni_id := (SELECT s.rni_id
                   from smartpoint s
                       ,light l 
					   ,status_message sm
                  where sm.status_message_id = NEW.status_message_id
				    and sm.light_id = l.light_id
                    and l.smartpoint_id = s.smartpoint_id);

   PERFORM upsert_light_property(p_property_id_status_subtype, p_rni_id, cast(NEW.status_subtype_id as character varying), NEW.create_user);

    RETURN NEW;
    
END;
$$
  LANGUAGE plpgsql;
 
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION reset_min_max(p_light_id integer, p_modify_user character varying)
  RETURNS void AS
$$
        DECLARE

                v_property_voltage_min int;                              
                v_property_voltage_max int;
                v_property_current_min int;
                v_property_current_max int;
                v_property_consumption_min int;         
                v_property_consumption_max int;
                v_reset_value character varying;
                                                               
        BEGIN
                        
                v_property_voltage_min:= 83;
                v_property_voltage_max:= 84; 
                v_property_current_min:= 85;
                v_property_current_max:= 86;
                v_property_consumption_min:= 88;
                v_property_consumption_max:= 89;
                v_reset_value = '0';
                        
                UPDATE light_property 
                SET property_value = v_reset_value
                    ,modify_user = p_modify_user
                WHERE light_id = p_light_id 
                AND property_id IN (v_property_voltage_min, v_property_voltage_max, v_property_current_min, v_property_current_max, v_property_consumption_min, v_property_consumption_max);
                        
        END;
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_user_settings(IN p_user_screen_name character varying, IN p_tenant_id integer)
  RETURNS TABLE(property_id integer, property_name character varying, property_value character varying) AS
$$
DECLARE
  p_language int; 
  p_time_zone int;
  p_date_format int;
  p_monitor_request int;
  p_convert_energy_unit int;
  p_page_size int;
  p_page_size_preferences int;
BEGIN
  p_language := 37;   
  p_time_zone := 38;
  p_date_format := 39;
  p_monitor_request := 40;
  p_convert_energy_unit := 54;
  p_page_size := 90;
  p_page_size_preferences := 101;
RETURN QUERY

  SELECT u_prop.property_id
        ,prop.property_name
        ,u_prop.user_setting_property_value
    FROM property prop
        ,user_settings_property  u_prop
        ,users usr 
        ,user_settings u_sett      
   WHERE u_sett.tenant_id = p_tenant_id
     AND u_sett.username = usr.username 
     AND usr.username = p_user_screen_name   
     AND u_sett.user_settings_id = u_prop.user_settings_id
     AND u_prop.property_id = prop.property_id
     AND u_prop.property_id IN (p_language,p_time_zone,p_date_format,p_monitor_request,p_convert_energy_unit,p_page_size,p_page_size_preferences)
   UNION ALL
  SELECT u_prop.property_id
        ,prop.property_name
        ,u_prop.user_setting_property_value 
    FROM property prop
        ,user_settings_property  u_prop
        ,user_settings u_sett      
   WHERE u_sett.tenant_id = p_tenant_id
     AND u_sett.username is null
     AND u_sett.user_settings_id = u_prop.user_settings_id
     AND u_prop.property_id = prop.property_id
     AND u_prop.property_id IN (p_language,p_time_zone,p_date_format,p_monitor_request,p_convert_energy_unit,p_page_size,p_page_size_preferences)
     AND NOT EXISTS (SELECT 1
                      FROM users usr 
                          ,user_settings u_sett1
                      WHERE u_sett1.tenant_id = p_tenant_id
                       AND u_sett1.username = usr.username 
                       AND usr.username    = p_user_screen_name);
END
$$
  LANGUAGE plpgsql;

  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_system_settings(IN p_tenant_id integer)
  RETURNS TABLE(property_id integer, property_name character varying, property_value character varying) AS
$$
DECLARE
    p_language int; 
    p_time_zone int;
    p_date_format int;
    p_convert_energy_unit int;
    p_page_size int;
        p_page_size_preferences int;
BEGIN
    p_language := 37;   
    p_time_zone := 38;
    p_date_format := 39;
    p_convert_energy_unit := 54;
    p_page_size := 90;
        p_page_size_preferences := 101;
    
    RETURN QUERY 
    SELECT u_prop.property_id, prop.property_name, u_prop.user_setting_property_value
      FROM user_settings_property u_prop
          ,user_settings u_sett
          ,property prop
     WHERE u_sett.tenant_id = p_tenant_id
       AND u_sett.username is null
       AND u_sett.user_settings_id = u_prop.user_settings_id
       AND u_prop.property_id in (p_language,p_time_zone,p_date_format,p_convert_energy_unit,p_page_size,p_page_size_preferences)
       AND prop.property_id = u_prop.property_id;
END
$$
LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE FUNCTION ins_firmware_version(p_property_id integer, p_property_value character varying, p_user character varying)
  RETURNS void AS
$$
DECLARE 
        v_value character varying;
BEGIN
        v_value = (SELECT valid_value FROM property_valid_value WHERE property_id = p_property_id AND valid_value = p_property_value);
               
        IF v_value is null THEN
           INSERT INTO property_valid_value (valid_value, create_user, property_id) values (p_property_value, p_user, p_property_id);
        END IF;
END;
$$
  LANGUAGE plpgsql;

  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_dup_status_message(p_light_id integer)
  RETURNS character varying AS
$$
DECLARE 

	p_cursor refcursor;
	p_rec RECORD;
	p_max_id integer;

BEGIN

	OPEN p_cursor FOR 
	  SELECT light_id
			,status_id
			,message_type
			,date_trunc('hour',message_date) date_time
			,COUNT(1)
	    FROM status_message 
	   WHERE light_id = coalesce(p_light_id,light_id)
	   GROUP BY light_id
			,status_id
			,message_type
			,date_trunc('hour',message_date)
	  HAVING COUNT(1) > 1
	   ORDER BY 1,3,2 DESC;
	   
	LOOP
		FETCH p_cursor INTO p_rec;
		EXIT WHEN NOT FOUND;

		p_max_id = (SELECT MAX(status_message_id) 
                      FROM status_message 
                     WHERE light_id = p_rec.light_id
                       AND status_id = p_rec.status_id
					   AND message_type = p_rec.message_type
                       AND date_trunc('hour',message_date) = p_rec.date_time);

		DELETE 
		  FROM operational_data_value
		 WHERE status_message_id in ( SELECT status_message_id 
										FROM status_message 
									   WHERE light_id = p_rec.light_id 
									     AND status_id = p_rec.status_id 
										 AND message_type = p_rec.message_type
										 AND date_trunc('hour',message_date) = p_rec.date_time
										 AND status_message_id != p_max_id);					   
		DELETE 
		  FROM status_message_status_subtype 
		 WHERE status_message_id in ( SELECT status_message_id 
										FROM status_message 
									   WHERE light_id = p_rec.light_id 
									     AND status_id = p_rec.status_id 
										 AND message_type = p_rec.message_type
										 AND date_trunc('hour',message_date) = p_rec.date_time
										 AND status_message_id != p_max_id);
		
		DELETE 
		  FROM status_message 
         WHERE light_id = p_rec.light_id
           AND status_id = p_rec.status_id
		   AND message_type = p_rec.message_type
           AND date_trunc('hour',message_date) = p_rec.date_time
           AND status_message_id != p_max_id;
	
	END LOOP;

	RETURN 'success';
                         
END;
$$
  LANGUAGE plpgsql;

  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_light_history(IN p_light_id integer, IN p_tenant_id integer, IN p_start_row integer, IN p_page_size integer)
  RETURNS TABLE(id integer, create_date timestamp with time zone, "name" character varying, description character varying, status boolean, create_user character varying) AS
$$
DECLARE
    p_type_alarm int;   
    p_type_warning int;
    p_smartpoint_id int;
BEGIN
    p_type_alarm := 1;  
    p_type_warning := 2;
    p_smartpoint_id := (SELECT smartpoint_id FROM light WHERE light_id = p_light_id);
    
    
    RETURN QUERY 

    SELECT process_id as id, date as create_date, history_name as name, history_desc as description, status_process As status, user_name as create_user

    FROM (

        SELECT ROW_NUMBER() OVER(ORDER BY date DESC) as RowNum
                ,lh.*

        FROM (

            SELECT  null AS process_id
                    ,s.message_date AS date
                    ,(CASE s.status_id WHEN p_type_alarm THEN CAST('Alarm' AS character varying) WHEN p_type_warning THEN CAST('Warning' AS character varying) END ) AS history_name
                    ,(SELECT s1.label_key
                        FROM status_subtype s1,
                             status_message_status_subtype s2,
                             status_message s3
                        WHERE s3.status_message_id = s.status_message_id
                        AND s1.status_subtype_id = s2.status_subtype_id
                        AND s2.status_message_id = s3.status_message_id) AS history_desc
		    ,null AS status_process
                    ,s.create_user AS user_name
                        
            FROM status_message s
            WHERE s.light_id = p_light_id 
                AND (s.status_id = p_type_alarm OR s.status_id = p_type_warning)
            
            UNION ALL

            SELECT  p.process_id AS process_id
                   ,p.create_date AS date
                   ,p.description AS history_name
                   ,p.lc_action_description AS history_desc
				   ,p.is_process_complete AS status_process
                   ,p.create_user AS user_name
              FROM process p
                   ,smartpoint_process sp
             WHERE sp.smartpoint_id = p_smartpoint_id
                   AND sp.process_id  = p.process_id
                   AND p.tenant_id = p_tenant_id ) AS lh

        ) AS light_history

     WHERE RowNum BETWEEN p_start_row + 1 AND (p_start_row + p_page_size);
                  
END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_status_message_by_light(IN p_light_id integer)
  RETURNS TABLE(status_message_id integer, message_date timestamp with time zone, status_id integer, message_type integer) AS
$$
BEGIN

        RETURN QUERY SELECT
             s.status_message_id
            ,s.message_date
            ,s.status_id
            ,s.message_type
        FROM status_message s
        WHERE s.light_id = p_light_id
        ORDER BY s.status_message_id DESC LIMIT 1 ;
END
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_status_message_by_light_message_type(IN p_light_id integer, IN p_message_type_id integer)
  RETURNS TABLE(status_message_id integer, message_date timestamp with time zone, status_id integer, message_type integer) AS
$$
BEGIN
        RETURN QUERY SELECT
             s.status_message_id
            ,s.message_date
            ,s.status_id
            ,s.message_type
        FROM status_message s
        WHERE s.light_id = p_light_id
          AND s.message_type = p_message_type_id
        ORDER BY s.message_date DESC LIMIT 1;
END
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_schedule(IN p_schedule_id integer)
  RETURNS TABLE(schedule_id integer, smartpoint_count bigint, name character varying, description character varying, create_date timestamp with time zone, schedule_type smallint, sunrise_offset integer, sunset_offset integer, intensity integer) AS
$$
BEGIN
    RETURN QUERY
    SELECT s.schedule_id
          ,(SELECT COUNT(1) from schedule_membership where schedule_membership.schedule_id = s.schedule_id) as smartpoint_count
                  ,s.name
                  ,s.description
                  ,s.create_date
                  ,s.schedule_type 
                  ,s.sunrise_offset
                  ,s.sunset_offset
                  ,s.intensity
        FROM schedule s
        WHERE s.schedule_id = p_schedule_id;
END  
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_average_light_voltage(p_light_id integer, p_initial_date timestamp with time zone, p_end_date timestamp with time zone)
  RETURNS real AS
$$
DECLARE
    p_type_voltage int;
    v_end_date date;
BEGIN
    p_type_voltage := 3;
    v_end_date := CAST(p_end_date + CAST('1 days' AS interval) AS DATE);        
        RETURN (
        SELECT AVG(value) AS value 
        FROM operational_data_value odv INNER JOIN status_message sm 
        ON odv.status_message_id = sm.status_message_id
        WHERE sm.light_id = p_light_id 
        AND odv.create_date < v_end_date
        AND odv.create_date >= p_initial_date 
        AND odv.operational_data_type_id = p_type_voltage);
END
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_average_light_current(p_light_id integer, p_initial_date timestamp with time zone, p_end_date timestamp with time zone)
  RETURNS real AS
$$
DECLARE
    p_type_current int;
    v_end_date date;    
BEGIN
    p_type_current := 2;
    v_end_date := CAST(p_end_date + CAST('1 days' AS interval) AS DATE);
        RETURN (
        SELECT AVG(value) AS value 
        FROM operational_data_value odv 
        INNER JOIN status_message sm ON odv.status_message_id = sm.status_message_id
        WHERE sm.light_id = p_light_id 
        AND odv.create_date < v_end_date
        AND odv.create_date >= p_initial_date 
        AND odv.operational_data_type_id = p_type_current);
END
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_custom_search_count_by_name(integer, character varying, character varying);
CREATE OR REPLACE FUNCTION get_custom_search_count_by_name(p_tenant_id integer, p_custom_search_name character varying, p_username character varying)
  RETURNS bigint AS
$$
BEGIN
        RETURN (SELECT COUNT(1) FROM custom_search WHERE custom_search_name = p_custom_search_name and username = p_username and tenant_id = p_tenant_id);
END
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_is_light_in_tenant(p_rni_id integer, p_rni_code character varying)
  RETURNS integer AS
$$    
BEGIN
      IF ((SELECT COUNT(1) from tenant where rni_code = p_rni_code) > 0)
      THEN
        IF ((SELECT COUNT(1) from smartpoint where rni_id = p_rni_id) > 0)
        THEN
            IF ((SELECT COUNT(1) from smartpoint where rni_id = p_rni_id and tenant_id = (SELECT tenant_id from tenant where rni_code = p_rni_code)) > 0)
            THEN
                RETURN 0;
            ELSE
                RETURN -1; -- smartpoint does not belong to this tenant.
            END IF;
        ELSE
            RETURN -2; -- RniId not found.
        END IF;
    ELSE
        RETURN -3; -- Tenant not found.
    END IF;
END
$$
LANGUAGE 'plpgsql';


------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_count_conservation_by_tag(p_tag_id integer)
RETURNS bigint AS
$$
BEGIN
    RETURN (SELECT COUNT(1)
    FROM conservation_baseline
    WHERE tag_id = p_tag_id);
END
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_tag_pagination_total_rows(p_tenant_id integer)
  RETURNS bigint AS
$$
BEGIN
      RETURN (SELECT count(1) FROM (SELECT tenant_id FROM tag WHERE tenant_id = p_tenant_id) AS TAGPAGE); 
END
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_groups_by_smartpoint(IN p_smartpoint_id integer, IN p_tenant_id integer)
  RETURNS TABLE(grouping_id integer, "name" character varying, description character varying, create_date timestamp with time zone, latitude double precision, longitude double precision, smartpoint_count bigint) AS
$$
BEGIN
    RETURN QUERY       
    SELECT g.grouping_id
          ,g.name
          ,g.description
          ,g.create_date
          ,g.latitude
          ,g.longitude
          ,(SELECT COUNT(1)
              FROM smartpoint_grouping sg1
             WHERE sg1.grouping_id = g.grouping_id) as smartpoint_count
    FROM grouping g
        ,smartpoint_grouping sg
    WHERE sg.smartpoint_id = p_smartpoint_id
      AND sg.grouping_id   = g.grouping_id 
      AND g.tenant_id      = p_tenant_id;
END
$$
  LANGUAGE plpgsql;
  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_groups_by_light(IN p_light_id integer, IN p_tenant_id integer)
RETURNS TABLE(grouping_id integer, "name" character varying, description character varying, create_date timestamp with time zone, latitude double precision, longitude double precision, smartpoint_count bigint) AS
$$
BEGIN
    RETURN QUERY       
    SELECT  grouping.grouping_id
            ,grouping.name
            ,grouping.description
            ,grouping.create_date
            ,grouping.latitude
            ,grouping.longitude,
                        (SELECT COUNT(1)
        FROM smartpoint_grouping
        WHERE smartpoint_grouping.grouping_id = grouping.grouping_id) as smartpoint_count
    FROM grouping, smartpoint_grouping, light 
    WHERE light.light_id = p_light_id AND smartpoint_grouping.grouping_id = grouping.grouping_id AND smartpoint_grouping.smartpoint_id = light.smartpoint_id AND light.tenant_id = p_tenant_id AND grouping.tenant_id = p_tenant_id;
END
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_group_count_by_name (p_tenant_id int , p_group_name  varchar(100))
RETURNS bigint AS
$$
BEGIN
     RETURN (SELECT COUNT(1) from grouping where grouping.name = p_group_name and grouping.tenant_id = p_tenant_id);
END
$$ 
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_count_light_groups (p_smartpoint_id int) 
RETURNS bigint   
AS 
$$
BEGIN
      RETURN (SELECT COUNT(1) from smartpoint_grouping where smartpoint_grouping.smartpoint_id = p_smartpoint_id); 
END
$$ 
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_count_running_lrp(p_property_id integer, p_value character varying)
RETURNS bigint AS
$$
BEGIN
        RETURN (SELECT COUNT(1)
        FROM process p, process_property pp
        WHERE p.is_process_complete = false
        AND p.process_id = pp.process_id
        AND pp.property_id = p_property_id
        AND pp.value = p_value);
END
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_is_light_in_group (p_smartpoint_id int , p_group_id int)   
RETURNS bigint AS 
$$
BEGIN
    RETURN (SELECT COUNT(1) FROM smartpoint_grouping WHERE smartpoint_grouping.smartpoint_id = p_smartpoint_id AND smartpoint_grouping.grouping_id = p_group_id);
END
$$ 
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION apply_unknown_offset_schedule(p_rni_id integer, p_schedule_id integer, p_create_user character varying)
RETURNS void AS
$$ 
DECLARE
    p_smartpoint_id int;
    p_schedule_type int;
    p_undetermined_offset_type int;
BEGIN
    p_smartpoint_id := (SELECT smartpoint_id FROM smartpoint WHERE rni_id = p_rni_id);
    p_schedule_type := 1;
    p_undetermined_offset_type := 3;
        IF  EXISTS (SELECT create_date ,modified_date ,create_user ,modify_user ,schedule_id ,smartpoint_id FROM schedule_membership, schedule WHERE smartpoint_id = p_smartpoint_id and schedule_membership.schedule_id = schedule.schedule_id and (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_offset_type))
        THEN
                        UPDATE schedule_membership SET schedule_id = p_schedule_type,
                        modify_user = p_create_user
                        FROM schedule
                        WHERE schedule_membership.smartpoint_id = p_smartpoint_id
                        AND schedule_membership.schedule_id = schedule.schedule_id
                        AND (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_offset_type);
        ELSE
                        INSERT  INTO schedule_membership
           (schedule_id
           ,smartpoint_id
           ,create_user)
                        VALUES

           (p_schedule_type
           ,p_smartpoint_id
           ,p_create_user);
        END IF;
END          
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION apply_unknown_event_schedule(p_rni_id integer, p_schedule_id integer, p_create_user character varying)
RETURNS void AS
$$ 
DECLARE 
    p_smartpoint_id int;
    p_schedule_type int;
    p_undetermined_event_type int;
BEGIN
    p_smartpoint_id := (SELECT smartpoint_id FROM smartpoint WHERE rni_id = p_rni_id);
    p_schedule_type := 2;
    p_undetermined_event_type := 4;
    IF  EXISTS (SELECT create_date ,modified_date ,create_user ,modify_user ,schedule_id ,smartpoint_id FROM schedule_membership, schedule WHERE smartpoint_id = p_smartpoint_id AND schedule_membership.schedule_id = schedule.schedule_id AND (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_event_type))
    THEN    
                        UPDATE schedule_membership
                        SET schedule_id = p_schedule_type, 
                        modify_user = p_create_user
                        FROM schedule 
                        WHERE schedule_membership.smartpoint_id = p_smartpoint_id 
                        AND schedule_membership.schedule_id = schedule.schedule_id 
                        AND (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_event_type);
    ELSE
           INSERT INTO schedule_membership
           (schedule_id
           ,smartpoint_id
           ,create_user) 
                VALUES
           (p_schedule_type
           ,p_smartpoint_id
           ,p_create_user);
    END IF;
END
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_schedule_count_by_name (p_tenant_id int , p_name varchar(100))  
RETURNS bigint AS   
$$
BEGIN
    RETURN (SELECT COUNT(1) FROM schedule WHERE schedule.name = p_name AND schedule.tenant_id = p_tenant_id);
END
$$ 
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_analytics_pagination_total_rows(p_tenant_id integer, p_group_id integer)
  RETURNS bigint AS
$$
BEGIN
  IF p_group_id = 0 THEN
    p_group_id = null;
  END IF;
  
    RETURN (
            SELECT COUNT(1) 
            FROM (
                  SELECT analytic_group_id 
                    FROM analytics_group 
                   WHERE tenant_id = p_tenant_id
                     AND (  (    p_group_id is not null 
                             AND analytic_group_id = p_group_id)
                         OR (    p_group_id is  null 
                             AND analytic_group_id in (SELECT ag1.analytic_group_id 
                                                         FROM analytics_group ag1 
                                                        WHERE ag1.analytic_group_name != 'All' 
                                                          AND ag1.tenant_id = p_tenant_id))
                         )
                                           
                ) AS ANALYTICS_PAGE
           ); 
END
$$
  LANGUAGE plpgsql;
  
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_cursor_light_by_tenant(p_tenant_id integer)
  RETURNS refcursor AS
$$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT l.light_id      
                                FROM light l                  
                 WHERE l.tenant_id = p_tenant_id
                 ORDER BY light_id;
    RETURN ref;
END;
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_light_properties_for_comm_fail(p_light_id integer)
  RETURNS void AS
$BODY$
DECLARE
	v_status_id integer;
	v_communication_id integer;
	v_current_light_status_property integer;
	v_current_light_subtype_status_property integer;
BEGIN
	v_status_id := 2;
	v_communication_id := 8;
	v_current_light_status_property := 81;
	v_current_light_subtype_status_property := 82;

	-- Updating the light properties.

	UPDATE
		light_property
	SET
		property_value = v_status_id
	WHERE
		property_id = v_current_light_status_property AND
		light_id = p_light_id;

	UPDATE
		light_property
	SET
		property_value = v_communication_id
	WHERE
		property_id = v_current_light_subtype_status_property AND
		light_id = p_light_id;
END
$BODY$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_group (p_grouping_id int)   
RETURNS SETOF grouping AS
$$
BEGIN
    RETURN QUERY 
    SELECT   grouping_id 
            ,name 
            ,description 
            ,create_date 
            ,modified_date 
            ,create_user 
            ,modify_user 
            ,tenant_id
            ,latitude
            ,longitude
    FROM grouping
    WHERE grouping_id =p_grouping_id;
END
$$ 
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_group_by_name(p_group_name character varying)
RETURNS SETOF grouping AS
$$
BEGIN
    RETURN QUERY SELECT  grouping_id 
                        ,name 
                        ,description 
                        ,create_date 
                        ,modified_date 
                        ,create_user 
                        ,modify_user 
                        ,tenant_id
                        ,latitude
                        ,longitude
    FROM grouping
    WHERE name = p_group_name;
END
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_map_center()
  RETURNS void AS
$$ 
DECLARE
        v_middle_lat FLOAT;
		v_middle_long FLOAT;
		v_lat_max FLOAT;
        v_lat_min FLOAT;
		v_lon_max FLOAT;
		v_lon_min FLOAT;
        v_tenant_record RECORD;
        v_tenant_cursor refcursor;
BEGIN
        Open v_tenant_cursor For SELECT tenant_id from tenant;
        
        LOOP
          FETCH v_tenant_cursor INTO v_tenant_record;
             EXIT WHEN NOT FOUND;

             
			v_lat_max := (SELECT MAX(latitude) from vw_maps where  tenant_id = v_tenant_record.tenant_id);
			v_lat_min := (SELECT MIN(latitude) from vw_maps where  tenant_id = v_tenant_record.tenant_id);
			v_lon_max := (SELECT MAX(longitude) from vw_maps where tenant_id = v_tenant_record.tenant_id);
			v_lon_min := (SELECT MIN(longitude) from vw_maps where tenant_id = v_tenant_record.tenant_id);


			v_middle_lat  := cast((cast(v_lat_max as numeric) + cast(v_lat_min as numeric) ) / 2 as character varying);
			v_middle_long := cast((cast(v_lon_max as numeric) + cast(v_lon_min as numeric) ) / 2 as character varying);

               PERFORM upsert_tenant_lat_long(v_tenant_record.tenant_id, v_middle_lat, v_middle_long);
                        
        END LOOP;
        CLOSE v_tenant_cursor;

END
$$
  LANGUAGE plpgsql;
  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION ins_light(integer, character varying, integer, character varying, character varying, integer);

CREATE OR REPLACE FUNCTION ins_light(IN p_rni_id integer, IN p_create_user character varying, IN p_tenant_id integer, IN p_light_status integer)
  RETURNS TABLE(light_id integer, smartpoint_id integer, rni_id integer, create_date timestamp with time zone) AS
$$
DECLARE 
        new_smp_id int;
        new_light_id int;
BEGIN
    INSERT INTO smartpoint
           (rni_id
           ,smartpoint_type
           ,tenant_id
           ,create_user)
    VALUES
           (p_rni_id
           ,1 -- Light
           ,p_tenant_id
           ,p_create_user)  RETURNING smartpoint.smartpoint_id INTO new_smp_id;

    INSERT INTO light
           (light_status
           ,smartpoint_id
           ,tenant_id
           ,create_user)
    VALUES
           (p_light_status
           ,new_smp_id
           ,p_tenant_id 
           ,p_create_user)  RETURNING light.light_id INTO new_light_id;

    RETURN QUERY SELECT l.light_id, l.smartpoint_id ,p_rni_id as rni_id, l.create_date FROM light l WHERE l.light_id = new_light_id;
END
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_location_has_changed(p_rni_id integer, lat double precision, lng double precision)
RETURNS boolean AS
$$ 
DECLARE 
        oldLatitude double precision;
        oldLongitude double precision;
        Lc_precision double precision;
        p_light_id int;
BEGIN
        -- This determines the precision of the comparison below. 6 decimal places will be used.
        Lc_precision := 0.000001;

        p_light_id := (SELECT get_light_id_by_rni_id(p_rni_id));
              
		oldLatitude  :=  CAST((SELECT property_value from light_property WHERE light_id = p_light_id and property_id = 16) as float);
        oldLongitude :=  CAST((SELECT property_value from light_property WHERE light_id = p_light_id and property_id = 17) as float);
                
        IF (ABS(lat - oldLatitude) < Lc_precision AND ABS(lng - oldLongitude) < Lc_precision)
        THEN
                RETURN false;
        ELSE
                RETURN true;
        END IF;
END
$$
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_light_lat_lng (p_light_id int , p_latitude double precision, p_longitude double precision , p_modify_user varchar(20))   
RETURNS void AS
$$
BEGIN
  UPDATE light
  SET modify_user =  p_modify_user
  WHERE light_id =p_light_id;
END
$$ 
LANGUAGE 'plpgsql';

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_custom_search_properties(IN p_custom_search_id integer)
  RETURNS TABLE(custom_search_id integer, property_id integer, property_name character varying, label_key character varying, create_user character varying, create_date timestamp with time zone, modified_user character varying, modified_date timestamp with time zone, custom_search_property_value character varying, custom_search_property_name character varying, operator_id integer) AS
$$
DECLARE
    p_all_alarms int;
    p_group_id_property int;
    p_tag_id_property int;
    p_event_schedule_property int;
    p_offset_schedule_property int;
    p_all_warnings int;
    p_housing_property int;
    p_dimming_property int;
BEGIN
    p_all_alarms := 96;
    p_all_warnings := 97;
    p_housing_property := 56;
    p_dimming_property := 55;
    p_group_id_property := 21;
    p_tag_id_property := 25;
    p_event_schedule_property := 42;
    p_offset_schedule_property := 43;
    RETURN QUERY SELECT csp.custom_search_id
                        ,p.property_id
                        ,p.property_name
                        ,p.label_key
                        ,csp.create_user
                        ,csp.create_date
                        ,csp.modified_user
                        ,csp.modified_date
                        ,csp.custom_search_property_value
                        ,CASE csp.property_id WHEN p_group_id_property THEN g.name 
                                              WHEN p_event_schedule_property THEN s.name 
                                              WHEN p_offset_schedule_property THEN s.name 
                                              WHEN p_tag_id_property THEN t.name 
                                              WHEN p_all_alarms THEN ss.label_key 
                                              WHEN p_all_warnings THEN ss.label_key 
                                              END custom_search_property_name
                        ,csp.operator_id
                                        FROM property p
                                                ,custom_search_property csp 
                                                LEFT OUTER JOIN grouping g ON g.grouping_id = CAST(csp.custom_search_property_value AS integer) AND csp.property_id = p_group_id_property
                                                LEFT OUTER JOIN schedule s ON s.schedule_id = CAST(csp.custom_search_property_value AS integer) AND (csp.property_id = p_event_schedule_property OR csp.property_id = p_offset_schedule_property)
                                                LEFT OUTER JOIN tag t ON t.tag_id = CAST(csp.custom_search_property_value AS integer) AND csp.property_id = p_tag_id_property
                                                LEFT OUTER JOIN property_valid_value pvv ON pvv.property_id = csp.property_id AND pvv.property_valid_value_id = CAST(csp.custom_search_property_value AS integer)
                                                LEFT OUTER JOIN status_subtype ss ON ss.status_subtype_id = CAST(csp.custom_search_property_value AS integer) AND csp.property_id IN (p_all_alarms,p_all_warnings)
                                        WHERE csp.custom_search_id = p_custom_search_id
                    AND (csp.property_id = p_group_id_property
                    OR csp.property_id = p_tag_id_property
                    OR csp.property_id = p_event_schedule_property
                    OR csp.property_id = p_offset_schedule_property
                    OR csp.property_id = p_all_alarms
                    OR csp.property_id = p_all_warnings)
                    AND csp.property_id = p.property_id
                                        UNION ALL 
                                        SELECT csp.custom_search_id
                                                        ,p.property_id
                            ,p.property_name
                            ,p.label_key
                            ,csp.create_user
                            ,csp.create_date
                            ,csp.modified_user
                            ,csp.modified_date
                            ,csp.custom_search_property_value
                                                        ,csp.custom_search_property_value custom_search_property_name
                            ,csp.operator_id
                                        FROM property p
                                                ,custom_search_property csp 
                                        WHERE csp.custom_search_id = p_custom_search_id
            
                    AND csp.property_id != p_group_id_property
                    AND csp.property_id != p_tag_id_property
                    AND csp.property_id != p_event_schedule_property
                    AND csp.property_id != p_offset_schedule_property
                    AND csp.property_id != p_all_alarms
                    AND csp.property_id != p_all_warnings
                    AND csp.property_id = p.property_id;
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_status_message_by_light_detail(IN p_light_id integer)
  RETURNS TABLE(status_message_id integer, message_date timestamp with time zone, status_id integer, message_type integer) AS
$$
Declare 
	status_subtype_communication_fail integer;
BEGIN

	status_subtype_communication_fail := 8;

        RETURN QUERY 
       
 SELECT sm.status_message_id,
	(SELECT	s.message_date
           FROM status_message s
	  WHERE s.light_id = p_light_id
	   AND(NOT EXISTS(SELECT smss.status_message_id FROM status_message_status_subtype smss where s.status_message_id  = smss.status_message_id AND smss.status_subtype_id = status_subtype_communication_fail ) )
	   ORDER BY s.status_message_id DESC LIMIT 1) AS message_date
	,sM.status_id
        ,sm.message_type
  FROM status_message sm
  WHERE sm.light_id = p_light_id
        ORDER BY sm.status_message_id DESC LIMIT 1 ;

END
$$
  LANGUAGE plpgsql ;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
 DROP FUNCTION calculate_dashboard_resume(double precision);

CREATE OR REPLACE FUNCTION calculate_dashboard_resume(p_carbon_credits_factor double precision)
  RETURNS character varying AS
$$
DECLARE 
    p_cursor_tenant refcursor;
    p_rec_tenant RECORD;
    p_cursor_conservation refcursor;
    p_rec_conservation RECORD;
    p_cursor_grouping_id refcursor;
    p_rec_grouping_id RECORD;

    p_grouping_id CHARACTER VARYING;
    p_lighting_alarm_id CHARACTER VARYING;
    p_lighting_warning_id CHARACTER VARYING;
    p_lighting_installed_id CHARACTER VARYING;
    p_conservation_consumption_id CHARACTER VARYING;
    p_conservation_energy_savings_id CHARACTER VARYING;
    p_conservation_carbon_credits_id CHARACTER VARYING;

    p_init_date DATE;
    p_end_date DATE;
    p_user CHARACTER VARYING;
    p_date TIMESTAMP;

    p_array_view_mode CHARACTER VARYING ARRAY;
BEGIN
    -- Defining procedure constants.
    p_user := 'sysuser';
    p_lighting_alarm_id := '1';
    p_lighting_warning_id := '2';
    p_lighting_installed_id := '3';
    p_conservation_consumption_id := '4';
    p_conservation_energy_savings_id := '5';
    p_conservation_carbon_credits_id := '6';

    p_date := CURRENT_TIMESTAMP;

    p_array_view_mode[0]:= 'week';
    p_array_view_mode[1]:= 'month';

    -- Cleaning dashboard_resume table.
    DELETE FROM dashboard_resume;

    -- Gets a cursor for all tenants.
    p_cursor_tenant := get_cursor_for_tenant();

	-- Looping all tenants.
	LOOP FETCH p_cursor_tenant INTO p_rec_tenant;
	EXIT WHEN NOT FOUND;

		OPEN p_cursor_grouping_id FOR
		SELECT ag.grouping_id FROM analytics_group ag WHERE ag.tenant_id = p_rec_tenant.tenant_id;

		-- Looping all groups of the current tenant.
		LOOP FETCH p_cursor_grouping_id INTO p_rec_grouping_id;
		EXIT WHEN NOT FOUND;
			p_grouping_id :=  p_rec_grouping_id.grouping_id;		
		
		   FOR i IN 0..array_upper(p_array_view_mode, 1) LOOP

			   IF (p_array_view_mode[i] = 'week') THEN
					   p_init_date := (SELECT CURRENT_DATE - 7);
			   ELSIF (p_array_view_mode[i] = 'month') THEN
					   p_init_date := (SELECT CURRENT_DATE - INTERVAL '30 days');
			   END IF;

			   p_end_date := (SELECT CURRENT_DATE - 1); 

			   INSERT INTO dashboard_resume (tenant_id, view_mode, analytics_type, grouping_id, VALUE, average, change, trends, create_user, create_date)
			   SELECT
				   p_rec_tenant.tenant_id AS tenant_id,
				   p_array_view_mode[i] AS view_mode,
				   p_lighting_alarm_id AS analytics_type,
				   p_rec_grouping_id.grouping_id AS grouping_id,				
				   get_analytics_alarms_total(p_rec_tenant.tenant_id, NULL, p_init_date, p_end_date, NULL, p_grouping_id) AS VALUE,
				   get_analytics_average(p_rec_tenant.tenant_id, NULL, p_array_view_mode[i], p_lighting_alarm_id, p_carbon_credits_factor, p_grouping_id) AS average,
				   get_analytics_change(p_rec_tenant.tenant_id, NULL, p_array_view_mode[i], p_lighting_alarm_id, p_carbon_credits_factor, p_grouping_id) AS change,
				   get_analytics_trend(p_rec_tenant.tenant_id, p_array_view_mode[i], p_lighting_alarm_id, p_carbon_credits_factor, NULL, p_grouping_id) AS trends,
				   p_user,
			   	   p_date
			   UNION ALL
			   SELECT
				   p_rec_tenant.tenant_id,
				   p_array_view_mode[i],
				   p_lighting_warning_id,
				   p_rec_grouping_id.grouping_id AS grouping_id,				
				   get_analytics_warnings_total(p_rec_tenant.tenant_id, NULL, p_init_date, p_end_date, NULL, p_grouping_id),
				   get_analytics_average(p_rec_tenant.tenant_id, NULL, p_array_view_mode[i], p_lighting_warning_id, p_carbon_credits_factor, p_grouping_id),
				   get_analytics_change(p_rec_tenant.tenant_id, NULL, p_array_view_mode[i], p_lighting_warning_id, p_carbon_credits_factor, p_grouping_id),
				   get_analytics_trend(p_rec_tenant.tenant_id, p_array_view_mode[i], p_lighting_warning_id, p_carbon_credits_factor, NULL, p_grouping_id),
				   p_user,
				   p_date
			   UNION ALL
			   SELECT
				   p_rec_tenant.tenant_id,
				   p_array_view_mode[i],
				   p_lighting_installed_id,
				   p_rec_grouping_id.grouping_id AS grouping_id,				
				   get_analytics_installed_total(p_rec_tenant.tenant_id, NULL, p_init_date, p_end_date, NULL, p_grouping_id),
				   get_analytics_average(p_rec_tenant.tenant_id, NULL, p_array_view_mode[i], p_lighting_installed_id, p_carbon_credits_factor, p_grouping_id),
				   get_analytics_change(p_rec_tenant.tenant_id, NULL, p_array_view_mode[i], p_lighting_installed_id, p_carbon_credits_factor, p_grouping_id),
				   get_analytics_trend(p_rec_tenant.tenant_id, p_array_view_mode[i], p_lighting_installed_id, p_carbon_credits_factor, NULL, p_grouping_id),
				   p_user,
		      		   p_date;
		   END LOOP;
		END LOOP;
		CLOSE p_cursor_grouping_id;	
	END LOOP;

	RETURN 'success';                  
END;
$$
  LANGUAGE plpgsql;   

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION upsert_settings(character varying, character varying, integer, integer, character varying);
CREATE OR REPLACE FUNCTION upsert_settings(p_user_id integer, p_create_user character varying, p_tenant_id integer, p_property_id integer, p_user_setting_property_value character varying)
  RETURNS void AS
$$
DECLARE
  v_user_settings_id int;
BEGIN

    if p_user_id is null then
        SELECT user_settings_id
          into v_user_settings_id
          from user_settings
         where user_id is null 
           and tenant_id = p_tenant_id;
    else 
        SELECT user_settings_id
          into v_user_settings_id
          from user_settings
         where user_id = p_user_id
           and tenant_id = p_tenant_id;
    END IF;
    
    if v_user_settings_id is null then
        INSERT INTO USER_SETTINGS (tenant_id   ,user_id   ,create_user)
                           VALUES (p_tenant_id ,p_user_id ,p_create_user) RETURNING user_settings_id INTO v_user_settings_id;                           
    END IF;
  
    UPDATE user_settings_property 
       SET modify_user = p_user_id
          ,user_setting_property_value = p_user_setting_property_value
     WHERE property_id = p_property_id 
       AND user_settings_id = v_user_settings_id;
  
    IF found THEN
        RETURN;
    END IF;
    
    INSERT INTO user_settings_property (user_settings_id   ,property_id   ,create_user   ,user_setting_property_value)
                               VALUES  (v_user_settings_id ,p_property_id ,p_create_user ,p_user_setting_property_value);

END;
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION upd_system_settings(character varying, integer, integer, character varying);
CREATE OR REPLACE FUNCTION upd_system_settings(p_user_id integer, p_tenant_id integer, p_property_id integer, p_user_setting_property_value character varying)
  RETURNS void AS
$$
BEGIN
    -- first try to update the key
    UPDATE user_settings_property SET modify_user = p_user_id, user_setting_property_value = p_user_setting_property_value
    WHERE ((property_id = p_property_id) AND (user_settings_id = (SELECT user_settings.user_settings_id FROM user_settings WHERE tenant_id = p_tenant_id AND user_id is null)));
END;
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION ins_custom_search(character varying, character varying, character varying, integer);
CREATE OR REPLACE FUNCTION ins_custom_search(p_custom_search_name character varying, p_custom_search_description character varying, p_create_user character varying, p_user_id integer, p_tenant_id integer)
  RETURNS integer AS
$$
DECLARE
        w_id int;
BEGIN
        INSERT INTO custom_search
                                (custom_search_name
                                ,custom_search_description
                                ,create_user
                                ,user_id
                                ,tenant_id)
                        VALUES
                                (p_custom_search_name
                                ,p_custom_search_description
                                ,p_create_user
                                ,p_user_id
                                ,p_tenant_id) 
        RETURNING custom_search_id INTO w_id;
        RETURN w_id;
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE FUNCTION split(IN p_vartext character varying, IN p_delimiter character varying)
	RETURNS TABLE(list_integer integer) AS
$$
DECLARE
  p_count int;
  p_str character varying;
BEGIN
  p_count := 0;


    IF EXISTS(SELECT * FROM information_schema.tables WHERE table_name = 'split_foo') THEN

	DROP TABLE split_foo;

    END IF;

    CREATE TEMP TABLE split_foo
    (
	int_value  integer
    );

   LOOP
    p_count := p_count + 1;
    p_str := (SELECT split_part(p_vartext , p_delimiter, p_count));
    EXIT WHEN p_str = '';
    INSERT INTO split_foo SELECT CAST(split_part(p_vartext, ',', p_count) AS integer);
   END LOOP;

  RETURN QUERY   
  SELECT int_value FROM split_foo;
    
END
$$
LANGUAGE plpgsql;


 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_user(p_tenant_id integer, p_user_name character varying, p_password character varying, p_first_name character varying, p_last_name character varying, p_email character varying, p_all_lights_auth boolean, p_create_user character varying)
  RETURNS integer AS
$$
DECLARE
    id int;
BEGIN
       INSERT INTO users(
            username
            ,tenant_id
            , password
            , first_name
            , last_name
            , email
            , enabled
            , all_lights_auth
            , create_date
            , create_user)
       VALUES 
           (p_user_name
           , p_tenant_id
           , p_password
           , p_first_name
           , p_last_name
           , p_email
           , TRUE
           , p_all_lights_auth
           , CURRENT_TIMESTAMP
           , p_create_user)
           RETURNING user_id INTO id;

       INSERT INTO user_settings(
              user_id
            , create_date
            , create_user)
       VALUES 
	   ( id 
	   , CURRENT_TIMESTAMP
           , p_create_user);

	RETURN id;

END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_group_to_user(IN p_user_id integer, IN p_group_id integer, IN p_create_user character varying)
  RETURNS integer AS
$$

BEGIN
      INSERT INTO users_grouping(
		user_id
		, grouping_id
		, create_user
		, create_date)
	VALUES (p_user_id
		, p_group_id
		, p_create_user
		, CURRENT_TIMESTAMP);

		RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_role_to_user(p_user_id integer, p_authotity_id character varying)
  RETURNS integer AS
$$

BEGIN

	INSERT INTO authorities(
        	user_id
		, authority
		)
	VALUES (p_user_id
		, p_authotity_id
		);

		RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_user(p_user_id integer, p_tenant_id integer)
  RETURNS integer AS
$$

BEGIN

	UPDATE users SET enabled = FALSE
	 WHERE user_id = p_user_id 
	   AND tenant_id = p_tenant_id;

	RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_user(p_user_id integer,p_user_name character varying, p_password character varying, p_first_name character varying, p_last_name character varying, p_email character varying,
	 p_all_lights_auth boolean, p_modify_user character varying)
  RETURNS void AS
$$
BEGIN

	UPDATE users
	   SET username = p_user_name
		, password = p_password
		, first_name = p_first_name
		, last_name = p_last_name
		, email = p_email
		, all_lights_auth = p_all_lights_auth
		, modified_user = p_modify_user
	WHERE user_id = p_user_id;
       
END
$$
  LANGUAGE plpgsql ;
  

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_groups_by_user(p_user_id integer)
  RETURNS void AS
$$
BEGIN

    DELETE FROM users_grouping
           WHERE user_id = p_user_id;

END
$$
  LANGUAGE plpgsql;
  
 
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_roles_by_user(p_user_id integer)
  RETURNS void AS
$$
BEGIN

    DELETE FROM authorities
           WHERE user_id = p_user_id;



END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_carbon_credits_total(integer, integer, timestamp without time zone, timestamp without time zone, double precision, integer, character varying);

CREATE FUNCTION get_analytics_carbon_credits_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp without time zone, p_end_date timestamp without time zone, p_carbon_credits_factor double precision, p_light_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
DECLARE

 p_v_energy_saved double precision;
 p_v_result double precision;

BEGIN

  p_v_energy_saved := (get_analytics_energy_savings_total(p_tenant_id , p_group_id , p_init_date , p_end_date , p_grouping_ids));
  	
  IF (p_v_energy_saved > 0) THEN

     p_v_result :=  (p_v_energy_saved * p_carbon_credits_factor);     

  END IF;

  RETURN  p_v_result;	   
	      
END
$$
  LANGUAGE plpgsql;
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_energy_savings_total(integer, integer, timestamp without time zone, timestamp without time zone, character varying);

CREATE OR REPLACE FUNCTION get_analytics_energy_savings_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp without time zone, p_end_date timestamp without time zone, p_grouping_ids character varying)
  RETURNS real AS
$$
DECLARE
 v_ecomode double precision;
 v_consumption double precision;
 v_result double precision;

BEGIN

   v_ecomode := (select get_analytics_eco_mode_total(p_tenant_id, p_group_id, p_init_date, p_end_date, p_grouping_ids));
   v_consumption := (select get_analytics_consumption_total(p_tenant_id, p_group_id, p_init_date, p_end_date, null, p_grouping_ids));

   IF (v_ecomode < 100) THEN

         v_result := ((v_consumption * 100)/(100 - v_ecomode)) - v_consumption;

   ELSE

         v_result := v_consumption;

   END IF;

  RETURN v_result;
END
$$
  LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE FUNCTION trigger_analytics_grouping_ins_grouping() RETURNS trigger AS 
$$
        BEGIN
                PERFORM  ins_analytics_group(NEW.name, NEW.tenant_id, NEW.create_user, NEW.grouping_id);
                RETURN null;
        END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_analytics_group(p_name character varying, p_tenant_id integer, p_create_user character varying, p_grouping_id integer)
  RETURNS integer AS
$$
DECLARE
        id int;
BEGIN
    INSERT INTO analytics_group(
                analytic_group_name, 
                tenant_id,
                create_user,
                grouping_id)
    VALUES (
                p_name, 
                p_tenant_id,
                p_create_user,
		p_grouping_id) RETURNING analytic_group_id INTO id;
           
    RETURN id;
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_current_alarm_warning_message(p_light_id integer, p_status_message_id integer, p_status_message_type integer, p_status_messages_status_id integer, p_status_messages_status_subtype_id integer, p_message_date timestamp with time zone, p_tenant_id integer)
  RETURNS void AS
$BODY$
BEGIN
	INSERT INTO current_alarm_warning_message(
		light_id, status_message_id, status_message_type, status_messages_status_id, status_messages_status_subtype_id, message_date, tenant_id
	) VALUES (
		p_light_id, p_status_message_id, p_status_message_type, p_status_messages_status_id, p_status_messages_status_subtype_id, p_message_date, p_tenant_id
	);
END
$BODY$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_user(p_tenant_id integer, p_user_name character varying, p_password character varying, p_first_name character varying, p_last_name character varying, p_email character varying, p_all_lights_auth boolean, p_create_user character varying)
  RETURNS integer AS
$$
DECLARE
    id int;
BEGIN
       INSERT INTO users(
            username
            ,tenant_id
            , password
            , first_name
            , last_name
            , email
            , enabled
            , all_lights_auth
            , create_date
            , create_user)
       VALUES 
           (p_user_name
           , p_tenant_id
           , p_password
           , p_first_name
           , p_last_name
           , p_email
           , TRUE
           , p_all_lights_auth
           , CURRENT_TIMESTAMP
           , p_create_user)
           RETURNING user_id INTO id;

       INSERT INTO user_settings(
			user_id
			, tenant_id
            , create_date
            , create_user)
       VALUES 
	   ( id 
	   , p_tenant_id
	   , CURRENT_TIMESTAMP
           , p_create_user);

	RETURN id;

END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_group_to_user(IN p_user_id integer, IN p_group_id integer, IN p_create_user character varying)
  RETURNS integer AS
$$

BEGIN
      INSERT INTO users_grouping(
		user_id
		, grouping_id
		, create_user
		, create_date)
	VALUES (p_user_id
		, p_group_id
		, p_create_user
		, CURRENT_TIMESTAMP);

		RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_role_to_user(p_user_id integer, p_authotity_id character varying)
  RETURNS integer AS
$$

BEGIN

	INSERT INTO authorities(
        	user_id
		, authority
		)
	VALUES (p_user_id
		, p_authotity_id
		);

		RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_user(p_user_id integer, p_tenant_id integer)
  RETURNS integer AS
$$

BEGIN

	UPDATE users SET enabled = FALSE
	 WHERE user_id = p_user_id 
	   AND tenant_id = p_tenant_id;

	RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_user(p_user_id integer,p_user_name character varying, p_password character varying, p_first_name character varying, p_last_name character varying, p_email character varying,
	 p_all_lights_auth boolean, p_modify_user character varying)
  RETURNS void AS
$$
BEGIN

	UPDATE users
	   SET username = p_user_name
		, password = p_password
		, first_name = p_first_name
		, last_name = p_last_name
		, email = p_email
		, all_lights_auth = p_all_lights_auth
		, modify_user = p_modify_user
		, modify_date = CURRENT_TIMESTAMP
	WHERE user_id = p_user_id;
       
END
$$
  LANGUAGE plpgsql ;
  

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_groups_by_user(p_user_id integer)
  RETURNS void AS
$$
BEGIN

    DELETE FROM users_grouping
           WHERE user_id = p_user_id;

END
$$
  LANGUAGE plpgsql;
  
 
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_roles_by_user(p_user_id integer)
  RETURNS void AS
$$
BEGIN

    DELETE FROM authorities
           WHERE user_id = p_user_id;



END
$$
  LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_columns_to_custom_search(p_custom_search_id integer, p_column_value character varying, p_display_order integer, p_create_user character varying)
  RETURNS void AS
$$
DECLARE
	v_property_id integer;
BEGIN
	v_property_id := 104;

         INSERT INTO custom_search_property(custom_search_id, property_id, create_date, create_user, custom_search_property_value, display_order)
		VALUES (p_custom_search_id, v_property_id, CURRENT_TIMESTAMP, p_create_user, p_column_value, p_display_order);
END;
$$
  LANGUAGE plpgsql;

  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_filters_to_custom_search(p_custom_search_id integer, p_filters_value character varying, p_display_order integer, p_create_user character varying)
  RETURNS void AS
$$
DECLARE
	v_property_id integer;
BEGIN
	v_property_id := 105;

         INSERT INTO custom_search_property(custom_search_id, property_id, create_date, create_user, custom_search_property_value, display_order)
		VALUES (p_custom_search_id, v_property_id, CURRENT_TIMESTAMP, p_create_user, p_filters_value, p_display_order);
END;
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_columns_to_user(p_user_id integer, p_column_value character varying, p_display_order integer, p_create_user character varying, p_tenant_id integer)
  RETURNS void AS
$$
DECLARE
	v_property_id integer;
	v_user_settings_id integer;
	id integer;
BEGIN
	v_property_id := 104;

	v_user_settings_id := (SELECT user_settings_id FROM user_settings WHERE tenant_id = p_tenant_id AND user_id = p_user_id);

	IF(v_user_settings_id IS NULL) THEN

		INSERT INTO user_settings(tenant_id, create_date, create_user, user_id)
		     VALUES (p_tenant_id, CURRENT_TIMESTAMP, p_create_user , p_user_id)
		  RETURNING user_settings_id INTO id;


		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (id, v_property_id,CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);

	ELSE		

		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (v_user_settings_id, v_property_id, CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);

	END IF;


END;
$$
  LANGUAGE plpgsql;
  

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_filters_to_user(p_user_id integer, p_column_value character varying, p_display_order integer, p_create_user character varying, p_tenant_id integer)
  RETURNS void AS
$$
DECLARE
	v_property_id integer;
	v_user_settings_id integer;
	id integer;
BEGIN
	v_property_id := 105;

	v_user_settings_id := (SELECT user_settings_id FROM user_settings WHERE tenant_id = p_tenant_id AND user_id = p_user_id);

	IF(v_user_settings_id IS NULL) THEN

		INSERT INTO user_settings(tenant_id, create_date, create_user, user_id)
		     VALUES (p_tenant_id, CURRENT_TIMESTAMP, p_create_user , p_user_id)
		  RETURNING user_settings_id INTO id;

		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (id, v_property_id,CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);
	ELSE		

		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (v_user_settings_id, v_property_id, CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);

	END IF;


END;
$$
  LANGUAGE plpgsql;

  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_column_to_user(p_tenant_id integer, p_user_id integer)
  RETURNS void AS
$$
DECLARE
 v_column_property INTEGER;
BEGIN
    v_column_property := 104;
    
    DELETE FROM user_settings_property 
    WHERE property_id = v_column_property 
      AND user_settings_id = (SELECT user_settings_id FROM user_settings WHERE user_id = p_user_id AND tenant_id = p_tenant_id);
END
$$
  LANGUAGE plpgsql;
  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_filter_to_user(p_tenant_id integer, p_user_id integer)
  RETURNS void AS
$$
DECLARE
 v_column_property INTEGER;
BEGIN
    v_column_property := 105;
    
    DELETE FROM user_settings_property 
    WHERE property_id = v_column_property 
      AND user_settings_id = (SELECT user_settings_id FROM user_settings WHERE user_id = p_user_id AND tenant_id = p_tenant_id );
END
$$
  LANGUAGE plpgsql;  

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_current_alarm_warning_messages_by_light_id(p_light_id INTEGER)
  RETURNS void AS
$BODY$
BEGIN
    DELETE FROM current_alarm_warning_message WHERE light_id = p_light_id;
END
$BODY$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_average_light_consumption(p_light_id integer, p_initial_date timestamp with time zone, p_end_date timestamp with time zone)
RETURNS real AS
$$
DECLARE

    p_type_consumption int;
    p_date_time timestamp with time zone;
    finalConsumption float4;
    initialConsumption float4;

BEGIN

    p_type_consumption := 1;

    -- consumption for the last 24hr
    if (p_initial_date = p_end_date) then
        
        p_date_time := CAST( CURRENT_TIMESTAMP - CAST('1 day' AS interval) AS timestamp);
      
    -- consumption for the last month
    else
    
        p_date_time := CAST( CURRENT_TIMESTAMP - CAST('1 month' AS interval) AS timestamp);
    
    END IF;
    

    initialConsumption := ( SELECT coalesce(odv.value,0) AS endValue 
                              FROM operational_data_value odv 
                                  ,status_message sm 
                             WHERE sm.light_id           = p_light_id 
                               AND sm.message_date      >= p_date_time
                               AND odv.status_message_id = sm.status_message_id
                               AND odv.operational_data_type_id = p_type_consumption
                             ORDER BY odv.create_date LIMIT 1);

    finalConsumption := (   SELECT coalesce(odv.value,0) AS endValue 
                              FROM operational_data_value odv 
                                  ,status_message sm 
                             WHERE sm.light_id           = p_light_id 
                               AND sm.message_date      >= p_date_time
                               AND odv.status_message_id = sm.status_message_id
                               AND odv.operational_data_type_id = p_type_consumption
                             ORDER BY odv.create_date DESC LIMIT 1);

    RETURN finalConsumption - initialConsumption;

END
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION upd_analytics_alarms_warnings(integer, integer, character varying, character, character);
CREATE OR REPLACE FUNCTION upd_analytics_alarms_warnings(p_status_message_id integer, p_alarm_subtype_id integer, p_create_user varchar(20), type char, p_operator char) RETURNS void AS
$$
DECLARE
        v_analytics_alarm_warning_subtype_id integer;
        v_light_id integer;
        v_analytic_group_id integer;
        v_analytic_group_by_date_id integer;
        v_property_light_source integer;
        v_lamp_type char;
        v_light_type_id integer;
        v_refcursor refcursor;
        v_record RECORD;
                v_status_message RECORD;
BEGIN

        SELECT INTO v_status_message light_id,tenant_id FROM status_message WHERE status_message_id = p_status_message_id;
                
        -- retrieve light id
        v_light_id = v_status_message.light_id;
                
        -- retrieve analytic_group_by_date_id for Group = 'All' and tenant in question
        v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND tenant_id = v_status_message.tenant_id);
        v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));
        
        -- retrieve lamp_type
        v_property_light_source:= 51;
        v_lamp_type := (SELECT property_value FROM light_property WHERE property_id = v_property_light_source AND light_id = v_light_id);
        IF (v_lamp_type = 'I')
        THEN
                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION');
        END IF;
        IF (v_lamp_type ='L')
        THEN
                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'LED');
        END IF;
        IF (v_lamp_type ='O')
        THEN
                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'OTHER');
        END IF;
        
        -- retrieve analytics_alarm_warning_subtype_id
        v_analytics_alarm_warning_subtype_id := (SELECT analytics_alarm_warning_subtype_id FROM analytics_alarm_warning_subtype WHERE label_key = (SELECT label_key FROM status_subtype WHERE status_subtype_id = p_alarm_subtype_id));
        
        IF (type = 'A') THEN

                PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                                
        END IF;
        
        IF (type = 'W') THEN
       
                PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator);
                                
        END IF;
        
        v_refcursor := get_cursor_groups_for_light(v_light_id);
                                
        LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;
                                        
                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name);
                
                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));
                                
                -- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
                IF (type = 'A') THEN
               
                        PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;
        
                IF (type = 'W') THEN
                
                        PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;
                                        
        END LOOP;
END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsert_analytics_warnings(p_analytic_group_by_date_id integer, p_light_type_id integer, p_analytics_warning_subtype_id integer, p_create_user varchar(20), p_operator char) RETURNS void AS
$$
BEGIN
        IF (p_operator = '+') THEN
            UPDATE analytics_warnings SET value = value + 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_warning_subtype = p_analytics_warning_subtype_id);
        ELSEIF (p_operator = '-') THEN
            UPDATE analytics_warnings SET value = value - 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_warning_subtype = p_analytics_warning_subtype_id);
        END IF;
        
        IF NOT FOUND THEN
                -- not there, so try to insert the key
			IF (p_operator = '+') THEN
				PERFORM ins_analytics_warnings(p_analytic_group_by_date_id, p_light_type_id, p_analytics_warning_subtype_id, p_create_user, 1);
			ELSEIF (p_operator = '-') THEN
				PERFORM ins_analytics_warnings(p_analytic_group_by_date_id, p_light_type_id, p_analytics_warning_subtype_id, p_create_user, -1);
			END IF;
        END IF;
END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsert_analytics_alarms(p_analytic_group_by_date_id integer, p_light_type_id integer, p_analytics_alarm_subtype_id integer, p_create_user varchar(20), p_operator char) RETURNS void AS
$$
BEGIN
    
    IF (p_operator = '+') THEN
        UPDATE analytics_alarms SET value = value + 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_alarm_subtype = p_analytics_alarm_subtype_id);
    END IF;
    
    IF (p_operator = '-') THEN
        UPDATE analytics_alarms SET value = value - 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_alarm_subtype = p_analytics_alarm_subtype_id);
    END IF;
    
    IF NOT FOUND THEN
        -- not there, so try to insert the key
		IF (p_operator = '+') THEN
			PERFORM ins_analytics_alarms(p_analytic_group_by_date_id, p_light_type_id, p_analytics_alarm_subtype_id, p_create_user, 1);
		ELSEIF (p_operator = '-') THEN
			PERFORM ins_analytics_alarms(p_analytic_group_by_date_id, p_light_type_id, p_analytics_alarm_subtype_id, p_create_user, -1);
		END IF;
    END IF;
END;
$$
LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_analytics_alarms_warnings_by_light(p_tenant_id integer, p_light_id integer, p_alarm_subtype_id integer, p_create_user character varying, type character, p_operator character)
  RETURNS void AS
$$
DECLARE
        v_analytics_alarm_warning_subtype_id integer;
        v_analytic_group_id integer;
        v_analytic_group_by_date_id integer;
        v_property_light_source integer;
        v_lamp_type char;
        v_light_type_id integer;
        v_refcursor refcursor;
        v_record RECORD;
BEGIN
               
        -- retrieve analytic_group_by_date_id for Group = 'All' and tenant in question
        v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND tenant_id = p_tenant_id);
        v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));
        
        -- retrieve lamp_type
        v_property_light_source:= 51;
        v_lamp_type := (SELECT property_value FROM light_property WHERE property_id = v_property_light_source AND light_id = p_light_id);
        IF (v_lamp_type = 'I')
        THEN
                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION');
        END IF;
        IF (v_lamp_type ='L')
        THEN
                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'LED');
        END IF;
        IF (v_lamp_type ='O')
        THEN
                v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'OTHER');
        END IF;
        
        -- retrieve analytics_alarm_warning_subtype_id
        v_analytics_alarm_warning_subtype_id := (SELECT analytics_alarm_warning_subtype_id FROM analytics_alarm_warning_subtype WHERE label_key = (SELECT label_key FROM status_subtype WHERE status_subtype_id = p_alarm_subtype_id));
        
        IF (type = 'A') THEN

                PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                                
        END IF;
        
        IF (type = 'W') THEN
       
                PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator);
                                
        END IF;
        
        v_refcursor := get_cursor_groups_for_light(p_light_id);
                                
        LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;
                                        
                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name);
                
                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));
                                
                -- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
                IF (type = 'A') THEN
               
                        PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;
        
                IF (type = 'W') THEN
                
                        PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;
                                        
        END LOOP;
END;
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_light_status(p_rni_light_id integer, p_light_status integer, p_modify_user character varying, p_intensity integer, p_blink_level integer, 
					    p_override integer, p_override_per_date timestamp with time zone, p_override_create_date timestamp with time zone)
  RETURNS void AS
$$
DECLARE 
    id int;
BEGIN
  id:= get_light_id_by_rni_id (p_rni_light_id);
  UPDATE light
  SET   light_status = p_light_status
                ,intensity = p_intensity
                ,modify_user = p_modify_user
                ,blink_level = coalesce(p_blink_level,blink_level)
                ,override = coalesce(p_override,override)
                ,override_per_date = coalesce(p_override_per_date,override_per_date)
                ,override_create_date = coalesce(p_override_create_date, override_create_date)
  WHERE light_id = id;
END  
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_analytics_warnings_by_type(IN p_tenant_id integer, IN p_group_id integer, IN p_init_date date, IN p_end_date date, IN p_alarm_type_id integer, IN p_grouping_ids character varying)
  RETURNS TABLE(status_subtype integer, amount bigint) AS
$$
DECLARE

  p_ini_dt timestamp;
  p_end_dt timestamp;
  p_analytic_group_id int;
    
BEGIN
 
  p_ini_dt := (SELECT cast(p_init_date||' 00:00:00' as timestamp));
  p_end_dt := (SELECT cast(p_end_date||' 23:59:59' as timestamp));  
  
  IF(p_grouping_ids IS NULL) THEN
	
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	  RETURN QUERY
	   (SELECT aw.analytics_warning_subtype, COALESCE(SUM(aw.value),0) AS amount
		  FROM analytics_warnings                aw
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag			
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(aw.analytics_warning_subtype)		 
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 2))
		 GROUP BY  aw.analytics_warning_subtype);
 ELSE  
 	  RETURN QUERY
	   (SELECT aw.analytics_warning_subtype, COALESCE(SUM(aw.value),0) AS amount
		  FROM analytics_warnings                aw
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag			  
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(aw.analytics_warning_subtype)		 
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 2))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		 GROUP BY  aw.analytics_warning_subtype);		   
    END IF;
END
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_analytics_alarms_by_type(IN p_tenant_id integer, IN p_group_id integer, IN p_init_date date, IN p_end_date date, IN p_alarm_type_id integer, IN p_grouping_ids character varying)
  RETURNS TABLE(status_subtype integer, amount bigint) AS
$$
DECLARE

  p_ini_dt timestamp;
  p_end_dt timestamp;
    
BEGIN  

  p_ini_dt := (SELECT cast(p_init_date || ' 00:00:00' as timestamp));
  p_end_dt := (SELECT cast(p_end_date || ' 23:59:59' as timestamp));
    
  IF(p_grouping_ids IS NULL) THEN
	
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;
	
	RETURN QUERY
	   (SELECT aa.analytics_alarm_subtype, COALESCE(SUM(aa.value),0) AS amount
		  FROM analytics_alarms                  aa
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(p_alarm_type_id,aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 1))
		 GROUP BY aa.analytics_alarm_subtype);
 ELSE  
	RETURN QUERY
	   (SELECT aa.analytics_alarm_subtype, COALESCE(SUM(aa.value),0) AS amount
		  FROM analytics_alarms                  aa
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag		  
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id		  
		   AND agbd.analytic_date BETWEEN p_ini_dt AND p_end_dt	
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 1))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))  		 		 		 
		 GROUP BY  aa.analytics_alarm_subtype);
  END IF;
END
$$
  LANGUAGE plpgsql;  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION ins_schedule_event(timestamp with time zone, integer, integer, integer, integer, character varying);

CREATE FUNCTION ins_schedule_event(p_event_time timestamp with time zone, p_intensity integer, p_blink_level integer, p_schedule_id integer, p_tenant_id integer, p_create_user character varying)
  RETURNS integer AS
$$
DECLARE
        id int;
BEGIN
    INSERT INTO schedule_event
           (event_time
           ,intensity
           ,blink_level
           ,schedule_id
           ,tenant_id
           ,create_user)
    VALUES
           (p_event_time
           ,p_intensity
           ,p_blink_level
           ,p_schedule_id
           ,p_tenant_id
           ,p_create_user) RETURNING schedule_event_id INTO id;
    RETURN id;
END
$$
  LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_last_operational_data_value(integer, integer);

CREATE OR REPLACE FUNCTION get_last_operational_data_value(p_light_id integer, p_data_type_id integer)
  RETURNS double precision AS
$$
BEGIN
    RETURN (
    SELECT odv.value
	FROM operational_data_value odv 
	INNER JOIN status_message sm ON odv.status_message_id = sm.status_message_id
	WHERE sm.light_id = p_light_id 
	AND odv.operational_data_type_id = p_data_type_id
	ORDER BY odv.create_date DESC LIMIT 1);
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsert_ecomode(p_tenant_id integer, p_rni_id integer, p_consumption_day date, p_calculate_retroactive_ecomode boolean, p_ecomode double precision, p_light_type integer, p_wattage_value double precision, p_baseline_value double precision)
  RETURNS integer AS
$$
DECLARE
	num_rows int;
BEGIN

   IF p_rni_id IS NOT NULL AND p_consumption_day IS NOT NULL THEN

      UPDATE light_daily_consumption AS ldc
         SET ecomode = p_ecomode,
	     ecomode_baseline = p_baseline_value
        FROM light AS l JOIN 
	     smartpoint AS smt ON l.smartpoint_id = smt.smartpoint_id
       WHERE ldc.light_id = l.light_id
         AND ldc.consumption_day = p_consumption_day
         AND smt.rni_id = p_rni_id;
      
   END IF;

   UPDATE light AS l
      SET ecomode = p_ecomode,
          ecomode_replaced_type = p_light_type,
          ecomode_replaced_wattage = p_wattage_value,
          calculate_retroactive_ecomode = p_calculate_retroactive_ecomode
     FROM smartpoint smt
    WHERE l.smartpoint_id = smt.smartpoint_id
      AND l.tenant_id = p_tenant_id
      AND smt.rni_id = p_rni_id;

    IF FOUND THEN
        GET DIAGNOSTICS num_rows = ROW_COUNT;
    ELSE
        num_rows = -1;
    END IF;

    RETURN num_rows;
END;
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_light_configuration_by_part_number(IN p_light_id integer)
  RETURNS TABLE(intensity_level integer, hardware_setting integer, current_scale integer, full_on_required boolean, dim_on_delay integer) AS
$$
DECLARE
        
	p_cursor_tenant refcursor;
	p_rec_sensus_part_number RECORD;
	p_array_value CHARACTER VARYING[];
	p_array_mask CHARACTER VARYING[];
	p_array_position INTEGER[];
	p_product_number CHARACTER VARYING;
	p_property_part_number INTEGER;
	p_aux TEXT;
	p_test boolean;
	p_default boolean;
	
	        
BEGIN
	p_default := true;
	p_property_part_number := 44;
        p_cursor_tenant := get_cursor_for_configuration_part_number();

	p_product_number := (SELECT property_value FROM light_property WHERE property_id = p_property_part_number AND light_id = p_light_id);

	LOOP FETCH p_cursor_tenant INTO p_rec_sensus_part_number;
	   EXIT WHEN NOT FOUND;
		p_test := false;
	   	p_array_value := (SELECT string_to_array(p_rec_sensus_part_number.sensus_part_number_value, '|'));
		p_array_mask  := (SELECT string_to_array(p_rec_sensus_part_number.format_mask	, '|'));

		<<next_value>>
		--Loop to value
		FOR i IN 1..array_length(p_array_value, 1) LOOP

			--separates the position initial and total of field
			p_array_position := (SELECT string_to_array(p_array_mask[i], '-'));
		
			FOR j IN 1..(array_length(p_array_position, 1))-1 LOOP
				p_test := false;
			 
				p_aux :=(SELECT SUBSTRING(p_product_number, p_array_position[j], p_array_position[j+1]));

				IF(p_aux = p_array_value[i])THEN
					p_test := true;
				ELSE
					-- if different go to the next value and ignore other field to this value
					EXIT next_value;
				END IF;
			END LOOP;

		END LOOP;

		IF(p_test = true and p_default = true) THEN
			-- set default=false to ignore the Generic Configurations
			p_default = false;
			RETURN QUERY 
				SELECT sensus_part_number_configuration.intensity_level, sensus_part_number_configuration.hardware_setting, sensus_part_number_configuration.current_scale, sensus_part_number_configuration.full_on_required,
				(SELECT sensus_part_number.dim_on_delay FROM sensus_part_number WHERE sensus_part_number_id = p_rec_sensus_part_number.sensus_part_number_id ) dim_on_delay_value
				FROM sensus_part_number_configuration
				WHERE sensus_part_number_configuration.sensus_part_number_id = p_rec_sensus_part_number.sensus_part_number_id;
		END IF;
	END LOOP;
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_cursor_for_configuration_part_number()
  RETURNS refcursor AS
$$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT * FROM sensus_part_number order by sensus_part_number_id;
    RETURN ref;
END;
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------ 
  CREATE OR REPLACE FUNCTION get_light_intensity_percentage_by_light(IN p_light_id integer)
  RETURNS TABLE(intensity_level integer, percentage integer) AS
$$
DECLARE
    p_cursor_tenant refcursor;
	p_rec_sensus_part_number RECORD;
	p_array_value CHARACTER VARYING[];
	p_array_mask CHARACTER VARYING[];
	p_array_position INTEGER[];
	p_product_number CHARACTER VARYING;
	p_property_part_number INTEGER;
	p_aux TEXT;
	p_test boolean;
	p_default boolean;
	
BEGIN
	p_default := true;
	p_property_part_number := 44;
        p_cursor_tenant := get_cursor_for_configuration_part_number();

	p_product_number := (SELECT property_value FROM light_property WHERE property_id = p_property_part_number AND light_id = p_light_id);

	LOOP FETCH p_cursor_tenant INTO p_rec_sensus_part_number;
	   EXIT WHEN NOT FOUND;
		p_test := false;
	   	p_array_value := (SELECT string_to_array(p_rec_sensus_part_number.sensus_part_number_value, '|'));
		p_array_mask  := (SELECT string_to_array(p_rec_sensus_part_number.format_mask	, '|'));

		<<next_value>>
		--Loop to value
		FOR i IN 1..array_length(p_array_value, 1) LOOP

			--separates the position initial and total of field
			p_array_position := (SELECT string_to_array(p_array_mask[i], '-'));
		
			FOR j IN 1..(array_length(p_array_position, 1))-1 LOOP
				p_test := false;
			 
				p_aux :=(SELECT SUBSTRING(p_product_number, p_array_position[j], p_array_position[j+1]));

				IF(p_aux = p_array_value[i])THEN
					p_test := true;
				ELSE
					-- if different go to the next value and ignore other field to this value
					EXIT next_value;
				END IF;
			END LOOP;

		END LOOP;

		IF(p_test = true and p_default = true) THEN
			-- set default=false to ignore the Generic Configurations
			p_default = false;
			  RETURN QUERY SELECT sensus_part_number_configuration.intensity_level, sensus_part_number_configuration.percentage 
					FROM sensus_part_number_configuration 
					WHERE sensus_part_number_configuration.sensus_part_number_id = p_rec_sensus_part_number.sensus_part_number_id;
		END IF;
	END LOOP;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_light_current_status_message(p_light_id integer, p_current_status_message integer)
  RETURNS void AS
$$
BEGIN
  UPDATE light
  SET   current_status_message_id = p_current_status_message
  WHERE light_id = p_light_id;
END  
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------  
CREATE OR REPLACE FUNCTION calculate_analytics_ecomode()
  RETURNS character varying AS
$$
DECLARE
    p_cursor_tenant refcursor;
    p_rec_tenant RECORD;

    p_user CHARACTER VARYING;
    p_date TIMESTAMP;

BEGIN
    -- Defining procedure constants.
    p_user := 'sysuser';
    p_date := CURRENT_TIMESTAMP;

    -- Gets a cursor for all tenants.
    p_cursor_tenant := get_cursor_for_tenant();

    -- Cleaning analytics_ecomode table.
    DELETE FROM analytics_ecomode;

    -- Looping all tenants.
    LOOP FETCH p_cursor_tenant INTO p_rec_tenant;
    EXIT WHEN NOT FOUND;
	-- Inserting average consumption of light by group in analytics ecomode
	INSERT INTO analytics_ecomode (analytic_group_by_date_id, ecomode_percent, ecomode_measured, ecomode_baseline, create_user, create_date)
	select get_analytics_group_by_date_pk(analytic_group_id, consumption_day, p_user)
	  ,round(cast(cast(ecomode_percent as real) as numeric),3) as ecomode_percent
      ,round(cast(cast(ecomode_measured as real) as numeric),3) as ecomode_measured
	  ,round(cast(cast(ecomode_baseline as real) as numeric),3) as ecomode_baseline
	  ,p_user
	  ,p_date
	from(
	     select ag.analytic_group_id
				,ldc.consumption_day
				,sum(ldc.consumption) as ecomode_measured
				,avg(coalesce(ldc.ecomode,0)) as ecomode_percent
				,sum(coalesce(ldc.ecomode_baseline,0)) as ecomode_baseline
		 from light_daily_consumption ldc
			inner join light l on (l.light_id = ldc.light_id)
			inner join smartpoint s on(s.smartpoint_id = l.smartpoint_id)
			inner join smartpoint_grouping sg on(sg.smartpoint_id = s.smartpoint_id)
			inner join analytics_group ag on (ag.grouping_id = sg.grouping_id)
		where ag.tenant_id = p_rec_tenant.tenant_id
		   AND ldc.ecomode IS NOT NULL
		group by ag.analytic_group_id, ldc.consumption_day
	    ) ls;
	-- Inserting average consumption of light by tenant in analytics ecomode
	INSERT INTO analytics_ecomode (analytic_group_by_date_id, ecomode_percent, ecomode_measured, ecomode_baseline, create_user, create_date)
	select get_analytics_group_by_date_pk(analytic_group_id, consumption_day, p_user)
	  ,round(cast(cast(ecomode_percent as real) as numeric),3) as ecomode_percent
      ,round(cast(cast(ecomode_measured as real) as numeric),3) as ecomode_measured
	  ,round(cast(cast(ecomode_baseline as real) as numeric),3) as ecomode_baseline
	  ,p_user
	  ,p_date
	from(
	     select ag.analytic_group_id
				,ldc.consumption_day
				,sum(ldc.consumption) as ecomode_measured
				,avg(coalesce(ldc.ecomode,0)) as ecomode_percent
				,sum(coalesce(ldc.ecomode_baseline,0)) as ecomode_baseline
	     from light_daily_consumption ldc
			inner join light l on (l.light_id = ldc.light_id)
			inner join smartpoint s on(s.smartpoint_id = l.smartpoint_id)
			inner join analytics_group ag on (ag.tenant_id = s.tenant_id and analytic_group_name like'All%')
	     where ag.tenant_id = p_rec_tenant.tenant_id
	       AND ldc.ecomode IS NOT NULL
	     group by ag.analytic_group_id, ldc.consumption_day
	    ) ls;
    END LOOP;

    RETURN 'success';
END;
$$
  LANGUAGE plpgsql; 
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
DROP FUNCTION get_analytics_eco_mode_total(integer, integer, timestamp with time zone, timestamp with time zone, character varying);

CREATE FUNCTION get_analytics_eco_mode_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp with time zone, p_end_date timestamp with time zone, p_grouping_ids character varying)
  RETURNS real AS
$$    
BEGIN
    
  IF(p_grouping_ids IS NULL) THEN
	
	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;
	
	RETURN 
	   (SELECT avg(ecomode_percent) as ecomode_percent										
	       FROM analytics_ecomode ae
	       INNER JOIN analytics_group_by_date agbd on (agbd.analytic_group_by_date_id = ae.analytic_group_by_date_id)
	       INNER JOIN analytics_group ag on (ag.analytic_group_id = agbd.analytic_group_id)
	    WHERE ag.tenant_id = p_tenant_id
	      AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
	      AND ag.analytic_group_id = p_group_id
	   );	
  ELSE
  
	RETURN 
	   (SELECT avg(ecomode_percent) as ecomode_percent										
	       FROM analytics_ecomode ae
	       INNER JOIN analytics_group_by_date agbd on (agbd.analytic_group_by_date_id = ae.analytic_group_by_date_id)
	       INNER JOIN analytics_group ag on (ag.analytic_group_id = agbd.analytic_group_id)
	    WHERE ag.tenant_id = p_tenant_id
	      AND agbd.analytic_date BETWEEN p_init_date AND p_end_date	    
              AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))  
	   );
  END IF;
END
$$
  LANGUAGE plpgsql; 
  
  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_light_daily_consumption(p_light_id integer, p_delta double precision)
  RETURNS void AS
$$
BEGIN

	   INSERT INTO light_daily_consumption(
		        light_id
			,consumption_day
			,consumption)
		VALUES(
			p_light_id
			,date_trunc('day',current_timestamp)
			,p_delta);


END;
$$
  LANGUAGE plpgsql;
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsert_light_daily_consumption(p_light_id integer, p_delta double precision)
  RETURNS void AS
$$
DECLARE

    v_consumption double precision;

BEGIN

   IF p_delta IS NOT NULL AND p_delta > 0 THEN

	    v_consumption:= (select consumption from light_daily_consumption where light_id = p_light_id AND (date_trunc('day',current_timestamp) = consumption_day));

        UPDATE light_daily_consumption SET consumption = (v_consumption + p_delta) WHERE light_id = p_light_id AND  (date_trunc('day',current_timestamp) = consumption_day);

	    IF NOT FOUND THEN
		    -- not there, so try to insert the key
		    PERFORM ins_light_daily_consumption(p_light_id, p_delta);
	  END IF;
	
   END IF;
   
END;
$$
  LANGUAGE plpgsql;

  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE FUNCTION trigger_analytics_consumption_add_operational_data_value()
  RETURNS trigger AS
$$
DECLARE
    
    v_last_consumption double precision;
    v_delta_consumption double precision;
    v_op_data_type_consumption integer;
    v_property_light_source integer;
    v_refcursor refcursor;
    v_record RECORD;
    v_analytic_group_id integer;
    v_analytic_group_by_date_id integer;
    v_lamp_type varchar(4094);
    v_light_type_id integer;
    v_status_message RECORD;
    v_property_consumption integer;
    v_rni_id integer;
    v_new_date Date;
    
BEGIN

    v_op_data_type_consumption := 1;
    v_property_light_source := 51;
    v_property_consumption := 87;

	v_new_date := current_timestamp;

    IF (NEW.operational_data_type_id = v_op_data_type_consumption) THEN
    
        SELECT light_id ,tenant_id ,message_date
          INTO v_status_message
          FROM status_message 
         WHERE status_message_id = NEW.status_message_id;


        -- get the previcous consumption (right before the actual message)
        v_last_consumption := (SELECT value AS endValue 
                                 FROM operational_data_value odv 
                                     ,status_message sm 
                                WHERE sm.light_id = v_status_message.light_id 
                                  AND odv.create_date != v_new_date
                                  AND sm.status_message_id = odv.status_message_id
                                  AND odv.operational_data_type_id = v_op_data_type_consumption 
                                ORDER BY odv.create_date desc LIMIT 1);


        -- if there is no previous consumption, do not calculate
        IF COALESCE(v_last_consumption,0) > 0 THEN

        
            v_delta_consumption := NEW.value - COALESCE(v_last_consumption,0);

            -- if the previous consumption is the same of the actual, do not calculate
            IF COALESCE(v_delta_consumption,0) > 0 THEN
            
              v_lamp_type := (SELECT property_value FROM light_property WHERE property_id = v_property_light_source AND light_id = v_status_message.light_id);

              IF v_lamp_type = 'I' THEN
                v_lamp_type = 'INDUCTION';
              ELSIF v_lamp_type = 'L' THEN
                v_lamp_type = 'LED';
              ELSE
                v_lamp_type = 'OTHER';
              END IF;
              
              v_light_type_id := (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = v_lamp_type);
              
              v_refcursor := get_cursor_groups_for_light(v_status_message.light_id);
            
              LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;
                                                                                                    
                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name);
                                                    
                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);
                                                                                    
                -- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
                PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);
                                                                                                    
              END LOOP;
                                    
              v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND analytics_group.tenant_id = v_status_message.tenant_id);

              v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);

              PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);

                          v_rni_id := (SELECT smp.rni_id FROM light l, smartpoint smp WHERE l.light_id = v_status_message.light_id AND smp.smartpoint_id = l.smartpoint_id);

                          -- Update the comsumption property value
              PERFORM upsert_light_property(v_property_consumption, v_rni_id, CAST(v_delta_consumption AS character varying), NEW.create_user);
                  
	         -------------------------UPDATE LIGHT DAILY CONSUMPTION---------------------------------
			 PERFORM upsert_light_daily_consumption(v_status_message.light_id, v_delta_consumption);	
				  
                  
            END IF;
	  ELSE
			 -------------------------UPDATE LIGHT DAILY CONSUMPTION---------------------------------
			PERFORM upsert_light_daily_consumption(v_status_message.light_id, NEW.value );	
      END IF;

        	
    END IF;
    RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_analytics_carbon_credits_by_date(IN p_date_type_id character varying, IN p_tenant_id integer, IN p_group_id integer, IN p_init_date timestamp with time zone, IN p_end_date timestamp with time zone, IN p_carbon_credits_factor double precision, IN p_barrels_of_oil_factor double precision, IN p_metric_of_co_factor double precision, IN p_grouping_ids character varying)
  RETURNS TABLE(description character varying, date_time timestamp with time zone, amount numeric) AS
$$
BEGIN 
   RETURN QUERY 
       (

	SELECT 'sensus.mlc.conservation.credits_created'::character varying AS description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time, 
			COALESCE(get_analytics_carbon_credits_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_carbon_credits_factor,NULL,p_grouping_ids),0)::NUMERIC AS amount
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time

	UNION 

	SELECT 'sensus.mlc.conservation.energy_saved'::character varying AS  description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time, 
			COALESCE(get_analytics_energy_savings_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_grouping_ids),0)::NUMERIC AS amount 			
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time

	UNION 

	SELECT 'sensus.mlc.conservation.barrels_oil_saved'::character varying AS  description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time, 
			COALESCE(get_analytics_carbon_credits_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_barrels_of_oil_factor,NULL,p_grouping_ids),0)::NUMERIC AS amount
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time

	UNION 

	SELECT 'sensus.mlc.conservation.metric_tons_co'::character varying AS  description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time, 
			COALESCE(get_analytics_carbon_credits_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_metric_of_co_factor,NULL,p_grouping_ids),0)::NUMERIC AS amount
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time
	
	ORDER BY 2,1
       );               
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_light_calculation_retroactive_ecomode(p_light_id integer, p_calculate_retroactive_ecomode boolean)
  RETURNS void AS
$$
BEGIN

  UPDATE light
     SET calculate_retroactive_ecomode = p_calculate_retroactive_ecomode
  WHERE light_id = p_light_id;
  
END  
$$
  LANGUAGE plpgsql;
  
  
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_analytics_group_by_date_pk(p_analytic_group_id integer, p_analytic_date timestamp with time zone, p_create_user character varying)
  RETURNS integer AS
$$
DECLARE
        v_id int;

BEGIN

        -- retrieve analytic_group_by_date_id using group_id and date passed in (most likely current date)
        v_id:= (SELECT max(analytic_group_by_date_id) FROM analytics_group_by_date WHERE analytic_group_id = p_analytic_group_id AND DATE_TRUNC('minute',analytic_date) = DATE_TRUNC('minute',p_analytic_date));
        
        -- if not found, insert, otherwise return id found
        IF (v_id IS NULL AND p_analytic_group_id IS NOT NULL)
        THEN
                v_id:= (SELECT ins_analytics_group_by_date(p_analytic_group_id, p_analytic_date, p_create_user));
        END IF;
           
    RETURN v_id;
END
$$
  LANGUAGE plpgsql;