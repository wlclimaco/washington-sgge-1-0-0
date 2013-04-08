package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Uf;
import com.sensus.mlc.gestao.model.request.InquiryUfRequest;
import com.sensus.mlc.gestao.model.request.UfRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IUfBCL
{

	/**
	 * Insert uf.
	 *
	 * @param ufRequest the uf request
	 * @return the uf response
	 */
	public InternalResultsResponse<Uf> insertUf(UfRequest ufRequest);

	/**
	 * Update uf.
	 *
	 * @param ufRequest the uf request
	 * @return the uf response
	 */
	public InternalResultsResponse<Uf> updateUf(UfRequest ufRequest);

	/**
	 * Delete uf.
	 *
	 * @param ufRequest the uf request
	 * @return the uf response
	 */
	public InternalResponse deleteUf(UfRequest ufRequest);

	/**
	 * Fetch all uf.
	 *
	 * @param inquiryufRequest the inquiryuf request
	 * @return the inquiry uf response
	 */
	public InternalResultsResponse<Uf> fetchAllUf(InquiryUfRequest inquiryufRequest);

	/**
	 * Fetch uf by id.
	 *
	 * @param inquiryufRequest the inquiryuf request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Uf> fetchUfById(UfRequest ufRequest);

	/**
	 * Fetch all uf types.
	 *
	 * @param request the request
	 * @return the uf response
	 */
	public InternalResultsResponse<Uf> fetchAllUfTypes(InquiryUfRequest inquiryufRequest);

	/**
	 * Fetch all uf filial.
	 *
	 * @param request the request
	 * @return the uf response
	 */
	public InternalResultsResponse<Uf> fetchAllUfFilial(UfRequest ufRequest);

}
