package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.BeneficiosResponse;

public interface IBeneficiosBAI
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BeneficiosResponse insertBeneficios(BeneficiosMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BeneficiosResponse updateBeneficios(BeneficiosMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BeneficiosResponse deleteBeneficios(BeneficiosMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BeneficiosResponse fetchBeneficiosById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public BeneficiosResponse fetchBeneficiosByRequest(PagedInquiryRequest request);

}