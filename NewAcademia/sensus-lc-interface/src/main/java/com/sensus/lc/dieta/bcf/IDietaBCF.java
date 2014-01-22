package com.sensus.lc.dieta.bcf;

import com.sensus.lc.dieta.model.request.DietaRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaRequest;
import com.sensus.lc.dieta.model.response.DietaResponse;
import com.sensus.lc.dieta.model.response.InquiryDietaResponse;

/**
 * The Interface IDietaBCF.
 * 
 * @author Washington.
 * 
 */
public interface IDietaBCF
{

	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryDietaRequest the inquiry dieta request
	 * @return the inquiry dieta response
	 */
	InquiryDietaResponse fetchAllDietas(InquiryDietaRequest inquiryDietaRequest);

	/**
	 * Fetch dieta by id.
	 * 
	 * @param inquiryDietaRequest the inquiry dieta request
	 * @return the dieta response
	 */
	DietaResponse fetchDietaById(InquiryDietaRequest inquiryDietaRequest);

	/**
	 * Fetch dieta by name.
	 * 
	 * @param inquiryDietaRequest the inquiry dieta request
	 * @return the dieta response
	 */
	DietaResponse fetchDietaByName(InquiryDietaRequest inquiryDietaRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	DietaResponse insertDieta(DietaRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	DietaResponse updateDieta(DietaRequest dietaRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	DietaResponse deleteDieta(DietaRequest dietaRequest);

}
