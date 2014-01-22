-- *************************************************************************
-- **	                       SLC 1.2 DB UPDATE                          **
-- ** Version: 0.0                                                        **
-- ** Author:  Thiago - thiago_silva@qat.com                              **
-- **          Gustavo - gustavo_peres@qat.com                            **
-- ** CAUTION - CAUTION - CAUTION - CAUTION - CAUTION - CAUTION - CAUTION **
-- ** CAUTION - PRODUCTION ENVIRONMENT NEEDS MIGRATION SCRIPT - CAUTION   **
-- *************************************************************************

-- DROP TABLE light_tag;
-- DROP INDEX idxlight_tag_tag_id;
-- DROP INDEX nx_light_tag_light_id;
-- DROP TABLE light_grouping;
-- DROP INDEX nx_light_grouping_grouping_id;
-- DROP INDEX nx_light_grouping_light_id;
-- DROP TABLE light2;

/* Table: light_tag */

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
)
WITH (
  OIDS=FALSE
);

ALTER TABLE light_tag OWNER TO postgres;

/* Index: idxlight_tag_tag_id */

CREATE INDEX idxlight_tag_tag_id
  ON light_tag
  USING btree
  (tag_id );

/* Index: nx_light_tag_light_id */

CREATE INDEX nx_light_tag_light_id
  ON light_tag
  USING btree
  (light_id);

/* Table: light_grouping */


CREATE TABLE light_grouping
(
  create_date timestamp with time zone NOT NULL,
  create_user character varying(20) NOT NULL,
  grouping_id integer NOT NULL,
  light_id integer NOT NULL,
  CONSTRAINT light_grouping_pkey PRIMARY KEY (grouping_id , light_id),
  CONSTRAINT fk_light_group_group FOREIGN KEY (grouping_id)
      REFERENCES grouping (grouping_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT fk_light_group_light FOREIGN KEY (light_id)
      REFERENCES light (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE light_grouping
  OWNER TO postgres;

/* Index: nx_light_grouping_grouping_id */

CREATE INDEX nx_light_grouping_grouping_id
  ON light_grouping
  USING btree
  (grouping_id );

/* Index: nx_smartpoint_grouping_smartpoint_id */

CREATE INDEX nx_light_grouping_light_id
  ON light_grouping
  USING btree
  (light_id);

/*  Light table */


CREATE TABLE light2
(
  -- Device columns
  light_id serial NOT NULL,
  light_type smallint NOT NULL,
  lifecycle_state smallint NOT NULL,
  light_state smallint NOT NULL,

  create_date timestamp with time zone NOT NULL,
  modified_date timestamp with time zone,
  create_user character varying(20) NOT NULL,
  modify_user character varying(20),

  -- Radio columns
  tenant_id integer NOT NULL,
  flexnet_id integer NOT NULL,

  -- Location columns
  address character varying(50),
  city character varying(50),
  state character varying(50),
  zip_code character varying(10),
  county character varying(50),
  timezone character varying(30),
  latitude double precision,
  longitude double precision,

  -- Rest of Light columns
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

  CONSTRAINT light2_pkey PRIMARY KEY (light_id )
)
WITH (
  OIDS=FALSE
);

ALTER TABLE light2
  OWNER TO postgres;

/* Light Sequence */

CREATE SEQUENCE light2_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE light2_id_seq
  OWNER TO postgres;

/* Alert Type */

CREATE TABLE alert_type (
	alert_type_id INTEGER NOT NULL,
	label_key VARCHAR(80) NOT NULL,
	precedence INTEGER NOT NULL,
	create_date TIMESTAMP NOT NULL,
	create_user VARCHAR(20) NOT NULL,
	CONSTRAINT alert_type_pkey PRIMARY KEY (alert_type_id)
);

/* Alert Subtype */

CREATE TABLE alert_subtype (
	alert_subtype_id INTEGER NOT NULL,
	label_key VARCHAR(80) NOT NULL,
	create_date TIMESTAMP NOT NULL,
	create_user VARCHAR(20) NOT NULL,
	alert_type_id INTEGER NOT NULL,
	CONSTRAINT alert_subtype_pkey PRIMARY KEY (alert_subtype_id)
);

CREATE INDEX nx_alert_subtype_alert_id
 ON alert_subtype USING BTREE
 ( alert_type_id );


ALTER TABLE alert_subtype ADD CONSTRAINT fk_alert_type_alert_subtype
FOREIGN KEY (alert_type_id)
REFERENCES alert_type (alert_type_id)
ON DELETE RESTRICT
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* Notification history sequence */

CREATE SEQUENCE notification_history_status_message_id_seq;

CREATE TABLE notification_history (
	notification_history_id INTEGER NOT NULL DEFAULT nextval('notification_history_status_message_id_seq'),
	light_id INTEGER NOT NULL,
	lifecycle_state smallint NOT NULL,
	notification_type smallint NOT NULL,
	message_date TIMESTAMP NOT NULL,
	create_date TIMESTAMP NOT NULL,
	update_date TIMESTAMP NOT NULL,
	create_user VARCHAR(20) NOT NULL,
	precedence INTEGER,
	simple_notification BOOLEAN,
	notification_transation_id VARCHAR(40),
	CONSTRAINT notification_history_id PRIMARY KEY (notification_history_id)
);

ALTER SEQUENCE notification_history_status_message_id_seq OWNED BY notification_history.notification_history_id;


/* Triggers to maintain date columns from light2 table */
CREATE TRIGGER light2_ins_trigger
  BEFORE INSERT
  ON light2
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

CREATE TRIGGER light2_upd_trigger
  BEFORE UPDATE
  ON light2
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();

/* Triggers to maintain analytics tables */
CREATE TRIGGER ins_light2_trigger
  AFTER INSERT
  ON light2
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_ins_light();

CREATE TRIGGER upd_light2_trigger
  AFTER UPDATE
  ON light2
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_upd_light();

/* Table: notification_history_alert */

  CREATE TABLE notification_history_alert (
	alert_subtype_id INTEGER NOT NULL,
	notification_history_id INTEGER NOT NULL,
	create_date TIMESTAMP NOT NULL,
	update_date TIMESTAMP NOT NULL,
	message_date TIMESTAMP NOT NULL,
	create_user VARCHAR(20) NOT NULL,
	CONSTRAINT notification_history_pk PRIMARY KEY (alert_subtype_id, notification_history_id)
);

/* Alter table notification_history_alert */

ALTER TABLE notification_history_alert ADD CONSTRAINT notification_history_notification_history_alert_fk
FOREIGN KEY (notification_history_id)
REFERENCES notification_history (notification_history_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;


ALTER TABLE notification_history_alert ADD CONSTRAINT alert_subtype_notification_history_alert_fk
FOREIGN KEY (alert_subtype_id)
REFERENCES alert_subtype (alert_subtype_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* Remove constraint to light  */

ALTER TABLE light_schedule DROP CONSTRAINT fk_light_light_schedule;

ALTER TABLE light_configuration DROP CONSTRAINT fk_light_light_configuration;

ALTER TABLE light_last_operational_data DROP CONSTRAINT fk_light_light_last_op_data;

/* Table light_process */

CREATE TABLE light_process
(
  process_result integer NOT NULL,
  light_id integer NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  failure_id integer,
  process_id integer NOT NULL,
  CONSTRAINT light_process_pkey PRIMARY KEY (light_id , process_id ),
  CONSTRAINT fk_failure_light_process FOREIGN KEY (failure_id)
      REFERENCES failure (failure_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_process_light_process FOREIGN KEY (process_id)
      REFERENCES process (process_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_light_light_process FOREIGN KEY (light_id)
      REFERENCES light2 (light_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE light_process
  OWNER TO postgres;

/* Trigger: light_process_trigger */

CREATE TRIGGER light_process_trigger
  BEFORE INSERT
  ON light_process
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();

ALTER TABLE schedule_membership ADD COLUMN light_id integer;
ALTER TABLE schedule_membership ADD CONSTRAINT fk_schedule_membership_light FOREIGN KEY (light_id) REFERENCES light2 (light_id) ON UPDATE NO ACTION ON DELETE NO ACTION;
