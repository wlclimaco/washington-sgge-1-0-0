/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.banco.model.request.BancoMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.response.BancoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FormaPgResponse;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.CondPagResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContaCorrenteResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContasPagarResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContasReceberResponse;
import com.qat.samples.sysmgmt.nf.model.response.CaixaResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IFinanceiroBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "FinanceiroService", targetNamespace = "http://qat.com/jms", portName = "FinanceiroServicePort")
public interface IFinanceiroWS
{

//===================================### CONTASPAGAR ####======================================

/**
	 * Rebuild a list of ContasPagars.
	 *
	 * @param request ContasPagarRequest object containing parameter for building the list of ContasPagar objects.
	 *
	 * @return the ContasPagarResponse containing the list of ContasPagars built
	 */
	@WebMethod(action = "insertContasPagars")
	@WebResult(name = "insertContasPagarsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasPagarResponse insertContasPagar(@WebParam(name = "request") ContasPagarMaintenanceRequest request);

	/**
	 * Rebuild a list of ContasPagars.
	 *
	 * @param request ContasPagarRequest object containing parameter for building the list of ContasPagar objects.
	 *
	 * @return the ContasPagarResponse containing the list of ContasPagars built
	 */
	@WebMethod(action = "updateContasPagars")
	@WebResult(name = "updateContasPagarsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasPagarResponse updateContasPagar(@WebParam(name = "request") ContasPagarMaintenanceRequest request);

	/**
	 * Rebuild a list of ContasPagars.
	 *
	 * @param request ContasPagarRequest object containing parameter for building the list of ContasPagar objects.
	 *
	 * @return the ContasPagarResponse containing the list of ContasPagars built
	 */
	@WebMethod(action = "deleteContasPagars")
	@WebResult(name = "deleteContasPagarsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasPagarResponse deleteContasPagar(@WebParam(name = "request") ContasPagarMaintenanceRequest request);

	/**
	 * Rebuild a list of ContasPagars.
	 *
	 * @param request ContasPagarRequest object containing parameter for building the list of ContasPagar objects.
	 *
	 * @return the ContasPagarResponse containing the list of ContasPagars built
	 */
	@WebMethod(action = "fetchContasPagarById")
	@WebResult(name = "fetchContasPagarByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasPagarResponse fetchContasPagarById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of ContasPagars.
	 *
	 * @param request ContasPagarRequest object containing parameter for building the list of ContasPagar objects.
	 *
	 * @return the ContasPagarResponse containing the list of ContasPagars built
	 */
	@WebMethod(action = "fetchContasPagarsByRequest")
	@WebResult(name = "fetchContasPagarsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasPagarResponse fetchContasPagarsByRequest(@WebParam(name = "request") ContasPagarInquiryRequest request);


	/**
	 * Rebuild a list of ContasPagars.
	 *
	 * @param request ContasPagarRequest object containing parameter for building the list of ContasPagar objects.
	 *
	 * @return the ContasPagarResponse containing the list of ContasPagars built
	 */
	@WebMethod(action = "refreshContasPagars")
	@WebResult(name = "refreshContasPagarsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasPagarResponse refreshContasPagars(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all ContasPagars.
	 *
	 * @param request the request
	 *
	 * @return the ContasPagarResponse containing all ContasPagar objects
	 */
	@WebMethod(action = "fetchAllContasPagars")
	@WebResult(name = "fetchAllContasPagarsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ContasPagarResponse fetchAllContasPagars(@WebParam(name = "request") FetchAllRequest request);



//===================================### CONTASRECEBER ####======================================

/**
	 * Rebuild a list of ContasRecebers.
	 *
	 * @param request ContasReceberRequest object containing parameter for building the list of ContasReceber objects.
	 *
	 * @return the ContasReceberResponse containing the list of ContasRecebers built
	 */
	@WebMethod(action = "insertContasRecebers")
	@WebResult(name = "insertContasRecebersReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasReceberResponse insertContasReceber(@WebParam(name = "request") ContasReceberMaintenanceRequest request);

	/**
	 * Rebuild a list of ContasRecebers.
	 *
	 * @param request ContasReceberRequest object containing parameter for building the list of ContasReceber objects.
	 *
	 * @return the ContasReceberResponse containing the list of ContasRecebers built
	 */
	@WebMethod(action = "updateContasRecebers")
	@WebResult(name = "updateContasRecebersReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasReceberResponse updateContasReceber(@WebParam(name = "request") ContasReceberMaintenanceRequest request);

	/**
	 * Rebuild a list of ContasRecebers.
	 *
	 * @param request ContasReceberRequest object containing parameter for building the list of ContasReceber objects.
	 *
	 * @return the ContasReceberResponse containing the list of ContasRecebers built
	 */
	@WebMethod(action = "deleteContasRecebers")
	@WebResult(name = "deleteContasRecebersReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasReceberResponse deleteContasReceber(@WebParam(name = "request") ContasReceberMaintenanceRequest request);

	/**
	 * Rebuild a list of ContasRecebers.
	 *
	 * @param request ContasReceberRequest object containing parameter for building the list of ContasReceber objects.
	 *
	 * @return the ContasReceberResponse containing the list of ContasRecebers built
	 */
	@WebMethod(action = "fetchContasReceberById")
	@WebResult(name = "fetchContasReceberByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasReceberResponse fetchContasReceberById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of ContasRecebers.
	 *
	 * @param request ContasReceberRequest object containing parameter for building the list of ContasReceber objects.
	 *
	 * @return the ContasReceberResponse containing the list of ContasRecebers built
	 */
	@WebMethod(action = "fetchContasRecebersByRequest")
	@WebResult(name = "fetchContasRecebersByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasReceberResponse fetchContasRecebersByRequest(@WebParam(name = "request") ContasReceberInquiryRequest request);


	/**
	 * Rebuild a list of ContasRecebers.
	 *
	 * @param request ContasReceberRequest object containing parameter for building the list of ContasReceber objects.
	 *
	 * @return the ContasReceberResponse containing the list of ContasRecebers built
	 */
	@WebMethod(action = "refreshContasRecebers")
	@WebResult(name = "refreshContasRecebersReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContasReceberResponse refreshContasRecebers(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all ContasRecebers.
	 *
	 * @param request the request
	 *
	 * @return the ContasReceberResponse containing all ContasReceber objects
	 */
	@WebMethod(action = "fetchAllContasRecebers")
	@WebResult(name = "fetchAllContasRecebersReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ContasReceberResponse fetchAllContasRecebers(@WebParam(name = "request") FetchAllRequest request);



//===================================### CONDPAG ####======================================

/**
	 * Rebuild a list of CondPags.
	 *
	 * @param request CondPagRequest object containing parameter for building the list of CondPag objects.
	 *
	 * @return the CondPagResponse containing the list of CondPags built
	 */
	@WebMethod(action = "insertCondPags")
	@WebResult(name = "insertCondPagsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CondPagResponse insertCondPag(@WebParam(name = "request") CondPagMaintenanceRequest request);

	/**
	 * Rebuild a list of CondPags.
	 *
	 * @param request CondPagRequest object containing parameter for building the list of CondPag objects.
	 *
	 * @return the CondPagResponse containing the list of CondPags built
	 */
	@WebMethod(action = "updateCondPags")
	@WebResult(name = "updateCondPagsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CondPagResponse updateCondPag(@WebParam(name = "request") CondPagMaintenanceRequest request);

	/**
	 * Rebuild a list of CondPags.
	 *
	 * @param request CondPagRequest object containing parameter for building the list of CondPag objects.
	 *
	 * @return the CondPagResponse containing the list of CondPags built
	 */
	@WebMethod(action = "deleteCondPags")
	@WebResult(name = "deleteCondPagsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CondPagResponse deleteCondPag(@WebParam(name = "request") CondPagMaintenanceRequest request);

	/**
	 * Rebuild a list of CondPags.
	 *
	 * @param request CondPagRequest object containing parameter for building the list of CondPag objects.
	 *
	 * @return the CondPagResponse containing the list of CondPags built
	 */
	@WebMethod(action = "fetchCondPagById")
	@WebResult(name = "fetchCondPagByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CondPagResponse fetchCondPagById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of CondPags.
	 *
	 * @param request CondPagRequest object containing parameter for building the list of CondPag objects.
	 *
	 * @return the CondPagResponse containing the list of CondPags built
	 */
	@WebMethod(action = "fetchCondPagsByRequest")
	@WebResult(name = "fetchCondPagsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CondPagResponse fetchCondPagsByRequest(@WebParam(name = "request") CondPagInquiryRequest request);


	/**
	 * Rebuild a list of CondPags.
	 *
	 * @param request CondPagRequest object containing parameter for building the list of CondPag objects.
	 *
	 * @return the CondPagResponse containing the list of CondPags built
	 */
	@WebMethod(action = "refreshCondPags")
	@WebResult(name = "refreshCondPagsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CondPagResponse refreshCondPags(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all CondPags.
	 *
	 * @param request the request
	 *
	 * @return the CondPagResponse containing all CondPag objects
	 */
	@WebMethod(action = "fetchAllCondPags")
	@WebResult(name = "fetchAllCondPagsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	CondPagResponse fetchAllCondPags(@WebParam(name = "request") FetchAllRequest request);



//===================================### FORMAPG ####======================================

/**
	 * Rebuild a list of FormaPgs.
	 *
	 * @param request FormaPgRequest object containing parameter for building the list of FormaPg objects.
	 *
	 * @return the FormaPgResponse containing the list of FormaPgs built
	 */
	@WebMethod(action = "insertFormaPgs")
	@WebResult(name = "insertFormaPgsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FormaPgResponse insertFormaPg(@WebParam(name = "request") FormaPgMaintenanceRequest request);

	/**
	 * Rebuild a list of FormaPgs.
	 *
	 * @param request FormaPgRequest object containing parameter for building the list of FormaPg objects.
	 *
	 * @return the FormaPgResponse containing the list of FormaPgs built
	 */
	@WebMethod(action = "updateFormaPgs")
	@WebResult(name = "updateFormaPgsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FormaPgResponse updateFormaPg(@WebParam(name = "request") FormaPgMaintenanceRequest request);

	/**
	 * Rebuild a list of FormaPgs.
	 *
	 * @param request FormaPgRequest object containing parameter for building the list of FormaPg objects.
	 *
	 * @return the FormaPgResponse containing the list of FormaPgs built
	 */
	@WebMethod(action = "deleteFormaPgs")
	@WebResult(name = "deleteFormaPgsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FormaPgResponse deleteFormaPg(@WebParam(name = "request") FormaPgMaintenanceRequest request);

	/**
	 * Rebuild a list of FormaPgs.
	 *
	 * @param request FormaPgRequest object containing parameter for building the list of FormaPg objects.
	 *
	 * @return the FormaPgResponse containing the list of FormaPgs built
	 */
	@WebMethod(action = "fetchFormaPgById")
	@WebResult(name = "fetchFormaPgByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FormaPgResponse fetchFormaPgById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of FormaPgs.
	 *
	 * @param request FormaPgRequest object containing parameter for building the list of FormaPg objects.
	 *
	 * @return the FormaPgResponse containing the list of FormaPgs built
	 */
	@WebMethod(action = "fetchFormaPgsByRequest")
	@WebResult(name = "fetchFormaPgsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FormaPgResponse fetchFormaPgsByRequest(@WebParam(name = "request") FormaPgInquiryRequest request);


	/**
	 * Rebuild a list of FormaPgs.
	 *
	 * @param request FormaPgRequest object containing parameter for building the list of FormaPg objects.
	 *
	 * @return the FormaPgResponse containing the list of FormaPgs built
	 */
	@WebMethod(action = "refreshFormaPgs")
	@WebResult(name = "refreshFormaPgsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FormaPgResponse refreshFormaPgs(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all FormaPgs.
	 *
	 * @param request the request
	 *
	 * @return the FormaPgResponse containing all FormaPg objects
	 */
	@WebMethod(action = "fetchAllFormaPgs")
	@WebResult(name = "fetchAllFormaPgsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	FormaPgResponse fetchAllFormaPgs(@WebParam(name = "request") FetchAllRequest request);



//===================================### BANCO ####======================================

/**
	 * Rebuild a list of Bancos.
	 *
	 * @param request BancoRequest object containing parameter for building the list of Banco objects.
	 *
	 * @return the BancoResponse containing the list of Bancos built
	 */
	@WebMethod(action = "insertBancos")
	@WebResult(name = "insertBancosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BancoResponse insertBanco(@WebParam(name = "request") BancoMaintenanceRequest request);

	/**
	 * Rebuild a list of Bancos.
	 *
	 * @param request BancoRequest object containing parameter for building the list of Banco objects.
	 *
	 * @return the BancoResponse containing the list of Bancos built
	 */
	@WebMethod(action = "updateBancos")
	@WebResult(name = "updateBancosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BancoResponse updateBanco(@WebParam(name = "request") BancoMaintenanceRequest request);

	/**
	 * Rebuild a list of Bancos.
	 *
	 * @param request BancoRequest object containing parameter for building the list of Banco objects.
	 *
	 * @return the BancoResponse containing the list of Bancos built
	 */
	@WebMethod(action = "deleteBancos")
	@WebResult(name = "deleteBancosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BancoResponse deleteBanco(@WebParam(name = "request") BancoMaintenanceRequest request);

	/**
	 * Rebuild a list of Bancos.
	 *
	 * @param request BancoRequest object containing parameter for building the list of Banco objects.
	 *
	 * @return the BancoResponse containing the list of Bancos built
	 */
	@WebMethod(action = "fetchBancoById")
	@WebResult(name = "fetchBancoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BancoResponse fetchBancoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Bancos.
	 *
	 * @param request BancoRequest object containing parameter for building the list of Banco objects.
	 *
	 * @return the BancoResponse containing the list of Bancos built
	 */
	@WebMethod(action = "fetchBancosByRequest")
	@WebResult(name = "fetchBancosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BancoResponse fetchBancosByRequest(@WebParam(name = "request") BancoInquiryRequest request);


	/**
	 * Rebuild a list of Bancos.
	 *
	 * @param request BancoRequest object containing parameter for building the list of Banco objects.
	 *
	 * @return the BancoResponse containing the list of Bancos built
	 */
	@WebMethod(action = "refreshBancos")
	@WebResult(name = "refreshBancosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BancoResponse refreshBancos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Bancos.
	 *
	 * @param request the request
	 *
	 * @return the BancoResponse containing all Banco objects
	 */
	@WebMethod(action = "fetchAllBancos")
	@WebResult(name = "fetchAllBancosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	BancoResponse fetchAllBancos(@WebParam(name = "request") FetchAllRequest request);



//===================================### CONTACORRENTE ####======================================

/**
	 * Rebuild a list of ContaCorrentes.
	 *
	 * @param request ContaCorrenteRequest object containing parameter for building the list of ContaCorrente objects.
	 *
	 * @return the ContaCorrenteResponse containing the list of ContaCorrentes built
	 */
	@WebMethod(action = "insertContaCorrentes")
	@WebResult(name = "insertContaCorrentesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContaCorrenteResponse insertContaCorrente(@WebParam(name = "request") ContaCorrenteMaintenanceRequest request);

	/**
	 * Rebuild a list of ContaCorrentes.
	 *
	 * @param request ContaCorrenteRequest object containing parameter for building the list of ContaCorrente objects.
	 *
	 * @return the ContaCorrenteResponse containing the list of ContaCorrentes built
	 */
	@WebMethod(action = "updateContaCorrentes")
	@WebResult(name = "updateContaCorrentesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContaCorrenteResponse updateContaCorrente(@WebParam(name = "request") ContaCorrenteMaintenanceRequest request);

	/**
	 * Rebuild a list of ContaCorrentes.
	 *
	 * @param request ContaCorrenteRequest object containing parameter for building the list of ContaCorrente objects.
	 *
	 * @return the ContaCorrenteResponse containing the list of ContaCorrentes built
	 */
	@WebMethod(action = "deleteContaCorrentes")
	@WebResult(name = "deleteContaCorrentesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContaCorrenteResponse deleteContaCorrente(@WebParam(name = "request") ContaCorrenteMaintenanceRequest request);

	/**
	 * Rebuild a list of ContaCorrentes.
	 *
	 * @param request ContaCorrenteRequest object containing parameter for building the list of ContaCorrente objects.
	 *
	 * @return the ContaCorrenteResponse containing the list of ContaCorrentes built
	 */
	@WebMethod(action = "fetchContaCorrenteById")
	@WebResult(name = "fetchContaCorrenteByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContaCorrenteResponse fetchContaCorrenteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of ContaCorrentes.
	 *
	 * @param request ContaCorrenteRequest object containing parameter for building the list of ContaCorrente objects.
	 *
	 * @return the ContaCorrenteResponse containing the list of ContaCorrentes built
	 */
	@WebMethod(action = "fetchContaCorrentesByRequest")
	@WebResult(name = "fetchContaCorrentesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContaCorrenteResponse fetchContaCorrentesByRequest(@WebParam(name = "request") ContaCorrenteInquiryRequest request);


	/**
	 * Rebuild a list of ContaCorrentes.
	 *
	 * @param request ContaCorrenteRequest object containing parameter for building the list of ContaCorrente objects.
	 *
	 * @return the ContaCorrenteResponse containing the list of ContaCorrentes built
	 */
	@WebMethod(action = "refreshContaCorrentes")
	@WebResult(name = "refreshContaCorrentesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ContaCorrenteResponse refreshContaCorrentes(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all ContaCorrentes.
	 *
	 * @param request the request
	 *
	 * @return the ContaCorrenteResponse containing all ContaCorrente objects
	 */
	@WebMethod(action = "fetchAllContaCorrentes")
	@WebResult(name = "fetchAllContaCorrentesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ContaCorrenteResponse fetchAllContaCorrentes(@WebParam(name = "request") FetchAllRequest request);



//===================================### CAIXA ####======================================

/**
	 * Rebuild a list of Caixas.
	 *
	 * @param request CaixaRequest object containing parameter for building the list of Caixa objects.
	 *
	 * @return the CaixaResponse containing the list of Caixas built
	 */
	@WebMethod(action = "insertCaixas")
	@WebResult(name = "insertCaixasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CaixaResponse insertCaixa(@WebParam(name = "request") CaixaMaintenanceRequest request);

	/**
	 * Rebuild a list of Caixas.
	 *
	 * @param request CaixaRequest object containing parameter for building the list of Caixa objects.
	 *
	 * @return the CaixaResponse containing the list of Caixas built
	 */
	@WebMethod(action = "updateCaixas")
	@WebResult(name = "updateCaixasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CaixaResponse updateCaixa(@WebParam(name = "request") CaixaMaintenanceRequest request);

	/**
	 * Rebuild a list of Caixas.
	 *
	 * @param request CaixaRequest object containing parameter for building the list of Caixa objects.
	 *
	 * @return the CaixaResponse containing the list of Caixas built
	 */
	@WebMethod(action = "deleteCaixas")
	@WebResult(name = "deleteCaixasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CaixaResponse deleteCaixa(@WebParam(name = "request") CaixaMaintenanceRequest request);

	/**
	 * Rebuild a list of Caixas.
	 *
	 * @param request CaixaRequest object containing parameter for building the list of Caixa objects.
	 *
	 * @return the CaixaResponse containing the list of Caixas built
	 */
	@WebMethod(action = "fetchCaixaById")
	@WebResult(name = "fetchCaixaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CaixaResponse fetchCaixaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Caixas.
	 *
	 * @param request CaixaRequest object containing parameter for building the list of Caixa objects.
	 *
	 * @return the CaixaResponse containing the list of Caixas built
	 */
	@WebMethod(action = "fetchCaixasByRequest")
	@WebResult(name = "fetchCaixasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CaixaResponse fetchCaixasByRequest(@WebParam(name = "request") CaixaInquiryRequest request);


	/**
	 * Rebuild a list of Caixas.
	 *
	 * @param request CaixaRequest object containing parameter for building the list of Caixa objects.
	 *
	 * @return the CaixaResponse containing the list of Caixas built
	 */
	@WebMethod(action = "refreshCaixas")
	@WebResult(name = "refreshCaixasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CaixaResponse refreshCaixas(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Caixas.
	 *
	 * @param request the request
	 *
	 * @return the CaixaResponse containing all Caixa objects
	 */
	@WebMethod(action = "fetchAllCaixas")
	@WebResult(name = "fetchAllCaixasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	CaixaResponse fetchAllCaixas(@WebParam(name = "request") FetchAllRequest request);


}
