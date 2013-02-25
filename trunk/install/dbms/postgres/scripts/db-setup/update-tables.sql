 ALTER TABLE tenant ADD COLUMN min_smartpoint_comm_time INTEGER NOT NULL DEFAULT 3;
 ALTER TABLE tenant ADD COLUMN ecomode_disable boolean DEFAULT false;
 ALTER TABLE light DROP COLUMN spatial_location;
 ALTER TABLE grouping DROP COLUMN spatial_location;
 ALTER TABLE custom_search_property ADD COLUMN display_order INTEGER NULL;
 ALTER TABLE user_settings_property ADD COLUMN display_order INTEGER NULL; 
 
 CREATE TABLE users_grouping
(
  user_id integer NOT NULL,
  grouping_id integer NOT NULL,
  create_user character varying(50),
  create_date timestamp with time zone,
  PRIMARY KEY (user_id,grouping_id)
  
);

CREATE TABLE authorities
(
  user_id integer NOT NULL,
  authority character varying(50) NOT NULL
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

DROP TABLE operational_data_value;

CREATE TABLE operational_data_value
(
  value double precision NOT NULL,
  create_user character varying(20) NOT NULL,
  create_date timestamp with time zone NOT NULL,
  operational_data_type_id integer NOT NULL,
  status_message_id integer NOT NULL,
  PRIMARY KEY (operational_data_type_id,status_message_id,create_date)
);

CREATE INDEX ix_auth_username ON authorities
(
  user_id,
  authority
);

DROP INDEX IF EXISTS nx_user_settings_user_id;
CREATE INDEX nx_user_settings_user_id ON user_settings
(
  user_id
);

DROP INDEX IF EXISTS ux_tenant_id_user_id_key;
CREATE UNIQUE INDEX ux_tenant_id_user_id_key ON user_settings
(
  tenant_id,
  user_id
);

DROP INDEX IF EXISTS nx_custom_search_user_id;
CREATE INDEX nx_custom_search_user_id ON custom_search
(
  user_id
);
  
ALTER TABLE custom_search DROP CONSTRAINT fk_custom_search_user_id;
--ALTER TABLE custom_search RENAME user_id  TO username;
--ALTER TABLE custom_search
  -- ALTER COLUMN username TYPE character varying(50);

ALTER TABLE lc_user
   ALTER COLUMN create_user TYPE character varying(50);
ALTER TABLE lc_user
   ALTER COLUMN modify_user TYPE character varying(50);
ALTER TABLE lc_user ADD COLUMN username character varying(50) NOT NULL;
ALTER TABLE lc_user DROP CONSTRAINT lc_user_pkey CASCADE;
ALTER TABLE lc_user DROP COLUMN user_id;
ALTER TABLE lc_user ADD COLUMN user_id serial NOT NULL;
ALTER TABLE lc_user ADD COLUMN tenant_id integer NOT NULL;
ALTER TABLE lc_user ADD COLUMN password character varying(250) NOT NULL;
ALTER TABLE lc_user ADD COLUMN enabled boolean NOT NULL;
ALTER TABLE lc_user ADD COLUMN all_lights_auth boolean NOT NULL DEFAULT false;
ALTER TABLE lc_user RENAME TO users;

ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);

ALTER TABLE users ADD CONSTRAINT ux_users_unique_tenant_user_id UNIQUE (username,tenant_id);

ALTER TABLE user_settings DROP COLUMN user_id;
ALTER TABLE user_settings ADD COLUMN user_id integer not null;

ALTER TABLE user_settings ADD CONSTRAINT fk_user_settings_users
  FOREIGN KEY ( user_id )
   REFERENCES users ( user_id );
   
ALTER TABLE custom_search DROP COLUMN user_id;
ALTER TABLE custom_search ADD COLUMN user_id integer not null;
   
ALTER TABLE custom_search ADD CONSTRAINT fk_custom_search_users
  FOREIGN KEY ( user_id )
   REFERENCES users ( user_id );

ALTER TABLE users_grouping DROP COLUMN user_id;
ALTER TABLE users_grouping ADD COLUMN user_id integer not null;
   
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
    
ALTER TABLE light ADD COLUMN calculate_retroactive_ecomode boolean DEFAULT false, boolean DEFAULT false;

