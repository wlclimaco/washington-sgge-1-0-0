CREATE FUNCTION InsertLightTimeZoneValue()
RETURNS void AS
$$
DECLARE
	p_light_id int;
	p_light_count int;
	p_property_id_time_zone int;
    p_time_zone character varying;
BEGIN
	p_light_id := 1;
	p_time_zone := 'America/New_York';
	p_property_id_time_zone := 38;
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
			(p_light_id,p_property_id_time_zone,null,p_time_zone,CURRENT_TIMESTAMP,'System');
			
			p_light_id:= p_light_id + 1;	
			
		END;		
	END LOOP;
END
$$
LANGUAGE plpgsql;

SELECT InsertLightTimeZoneValue();