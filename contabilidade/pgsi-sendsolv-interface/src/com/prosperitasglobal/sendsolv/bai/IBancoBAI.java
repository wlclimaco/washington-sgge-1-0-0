package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.BancoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.BancoResponse;

public interface IBancoBAI
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BancoResponse insertBanco(BancoMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BancoResponse updateBanco(BancoMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BancoResponse deleteBanco(BancoMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BancoResponse fetchBancoById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BancoResponse fetchBancoByRequest(PagedInquiryRequest request);

}