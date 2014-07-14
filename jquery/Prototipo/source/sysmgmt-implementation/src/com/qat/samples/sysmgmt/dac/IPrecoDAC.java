package com.qat.samples.sysmgmt.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Tabelapreco;

/**
 * The Interface IPrecoDAC. (Data Access Component - DAC)
 */
public interface IPrecoDAC
{

	/**
	 * Insert bundle.
	 * 
	 * @param bundle the bundle
	 * @return the internal response
	 */
	public InternalResponse insertPreco(Tabelapreco preco);

	/**
	 * Update preco.
	 * 
	 * @param preco the preco
	 * 
	 * @return the internal response
	 */
	public InternalResponse updatePreco(Tabelapreco preco);

	/**
	 * Delete preco.
	 * 
	 * @param preco the preco
	 * 
	 * @return the internal response
	 */
	public InternalResponse deletePreco(Tabelapreco preco);

	/**
	 * Delete all precos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllPrecos();

	/**
	 * Fetch all precos.
	 * 
	 * @return the list< preco>
	 */
	public List<Tabelapreco> fetchAllPrecos();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Tabelapreco fetchPrecoById(FetchByIdRequest request);

	/**
	 * Fetch precos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tabelapreco> fetchPrecosByRequest(PagedInquiryRequest request);

}
