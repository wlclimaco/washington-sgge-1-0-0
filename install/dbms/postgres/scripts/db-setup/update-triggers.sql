------------------------------------------------------------------------------
DROP TRIGGER analytics_installed_add_lamp_type_trigger ON light_property;

CREATE TRIGGER ins_light_property_trigger
  AFTER INSERT
  ON light_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_ins_light_property();

------------------------------------------------------------------------------  
DROP TRIGGER analytics_installed_upd_lamp_type_trigger ON light_property;

CREATE TRIGGER upd_light_property_trigger
  AFTER UPDATE
  ON light_property
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_upd_light_property();
  
------------------------------------------------------------------------------

CREATE TRIGGER status_message_status_subtype_trigger
  AFTER INSERT
  ON status_message_status_subtype
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_update_light_status_subtype();
 ------------------------------------------------------------------------------
 
 DROP TRIGGER status_message_upd_trigger ON status_message;
 
 ---------------------------------------------  
-- users
---------------------------------------------  
CREATE TRIGGER users_ins_trigger BEFORE INSERT ON users
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date();
  
CREATE TRIGGER users_upd_trigger BEFORE UPDATE ON users
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_modified_date();
---------------------------------------------  
-- operational_data_value   
---------------------------------------------  
 DROP TRIGGER operational_data_value_ins_trigger ON operational_data_value;
  
CREATE TRIGGER operational_data_value_ins_trigger
  BEFORE INSERT
  ON operational_data_value
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_create_date(); 
  