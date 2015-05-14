package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IBeneficiosBAC.
 */
public interface IBeneficiosBAC
{

	public InternalResultsResponse<Beneficios> insertBeneficios(BeneficiosMaintenanceRequest request);

	public InternalResultsResponse<Beneficios> updateBeneficios(BeneficiosMaintenanceRequest request);

	public InternalResponse deleteBeneficios(BeneficiosMaintenanceRequest request);

	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request);

	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request);

}
