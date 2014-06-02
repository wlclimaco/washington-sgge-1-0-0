package com.qat.samples.sysmgmt.cidade.bai;

import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface ICidadeBAI. (Business Area Interface - BAI)
 */
public interface ICidadeBAI
{

	/**
	 * Insert cidade.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public CidadeResponse insertCidade(CidadeMaintenanceRequest request);

	/**
	 * Update cidade.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public CidadeResponse updateCidade(CidadeMaintenanceRequest request);

	/**
	 * Delete cidade.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request);

	/**
	 * Fetch all cidades.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	public CidadeResponse fetchAllCidades(FetchAllRequest request);

	/**
	 * Refresh cidades.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public CidadeResponse refreshCidades(RefreshRequest request);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	public CidadeResponse fetchCidadeById(FetchByIdRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public CidadeResponse fetchCidadesByRequest(PagedInquiryRequest request);

}
