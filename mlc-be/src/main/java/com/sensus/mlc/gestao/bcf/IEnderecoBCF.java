package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.endereco.model.request.EnderecoRequest;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.endereco.model.response.EnderecoResponse;
import com.sensus.mlc.endereco.model.response.InquiryEnderecoResponse;


/**
 * The Interface IEnderecoBCF.
 *
 * @author Washington.Costa
 */
public interface IEnderecoBCF
{

	/**
	 * Insert endereco.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the endereco response
	 */
	public EnderecoResponse insertEndereco(EnderecoRequest enderecoRequest);

	/**
	 * Update endereco.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the endereco response
	 */
	public EnderecoResponse updateEndereco(EnderecoRequest enderecoRequest);

	/**
	 * Delete endereco.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the endereco response
	 */
	public EnderecoResponse deleteEndereco(EnderecoRequest enderecoRequest);

	/**
	 * Fetch all endereco.
	 *
	 * @param inquiryEnderecoRequest the inquiryEndereco request
	 * @return the inquiry endereco response
	 */
	public InquiryEnderecoResponse fetchAllEndereco(InquiryEnderecoRequest inquiryEnderecoRequest);

	/**
	 * Fetch endereco by id.
	 *
	 * @param EnderecoRequest the endereco request
	 * @return the endereco response
	 */
	public EnderecoResponse fetchEnderecoById(EnderecoRequest enderecoRequest);

}
