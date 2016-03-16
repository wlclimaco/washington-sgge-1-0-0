package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Financeiro;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IFinanceiroDAC.
 */
public interface IFinanceiroDAC
{

	/**
	 * Update endereco.
	 *
	 * @param endereco the endereco
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Financeiro> updateFinanceiro(Financeiro endereco);

	/**
	 * Insert endereco.
	 *
	 * @param endereco the endereco
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Financeiro> insertFinanceiro(Financeiro endereco);

	/**
	 * Delete endereco.
	 *
	 * @param endereco the endereco
	 * @return the internal response
	 */
	public InternalResponse deleteFinanceiro(Financeiro endereco);

	/**
	 * Fetch endereco by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Financeiro> fetchFinanceiroById(FetchByIdRequest request);

	/**
	 * Fetch all enderecos.
	 *
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Financeiro> fetchAllFinanceiros();

	/**
	 * Fetch endereco by request.
	 *
	 * @param request the request
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Financeiro> fetchFinanceiroByRequest(PagedInquiryRequest request);

}
