package com.sensus.lc.ecomode.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;

/**
 * The Interface EcoModeBCF.
 */
public interface IEcoModeDAC
{

	/**
	 * Upsert eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> upsertEcoMode(EcoModeRequest ecoModeRequest);

	/**
	 * Update light consumptions.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal response
	 */
	InternalResponse updateLightConsumption(EcoModeRequest ecoModeRequest);

	/**
	 * Update calculation retroactive ecomode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal response
	 */
	InternalResponse updateCalculationRetroactiveEcomode(EcoModeRequest ecoModeRequest);

	/**
	 * Insert light consumption.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Consumption> insertLightConsumption(EcoModeRequest ecoModeRequest);

	/**
	 * Fetch eco mode by pole id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchEcoModeByLight(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions by light id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Consumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch all light consumptions by light id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Consumption> fetchAllLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch all lights to calculate eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch count all lights to calculate eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the integer
	 */
	Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions to chart.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Consumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Update analytics data.
	 */
	void updateAnalyticsData();
}
