package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.InquiryUfRequest;
import com.sensus.mlc.gestao.model.request.UfRequest;
import com.sensus.mlc.gestao.model.response.InquiryUfResponse;
import com.sensus.mlc.gestao.model.response.UfResponse;


/**
 * The Interface IUfBCF.
 *
 * @author Washington.Costa
 */
public interface IUfBCF
{

	/**
	 * Insert uf.
	 *
	 * @param ufRequest the uf request
	 * @return the uf response
	 */
	public UfResponse insertUf(UfRequest ufRequest);

	/**
	 * Update uf.
	 *
	 * @param ufRequest the uf request
	 * @return the uf response
	 */
	public UfResponse updateUf(UfRequest ufRequest);

	/**
	 * Delete uf.
	 *
	 * @param ufRequest the uf request
	 * @return the uf response
	 */
	public UfResponse deleteUf(UfRequest ufRequest);

	/**
	 * Fetch all uf.
	 *
	 * @param inquiryUfRequest the inquiryUf request
	 * @return the inquiry uf response
	 */
	public InquiryUfResponse fetchAllUf(InquiryUfRequest inquiryUfRequest);

	/**
	 * Fetch uf by id.
	 *
	 * @param UfRequest the uf request
	 * @return the uf response
	 */
	public UfResponse fetchUfById(UfRequest ufRequest);

}
