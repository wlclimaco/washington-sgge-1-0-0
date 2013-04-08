package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Tipcliente;
import com.sensus.mlc.gestao.model.request.InquiryTipclienteRequest;
import com.sensus.mlc.gestao.model.request.TipclienteRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface ITipclienteBCL
{

	/**
	 * Insert tipcliente.
	 *
	 * @param tipclienteRequest the tipcliente request
	 * @return the tipcliente response
	 */
	public InternalResultsResponse<Tipcliente> insertTipcliente(TipclienteRequest tipclienteRequest);

	/**
	 * Update tipcliente.
	 *
	 * @param tipclienteRequest the tipcliente request
	 * @return the tipcliente response
	 */
	public InternalResultsResponse<Tipcliente> updateTipcliente(TipclienteRequest tipclienteRequest);

	/**
	 * Delete tipcliente.
	 *
	 * @param tipclienteRequest the tipcliente request
	 * @return the tipcliente response
	 */
	public InternalResponse deleteTipcliente(TipclienteRequest tipclienteRequest);

	/**
	 * Fetch all tipcliente.
	 *
	 * @param inquirytipclienteRequest the inquirytipcliente request
	 * @return the inquiry tipcliente response
	 */
	public InternalResultsResponse<Tipcliente> fetchAllTipcliente(InquiryTipclienteRequest inquirytipclienteRequest);

	/**
	 * Fetch tipcliente by id.
	 *
	 * @param inquirytipclienteRequest the inquirytipcliente request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tipcliente> fetchTipclienteById(TipclienteRequest tipclienteRequest);

	/**
	 * Fetch all tipcliente types.
	 *
	 * @param request the request
	 * @return the tipcliente response
	 */
	public InternalResultsResponse<Tipcliente> fetchAllTipclienteTypes(InquiryTipclienteRequest inquirytipclienteRequest);

	/**
	 * Fetch all tipcliente filial.
	 *
	 * @param request the request
	 * @return the tipcliente response
	 */
	public InternalResultsResponse<Tipcliente> fetchAllTipclienteFilial(TipclienteRequest tipclienteRequest);

}
