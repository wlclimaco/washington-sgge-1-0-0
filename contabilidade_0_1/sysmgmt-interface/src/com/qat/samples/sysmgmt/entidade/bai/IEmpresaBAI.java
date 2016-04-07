package com.qat.samples.sysmgmt.entidade.bai;

import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.response.CondominioResponse;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.DepositoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.FilialResponse;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.ClassificacaoResponse;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.util.model.response.UsuarioResponse;

/**
 * The Interface IEmpresaBAI.
 */
public interface IEmpresaBAI
{

	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request);

	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request);

	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request);

	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request);

	public EmpresaResponse fetchEmpresaByRequest(EmpresaInquiryRequest request);

	public CondominioResponse insertCondominio(CondominioMaintenanceRequest request);

	public CondominioResponse updateCondominio(CondominioMaintenanceRequest request);

	public CondominioResponse deleteCondominio(CondominioMaintenanceRequest request);

	public CondominioResponse fetchCondominioById(FetchByIdRequest request);

	public CondominioResponse fetchCondominioByRequest(CondominioInquiryRequest request);

	// filial
	public FilialResponse insertFilial(FilialMaintenanceRequest request);

	public FilialResponse updateFilial(FilialMaintenanceRequest request);

	public FilialResponse deleteFilial(FilialMaintenanceRequest request);

	public FilialResponse fetchFilialById(FetchByIdRequest request);

	public FilialResponse fetchFilialByRequest(FilialInquiryRequest request);

	// deposito
	public DepositoResponse insertDeposito(DepositoMaintenanceRequest request);

	public DepositoResponse updateDeposito(DepositoMaintenanceRequest request);

	public DepositoResponse deleteDeposito(DepositoMaintenanceRequest request);

	public DepositoResponse fetchDepositoById(FetchByIdRequest request);

	public DepositoResponse fetchDepositoByRequest(DepositoInquiryRequest request);

	// outros
	public CnaeResponse fetchCnaeByRequest(CnaeInquiryRequest request);

	public RegimeResponse fetchRegimeByRequest(RegimeInquiryRequest request);

	public CidadeResponse insertCidade(CidadeMaintenanceRequest request);

	public CidadeResponse updateCidade(CidadeMaintenanceRequest request);

	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request);

	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request);

	public PlanoResponse fetchPlanoByRequest(PlanoInquiryRequest request);

	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

	// Usuario
	public UsuarioResponse insertUsuario(UsuarioMaintenanceRequest request);

	public UsuarioResponse updateUsuario(UsuarioMaintenanceRequest request);

	public UsuarioResponse deleteUsuario(UsuarioMaintenanceRequest request);

	public UsuarioResponse fetchUsuarioById(FetchByIdRequest request);

	public UsuarioResponse fetchUsuarioByRequest(UsuarioInquiryRequest request);


	public EstadoResponse insertEstado(EstadoMaintenanceRequest request);

	public EstadoResponse updateEstado(EstadoMaintenanceRequest request);

	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request);

	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

}