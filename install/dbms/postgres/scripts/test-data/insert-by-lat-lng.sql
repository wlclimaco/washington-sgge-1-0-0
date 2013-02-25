-- p_number_of_lights: The number of lights that will be created
-- p_group_name: Light will be added to this group and tag
-- p_lat_seed: seed for Latitude
-- p_lng_seed: seed for Longitude
-- p_lat_factor: smaller number makes lights closer (horizontally)
-- p_lng_factor: smaller number makes lights closer (vertically)
CREATE FUNCTION InsertByLatLng (p_number_of_lights int ,p_group_name varchar(100) ,p_lat_seed float ,p_lng_seed float ,p_lat_factor float ,p_lng_factor float, p_create_user varchar(20))
RETURNS void AS
$$
DECLARE
	 p_light_count int; 
	 p_first_light_id int;
	 p_intensity int;

	 p_rni_id int; 
	 p_rni_tenant_code varchar(5); 
	 p_light_status smallint;

	 p_lat float;
	 p_lng float;
	 smp_id int;
	 p_light_id int;
	 p_message_id int;
	 p_lamp_type varchar(20);
	 p_wattage varchar(20);
	 p_lamp_type_wattage varchar(20);
	 p_property_valid_value_id int;
	 
	 
	 p_ecomode_replaced_type int; 
	 p_ecomode_replaced_wattage int; 

	-- The Group lights will be added to 
	 p_grouping_id int; 
	 p_tag_id int;
	 
	randomic float;
	
	status integer;
	substatus integer;
	statusDate timestamp;
	
	p_tenant_id integer;
	p_status_message_id integer;
	
BEGIN

	p_ecomode_replaced_type :=1; 
	p_ecomode_replaced_wattage := 10; 
	randomic := random();
	p_light_count:= 0;

	p_rni_id := (SELECT MAX (rni_id) from smartpoint)+1;
		
	IF (p_rni_id IS NULL)
	THEN
		p_rni_id := 1;
	END IF;
		
	SELECT t.rni_code FROM tenant t JOIN grouping g ON t.tenant_id = g.tenant_id WHERE g.name = p_group_name INTO p_rni_tenant_code;
	
	p_tenant_id := (SELECT tenant_id FROM tenant WHERE rni_code = p_rni_tenant_code);
	p_light_status := 0;
	p_intensity := 0;

	-- The Group lights will be added to 
	
	SELECT grouping_id from grouping WHERE name = p_group_name INTO p_grouping_id ;
	SELECT tag_id from tag WHERE name = p_group_name INTO p_tag_id;

	-- RAISE NOTICE ' p_rni_id = %, grouping_name = %, grouping_id = % ', cast(p_rni_id as varchar(4)), p_group_name, cast(p_grouping_id as varchar(10));
	
	WHILE p_light_count < p_number_of_lights
	LOOP
		BEGIN
			
			-- Insert Smartpoint
			INSERT INTO smartpoint
				(rni_id
				,smartpoint_type
				,tenant_id
				,create_user)
			VALUES
				(p_rni_id
				,1 -- Light
				,p_tenant_id
				,p_create_user) RETURNING smartpoint_id INTO smp_id;

			IF (random() > 0.5)
			THEN
				BEGIN
					p_lat := p_lat_seed - random()*p_lat_factor;
				END;
			ELSE
				BEGIN
					p_lat := p_lat_seed + random()*p_lat_factor;
				END;
			END IF;
			
			
			IF (random() > 0.5)
			THEN
				BEGIN
					p_lng := p_lng_seed - random()*p_lng_factor;
				END;
			ELSE
				BEGIN
					p_lng := p_lng_seed + random()*p_lng_factor;
				END;
			END IF;
			
			-- Insert Light using EPSG:3857 (the official Spherical Mercator)
			INSERT INTO light
				(light_status
				,smartpoint_id
				,tenant_id
				,create_user
				,intensity
				,blink_level
				,override)
			VALUES
				(p_light_status
				,smp_id
				,p_tenant_id
				,p_create_user
				,p_intensity
				,0
				,0) RETURNING light_id INTO p_light_id;
			
			IF (p_light_count = 0)
			THEN
				BEGIN
					p_first_light_id := p_light_id; 
				END;
			END IF;
				
			-- properties for the light
			PERFORM InsertPropertyValue(p_light_id, p_create_user, p_lat, p_lng);
			
			-- Add smartpoint to group
			INSERT INTO smartpoint_grouping
			   (smartpoint_id
			   ,grouping_id
			   ,create_user)
			VALUES
			   (smp_id
			   ,p_grouping_id
			   ,p_create_user);

			INSERT INTO smartpoint_tag
			   (smartpoint_id
			   ,tag_id
			   ,create_user)
			VALUES
			   (smp_id
			   ,p_tag_id
			   ,p_create_user);
						
			-- Insert consumption daily
			INSERT INTO light_daily_consumption(
						       light_id
						       ,consumption_day
						       ,consumption)
						
						SELECT 
						       p_light_id AS light_id
						       ,consumption_day
						       ,MOD(round((random() * 999999))::INTEGER,2000) + 500
						FROM (
							SELECT generate_series((current_date::date - INTERVAL '3 month'), (current_date - INTERVAL '1 day'), interval '1 day') AS consumption_day
						) AS CONSUMPTION;						
			
			-- Insert StatusMessage data
			status := mod(round((random() * 1000))::INTEGER,3);
				
			IF status = 1 THEN
			
				substatus := mod(round((random() * 1000))::INTEGER,5) + 1;
			
			ELSE IF status = 2 THEN
			
				substatus := mod(round((random() * 1000))::INTEGER,7) + 6;
			
				ELSE
					
					status := 3;
					substatus := null;
			
				END IF;
			END IF;
			
			statusDate := (SELECT CURRENT_TIMESTAMP + cast('-2 month' as interval));
													
			INSERT INTO status_message (light_id, status_id, message_date, create_date, create_user, tenant_id ,message_type)
						VALUES (p_light_id, status, statusDate, CURRENT_TIMESTAMP, p_create_user, p_tenant_id ,'2')
							 RETURNING status_message_id INTO p_status_message_id;
			
			DELETE FROM current_alarm_warning_message WHERE light_id = p_light_id;   
			
			INSERT INTO current_alarm_warning_message(light_id, status_message_id, status_message_type, status_messages_status_id, 
													  status_messages_status_subtype_id, tenant_id, message_date)
											  VALUES (p_light_id, p_status_message_id, '2', status, 
													  substatus, p_tenant_id, statusDate);
			UPDATE light
				SET   current_status_message_id = p_status_message_id
			WHERE light_id = p_light_id;
			
			IF substatus IS NOT NULL THEN
			
				INSERT INTO status_message_status_subtype (status_message_id, status_subtype_id, create_date, create_user)
												   VALUES (p_status_message_id, substatus, CURRENT_TIMESTAMP, p_create_user);

				PERFORM upd_analytics_alarms_test(p_status_message_id, substatus, p_create_user, null, '+', statusDate);

			END IF;

			-- InsertOperationalData p_number_of_lights int ,consumption_seed int , current_seed int ,voltage_seed int , max_days int , p_initial_light_id int
			PERFORM InsertOperationalData(1, 1000, 1.25, 120, 60, p_light_id, p_create_user, p_tenant_id);	
						
			-- Next light			
			p_light_count := p_light_count + 1;
			p_rni_id := p_rni_id + 1;

		END;
	END LOOP;
END
$$
LANGUAGE 'plpgsql';	