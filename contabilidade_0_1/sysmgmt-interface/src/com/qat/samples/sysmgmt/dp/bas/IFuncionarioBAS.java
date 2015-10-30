package com.qat.samples.sysmgmt.dp.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.DepositoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.FilialResponse;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.ClassificacaoResponse;
import com.qat.samples.sysmgmt.fiscal.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.util.model.response.UsuarioResponse;

/**
 * The Interface IEntidadeBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "EntidadeService", targetNamespace = "http://qat.com/sysmgmt", portName = "EntidadeServicePort")
public interface IFuncionarioBAS
{
	@WebMethod(action = "insertEmpresa")
	@WebResult(name = "insertEmpresaReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request);

	@WebMethod(action = "updateEmpresa")
	@WebResult(name = "updateEmpresaReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request);

	@WebMethod(action = "deleteEmpresa")
	@WebResult(name = "deleteEmpresaReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request);

	@WebMethod(action = "fetchEmpresaById")
	@WebResult(name = "fetchEmpresaByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request);

	@WebMethod(action = "fetchEmpresaByRequest")
	@WebResult(name = "fetchEmpresaByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EmpresaResponse fetchEmpresaByRequest(EmpresaInquiryRequest request);

	// filial

	@WebMethod(action = "insertFilial")
	@WebResult(name = "insertFilialReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FilialResponse insertFilial(FilialMaintenanceRequest request);

	@WebMethod(action = "updateFilial")
	@WebResult(name = "updateFilialReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FilialResponse updateFilial(FilialMaintenanceRequest request);

	@WebMethod(action = "deleteFilial")
	@WebResult(name = "deleteFilialReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FilialResponse deleteFilial(FilialMaintenanceRequest request);

	@WebMethod(action = "fetchFilialById")
	@WebResult(name = "fetchFilialByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FilialResponse fetchFilialById(FetchByIdRequest request);

	@WebMethod(action = "fetchFilialByRequest")
	@WebResult(name = "fetchFilialByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public FilialResponse fetchFilialByRequest(FilialInquiryRequest request);

	// deposito

	@WebMethod(action = "insertDeposito")
	@WebResult(name = "insertDepositoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public DepositoResponse insertDeposito(DepositoMaintenanceRequest request);

	public DepositoResponse updateDeposito(DepositoMaintenanceRequest request);

	@WebMethod(action = "deleteDeposito")
	@WebResult(name = "deleteDepositoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public DepositoResponse deleteDeposito(DepositoMaintenanceRequest request);

	@WebMethod(action = "fetchDepositoById")
	@WebResult(name = "fetchDepositoByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public DepositoResponse fetchDepositoById(FetchByIdRequest request);

	@WebMethod(action = "fetchDepositoByRequest")
	@WebResult(name = "fetchDepositoByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public DepositoResponse fetchDepositoByRequest(DepositoInquiryRequest request);

	// outros

	@WebMethod(action = "fetchCnaeByRequest")
	@WebResult(name = "fetchCnaeByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public CnaeResponse fetchCnaeByRequest(CnaeInquiryRequest request);

	@WebMethod(action = "fetchRegimeByRequest")
	@WebResult(name = "fetchRegimeByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public RegimeResponse fetchRegimeByRequest(RegimeInquiryRequest request);

	@WebMethod(action = "fetchCidadeByRequest")
	@WebResult(name = "fetchCidadeByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request);

	@WebMethod(action = "fetchPlanoByRequest")
	@WebResult(name = "fetchPlanoByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public PlanoResponse fetchPlanoByRequest(PlanoInquiryRequest request);

	@WebMethod(action = "fetchClassificacaoByRequest")
	@WebResult(name = "fetchClassificacaoByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

	// Usuario

	@WebMethod(action = "insertUsuario")
	@WebResult(name = "insertUsuarioReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public UsuarioResponse insertUsuario(UsuarioMaintenanceRequest request);

	@WebMethod(action = "updateUsuario")
	@WebResult(name = "updateUsuarioReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public UsuarioResponse updateUsuario(UsuarioMaintenanceRequest request);

	@WebMethod(action = "deleteUsuario")
	@WebResult(name = "deleteUsuarioReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public UsuarioResponse deleteUsuario(UsuarioMaintenanceRequest request);

	@WebMethod(action = "fetchUsuarioById")
	@WebResult(name = "fetchUsuarioByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public UsuarioResponse fetchUsuarioById(FetchByIdRequest request);

	@WebMethod(action = "fetchUsuarioByRequest")
	@WebResult(name = "fetchUsuarioByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public UsuarioResponse fetchUsuarioByRequest(UsuarioInquiryRequest request);

}
