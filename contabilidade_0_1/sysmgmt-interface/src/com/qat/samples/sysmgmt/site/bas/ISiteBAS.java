package com.qat.samples.sysmgmt.site.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;

/**
 * The Interface ISiteBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "SiteService", targetNamespace = "http://qat.com/sysmgmt", portName = "SiteServicePort")
public interface ISiteBAS
{

	/**
	 * Insert site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "insertSite")
	@WebResult(name = "insertSiteReturn")
	@WSDLDocumentation(value = "Insert a site record and optionally returns a list of sites.")
	public SiteResponse insertSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Update site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "updateSite")
	@WebResult(name = "updateSiteReturn")
	@WSDLDocumentation(value = "Updates the selected site record and optionally returns a list of sites.")
	public SiteResponse updateSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Delete site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "deleteSite")
	@WebResult(name = "deleteSiteReturn")
	@WSDLDocumentation(value = "Deletes a site record and optionally returns a list of sites.")
	public SiteResponse deleteSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Fetch all sites.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "fetchAllSites")
	@WebResult(name = "fetchAllSitesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all sites.")
	public SiteResponse fetchAllSites(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch site by id.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "fetchSiteById")
	@WebResult(name = "fetchSiteByIdReturn")
	@WSDLDocumentation(value = "Returns the desired site.")
	public SiteResponse fetchSiteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh sites.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "refreshSites")
	@WebResult(name = "refreshSitesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the site tables.")
	public SiteResponse refreshSites(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch sites by request.
	 * 
	 * @param request the request
	 * @return the site paged response
	 */
	@WebMethod(action = "fetchSitesByRequest")
	@WebResult(name = "fetchSitesByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of sites paged.")
	public SiteResponse fetchSitesByRequest(@WebParam(name = "request") PagedInquiryRequest request);

	// contato

	/**
	 * Insert site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "insertContato")
	@WebResult(name = "insertContatoReturn")
	@WSDLDocumentation(value = "Insert a site record and optionally returns a list of sites.")
	public ContatoResponse insertContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Update site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "updateContato")
	@WebResult(name = "updateContatoReturn")
	@WSDLDocumentation(value = "Updates the selected site record and optionally returns a list of sites.")
	public ContatoResponse updateContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Delete site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "deleteContato")
	@WebResult(name = "deleteContatoReturn")
	@WSDLDocumentation(value = "Deletes a site record and optionally returns a list of sites.")
	public ContatoResponse deleteContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Fetch all sites.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "fetchAllContatos")
	@WebResult(name = "fetchAllContatosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all sites.")
	public ContatoResponse fetchAllContatos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch site by id.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "fetchContatoById")
	@WebResult(name = "fetchContatoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired site.")
	public ContatoResponse fetchContatoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh sites.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "refreshContatos")
	@WebResult(name = "refreshContatosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the site tables.")
	public ContatoResponse refreshContatos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch sites by request.
	 * 
	 * @param request the request
	 * @return the site paged response
	 */
	@WebMethod(action = "fetchContatosByRequest")
	@WebResult(name = "fetchContatosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of sites paged.")
	public ContatoResponse fetchContatosByRequest(@WebParam(name = "request") ContatoInquiryRequest request);

	// os

	/**
	 * Insert site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "insertOrdemServico")
	@WebResult(name = "insertOrdemServicoReturn")
	@WSDLDocumentation(value = "Insert a site record and optionally returns a list of sites.")
	public OrdemServicoResponse insertOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Update site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "updateOrdemServico")
	@WebResult(name = "updateOrdemServicoReturn")
	@WSDLDocumentation(value = "Updates the selected site record and optionally returns a list of sites.")
	public OrdemServicoResponse updateOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Delete site.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "deleteOrdemServico")
	@WebResult(name = "deleteOrdemServicoReturn")
	@WSDLDocumentation(value = "Deletes a site record and optionally returns a list of sites.")
	public OrdemServicoResponse deleteOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Fetch all sites.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "fetchAllOrdemServicos")
	@WebResult(name = "fetchAllOrdemServicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all sites.")
	public OrdemServicoResponse fetchAllOrdemServicos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch site by id.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "fetchOrdemServicoById")
	@WebResult(name = "fetchOrdemServicoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired site.")
	public OrdemServicoResponse fetchOrdemServicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh sites.
	 * 
	 * @param request the request
	 * 
	 * @return the site response
	 */
	@WebMethod(action = "refreshOrdemServicos")
	@WebResult(name = "refreshOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the site tables.")
	public OrdemServicoResponse refreshOrdemServicos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch sites by request.
	 * 
	 * @param request the request
	 * @return the site paged response
	 */
	@WebMethod(action = "fetchOrdemServicosByRequest")
	@WebResult(name = "fetchOrdemServicosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of sites paged.")
	public OrdemServicoResponse fetchOrdemServicosByRequest(
			@WebParam(name = "request") OrdemServicoInquiryRequest request);

}
