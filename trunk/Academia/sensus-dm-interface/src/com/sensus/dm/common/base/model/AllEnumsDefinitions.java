package com.sensus.dm.common.base.model;

import java.util.HashMap;

import com.sensus.dm.common.action.model.ActionColumnEnum;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.group.model.GroupColumnEnum;
import com.sensus.dm.common.process.model.ProcessOrderByEnum;
import com.sensus.dm.common.schedule.model.ScheduleOrderByEnum;
import com.sensus.dm.elec.device.model.PeakDemandOrderByEnum;

/**
 * The Class AllEnumsDefinitions.
 * 
 * @author QAT Global.
 */
public final class AllEnumsDefinitions
{

	/**
	 * Instantiates a new all enums definitions.
	 */
	private AllEnumsDefinitions()
	{

	}

	/** The Constant ALL_SYSTEM_ENUMS. All system's enumerations should be on this array. */
	private static final Class<?>[] ALL_SYSTEM_ENUMS =
	{

			ProcessOrderByEnum.class, ScheduleOrderByEnum.class, DeviceColumnEnum.class, GroupColumnEnum.class,
			ActionColumnEnum.class, BaseSortEnum.class, PeakDemandOrderByEnum.class

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

				enumConstantAndValue.put(theEnumConstItSelf.toString(), theEnumConstValue.toString());
			}

			enumsDefinitions.put(enumeration.getSimpleName(), enumConstantAndValue);
		}

		return enumsDefinitions;
	}
}
