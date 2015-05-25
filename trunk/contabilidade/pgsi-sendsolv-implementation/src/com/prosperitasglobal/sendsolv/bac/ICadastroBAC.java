package com.prosperitasglobal.sendsolv.bac;


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
	public InternalResultsResponse<UniMed> insertUniMed(UniMedMaintenanceRequest request);

	public InternalResultsResponse<UniMed> updateUniMed(UniMedMaintenanceRequest request);

	public InternalResponse deleteUniMed(UniMedMaintenanceRequest request);

	public InternalResultsResponse<UniMed> fetchUniMedByRequest(UniMedInquiryRequest request);

	// =======================================================

	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request);

	// =======================================================

}
