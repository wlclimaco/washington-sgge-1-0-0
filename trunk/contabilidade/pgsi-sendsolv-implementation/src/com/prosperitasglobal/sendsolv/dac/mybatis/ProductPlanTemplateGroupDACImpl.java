package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IProductPlanDAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanTemplateGroupDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ProductPlanTemplateGroupDACImpl.
 */
public class ProductPlanTemplateGroupDACImpl extends SqlSessionDaoSupport implements IProductPlanTemplateGroupDAC
{
	/*
	 * Spring Injected Properties
	 */

	/** The product plan DAC. Will be injected by Spring. */
	private IProductPlanDAC productPlanDAC;
	/** The valid sort fields for a product plan template group inquiry. Will be injected by Spring. */
	private Map<String, String> productPlanTemplateGroupInquiryValidSortFields;

	/*
	 * Constants
	 */

	/*
	 * ProductPlanTemplateGroupMap name space
	 */
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE = "ProductPlanTemplateGroupMap.";
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_STMT_DELETE = PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE
			+ "deleteProductPlanTemplateGroup";
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_BY_ID = PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE
			+ "fetchProductPlanTemplateGroupById";
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_BY_REQUEST =
			PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE + "fetchProductPlanTemplateGroupByRequest";
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_ROW_COUNT =
			PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE + "fetchProductPlanTemplateGroupRowCount";
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_VERSION = PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE
			+ "fetchProductPlanTemplateGroupVersionNumber";
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_STMT_INSERT = PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE
			+ "insertProductPlanTemplateGroup";
	private static final String PRODUCT_PLAN_TEMPLATE_GROUP_STMT_UPDATE = PRODUCT_PLAN_TEMPLATE_GROUP_NAMESPACE
			+ "updateProductPlanTemplateGroup";

	/**
	 * Delete a template product plan.
	 *
	 * @param templateProductPlan The template product plan to delete.
	 * @param response The current response for the transaction.
	 * @return The number of successful deletes.
	 */
	private int deleteTemplateProductPlan(TemplateProductPlan templateProductPlan, InternalResponse response)
	{
		int deleteCount = 0;

		InternalResponse templateProductPlanResponse =
				getProductPlanDAC().deleteTemplateProductPlan(templateProductPlan);

		if (templateProductPlanResponse.isInError())
		{
			response.merge(templateProductPlanResponse);
		}
		else
		{
			deleteCount = 1;
		}

		return deleteCount;
	}

	/**
	 * Insert a template product plan.
	 *
	 * @param templateProductPlan The template product plan to insert.
	 * @param response The current response for the transaction.
	 * @return The number of successful inserts.
	 */
	private int insertTemplateProductPlan(TemplateProductPlan templateProductPlan, InternalResponse response)
	{
		int insertCount = 0;

		InternalResponse templateProductPlanResponse =
				getProductPlanDAC().insertTemplateProductPlan(templateProductPlan);

		if (templateProductPlanResponse.isInError())
		{
			response.merge(templateProductPlanResponse);
		}
		else
		{
			insertCount = 1;
		}

		return insertCount;
	}

	/**
	 * Maintain product plan template group associations.
	 *
	 * @param productPlan The product plan.
	 * @param response The response.
	 * @return The number of inserts performed.
	 */
	private int maintainProductPlanTemplateGroupAssociations(ProductPlanTemplateGroup productPlanTemplateGroup,
			InternalResponse response)
	{
		int count = 0;

		// First Maintain Template Product Plan
		if (!ValidationUtil.isNullOrEmpty(productPlanTemplateGroup.getTemplateProductPlanList()))
		{
			for (TemplateProductPlan templateProductPlan : productPlanTemplateGroup.getTemplateProductPlanList())
			{
				if (productPlanTemplateGroup.getModelAction() == PersistanceActionEnum.DELETE)
				{
					// We need to break the association the children. So remove the parent key.
					templateProductPlan.setProductPlanTemplateGroupId(null);
				}
				else
				{
					// Make sure we set the parent key
					templateProductPlan.setProductPlanTemplateGroupId(productPlanTemplateGroup.getId());
				}

				switch (templateProductPlan.getModelAction())
				{
					case UPDATE:
						count += updateTemplateProductPlan(templateProductPlan, response);
						break;
					case INSERT:
						count += insertTemplateProductPlan(templateProductPlan, response);
						break;
					case DELETE:
						count += deleteTemplateProductPlan(templateProductPlan, response);
						break;
					default:

						/*
						 * If parent is being deleted, and the children have no action, we still need to break the
						 * association. Call the update method to do this.
						 */
						if (productPlanTemplateGroup.getModelAction() == PersistanceActionEnum.DELETE)
						{
							// If an id exists, implies it already exists on the database. Needs to get updated.
							if (!ValidationUtil.isNull(templateProductPlan.getId()))
							{
								templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
								count += updateTemplateProductPlan(templateProductPlan, response);
							}
						}

						break;
				}
			}
		}

		return count;
	}

	/**
	 * Update a template product plan.
	 *
	 * @param templateProductPlan The template product plan to update.
	 * @param response The current response for the transaction.
	 * @return The number of successful updates.
	 */
	private int updateTemplateProductPlan(TemplateProductPlan templateProductPlan, InternalResponse response)
	{
		int updateCount = 0;

		InternalResponse templateProductPlanResponse =
				getProductPlanDAC().updateTemplateProductPlan(templateProductPlan);

		if (templateProductPlanResponse.isInError())
		{
			response.merge(templateProductPlanResponse);
		}
		else
		{
			updateCount = 1;
		}

		return updateCount;
	}

	/**
	 * Get the product plan DAC. Attribute injected by Spring.
	 *
	 * @return The product plan DAC.
	 */
	public IProductPlanDAC getProductPlanDAC()
	{
		return productPlanDAC;
	}

	/**
	 * Set the product plan DAC. Attribute injected by Spring.
	 *
	 * @param productPlanDAC The product plan DAC to set.
	 */
	public void setProductPlanDAC(IProductPlanDAC productPlanDAC)
	{
		this.productPlanDAC = productPlanDAC;
	}

	/**
	 * Get the valid sort fields for the product plan template group inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the product plan template group inquiry.
	 */
	public Map<String, String> getProductPlanTemplateGroupInquiryValidSortFields()
	{
		return productPlanTemplateGroupInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the product plan template group inquiry. Attribute injected by Spring.
	 *
	 * @param productPlanTemplateGroupInquiryValidSortFields The valid sort fields for the product plan template group
	 *            inquiry to set.
	 */
	public void setProductPlanTemplateGroupInquiryValidSortFields(
			Map<String, String> productPlanTemplateGroupInquiryValidSortFields)
	{
		this.productPlanTemplateGroupInquiryValidSortFields = productPlanTemplateGroupInquiryValidSortFields;
	}

	/**
	 * Delete a {@link ProductPlanTemplateGroup} from the SendSolv system.
	 * <p>
	 * No composite associated objects will be deleted unless the model action for them has been marked. Be careful with
	 * the associates. The system will attempt to perform the action specified. This could lead to an abnormal database
	 * method being applied to the object. Make sure that the correct model action has been specified for all of the
	 * composite associated objects.
	 * <p>
	 * The delete action will enforce the Optimistic Locking of the row so that an incorrect delete isn't performed. If
	 * the correct version is being deleted, it will delete the {@link ProductPlanTemplateGroup}.
	 *
	 * @param productPlanTemplateGroup The product plan template group to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemoveOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse deleteProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		InternalResultsResponse<ProductPlanTemplateGroup> response =
				new InternalResultsResponse<ProductPlanTemplateGroup>();

		// First process the children nodes
		maintainProductPlanTemplateGroupAssociations(productPlanTemplateGroup, response);

		if (response.isInError())
		{
			return response;
		}

		// Now, we can remove the parent.
		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), PRODUCT_PLAN_TEMPLATE_GROUP_STMT_DELETE,
				productPlanTemplateGroup, PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_VERSION, response);

		return response;
	}

	/**
	 * Fetch a product plan template group by id.
	 *
	 * @param id The id of the product plan template group to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link ProductPlanTemplateGroup} along with
	 *         information about the success/failure of the fetch.
	 * @see QATMyBatisDacHelper#doQueryForList(org.apache.ibatis.session.SqlSession, String, Object,
	 *      InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupById(Integer id)
	{
		InternalResultsResponse<ProductPlanTemplateGroup> response =
				new InternalResultsResponse<ProductPlanTemplateGroup>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/**
	 * Fetch all product plan template groups matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link ProductPlanTemplateGroup}'s along
	 *         with information about the success/failure of the fetch.
	 * @see PagedResultsDACD#fetchObjectsByRequest(org.apache.ibatis.session.SqlSession, PagedInquiryRequest, String,
	 *      String, InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<ProductPlanTemplateGroup> fetchProductPlanTemplateGroupByRequest(
			PagedInquiryRequest request)
			{
		InternalResultsResponse<ProductPlanTemplateGroup> response =
				new InternalResultsResponse<ProductPlanTemplateGroup>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getProductPlanTemplateGroupInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request,
				PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_ROW_COUNT, PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_BY_REQUEST,
				response);

		return response;
			}

	/**
	 * Insert a {@link ProductPlanTemplateGroup} into the SendSolv system, along with all of its associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. If the model action is not marked as an insert, the system will attempt to
	 * perform the action specified. This could lead to an abnormal database method being applied to the object. A worst
	 * case here, is that the model action is not marked at all, and thus the associated object won't be inserted.
	 * <p>
	 * The insert will first attempt the insert of the {@link ProductPlanTemplateGroup}. If that insert is successful,
	 * then all of the composite associations will be looked at and have their model action performed. Before the action
	 * is performed on them, the parent key will be updated to keep them in sync. If an error is taken during any of the
	 * actions, the method will stop and return with the error. It is assumed that the transaction is being maintained
	 * elsewhere, and that it will be rolled back upon getting an error.
	 *
	 * @param productPlanTemplateGroup The product plan template group to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), PRODUCT_PLAN_TEMPLATE_GROUP_STMT_INSERT,
						productPlanTemplateGroup, response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainProductPlanTemplateGroupAssociations(productPlanTemplateGroup, response);

		if (!response.isInError() && (insertCount > 0))
		{
			productPlanTemplateGroup.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a {@link ProductPlanTemplateGroup} in the SendSolv system, along with all of its composite associated
	 * objects where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. The system will attempt to perform the action specified. This could lead to an
	 * abnormal database method being applied to the object. Make sure that the correct model action has been specified
	 * for all of the composite associated objects.
	 * <p>
	 * The update action will enforce the Optimistic Locking of the row, along with Optimistic locking on all of the
	 * composite associated objects. Pay close attention to the version number of the object that has been marked for an
	 * action.
	 *
	 * @param productPlanTemplateGroup The product plan template group to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdateOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse updateProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		// First update the root
		updateCount =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PRODUCT_PLAN_TEMPLATE_GROUP_STMT_UPDATE,
						productPlanTemplateGroup, PRODUCT_PLAN_TEMPLATE_GROUP_STMT_FETCH_VERSION, response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainProductPlanTemplateGroupAssociations(productPlanTemplateGroup, response);

		if (!response.isInError() && (updateCount > 0))
		{
			productPlanTemplateGroup.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}
}
