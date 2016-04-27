/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.SiteResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ISiteWS.
 */
@WebService(serviceName = "SiteService", targetNamespace = "http://qat.com/sysmgmt", portName = "SiteServicePort")
public interface ISiteWS
{

//===================================### SITE ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertSite")
	@WebResult(name = "insertSiteReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public SiteResponse insertSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateSite")
	@WebResult(name = "updateSiteReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public SiteResponse updateSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteSite")
	@WebResult(name = "deleteSiteReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public SiteResponse deleteSite(@WebParam(name = "request") SiteMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshSites")
	@WebResult(name = "refreshSitesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public SiteResponse refreshSites(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllSites")
	@WebResult(name = "fetchAllSitesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public SiteResponse fetchAllSites(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchSiteById")
	@WebResult(name = "fetchSiteByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public SiteResponse fetchSiteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchSitesByRequest")
	@WebResult(name = "fetchSitesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public SiteResponse fetchSitesByRequest(@WebParam(name = "request") SiteInquiryRequest request);


//===================================### CONTATO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertContato")
	@WebResult(name = "insertContatoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ContatoResponse insertContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateContato")
	@WebResult(name = "updateContatoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ContatoResponse updateContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteContato")
	@WebResult(name = "deleteContatoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ContatoResponse deleteContato(@WebParam(name = "request") ContatoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshContatos")
	@WebResult(name = "refreshContatosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ContatoResponse refreshContatos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllContatos")
	@WebResult(name = "fetchAllContatosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ContatoResponse fetchAllContatos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContatoById")
	@WebResult(name = "fetchContatoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ContatoResponse fetchContatoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContatosByRequest")
	@WebResult(name = "fetchContatosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ContatoResponse fetchContatosByRequest(@WebParam(name = "request") ContatoInquiryRequest request);


//===================================### ORDEMSERVICO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertOrdemServico")
	@WebResult(name = "insertOrdemServicoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public OrdemServicoResponse insertOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateOrdemServico")
	@WebResult(name = "updateOrdemServicoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public OrdemServicoResponse updateOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteOrdemServico")
	@WebResult(name = "deleteOrdemServicoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public OrdemServicoResponse deleteOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshOrdemServicos")
	@WebResult(name = "refreshOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public OrdemServicoResponse refreshOrdemServicos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllOrdemServicos")
	@WebResult(name = "fetchAllOrdemServicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public OrdemServicoResponse fetchAllOrdemServicos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchOrdemServicoById")
	@WebResult(name = "fetchOrdemServicoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public OrdemServicoResponse fetchOrdemServicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchOrdemServicosByRequest")
	@WebResult(name = "fetchOrdemServicosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public OrdemServicoResponse fetchOrdemServicosByRequest(@WebParam(name = "request") OrdemServicoInquiryRequest request);


//===================================### PLANO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertPlano")
	@WebResult(name = "insertPlanoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public PlanoResponse insertPlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updatePlano")
	@WebResult(name = "updatePlanoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public PlanoResponse updatePlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deletePlano")
	@WebResult(name = "deletePlanoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public PlanoResponse deletePlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshPlanos")
	@WebResult(name = "refreshPlanosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public PlanoResponse refreshPlanos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllPlanos")
	@WebResult(name = "fetchAllPlanosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public PlanoResponse fetchAllPlanos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchPlanoById")
	@WebResult(name = "fetchPlanoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public PlanoResponse fetchPlanoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchPlanosByRequest")
	@WebResult(name = "fetchPlanosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public PlanoResponse fetchPlanosByRequest(@WebParam(name = "request") PlanoInquiryRequest request);

}

