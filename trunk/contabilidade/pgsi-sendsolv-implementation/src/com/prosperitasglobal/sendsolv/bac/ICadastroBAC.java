package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Csosn;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Regime;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.request.CfopInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CfopMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.CsosnInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CsosnMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IBancoBAC.
 */
public interface ICadastroBAC
{
	public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request);

	public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request);

	public InternalResponse deleteCidade(CidadeMaintenanceRequest request);

	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request);

	// =======================================

	public InternalResultsResponse<Estado> insertEstado(EstadoMaintenanceRequest request);

	public InternalResultsResponse<Estado> updateEstado(EstadoMaintenanceRequest request);

	public InternalResponse deleteEstado(EstadoMaintenanceRequest request);

	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request);

	// ====================================
	public InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request);

	public InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request);

	public InternalResponse deleteCnae(CnaeMaintenanceRequest request);

	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request);

	// =======================================
	public InternalResultsResponse<Regime> insertRegime(RegimeMaintenanceRequest request);

	public InternalResultsResponse<Regime> updateRegime(RegimeMaintenanceRequest request);

	public InternalResponse deleteRegime(RegimeMaintenanceRequest request);

	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request);

	// =======================================================

	public InternalResultsResponse<Csosn> insertCsosn(CsosnMaintenanceRequest request);

	public InternalResultsResponse<Csosn> updateCsosn(CsosnMaintenanceRequest request);

	public InternalResponse deleteCsosn(CsosnMaintenanceRequest request);

	public InternalResultsResponse<Csosn> fetchCsosnByRequest(CsosnInquiryRequest request);

	// =======================================================
	public InternalResultsResponse<UniMed> insertCsosn(UniMedMaintenanceRequest request);

	public InternalResultsResponse<UniMed> updateCsosn(UniMedMaintenanceRequest request);

	public InternalResponse deleteCsosn(UniMedMaintenanceRequest request);

	public InternalResultsResponse<UniMed> fetchCsosnByRequest(UniMedInquiryRequest request);

	// =======================================================

	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request);

	// =======================================================

}
