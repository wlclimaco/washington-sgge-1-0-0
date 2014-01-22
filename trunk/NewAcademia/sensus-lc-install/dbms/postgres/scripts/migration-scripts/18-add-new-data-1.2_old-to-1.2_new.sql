/* Add new data values to property tables *********** */

INSERT INTO property ( property_name
                       ,label_key
                       ,data_type
                       ,create_user
                       ,property_class
                       ,has_valid_values
                     )
     VALUES
	     ('PROCESS_ID','sensus.mlc.property.process id',1,'sysuser',0,'N' )
        ,('LIFECYCLE_STATE','sensus.mlc.property.lifecycle state',1,'sysuser',0,'N' )
        ,('ALERTS','sensus.mlc.property.alerts',1,'sysuser',0,'N' )
        ,('SUBSCRIBE_ALARM_BOARD_FAILURE', 'sensus.mlc.property.alarm_board_failure', 1, 'sysuser', 0, 'N')
        ,('SUBSCRIBE_ALARM_LAMP_FAILURE', 'sensus.mlc.property.alarm_lamp_failure', 1, 'sysuser', 0, 'N')
        ,('SUBSCRIBE_ALARM_POWER_FAILURE', 'sensus.mlc.property.alarm_power_failure', 1, 'sysuser', 0, 'N')
		,('SUBSCRIBE_WARN_BROWNOUT_DETECTED', 'sensus.mlc.property.warning_brownout_detected', 1, 'sysuser', 0, 'N')
		,('SUBSCRIBE_WARN_COMMN_FAIL', 'sensus.mlc.property.warning_commn_fail', 1, 'sysuser', 0, 'N')
		,('SUBSCRIBE_WARN_POWER_SURGE', 'sensus.mlc.property.warning_power_surge', 1, 'sysuser', 0, 'N')
		,('SUBSCRIBE_WARN_LIGHT_QUALITY', 'sensus.mlc.property.warning_light_quality', 1, 'sysuser', 0, 'N')
		,('SHOW_SUBSCRIPTION_DIALOG', 'sensus.mlc.property.show_subscription_dialog', 1, 'sysuser', 0, 'N')
        ,('SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE', 'sensus.mlc.property.alarm_metrology_com_failure', 1, 'sysuser', 0, 'N') --127
        ,('SUBSCRIBE_ALARM_METROLOGY_ERROR', 'sensus.mlc.property.alarm_metrology_error', 1, 'sysuser', 0, 'N') --128
        ,('SUBSCRIBE_WARN_HIGH_CURRENT', 'sensus.mlc.property.warning_high_current', 1, 'sysuser', 0, 'N') --129
        ,('SUBSCRIBE_WARN_LOW_CURRENT', 'sensus.mlc.property.warning_low_current', 1, 'sysuser', 0, 'N') --130
        ,('SUBSCRIBE_WARN_METROLOGY_RESET', 'sensus.mlc.property.warning_metrology_reset', 1, 'sysuser', 0, 'N') --131
        ,('SUBSCRIBE_WARN_REVERSE_ENERGY', 'sensus.mlc.property.warning_reverse_energy', 1, 'sysuser', 0, 'N'); --132

/* ************************************************** */


/* Configure 'SHOW_SUBSCRIPTION_DIALOG' to user ***** */

INSERT INTO user_settings_property( user_settings_id
                                   ,property_id
                                   ,create_date
                                   ,create_user
                                   ,user_setting_property_value)
SELECT us.user_settings_id
       , 126
       , CURRENT_TIMESTAMP::timestamp without time zone
       , 'System'
       , 'true'
  FROM users u
       JOIN user_settings us on (u.user_id = us.user_id or us.user_id IS NULL)
       JOIN user_settings_property usp on us.user_settings_id = usp.user_settings_id
   GROUP BY us.user_settings_id
   ORDER BY us.user_settings_id;


/* ************************************************** */


/* Configure 'API CONTROL' to tenants *************** */

INSERT INTO api_control( tenant_id, count, first_date)
SELECT tenant_id,     0,      now() FROM tenant ORDER BY tenant_id;


/* ************************************************** */


/* Update tenant *************** */

UPDATE TENANT SET number_api_access_hour = 10;

ALTER TABLE tenant
   ALTER COLUMN number_api_access_hour SET DEFAULT 10;