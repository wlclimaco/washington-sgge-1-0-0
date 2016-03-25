package com.qat.samples.sysmgmt.nf.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;

/**
 * The Interface INotaFiscalItensDAC.
 */
public interface INotaFiscalItensDAC
{

	/**
	 * Update nota fiscal itens.
	 * 
	 * @param notaFiscalItens the nota fiscal itens
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateNotaFiscalItens(NotaFiscalItens notaFiscalItens, InternalResultsResponse<?> response);

	/**
	 * Insert nota fiscal itens.
	 * 
	 * @param notaFiscalItens the nota fiscal itens
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertNotaFiscalItens(NotaFiscalItens notaFiscalItens, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete nota fiscal itens.
	 * 
	 * @param notaFiscalItens the nota fiscal itens
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteNotaFiscalItens(NotaFiscalItens notaFiscalItens, InternalResultsResponse<?> response);

	/**
	 * Fetch nota fiscal itens by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NotaFiscalItens> fetchNotaFiscalItensByRequest(PagedInquiryRequest request);

	public InternalResultsResponse<NotaFiscalItens> fetchNotaFiscalItensById(FetchByIdRequest request);

}
