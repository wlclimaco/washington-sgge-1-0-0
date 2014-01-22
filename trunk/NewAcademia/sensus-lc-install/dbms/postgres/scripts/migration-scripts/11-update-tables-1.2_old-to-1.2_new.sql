/* Change light table ***************** */

ALTER TABLE light DROP CONSTRAINT fk_tenant_light;
  
DROP INDEX nx_light_status_message_transation_id;
DROP INDEX nx_light_tenant_id;
DROP INDEX ux_light_smartpoint_id;
  
ALTER TABLE current_alarm_warning_message 	DROP CONSTRAINT fk_light_current_alarm_warrning_message;
ALTER TABLE light_configuration 			DROP CONSTRAINT fk_light_light_configuration;
ALTER TABLE light_daily_consumption 		DROP CONSTRAINT fk_light_light_daily_consumption;
ALTER TABLE light_last_operational_data 	DROP CONSTRAINT fk_light_light_last_op_data;
ALTER TABLE light_location 					DROP CONSTRAINT fk_light_light_location;
ALTER TABLE light_schedule 					DROP CONSTRAINT fk_light_light_schedule;
ALTER TABLE status_message 					DROP CONSTRAINT fk_light_status_message;
ALTER TABLE schedule_membership 			DROP CONSTRAINT fk_schedule_membership_smartpoint;
ALTER TABLE smartpoint_grouping 			DROP CONSTRAINT fk_smartpoint_group_smartpoint;
ALTER TABLE smartpoint_process 				DROP CONSTRAINT fk_smartpoint_smartpoint_process;
ALTER TABLE smartpoint_tag 					DROP CONSTRAINT fk_smartpoint_tag_smartpoint;

ALTER SEQUENCE light_light_id_seq RENAME TO light_old_light_id_seq;

ALTER TABLE light DROP CONSTRAINT light_pkey;
ALTER TABLE light RENAME TO light_old;
  
/* ************************************* */


/* Table: alert_communication ********** */

CREATE TABLE alert_communication
(
  tenant_id integer NOT NULL,
  phase_indicator integer,
  phase_starttime timestamp without time zone,
  phase_endtime timestamp without time zone,
  success_indicator boolean,
  cycle_starttime timestamp without time zone,
  CONSTRAINT alert_comm_pk PRIMARY KEY (tenant_id)
);

/* ************************************* */


/* Table: alert_communication_config *** */

CREATE TABLE alert_communication_config
(
  tenant_id integer NOT NULL,
  phase integer NOT NULL,
  elapsed_time integer NOT NULL,
  CONSTRAINT alert_comm_config_pk PRIMARY KEY (tenant_id, phase)
);

/* ************************************* */


/* Table: alert_type ****************** */

CREATE TABLE alert_type
(
  alert_type_id integer NOT NULL,
  label_key character varying(80) NOT NULL,
  create_date timestamp without time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  CONSTRAINT alert_type_pkey PRIMARY KEY (alert_type_id)
);

/* ************************************* */


/* Table: alert_subtype **************** */

CREATE TABLE alert_subtype
(
  alert_subtype_id integer NOT NULL,
  label_key character varying(80) NOT NULL,
  create_date timestamp without time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  alert_type_id integer NOT NULL,
  CONSTRAINT alert_subtype_pkey PRIMARY KEY (alert_subtype_id),
  CONSTRAINT fk_alert_type_alert_subtype FOREIGN KEY (alert_type_id)
      REFERENCES alert_type (alert_type_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
);

CREATE INDEX nx_alert_subtype_alert_id
  ON alert_subtype
  USING btree
  (alert_type_id);

/* ************************************* */


/*  Light table ************************ */

CREATE TABLE light
(
  light_id serial NOT NULL,
  light_type smallint NOT NULL,
  lifecycle_state smallint NOT NULL,
  light_state smallint NOT NULL,
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  tenant_id integer NOT NULL,
  flexnet_id bigint NOT NULL,
  address character varying(50),
  city character varying(50),
  state character varying(50),
  zip_code character varying(10),
  county character varying(50),
  timezone character varying(30),
  latitude double precision,
  longitude double precision,
  notification_history_id integer,
  pole_id character varying(30),
  protected boolean DEFAULT false,
  intensity integer DEFAULT 0,
  ecomode double precision,
  ecomode_replaced_type integer,
  ecomode_replaced_wattage double precision,
  calculate_retroactive_ecomode boolean DEFAULT false,
  blink_level integer DEFAULT 0,
  override integer DEFAULT 0,
  override_per_date timestamp with time zone,
  override_create_date timestamp with time zone,
  CONSTRAINT light_pkey PRIMARY KEY (light_id),
  CONSTRAINT fk_tenant_light FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX nx_light_tenant_id
  ON light
  USING btree
  (tenant_id);

/* ************************************* */


/* Table: light_grouping *************** */

CREATE TABLE light_grouping
(
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  grouping_id integer NOT NULL,
  light_id integer NOT NULL,
  CONSTRAINT light_grouping_pkey PRIMARY KEY (grouping_id, light_id),
  CONSTRAINT fk_light_group_group FOREIGN KEY (grouping_id)
      REFERENCES grouping (grouping_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT fk_light_group_light FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
);

CREATE INDEX nx_light_grouping_grouping_id
  ON light_grouping
  USING btree
  (grouping_id);

CREATE INDEX nx_light_grouping_light_id
  ON light_grouping
  USING btree
  (light_id);

/* ************************************* */


/* Table: light_process *************** */

CREATE TABLE light_process
(
  process_result integer NOT NULL,
  light_id integer NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  failure_id integer,
  process_id integer NOT NULL,
  CONSTRAINT light_process_pkey PRIMARY KEY (light_id, process_id),
  CONSTRAINT fk_failure_light_process FOREIGN KEY (failure_id)
      REFERENCES failure (failure_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_light_light_process FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_process_light_process FOREIGN KEY (process_id)
      REFERENCES process (process_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
);

/* ************************************* */


/* Table: light_tag ******************** */

CREATE TABLE light_tag
(
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  light_id integer NOT NULL,
  tag_id integer NOT NULL,
  CONSTRAINT light_tag_pkey PRIMARY KEY (light_id , tag_id ),
  CONSTRAINT fk_light_tag_smartpoint FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_light_tag_tag FOREIGN KEY (tag_id)
      REFERENCES tag (tag_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX idxlight_tag_tag_id
  ON light_tag
  USING btree
  (tag_id );

CREATE INDEX nx_light_tag_light_id
  ON light_tag
  USING btree
  (light_id);

/* ************************************* */


/* Table: notification_history ********* */

CREATE SEQUENCE notification_history_status_message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE notification_history
(
  notification_history_id integer NOT NULL DEFAULT nextval('notification_history_status_message_id_seq'::regclass),
  light_id integer NOT NULL,
  lifecycle_state smallint NOT NULL,
  notification_type smallint NOT NULL,
  message_date timestamp without time zone NOT NULL,
  create_date timestamp without time zone NOT NULL,
  update_date timestamp without time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  precedence integer NOT NULL,
  simple_notification boolean,
  notification_transation_id character varying(40) NOT NULL,
  CONSTRAINT notification_history_id PRIMARY KEY (notification_history_id)
);

ALTER SEQUENCE notification_history_status_message_id_seq OWNED BY notification_history.notification_history_id;

ALTER TABLE ONLY notification_history ALTER COLUMN notification_history_id SET DEFAULT nextval('notification_history_status_message_id_seq'::regclass);

/* ************************************* */


/* Table: notification_history_alert *** */

CREATE TABLE notification_history_alert
(
  alert_subtype_id integer NOT NULL,
  notification_history_id integer NOT NULL,
  create_date timestamp without time zone NOT NULL,
  update_date timestamp without time zone NOT NULL,
  message_date timestamp without time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  modified_date timestamp without time zone,
  CONSTRAINT notification_history_pk PRIMARY KEY (alert_subtype_id, notification_history_id),
  CONSTRAINT alert_subtype_notification_history_alert_fk FOREIGN KEY (alert_subtype_id)
      REFERENCES alert_subtype (alert_subtype_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT notification_history_notification_history_alert_fk FOREIGN KEY (notification_history_id)
      REFERENCES notification_history (notification_history_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

/* ************************************* */


/* Table: schedule_membership ********** */

ALTER TABLE schedule_membership RENAME smartpoint_id TO light_id;

ALTER TABLE schedule_membership
 DROP CONSTRAINT schedule_membership_pkey;

UPDATE schedule_membership AS sm
   SET light_id = lgt_old.light_id
  FROM light_old lgt_old,
       smartpoint smp
 WHERE smp.smartpoint_id = sm.light_id
   AND lgt_old.smartpoint_id = smp.smartpoint_id;

ALTER TABLE schedule_membership
  ADD CONSTRAINT schedule_membership_pkey PRIMARY KEY (schedule_id, light_id);

/* ************************************* */


/* Table: tenant *********************** */

ALTER TABLE tenant ADD COLUMN communication_cycle_timeout INTEGER NULL;
ALTER TABLE tenant ADD COLUMN number_api_access_hour INTEGER NULL;

/* ************************************* */


/* Table: api_control ****************** */

CREATE TABLE api_control (
                tenant_id INTEGER NOT NULL,
                count INTEGER NOT NULL,
                first_date TIMESTAMP,
                CONSTRAINT tenant_id PRIMARY KEY (tenant_id)
);

ALTER TABLE api_control  ADD CONSTRAINT tenant_api_control_request_fk
    FOREIGN KEY (tenant_id)
     REFERENCES tenant (tenant_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

/* ************************************* */


/* Table: analytics_alarms_summarized ** */

CREATE TABLE analytics_alarms_summarized
(
  date_time timestamp without time zone NOT NULL,  
  lamp_failure integer NULL,
  power_failure integer NULL,
  board_failure integer NULL,
  metrology_error integer NULL,
  metrology_com_failure integer NULL, 
  group_id integer NOT NULL,
  range_date integer NOT NULL, 
  tenant_id integer NOT NULL  
);

/* ************************************* */


/* Table: analytics_warnings_summarized  */

CREATE TABLE analytics_warnings_summarized
(
  date_time timestamp without time zone NOT NULL,  
  power_surge_detected integer NULL,
  brownout_detected integer NULL,
  communication_fail integer NULL,
  high_current integer NULL,
  low_current integer NULL,
  reverse_energy integer NULL,
  metrology_reset integer NULL,  
  group_id integer NOT NULL,
  range_date integer NOT NULL, 
  tenant_id integer NOT NULL  
);

/* ************************************* */


/* Table: analytics_installed_summarized */

CREATE TABLE analytics_installed_summarized
(
  date_time timestamp without time zone NOT NULL,  
  induction integer NULL,
  led integer NULL,
  other integer NULL,  
  group_id integer NOT NULL,
  range_date integer NOT NULL, 
  tenant_id integer NOT NULL  
);

/* ************************************* */


/* Add new indexes ********************* */

CREATE INDEX idx_alarm_group_id
  ON analytics_alarms_summarized
  USING btree
  (group_id);

CREATE INDEX idx_alarm_range_date
  ON analytics_alarms_summarized
  USING btree
  (range_date);

 CREATE INDEX idx_warnings_group_id
  ON analytics_warnings_summarized
  USING btree
  (group_id);

CREATE INDEX idx_warnings_range_date
  ON analytics_warnings_summarized
  USING btree
  (range_date);
  
CREATE INDEX idx_installed_group_id
  ON analytics_installed_summarized
  USING btree
  (group_id);

CREATE INDEX idx_installed_range_date
  ON analytics_installed_summarized
  USING btree
  (range_date);
  
/* ************************************* */


/* Table: dashboard_resume_chart ******* */

ALTER TABLE dashboard_resume_chart RENAME status_subtype_id  TO alert_subtype_id;

/* ************************************* */


/* Table: operational_data_value ******* */

ALTER TABLE operational_data_value DROP CONSTRAINT fk_status_message_operational_data_value;
ALTER TABLE operational_data_value RENAME status_message_id  TO notification_history_id;

/* ************************************* */


/* Table: process ********************** */

ALTER TABLE process ADD COLUMN parameter_value character varying(4094);
ALTER TABLE process ALTER COLUMN lc_action_description type character varying(4094); 

/* ************************************* */