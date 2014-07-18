package com.qat.samples.sysmgmt.produto.bai;

import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.CadastroResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

/**
 * The Interface IProdutoBAI. (Business Area Interface - BAI)
 */
public interface IProdutoBAI
{

	/**
	 * Insert produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request);

	/**
	 * Update produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request);

	/**
	 * Delete produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request);

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	public ProdutoResponse fetchAllProdutos(ProdutoInquiryRequest request);

	/**
	 * Refresh produtos.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public ProdutoResponse refreshProdutos(RefreshRequest request);

	/**
	 * Fetch produto by id.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch produtos by request.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public ProdutoResponse fetchProdutosByRequest(PagedInquiryRequest request);

	// =======================
	/**
	 * Insert property.
	 * 
	 * @param request the request
	 * @return the property paged response
	 */
	public CadastroResponse insertCadastro(CadastroMaintenanceRequest request);

	/**
	 * Update property.
	 * 
	 * @param request the request
	 * @return the property paged response
	 */
	public CadastroResponse updateCadastro(CadastroMaintenanceRequest request);

	/**
	 * Delete property.
	 * 
	 * @param request the request
	 * @return the property paged response
	 */
	public CadastroResponse deleteCadastro(CadastroMaintenanceRequest request);

	/**
	 * Fetch all propertys.
	 * 
	 * @param request the request
	 * @return the property response
	 */
	public CadastroResponse fetchAllCadastros(CadastroInquiryRequest request);

	/**
	 * Refresh propertys.
	 * 
	 * @param request the request
	 * @return the property paged response
	 */
	public CadastroResponse refreshCadastros(RefreshRequest request);

	/**
	 * Fetch property by id.
	 * 
	 * @param request the request
	 * @return the property response
	 */
	public CadastroResponse fetchCadastroById(FetchByIdRequest request);

	/**
	 * Fetch propertys by request.
	 * 
	 * @param request the request
	 * @return the property paged response
	 */
	public CadastroResponse fetchCadastrosByRequest(CadastroInquiryRequest request);

}
