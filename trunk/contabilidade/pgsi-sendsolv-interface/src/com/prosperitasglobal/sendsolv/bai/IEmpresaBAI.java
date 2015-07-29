package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClassificacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PlanoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.CidadeResponse;
import com.prosperitasglobal.sendsolv.model.response.ClassificacaoResponse;
import com.prosperitasglobal.sendsolv.model.response.CnaeResponse;
import com.prosperitasglobal.sendsolv.model.response.DepositoResponse;
import com.prosperitasglobal.sendsolv.model.response.EmpresaResponse;
import com.prosperitasglobal.sendsolv.model.response.FilialResponse;
import com.prosperitasglobal.sendsolv.model.response.PlanoResponse;
import com.prosperitasglobal.sendsolv.model.response.RegimeResponse;

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

	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request);

	public PlanoResponse fetchPlanoByRequest(PlanoInquiryRequest request);

	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

}