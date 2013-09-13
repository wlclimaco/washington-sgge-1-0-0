package com.sensus.dm.common.dieta.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.dieta.model.Dietaitens;
import com.sensus.dm.common.dieta.model.request.DietaitensRequest;
import com.sensus.dm.common.dieta.model.request.InquiryDietaitensRequest;

/**
 * The Interface IDietaitensDAC.
 * 
 * @author - Washington.
 */
public interface IDietaitensDAC
{
	/**
	 * Fetch all dietaitenss.
	 * 
	 * @param inquiryDietaitensRequest the inquiry dietaitens request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> fetchAllDietaitenss(InquiryDietaitensRequest inquiryDietaitensRequest);

	/**
	 * Fetch devices by dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> fetchDietaitensById(InquiryDietaitensRequest dietaitensRequest);

	/**
	 * Fetch devices by dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> fetchDietaitensByName(InquiryDietaitensRequest dietaitensRequest);

	/**
	 * Insert dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> insertDietaitens(DietaitensRequest dietaitensRequest);

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
	 * @return the internal results response
	 */
	InternalResultsResponse<Dietaitens> updateDietaitens(DietaitensRequest dietaitensRequest);

}
