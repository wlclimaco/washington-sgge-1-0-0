package com.sensus.dm.common.dieta.bcf;

import com.sensus.dm.common.dieta.model.request.DietaitensRequest;
import com.sensus.dm.common.dieta.model.request.InquiryDietaitensRequest;
import com.sensus.dm.common.dieta.model.response.DietaitensResponse;
import com.sensus.dm.common.dieta.model.response.InquiryDietaitensResponse;

/**
 * The Interface IDietaitensBCF.
 * 
 * @author Washington.
 * 
 */
public interface IDietaitensBCF
{

	/**
	 * Fetch all dietaitenss.
	 * 
	 * @param inquiryDietaitensRequest the inquiry dietaitens request
	 * @return the inquiry dietaitens response
	 */
	InquiryDietaitensResponse fetchAllDietaitens(InquiryDietaitensRequest inquiryDietaitensRequest);

	/**
	 * Fetch dietaitens by id.
	 * 
	 * @param inquiryDietaitensRequest the inquiry dietaitens request
	 * @return the dietaitens response
	 */
	DietaitensResponse fetchDietaitensById(InquiryDietaitensRequest inquiryDietaitensRequest);

	/**
	 * Fetch dietaitens by name.
	 * 
	 * @param inquiryDietaitensRequest the inquiry dietaitens request
	 * @return the dietaitens response
	 */
	InquiryDietaitensResponse fetchDietaitensByName(InquiryDietaitensRequest inquiryDietaitensRequest);

	/**
	 * Insert dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the dietaitens response
	 */
	DietaitensResponse insertDietaitens(DietaitensRequest dietaitensRequest);

	/**
	 * Update dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the dietaitens response
	 */
	DietaitensResponse updateDietaitens(DietaitensRequest dietaitensRequest);

	/**
	 * Delete dietaitens.
	 * 
	 * @param dietaitensRequest the dietaitens request
	 * @return the dietaitens response
	 */
	DietaitensResponse deleteDietaitens(DietaitensRequest dietaitensRequest);

}
