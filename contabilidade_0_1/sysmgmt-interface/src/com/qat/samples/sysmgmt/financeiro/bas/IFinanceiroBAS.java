package com.qat.samples.sysmgmt.financeiro.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IFinanceiroBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "FinanceiroService", targetNamespace = "http://qat.com/sysmgmt", portName = "FinanceiroServicePort")
public interface IFinanceiroBAS
{
	@WebMethod(action = "insertFinanceiro")
	@WebResult(name = "insertFinanceiroReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FinanceiroResponse insertFinanceiro(FinanceiroMaintenanceRequest request);

	@WebMethod(action = "updateFinanceiro")
	@WebResult(name = "updateFinanceiroReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FinanceiroResponse updateFinanceiro(FinanceiroMaintenanceRequest request);

	@WebMethod(action = "deleteFinanceiro")
	@WebResult(name = "deleteFinanceiroReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FinanceiroResponse deleteFinanceiro(FinanceiroMaintenanceRequest request);

	@WebMethod(action = "fetchFinanceiroById")
	@WebResult(name = "fetchFinanceiroByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FinanceiroResponse fetchFinanceiroById(FetchByIdRequest request);

	@WebMethod(action = "fetchFinanceiroByRequest")
	@WebResult(name = "fetchFinanceiroByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FinanceiroResponse fetchFinanceiroByRequest(FinanceiroInquiryRequest request);

}
