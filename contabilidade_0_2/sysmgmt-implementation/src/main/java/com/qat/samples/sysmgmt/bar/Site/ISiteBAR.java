package com.qat.samples.sysmgmt.bar.Site;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface SiteBAR.. (Data Access Component - DAC)
 */
public interface ISiteBAR
{

	/**
	 * Fetch site by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request);

	/**
* Insert site.
*
* @param site the site
*
* @return the internal response
*/
	public InternalResponse insertSite(Site site);

	/**
* Update site.
*
* @param site the site
*
* @return the internal response
*/
	public InternalResponse updateSite(Site site);

	/**
* Delete site.
*
* @param site the site
*
* @return the internal response
*/
	public InternalResponse deleteSiteById(Site site);

	/**
* Delete all sites.
*
* @return the internal response
*/
	public InternalResponse deleteAllSites();

	/**
* Fetch all sites.
*
* @return the list< site>
*/
	public InternalResultsResponse<Site> fetchAllSites(Site  site);

	/**
* Fetch sites by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Site> fetchSitesByRequest(SiteInquiryRequest request);

	/**
	 * Fetch contato by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request);

	/**
* Insert contato.
*
* @param contato the contato
*
* @return the internal response
*/
	public InternalResponse insertContato(Contato contato);

	/**
* Update contato.
*
* @param contato the contato
*
* @return the internal response
*/
	public InternalResponse updateContato(Contato contato);

	/**
* Delete contato.
*
* @param contato the contato
*
* @return the internal response
*/
	public InternalResponse deleteContatoById(Contato contato);

	/**
* Delete all contatos.
*
* @return the internal response
*/
	public InternalResponse deleteAllContatos();

	/**
* Fetch all contatos.
*
* @return the list< contato>
*/
	public InternalResultsResponse<Contato> fetchAllContatos(Contato  contato);

	/**
* Fetch contatos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request);

	/**
	 * Fetch ordemservico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	/**
* Insert ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse insertOrdemServico(OrdemServico ordemservico);

	/**
* Update ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse updateOrdemServico(OrdemServico ordemservico);

	/**
* Delete ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse deleteOrdemServicoById(OrdemServico ordemservico);

	/**
* Delete all ordemservicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllOrdemServicos();

	/**
* Fetch all ordemservicos.
*
* @return the list< ordemservico>
*/
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico  ordemservico);

	/**
* Fetch ordemservicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);

	/**
	 * Fetch plano by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Plano> fetchPlanoById(FetchByIdRequest request);

	/**
* Insert plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse insertPlano(Plano plano);

	/**
* Update plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse updatePlano(Plano plano);

	/**
* Delete plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse deletePlanoById(Plano plano);

	/**
* Delete all planos.
*
* @return the internal response
*/
	public InternalResponse deleteAllPlanos();

	/**
* Fetch all planos.
*
* @return the list< plano>
*/
	public InternalResultsResponse<Plano> fetchAllPlanos(Plano  plano);

	/**
* Fetch planos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Plano> fetchPlanosByRequest(PlanoInquiryRequest request);

}
