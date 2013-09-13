package com.sensus.dm.common.academia.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.academia.model.Academia;
import com.sensus.dm.common.academia.model.request.AcademiaRequest;
import com.sensus.dm.common.academia.model.request.InquiryAcademiaRequest;

/**
 * The Interface IAcademiaBCL.
 * 
 * @author Washington.
 * 
 */
public interface IAcademiaBCL
{

	/**
	 * Fetch all academias.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> fetchAllAcademias(InquiryAcademiaRequest inquiryPaginationRequest);

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
	 * @return the internal response
	 */
	InternalResultsResponse<Academia> updateAcademia(AcademiaRequest academiaRequest);

	/**
	 * Fetch academias by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> fetchAcademiasById(InquiryAcademiaRequest InquiryAcademiaRequest);

	/**
	 * Insert academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> insertAcademia(AcademiaRequest academiaRequest);

	/**
	 * Fetch devices by academia.
	 * 
	 * @param academiaRequest the academia request
	 * @return the internal results response
	 */
	InternalResultsResponse<Academia> fetchDevicesByName(AcademiaRequest academiaRequest);

}
