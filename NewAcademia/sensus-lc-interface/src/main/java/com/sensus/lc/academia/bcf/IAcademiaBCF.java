package com.sensus.lc.academia.bcf;

import com.sensus.lc.academia.model.request.AcademiaRequest;
import com.sensus.lc.academia.model.request.InquiryAcademiaRequest;
import com.sensus.lc.academia.model.response.AcademiaResponse;
import com.sensus.lc.academia.model.response.InquiryAcademiaResponse;

/**
 * The Interface IAcademiaBCF.
 * 
 * @author Washington.
 * 
 */
public interface IAcademiaBCF
{

	/**
	 * Fetch all academias.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the inquiry academia response
	 */
	InquiryAcademiaResponse fetchAllAcademias(InquiryAcademiaRequest inquiryAcademiaRequest);

	/**
	 * Fetch all academia by user.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the inquiry academia response
	 */
	InquiryAcademiaResponse fetchAllAcademiaByUser(InquiryAcademiaRequest inquiryAcademiaRequest);

	/**
	 * Fetch academia by id.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the academia response
	 */
	AcademiaResponse fetchAcademiaById(InquiryAcademiaRequest inquiryAcademiaRequest);

	/**
	 * Fetch academia by name.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the academia response
	 */
	AcademiaResponse fetchAcademiaByName(InquiryAcademiaRequest inquiryAcademiaRequest);

	/**
	 * Insert academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the academia response
	 */
	AcademiaResponse insertAcademia(AcademiaRequest academiaRequest);

	/**
	 * Update academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the academia response
	 */
	AcademiaResponse updateAcademia(AcademiaRequest academiaRequest);

	/**
	 * Delete academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the academia response
	 */
	AcademiaResponse deleteAcademia(AcademiaRequest academiaRequest);

}
