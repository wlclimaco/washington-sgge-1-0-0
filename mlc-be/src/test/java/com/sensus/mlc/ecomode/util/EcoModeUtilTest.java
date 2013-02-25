package com.sensus.mlc.ecomode.util;

import static com.sensus.mlc.base.TestBaseUtil.createLightConsumption;
import static com.sensus.mlc.base.TestBaseUtil.createLightLocation;
import static com.sensus.mlc.base.TestBaseUtil.getConsumptionRandom;
import static com.sensus.mlc.base.TestBaseUtil.getEcoModeRandom;
import static com.sensus.mlc.base.TestBaseUtil.getLightTypeRandom;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.smartpoint.model.Light;

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
		light.setLightLocation(createLightLocation());

		EcoModeBaseline baseline = new EcoModeBaseline();
		baseline.setReplacedType(getLightTypeRandom());
		baseline.setReplacedWattage(getEcoModeRandom());
		baseline.setLightId(light);

		Double ecomodeBaseline = LCEcoModeUtil.calculateEcomodeBaseline(baseline);
		assertNotNull(ecomodeBaseline);

		LightConsumption lastConsumption = createLightConsumption(light, 1);
		Double ecomode = LCEcoModeUtil.calculateEcoMode(lastConsumption);
		assertNotNull(ecomode);
		assertTrue(ecomode < ONE_HUNDRED);

		System.out.println(String.format(
				" Latitude: %s\n ReplaceWattage: %s\n EcoModeBaseline: %s WH\n Ecomode: %s\n",
				light.getLightLocation().getLatitude(),
				baseline.getReplacedWattage(),
				ecomodeBaseline,
				ecomode
				));
	}

	@Test
	public void testCalculateEcoModeNoOneHundredPercent()
	{
		Light light = new Light();
		LightConsumption lastConsumption = createLightConsumption(light, 0);
		lastConsumption.setEcomodeBaseline(getConsumptionRandom() + THREE_THOUSAND);
		lastConsumption.setConsumption(DOT_ZERO_ONE);
		Double ecomode = LCEcoModeUtil.calculateEcoMode(lastConsumption);
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
