package com.sensus.mlc.ecomode.bcf;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;

/**
 * The Interface EcoModeBCF.
 */
public interface IEcoModeBCF
{
	/**
	 * Upsert eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the inquiry eco mode response
	 */
	InquiryEcoModeResponse upsertEcoMode(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions by light id.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the inquiry eco mode response
	 */
	InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryEcoModeRequest the inquiry eco mode request
	 * @return the inquiry eco mode response
	 */
	InquiryEcoModeResponse generateFileCSV(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Insert csv process.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest);

	/**
	 * Import light consumptions from file csv.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the internal response
	 */
	InquiryEcoModeResponse importEcoModeBaselineFromFileCSV(InquiryEcoModeRequest ecoModeRequest);

	/**
	 * Fetch light consumptions to chart.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the inquiry eco mode response
	 */
	InquiryEcoModeResponse fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest);
}
