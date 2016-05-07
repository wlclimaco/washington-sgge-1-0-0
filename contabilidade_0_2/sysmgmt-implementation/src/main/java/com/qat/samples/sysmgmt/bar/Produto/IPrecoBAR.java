/** create by system gera-java version 1.0.0 07/05/2016 18:43 : 6*/
package com.qat.samples.sysmgmt.bar.Produto;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.produto.model.Preco;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface PrecoBAR.. (Data Access Component - DAC)
 */
public interface IPrecoBAR
{

	/**
	 * Fetch preco by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Preco fetchPrecoById(FetchByIdRequest request);

	/**
* Insert preco.
*
* @param preco the preco
*
* @return the internal response
*/
	public InternalResponse insertPreco(Preco preco);

	/**
* Update preco.
*
* @param preco the preco
*
* @return the internal response
*/
	public InternalResponse updatePreco(Preco preco);

	/**
* Delete preco.
*
* @param preco the preco
*
* @return the internal response
*/
	public InternalResponse deletePrecoById(Preco preco);

	/**
* Delete all precos.
*
* @return the internal response
*/
	public InternalResponse deleteAllPrecos();

	/**
* Fetch all precos.
*
* @return the list< preco>
*/
	public InternalResultsResponse<Preco> fetchAllPrecos(Preco  preco);

	/**
* Fetch precos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Preco> fetchPrecosByRequest(PagedInquiryRequest request);

}
