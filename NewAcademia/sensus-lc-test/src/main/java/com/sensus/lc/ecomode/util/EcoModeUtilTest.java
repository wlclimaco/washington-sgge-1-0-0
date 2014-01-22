package com.sensus.lc.ecomode.util;

import static com.sensus.lc.base.TestBaseUtil.createLightConsumption;
import static com.sensus.lc.base.TestBaseUtil.createRadio;
import static com.sensus.lc.base.TestBaseUtil.getConsumptionRandom;
import static com.sensus.lc.base.TestBaseUtil.getEcoModeRandom;
import static com.sensus.lc.base.TestBaseUtil.getLightTypeRandom;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;

public class EcoModeUtilTest extends AbstractTestBaseBusiness
{
	/** The Constant ONE_HUNDRED. */
	private static final Double ONE_HUNDRED = 100D;

	/** The Constant DOT_ZERO_ONE. */
	private static final Double DOT_ZERO_ONE = 0.01D;

	/** The Constant THREE_THOUSAND. */
	private static final Double THREE_THOUSAND = 3000D;

	@Test
	public void testCalculateEcoMode()
	{
		Light light = new Light();
		light.setRadio(createRadio());

		EcoModeBaseline baseline = new EcoModeBaseline();
		baseline.setReplacedType(getLightTypeRandom());
		baseline.setReplacedWattage(getEcoModeRandom());

		light.setEcoModeBaseline(baseline);
		Double ecomodeBaseline = EcoModeUtil.calculateEcomodeBaseline(light);
		assertNotNull(ecomodeBaseline);

		Consumption lastConsumption = createLightConsumption(1);
		Double ecomode = EcoModeUtil.calculateEcoMode(lastConsumption);
		assertNotNull(ecomode);
		assertTrue(ecomode < ONE_HUNDRED);

		System.out.println(String.format(
				" Latitude: %s\n ReplaceWattage: %s\n EcoModeBaseline: %s WH\n Ecomode: %s\n",
				light.getRadio().getLocation().getLatitude(),
				baseline.getReplacedWattage(),
				ecomodeBaseline,
				ecomode
				));
	}

	@Test
	public void testCalculateEcoModeNoOneHundredPercent()
	{
		Consumption lastConsumption = createLightConsumption(0);
		lastConsumption.setEcomodeBaseline(getConsumptionRandom() + THREE_THOUSAND);
		lastConsumption.setConsumption(DOT_ZERO_ONE);
		Double ecomode = EcoModeUtil.calculateEcoMode(lastConsumption);
		assertNotNull(ecomode);
		assertTrue(ecomode < ONE_HUNDRED);

		System.out.println(String.format(
				" Consumption: %s\n Baseline: %s WH\n Ecomode: %s\n",
				lastConsumption.getConsumption(),
				lastConsumption.getEcomodeBaseline(),
				ecomode
				));
	}

}
