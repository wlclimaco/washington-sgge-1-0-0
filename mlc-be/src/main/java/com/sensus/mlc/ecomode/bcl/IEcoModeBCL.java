package com.sensus.mlc.ecomode.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;

/**
 * The Interface EcoModeBCF.
 */
public interface IEcoModeBCL
{

	/**
	 * Upsert eco mode.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<EcoModeBaseline> upsertEcoMode(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions by light id.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightConsumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Generate file csv.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @return the inquiry eco mode response
	 */
	InquiryEcoModeResponse generateFileCSV(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Import light consumptions from file csv.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @return the internal response
	 */
	InternalResponse importEcoModeBaselineFromFileCSV(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions to chart.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightConsumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch count all lights to calculate eco mode.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @return the integer
	 */
	Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Update analytics data.
	 */
	void updateAnalyticsData();

	/**
	 * Fetch all lights to calculate eco mode.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<EcoModeBaseline> fetchAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest);
}
