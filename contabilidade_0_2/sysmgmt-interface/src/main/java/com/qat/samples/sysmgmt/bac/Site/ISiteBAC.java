package com.qat.samples.sysmgmt.bac.Site;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ISiteBAC. (Business Area Component - BAC)
 */
public interface ISiteBAC
{



//===================================### SITE ####======================================
	/**

	/**
	 * Insert site.
	 *
* @param request the site maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Site> insertSite(SiteMaintenanceRequest request);

	/**
* Update site.
*
* @param request the site maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Site> updateSite(SiteMaintenanceRequest request);

	/**
* Delete site.
*
* @param request the site maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Site> deleteSite(SiteMaintenanceRequest request);

	/**
* Refresh sites.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Site> refreshSites(RefreshRequest request);

	/**
* Fetch site by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request);

	/**
* Fetch all sites.
*
* @return the internal results response< site>
*/
	public InternalResultsResponse<Site> fetchAllSites(Site  site);

	/**
* Fetch sites by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Site> fetchSitesByRequest(SiteInquiryRequest request);


//===================================### CONTATO ####======================================
	/**

	/**
	 * Insert contato.
	 *
* @param request the contato maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Contato> insertContato(ContatoMaintenanceRequest request);

	/**
* Update contato.
*
* @param request the contato maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Contato> updateContato(ContatoMaintenanceRequest request);

	/**
* Delete contato.
*
* @param request the contato maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Contato> deleteContato(ContatoMaintenanceRequest request);

	/**
* Refresh contatos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Contato> refreshContatos(RefreshRequest request);

	/**
* Fetch contato by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request);

	/**
* Fetch all contatos.
*
* @return the internal results response< contato>
*/
	public InternalResultsResponse<Contato> fetchAllContatos(Contato  contato);

	/**
* Fetch contatos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request);


//===================================### ORDEMSERVICO ####======================================
	/**

	/**
	 * Insert ordemservico.
	 *
* @param request the ordemservico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Update ordemservico.
*
* @param request the ordemservico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Delete ordemservico.
*
* @param request the ordemservico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> deleteOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Refresh ordemservicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<OrdemServico> refreshOrdemServicos(RefreshRequest request);

	/**
* Fetch ordemservico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	/**
* Fetch all ordemservicos.
*
* @return the internal results response< ordemservico>
*/
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico  ordemservico);

	/**
* Fetch ordemservicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);


//===================================### PLANO ####======================================
	/**

	/**
	 * Insert plano.
	 *
* @param request the plano maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Plano> insertPlano(PlanoMaintenanceRequest request);

	/**
* Update plano.
*
* @param request the plano maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Plano> updatePlano(PlanoMaintenanceRequest request);

	/**
* Delete plano.
*
* @param request the plano maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Plano> deletePlano(PlanoMaintenanceRequest request);

	/**
* Refresh planos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Plano> refreshPlanos(RefreshRequest request);

	/**
* Fetch plano by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Plano> fetchPlanoById(FetchByIdRequest request);

	/**
* Fetch all planos.
*
* @return the internal results response< plano>
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
