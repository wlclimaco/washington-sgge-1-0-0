
CREATE FUNCTION InsertPropertyValue (p_light_id int , p_create_user varchar(20), p_lat float, p_lng float)
RETURNS void AS
$$
DECLARE
	p_lamp_type varchar(20);
	p_lamp_type_valid_value int;
	p_light_source char;
	p_wattage varchar(20);
	p_lamp_type_wattage varchar(20);
	p_property_valid_value_id int;
	p_sunrise_offset varchar(20);
	p_sunset_offset varchar(20);
	p_dimmable varchar(20);
	p_housing varchar(20);
	p_housing_color varchar(20);
	p_lower_assembly_serial varchar(20);
	p_upper_assembly_serial varchar(20);
	p_bulb_serial_number varchar(20);

	
	
	p_random float;
BEGIN
	-- properties for the light
	p_random := random();
	IF (p_random <0.2)
	THEN
		BEGIN
			p_lamp_type := 'Induction';
			p_light_source := 'I';
			p_wattage := '40W';
			p_lamp_type_wattage := '40W Induction';
			p_property_valid_value_id := 19;
			p_lower_assembly_serial := 'Lower 1' ;
			p_upper_assembly_serial := 'Upper 1';
			p_bulb_serial_number := 'Bulb1';
		END;
	ELSE
		IF (p_random <0.4)
		THEN
			BEGIN
				p_lamp_type := 'Induction';
				p_light_source := 'I';
				p_wattage := '80W';
				p_lamp_type_wattage := '80W Induction';
				p_property_valid_value_id := 21;
				p_lower_assembly_serial := 'Lower 4' ;
				p_upper_assembly_serial := 'Upper 4';
				p_bulb_serial_number := 'Bulb 4';
			END;
		ELSE
			IF (p_random <0.6)
			THEN
				BEGIN
					p_lamp_type := 'Induction';
					p_light_source := 'I';
					p_wattage := '120W';
					p_lamp_type_wattage := '120W Induction';
					p_property_valid_value_id := 23;
					p_lower_assembly_serial := 'Lower 2' ;
					p_upper_assembly_serial := 'Upper 2';
					p_bulb_serial_number := 'Bulb 2';
				END;
			ELSE
				BEGIN
					p_lamp_type := 'Induction';
					p_light_source := 'I';
					p_wattage := '200W';
					p_lamp_type_wattage := '200W Induction';
					p_property_valid_value_id := 27;
					p_lower_assembly_serial := 'Lower 3' ;
					p_upper_assembly_serial := 'Upper 3';
					p_bulb_serial_number := 'Bulb 3';
				END;
			END IF;
		END IF;
	END IF;

	p_random := random();
	IF (p_random <0.2)
	THEN
		BEGIN
			p_sunrise_offset := '5';
		END;
	ELSE
		IF (p_random <0.4)
		THEN
			BEGIN
				p_sunrise_offset := '10';
			END;
		ELSE
			IF (p_random <0.6)
			THEN
				BEGIN
					p_sunrise_offset := '-5';
				END;
			ELSE
				BEGIN
					p_sunrise_offset := '-10';
				END;
			END IF;
		END IF;
	END IF;
				
	p_random := random();
	IF (p_random <0.4)
	THEN
		BEGIN
			p_dimmable := 'True';
			
		END;
	ELSE
		BEGIN
			p_dimmable := 'False';
			
		END;
	END IF;
	

	p_random := random();
	IF (p_random <0.2)
	THEN
		BEGIN
			p_housing := 'Street';
		END;
	ELSE
		IF (p_random >0.2 and p_random <0.3)
		THEN
			BEGIN
				p_housing := 'Decorative';
			
			END;
		ELSE
			IF (p_random >0.3 and p_random <0.4)
			THEN
				BEGIN
					p_housing := 'High Bay';
			
				END;
			ELSE
				IF(p_random >0.4 and p_random <0.5)
				THEN
					BEGIN
						p_housing := 'Low Bay';
					
					END;
				ELSE
					IF(p_random >0.5 and p_random <0.6)
					THEN
						BEGIN
							p_housing := 'Tunnel';
					
						END;
					ELSE
						IF(p_random >0.6 and p_random <0.7)
						THEN
							BEGIN
								p_housing := 'Shoebox';
					
							END;
						ELSE 
							IF(p_random >0.7 and p_random <0.8)
							THEN
								BEGIN
									p_housing := 'Flood';
					
								END;
							ELSE
								IF(p_random >0.8 and p_random <0.9)
								THEN
									BEGIN
										p_housing := 'Wall Pack';
										
									END;
								ELSE
									BEGIN
										p_housing := 'Post Top';
										
									END;
								END IF;
							END IF;
						END IF;	
					END IF;
				END IF;		
			END IF;
		END IF;
	END IF;
	
	p_random := random();
	IF (p_random <0.2)
	THEN
		BEGIN
			p_housing_color:= 'Gray';
			p_property_valid_value_id := 54;
		END;
	ELSE
		IF (p_random <0.4)
		THEN
			BEGIN
				p_housing_color := 'Black';
				p_property_valid_value_id := 55;
			END;
		ELSE
			IF (p_random <0.6)
			THEN
				BEGIN
					p_housing_color := 'Silver';
					p_property_valid_value_id := 56;
				END;
			ELSE
				BEGIN
					p_housing_color := 'Bronze';
					p_property_valid_value_id := 57;
				END;
			END IF;
		END IF;
	END IF;
	
	
	p_random := random();
	IF (p_random <0.2)
	THEN
		BEGIN
			p_sunset_offset := '5';
		END;
	ELSE
		IF (p_random <0.4)
		THEN
			BEGIN
				p_sunset_offset := '10';
			END;
		ELSE
			IF (p_random <0.6)
			THEN
				BEGIN
					p_sunset_offset := '-5';
				END;
			ELSE
				BEGIN
					p_sunset_offset := '-10';
				END;
			END IF;
		END IF;
	END IF;
	
	INSERT INTO light_property
	   (light_id
	   ,property_id
	   ,property_valid_value_id
	   ,property_value
	   ,create_date
	   ,create_user)
	VALUES
	 (p_light_id,1,null,'06:24:23',CURRENT_TIMESTAMP + cast('-3 days' as interval),p_create_user)
	,(p_light_id,2,null,p_sunrise_offset,CURRENT_TIMESTAMP + cast('-2 months' as interval),p_create_user)
	,(p_light_id,3,null,'20:24:23',CURRENT_TIMESTAMP + cast('-5 years' as interval),p_create_user)
	,(p_light_id,4,null,p_sunset_offset,CURRENT_TIMESTAMP + cast('-1 days' as interval),p_create_user)
	,(p_light_id,5,null,'01234567890123',CURRENT_TIMESTAMP + cast('-6 months' as interval),p_create_user)
	,(p_light_id,6,p_lamp_type_valid_value,p_lamp_type,CURRENT_TIMESTAMP + cast('-1 years' as interval),p_create_user)
	,(p_light_id,7,null,p_wattage,CURRENT_TIMESTAMP + cast('-3 days' as interval),p_create_user)
	,(p_light_id,8,null,'90-300V',CURRENT_TIMESTAMP + cast('-0 months' as interval),p_create_user)
	,(p_light_id,9,null,'3,000K',CURRENT_TIMESTAMP + cast('-1 years' as interval),p_create_user)
	,(p_light_id,10,null,p_create_user,CURRENT_TIMESTAMP + cast('-3 days' as interval),p_create_user)
	,(p_light_id,44,null,'539449037C2321',CURRENT_TIMESTAMP + cast('-5 months' as interval),p_create_user)
	,(p_light_id,12,null,'123-4546-987',CURRENT_TIMESTAMP + cast('-7 days' as interval),p_create_user)
	,(p_light_id,13,null,'04/16/2010',CURRENT_TIMESTAMP + cast('-0 days' as interval),p_create_user)
	,(p_light_id,14,null,'04/16/2010',CURRENT_TIMESTAMP + cast('-3 months' as interval),p_create_user)
	,(p_light_id,15,null,'1.0.0',CURRENT_TIMESTAMP + cast('-7 years' as interval),p_create_user)
	,(p_light_id,16,null,CAST((p_lat) AS VARCHAR(20)),CURRENT_TIMESTAMP + cast('-7 days' as interval),p_create_user)
	,(p_light_id,17,null,CAST((p_lng) AS VARCHAR(20)),CURRENT_TIMESTAMP + cast('-9 months' as interval),p_create_user)
	,(p_light_id,18,p_property_valid_value_id,p_lamp_type_wattage,CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)	
	,(p_light_id,28,null,'SE Stark St.',CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)
	,(p_light_id,30,null,'USA',CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)
	,(p_light_id,31,null,'Oregon',CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)
	,(p_light_id,32,null,'553447',CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)	
	,(p_light_id,45,null,p_lower_assembly_serial,CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)	
	,(p_light_id,46,null,p_upper_assembly_serial,CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)	
	,(p_light_id,47,null,p_bulb_serial_number,CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)	
	,(p_light_id,51,null,p_light_source,CURRENT_TIMESTAMP + cast('-1 months' as interval),p_create_user)
	,(p_light_id,55,null,p_dimmable,CURRENT_TIMESTAMP,p_create_user)
	,(p_light_id,56,null,p_housing,CURRENT_TIMESTAMP,p_create_user)
	,(p_light_id,75, p_property_valid_value_id, p_housing_color, CURRENT_TIMESTAMP, p_create_user);
	
	
	
END
$$
LANGUAGE 'plpgsql';		