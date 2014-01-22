package com.sensus.lc.ecomode.bcf;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.model.response.EcoModeResponse;
import com.sensus.lc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Interface EcoModeBCF.
 */
public interface IEcoModeBCF
{
	/**
	 * Upsert eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the eco mode response
	 */
	EcoModeResponse upsertEcoMode(EcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions by light id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the inquiry eco mode response
	 */
	InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions to chart.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the inquiry eco mode response
	 */
	InquiryEcoModeResponse fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the cSV response
	 */
	CSVResponse generateFileCSV(EcoModeCSVRequest ecoModeRequest);

	/**
	 * Insert csv process.
	 * 
	 * @param request the request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(InquiryPaginationRequest request);

	/**
	 * Import eco mode baseline from file csv.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the eco mode response
	 */
	EcoModeResponse importEcoModeBaselineFromFileCSV(EcoModeRequest ecoModeRequest);
}
