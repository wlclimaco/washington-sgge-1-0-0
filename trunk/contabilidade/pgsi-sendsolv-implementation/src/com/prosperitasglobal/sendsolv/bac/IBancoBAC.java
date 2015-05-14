package com.prosperitasglobal.sendsolv.bac;

/**
 * The Interface IBancoBAC.
 */
public interface IBancoBAC
{

	public InternalResultsResponse<Banco> insertBanco(BancoMaintenanceRequest request);

	public InternalResultsResponse<Banco> updateBanco(BancoMaintenanceRequest request);

	public InternalResponse deleteBanco(BancoMaintenanceRequest request);

	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request);

	public InternalResultsResponse<Banco> fetchBancoByRequest(PagedInquiryRequest request);

}
