package com.prosperitasglobal.sendsolv.bac;

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
