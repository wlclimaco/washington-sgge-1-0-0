/* After light data migration ********** */

ALTER TABLE schedule_membership
  ADD CONSTRAINT fk_schedule_membership_light FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT;

ALTER TABLE light_configuration
  ADD CONSTRAINT fk_light_light_configuration FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE light_daily_consumption
  ADD CONSTRAINT fk_light_light_daily_consumption FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE light_last_operational_data
  ADD CONSTRAINT fk_light_light_last_op_data FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE light_schedule
  ADD CONSTRAINT fk_light_light_schedule FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE operational_data_value
  ADD CONSTRAINT fk_status_message_operational_data_value FOREIGN KEY (notification_history_id)
      REFERENCES notification_history (notification_history_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE light
  ADD CONSTRAINT fk_notification_history_light FOREIGN KEY (notification_history_id)
      REFERENCES notification_history (notification_history_id)
	  ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE current_alarm_warning_message DROP CONSTRAINT fk_status_current_alarm_warning_message;
ALTER TABLE current_alarm_warning_message DROP CONSTRAINT fk_status_message_current_alarm_warning_message;
ALTER TABLE current_alarm_warning_message DROP CONSTRAINT fk_status_subtype_current_alarm_warning_message;
ALTER TABLE current_alarm_warning_message DROP CONSTRAINT fk_tenant_current_alarm_warning_message;
ALTER TABLE status_message DROP CONSTRAINT fk_status_status_message;
ALTER TABLE status_message DROP CONSTRAINT fk_tenant_status_message;
ALTER TABLE status_subtype DROP CONSTRAINT fk_status_status_subtype;
ALTER TABLE status_message_status_subtype DROP CONSTRAINT fk_status_message_status_message_status_subtype;
ALTER TABLE status_message_status_subtype DROP CONSTRAINT fk_status_subtype_status_message_status_subtype;
ALTER TABLE smartpoint DROP CONSTRAINT fk_tenant_smartpoint;

DROP TABLE status;
DROP TABLE status_subtype;
DROP TABLE status_message_status_subtype;
DROP TABLE status_message;
DROP TABLE current_alarm_warning_message;
DROP TABLE light_location;
DROP TABLE smartpoint_grouping;
DROP TABLE smartpoint_process;
DROP TABLE smartpoint_tag;
DROP TABLE smartpoint;
DROP TABLE light_old;

ALTER SEQUENCE light_light_id_seq RENAME TO light_id_seq;

DROP FUNCTION calculate_retroactive_light_daily_consumption();
DROP TRIGGER light_last_op_data_upd_trigger ON light_last_operational_data;

DROP TRIGGER light_last_op_data_ins_min_max_trigger ON light_last_operational_data;
DROP TRIGGER light_last_op_data_upd_min_max_trigger ON light_last_operational_data;

DROP FUNCTION trigger_insert_min_max();
DROP FUNCTION trigger_modified_min_max();