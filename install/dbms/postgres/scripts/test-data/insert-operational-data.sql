-- p_number_of_lights: The number of lights that will receive test data
-- p_consumption_seed: Consumption will decrease from this value
-- p_current_seed: Current will float around this value
-- p_voltage_seed: Voltage will float around this value
-- p_max_days: Generate data for this number of days
-- p_initial_light_id: Start from this light
CREATE FUNCTION InsertOperationalData (p_number_of_lights int ,p_consumption_seed int , p_current_seed float ,p_voltage_seed int , p_max_days int , p_initial_light_id int, p_create_user varchar(20), tenant_id int)
RETURNS void AS
$$
DECLARE
	 p_rate integer ARRAY;
	 p_status_message_id int;
	 consumption_value int;
	 current_value float;
	 voltage_value float;
	 day_counter int;
	 light_counter int;
	 p_light_id int;
	 consumption_id int;
	 current_id int;
	 voltage_id int;
	 status_active_id int;
	 substatus integer;
	 record_date timestamp with time zone;
	 p_statusLast int;
	 p_substatusLast int;
BEGIN
	--Creating random status messages.

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
	
	consumption_id := 1;
	current_id := 2;
	voltage_id := 3;
	status_active_id := 3;
	
	p_light_id := p_initial_light_id;
	light_counter := 0;
	
	WHILE light_counter < p_number_of_lights
	LOOP
		BEGIN
			
			consumption_value := p_consumption_seed;
			current_value := p_current_seed;
			voltage_value := p_voltage_seed;
			day_counter := 1;
			
			--RAISE NOTICE 'light_id  = %',CAST(light_id AS VARCHAR(20)) ;
			
			WHILE day_counter < p_max_days
			LOOP
				BEGIN
					
					status_active_id := mod(round((random() * 1000))::INTEGER,4) + 1;
				
					IF status_active_id = 1
					THEN
						BEGIN
							substatus := mod(round((random() * 1000))::INTEGER,5) + 1;
						END;
					ELSE
						IF status_active_id = 2
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
				 
					-- This is the create_date. It will decrease 1 day until p_max_days
					record_date := CURRENT_TIMESTAMP + cast(cast((p_max_days-day_counter) * -1 as varchar(3)) || ' days' as interval);
					
					p_statusLast := (  SELECT sm.status_id
									  FROM status_message AS sm 
										   LEFT JOIN status_message_status_subtype smss
										   ON sm.status_message_id = smss.status_message_id
									 WHERE sm.light_id = p_light_id 
									   AND sm.message_date BETWEEN (record_date::date - interval '1 month ') AND (record_date::date + interval '1 day ')::date
									 ORDER BY sm.message_date  DESC
									 LIMIT 1
								);
					
					p_substatusLast := (  SELECT smss.status_message_id
									  FROM status_message AS sm 
										   LEFT JOIN status_message_status_subtype smss
										   ON sm.status_message_id = smss.status_message_id
									 WHERE sm.light_id = p_light_id 
									   AND sm.message_date BETWEEN (record_date::date - interval '1 month ') AND (record_date::date + interval '1 day ')::date
									 ORDER BY sm.message_date  DESC
									 LIMIT 1
								);

					IF (p_statusLast <> status_active_id AND p_substatusLast <> substatus) THEN
						BEGIN	
							INSERT INTO status_message
								(message_date
								,light_id
								,create_date
								,status_id
								,create_user
								,tenant_id
								,message_type)
							VALUES
								(record_date
								,p_light_id
								,CURRENT_TIMESTAMP
								,status_active_id 
								,p_create_user
								,tenant_id
								,2) RETURNING status_message_id INTO p_status_message_id;
			   				
							DELETE FROM current_alarm_warning_message WHERE light_id = p_light_id;
												
							INSERT INTO current_alarm_warning_message(light_id, status_message_id, status_message_type, status_messages_status_id, 
													  status_messages_status_subtype_id, tenant_id, message_date)
											  VALUES (p_light_id, p_status_message_id, '2', status_active_id, 
													  substatus, tenant_id, record_date);
							UPDATE light
								SET   current_status_message_id = p_status_message_id
						    WHERE light_id = p_light_id;
							
							IF substatus IS NOT NULL THEN
						
								INSERT INTO status_message_status_subtype (status_message_id   ,status_subtype_id          ,create_date        ,create_user)
																   VALUES (p_status_message_id ,substatus ,CURRENT_TIMESTAMP  ,p_create_user);
							
			
							    PERFORM upd_analytics_alarms_test(p_status_message_id, substatus,p_create_user,null,'+', record_date);
							
							END IF;
						
							-- Consumption
							consumption_value := consumption_value - round(random()*10);
							INSERT INTO operational_data_value
								(status_message_id
								,operational_data_type_id
								,value
								,create_user
								,create_date)
							VALUES
								(p_status_message_id
								,consumption_id
								,consumption_value
								,p_create_user
								,record_date);
							
							-- Current
							INSERT INTO operational_data_value
								(status_message_id
								,operational_data_type_id
								,value
								,create_user
								,create_date)
							VALUES
								(p_status_message_id
								,current_id
								,current_value - (random()/1000)
								,p_create_user
								,record_date);
							
							-- Voltage
							INSERT INTO operational_data_value
								(status_message_id
								,operational_data_type_id
								,value
								,create_user
								,create_date)
							VALUES
								(p_status_message_id
								,voltage_id
								,voltage_value - random()
								,p_create_user
								,record_date);
						END;
					END IF;
					day_counter := day_counter + 1;
				END;
			END LOOP;
			p_light_id := p_light_id + 1;
			light_counter := light_counter + 1;
		END;
	END LOOP;
END
$$
LANGUAGE 'plpgsql';