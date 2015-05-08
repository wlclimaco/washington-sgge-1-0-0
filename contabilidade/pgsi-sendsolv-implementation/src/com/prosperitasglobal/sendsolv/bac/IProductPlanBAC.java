package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Interface defining the methods available for working with both a Template and Business Product's business area
 * functions.
 */
public interface IProductPlanBAC
{
	/**
	 * Delete business product plan.
	 *
	 * @param businessProductPlan The {@link BusinessProductPlan} to delete.
	 * @return The {@link InternalResponse} containing information about whether the delete was successful or not.
	 */
	public InternalResponse deleteBusinessProductPlan(BusinessProductPlan businessProductPlan);

	/**
	 * Delete template product plan.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} to delete.
	 * @return The {@link InternalResponse} containing information about whether the delete was successful or not.
	 */
	public InternalResponse deleteTemplateProductPlan(TemplateProductPlan templateProductPlan);

	/**
	 * Fetch business product plan by id.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing the {@link BusinessProductPlan} if found by the id. Also
	 *         contains information about whether the fetch was successful or not.
	 */
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanById(FetchByIdRequest request);

	/**
	 * Fetch business product plan by request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link BusinessProductPlan} that were found
	 *         with the matching criteria. Also contains information about whether the fetch was successful or not.
	 */
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanByRequest(
			BusinessProductPlanInquiryRequest request);

	/**
	 * Fetch template product plan by id.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing the {@link TemplateProductPlan} if found by the id. Also
	 *         contains information about whether the fetch was successful or not.
	 */
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanById(FetchByIdRequest request);

	/**
	 * Fetch template product plan by request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link TemplateProductPlan} that were found
	 *         with the matching criteria. Also contains information about whether the fetch was successful or not.
	 */
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanByRequest(
			TemplateProductPlanInquiryRequest request);

	/**
	 * Insert business product plan.
	 *
	 * @param businessProductPlan The {@link BusinessProductPlan} to insert.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	public InternalResponse insertBusinessProductPlan(BusinessProductPlan businessProductPlan);

	/**
	 * Insert template product plan.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} to insert.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	public InternalResponse insertTemplateProductPlan(TemplateProductPlan templateProductPlan);

	/**
	 * Update business product plan.
	 *
	 * @param businessProductPlan The {@link BusinessProductPlan} to update.
	 * @return The {@link InternalResponse} containing information about whether the update was successful or not.
	 */
	public InternalResponse updateBusinessProductPlan(BusinessProductPlan businessProductPlan);

	/**
	 * Update template product plan.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} to update.
	 * @return The {@link InternalResponse} containing information about whether the update was successful or not.
	 */
	public InternalResponse updateTemplateProductPlan(TemplateProductPlan templateProductPlan);
}
