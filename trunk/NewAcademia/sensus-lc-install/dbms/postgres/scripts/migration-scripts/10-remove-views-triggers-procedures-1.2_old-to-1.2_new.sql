/* Views ******************************* */

DROP VIEW vw_current_message;
DROP VIEW vw_group_smartpoint_count;
DROP VIEW vw_process_smartpoint_count;

/* ************************************* */


/* Triggers **************************** */

CREATE OR REPLACE FUNCTION strip_all_triggers() RETURNS text AS $$ DECLARE
        triggNameRecord RECORD;
    triggTableRecord RECORD;
BEGIN
    FOR triggNameRecord IN select distinct(trigger_name) from information_schema.triggers where trigger_schema = 'public' LOOP
        SELECT distinct(event_object_table) INTO triggTableRecord from information_schema.triggers where trigger_name = triggNameRecord.trigger_name;
        RAISE NOTICE 'Dropping trigger: % on table: %', triggNameRecord.trigger_name, triggTableRecord.event_object_table;
        EXECUTE 'DROP TRIGGER ' || triggNameRecord.trigger_name || ' ON ' || triggTableRecord.event_object_table || ';';
    END LOOP;

    RETURN 'done';
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

SELECT strip_all_triggers();

DROP FUNCTION strip_all_triggers();

/* ************************************* */


/* Procedures ************************** */

DROP FUNCTION public.apply_smartpoint_in_schedule(integer, integer, character varying);
DROP FUNCTION public.apply_unknown_event_schedule(integer, integer, character varying);
DROP FUNCTION public.apply_unknown_offset_schedule(integer, integer, character varying);
DROP FUNCTION public.calculate_analytics_ecomode();
DROP FUNCTION public.calculate_dashboard_resume(double precision, integer, integer);
DROP FUNCTION consumption_fix_all_lights(integer);
DROP FUNCTION consumption_fix_all_lights_all_tenants();
DROP FUNCTION public.del_addr_tags_for_light(integer);
DROP FUNCTION public.del_column_to_user(integer, integer);
DROP FUNCTION public.del_current_alarm_warning_messages_by_light_id(integer);
DROP FUNCTION public.del_dashboard_resume(integer);
DROP FUNCTION public.del_filter_to_user(integer, integer);
DROP FUNCTION public.del_group(integer);
DROP FUNCTION public.del_groups_by_user(integer);
DROP FUNCTION public.del_light(integer);
DROP FUNCTION public.del_old_data(integer);
DROP FUNCTION public.del_roles_by_user(integer);
DROP FUNCTION public.del_schedule(integer);
DROP FUNCTION public.del_schedule_event(integer);
DROP FUNCTION public.del_tag(integer);
DROP FUNCTION public.del_user(integer, integer);
DROP FUNCTION public.get_all_groups_by_tenant_for_dashboard(integer);
DROP FUNCTION public.get_analytics_alarms_by_type(integer, integer, timestamp with time zone, timestamp with time zone, integer, character varying);
DROP FUNCTION public.get_analytics_alarms_total(integer, integer, timestamp with time zone, timestamp with time zone, integer, character varying);
DROP FUNCTION public.get_analytics_average(integer, integer, character varying, character varying, double precision, character varying);
DROP FUNCTION public.get_analytics_carbon_credits_by_date(character varying, integer, integer, timestamp with time zone, timestamp with time zone, double precision, double precision, double precision, character varying);
DROP FUNCTION public.get_analytics_carbon_credits_total(integer, integer, timestamp without time zone, timestamp without time zone, double precision, integer, character varying);
DROP FUNCTION public.get_analytics_change(integer, integer, character varying, character varying, double precision, character varying);
DROP FUNCTION public.get_analytics_consumption_total(integer, integer, timestamp with time zone, timestamp with time zone, integer, character varying);
DROP FUNCTION public.get_analytics_eco_mode_total(integer, integer, timestamp with time zone, timestamp with time zone, character varying);
DROP FUNCTION public.get_analytics_energy_savings_total(integer, integer, timestamp without time zone, timestamp without time zone, character varying);
DROP FUNCTION public.get_analytics_group_by_date_pk(integer, timestamp with time zone, character varying);
DROP FUNCTION public.get_analytics_installed_total(integer, integer, timestamp with time zone, timestamp with time zone, integer, character varying);
DROP FUNCTION public.get_analytics_pagination_total_rows(integer, integer);
DROP FUNCTION public.get_analytics_trend(integer, character varying, character varying, double precision, integer, character varying);
DROP FUNCTION public.get_analytics_warnings_by_type(integer, integer, timestamp with time zone, timestamp with time zone, integer, character varying);
DROP FUNCTION public.get_analytics_warnings_total(integer, integer, timestamp with time zone, timestamp with time zone, integer, character varying);
DROP FUNCTION public.get_average_light_consumption(integer, timestamp with time zone, timestamp with time zone);
DROP FUNCTION public.get_average_light_current(integer, timestamp with time zone, timestamp with time zone);
DROP FUNCTION public.get_average_light_voltage(integer, timestamp with time zone, timestamp with time zone);
DROP FUNCTION public.get_cursor_for_tenant();
DROP FUNCTION public.get_cursor_groups_for_light(integer);
DROP FUNCTION public.get_dashboard_header(integer, double precision, character varying);
DROP FUNCTION public.get_group_list_by_tenant(character varying, integer);
DROP FUNCTION public.get_last_operational_data_value(integer, integer);
DROP FUNCTION public.get_light_id_by_rni_id(integer);
DROP FUNCTION public.get_lights_by_tag_id(integer);
DROP FUNCTION public.get_smartpoints_by_group_list_to_map(character varying, integer, double precision, double precision, double precision, double precision, integer);
DROP FUNCTION public.get_smartpoints_by_schedule_to_map(integer, double precision, double precision, double precision, double precision, integer);
DROP FUNCTION public.get_status_message_by_id(integer);
DROP FUNCTION public.get_tag_by_id(integer);
DROP FUNCTION public.ins_analytics_alarms(integer, integer, integer, character varying, integer);
DROP FUNCTION public.ins_analytics_consumption(integer, integer, character varying, double precision);
DROP FUNCTION public.ins_analytics_group(character varying, integer, character varying, integer);
DROP FUNCTION public.ins_analytics_group_by_date(integer, timestamp with time zone, character varying);
DROP FUNCTION public.ins_analytics_installed(integer, integer, integer, character varying);
DROP FUNCTION public.ins_analytics_warnings(integer, integer, integer, character varying, integer);
DROP FUNCTION public.ins_columns_to_user(integer, character varying, integer, character varying, integer);
DROP FUNCTION public.ins_current_alarm_warning_message(integer, integer, integer, integer, integer, timestamp with time zone, integer);
DROP FUNCTION public.ins_filters_to_user(integer, character varying, integer, character varying, integer);
DROP FUNCTION public.ins_group(character varying, character varying, integer, character varying);
DROP FUNCTION public.ins_group_to_user(integer, integer, character varying);
DROP FUNCTION public.ins_light_daily_consumption(integer, double precision);
DROP FUNCTION public.ins_operational_data_value(integer, integer, double precision, character varying);
DROP FUNCTION public.ins_process(character varying, character varying, timestamp with time zone, timestamp with time zone, character varying, boolean, integer, integer, integer, boolean, bigint, character varying, boolean, boolean);
DROP FUNCTION public.ins_role_to_user(integer, character varying);
DROP FUNCTION public.ins_schedule(character varying, character varying, integer, integer, integer, character varying, integer, boolean, integer);
DROP FUNCTION public.ins_schedule_event(timestamp with time zone, integer, integer, integer, integer, character varying);
DROP FUNCTION public.ins_schedule_event_day_of_week(integer, integer, character varying);
DROP FUNCTION public.ins_smartpoint_process(integer, integer, integer, integer);
DROP FUNCTION public.ins_tag(character varying, boolean, boolean, integer, character varying);
DROP FUNCTION public.ins_user(integer, character varying, character varying, character varying, character varying, character varying, boolean, character varying);
DROP FUNCTION public.insert_smartpoint_to_auto_group(integer, integer, character varying);
DROP FUNCTION public.split(character varying, character varying);
DROP FUNCTION public.trigger_analytics_consumption_add_operational_data_value();
DROP FUNCTION public.trigger_analytics_grouping_del_grouping();
DROP FUNCTION public.trigger_analytics_grouping_ins_grouping();
DROP FUNCTION public.trigger_analytics_grouping_ins_tenant();
DROP FUNCTION public.trigger_analytics_installed_add_smartpoint_to_group();
DROP FUNCTION public.trigger_analytics_installed_del_smartpoint_from_group();
DROP FUNCTION public.trigger_create_date();
DROP FUNCTION public.trigger_ins_light();
DROP FUNCTION public.trigger_insert_min_max();
DROP FUNCTION public.trigger_modified_date();
DROP FUNCTION public.trigger_modified_min_max();
DROP FUNCTION public.trigger_upd_light();
DROP FUNCTION public.trigger_update_light_status();
DROP FUNCTION public.trigger_update_light_status_subtype();
DROP FUNCTION public.upd_analytics_alarms_warnings(integer, integer, character varying, character, character);
DROP FUNCTION public.upd_analytics_alarms_warnings_by_light(integer, integer, integer, character varying, character, character);
DROP FUNCTION public.upd_auto_group_tag(integer, boolean);
DROP FUNCTION public.upd_group(integer, character varying, character varying, character varying, character varying);
DROP FUNCTION public.upd_group_center(integer);
DROP FUNCTION public.upd_is_monitored_process(boolean, integer);
DROP FUNCTION public.upd_light_calculation_retroactive_ecomode(integer, boolean);
DROP FUNCTION public.upd_light_lat_lng(integer, double precision, double precision, character varying);
DROP FUNCTION public.upd_map_center(integer);
DROP FUNCTION public.upd_process(timestamp with time zone, boolean, boolean, integer, boolean, character varying, character varying);
DROP FUNCTION public.upd_process_is_first_level(integer, boolean);
DROP FUNCTION public.upd_schedule(integer, character varying, character varying, integer, integer, character varying, integer);
DROP FUNCTION public.upd_schedule_center(integer);
DROP FUNCTION public.upd_smartpoint_process(integer, integer, integer, integer);
DROP FUNCTION public.upd_system_settings(integer, integer, integer, character varying);
DROP FUNCTION public.upsert_analytics_alarms(integer, integer, integer, character varying, character);
DROP FUNCTION public.upsert_analytics_consumption(integer, integer, character varying, double precision);
DROP FUNCTION public.upsert_analytics_installed(integer, integer, character varying, character);
DROP FUNCTION public.upsert_analytics_warnings(integer, integer, integer, character varying, character);
DROP FUNCTION public.upsert_ecomode(integer, integer, date, boolean, double precision, integer, double precision, double precision);
DROP FUNCTION public.upsert_light_daily_consumption(integer, double precision);
DROP FUNCTION public.upsert_process_property(integer, integer, character varying);
DROP FUNCTION public.upsert_settings(integer, character varying, integer, integer, character varying);
DROP FUNCTION public.upsert_tenant_lat_long(integer, double precision, double precision);

/* ************************************* */