package com.qat.samples.sysmgmt.nf.bas.rest;

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
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.ClassificacaoResponse;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.bai.INotaFiscalBAI;
import com.qat.samples.sysmgmt.nf.bas.INotaFiscalRESTBAS;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.util.model.response.UsuarioResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class NotaFiscalBAS implements INotaFiscalRESTBAS
{

	/** The empresa bai. */
	private INotaFiscalBAI pessoaBAI; // injected by Spring through setter

	public INotaFiscalBAI getNotaFiscalBAI()
	{
		return pessoaBAI;
	}

	public void setNotaFiscalBAI(INotaFiscalBAI pessoaBAI)
	{
		this.pessoaBAI = pessoaBAI;
	}

	@Override
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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

}
