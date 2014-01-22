package com.sensus.lc.analytics.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum AnalyticsGroupOrderByEnum.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 * 
 */
public enum AnalyticsGroupOrderByEnum implements IStringEnum
{

	/** The ANALYTICS_GROUP_NAME. */
	ANALYTICS_GROUP_NAME("group_name"),

	/** The ANALYTICS_GROUP_TOTAL. */
	ANALYTICS_GROUP_TOTAL("total"),

	/** The ANALYTICS_GROUP_LAMP_FAILURE. */
	ANALYTICS_GROUP_LAMP_FAILURE("lamp_failure"),

	/** The ANALYTICS_GROUP_POWER_FAILURE. */
	ANALYTICS_GROUP_POWER_FAILURE("power_failure"),

	/** The ANALYTICS_GROUP_POWER_SURGE. */
	ANALYTICS_GROUP_POWER_SURGE("power_surge"),

	/** The ANALYTICS_GROUP_BROWNOUT_DETECTED. */
	ANALYTICS_GROUP_BROWNOUT_DETECTED("brownout_detected"),

	/** The analytics group board failure. */
	ANALYTICS_GROUP_BOARD_FAILURE("board_failure"),

	/** The analytics group metrology error. */
	ANALYTICS_GROUP_METROLOGY_ERROR("metrology_error"),

	/** The analytics group metrology com failure. */
	ANALYTICS_GROUP_METROLOGY_COM_FAILURE("metrology_com_failure"),

	/** The analytics group high current. */
	ANALYTICS_GROUP_HIGH_CURRENT("high_current"),

	/** The analytics group low current. */
	ANALYTICS_GROUP_LOW_CURRENT("low_current"),

	/** The analytics group reverse energy. */
	ANALYTICS_GROUP_REVERSE_ENERGY("reverse_energy"),

	/** The analytics group metrology reset. */
	ANALYTICS_GROUP_METROLOGY_RESET("metrology_reset"),

	/** The ANALYTICS_GROUP_INDUCTION. */
	ANALYTICS_GROUP_INDUCTION("induction"),

	/** The ANALYTICS_GROUP_LED. */
	ANALYTICS_GROUP_LED("led"),

	/** The ANALYTICS_GROUP_OTHER. */
	ANALYTICS_GROUP_OTHER("other"),

	/** The ANALYTICS_GROUP_CREDITS_CREATED. */
	ANALYTICS_GROUP_CREDITS_CREATED("credits_created"),

	/** The ANALYTICS_GROUP_ENERGY_SAVED. */
	ANALYTICS_GROUP_ENERGY_SAVED("energy_saved"),

	/** The ANALYTICS_GROUP_BARRELS_OF_OIL_SAVED. */
	ANALYTICS_GROUP_BARRELS_OF_OIL_SAVED("barrels_of_oil_saved"),

	/** The ANALYTICS_GROUP_METRIC_OF_CO_SABED. */
	ANALYTICS_GROUP_METRIC_OF_CO_SABED("tons_of_co_saved"),

	/** The analytics group communication fail. */
	ANALYTICS_GROUP_COMMUNICATION_FAIL("communication_fail"),

	/** The measured consumption. */
	ANALYTICS_GROUP_MEASURED_CONSUMPTION("ecomode_measured"),

	/** The baseline consumption. */
	ANALYTICS_GROUP_BASELINE_CONSUMPTION("ecomode_baseline"),

	/** The ecomode percent. */
	ANALYTICS_GROUP_ECOMODE_PERCENT("ecomode_percent");

	/** The colum name. */
	private final String columId;

	/**
	 * Instantiates a new order by enum.
	 * 
	 * @param columNameInd the colum name ind
	 */
	private AnalyticsGroupOrderByEnum(String columNameInd)
	{
		columId = columNameInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return columId;
	}

}
