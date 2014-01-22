-- Light data deletion
DELETE FROM light_grouping;
DELETE FROM light_tag;
DELETE FROM light;
DELETE FROM sensus_part_number_configuration;
DELETE FROM sensus_part_number;

-- Notification data deletion
DELETE FROM notification_history_alert;
DELETE FROM notification_history;

DELETE FROM schedule_membership;
DELETE FROM schedule_event;
DELETE FROM schedule;

-- User data deletion
DELETE FROM grouping;
DELETE FROM user_settings_property;
DELETE FROM user_settings;
DELETE FROM authorities;
DELETE FROM users;
DELETE FROM tenant;
DELETE FROM users;

-- Analytics deletion
DELETE FROM analytics_alarms;
DELETE FROM analytics_warnings;
DELETE FROM analytics_consumption;
DELETE FROM analytics_installed;
DELETE FROM analytics_group_by_date;
DELETE FROM analytics_group WHERE tenant_id IS NOT NULL;

-- Insert tenant
INSERT INTO tenant
           (tenant_id
           ,name
           ,description
           ,rni_code
		   ,server_name
		   ,gateway_rni_location
		   ,create_user
		   ,min_smartpoint_comm_time
		   ,light_time_zone
		   ,ecomode_disable
		   ,batch_process_time)

    VALUES
		  (1,'acme','ACME Corporation LC Demo','ACME','acme-app.davis.sensus.lab','http://mallet-web.davis.sensus.lab/mlc-ws/mlc-ws/','System',3, 'US/Eastern', false, 9),
		  (2,'peco','PECO Corporation LC Demo','PECO','mallet-app.davis.sensus.lab'  ,'http://mallet-web.davis.sensus.lab/mlc-ws/mlc-ws/','System',3, 'US/Eastern', true, 9);

-- Insert grouping
INSERT INTO grouping
           (name
           ,description
		   ,create_user
		   ,tenant_id)
    VALUES
		('Beaverton','Beaverton','System',1)
	   ,('Buckman','Buckman','System',2)
	   ,('Downtown','Downtown','System',1)
	   ,('Concordia','Concordia','System',2)
	   ,('Cully','Cully','System',2)
	   ,('Kully','Kully','System',1)
	   ,('Eliot','Eliot','System',2)
	   ,('Forest Park','Forest Park','System',1)
	   ,('Healy Heights','Healy Heights','System',1)
	   ,('Hillsdale','Hillsdale','System',1)
	   ,('I5','I5','System',1)
	   ,('Irvington','Irvington','System',1)
	   ,('Lloyd','Lloyd','System',1)
	   ,('Mount Tabor','Mount Tabor','System',1)
	   ,('Old Town','Old Town','System',1)
	   ,('Oregon Health & Science University','Oregon Health & Science University','System',1)
	   ,('Overlook','Overlook','System',1)
	   ,('PDX NE','PDX NE','System',1)
	   ,('PDX NW','PDX NW','System',1)
	   ,('PDX SE','PDX SE','System',1)
	   ,('PDX SW','PDX SW','System',1)
	   ,('PDX Airport','PDX Airport','System',1)
	   ,('Pearl District','Pearl District','System',1)
	   ,('Reed College','Reed College','System',1)
	   ,('Richmoned','Richmoned','System',1)
	   ,('Southwest Hills','Southwest Hills','System',1)
	   ,('University Park','University Park','System',1)
	   ,('Woodstock','Woodstock','System',1)
	   ,('System','QAT Group','System',1)
;

-- Insert tags
INSERT INTO tag
           (name
           ,auto_group
		   ,create_user
		   ,tenant_id)
     VALUES
		 ('Banks',false,'System',1)
		,('Beaverton',false,'System',1)
		,('Buckman',false,'System',2)
		,('Burnside',false,'System',1)
		,('Downtown',false,'System',1)
		,('Concordia',false,'System',2)
		,('Clackamas',false,'System',1)
		,('Cully',false,'System',2)
		,('Eliot',false,'System',2)
		,('Estacada',false,'System',1)
		,('Forest Park',false,'System',1)
		,('Healy Heights',false,'System',1)
		,('Hillsdale',false,'System',1)
		,('Irvington',false,'System',1)
		,('Lloyd',false,'System',1)
		,('Mount Tabor',false,'System',1)
		,('Multnomah',false,'System',1)
		,('NE 10th Ave',false,'System',1)
		,('NE 11th Ave',false,'System',1)
		,('NE 12th Ave',false,'System',1)
		,('NE 13th Ave',false,'System',1)
		,('NE 14th Ave',false,'System',1)
		,('NE 15th Ave',false,'System',1)
		,('NE Brazee St',false,'System',1)
		,('NE Hancock St',false,'System',1)
		,('NE Tillamook St',false,'System',1)
		,('NE Stanton St',false,'System',1)
		,('Old Town',false,'System',1)
		,('Overlook',false,'System',1)
		,('Pearl District',false,'System',1)
		,('Portland',false,'System',1)
		,('Richmond',false,'System',1)
		,('Southwest Hills',false,'System',1)
		,('SW 4th Ave',false,'System',1)
		,('SW 5th Ave',false,'System',1)
		,('SW 6th Ave',false,'System',1)
		,('SW Mill St',false,'System',1)
		,('SW Harrison St',false,'System',1)
		,('University Park',false,'System',1)
		,('Woodstock',false,'System',1)
		,('Washington Park',false,'System',1)
		,('Yamhill',false,'System',1)
		,('PDX Airport',false,'System',1)
		,('System',false,'System',1)
;

-- Insert schedules
INSERT INTO schedule
           (schedule_id
           ,name
           ,description
           ,sunrise_offset
           ,sunset_offset
		   ,schedule_type
		   ,tenant_id
		   ,use_sunrise_sunset_tables
		   ,create_user
		   ,intensity)
    VALUES
	    (1,'Regular Schedule','Regular Schedule description',5,-5,1,1,'0','System',10)
	   ,(2,'Summer Schedule','Summer Schedule description',-15,15,1,1,'0','System',10)
	   ,(3,'Holiday Schedule','Holiday Schedule description',30,-30,1,1,'0','System',10)
	   ,(4,'Fall Schedule','Fall Schedule description',0,0,2,1,'0','System',null)
;

-- Insert schedule events
INSERT INTO schedule_event
			(schedule_event_id
			,event_time
			,intensity
			,create_user
			,schedule_id
			,tenant_id)
     VALUES (1,CURRENT_TIMESTAMP, 10, 'System', 1, 1)
;

INSERT INTO schedule_event_day_of_week
			(create_user
			,day_of_week_id
			,schedule_event_id)
     VALUES ( 'System', 3, 1)
;

-- Insert users
INSERT INTO users
			(user_id
			,tenant_id
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
			(1,1,'rod','841d62d455161130a5de58aa957d3431ef39248d3c69c657f1807b3e2e5a6a5e','Rod','Koala','rod@koala.com',true,true,CURRENT_TIMESTAMP,'System',null,null), --1
			(2,1,'dianne','emu','Dianne','Emu','dianne@emu.com',true,true,CURRENT_TIMESTAMP,'System',null,null),		--2
			(3,1,'scott','wombat','Scott','Wombat','scott@wambat.com',true,true,CURRENT_TIMESTAMP,'System',null,null), --3
			(4,1,'peter','opal','Peter','Opal','peter@opal.com',true,true,CURRENT_TIMESTAMP,'System',null,null),--4
			(5,1,'superuser','f653dd94ea893ca824e596f8644d4894141fccc15bdbc7494d0ccd15156b6150','Super User','Super User','super@super.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL,NULL),--5
			(6,1,'annab','d8301cc0175be5198f8da53128932d4bcae4ae9478ececd0d8e6636f6aab3248','Anna','Borges','annab@annab.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--6
			(7,1,'jorge','6320899ff8cff2892fadd08c42e4d4ad1051787f1ffc2e9e4b3e98e0b391045d','Jorge','Penedo','jorge@jorge.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL);--7

-- Insert user settings
INSERT INTO user_settings
			(user_id
			,tenant_id
			,create_date
			,create_user)
    VALUES
			(1, 1, CURRENT_TIMESTAMP, 'System'),
			(2, 1, CURRENT_TIMESTAMP, 'System'),
			(3, 2, CURRENT_TIMESTAMP, 'System'),
			(4, 2, CURRENT_TIMESTAMP, 'System');

-- Insert authorities
INSERT INTO authorities
			(user_id
			,authority)
    VALUES
			(1, 'ROLE_Role.Admin'),
			(2, 'ROLE_Role.Admin'),
			(3, 'ROLE_Role.Admin'),
			(4, 'ROLE_Role.Admin'),
			(5, 'ROLE_Role.Admin'),
			(6, 'ROLE_Role.Admin'),
			(7, 'ROLE_Role.Admin');

