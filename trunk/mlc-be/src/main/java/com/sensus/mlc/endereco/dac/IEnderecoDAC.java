package com.sensus.mlc.endereco.dac;

import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.endereco.model.request.EnderecoRequest;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.endereco.model.response.EnderecoResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;

/**
 * The Interface IActionDAC.
 *
 * @author - QAT Brazil.
 *
 */
public interface IEnderecoDAC
{

	/**
	 * Update endereco.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the endereco response
	 */
	public InternalResultsResponse<Endereco> updateEndereco(EnderecoRequest enderecoRequest);

	/**
	 * Delete endereco.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the endereco response
	 */
	public InternalResponse deleteEndereco(EnderecoRequest enderecoRequest);

	/**
	 * Fetch all endereco.
	 *
	 * @param inquiryenderecoRequest the inquiryendereco request
	 * @return the inquiry endereco response
	 */
	public InternalResultsResponse<Endereco> fetchAllEndereco(InquiryEnderecoRequest inquiryenderecoRequest);

	/**
	 * Fetch endereco by id.
	 *
	 * @param inquiryenderecoRequest the inquiryendereco request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Endereco> fetchEnderecoById(EnderecoRequest enderecoRequest);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryEnderecoRequest the inquiry endereco request
	 * @return the inquiry endereco response
	 */
	public InternalResponse generateFileCSV(InquiryEnderecoRequest inquiryEnderecoRequest);

	/**
	 * Fetch all endereco types.
	 *
	 * @param request the request
	 * @return the endereco response
	 */
	public EnderecoResponse fetchAllEnderecoTypes(InquiryEnderecoRequest request);

	/**
	 * Fetch all endereco filial.
	 *
	 * @param request the request
	 * @return the endereco response
	 */
	public EnderecoResponse fetchAllEnderecoFilial(InquiryEnderecoRequest request);

	public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest);
}