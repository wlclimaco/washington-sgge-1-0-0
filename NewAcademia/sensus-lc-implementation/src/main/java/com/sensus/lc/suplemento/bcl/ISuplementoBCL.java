package com.sensus.lc.suplemento.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.suplemento.model.Suplemento;
import com.sensus.lc.suplemento.model.request.InquirySuplementoRequest;
import com.sensus.lc.suplemento.model.request.SuplementoRequest;

/**
 * The Interface ISuplementoBCL.
 * 
 * @author Washington.
 * 
 */
public interface ISuplementoBCL
{

	/**
	 * Fetch all suplementos.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> fetchAllSuplementos(InquirySuplementoRequest inquiryPaginationRequest);

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
	 * @return the internal response
	 */
	InternalResultsResponse<Suplemento> updateSuplemento(SuplementoRequest suplementoRequest);

	/**
	 * Fetch suplementos by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> fetchSuplementosById(InquirySuplementoRequest InquirySuplementoRequest);

	/**
	 * Insert suplemento.
	 * 
	 * @param suplementoRequest the suplemento request
	 * @return the internal results response
	 */
	InternalResultsResponse<Suplemento> insertSuplemento(SuplementoRequest suplementoRequest);

}
