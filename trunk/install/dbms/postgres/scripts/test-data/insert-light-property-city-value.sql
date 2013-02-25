CREATE FUNCTION InsertLightPropertyCityValue()
RETURNS void AS
$$
DECLARE
	p_light_id int;
	p_light_count int;
	p_property_id_city int;
    p_city_name character varying;
BEGIN
	p_light_id := 1;
	p_city_name := 'Morrisville';
	p_property_id_city := 29;
	p_light_count := (select count(*) from light);
	
	WHILE p_light_id < p_light_count + 1
	LOOP
		BEGIN
			INSERT INTO light_property
			(light_id
			,property_id
			,property_valid_value_id
			,property_value
			,create_date
			,create_user)
			VALUES
			(p_light_id,p_property_id_city,null,p_city_name,CURRENT_TIMESTAMP,'System');
			
			p_light_id:= p_light_id + 1;	
			
		END;		
	END LOOP;
END
$$
LANGUAGE plpgsql;

SELECT InsertLightPropertyCityValue();