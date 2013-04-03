DELETE FROM smartpoint_grouping;
DELETE FROM smartpoint_tag;
DELETE FROM status_message_status_subtype;

DELETE FROM status_message;
SELECT SETVAL('status_message_status_message_id_seq',1,false);

DELETE FROM grouping;
DELETE FROM user_settings_property;

DELETE FROM user_settings;
SELECT SETVAL('user_settings_user_settings_id_seq',1,false);

DELETE FROM light_property;
DELETE FROM authorities;
DELETE FROM users;

DELETE FROM schedule_membership;

DELETE FROM schedule_event;
SELECT SETVAL('schedule_event_schedule_event_id_seq',1,false);

DELETE FROM schedule WHERE tenant_id IS NOT NULL;
SELECT SETVAL('schedule_schedule_id_seq',3,false);

DELETE FROM light;
SELECT SETVAL('light_light_id_seq',1,false);

DELETE FROM smartpoint;
SELECT SETVAL('smartpoint_smartpoint_id_seq',1,false);

DELETE FROM tenant;
SELECT SETVAL('tenant_tenant_id_seq',1,false);

DELETE FROM users;
SELECT SETVAL('users_user_id_seq',1,false);

DELETE FROM analytics_alarms;
DELETE FROM analytics_warnings;
DELETE FROM analytics_consumption;
DELETE FROM analytics_installed;
DELETE FROM analytics_group_by_date;
DELETE FROM analytics_group WHERE tenant_id IS NOT NULL;

DELETE FROM sensus_part_number_configuration;
DELETE FROM sensus_part_number;






INSERT INTO tenant
           (name
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
		  ('acme','ACME Corporation LC Demo','ACME','acme-app.davis.sensus.lab','http://mallet-web.davis.sensus.lab/mlc-ws/mlc-ws/','System',3, 'US/Eastern', false, 9),
		  ('peco','PECO Corporation LC Demo','PECO','mallet-app.davis.sensus.lab'  ,'http://mallet-web.davis.sensus.lab/mlc-ws/mlc-ws/','System',3, 'US/Eastern', true, 9);
		  


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

INSERT INTO schedule
           (name
           ,description
           ,sunrise_offset
           ,sunset_offset
		   ,schedule_type
		   ,tenant_id
		   ,use_sunrise_sunset_tables
		   ,create_user
		   ,intensity)
    VALUES
	    ('Regular Schedule','Regular Schedule description',5,-5,1,1,'0','System',10)
	   ,('Summer Schedule','Summer Schedule description',-15,15,1,1,'0','System',10)
	   ,('Holiday Schedule','Holiday Schedule description',30,-30,1,1,'0','System',10)
	   ,('Fall Schedule','Fall Schedule description',0,0,2,1,'0','System',null)
;

INSERT INTO schedule_event
			(event_time
			,intensity
			,create_user
			,schedule_id
			,tenant_id)
     VALUES (CURRENT_TIMESTAMP, 10, 'System', 6, 1)
;

INSERT INTO schedule_event_day_of_week
			(create_user
			,day_of_week_id
			,schedule_event_id)
     VALUES ( 'System', 3, 1)
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
			(2,'rod','841d62d455161130a5de58aa957d3431ef39248d3c69c657f1807b3e2e5a6a5e','Rod','Koala','rod@koala.com',true,true,CURRENT_TIMESTAMP,'System',null,null), --1
			(2,'dianne','emu','Dianne','Emu','dianne@emu.com',true,true,CURRENT_TIMESTAMP,'System',null,null),		--2
			(2,'scott','wombat','Scott','Wombat','scott@wambat.com',true,true,CURRENT_TIMESTAMP,'System',null,null), --3
			(2,'peter','opal','Peter','Opal','peter@opal.com',true,true,CURRENT_TIMESTAMP,'System',null,null),--4
			(NULL,'superuser','f653dd94ea893ca824e596f8644d4894141fccc15bdbc7494d0ccd15156b6150','Super User','Super User','super@super.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL,NULL),--5
			(2,'annab','d8301cc0175be5198f8da53128932d4bcae4ae9478ececd0d8e6636f6aab3248','Anna','Borges','annab@annab.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--6
			(2,'jorge','6320899ff8cff2892fadd08c42e4d4ad1051787f1ffc2e9e4b3e98e0b391045d','Jorge','Penedo','jorge@jorge.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--7
			(2,'leandro','37d52b4ca6aaadf9b37292e1c1343fec6eed2eba39b1b7ed2a8648d345f0407c','Leandro ','Inacio','leando@leandro.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--8
			(2,'gustavo','08a4bbf8090d955085f65cbe6854b14c86dc22302e03c9c0a34c9e564afcdb1e','Gustavo','Aragao','gustavo@gustavo.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--9
			(2,'venky','5f108ace38c8f28998a4f3777f302162102a68bb86bcc750e7e4aad0e9a47b64','Venky','krishnan','venky@venky.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--10
			(2,'anna','972a66170a1ce3e39692e004434ec1f0d7abf107ae7bd69f5fbe8487af0ba24e','Anna','huynh','anna@anna.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--11
			(2,'jake','89f594136bed19c12aa62d0512715a0280d9e93fa5d3304f3caa4bf82cd337c4','Jake','Schirm','jake@jake.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--12
			(2,'david','c52e5ae54fa7262d8a914a667dac4b663838d27c3fe026a3042a016e394d6450','David','Willcox','david@david.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--13
			(2,'rich','7d0805399d46807b253cf59a091a1826ebb0eebcdebf53b799cf3016090b8bfe','Rich','Barndt','rich@rich.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--14
			(2,'swati','7e70e9250b7efcd4c2db6702b6653128e708789452ff416eb3e0e00391867590','Swati','Ladhe','swati@swati.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--15
			(1,'rod','841d62d455161130a5de58aa957d3431ef39248d3c69c657f1807b3e2e5a6a5e','Rod','Koala','rod@koala.com',true,true,CURRENT_TIMESTAMP,'System',null,null),--16
			(1,'dianne','emu','Dianne','Emu','dianne@emu.com',true,true,CURRENT_TIMESTAMP,'System',null,null),--17
			(1,'scott','wombat','Scott','Wombat','scott@wambat.com',true,true,CURRENT_TIMESTAMP,'System',null,null),--18
			(1,'peter','opal','Peter','Opal','peter@opal.com',true,true,CURRENT_TIMESTAMP,'System',null,null),--19
			(1,'annab','d8301cc0175be5198f8da53128932d4bcae4ae9478ececd0d8e6636f6aab3248','Anna','Borges','annab@annab.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--20
			(1,'jorge','6320899ff8cff2892fadd08c42e4d4ad1051787f1ffc2e9e4b3e98e0b391045d','Jorge','Penedo','jorge@jorge.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--21
			(1,'leandro','37d52b4ca6aaadf9b37292e1c1343fec6eed2eba39b1b7ed2a8648d345f0407c','Leandro ','Inacio','leando@leandro.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--22
			(1,'gustavo','08a4bbf8090d955085f65cbe6854b14c86dc22302e03c9c0a34c9e564afcdb1e','Gustavo','Aragao','gustavo@gustavo.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--23
			(1,'venky','5f108ace38c8f28998a4f3777f302162102a68bb86bcc750e7e4aad0e9a47b64','Venky','krishnan','venky@venky.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--24
			(1,'anna','972a66170a1ce3e39692e004434ec1f0d7abf107ae7bd69f5fbe8487af0ba24e','Anna','huynh','anna@anna.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--25
			(1,'jake','89f594136bed19c12aa62d0512715a0280d9e93fa5d3304f3caa4bf82cd337c4','Jake','Schirm','jake@jake.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--26
			(1,'david','c52e5ae54fa7262d8a914a667dac4b663838d27c3fe026a3042a016e394d6450','David','Willcox','david@david.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--27
			(1,'rich','7d0805399d46807b253cf59a091a1826ebb0eebcdebf53b799cf3016090b8bfe','Rich','Barndt','rich@rich.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL),--28
			(1,'swati','7e70e9250b7efcd4c2db6702b6653128e708789452ff416eb3e0e00391867590','Swati','Ladhe','swati@swati.com',TRUE,TRUE,CURRENT_TIMESTAMP,'System',NULL, NULL);--29

	
INSERT INTO user_settings
			(user_id
			,tenant_id
			,create_date
			,create_user)
    VALUES 
			(1, 1, CURRENT_TIMESTAMP, 'System'),
			(2, 1, CURRENT_TIMESTAMP, 'System'),
			(3, 2, CURRENT_TIMESTAMP, 'System'),
			(4, 2, CURRENT_TIMESTAMP, 'System')
;
		
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
			(7, 'ROLE_Role.Admin'),
			(8, 'ROLE_Role.Admin'),
			(9, 'ROLE_Role.Admin'),
			(10, 'ROLE_Role.Admin'),
			(11, 'ROLE_Role.Admin'),
			(12, 'ROLE_Role.Admin'),
			(13, 'ROLE_Role.Admin'),
			(14, 'ROLE_Role.Admin'),
			(15, 'ROLE_Role.Admin'),
			(16, 'ROLE_Role.Admin'),
			(17, 'ROLE_Role.Admin'),
			(18, 'ROLE_Role.Admin'),
			(19, 'ROLE_Role.Admin'),
			(20, 'ROLE_Role.Admin'),
			(21, 'ROLE_Role.Admin'),
			(22, 'ROLE_Role.Admin'),
			(23, 'ROLE_Role.Admin'),
			(24, 'ROLE_Role.Admin'),
			(25, 'ROLE_Role.Admin'),
			(26, 'ROLE_Role.Admin'),
			(27, 'ROLE_Role.Admin'),
			(28, 'ROLE_Role.Admin'),
			(29, 'ROLE_Role.Admin');
			
	