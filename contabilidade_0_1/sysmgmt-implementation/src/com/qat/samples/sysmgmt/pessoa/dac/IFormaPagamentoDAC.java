package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.FormaPgPessoa;
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
	public Integer updateFormaPg(FormaPg formaPg);

	/**
	 * Insert formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertFormaPg(FormaPg formaPg);

	/**
	 * Delete business formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteFormaPg(FormaPg formaPg);

	/**
	 * Fetch formaPg by id.
	 * 
	 * @param id the id
	 * @return the internal results response< formaPg>
	 */
	public InternalResultsResponse<FormaPg> fetchFormaPgById(Integer id);

	/**
	 * Update forma pg pessoa.
	 * 
	 * @param formaPg the forma pg
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateFormaPgPessoa(FormaPgPessoa formaPg);

	/**
	 * Insert formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertFormaPgPessoa(FormaPgPessoa formaPg);

	/**
	 * Delete business formaPg.
	 * 
	 * @param formaPg the formaPg
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteFormaPgPessoa(FormaPgPessoa formaPg);

	public InternalResultsResponse<FormaPg> fetchFormaPgByRequest(PagedInquiryRequest request);
}
