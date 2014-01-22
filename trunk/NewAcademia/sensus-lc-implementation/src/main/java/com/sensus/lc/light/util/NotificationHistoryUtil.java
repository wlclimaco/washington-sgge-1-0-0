package com.sensus.lc.light.util;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertTypeEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.PrecedenceEnum;

/**
 * The Class NotificationHistoryUtil.
 */
public final class NotificationHistoryUtil
{

	/**
	 * Instantiates a new notification history util.
	 */
	private NotificationHistoryUtil()
	{

	}

	/**
	 * Calculate notification history precedence.
	 * 
	 * @param notificationHistory the notification history
	 */
	public static void calculateNotificationHistoryPrecedence(NotificationHistory notificationHistory)
	{
		if (LifeCycleStateEnum.DEACTIVATED == notificationHistory.getLifeCycleState())
		{
			notificationHistory.setPrecedence(PrecedenceEnum.DEACTIVATED);
			return;
		}

		if (!ValidationUtil.isNull(notificationHistory.getAlertClassifications()))
		{
			List<AlertTypeEnum> alerts = new ArrayList<AlertTypeEnum>();

			if (!ValidationUtil.isNullOrEmpty(notificationHistory.getAlertClassifications()))
			{
				for (AlertClassification alert : notificationHistory.getAlertClassifications())
				{
					alerts.add(alert.getAlertType());
				}

				if (alerts.contains(AlertTypeEnum.ALARM))
				{
					notificationHistory.setPrecedence(PrecedenceEnum.ALARM);
					return;
				}

				if (alerts.contains(AlertTypeEnum.WARNING))
				{
					notificationHistory.setPrecedence(PrecedenceEnum.WARNING);
					return;
				}
			}
		}

		if (LifeCycleStateEnum.MAINTENANCE == notificationHistory.getLifeCycleState())
		{
			notificationHistory.setPrecedence(PrecedenceEnum.MAINTENANCE);
			return;
		}

		if (LifeCycleStateEnum.ACTIVE == notificationHistory.getLifeCycleState())
		{
			notificationHistory.setPrecedence(PrecedenceEnum.ACTIVE);
			return;
		}
		notificationHistory.setPrecedence(PrecedenceEnum.UNKNOWN);
	}

	/**
	 * Calculate notification history precedence.
	 * 
	 * @param light the light
	 * @return the precedence enum
	 */
	public static void calculateNotificationHistoryPrecedence(Light light)
	{
		if (!ValidationUtil.isNull(light.getLastNotificationHistory()))
		{
			calculateNotificationHistoryPrecedence(light.getLastNotificationHistory());
		}
	}

	/**
	 * Calculate notification history precedence.
	 * 
	 * @param lights the lights
	 */
	public static void calculateNotificationHistoryPrecedence(List<Light> lights)
	{
		for (Light light : lights)
		{
			if (!ValidationUtil.isNull(light.getLastNotificationHistory()))
			{
				calculateNotificationHistoryPrecedence(light.getLastNotificationHistory());
			}
		}
	}

}
