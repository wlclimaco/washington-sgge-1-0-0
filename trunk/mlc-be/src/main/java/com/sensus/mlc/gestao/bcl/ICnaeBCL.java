package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Cnae;
import com.sensus.mlc.gestao.model.request.CnaeRequest;
import com.sensus.mlc.gestao.model.request.InquiryCnaeRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface ICnaeBCL
{

	/**
	 * Insert cnae.
	 *
	 * @param cnaeRequest the cnae request
	 * @return the cnae response
	 */
	public InternalResultsResponse<Cnae> insertCnae(CnaeRequest cnaeRequest);

	/**
	 * Update cnae.
	 *
	 * @param cnaeRequest the cnae request
	 * @return the cnae response
	 */
	public InternalResultsResponse<Cnae> updateCnae(CnaeRequest cnaeRequest);

	/**
	 * Delete cnae.
	 *
	 * @param cnaeRequest the cnae request
	 * @return the cnae response
	 */
	public InternalResponse deleteCnae(CnaeRequest cnaeRequest);

	/**
	 * Fetch all cnae.
	 *
	 * @param inquirycnaeRequest the inquirycnae request
	 * @return the inquiry cnae response
	 */
	public InternalResultsResponse<Cnae> fetchAllCnae(InquiryCnaeRequest inquirycnaeRequest);

	/**
	 * Fetch cnae by id.
	 *
	 * @param inquirycnaeRequest the inquirycnae request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cnae> fetchCnaeById(CnaeRequest cnaeRequest);

	/**
	 * Fetch all cnae types.
	 *
	 * @param request the request
	 * @return the cnae response
	 */
	public InternalResultsResponse<Cnae> fetchAllCnaeTypes(InquiryCnaeRequest inquirycnaeRequest);

	/**
	 * Fetch all cnae filial.
	 *
	 * @param request the request
	 * @return the cnae response
	 */
	public InternalResultsResponse<Cnae> fetchAllCnaeFilial(CnaeRequest cnaeRequest);

}
