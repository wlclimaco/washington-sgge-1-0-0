package com.sensus.lc.base.util;

import java.util.HashMap;

import com.sensus.common.scheduler.model.DaysOfWeekEnum;
import com.sensus.lc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.lc.analytics.model.AnalyticsGroupOrderByEnum;
import com.sensus.lc.analytics.model.AnalyticsRangeDateEnum;
import com.sensus.lc.analytics.model.AnalyticsTypeEnum;
import com.sensus.lc.analytics.model.DashboardViewTypeEnum;
import com.sensus.lc.base.model.ListTypeEnum;
import com.sensus.lc.base.model.MonitorRequestStatusEnum;
import com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.lc.base.model.PageSizeDialogEnum;
import com.sensus.lc.ecomode.model.EcoModeFilterEnum;
import com.sensus.lc.ecomode.model.EcoModeOrderByEnum;
import com.sensus.lc.ecomode.model.LightEcoModeTypeEnum;
import com.sensus.lc.group.model.GroupOrderByEnum;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.AlertTypeEnum;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.DataTypeEnum;
import com.sensus.lc.light.model.DeleteLightReferenceEnum;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.GetDataFromLightEnum;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.LightDetailDataTypeEnum;
import com.sensus.lc.light.model.LightFilterTypeEnum;
import com.sensus.lc.light.model.LightOrderByEnum;
import com.sensus.lc.light.model.LightPropertyForSearchEnum;
import com.sensus.lc.light.model.LightStateEnum;
import com.sensus.lc.light.model.LightTypeEnum;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.light.model.OperationalDataTypeEnum;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.PrecedenceEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchOperatorEnum;
import com.sensus.lc.process.model.LCActionCategoryEnum;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessOrderByEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.schedule.model.ScheduleOrderByEnum;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.settings.model.LanguagesEnum;
import com.sensus.lc.tag.model.TagOrderByEnum;
import com.sensus.lc.user.model.UserOrderByEnum;

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
			AnalyticsDateTypeEnum.class
			, AlertSubTypeEnum.class
			, AlertTypeEnum.class
			, AnalyticsGroupOrderByEnum.class
			, AnalyticsTypeEnum.class
			, BlinkStatusEnum.class
			, DashboardViewTypeEnum.class
			, DataTypeEnum.class
			, DaysOfWeekEnum.class
			, DeleteLightReferenceEnum.class
			, EcoModeFilterEnum.class
			, EcoModeFilterEnum.class
			, EcoModeOrderByEnum.class
			, EcoModeOrderByEnum.class
			, FilterEnum.class
			, GetDataFromLightEnum.class
			, GroupOrderByEnum.class
			, IntensityEnum.class
			, LanguagesEnum.class
			, LCActionCategoryEnum.class
			, LCActionTypeEnum.class
			, LifeCycleStateEnum.class
			, LightColumnEnum.class
			, LightDetailDataTypeEnum.class
			, LightEcoModeTypeEnum.class
			, LightEcoModeTypeEnum.class
			, LightFilterTypeEnum.class
			, LightOrderByEnum.class
			, LightPropertyForSearchEnum.class
			, LightStateEnum.class
			, LightTypeEnum.class
			, ListTypeEnum.class
			, MonitorRequestStatusEnum.class
			, NotificationTypeEnum.class
			, ObjectToBeValidatedKeyEnum.class
			, OperationalDataTypeEnum.class
			, OverrideEnum.class
			, PageSizeDialogEnum.class
			, PrecedenceEnum.class
			, ProcessItemStatusEnum.class
			, ProcessOrderByEnum.class
			, ProcessStatusReasonEnum.class
			, PropertyEnum.class
			, ScheduleOrderByEnum.class
			, ScheduleTypeEnum.class
			, SearchOperatorEnum.class
			, AlertSubTypeEnum.class
			, TagOrderByEnum.class
			, UserOrderByEnum.class
			, AnalyticsRangeDateEnum.class
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
