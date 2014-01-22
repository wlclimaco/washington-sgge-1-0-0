DELETE FROM light_daily_consumption;
DELETE FROM analytics_group_by_date;
DELETE FROM analytics_consumption;
DELETE FROM operational_data_value;
DELETE FROM notification_history_alert;
DELETE FROM notification_history;
DELETE FROM light_configuration;
DELETE FROM light;

-- Pattern

--INSERT INTO light( light_id, light_type, lifecycle_state, light_state, create_date, modified_date, create_user, modify_user, tenant_id, flexnet_id,
--            address, city, state, zip_code, county, timezone, latitude, longitude, notification_history_id, pole_id, protected, intensity, ecomode,
--            ecomode_replaced_type, ecomode_replaced_wattage, calculate_retroactive_ecomode, blink_level, override, override_per_date, override_create_date)
--    VALUES (1, -- light_id
--            1, -- light_type
--            1, -- lifecycle_state
--            1, -- light_state
--            CURRENT_TIMESTAMP, -- create_date
--            CURRENT_TIMESTAMP, -- modified_date
--            'system', -- create_user
--            'system', -- modify_user
--            1, -- tenant_id
--            '123456', -- flexnet_id
--            '450, st mallet', -- address
--            'Raleigh', -- city
--            'NC', -- state
--            '10001', -- zip_code
--            'County', -- county
--            'America/New_York', -- timezone
--            35.9167028561661, -- Latitude
--            78.5109569922919, -- Longitude
--            null, -- notification_history_id
--            1, -- pole_id
--            false, -- protected
--            1, -- intensity
--            null, -- ecomode
--            null, -- ecomode_replaced_type
--            null, -- ecomode_replaced_wattage
--           false, -- calculate_retroactive_ecomode
--            1, -- blink_level
--            1, -- override
--            null, -- override_per_date
--            null); -- override_create_date

CREATE OR REPLACE FUNCTION insert_light_test_data()
  RETURNS void AS
$$
DECLARE
    v_refcursor refcursor;
    v_record RECORD;
    idx integer;
    lightId integer;
    notifHistId1 integer;
    notifHistId2 integer;
    consumptionValue integer;
	lightamount integer;
    lat double precision;
    lon double precision;
	percentage double precision;
	latOriginal double precision;

BEGIN
    ------------------------------------------------------
	lightamount := 5000; -- PLEASE SET THE LIGHT AMOUNT --
	------------------------------------------------------
	-- CAUTION: MAX OF 100.000 LIGHTS. THE PERFORMANCE MAY NOT BE GOOD

	idx := 0;
    lat := 35.9167028561661;
    lon := -78.5817566747656;
    consumptionValue := 0;
	percentage  := 0.10;
	latOriginal	:= lat;
	select setval('notification_history_status_message_id_seq',100000) into notifHistId1;
	select setval('light_id_seq',100000) into lightId;


    LOOP
        idx := idx + 1;
    EXIT WHEN idx = lightamount;
        RAISE INFO 'Inserting light: %',idx;

	-- the logic below will put lights as grid on map.
	IF(idx >= lightamount * percentage)
	THEN
    	lat := latOriginal;
		percentage := percentage + 0.10;
	END IF;

		lat := lat + 0.0010;
		lon := lon - 0.0010;


	-- Setup high ids.
    select nextval('notification_history_status_message_id_seq') into notifHistId1;
	select nextval('light_id_seq') into lightId;
    notifHistId2 := notifHistId1+100001;

	INSERT INTO light( light_id, light_type, lifecycle_state, light_state, create_date, modified_date, create_user, modify_user, tenant_id, flexnet_id,
            address, city, state, zip_code, county, timezone, latitude, longitude, notification_history_id, pole_id, protected, intensity, ecomode,
            ecomode_replaced_type, ecomode_replaced_wattage, calculate_retroactive_ecomode, blink_level, override, override_per_date, override_create_date)
        VALUES (lightId, -- light_id
            1, -- light_type
            (round(random() * 2 + 3)), -- lifecycle_state (valid values 3,4 or 5)
            (round(random() * 4 + 1)), -- light_state
            CURRENT_TIMESTAMP, -- create_date
            CURRENT_TIMESTAMP, -- modified_date
            'system', -- create_user
            'system', -- modify_user
            1, -- tenant_id
            idx, -- flexnet_id
            '450, st mallet', -- address
            'Raleigh', -- city
            'NC', -- state
            '10001', -- zip_code
            'County', -- county
            'America/New_York', -- timezone
            lat, -- Latitude
            lon, -- Longitude
            null, -- notification_history_id
            lightId, -- pole_id
            false, -- protected
            (round(random() * 5 + 1)), -- intensity
            null, -- ecomode
            null, -- ecomode_replaced_type
            null, -- ecomode_replaced_wattage
           false, -- calculate_retroactive_ecomode
            1, -- blink_level
            1, -- override
            null, -- override_per_date
            null); -- override_create_date

	   notifHistId1 := notifHistId1 + 1;

       -- Insert new BINDING notification_history for this light
       INSERT INTO notification_history (notification_history_id, light_id, lifecycle_state, notification_type,
        message_date, create_date, update_date, create_user, precedence,
        simple_notification, notification_transation_id) values (notifHistId1, lightId, 3, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SYSTEM', 1, false, 'tx');

       notifHistId2 := notifHistId2 + 1;

       -- Insert a random state with 1 alert
       INSERT INTO notification_history (notification_history_id, light_id, lifecycle_state, notification_type,
        message_date, create_date, update_date, create_user, precedence,
        simple_notification, notification_transation_id) values (notifHistId2, lightId, (round(random() * 2 + 3)), 2, CURRENT_TIMESTAMP + INTERVAL '1 second', CURRENT_TIMESTAMP + INTERVAL '1 second', CURRENT_TIMESTAMP + INTERVAL '1 second', 'SYSTEM', 1, false, 'tx');

       -- Insert a random alert
       INSERT INTO notification_history_alert(
        alert_subtype_id, notification_history_id, create_date, update_date,
        message_date, create_user, modify_user, modified_date)
	    VALUES ((round(random() * 5 + 1)), notifHistId2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SYSUSER', 'SYSUSER', CURRENT_TIMESTAMP);


         -- Insert operational data value
	    consumptionValue := 1;

       INSERT INTO operational_data_value(value, create_user, create_date, operational_data_type_id, notification_history_id) VALUES (consumptionValue, 'SYSUSER', CURRENT_TIMESTAMP - INTERVAL '7 day', 1, notifHistId2);

	   consumptionValue := consumptionValue + 1;

	   INSERT INTO operational_data_value(value, create_user, create_date, operational_data_type_id, notification_history_id) VALUES (consumptionValue, 'SYSUSER', CURRENT_TIMESTAMP - INTERVAL '6 day', 1, notifHistId2);

       consumptionValue := consumptionValue + 2;

	   INSERT INTO operational_data_value(value, create_user, create_date, operational_data_type_id, notification_history_id) VALUES (consumptionValue, 'SYSUSER', CURRENT_TIMESTAMP - INTERVAL '5 day', 1, notifHistId2);

	   consumptionValue := consumptionValue + 1;

	   INSERT INTO operational_data_value(value, create_user, create_date, operational_data_type_id, notification_history_id) VALUES (consumptionValue, 'SYSUSER', CURRENT_TIMESTAMP - INTERVAL '4 day', 1, notifHistId2);

	   consumptionValue := consumptionValue + 3;

	   INSERT INTO operational_data_value(value, create_user, create_date, operational_data_type_id, notification_history_id) VALUES (consumptionValue, 'SYSUSER', CURRENT_TIMESTAMP - INTERVAL '3 day', 1, notifHistId2);

	   consumptionValue := consumptionValue + 1;

	   INSERT INTO operational_data_value(value, create_user, create_date, operational_data_type_id, notification_history_id) VALUES (consumptionValue, 'SYSUSER', CURRENT_TIMESTAMP - INTERVAL '2 day', 1, notifHistId2);

	   consumptionValue := consumptionValue + 4;

	   INSERT INTO operational_data_value(value, create_user, create_date, operational_data_type_id, notification_history_id) VALUES (consumptionValue, 'SYSUSER', CURRENT_TIMESTAMP - INTERVAL '1 day', 1, notifHistId2);


       -- INSERT LIGHT CONFIG
       INSERT INTO light_configuration(
		    light_id, housing, housing_color, dimmable, lamp_type_wattage_dimmable,
		    wattage_rating, input_voltage_range, color_temperature, manufacturer,
		    firmware_version, model_number, lower_assembly_serial_number,
		    upper_assembly_serial_number, frequency, date_added, bulb_serial_number,
		    ballast_serial_number, customer_serial_number, create_date, modified_date,
		    create_user, modified_user)
	    VALUES (lightId,
		    'Shoebox',
		    'Gray',
		    false, -- dimmable
		    '200W Induction',
		    '200W', -- wattage_rating
		    '90-300V', -- input_voltage_range
		    '3,000K', --  color_temperature
		    'Sensus',
		    '1.0.0', -- firmware_version
		    '539449037C2321', -- model_number
		    'Lower 3', -- lower_assembly_serial_number
		    'Upper 3', -- upper_assembly_serial_number
		    null,
		    CURRENT_TIMESTAMP, -- date_added
		    'Bulb SN', -- bulb_serial_number
		    'Ballast SN', -- ballast_serial_number
		    'Customer SN', -- customer_serial_number
		    CURRENT_TIMESTAMP,
		    CURRENT_TIMESTAMP,
		    'SYSTEM',
		    'SYSTEM');

		-- Inserts last operational data
		INSERT INTO light_last_operational_data(
            light_id, ac_voltage, ac_voltage_min, ac_voltage_min_date, ac_voltage_max,
            ac_voltage_max_date, ac_current, ac_current_min, ac_current_min_date,
            ac_current_max, ac_current_max_date, consumption, consumption_min,
            consumption_max, create_date, modified_date, create_user, modified_user)
   		 VALUES (lightId, 12, 11, CURRENT_TIMESTAMP, 15,
            CURRENT_TIMESTAMP, 13, 12, CURRENT_TIMESTAMP,
            15, CURRENT_TIMESTAMP, 2, 11,
            15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SYSUSER', 'SYSUSER');


    END LOOP;

END
$$
LANGUAGE plpgsql;


SELECT "insert_light_test_data"();


CREATE OR REPLACE FUNCTION consumption_fix(param_lightid integer, param_tenantid integer)
  RETURNS void AS
$$
DECLARE
    v_refcursor refcursor;
    v_record RECORD;
    v_refcursor_group refcursor;
    v_record_group RECORD;
    v_tenant_id int;
    v_lamp_type varchar(4094);
    v_analytic_group_id int;
    v_analytic_group_by_date_id int;
    v_property_light_source integer;
    v_op_data_type_consumption integer;
    v_light_type_id integer;
    v_last_consumption double precision;
    v_current double precision;
    v_delta_consumption double precision;
    v_consumption double precision;
BEGIN



    v_tenant_id := param_tenantid;
    v_op_data_type_consumption := 1;
    v_property_light_source := 51;

    -- Get operational_data_value to recalculate the consumption.
    -- The operational_data_value has the original history of all consumptions.
    OPEN v_refcursor FOR (  select odv.*,nh.light_id
			    from operational_data_value odv join notification_history nh on odv.notification_history_id = nh.notification_history_id
			    where odv.operational_data_type_id = 1 and nh.light_id = param_lightid order by odv.create_date
			  );

     -- For each consumption entry of this light...
     LOOP
	FETCH v_refcursor INTO v_record;
	EXIT WHEN NOT FOUND;

        v_current := v_record.value;

	-- Get the lastest consumption before the current record. (sub-query)
	-- Select the max value of all consumptions before the current record, this MAX() is necessary because some times the light has weird behaviors
	-- that resets the consumptions, in order to ignore reseted values let's pick the MAX.
	v_last_consumption :=  (SELECT max(value) from (
					SELECT value
					 FROM operational_data_value odv
					     ,notification_history nh
					WHERE nh.light_id = param_lightid
					  AND odv.create_date != current_timestamp
					  AND odv.notification_history_id = nh.notification_history_id
					  AND odv.operational_data_type_id = 1
					 AND odv.create_date < v_record.create_date
					ORDER BY odv.create_date desc) as vvf);


	-- Delta calc ( history consumption - last consumption before the history entry ).
	v_delta_consumption := v_record.value - COALESCE(v_last_consumption,0);

 RAISE INFO 'Delta: %',v_delta_consumption;

        -- if the delta is not null and the last consumption is not null and also the current history entry is greater
        -- than the last consumption then persist
	if (v_delta_consumption is not null)
        THEN

		v_light_type_id := 1;

		-- Select entries for the group name All.
		v_analytic_group_id := (SELECT analytic_group_id FROM analytics_group WHERE analytic_group_name = 'All' AND tenant_id = v_tenant_id);
		v_analytic_group_by_date_id := get_analytics_group_by_date_pk(v_analytic_group_id, v_record.create_date,v_record.create_user);

		-- persist the analytics consumption
		PERFORM upsert_analytics_consumption(v_analytic_group_by_date_id, v_light_type_id, v_record.create_user, v_delta_consumption);

		-- persist the light daily consumption
		v_consumption := (select consumption from light_daily_consumption where light_id = param_lightid AND (date_trunc('day',v_record.create_date) = consumption_day));

		IF v_consumption is null THEN v_consumption := 0; END IF;

		-- Try to insert the light_daily_consumption.
		UPDATE light_daily_consumption SET consumption = (v_consumption + v_delta_consumption) WHERE light_id = param_lightid AND (date_trunc('day',v_record.create_date) = consumption_day);
		IF NOT FOUND THEN
		     INSERT INTO light_daily_consumption(light_id,consumption_day,consumption)
		     VALUES(param_lightid,date_trunc('day',v_record.create_date),v_delta_consumption);
		END IF;

        END IF; -- if test delta



     END LOOP;
END
$$
LANGUAGE plpgsql;

-- **************************************************************************
-- *** This function selects all lights by tenant and calls the fix proc  ***
-- **************************************************************************
CREATE OR REPLACE FUNCTION consumption_fix_all_lights(param_tenantid integer)
  RETURNS void AS
$$
DECLARE
    v_record RECORD;
    v_refcursor_group refcursor;
    v_refcursor refcursor;
    v_i integer;
BEGIN
     -- Select all lights by tenant.
     OPEN v_refcursor FOR ( select light_id from light where tenant_id = param_tenantid);
     v_i:=0;
     LOOP
	v_i:=v_i+1;
	RAISE INFO  '% lights processed',v_i;
	FETCH v_refcursor INTO v_record;
	EXIT WHEN NOT FOUND;
	-- Call consumption fix function.
	PERFORM consumption_fix(v_record.light_id,param_tenantid);
     END LOOP;
END
$$
LANGUAGE plpgsql;

-- Run the fix for all lights
select "consumption_fix_all_lights"(1); --// Tenant: 1.




