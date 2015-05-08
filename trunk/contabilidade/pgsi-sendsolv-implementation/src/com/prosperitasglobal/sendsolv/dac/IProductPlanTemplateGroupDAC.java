package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IProductPlanTemplateGroupDAC covers operations for Product Plan's.
 */
public interface IProductPlanTemplateGroupDAC
{
	/**
	 * Delete a product plan template group.
	 *
	 * @param productPlanTemplateGroup The product plan template group to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup);

	/**
	 * Fetch a product plan template group by id.
	 *
	 * @param id The id of the product plan template group to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link ProductPlanTemplateGroup} along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupById(Integer id);

	/**
	 * Fetch all product plan template groups matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link ProductPlanTemplateGroup}'s along
	 *         with information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupByRequest(
			PagedInquiryRequest request);

	/**
	 * Insert a product plan template group.
	 *
	 * @param productPlanTemplateGroup The product plan template group to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup);

	/**
	 * Update a product plan template group.
	 *
	 * @param productPlanTemplateGroup The product plan template group to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updateProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup);
}
