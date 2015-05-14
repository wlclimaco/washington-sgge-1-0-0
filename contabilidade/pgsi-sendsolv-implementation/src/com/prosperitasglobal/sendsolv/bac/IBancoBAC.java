package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Banco;
import com.prosperitasglobal.sendsolv.model.request.BancoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
