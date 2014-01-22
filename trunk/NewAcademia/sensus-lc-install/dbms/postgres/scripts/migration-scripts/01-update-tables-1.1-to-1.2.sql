DROP TABLE IF EXISTS sensus_part_number_configuration;
DROP TABLE IF EXISTS sensus_part_number;

-- Add new column to control status messages notification
ALTER TABLE light ADD COLUMN status_message_transation_id character varying(40);

CREATE INDEX nx_light_status_message_transation_id ON light
(
 status_message_transation_id
);

UPDATE light
   SET status_message_transation_id = l.current_status_message_id
  FROM light l
 WHERE light.light_id = l.light_id;

ALTER TABLE status_message ADD COLUMN status_message_transation_id character varying(40);

CREATE INDEX nx_status_message_transation_id ON status_message
(
 status_message_transation_id
);

UPDATE status_message sm
   SET status_message_transation_id = l.status_message_transation_id
  FROM light l JOIN current_alarm_warning_message cawm ON l.light_id = cawm.light_id
 WHERE sm.status_message_id = cawm.status_message_id;

UPDATE status_message sm
   SET status_message_transation_id = sm.light_id || TB.long_date
  FROM (
	  SELECT DISTINCT(EXTRACT(EPOCH FROM date_trunc('SECOND',sm.message_date)))::character varying AS long_date
	    FROM light l JOIN status_message sm ON sm.light_id = l.light_id
        ) TB
 WHERE sm.status_message_transation_id IS NULL
   AND EXTRACT(EPOCH FROM date_trunc('SECOND',sm.message_date))::character varying = TB.long_date;

ALTER TABLE status_message ALTER COLUMN status_message_transation_id SET NOT NULL;

CREATE TABLE light_filter_type
(
		  light_filter_type_id serial NOT NULL,
		  name character varying(4094) NOT NULL,
		  create_date timestamp with time zone NOT NULL,
		  create_user character varying(20) NOT NULL,
		  PRIMARY KEY (light_filter_type_id)
);

CREATE TABLE light_filter_value
(
		  light_filter_value_id serial NOT NULL,
		  filter_value character varying(4094) NOT NULL,
		  light_filter_type_id integer NOT NULL,
		  create_date timestamp with time zone NOT NULL,
		  create_user character varying(20) NOT NULL,
		  PRIMARY KEY (light_filter_value_id),
		  CONSTRAINT fk_light_filter_type FOREIGN KEY (light_filter_type_id)
		      REFERENCES light_filter_type (light_filter_type_id) MATCH SIMPLE
		      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE light_schedule (
                light_id INTEGER NOT NULL,
                sunrise_time CHARACTER VARYING(20),
                sunrise_offset INTEGER,
                sunset_time CHARACTER VARYING(20),
                sunset_offset INTEGER,
                create_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                modified_date TIMESTAMP WITH TIME ZONE,
                create_user CHARACTER VARYING(20) NOT NULL,
                modified_user CHARACTER VARYING(20),
                CONSTRAINT pk_light_schedule PRIMARY KEY (light_id)
);

CREATE TABLE light_last_operational_data (
                light_id INTEGER NOT NULL,
                ac_voltage DOUBLE PRECISION,
                ac_voltage_min DOUBLE PRECISION,
                ac_voltage_min_date TIMESTAMP WITHOUT TIME ZONE,
                ac_voltage_max DOUBLE PRECISION,
                ac_voltage_max_date TIMESTAMP WITHOUT TIME ZONE,
                ac_current DOUBLE PRECISION,
                ac_current_min DOUBLE PRECISION,
                ac_current_min_date TIMESTAMP WITHOUT TIME ZONE,
                ac_current_max DOUBLE PRECISION,
                ac_current_max_date TIMESTAMP WITHOUT TIME ZONE,
                consumption DOUBLE PRECISION,
                consumption_min DOUBLE PRECISION,
                consumption_max DOUBLE PRECISION,
                create_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                modified_date TIMESTAMP WITHOUT TIME ZONE,
                create_user CHARACTER VARYING(20) NOT NULL,
                modified_user CHARACTER VARYING(20),
                CONSTRAINT pk_light_last_op_data PRIMARY KEY (light_id)
);

CREATE TABLE light_configuration (
                light_id INTEGER NOT NULL,
                housing CHARACTER VARYING(30),
                housing_color CHARACTER VARYING(20),
                dimmable BOOLEAN DEFAULT FALSE,
                lamp_type_wattage_dimmable CHARACTER VARYING(20),
                wattage_rating CHARACTER VARYING(10),
                input_voltage_range CHARACTER VARYING(20),
                color_temperature CHARACTER VARYING(10),
                manufacturer CHARACTER VARYING(30),
                firmware_version CHARACTER VARYING(10),
                model_number CHARACTER VARYING(30),
                lower_assembly_serial_number CHARACTER VARYING(20),
                upper_assembly_serial_number CHARACTER VARYING(20),
                frequency CHARACTER VARYING(10),
                date_added TIMESTAMP WITHOUT TIME ZONE,
                bulb_serial_number CHARACTER VARYING(20),
                ballast_serial_number CHARACTER VARYING(20),
                customer_serial_number CHARACTER VARYING(20),
                create_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                modified_date TIMESTAMP WITH TIME ZONE,
                create_user CHARACTER VARYING(20) NOT NULL,
                modified_user CHARACTER VARYING(20),
                CONSTRAINT pk_light_configuration_id PRIMARY KEY (light_id)
);

CREATE TABLE light_location (
                light_id INTEGER NOT NULL,
                timezone CHARACTER VARYING(30),
                latitude DOUBLE PRECISION,
                longitude DOUBLE PRECISION,
                street_name CHARACTER VARYING(50),
                city_name CHARACTER VARYING(50),
                county_name CHARACTER VARYING(50),
                state_name CHARACTER VARYING(50),
                zip_code CHARACTER VARYING(10),
                create_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                modified_date TIMESTAMP WITH TIME ZONE,
                create_user CHARACTER VARYING(20) NOT NULL,
                modified_user CHARACTER VARYING(20),
                CONSTRAINT pk_light_location_id PRIMARY KEY (light_id)
);

CREATE INDEX nx_light_location_latitude ON light_location
(
 latitude
);

CREATE INDEX nx_light_location_longitude ON light_location
(
 longitude
);

ALTER TABLE light ALTER COLUMN light_status DROP NOT NULL;
ALTER TABLE light ADD COLUMN light_status_subtype SMALLINT NULL;
ALTER TABLE light ADD COLUMN light_state SMALLINT NULL;
ALTER TABLE light ADD COLUMN pole_id CHARACTER VARYING(30) NULL;
ALTER TABLE light ADD COLUMN light_type SMALLINT NULL;


ALTER TABLE light_location ADD CONSTRAINT fk_light_light_location
FOREIGN KEY (light_id)
REFERENCES light (light_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE light_configuration ADD CONSTRAINT fk_light_light_configuration
FOREIGN KEY (light_id)
REFERENCES light (light_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE light_last_operational_data ADD CONSTRAINT fk_light_light_last_op_data
FOREIGN KEY (light_id)
REFERENCES light (light_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE light_schedule ADD CONSTRAINT fk_light_light_schedule
FOREIGN KEY (light_id)
REFERENCES light (light_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER DATABASE LC SET timezone to 'UTC';