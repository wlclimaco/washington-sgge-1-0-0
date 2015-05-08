package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.BusinessProductPlanResponse;
import com.prosperitasglobal.sendsolv.model.response.TemplateProductPlanResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Interface IProductPlanBAI.
 */
public interface IProductPlanBAI
{
	/**
	 * Delete business product plan.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the delete was successful or not.
	 */
	public MaintenanceResponse deleteBusinessProductPlan(BusinessProductPlanMaintenanceRequest request);

	/**
	 * Delete template product plan.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the delete was successful or not.
	 */
	public MaintenanceResponse deleteTemplateProductPlan(TemplateProductPlanMaintenanceRequest request);

	/**
	 * Fetch business product plan by id.
	 *
	 * @param request The request.
	 * @return The business product plan response.
	 */
	public BusinessProductPlanResponse fetchBusinessProductPlanById(FetchByIdRequest request);

	/**
	 * Fetch business product plan by request.
	 *
	 * @param request The request.
	 * @return The business product plan response.
	 */
	public BusinessProductPlanResponse fetchBusinessProductPlanByRequest(BusinessProductPlanInquiryRequest request);

	/**
	 * Fetch template product plan by id.
	 *
	 * @param request The request.
	 * @return The template product plan response.
	 */
	public TemplateProductPlanResponse fetchTemplateProductPlanById(FetchByIdRequest request);

	/**
	 * Fetch template product plan by request.
	 *
	 * @param request The request.
	 * @return The template product plan response.
	 */
	public TemplateProductPlanResponse fetchTemplateProductPlanByRequest(TemplateProductPlanInquiryRequest request);

	/**
	 * Insert business product plan.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	public MaintenanceResponse insertBusinessProductPlan(BusinessProductPlanMaintenanceRequest request);

	/**
	 * Insert template product plan.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	public MaintenanceResponse insertTemplateProductPlan(TemplateProductPlanMaintenanceRequest request);

	/**
	 * Update business product plan.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	public MaintenanceResponse updateBusinessProductPlan(BusinessProductPlanMaintenanceRequest request);

	/**
	 * Update template product plan.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	public MaintenanceResponse updateTemplateProductPlan(TemplateProductPlanMaintenanceRequest request);
}
