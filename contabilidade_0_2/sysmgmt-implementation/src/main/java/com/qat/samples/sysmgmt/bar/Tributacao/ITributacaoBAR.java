package com.qat.samples.sysmgmt.bar.Tributacao;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface TributacaoBAR.. (Data Access Component - DAC)
 */
public interface ITributacaoBAR
{

	/**
	 * Fetch tributacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Tributacao fetchTributacaoById(FetchByIdRequest request);

	/**
* Insert tributacao.
*
* @param tributacao the tributacao
*
* @return the internal response
*/
	public InternalResponse insertTributacao(Tributacao tributacao);

	/**
* Update tributacao.
*
* @param tributacao the tributacao
*
* @return the internal response
*/
	public InternalResponse updateTributacao(Tributacao tributacao);

	/**
* Delete tributacao.
*
* @param tributacao the tributacao
*
* @return the internal response
*/
	public InternalResponse deleteTributacaoById(Tributacao tributacao);

	/**
* Delete all tributacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllTributacaos();

	/**
* Fetch all tributacaos.
*
* @return the list< tributacao>
*/
	public InternalResultsResponse<Tributacao> fetchAllTributacaos(Tributacao  tributacao);

	/**
* Fetch tributacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Tributacao> fetchTributacaosByRequest(TributacaoInquiryRequest request);

}
