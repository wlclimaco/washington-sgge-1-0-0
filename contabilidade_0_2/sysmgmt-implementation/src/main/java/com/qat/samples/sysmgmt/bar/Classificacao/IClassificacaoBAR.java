package com.qat.samples.sysmgmt.bar.Classificacao;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface ClassificacaoBAR.. (Data Access Component - DAC)
 */
public interface IClassificacaoBAR
{

	/**
	 * Fetch classificacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Classificacao fetchClassificacaoById(FetchByIdRequest request);

	/**
* Insert classificacao.
*
* @param classificacao the classificacao
*
* @return the internal response
*/
	public InternalResponse insertClassificacao(Classificacao classificacao);

	/**
* Update classificacao.
*
* @param classificacao the classificacao
*
* @return the internal response
*/
	public InternalResponse updateClassificacao(Classificacao classificacao);

	/**
* Delete classificacao.
*
* @param classificacao the classificacao
*
* @return the internal response
*/
	public InternalResponse deleteClassificacaoById(Classificacao classificacao);

	/**
* Delete all classificacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllClassificacaos();

	/**
* Fetch all classificacaos.
*
* @return the list< classificacao>
*/
	public InternalResultsResponse<Classificacao> fetchAllClassificacaos(Classificacao  classificacao);

	/**
* Fetch classificacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Classificacao> fetchClassificacaosByRequest(ClassificacaoInquiryRequest request);

}
