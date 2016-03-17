package com.qat.samples.sysmgmt.nf.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;

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
