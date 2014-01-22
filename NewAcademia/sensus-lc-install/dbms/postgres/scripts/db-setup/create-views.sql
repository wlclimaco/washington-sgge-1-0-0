CREATE OR REPLACE VIEW vw_group_light_count AS SELECT grouping.grouping_id,
												      grouping.name,
												      grouping.description,
													  grouping.create_date,
													  grouping.latitude,
													  grouping.longitude,
													  grouping.tenant_id,
													  ( SELECT COUNT(*) AS count
													      FROM light_grouping
														 INNER JOIN light ON light.light_id = light_grouping.light_id AND light.tenant_id = grouping.tenant_id
														 WHERE light_grouping.grouping_id = grouping.grouping_id) AS smartpoint_count
											     FROM grouping;

CREATE OR REPLACE VIEW vw_process_light_count AS SELECT process_id, description, parameter_value, lc_action_description, start_datetime, end_datetime, rni_correlation_id, is_submitted
                                                        , lc_action_id, tenant_id, parent_process_id, is_monitored_instance, estimated_seconds_to_complete, create_user
                                                        , create_date, is_process_complete, is_first_level,
                                                        ( SELECT COUNT(*)
                                                            FROM light_process
                                                           WHERE (process_id = process.process_id)) AS light_count,
                                                        ( SELECT count(*) AS count_failed
                                                            FROM light_process lp
                                                           WHERE lp.process_id = process.process_id AND lp.failure_id IS NOT NULL) AS light_failed_count
                                                   FROM process;

CREATE OR REPLACE VIEW vw_notification_history AS SELECT notification_history.light_id, MAX(notification_history.notification_history_id) AS notification_history_id
											        FROM notification_history
											    GROUP BY notification_history.light_id;

