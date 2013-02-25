DELETE FROM tag;
DELETE FROM property_class;
DELETE FROM operator;
DELETE FROM operational_data_type;
DELETE FROM status;
DELETE FROM status_subtype;
DELETE FROM day_of_week;
DELETE FROM lc_action;
DELETE FROM failure;
DELETE FROM action_category;
DELETE FROM action_category_lc_action;
DELETE FROM schedule;
DELETE FROM property_valid_value;
DELETE FROM property;
DELETE FROM analytics_light_type;
DELETE FROM analytics_alarm_warning_subtype;
DELETE FROM analytics_group;
DELETE FROM analytics_group_by_date;
DELETE FROM analytics_installed;
DELETE FROM analytics_consumption;
DELETE FROM analytics_alarms;
DELETE FROM analytics_warnings;

DELETE FROM tenant;
SELECT SETVAL('tenant_tenant_id_seq',1,false);

DELETE FROM users;
DELETE FROM user_settings_property;
DELETE FROM user_settings;

DELETE FROM sensus_part_number_configuration;
DELETE FROM sensus_part_number;

INSERT INTO tenant
           (name
           ,description
           ,rni_code
		   ,server_name
		   ,gateway_rni_location
		   ,create_user
		   ,latitude
		   ,longitude
		   ,min_smartpoint_comm_time
		   ,light_time_zone
		   ,ecomode_disable
		   ,batch_process_time)
    VALUES ('acme','ACME Corporation LC Demo','ACME','acme-app.davis.sensus.lab','http://mallet-web.davis.sensus.lab/mlc-ws/mlc-ws/','QAT',35.823483,-78.8255621,3, 'US/Eastern',false, 9);
	

INSERT INTO analytics_light_type(
            light_type_id, 
			light_type_name,
			label_key,
			create_user)
    VALUES 
		(1, 'INDUCTION','sensus.mlc.lamp_type.induction','sysuser'),
		(2, 'LED','sensus.mlc.lamp_type.led','sysuser'),
		(3, 'OTHER','sensus.mlc.lamp_type.other','sysuser')
;

	INSERT INTO tag
           (name
           ,auto_group
		   ,create_user
		   ,address_related)
    VALUES
		('Incomplete Data',false,'sysuser','Y')
;

INSERT INTO property_class
           (property_class_id
           ,property_class_name
		   ,property_class_description
           ,create_user)
     VALUES
		 (0,'Generic','','sysuser'),
		 (1,'Light Detail','','sysuser'),
		 (2,'Light List  ','','sysuser')
 ;
 
 INSERT INTO operator
           (operator_id
           ,operator_code
		   ,operator_description
           ,create_user)
     VALUES
		(1,'EQ','Equal','sysuser')
 ;

INSERT INTO operational_data_type
           (operational_data_type_id
           ,label_key
           ,unit_of_measurement
           ,create_user)
     VALUES
	   (1,'sensus.mlc.operational_data_type.cumulative_consumption','kWh','sysuser'),
	   (2,'sensus.mlc.operational_data_type.current','mA','sysuser'),
	   (3,'sensus.mlc.operational_data_type.voltage','V','sysuser')
;

INSERT INTO status
           (status_id
           ,label_key
		   ,create_user)
     VALUES
	   (0,'sensus.mlc.status.maintenance','sysuser'),
	   (1,'sensus.mlc.status.alarm','sysuser'),
	   (2,'sensus.mlc.status.warning','sysuser'),
	   (3,'sensus.mlc.status.active','sysuser'),
	   (4,'sensus.mlc.status.idle','sysuser') 	   
;

INSERT INTO status_subtype
           (status_subtype_id
           ,status_id
		   ,label_key
		   ,create_user)
     VALUES
	   (1,1,'sensus.mlc.status_subtype.lampfailure','sysuser'),
	   (2,1,'sensus.mlc.status_subtype.powerfailure','sysuser'),
	   (3,1,'sensus.mlc.status_subtype.boardfailure','sysuser'),
       (4,1,'sensus.mlc.status_subtype.metrologyerror','sysuser'),
       (5,1,'sensus.mlc.status_subtype.metrologycomfailure','sysuser'),
	   (6,2,'sensus.mlc.status_subtype.powersurgedetected','sysuser'),
	   (7,2,'sensus.mlc.status_subtype.brownoutdetected','sysuser'),
	   (8,2,'sensus.mlc.status_subtype.communicationfail','sysuser'),
	   (9,2,'sensus.mlc.status_subtype.highcurrent','sysuser'),
       (10,2,'sensus.mlc.status_subtype.lowcurrent','sysuser'),
       (11,2,'sensus.mlc.status_subtype.reverseenergy','sysuser'),
       (12,2,'sensus.mlc.status_subtype.metrologyreset','sysuser')
;

INSERT INTO analytics_eco_mode_type
           (eco_mode_id
            ,eco_mode_type_name
            ,label_key
            ,create_user
            ,create_date) 
       VALUES 
          (1,'ECOMODE_MEASURED','sensus.mlc.ecomode_consumption.measured','sysuser',CURRENT_TIMESTAMP),
          (2,'ECOMODE_BASELINE','sensus.mlc.ecomode_consumption.baseline','sysuser',CURRENT_TIMESTAMP);


INSERT INTO analytics_alarm_warning_subtype(
            analytics_alarm_warning_subtype_id, 
			label_key,
			create_user)
    VALUES 
		(1,'sensus.mlc.status_subtype.lampfailure','sysuser'),
		(2,'sensus.mlc.status_subtype.powerfailure','sysuser'),
		(3,'sensus.mlc.status_subtype.boardfailure','sysuser'),
		(4,'sensus.mlc.status_subtype.metrologyerror','sysuser'),
		(5,'sensus.mlc.status_subtype.metrologycomfailure','sysuser'),
		(6,'sensus.mlc.status_subtype.powersurgedetected','sysuser'),
		(7,'sensus.mlc.status_subtype.brownoutdetected','sysuser'),
		(8,'sensus.mlc.status_subtype.communicationfail','sysuser'),
		(9,'sensus.mlc.status_subtype.highcurrent','sysuser'),
		(10,'sensus.mlc.status_subtype.lowcurrent','sysuser'),
		(11,'sensus.mlc.status_subtype.reverseenergy','sysuser'),
		(12,'sensus.mlc.status_subtype.metrologyreset','sysuser')
;



INSERT INTO day_of_week
           (day_of_week_id
           ,name)
     VALUES
	  (0,'Sunday'),
	  (1,'Monday'),
	  (2,'Tuesday'),
	  (3,'Wednesday'),
	  (4,'Thursday'),
	  (5,'Friday'),
	  (6,'Saturday')
;

INSERT INTO failure
           (failure_id
           ,label_key
           ,lc_action_id)
     VALUES
		(1,'sensus.lc.reason.light_protected',null)
	   ,(2,'sensus.lc.reason.rni_sync_failure',null)
	   ,(3,'sensus.lc.reason.rni_async_failure',null)
	   ,(4,'sensus.lc.reason.aborted',null)
	   ,(5,'sensus.lc.reason.light_desactivated',null)
	   ,(6,'sensus.lc.reason.light_in_maintenance',null)
	   ,(7,'sensus.lc.reason.light_in_failure',null)
	   ,(8,'sensus.lc.reason.light_with_max_group_allowed',null)
	   ,(9,'sensus.lc.reason.light_belong_group',null)
;

INSERT INTO lc_action(
            lc_action_id
			,description
			,create_user)
     VALUES
		 (1, 'ADD_SMP_TO_GRP', 'sysuser')
		,(2, 'DEL_SMP_FROM_GRP', 'sysuser')
		,(3, 'SET_INTENSITY_BY_GRP', 'sysuser')
		,(4, 'SET_INTENSITY_BY_LIGHT', 'sysuser')
		,(5, 'DEL_GRP', 'sysuser')
		,(6, 'SET_PROTECTED', 'sysuser')
		,(7, 'GET_LIGHT_STATUS', 'sysuser')
		,(8, 'ABORT', 'sysuser')
		,(9, 'DEL_SCHEDULE', 'sysuser')       
		,(10, 'DEL_SMP_FROM_SCHEDULE_EVENT', 'sysuser')
		,(11, 'DEL_SMP_FROM_SCHEDULE_OFFSET', 'sysuser')
        ,(12, 'ADD_SMP_TO_SCHEDULE_EVENT', 'sysuser')
        ,(13, 'INSERT_GROUP', 'sysuser')
		,(14, 'INSERT_SCHEDULE', 'sysuser')
		,(15, 'INSERT_TAG', 'sysuser')
		,(16, 'DELETE_TAG', 'sysuser')
		,(17, 'AUTO_GROUP', 'sysuser')
		,(18, 'UPDATE_GROUP', 'sysuser')
		,(19, 'UPDATE_SCHEDULE', 'sysuser')
		,(20, 'ADD_SMP_TO_TAG', 'sysuser')
		,(21, 'DEL_SMP_FROM_TAG', 'sysuser')
		,(22, 'CLEAR_ALARM', 'sysuser')
		,(23, 'CLEAR_ALL_ALARMS', 'sysuser')
		,(24, 'UPDATE_LIGHT_LAT_LONG', 'sysuser')
		,(25, 'UPDATE_LIGHT_POLE_ID', 'sysuser')
		,(26, 'GENERATE_CSV_FILE', 'sysuser')
		,(27, 'ADD_SMP_TO_SCHEDULE_OFFSET', 'sysuser')
		,(28, 'SETUP_DIMMING_CONFIGURATION', 'sysuser')
		,(29, 'RESET_LIGHT_MIN_MAX', 'sysuser')
		,(30, 'INSERT_USER', 'sysuser')
		,(31, 'UPDATE_LIGHT_STATUS', 'sysuser')
		,(32, 'SET_BLINK_BY_LIGHT', 'sysuser')
		,(33, 'SET_CLEAR_OVERRIDE_BY_LIGHT', 'sysuser')
		,(34, 'DEL_SCHEDULE_OFFSET_FROM_SMP', 'sysuser')
		,(35, 'CONFIGURATION', 'sysuser')
		,(36, 'IMPORT_CSV_FILE', 'sysuser')
		,(37,'TURN_ON', 'sysuser')
		,(38,'TURN_OFF', 'sysuser')
		,(39,'DIM', 'sysuser')
;

INSERT INTO action_category
           (action_category_id
           ,label_key
		   ,create_user)
     VALUES
		 (1,'CLEAR_ALERTS','sysuser')
		,(2,'CONFIGURATION','sysuser')
		,(3,'GROUP','sysuser')
		,(4,'ON_OFF','sysuser')
		,(5,'SCHEDULE','sysuser')
		,(6,'TAG','sysuser')
		,(7,'LIGHT_STATUS','sysuser')
		,(8,'ALARM','sysuser')
        ,(9,'WARNING','sysuser')
        ,(10,'ACTIVE','sysuser')
;

INSERT INTO action_category_lc_action(
            action_category_id
		   ,lc_action_id
		   ,create_user)
     VALUES
		(3, 1,'sysuser')
	   ,(3, 2,'sysuser')
	   ,(3, 3,'sysuser')
	   ,(4, 3,'sysuser')
	   ,(4, 4,'sysuser')
	   ,(3, 5,'sysuser')
	   ,(2, 6,'sysuser')
	   ,(7, 7,'sysuser')
	   ,(5, 9,'sysuser')       
	   ,(5, 10,'sysuser')
	   ,(5, 11,'sysuser')
	   ,(5, 12,'sysuser')
	   ,(3, 13,'sysuser')
	   ,(5, 14,'sysuser')
	   ,(6, 15,'sysuser')		
	   ,(6, 16,'sysuser')
	   ,(3, 17,'sysuser')
	   ,(3, 18,'sysuser')
	   ,(5, 19,'sysuser')
	   ,(6, 20,'sysuser')
	   ,(6, 21,'sysuser')
	   ,(1, 22,'sysuser')
	   ,(1, 23,'sysuser')
	   ,(5, 27,'sysuser')
;

INSERT INTO schedule
           (name
           ,description
           ,sunrise_offset
           ,sunset_offset
		   ,schedule_type
		   ,use_sunrise_sunset_tables
		   ,create_user)
    VALUES
		('Unknown Offset Schedule','Unknown Offset Schedule description',0,0,3,'0','sysuser')
	   ,('Unknown Event Schedule','Unknown Event Schedule',0,0,4,'0','sysuser')
;

INSERT INTO property
           (property_name
		   ,label_key
		   ,data_type
		   ,create_user
		   ,property_class
		   ,has_valid_values)
	VALUES
		 ('SUNRISE_TIME','sensus.mlc.property.sunrise_time',7,'sysuser',0,'N') 					--1
		,('SUNRISE_OFFSET','sensus.mlc.property.sunrise_offset',2,'sysuser',0,'N')				--2
		,('SUNSET_TIME','sensus.mlc.property.sunset_time',7,'sysuser',0,'N')						--3
		,('SUNSET_OFFSET','sensus.mlc.property.sunset_offset',2,'sysuser',0,'N')					--4
		,('SERIAL_NUMBER','sensus.mlc.property.serial_number',1,'sysuser',1,'N')					--5
		,('LAMP_TYPE','sensus.mlc.property.lamp_type',1,'sysuser',1,'Y')							--6
		,('WATTAGE_RATING','sensus.mlc.property.wattage_rating',1,'sysuser',1,'Y')				--7
		,('INPUT_VOLTAGE_RANGE','sensus.mlc.property.input_voltage_range',1,'sysuser',1,'N')		--8
		,('COLOR_TEMPERATURE','sensus.mlc.property.color_temperature',1,'sysuser',1,'N')			--9
		,('MANUFACTURER','sensus.mlc.property.manufacturer',1,'sysuser',0,'N')					--10
		,('SENSUS_PART_NUMBER','sensus.mlc.property.sensus_part_number',1,'sysuser',0,'N')		--11
		,('POLE_ID','sensus.mlc.property.pole_id',1,'sysuser',1,'N')								--12
		,('DATE_ADDED','sensus.mlc.property.date_added',5,'sysuser',1,'N')						--13
		,('DATE_INSTALLED','sensus.mlc.property.date_instalInduction',5,'sysuser',0,'N')			--14
		,('FIRMWARE_VERSION','sensus.mlc.property.firmware_version',1,'sysuser',1,'N')			--15
		,('LATITUDE','sensus.mlc.property.latitude',4,'sysuser',1,'N')							--16
		,('LONGITUDE','sensus.mlc.property.longitude',4,'sysuser',1,'N')							--17
		,('LAMP_TYPE_WATTAGE_DIMMABLE','sensus.mlc.property.lamp_type_wattage',1,'sysuser',1,'Y')			--18
		,('AC_VOLTAGE','sensus.mlc.property.ac_voltage',1,'sysuser',0,'N')						--19
		,('AC_CURRENT','sensus.mlc.property.ac_current',1,'sysuser',0,'N')						--20
		,('GROUP_ID',  'sensus.mlc.property.group_id',  2,'sysuser',0, 'N' )						--21
		,('SCHEDULE_ID', 'sensus.mlc.property.schedule_id', 2,'sysuser', 0, 'N')					--22
		,('LIGHT_INTENSITY', 'sensus.mlc.property.light_intensity', 2,'sysuser',0, 'N')			--23
		,('PROTECTED', 'sensus.mlc.property.protected', 2,'sysuser',0, 'N')   					--24
		,('TAG_ID', 'sensus.mlc.property.tag_id', 2, 'sysuser', 0, 'N')    						--25
		,('STATUS_MESSAGE_ID', 'sensus.mlc.property.status_message_id', 2, 'sysuser', 0, 'N') 	--26
		,('STATUS_SUBTYPE_ID', 'sensus.mlc.property.status_subtype_id', 2, 'sysuser', 0, 'N') 	--27
		,('STREET_NAME', 'sensus.mlc.property.street_name', 1, 'sysuser', 0, 'N')       			--28
		,('CITY_NAME', 'sensus.mlc.property.city_name', 1, 'sysuser', 1, 'N')       				--29
		,('COUNTY_NAME', 'sensus.mlc.property.county_name', 1, 'sysuser', 0, 'N')       			--30
		,('STATE_NAME', 'sensus.mlc.property.state_name', 1, 'sysuser', 0, 'N')       			--31
		,('ZIP_CODE', 'sensus.mlc.property.zip_code', 1, 'sysuser', 0, 'N')       				--32
		,('SCHEDULE_NAME', 'sensus.mlc.property.schedule_name', 1, 'sysuser', 0, 'N')       		--33
		,('GROUP_NAME', 'sensus.mlc.property.group_name', 1, 'sysuser', 0, 'N')       			--34
		,('TAG_NAME', 'sensus.mlc.property.tag_name', 1, 'sysuser', 0, 'N')       				--35
		,('FLEXNET_ID', 'sensus.mlc.property.tag_name', 1, 'sysuser', 0, 'N')       				--36
	    ,('LANGUAGE', 'sensus.mlc.property.language', 1, 'sysuser', 0, 'N')       				--37
		,('TIME_ZONE', 'sensus.mlc.property.time_zone', 1, 'sysuser', 0, 'N')    	  			--38
		,('DATE_FORMAT', 'sensus.mlc.property.date_format', 1, 'sysuser', 0, 'N')       			--39
		,('MONITOR_REQUEST', 'sensus.mlc.property.monitor_request', 1, 'sysuser', 0, 'N')   	 	--40
		,('TIME_ZONE_REGION', 'sensus.mlc.property.time_zone_region', 1, 'sysuser', 0, 'N')  	--41
		,('EVENT_SCHEDULE', 'sensus.mlc.property.event_schedule', 1, 'sysuser', 0, 'N')    	 	--42
		,('OFFSET_SCHEDULE', 'sensus.mlc.property.offset_schedule', 1, 'sysuser', 0, 'N')   		--43
		,('MODEL_NUMBER', 'sensus.mlc.property.model_number', 1, 'sysuser', 1, 'N')     			--44
		,('LOWER_ASSEMBLY_SERIAL_NUMBER', 'sensus.mlc.property.lower_assembly_serial_number', 1, 'sysuser', 1, 'N')     	--45
		,('UPPER_ASSEMBLY_SERIAL_NUMBER', 'sensus.mlc.property.upper_assembly_serial_number', 1, 'sysuser', 1, 'N')     	--46
		,('BULB_SERIAL_NUMBER', 'sensus.mlc.property.bulb_serial_number', 1, 'sysuser', 1, 'N')  --47
		,('BALLAST_SERIAL_NUMBER', 'sensus.mlc.property.ballast_serial_number', 1, 'sysuser', 0, 'N') 	--48
		,('CUSTOMER_SERIAL_NUMBER', 'sensus.mlc.property.customer_serial_number', 1, 'sysuser', 0, 'N')	--49
		,('FREQUENCY', 'sensus.mlc.property.frequency', 1, 'sysuser', 0, 'N')     				--50
		,('LIGHT_SOURCE', 'sensus.mlc.property.light_source', 1, 'sysuser', 0, 'N')    			--51
		,('LIGHT_DETAIL_TYPE', 'sensus.mlc.property.light_detail_type', 1, 'sysuser', 0, 'N')		--52
		,('FILE_NAME', 'sensus.mlc.property.file_name', 1, 'sysuser', 0, 'N')					--53
		,('CONVERT_ENERGY_UNIT', 'sensus.mlc.property.convert_unit_energy', 1, 'sysuser', 0, 'N')					--54
		,('DIMMABLE', 'sensus.mlc.property.dimmable', 1, 'sysuser', 0, 'Y')					--55
		,('HOUSING', 'sensus.mlc.property.housing', 1, 'sysuser', 0, 'Y')					--56
		,('HARDWARE_SETTING_CONFIGURATION_1', 'sensus.mlc.property.hardware_setting_configuration_1', 1, 'sysuser', 0, 'Y')					--57
		,('CURRENT_SCALE_CONFIGURATION_1', 'sensus.mlc.property.current_scale_configuration_1', 1, 'sysuser', 0, 'Y')					--58
		,('FULL_ON_REQUIRED_CONFIGURATION_1', 'sensus.mlc.property.full_on_required_configuration_1', 1, 'sysuser', 0, 'Y')					--59
		,('HARDWARE_SETTING_CONFIGURATION_2', 'sensus.mlc.property.hardware_setting_configuration_2', 1, 'sysuser', 0, 'Y')					--60
		,('CURRENT_SCALE_CONFIGURATION_2', 'sensus.mlc.property.current_scale_configuration_2', 1, 'sysuser', 0, 'Y')					--61
		,('FULL_ON_REQUIRED_CONFIGURATION_2', 'sensus.mlc.property.full_on_required_configuration_2', 1, 'sysuser', 0, 'Y')					--62
		,('HARDWARE_SETTING_CONFIGURATION_3', 'sensus.mlc.property.hardware_setting_configuration_3', 1, 'sysuser', 0, 'Y')					--63
		,('CURRENT_SCALE_CONFIGURATION_3', 'sensus.mlc.property.current_scale_configuration_3', 1, 'sysuser', 0, 'Y')					--64
		,('FULL_ON_REQUIRED_CONFIGURATION_3', 'sensus.mlc.property.full_on_required_configuration_3', 1, 'sysuser', 0, 'Y')					--65
		,('HARDWARE_SETTING_CONFIGURATION_4', 'sensus.mlc.property.hardware_setting_configuration_4', 1, 'sysuser', 0, 'Y')					--66
		,('CURRENT_SCALE_CONFIGURATION_4', 'sensus.mlc.property.current_scale_configuration_4', 1, 'sysuser', 0, 'Y')					--67
		,('FULL_ON_REQUIRED_CONFIGURATION_4', 'sensus.mlc.property.full_on_required_configuration_4', 1, 'sysuser', 0, 'Y')					--68
		,('HARDWARE_SETTING_CONFIGURATION_5', 'sensus.mlc.property.hardware_setting_configuration_5', 1, 'sysuser', 0, 'Y')					--69
		,('CURRENT_SCALE_CONFIGURATION_5', 'sensus.mlc.property.current_scale_configuration_5', 1, 'sysuser', 0, 'Y')					--70
		,('FULL_ON_REQUIRED_CONFIGURATION_5', 'sensus.mlc.property.full_on_required_configuration_5', 1, 'sysuser', 0, 'Y')					--71
		,('HARDWARE_SETTING_CONFIGURATION_6', 'sensus.mlc.property.hardware_setting_configuration_6', 1, 'sysuser', 0, 'Y')					--72
		,('CURRENT_SCALE_CONFIGURATION_6', 'sensus.mlc.property.current_scale_configuration_6', 1, 'sysuser', 0, 'Y')					--73
		,('FULL_ON_REQUIRED_CONFIGURATION_6', 'sensus.mlc.property.full_on_required_configuration_6', 1, 'sysuser', 0, 'Y')					--74
		,('HOUSING_COLOR', 'sensus.mlc.property.housing_color', 1, 'sysuser', 1, 'Y')					--75
		,('SUNRISE_BEFORE','sensus.mlc.property.sunrise_before',1, 'sysuser', 0, 'N')--76
		,('SUNSET_BEFORE','sensus.mlc.property.sunset_before',1, 'sysuser', 0, 'N')--77
		,('DAYS_OF_WEEK','sensus.mlc.property.dasy_of_week',1,'sysuser',0,'N')--78
		,('SCHEDULE_EVENT_TIME','sensus.mlc.property.schedule_event_time',1,'sysuser',0,'N')--79
		,('DIM_ON_DELAY','sensus.mlc.property.dim_on_delay',1,'sysuser',0,'N')--80
		,('CURRENT_LIGHT_STATUS','sensus.mlc.property.current_light_status',1,'sysuser',0,'N')--81
		,('CURRENT_ALARM_WARNING_STATUS_SUBTYPE','sensus.mlc.property.current_alarm_warning_status_subtype',1,'sysuser',0,'N')--82
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
        ,('SHOW_DIALOG_POLYGON','sensus.mlc.property.show_dialog_polygon',2,'sysuser',0,'N') --115		
; 

INSERT INTO property_valid_value
           (valid_value
		   ,property_id
		   ,create_user)
    VALUES
		 ('Induction',6,'sysuser')
		,('LED',6,'sysuser')
		,('40W',7,'sysuser')
		,('60W',7,'sysuser')
		,('75W',7,'sysuser')
		,('80W',7,'sysuser')
		,('100W',7,'sysuser')
		,('105W',7,'sysuser')
		,('120W',7,'sysuser')
		,('150W',7,'sysuser')
		,('200W',7,'sysuser')
		,('250W',7,'sysuser')
		,('300W',7,'sysuser')
		,('400W',7,'sysuser')
		,('28W',7,'sysuser')
		,('56W',7,'sysuser')
		,('84W',7,'sysuser')
		,('112W',7,'sysuser')
		,('140W',7,'sysuser')
		,('168W',7,'sysuser')
		,('196W',7,'sysuser')
		,('224W',7,'sysuser')
		,('252W',7,'sysuser')	
		,('150-300V',8,'sysuser')
		,('90-300V',8,'sysuser')
		,('3,000K',9,'sysuser')
		,('4,000K',9,'sysuser')
		,('5,000K',9,'sysuser')
		,('6,000K',9,'sysuser')
		,('40W Induction',18,'sysuser')
		,('60W Induction',18,'sysuser')
		,('80W Induction',18,'sysuser')
		,('100W Induction',18,'sysuser')
		,('120W Induction',18,'sysuser')
		,('150W Induction',18,'sysuser')
		,('200W Induction',18,'sysuser')
		,('250W Induction',18,'sysuser')
		,('300W Induction',18,'sysuser')
		,('400W Induction',18,'sysuser')
		,('28W LED',18,'sysuser')
		,('56W LED',18,'sysuser')
		,('75W LED',18,'sysuser')
		,('84W LED',18,'sysuser')
		,('105W LED',18,'sysuser')
		,('112W LED',18,'sysuser')
		,('140W LED',18,'sysuser')
		,('168W LED',18,'sysuser')
		,('196W LED',18,'sysuser')
		,('224W LED',18,'sysuser')
		,('252W LED',18,'sysuser')
		,('Street',56,'sysuser')
		,('Decorative',56,'sysuser')
		,('High Bay',56,'sysuser')
		,('Low Bay',56,'sysuser')
		,('Tunnel',56,'sysuser')
		,('Shoebox',56,'sysuser')
		,('Flood',56,'sysuser')
		,('Wall Pack',56,'sysuser')
		,('Post Top',56,'sysuser')
		,('Shepherd',56,'sysuser')
		,('True',55,'sysuser')
		,('False',55,'sysuser')
		,('Gray',75,'sysuser')
		,('Black',75,'sysuser')
		,('Silver',75,'sysuser')
		,('Bronze',75,'sysuser')
		,('White',75,'sysuser')
		,('Off-White / Cream',75,'sysuser')
		,('Green',75,'sysuser')
		,('Aluminum',75,'sysuser')
;


INSERT INTO users
			(tenant_id
			,username
			,password
			,first_name
			,last_name
			,email
			,enabled
			,all_lights_auth
			,create_date
			,create_user
			,modified_date
			,modified_user)
    VALUES 
			(NULL,'superuser','f653dd94ea893ca824e596f8644d4894141fccc15bdbc7494d0ccd15156b6150','Super User','Super User','super@super.com',TRUE,TRUE,CURRENT_TIMESTAMP,'QAT',NULL,NULL);
			
INSERT INTO authorities
(user_id
,authority)
    VALUES 
			(1, 'ROLE_Role.Admin');