package com.qat.samples.sysmgmt.cidade.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface ICidadeDAC. (Data Access Component - DAC)
 */
public interface ICidadeDAC
{

	/**
	 * Insert cidade.
	 * 
	 * @param cidade the cidade
	 * @return the internal response
	 */
	public InternalResponse insertCidade(Cidade cidade);

	/**
	 * Update cidade.
	 * 
	 * @param cidade the cidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateCidade(Cidade cidade);

	/**
	 * Delete cidade.
	 * 
	 * @param cidade the cidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteCidade(Cidade cidade);

	/**
	 * Delete all cidades.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllCidades();

	/**
	 * Fetch all cidades.
	 * 
	 * @return the list< cidade>
	 */
	public List<Cidade> fetchAllCidades();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Cidade fetchCidadeById(FetchByIdRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cidade> fetchCidadesByRequest(PagedInquiryRequest request);

}
