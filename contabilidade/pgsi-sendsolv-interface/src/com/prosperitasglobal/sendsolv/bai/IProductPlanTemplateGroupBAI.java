package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.ProductPlanTemplateGroupCreateResponse;
import com.prosperitasglobal.sendsolv.model.response.ProductPlanTemplateGroupResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Interface IProductPlanTemplateGroupBAI.
 */
public interface IProductPlanTemplateGroupBAI
{
	/**
	 * Create business product plans.
	 *
	 * @param request The create request.
	 * @return The {ProductPlanTemplateGroupCreateResponse} containing the newly created
	 *         {@link com.prosperitasglobal.sendsolv.model.BusinessProductPlan}'s.
	 *         Will also contain information about whether the create was successful or not.
	 */
	public ProductPlanTemplateGroupCreateResponse createBusinessProductPlans(
			ProductPlanTemplateGroupCreateRequest request);

	/**
	 * Delete product plan template group.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the delete was successful or not.
	 */
	public MaintenanceResponse deleteProductPlanTemplateGroup(ProductPlanTemplateGroupMaintenanceRequest request);

	/**
	 * Fetch product plan template group by id.
	 *
	 * @param request The request.
	 * @return The product plan template group response.
	 */
	public ProductPlanTemplateGroupResponse fetchProductPlanTemplateGroupById(FetchByIdRequest request);

	/**
	 * Fetch product plan template by request.
	 *
	 * @param request The inquiry request.
	 * @return The product plan template group response.
	 */
	public ProductPlanTemplateGroupResponse fetchProductPlanTemplateGroupByRequest(
			ProductPlanTemplateGroupInquiryRequest request);

	/**
	 * Insert product plan template group.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	public MaintenanceResponse insertProductPlanTemplateGroup(ProductPlanTemplateGroupMaintenanceRequest request);

	/**
	 * Update product plan template group.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	public MaintenanceResponse updateProductPlanTemplateGroup(ProductPlanTemplateGroupMaintenanceRequest request);
}
