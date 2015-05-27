---------------------------------------------
-- users
---------------------------------------------
CREATE TRIGGER users_ins_trigger
  BEFORE INSERT
  ON users
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER users_upd_trigger
  BEFORE UPDATE
  ON users
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- user_settings
---------------------------------------------
CREATE TRIGGER user_settings_ins_trigger
  BEFORE INSERT
  ON user_settings
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- operator
---------------------------------------------
CREATE TRIGGER operator_ins_trigger
  BEFORE INSERT
  ON operator
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- custom_search
---------------------------------------------
CREATE TRIGGER custom_search_ins_trigger
  BEFORE INSERT
  ON custom_search
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER custom_search_upd_trigger
  BEFORE UPDATE
  ON custom_search
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- tenant
---------------------------------------------
CREATE TRIGGER tenant_ins_trigger
  BEFORE INSERT
  ON tenant
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- grouping
---------------------------------------------
CREATE TRIGGER grouping_ins_trigger
  BEFORE INSERT
  ON grouping
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER grouping_upd_trigger
  BEFORE UPDATE
  ON grouping
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- tag
---------------------------------------------
CREATE TRIGGER tag_ins_trigger
  BEFORE INSERT
  ON tag
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER tag_upd_trigger
  BEFORE UPDATE
  ON tag
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- schedule
---------------------------------------------
CREATE TRIGGER schedule_ins_trigger
  BEFORE INSERT
  ON schedule
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER schedule_upd_trigger
  BEFORE UPDATE
  ON schedule
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- schedule membership
---------------------------------------------
CREATE TRIGGER schedule_membership_ins_trigger
  BEFORE INSERT
  ON schedule_membership
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER schedule_membership_upd_trigger
  BEFORE UPDATE
  ON schedule_membership
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- schedule_event
---------------------------------------------
CREATE TRIGGER schedule_event_ins_trigger
  BEFORE INSERT
  ON schedule_event
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER schedule_event_upd_trigger
  BEFORE UPDATE
  ON schedule_event
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- schedule_event_day_of_week
---------------------------------------------
CREATE TRIGGER schedule_event_day_of_week_ins_trigger
  BEFORE INSERT
  ON schedule_event_day_of_week
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER schedule_event_day_of_week_upd_trigger
  BEFORE UPDATE
  ON schedule_event_day_of_week
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- light
---------------------------------------------
CREATE TRIGGER light_ins_trigger
  BEFORE INSERT
  ON light
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER light_upd_trigger
  BEFORE UPDATE
  ON light
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- user_settings_property
---------------------------------------------
CREATE TRIGGER user_settings_property_ins_trigger
  BEFORE INSERT
  ON user_settings_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER user_settings_property_upd_trigger
  BEFORE UPDATE
  ON user_settings_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- custom_search_property
---------------------------------------------
CREATE TRIGGER custom_search_property_ins_trigger
  BEFORE INSERT
  ON custom_search_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER custom_search_property_upd_trigger
  BEFORE UPDATE
  ON custom_search_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- light_tag
---------------------------------------------
CREATE TRIGGER light_tag_ins_trigger
  BEFORE INSERT
  ON light_tag
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- light_grouping
---------------------------------------------
CREATE TRIGGER light_grouping_trigger
  BEFORE INSERT
  ON light_grouping
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- light_process
---------------------------------------------
CREATE TRIGGER light_process_trigger
  BEFORE INSERT
  ON light_process
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- process
---------------------------------------------
CREATE TRIGGER process_ins_trigger
  BEFORE INSERT
  ON process
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER process_upd_trigger
  BEFORE UPDATE
  ON process
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- process_property
---------------------------------------------
CREATE TRIGGER process_property_ins_trigger
  BEFORE INSERT
  ON process_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- process_class
---------------------------------------------
CREATE TRIGGER property_class_ins_trigger
  BEFORE INSERT
  ON property_class
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- property
---------------------------------------------
CREATE TRIGGER property_ins_trigger
  BEFORE INSERT
  ON property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER property_upd_trigger
  BEFORE UPDATE
  ON property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- operational_data_type
---------------------------------------------
CREATE TRIGGER operational_data_type_ins_trigger
  BEFORE INSERT
  ON operational_data_type
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER operational_data_type_upd_trigger
  BEFORE UPDATE
  ON operational_data_type
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- notification_history
---------------------------------------------
CREATE TRIGGER notification_history_ins_trigger
  BEFORE INSERT
  ON notification_history
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- notification_history_alert
---------------------------------------------
CREATE TRIGGER notification_history_alert_ins_trigger
  BEFORE INSERT
  ON notification_history_alert
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
CREATE TRIGGER notification_history_alert_upd_trigger
  BEFORE UPDATE
  ON notification_history_alert
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- lc_action
---------------------------------------------
CREATE TRIGGER lc_action_ins_trigger
  BEFORE INSERT
  ON lc_action
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- failure
---------------------------------------------
CREATE TRIGGER failure_ins_trigger
  BEFORE INSERT
  ON failure
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- alert_type
---------------------------------------------
CREATE TRIGGER alert_type_ins_trigger
  BEFORE INSERT
  ON alert_type
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- alert_subtype
---------------------------------------------
CREATE TRIGGER alert_subtype_ins_trigger
  BEFORE INSERT
  ON alert_subtype
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- action_category
---------------------------------------------
CREATE TRIGGER action_category_ins_trigger
  BEFORE INSERT
  ON action_category
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- action_category_lc_action
---------------------------------------------
CREATE TRIGGER action_category_lc_action_ins_trigger
  BEFORE INSERT
  ON action_category_lc_action
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- update status message
---------------------------------------------
CREATE TRIGGER notification_history_ins_trigger_after
  AFTER INSERT
  ON notification_history
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_update_light_status();
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------
-- When lamp type added, add to the count for that lamp type and remove from the count for type ='Other' (for all groups the light belongs to)
-- When consumption, current or voltage is inserted, insert min and max values
--------------------------------------------------
CREATE TRIGGER ins_light_trigger
  AFTER INSERT
  ON light
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_ins_light();

--------------------------------------------------
-- When lamp type changes, add to the count for that lamp type and remove from the count for old lamp type (for all groups the light belongs to)
-- When consumption, current or voltage is updated, update min and max values
--------------------------------------------------
CREATE TRIGGER upd_light_trigger
  AFTER UPDATE
  ON light --_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_upd_light();
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------
-- When tenant added, add also an 'All' analytics_group for that tenant
--------------------------------------------------
CREATE TRIGGER analytics_grouping_ins_tenant_trigger
	AFTER INSERT
	ON tenant
    FOR EACH ROW EXECUTE PROCEDURE trigger_analytics_grouping_ins_tenant();

--------------------------------------------------
-- When grouping deleted, reset analytics_installed for that group
--------------------------------------------------
CREATE TRIGGER analytics_grouping_del_grouping_trigger
	AFTER DELETE
	ON "grouping"
    FOR EACH ROW EXECUTE PROCEDURE trigger_analytics_grouping_del_grouping();

--------------------------------------------------
-- When grouping added, add also to analytics_group
--------------------------------------------------
CREATE TRIGGER analytics_grouping_ins_grouping_trigger
	AFTER INSERT
	ON "grouping"
    FOR EACH ROW EXECUTE PROCEDURE trigger_analytics_grouping_ins_grouping();

--------------------------------------------------
-- When light added to Group , also ADD to installed_lights for that Group
--------------------------------------------------
CREATE TRIGGER analytics_installed_add_smp_to_group_trigger
	AFTER INSERT
	ON light_grouping
	FOR EACH ROW EXECUTE PROCEDURE trigger_analytics_installed_add_light_to_group();

--------------------------------------------------
-- When light removed from Group , also REMOVE from installed_lights for that Group
--------------------------------------------------
CREATE TRIGGER analytics_installed_del_smp_from_group_trigger
	AFTER DELETE
	ON light_grouping
	FOR EACH ROW EXECUTE PROCEDURE trigger_analytics_installed_del_light_from_group();

--------------------------------------------------
-- When consumption added, update analytics_consumption
--------------------------------------------------
CREATE TRIGGER analytics_consumption_add_operational_data_value_trigger
	BEFORE INSERT
	ON operational_data_value
	FOR EACH ROW EXECUTE PROCEDURE trigger_analytics_consumption_add_operational_data_value();

---------------------------------------------
-- analytics_light_type
---------------------------------------------
CREATE TRIGGER analytics_light_type_ins_trigger
  BEFORE INSERT
  ON analytics_light_type
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- analytics_alarm_warning_subtype
---------------------------------------------
CREATE TRIGGER analytics_alarm_warning_subtype_ins_trigger
  BEFORE INSERT
  ON analytics_alarm_warning_subtype
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- analytics_group
---------------------------------------------
CREATE TRIGGER analytics_group_ins_trigger
  BEFORE INSERT
  ON analytics_group
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- analytics_group_by_date
---------------------------------------------
CREATE TRIGGER analytics_group_by_date_ins_trigger
  BEFORE INSERT
  ON analytics_group_by_date
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- analytics_alarms
---------------------------------------------
CREATE TRIGGER analytics_alarms_ins_trigger
  BEFORE INSERT
  ON analytics_alarms
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- analytics_warnings
---------------------------------------------
CREATE TRIGGER analytics_warnings_ins_trigger
  BEFORE INSERT
  ON analytics_warnings
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- analytics_consumption
---------------------------------------------
CREATE TRIGGER analytics_consumption_ins_trigger
  BEFORE INSERT
  ON analytics_consumption
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- analytics_installed
---------------------------------------------
CREATE TRIGGER analytics_installed_ins_trigger
  BEFORE INSERT
  ON analytics_installed
  FOR EACH ROW EXECUTE PROCEDURE trigger_create_date();

---------------------------------------------
-- light_schedule
---------------------------------------------
  CREATE TRIGGER light_schedule_upd_trigger
  BEFORE UPDATE
  ON light_schedule
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- light_last_operational_data
---------------------------------------------
  CREATE TRIGGER light_last_op_data_upd_trigger
  BEFORE UPDATE
  ON light_last_operational_data
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

  CREATE TRIGGER light_last_op_data_ins_min_max_trigger
  BEFORE INSERT
  ON light_last_operational_data
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_insert_min_max();

  CREATE TRIGGER light_last_op_data_upd_min_max_trigger
  BEFORE UPDATE
  ON light_last_operational_data
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_min_max();

---------------------------------------------
-- light_configuration
---------------------------------------------
  CREATE TRIGGER light_configuration_upd_trigger
  BEFORE UPDATE
  ON light_configuration
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- light_filter_value
---------------------------------------------
  CREATE TRIGGER light_filter_value_ins_trigger
  BEFORE INSERT
  ON light_filter_value
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
  CREATE TRIGGER light_filter_value_upd_trigger
  BEFORE UPDATE
  ON light_filter_value
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

---------------------------------------------
-- ins_api_control
---------------------------------------------
CREATE OR REPLACE FUNCTION trigger_ins_api_control()
  RETURNS trigger AS
$BODY$
BEGIN
   INSERT INTO api_control(tenant_id, count, first_date)
                    VALUES(NEW.tenant_id, 0, now());
   RETURN NEW;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION trigger_ins_api_control()
  OWNER TO qat;

---------------------------------------------
-- ins_api_control
---------------------------------------------

CREATE TRIGGER tenant_ins_api_control
  AFTER INSERT
  ON tenant
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_ins_api_control();