package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.ProdutoResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IProdutoBAI.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface IProdutoBAI
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse fetchProdutoByRequest(ProdutoInquiryRequest request);

	/**
	 * Fetch location by organization.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse fetchProdutoByOrganization(ProdutoInquiryRequest request);

}