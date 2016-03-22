package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.CfopPessoa;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICfopDAC.
 */
public interface ICfopDAC
{
	/**
	 * Update cfop.
	 * 
	 * @param cfop the cfop
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateCfop(Cfop cfop, InternalResultsResponse<?> response);

	/**
	 * Insert cfop.
	 * 
	 * @param cfop the cfop
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertCfop(Cfop cfop, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business cfop.
	 * 
	 * @param cfop the cfop
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteCfop(Cfop cfop, InternalResultsResponse<?> response);

	/**
	 * Fetch cfop by id.
	 * 
	 * @param id the id
	 * @return the internal results response< cfop>
	 */
	public InternalResultsResponse<Cfop> fetchCfopById(Integer id);

	/**
	 * Fetch cfop by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cfop> fetchCfopByRequest(PagedInquiryRequest request);

	/**
	 * Update cfop pessoa.
	 * 
	 * @param cfop the cfop
	 * @return the integer
	 */
	public Integer updateCfopPessoa(CfopPessoa cfop, InternalResultsResponse<?> response);

	/**
	 * Insert cfop.
	 * 
	 * @param cfop the cfop
	 * @return the integer
	 */
	public Integer insertCfopPessoa(CfopPessoa cfop, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business cfop.
	 * 
	 * @param cfop the cfop
	 * @return the integer
	 */
	public Integer deleteCfopPessoa(CfopPessoa cfop, InternalResultsResponse<?> response);

}