package com.sensus.lc.academia.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.academia.model.Academia;
import com.sensus.lc.academia.model.request.AcademiaRequest;
import com.sensus.lc.academia.model.request.InquiryAcademiaRequest;

/**
 * The Interface IAcademiaDAC.
 * 
 * @author - Washington.
 */
public interface IAcademiaDAC
{
	/**
	 * Fetch all academias.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> fetchAllAcademias(InquiryAcademiaRequest inquiryAcademiaRequest);

	/**
	 * Fetch all academia by user.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> fetchAllAcademiaByUser(InquiryAcademiaRequest inquiryAcademiaRequest);

	/**
	 * Fetch devices by academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> fetchAcademiaById(InquiryAcademiaRequest inquiryAcademiaRequest);

	/**
	 * Fetch devices by academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> fetchAcademiasByName(AcademiaRequest academiaRequest);

	/**
	 * Insert academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> insertAcademia(AcademiaRequest academiaRequest);

	/**
	 * Delete academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the internal response
	 */
	InternalResponse deleteAcademia(AcademiaRequest academiaRequest);

	/**
	 * Update academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> updateAcademia(AcademiaRequest academiaRequest);

}
