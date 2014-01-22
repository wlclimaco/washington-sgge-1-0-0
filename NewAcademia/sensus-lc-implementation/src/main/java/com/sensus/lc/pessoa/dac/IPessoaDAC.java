package com.sensus.lc.pessoa.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.pessoa.model.Pessoa;
import com.sensus.lc.pessoa.model.request.InquiryPessoaRequest;
import com.sensus.lc.pessoa.model.request.PessoaRequest;

/**
 * The Interface IPessoaDAC.
 * 
 * @author - Washington.
 */
public interface IPessoaDAC
{
	/**
	 * Fetch all pessoas.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> fetchAllPessoas(InquiryPessoaRequest inquiryPessoaRequest);

	/**
	 * Fetch devices by pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> fetchPessoasById(InquiryPessoaRequest pessoaRequest);

	/**
	 * Fetch devices by pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> fetchPessoasByName(InquiryPessoaRequest pessoaRequest);

	/**
	 * Insert pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> insertPessoa(PessoaRequest pessoaRequest);

	/**
	 * Delete pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the internal response
	 */
	InternalResponse deletePessoa(PessoaRequest pessoaRequest);

	/**
	 * Update pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> updatePessoa(PessoaRequest pessoaRequest);

}
