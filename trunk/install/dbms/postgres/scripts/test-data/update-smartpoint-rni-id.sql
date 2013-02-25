
CREATE FUNCTION UpdateSmartpointRniId (p_smartpoint_id int, p_rni_id int, p_tenant_id int)
RETURNS void AS
$$
DECLARE 
	p_grouping_count int;
	p_counter int;
BEGIN
	UPDATE smartpoint 
	SET rni_id = p_rni_id
		,tenant_id = p_tenant_id
	WHERE smartpoint_id = p_smartpoint_id;
	
	UPDATE light 
	SET tenant_id = p_tenant_id
	WHERE smartpoint_id = p_smartpoint_id;
END
$$
LANGUAGE 'plpgsql';	