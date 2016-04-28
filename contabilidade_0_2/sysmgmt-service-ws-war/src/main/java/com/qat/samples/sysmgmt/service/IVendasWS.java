/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalSaidaResponse;
import com.qat.samples.sysmgmt.nf.model.response.OrcamentoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IVendasWS.
 */
@WebService(serviceName = "VendasService", targetNamespace = "http://qat.com/sysmgmt", portName = "VendasServicePort")
public interface IVendasWS
{

//===================================### NOTAFISCALSAIDA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertNotaFiscalSaida")
	@WebResult(name = "insertNotaFiscalSaidaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public NotaFiscalSaidaResponse insertNotaFiscalSaida(@WebParam(name = "request") NotaFiscalSaidaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateNotaFiscalSaida")
	@WebResult(name = "updateNotaFiscalSaidaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public NotaFiscalSaidaResponse updateNotaFiscalSaida(@WebParam(name = "request") NotaFiscalSaidaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteNotaFiscalSaida")
	@WebResult(name = "deleteNotaFiscalSaidaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public NotaFiscalSaidaResponse deleteNotaFiscalSaida(@WebParam(name = "request") NotaFiscalSaidaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshNotaFiscalSaidas")
	@WebResult(name = "refreshNotaFiscalSaidasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public NotaFiscalSaidaResponse refreshNotaFiscalSaidas(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllNotaFiscalSaidas")
	@WebResult(name = "fetchAllNotaFiscalSaidasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public NotaFiscalSaidaResponse fetchAllNotaFiscalSaidas(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchNotaFiscalSaidaById")
	@WebResult(name = "fetchNotaFiscalSaidaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchNotaFiscalSaidasByRequest")
	@WebResult(name = "fetchNotaFiscalSaidasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidasByRequest(@WebParam(name = "request") NotaFiscalInquiryRequest request);


//===================================### ORCAMENTO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertOrcamento")
	@WebResult(name = "insertOrcamentoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public OrcamentoResponse insertOrcamento(@WebParam(name = "request") OrcamentoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateOrcamento")
	@WebResult(name = "updateOrcamentoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public OrcamentoResponse updateOrcamento(@WebParam(name = "request") OrcamentoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteOrcamento")
	@WebResult(name = "deleteOrcamentoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public OrcamentoResponse deleteOrcamento(@WebParam(name = "request") OrcamentoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshOrcamentos")
	@WebResult(name = "refreshOrcamentosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public OrcamentoResponse refreshOrcamentos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllOrcamentos")
	@WebResult(name = "fetchAllOrcamentosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public OrcamentoResponse fetchAllOrcamentos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchOrcamentoById")
	@WebResult(name = "fetchOrcamentoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public OrcamentoResponse fetchOrcamentoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchOrcamentosByRequest")
	@WebResult(name = "fetchOrcamentosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public OrcamentoResponse fetchOrcamentosByRequest(@WebParam(name = "request") OrcamentoInquiryRequest request);


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

}

