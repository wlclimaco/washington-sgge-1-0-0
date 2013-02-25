CREATE VIEW vw_group_smartpoint_count AS SELECT grouping.grouping_id, 
                        grouping.name, 
                        grouping.description, 
                        grouping.create_date, 
                        grouping.latitude, 
                        grouping.longitude, 
                        grouping.tenant_id, 
                        ( SELECT COUNT(*) AS count
                                FROM smartpoint_grouping
                                INNER JOIN smartpoint ON smartpoint.smartpoint_id = smartpoint_grouping.smartpoint_id AND smartpoint.tenant_id = grouping.tenant_id
                                WHERE smartpoint_grouping.grouping_id =  grouping.grouping_id) AS smartpoint_count
                                FROM grouping;
CREATE VIEW vw_schedule_smartpoint_count AS SELECT schedule.schedule_id, 
                                                        schedule.name, 
                                                        schedule.description, 
                                                        schedule.schedule_type, 
                                                        schedule.create_date, 
                                                        schedule.sunrise_offset, 
                                                        schedule.sunset_offset, 
                                                        schedule.tenant_id, 
                                                        schedule.latitude, 
                                                        schedule.longitude, 
                                                        ( SELECT count(*) AS count
                                                                FROM schedule_membership
                                                                WHERE schedule_membership.schedule_id = schedule.schedule_id) AS smartpoint_count
   FROM schedule;
CREATE VIEW vw_process_smartpoint_count AS SELECT     process_id, description, lc_action_description, start_datetime, end_datetime, rni_correlation_id, is_submitted, lc_action_id,  
                    tenant_id, parent_process_id, is_monitored_instance, estimated_seconds_to_complete, create_user, create_date, 
                      is_process_complete, is_first_level,
                          (SELECT     COUNT(*)
                            FROM          smartpoint_process
                            WHERE      (process_id = process.process_id)) AS smartpoint_count
        FROM         process;
CREATE VIEW vw_current_message AS SELECT status_message.light_id, max(status_message.status_message_id) AS message_id
   FROM status_message
   GROUP BY status_message.light_id;
CREATE VIEW vw_light_property AS SELECT result1.light_id, 
                result1.city_name,
                result1.color_temperature, 
                result1.date_added, 
                result1.date_installed, 
                result1.firmware_version, 
                result1.input_voltage_range, 
                result1.lamp_type, 
                result1.latitude, 
                result1.longitude, 
                result1.manufacturer, 
                result1.pole_id, 
                result1.sensus_part_number, 
                result1.serial_number, 
                result1.street_name,
                result1.sunrise_offset, 
                result1.sunrise_time, 
                result1.sunset_offset, 
                result1.sunset_time, 
                result1.wattage_rating,
                result1.zip_code,
                result1.housing,
                result1.dimmable
   FROM   crosstab
                (
                'SELECT   t1.light_id, 
                                 t3.property_name, 
                                 t2.property_value
                FROM      light AS t1 
                JOIN         light_property AS t2 ON t1.light_id = t2.light_id 
                JOIN         property AS t3 ON t2.property_id = t3.property_id ORDER BY t1.light_id'::text
                , 
                'SELECT  property_name 
                FROM property 
                WHERE property_name IN (''CITY_NAME'',
                                                                         ''COLOR_TEMPERATURE'', 
                                                                        ''DATE_ADDED'', 
                                                                        ''DATE_INSTALLED'',
                                                                        ''FIRMWARE_VERSION'', 
                                                                        ''INPUT_VOLTAGE_RANGE'', 
                                                                        ''LAMP_TYPE'', 
                                                                        ''LATITUDE'',
                                                                        ''LONGITUDE'',
                                                                        ''MANUFACTURER'', 
                                                                        ''POLE_ID'', 
                                                                        ''SENSUS_PART_NUMBER'', 
                                                                        ''SERIAL_NUMBER'',
                                                                        ''STREET_NAME'', 
                                                                        ''SUNRISE_OFFSET'',
                                                                        ''SUNRISE_TIME'', 
                                                                        ''SUNSET_OFFSET'', 
                                                                        ''SUNSET_TIME'', 
                                                                        ''WATTAGE_RATING'',
                                                                        ''ZIP_CODE'',
                                                                        ''HOUSING'',
                                                                        ''DIMMABLE'') 
                                ORDER BY property_name'::text
                )
                result1(light_id integer, 
                                                city_name character varying(4094), 
                                                color_temperature character varying(4094), 
                                                date_added character varying(4094), 
                                                date_installed character varying(4094), 
                                                dimmable character varying(4094), 
                                                firmware_version character varying(4094), 
                                                housing character varying(4094), 
                                                input_voltage_range character varying(4094), 
                                                lamp_type character varying(4094), 
                                                latitude character varying(4094), 
                                                longitude character varying(4094), 
                                                manufacturer character varying(4094), 
                                                pole_id character varying(4094), 
                                                sensus_part_number character varying(4094), 
                                                serial_number character varying(4094), 
                                                street_name character varying(4094), 
                                                sunrise_offset character varying(4094), 
                                                sunrise_time character varying(4094), 
                                                sunset_offset character varying(4094), 
                                                sunset_time character varying(4094), 
                                                wattage_rating character varying(4094), 
                                                zip_code character varying(4094)
                                );
CREATE VIEW vw_maps AS SELECT          smp.smartpoint_id AS SMARTPOINT_ID, 
                smp.smartpoint_type AS SMARTPOINT_TYPE,
                smp.rni_id AS RNI_ID, 
                smp.rni_id::text AS rni_cast,
                smp_grp.grouping_id AS GROUPING_ID, 
                smp_tag.tag_id AS TAG_ID,
                sch_m.schedule_id AS SCHEDULE_ID,
                sch.schedule_type AS SCHEDULE_TYPE,
                light.tenant_id AS TENANT_ID, 
                light.light_id AS LIGHT_ID, 
                light.protected,
                status_message.status_id AS CURRENT_STATUS, 
                color_temperature, 
                date_added, 
                date_installed, 
                firmware_version,         
                input_voltage_range, 
                pvt.lamp_type, 
                pvt.latitude, 
                pvt.longitude, 
                manufacturer, 
                pole_id, 
                sensus_part_number, 
                serial_number, 
                pvt.sunrise_offset, 
                sunrise_time, 
                pvt.sunset_offset, 
                sunset_time, 
                pvt.wattage_rating, 
                pvt.city_name,
                pvt.street_name,
                pvt.zip_code,
                pvt.dimmable,
                pvt.housing
FROM       
               vw_light_property AS pvt,                       
               vw_current_message AS current_status_message,
               light AS light, 
                status_message AS status_message,
                smartpoint AS smp
                LEFT OUTER JOIN smartpoint_tag AS smp_tag on smp_tag.smartpoint_id = smp.smartpoint_id
                LEFT OUTER JOIN smartpoint_grouping AS smp_grp on smp_grp.smartpoint_id = smp.smartpoint_id
                LEFT OUTER JOIN schedule_membership AS sch_m on sch_m.smartpoint_id = smp.smartpoint_id
                LEFT OUTER JOIN schedule as sch on sch_m.schedule_id = sch.schedule_id  
                                                                                                
WHERE   pvt.light_id = light.light_id AND 
                status_message.light_id = light.light_id AND 
                light.smartpoint_id = smp.smartpoint_id AND 
                status_message.status_message_id = current_status_message.message_id;