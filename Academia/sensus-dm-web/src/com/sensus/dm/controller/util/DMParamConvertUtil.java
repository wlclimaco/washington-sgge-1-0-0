package com.sensus.dm.controller.util;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;

/**
 * The Class DMParamConvertUtil.
 */
public class DMParamConvertUtil
{

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant SPLIT_PIPE_REGEX. */
	private static final String SPLIT_PIPE_REGEX = "\\|";

	/**
	 * Fill device types.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<DeviceTypeEnum> fillDeviceTypes(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<DeviceTypeEnum> deviceTypeList = new ArrayList<DeviceTypeEnum>();

				for (String p : list)
				{
					deviceTypeList.add(DeviceTypeEnum.valueOf(p));
				}

				return deviceTypeList;
			}
		}

		return null;
	}

	/**
	 * Fill LCM sub types.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<LCMTypeEnum> fillLCMSubTypes(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<LCMTypeEnum> lcmTypeList = new ArrayList<LCMTypeEnum>();

				for (String p : list)
				{
					lcmTypeList.add(LCMTypeEnum.valueOf(p));
				}

				return lcmTypeList;
			}
		}

		return null;
	}

	/**
	 * Fill HAN sub types.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<HanDeviceTypeEnum> fillHANSubTypes(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<HanDeviceTypeEnum> hanTypeList = new ArrayList<HanDeviceTypeEnum>();

				for (String p : list)
				{
					if ((p.equals(HanDeviceTypeEnum.IHD.toString()))
							|| (p.equals(HanDeviceTypeEnum.THERMOSTAT.toString())))
					{
						hanTypeList.add(HanDeviceTypeEnum.valueOf(p));
					}
				}

				return hanTypeList;
			}
		}

		return null;
	}

	/**
	 * Fill HAN Life Cycle state.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<HanLifecycleStateEnum> fillHanLifecycleState(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<HanLifecycleStateEnum> hanLifecycleStateList = new ArrayList<HanLifecycleStateEnum>();

				for (String p : list)
				{
					hanLifecycleStateList.add(HanLifecycleStateEnum.valueOf(p));
				}

				return hanLifecycleStateList;
			}
		}

		return null;
	}

	/**
	 * Fill LCM Life Cycle state.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<LCMLifecycleStateEnum> fillLcmLifecycleState(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<LCMLifecycleStateEnum> lcmLifecycleStateList = new ArrayList<LCMLifecycleStateEnum>();

				for (String p : list)
				{
					lcmLifecycleStateList.add(LCMLifecycleStateEnum.valueOf(p));
				}

				return lcmLifecycleStateList;
			}
		}

		return null;
	}

	/**
	 * Fill Electric Life Cycle State.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<ElectricMeterLifecycleStateEnum> fillElectricLifecycleState(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<ElectricMeterLifecycleStateEnum> lifecycleStateList =
						new ArrayList<ElectricMeterLifecycleStateEnum>();

				for (String p : list)
				{
					lifecycleStateList.add(ElectricMeterLifecycleStateEnum.valueOf(p));
				}

				return lifecycleStateList;
			}
		}

		return null;
	}

	/**
	 * Fill Water/Gas Meter Status.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<WaterGasMeterStatusEnum> fillWaterGasMeterStatus(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<WaterGasMeterStatusEnum> waterGasMeterStatusList = new ArrayList<WaterGasMeterStatusEnum>();

				for (String p : list)
				{
					waterGasMeterStatusList.add(WaterGasMeterStatusEnum.valueOf(p));
				}

				return waterGasMeterStatusList;
			}
		}

		return null;
	}

	/**
	 * Fill alarms.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<AlarmEnum> fillAlarms(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<AlarmEnum> alarmList = new ArrayList<AlarmEnum>();

				for (String p : list)
				{
					alarmList.add(AlarmEnum.valueOf(p));
				}

				return alarmList;
			}
		}

		return null;
	}

	/**
	 * Fill groups.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<Group> fillGroups(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<Group> groupList = new ArrayList<Group>();

				for (String p : list)
				{
					groupList.add(new Group(Integer.valueOf(p)));
				}

				return groupList;
			}
		}

		return null;
	}

	/**
	 * Fill tags.
	 * 
	 * @param parameter the parameter
	 * @return the list
	 */
	public static List<Tag> fillTags(String[] parameter)
	{

		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				List<Tag> tagList = new ArrayList<Tag>();

				for (String p : list)
				{
					tagList.add(new Tag(Integer.valueOf(p)));
				}

				return tagList;
			}
		}

		return null;
	}

	/**
	 * Fill quarantine.
	 * 
	 * @param parameter the parameter
	 * @return the boolean
	 */
	public static Boolean fillQuarantine(String[] parameter)
	{
		if (!ValidationUtil.isNull(parameter))
		{
			String[] list = parameter[ZERO].split(SPLIT_PIPE_REGEX);

			if (!ValidationUtil.isNull(list) && (list.length > ZERO))
			{
				if (list[ZERO].equals("true"))
				{
					return true;
				}
			}
		}

		return null;
	}

	/**
	 * Fill process id.
	 * 
	 * @param parameter the parameter
	 * @return the integer
	 */
	public static Integer fillProcessId(String[] parameter)
	{
		if (!ValidationUtil.isNull(parameter))
		{
			return Integer.valueOf(parameter[ZERO]);
		}

		return null;
	}
}
