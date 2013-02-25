DROP VIEW vw_maps;
DROP VIEW vw_group_smartpoint_count;
DROP VIEW vw_schedule_smartpoint_count;

   ------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------
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
   
   
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW vw_maps AS SELECT          smp.smartpoint_id AS SMARTPOINT_ID, 
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
	
	
------------------------------------------------------------------------------------------------------------------------------------------------
-- Copyright     : Sensus 2011
-- Product       : LC
-- Description   : 
-- Revision History
-- When            Who              What
-- -------------------  --------------------- -------------------------
--
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW vw_group_smartpoint_count AS SELECT grouping.grouping_id, 
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