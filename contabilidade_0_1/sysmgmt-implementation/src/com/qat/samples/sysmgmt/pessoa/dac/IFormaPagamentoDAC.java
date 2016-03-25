package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.FormaPgPessoa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IFormaPgDAC.
 */
public interface IFormaPagamentoDAC
{

	/**
	 * Update formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateFormaPg(FormaPg formaPg, InternalResultsResponse<?> response);

	/**
	 * Insert formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertFormaPg(FormaPg formaPg, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteFormaPg(FormaPg formaPg, InternalResultsResponse<?> response);

	/**
	 * Fetch formaPg by id.
	 * 
	 * @param request the id
	 * @return the internal results response< formaPg>
	 */
	public InternalResultsResponse<FormaPg> fetchFormaPgById(FetchByIdRequest request);

	/**
	 * Update forma pg pessoa.
	 * 
	 * @param formaPg the forma pg
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateFormaPgPessoa(FormaPgPessoa formaPg, InternalResultsResponse<?> response);

	/**
	 * Insert formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertFormaPgPessoa(FormaPgPessoa formaPg, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteFormaPgPessoa(FormaPgPessoa formaPg, InternalResultsResponse<?> response);

	public InternalResultsResponse<FormaPg> fetchFormaPgByRequest(PagedInquiryRequest request);
}
