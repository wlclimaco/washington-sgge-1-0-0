package com.prosperitasglobal.sendsolv.dac;


/**
 * The Interface IEnderecoDAC.
 */
public interface IFinanceiroDAC
{

	/**
	 * Update endereco.
	 *
	 * @param endereco the endereco
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Endereco> updateEndereco(Endereco endereco);

	/**
	 * Insert endereco.
	 *
	 * @param endereco the endereco
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Endereco> insertEndereco(Endereco endereco);

	/**
	 * Delete endereco.
	 *
	 * @param endereco the endereco
	 * @return the internal response
	 */
	public InternalResponse deleteEndereco(Endereco endereco);

	/**
	 * Fetch endereco by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Endereco> fetchEnderecoById(FetchByIdRequest request);

	/**
	 * Fetch all enderecos.
	 *
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Endereco> fetchAllEnderecos();

	/**
	 * Fetch endereco by request.
	 *
	 * @param request the request
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Endereco> fetchEnderecoByRequest(EnderecoInquiryRequest request);

}
