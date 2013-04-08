package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Bairro;
import com.sensus.mlc.gestao.model.request.BairroRequest;
import com.sensus.mlc.gestao.model.request.InquiryBairroRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IBairroBCL
{

	/**
	 * Insert bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response
	 */
	public InternalResultsResponse<Bairro> insertBairro(BairroRequest bairroRequest);

	/**
	 * Update bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response
	 */
	public InternalResultsResponse<Bairro> updateBairro(BairroRequest bairroRequest);

	/**
	 * Delete bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response
	 */
	public InternalResponse deleteBairro(BairroRequest bairroRequest);

	/**
	 * Fetch all bairro.
	 *
	 * @param inquirybairroRequest the inquirybairro request
	 * @return the inquiry bairro response
	 */
	public InternalResultsResponse<Bairro> fetchAllBairro(InquiryBairroRequest inquirybairroRequest);

	/**
	 * Fetch bairro by id.
	 *
	 * @param inquirybairroRequest the inquirybairro request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Bairro> fetchBairroById(BairroRequest bairroRequest);

	/**
	 * Fetch all bairro types.
	 *
	 * @param request the request
	 * @return the bairro response
	 */
	public InternalResultsResponse<Bairro> fetchAllBairroTypes(InquiryBairroRequest inquirybairroRequest);

	/**
	 * Fetch all bairro filial.
	 *
	 * @param request the request
	 * @return the bairro response
	 */
	public InternalResultsResponse<Bairro> fetchAllBairroFilial(BairroRequest bairroRequest);

}
