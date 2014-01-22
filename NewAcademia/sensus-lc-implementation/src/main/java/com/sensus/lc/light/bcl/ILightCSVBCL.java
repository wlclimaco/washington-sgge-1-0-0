package com.sensus.lc.light.bcl;

import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;

/**
 * Contains methods related to Lights and CSV files.
 * Do not update this interface without checking with Gustavo Peres
 */
public interface ILightCSVBCL
{

	/**
	 * Generate light history CSV file based on the request.
	 * Note, the light history data-source is first queried in order to retrieve the data to be exported to CSV.
	 *
	 * @param request the request
	 * @return the cSV internal response
	 */
	CSVInternalResponse generateLightHistoryFileCSV(LightHistoryCSVRequest request);

	/**
	 * Generate light summary CSV file based on the request.
	 * Note the lights for which the CSV file will be generated are already contained within this request.
	 *
	 * @param request the request
	 * @return the cSV internal response
	 */
	CSVInternalResponse generateLightSummaryFileCSV(LightSummaryCSVRequest request);

	/**
	 * Generate a light detail CSV file based on the request.
	 *
	 * @param request the request
	 * @return the cSV internal response
	 */
	CSVInternalResponse generateLightDetailFileCSV(LightCSVRequest request);
}
