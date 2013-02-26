-- +---------------------------------------------------------
-- | MODEL       : LC-PostGres
-- | AUTHOR      : 
-- | GENERATED BY: Open System Architect
-- +---------------------------------------------------------
-- | WARNING     : Review before execution
-- +---------------------------------------------------------

-- +---------------------------------------------------------
-- | CREATE
-- +---------------------------------------------------------
CREATE TABLE day_of_week
(
  day_of_week_id integer NOT NULL,
  name character varying(10) NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  PRIMARY KEY  (day_of_week_id)
);
CREATE TABLE lc_action
(
  lc_action_id integer NOT NULL,
  description character varying(200) NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  PRIMARY KEY (lc_action_id)
);
CREATE TABLE failure
(
  failure_id serial NOT NULL,
  label_key character varying(80) NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  lc_action_id integer,
  PRIMARY KEY (failure_id)
);
CREATE INDEX nx_failure_lc_action_lc_action_id ON failure
(
  lc_action_id
);

CREATE TABLE tenant
(
  tenant_id serial NOT NULL,
  name character varying(20) NOT NULL,
  description character varying(80),
  rni_code character varying(5) NOT NULL,
  server_name character varying(150) NOT NULL,
  gateway_rni_location character varying(150),
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone,
  latitude float8,
  longitude float8,
  min_smartpoint_comm_time integer NOT NULL,
  light_time_zone character varying(80),
  ecomode_disable boolean DEFAULT false,
  batch_process_time integer NOT NULL,
  PRIMARY KEY (tenant_id)
);
CREATE UNIQUE INDEX ux_tenant_rni_code ON tenant
(
  rni_code
);

CREATE UNIQUE INDEX ux_tenant_server_name ON tenant
(
  server_name
);

CREATE TABLE grouping
(
  grouping_id serial NOT NULL,
  name character varying(100) NOT NULL,
  description character varying(150),
  create_date timestamp with time zone DEFAULT (NULL) NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  tenant_id integer NOT NULL,
  latitude float8,
  longitude float8,
  PRIMARY KEY (grouping_id)
);
CREATE UNIQUE INDEX ux_grouping_tenant_id_name ON grouping
(
  tenant_id,
  upper(name::text) COLLATE pg_catalog."default"
);

CREATE INDEX nx_grouping_tenant_id ON grouping
(
  tenant_id
);

CREATE TABLE light
(
  light_id serial NOT NULL,
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  light_status smallint NOT NULL,
  smartpoint_id integer NOT NULL,
  tenant_id integer NOT NULL,
  protected boolean DEFAULT false,
  intensity integer DEFAULT 0,
  ecomode double precision,
  ecomode_replaced_type INTEGER,
  ecomode_replaced_wattage double precision,
  calculate_retroactive_ecomode boolean DEFAULT false,
  blink_level integer DEFAULT 0,
  override integer DEFAULT 0,
  override_per_date  timestamp with time zone,
  override_create_date  timestamp with time zone,
  current_status_message_id integer,
  PRIMARY KEY (light_id)
);
CREATE UNIQUE INDEX ux_light_smartpoint_id ON light
(
  smartpoint_id
);

CREATE INDEX nx_light_tenant_id ON light
(
  tenant_id
);

CREATE TABLE light_daily_consumption 
(
	light_id INTEGER NOT NULL,
	consumption_day DATE NOT NULL,
	consumption REAL NOT NULL,
	ecomode_baseline real,
    ecomode double precision,
	CONSTRAINT light_daily_consumption_pk PRIMARY KEY (light_id, consumption_day)
);

CREATE TABLE property_class
(
  property_class_id integer NOT NULL,
  property_class_name character varying(20) NOT NULL,
  property_class_description character varying(100),
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  PRIMARY KEY (property_class_id)
);
CREATE TABLE property
(
  property_id serial NOT NULL,
  property_name character varying(50) NOT NULL,
  label_key character varying(80) NOT NULL,
  data_type integer NOT NULL,
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  property_class smallint NOT NULL,
  has_valid_values character(1) NOT NULL,
  tenant_id integer,
  PRIMARY KEY (property_id)
);
CREATE INDEX nx_property_tenant_id ON property
(
  tenant_id
);

CREATE TABLE property_valid_value
(
  property_valid_value_id serial NOT NULL,
  valid_value character varying(4094) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  property_id integer NOT NULL,
  PRIMARY KEY (property_valid_value_id)
);
CREATE INDEX nx_property_valid_value_property_id ON property_valid_value
(
  property_id
);

CREATE TABLE light_property
(
  property_value character varying(4094),
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  modify_user character varying(20),
  create_user character varying(20) NOT NULL,
  light_id integer NOT NULL,
  property_id integer NOT NULL,
  property_valid_value_id integer,
  PRIMARY KEY (light_id,property_id)
);
CREATE INDEX nx_light_property_light_id ON light_property
(
  light_id
);

CREATE INDEX nx_light_property_property_id ON light_property
(
  property_id
);

CREATE INDEX nx_light_property_valid_value_id ON light_property
(
  property_valid_value_id
);

CREATE TABLE process
(
  description character varying(1000) NOT NULL,
  start_datetime timestamp with time zone NOT NULL,
  end_datetime timestamp with time zone,
  rni_correlation_id character varying(40),
  parent_process_id integer,
  is_monitored_instance boolean NOT NULL,
  estimated_seconds_to_complete bigint,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  is_process_complete boolean NOT NULL,
  is_first_level boolean NOT NULL,
  modify_user character varying(20),
  modified_date timestamp with time zone,
  lc_action_description character varying(40) NOT NULL,
  lc_action_id integer NOT NULL,
  tenant_id integer NOT NULL,
  process_id serial NOT NULL,
  is_submitted boolean,
  PRIMARY KEY (process_id)
);
CREATE UNIQUE INDEX ux_process_parent_process_id ON process
(
  parent_process_id
);

CREATE INDEX nx_process_action_id ON process
(
  lc_action_id
);

CREATE INDEX nx_process_tenant_id ON process
(
  tenant_id
);

CREATE TABLE operational_data_type
(
  operational_data_type_id integer NOT NULL,
  label_key character varying(80) NOT NULL,
  unit_of_measurement character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  PRIMARY KEY (operational_data_type_id)
);
CREATE TABLE status
(
  status_id integer NOT NULL,
  label_key character varying(80) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  PRIMARY KEY (status_id)
);
CREATE TABLE status_message
(
  status_message_id serial NOT NULL,
  message_date timestamp with time zone NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  message_type integer NOT NULL,
  light_id integer NOT NULL,
  status_id integer NOT NULL,
  tenant_id integer NOT NULL,
  simple_notification boolean,
  PRIMARY KEY (status_message_id)
);
CREATE INDEX nx_status_message_light_id ON status_message
(
  light_id
);

CREATE INDEX nx_status_message_status_id ON status_message
(
  status_id
);

CREATE INDEX nx_status_message_tenant_id ON status_message
(
  tenant_id
);

CREATE INDEX nx_status_message_message_date ON status_message
(
  message_date
);

CREATE TABLE operational_data_value
(
  value double precision NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  operational_data_type_id integer NOT NULL,
  status_message_id integer NOT NULL,
  PRIMARY KEY (operational_data_type_id,status_message_id,create_date)
);
CREATE INDEX nx_operational_data_value_operational_data_type_id ON operational_data_value
(
  operational_data_type_id
);

CREATE INDEX nx_operational_data_value_status_message_id ON operational_data_value
(
  status_message_id
);

CREATE TABLE schedule
(
  schedule_id serial NOT NULL,
  name character varying(100) NOT NULL,
  description character varying(150),
  sunrise_offset integer,
  sunset_offset integer,
  use_sunrise_sunset_tables boolean NOT NULL,
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  schedule_type smallint NOT NULL,
  tenant_id integer,
  intensity integer,
  latitude float8,
  longitude float8,
  PRIMARY KEY (schedule_id)
);
CREATE INDEX nx_schedule_tenant_id ON schedule
(
  tenant_id
);

CREATE TABLE schedule_event
(
  schedule_event_id serial NOT NULL,
  event_time timestamp with time zone NOT NULL,
  intensity integer NOT NULL,
  blink_level integer DEFAULT 0,
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20),
  modify_user character varying(20),
  schedule_id integer NOT NULL,
  tenant_id integer NOT NULL,
  PRIMARY KEY (schedule_event_id)
);
CREATE INDEX nx_schedule_event_schedule_id ON schedule_event
(
  schedule_id
);

CREATE INDEX nx_schedule_event_tenant_id ON schedule_event
(
  tenant_id
);

CREATE TABLE schedule_event_day_of_week
(
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20),
  modify_user character varying(20),
  day_of_week_id integer NOT NULL,
  schedule_event_id integer NOT NULL
);
CREATE INDEX nx_schedule_event_day_of_week_day_of_week_id ON schedule_event_day_of_week
(
  day_of_week_id
);

CREATE INDEX nx_schedule_event_day_of_week_schedule_event_id ON schedule_event_day_of_week
(
  schedule_event_id
);

CREATE TABLE tag
(
  tag_id serial NOT NULL,
  name character varying(100),
  create_date timestamp with time zone,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  tenant_id integer,
  auto_group boolean DEFAULT false,
  address_related boolean DEFAULT false,
  PRIMARY KEY (tag_id)
);
CREATE INDEX nx_tag_tenant_id ON tag
(
  tenant_id
);

CREATE UNIQUE INDEX ux_tag_tenant_id_name
  ON tag
  USING btree
  (tenant_id , upper(name::text) COLLATE pg_catalog."default" );

CREATE TABLE smartpoint
(
  smartpoint_id serial NOT NULL,
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  rni_id integer NOT NULL,
  smartpoint_type integer NOT NULL,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  tenant_id integer NOT NULL,
  PRIMARY KEY (smartpoint_id)
);
CREATE INDEX nx_smartpoint_tenant_id ON smartpoint
(
  tenant_id
);

CREATE UNIQUE INDEX ux_smartpoint_rni_id ON smartpoint
(
  rni_id
);

CREATE TABLE schedule_membership
(
  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),
  schedule_id integer NOT NULL,
  smartpoint_id integer NOT NULL,
  PRIMARY KEY (schedule_id,smartpoint_id)
);
CREATE INDEX nx_schedule_membership_schedule_id ON schedule_membership
(
  schedule_id
);

CREATE INDEX nx_schedule_membership_smartpoint_id ON schedule_membership
(
  smartpoint_id
);

CREATE TABLE smartpoint_grouping
(
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  grouping_id integer NOT NULL,
  smartpoint_id integer NOT NULL,
  PRIMARY KEY (grouping_id,smartpoint_id)
);
CREATE INDEX nx_smartpoint_grouping_grouping_id ON smartpoint_grouping
(
  grouping_id
);

CREATE INDEX nx_smartpoint_grouping_smartpoint_id ON smartpoint_grouping
(
  smartpoint_id
);

CREATE TABLE smartpoint_process
(
  process_result integer NOT NULL,
  smartpoint_id integer NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  failure_id integer,
  process_id integer NOT NULL,
  PRIMARY KEY (smartpoint_id,process_id)
);
CREATE INDEX nx_smartpoint_process_failure_id ON smartpoint_process
(
  failure_id
);

CREATE INDEX nx_smartpoint_process_process_id ON smartpoint_process
(
  process_id
);

CREATE INDEX nx_smartpoint_process_smartpoint_id ON smartpoint_process
(
  smartpoint_id
);

CREATE TABLE smartpoint_tag
(
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  smartpoint_id integer NOT NULL,
  tag_id integer NOT NULL,
  PRIMARY KEY (smartpoint_id,tag_id)
);
CREATE INDEX nx_smartpoint_tag_smartpoint_id ON smartpoint_tag
(
  smartpoint_id
);

CREATE INDEX idxsmartpoint_tag_tag_id ON smartpoint_tag
(
  tag_id
);

CREATE TABLE status_subtype
(
  status_subtype_id integer NOT NULL,
  label_key character varying(80) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  status_id integer NOT NULL,
  PRIMARY KEY (status_subtype_id)
);
CREATE INDEX nx_status_subtype_status_id ON status_subtype
(
  status_id
);

CREATE TABLE status_message_status_subtype
(
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  status_message_id integer NOT NULL,
  status_subtype_id integer NOT NULL,
  modified_user character varying(20),
  modified_date timestamp with time zone,
  PRIMARY KEY (status_message_id,status_subtype_id)
);
CREATE INDEX nx_status_message_status_subtype_status_message_id ON status_message_status_subtype
(
  status_message_id
);

CREATE INDEX nx_status_message_status_subtype_status_subtype_id ON status_message_status_subtype
(
  status_subtype_id
);

CREATE TABLE action_category
(
  action_category_id serial NOT NULL,
  label_key character varying(200),
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  PRIMARY KEY (action_category_id)
);
CREATE TABLE action_category_lc_action
(
  action_category_id integer NOT NULL,
  lc_action_id integer NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  PRIMARY KEY (action_category_id,lc_action_id)
);
CREATE TABLE process_property
(
  process_id integer NOT NULL,
  property_id integer NOT NULL,
  value character varying(200),
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20),
  PRIMARY KEY (process_id,property_id)
);
CREATE TABLE users
(
  user_id serial NOT NULL,
  username character varying(50) NOT NULL,
  tenant_id integer NULL,
  password character varying(250) NOT NULL,
  first_name character varying(50),
  last_name character varying(50),
  email character varying(50),
  enabled boolean NOT NULL,
  all_lights_auth boolean DEFAULT false NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(50) NOT NULL,
  modified_date timestamp with time zone,
  modified_user character varying(50),
  
  PRIMARY KEY (user_id)
);
ALTER TABLE users ADD CONSTRAINT ux_users_unique_tenant_user_id UNIQUE (username,tenant_id);

CREATE UNIQUE INDEX ux_users_tenant_id_name
  ON users
  USING btree
  (tenant_id , upper(username::text) COLLATE pg_catalog."default" );

CREATE TABLE user_settings
(
  user_settings_id serial NOT NULL,
  user_id integer,
  tenant_id integer NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  PRIMARY KEY (user_settings_id)
);
CREATE INDEX nx_user_settings_user_id ON user_settings
(
  user_id
);

CREATE INDEX nx_user_settings_tenant_id ON user_settings
(
  tenant_id
);

CREATE UNIQUE INDEX ux_tenant_id_user_id_key ON user_settings
(
  tenant_id,
  user_id
);

CREATE TABLE user_settings_property
(
  user_settings_property serial NOT NULL,
  user_settings_id integer NOT NULL,
  property_id integer NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  modified_date timestamp with time zone,
  modify_user character varying(20),
  user_setting_property_value character varying(4094),
  display_order integer NULL,
  PRIMARY KEY (user_settings_property)
);
CREATE TABLE custom_search
(
  custom_search_id serial NOT NULL,
  custom_search_name character varying(40) NOT NULL,
  custom_search_description character varying(200),
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  modified_date timestamp with time zone,
  modified_user character varying(20),
  user_id integer NOT NULL,
  tenant_id integer,
  PRIMARY KEY (custom_search_id)
);
CREATE INDEX nx_custom_search_user_id ON custom_search
(
  user_id
);

CREATE INDEX nx_custom_search_tenant_id ON custom_search
(
  tenant_id
);
CREATE UNIQUE INDEX ux_custom_search_name ON custom_search
(
  custom_search_name
);

CREATE TABLE operator
(
  operator_id integer NOT NULL,
  operator_code character varying(20) NOT NULL,
  operator_description character varying(100),
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  PRIMARY KEY (operator_id)
);
CREATE TABLE custom_search_property
(
  custom_search_property_id serial NOT NULL,
  custom_search_id integer NOT NULL,
  property_id integer NOT NULL,
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  modified_date timestamp with time zone,
  modified_user character varying(20),
  custom_search_property_value character varying(4094),
  operator_id integer,
  display_order integer NULL,
  PRIMARY KEY (custom_search_property_id)
);
CREATE INDEX nx_custom_search_property_operator_id ON custom_search_property
(
  operator_id
);

CREATE TABLE analytics_group
(
  analytic_group_id serial NOT NULL,
  analytic_group_name character varying(100) NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  tenant_id integer,
  grouping_id integer,
  PRIMARY KEY (analytic_group_id)
);
CREATE UNIQUE INDEX ux_analytics_group_analytic_group_name ON analytics_group
(
  analytic_group_name,
  tenant_id
);

CREATE TABLE analytics_group_by_date
(
  analytic_group_by_date_id serial NOT NULL,
  analytic_group_id integer NOT NULL,
  analytic_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (analytic_group_by_date_id)
);
CREATE INDEX nx_analytics_group_by_date_analytics_group ON analytics_group_by_date
(
  analytic_group_id
);

CREATE TABLE analytics_light_type
(
  light_type_id integer NOT NULL,
  light_type_name character varying(10) NOT NULL,
  label_key character varying(80) NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (light_type_id)
);
CREATE TABLE analytics_consumption
(
  id serial NOT NULL,
  light_type_id integer NOT NULL,
  value real NOT NULL,
  analytic_group_by_date_id integer NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);
CREATE INDEX nx_analytics_consumption_light_type ON analytics_consumption
(
  light_type_id
);

CREATE INDEX nx_analytics_consumption_analytic_group_by_date ON analytics_consumption
(
  analytic_group_by_date_id
);

CREATE TABLE analytics_installed
(
  id serial NOT NULL,
  light_type_id integer NOT NULL,
  value integer NOT NULL,
  analytic_group_by_date_id integer NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);
CREATE INDEX nx_analytics_installed_analytic_group_by_date ON analytics_installed
(
  analytic_group_by_date_id
);

CREATE INDEX nx_analytics_installed_light_type ON analytics_installed
(
  light_type_id
);

CREATE TABLE analytics_alarm_warning_subtype
(
  analytics_alarm_warning_subtype_id integer NOT NULL,
  label_key character varying(80) NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (analytics_alarm_warning_subtype_id)
);
CREATE TABLE analytics_alarms
(
  id serial NOT NULL,
  light_type_id integer NOT NULL,
  value integer NOT NULL,
  analytic_group_by_date_id integer NOT NULL,
  analytics_alarm_subtype integer NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);
CREATE INDEX nx_analytics_alarms_light_type ON analytics_alarms
(
  light_type_id
);

CREATE INDEX nx_analytics_alarms_analytic_group_by_date ON analytics_alarms
(
  analytic_group_by_date_id
);

CREATE INDEX nx_analytics_alarms_analytics_alarm_warning_subtype ON analytics_alarms
(
  analytics_alarm_subtype
);

CREATE TABLE analytics_warnings
(
  id serial NOT NULL,
  light_type_id integer NOT NULL,
  value integer NOT NULL,
  analytic_group_by_date_id integer NOT NULL,
  analytics_warning_subtype integer NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);
CREATE INDEX nx_analytics_warnings_analytic_group_by_date ON analytics_warnings
(
  analytic_group_by_date_id
);

CREATE INDEX nx_analytics_warnings_light_type ON analytics_warnings
(
  light_type_id
);

CREATE INDEX nx_analytics_warnings_analytics_alarm_warning_subtype ON analytics_warnings
(
  analytics_warning_subtype
);

CREATE TABLE sensus_part_number
(
  sensus_part_number_id serial NOT NULL,
  sensus_part_number_value character varying(17) NOT NULL,
  format_mask character varying,
  dim_on_delay integer,
  PRIMARY KEY (sensus_part_number_id)
);
CREATE TABLE sensus_part_number_configuration
(
  sensus_part_number_configuration_id serial NOT NULL,
  intensity_level integer NOT NULL,
  hardware_setting integer NOT NULL,
  current_scale integer NOT NULL,
  full_on_required boolean NOT NULL,
  percentage integer NOT NULL,
  sensus_part_number_id integer NOT NULL,
  PRIMARY KEY (sensus_part_number_configuration_id)
);
CREATE TABLE users_grouping
(
  user_id integer NOT NULL,
  grouping_id integer NOT NULL,
  create_user character varying(50),
  create_date timestamp with time zone,
  PRIMARY KEY (user_id,grouping_id)
);
CREATE TABLE dashboard_resume
(
  tenant_id INTEGER NOT NULL,
  analytics_type CHARACTER VARYING(1) NOT NULL,
  grouping_id INTEGER,
  view_mode CHARACTER VARYING(20) NOT NULL,
  value REAL NOT NULL,
  average REAL NOT NULL,
  change REAL NOT NULL,
  trends CHARACTER VARYING(500) NOT NULL,
  create_date TIMESTAMP WITH TIME ZONE NOT NULL,
  create_user CHARACTER VARYING(20) NOT NULL
  --CONSTRAINT dashboard_resume_pkey PRIMARY KEY (tenant_id, analytics_type, view_mode)
);
CREATE TABLE dashboard_resume_chart
(
  tenant_id integer NOT NULL,
  grouping_id integer,
  status_subtype_id integer NOT NULL,
  amount integer NOT NULL    
);
CREATE TABLE authorities
(
  user_id integer NOT NULL,
  authority character varying(50) NOT NULL
);
CREATE INDEX ix_auth_username ON authorities
(
  user_id,
  authority
);

CREATE TABLE current_alarm_warning_message
(
  light_id integer NOT NULL,
  status_message_id integer NOT NULL,
  status_message_type integer NOT NULL,
  status_messages_status_id integer,
  status_messages_status_subtype_id integer,
  tenant_id integer NOT NULL,
  message_date timestamp with time zone NOT NULL
);

CREATE TABLE analytics_ecomode
(
  id serial NOT NULL,
  analytic_group_by_date_id integer NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  ecomode_percent double precision not null,
  ecomode_measured double precision not null,
  ecomode_baseline double precision not null,
  CONSTRAINT analytics_ecomode_pkey PRIMARY KEY (id),
  CONSTRAINT fk_analytics_ecomode_analytics_group_by_date FOREIGN KEY (analytic_group_by_date_id)
      REFERENCES analytics_group_by_date (analytic_group_by_date_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);
CREATE TABLE analytics_eco_mode_type
(
  eco_mode_id integer NOT NULL,
  eco_mode_type_name character varying(25) NOT NULL,
  label_key character varying(80) NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  PRIMARY KEY (eco_mode_id)
);

ALTER DATABASE LC SET timezone to 'UTC';	 
ALTER TABLE failure ADD CONSTRAINT fk_lc_action_failure
  FOREIGN KEY ( lc_action_id )
   REFERENCES lc_action ( lc_action_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE grouping ADD CONSTRAINT fk_tenant_grouping
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE light ADD CONSTRAINT fk_tenant_light
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE light_property ADD CONSTRAINT fk_light_property_value
  FOREIGN KEY ( light_id )
   REFERENCES light ( light_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE light_property ADD CONSTRAINT fk_property_property_value
  FOREIGN KEY ( property_id )
   REFERENCES property ( property_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE light_property ADD CONSTRAINT fk_property_valid_value_light_property
  FOREIGN KEY ( property_valid_value_id )
   REFERENCES property_valid_value ( property_valid_value_id )
    ON DELETE SET NULL NOT DEFERRABLE;
ALTER TABLE process ADD CONSTRAINT fk_lc_action_process
  FOREIGN KEY ( lc_action_id )
   REFERENCES lc_action ( lc_action_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE process ADD CONSTRAINT fk_tenant_process
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE operational_data_value ADD CONSTRAINT fk_operational_data_type_operational_data_value
  FOREIGN KEY ( operational_data_type_id )
   REFERENCES operational_data_type ( operational_data_type_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE operational_data_value ADD CONSTRAINT fk_status_message_operational_data_value
  FOREIGN KEY ( status_message_id )
   REFERENCES status_message ( status_message_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE property ADD CONSTRAINT fk_tenant_property
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE property_valid_value ADD CONSTRAINT fk_property_property_valid_value
  FOREIGN KEY ( property_id )
   REFERENCES property ( property_id )
    ON DELETE SET NULL NOT DEFERRABLE;
ALTER TABLE schedule ADD CONSTRAINT fk_tenant_schedule
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE schedule_event ADD CONSTRAINT fk_schedule_event_schedule
  FOREIGN KEY ( schedule_id )
   REFERENCES schedule ( schedule_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE schedule_event ADD CONSTRAINT fk_tenant_schedule_event
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE schedule_event_day_of_week ADD CONSTRAINT fk_schedule_event_day_of_week_day_of_week
  FOREIGN KEY ( day_of_week_id )
   REFERENCES day_of_week ( day_of_week_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE schedule_event_day_of_week ADD CONSTRAINT fk_schedule_event_day_of_week_schedule_event
  FOREIGN KEY ( schedule_event_id )
   REFERENCES schedule_event ( schedule_event_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE schedule_membership ADD CONSTRAINT fk_schedule_membership_schedule
  FOREIGN KEY ( schedule_id )
   REFERENCES schedule ( schedule_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE schedule_membership ADD CONSTRAINT fk_schedule_membership_smartpoint
  FOREIGN KEY ( smartpoint_id )
   REFERENCES smartpoint ( smartpoint_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE smartpoint ADD CONSTRAINT fk_tenant_smartpoint
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE smartpoint_grouping ADD CONSTRAINT fk_smartpoint_group_group
  FOREIGN KEY ( grouping_id )
   REFERENCES grouping ( grouping_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE smartpoint_grouping ADD CONSTRAINT fk_smartpoint_group_smartpoint
  FOREIGN KEY ( smartpoint_id )
   REFERENCES smartpoint ( smartpoint_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE smartpoint_process ADD CONSTRAINT fk_failure_smartpoint_process
  FOREIGN KEY ( failure_id )
   REFERENCES failure ( failure_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE smartpoint_process ADD CONSTRAINT fk_process_smartpoint_process
  FOREIGN KEY ( process_id )
   REFERENCES process ( process_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE smartpoint_process ADD CONSTRAINT fk_smartpoint_smartpoint_process
  FOREIGN KEY ( smartpoint_id )
   REFERENCES smartpoint ( smartpoint_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE smartpoint_tag ADD CONSTRAINT fk_smartpoint_tag_smartpoint
  FOREIGN KEY ( smartpoint_id )
   REFERENCES smartpoint ( smartpoint_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE smartpoint_tag ADD CONSTRAINT fk_smartpoint_tag_tag
  FOREIGN KEY ( tag_id )
   REFERENCES tag ( tag_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE status_message ADD CONSTRAINT fk_light_status_message
  FOREIGN KEY ( light_id )
   REFERENCES light ( light_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE status_message ADD CONSTRAINT fk_status_status_message
  FOREIGN KEY ( status_id )
   REFERENCES status ( status_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE status_message ADD CONSTRAINT fk_tenant_status_message
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE status_message_status_subtype ADD CONSTRAINT fk_status_message_status_message_status_subtype
  FOREIGN KEY ( status_message_id )
   REFERENCES status_message ( status_message_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE status_message_status_subtype ADD CONSTRAINT fk_status_subtype_status_message_status_subtype
  FOREIGN KEY ( status_subtype_id )
   REFERENCES status_subtype ( status_subtype_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE status_subtype ADD CONSTRAINT fk_status_status_subtype
  FOREIGN KEY ( status_id )
   REFERENCES status ( status_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE tag ADD CONSTRAINT fk_tenant_tag
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE process ADD CONSTRAINT fk_process_process
  FOREIGN KEY ( parent_process_id )
   REFERENCES process ( process_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE action_category_lc_action ADD CONSTRAINT fk_action_category_lc_action_lc_action_id
  FOREIGN KEY ( lc_action_id )
   REFERENCES lc_action ( lc_action_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE action_category_lc_action ADD CONSTRAINT fk_action_category_process_action_category
  FOREIGN KEY ( action_category_id )
   REFERENCES action_category ( action_category_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE process_property ADD CONSTRAINT fk_process_property_process
  FOREIGN KEY ( process_id )
   REFERENCES process ( process_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE process_property ADD CONSTRAINT fk_process_property_property
  FOREIGN KEY ( property_id )
   REFERENCES property ( property_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE user_settings ADD CONSTRAINT fk_user_settings_users
  FOREIGN KEY ( user_id )
   REFERENCES users ( user_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE user_settings ADD CONSTRAINT fk_user_settings_tenant_id
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE user_settings_property ADD CONSTRAINT fk_user_settings_property_user_settings_id
  FOREIGN KEY ( user_settings_id )
   REFERENCES user_settings ( user_settings_id )
    NOT DEFERRABLE;
ALTER TABLE user_settings_property ADD CONSTRAINT fk_user_settings_property_property_id
  FOREIGN KEY ( property_id )
   REFERENCES property ( property_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE property ADD CONSTRAINT fk_property_class_property
  FOREIGN KEY ( property_class )
   REFERENCES property_class ( property_class_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE custom_search_property ADD CONSTRAINT fk_custom_search_property_custom_search_id
  FOREIGN KEY ( custom_search_id )
   REFERENCES custom_search ( custom_search_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE custom_search_property ADD CONSTRAINT fk_custom_search_property_property_id
  FOREIGN KEY ( property_id )
   REFERENCES property ( property_id )
    NOT DEFERRABLE;
ALTER TABLE custom_search_property ADD CONSTRAINT fk_operator_custom_search_property
  FOREIGN KEY ( operator_id )
   REFERENCES operator ( operator_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE analytics_consumption ADD CONSTRAINT fk_analytics_consumption_analytics_group_by_date
  FOREIGN KEY ( analytic_group_by_date_id )
   REFERENCES analytics_group_by_date ( analytic_group_by_date_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE analytics_installed ADD CONSTRAINT fk_analytics_installed_analytics_group_by_date
  FOREIGN KEY ( analytic_group_by_date_id )
   REFERENCES analytics_group_by_date ( analytic_group_by_date_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE analytics_alarms ADD CONSTRAINT fk_analytics_alarms_analytics_group_by_date
  FOREIGN KEY ( analytic_group_by_date_id )
   REFERENCES analytics_group_by_date ( analytic_group_by_date_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE analytics_warnings ADD CONSTRAINT fk_analytics_consumption_analytics_group_by_date
  FOREIGN KEY ( analytic_group_by_date_id )
   REFERENCES analytics_group_by_date ( analytic_group_by_date_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE analytics_alarms ADD CONSTRAINT fk_analytics_alarms_analytics_light_type
  FOREIGN KEY ( light_type_id )
   REFERENCES analytics_light_type ( light_type_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE analytics_installed ADD CONSTRAINT fk_analytics_installed_analytics_light_type
  FOREIGN KEY ( light_type_id )
   REFERENCES analytics_light_type ( light_type_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE analytics_warnings ADD CONSTRAINT fk_analytics_warnings_analytics_light_type
  FOREIGN KEY ( light_type_id )
   REFERENCES analytics_light_type ( light_type_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE analytics_consumption ADD CONSTRAINT fk_analytics_consumption_analytics_light_type
  FOREIGN KEY ( light_type_id )
   REFERENCES analytics_light_type ( light_type_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE analytics_group_by_date ADD CONSTRAINT fk_analytics_group_by_date_analytics_group
  FOREIGN KEY ( analytic_group_id )
   REFERENCES analytics_group ( analytic_group_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE analytics_warnings ADD CONSTRAINT fk_analytics_warnings_analytics_alarm_warning_subtype
  FOREIGN KEY ( analytics_warning_subtype )
   REFERENCES analytics_alarm_warning_subtype ( analytics_alarm_warning_subtype_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE analytics_alarms ADD CONSTRAINT fk_analytics_alarms_analytics_alarm_warning_subtype
  FOREIGN KEY ( analytics_alarm_subtype )
   REFERENCES analytics_alarm_warning_subtype ( analytics_alarm_warning_subtype_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE custom_search ADD CONSTRAINT fk_custom_search_tenant_id
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE custom_search ADD CONSTRAINT fk_custom_search_users
  FOREIGN KEY ( user_id )
   REFERENCES users ( user_id )
    ON DELETE RESTRICT NOT DEFERRABLE;
ALTER TABLE sensus_part_number_configuration ADD CONSTRAINT fk_sensus_part_number_configuration_sensus_part_number
  FOREIGN KEY ( sensus_part_number_id )
   REFERENCES sensus_part_number ( sensus_part_number_id )
    ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE users_grouping ADD CONSTRAINT fk_users_grouping_users
  FOREIGN KEY ( user_id )
   REFERENCES users ( user_id )
    NOT DEFERRABLE;
ALTER TABLE users_grouping ADD CONSTRAINT fk_users_grouping_grouping
  FOREIGN KEY ( grouping_id )
   REFERENCES grouping ( grouping_id )
    NOT DEFERRABLE;
ALTER TABLE authorities ADD CONSTRAINT fk_authorities_users
  FOREIGN KEY ( user_id )
   REFERENCES users ( user_id )
    NOT DEFERRABLE;

ALTER TABLE current_alarm_warning_message ADD CONSTRAINT fk_status_current_alarm_warning_message
  FOREIGN KEY ( status_messages_status_id )
   REFERENCES status ( status_id )
    NOT DEFERRABLE;
ALTER TABLE current_alarm_warning_message ADD CONSTRAINT fk_status_subtype_current_alarm_warning_message
  FOREIGN KEY ( status_messages_status_subtype_id )
   REFERENCES status_subtype ( status_subtype_id )
    NOT DEFERRABLE;

ALTER TABLE current_alarm_warning_message ADD CONSTRAINT fk_status_message_current_alarm_warning_message
  FOREIGN KEY ( status_message_id )
   REFERENCES status_message ( status_message_id )
    NOT DEFERRABLE;
    
ALTER TABLE current_alarm_warning_message ADD CONSTRAINT fk_light_current_alarm_warrning_message
  FOREIGN KEY ( light_id )
   REFERENCES light ( light_id )
    NOT DEFERRABLE;
ALTER TABLE current_alarm_warning_message ADD CONSTRAINT fk_tenant_current_alarm_warning_message
  FOREIGN KEY ( tenant_id )
   REFERENCES tenant ( tenant_id )
    NOT DEFERRABLE;
	
ALTER TABLE light_daily_consumption ADD CONSTRAINT fk_light_light_daily_consumption
	FOREIGN KEY (light_id)
	 REFERENCES public.light (light_id)
      NOT DEFERRABLE;
    