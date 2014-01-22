package com.sensus.lc.ecomode.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVInternalResponse;

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
	InternalResultsResponse<Light> upsertEcoMode(EcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions by light id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Consumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the cSV internal response
	 */
	CSVInternalResponse generateFileCSV(EcoModeCSVRequest ecoModeRequest);

	/**
	 * Import eco mode baseline from file csv.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> importEcoModeBaselineFromFileCSV(EcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions to chart.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal results response
	 */
	InternalResultsResponse<Consumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest);

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
	InternalResultsResponse<Light> fetchAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest);
}
