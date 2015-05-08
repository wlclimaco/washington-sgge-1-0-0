package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IProductPlanBAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanDAC;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Implementation of the {@link IProductPlanBAC} interface. Implements the methods for working with both a Template and
 * Business Product's business area functions in the SendSolv system.
 */
public class ProductPlanBACImpl implements IProductPlanBAC
{
	/** The implementation of the {@link IProductPlanDAC}. Injected by Spring. */
	private IProductPlanDAC productPlanDAC;

	/**
	 * Get the implementation of the {@link IProductPlanDAC} to use. Injected by Spring.
	 *
	 * @return The implementation of the {@link IProductPlanDAC}.
	 */
	public IProductPlanDAC getProductPlanDAC()
	{
		return productPlanDAC;
	}

	/**
	 * Set the implementation of the {@link IProductPlanDAC}. Injected by Spring.
	 *
	 * @param productPlanDAC The implementation of the {@link IProductPlanDAC} interface.
	 */
	public void setProductPlanDAC(IProductPlanDAC productPlanDAC)
	{
		this.productPlanDAC = productPlanDAC;
	}

	/**
	 * Delete business product plan from the SendSolv system.
	 *
	 * @param businessProductPlan The {@link BusinessProductPlan} to delete.
	 * @return The {@link InternalResponse} containing information about whether the delete was successful or not.
	 */
	@Override
	public InternalResponse deleteBusinessProductPlan(BusinessProductPlan businessProductPlan)
	{
		return getProductPlanDAC().deleteBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Delete template product plan from the SendSolv system.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} to delete.
	 * @return The {@link InternalResponse} containing information about whether the delete was successful or not.
	 */
	@Override
	public InternalResponse deleteTemplateProductPlan(TemplateProductPlan templateProductPlan)
	{
		return getProductPlanDAC().deleteTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Fetch business product plan by id in the SendSolv system.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing the {@link BusinessProductPlan} if found by the id. Also
	 *         contains information about whether the fetch was successful or not.
	 */
	@Override
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanById(FetchByIdRequest request)
	{
		return getProductPlanDAC().fetchBusinessProductPlanById(request.getId());
	}

	/**
	 * Fetch business product plan by request in the SendSolv system.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link BusinessProductPlan} that were found
	 *         with the matching criteria. Also contains information about whether the fetch was successful or not.
	 */
	@Override
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanByRequest(
			BusinessProductPlanInquiryRequest request)
	{
		return getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
	}

	/**
	 * Fetch template product plan by id in the SendSolv system.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing the {@link TemplateProductPlan} if found by the id. Also
	 *         contains information about whether the fetch was successful or not.
	 */
	@Override
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanById(FetchByIdRequest request)
	{
		return getProductPlanDAC().fetchTemplateProductPlanById(request.getId());
	}

	/**
	 * Fetch template product plan by request in the SendSolv system.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link TemplateProductPlan} that were found
	 *         with the matching criteria. Also contains information about whether the fetch was successful or not.
	 */
	@Override
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanByRequest(
			TemplateProductPlanInquiryRequest request)
	{
		return getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
	}

	/**
	 * Insert business product plan in the SendSolv system.
	 *
	 * @param businessProductPlan The {@link BusinessProductPlan} to insert.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	@Override
	public InternalResponse insertBusinessProductPlan(BusinessProductPlan businessProductPlan)
	{
		return getProductPlanDAC().insertBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Insert template product plan in the SendSolv system.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} to insert.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	@Override
	public InternalResponse insertTemplateProductPlan(TemplateProductPlan templateProductPlan)
	{
		return getProductPlanDAC().insertTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Update business product plan in the SendSolv system.
	 *
	 * @param businessProductPlan The {@link BusinessProductPlan} to update.
	 * @return The {@link InternalResponse} containing information about whether the update was successful or not.
	 */
	@Override
	public InternalResponse updateBusinessProductPlan(BusinessProductPlan businessProductPlan)
	{
		return getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Update template product plan in the SendSolv system.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} to update.
	 * @return The {@link InternalResponse} containing information about whether the update was successful or not.
	 */
	@Override
	public InternalResponse updateTemplateProductPlan(TemplateProductPlan templateProductPlan)
	{
		return getProductPlanDAC().updateTemplateProductPlan(templateProductPlan);
	}
}
