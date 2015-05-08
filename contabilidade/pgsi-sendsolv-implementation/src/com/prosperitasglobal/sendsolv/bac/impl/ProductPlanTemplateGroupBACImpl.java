package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IProductPlanTemplateGroupBAC;
import com.prosperitasglobal.sendsolv.bad.ProductPlanBAD;
import com.prosperitasglobal.sendsolv.dac.IProductPlanDAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanTemplateGroupDAC;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Implementation of the {@link IProductPlanTemplateGroupBAC} interface. Implements the methods for working with both a
 * Product Plan Template Group.
 */
public class ProductPlanTemplateGroupBACImpl implements IProductPlanTemplateGroupBAC
{
	/** The implementation of the {@link IProductPlanDAC}. Injected by Spring. */
	private IProductPlanDAC productPlanDAC;

	/** The implementation of the {@link IProductPlanTemplateGroupDAC}. Injected by Spring. */
	private IProductPlanTemplateGroupDAC productPlanTemplateGroupDAC;

	/**
	 * Creates a {@link BusinessProductPlan} for every {@link com.prosperitasglobal.sendsolv.model.TemplateProductPlan}
	 * found in the request, and inserts them into the SendSolv system.
	 *
	 * @param request The request containing all of the {@link com.prosperitasglobal.sendsolv.model.TemplateProductPlan}
	 *            to copy and make a {@link BusinessProductPlan}. Also the location id to associate them to is included.
	 * @param response The {@link InternalResultsResponse} containing the {@link BusinessProductPlan}'s that were
	 *            created and inserted. The inserted {@link BusinessProductPlan}'s will contain unique id's that can be
	 *            used for further processing. Also contains information about whether the create was successful or not.
	 *            If the create was not successful, no {@link BusinessProductPlan} will be contained in the response.
	 */
	private void createBusinessProductPlan(ProductPlanTemplateGroupCreateRequest request,
			InternalResultsResponse<BusinessProductPlan> response)
	{
		ProductPlanTemplateGroup productPlanTemplateGroup =
				request.getProductPlanTemplateGroupLocation().getProductPlanTemplateGroup();
		Integer locationId = request.getProductPlanTemplateGroupLocation().getLocationId();

		if (!ValidationUtil.isNullOrEmpty(productPlanTemplateGroup.getTemplateProductPlanList()))
		{
			InternalResponse insertResponse = null;
			BusinessProductPlan businessProductPlan = null;
			for (TemplateProductPlan templateProductPlan : productPlanTemplateGroup.getTemplateProductPlanList())
			{
				businessProductPlan =
						ProductPlanBAD.createBusinessProductPlan(templateProductPlan, locationId,
								request.getUserContext());
				insertResponse = getProductPlanDAC().insertBusinessProductPlan(businessProductPlan);

				if (insertResponse.isInError())
				{
					response.merge(insertResponse);
					return;
				}

				response.addResult(businessProductPlan);
			}
		}
	}

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
	 * Get the implementation of the {@link IProductPlanTemplateGroupDAC} to use. Injected by Spring.
	 *
	 * @return The implementation of the {@link IProductPlanTemplateGroupDAC}.
	 */
	public IProductPlanTemplateGroupDAC getProductPlanTemplateGroupDAC()
	{
		return productPlanTemplateGroupDAC;
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
	 * Set the implementation of the {@link IProductPlanTemplateGroupDAC}. Injected by Spring.
	 *
	 * @param productPlanTemplateGroupDAC The implementation of the {@link IProductPlanTemplateGroupDAC} interface.
	 */
	public void setProductPlanTemplateGroupDAC(IProductPlanTemplateGroupDAC productPlanTemplateGroupDAC)
	{
		this.productPlanTemplateGroupDAC = productPlanTemplateGroupDAC;
	}

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
	@Override
	public InternalResultsResponse<BusinessProductPlan> createBusinessProductPlans(
			ProductPlanTemplateGroupCreateRequest request)
	{
		InternalResultsResponse<BusinessProductPlan> response = new InternalResultsResponse<BusinessProductPlan>();
		createBusinessProductPlan(request, response);
		return response;
	}

	/**
	 * Delete product plan template group in the SendSolv system.
	 *
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to delete.
	 * @return The {@link InternalResponse} containing information about whether the delete was successful or not.
	 */
	@Override
	public InternalResponse deleteProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		return getProductPlanTemplateGroupDAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Fetch a product plan template group by id in the SendSolv system.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing the {@link ProductPlanTemplateGroup} if found by the id.
	 *         Also contains information about whether the fetch was successful or not.
	 */
	@Override
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupById(FetchByIdRequest request)
	{
		return getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupById(request.getId());
	}

	/**
	 * Fetch a product plan template group by request in the SendSolv system.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link ProductPlanTemplateGroup} that were
	 *         found with the matching criteria. Also contains information about whether the fetch was successful or
	 *         not.
	 */
	@Override
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupByRequest(
			ProductPlanTemplateGroupInquiryRequest request)
	{
		return getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
	}

	/**
	 * Insert product plan template group in the SendSolv system.
	 *
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to insert.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	@Override
	public InternalResponse insertProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		return getProductPlanTemplateGroupDAC().insertProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Update product plan template group.
	 *
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to update.
	 * @return The {@link InternalResponse} containing information about whether the update was successful or not.
	 */
	@Override
	public InternalResponse updateProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		return getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
	}
}
