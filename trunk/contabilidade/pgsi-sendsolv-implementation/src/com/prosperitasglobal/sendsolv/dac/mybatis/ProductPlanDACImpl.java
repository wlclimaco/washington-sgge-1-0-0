package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IProductPlanDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.FeeTier;
import com.prosperitasglobal.sendsolv.model.PlanCategory;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.ProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ProductPlanDACImpl.
 */
public class ProductPlanDACImpl extends SqlSessionDaoSupport implements IProductPlanDAC
{
	/*
	 * Spring Injected Properties
	 */
	/** The valid sort fields for a business product plan inquiry. Will be injected by Spring. */
	private Map<String, String> businessProductPlanInquiryValidSortFields;
	/** The valid sort fields for a template product plan inquiry. Will be injected by Spring. */
	private Map<String, String> templateProductPlanInquiryValidSortFields;

	/*
	 * Constants
	 */

	/*
	 * BusinessProductPlanMap name space
	 */
	private static final String BUSINESS_PRODUCT_PLAN_NAMESPACE = "BusinessProductPlanMap.";
	private static final String BUSINESS_PRODUCT_PLAN_STMT_DELETE = BUSINESS_PRODUCT_PLAN_NAMESPACE
			+ "deleteBusinessProductPlan";
	private static final String BUSINESS_PRODUCT_PLAN_STMT_INSERT = BUSINESS_PRODUCT_PLAN_NAMESPACE
			+ "insertBusinessProductPlan";
	private static final String BUSINESS_PRODUCT_PLAN_STMT_FETCH_BY_ID = BUSINESS_PRODUCT_PLAN_NAMESPACE
			+ "fetchBusinessProductPlanById";
	private static final String BUSINESS_PRODUCT_PLAN_STMT_FETCH_BY_REQUEST = BUSINESS_PRODUCT_PLAN_NAMESPACE
			+ "fetchBusinessProductPlanByRequest";
	private static final String BUSINESS_PRODUCT_PLAN_STMT_FETCH_ROW_COUNT = BUSINESS_PRODUCT_PLAN_NAMESPACE
			+ "fetchBusinessProductPlanRowCount";
	private static final String BUSINESS_PRODUCT_PLAN_STMT_FETCH_VERSION = BUSINESS_PRODUCT_PLAN_NAMESPACE
			+ "fetchBusinessProductPlanVersionNumber";
	private static final String BUSINESS_PRODUCT_PLAN_STMT_UPDATE = BUSINESS_PRODUCT_PLAN_NAMESPACE
			+ "updateBusinessProductPlan";

	/*
	 * FeeTierMap name space
	 */
	private static final String FEE_TIER_NAMESPACE = "FeeTierMap.";
	private static final String FEE_TIER_STMT_DELETE = FEE_TIER_NAMESPACE + "deleteFeeTier";
	private static final String FEE_TIER_STMT_FETCH_VERSION = FEE_TIER_NAMESPACE + "fetchFeeTierVersionNumber";
	private static final String FEE_TIER_STMT_INSERT = FEE_TIER_NAMESPACE + "insertFeeTier";
	private static final String FEE_TIER_STMT_UPDATE = FEE_TIER_NAMESPACE + "updateFeeTier";

	/*
	 * PlanCategoryMap name space
	 */
	private static final String PLAN_CATEGORY_NAMESPACE = "PlanCategoryMap.";
	private static final String PLAN_CATEGORY_STMT_DELETE = PLAN_CATEGORY_NAMESPACE + "deletePlanCategory";
	private static final String PLAN_CATEGORY_STMT_FETCH_VERSION = PLAN_CATEGORY_NAMESPACE
			+ "fetchPlanCategoryVersionNumber";
	private static final String PLAN_CATEGORY_STMT_INSERT = PLAN_CATEGORY_NAMESPACE + "insertPlanCategory";
	private static final String PLAN_CATEGORY_STMT_UPDATE = PLAN_CATEGORY_NAMESPACE + "updatePlanCategory";

	/*
	 * ProductMap name space
	 */
	private static final String PRODUCT_NAMESPACE = "ProductMap.";
	private static final String PRODUCT_STMT_DELETE = PRODUCT_NAMESPACE + "deleteProduct";
	private static final String PRODUCT_STMT_INSERT = PRODUCT_NAMESPACE + "insertProduct";
	private static final String PRODUCT_STMT_FETCH_BY_ID = PRODUCT_NAMESPACE + "fetchProductById";
	private static final String PRODUCT_STMT_FETCH_VERSION = PRODUCT_NAMESPACE + "fetchProductVersionNumber";
	private static final String PRODUCT_STMT_UPDATE = PRODUCT_NAMESPACE + "updateProduct";

	/*
	 * ProductPlanApplicabilityMap name space
	 */
	private static final String PRODUCT_PLAN_APPLICABILITY_NAMESPACE = "ProductPlanApplicabilityMap.";
	private static final String PRODUCT_PLAN_APPLICABILITY_STMT_DELETE = PRODUCT_PLAN_APPLICABILITY_NAMESPACE
			+ "deleteProductPlanApplicability";
	private static final String PRODUCT_PLAN_APPLICABILITY_STMT_FETCH_VERSION = PRODUCT_PLAN_APPLICABILITY_NAMESPACE
			+ "fetchProductPlanApplicabilityVersionNumber";
	private static final String PRODUCT_PLAN_APPLICABILITY_STMT_INSERT = PRODUCT_PLAN_APPLICABILITY_NAMESPACE
			+ "insertProductPlanApplicability";
	private static final String PRODUCT_PLAN_APPLICABILITY_STMT_UPDATE = PRODUCT_PLAN_APPLICABILITY_NAMESPACE
			+ "updateProductPlanApplicability";

	/*
	 * TemplateProductPlanMap name space
	 */
	private static final String TEMPLATE_PRODUCT_PLAN_NAMESPACE = "TemplateProductPlanMap.";
	private static final String TEMPLATE_PRODUCT_PLAN_STMT_DELETE = TEMPLATE_PRODUCT_PLAN_NAMESPACE
			+ "deleteTemplateProductPlan";
	private static final String TEMPLATE_PRODUCT_PLAN_STMT_INSERT = TEMPLATE_PRODUCT_PLAN_NAMESPACE
			+ "insertTemplateProductPlan";
	private static final String TEMPLATE_PRODUCT_PLAN_STMT_FETCH_BY_ID = TEMPLATE_PRODUCT_PLAN_NAMESPACE
			+ "fetchTemplateProductPlanById";
	private static final String TEMPLATE_PRODUCT_PLAN_STMT_FETCH_BY_REQUEST = TEMPLATE_PRODUCT_PLAN_NAMESPACE
			+ "fetchTemplateProductPlanByRequest";
	private static final String TEMPLATE_PRODUCT_PLAN_STMT_FETCH_ROW_COUNT = TEMPLATE_PRODUCT_PLAN_NAMESPACE
			+ "fetchTemplateProductPlanRowCount";
	private static final String TEMPLATE_PRODUCT_PLAN_STMT_FETCH_VERSION = TEMPLATE_PRODUCT_PLAN_NAMESPACE
			+ "fetchTemplateProductPlanVersionNumber";
	private static final String TEMPLATE_PRODUCT_PLAN_STMT_UPDATE = TEMPLATE_PRODUCT_PLAN_NAMESPACE
			+ "updateTemplateProductPlan";

	/**
	 * Delete a {@link FeeTier} object.
	 *
	 * @param feeTier The {@link FeeTier} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the delete. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful deletes were performed.
	 */
	private int deleteFeeTier(FeeTier feeTier, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemoveOL(getSqlSession(), FEE_TIER_STMT_DELETE, feeTier,
						FEE_TIER_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			feeTier.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Delete a {@link PlanCategory} object.
	 *
	 * @param planCategory The {@link PlanCategory} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the delete. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful deletes were performed.
	 */
	private int deletePlanCategory(PlanCategory planCategory, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemoveOL(getSqlSession(), PLAN_CATEGORY_STMT_DELETE, planCategory,
						PLAN_CATEGORY_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			planCategory.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Delete a {@link ProductPlanApplicability} object.
	 *
	 * @param productPlanApplicability The {@link ProductPlanApplicability} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the delete. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful deletes were performed.
	 */
	private int deleteProductPlanApplicability(ProductPlanApplicability productPlanApplicability,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemoveOL(getSqlSession(), PRODUCT_PLAN_APPLICABILITY_STMT_DELETE,
						productPlanApplicability, PRODUCT_PLAN_APPLICABILITY_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			productPlanApplicability.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link FeeTier} object.
	 *
	 * @param feeTier The {@link FeeTier} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertFeeTier(FeeTier feeTier, InternalResponse response)
	{
		int count = QATMyBatisDacHelper.doInsert(getSqlSession(), FEE_TIER_STMT_INSERT, feeTier, response);

		if (count == 1)
		{
			feeTier.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link PlanCategory} object.
	 *
	 * @param planCategory The {@link PlanCategory} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertPlanCategory(PlanCategory planCategory, InternalResponse response)
	{
		int count = QATMyBatisDacHelper.doInsert(getSqlSession(), PLAN_CATEGORY_STMT_INSERT, planCategory, response);

		if (count == 1)
		{
			planCategory.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link ProductPlanApplicability} object.
	 *
	 * @param productPlanApplicability The {@link ProductPlanApplicability} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertProductPlanApplicability(ProductPlanApplicability productPlanApplicability,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doInsert(getSqlSession(), PRODUCT_PLAN_APPLICABILITY_STMT_INSERT,
						productPlanApplicability, response);

		if (count == 1)
		{
			productPlanApplicability.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Maintain business product plan associations.
	 *
	 * @param businessProductPlan The business product plan.
	 * @param response The response.
	 * @return The number of insert/update/delete performed.
	 */
	private int maintainBusinessProductPlanAssociations(BusinessProductPlan businessProductPlan,
			InternalResponse response)
	{
		// First Maintain the Product Plan associations.
		int count = maintainProductPlanAssociations(businessProductPlan, response);

		return count;
	}

	/**
	 * Maintain plan category associations.
	 *
	 * @param planCategory The plan category.
	 * @param response The response.
	 * @return The number of inserts performed.
	 */
	private int maintainPlanCategoryAssociations(PlanCategory planCategory, InternalResponse response)
	{
		int count = 0;

		// First Maintain Fee Tier
		if (!ValidationUtil.isNullOrEmpty(planCategory.getFeeTierList()))
		{
			// For Each Fee Tier...
			for (FeeTier feeTier : planCategory.getFeeTierList())
			{
				// Make sure we set the parent key
				feeTier.setPlanCategoryId(planCategory.getId());

				switch (feeTier.getModelAction())
				{
					case UPDATE:
						count += updateFeeTier(feeTier, response);
						break;
					case INSERT:
						count += insertFeeTier(feeTier, response);
						break;
					case DELETE:
						count += deleteFeeTier(feeTier, response);
						break;
					default:
						break;
				}
			}
		}

		return count;
	}

	/**
	 * Maintain product plan associations.
	 *
	 * @param productPlan The product plan.
	 * @param response The response.
	 * @return The number of inserts performed.
	 */
	private int maintainProductPlanAssociations(ProductPlan productPlan, InternalResponse response)
	{
		int count = 0;

		// First Maintain Plan Category
		if (!ValidationUtil.isNullOrEmpty(productPlan.getPlanCategoryList()))
		{
			// For Each Plan Category...
			for (PlanCategory planCategory : productPlan.getPlanCategoryList())
			{
				// Make sure we set the parent key
				planCategory.setProductPlanId(productPlan.getId());

				switch (planCategory.getModelAction())
				{
					case UPDATE:
						count += updatePlanCategory(planCategory, response);
						break;
					case INSERT:
						count += insertPlanCategory(planCategory, response);
						break;
					case DELETE:
						count += deletePlanCategory(planCategory, response);
						break;
					default:
						break;
				}

				count += maintainPlanCategoryAssociations(planCategory, response);
			}
		}

		// Second Maintain Product Plan Applicability
		if (!ValidationUtil.isNullOrEmpty(productPlan.getProductPlanApplicabilityList()))
		{
			// For Each Product Plan Applicability...
			for (ProductPlanApplicability productPlanApplicability : productPlan.getProductPlanApplicabilityList())
			{
				// Make sure we set the parent key
				productPlanApplicability.setProductPlanId(productPlan.getId());

				switch (productPlanApplicability.getModelAction())
				{
					case UPDATE:
						count += updateProductPlanApplicability(productPlanApplicability, response);
						break;
					case INSERT:
						count += insertProductPlanApplicability(productPlanApplicability, response);
						break;
					case DELETE:
						count += deleteProductPlanApplicability(productPlanApplicability, response);
						break;
					default:
						break;
				}
			}
		}

		return count;
	}

	/**
	 * Maintain template product plan associations.
	 *
	 * @param templateProductPlan The template product plan.
	 * @param response The response.
	 * @return The number of insert/update/delete performed.
	 */
	private int maintainTemplateProductPlanAssociations(TemplateProductPlan templateProductPlan,
			InternalResponse response)
	{
		// First Maintain the Product Plan associations.
		int count = maintainProductPlanAssociations(templateProductPlan, response);

		return count;
	}

	/**
	 * Update a {@link FeeTier} object.
	 *
	 * @param feeTier The {@link FeeTier} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updateFeeTier(FeeTier feeTier, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), FEE_TIER_STMT_UPDATE, feeTier,
						FEE_TIER_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			feeTier.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Update a {@link PlanCategory} object.
	 *
	 * @param planCategory The {@link PlanCategory} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updatePlanCategory(PlanCategory planCategory, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PLAN_CATEGORY_STMT_UPDATE, planCategory,
						PLAN_CATEGORY_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			planCategory.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Update a {@link ProductPlanApplicability} object.
	 *
	 * @param productPlanApplicability The {@link ProductPlanApplicability} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updateProductPlanApplicability(ProductPlanApplicability productPlanApplicability,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PRODUCT_PLAN_APPLICABILITY_STMT_UPDATE,
						productPlanApplicability, PRODUCT_PLAN_APPLICABILITY_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			productPlanApplicability.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Get the valid sort fields for the business product plan inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the business product plan inquiry.
	 */
	public Map<String, String> getBusinessProductPlanInquiryValidSortFields()
	{
		return businessProductPlanInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the business product plan inquiry. Attribute injected by Spring.
	 *
	 * @param businessProductPlanInquiryValidSortFields The valid sort fields for the business product plan inquiry to
	 *            set.
	 */
	public void setBusinessProductPlanInquiryValidSortFields(
			Map<String, String> businessProductPlanInquiryValidSortFields)
	{
		this.businessProductPlanInquiryValidSortFields = businessProductPlanInquiryValidSortFields;
	}

	/**
	 * Get the valid sort fields for the template product plan inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the template product plan inquiry.
	 */
	public Map<String, String> getTemplateProductPlanInquiryValidSortFields()
	{
		return templateProductPlanInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the template product plan inquiry. Attribute injected by Spring.
	 *
	 * @param templateProductPlanInquiryValidSortFields The valid sort fields for the template product plan inquiry to
	 *            set.
	 */
	public void setTemplateProductPlanInquiryValidSortFields(
			Map<String, String> templateProductPlanInquiryValidSortFields)
	{
		this.templateProductPlanInquiryValidSortFields = templateProductPlanInquiryValidSortFields;
	}

	/**
	 * Delete a {@link BusinessProductPlan} from the SendSolv system, along with all of its composite associated
	 * objects.
	 * <p>
	 * The delete action will enforce the Optimistic Locking of the row so that an incorrect delete isn't performed. If
	 * the correct version is being deleted, it will delete the {@link BusinessProductPlan} and all of its associated
	 * tables. A cascade delete has been setup on the database.
	 *
	 * @param businessProductPlan The business product plan to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemoveOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse deleteBusinessProductPlan(BusinessProductPlan businessProductPlan)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), BUSINESS_PRODUCT_PLAN_STMT_DELETE, businessProductPlan,
				BUSINESS_PRODUCT_PLAN_STMT_FETCH_VERSION, response);
		return response;
	}

	/**
	 * Delete only the {@link Product} from the SendSolv system.
	 * <p>
	 * The delete action will enforce the Optimistic Locking of the row so that an incorrect delete isn't performed. If
	 * the correct version is being deleted, it will delete the {@link Product}. Only the {@link Product} will be
	 * deleted and any associates will remain on the database.
	 *
	 * @param product The product to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemoveOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse deleteProduct(Product product)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), PRODUCT_STMT_DELETE, product, PRODUCT_STMT_FETCH_VERSION,
				response);
		return response;
	}

	/**
	 * Delete a {@link TemplateProductPlan} from the SendSolv system, along with all of its composite associated
	 * objects.
	 * <p>
	 * The delete action will enforce the Optimistic Locking of the row so that an incorrect delete isn't performed. If
	 * the correct version is being deleted, it will delete the {@link TemplateProductPlan} and all of its associated
	 * tables. A cascade delete has been setup on the database.
	 *
	 * @param templateProductPlan The template product plan to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemoveOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse deleteTemplateProductPlan(TemplateProductPlan templateProductPlan)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), TEMPLATE_PRODUCT_PLAN_STMT_DELETE, templateProductPlan,
				TEMPLATE_PRODUCT_PLAN_STMT_FETCH_VERSION, response);
		return response;
	}

	/**
	 * Fetch a business product plan by id.
	 *
	 * @param id The id of the business product plan to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link BusinessProductPlan} along with
	 *         information about the success/failure of the fetch.
	 * @see QATMyBatisDacHelper#doQueryForList(org.apache.ibatis.session.SqlSession, String, Object,
	 *      InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanById(Integer id)
	{
		InternalResultsResponse<BusinessProductPlan> response = new InternalResultsResponse<BusinessProductPlan>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), BUSINESS_PRODUCT_PLAN_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/**
	 * Fetch all business product plans matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link BusinessProductPlan}'s along with
	 *         information about the success/failure of the fetch.
	 * @see PagedResultsDACD#fetchObjectsByRequest(org.apache.ibatis.session.SqlSession,
	 *      com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest, String, String, InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<BusinessProductPlan> fetchBusinessProductPlanByRequest(
			BusinessProductPlanInquiryRequest request)
	{
		InternalResultsResponse<BusinessProductPlan> response = new InternalResultsResponse<BusinessProductPlan>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getBusinessProductPlanInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, BUSINESS_PRODUCT_PLAN_STMT_FETCH_ROW_COUNT,
				BUSINESS_PRODUCT_PLAN_STMT_FETCH_BY_REQUEST, response);

		return response;
	}

	/**
	 * Fetch a product by id.
	 *
	 * @param id The id of the product to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link Product} along with
	 *         information about the success/failure of the fetch.
	 * @see QATMyBatisDacHelper#doQueryForList(org.apache.ibatis.session.SqlSession, String, Object,
	 *      InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<Product> fetchProductById(Integer id)
	{
		InternalResultsResponse<Product> response = new InternalResultsResponse<Product>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PRODUCT_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/**
	 * Fetch a template product plan by id.
	 *
	 * @param id The id of the template product plan to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link TemplateProductPlan} along with
	 *         information about the success/failure of the fetch.
	 * @see QATMyBatisDacHelper#doQueryForList(org.apache.ibatis.session.SqlSession, String, Object,
	 *      InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanById(Integer id)
	{
		InternalResultsResponse<TemplateProductPlan> response = new InternalResultsResponse<TemplateProductPlan>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), TEMPLATE_PRODUCT_PLAN_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/**
	 * Fetch all template product plans matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link TemplateProductPlan}'s along with
	 *         information about the success/failure of the fetch.
	 * @see PagedResultsDACD#fetchObjectsByRequest(org.apache.ibatis.session.SqlSession,
	 *      com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest, String, String, InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<TemplateProductPlan> fetchTemplateProductPlanByRequest(
			TemplateProductPlanInquiryRequest request)
	{
		InternalResultsResponse<TemplateProductPlan> response = new InternalResultsResponse<TemplateProductPlan>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getTemplateProductPlanInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, TEMPLATE_PRODUCT_PLAN_STMT_FETCH_ROW_COUNT,
				TEMPLATE_PRODUCT_PLAN_STMT_FETCH_BY_REQUEST, response);

		return response;
	}

	/**
	 * Insert a {@link BusinessProductPlan} into the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. If the model action is not marked as an insert, the system will attempt to
	 * perform the action specified. This could lead to an abnormal database method being applied to the object. A worst
	 * case here, is that the model action is not marked at all, and thus the associated object won't be inserted.
	 * <p>
	 * The insert will first attempt the insert of the {@link BusinessProductPlan}. If that insert is successful, then
	 * all of the composite associations will be looked at and have their model action performed. Before the action is
	 * performed on them, the parent key will be updated to keep them in sync. If an error is taken during any of the
	 * actions, the method will stop and return with the error. It is assumed that the transaction is being maintained
	 * elsewhere, and that it will be rolled back upon getting an error.
	 *
	 * @param businessProductPlan The business product plan to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertBusinessProductPlan(BusinessProductPlan businessProductPlan)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), BUSINESS_PRODUCT_PLAN_STMT_INSERT, businessProductPlan,
						response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainBusinessProductPlanAssociations(businessProductPlan, response);

		if (!response.isInError() && (insertCount > 0))
		{
			businessProductPlan.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Insert only the {@link Product} into the SendSolv system.
	 *
	 * @param product The product to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertProduct(Product product)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// If successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PRODUCT_STMT_INSERT, product, response);

		if (!response.isInError() && (insertCount > 0))
		{
			product.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Insert a {@link TemplateProductPlan} into the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. If the model action is not marked as an insert, the system will attempt to
	 * perform the action specified. This could lead to an abnormal database method being applied to the object. A worst
	 * case here, is that the model action is not marked at all, and thus the associated object won't be inserted.
	 * <p>
	 * The insert will first attempt the insert of the {@link BusinessProductPlan}. If that insert is successful, then
	 * all of the composite associations will be looked at and have their model action performed. Before the action is
	 * performed on them, the parent key will be updated to keep them in sync. If an error is taken during any of the
	 * actions, the method will stop and return with the error. It is assumed that the transaction is being maintained
	 * elsewhere, and that it will be rolled back upon getting an error.
	 *
	 * @param templateProductPlan The template product plan to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertTemplateProductPlan(TemplateProductPlan templateProductPlan)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), TEMPLATE_PRODUCT_PLAN_STMT_INSERT, templateProductPlan,
						response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainTemplateProductPlanAssociations(templateProductPlan, response);

		if (!response.isInError() && (insertCount > 0))
		{
			templateProductPlan.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a {@link BusinessProductPlan} in the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. The system will attempt to perform the action specified. This could lead to an
	 * abnormal database method being applied to the object. Make sure that the correct model action has been specified
	 * for all of the composite associated objects.
	 * <p>
	 * The update action will enforce the Optimistic Locking of the row, along with Optimistic locking on all of the
	 * composite associated objects. Pay close attention to the version number of the object that has been marked for an
	 * action.
	 *
	 * @param businessProductPlan The business product plan to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdateOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse updateBusinessProductPlan(BusinessProductPlan businessProductPlan)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		// First update the root
		updateCount =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), BUSINESS_PRODUCT_PLAN_STMT_UPDATE, businessProductPlan,
						BUSINESS_PRODUCT_PLAN_STMT_FETCH_VERSION, response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainBusinessProductPlanAssociations(businessProductPlan, response);

		if (!response.isInError() && (updateCount > 0))
		{
			businessProductPlan.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a {@link Product} in the SendSolv system.
	 * <p>
	 * No associates will be updated and the update action will enforce the Optimistic Locking of the row.
	 *
	 * @param product The product to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdateOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse updateProduct(Product product)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		// If successful the unique-id will be populated back into the object.
		updateCount =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PRODUCT_STMT_UPDATE, product,
						PRODUCT_STMT_FETCH_VERSION, response);

		if (!response.isInError() && (updateCount > 0))
		{
			product.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a {@link TemplateProductPlan} in the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. The system will attempt to perform the action specified. This could lead to an
	 * abnormal database method being applied to the object. Make sure that the correct model action has been specified
	 * for all of the composite associated objects.
	 * <p>
	 * The update action will enforce the Optimistic Locking of the row, along with Optimistic locking on all of the
	 * composite associated objects. Pay close attention to the version number of the object that has been marked for an
	 * action.
	 *
	 * @param templateProductPlan The template product plan to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdateOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse updateTemplateProductPlan(TemplateProductPlan templateProductPlan)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		// First update the root
		updateCount =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), TEMPLATE_PRODUCT_PLAN_STMT_UPDATE, templateProductPlan,
						TEMPLATE_PRODUCT_PLAN_STMT_FETCH_VERSION, response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainTemplateProductPlanAssociations(templateProductPlan, response);

		if (!response.isInError() && (updateCount > 0))
		{
			templateProductPlan.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}
}
