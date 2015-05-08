package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IProductPlanDAC covers operations for Product Plan's.
 */
public interface IProductPlanDAC
{
	/**
	 * Delete a business product plan.
	 *
	 * @param businessProductPlan The business product plan to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteBusinessProductPlan(BusinessProductPlan businessProductPlan);

	/**
	 * Delete a product.
	 *
	 * @param product The product to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteProduct(Product product);

	/**
	 * Delete a template product plan.
	 *
	 * @param templateProductPlan The template product plan to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteTemplateProductPlan(TemplateProductPlan templateProductPlan);

	/**
	 * Fetch a business product plan by id.
	 *
	 * @param id The id of the business product plan to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link BusinessProductPlan} along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanById(Integer id);

	/**
	 * Fetch all business product plans matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link BusinessProductPlan}'s along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanByRequest(
			BusinessProductPlanInquiryRequest request);

	/**
	 * Fetch a product by id.
	 *
	 * @param id The id of the product to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link Product} along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<Product> fetchProductById(Integer id);

	/**
	 * Fetch a template product plan by id.
	 *
	 * @param id The id of the template product plan to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link TemplateProductPlan} along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanById(Integer id);

	/**
	 * Fetch all template product plans matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link TemplateProductPlan}'s along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanByRequest(
			TemplateProductPlanInquiryRequest request);

	/**
	 * Insert a business product plan.
	 *
	 * @param businessProductPlan The business product plan to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertBusinessProductPlan(BusinessProductPlan businessProductPlan);

	/**
	 * Insert a product.
	 *
	 * @param product The product to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertProduct(Product product);

	/**
	 * Insert a template product plan.
	 *
	 * @param templateProductPlan The template product plan to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertTemplateProductPlan(TemplateProductPlan templateProductPlan);

	/**
	 * Update a business product plan.
	 *
	 * @param businessProductPlan The business product plan to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updateBusinessProductPlan(BusinessProductPlan businessProductPlan);

	/**
	 * Update a product.
	 *
	 * @param product The product to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updateProduct(Product product);

	/**
	 * Update a template product plan.
	 *
	 * @param templateProductPlan The template product plan to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updateTemplateProductPlan(TemplateProductPlan templateProductPlan);
}
