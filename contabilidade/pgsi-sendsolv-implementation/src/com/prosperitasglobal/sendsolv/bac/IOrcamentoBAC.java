package com.prosperitasglobal.sendsolv.bac;

/**
 * The Interface IOrcamentoBAC.
 */
public interface IOrcamentoBAC
{

	public InternalResultsResponse<Orcamento> insertOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResultsResponse<Orcamento> updateOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResponse deleteOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoInquiryRequest request);

}
