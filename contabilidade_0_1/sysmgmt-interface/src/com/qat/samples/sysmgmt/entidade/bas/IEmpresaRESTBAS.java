package com.qat.samples.sysmgmt.entidade.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.response.CondominioResponse;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.DepositoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.FilialResponse;
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
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IEmpresaRESTBAS
{

	@POST
	@Path("/insertEmpresa/")
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request);

	@POST
	@Path("/updateEmpresa/")
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request);

	@POST
	@Path("/deleteEmpresa/")
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request);

	@POST
	@Path("/fetchEmpresaById/")
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request);

	@POST
	@Path("/fetchEmpresaByRequest/")
	public EmpresaResponse fetchEmpresaByRequest(EmpresaInquiryRequest request);

	@POST
	@Path("/insertCondominio/")
	public CondominioResponse insertCondominio(CondominioMaintenanceRequest request);

	@POST
	@Path("/updateCondominio/")
	public CondominioResponse updateCondominio(CondominioMaintenanceRequest request);

	@POST
	@Path("/deleteCondominio/")
	public CondominioResponse deleteCondominio(CondominioMaintenanceRequest request);

	@POST
	@Path("/fetchCondominioById/")
	public CondominioResponse fetchCondominioById(FetchByIdRequest request);

	@POST
	@Path("/fetchCondominioByRequest/")
	public CondominioResponse fetchCondominioByRequest(CondominioInquiryRequest request);

	// filial
	@POST
	@Path("/insertFilial/")
	public FilialResponse insertFilial(FilialMaintenanceRequest request);

	@POST
	@Path("/updateFilial/")
	public FilialResponse updateFilial(FilialMaintenanceRequest request);

	@POST
	@Path("/deleteFilial/")
	public FilialResponse deleteFilial(FilialMaintenanceRequest request);

	@POST
	@Path("/fetchFilialById/")
	public FilialResponse fetchFilialById(FetchByIdRequest request);

	@POST
	@Path("/fetchFilialByRequest/")
	public FilialResponse fetchFilialByRequest(FilialInquiryRequest request);

	// deposito
	@POST
	@Path("/insertDeposito/")
	public DepositoResponse insertDeposito(DepositoMaintenanceRequest request);

	@POST
	@Path("/updateDeposito/")
	public DepositoResponse updateDeposito(DepositoMaintenanceRequest request);

	@POST
	@Path("/deleteDeposito/")
	public DepositoResponse deleteDeposito(DepositoMaintenanceRequest request);

	@POST
	@Path("/fetchDepositoById/")
	public DepositoResponse fetchDepositoById(FetchByIdRequest request);

	@POST
	@Path("/fetchDepositoByRequest/")
	public DepositoResponse fetchDepositoByRequest(DepositoInquiryRequest request);

	// outros
	@POST
	@Path("/fetchCnaeByRequest/")
	public CnaeResponse fetchCnaeByRequest(CnaeInquiryRequest request);

	@POST
	@Path("/fetchRegimeByRequest/")
	public RegimeResponse fetchRegimeByRequest(RegimeInquiryRequest request);

	@POST
	@Path("/fetchCidadeByRequest/")
	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request);

	@POST
	@Path("/fetchPlanoByRequest/")
	public PlanoResponse fetchPlanoByRequest(PlanoInquiryRequest request);

	@POST
	@Path("/fetchClassificacaoByRequest/")
	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

	// Usuario
	@POST
	@Path("/insertUsuario/")
	public UsuarioResponse insertUsuario(UsuarioMaintenanceRequest request);

	@POST
	@Path("/updateUsuario/")
	public UsuarioResponse updateUsuario(UsuarioMaintenanceRequest request);

	@POST
	@Path("/deleteUsuario/")
	public UsuarioResponse deleteUsuario(UsuarioMaintenanceRequest request);

	@POST
	@Path("/fetchUsuarioById/")
	public UsuarioResponse fetchUsuarioById(FetchByIdRequest request);

	@POST
	@Path("/fetchUsuarioByRequest/")
	public UsuarioResponse fetchUsuarioByRequest(UsuarioInquiryRequest request);


	@POST
	@Path("/insertEstado/")
	public EstadoResponse insertEstado(EstadoMaintenanceRequest request);

	@POST
	@Path("/updateEstado/")
	public EstadoResponse updateEstado(EstadoMaintenanceRequest request);

	@POST
	@Path("/deleteEstado/")
	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request);
}
