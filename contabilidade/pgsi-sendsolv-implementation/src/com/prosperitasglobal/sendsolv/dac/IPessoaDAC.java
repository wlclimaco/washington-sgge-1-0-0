package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Pessoa;
import com.prosperitasglobal.sendsolv.model.request.PessoaInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IPessoaDAC.
 */
public interface IPessoaDAC
{

	/**
	 * Update cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Pessoa> updatePessoa(Pessoa cnae);

	/**
	 * Insert cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Pessoa> insertPessoa(Pessoa cnae);

	/**
	 * Delete cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal response
	 */
	public InternalResponse deletePessoa(Pessoa cnae);

	/**
	 * Fetch cnae by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Pessoa> fetchPessoaById(FetchByIdRequest request);

	/**
	 * Fetch all cnaes.
	 *
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Pessoa> fetchAllPessoas();

	/**
	 * Fetch cnae by request.
	 *
	 * @param request the request
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Pessoa> fetchPessoaByRequest(PessoaInquiryRequest request);

}
