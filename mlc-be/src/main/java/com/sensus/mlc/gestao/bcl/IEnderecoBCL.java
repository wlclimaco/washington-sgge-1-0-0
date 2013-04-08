package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.endereco.model.request.EnderecoRequest;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IEnderecoBCL
{

	/**
	 * Insert endereco.
	 *
	 * @param enderecoRequest the endereco request
	 * @return the endereco response
	 */
	public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest);

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
	 * Fetch all endereco types.
	 *
	 * @param request the request
	 * @return the endereco response
	 */
	public InternalResultsResponse<Endereco> fetchAllEnderecoTypes(InquiryEnderecoRequest inquiryenderecoRequest);

	/**
	 * Fetch all endereco filial.
	 *
	 * @param request the request
	 * @return the endereco response
	 */
	public InternalResultsResponse<Endereco> fetchAllEnderecoFilial(EnderecoRequest enderecoRequest);

}
