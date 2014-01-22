package com.sensus.lc.dieta.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.dieta.model.Dietaitens;
import com.sensus.lc.dieta.model.request.DietaitensRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaitensRequest;

/**
 * The Interface IDietaitensBCL.
 * 
 * @author Washington.
 * 
 */
public interface IDietaitensBCL
{

	/**
	 * Fetch all dietaitenss.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> fetchAllDietaitenss(InquiryDietaitensRequest inquiryPaginationRequest);

	/**
	 * Delete dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the internal response
	 */
	InternalResponse deleteDietaitens(DietaitensRequest dietaitensRequest);

	/**
	 * Update dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the internal response
	 */
	InternalResultsResponse<Dietaitens> updateDietaitens(DietaitensRequest dietaitensRequest);

	/**
	 * Fetch dietaitenss by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> fetchDietaitenssById(InquiryDietaitensRequest inquiryPaginationRequest);

	/**
	 * Insert dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> insertDietaitens(DietaitensRequest dietaitensRequest);

	/**
	 * Fetch devices by dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> fetchDietaitensByName(InquiryDietaitensRequest dietaitensRequest);

}
