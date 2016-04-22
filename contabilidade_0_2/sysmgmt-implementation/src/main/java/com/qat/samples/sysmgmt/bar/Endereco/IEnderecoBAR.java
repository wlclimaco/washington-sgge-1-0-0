package com.qat.samples.sysmgmt.bar.Endereco;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface EnderecoBAR.. (Data Access Component - DAC)
 */
public interface IEnderecoBAR
{

	/**
	 * Fetch endereco by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Endereco fetchEnderecoById(FetchByIdRequest request);

	/**
* Insert endereco.
*
* @param endereco the endereco
*
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
	public InternalResponse deleteEnderecoById(Endereco endereco);

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
	public InternalResultsResponse<Endereco> fetchAllEnderecos(Endereco  endereco);

	/**
* Fetch enderecos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Endereco> fetchEnderecosByRequest(PagedInquiryRequest request);

}
