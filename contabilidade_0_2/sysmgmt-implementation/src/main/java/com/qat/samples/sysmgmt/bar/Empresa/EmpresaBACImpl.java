package com.qat.samples.sysmgmt.bar.Empresa;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface EmpresaBACImpl
{

	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaMaintenanceRequest request);

	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaMaintenanceRequest request);

	public InternalResultsResponse<Empresa> deleteEmpresa(EmpresaMaintenanceRequest request);

	public InternalResultsResponse<Empresa> refreshEmpresas(RefreshRequest request);

	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

	public InternalResultsResponse<Empresa> fetchAllEmpresas();

	public InternalResultsResponse<Empresa> fetchEmpresasByRequest(PagedInquiryRequest request);

	//

	public InternalResultsResponse<Filial> insertFilial(FilialMaintenanceRequest request);

	public InternalResultsResponse<Filial> updateFilial(FilialMaintenanceRequest request);

	public InternalResultsResponse<Filial> deleteFilial(FilialMaintenanceRequest request);

	public InternalResultsResponse<Filial> refreshFilials(RefreshRequest request);

	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request);

	public InternalResultsResponse<Filial> fetchAllFilials();

	public InternalResultsResponse<Filial> fetchFilialsByRequest(PagedInquiryRequest request);

	//

	public InternalResultsResponse<Deposito> insertDeposito(DepositoMaintenanceRequest request);

	public InternalResultsResponse<Deposito> updateDeposito(DepositoMaintenanceRequest request);

	public InternalResultsResponse<Deposito> deleteDeposito(DepositoMaintenanceRequest request);

	public InternalResultsResponse<Deposito> refreshDepositos(RefreshRequest request);

	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request);

	public InternalResultsResponse<Deposito> fetchAllDepositos();

	public InternalResultsResponse<Deposito> fetchDepositosByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Usuario> insertUsuario(UsuarioMaintenanceRequest request);

	public InternalResultsResponse<Usuario> updateUsuario(UsuarioMaintenanceRequest request);

	public InternalResultsResponse<Usuario> deleteUsuario(UsuarioMaintenanceRequest request);

	public InternalResultsResponse<Usuario> refreshUsuarios(RefreshRequest request);

	public InternalResultsResponse<Usuario> fetchUsuarioById(FetchByIdRequest request);

	public InternalResultsResponse<Usuario> fetchAllUsuarios();

	public InternalResultsResponse<Usuario> fetchUsuariosByRequest(PagedInquiryRequest request);
}
