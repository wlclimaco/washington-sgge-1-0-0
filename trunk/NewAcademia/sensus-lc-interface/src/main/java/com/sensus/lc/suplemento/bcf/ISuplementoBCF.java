package com.sensus.lc.suplemento.bcf;

import com.sensus.lc.suplemento.model.request.InquirySuplementoRequest;
import com.sensus.lc.suplemento.model.request.SuplementoRequest;
import com.sensus.lc.suplemento.model.response.InquirySuplementoResponse;
import com.sensus.lc.suplemento.model.response.SuplementoResponse;

/**
 * The Interface ISuplementoBCF.
 * 
 * @author Washington.
 * 
 */
public interface ISuplementoBCF
{

	/**
	 * Fetch all suplementos.
	 * 
	 * @param inquirySuplementoRequest the inquiry suplemento request
	 * @return the inquiry suplemento response
	 */
	InquirySuplementoResponse fetchAllSuplementos(InquirySuplementoRequest inquirySuplementoRequest);

	/**
	 * Fetch suplemento by id.
	 * 
	 * @param inquirySuplementoRequest the inquiry suplemento request
	 * @return the suplemento response
	 */
	SuplementoResponse fetchSuplementoById(InquirySuplementoRequest inquirySuplementoRequest);

	/**
	 * Fetch suplemento by name.
	 * 
	 * @param inquirySuplementoRequest the inquiry suplemento request
	 * @return the suplemento response
	 */
	SuplementoResponse fetchSuplementoByName(InquirySuplementoRequest inquirySuplementoRequest);

	/**
	 * Insert suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the suplemento response
	 */
	SuplementoResponse insertSuplemento(SuplementoRequest suplementoRequest);

	/**
	 * Update suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the suplemento response
	 */
	SuplementoResponse updateSuplemento(SuplementoRequest suplementoRequest);

	/**
	 * Delete suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the suplemento response
	 */
	SuplementoResponse deleteSuplemento(SuplementoRequest suplementoRequest);

}
