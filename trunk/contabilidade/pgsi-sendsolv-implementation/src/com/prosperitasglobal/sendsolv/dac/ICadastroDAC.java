package com.prosperitasglobal.sendsolv.dac;


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

	public InternalResultsResponse<Cfop> fetchCfopByRequest(Cfop request);

}
