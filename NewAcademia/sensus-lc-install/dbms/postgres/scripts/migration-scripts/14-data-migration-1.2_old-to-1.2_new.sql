DO $$
DECLARE
	v_rows INTEGER ;
BEGIN
	/* Alert type data migration *********** */

	INSERT INTO alert_type(
	            alert_type_id, label_key, create_date, create_user)
	    VALUES (1, 'sensus.mlc.status.alarm', CURRENT_TIMESTAMP, 'sysuser'),
		       (2, 'sensus.mlc.status.warning', CURRENT_TIMESTAMP, 'sysuser');

	/* ************************************* */


	/* Alert subtype type data migration *** */

	INSERT INTO alert_subtype(
	            alert_subtype_id, label_key, create_date, create_user, alert_type_id)
	    VALUES
		   (1, 'sensus.mlc.alert_subtype.lampfailure', CURRENT_TIMESTAMP, 'sysuser', 1),
		   (2, 'sensus.mlc.alert_subtype.powerfailure', CURRENT_TIMESTAMP, 'sysuser', 1),
		   (3, 'sensus.mlc.alert_subtype.boardfailure', CURRENT_TIMESTAMP, 'sysuser', 1),
		   (4, 'sensus.mlc.alert_subtype.metrologyerror', CURRENT_TIMESTAMP, 'sysuser', 1),
		   (5, 'sensus.mlc.alert_subtype.metrologycomfailure', CURRENT_TIMESTAMP, 'sysuser', 1),
		   (6, 'sensus.mlc.alert_subtype.powersurgedetected', CURRENT_TIMESTAMP, 'sysuser', 2),
		   (7, 'sensus.mlc.alert_subtype.brownoutdetected', CURRENT_TIMESTAMP, 'sysuser', 2),
		   (8, 'sensus.mlc.alert_subtype.communicationfail', CURRENT_TIMESTAMP, 'sysuser', 2),
		   (9, 'sensus.mlc.alert_subtype.highcurrent', CURRENT_TIMESTAMP, 'sysuser', 2),
		   (10, 'sensus.mlc.alert_subtype.lowcurrent', CURRENT_TIMESTAMP, 'sysuser', 2),
		   (11, 'sensus.mlc.alert_subtype.reverseenergy', CURRENT_TIMESTAMP, 'sysuser', 2),
		   (12, 'sensus.mlc.alert_subtype.metrologyreset', CURRENT_TIMESTAMP, 'sysuser', 2);

	/* ************************************* */

	/* Analytics alarm warning subtype data migration *** */

	UPDATE analytics_alarm_warning_subtype SET label_key = replace(label_key,'status','alert');

	/* ************************************* */

	/* Filter "STATUS" to "LIFECYCLE_STATE" data migration */

	UPDATE user_settings_property SET
       user_setting_property_value = 'LIFECYCLE_STATE'
	 WHERE user_setting_property_value = 'STATUS'
       AND property_id = 105;

	/* ************************************* */

	/* Verify the light quantity before in the old table */
	SELECT COUNT(*)
	  INTO v_rows
	  FROM (
			SELECT l.light_id
			  FROM light_old l
	    	    INNER JOIN smartpoint s ON l.smartpoint_id = s.smartpoint_id
		     LEFT JOIN light_location ll ON l.light_id = ll.light_id
		     LEFT JOIN current_alarm_warning_message cawm ON l.light_id = cawm.light_id
		     LEFT JOIN status_message sm
					            ON (sm.status_message_id = cawm.status_message_id
					                AND cawm.light_id NOT IN (SELECT light_id
					                                            FROM current_alarm_warning_message
					                                           GROUP BY light_id
					                                          HAVING count(1) > 1))
		      GROUP BY
				        l.light_id
				       ,l.light_type
				       ,sm.status_id
				       ,l.light_state
				       ,l.create_date
				       ,l.modified_date
				       ,l.create_user
				       ,l.modify_user
				       ,l.tenant_id
				       ,s.rni_id
				       ,ll.street_name
				       ,ll.city_name
				       ,ll.state_name
				       ,ll.zip_code
				       ,ll.county_name
				       ,ll.timezone
				       ,ll.latitude
				       ,ll.longitude
				       ,sm.status_message_id
				       ,l.pole_id
				       ,l.protected
				       ,l.intensity
				       ,l.ecomode
				       ,l.ecomode_replaced_type
				       ,l.ecomode_replaced_wattage
				       ,l.calculate_retroactive_ecomode
				       ,l.blink_level
				       ,l.override
				       ,l.override_per_date
				       ,l.override_create_date) AS TB;

	RAISE NOTICE '% lights found in the old table (light).', v_rows;

	/* Light data migration **************** */

	INSERT INTO light(   light_id
	                    ,light_type
	                    ,lifecycle_state
	                    ,light_state
	                    ,create_date
	                    ,modified_date
	                    ,create_user
	                    ,modify_user
	                    ,tenant_id
	                    ,flexnet_id
	                    ,address
	                    ,city
	                    ,state
	                    ,zip_code
	                    ,county
	                    ,timezone
	                    ,latitude
	                    ,longitude
	                    ,notification_history_id
	                    ,pole_id
	                    ,protected
	                    ,intensity
	                    ,ecomode
	                    ,ecomode_replaced_type
	                    ,ecomode_replaced_wattage
	                    ,calculate_retroactive_ecomode
	                    ,blink_level
	                    ,override
	                    ,override_per_date
	                    ,override_create_date)
	SELECT  l.light_id                          AS light_id
	       ,l.light_type                        AS light_type
	       ,CASE WHEN sm.status_id = 1 OR
	                  sm.status_id = 2 OR
	                  sm.status_id = 3 OR
	                  (sm.status_id IS NULL
	                   AND l.light_state <> 3
	                   AND l.light_state <> 4)
	                  THEN 5
	             WHEN sm.status_id = 0 OR
	                  (sm.status_id IS NULL
	                   AND l.light_state = 3)
	                  THEN 3
	             WHEN sm.status_id = 4 OR
	                  (sm.status_id IS NULL
	                   AND l.light_state = 4)
	                  THEN 4
	             ELSE 6
	         END                                AS lifecycle_state
	       ,CASE WHEN l.light_state = 0 THEN 2
	             WHEN l.light_state = 1 THEN 1
	             WHEN l.light_state = 2 THEN 4
	             WHEN l.light_state = 3 THEN 5
	             ELSE -1
	        END                                 AS light_state
	       ,l.create_date                       AS create_date
	       ,l.modified_date                     AS modified_date
	       ,l.create_user                       AS create_user
	       ,l.modify_user                       AS modify_user
	       ,l.tenant_id                         AS tenant_id
	       ,s.rni_id                            AS flexnet_id
	       ,ll.street_name                      AS address
	       ,ll.city_name                        AS city
	       ,ll.state_name                       AS state
	       ,ll.zip_code                         AS zip_code
	       ,ll.county_name                      AS county
	       ,ll.timezone                         AS timezone
	       ,ll.latitude                         AS latitude
	       ,ll.longitude                        AS longitude
           ,COALESCE(sm.status_message_id,
                     (SELECT cawm.status_message_id
	                    FROM current_alarm_warning_message cawm
	                   WHERE cawm.light_id = l.light_id
	                   ORDER BY cawm.status_messages_status_subtype_id
	                   LIMIT 1)
            ) AS notification_history_id
	       ,l.pole_id                           AS pole_id
	       ,l.protected                         AS protected
	       ,l.intensity                         AS intensity
	       ,l.ecomode                           AS ecomode
	       ,l.ecomode_replaced_type             AS ecomode_replaced_type
	       ,l.ecomode_replaced_wattage          AS ecomode_replaced_wattage
	       ,l.calculate_retroactive_ecomode     AS calculate_retroactive_ecomode
	       ,l.blink_level                       AS blink_level
	       ,l.override                          AS override
	       ,l.override_per_date                 AS override_per_date
	       ,l.override_create_date              AS override_create_date
	  FROM light_old l
	       INNER JOIN smartpoint s ON l.smartpoint_id = s.smartpoint_id
	       LEFT  JOIN light_location ll ON l.light_id = ll.light_id
	       LEFT JOIN current_alarm_warning_message cawm ON l.light_id = cawm.light_id
	       LEFT JOIN status_message sm
	            ON (sm.status_message_id = cawm.status_message_id
	                AND cawm.light_id NOT IN (SELECT light_id
	                                            FROM current_alarm_warning_message
	                                           GROUP BY light_id
	                                          HAVING count(1) > 1))
	 GROUP BY
	        l.light_id
	       ,l.light_type
	       ,sm.status_id
	       ,l.light_state
	       ,l.create_date
	       ,l.modified_date
	       ,l.create_user
	       ,l.modify_user
	       ,l.tenant_id
	       ,s.rni_id
	       ,ll.street_name
	       ,ll.city_name
	       ,ll.state_name
	       ,ll.zip_code
	       ,ll.county_name
	       ,ll.timezone
	       ,ll.latitude
	       ,ll.longitude
	       ,sm.status_message_id
	       ,l.pole_id
	       ,l.protected
	       ,l.intensity
	       ,l.ecomode
	       ,l.ecomode_replaced_type
	       ,l.ecomode_replaced_wattage
	       ,l.calculate_retroactive_ecomode
	       ,l.blink_level
	       ,l.override
	       ,l.override_per_date
	       ,l.override_create_date
	 ORDER BY l.light_id;

  	 GET DIAGNOSTICS v_rows = ROW_COUNT;
  	 RAISE NOTICE '% lights INSERTED on the new light table.', v_rows;


	-- Adjustemnt sequence
	PERFORM setval('public.light_light_id_seq', (SELECT MAX(light_id) + 1 FROM light), true);

	/* ************************************* */


	/* Light Grouping data migration ******* */

	/* Total of lights groups */
	SELECT COUNT(*)
	  INTO v_rows
	  FROM smartpoint_grouping sg
	  JOIN light_old l ON sg.smartpoint_id = l.smartpoint_id;

	RAISE NOTICE '% groups found on smartpoint_grouping.', v_rows;

	INSERT INTO light_grouping( create_date
	                           ,create_user
	                           ,grouping_id
	                           ,light_id)
	SELECT sg.create_date
	       ,sg.create_user
	       ,sg.grouping_id
	       ,l.light_id
	  FROM smartpoint_grouping sg
	  JOIN light_old l ON sg.smartpoint_id = l.smartpoint_id;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% groups INSERTED on light_grouping', v_rows;


	/* ************************************* */


	/* Light Process data migration ******** */

	/* Total of smartpoint process */
	SELECT COUNT(*)
	  INTO v_rows
      FROM smartpoint_process sp
	  JOIN light_old l ON sp.smartpoint_id = l.smartpoint_id;

    RAISE NOTICE '% smartpoint found on smartpoint_process.', v_rows;

	INSERT INTO light_process( process_result
	                           ,light_id
	                           ,create_date
	                           ,create_user
	                           ,failure_id
	                           ,process_id)
	SELECT sp.process_result
	       ,l.light_id
	       ,sp.create_date
	       ,sp.create_user
	       ,sp.failure_id
	       ,sp.process_id
	  FROM smartpoint_process sp
	  JOIN light_old l ON sp.smartpoint_id = l.smartpoint_id;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights INSERTED on light_process.', v_rows;


	/* ************************************* */


	/* Light tag data migration ************ */

	/* Total of smartpoint_tag */
	SELECT COUNT(*)
	  INTO v_rows
	  FROM smartpoint_tag st
	  JOIN light_old l ON st.smartpoint_id = l.smartpoint_id;

	RAISE NOTICE '% tags found on smartpoint_tag.', v_rows;


	INSERT INTO light_tag( create_date
	                      ,create_user
	                      ,light_id
	                      ,tag_id)
	SELECT st.create_date
	       ,st.create_user
	       ,l.light_id
	       ,st.tag_id
	  FROM smartpoint_tag st
	  JOIN light_old l ON st.smartpoint_id = l.smartpoint_id;

	GET DIAGNOSTICS v_rows = ROW_COUNT;
	RAISE NOTICE '% lights INSERTED on light_tag.', v_rows;


	/* ************************************* */


	/* Notification history data migration * */

	/* Total of status messages */
	SELECT COUNT(*)
	  INTO v_rows
	  FROM status_message sm
	  JOIN light_old l ON sm.light_id = l.light_id;

	RAISE NOTICE '% status messages found on status_message.', v_rows;

	INSERT INTO notification_history( notification_history_id
	                                  ,light_id
	                                  ,lifecycle_state
	                                  ,notification_type
	                                  ,message_date
	                                  ,create_date
	                                  ,update_date
	                                  ,create_user
	                                  ,precedence
	                                  ,simple_notification
	                                  ,notification_transation_id)

	SELECT status_message_id                    AS notification_history_id
	       ,sm.light_id                         AS light_id
	       ,CASE WHEN sm.status_id = 1 OR
	                  sm.status_id = 2 OR
	                  sm.status_id = 3 OR
	                  (sm.status_id IS NULL
	                   AND l.light_state <> 3
	                   AND l.light_state <> 4)
	                  THEN 5
	             WHEN sm.status_id = 0 OR
	                  (sm.status_id IS NULL
	                   AND l.light_state = 3)
	                  THEN 3
	             WHEN sm.status_id = 4 OR
	                  (sm.status_id IS NULL
	                   AND l.light_state = 4)
	                  THEN 4
	             ELSE 6
	         END                                AS lifecycle_state
	       ,sm.message_type                     AS notification_type
	       ,sm.message_date                     AS message_date
	       ,sm.create_date                      AS create_date
           ,CASE WHEN l.current_status_message_id = sm.status_message_id  AND (SELECT sm.message_date::timestamp without time zone FROM status_message sm WHERE sm.light_id = l.light_id AND message_type = 99) IS NOT NULL THEN
                      (SELECT sm.message_date::timestamp without time zone FROM status_message sm WHERE sm.light_id = l.light_id AND message_type = 99)
                 WHEN l.current_status_message_id = sm.status_message_id THEN
                      (SELECT MAX(cawm.message_date) FROM current_alarm_warning_message cawm WHERE cawm.status_message_id = l.current_status_message_id)
		         ELSE
		               sm.message_date
	        END AS update_date
	       ,sm.create_user                      AS create_user
	       ,CASE sm.status_id
	             WHEN 1 THEN 0
	             WHEN 2 THEN 1
	             WHEN 0 THEN 2
	             WHEN 4 THEN 3
	             WHEN 3 THEN 4
	             ELSE 5
	        END                                 AS precedence
	       ,sm.simple_notification              AS simple_notification
	       ,sm.status_message_transation_id     AS notification_transation_id
	  FROM status_message sm JOIN light_old l ON sm.light_id = l.light_id
	 WHERE sm.message_type <> 99
	 ORDER BY sm.status_message_id;

	 GET DIAGNOSTICS v_rows = ROW_COUNT;
	 RAISE NOTICE '% notification history INSERTED on notification_history.', v_rows;


	-- Adjustemnt sequence
	PERFORM setval('public.notification_history_status_message_id_seq', (SELECT MAX(notification_history_id) + 1 FROM notification_history), true);


	/* ************************************* */


	/* Notification history alert data migration */

	/* Total of status message subtype */
	SELECT COUNT(*)
	  INTO v_rows
	  FROM status_message_status_subtype;

    RAISE NOTICE '% status message status subtype found on status_message_status_subtype.', v_rows;


	INSERT INTO notification_history_alert( alert_subtype_id
	                                        ,notification_history_id
	                                        ,create_date
	                                        ,update_date
	                                        ,message_date
	                                        ,create_user
	                                        ,modify_user
	                                        ,modified_date)
	SELECT status_subtype_id AS alert_subtype_id
	       ,smss.status_message_id AS notification_history_id
	       ,smss.create_date AS create_date
	       ,smss.create_date AS update_date
	       ,CASE WHEN l.current_status_message_id = sm.status_message_id THEN
                      (SELECT MAX(cawm.message_date) FROM current_alarm_warning_message cawm WHERE cawm.status_message_id = l.current_status_message_id)
		         ELSE
			      smss.create_date
	        END AS message_date
	       ,smss.create_user AS create_user
	       ,smss.modified_user AS modify_user
	       ,smss.modified_date AS modified_date
	  FROM status_message_status_subtype smss
	       JOIN status_message sm ON smss.status_message_id = sm.status_message_id
	       LEFT JOIN light_old l ON l.current_status_message_id = sm.status_message_id
	 ORDER BY smss.status_message_id;

	 GET DIAGNOSTICS v_rows = ROW_COUNT;
	 RAISE NOTICE '% notification history alert INSERTED on notification_history_alert.', v_rows;

	/* ************************************* */


	/*Process table: Change column type from 'timestamp with timezone' to 'timestamp without timezone' **/
	DROP VIEW vw_process_light_count;

	ALTER TABLE process ALTER COLUMN start_datetime TYPE timestamp without time zone;
	ALTER TABLE process ALTER COLUMN end_datetime TYPE timestamp without time zone;
	ALTER TABLE process ALTER COLUMN create_date TYPE timestamp without time zone;
	ALTER TABLE process ALTER COLUMN modified_date TYPE timestamp without time zone;

	CREATE OR REPLACE VIEW vw_process_light_count AS
	 SELECT process.process_id, process.description, process.parameter_value,
	    process.lc_action_description, process.start_datetime, process.end_datetime,
	    process.rni_correlation_id, process.is_submitted, process.lc_action_id,
	    process.tenant_id, process.parent_process_id, process.is_monitored_instance,
	    process.estimated_seconds_to_complete, process.create_user,
	    process.create_date, process.is_process_complete, process.is_first_level,
	    ( SELECT count(*) AS count
	           FROM light_process
	          WHERE light_process.process_id = process.process_id) AS light_count,
	    ( SELECT count(*) AS count_failed
	           FROM light_process lp
	          WHERE lp.process_id = process.process_id AND lp.failure_id IS NOT NULL) AS light_failed_count
	   FROM process;

END $$