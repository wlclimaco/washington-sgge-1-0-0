package com.sensus.lc.dieta.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.dieta.model.Dieta;
import com.sensus.lc.dieta.model.request.DietaRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaRequest;

/**
 * The Interface IDietaBCL.
 * 
 * @author Washington.
 * 
 */
public interface IDietaBCL
{

	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> fetchAllDietas(InquiryDietaRequest inquiryPaginationRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal response
	 */
	InternalResponse deleteDieta(DietaRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal response
	 */
	InternalResultsResponse<Dieta> updateDieta(DietaRequest dietaRequest);

	/**
	 * Fetch dietas by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> fetchDietasById(DietaRequest dietaRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> insertDieta(DietaRequest dietaRequest);

	/**
	 * Fetch devices by dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> fetchDietaByName(DietaRequest dietaRequest);

}
