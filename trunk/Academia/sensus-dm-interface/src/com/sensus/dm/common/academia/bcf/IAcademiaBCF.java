package com.sensus.dm.common.academia.bcf;

import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.academia.model.request.AcademiaRequest;
import com.sensus.dm.common.academia.model.request.InquiryAcademiaRequest;
import com.sensus.dm.common.academia.model.response.AcademiaResponse;
import com.sensus.dm.common.academia.model.response.InquiryAcademiaResponse;

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

	/**
	 * Fetch devices by academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesByAcademia(AcademiaRequest academiaRequest);

}

