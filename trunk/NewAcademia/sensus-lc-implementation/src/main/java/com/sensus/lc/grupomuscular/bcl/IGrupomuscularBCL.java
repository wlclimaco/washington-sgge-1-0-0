package com.sensus.lc.grupomuscular.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.grupomuscular.model.Grupomuscular;
import com.sensus.lc.grupomuscular.model.request.GrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.request.InquiryGrupomuscularRequest;

/**
 * The Interface IGrupomuscularBCL.
 * 
 * @author Washington.
 * 
 */
public interface IGrupomuscularBCL
{

	/**
	 * Fetch all grupomusculars.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> fetchAllGrupomusculars(InquiryGrupomuscularRequest inquiryPaginationRequest);

	/**
	 * Delete grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the internal response
	 */
	InternalResponse deleteGrupomuscular(GrupomuscularRequest grupomuscularRequest);

	/**
	 * Update grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the internal response
	 */
	InternalResultsResponse<Grupomuscular> updateGrupomuscular(GrupomuscularRequest grupomuscularRequest);

	/**
	 * Fetch grupomusculars by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> fetchGrupomuscularsById(
			InquiryGrupomuscularRequest InquiryGrupomuscularRequest);

	/**
	 * Fetch all grupomuscular by user.
	 * 
	 * @param InquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> fetchAllGrupomuscularByUser(
			InquiryGrupomuscularRequest InquiryGrupomuscularRequest);

	/**
	 * Insert grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> insertGrupomuscular(GrupomuscularRequest grupomuscularRequest);

}
