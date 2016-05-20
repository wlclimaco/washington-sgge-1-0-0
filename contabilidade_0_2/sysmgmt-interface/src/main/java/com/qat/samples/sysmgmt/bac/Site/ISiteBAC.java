/** create by system gera-java version 1.0.0 19/05/2016 21:6 : 51*/
package com.qat.samples.sysmgmt.bac.Site;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
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



//===================================### SERVICO ####======================================
	/**

	/**
	 * Insert servico.
	 *
* @param request the servico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Servico> insertServico(ServicoMaintenanceRequest request);

	/**
* Update servico.
*
* @param request the servico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Servico> updateServico(ServicoMaintenanceRequest request);

	/**
* Delete servico.
*
* @param request the servico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Servico> deleteServico(ServicoMaintenanceRequest request);

	/**
* Refresh servicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Servico> refreshServicos(RefreshRequest request);

	/**
* Fetch servico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Servico> fetchServicoById(FetchByIdRequest request);

	/**
* Fetch all servicos.
*
* @return the internal results response< servico>
*/
	public InternalResultsResponse<Servico> fetchAllServicos(Servico  servico);

	/**
* Fetch servicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Servico> fetchServicosByRequest(ServicoInquiryRequest request);

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



//===================================### OrdemServico ####======================================
	/**

	/**
	 * Insert OrdemServico.
	 *
* @param request the OrdemServico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Update OrdemServico.
*
* @param request the OrdemServico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Delete OrdemServico.
*
* @param request the OrdemServico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> deleteOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Refresh OrdemServicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<OrdemServico> refreshOrdemServicos(RefreshRequest request);

	/**
* Fetch OrdemServico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	/**
* Fetch all OrdemServicos.
*
* @return the internal results response< OrdemServico>
*/
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico  OrdemServico);

	/**
* Fetch OrdemServicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);



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
