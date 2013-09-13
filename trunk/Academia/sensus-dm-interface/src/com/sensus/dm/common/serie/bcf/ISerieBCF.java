package com.sensus.dm.common.serie.bcf;

import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.serie.model.request.InquirySerieRequest;
import com.sensus.dm.common.serie.model.request.SerieRequest;
import com.sensus.dm.common.serie.model.response.InquirySerieResponse;
import com.sensus.dm.common.serie.model.response.SerieResponse;

/**
 * The Interface ISerieBCF.
 * 
 * @author Washington.
 * 
 */
public interface ISerieBCF
{

	/**
	 * Fetch all series.
	 * 
	 * @param inquirySerieRequest the inquiry serie request
	 * @return the inquiry serie response
	 */
	InquirySerieResponse fetchAllSeries(InquirySerieRequest inquirySerieRequest);

	/**
	 * Fetch serie by id.
	 * 
	 * @param inquirySerieRequest the inquiry serie request
	 * @return the serie response
	 */
	SerieResponse fetchSerieById(InquirySerieRequest inquirySerieRequest);

	/**
	 * Fetch serie by name.
	 * 
	 * @param inquirySerieRequest the inquiry serie request
	 * @return the serie response
	 */
	SerieResponse fetchSerieByName(InquirySerieRequest inquirySerieRequest);

	/**
	 * Insert serie.
	 * 
	 * @param serieRequest the serie request
	 * @return the serie response
	 */
	SerieResponse insertSerie(SerieRequest serieRequest);

	/**
	 * Update serie.
	 * 
	 * @param serieRequest the serie request
	 * @return the serie response
	 */
	SerieResponse updateSerie(SerieRequest serieRequest);

	/**
	 * Delete serie.
	 * 
	 * @param serieRequest the serie request
	 * @return the serie response
	 */
	SerieResponse deleteSerie(SerieRequest serieRequest);

	/**
	 * Fetch devices by serie.
	 * 
	 * @param serieRequest the serie request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesBySerie(SerieRequest serieRequest);

}
