/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ISiteBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "SiteService", targetNamespace = "http://qat.com/jms", portName = "SiteServicePort")
public interface ISiteWS
{

//===================================### SITE ####======================================

/**
	 * Rebuild a list of Sites.
	 *
	 * @param request SiteRequest object containing parameter for building the list of Site objects.
	 *
	 * @return the SiteResponse containing the list of Sites built
	 */
	@WebMethod(action = "insertSites")
	@WebResult(name = "insertSitesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SiteResponse insertSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Rebuild a list of Sites.
	 *
	 * @param request SiteRequest object containing parameter for building the list of Site objects.
	 *
	 * @return the SiteResponse containing the list of Sites built
	 */
	@WebMethod(action = "updateSites")
	@WebResult(name = "updateSitesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SiteResponse updateSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Rebuild a list of Sites.
	 *
	 * @param request SiteRequest object containing parameter for building the list of Site objects.
	 *
	 * @return the SiteResponse containing the list of Sites built
	 */
	@WebMethod(action = "deleteSites")
	@WebResult(name = "deleteSitesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SiteResponse deleteSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Rebuild a list of Sites.
	 *
	 * @param request SiteRequest object containing parameter for building the list of Site objects.
	 *
	 * @return the SiteResponse containing the list of Sites built
	 */
	@WebMethod(action = "fetchSiteById")
	@WebResult(name = "fetchSiteByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SiteResponse fetchSiteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Sites.
	 *
	 * @param request SiteRequest object containing parameter for building the list of Site objects.
	 *
	 * @return the SiteResponse containing the list of Sites built
	 */
	@WebMethod(action = "fetchSitesByRequest")
	@WebResult(name = "fetchSitesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SiteResponse fetchSitesByRequest(@WebParam(name = "request") SiteInquiryRequest request);


	/**
	 * Rebuild a list of Sites.
	 *
	 * @param request SiteRequest object containing parameter for building the list of Site objects.
	 *
	 * @return the SiteResponse containing the list of Sites built
	 */
	@WebMethod(action = "refreshSites")
	@WebResult(name = "refreshSitesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SiteResponse refreshSites(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Sites.
	 *
	 * @param request the request
	 *
	 * @return the SiteResponse containing all Site objects
	 */
	@WebMethod(action = "fetchAllSites")
	@WebResult(name = "fetchAllSitesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	SiteResponse fetchAllSites(@WebParam(name = "request") FetchAllRequest request);



//===================================### CONTATO ####======================================

/**
	 * Rebuild a list of Contatos.
	 *
	 * @param request ContatoRequest object containing parameter for building the list of Contato objects.
	 *
	 * @return the ContatoResponse containing the list of Contatos built
	 */
	@WebMethod(action = "insertContatos")
	@WebResult(name = "insertContatosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContatoResponse insertContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Rebuild a list of Contatos.
	 *
	 * @param request ContatoRequest object containing parameter for building the list of Contato objects.
	 *
	 * @return the ContatoResponse containing the list of Contatos built
	 */
	@WebMethod(action = "updateContatos")
	@WebResult(name = "updateContatosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContatoResponse updateContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Rebuild a list of Contatos.
	 *
	 * @param request ContatoRequest object containing parameter for building the list of Contato objects.
	 *
	 * @return the ContatoResponse containing the list of Contatos built
	 */
	@WebMethod(action = "deleteContatos")
	@WebResult(name = "deleteContatosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContatoResponse deleteContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Rebuild a list of Contatos.
	 *
	 * @param request ContatoRequest object containing parameter for building the list of Contato objects.
	 *
	 * @return the ContatoResponse containing the list of Contatos built
	 */
	@WebMethod(action = "fetchContatoById")
	@WebResult(name = "fetchContatoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContatoResponse fetchContatoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Contatos.
	 *
	 * @param request ContatoRequest object containing parameter for building the list of Contato objects.
	 *
	 * @return the ContatoResponse containing the list of Contatos built
	 */
	@WebMethod(action = "fetchContatosByRequest")
	@WebResult(name = "fetchContatosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContatoResponse fetchContatosByRequest(@WebParam(name = "request") ContatoInquiryRequest request);


	/**
	 * Rebuild a list of Contatos.
	 *
	 * @param request ContatoRequest object containing parameter for building the list of Contato objects.
	 *
	 * @return the ContatoResponse containing the list of Contatos built
	 */
	@WebMethod(action = "refreshContatos")
	@WebResult(name = "refreshContatosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContatoResponse refreshContatos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Contatos.
	 *
	 * @param request the request
	 *
	 * @return the ContatoResponse containing all Contato objects
	 */
	@WebMethod(action = "fetchAllContatos")
	@WebResult(name = "fetchAllContatosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ContatoResponse fetchAllContatos(@WebParam(name = "request") FetchAllRequest request);



//===================================### ORDEMSERVICO ####======================================

/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "insertOrdemServicos")
	@WebResult(name = "insertOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse insertOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "updateOrdemServicos")
	@WebResult(name = "updateOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse updateOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "deleteOrdemServicos")
	@WebResult(name = "deleteOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse deleteOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "fetchOrdemServicoById")
	@WebResult(name = "fetchOrdemServicoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse fetchOrdemServicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "fetchOrdemServicosByRequest")
	@WebResult(name = "fetchOrdemServicosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse fetchOrdemServicosByRequest(@WebParam(name = "request") OrdemServicoInquiryRequest request);


	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "refreshOrdemServicos")
	@WebResult(name = "refreshOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse refreshOrdemServicos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all OrdemServicos.
	 *
	 * @param request the request
	 *
	 * @return the OrdemServicoResponse containing all OrdemServico objects
	 */
	@WebMethod(action = "fetchAllOrdemServicos")
	@WebResult(name = "fetchAllOrdemServicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	OrdemServicoResponse fetchAllOrdemServicos(@WebParam(name = "request") FetchAllRequest request);



//===================================### PLANO ####======================================

/**
	 * Rebuild a list of Planos.
	 *
	 * @param request PlanoRequest object containing parameter for building the list of Plano objects.
	 *
	 * @return the PlanoResponse containing the list of Planos built
	 */
	@WebMethod(action = "insertPlanos")
	@WebResult(name = "insertPlanosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PlanoResponse insertPlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	/**
	 * Rebuild a list of Planos.
	 *
	 * @param request PlanoRequest object containing parameter for building the list of Plano objects.
	 *
	 * @return the PlanoResponse containing the list of Planos built
	 */
	@WebMethod(action = "updatePlanos")
	@WebResult(name = "updatePlanosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PlanoResponse updatePlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	/**
	 * Rebuild a list of Planos.
	 *
	 * @param request PlanoRequest object containing parameter for building the list of Plano objects.
	 *
	 * @return the PlanoResponse containing the list of Planos built
	 */
	@WebMethod(action = "deletePlanos")
	@WebResult(name = "deletePlanosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PlanoResponse deletePlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	/**
	 * Rebuild a list of Planos.
	 *
	 * @param request PlanoRequest object containing parameter for building the list of Plano objects.
	 *
	 * @return the PlanoResponse containing the list of Planos built
	 */
	@WebMethod(action = "fetchPlanoById")
	@WebResult(name = "fetchPlanoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PlanoResponse fetchPlanoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Planos.
	 *
	 * @param request PlanoRequest object containing parameter for building the list of Plano objects.
	 *
	 * @return the PlanoResponse containing the list of Planos built
	 */
	@WebMethod(action = "fetchPlanosByRequest")
	@WebResult(name = "fetchPlanosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PlanoResponse fetchPlanosByRequest(@WebParam(name = "request") PlanoInquiryRequest request);


	/**
	 * Rebuild a list of Planos.
	 *
	 * @param request PlanoRequest object containing parameter for building the list of Plano objects.
	 *
	 * @return the PlanoResponse containing the list of Planos built
	 */
	@WebMethod(action = "refreshPlanos")
	@WebResult(name = "refreshPlanosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PlanoResponse refreshPlanos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Planos.
	 *
	 * @param request the request
	 *
	 * @return the PlanoResponse containing all Plano objects
	 */
	@WebMethod(action = "fetchAllPlanos")
	@WebResult(name = "fetchAllPlanosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	PlanoResponse fetchAllPlanos(@WebParam(name = "request") FetchAllRequest request);


}
