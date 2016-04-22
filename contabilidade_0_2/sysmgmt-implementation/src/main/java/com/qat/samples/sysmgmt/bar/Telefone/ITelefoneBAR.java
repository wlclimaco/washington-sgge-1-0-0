package com.qat.samples.sysmgmt.bar.Telefone;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.Telefone;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface TelefoneBAR.. (Data Access Component - DAC)
 */
public interface ITelefoneBAR
{

	/**
	 * Fetch telefone by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Telefone fetchTelefoneById(FetchByIdRequest request);

	/**
* Insert telefone.
*
* @param telefone the telefone
*
* @return the internal response
*/
	public InternalResponse insertTelefone(Telefone telefone);

	/**
* Update telefone.
*
* @param telefone the telefone
*
* @return the internal response
*/
	public InternalResponse updateTelefone(Telefone telefone);

	/**
* Delete telefone.
*
* @param telefone the telefone
*
* @return the internal response
*/
	public InternalResponse deleteTelefoneById(Telefone telefone);

	/**
* Delete all telefones.
*
* @return the internal response
*/
	public InternalResponse deleteAllTelefones();

	/**
* Fetch all telefones.
*
* @return the list< telefone>
*/
	public InternalResultsResponse<Telefone> fetchAllTelefones(Telefone  telefone);

	/**
* Fetch telefones by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Telefone> fetchTelefonesByRequest(PagedInquiryRequest request);

}
