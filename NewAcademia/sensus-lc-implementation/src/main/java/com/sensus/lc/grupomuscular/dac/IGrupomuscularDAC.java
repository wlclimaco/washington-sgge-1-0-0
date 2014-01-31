package com.sensus.lc.grupomuscular.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.grupomuscular.model.Grupomuscular;
import com.sensus.lc.grupomuscular.model.request.GrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.request.InquiryGrupomuscularRequest;

/**
 * The Interface IGrupomuscularDAC.
 * 
 * @author - Washington.
 */
public interface IGrupomuscularDAC
{
	/**
	 * Fetch all grupomusculars.
	 * 
	 * @param inquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> fetchAllGrupomusculars(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest);

	/**
	 * Fetch all grupomuscular by user.
	 * 
	 * @param inquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> fetchAllGrupomuscularByUser(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest);

	/**
	 * Fetch devices by grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> fetchGrupomuscularById(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest);

	/**
	 * Fetch devices by grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> fetchGrupomuscularsByName(GrupomuscularRequest grupomuscularRequest);

	/**
	 * Insert grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> insertGrupomuscular(GrupomuscularRequest grupomuscularRequest);

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
	 * @return the internal results response
	 */
	InternalResultsResponse<Grupomuscular> updateGrupomuscular(GrupomuscularRequest grupomuscularRequest);

}
