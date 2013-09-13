package com.sensus.dm.common.device.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum AlarmEnum.
 * 
 * @author QAT Global.
 */
public enum AlarmEnum implements IStringEnum
{

	/** The back flow. */
	BACK_FLOW("FLDBACKFLOW"),

	/** The broken pipe. */
	BROKEN_PIPE("FLDBROKENPIPE"),

	/** The brown out. */
	BROWN_OUT("FLDBROWNOUT"),

	/** The click count. */
	CLICK_COUNT("FLDCLICKCOUNT"),

	/** The cut wire. */
	CUT_WIRE("FLDCUTWIRE"),

	/** The hist over flow. */
	HIST_OVER_FLOW("FLDHISTOVERFLOW"),

	/** The hot socket. */
	HOT_SOCKET("FLDHOTSOCKET"),

	/** The leak detected. */
	LEAK_DETECTED("FLDLEAKDETECTED"),

	/** The low battery error. */
	LOW_BATTERY_ERROR("FLDLOWBATTERYERROR"),

	/** The low ac volts. */
	LOW_AC_VOLTS("FLDLOWACVOLTS"),

	/** The magnetic. */
	MAGNETIC("FLDMAGNETIC"),

	/** The meter read fail. */
	METER_READ_FAIL("FLDMETERREADFAIL"),

	/** The power fail. */
	POWER_FAIL("FLDPOWERFAIL"),

	/** The power restore. */
	POWER_RESTORE("FLDPOWERRESTORE"),

	/** The power theft. */
	POWER_THEFT("FLDPOWERTHEFT"),

	/** The tamper. */
	TAMPER("FLDTAMPER"),

	/** The tilt. */
	TILT("FLDTILT"),

	/** The swapped meter. */
	SWAPPED_METER("FLDSWAPPEDMETER"),

	/** The corrector. */
	CORRECTOR("FLDCORRECTOR"),

	/** The meter comm fail. */
	METER_COMM_FAIL("FLDMETERCOMMFAIL"),

	/** The high temp. */
	HIGH_TEMP("FLDHIGHTEMP"),

	/** The pressure low shutoff. */
	PRESSURE_LOW_SHUTOFF("FLDPRESSURELOWSHUTOFF"),

	/** The pressure high shutoff. */
	PRESSURE_HIGH_SHUTOFF("FLDPRESSUREHIGHSHUTOFF"),

	/** The excess flow. */
	EXCESS_FLOW("FLDEXCESSFLOW"),

	/** The seismic event. */
	SEISMIC_EVENT("FLDSEISMICEVENT"),

	/** The pressure low warning. */
	PRESSURE_LOW_WARNING("FLDPRESSURELOWWARNING"),

	/** The pressure high warning. */
	PRESSURE_HIGH_WARNING("FLDPRESSUREHIGHWARNING"),

	/** The unknown valve state. */
	UNKNOWN_VALVE_STATE("FLDUNKNOWNVALVESTATE"),

	/** The high voltage alarm. */
	HIGH_VOLTAGE_ALARM("FLDHIGHVOLTAGEALARM"),

	/** The over class amps alarm. */
	OVER_CLASS_AMPS_ALARM("FLDOVERCLASSAMPSALARM"),

	/** The over temperature. */
	OVER_TEMPERATURE("FLDOVERTEMPERATURE"),

	/** The battery door tamper. */
	BATTERY_DOOR_TAMPER("FLDBATTERYDOORTAMPER"),

	/** The single phase fail. */
	SINGLE_PHASE_FAIL("FLDSINGLEPHASEFAIL"),

	/** The reverse energy alarm. */
	REVERSE_ENERGY_ALARM("FLDREVERSEENERGYALARM"),

	/** The empty pipe. */
	EMPTY_PIPE("FLDEMPTYPIPE"),

	/** The reverse flow. */
	REVERSE_FLOW("FLDREVERSEFLOW"),

	/** The dead battery. */
	DEAD_BATTERY("FLDDEADBATTERY"),

	/** The severe hardware problem. */
	SEVERE_HARDWARE_PROBLEM("FLDSEVEREHARDWAREPROBLEM"),

	/** The non volatile memory alarm. */
	NON_VOLATILE_MEMORY_ALARM("FLDNONVOLATILEMEMORYALARM"),

	/** The critical hardware warning. */
	CRITICAL_HARDWARE_WARNING("FLDCRITICALHARDWAREWARNING"),

	/** The relay current tamper. */
	RELAY_CURRENT_TAMPER("FLDRELAYCURRENTTAMPER"),

	/** The relay voltage tamper. */
	RELAY_VOLTAGE_TAMPER("FLDRELAYVOLTAGETAMPER"),

	/** The multiple. */
	MULTIPLE("FLDMULTIPLE");

	/** The alarm. */
	private String alarm;

	/**
	 * Instantiates a new alarm enum.
	 * 
	 * @param alarmParam the alarm param
	 */
	private AlarmEnum(String alarmParam)
	{
		alarm = alarmParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return alarm;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the alarm enum
	 */
	public static AlarmEnum enumForValue(String value)
	{
		for (AlarmEnum a : values())
		{
			if (a.getValue().equals(value))
			{
				return a;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		AlarmEnum[] enums = AlarmEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (AlarmEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
