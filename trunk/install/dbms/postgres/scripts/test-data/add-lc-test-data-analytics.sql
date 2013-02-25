CREATE OR REPLACE FUNCTION upd_analytics_alarms_test(p_status_message_id integer, p_alarm_subtype_id integer, p_create_user character varying, type character, p_operator character, p_datetime timestamp with time zone)
  RETURNS void AS
$BODY$
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
		 
        v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, p_datetime,p_create_user));
        
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
        
        IF (p_alarm_subtype_id >= 1 AND p_alarm_subtype_id <= 5) THEN
                PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
        END IF;
        
        IF (p_alarm_subtype_id >= 6 AND p_alarm_subtype_id <= 12) THEN

                PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator);
                                
        END IF;
        
        v_refcursor := get_cursor_groups_for_light(v_light_id);
                                
        LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;
                                        
                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name AND tenant_id = v_status_message.tenant_id);
                
				IF v_analytic_group_id IS NOT NULL THEN
					
					-- Get the analytic_group_by_date_id.
					v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, p_datetime,p_create_user));
                                
					-- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
					IF (p_alarm_subtype_id >= 1 AND p_alarm_subtype_id <= 5) THEN
               
						PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
					END IF;
        
					IF (p_alarm_subtype_id >= 6 AND p_alarm_subtype_id >= 12) THEN
                
                        PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
					END IF;
				
				END IF;
				                                        
        END LOOP;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
  
 CREATE OR REPLACE FUNCTION upsert_analytics_installed_test(p_light_id int)
  RETURNS void AS
$BODY$
DECLARE
        v_refcursor refcursor;
		v_record RECORD;
        v_analytic_group_id integer;
        v_analytic_group_by_date_id integer;

BEGIN

    -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND tenant_id = (SELECT tenant_id FROM light WHERE light_id = p_light_id));
                
                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP + cast((-1)||' days' as interval),'BOT'));
            

        UPDATE analytics_installed SET value = value + 1 WHERE (analytic_group_by_date_id = v_analytic_group_by_date_id) AND (light_type_id = (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION'));
                                
        IF NOT FOUND THEN
            PERFORM ins_analytics_installed((SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION'), 1, v_analytic_group_by_date_id, 'BOT');
        END IF;

        v_refcursor := get_cursor_groups_for_light(p_light_id);
                                
        LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;
                                        
                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name AND tenant_id = (SELECT tenant_id FROM light WHERE light_id = p_light_id));
                
                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP + cast((-1)||' days' as interval),'BOT'));
 
				UPDATE analytics_installed SET value = value + 1 WHERE (analytic_group_by_date_id = v_analytic_group_by_date_id) AND (light_type_id = (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION'));
                                
        IF NOT FOUND THEN
            PERFORM ins_analytics_installed((SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION'), 1, v_analytic_group_by_date_id, 'BOT');
        END IF;
 
        END LOOP;
        
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
