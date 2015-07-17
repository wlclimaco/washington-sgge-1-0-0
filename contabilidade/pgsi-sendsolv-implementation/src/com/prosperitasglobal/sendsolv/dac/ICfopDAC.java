package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.CfopPessoa;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

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
	public Integer updateCfop(Cfop cfop);

	/**
	 * Insert cfop.
	 *
	 * @param cfop the cfop
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertCfop(Cfop cfop);

	/**
	 * Delete business cfop.
	 *
	 * @param cfop the cfop
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteCfop(Cfop cfop);

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
	public Integer updateCfopPessoa(CfopPessoa cfop);

	/**
	 * Insert cfop.
	 *
	 * @param cfop the cfop
	 * @return the integer
	 */
	public Integer insertCfopPessoa(CfopPessoa cfop);

	/**
	 * Delete business cfop.
	 *
	 * @param cfop the cfop
	 * @return the integer
	 */
	public Integer deleteCfopPessoa(CfopPessoa cfop);

}