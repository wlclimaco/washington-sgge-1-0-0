DROP FUNCTION IF EXISTS add_smartpoint_to_group(integer, integer, character varying);
DROP FUNCTION IF EXISTS cnt_smartpoints_in_group(integer);
DROP FUNCTION IF EXISTS del_smartpoint_from_group(integer ,integer);
DROP FUNCTION IF EXISTS get_alarms_columns_by_group_id(integer,date,date);
DROP FUNCTION IF EXISTS get_all_analytics_groups(integer);
DROP FUNCTION IF EXISTS get_all_lights_by_process(integer,integer,integer);
DROP FUNCTION IF EXISTS get_all_tenant();
DROP FUNCTION IF EXISTS get_analytics_energy_savings_by_date(character varying, integer, integer, date, date, character varying);
DROP FUNCTION IF EXISTS get_analytics_pagination_total_rows();
DROP FUNCTION IF EXISTS get_consumption_by_light_type(integer, timestamp without time zone, timestamp without time zone, integer);
DROP FUNCTION IF EXISTS get_consumption_columns_by_group_id(integer, date, date);
DROP FUNCTION IF EXISTS get_count_light_groups(integer);
DROP FUNCTION IF EXISTS get_count_monitored_processes(integer, character varying);
DROP FUNCTION IF EXISTS get_count_running_lrp(integer, character varying);
DROP FUNCTION IF EXISTS get_current_alarms(integer,integer);
DROP FUNCTION IF EXISTS get_cursor_light_by_tenant(integer);
DROP FUNCTION IF EXISTS get_custom_search_by_id(integer);
DROP FUNCTION IF EXISTS get_custom_search_count_by_name(integer, character varying, character varying);
DROP FUNCTION IF EXISTS get_custom_search_properties(integer);
DROP FUNCTION IF EXISTS get_failure_by_id(integer);
DROP FUNCTION IF EXISTS get_group(integer);
DROP FUNCTION IF EXISTS get_group_by_name(character varying);
DROP FUNCTION IF EXISTS get_group_count_by_name(integer, character varying);
DROP FUNCTION IF EXISTS get_groups_and_smartpoint_count();
DROP FUNCTION IF EXISTS get_groups_by_light(integer, integer);
DROP FUNCTION IF EXISTS get_groups_by_smartpoint(integer, integer);
DROP FUNCTION IF EXISTS get_installed_columns_by_group_id(integer, date, date);
DROP FUNCTION IF EXISTS get_is_light_in_group(integer, integer);
DROP FUNCTION IF EXISTS get_is_light_in_tenant(integer, character varying);
DROP FUNCTION IF EXISTS get_lc_action_by_id(integer);
DROP FUNCTION IF EXISTS get_light_by_id(integer);
DROP FUNCTION IF EXISTS get_light_by_smartpoint_id(integer);
DROP FUNCTION IF EXISTS get_light_consumption(integer, timestamp without time zone, timestamp without time zone);
DROP FUNCTION IF EXISTS get_light_history(integer, integer, integer, integer);
DROP FUNCTION IF EXISTS get_light_history_header(integer);
DROP FUNCTION IF EXISTS get_light_history_pagination_total_rows(integer, integer);
DROP FUNCTION IF EXISTS get_light_is_communication_fail(integer);
DROP FUNCTION IF EXISTS get_process_by_file_name(character varying);
DROP FUNCTION IF EXISTS get_process_by_id(integer);
DROP FUNCTION IF EXISTS get_process_by_light_id(integer);
DROP FUNCTION IF EXISTS get_process_by_rni_id(character varying);
DROP FUNCTION IF EXISTS get_process_property_by_process(integer);
DROP FUNCTION IF EXISTS get_process_result_by_lrp(integer);
DROP FUNCTION IF EXISTS get_properties_by_light(integer);
DROP FUNCTION IF EXISTS get_properties_by_light_class_1(integer, integer);
DROP FUNCTION IF EXISTS get_property_by_light_rni_id(integer, integer);
DROP FUNCTION IF EXISTS get_schedule(integer);
DROP FUNCTION IF EXISTS get_schedule_codes();
DROP FUNCTION IF EXISTS get_schedule_count_by_name(integer, character varying);
DROP FUNCTION IF EXISTS get_schedule_event_day_of_week(integer);
DROP FUNCTION IF EXISTS get_schedule_events(integer);
DROP FUNCTION IF EXISTS get_schedules_and_smartpoint_count();
DROP FUNCTION IF EXISTS get_smartpoint_from_schedule(integer, integer);
DROP FUNCTION IF EXISTS get_smartpoint_id_by_rni_id(integer);
DROP FUNCTION IF EXISTS get_smartpoints_by_tag_id(integer, integer);
DROP FUNCTION IF EXISTS get_status_exception_by_light(integer);
DROP FUNCTION IF EXISTS get_status_message_by_light(integer);
DROP FUNCTION IF EXISTS get_status_message_by_light_detail(integer);
DROP FUNCTION IF EXISTS get_status_message_by_light_message_type(integer, integer);
DROP FUNCTION IF EXISTS get_status_message_communication_fail(integer);
DROP FUNCTION IF EXISTS get_system_settings(integer);
DROP FUNCTION IF EXISTS get_tag_by_name(character varying);
DROP FUNCTION IF EXISTS get_tag_pagination_total_rows(integer);
DROP FUNCTION IF EXISTS get_tags_by_light_id(integer, integer);
DROP FUNCTION IF EXISTS get_tags_by_smartpoint_id(integer, integer);
DROP FUNCTION IF EXISTS get_tenant_by_id(integer);
DROP FUNCTION IF EXISTS get_tenant_by_rni_code(character varying);
DROP FUNCTION IF EXISTS get_tenant_by_server_name(character varying);
DROP FUNCTION IF EXISTS get_unsubmitted_processes();
DROP FUNCTION IF EXISTS get_user_settings(character varying, integer);
DROP FUNCTION IF EXISTS get_warnings_columns_by_group_id(integer, date, date);
DROP FUNCTION IF EXISTS ins_firmware_version(integer, character varying, character varying);
DROP FUNCTION IF EXISTS ins_light(integer, character varying, integer, integer);
DROP FUNCTION IF EXISTS ins_smartpoint_to_tag(integer, integer, character varying);
DROP FUNCTION IF EXISTS upd_light_property(integer, integer , character varying, character varying);
DROP FUNCTION IF EXISTS upd_light_protected(integer, boolean, character varying);
DROP FUNCTION IF EXISTS upd_schedule_event(integer , timestamp with time zone , integer, character varying);
DROP FUNCTION IF EXISTS upd_schedule_event_day_of_week(integer, integer, character varying);
DROP FUNCTION IF EXISTS upd_user(integer,character varying, character varying, character varying, character varying, character varying, boolean, character varying);

DROP FUNCTION IF EXISTS get_location_has_changed(integer, double precision, double precision);
DROP FUNCTION IF EXISTS get_analytics_alarms(integer, integer, integer);
DROP FUNCTION IF EXISTS get_analytics_alarms_by_status_id(integer, integer, integer);
DROP FUNCTION IF EXISTS get_pole_id_by_light(integer);
DROP FUNCTION IF EXISTS get_time_zone_by_light(integer);
DROP FUNCTION IF EXISTS ins_min_max(integer, character varying, integer, character varying);
DROP FUNCTION IF EXISTS reset_min_max(integer, character varying);
DROP FUNCTION IF EXISTS upd_light_properties_for_comm_fail(integer);
DROP FUNCTION IF EXISTS upd_min_max(integer, character varying, integer);

DROP FUNCTION IF EXISTS ins_custom_search(character varying, character varying, character varying, integer, integer);
DROP FUNCTION IF EXISTS ins_property_to_custom_search(integer, integer, character varying, integer, character varying);
DROP FUNCTION IF EXISTS del_custom_search (integer);
DROP FUNCTION IF EXISTS ins_columns_to_custom_search(integer, character varying, integer, character varying);
DROP FUNCTION IF EXISTS ins_filters_to_custom_search(integer, character varying, integer, character varying);

DROP FUNCTION IF EXISTS del_smartpoint_from_tag(integer, integer);
DROP FUNCTION IF EXISTS del_smartpoint_from_schedule(integer, integer);
DROP FUNCTION IF EXISTS del_smartpoint_from_all_tag_schedule_group(integer);
DROP FUNCTION IF EXISTS ins_status_message(timestamp with time zone, integer, boolean, integer, integer, integer, character varying);
DROP FUNCTION IF EXISTS ins_status_message_status_subtype (integer, integer, character varying);
DROP FUNCTION IF EXISTS upd_light_current_status_message(integer, integer);
DROP FUNCTION IF EXISTS upd_light_status(integer, integer, character varying, integer, integer, integer, timestamp with time zone, timestamp with time zone);
DROP FUNCTION IF EXISTS upsert_light_property(integer, integer, character varying, character varying);

DROP FUNCTION IF EXISTS get_cursor_for_configuration_part_number();
DROP FUNCTION IF EXISTS get_light_configuration_by_part_number(integer);
DROP FUNCTION IF EXISTS get_light_intensity_percentage_by_light(integer);

DROP FUNCTION IF EXISTS trigger_upd_light_property() CASCADE;
DROP FUNCTION IF EXISTS trigger_ins_light_property() CASCADE;

-- Analytic Eco-Mode function
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

   IF (v_ecomode IS NOT NULL AND v_ecomode < 100) THEN

        v_result := ((v_consumption * 100)/(100 - v_ecomode)) - v_consumption;

   ELSE
	IF (v_ecomode IS NOT NULL AND v_ecomode >= 100) THEN

         v_result := v_consumption;

	END IF;

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
CREATE OR REPLACE FUNCTION trigger_insert_min_max()
  RETURNS trigger AS
$$
BEGIN
	-- Set Min/Max values to voltage
           NEW.ac_voltage_max := NEW.ac_voltage;
	   NEW.ac_voltage_max_date := CURRENT_TIMESTAMP;
           NEW.ac_voltage_min := NEW.ac_voltage;
	   NEW.ac_voltage_min_date := CURRENT_TIMESTAMP;

        -- Set Min/Max values to current
           NEW.ac_current_max := NEW.ac_current;
	   NEW.ac_current_max_date := CURRENT_TIMESTAMP;
           NEW.ac_current_min := NEW.ac_current;
	   NEW.ac_current_min_date := CURRENT_TIMESTAMP;

        -- Set Min/Max values to consumption
          NEW.consumption_max := NEW.consumption;
	  NEW.consumption_min := NEW.consumption;


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

-- Function: trigger_modified_min_max()

-- DROP FUNCTION trigger_modified_min_max();

CREATE OR REPLACE FUNCTION trigger_modified_min_max()
  RETURNS trigger AS
$$
BEGIN
	-- Set Min/Max values to voltage
        IF(NEW.ac_voltage > OLD.ac_voltage) THEN
	   NEW.ac_voltage_max := NEW.ac_voltage;
	   NEW.ac_voltage_max_date := CURRENT_TIMESTAMP;
        END IF;

	IF(NEW.ac_voltage < OLD.ac_voltage) THEN
	   NEW.ac_voltage_min := NEW.ac_voltage;
	   NEW.ac_voltage_min_date := CURRENT_TIMESTAMP;
        END IF;

	-- Set Min/Max values to current
        IF(NEW.ac_current > OLD.ac_current) THEN
	   NEW.ac_current_max := NEW.ac_current;
	   NEW.ac_current_max_date := CURRENT_TIMESTAMP;
        END IF;

	IF(NEW.ac_current < OLD.ac_current) THEN
	   NEW.ac_current_min := NEW.ac_current;
	   NEW.ac_current_min_date := CURRENT_TIMESTAMP;
        END IF;

	-- Set Min/Max values to consumption
        IF(NEW.consumption > OLD.consumption) THEN
	  NEW.consumption_max := NEW.consumption;
	END IF;

	IF(NEW.consumption < OLD.consumption) THEN
	   NEW.consumption_min := NEW.consumption;
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
CREATE OR REPLACE FUNCTION get_dashboard_header(IN p_tenant_id integer, IN p_carbon_credits_factor double precision, IN p_grouping_ids character varying)
  RETURNS TABLE(description character varying, date_time timestamp without time zone, amount double precision) AS
$$
DECLARE

    p_init_date timestamp;
    p_end_date timestamp;

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

    p_type_alarm int;
    p_type_warning int;

BEGIN

    p_type_alarm := 1;
    p_type_warning := 2;

    w_array_type_column[0]:= 'Total Installed';
    w_array_type_column[1]:= 'Total Alarms';
    w_array_type_column[2]:= 'Total Warnings';

    w_array_type_column[3]:= 'Total Consumption';
    w_array_type_column[4]:= 'Total Eco-Mode';
    w_array_type_column[5]:= 'Total Carbon Credits';

    p_init_date := (SELECT CURRENT_DATE);
    p_end_date := (SELECT DATE_TRUNC('day',CURRENT_TIMESTAMP) - INTERVAL '1 second');

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
				       FROM light l
				     WHERE l.tenant_id       = p_tenant_id
				       AND l.light_status = p_type_alarm);
    ELSE

		w_array_total[1] := (SELECT COUNT(DISTINCT(l.light_id))
				       FROM light l
					    ,smartpoint_grouping smp_grp
				     WHERE l.tenant_id = p_tenant_id
				       AND l.smartpoint_id = smp_grp.smartpoint_id
				       AND smp_grp.grouping_id IN (SELECT * FROM split(p_grouping_ids,','))
				       AND l.light_status = p_type_alarm);

    END IF;
    w_array_date[1] := p_init_date;

    -- Total Warnigs
    IF(p_grouping_ids IS NULL) THEN

		w_array_total[2] := (SELECT COUNT(1)
				       FROM light l
				     WHERE l.tenant_id       = p_tenant_id
				       AND l.light_status = p_type_warning);
	ELSE

		w_array_total[2] := (SELECT COUNT(DISTINCT(l.light_id))
				       FROM light l
					    ,smartpoint_grouping smp_grp
				     WHERE l.tenant_id = p_tenant_id
				       AND l.smartpoint_id = smp_grp.smartpoint_id
				       AND smp_grp.grouping_id IN (SELECT * FROM split(p_grouping_ids,','))
				       AND l.light_status = p_type_warning);
	END IF;
    w_array_date[2] := p_init_date;

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

    --Total Consumption
    w_array_total[3] := ((SELECT get_analytics_consumption_total(p_tenant_id, v_analytic_group_id, p_init_date, p_end_date, NULL, p_grouping_ids)));
    w_array_date[3] := p_init_date;

    --Total Eco-Mode
    w_array_total[4] := (SELECT get_analytics_eco_mode_total(p_tenant_id, v_analytic_group_id, p_init_date, p_end_date, p_grouping_ids));
    w_array_date[4] := p_init_date;

    -- Total Carbon Credits
	w_array_total[5] := (SELECT get_analytics_carbon_credits_total(p_tenant_id, v_analytic_group_id, p_init_date, p_end_date, p_carbon_credits_factor, NULL, p_grouping_ids));
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
    p_timezone CHARACTER VARYING;

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
    p_timezone := (SELECT light_time_zone FROM tenant WHERE tenant_id = p_tenant_id);

   FOR i IN 0..array_upper(p_array_view_mode, 1) LOOP

	   IF (p_array_view_mode[i] = 'week') THEN
		p_init_date := (SELECT (DATE_TRUNC('day',CURRENT_TIMESTAMP - INTERVAL '1 week') + (CURRENT_TIMESTAMP at time zone 'UTC' - CURRENT_TIMESTAMP at time zone p_timezone)))::timestamp without time zone;
	   ELSIF (p_array_view_mode[i] = 'month') THEN
		p_init_date := (SELECT (DATE_TRUNC('day',CURRENT_TIMESTAMP - INTERVAL '1 month') + (CURRENT_TIMESTAMP at time zone 'UTC' - CURRENT_TIMESTAMP at time zone p_timezone)))::timestamp without time zone;
	   END IF;

	   p_end_date := (SELECT (DATE_TRUNC('day',CURRENT_TIMESTAMP) - INTERVAL '1 second') + (CURRENT_TIMESTAMP at time zone 'UTC' - CURRENT_TIMESTAMP at time zone p_timezone))::timestamp with time zone;

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
CREATE OR REPLACE FUNCTION trigger_analytics_consumption_add_operational_data_value()
  RETURNS trigger AS
$$
    DECLARE
        v_last_consumption DOUBLE PRECISION;
        v_delta_consumption DOUBLE PRECISION;
        v_op_data_type_consumption INTEGER;
        v_refcursor refcursor;
        v_record RECORD;
        v_analytic_group_id         INTEGER;
        v_analytic_group_by_date_id INTEGER;
        v_light_type                VARCHAR(4094);
        v_light_type_id             INTEGER;
        v_status_message RECORD;
        v_rni_id   INTEGER;
        v_new_date DATE;
    BEGIN
        v_op_data_type_consumption      := 1;
        v_new_date                      := CURRENT_TIMESTAMP;
        IF (NEW.operational_data_type_id = v_op_data_type_consumption) THEN
            SELECT light_id ,
                tenant_id   ,
                message_date
            INTO v_status_message
            FROM status_message
            WHERE status_message_id = NEW.status_message_id;

            -- get the previous consumption (right before the actual message)
            v_last_consumption := (SELECT value AS endValue
                                     FROM operational_data_value odv ,
                                          status_message sm
                                    WHERE sm.light_id                = v_status_message.light_id
                                      AND odv.create_date             != v_new_date
                                      AND sm.status_message_id         = odv.status_message_id
                                      AND odv.operational_data_type_id = v_op_data_type_consumption
                                      ORDER BY odv.create_date DESC LIMIT 1);

            v_light_type := (SELECT light_type
                               FROM light
                              WHERE light_id = v_status_message.light_id);

            -- analytics_light_type table mirrors the enum INDUCTION(1),LED(2),OTHER(3);
            v_light_type_id     := v_light_type;
            v_refcursor         := get_cursor_groups_for_light(v_status_message.light_id);
            v_delta_consumption := NEW.value - COALESCE(v_last_consumption,0);
            LOOP
                FETCH v_refcursor
                INTO v_record;

                EXIT
            WHEN NOT FOUND;
                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id
                                          FROM analytics_group
                                         WHERE analytic_group_name = v_record.name
                                           AND tenant_id             = v_status_message.tenant_id);
                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);

				-- Update analytics_consumption for light_type, current_date, Group=<current_group>
                PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);
            END LOOP;
            -- Process group = 'All'
            v_analytic_group_id := (SELECT analytic_group_id
                                      FROM analytics_group
                                     WHERE analytic_group_name     = 'All'
                                       AND analytics_group.tenant_id = v_status_message.tenant_id);

            v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_status_message.message_date,NEW.create_user);
            PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user, v_delta_consumption);

			-- Update LIGHT DAILY CONSUMPTION. This is used when calculating ecomode
            PERFORM upsert_light_daily_consumption(v_status_message.light_id, v_delta_consumption);

        END IF;
        RETURN NEW;
    END;
    $$
  LANGUAGE plpgsql;
