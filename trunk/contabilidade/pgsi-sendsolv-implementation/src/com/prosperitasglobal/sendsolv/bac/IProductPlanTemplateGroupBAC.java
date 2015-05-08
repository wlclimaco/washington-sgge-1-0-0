package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Interface defining the methods available for working with a Product Plan Template Group's business area
 * functions.
 */
public interface IProductPlanTemplateGroupBAC
{
	/**
	 * Creates a {@link BusinessProductPlan} for every {@link com.prosperitasglobal.sendsolv.model.TemplateProductPlan}
	 * found in the request, and inserts them into the SendSolv system.
	 *
	 * @param request The request containing all of the {@link com.prosperitasglobal.sendsolv.model.TemplateProductPlan}
	 *            to copy and make a {@link BusinessProductPlan}. Also the location id to associate them to is included.
	 * @return The {@link InternalResultsResponse} containing the {@link BusinessProductPlan}'s that were created and
	 *         inserted. The inserted {@link BusinessProductPlan}'s will contain unique id's that can be used for
	 *         further processing. Also contains information about whether the create was successful or not. If the
	 *         create was not successful, no {@link BusinessProductPlan} will be contained in the response.
	 */
	public InternalResultsResponse<BusinessProductPlan> createBusinessProductPlans(
			ProductPlanTemplateGroupCreateRequest request);

	/**
	 * Delete product plan template group.
	 *
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to delete.
	 * @return The {@link InternalResponse} containing information about whether the delete was successful or not.
	 */
	public InternalResponse deleteProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup);

	/**
	 * Fetch a product plan template group by id.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing the {@link ProductPlanTemplateGroup} if found by the id.
	 *         Also contains information about whether the fetch was successful or not.
	 */
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupById(
			FetchByIdRequest request);

	/**
	 * Fetch a product plan template group by request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link ProductPlanTemplateGroup} that were
	 *         found with the matching criteria. Also contains information about whether the fetch was successful or
	 *         not.
	 */
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupByRequest(
			ProductPlanTemplateGroupInquiryRequest request);

	/**
	 * Insert product plan template group.
	 *
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to insert.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	public InternalResponse insertProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup);

	/**
	 * Update product plan template group.
	 *
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to update.
	 * @return The {@link InternalResponse} containing information about whether the update was successful or not.
	 */
	public InternalResponse updateProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup);
}
