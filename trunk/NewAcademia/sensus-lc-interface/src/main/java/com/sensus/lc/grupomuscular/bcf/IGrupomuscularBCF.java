package com.sensus.lc.grupomuscular.bcf;

import com.sensus.lc.grupomuscular.model.request.GrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.request.InquiryGrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.response.GrupomuscularResponse;
import com.sensus.lc.grupomuscular.model.response.InquiryGrupomuscularResponse;

/**
 * The Interface IGrupomuscularBCF.
 * 
 * @author Washington.
 * 
 */
public interface IGrupomuscularBCF
{

	/**
	 * Fetch all grupomusculars.
	 * 
	 * @param inquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the inquiry grupomuscular response
	 */
	InquiryGrupomuscularResponse fetchAllGrupomusculars(InquiryGrupomuscularRequest inquiryGrupomuscularRequest);

	/**
	 * Fetch grupomuscular by id.
	 * 
	 * @param inquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the grupomuscular response
	 */
	GrupomuscularResponse fetchGrupomuscularById(InquiryGrupomuscularRequest inquiryGrupomuscularRequest);

	/**
	 * Fetch grupomuscular by name.
	 * 
	 * @param inquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the grupomuscular response
	 */
	GrupomuscularResponse fetchGrupomuscularByName(InquiryGrupomuscularRequest inquiryGrupomuscularRequest);

	/**
	 * Insert grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the grupomuscular response
	 */
	GrupomuscularResponse insertGrupomuscular(GrupomuscularRequest grupomuscularRequest);

	/**
	 * Update grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the grupomuscular response
	 */
	GrupomuscularResponse updateGrupomuscular(GrupomuscularRequest grupomuscularRequest);

	/**
	 * Delete grupomuscular.
	 * 
	 * @param grupomuscularRequest the grupomuscular request
	 * @return the grupomuscular response
	 */
	GrupomuscularResponse deleteGrupomuscular(GrupomuscularRequest grupomuscularRequest);

	/**
	 * Fetch all grupomuscular by user.
	 * 
	 * @param inquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the inquiry grupomuscular response
	 */
	InquiryGrupomuscularResponse fetchAllGrupomuscularByUser(InquiryGrupomuscularRequest inquiryGrupomuscularRequest);

}
