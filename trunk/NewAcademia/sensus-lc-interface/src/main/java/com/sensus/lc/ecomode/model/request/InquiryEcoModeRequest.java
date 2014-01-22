/*
 *
 */
package com.sensus.lc.ecomode.model.request;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;

/**
 * The Class InquiryEcoModeRequest.
 */
public class InquiryEcoModeRequest extends InquiryPaginationRequest
{
	/** Attributes. */
	private Light light;
	private boolean lightsToReprocess;

	/**
	 * Instantiates a new inquiry eco mode request.
	 */
	public InquiryEcoModeRequest()
	{
	}

	/**
	 * Instantiates a new inquiry eco mode request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryEcoModeRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Light getLight()
	{
		return light;
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/**
	 * Gets the first eco mode baseline.
	 * 
	 * @return the first eco mode baseline
	 */
	public EcoModeBaseline getEcoModeBaseline()
	{
		if (isNull(getLight()))
		{
			return null;
		}

		return getLight().getEcoModeBaseline();
	}

	/**
	 * Adds the eco mode baseline.
	 * 
	 * @param baseline the baseline
	 */
	public void setEcoModeBaseline(EcoModeBaseline baseline)
	{
		if (isNull(getLight()))
		{
			return;
		}

		getLight().setEcoModeBaseline(baseline);
	}

	/**
	 * Gets the first light consumption.
	 * 
	 * @return the first light consumption
	 */
	public Consumption getFirstLightConsumption()
	{
		if (isNull(getLight()))
		{
			return null;
		}

		return getLight().getLastConsumption();
	}

	/**
	 * Gets the light consumptions.
	 * 
	 * @return the light consumptions
	 */
	public List<Consumption> getLightConsumptions()
	{
		if (isNull(getLight()))
		{
			return null;
		}

		return getLight().getConsumptions();
	}

	/**
	 * Sets the light consumptions.
	 * 
	 * @param lightConsumptions the new light consumptions
	 */
	public void setLightConsumptions(List<Consumption> lightConsumptions)
	{
		if (isNull(getLight()))
		{
			return;
		}

		getLight().setConsumptions(lightConsumptions);
	}

	/**
	 * Adds the light consumption.
	 * 
	 * @param lightConsumption the light consumption
	 */
	public void addLightConsumption(Consumption lightConsumption)
	{
		if (isNull(getLight()) || isNullOrEmpty(getLight().getConsumptions()))
		{
			return;
		}

		getLight().getConsumptions().add(lightConsumption);
	}

	/**
	 * Gets the lights to reprocess.
	 * 
	 * @return the lights to reprocess
	 */
	public Boolean getLightsToReprocess()
	{
		return lightsToReprocess;
	}

	/**
	 * Sets the lights to reprocess.
	 * 
	 * @param lightsToReprocess the new lights to reprocess
	 */
	public void setLightsToReprocess(Boolean lightsToReprocess)
	{
		this.lightsToReprocess = lightsToReprocess;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryEcoModeRequest [getLight()=" + getLight() + ", getEcoModeBaseline()=" + getEcoModeBaseline()
				+ ", getFirstLightConsumption()=" + getFirstLightConsumption() + ", getLightConsumptions()="
				+ getLightConsumptions() + ", getLightsToReprocess()=" + getLightsToReprocess() + ", toString()="
				+ super.toString() + "]";
	}
}
