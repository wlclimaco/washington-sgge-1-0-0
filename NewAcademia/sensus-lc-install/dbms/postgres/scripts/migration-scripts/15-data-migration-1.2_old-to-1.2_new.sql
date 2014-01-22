/* ************************************* */

/* Update lights without notification history */

CREATE OR REPLACE FUNCTION update_notification_history_to_lights()
  RETURNS text AS $$

DECLARE
    lightRecord RECORD;
	p_notification_history_id INTEGER;
	p_date timestamp;
BEGIN

	p_date := (SELECT CURRENT_TIMESTAMP::TIMESTAMP WITHOUT TIME ZONE);

	-- Loop for all light without notification history
    FOR lightRecord IN
        SELECT light_id
          FROM light
         WHERE notification_history_id IS NULL
           AND light_id NOT IN (
                                 SELECT light_id
                                   FROM current_alarm_warning_message
                                  GROUP BY light_id
                                 HAVING count(1) > 1
           )
	LOOP
	    -- Insert notification history with warning
        INSERT INTO notification_history( light_id
                                          ,lifecycle_state
                                          ,notification_type
                                          ,message_date
                                          ,create_date
                                          ,update_date
                                          ,create_user
                                          ,precedence
                                          ,notification_transation_id)
             VALUES ( lightRecord.light_id
			          ,5
					  ,1
					  ,p_date
					  ,p_date
					  ,p_date
					  ,'System'
					  ,1
					  ,(SELECT ROUND(RANDOM() * 9999999999))
					);

		p_notification_history_id := (SELECT currval('public.notification_history_status_message_id_seq'));

		-- Insert notification history with communication failure
        INSERT INTO notification_history_alert( alert_subtype_id
                                                ,notification_history_id
                                                ,create_date
                                                ,update_date
                                                ,message_date
                                                ,create_user)
		     VALUES ( 8
			         ,p_notification_history_id
					 ,p_date
					 ,p_date
					 ,p_date
					 ,'System'
			        );

		-- Update last notification history to light
        UPDATE light l
           SET notification_history_id = p_notification_history_id
               ,lifecycle_state = 5
         WHERE l.light_id = lightRecord.light_id;

    END LOOP;

    RETURN 'SUCCESS';
END;
$$ LANGUAGE plpgsql;

SELECT update_notification_history_to_lights();
DROP FUNCTION update_notification_history_to_lights();

/* ************************************* */


/* ************************************* */

SELECT calculate_retroactive_light_daily_consumption();