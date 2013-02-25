package com.sensus.mlc.ecomode.util;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import com.sensus.mlc.base.util.DayLengthCalculator;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class LCEcoModeUtil.
 */
public final class LCEcoModeUtil
{
	private static final int DAY_HOURS_TOTAL = 24;
	private static final double HOUR_FACTOR = 0.5;
	private static final double POWER_FACTOR = 1.2;
	private static final int SCALE = 9;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static final int HUNDRED_PERCENT = 2;

	/**
	 * Instantiates a new lC eco mode util.
	 */
	private LCEcoModeUtil()
	{

	}

	/**
	 * Calculate old consumption.
	 *
	 * @param baseline the baseline
	 * @return the double
	 */
	public static Double calculateEcomodeBaseline(EcoModeBaseline baseline)
	{
		return calculateEcomodeBaseline(baseline, Calendar.getInstance());
	}

	/**
	 * Calculate old consumption.
	 *
	 * @param baseline the baseline
	 * @param calendar the calendar
	 * @return the double
	 */
	public static Double calculateEcomodeBaseline(EcoModeBaseline baseline, Calendar calendar)
	{

	//	Double latitude = Double.valueOf(light.getParameterValue(PropertyEnum.LATITUDE));
		//FIXME - properties
		Double latitude = 0D;
		Double dayLength = DayLengthCalculator.calculate(latitude, calendar);
		Double wattage = baseline.getReplacedWattage();

		return ((DAY_HOURS_TOTAL - dayLength) + HOUR_FACTOR) * wattage * POWER_FACTOR;
	}

	public static Double calculateEcoMode(LightConsumption lastConsumption)
	{
		if (isNull(lastConsumption)
				|| isNull(lastConsumption.getConsumption())
				|| isNull(lastConsumption.getEcomodeBaseline()))
		{
			return null;
		}

		BigDecimal ecomodeBaselineDecimal = new BigDecimal(lastConsumption.getEcomodeBaseline());
		BigDecimal lastConsumptionDecimal = new BigDecimal(lastConsumption.getConsumption());
		BigDecimal result = ecomodeBaselineDecimal.setScale(SCALE, ROUNDING_MODE)
				.subtract(lastConsumptionDecimal)
				.divide(ecomodeBaselineDecimal, SCALE, ROUNDING_MODE)
				.movePointRight(HUNDRED_PERCENT)
				.setScale(SCALE, ROUNDING_MODE);

		return new Double(result.doubleValue());
	}

	/**
	 * Checks if is eco mode baseline valid.
	 *
	 * @param baseline the baseline
	 * @return true, if is eco mode baseline valid
	 */
	private static boolean isEcoModeBaselineValid(EcoModeBaseline baseline)
	{
		if ((baseline == null)
				|| (baseline.getLightId() == null)
				|| (baseline.getReplacedWattage() == null))
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks if is light valid.
	 *
	 * @param light the light
	 * @return true, if is light valid
	 */
	private static boolean isLightValid(Light light)
	{
		// FIXME - properties
		/*	if ((light == null) || (light.getParameterValue(PropertyEnum.LATITUDE) == null))
		{
			return false;
		}*/
		return true;
	}
}
