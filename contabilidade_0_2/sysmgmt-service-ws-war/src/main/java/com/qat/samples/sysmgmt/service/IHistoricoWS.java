/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.HistoricoMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IHistoricoWS.
 */
@WebService(serviceName = "HistoricoService", targetNamespace = "http://qat.com/sysmgmt", portName = "HistoricoServicePort")
public interface IHistoricoWS
{

//===================================### HISTORICO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertHistorico")
	@WebResult(name = "insertHistoricoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public HistoricoResponse insertHistorico(@WebParam(name = "request") HistoricoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateHistorico")
	@WebResult(name = "updateHistoricoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public HistoricoResponse updateHistorico(@WebParam(name = "request") HistoricoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteHistorico")
	@WebResult(name = "deleteHistoricoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public HistoricoResponse deleteHistorico(@WebParam(name = "request") HistoricoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshHistoricos")
	@WebResult(name = "refreshHistoricosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public HistoricoResponse refreshHistoricos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllHistoricos")
	@WebResult(name = "fetchAllHistoricosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public HistoricoResponse fetchAllHistoricos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchHistoricoById")
	@WebResult(name = "fetchHistoricoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public HistoricoResponse fetchHistoricoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchHistoricosByRequest")
	@WebResult(name = "fetchHistoricosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public HistoricoResponse fetchHistoricosByRequest(@WebParam(name = "request") HistoricoInquiryRequest request);


//===================================### HISTORICOITENS ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertHistoricoItens")
	@WebResult(name = "insertHistoricoItensReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public HistoricoItensResponse insertHistoricoItens(@WebParam(name = "request") HistoricoItensMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateHistoricoItens")
	@WebResult(name = "updateHistoricoItensReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public HistoricoItensResponse updateHistoricoItens(@WebParam(name = "request") HistoricoItensMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteHistoricoItens")
	@WebResult(name = "deleteHistoricoItensReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public HistoricoItensResponse deleteHistoricoItens(@WebParam(name = "request") HistoricoItensMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshHistoricoItenss")
	@WebResult(name = "refreshHistoricoItenssReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public HistoricoItensResponse refreshHistoricoItenss(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllHistoricoItenss")
	@WebResult(name = "fetchAllHistoricoItenssReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public HistoricoItensResponse fetchAllHistoricoItenss(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchHistoricoItensById")
	@WebResult(name = "fetchHistoricoItensByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public HistoricoItensResponse fetchHistoricoItensById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchHistoricoItenssByRequest")
	@WebResult(name = "fetchHistoricoItenssByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public HistoricoItensResponse fetchHistoricoItenssByRequest(@WebParam(name = "request") HistoricoItensInquiryRequest request);

}

