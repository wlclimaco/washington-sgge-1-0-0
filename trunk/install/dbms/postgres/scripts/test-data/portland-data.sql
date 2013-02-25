CREATE or replace FUNCTION create_random_status_message(p_amount_recs integer)
  RETURNS character varying AS
$$
DECLARE

p_rate integer ARRAY;

p_cur refcursor;
p_rec RECORD;

p_cur_st refcursor;
p_rec_st RECORD;

p_status_message_id integer;
p_status_message_counter bigint;

p_tenant_id integer;
max_light_id integer;
status integer;
substatus integer;
statusDate timestamp;
p_statusLast int;
p_substatusLast int;

BEGIN
	RAISE NOTICE ' Creating random status messages. Wait....';

	p_rate [0]:= 110;
	p_rate [1]:= 105;
	p_rate [2]:= 100;
	p_rate [3]:= 90;
	p_rate [4]:= 80;
	p_rate [5]:= 70;
	p_rate [6]:= 60;
	p_rate [7]:= 50;
	p_rate [8]:= 40;
	p_rate [9]:= 30;
	p_rate [10]:= 20;
	p_rate [11]:= 19;
	p_rate [12]:= 15;
	p_rate [13]:= 10;
	p_rate [14]:= 1;
	
	max_light_id := (SELECT MAX(light_id) FROM light);

	FOR i IN 0..array_upper(p_rate,1) LOOP
		OPEN p_cur FOR 
		SELECT MOD(round((random() * 1000))::INTEGER,max_light_id) + 1 AS light_id
		FROM generate_series(1,p_amount_recs);
		 		
		LOOP
			FETCH p_cur INTO p_rec;
			EXIT WHEN NOT FOUND;

			IF((SELECT l.tenant_id FROM light l WHERE l.light_id = p_rec.light_id) IS NOT NULL) THEN

				p_tenant_id := (SELECT l.tenant_id FROM light l WHERE l.light_id = p_rec.light_id);
			
				status := mod(round((random() * 1000))::INTEGER,4) + 1;
				
				IF status = 1
				THEN
					BEGIN
						substatus := mod(round((random() * 1000))::INTEGER,5) + 1;
					END;
				ELSE
					IF status = 2
					THEN
						BEGIN
							substatus := mod(round((random() * 1000))::INTEGER,7) + 6;
						END;
					ELSE
						BEGIN
							substatus := null;
						END;
					END IF;
				END IF;
				
				-- RAISE NOTICE ' status = %, substatus = % ', cast(status as varchar(4)), cast(substatus as varchar(10));
				statusDate := (SELECT CURRENT_TIMESTAMP - interval '1 day');
				
				p_statusLast := (  SELECT sm.status_id
								  FROM status_message AS sm 
									   LEFT JOIN status_message_status_subtype smss
									   ON sm.status_message_id = smss.status_message_id
								 WHERE sm.light_id = p_rec.light_id 
								    AND sm.message_date BETWEEN (statusDate::date - interval '1 month ') AND (statusDate::date + interval '1 day ')::date
								 ORDER BY sm.message_date DESC, sm.status_message_id DESC
								 LIMIT 1
							);
				
				p_substatusLast := (  SELECT smss.status_message_id
								  FROM status_message AS sm 
									   LEFT JOIN status_message_status_subtype smss
									   ON sm.status_message_id = smss.status_message_id
								 WHERE sm.light_id = p_rec.light_id 
								    AND sm.message_date BETWEEN (statusDate::date - interval '1 month ') AND (statusDate::date + interval '1 day ')::date
								 ORDER BY sm.message_date DESC, sm.status_message_id DESC
								 LIMIT 1
							);
				
				IF ( (substatus IS NOT NULL AND p_substatusLast <> substatus AND status <> p_statusLast) OR 
				     (status = 4 AND p_statusLast <> 4) ) THEN
					BEGIN
						INSERT INTO status_message (light_id, status_id, message_date, create_date, create_user, tenant_id ,message_type)
											VALUES (p_rec.light_id, status, statusDate, statusDate, 'QAT', p_tenant_id , '2')
										 RETURNING status_message_id INTO p_status_message_id;
						
						DELETE FROM current_alarm_warning_message WHERE light_id = p_rec.light_id;
						
						
						INSERT INTO current_alarm_warning_message(light_id, status_message_id, status_message_type, status_messages_status_id, 
												  status_messages_status_subtype_id, tenant_id, message_date)
										  VALUES (p_rec.light_id, p_status_message_id, '2', status, 
												  substatus, p_tenant_id, statusDate);
						UPDATE light
							SET   current_status_message_id = p_status_message_id
					    WHERE light_id = p_rec.light_id;
						
						IF substatus IS NOT NULL THEN
						
							INSERT INTO status_message_status_subtype (status_message_id   ,status_subtype_id          ,create_date        ,create_user)
															   VALUES (p_status_message_id ,substatus , statusDate ,'QAT');
						
		
						    PERFORM upd_analytics_alarms_test(p_status_message_id, substatus,'QAT',null,'+', statusDate);
						
						END IF;
						
						IF status = 4 THEN
							BEGIN
								-- Remove references by group and tag
								DELETE FROM smartpoint_grouping WHERE smartpoint_id = p_rec.light_id;
								DELETE FROM smartpoint_tag WHERE smartpoint_id = p_rec.light_id;
							END;
						END IF;
					END;
				END IF;			
			END IF;			

		end loop;
		CLOSE p_cur;

	end loop;
        
    RETURN 'success';
END
$$
  LANGUAGE plpgsql;

-- InsertData p_number_of_lights int ,p_group_name varchar(100) ,p_lat_seed float ,p_lng_seed float ,p_lat_factor float ,p_lng_factor float
CREATE FUNCTION PortlandData1 ()
RETURNS void AS
$$
DECLARE 
	p_create_user varchar(20);
	p_grouping_id int;
	
	p_number_of_qat_lights integer;
BEGIN
	p_create_user := 'QAT';

	-- Downtown
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '60', 'Downtown';
	PERFORM InsertByLatLng ( 60, 'Downtown', 35.8667, -78.8610, 0.01, 0.02,p_create_user);

	-- Airport PDX 	45.58569252	-122.5960922
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '45', 'PDX Airport';
	PERFORM InsertByLatLng ( 45, 'PDX Airport', 35.9667, -78.1610, 0.01, 0.02,p_create_user);


END
$$
LANGUAGE 'plpgsql';	

CREATE FUNCTION PortlandData2 ()
RETURNS void AS
$$
DECLARE 
	p_create_user varchar(20);
	p_grouping_id int;
	
	p_number_of_qat_lights integer;
BEGIN

	p_create_user := 'QAT';
	
	-- Buckman 	45.51711321	-122.6557446
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '25', 'Buckman';
	PERFORM InsertByLatLng ( 25, 'Buckman', 35.7667, -78.2610, 0.01, 0.02,p_create_user);

	-- Concordia	45.56995253	-122.6340294
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '35', 'Concordia';
	PERFORM InsertByLatLng ( 35, 'Concordia', 35.6667, -78.3610, 0.01, 0.02,p_create_user);

	-- Cully 	45.5619006	-122.5782394
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '15', 'Cully';
	PERFORM InsertByLatLng ( 15, 'Cully', 35.5667, -78.4610, 0.01, 0.02,p_create_user);

	-- Eliot 	45.53918072	-122.6745415
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '15', 'Eliot';
	PERFORM InsertByLatLng ( 15, 'Eliot', 35.4667, -78.5610, 0.01, 0.02,p_create_user);

END
$$
LANGUAGE 'plpgsql';	

CREATE FUNCTION PortlandData3 ()
RETURNS void AS
$$
DECLARE 
	p_create_user varchar(20);
	p_grouping_id int;
	
	p_number_of_qat_lights integer;
BEGIN

	p_create_user := 'QAT';

	-- Forest Park 	45.55204442	-122.7457809
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '125', 'Forest Park';
	PERFORM InsertByLatLng ( 125, 'Forest Park', 35.3667, -78.6610, 0.01, 0.02,p_create_user);

	--Healy Heights 	45.49335241	-122.6987457
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '65', 'Healy Heights';
	PERFORM InsertByLatLng ( 65, 'Healy Heights', 35.2667, -78.7610, 0.01, 0.02,p_create_user);
END
$$
LANGUAGE 'plpgsql';	

CREATE FUNCTION PortlandData4 (p_number_of_qat_lights integer, p_total_number_of_qat_lights integer)
RETURNS void AS
$$
DECLARE 
	p_create_user varchar(20);
	p_grouping_id int;
	
BEGIN

	p_create_user := 'QAT';
	
	-- QAT 41.257153 -95.937363 
	RAISE NOTICE ' Inserting % more lights in Group %. Total now will be %. Wait.... ', p_number_of_qat_lights, 'System', p_total_number_of_qat_lights;
	PERFORM InsertByLatLng ( p_number_of_qat_lights, 'System', 35.8667, -88.8610 , 0.01, 0.02,p_create_user);
	
END
$$
LANGUAGE 'plpgsql';	

CREATE FUNCTION PortlandData5()
RETURNS void AS
$$
DECLARE 
	p_create_user varchar(20);
	p_grouping_id int;
	
	p_number_of_qat_lights integer;
BEGIN

	p_create_user := 'QAT';


	-- Hillsdale 	45.4801142	-122.6858711
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '134', 'Hillsdale';
	PERFORM InsertByLatLng ( 134, 'Hillsdale', 35.1667, -78.9610, 0.01, 0.02,p_create_user);

	-- Beaverton	45.488119	-122.804012
	RAISE NOTICE ' Inserting % lights in Group %. Wait.... ', '10', 'Beaverton';
	PERFORM InsertByLatLng ( 10, 'Beaverton', 36.1667, -77.0610, 0.01, 0.02,p_create_user);


END
$$
LANGUAGE 'plpgsql';	

SELECT PortlandData1();
SELECT PortlandData2();

-- Clean up analytics tables to remove rows created by triggers, in order to acept only the messages created with 'BOT' user and let create_random_status_message take cara about new messages.
--delete from analytics_installed where create_user != 'BOT';
delete from analytics_warnings where create_user != 'BOT';
delete from analytics_alarms where create_user != 'BOT';
--delete from analytics_group_by_date where create_user != 'BOT';

SELECT create_random_status_message(100);
	