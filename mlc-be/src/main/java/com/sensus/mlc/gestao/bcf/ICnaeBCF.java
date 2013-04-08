package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.CnaeRequest;
import com.sensus.mlc.gestao.model.request.InquiryCnaeRequest;
import com.sensus.mlc.gestao.model.response.CnaeResponse;
import com.sensus.mlc.gestao.model.response.InquiryCnaeResponse;


/**
 * The Interface ICnaeBCF.
 *
 * @author Washington.Costa
 */
public interface ICnaeBCF
{

	/**
	 * Insert cnae.
	 *
	 * @param cnaeRequest the cnae request
	 * @return the cnae response
	 */
	public CnaeResponse insertCnae(CnaeRequest cnaeRequest);

	/**
	 * Update cnae.
	 *
	 * @param cnaeRequest the cnae request
	 * @return the cnae response
	 */
	public CnaeResponse updateCnae(CnaeRequest cnaeRequest);

	/**
	 * Delete cnae.
	 *
	 * @param cnaeRequest the cnae request
	 * @return the cnae response
	 */
	public CnaeResponse deleteCnae(CnaeRequest cnaeRequest);

	/**
	 * Fetch all cnae.
	 *
	 * @param inquiryCnaeRequest the inquiryCnae request
	 * @return the inquiry cnae response
	 */
	public InquiryCnaeResponse fetchAllCnae(InquiryCnaeRequest inquiryCnaeRequest);

	/**
	 * Fetch cnae by id.
	 *
	 * @param CnaeRequest the cnae request
	 * @return the cnae response
	 */
	public CnaeResponse fetchCnaeById(CnaeRequest cnaeRequest);

}
