package com.qat.samples.sysmgmt.supermercado.bai;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * The Interface ISupermercadoBAI. (Business Area Interface - BAI)
 */
public interface ISupermercadoBAI
{

	/**
	 * Insert supermercado.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SupermercadoResponse insertSupermercado(SupermercadoMaintenanceRequest request);

	/**
	 * Update supermercado.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SupermercadoResponse updateSupermercado(SupermercadoMaintenanceRequest request);

	/**
	 * Delete supermercado.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SupermercadoResponse deleteSupermercado(SupermercadoMaintenanceRequest request);

	/**
	 * Fetch all supermercados.
	 * 
	 * @param request the request
	 * @return the supermercado response
	 */
	public SupermercadoResponse fetchAllSupermercados(FetchAllRequest request);

	/**
	 * Refresh supermercados.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SupermercadoResponse refreshSupermercados(RefreshRequest request);

	/**
	 * Fetch supermercado by id.
	 * 
	 * @param request the request
	 * @return the supermercado response
	 */
	public SupermercadoResponse fetchSupermercadoById(FetchByIdRequest request);

	/**
	 * Fetch supermercados by request.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SupermercadoResponse fetchSupermercadosByRequest(PagedInquiryRequest request);

}
