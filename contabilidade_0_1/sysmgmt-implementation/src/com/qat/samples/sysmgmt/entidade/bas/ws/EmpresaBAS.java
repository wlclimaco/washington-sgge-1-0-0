package com.qat.samples.sysmgmt.entidade.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.entidade.bai.IEmpresaBAI;
import com.qat.samples.sysmgmt.entidade.bas.IEmpresaBAS;
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
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
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
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class EmpresaBAS implements IEmpresaBAS
{

	/** The bundle bai. */
	private IEmpresaBAI bundleBAI; // injected by Spring through setter

	/**
	 * Spring Sets the bundle bai.
	 * 
	 * @param bundleBAI the new bundle bai
	 */
	public void setEmpresaBAI(IEmpresaBAI bundleBAI)
	{
		this.bundleBAI = bundleBAI;
	}

	/**
	 * Gets the bundle bac.
	 * 
	 * @return the bundle bac
	 */
	public IEmpresaBAI getEmpresaBAI()
	{
		return bundleBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IEmpresaBAS#insertEmpresa(com.qat.samples.sysmgmt.model.request.EmpresaMaintenanceRequest
	 * )
	 */
	@Override
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request)
	{
		return getEmpresaBAI().insertEmpresa(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IEmpresaBAS#updateEmpresa(com.qat.samples.sysmgmt.model.request.EmpresaMaintenanceRequest
	 * )
	 */
	@Override
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request)
	{
		return getEmpresaBAI().updateEmpresa(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IEmpresaBAS#deleteEmpresa(com.qat.samples.sysmgmt.model.request.EmpresaMaintenanceRequest
	 * )
	 */
	@Override
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request)
	{
		return getEmpresaBAI().deleteEmpresa(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IEmpresaBAS#fetchEmpresaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request)
	{
		return getEmpresaBAI().fetchEmpresaById(request);
	}

	@Override
	public EmpresaResponse fetchEmpresaByRequest(EmpresaInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse insertFilial(FilialMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse updateFilial(FilialMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse deleteFilial(FilialMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse fetchFilialById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse fetchFilialByRequest(FilialInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepositoResponse insertDeposito(DepositoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepositoResponse updateDeposito(DepositoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepositoResponse deleteDeposito(DepositoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepositoResponse fetchDepositoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepositoResponse fetchDepositoByRequest(DepositoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CnaeResponse fetchCnaeByRequest(CnaeInquiryRequest request)
	{
		return getEmpresaBAI().fetchCnaeByRequest(request);
	}

	@Override
	public RegimeResponse fetchRegimeByRequest(RegimeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request)
	{
		return getEmpresaBAI().fetchCidadeByRequest(request);
	}

	@Override
	public PlanoResponse fetchPlanoByRequest(PlanoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResponse insertUsuario(UsuarioMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResponse updateUsuario(UsuarioMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResponse deleteUsuario(UsuarioMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResponse fetchUsuarioById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResponse fetchUsuarioByRequest(UsuarioInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CidadeResponse insertCidade(CidadeMaintenanceRequest request)
	{
		return getEmpresaBAI().insertCidade(request);
	}

	@Override
	public CidadeResponse updateCidade(CidadeMaintenanceRequest request)
	{
		return getEmpresaBAI().updateCidade(request);
	}

	@Override
	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		return getEmpresaBAI().deleteCidade(request);
	}

	@Override
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
