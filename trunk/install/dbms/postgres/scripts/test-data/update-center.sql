-- Updates center point for all groups
CREATE FUNCTION update_center ()
RETURNS void AS
$$
DECLARE 
	p_grouping_count int;
	p_counter int;
BEGIN

	p_grouping_count := (select count(*) from grouping);
	p_counter := 1;
	
	WHILE p_counter < p_grouping_count + 1
	LOOP
		BEGIN
			PERFORM upd_group_center(p_counter);
			p_counter := p_counter + 1;
		END;
	END LOOP;
END
$$
LANGUAGE 'plpgsql';	

SELECT update_center();

DROP FUNCTION update_center ();


-- Updates center point for all lights

SELECT upd_map_center(1);
SELECT upd_map_center(2);


-- Updates center point for all groups
CREATE FUNCTION update_center_schedule ()
RETURNS void AS
$$
DECLARE 
	p_schedule_count int;
	p_counter int;
BEGIN

	p_schedule_count := (select count(*) from schedule);
	p_counter := 1;
	
	WHILE p_counter < p_schedule_count + 1
	LOOP
		BEGIN
			PERFORM upd_schedule_center(p_counter);
			p_counter := p_counter + 1;
		END;
	END LOOP;
END
$$
LANGUAGE 'plpgsql';	

SELECT update_center_schedule();

DROP FUNCTION update_center_schedule ();