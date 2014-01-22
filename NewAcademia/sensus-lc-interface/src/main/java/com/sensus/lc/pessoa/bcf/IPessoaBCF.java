package com.sensus.lc.pessoa.bcf;

import com.sensus.lc.pessoa.model.request.InquiryPessoaRequest;
import com.sensus.lc.pessoa.model.request.PessoaRequest;
import com.sensus.lc.pessoa.model.response.InquiryPessoaResponse;
import com.sensus.lc.pessoa.model.response.PessoaResponse;


/**
 * The Interface IPessoaBCF.
 * 
 * @author Washington.
 * 
 */
public interface IPessoaBCF
{

	/**
	 * Fetch all pessoas.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the inquiry pessoa response
	 */
	InquiryPessoaResponse fetchAllPessoas(InquiryPessoaRequest inquiryPessoaRequest);

	/**
	 * Fetch pessoa by id.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse fetchPessoaById(InquiryPessoaRequest inquiryPessoaRequest);

	/**
	 * Fetch pessoa by name.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse fetchPessoaByName(InquiryPessoaRequest inquiryPessoaRequest);

	/**
	 * Insert pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse insertPessoa(PessoaRequest pessoaRequest);

	/**
	 * Update pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse updatePessoa(PessoaRequest pessoaRequest);

	/**
	 * Delete pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse deletePessoa(PessoaRequest pessoaRequest);

}
