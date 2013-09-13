package com.sensus.dm.common.suplemento.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.suplemento.model.Suplemento;
import com.sensus.dm.common.suplemento.model.request.InquirySuplementoRequest;
import com.sensus.dm.common.suplemento.model.request.SuplementoRequest;

/**
 * The Interface ISuplementoDAC.
 * 
 * @author - Washington.
 */
public interface ISuplementoDAC
{
	/**
	 * Fetch all suplementos.
	 * 
	 * @param inquirySuplementoRequest the inquiry suplemento request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> fetchAllSuplementos(InquirySuplementoRequest inquirySuplementoRequest);

	/**
	 * Fetch devices by suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> fetchSuplementosById(InquirySuplementoRequest suplementoRequest);

	/**
	 * Fetch devices by suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> fetchDSuplementosByName(InquirySuplementoRequest suplementoRequest);

	/**
	 * Insert suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> insertSuplemento(SuplementoRequest suplementoRequest);

	/**
	 * Delete suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the internal response
	 */
	InternalResponse deleteSuplemento(SuplementoRequest suplementoRequest);

	/**
	 * Update suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> updateSuplemento(SuplementoRequest suplementoRequest);

}
