package com.sensus.dm.common.pessoa.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.pessoa.model.Pessoa;
import com.sensus.dm.common.pessoa.model.request.InquiryPessoaRequest;
import com.sensus.dm.common.pessoa.model.request.PessoaRequest;

/**
 * The Interface IPessoaBCL.
 * 
 * @author Washington.
 * 
 */
public interface IPessoaBCL
{

	/**
	 * Fetch all pessoas.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> fetchAllPessoas(InquiryPessoaRequest inquiryPaginationRequest);

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
	 * @return the internal response
	 */
	InternalResultsResponse<Pessoa> updatePessoa(PessoaRequest pessoaRequest);

	/**
	 * Fetch pessoas by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> fetchPessoasById(InquiryPessoaRequest InquiryPessoaRequest);

	/**
	 * Insert pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> insertPessoa(PessoaRequest pessoaRequest);

	/**
	 * Fetch devices by pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the internal results response
	 */
	InternalResultsResponse<Pessoa> fetchPessoasByName(InquiryPessoaRequest pessoaRequest);

}
