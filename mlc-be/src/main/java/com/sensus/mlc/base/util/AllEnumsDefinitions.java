package com.sensus.mlc.base.util;

import java.util.HashMap;

import com.sensus.common.scheduler.model.DaysOfWeekEnum;
import com.sensus.mlc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsGroupOrderByEnum;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.DashboardViewTypeEnum;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.MonitorRequestStatusEnum;
import com.sensus.mlc.ecomode.model.EcoModeFilterEnum;
import com.sensus.mlc.ecomode.model.EcoModeOrderByEnum;
import com.sensus.mlc.group.model.GroupOrderByEnum;
import com.sensus.mlc.process.model.LCActionCategoryEnum;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessOrderByEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.schedule.model.ScheduleOrderByEnum;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.settings.model.LanguagesEnum;
import com.sensus.mlc.smartpoint.model.CustomSearchOrderByEnum;
import com.sensus.mlc.smartpoint.model.DataTypeEnum;
import com.sensus.mlc.smartpoint.model.LightColumnEnum;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.LightTypeEnum;
import com.sensus.mlc.smartpoint.model.OperationalDataTypeEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchOperatorEnum;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.SmartPointTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.tag.model.TagOrderByEnum;
import com.sensus.mlc.user.model.UserOrderByEnum;

/**
 * The Class AllEnumsDefinitions.
 */
public final class AllEnumsDefinitions
{
	private AllEnumsDefinitions()
	{

	}

	/** The Constant ALL_SYSTEM_ENUMS. All system's enumerations should be on this array. */
	private static final Class<?>[] ALL_SYSTEM_ENUMS =
	{
			LCActionCategoryEnum.class, LCActionTypeEnum.class, ListTypeEnum.class, MonitorRequestStatusEnum.class,
			AnalyticsDateTypeEnum.class, AnalyticsGroupOrderByEnum.class, LightTypeEnum.class,
			AnalyticsTypeEnum.class, DashboardViewTypeEnum.class,
			GroupOrderByEnum.class, LCActionCategoryEnum.class, LCActionTypeEnum.class, ProcessItemStatusEnum.class,
			ProcessOrderByEnum.class, ProcessStatusReasonEnum.class, DaysOfWeekEnum.class, ScheduleOrderByEnum.class,
			ScheduleTypeEnum.class, LanguagesEnum.class, CustomSearchOrderByEnum.class, DataTypeEnum.class,
			LightColumnEnum.class, LightDetailDataTypeEnum.class, LightIntensityEnum.class, LightOrderByEnum.class,
			LightPropertyForSearchEnum.class, LightStateEnum.class, LightStatusEnum.class,
			OperationalDataTypeEnum.class, PropertyEnum.class, SearchOperatorEnum.class, SmartPointColumnEnum.class,
			SmartPointFilterEnum.class, SmartPointTypeEnum.class, StatusExceptionTypeEnum.class,
			StatusMessageCategoryEnum.class, TagOrderByEnum.class, UserOrderByEnum.class,
			com.sensus.mlc.ecomode.model.LightEcoModeTypeEnum.class,
			com.sensus.mlc.smartpoint.model.LightTypeEnum.class,
			EcoModeFilterEnum.class, EcoModeOrderByEnum.class
	};

	/**
	 * Gets the system enums definitions.
	 * 
	 * @return the system enums definitions
	 * @throws Exception the exception
	 */
	public static HashMap<String, HashMap<String, String>> getSystemEnumsDefinitions() throws Exception
	{
		final HashMap<String, HashMap<String, String>> enumsDefinitions =
				new HashMap<String, HashMap<String, String>>();

		for (final Class<?> enumeration : ALL_SYSTEM_ENUMS)
		{
			final HashMap<String, String> enumConstantAndValue = new HashMap<String, String>();

			for (final Object enumConstant : enumeration.getEnumConstants())
			{
				final Object theEnumConstItSelf =
						enumeration.getMethod("valueOf", String.class).invoke(enumeration, enumConstant.toString());
				final Object theEnumConstValue =
						theEnumConstItSelf.getClass().getMethod("getValue", new Class<?>[] {})
								.invoke(theEnumConstItSelf, new Object[] {});

				enumConstantAndValue.put(theEnumConstValue.toString(), theEnumConstItSelf.toString());
			}

			enumsDefinitions.put(enumeration.getSimpleName(), enumConstantAndValue);
		}

		return enumsDefinitions;
	}
}
