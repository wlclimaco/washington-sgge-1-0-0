package com.sensus.lc.ecomode.util;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.lc.base.util.DayLengthCalculator;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;

/**
 * The Class LCEcoModeUtil.
 */
public final class EcoModeUtil
{
	private static final int DAY_HOURS_TOTAL = 24;
	private static final double HOUR_FACTOR = 0.5;
	private static final double POWER_FACTOR = 1.2;
	private static final int SCALE = 9;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static final int HUNDRED_PERCENT = 2;
	private static final int THOUSAND = 1000;

	/**
	 * Instantiates a new lC eco mode util.
	 */
	private EcoModeUtil()
	{

	}

	/**
	 * Calculate ecomode baseline.
	 * 
	 * @param light the light
	 * @return the double
	 */
	public static Double calculateEcomodeBaseline(Light light)
	{
		return calculateEcomodeBaseline(light, Calendar.getInstance());
	}

	/**
	 * Calculate ecomode baseline.
	 * 
	 * @param light the light
	 * @param calendar the calendar
	 * @return the double
	 */
	public static Double calculateEcomodeBaseline(Light light, Calendar calendar)
	{
		if (!isLightValid(light) || !isEcoModeBaselineValid(light.getEcoModeBaseline()))
		{
			return null;
		}

		EcoModeBaseline baseline = light.getEcoModeBaseline();
		Double latitude = light.getRadio().getLocation().getLatitude();
		Double dayLength = DayLengthCalculator.calculate(latitude, calendar);
		Double kwattage = baseline.getReplacedWattage() / THOUSAND;

		return ((DAY_HOURS_TOTAL - dayLength) + HOUR_FACTOR) * kwattage * POWER_FACTOR;
	}

	/**
	 * Calculate eco mode.
	 * 
	 * @param lastConsumption the last consumption
	 * @return the double
	 */
	public static Double calculateEcoMode(Consumption lastConsumption)
	{
		if (isNull(lastConsumption)
				|| isNull(lastConsumption.getEcomodeBaseline()))
		{
			return null;
		}

		BigDecimal lastConsumptionDecimal = new BigDecimal(NumberUtils.INTEGER_ZERO);
		if (!isNull(lastConsumption.getConsumption()))
		{
			lastConsumptionDecimal = new BigDecimal(lastConsumption.getConsumption());
		}

		BigDecimal ecomodeBaselineDecimal = new BigDecimal(lastConsumption.getEcomodeBaseline());
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
		if (isNull(light)
				|| isNull(light.getRadio())
				|| isNull(light.getRadio().getLocation())
				|| isNull(light.getRadio().getLocation().getLatitude()))
		{
			return false;
		}

		return true;
	}
}
