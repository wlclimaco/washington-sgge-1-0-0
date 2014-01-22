CREATE OR REPLACE VIEW vw_group_smartpoint_count AS SELECT grouping.grouping_id, 
                        grouping.name, 
                        grouping.description, 
                        grouping.create_date, 
                        grouping.latitude, 
                        grouping.longitude, 
                        grouping.tenant_id, 
                        ( SELECT COUNT(*) AS count
                                FROM smartpoint_grouping
                                INNER JOIN smartpoint ON smartpoint.smartpoint_id = smartpoint_grouping.smartpoint_id AND smartpoint.tenant_id = grouping.tenant_id
                                WHERE smartpoint_grouping.grouping_id =  grouping.grouping_id) AS smartpoint_count
                                FROM grouping;
								
CREATE OR REPLACE VIEW vw_process_smartpoint_count AS SELECT     process_id, description, lc_action_description, start_datetime, end_datetime, rni_correlation_id, is_submitted, lc_action_id,  
                    tenant_id, parent_process_id, is_monitored_instance, estimated_seconds_to_complete, create_user, create_date, 
                      is_process_complete, is_first_level,
                          (SELECT     COUNT(*)
                            FROM          smartpoint_process
                            WHERE      (process_id = process.process_id)) AS smartpoint_count
        FROM         process;
		
CREATE OR REPLACE VIEW vw_current_message AS SELECT status_message.light_id, max(status_message.status_message_id) AS message_id
   FROM status_message
   GROUP BY status_message.light_id;