DO $$
DECLARE
	v_rows INTEGER ;
	prop_schedule INTEGER[04] = ARRAY[1,2,3,4];
	prop_location INTEGER[08] = ARRAY[16,17,28,29,30,31,32,38];
	prop_op_data  INTEGER[09] = ARRAY[19,20,83,84,85,86,87,88,89];
	prop_config   INTEGER[17] = ARRAY[7,8,9,10,13,15,18,44,45,46,47,48,49,50,55,56,75];
BEGIN

	RAISE NOTICE 'Truncating tables';
	TRUNCATE light_schedule;
	TRUNCATE light_location;
	TRUNCATE light_last_operational_data;
	TRUNCATE light_configuration;

	RAISE NOTICE 'Disabling TRIGGERS';
	--disable triggers due analytics
	ALTER TABLE light DISABLE TRIGGER USER;
	ALTER TABLE operational_data_value DISABLE TRIGGER USER;
	ALTER TABLE "grouping" DISABLE TRIGGER USER;
	ALTER TABLE tenant DISABLE TRIGGER USER;
	ALTER TABLE smartpoint_grouping DISABLE TRIGGER USER;

	ALTER TABLE light_schedule DISABLE TRIGGER USER;
	ALTER TABLE light_location DISABLE TRIGGER USER;
	ALTER TABLE light_last_operational_data DISABLE TRIGGER USER;
	ALTER TABLE light_configuration DISABLE TRIGGER USER;

	SELECT count(distinct light_id)
	into v_rows
	from light_property
	where property_id = ANY(prop_schedule);

	RAISE NOTICE '% lights found to properties % (light_schedule) on light_property.',v_rows, prop_schedule;

	insert into light_schedule
	with prop as (
	SELECT light_id
	,(SELECT lp.property_value where lp.property_id = 1) as sunrise_time
	,(SELECT cast(lp.property_value as integer) where lp.property_id = 2) as sunrise_offset
	,(SELECT lp.property_value where lp.property_id = 3) as sunset_time
	,(SELECT cast(lp.property_value as integer) where lp.property_id = 4) as sunset_offset
	,lp.create_date
	,lp.modified_date
	,lp.create_user
	,lp.modify_user
	from light_property lp
	where property_id = ANY(prop_schedule)
	)
	SELECT light_id
	,min(sunrise_time) as sunrise_time
	,min(sunrise_offset) as sunrise_offset
	,min(sunset_time) as sunset_time
	,min(sunset_offset) as sunset_offset
	,min(create_date) as create_date
	,min(modified_date) as modified_date
	,min(create_user) as create_user
	,min(modify_user) as modified_user
	from prop
	group by light_id
	order by 1;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights INSERTED on light_schedule %.',v_rows,prop_schedule;

	SELECT count(distinct light_id)
	into v_rows
	from light_property
	where property_id = ANY(prop_location);

	RAISE NOTICE '% lights found to properties % (light_location) on light_property.',v_rows, prop_location;

	insert into light_location
	with prop as (
	select light_id
	,(select lp.property_value where lp.property_id = 38) as timezone
	,(select cast(lp.property_value as double precision) where lp.property_id = 16) as latitude
	,(select cast(lp.property_value as double precision) where lp.property_id = 17) as longitude
	,(select lp.property_value where lp.property_id = 28) as street_name
	,(select lp.property_value where lp.property_id = 29) as city_name
	,(select lp.property_value where lp.property_id = 30) as county_name
	,(select lp.property_value where lp.property_id = 31) as state_name
	,(select lp.property_value where lp.property_id = 32) as zip_code
	,lp.create_date
	,lp.modified_date
	,lp.create_user
	,lp.modify_user
	from light_property lp
	where property_id = ANY(prop_location)
	)
	select light_id
	,min(timezone) as timezone
	,min(latitude) as latitude
	,min(longitude) as longitude
	,min(street_name) as street_name
	,min(city_name) as city_name
	,min(county_name) as county_name
	,min(state_name) as state_name
	,min(zip_code) as zip_code
	,min(create_date) as create_date
	,min(modified_date) as modified_date
	,min(create_user) as create_user
	,min(modify_user) as modified_user
	from prop
	group by light_id
	order by 1;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights INSERTED on light_location %.',v_rows,prop_location;

	select count(distinct light_id)
	into v_rows
	from light_property
	where property_id = ANY(prop_op_data);

	RAISE NOTICE '% lights found to properties % (light_last_operational_data) on light_property.',v_rows, prop_op_data;

	insert into light_last_operational_data
	with prop as (
	select light_id
	,(select cast(lp.property_value as double precision) where lp.property_id = 19) as ac_voltage
	,(select cast(lp.property_value as double precision) where lp.property_id = 83) as ac_voltage_min
	,(select coalesce(lp.modified_date,lp.create_date)::timestamp without time zone where lp.property_id = 83) as ac_voltage_min_date
	,(select cast(lp.property_value as double precision) where lp.property_id = 84) as ac_voltage_max
	,(select coalesce(lp.modified_date,lp.create_date)::timestamp without time zone where lp.property_id = 84) as ac_voltage_max_date
	,(select cast(lp.property_value as double precision) where lp.property_id = 20) as ac_current
	,(select cast(lp.property_value as double precision) where lp.property_id = 85) as ac_current_min
	,(select coalesce(lp.modified_date,lp.create_date)::timestamp without time zone where lp.property_id = 85) as ac_current_min_date
	,(select cast(lp.property_value as double precision) where lp.property_id = 86) as ac_current_max
	,(select coalesce(lp.modified_date,lp.create_date)::timestamp without time zone where lp.property_id = 86) as ac_current_max_date
	,(select cast(lp.property_value as double precision) where lp.property_id = 87) as consumption
	,(select cast(lp.property_value as double precision) where lp.property_id = 88) as consumption_min
	,(select cast(lp.property_value as double precision) where lp.property_id = 89) as consumption_max
	,(lp.create_date)::timestamp without time zone
	,(select lp.modified_date where lp.property_id = 19)::timestamp without time zone as modified_date
	,lp.create_user
	,lp.modify_user
	from light_property lp
	where property_id = ANY(prop_op_data)
	)
	select light_id
	,min(ac_voltage) as ac_voltage
	,min(ac_voltage_min) as ac_voltage_min
	,min(ac_voltage_min_date) as ac_voltage_min_date
	,min(ac_voltage_max) as ac_voltage_max
	,min(ac_voltage_max_date) as ac_voltage_max_date
	,min(ac_current) as ac_current
	,min(ac_current_min) as ac_current_min
	,min(ac_current_min_date) as ac_current_min_date
	,min(ac_current_max) as ac_current_max
	,min(ac_current_max_date) as ac_current_max_date
	,min(consumption) as consumption
	,min(consumption_min) as consumption_min
	,min(consumption_max) as consumption_max
	,min(create_date) as create_date
	,min(modified_date) as modified_date
	,min(create_user) as create_user
	,min(modify_user) as modified_user
	from prop
	group by light_id
	order by 1;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights INSERTED on light_last_operational_data %.',v_rows,prop_op_data;

	select count(distinct light_id)
	into v_rows
	from light_property
	where property_id = ANY(prop_config);

	RAISE NOTICE '% lights found to properties % (light_configuration) on light_property.',v_rows, prop_config;

	insert into light_configuration
	with prop as (
	select light_id
	,(select lp.property_value where lp.property_id = 56) as housing
	,(select lp.property_value where lp.property_id = 75) as housing_color
	,(select lp.property_value where lp.property_id = 55) as dimmable
	,(select lp.property_value where lp.property_id = 18) as lamp_type_wattage_dimmable
	,(select lp.property_value where lp.property_id = 7) as wattage_rating
	,(select lp.property_value where lp.property_id = 8) as input_voltage_range
	,(select lp.property_value where lp.property_id = 9) as color_temperature
	,(select lp.property_value where lp.property_id = 10) as manufacturer
	,(select lp.property_value where lp.property_id = 15) as firmware_version
	,(select lp.property_value where lp.property_id = 44) as model_number
	,(select lp.property_value where lp.property_id = 45) as lower_assembly_serial_number
	,(select lp.property_value where lp.property_id = 46) as upper_assembly_serial_number
	,(select lp.property_value where lp.property_id = 50) as frequency
	,(select cast(lp.create_date as timestamp without time zone) where lp.property_id = 13) as date_added
	,(select lp.property_value where lp.property_id = 47) as bulb_serial_number
	,(select lp.property_value where lp.property_id = 48) as ballast_serial_number
	,(select lp.property_value where lp.property_id = 49) as customer_serial_number
	,lp.create_date
	,lp.modified_date
	,lp.create_user
	,lp.modify_user
	from light_property lp
	where property_id = ANY(prop_config)
	)
	select light_id
	,min(housing) as housing
	,min(housing_color) as housing_color
	,cast(min(dimmable) as boolean) as dimmable
	,min(lamp_type_wattage_dimmable) as lamp_type_wattage_dimmable
	,min(wattage_rating) as wattage_rating
	,min(input_voltage_range) as input_voltage_range
	,min(color_temperature) as color_temperature
	,min(manufacturer) as manufacturer
	,min(firmware_version) as firmware_version
	,min(model_number) as model_number
	,min(lower_assembly_serial_number) as lower_assembly_serial_number
	,min(upper_assembly_serial_number) as upper_assembly_serial_number
	,min(frequency) as frequency
	,min(date_added) as date_added
	,min(bulb_serial_number) as bulb_serial_number
	,min(ballast_serial_number) as ballast_serial_number
	,min(customer_serial_number) as customer_serial_number
	,min(create_date) as create_date
	,min(modified_date) as modified_date
	,min(create_user) as create_user
	,min(modify_user) as modified_user
	from prop
	group by light_id
	order by 1;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights INSERTED on light_configuration %.',v_rows,prop_config;

	UPDATE light
	set light_state = light_status;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights UPDATED[1].',v_rows;

	WITH upd as
		(select
		light_id
		,(select lp.property_value::smallint from light_property lp where lp.light_id = l.light_id and lp.property_id = 81) as light_status
		,(select lp.property_value::smallint from light_property lp where lp.light_id = l.light_id and lp.property_id  = 82 and lower(lp.property_value) <> 'null') as light_status_subtype
		,(select lp.property_value from light_property lp where lp.light_id = l.light_id and lp.property_id  = 12) as pole_id
		,(case (select lp.property_value lp from light_property lp where lp.light_id = l.light_id and lp.property_id = 51)
				WHEN 'I' THEN 1
				WHEN 'L' THEN 2
				WHEN 'O' THEN 3
				END)::smallint as light_type
		 from light l
		)
	UPDATE light
	set light_status = upd.light_status
	,light_status_subtype = upd.light_status_subtype
	,pole_id = upd.pole_id
	,light_type = upd.light_type
	from upd
	where light.light_id = upd.light_id;

	ALTER TABLE light ALTER COLUMN light_type SET NOT NULL;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights UPDATED[2].',v_rows;

	RAISE NOTICE 'Enabling TRIGGERS';
	ALTER TABLE light ENABLE TRIGGER USER;
	ALTER TABLE operational_data_value ENABLE TRIGGER USER;
	ALTER TABLE "grouping" ENABLE TRIGGER USER;
	ALTER TABLE tenant ENABLE TRIGGER USER;
	ALTER TABLE smartpoint_grouping ENABLE TRIGGER USER;

	ALTER TABLE light_schedule ENABLE TRIGGER USER;
	ALTER TABLE light_location ENABLE TRIGGER USER;
	ALTER TABLE light_last_operational_data ENABLE TRIGGER USER;
	ALTER TABLE light_configuration ENABLE TRIGGER USER;

	--dropping here because we need the tables to migrate data
	RAISE NOTICE 'Droping tables light_property/property_valid_value';
	DROP TABLE IF EXISTS light_property CASCADE;
	DROP TABLE IF EXISTS property_valid_value CASCADE;


	 ----------------------------------------------------------------------------------------------------------------
 --UPDATE SMARTPOINT_COLUMN ON CUSTOM SEARCH
 ----------------------------------------------------------------------------------------------------------------
 ----------------------------------------------------------------------------------------------------------------
UPDATE custom_search_property set custom_search_property_value = COALESCE(TB.NEW_VALUE,TB.OLD_VALUE)
FROM (
	SELECT custom_search_property_value AS OLD_VALUE ,
	CASE WHEN custom_search_property_value = 'lp_cur_stat.property_value' THEN 'lssm.label_key'
		WHEN custom_search_property_value = '13' THEN 'cfg.date_added'
		WHEN custom_search_property_value = '6' THEN 'cfg.lamp_type_wattage_dimmable'
		WHEN custom_search_property_value = '29' THEN 'loc.city_name'
		WHEN custom_search_property_value = '12' THEN 'light.pole_id'
		WHEN custom_search_property_value = '47' THEN 'cfg.bulb_serial_number'
		WHEN custom_search_property_value = '9' THEN 'cfg.color_temperature'
		WHEN custom_search_property_value = '15' THEN 'cfg.firmware_version'
		WHEN custom_search_property_value = '75' THEN 'cfg.housing_color'
		WHEN custom_search_property_value = '5' THEN 'cfg.ballast_serial_number'
		WHEN custom_search_property_value = '45' THEN 'cfg.lower_assembly_serial_number'
		WHEN custom_search_property_value = '44' THEN 'cfg.model_number'
		WHEN custom_search_property_value = '46' THEN 'cfg.upper_assembly_serial_number'
		WHEN custom_search_property_value = '45' THEN 'cfg.lower_assembly_serial_number'
		WHEN custom_search_property_value = '8' THEN 'cfg.input_voltage_range'

	END as NEW_VALUE
	FROM custom_search_property
	WHERE  property_id = 104
) TB

 WHERE
 custom_search_property_value = TB.OLD_VALUE;

 ----------------------------------------------------------------------------------------------------------------
 --UPDATE SMARTPOINT_COLUMN ON USER SETTINGS
 ----------------------------------------------------------------------------------------------------------------
 ----------------------------------------------------------------------------------------------------------------
 UPDATE user_settings_property SET user_setting_property_value = COALESCE(TB.NEW_VALUE,TB.OLD_VALUE)
FROM (
	SELECT user_setting_property_value AS OLD_VALUE ,
	CASE WHEN user_setting_property_value = 'lp_cur_stat.property_value' THEN 'lssm.label_key'
		WHEN user_setting_property_value = '13' THEN 'cfg.date_added'
		WHEN user_setting_property_value = '6' THEN 'cfg.lamp_type_wattage_dimmable'
		WHEN user_setting_property_value = '29' THEN 'loc.city_name'
		WHEN user_setting_property_value = '12' THEN 'light.pole_id'
		WHEN user_setting_property_value = '47' THEN 'cfg.bulb_serial_number'
		WHEN user_setting_property_value = '9' THEN 'cfg.color_temperature'
		WHEN user_setting_property_value = '15' THEN 'cfg.firmware_version'
		WHEN user_setting_property_value = '75' THEN 'cfg.housing_color'
		WHEN user_setting_property_value = '5' THEN 'cfg.ballast_serial_number'
		WHEN user_setting_property_value = '45' THEN 'cfg.lower_assembly_serial_number'
		WHEN user_setting_property_value = '44' THEN 'cfg.model_number'
		WHEN user_setting_property_value = '46' THEN 'cfg.upper_assembly_serial_number'
		WHEN user_setting_property_value = '45' THEN 'cfg.lower_assembly_serial_number'
		WHEN user_setting_property_value = '8' THEN 'cfg.input_voltage_range'

	END as NEW_VALUE
	FROM user_settings_property
	WHERE user_settings_id = 5 AND property_id = 104
) TB

 WHERE
 user_setting_property_value = TB.OLD_VALUE;




END $$

