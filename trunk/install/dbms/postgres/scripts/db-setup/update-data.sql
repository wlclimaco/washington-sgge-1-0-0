 -------------------------------------------------------------------------------------------------------------------------
 -- VERSION 1.1.0
 -------------------------------------------------------------------------------------------------------------------------

DELETE FROM sensus_part_number_configuration WHERE sensus_part_number_id = 13;

DELETE FROM sensus_part_number WHERE sensus_part_number_id = 13;

UPDATE sensus_part_number_configuration SET hardware_setting=4, current_scale=12, percentage=20 WHERE sensus_part_number_configuration_id = 14;

UPDATE sensus_part_number_configuration SET hardware_setting=4, current_scale=12, percentage=20 WHERE sensus_part_number_configuration_id = 32;

UPDATE sensus_part_number_configuration SET hardware_setting=4, current_scale=12, percentage=20 WHERE sensus_part_number_configuration_id = 2;

UPDATE sensus_part_number_configuration SET hardware_setting=4, current_scale=12, percentage=20 WHERE sensus_part_number_configuration_id = 8;

UPDATE sensus_part_number_configuration SET hardware_setting=4, current_scale=12, percentage=20 WHERE sensus_part_number_configuration_id = 20;

UPDATE sensus_part_number_configuration SET hardware_setting=4, current_scale=12, percentage=20 WHERE sensus_part_number_configuration_id = 26;

UPDATE sensus_part_number_configuration SET hardware_setting=4, current_scale=12, percentage=20 WHERE sensus_part_number_configuration_id = 62;

UPDATE sensus_part_number_configuration SET hardware_setting=3, current_scale=40, percentage=50 WHERE sensus_part_number_configuration_id = 39;

UPDATE sensus_part_number_configuration SET hardware_setting=2, current_scale=46, percentage=70 WHERE sensus_part_number_configuration_id = 41;

UPDATE sensus_part_number_configuration SET hardware_setting=3, current_scale=40, percentage=50 WHERE sensus_part_number_configuration_id = 51;

UPDATE sensus_part_number_configuration SET hardware_setting=2, current_scale=46, percentage=70 WHERE sensus_part_number_configuration_id = 53;

UPDATE sensus_part_number_configuration SET hardware_setting=11, current_scale=37, percentage=50 WHERE sensus_part_number_configuration_id = 45;

UPDATE sensus_part_number_configuration SET hardware_setting=11, current_scale=37, percentage=50 WHERE sensus_part_number_configuration_id = 57;

UPDATE sensus_part_number_configuration SET hardware_setting=11, current_scale=37, percentage=50 WHERE sensus_part_number_configuration_id = 69;

INSERT INTO status_subtype
           (status_subtype_id
           ,status_id
		   ,label_key
		   ,create_user)
     VALUES
	    (8,2,'sensus.mlc.status_subtype.communicationfail','sysuser');


INSERT INTO property
           (property_name
		   ,label_key
		   ,data_type
		   ,create_user
		   ,property_class
		   ,has_valid_values)
	VALUES
		 ('CURRENT_ALARM_WARNING_STATUS_SUBTYPE','sensus.mlc.property.current_alarm_warning_status_subtype',1,'sysuser',0,'N')--82
		,('AC_VOLTAGE_MIN','sensus.mlc.property.ac_voltage_min',1,'sysuser',0,'N')--83 
		,('AC_VOLTAGE_MAX','sensus.mlc.property.ac_voltage_max',1,'sysuser',0,'N')--84 
		,('AC_CURRENT_MIN','sensus.mlc.property.ac_current_min',1,'sysuser',0,'N')--85 
		,('AC_CURRENT_MAX','sensus.mlc.property.ac_current_max',1,'sysuser',0,'N')--86 
		,('CONSUMPTION','sensus.mlc.property.comsumption',1,'sysuser',0,'N')--87
		,('CONSUMPTION_MIN','sensus.mlc.property.comsumption_min',1,'sysuser',0,'N')--88 
		,('CONSUMPTION_MAX','sensus.mlc.property.comsumption_max',1,'sysuser',0,'N')--89
		,('PAGE_SIZE','sensus.mlc.property.page_size',1,'sysuser',0,'N')--90
		,('DATE_ADDED_AFTER','sensus.mlc.property.date_added_after',1,'sysuser',0,'N')--91
		,('DATE_ADDED_BEFORE','sensus.mlc.property.date_added_before',1,'sysuser',0,'N')--92
		,('SORT','sensus.mlc.property.sort',1,'sysuser',0,'N')--93
		,('ALL_GROUPS','sensus.mlc.property.all_groups',1,'sysuser',0,'N')--94
		,('ALL_STATUS','sensus.mlc.property.all_status',1,'sysuser',0,'N')--95
		,('ALL_ALARMS','sensus.mlc.property.all_alarms',1,'sysuser',0,'N')--96
		,('ALL_WARNINGS','sensus.mlc.property.all_warnings',1,'sysuser',0,'N')--97
		,('ALL_EVENTS','sensus.mlc.property.all_events',1,'sysuser',0,'N')--98
		,('ALL_OFFSETS','sensus.mlc.property.all_offsets',1,'sysuser',0,'N')--99
		,('ALL_TAGS','sensus.mlc.property.all_tags',1,'sysuser',0,'N')--100
		,('PAGE_SIZE_SHOW_DIALOG','sensus.mlc.property.page_size_show_dialog',1,'sysuser',0,'N')--101
		,('USER_ID','sensus.mlc.property.user_id',2,'sysuser',0,'N')  		--102
		,('USER_NAME','sensus.mlc.property.user_name',1,'sysuser',0,'N') 	--103
		,('SMARTPOINT_COLUMN','sensus.mlc.property.smartpoint_column',1,'sysuser',0,'N') 	--104
		,('SMARTPOINT_FILTER','sensus.mlc.property.smartpoint_filter',1,'sysuser',0,'N') 	--105
		,('ORDER_BY','sensus.mlc.property.order_by',1,'sysuser',0,'N') 		--106
		,('LIGHT_BLINK','sensus.mlc.property.light_blink',2,'sysuser',0,'N') 		--107
		,('OVERRIDE','sensus.mlc.property.override',2,'sysuser',0,'N') 		--108
		,('OVERRIDE_PER_DATE','sensus.mlc.property.override_per_date',2,'sysuser',0,'N') 		--109
		,('ACTIVE','sensus.mlc.property.active',2,'sysuser',0,'N') --110
        ,('DEACTIVATED','sensus.mlc.property.deactivated',2,'sysuser',0,'N') --111
        ,('MAINTENANCE','sensus.mlc.property.maintenance',2,'sysuser',0,'N') --112
        ,('PAGE_SIZE_LIST','sensus.mlc.property.page_size_list',1,'sysuser',0,'N') --113		
        ,('ECO_MODE','sensus.mlc.property.eco_mode',1,'sysuser',0,'N') --114	