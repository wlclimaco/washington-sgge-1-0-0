/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IFinanceiroWS.
 */
@WebService(serviceName = "FinanceiroService", targetNamespace = "http://qat.com/sysmgmt", portName = "FinanceiroServicePort")
public interface IFinanceiroWS
{

//===================================### CONTASPAGAR ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertContasPagar")
	@WebResult(name = "insertContasPagarReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ContasPagarResponse insertContasPagar(@WebParam(name = "request") ContasPagarMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateContasPagar")
	@WebResult(name = "updateContasPagarReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ContasPagarResponse updateContasPagar(@WebParam(name = "request") ContasPagarMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteContasPagar")
	@WebResult(name = "deleteContasPagarReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ContasPagarResponse deleteContasPagar(@WebParam(name = "request") ContasPagarMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshContasPagars")
	@WebResult(name = "refreshContasPagarsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ContasPagarResponse refreshContasPagars(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllContasPagars")
	@WebResult(name = "fetchAllContasPagarsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ContasPagarResponse fetchAllContasPagars(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContasPagarById")
	@WebResult(name = "fetchContasPagarByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ContasPagarResponse fetchContasPagarById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContasPagarsByRequest")
	@WebResult(name = "fetchContasPagarsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ContasPagarResponse fetchContasPagarsByRequest(@WebParam(name = "request") ContasPagarInquiryRequest request);


//===================================### CONTASRECEBER ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertContasReceber")
	@WebResult(name = "insertContasReceberReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ContasReceberResponse insertContasReceber(@WebParam(name = "request") ContasReceberMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateContasReceber")
	@WebResult(name = "updateContasReceberReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ContasReceberResponse updateContasReceber(@WebParam(name = "request") ContasReceberMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteContasReceber")
	@WebResult(name = "deleteContasReceberReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ContasReceberResponse deleteContasReceber(@WebParam(name = "request") ContasReceberMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshContasRecebers")
	@WebResult(name = "refreshContasRecebersReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ContasReceberResponse refreshContasRecebers(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllContasRecebers")
	@WebResult(name = "fetchAllContasRecebersReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ContasReceberResponse fetchAllContasRecebers(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContasReceberById")
	@WebResult(name = "fetchContasReceberByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ContasReceberResponse fetchContasReceberById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContasRecebersByRequest")
	@WebResult(name = "fetchContasRecebersByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ContasReceberResponse fetchContasRecebersByRequest(@WebParam(name = "request") ContasReceberInquiryRequest request);


//===================================### CONDPAG ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertCondPag")
	@WebResult(name = "insertCondPagReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public CondPagResponse insertCondPag(@WebParam(name = "request") CondPagMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateCondPag")
	@WebResult(name = "updateCondPagReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public CondPagResponse updateCondPag(@WebParam(name = "request") CondPagMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteCondPag")
	@WebResult(name = "deleteCondPagReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public CondPagResponse deleteCondPag(@WebParam(name = "request") CondPagMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshCondPags")
	@WebResult(name = "refreshCondPagsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public CondPagResponse refreshCondPags(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllCondPags")
	@WebResult(name = "fetchAllCondPagsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public CondPagResponse fetchAllCondPags(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCondPagById")
	@WebResult(name = "fetchCondPagByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public CondPagResponse fetchCondPagById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCondPagsByRequest")
	@WebResult(name = "fetchCondPagsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public CondPagResponse fetchCondPagsByRequest(@WebParam(name = "request") CondPagInquiryRequest request);


//===================================### FORMAPG ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertFormaPg")
	@WebResult(name = "insertFormaPgReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public FormaPgResponse insertFormaPg(@WebParam(name = "request") FormaPgMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateFormaPg")
	@WebResult(name = "updateFormaPgReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public FormaPgResponse updateFormaPg(@WebParam(name = "request") FormaPgMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteFormaPg")
	@WebResult(name = "deleteFormaPgReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public FormaPgResponse deleteFormaPg(@WebParam(name = "request") FormaPgMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshFormaPgs")
	@WebResult(name = "refreshFormaPgsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public FormaPgResponse refreshFormaPgs(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllFormaPgs")
	@WebResult(name = "fetchAllFormaPgsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public FormaPgResponse fetchAllFormaPgs(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFormaPgById")
	@WebResult(name = "fetchFormaPgByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public FormaPgResponse fetchFormaPgById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFormaPgsByRequest")
	@WebResult(name = "fetchFormaPgsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public FormaPgResponse fetchFormaPgsByRequest(@WebParam(name = "request") FormaPgInquiryRequest request);


//===================================### BANCO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertBanco")
	@WebResult(name = "insertBancoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public BancoResponse insertBanco(@WebParam(name = "request") BancoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateBanco")
	@WebResult(name = "updateBancoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public BancoResponse updateBanco(@WebParam(name = "request") BancoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteBanco")
	@WebResult(name = "deleteBancoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public BancoResponse deleteBanco(@WebParam(name = "request") BancoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshBancos")
	@WebResult(name = "refreshBancosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public BancoResponse refreshBancos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllBancos")
	@WebResult(name = "fetchAllBancosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public BancoResponse fetchAllBancos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchBancoById")
	@WebResult(name = "fetchBancoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public BancoResponse fetchBancoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchBancosByRequest")
	@WebResult(name = "fetchBancosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public BancoResponse fetchBancosByRequest(@WebParam(name = "request") BancoInquiryRequest request);


//===================================### CONTACORRENTE ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertContaCorrente")
	@WebResult(name = "insertContaCorrenteReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ContaCorrenteResponse insertContaCorrente(@WebParam(name = "request") ContaCorrenteMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateContaCorrente")
	@WebResult(name = "updateContaCorrenteReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ContaCorrenteResponse updateContaCorrente(@WebParam(name = "request") ContaCorrenteMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteContaCorrente")
	@WebResult(name = "deleteContaCorrenteReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ContaCorrenteResponse deleteContaCorrente(@WebParam(name = "request") ContaCorrenteMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshContaCorrentes")
	@WebResult(name = "refreshContaCorrentesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ContaCorrenteResponse refreshContaCorrentes(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllContaCorrentes")
	@WebResult(name = "fetchAllContaCorrentesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ContaCorrenteResponse fetchAllContaCorrentes(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContaCorrenteById")
	@WebResult(name = "fetchContaCorrenteByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ContaCorrenteResponse fetchContaCorrenteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchContaCorrentesByRequest")
	@WebResult(name = "fetchContaCorrentesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ContaCorrenteResponse fetchContaCorrentesByRequest(@WebParam(name = "request") ContaCorrenteInquiryRequest request);


//===================================### CAIXA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertCaixa")
	@WebResult(name = "insertCaixaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public CaixaResponse insertCaixa(@WebParam(name = "request") CaixaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateCaixa")
	@WebResult(name = "updateCaixaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public CaixaResponse updateCaixa(@WebParam(name = "request") CaixaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteCaixa")
	@WebResult(name = "deleteCaixaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public CaixaResponse deleteCaixa(@WebParam(name = "request") CaixaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshCaixas")
	@WebResult(name = "refreshCaixasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public CaixaResponse refreshCaixas(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllCaixas")
	@WebResult(name = "fetchAllCaixasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public CaixaResponse fetchAllCaixas(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCaixaById")
	@WebResult(name = "fetchCaixaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public CaixaResponse fetchCaixaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCaixasByRequest")
	@WebResult(name = "fetchCaixasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public CaixaResponse fetchCaixasByRequest(@WebParam(name = "request") CaixaInquiryRequest request);

}

