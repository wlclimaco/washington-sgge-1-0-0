package com.qat.samples.sysmgmt.cnae.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IEntidadeBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "EntidadeService", targetNamespace = "http://qat.com/sysmgmt", portName = "EntidadeServicePort")
public interface ICnaeBAS
{
	@WebMethod(action = "insertCnae")
	@WebResult(name = "insertCnaeReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse insertCnae(EmpresaMaintenanceRequest request);

	@WebMethod(action = "updateCnae")
	@WebResult(name = "updateCnaeReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse updateCnae(EmpresaMaintenanceRequest request);

	@WebMethod(action = "deleteCnae")
	@WebResult(name = "deleteCnaeReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse deleteCnae(EmpresaMaintenanceRequest request);

	@WebMethod(action = "fetchCnaeById")
	@WebResult(name = "fetchCnaeByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse fetchCnaeById(FetchByIdRequest request);

	@WebMethod(action = "fetchCnaeByRequest")
	@WebResult(name = "fetchCnaeByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse fetchCnaeByRequest(EmpresaInquiryRequest request);

}
