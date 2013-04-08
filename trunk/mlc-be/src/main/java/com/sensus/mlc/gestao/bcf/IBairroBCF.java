package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.BairroRequest;
import com.sensus.mlc.gestao.model.request.InquiryBairroRequest;
import com.sensus.mlc.gestao.model.response.BairroResponse;
import com.sensus.mlc.gestao.model.response.InquiryBairroResponse;


/**
 * The Interface IBairroBCF.
 *
 * @author Washington.Costa
 */
public interface IBairroBCF
{

	/**
	 * Insert bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response
	 */
	public BairroResponse insertBairro(BairroRequest bairroRequest);

	/**
	 * Update bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response
	 */
	public BairroResponse updateBairro(BairroRequest bairroRequest);

	/**
	 * Delete bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response
	 */
	public BairroResponse deleteBairro(BairroRequest bairroRequest);

	/**
	 * Fetch all bairro.
	 *
	 * @param inquiryBairroRequest the inquiryBairro request
	 * @return the inquiry bairro response
	 */
	public InquiryBairroResponse fetchAllBairro(InquiryBairroRequest inquiryBairroRequest);

	/**
	 * Fetch bairro by id.
	 *
	 * @param BairroRequest the bairro request
	 * @return the bairro response
	 */
	public BairroResponse fetchBairroById(BairroRequest bairroRequest);

}
