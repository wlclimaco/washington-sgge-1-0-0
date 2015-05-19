package com.prosperitasglobal.sendsolv.bai;


// TODO: Auto-generated Javadoc
/**
 * The Interface IHistoricoBAI.
 */
public interface ICadastrosBAI
{

	public CidadeResponse insertCidade(CidadeMaintenanceRequest request);

	public CidadeResponse updateCidade(CidadeMaintenanceRequest request);

	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request);

	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request);

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

}