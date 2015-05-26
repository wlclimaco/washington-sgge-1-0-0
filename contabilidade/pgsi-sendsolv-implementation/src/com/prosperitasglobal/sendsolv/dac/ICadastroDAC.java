package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Csosn;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Regime;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.request.CfopInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CsosnInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IBancoDAC.
 */
public interface ICadastroDAC
{

	public InternalResultsResponse<Cidade> insertCidade(Cidade request);

	public InternalResultsResponse<Cidade> updateCidade(Cidade request);

	public InternalResponse deleteCidade(Cidade request);

	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request);

	// =======================================

	public InternalResultsResponse<Estado> insertEstado(Estado request);

	public InternalResultsResponse<Estado> updateEstado(Estado request);

	public InternalResponse deleteEstado(Estado request);

	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request);

	// ====================================
	public InternalResultsResponse<Cnae> insertCnae(Cnae request);

	public InternalResultsResponse<Cnae> updateCnae(Cnae request);

	public InternalResponse deleteCnae(Cnae request);

	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request);

	// =======================================
	public InternalResultsResponse<Regime> insertRegime(Regime request);

	public InternalResultsResponse<Regime> updateRegime(Regime request);

	public InternalResponse deleteRegime(Regime request);

	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request);

	// =======================================================

	public InternalResultsResponse<Csosn> insertCsosn(Csosn request);

	public InternalResultsResponse<Csosn> updateCsosn(Csosn request);

	public InternalResponse deleteCsosn(Csosn request);

	public InternalResultsResponse<Csosn> fetchCsosnByRequest(CsosnInquiryRequest request);

	// =======================================================
	public InternalResultsResponse<UniMed> insertUniMed(UniMed request);

	public InternalResultsResponse<UniMed> updateUniMed(UniMed request);

	public InternalResponse deleteUniMed(UniMed request);

	public InternalResultsResponse<UniMed> fetchUniMedByRequest(UniMedInquiryRequest request);

	// =======================================================

	public InternalResultsResponse<Cfop> insertCfop(Cfop request);

	public InternalResultsResponse<Cfop> updateCfop(Cfop request);

	public InternalResultsResponse<Cfop> deleteCfop(Cfop request);

	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request);

}
