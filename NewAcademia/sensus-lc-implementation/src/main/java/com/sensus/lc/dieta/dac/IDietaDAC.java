package com.sensus.lc.dieta.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.dieta.model.Dieta;
import com.sensus.lc.dieta.model.request.DietaRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaRequest;

/**
 * The Interface IDietaDAC.
 * 
 * @author - Washington.
 */
public interface IDietaDAC
{
	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryDietaRequest the inquiry dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> fetchAllDietas(InquiryDietaRequest inquiryDietaRequest);

	/**
	 * Fetch devices by dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> fetchDevicesById(DietaRequest dietaRequest);

	/**
	 * Fetch devices by dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> fetchDevicesByName(DietaRequest dietaRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> insertDieta(DietaRequest dietaRequest);

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
	 * @return the internal results response
	 */
	InternalResultsResponse<Dieta> updateDieta(DietaRequest dietaRequest);

}
