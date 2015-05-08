package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IProductPlanTemplateGroupBAC;
import com.prosperitasglobal.sendsolv.bai.IProductPlanTemplateGroupBAI;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.ProductPlanTemplateGroupCreateResponse;
import com.prosperitasglobal.sendsolv.model.response.ProductPlanTemplateGroupResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.MaintenanceResponse;
import com.qat.framework.model.response.Response;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * A class that implements methods for working with a Product Plan Template Group area functions.
 */
public class ProductPlanTemplateGroupBAIImpl implements IProductPlanTemplateGroupBAI
{
	/** The product plan template group validation controller. */
	private ValidationController productPlanTemplateGroupValidationController;

	/** The product plan template group inquiry request validation controller. */
	private ValidationController productPlanTemplateGroupInquiryRequestValidationController;

	/** The product plan template group create request validation controller. */
	private ValidationController productPlanTemplateGroupCreateRequestValidationController;

	/** The implementation of the {@link IProductPlanTemplateGroupBAC} interface. */
	private IProductPlanTemplateGroupBAC productPlanTemplateGroupBAC;

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ProductPlanTemplateGroupBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProductPlanTemplateGroupBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	public static final String OL_ERROR = "sendsolv.base.optimistic.locking.error";

	/** The Constant PRODUCT_PLAN_TEMPLATE_GROUP_ID_REQUIRED. */
	public static final String PRODUCT_PLAN_TEMPLATE_GROUP_ID_REQUIRED =
			"sendsolv.base.productplantemplategroupvalidator.id.required";

	/**
	 * Perform the persistence actions for a {@link ProductPlanTemplateGroupMaintenanceRequest}.
	 *
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to perform the action on.
	 * @param modelAction The persistence action to perform.
	 * @return The {@link MaintenanceResponse} containing information about whether the action was successful or not.
	 */
	private InternalResponse doPersistance(ProductPlanTemplateGroup productPlanTemplateGroup,
			PersistanceActionEnum modelAction)
	{
		switch (modelAction)
		{
			case INSERT:
				return getProductPlanTemplateGroupBAC().insertProductPlanTemplateGroup(productPlanTemplateGroup);

			case UPDATE:
				return getProductPlanTemplateGroupBAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);

			case DELETE:
				return getProductPlanTemplateGroupBAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("model action missing for ProductPlanTemplateGroupMaintenanceRequest!");
				}
				break;
		}
		return null;
	}

	/**
	 * Fetch the {@link ProductPlanTemplateGroup} using the paged inquiry.
	 *
	 * @param request The request.
	 * @param response The {@link ProductPlanTemplateGroupResponse} containing the current state of the process.
	 * @return The updated {@link ProductPlanTemplateGroupResponse} containing the next state of the process.
	 */
	private ProductPlanTemplateGroupResponse fetchPaged(ProductPlanTemplateGroupInquiryRequest request,
			ProductPlanTemplateGroupResponse response)
	{
		InternalResultsResponse<ProductPlanTemplateGroup> internalResponse =
				new InternalResultsResponse<ProductPlanTemplateGroup>();

		ValidationContext context =
				new ValidationContext(ProductPlanTemplateGroupInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getProductPlanTemplateGroupInquiryRequestValidationController().validate(context))
		{
			internalResponse = getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (ProductPlanTemplateGroupResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Handle the return from a persistence call.
	 *
	 * @param response The {@link ProductPlanTemplateGroupResponse} containing the current state of the process.
	 * @param internalResponse The {@link InternalResponse} containing information about whether the persistence action
	 *            was successful or not.
	 * @param messages The {@link List} of validation {@link MessageInfo} that might have been set during validation.
	 * @param copyOver If true, and the internalResponse parameter is an instance of InternalResultsResponse AND the
	 *            response parameter is an instance of {@link com.qat.framework.model.response.InquiryResponse}, the
	 *            contents of the InternalResultsResponse will be copied over to the InquiryResponse. Note, there is a
	 *            special method on the InquiryResponse class,
	 *            {@link com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)} that is used
	 *            to perform this copy-over. Make sure the InquiryResponse you are using overrides this method before
	 *            setting this parameter to true.
	 * @return The updated {@link ProductPlanTemplateGroupResponse} containing the next state of the process.
	 */
	private Response handleReturn(Response response, InternalResponse internalResponse, List<MessageInfo> messages,
			boolean copyOver)
	{
		// In the case there was an Optimistic Locking error, add the specific message
		if (!ValidationUtil.isNull(internalResponse) && !ValidationUtil.isNull(internalResponse.getStatus())
				&& Status.OptimisticLockingError.equals(internalResponse.getStatus()))
		{
			messages.add(new MessageInfo(OL_ERROR, MessageSeverity.Error, MessageLevel.Object));
		}

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Process the persistence request.
	 *
	 * @param indicator Used as an indicator during the execution of validators and is used to indicate the context in
	 *            which a given validator should execute.
	 * @param persistType The {@link PersistanceActionEnum} being requested.
	 * @param request The maintenance request containing the {@link ProductPlanTemplateGroup} to perform the action.
	 * @return The {@link MaintenanceResponse} containing information about whether the action was successful or not.
	 */
	private MaintenanceResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			ProductPlanTemplateGroupMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		InternalResponse internalResponse = null;

		// Validate.
		ValidationContext context =
				new ValidationContext(ProductPlanTemplateGroup.class.getSimpleName(),
						request.getProductPlanTemplateGroup(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getProductPlanTemplateGroupValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request.getProductPlanTemplateGroup(), persistType);
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Gets the product plan template group validation controller.
	 *
	 * @return The product plan template group validation controller.
	 */
	public ValidationController getProductPlanTemplateGroupValidationController()
	{
		return productPlanTemplateGroupValidationController;
	}

	/**
	 * Sets the product plan template group validation controller.
	 *
	 * @param productPlanTemplateGroupValidationController The product plan template group validation controller to set.
	 */
	public void setProductPlanTemplateGroupValidationController(
			ValidationController productPlanTemplateGroupValidationController)
	{
		this.productPlanTemplateGroupValidationController = productPlanTemplateGroupValidationController;
	}

	/**
	 * Gets the product plan template group create request validation controller.
	 *
	 * @return The product plan template group create request validation controller.
	 */
	public ValidationController getProductPlanTemplateGroupCreateRequestValidationController()
	{
		return productPlanTemplateGroupCreateRequestValidationController;
	}

	/**
	 * Sets the product plan template group create request validation controller.
	 *
	 * @param productPlanTemplateGroupCreateRequestValidationController The product plan template group create request
	 *            validation controller to set.
	 */
	public void setProductPlanTemplateGroupCreateRequestValidationController(
			ValidationController productPlanTemplateGroupCreateRequestValidationController)
	{
		this.productPlanTemplateGroupCreateRequestValidationController =
				productPlanTemplateGroupCreateRequestValidationController;
	}

	/**
	 * Gets the product plan template group inquiry request validation controller.
	 *
	 * @return The product plan template group inquiry request validation controller.
	 */
	public ValidationController getProductPlanTemplateGroupInquiryRequestValidationController()
	{
		return productPlanTemplateGroupInquiryRequestValidationController;
	}

	/**
	 * Sets the product plan template group inquiry request validation controller.
	 *
	 * @param productPlanTemplateGroupInquiryRequestValidationController The the product plan template group inquiry
	 *            request
	 *            validation controller to set.
	 */
	public void setProductPlanTemplateGroupInquiryRequestValidationController(
			ValidationController productPlanTemplateGroupInquiryRequestValidationController)
	{
		this.productPlanTemplateGroupInquiryRequestValidationController =
				productPlanTemplateGroupInquiryRequestValidationController;
	}

	/**
	 * Gets the implementation of the {@link IProductPlanTemplateGroupBAC} interface.
	 *
	 * @return The implementation of the {@link IProductPlanTemplateGroupBAC} interface.
	 */
	public IProductPlanTemplateGroupBAC getProductPlanTemplateGroupBAC()
	{
		return productPlanTemplateGroupBAC;
	}

	/**
	 * Sets the implementation of the {@link IProductPlanTemplateGroupBAC} interface.
	 *
	 * @param productPlanTemplateGroupBAC The implementation of the {@link IProductPlanTemplateGroupBAC} interface to
	 *            set.
	 */
	public void setProductPlanTemplateGroupBAC(IProductPlanTemplateGroupBAC productPlanTemplateGroupBAC)
	{
		this.productPlanTemplateGroupBAC = productPlanTemplateGroupBAC;
	}

	/**
	 * Create business product plan's from a product plan template group.
	 *
	 * @param request The create request containing the {@link ProductPlanTemplateGroup} to create from.
	 * @return The {@link ProductPlanTemplateGroupCreateResponse} containing information about whether the action was
	 *         successful or not. The {@link List} of {@link com.prosperitasglobal.sendsolv.model.BusinessProductPlan}'s
	 *         will be those that were created from the process.
	 */
	@Override
	public ProductPlanTemplateGroupCreateResponse createBusinessProductPlans(
			ProductPlanTemplateGroupCreateRequest request)
	{
		ProductPlanTemplateGroupCreateResponse response = new ProductPlanTemplateGroupCreateResponse();
		try
		{
			InternalResultsResponse<BusinessProductPlan> internalResultsResponse = null;

			// Validate.
			ValidationContext context =
					new ValidationContext(ProductPlanTemplateGroupCreateRequest.class.getSimpleName(), request,
							ValidationContextIndicator.FETCH);

			if (getProductPlanTemplateGroupCreateRequestValidationController().validate(context))
			{
				internalResultsResponse = getProductPlanTemplateGroupBAC().createBusinessProductPlans(request);
			}

			response =
					(ProductPlanTemplateGroupCreateResponse)handleReturn(response, internalResultsResponse,
							context.getMessages(), true);

			if (response.isOperationSuccess())
			{
				response.getBusinessProductPlanList().addAll(internalResultsResponse.getResultsList());
			}
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delete product plan template group.
	 *
	 * @param request The maintenance request containing the {@link ProductPlanTemplateGroup} to delete.
	 * @return The {@link MaintenanceResponse} containing information about whether the delete was successful or not.
	 */
	@Override
	public MaintenanceResponse deleteProductPlanTemplateGroup(ProductPlanTemplateGroupMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		try
		{
			response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch a product plan template group by id.
	 *
	 * @param request The request.
	 * @return The {@link ProductPlanTemplateGroupResponse} containing the {@link List} of
	 *         {@link ProductPlanTemplateGroup}'s that contained the id. Also contains information about whether the
	 *         action was successful or not. If the action was not successful, no {@link ProductPlanTemplateGroup} will
	 *         be contained in the response.
	 */
	@Override
	public ProductPlanTemplateGroupResponse fetchProductPlanTemplateGroupById(FetchByIdRequest request)
	{
		ProductPlanTemplateGroupResponse response = new ProductPlanTemplateGroupResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()))
			{
				internalResponse.addFieldErrorMessage(PRODUCT_PLAN_TEMPLATE_GROUP_ID_REQUIRED);
			}
			else
			{
				internalResponse = getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch all product plan template groups matching the information contained in the criteria.
	 *
	 * @param request The request.
	 * @return The {@link ProductPlanTemplateGroupResponse} containing the {@link List} of
	 *         {@link ProductPlanTemplateGroup}'s that matched the criteria. Also contains information about whether the
	 *         action was successful or not. If the action was not successful, no {@link ProductPlanTemplateGroup} will
	 *         be contained in the response.
	 */
	@Override
	public ProductPlanTemplateGroupResponse fetchProductPlanTemplateGroupByRequest(
			ProductPlanTemplateGroupInquiryRequest request)
	{
		ProductPlanTemplateGroupResponse response = new ProductPlanTemplateGroupResponse();
		try
		{
			response = fetchPaged(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Insert product plan template group.
	 *
	 * @param request The maintenance request containing the {@link ProductPlanTemplateGroup} to insert.
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	@Override
	public MaintenanceResponse insertProductPlanTemplateGroup(ProductPlanTemplateGroupMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		try
		{
			response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Update product plan template group.
	 *
	 * @param request The maintenance request containing the {@link ProductPlanTemplateGroup} to update.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	@Override
	public MaintenanceResponse updateProductPlanTemplateGroup(ProductPlanTemplateGroupMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		try
		{
			response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}
}
