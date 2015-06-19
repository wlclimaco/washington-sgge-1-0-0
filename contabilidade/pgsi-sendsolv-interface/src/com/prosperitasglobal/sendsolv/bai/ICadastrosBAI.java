package com.prosperitasglobal.sendsolv.bai;

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
import com.prosperitasglobal.sendsolv.model.response.CfopResponse;
import com.prosperitasglobal.sendsolv.model.response.CidadeResponse;
import com.prosperitasglobal.sendsolv.model.response.CnaeResponse;
import com.prosperitasglobal.sendsolv.model.response.CsosnResponse;
import com.prosperitasglobal.sendsolv.model.response.EstadoResponse;
import com.prosperitasglobal.sendsolv.model.response.RegimeResponse;
import com.prosperitasglobal.sendsolv.model.response.UniMedResponse;

/**
 * The Interface IHistoricoBAI.
 */
public interface ICadastrosBAI
{

	public CidadeResponse insertCidade(CidadeMaintenanceRequest request);

	public CidadeResponse updateCidade(CidadeMaintenanceRequest request);

	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request);

	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request);

	// =======================================

	public EstadoResponse insertEstado(EstadoMaintenanceRequest request);

	public EstadoResponse updateEstado(EstadoMaintenanceRequest request);

	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request);

	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

	// ====================================
	public CnaeResponse insertCnae(CnaeMaintenanceRequest request);

	public CnaeResponse updateCnae(CnaeMaintenanceRequest request);

	public CnaeResponse deleteCnae(CnaeMaintenanceRequest request);

	public CnaeResponse fetchCnaeByRequest(CnaeInquiryRequest request);

	// =======================================
	public RegimeResponse insertRegime(RegimeMaintenanceRequest request);

	public RegimeResponse updateRegime(RegimeMaintenanceRequest request);

	public RegimeResponse deleteRegime(RegimeMaintenanceRequest request);

	public RegimeResponse fetchRegimeByRequest(RegimeInquiryRequest request);

	// =======================================================

	public CsosnResponse insertCsosn(CsosnMaintenanceRequest request);

	public CsosnResponse updateCsosn(CsosnMaintenanceRequest request);

	public CsosnResponse deleteCsosn(CsosnMaintenanceRequest request);

	public CsosnResponse fetchCsosnByRequest(CsosnInquiryRequest request);

	// =======================================================
	public UniMedResponse insertCsosn(UniMedMaintenanceRequest request);

	public UniMedResponse updateCsosn(UniMedMaintenanceRequest request);

	public UniMedResponse deleteCsosn(UniMedMaintenanceRequest request);

	public UniMedResponse fetchCsosnByRequest(UniMedInquiryRequest request);

	// =======================================================

	public CfopResponse insertCfop(CfopMaintenanceRequest request);

	public CfopResponse updateCfop(CfopMaintenanceRequest request);

	public CfopResponse deleteCfop(CfopMaintenanceRequest request);

	public CfopResponse fetchCfopByRequest(CfopInquiryRequest request);

	// =======================================================

	// =======================================================

}