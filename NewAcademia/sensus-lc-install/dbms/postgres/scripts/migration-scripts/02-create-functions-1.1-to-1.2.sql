------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION apply_smartpoint_in_schedule(p_smartpoint_id integer, p_schedule_id integer, p_create_user character varying)
  RETURNS void AS
$$
DECLARE

    p_schedule_type int;
    p_schedule_type_aux int;
    p_schedule_offset int;
    p_schedule_event int;
    p_schedule_offset_undefined int;
    p_schedule_event_undefined int;

BEGIN

    p_schedule_offset := 1;
    p_schedule_event := 2;
    p_schedule_offset_undefined := 3;
    p_schedule_event_undefined := 4;

    p_schedule_type := (SELECT schedule_type FROM schedule WHERE schedule_id = p_schedule_id);

    IF p_schedule_type = p_schedule_offset THEN
        p_schedule_type_aux := p_schedule_offset_undefined;
    ELSIF p_schedule_type = p_schedule_offset_undefined THEN
        p_schedule_type_aux := p_schedule_offset;
    ELSIF p_schedule_type = p_schedule_event THEN
        p_schedule_type_aux := p_schedule_event_undefined;
    ELSIF p_schedule_type = p_schedule_event_undefined THEN
        p_schedule_type_aux := p_schedule_event;
    END IF;

    BEGIN
        INSERT INTO schedule_membership (schedule_id   ,smartpoint_id   ,create_user)
                                 VALUES (p_schedule_id ,p_smartpoint_id ,p_create_user);
    EXCEPTION
        WHEN unique_violation THEN
            UPDATE schedule_membership
               SET modify_user   = p_create_user
             WHERE smartpoint_id = p_smartpoint_id
               AND schedule_id   = p_schedule_id;
    END;

    DELETE
      FROM schedule_membership sm
     WHERE sm.smartpoint_id = p_smartpoint_id
       AND sm.schedule_id  != p_schedule_id
       AND EXISTS (SELECT 1
                     FROM schedule s
                    WHERE s.schedule_id   = sm.schedule_id
                      AND (   s.schedule_type = p_schedule_type
                           OR s.schedule_type = p_schedule_type_aux));
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
CREATE OR REPLACE FUNCTION apply_unknown_event_schedule(p_rni_id integer, p_schedule_id integer, p_create_user character varying)
RETURNS void AS
$$
DECLARE
    p_smartpoint_id int;
    p_schedule_type int;
    p_undetermined_event_type int;
BEGIN
    p_smartpoint_id := (SELECT smartpoint_id FROM smartpoint WHERE rni_id = p_rni_id);
    p_schedule_type := 2;
    p_undetermined_event_type := 4;
    IF  EXISTS (SELECT schedule_membership.create_date ,schedule_membership.modified_date ,schedule_membership.create_user ,schedule_membership.modify_user ,schedule_membership.schedule_id ,schedule_membership.smartpoint_id FROM schedule_membership, schedule WHERE smartpoint_id = p_smartpoint_id AND schedule_membership.schedule_id = schedule.schedule_id AND (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_event_type))
    THEN
                        UPDATE schedule_membership
                        SET schedule_id = p_schedule_type,
                        modify_user = p_create_user
                        FROM schedule
                        WHERE schedule_membership.smartpoint_id = p_smartpoint_id
                        AND schedule_membership.schedule_id = schedule.schedule_id
                        AND (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_event_type);
    ELSE
           INSERT INTO schedule_membership
           (schedule_id
           ,smartpoint_id
           ,create_user)
                VALUES
           (p_schedule_type
           ,p_smartpoint_id
           ,p_create_user);
    END IF;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION apply_unknown_offset_schedule(p_rni_id integer, p_schedule_id integer, p_create_user character varying)
RETURNS void AS
$$
DECLARE
    p_smartpoint_id int;
    p_schedule_type int;
    p_undetermined_offset_type int;
BEGIN
    p_smartpoint_id := (SELECT smartpoint_id FROM smartpoint WHERE rni_id = p_rni_id);
    p_schedule_type := 1;
    p_undetermined_offset_type := 3;
        IF  EXISTS (SELECT schedule_membership.create_date ,schedule_membership.modified_date ,schedule_membership.create_user ,schedule_membership.modify_user ,schedule_membership.schedule_id ,schedule_membership.smartpoint_id FROM schedule_membership, schedule WHERE smartpoint_id = p_smartpoint_id and schedule_membership.schedule_id = schedule.schedule_id and (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_offset_type))
        THEN
                        UPDATE schedule_membership SET schedule_id = p_schedule_type,
                        modify_user = p_create_user
                        FROM schedule
                        WHERE schedule_membership.smartpoint_id = p_smartpoint_id
                        AND schedule_membership.schedule_id = schedule.schedule_id
                        AND (schedule.schedule_type = p_schedule_type OR schedule.schedule_type = p_undetermined_offset_type);
        ELSE
                        INSERT  INTO schedule_membership
           (schedule_id
           ,smartpoint_id
           ,create_user)
                        VALUES

           (p_schedule_type
           ,p_smartpoint_id
           ,p_create_user);
        END IF;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_group(p_grouping_id integer)
  RETURNS void AS
$$
BEGIN
    DELETE FROM users_grouping
           WHERE grouping_id = p_grouping_id;

    DELETE FROM grouping
           WHERE grouping_id = p_grouping_id;

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
CREATE OR REPLACE FUNCTION del_schedule (p_schedule_id int)
RETURNS void AS
$$
BEGIN
    DELETE FROM schedule
           WHERE schedule_id = p_schedule_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_schedule_event (p_schedule_id int)
RETURNS void AS
$$
BEGIN
    DELETE FROM schedule_event
           WHERE schedule_id = p_schedule_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_light_id_by_rni_id (p_rni_light_id int)
RETURNS integer AS
$$
BEGIN
    RETURN (SELECT light.light_id FROM light INNER JOIN smartpoint ON light.smartpoint_id = smartpoint.smartpoint_id WHERE smartpoint.rni_id = p_rni_light_id);
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_group (p_name varchar(100) , p_description varchar(150) , p_tenant_id int , p_create_user varchar(20))
RETURNS int AS
$$
DECLARE
        id int;
BEGIN
        INSERT INTO grouping
           (name
           ,description
           ,tenant_id
           ,create_user)
        VALUES
           (p_name
           ,p_description
           ,p_tenant_id
           ,p_create_user) RETURNING grouping_id INTO id;

        RETURN id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION ins_process(p_description character varying, p_lc_action_description character varying, p_start_datetime timestamp with time zone, p_end_datetime timestamp with time zone, p_rni_correlation_id character varying, p_is_submitted boolean, p_lc_action_id integer, p_tenant_id integer, p_parent_process_id integer, p_is_monitored_instance boolean, p_estimated_seconds_to_complete bigint, p_create_user character varying, p_is_process_complete boolean, p_is_first_level boolean)
  RETURNS integer AS
$$
DECLARE
  id int;
BEGIN
        INSERT INTO process
           (description
           ,lc_action_description
           ,start_datetime
           ,end_datetime
           ,rni_correlation_id
           ,is_submitted
           ,lc_action_id
           ,tenant_id
           ,parent_process_id
           ,is_monitored_instance
           ,estimated_seconds_to_complete
           ,create_user
           ,is_process_complete
           ,is_first_level)
    VALUES
           (p_description
           ,p_lc_action_description
           ,p_start_datetime
           ,p_end_datetime
           ,p_rni_correlation_id
           ,p_is_submitted
           ,p_lc_action_id
           ,p_tenant_id
           ,p_parent_process_id
           ,p_is_monitored_instance
           ,p_estimated_seconds_to_complete
           ,p_create_user
           ,p_is_process_complete
           ,p_is_first_level) RETURNING process_id INTO id;

    RETURN id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_schedule(p_name character varying, p_description character varying, p_sunrise_offset integer, p_sunset_offset integer, p_tenant_id integer, p_create_user character varying, p_schedule_type integer, p_use_sunrise_sunset_tables boolean, p_intensity integer)
RETURNS integer AS
$$
DECLARE
        id int;
BEGIN
    INSERT INTO schedule
           (name
           ,description
           ,sunrise_offset
           ,sunset_offset
           ,tenant_id
           ,create_user
           ,schedule_type
           ,use_sunrise_sunset_tables
           ,intensity)
    VALUES
           (p_name
           ,p_description
           ,p_sunrise_offset
           ,p_sunset_offset
           ,p_tenant_id
           ,p_create_user
           ,p_schedule_type
           ,p_use_sunrise_sunset_tables
           ,p_intensity) RETURNING schedule_id INTO id;
        RETURN id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_schedule_event(p_event_time timestamp with time zone, p_intensity integer, p_blink_level integer, p_schedule_id integer, p_tenant_id integer, p_create_user character varying)
RETURNS integer AS
$$
DECLARE
        id int;
BEGIN
    INSERT INTO schedule_event
           (event_time
           ,intensity
           ,blink_level
           ,schedule_id
           ,tenant_id
           ,create_user)
    VALUES
           (p_event_time
           ,p_intensity
           ,p_blink_level
           ,p_schedule_id
           ,p_tenant_id
           ,p_create_user) RETURNING schedule_event_id INTO id;
    RETURN id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_schedule_event_day_of_week (p_day_of_week_id int , p_schedule_event_id int , p_create_user varchar(20))
RETURNS void AS
$$
BEGIN
    INSERT INTO schedule_event_day_of_week
           (day_of_week_id
           ,schedule_event_id
           ,create_user)
    VALUES
           (p_day_of_week_id
           ,p_schedule_event_id
           ,p_create_user);
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_smartpoint_process(p_process_result integer, p_smartpoint_id integer, p_process_id integer, p_failure_id integer)
RETURNS integer AS
$$
DECLARE
        id int;
BEGIN
    INSERT INTO smartpoint_process
           (process_result
           ,smartpoint_id
           ,process_id
           ,failure_id)
    VALUES
           (p_process_result
           ,p_smartpoint_id
           ,p_process_id
           ,p_failure_id) RETURNING process_result INTO id;

    RETURN id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : Inserts a tag in LC
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_tag(p_name character varying, p_autogroup boolean, p_address_related boolean,  p_tenant_id integer, p_create_user character varying)
RETURNS integer AS
$$
DECLARE
        id int;
BEGIN
    INSERT INTO tag
           (name
           ,auto_group
           ,address_related
           ,tenant_id
           ,create_user)
    VALUES
           (p_name
           ,p_autogroup
           ,p_address_related
           ,(SELECT tenant_id from tenant where tenant.tenant_id = p_tenant_id)
           ,p_create_user) RETURNING tag_id INTO id;

     RETURN id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_group(p_grouping_id integer, p_name character varying, p_description character varying, p_modify_user character varying, p_old_name character varying)
  RETURNS void AS
$$
BEGIN
        UPDATE grouping
        SET name = p_name
                   ,description = p_description
                   ,modify_user = p_modify_user
        WHERE grouping_id = p_grouping_id;

        UPDATE analytics_group
        SET analytic_group_name = p_name
        WHERE analytic_group_name = p_old_name;
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
CREATE OR REPLACE FUNCTION upd_is_monitored_process(p_is_monitored_instance boolean, p_process_id integer)
RETURNS void AS
$$
BEGIN
    UPDATE process
    SET is_monitored_instance = p_is_monitored_instance
    WHERE process_id = p_process_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_process(p_end_datetime timestamp with time zone, p_is_monitored_instance boolean, p_is_process_complete boolean, p_process_id integer, p_is_submitted boolean, p_rni_correlation_id character varying, p_description character varying)
RETURNS void AS
$$
BEGIN
    UPDATE process
       SET end_datetime  =  COALESCE(p_end_datetime,end_datetime)
          ,is_monitored_instance = COALESCE(p_is_monitored_instance,is_monitored_instance)
          ,is_process_complete = COALESCE(p_is_process_complete,is_process_complete)
          ,is_submitted = COALESCE(p_is_submitted,is_submitted)
          ,rni_correlation_id = COALESCE(p_rni_correlation_id,rni_correlation_id)
          ,description = COALESCE(p_description,description)
     WHERE process_id = p_process_id;
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
CREATE OR REPLACE FUNCTION upd_process_is_first_level(p_process_id integer, p_is_first_level boolean)
RETURNS void AS
$$
BEGIN
    UPDATE process
    SET is_first_level =  p_is_first_level
    WHERE process_id = p_process_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_schedule (p_schedule_id int , p_name varchar(50) , p_description varchar(256) , p_sunrise_offset int , p_sunset_offset int , p_modify_user varchar(20), p_intensity integer)
RETURNS void AS
$$
BEGIN
    UPDATE schedule
    SET name = p_name
      ,description = p_description
      ,sunrise_offset = p_sunrise_offset
      ,sunset_offset = p_sunset_offset
      ,modify_user = p_modify_user
      ,intensity = p_intensity
    WHERE schedule_id = p_schedule_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_smartpoint_process(p_process_id integer, p_smartpoint_id integer, p_process_result integer, p_failure_id integer)
  RETURNS integer AS
$$
BEGIN
        UPDATE smartpoint_process
        SET process_result = p_process_result
                ,failure_id =  p_failure_id
        WHERE smartpoint_id = p_smartpoint_id AND process_id = p_process_id;
        RETURN p_process_id;
END
$$
  LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_modified_date()
RETURNS trigger AS
$$
BEGIN
        NEW.modified_date := (SELECT CURRENT_TIMESTAMP);
        RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_tag_by_id(IN p_tag_id integer)
RETURNS TABLE(tag_id integer, "name" character varying, auto_group boolean) AS
$$
BEGIN
        RETURN QUERY SELECT
                 t.tag_id
                ,t.name
                ,t.auto_group
        FROM tag t
        WHERE t.tag_id = p_tag_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_lights_by_tag_id(IN p_tag_id integer)
RETURNS TABLE(light_id integer, smartpoint_id integer) AS
$$
BEGIN
        RETURN QUERY SELECT
                light.light_id,
                smartpoint_tag.smartpoint_id
        FROM smartpoint_tag, light
        WHERE smartpoint_tag.smartpoint_id = light.smartpoint_id AND smartpoint_tag.tag_id = p_tag_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsert_process_property(p_property_id integer, p_process_id integer, p_property_value character varying)
  RETURNS integer AS
$$
BEGIN
    LOOP
        -- first try to update the key
        UPDATE process_property SET value = p_property_value WHERE (property_id = p_property_id) AND (process_id = p_process_id);
        IF found THEN
            RETURN p_process_id;
        END IF;
        -- not there, so try to insert the key
        -- if someone else inserts the same key concurrently,
        -- we could get a unique-key failure
        BEGIN
            INSERT INTO process_property (property_id, process_id, value)
                        VALUES  (p_property_id
                        , p_process_id
                        , p_property_value);
            RETURN p_process_id;
        EXCEPTION WHEN unique_violation THEN
            -- do nothing, and loop to try the UPDATE again
        END;
    END LOOP;
END;
$$
  LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_auto_group_tag(p_tag_id integer, p_auto_group boolean)
RETURNS void AS
$$
BEGIN
        UPDATE tag
        SET auto_group = p_auto_group
        WHERE tag_id = p_tag_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_tag(p_tag_id integer)
RETURNS void AS
$$
BEGIN
    DELETE FROM tag
    WHERE tag_id = p_tag_id;
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2013
-- Product       : LC
-- Description   :
-- Revision History
-- When                               Who                        What
-- -------------------  --------------------- -------------------------
-- Jan 2013                                                           Alex Barros                                           Removed property table usage
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_group_center(p_group_id integer)
  RETURNS void AS
$$
DECLARE
        v_lat_max double precision;
        v_lat_min double precision;
        v_lon_max double precision;
        v_lon_min double precision;
        v_middle_lat double precision;
        v_middle_long double precision;
BEGIN

        v_lat_max := (SELECT MAX(loc.latitude)
                                                FROM smartpoint_grouping AS smp_grp
                                                LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
                                                LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
                                                LEFT JOIN light_location AS loc ON l.light_id = loc.light_id
                                                WHERE smp_grp.grouping_id = p_group_id );

                                v_lat_min := (SELECT MIN(loc.latitude)
                                                FROM smartpoint_grouping AS smp_grp
                                                LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
                                                LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
                                                LEFT JOIN light_location AS loc ON l.light_id = loc.light_id
                                                WHERE smp_grp.grouping_id = p_group_id );

                                v_lon_max := (SELECT MAX(loc.longitude)
                                                FROM smartpoint_grouping AS smp_grp
                                                LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
                                                LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
                                                LEFT JOIN light_location AS loc ON l.light_id = loc.light_id
                                                WHERE smp_grp.grouping_id = p_group_id );

                                v_lon_min := (SELECT MIN(loc.longitude)
                                                FROM smartpoint_grouping AS smp_grp
                                                LEFT JOIN smartpoint smp ON smp_grp.smartpoint_id = smp.smartpoint_id
                                                LEFT JOIN light AS l ON smp.smartpoint_id = l.smartpoint_id
                                                LEFT JOIN light_location AS loc ON l.light_id = loc.light_id
                                                WHERE smp_grp.grouping_id = p_group_id );

        v_middle_lat  := (v_lat_max + v_lat_min) / 2;
        v_middle_long := (v_lon_max + v_lon_min) / 2;

        UPDATE grouping
        SET
            longitude = v_middle_long,
            latitude = v_middle_lat
        WHERE grouping_id=p_group_id;

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
CREATE OR REPLACE FUNCTION trigger_create_date()
  RETURNS trigger AS
$$
BEGIN
        NEW.create_date := (SELECT CURRENT_TIMESTAMP);
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
CREATE OR REPLACE FUNCTION get_average_light_voltage(p_light_id integer, p_initial_date timestamp with time zone, p_end_date timestamp with time zone)
  RETURNS real AS
$$
DECLARE
    p_type_voltage int;
    v_end_date date;
BEGIN
    p_type_voltage := 3;
    v_end_date := CAST(p_end_date + CAST('1 days' AS interval) AS DATE);
        RETURN (
        SELECT AVG(value) AS value
        FROM operational_data_value odv INNER JOIN status_message sm
        ON odv.status_message_id = sm.status_message_id
        WHERE sm.light_id = p_light_id
        AND odv.create_date < v_end_date
        AND odv.create_date >= p_initial_date
        AND odv.operational_data_type_id = p_type_voltage);
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
CREATE OR REPLACE FUNCTION get_average_light_current(p_light_id integer, p_initial_date timestamp with time zone, p_end_date timestamp with time zone)
  RETURNS real AS
$$
DECLARE
    p_type_current int;
    v_end_date date;
BEGIN
    p_type_current := 2;
    v_end_date := CAST(p_end_date + CAST('1 days' AS interval) AS DATE);
        RETURN (
        SELECT AVG(value) AS value
        FROM operational_data_value odv
        INNER JOIN status_message sm ON odv.status_message_id = sm.status_message_id
        WHERE sm.light_id = p_light_id
        AND odv.create_date < v_end_date
        AND odv.create_date >= p_initial_date
        AND odv.operational_data_type_id = p_type_current);
END
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insert_smartpoint_to_auto_group(p_group_id integer, p_tag_id integer, p_create_user character varying)
RETURNS TABLE(failed_pk integer, failed_group integer) AS
$$
DECLARE
    v_failed_pk integer;
    v_failed_group integer;
    v_smartpoint_id integer;
    v_group_total integer;
BEGIN
    v_failed_pk := 0;
    v_failed_group := 0;
    FOR v_smartpoint_id IN SELECT smartpoint_id
                        FROM smartpoint_tag
                        WHERE tag_id = p_tag_id
                        LOOP
                            v_group_total := 0;
                            SELECT COUNT(1)
                            INTO v_group_total
                            FROM smartpoint_grouping
                            WHERE smartpoint_id = v_smartpoint_id;
                            IF (v_group_total >= 5) THEN
                                v_failed_group := v_failed_group + 1;
                            ELSE
                                BEGIN
                                    INSERT INTO smartpoint_grouping (create_user, grouping_id, smartpoint_id)
                                    VALUES (p_create_user, p_group_id, v_smartpoint_id);
                                EXCEPTION WHEN unique_violation THEN
                                    v_failed_pk := v_failed_pk + 1;
                                END;
                            END IF;
                        END LOOP;
    RETURN QUERY SELECT v_failed_pk, v_failed_group;
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
CREATE OR REPLACE FUNCTION upsert_settings(p_user_id integer, p_create_user character varying, p_tenant_id integer, p_property_id integer, p_user_setting_property_value character varying)
  RETURNS void AS
$$
DECLARE
  v_user_settings_id int;
BEGIN

    if p_user_id is null then
        SELECT user_settings_id
          into v_user_settings_id
          from user_settings
         where user_id is null
           and tenant_id = p_tenant_id;
    else
        SELECT user_settings_id
          into v_user_settings_id
          from user_settings
         where user_id = p_user_id
           and tenant_id = p_tenant_id;
    END IF;

    if v_user_settings_id is null then
        INSERT INTO USER_SETTINGS (tenant_id   ,user_id   ,create_user)
                           VALUES (p_tenant_id ,p_user_id ,p_create_user) RETURNING user_settings_id INTO v_user_settings_id;
    END IF;

    UPDATE user_settings_property
       SET modify_user = p_user_id
          ,user_setting_property_value = p_user_setting_property_value
     WHERE property_id = p_property_id
       AND user_settings_id = v_user_settings_id;

    IF found THEN
        RETURN;
    END IF;

    INSERT INTO user_settings_property (user_settings_id   ,property_id   ,create_user   ,user_setting_property_value)
                               VALUES  (v_user_settings_id ,p_property_id ,p_create_user ,p_user_setting_property_value);

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
CREATE OR REPLACE FUNCTION upd_system_settings(p_user_id integer, p_tenant_id integer, p_property_id integer, p_user_setting_property_value character varying)
  RETURNS void AS
$BODY$
BEGIN
    -- first try to update the key
    UPDATE user_settings_property SET modify_user = p_user_id, user_setting_property_value = p_user_setting_property_value
    WHERE ((property_id = p_property_id) AND (user_settings_id = (SELECT user_settings.user_settings_id FROM user_settings WHERE tenant_id = p_tenant_id AND user_id is null)));
END;
$BODY$
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
CREATE OR REPLACE FUNCTION get_status_message_by_id(IN p_status_message_id integer)
  RETURNS TABLE(status_message_id integer, message_date timestamp with time zone, status integer) AS
$$
BEGIN

  RETURN QUERY
        SELECT s.status_message_id
                  ,s.message_date
                  ,s.status_id
     FROM status_message s
    WHERE s.status_message_id = p_status_message_id;
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
CREATE OR REPLACE FUNCTION get_analytics_alarms_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp with time zone, p_end_date timestamp with time zone, p_alarm_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
BEGIN

  IF(p_grouping_ids IS NULL) THEN

	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	RETURN
	   (SELECT COALESCE(SUM(aa.value),0)         AS amount
		  FROM analytics_alarms                  aa
			  ,analytics_group_by_date           agbd
					  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(p_alarm_type_id,aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 1))
		   );

  ELSE

	RETURN
	   (SELECT COALESCE(SUM(aa.value),0)         AS amount
		  FROM analytics_alarms                  aa
			  ,analytics_group_by_date           agbd
					  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(p_alarm_type_id,aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 1))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		   );
  END IF;
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
CREATE OR REPLACE FUNCTION ins_analytics_group(p_name character varying, p_tenant_id integer, p_create_user character varying, p_grouping_id integer)
  RETURNS integer AS
$$
DECLARE
        id int;
BEGIN
    INSERT INTO analytics_group(
                analytic_group_name,
                tenant_id,
                create_user,
                grouping_id)
    VALUES (
                p_name,
                p_tenant_id,
                p_create_user,
		        p_grouping_id) RETURNING analytic_group_id INTO id;

    RETURN id;
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
CREATE OR REPLACE FUNCTION ins_analytics_group_by_date(p_analytic_group_id integer, p_analytic_date timestamp with time zone, p_create_user character varying)

RETURNS integer AS
$$
DECLARE
    id int;
BEGIN
     INSERT INTO analytics_group_by_date(
                                analytic_group_id,
                                analytic_date,

                                create_user)
    VALUES (
                        p_analytic_group_id,
                        DATE_TRUNC('minute', p_analytic_date),

                        p_create_user) RETURNING analytic_group_by_date_id INTO id;
    RETURN id;
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
CREATE OR REPLACE FUNCTION ins_analytics_installed(p_light_type_id integer, p_value integer, p_analytic_group_by_date_id integer, p_create_user character varying)
RETURNS integer AS
$$
DECLARE
       new_id int;
           v_analytic_group_id integer;
           v_value integer;
BEGIN

    INSERT INTO analytics_installed(
            light_type_id,
                        "value",
                        analytic_group_by_date_id,
            create_user)
    VALUES (p_light_type_id,
                        p_value,
                        p_analytic_group_by_date_id,
            p_create_user) RETURNING id INTO new_id;

    RETURN new_id;
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
CREATE OR REPLACE FUNCTION trigger_analytics_grouping_ins_grouping()
  RETURNS trigger AS
$$
        BEGIN
                PERFORM  ins_analytics_group(NEW.name, NEW.tenant_id, NEW.create_user, NEW.grouping_id);
                RETURN null;
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
CREATE OR REPLACE FUNCTION get_analytics_group_by_date_pk(p_analytic_group_id integer, p_analytic_date timestamp with time zone, p_create_user character varying)
  RETURNS integer AS
$$
DECLARE
        v_id int;

BEGIN

        -- retrieve analytic_group_by_date_id using group_id and date passed in (most likely current date)
        v_id:= (SELECT max(analytic_group_by_date_id) FROM analytics_group_by_date WHERE analytic_group_id = p_analytic_group_id AND DATE_TRUNC('minute',analytic_date) = DATE_TRUNC('minute',p_analytic_date));

        -- if not found, insert, otherwise return id found
        IF (v_id IS NULL AND p_analytic_group_id IS NOT NULL)
        THEN
                v_id:= (SELECT ins_analytics_group_by_date(p_analytic_group_id, p_analytic_date, p_create_user));
        END IF;

    RETURN v_id;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2013
-- Product       : LC
-- Description   : When light is added, add to the count for that lamp type
--                 and remove from the count for type ='Other' (for all groups the light belongs to)
-- Revision History
-- When                               Who                        What
-- -------------------  --------------------- -------------------------
-- Jan 2013                                                           Alex Barros
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_ins_light
    () RETURNS TRIGGER
AS
    $$
    DECLARE
        v_analytic_group_id         INT;
        v_analytic_group_by_date_id INT;
        v_light_type_id             INT;
        v_tenant_id                 INT;
        v_refcursor refcursor;
        v_record RECORD;
    BEGIN
        IF NEW.light_type IS NOT NULL THEN
            BEGIN
                v_tenant_id :=
                (SELECT tenant_id
                FROM light
                WHERE light_id = NEW.light_id
                )
                ;
                -- Get the analytic_group_by_date_id
                v_analytic_group_id :=
                (SELECT analytic_group_id
                FROM analytics_group
                WHERE analytic_group_name = 'All'
                AND tenant_id             = v_tenant_id
                )
                ;
                v_analytic_group_by_date_id             := get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user);
                IF v_analytic_group_by_date_id IS NOT NULL THEN
                    -- analytics_light_type table mirrors the enum INDUCTION(1),LED(2),OTHER(3);
                    v_light_type_id := NEW.light_type;
                    -- Add 1 to count for type = light_type for current_date, Group='All'
                    -- first try to update the key
                    PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user,'+');
                    -- Add 1 to count for type = light_type for current_date, Group = <all groups light belong to>
                    v_refcursor := get_cursor_groups_for_light(NEW.light_id);
                    LOOP
                        FETCH v_refcursor
                        INTO v_record;

                        EXIT
                    WHEN NOT FOUND;
                        -- Use group_name to retrieve analytic_group_id.
                        v_analytic_group_id :=
                        (SELECT analytic_group_id
                        FROM analytics_group
                        WHERE analytic_group_name = v_record.name
                        AND tenant_id             = v_tenant_id
                        )
                        ;
                        -- Get the analytic_group_by_date_id.
                        v_analytic_group_by_date_id :=
                        (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user)
                        )
                        ;
                        -- Add 1 to count for type = light_type for current_date, Group=<current_group>
                        -- first try to update the key
                        PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_light_type_id, NEW.create_user,'+');
                    END LOOP;
                END IF;
            END;
        END IF;
        RETURN NULL;
    END;
    $$ LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_cursor_groups_for_light(p_light_id integer) RETURNS refcursor AS
$$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT sg.grouping_id, name, smartpoint_id FROM smartpoint_grouping sg, grouping g WHERE smartpoint_id = (SELECT smartpoint_id FROM light WHERE light_id = p_light_id) AND sg.grouping_id = g.grouping_id;
    RETURN ref;
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
CREATE OR REPLACE FUNCTION upsert_analytics_installed(p_analytic_group_by_date_id integer, p_light_type_id integer, p_create_user character varying, p_operator character)
  RETURNS void AS
$$
DECLARE
    p_exist_value bigint;
BEGIN

	IF p_analytic_group_by_date_id IS NOT NULL THEN

		IF p_operator = '+' THEN

			UPDATE analytics_installed SET value = value + 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id);

			IF NOT FOUND THEN
				PERFORM ins_analytics_installed(p_light_type_id, 1, p_analytic_group_by_date_id, p_create_user);
			END IF;

		ELSE

			UPDATE analytics_installed SET value = value - 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id);

			IF NOT FOUND THEN
				PERFORM ins_analytics_installed(p_light_type_id, -1, p_analytic_group_by_date_id, p_create_user);
			END IF;

		END IF;

	END IF;
END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2013
-- Product       : LC
-- Description   : When lamp type is changed, add to the count for that lamp type
--                 and remove from the count for type ='Other' (for all groups the light belongs to)
-- Revision History
-- When                               Who                        What
-- -------------------  --------------------- -------------------------
-- Jan 2013                                                           Alex Barros
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_upd_light
    () RETURNS TRIGGER
AS
    $$
    DECLARE
        v_analytic_group_id         INT;
        v_analytic_group_by_date_id INT;
        v_old_light_type_id         INT;
        v_new_light_type_id         INT;
        v_refcursor refcursor;
        v_record RECORD;
        v_tenant_id INT;
        v_user CHARACTER VARYING;
    BEGIN
        v_user                     := NEW.create_user;
        IF NEW.light_type IS NOT NULL THEN
            BEGIN
                v_tenant_id :=
                (SELECT tenant_id
                FROM light
                WHERE light_id = NEW.light_id
                )
                ;
                -- Get the analytic_group_by_date_id
                v_analytic_group_id :=
                (SELECT analytic_group_id
                FROM analytics_group
                WHERE analytic_group_name = 'All'
                AND tenant_id             = v_tenant_id
                )
                ;
                v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user);
                -- analytics_light_type table mirrors the enum INDUCTION(1),LED(2),OTHER(3);
                v_new_light_type_id := NEW.light_type;
                v_old_light_type_id := OLD.light_type;
                PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_new_light_type_id, NEW.create_user,'+');
                PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_old_light_type_id, NEW.create_user,'-');
                v_refcursor := get_cursor_groups_for_light(NEW.light_id);
                LOOP
                    FETCH v_refcursor
                    INTO v_record;

                    EXIT
                WHEN NOT FOUND;
                    -- Use group_name to retrieve analytic_group_id.
                    v_analytic_group_id :=
                    (SELECT analytic_group_id
                    FROM analytics_group
                    WHERE analytic_group_name = v_record.name
                    AND tenant_id             = v_tenant_id
                    )
                    ;
                    -- Get the analytic_group_by_date_id.
                    v_analytic_group_by_date_id :=
                    (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user)
                    )
                    ;
                    -- Add 1 to count for type = light_type for current_date, Group=<current_group>
                    PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_new_light_type_id, NEW.create_user, '+');
                    PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_old_light_type_id, NEW.create_user, '-');
                END LOOP;
            END;
        END IF;
        RETURN NULL;
    END;
    $$ LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :  When light is added to group, increase analytics_installed for that group.
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_analytics_installed_add_smartpoint_to_group() RETURNS trigger AS
$$
DECLARE

        --v_light_type_id int;
        v_analytic_group_id int;
        v_analytic_group_by_date_id int;
        v_property_light_source int;
        v_light_type integer;
BEGIN
        -- retrieve lamp_type
        v_light_type :=  (SELECT light_type FROM light WHERE smartpoint_id = NEW.smartpoint_id);

        -- Use group_name to retrieve analytic_group_id.
        v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE grouping_id = NEW.grouping_id);

        -- Get the analytic_group_by_date_id.
        v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,NEW.create_user));

        PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_light_type, NEW.create_user, '+');

        RETURN null;

END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : When light excluded from group, decrease analytics_installed for that group. If group cannot be retrieved, it was deleted from LC. Set analytics_installed to 0 for that group
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_analytics_installed_del_smartpoint_from_group() RETURNS trigger AS
$$
DECLARE
        v_group_name varchar(150);
        v_light_type int;
        v_tenant_id int;
        v_analytic_group_id int;
        v_analytic_group_by_date_id int;
        v_property_light_source int;
BEGIN

        v_tenant_id := (SELECT tenant_id FROM grouping WHERE grouping_id = OLD.grouping_id);

        -- retrieve group name. If group was deleted, grouping will not be found.
        v_group_name := (SELECT name FROM grouping WHERE grouping_id = OLD.grouping_id AND tenant_id = v_tenant_id);

       -- only do the rest if the group was found in analytics
        IF (v_group_name IS NOT NULL) THEN
                BEGIN
                        -- retrieve lamp_type
			v_light_type :=  (SELECT light_type FROM light WHERE smartpoint_id = OLD.smartpoint_id);

                        -- Use group_name to retrieve analytic_group_id.
                        v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_group_name AND tenant_id = v_tenant_id);

                        -- Get the analytic_group_by_date_id.
                        v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,OLD.create_user));

                        PERFORM upsert_analytics_installed(v_analytic_group_by_date_id, v_light_type, OLD.create_user, '-');
		END;
        END IF;

        RETURN null;
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
CREATE OR REPLACE FUNCTION upsert_analytics_consumption(p_analytic_group_by_date_id integer, p_light_type_id integer, p_create_user varchar(20), p_delta double precision ) RETURNS void AS
$$
BEGIN

        UPDATE analytics_consumption SET value = value + p_delta WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id);

        IF NOT FOUND THEN
            -- not there, so try to insert the key
            PERFORM ins_analytics_consumption(p_analytic_group_by_date_id, p_light_type_id, p_create_user, p_delta);
        END IF;
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
CREATE OR REPLACE FUNCTION ins_analytics_consumption(p_analytic_group_by_date_id integer, p_light_type_id integer, p_create_user varchar(20), p_delta double precision ) RETURNS void AS
$$
BEGIN
    INSERT INTO analytics_consumption(
            light_type_id, "value", analytic_group_by_date_id, create_user)
    VALUES (p_light_type_id, p_delta, p_analytic_group_by_date_id, p_create_user);
END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2013
-- Product       : LC
-- Description   :
-- Revision History
-- When                               Who                        What
-- -------------------  --------------------- -------------------------
-- Jan 2013                                                           Alex Barros                                           Removed property table usage
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

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_analytics_alarms(p_analytic_group_by_date_id integer, p_light_type_id integer, p_analytics_alarm_subtype_id integer, p_create_user varchar(20), p_value integer ) RETURNS void AS
$$
BEGIN
        INSERT INTO analytics_alarms(light_type_id, "value", analytic_group_by_date_id, analytics_alarm_subtype, create_user)
         VALUES (p_light_type_id, p_value, p_analytic_group_by_date_id, p_analytics_alarm_subtype_id, p_create_user);
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
CREATE OR REPLACE FUNCTION ins_analytics_warnings(p_analytic_group_by_date_id integer, p_light_type_id integer, p_analytics_warning_subtype_id integer, p_create_user varchar(20), p_value integer ) RETURNS void AS
$$
BEGIN
        INSERT INTO analytics_warnings(light_type_id, "value", analytic_group_by_date_id, analytics_warning_subtype, create_user)
    VALUES (p_light_type_id, p_value, p_analytic_group_by_date_id, p_analytics_warning_subtype_id, p_create_user);
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
CREATE OR REPLACE FUNCTION upsert_analytics_alarms(p_analytic_group_by_date_id integer, p_light_type_id integer, p_analytics_alarm_subtype_id integer, p_create_user varchar(20), p_operator char) RETURNS void AS
$$
BEGIN

    IF (p_operator = '+') THEN
        UPDATE analytics_alarms SET value = value + 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_alarm_subtype = p_analytics_alarm_subtype_id);
    END IF;

    IF (p_operator = '-') THEN
        UPDATE analytics_alarms SET value = value - 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_alarm_subtype = p_analytics_alarm_subtype_id);
    END IF;

    IF NOT FOUND THEN
        -- not there, so try to insert the key
		IF (p_operator = '+') THEN
			PERFORM ins_analytics_alarms(p_analytic_group_by_date_id, p_light_type_id, p_analytics_alarm_subtype_id, p_create_user, 1);
		ELSEIF (p_operator = '-') THEN
			PERFORM ins_analytics_alarms(p_analytic_group_by_date_id, p_light_type_id, p_analytics_alarm_subtype_id, p_create_user, -1);
		END IF;
    END IF;
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
CREATE OR REPLACE FUNCTION upsert_analytics_warnings(p_analytic_group_by_date_id integer, p_light_type_id integer, p_analytics_warning_subtype_id integer, p_create_user varchar(20), p_operator char) RETURNS void AS
$$
BEGIN
        IF (p_operator = '+') THEN
            UPDATE analytics_warnings SET value = value + 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_warning_subtype = p_analytics_warning_subtype_id);
        ELSEIF (p_operator = '-') THEN
            UPDATE analytics_warnings SET value = value - 1 WHERE (analytic_group_by_date_id = p_analytic_group_by_date_id) AND (light_type_id = p_light_type_id) AND (analytics_warning_subtype = p_analytics_warning_subtype_id);
        END IF;

        IF NOT FOUND THEN
                -- not there, so try to insert the key
			IF (p_operator = '+') THEN
				PERFORM ins_analytics_warnings(p_analytic_group_by_date_id, p_light_type_id, p_analytics_warning_subtype_id, p_create_user, 1);
			ELSEIF (p_operator = '-') THEN
				PERFORM ins_analytics_warnings(p_analytic_group_by_date_id, p_light_type_id, p_analytics_warning_subtype_id, p_create_user, -1);
			END IF;
        END IF;
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
CREATE OR REPLACE FUNCTION upd_analytics_alarms_warnings(p_status_message_id integer, p_alarm_subtype_id integer, p_create_user character varying, type character, p_operator character)
  RETURNS void AS
$$
DECLARE
        v_analytics_alarm_warning_subtype_id integer;
        v_light_id integer;
        v_analytic_group_id integer;
        v_analytic_group_by_date_id integer;
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

        v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));

        -- retrieve light_type
        v_light_type_id := (SELECT l.light_type FROM light l WHERE l.light_id = v_light_id);

        -- retrieve analytics_alarm_warning_subtype_id
        v_analytics_alarm_warning_subtype_id := (SELECT analytics_alarm_warning_subtype_id FROM analytics_alarm_warning_subtype WHERE label_key = (SELECT label_key FROM status_subtype WHERE status_subtype_id = p_alarm_subtype_id));

        IF (type = 'A') THEN
                PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
        END IF;

        IF (type = 'W') THEN
                PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator);
        END IF;

        v_refcursor := get_cursor_groups_for_light(v_light_id);

        LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;

                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name AND tenant_id = v_status_message.tenant_id);

                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));

                -- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
                IF (type = 'A') THEN
                        PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;

                IF (type = 'W') THEN
                        PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;

        END LOOP;
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
CREATE OR REPLACE FUNCTION get_analytics_trend(p_tenant_id integer, p_view_mode_id character varying, p_view_type_id character varying, p_carbon_credits_factor double precision, p_analytics_group_id integer, p_grouping_ids character varying)
  RETURNS character varying AS
$$
DECLARE
	p_init_date DATE;
	p_return CHARACTER VARYING;
	p_day_amount integer;
BEGIN
	p_return := '';

	IF (p_view_mode_id = 'week') THEN
		p_init_date := (SELECT CURRENT_DATE - INTERVAL '1 week');
		p_day_amount := 7;
	ELSIF (p_view_mode_id = 'month') THEN
		p_init_date := (SELECT CURRENT_DATE - INTERVAL '1 month');
		p_day_amount := 30;
  	END IF;

	FOR i IN 0..p_day_amount - 1  LOOP

		-- Alarms.
		IF (p_view_type_id = '1') THEN
			p_return := p_return || (SELECT get_analytics_alarms_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Warnings.
		ELSIF (p_view_type_id = '2') THEN
			p_return := p_return || (SELECT get_analytics_warnings_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Installed.
		ELSIF (p_view_type_id = '3') THEN
			p_return := p_return || (SELECT get_analytics_installed_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Consumption.
		ELSIF (p_view_type_id = '4') THEN
			p_return := p_return || (SELECT get_analytics_consumption_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Energy savings.
		ELSIF (p_view_type_id = '5') THEN
			p_return := p_return || (SELECT get_analytics_energy_savings_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, NULL, p_grouping_ids)) || ',';
		-- Carbon credits.
		ELSIF (p_view_type_id = '6') THEN
			p_return := p_return || (SELECT get_analytics_carbon_credits_total(p_tenant_id, p_analytics_group_id, p_init_date, p_init_date, p_carbon_credits_factor, NULL, p_grouping_ids)) || ',';
		END IF;

		p_init_date := (SELECT p_init_date + 1);

	END LOOP;

	p_return := (SELECT SUBSTRING(p_return FROM 0 FOR CHAR_LENGTH(p_return)));

	RETURN p_return;
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
CREATE OR REPLACE FUNCTION get_analytics_average(p_tenant_id integer, p_group_id integer, p_view_mode_id character varying, p_view_type_id character varying, p_carbon_credits_factor double precision, p_grouping_ids character varying)
  RETURNS double precision AS
$BODY$
DECLARE

  p_total float;
  p_init_date timestamp;
  p_end_date timestamp;

  p_min_date timestamp;

  p_divide_week float;
  p_divide_month float;
  p_days int;

  p_week int;
  p_month int;

BEGIN

  p_total := 0;

  -- numbers of days in one week
  p_week := 7;

  -- numbers of days in one month
  p_month := 30;

  -- default week rate when there is more than 3 months of information
  -- (3 months * 4 weeks per month) = 12
  p_divide_week := 12;

  -- default month rate when there is more than 3 months of information
  p_divide_month := 3;

  p_init_date := (SELECT CURRENT_TIMESTAMP - INTERVAL '91 days');
  p_end_date := (SELECT CURRENT_TIMESTAMP - INTERVAL '1 days');

  --alarms
  IF (p_view_type_id = '1') THEN

        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id
                                                                                                                         FROM analytics_alarms
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));

        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE
                        p_days := (SELECT p_end_date::date - p_min_date::date);
                        p_total := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
                        p_divide_week := p_days / p_week;
                        p_divide_month := p_days / p_month;
        END IF;
  --warnings
 ELSIF (p_view_type_id = '2') THEN

        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id
                                                                                                                         FROM analytics_warnings
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));

        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE
            p_days := (SELECT p_end_date::date - p_min_date::date);
            p_total := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
            p_divide_week := p_days / p_week;
            p_divide_month := p_days / p_month;
        END IF;
 --installed
  ELSIF (p_view_type_id = '3') THEN

        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id
                                                                                                                         FROM analytics_installed
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));

        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE
            p_days := (SELECT p_end_date::date - p_min_date::date);
            p_total := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
            p_divide_week := p_days / p_week;
            p_divide_month := p_days / p_month;
        END IF;

  --consumption
  ELSIF (p_view_type_id = '4') THEN

                p_min_date := ( SELECT analytic_date
                                                                  FROM analytics_group_by_date
                                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id
                                                                                                                                         FROM analytics_consumption
                                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));

        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
                  p_total := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE
          p_days := (SELECT p_end_date::date - p_min_date::date);
          p_total := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
          p_divide_week := p_days / p_week;
          p_divide_month := p_days / p_month;
        END IF;

  --energy savings
  ELSIF (p_view_type_id = '5') THEN

        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id
                                                                                                                         FROM analytics_consumption
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
        --
        -- for energy savings and carbon credits the end date must be yesterday, once ecomode proceudre did not run yet
        p_end_date := (SELECT cast(p_end_date - interval '1 day' as date));

        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_init_date,p_end_date,NULL, p_grouping_ids));
        ELSE
                        p_days := (SELECT p_end_date::date - p_min_date::date);
                        p_total := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_min_date,p_end_date,NULL, p_grouping_ids));
                        p_divide_week := p_days / p_week;
                        p_divide_month := p_days / p_month;
        END IF;

  --carbon credits
  ELSIF (p_view_type_id = '6') THEN

        p_min_date := ( SELECT analytic_date
                                                  FROM analytics_group_by_date
                                                 WHERE analytic_group_by_date_id = (SELECT analytic_group_by_date_id
                                                                                                                         FROM analytics_consumption
                                                                                                                        ORDER BY analytic_group_by_date_id limit 1));
        --
        -- for energy savings and carbon credits the end date must be yesterday, once ecomode proceudre did not run yet
        p_end_date := (SELECT cast(p_end_date - interval '1 day' as date));

        -- there is more than 3 months of information
        IF p_min_date < p_init_date then
            p_total := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_init_date,p_end_date,p_carbon_credits_factor,NULL, p_grouping_ids));
        ELSE
                        p_days := (SELECT p_end_date::date - p_min_date::date);
                        p_total := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_min_date,p_end_date,p_carbon_credits_factor,NULL, p_grouping_ids));
                        p_divide_week := p_days / p_week;
                        p_divide_month := p_days / p_month;
        END IF;

  END IF;
  IF p_divide_week = 0 THEN
    p_divide_week := 1;
  END IF;

  IF p_divide_month = 0 THEN
    p_divide_month := 1;
  END IF;

  IF (p_view_mode_id = 'week') THEN
        RETURN coalesce((p_total / p_divide_week),0);
  ELSE
    RETURN coalesce((p_total / p_divide_month),0);
  END IF;

END
$BODY$
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
CREATE OR REPLACE FUNCTION get_analytics_change(p_tenant_id integer, p_group_id integer, p_view_mode_id character varying, p_view_type_id character varying, p_carbon_credits_factor double precision, p_grouping_ids character varying)
  RETURNS double precision AS
$$
DECLARE

  p_last_period float;
  p_current_period float;
  p_total float;

  p_init_date_current timestamp;
  p_init_date_last timestamp;
  p_end_date_current timestamp;
  p_end_date_last timestamp;

BEGIN

  IF (p_view_mode_id = 'week') THEN

        p_init_date_current := (SELECT CURRENT_TIMESTAMP - INTERVAL '1 week');
        p_end_date_current := (SELECT CURRENT_TIMESTAMP );

        p_init_date_last := (SELECT p_init_date_current - INTERVAL '1 week');
        p_end_date_last := (SELECT p_end_date_current - INTERVAL '1 week');

  ELSIF (p_view_mode_id = 'month') THEN

        p_init_date_current := (SELECT CURRENT_TIMESTAMP - INTERVAL '1 month');
        p_end_date_current := (SELECT CURRENT_TIMESTAMP);

        p_init_date_last := (SELECT p_init_date_current - INTERVAL '1 month');
        p_end_date_last := (SELECT p_end_date_current - INTERVAL '1 month');

  END IF;

  --alarms
  IF (p_view_type_id = '1') THEN
    p_current_period := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_alarms_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));

  --warnings
  ELSIF (p_view_type_id = '2') THEN
    p_current_period := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_warnings_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));

  --installed
  ELSIF (p_view_type_id = '3') THEN
    p_current_period := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_installed_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));

  --consumption
  ELSIF (p_view_type_id = '4') THEN
    p_current_period := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_consumption_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));

  --energy savings
  ELSIF (p_view_type_id = '5') THEN

    --
    -- for energy savings and carbon credits the end date must be yesterday, once ecomode proceudre did not run yet
    p_init_date_current := (SELECT p_init_date_current - interval '1 day');
    p_end_date_current := (SELECT p_end_date_current - interval '1 day');

    p_init_date_last := (SELECT p_init_date_last - interval '1 day');
    p_end_date_last := (SELECT p_end_date_last - interval '1 day');

    p_current_period := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_energy_savings_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,NULL, p_grouping_ids));

  --consumption
  ELSIF (p_view_type_id = '6') THEN

      --
    -- for energy savings and carbon credits the end date must be yesterday, once ecomode proceudre did not run yet
    p_init_date_current := (SELECT p_init_date_current - interval '1 day');
    p_end_date_current := (SELECT p_end_date_current - interval '1 day');

    p_init_date_last := (SELECT p_init_date_last - interval '1 day');
    p_end_date_last := (SELECT p_end_date_last - interval '1 day');

    p_current_period := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_init_date_current,p_end_date_current, p_carbon_credits_factor,NULL, p_grouping_ids));
    p_last_period := (SELECT get_analytics_carbon_credits_total(p_tenant_id,p_group_id,p_init_date_last,p_end_date_last,p_carbon_credits_factor,NULL, p_grouping_ids));

  END IF;

  p_total := 0;
  IF (COALESCE(p_last_period,0) = 0 AND COALESCE(p_current_period,0) = 0) THEN
    p_total := 0;
  ELSIF (COALESCE(p_last_period,0) = 0 AND COALESCE(p_current_period,0) > 0) THEN
    p_total := 100;
  ELSIF (COALESCE(p_last_period,0) = 0 AND COALESCE(p_current_period,0) < 0) THEN
    p_total := -100;
  ELSE
    p_total := ((p_current_period - p_last_period) / p_last_period) * 100;
  END IF;

  RETURN p_total;

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
CREATE OR REPLACE FUNCTION get_analytics_warnings_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp with time zone, p_end_date timestamp with time zone, p_alarm_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
BEGIN

  IF(p_grouping_ids IS NULL) THEN

	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	  RETURN
	   (SELECT COALESCE(SUM(aw.value),0)         AS amount
		  FROM analytics_warnings                aw
			  ,analytics_group_by_date           agbd
			  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(p_alarm_type_id,aw.analytics_warning_subtype)
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 2))
		   );
  ELSE

 	  RETURN
	   (SELECT COALESCE(SUM(aw.value),0)         AS amount
		  FROM analytics_warnings                aw
			  ,analytics_group_by_date           agbd
			  ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(p_alarm_type_id,aw.analytics_warning_subtype)
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 2))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		   );

    END IF;
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
CREATE OR REPLACE FUNCTION get_analytics_installed_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp with time zone, p_end_date timestamp with time zone, p_light_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
BEGIN

  IF(p_grouping_ids IS NULL) THEN

	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	RETURN
	   (SELECT COALESCE(SUM(ai.value),0)       AS amount
		  FROM analytics_installed             ai
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = p_group_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date              BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id  = ai.analytic_group_by_date_id
		   AND ai.light_type_id                = COALESCE(p_light_type_id, ai.light_type_id)
		   );

  ELSE

	RETURN
	   (SELECT COALESCE(SUM(ai.value),0)       AS amount
		  FROM analytics_installed             ai
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date              BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id  = ai.analytic_group_by_date_id
		   AND ai.light_type_id                = COALESCE(p_light_type_id, ai.light_type_id)
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		   );

  END IF;
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
CREATE OR REPLACE FUNCTION get_analytics_consumption_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp with time zone, p_end_date timestamp with time zone, p_light_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
BEGIN

  IF(p_grouping_ids IS NULL) THEN

	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	RETURN
	   (SELECT COALESCE(SUM(ac.value),0)       AS amount
		  FROM analytics_consumption           ac
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = p_group_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date 			   BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id  = ac.analytic_group_by_date_id
		   AND ac.light_type_id                = COALESCE(p_light_type_id, ac.light_type_id)
	   );

  ELSE

	RETURN
	   (SELECT COALESCE(SUM(ac.value),0)       AS amount
		  FROM analytics_consumption           ac
			  ,analytics_group_by_date         agbd
			  ,analytics_group                 ag
		 WHERE ag.tenant_id                    = p_tenant_id
		   AND ag.analytic_group_id            = agbd.analytic_group_id
		   AND agbd.analytic_date 			   BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id  = ac.analytic_group_by_date_id
		   AND ac.light_type_id                = COALESCE(p_light_type_id, ac.light_type_id)
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
	   );

  END IF;

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
CREATE OR REPLACE FUNCTION get_analytics_carbon_credits_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp without time zone, p_end_date timestamp without time zone, p_carbon_credits_factor double precision, p_light_type_id integer, p_grouping_ids character varying)
  RETURNS real AS
$$
DECLARE

 p_v_energy_saved double precision;
 p_v_result double precision;

BEGIN

  p_v_energy_saved := (get_analytics_energy_savings_total(p_tenant_id , p_group_id , p_init_date , p_end_date , p_grouping_ids));

  IF (p_v_energy_saved > 0) THEN

     p_v_result :=  (p_v_energy_saved * p_carbon_credits_factor);

  END IF;

  RETURN  p_v_result;

END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : Add analytics_group 'All' when a new tenant is inserted
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_analytics_grouping_ins_tenant()
RETURNS trigger AS
$$
BEGIN
    PERFORM  ins_analytics_group('All', NEW.tenant_id, NEW.create_user, NULL);
    RETURN null;
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
CREATE OR REPLACE FUNCTION trigger_analytics_grouping_del_grouping()
RETURNS trigger AS
$$
DECLARE
    v_analytic_group_id integer;
    v_analytic_group_by_date_id integer;
BEGIN
        -- retrieve analytics_grouping via name and tenant
         v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = OLD.name AND tenant_id = OLD.tenant_id);

        if (v_analytic_group_id is not null) then

            -- get the analytic_group_by_date_id.
            v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,OLD.create_user));

            -- try updating analytics installed to 0. If not found ok.
            UPDATE analytics_installed SET value = 0 WHERE (analytic_group_by_date_id = v_analytic_group_by_date_id) AND (light_type_id = (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'INDUCTION'));
            UPDATE analytics_installed SET value = 0 WHERE (analytic_group_by_date_id = v_analytic_group_by_date_id) AND (light_type_id = (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'LED'));
            UPDATE analytics_installed SET value = 0 WHERE (analytic_group_by_date_id = v_analytic_group_by_date_id) AND (light_type_id = (SELECT light_type_id FROM analytics_light_type WHERE light_type_name = 'OTHER'));

        END IF;

        RETURN null;

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
CREATE OR REPLACE FUNCTION upd_map_center(p_tenant_id integer)
  RETURNS void AS
$$
DECLARE
        v_middle_lat FLOAT;
		v_middle_long FLOAT;
		v_lat_max FLOAT;
        v_lat_min FLOAT;
		v_lon_max FLOAT;
		v_lon_min FLOAT;
BEGIN

	SELECT MAX(loc.latitude)
		,MIN(loc.latitude)
		,MAX(loc.longitude)
		,MIN(loc.longitude)
	INTO v_lat_max, v_lat_min, v_lon_max, v_lon_min
	FROM light l
	LEFT JOIN light_location loc ON (l.light_id = loc.light_id)
	WHERE l.tenant_id = p_tenant_id;


	v_middle_lat  := cast((cast(v_lat_max as numeric) + cast(v_lat_min as numeric) ) / 2 as character varying);
	v_middle_long := cast((cast(v_lon_max as numeric) + cast(v_lon_min as numeric) ) / 2 as character varying);

	PERFORM upsert_tenant_lat_long(p_tenant_id, v_middle_lat, v_middle_long);

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
CREATE OR REPLACE FUNCTION upsert_tenant_lat_long(p_tenant_id integer, p_latitude double precision, p_longitude double precision)
  RETURNS void AS
$$
DECLARE
v_usid integer;
BEGIN

        UPDATE tenant SET latitude = p_latitude,longitude = p_longitude
        WHERE tenant_id = p_tenant_id;

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
CREATE OR REPLACE FUNCTION get_cursor_for_tenant()
  RETURNS refcursor AS
$$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT tenant_id FROM tenant order by tenant_id;
    RETURN ref;
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
CREATE OR REPLACE FUNCTION get_average_light_consumption(p_light_id integer, p_initial_date timestamp with time zone, p_end_date timestamp with time zone)
RETURNS real AS
$$
DECLARE

    p_type_consumption int;
    p_date_time timestamp with time zone;
    finalConsumption float4;
    initialConsumption float4;

BEGIN

    p_type_consumption := 1;

    -- consumption for the last 24hr
    if (p_initial_date = p_end_date) then

        p_date_time := CAST( CURRENT_TIMESTAMP - CAST('1 day' AS interval) AS timestamp);

    -- consumption for the last month
    else

        p_date_time := CAST( CURRENT_TIMESTAMP - CAST('1 month' AS interval) AS timestamp);

    END IF;


    initialConsumption := ( SELECT coalesce(odv.value,0) AS endValue
                              FROM operational_data_value odv
                                  ,status_message sm
                             WHERE sm.light_id           = p_light_id
                               AND sm.message_date      >= p_date_time
                               AND odv.status_message_id = sm.status_message_id
                               AND odv.operational_data_type_id = p_type_consumption
                             ORDER BY odv.create_date LIMIT 1);

    finalConsumption := (   SELECT coalesce(odv.value,0) AS endValue
                              FROM operational_data_value odv
                                  ,status_message sm
                             WHERE sm.light_id           = p_light_id
                               AND sm.message_date      >= p_date_time
                               AND odv.status_message_id = sm.status_message_id
                               AND odv.operational_data_type_id = p_type_consumption
                             ORDER BY odv.create_date DESC LIMIT 1);

    RETURN finalConsumption - initialConsumption;

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
CREATE OR REPLACE FUNCTION trigger_update_light_status()
  RETURNS trigger AS
$$
BEGIN
    UPDATE light
    SET light_status = NEW.status_id
    ,modify_user = NEW.create_user
    WHERE light_id = NEW.light_id;

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
DROP FUNCTION IF EXISTS get_smartpoints_by_group_list_to_map(character varying, integer, double precision, double precision, double precision, double precision, integer);
CREATE OR REPLACE FUNCTION get_smartpoints_by_group_list_to_map(IN p_groupids character varying, IN p_tenant_id integer, IN p_bottomleftlat double precision, IN p_bottomleftlon double precision, IN p_toprightlat double precision, IN p_toprightlon double precision, IN p_maxdevicecount integer)
  RETURNS TABLE(light_id integer, flexnet_id integer, latitude double precision, longitude double precision, light_status integer) AS
$$
DECLARE

        p_sectors int;
        p_middleLatMin float;
        p_middleLatMax float;
        p_middleLonMin float;
        p_middleLonMax float;

BEGIN
        p_sectors := 5;

 IF p_groupids IS NOT NULL THEN

     IF(
	(SELECT COUNT(l.smartpoint_id)
	   FROM light l
	   JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
	   JOIN light_location ll on (l.light_id = ll.light_id)
          WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
            AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
	    AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon) > p_maxDeviceCount) AND (p_maxDeviceCount != 0) THEN

	p_maxDeviceCount := (p_maxDeviceCount / p_sectors);

	-- 20% Lat Asc
	RETURN QUERY
	SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
	  FROM light l
	  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
	  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
	  JOIN light_location ll ON (l.light_id = ll.light_id)
	 WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
	   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
	   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
	 ORDER BY ll.latitude ASC LIMIT p_maxDeviceCount;

	-- 20% Lat Desc
	RETURN QUERY
	SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
	  FROM light l
	  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
	  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
	  JOIN light_location ll ON (l.light_id = ll.light_id)
	 WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
	   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
	   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
	 ORDER BY ll.latitude DESC LIMIT p_maxDeviceCount;

	-- 20% Long Asc
	RETURN QUERY
	SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
	  FROM light l
	  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
	  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
	  JOIN light_location ll ON (l.light_id = ll.light_id)
	 WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
	   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
	   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
         ORDER BY ll.longitude ASC LIMIT p_maxDeviceCount;

	-- 20% Long Desc
	RETURN QUERY
	SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
	  FROM light l
	  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
	  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
	  JOIN light_location ll ON (l.light_id = ll.light_id)
	 WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
	   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
	   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
	 ORDER BY ll.longitude DESC LIMIT p_maxDeviceCount;

	-- 20% Middle Points

	-- Min Lat
	p_middleLatMin :=  (SELECT MIN(ll.latitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Max Lat
	p_middleLatMax :=  (SELECT MAX(ll.latitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Min Long
	p_middleLonMin :=  (SELECT MIN(ll.longitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Max Long
	p_middleLonMax :=   (SELECT MAX(ll.longitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Insert Middle Points

	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
		   AND ll.latitude BETWEEN p_middleLatMin AND p_middleLatMax
		   AND ll.longitude BETWEEN p_middleLonMin AND p_middleLonMax
	         LIMIT p_maxDeviceCount;
	ELSE
	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE smg.grouping_id IN (SELECT get_group_list_by_tenant(p_groupids, p_tenant_id))
		   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
		   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		 ORDER BY smp.rni_id ASC;
     END IF;
 ELSE
    IF (
	(SELECT COUNT(l.smartpoint_id)
	   FROM light l
	   JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
	   JOIN light_location ll on (l.light_id = ll.light_id)
          WHERE l.tenant_id = p_tenant_id
            AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
	    AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon) > p_maxDeviceCount) AND (p_maxDeviceCount != 0) THEN

	p_maxDeviceCount := (p_maxDeviceCount / p_sectors);

	-- 20% Lat Asc
	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE l.tenant_id = p_tenant_id
		   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
		   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		 ORDER BY ll.latitude ASC LIMIT p_maxDeviceCount;

	-- 20% Lat Desc
	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE l.tenant_id = p_tenant_id
		   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
		   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		 ORDER BY ll.latitude DESC LIMIT p_maxDeviceCount;

	-- 20% Long Asc
	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE l.tenant_id = p_tenant_id
		   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
		   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		 ORDER BY ll.longitude ASC LIMIT p_maxDeviceCount;

	-- 20% Long Desc
	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE l.tenant_id = p_tenant_id
		   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
		   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		 ORDER BY ll.longitude DESC LIMIT p_maxDeviceCount;

	-- 20% Middle Points

	-- Min Lat
	p_middleLatMin :=  (SELECT MIN(ll.latitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE l.tenant_id = p_tenant_id
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Max Lat
	p_middleLatMax :=   (SELECT MAX(ll.latitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE l.tenant_id = p_tenant_id
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Min Long
	p_middleLonMin :=   (SELECT MIN(ll.longitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE l.tenant_id = p_tenant_id
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Max Long
	p_middleLonMax :=   (SELECT MAX(ll.longitude)
			      FROM light l
			      JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
			      JOIN light_location ll on (l.light_id = ll.light_id)
			     WHERE l.tenant_id = p_tenant_id
			       AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			       AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

	-- Insert Middle Points

	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE l.tenant_id = p_tenant_id
		   AND ll.latitude BETWEEN p_middleLatMin AND p_middleLatMax
		   AND ll.longitude BETWEEN p_middleLonMin AND p_middleLonMax;
	ELSE
	RETURN QUERY
		SELECT l.light_id, smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN smartpoint_grouping smg on (l.smartpoint_id = smg.smartpoint_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		  JOIN light_location ll ON (l.light_id = ll.light_id)
		 WHERE l.tenant_id = p_tenant_id
		   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
		   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		 ORDER BY smp.rni_id ASC;
    END IF;
 END IF;
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
DROP FUNCTION IF EXISTS get_smartpoints_by_schedule_to_map(integer, double precision, double precision, double precision, double precision, integer);
CREATE OR REPLACE FUNCTION get_smartpoints_by_schedule_to_map(IN p_schedule_id integer, IN p_bottomleftlat double precision, IN p_bottomleftlon double precision, IN p_toprightlat double precision, IN p_toprightlon double precision, IN p_maxdevicecount integer)
  RETURNS TABLE(light_id integer, flexnet_id integer, latitude double precision, longitude double precision, light_status integer) AS
$$
DECLARE

        p_sectors int;
        p_middleLatMin float;
        p_middleLatMax float;
        p_middleLonMin float;
        p_middleLonMax float;

BEGIN
        p_sectors := 5;

        IF ((SELECT COUNT(l.smartpoint_id)
	       FROM light l
	       JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
	       JOIN light_location ll on (l.light_id = ll.light_id)
	      WHERE sm.schedule_id = p_schedule_id
	        AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
	        AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon)
		     > p_maxDeviceCount) AND (p_maxDeviceCount != 0) THEN

		p_maxDeviceCount := (p_maxDeviceCount / p_sectors);

		-- 20% Lat Asc
		RETURN QUERY
			SELECT l.light_id,smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
			  FROM light l
			  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
			  JOIN light_location ll on (l.light_id = ll.light_id)
		          JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
			 WHERE sm.schedule_id = p_schedule_id
			   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		         ORDER BY ll.latitude ASC LIMIT p_maxDeviceCount;

		-- 20% Lat Desc
		RETURN QUERY
			SELECT l.light_id,smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
			  FROM light l
			  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
			  JOIN light_location ll on (l.light_id = ll.light_id)
		          JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
			 WHERE sm.schedule_id = p_schedule_id
			   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
			ORDER BY ll.latitude DESC LIMIT p_maxDeviceCount;

		-- 20% Long Asc
		RETURN QUERY
			SELECT l.light_id,smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
			  FROM light l
			  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
			  JOIN light_location ll on (l.light_id = ll.light_id)
		          JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
			 WHERE sm.schedule_id = p_schedule_id
			   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		         ORDER BY ll.longitude ASC LIMIT p_maxDeviceCount;

		-- 20% Long Desc
		RETURN QUERY
			SELECT l.light_id,smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
			  FROM light l
			  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
			  JOIN light_location ll on (l.light_id = ll.light_id)
		          JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
			 WHERE sm.schedule_id = p_schedule_id
			   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
			   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		         ORDER BY ll.longitude DESC LIMIT p_maxDeviceCount;


		-- 20% Middle Points

		  -- Min Lat
		  p_middleLatMin :=  (SELECT MIN(ll.latitude)
					  FROM light l
					  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
					  JOIN light_location ll on (l.light_id = ll.light_id)
					  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
					 WHERE sm.schedule_id = p_schedule_id
					   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
					   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);



		  -- Max Lat
		  p_middleLatMax :=   (SELECT MAX(ll.latitude)
					  FROM light l
					  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
					  JOIN light_location ll on (l.light_id = ll.light_id)
					  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
					 WHERE sm.schedule_id = p_schedule_id
					   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
					   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);
		  -- Min Long
		   p_middleLonMin :=   (SELECT MIN(ll.longitude)
					  FROM light l
					  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
					  JOIN light_location ll on (l.light_id = ll.light_id)
					  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
					 WHERE sm.schedule_id = p_schedule_id
					   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
					   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);
		  -- Max Long
		   p_middleLonMax :=   (SELECT MAX(ll.longitude)
					  FROM light l
					  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
					  JOIN light_location ll on (l.light_id = ll.light_id)
					  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
					 WHERE sm.schedule_id = p_schedule_id
					   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
					   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon);

		  -- Insert Middle Points


		 -- INSERT INTO meter_list
		RETURN QUERY
			SELECT l.light_id,smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
			  FROM light l
			  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
			  JOIN light_location ll on (l.light_id = ll.light_id)
		          JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
			 WHERE sm.schedule_id = p_schedule_id
			   AND ll.latitude BETWEEN p_middleLatMin AND p_middleLatMax
			   AND ll.longitude BETWEEN p_middleLonMin AND p_middleLonMax
		         LIMIT p_maxDeviceCount;

  ELSE

        RETURN QUERY
		SELECT l.light_id,smp.rni_id, ll.latitude, ll.longitude, l.current_status_message_id
		  FROM light l
		  JOIN schedule_membership sm on (l.smartpoint_id = sm.smartpoint_id)
		  JOIN light_location ll on (l.light_id = ll.light_id)
		  JOIN smartpoint smp ON (l.smartpoint_id = smp.smartpoint_id)
		 WHERE sm.schedule_id = p_schedule_id
		   AND ll.latitude BETWEEN p_bottomLeftLat AND p_topRightLat
		   AND ll.longitude BETWEEN p_bottomLeftLon AND p_topRightLon
		ORDER BY smp.rni_id ASC;

  END IF;

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
CREATE OR REPLACE FUNCTION upd_schedule_center(p_schedule_id integer)
  RETURNS void AS
$$
DECLARE
    v_lat_max FLOAT;
    v_lat_min FLOAT;
    v_lon_max FLOAT;
    v_lon_min FLOAT;
    v_middle_lat FLOAT;
    v_middle_long FLOAT;
BEGIN

    SELECT MAX(loc.latitude),MIN(loc.latitude),MAX(loc.longitude),MIN(loc.longitude)
    INTO v_lat_max, v_lat_min, v_lon_max, v_lon_min
    FROM  light
    INNER JOIN light_location loc ON (light.light_id = loc.light_id)
    INNER JOIN status_message ON (status_message.light_id = light.light_id)
    INNER JOIN vw_current_message current_status_message ON (status_message.status_message_id = current_status_message.message_id)
    LEFT OUTER JOIN schedule_membership sch_m on sch_m.smartpoint_id = light.smartpoint_id
    LEFT OUTER JOIN schedule sch on sch_m.schedule_id = sch.schedule_id
    WHERE sch.schedule_id = p_schedule_id;

    v_middle_lat  := cast((cast(v_lat_max as numeric) + cast(v_lat_min as numeric) ) / 2 as character varying);
    v_middle_long := cast((cast(v_lon_max as numeric) + cast(v_lon_min as numeric) ) / 2 as character varying);

    UPDATE schedule
       SET longitude = v_middle_long,
           latitude = v_middle_lat
     WHERE schedule_id = p_schedule_id;

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
CREATE OR REPLACE FUNCTION del_light(p_rni_id integer)RETURNS character varying AS
$$
DECLARE
    v_smartpoint_id integer;
    v_light_id integer;
BEGIN

    SELECT s.smartpoint_id
          ,l.light_id
      FROM smartpoint s
          ,light l
     WHERE s.rni_id = p_rni_id
       AND s.smartpoint_id = l.smartpoint_id
      INTO v_smartpoint_id
          ,v_light_id;

    IF (v_light_id is null) THEN
        RETURN 'light_not_found';
    ELSE

		DELETE FROM current_alarm_warning_message WHERE light_id = v_light_id;

        DELETE FROM operational_data_value WHERE status_message_id in (SELECT status_message_id FROM status_message WHERE light_id = v_light_id);

        DELETE FROM status_message_status_subtype WHERE status_message_id in (SELECT status_message_id FROM status_message WHERE light_id = v_light_id);

        DELETE FROM status_message WHERE light_id = v_light_id;

        DELETE FROM light_schedule WHERE light_id = v_light_id;

        DELETE FROM light_location WHERE light_id = v_light_id;

        DELETE FROM light_last_operational_data WHERE light_id = v_light_id;

        DELETE FROM light_configuration WHERE light_id = v_light_id;

        DELETE FROM light_daily_consumption WHERE light_id = v_light_id;

        DELETE FROM light WHERE smartpoint_id = v_smartpoint_id;

        DELETE FROM schedule_membership WHERE smartpoint_id = v_smartpoint_id;

        DELETE FROM smartpoint_process WHERE smartpoint_id = v_smartpoint_id;

        ALTER TABLE smartpoint_grouping disable TRIGGER analytics_installed_del_smp_FROM_group_TRIGGER;

        DELETE FROM smartpoint_grouping WHERE smartpoint_id = v_smartpoint_id;

        ALTER TABLE smartpoint_grouping enable TRIGGER analytics_installed_del_smp_FROM_group_TRIGGER;

        DELETE FROM smartpoint_tag WHERE smartpoint_id = v_smartpoint_id;

        DELETE FROM smartpoint WHERE rni_id = p_rni_id;

        RETURN 'success';

    END IF;

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
CREATE OR REPLACE FUNCTION trigger_update_light_status_subtype()
  RETURNS trigger AS
$$
DECLARE

  p_rni_id integer;

BEGIN

    p_rni_id := (SELECT s.rni_id
                   from smartpoint s
                       ,light l
                       ,status_message sm
                  where sm.status_message_id = NEW.status_message_id
                    and sm.light_id          = l.light_id
                    and l.smartpoint_id      = s.smartpoint_id);

	UPDATE light
	SET light_status_subtype = NEW.status_subtype_id
	,modify_user = NEW.create_user
	FROM smartpoint
	WHERE light.smartpoint_id = smartpoint.smartpoint_id
	AND smartpoint.rni_id = p_rni_id;

    RETURN NEW;

END;
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION split(IN p_vartext character varying, IN p_delimiter character varying)
	RETURNS TABLE(list_integer integer) AS
$$
DECLARE
  p_count int;
  p_str character varying;
BEGIN
  p_count := 0;


    IF EXISTS(SELECT * FROM information_schema.tables WHERE table_name = 'split_foo') THEN

	DROP TABLE split_foo;

    END IF;

    CREATE TEMP TABLE split_foo
    (
	int_value  integer
    );

   LOOP
    p_count := p_count + 1;
    p_str := (SELECT split_part(p_vartext , p_delimiter, p_count));
    EXIT WHEN p_str = '';
    INSERT INTO split_foo SELECT CAST(split_part(p_vartext, ',', p_count) AS integer);
   END LOOP;

  RETURN QUERY
  SELECT int_value FROM split_foo;

END
$$
LANGUAGE plpgsql;
 ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_user(p_tenant_id integer, p_user_name character varying, p_password character varying, p_first_name character varying, p_last_name character varying, p_email character varying, p_all_lights_auth boolean, p_create_user character varying)
  RETURNS integer AS
$$
DECLARE
    id int;
BEGIN
       INSERT INTO users(
            username
            ,tenant_id
            , password
            , first_name
            , last_name
            , email
            , enabled
            , all_lights_auth
            , create_date
            , create_user)
       VALUES
           (p_user_name
           , p_tenant_id
           , p_password
           , p_first_name
           , p_last_name
           , p_email
           , TRUE
           , p_all_lights_auth
           , CURRENT_TIMESTAMP
           , p_create_user)
           RETURNING user_id INTO id;

       INSERT INTO user_settings(
              user_id
			, tenant_id
            , create_date
            , create_user)
       VALUES
	   ( id
	   , p_tenant_id
	   , CURRENT_TIMESTAMP
       , p_create_user);

	RETURN id;

END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_group_to_user(IN p_user_id integer, IN p_group_id integer, IN p_create_user character varying)
  RETURNS integer AS
$$

BEGIN
      INSERT INTO users_grouping(
		user_id
		, grouping_id
		, create_user
		, create_date)
	VALUES (p_user_id
		, p_group_id
		, p_create_user
		, CURRENT_TIMESTAMP);

		RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_role_to_user(p_user_id integer, p_authotity_id character varying)
  RETURNS integer AS
$$

BEGIN

	INSERT INTO authorities(
        	user_id
		, authority
		)
	VALUES (p_user_id
		, p_authotity_id
		);

		RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_user(p_user_id integer, p_tenant_id integer)
  RETURNS integer AS
$$

BEGIN

	UPDATE users SET enabled = FALSE
	 WHERE user_id = p_user_id
	   AND tenant_id = p_tenant_id;

	RETURN 1;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_groups_by_user(p_user_id integer)
  RETURNS void AS
$$
BEGIN

    DELETE FROM users_grouping
           WHERE user_id = p_user_id;

END
$$
  LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_roles_by_user(p_user_id integer)
  RETURNS void AS
$$
BEGIN

    DELETE FROM authorities
           WHERE user_id = p_user_id;



END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_columns_to_user(p_user_id integer, p_column_value character varying, p_display_order integer, p_create_user character varying, p_tenant_id integer)
  RETURNS void AS
$$
DECLARE
	v_property_id integer;
	v_user_settings_id integer;
	id integer;
BEGIN
	v_property_id := 104;

	v_user_settings_id := (SELECT user_settings_id FROM user_settings WHERE tenant_id = p_tenant_id AND user_id = p_user_id);

	IF(v_user_settings_id IS NULL) THEN

		INSERT INTO user_settings(tenant_id, create_date, create_user, user_id)
		     VALUES (p_tenant_id, CURRENT_TIMESTAMP, p_create_user , p_user_id)
		  RETURNING user_settings_id INTO id;


		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (id, v_property_id,CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);

	ELSE

		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (v_user_settings_id, v_property_id, CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);

	END IF;


END;
$$
  LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ins_filters_to_user(p_user_id integer, p_column_value character varying, p_display_order integer, p_create_user character varying, p_tenant_id integer)
  RETURNS void AS
$$
DECLARE
	v_property_id integer;
	v_user_settings_id integer;
	id integer;
BEGIN
	v_property_id := 105;

	v_user_settings_id := (SELECT user_settings_id FROM user_settings WHERE tenant_id = p_tenant_id AND user_id = p_user_id);

	IF(v_user_settings_id IS NULL) THEN

		INSERT INTO user_settings(tenant_id, create_date, create_user, user_id)
		     VALUES (p_tenant_id, CURRENT_TIMESTAMP, p_create_user , p_user_id)
		  RETURNING user_settings_id INTO id;

		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (id, v_property_id,CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);
	ELSE

		INSERT INTO user_settings_property(user_settings_id, property_id, create_date, create_user, user_setting_property_value, display_order)
		     VALUES (v_user_settings_id, v_property_id, CURRENT_TIMESTAMP, p_create_user , p_column_value, p_display_order);

	END IF;


END;
$$
  LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_column_to_user(p_tenant_id integer, p_user_id integer)
  RETURNS void AS
$$
DECLARE
 v_column_property INTEGER;
BEGIN
    v_column_property := 104;

    DELETE FROM user_settings_property
    WHERE property_id = v_column_property
      AND user_settings_id = (SELECT user_settings_id FROM user_settings WHERE user_id = p_user_id AND tenant_id = p_tenant_id);
END
$$
  LANGUAGE plpgsql;

  ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION del_filter_to_user(p_tenant_id integer, p_user_id integer)
  RETURNS void AS
$$
DECLARE
 v_column_property INTEGER;
BEGIN
    v_column_property := 105;

    DELETE FROM user_settings_property
    WHERE property_id = v_column_property
      AND user_settings_id = (SELECT user_settings_id FROM user_settings WHERE user_id = p_user_id AND tenant_id = p_tenant_id );
END
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_group_list_by_tenant(IN p_groupids character varying, IN p_tenant_id integer)
  RETURNS TABLE(grouping_id integer) AS
$$
DECLARE
BEGIN

   IF (p_groupids IS NULL) THEN

      RETURN QUERY
	    SELECT g.grouping_id FROM grouping g WHERE g.tenant_id = p_tenant_id;

   ELSE

      RETURN QUERY
        SELECT list_integer AS grouping_id FROM split(p_groupids,',');

   END IF;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upd_analytics_alarms_warnings_by_light(p_tenant_id integer, p_light_id integer, p_alarm_subtype_id integer, p_create_user character varying, type character, p_operator character)
  RETURNS void AS
$$
DECLARE
        v_analytics_alarm_warning_subtype_id integer;
        v_analytic_group_id integer;
        v_analytic_group_by_date_id integer;
        v_light_type_id integer;
        v_refcursor refcursor;
        v_record RECORD;
BEGIN

        -- retrieve analytic_group_by_date_id for Group = 'All' and tenant in question
        v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND tenant_id = p_tenant_id);

        v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));

        -- retrieve light_type
        v_light_type_id := (SELECT l.light_type FROM light l WHERE l.light_id = p_light_id);

        -- retrieve analytics_alarm_warning_subtype_id
        v_analytics_alarm_warning_subtype_id := (SELECT analytics_alarm_warning_subtype_id FROM analytics_alarm_warning_subtype WHERE label_key = (SELECT label_key FROM status_subtype WHERE status_subtype_id = p_alarm_subtype_id));

        IF (type = 'A') THEN
                PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
        END IF;

        IF (type = 'W') THEN
                PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator);
        END IF;

        v_refcursor := get_cursor_groups_for_light(p_light_id);

        LOOP
                FETCH v_refcursor INTO v_record;
                EXIT WHEN NOT FOUND;

                -- Use group_name to retrieve analytic_group_id.
                v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = v_record.name AND tenant_id = p_tenant_id);

                -- Get the analytic_group_by_date_id.
                v_analytic_group_by_date_id := (SELECT get_analytics_group_by_date_pk(v_analytic_group_id, CURRENT_TIMESTAMP,p_create_user));

                -- Add 1 to count for type = lamp_type for current_date, Group=<current_group>
                IF (type = 'A') THEN
                        PERFORM upsert_analytics_alarms(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;

                IF (type = 'W') THEN
                        PERFORM upsert_analytics_warnings(v_analytic_group_by_date_id, v_light_type_id, v_analytics_alarm_warning_subtype_id, p_create_user, p_operator );
                 END IF;

        END LOOP;
END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_analytics_warnings_by_type(IN p_tenant_id integer, IN p_group_id integer, IN p_init_date timestamp with time zone, IN p_end_date timestamp with time zone, IN p_alarm_type_id integer, IN p_grouping_ids character varying)
  RETURNS TABLE(status_subtype integer, amount bigint) AS
$$
BEGIN

  IF(p_grouping_ids IS NULL) THEN

	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	  RETURN QUERY
	   (SELECT aw.analytics_warning_subtype, COALESCE(SUM(aw.value),0) AS amount
		  FROM analytics_warnings                aw
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(aw.analytics_warning_subtype)
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id FROM status_subtype WHERE status_id = 2))
		 GROUP BY  aw.analytics_warning_subtype);
 ELSE
 	  RETURN QUERY
	   (SELECT aw.analytics_warning_subtype, COALESCE(SUM(aw.value),0) AS amount
		  FROM analytics_warnings                aw
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aw.analytic_group_by_date_id
		   AND aw.analytics_warning_subtype         = COALESCE(aw.analytics_warning_subtype)
		   AND (aw.analytics_warning_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 2))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		 GROUP BY  aw.analytics_warning_subtype);

    END IF;
END
$$
  LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_analytics_alarms_by_type(IN p_tenant_id integer, IN p_group_id integer, IN p_init_date timestamp with time zone, IN p_end_date timestamp with time zone, IN p_alarm_type_id integer, IN p_grouping_ids character varying)
  RETURNS TABLE(status_subtype integer, amount bigint) AS
$$
BEGIN

  IF(p_grouping_ids IS NULL) THEN

	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	RETURN QUERY
	       (SELECT aa.analytics_alarm_subtype, COALESCE(SUM(aa.value),0) AS amount
		  FROM analytics_alarms                  aa
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = p_group_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(p_alarm_type_id,aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 1))
		 GROUP BY aa.analytics_alarm_subtype);
 ELSE
	RETURN QUERY
	       (SELECT aa.analytics_alarm_subtype, COALESCE(SUM(aa.value),0) AS amount
		  FROM analytics_alarms                  aa
		       ,analytics_group_by_date           agbd
		       ,analytics_group                   ag
		 WHERE ag.tenant_id                         = p_tenant_id
		   AND ag.analytic_group_id                 = agbd.analytic_group_id
		   AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
		   AND agbd.analytic_group_by_date_id       = aa.analytic_group_by_date_id
		   AND aa.analytics_alarm_subtype           = COALESCE(aa.analytics_alarm_subtype)
		   AND (aa.analytics_alarm_subtype IN (SELECT status_subtype_id from status_subtype where status_id = 1))
		   AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
		 GROUP BY  aa.analytics_alarm_subtype);
  END IF;
END
$$
  LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_last_operational_data_value(p_light_id integer, p_data_type_id integer)
  RETURNS double precision AS
$BODY$
BEGIN
    RETURN (
    SELECT odv.value
	FROM operational_data_value odv
	INNER JOIN status_message sm ON odv.status_message_id = sm.status_message_id
	WHERE sm.light_id = p_light_id
	AND odv.operational_data_type_id = p_data_type_id
	ORDER BY odv.create_date DESC LIMIT 1);
END
$BODY$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsert_ecomode(p_tenant_id integer, p_rni_id integer, p_consumption_day date, p_calculate_retroactive_ecomode boolean, p_ecomode double precision, p_light_type integer, p_wattage_value double precision, p_baseline_value double precision)
  RETURNS integer AS
$$
DECLARE
	num_rows int;
BEGIN

   IF p_rni_id IS NOT NULL AND p_consumption_day IS NOT NULL THEN

      UPDATE light_daily_consumption AS ldc
         SET ecomode = p_ecomode,
	     ecomode_baseline = p_baseline_value
        FROM light AS l JOIN
	     smartpoint AS smt ON l.smartpoint_id = smt.smartpoint_id
       WHERE ldc.light_id = l.light_id
         AND ldc.consumption_day = p_consumption_day
         AND smt.rni_id = p_rni_id;

   END IF;

   UPDATE light AS l
      SET ecomode = p_ecomode,
          ecomode_replaced_type = p_light_type,
          ecomode_replaced_wattage = p_wattage_value,
          calculate_retroactive_ecomode = p_calculate_retroactive_ecomode
     FROM smartpoint smt
    WHERE l.smartpoint_id = smt.smartpoint_id
      AND l.tenant_id = p_tenant_id
      AND smt.rni_id = p_rni_id;

    IF FOUND THEN
        GET DIAGNOSTICS num_rows = ROW_COUNT;
    ELSE
        num_rows = -1;
    END IF;

    RETURN num_rows;
END;
$$
  LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2012
-- Product       : LC
-- Description   :
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION calculate_analytics_ecomode()
  RETURNS character varying AS
$$
DECLARE
    p_cursor_tenant refcursor;
    p_rec_tenant RECORD;

    p_user CHARACTER VARYING;
    p_date TIMESTAMP;

BEGIN
    -- Defining procedure constants.
    p_user := 'sysuser';
    p_date := CURRENT_TIMESTAMP;

    -- Gets a cursor for all tenants.
    p_cursor_tenant := get_cursor_for_tenant();

    -- Cleaning analytics_ecomode table.
    DELETE FROM analytics_ecomode;

    -- Looping all tenants.
    LOOP FETCH p_cursor_tenant INTO p_rec_tenant;
    EXIT WHEN NOT FOUND;
	-- Inserting average consumption of light by group in analytics ecomode
	INSERT INTO analytics_ecomode (analytic_group_by_date_id, ecomode_percent, ecomode_measured, ecomode_baseline, create_user, create_date)
	select get_analytics_group_by_date_pk(analytic_group_id, consumption_day, p_user)
	  ,round(cast(cast(ecomode_percent as real) as numeric),3) as ecomode_percent
      ,round(cast(cast(ecomode_measured as real) as numeric),3) as ecomode_measured
	  ,round(cast(cast(ecomode_baseline as real) as numeric),3) as ecomode_baseline
	  ,p_user
	  ,p_date
	from(
	     select ag.analytic_group_id
				,ldc.consumption_day
				,sum(ldc.consumption) as ecomode_measured
				,avg(coalesce(ldc.ecomode,0)) as ecomode_percent
				,sum(coalesce(ldc.ecomode_baseline,0)) as ecomode_baseline
		 from light_daily_consumption ldc
			inner join light l on (l.light_id = ldc.light_id)
			inner join smartpoint s on(s.smartpoint_id = l.smartpoint_id)
			inner join smartpoint_grouping sg on(sg.smartpoint_id = s.smartpoint_id)
			inner join analytics_group ag on (ag.grouping_id = sg.grouping_id)
		where ag.tenant_id = p_rec_tenant.tenant_id
		   AND ldc.ecomode IS NOT NULL
		group by ag.analytic_group_id, ldc.consumption_day
	    ) ls;
	-- Inserting average consumption of light by tenant in analytics ecomode
	INSERT INTO analytics_ecomode (analytic_group_by_date_id, ecomode_percent, ecomode_measured, ecomode_baseline, create_user, create_date)
	select get_analytics_group_by_date_pk(analytic_group_id, consumption_day, p_user)
	  ,round(cast(cast(ecomode_percent as real) as numeric),3) as ecomode_percent
      ,round(cast(cast(ecomode_measured as real) as numeric),3) as ecomode_measured
	  ,round(cast(cast(ecomode_baseline as real) as numeric),3) as ecomode_baseline
	  ,p_user
	  ,p_date
	from(
	     select ag.analytic_group_id
				,ldc.consumption_day
				,sum(ldc.consumption) as ecomode_measured
				,avg(coalesce(ldc.ecomode,0)) as ecomode_percent
				,sum(coalesce(ldc.ecomode_baseline,0)) as ecomode_baseline
	     from light_daily_consumption ldc
			inner join light l on (l.light_id = ldc.light_id)
			inner join smartpoint s on(s.smartpoint_id = l.smartpoint_id)
			inner join analytics_group ag on (ag.tenant_id = s.tenant_id and analytic_group_name like'All%')
	     where ag.tenant_id = p_rec_tenant.tenant_id
	       AND ldc.ecomode IS NOT NULL
	     group by ag.analytic_group_id, ldc.consumption_day
	    ) ls;
    END LOOP;

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
CREATE OR REPLACE FUNCTION get_analytics_eco_mode_total(p_tenant_id integer, p_group_id integer, p_init_date timestamp with time zone, p_end_date timestamp with time zone, p_grouping_ids character varying)
  RETURNS real AS
$$
BEGIN

  IF(p_grouping_ids IS NULL) THEN

	IF(p_group_id IS NULL OR p_group_id = 1) THEN
		p_group_id := (SELECT analytic_group_id from analytics_group where analytic_group_name = 'All' AND tenant_id = p_tenant_id);
	END IF;

	RETURN
	   (SELECT avg(ecomode_percent) as ecomode_percent
	       FROM analytics_ecomode ae
	       INNER JOIN analytics_group_by_date agbd on (agbd.analytic_group_by_date_id = ae.analytic_group_by_date_id)
	       INNER JOIN analytics_group ag on (ag.analytic_group_id = agbd.analytic_group_id)
	    WHERE ag.tenant_id = p_tenant_id
	      AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
	      AND ag.analytic_group_id = p_group_id
	   );
  ELSE

	RETURN
	   (SELECT avg(ecomode_percent) as ecomode_percent
	       FROM analytics_ecomode ae
	       INNER JOIN analytics_group_by_date agbd on (agbd.analytic_group_by_date_id = ae.analytic_group_by_date_id)
	       INNER JOIN analytics_group ag on (ag.analytic_group_id = agbd.analytic_group_id)
	    WHERE ag.tenant_id = p_tenant_id
	      AND agbd.analytic_date BETWEEN p_init_date AND p_end_date
              AND (ag.grouping_id IN (SELECT * FROM split(p_grouping_ids,',')))
	   );
  END IF;
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
CREATE OR REPLACE FUNCTION ins_light_daily_consumption(p_light_id integer, p_delta double precision)
  RETURNS void AS
$$
BEGIN

	   INSERT INTO light_daily_consumption(
		        light_id
			,consumption_day
			,consumption)
		VALUES(
			p_light_id
			,date_trunc('day',current_timestamp)
			,p_delta);


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
CREATE OR REPLACE FUNCTION upsert_light_daily_consumption(p_light_id integer, p_delta double precision)
  RETURNS void AS
$$
DECLARE

    v_consumption double precision;

BEGIN

   IF p_delta IS NOT NULL AND p_delta > 0 THEN

	    v_consumption:= (select consumption from light_daily_consumption where light_id = p_light_id AND (date_trunc('day',current_timestamp) = consumption_day));

        UPDATE light_daily_consumption SET consumption = (v_consumption + p_delta) WHERE light_id = p_light_id AND  (date_trunc('day',current_timestamp) = consumption_day);

	    IF NOT FOUND THEN
		    -- not there, so try to insert the key
		    PERFORM ins_light_daily_consumption(p_light_id, p_delta);
	  END IF;

   END IF;

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

CREATE OR REPLACE FUNCTION get_analytics_carbon_credits_by_date(IN p_date_type_id character varying, IN p_tenant_id integer, IN p_group_id integer, IN p_init_date timestamp with time zone, IN p_end_date timestamp with time zone, IN p_carbon_credits_factor double precision, IN p_barrels_of_oil_factor double precision, IN p_metric_of_co_factor double precision, IN p_grouping_ids character varying)
  RETURNS TABLE(description character varying, date_time timestamp with time zone, amount numeric) AS
$$
BEGIN
   RETURN QUERY
       (

	SELECT 'sensus.mlc.conservation.credits_created'::character varying AS description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time,
			COALESCE(get_analytics_carbon_credits_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_carbon_credits_factor,NULL,p_grouping_ids),0)::NUMERIC AS amount
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time

	UNION

	SELECT 'sensus.mlc.conservation.energy_saved'::character varying AS  description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time,
			COALESCE(get_analytics_energy_savings_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_grouping_ids),0)::NUMERIC AS amount
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time

	UNION

	SELECT 'sensus.mlc.conservation.barrels_oil_saved'::character varying AS  description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time,
			COALESCE(get_analytics_carbon_credits_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_barrels_of_oil_factor,NULL,p_grouping_ids),0)::NUMERIC AS amount
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time

	UNION

	SELECT 'sensus.mlc.conservation.metric_tons_co'::character varying AS  description,
	       result.date_time::TIMESTAMP WITH TIME ZONE,
	       SUM(result.amount) AS amount
	  FROM (
		SELECT  CASE WHEN (p_end_date::DATE - p_init_date::DATE) > 30 THEN date_trunc('month',vw_dt.date)::date
			     WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN  date_trunc('hour',vw_dt.date)::timestamp
			     ELSE date_trunc('day',vw_dt.date)::date
			END AS date_time,
			COALESCE(get_analytics_carbon_credits_total(p_tenant_id, p_group_id,vw_dt.date::TIMESTAMP, (vw_dt.date:: date || ' 23:59:59')::TIMESTAMP,p_metric_of_co_factor,NULL,p_grouping_ids),0)::NUMERIC AS amount
		  FROM (
			SELECT CASE WHEN (p_end_date::TIMESTAMP WITH TIME ZONE - p_init_date::TIMESTAMP WITH TIME ZONE) = '23:59:59' THEN generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 hour')
				    ELSE generate_series(p_init_date::TIMESTAMP WITH TIME ZONE, p_end_date::TIMESTAMP WITH TIME ZONE, INTERVAL '1 day')
				END AS date
			) vw_dt
	       ) result
	GROUP BY result.date_time

	ORDER BY 2,1
       );
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
CREATE OR REPLACE FUNCTION upd_light_calculation_retroactive_ecomode(p_light_id integer, p_calculate_retroactive_ecomode boolean)
  RETURNS void AS
$$
BEGIN

  UPDATE light
     SET calculate_retroactive_ecomode = p_calculate_retroactive_ecomode
  WHERE light_id = p_light_id;

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
CREATE OR REPLACE FUNCTION del_dashboard_resume(p_tenant_id integer)
  RETURNS void AS
$$
BEGIN

     -- Cleaning dashboard_resume table.
    DELETE FROM dashboard_resume  WHERE tenant_id = p_tenant_id;

      -- Cleaning dashboard_resume_chart table.
    DELETE FROM dashboard_resume_chart  WHERE tenant_id = p_tenant_id;

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
CREATE OR REPLACE FUNCTION get_all_groups_by_tenant_for_dashboard(p_tenant_id integer)
  RETURNS TABLE(group_id integer) AS
$$
BEGIN
	RETURN QUERY
   SELECT ag.grouping_id group_id FROM analytics_group ag WHERE ag.tenant_id = p_tenant_id and ag.grouping_id is not null;

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
CREATE FUNCTION trigger_insert_min_max()
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

CREATE FUNCTION trigger_modified_min_max()
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


