package com.sensus.mlc.ecomode.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;

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
	InternalResultsResponse<EcoModeBaseline> upsertEcoMode(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Update light consumptions.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal response
	 */
	InternalResponse updateLightConsumptions(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Update calculation retroactive ecomode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal response
	 */
	InternalResponse updateCalculationRetroactiveEcomode(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Insert light consumption.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightConsumption> insertLightConsumption(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch eco mode by light id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<EcoModeBaseline> fetchEcoModeByPoleId(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions by light id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightConsumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch all lights to calculate eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<EcoModeBaseline> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch count all lights to calculate eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the integer
	 */
	Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryEcoModeRequest the inquiry eco mode request
	 * @return the internal response
	 */
	InternalResponse generateFileCSV(InquiryEcoModeRequest inquiryEcoModeRequest);

	/**
	 * Fetch light consumptions to chart.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightConsumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Update analytics data.
	 */
	void updateAnalyticsData();
}
