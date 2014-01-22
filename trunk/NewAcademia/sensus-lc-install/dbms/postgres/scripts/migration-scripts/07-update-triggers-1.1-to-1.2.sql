DROP TRIGGER IF EXISTS ins_light_property_trigger ON light_property;
DROP TRIGGER IF EXISTS upd_light_property_trigger ON light_property;
DROP TRIGGER IF EXISTS light_property_ins_trigger ON light_property;
DROP TRIGGER IF EXISTS light_property_upd_trigger ON light_property;
DROP TRIGGER IF EXISTS property_valid_value_ins_trigger ON property_valid_value;
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
  ON light
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_upd_light();

---------------------------------------------  
-- light_schedule
---------------------------------------------  
  CREATE TRIGGER light_schedule_upd_trigger
  BEFORE UPDATE
  ON light_schedule
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();
  
---------------------------------------------  
-- light_location
---------------------------------------------  
  CREATE TRIGGER light_location_upd_trigger
  BEFORE UPDATE
  ON light_location
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
  CREATE TRIGGER light_filter_value_upd_trigger
  BEFORE UPDATE
  ON light_filter_value
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();
  