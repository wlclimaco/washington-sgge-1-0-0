package com.qat.samples.sysmgmt.endereco.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.endereco.model.Endereco;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IEnderecoDAC. (Data Access Component - DAC)
 */
public interface IEnderecoDAC
{

	/**
	 * Insert endereco.
	 * 
	 * @param endereco the endereco
	 * @return the internal response
	 */
	public InternalResponse insertEndereco(Endereco endereco);

	/**
	 * Update endereco.
	 * 
	 * @param endereco the endereco
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateEndereco(Endereco endereco);

	/**
	 * Delete endereco.
	 * 
	 * @param endereco the endereco
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteEndereco(Endereco endereco);

	/**
	 * Delete all enderecos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllEnderecos();

	/**
	 * Fetch all enderecos.
	 * 
	 * @return the list< endereco>
	 */
	public List<Endereco> fetchAllEnderecos();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Endereco fetchEnderecoById(FetchByIdRequest request);

	/**
	 * Fetch enderecos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Endereco> fetchEnderecosByRequest(PagedInquiryRequest request);

}
