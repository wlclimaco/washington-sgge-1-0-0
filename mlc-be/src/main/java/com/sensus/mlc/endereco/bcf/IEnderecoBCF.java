package com.sensus.mlc.endereco.bcf;


import com.sensus.mlc.endereco.model.request.EnderecoRequest;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.endereco.model.response.EnderecoResponse;
import com.sensus.mlc.endereco.model.response.InquiryEnderecoResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/**
 * The Interface IEnderecoBCF.
 *
 * @author QAT Brazil.
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
	 * @param inquiryenderecoRequest the inquiryendereco request
	 * @return the inquiry endereco response
	 */
	public InquiryEnderecoResponse fetchAllEndereco(InquiryEnderecoRequest inquiryenderecoRequest);

	/**
	 * Fetch endereco by id.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the endereco response
	 */
	public EnderecoResponse fetchEnderecoById(EnderecoRequest enderecoRequest);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryActionRequest the inquiry action request
	 * @return the internal response
	 */
	public ProcessResponse generateFileCSV(InquiryEnderecoRequest inquiryEnderecoRequest);

	/**
	 * Insert csv process.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the process response
	 */
	public ProcessResponse insertCSVProcess(InquiryEnderecoRequest enderecoRequest);

}
