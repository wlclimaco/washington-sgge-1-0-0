package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IProductPlanBAC;
import com.prosperitasglobal.sendsolv.bai.IProductPlanBAI;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.BusinessProductPlanResponse;
import com.prosperitasglobal.sendsolv.model.response.TemplateProductPlanResponse;
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
 * A class that implements methods for working with both a Template and Business Product's business area functions.
 */
public class ProductPlanBAIImpl implements IProductPlanBAI
{
	/** The business product plan validation controller. */
	private ValidationController businessProductPlanValidationController;

	/** The business product plan inquiry request validation controller. */
	private ValidationController businessProductPlanInquiryRequestValidationController;

	/** The implementation of the {@link IProductPlanBAC} interface. */
	private IProductPlanBAC productPlanBAC;

	/** The template product plan validation controller. */
	private ValidationController templateProductPlanValidationController;

	/** The template product plan inquiry request validation controller. */
	private ValidationController templateProductPlanInquiryRequestValidationController;

	/** The Constant BUSINESS_PRODUCT_PLAN_ID_REQUIRED. */
	public static final String BUSINESS_PRODUCT_PLAN_ID_REQUIRED =
			"sendsolv.base.businessproductplanvalidator.id.required";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ProductPlanBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProductPlanBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	public static final String OL_ERROR = "sendsolv.base.optimistic.locking.error";

	/** The Constant TEMPLATE_PRODUCT_PLAN_ID_REQUIRED. */
	public static final String TEMPLATE_PRODUCT_PLAN_ID_REQUIRED =
			"sendsolv.base.templateproductplanvalidator.id.required";

	/**
	 * Perform the persistence actions for a {@link BusinessProductPlan}.
	 *
	 * @param businessProductPlan The {@link BusinessProductPlan} to perform the action on.
	 * @param modelAction The persistence action to perform.
	 * @return The {@link MaintenanceResponse} containing information about whether the action was successful or not.
	 */
	private InternalResponse doPersistance(BusinessProductPlan businessProductPlan, PersistanceActionEnum modelAction)
	{
		switch (modelAction)
		{
			case INSERT:
				return getProductPlanBAC().insertBusinessProductPlan(businessProductPlan);

			case UPDATE:
				return getProductPlanBAC().updateBusinessProductPlan(businessProductPlan);

			case DELETE:
				return getProductPlanBAC().deleteBusinessProductPlan(businessProductPlan);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("model action missing for BusinessProductPlanMaintenanceRequest!");
				}
				break;
		}
		return null;
	}

	/**
	 * Perform the persistence actions for a {@link TemplateProductPlan}.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} to perform the action on.
	 * @param modelAction The persistence action to perform.
	 * @return The {@link MaintenanceResponse} containing information about whether the action was successful or not.
	 */
	private InternalResponse doPersistance(TemplateProductPlan templateProductPlan, PersistanceActionEnum modelAction)
	{
		switch (modelAction)
		{
			case INSERT:
				return getProductPlanBAC().insertTemplateProductPlan(templateProductPlan);

			case UPDATE:
				return getProductPlanBAC().updateTemplateProductPlan(templateProductPlan);

			case DELETE:
				return getProductPlanBAC().deleteTemplateProductPlan(templateProductPlan);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("model action missing for TemplateProductPlanMaintenanceRequest!");
				}
				break;
		}
		return null;
	}

	/**
	 * Fetch the {@link BusinessProductPlan} using the paged inquiry.
	 *
	 * @param request The request.
	 * @param response The {@link BusinessProductPlanResponse} containing the current state of the process.
	 * @return The updated {@link BusinessProductPlanResponse} containing the next state of the process.
	 */
	private BusinessProductPlanResponse fetchPaged(BusinessProductPlanInquiryRequest request,
			BusinessProductPlanResponse response)
	{
		InternalResultsResponse<BusinessProductPlan> internalResponse =
				new InternalResultsResponse<BusinessProductPlan>();

		ValidationContext context =
				new ValidationContext(BusinessProductPlanInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getBusinessProductPlanInquiryRequestValidationController().validate(context))
		{
			internalResponse = getProductPlanBAC().fetchBusinessProductPlanByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (BusinessProductPlanResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Fetch the {@link TemplateProductPlan} using the paged inquiry.
	 *
	 * @param request The request.
	 * @param response The {@link TemplateProductPlanResponse} containing the current state of the process.
	 * @return The updated {@link TemplateProductPlanResponse} containing the next state of the process.
	 */
	private TemplateProductPlanResponse fetchPaged(TemplateProductPlanInquiryRequest request,
			TemplateProductPlanResponse response)
	{
		InternalResultsResponse<TemplateProductPlan> internalResponse =
				new InternalResultsResponse<TemplateProductPlan>();

		ValidationContext context =
				new ValidationContext(TemplateProductPlanInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getTemplateProductPlanInquiryRequestValidationController().validate(context))
		{
			internalResponse = getProductPlanBAC().fetchTemplateProductPlanByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (TemplateProductPlanResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Handle the return from a persistence call.
	 *
	 * @param response The {@link BusinessProductPlanResponse} containing the current state of the process.
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
	 * @return The updated {@link BusinessProductPlanResponse} containing the next state of the process.
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
	 * @param request The maintenance request containing the {@link BusinessProductPlan} to perform the action.
	 * @return The {@link MaintenanceResponse} containing information about whether the action was successful or not.
	 */
	private MaintenanceResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			BusinessProductPlanMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		InternalResponse internalResponse = null;

		// Validate.
		ValidationContext context =
				new ValidationContext(BusinessProductPlan.class.getSimpleName(), request.getBusinessProductPlan(),
						indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getBusinessProductPlanValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request.getBusinessProductPlan(), persistType);
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Process the persistence request.
	 *
	 * @param indicator Used as an indicator during the execution of validators and is used to indicate the context in
	 *            which a given validator should execute.
	 * @param persistType The {@link PersistanceActionEnum} being requested.
	 * @param request The maintenance request containing the {@link TemplateProductPlan} to perform the action.
	 * @return The {@link MaintenanceResponse} containing information about whether the action was successful or not.
	 */
	private MaintenanceResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			TemplateProductPlanMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		InternalResponse internalResponse = null;

		// Validate.
		ValidationContext context =
				new ValidationContext(TemplateProductPlan.class.getSimpleName(), request.getTemplateProductPlan(),
						indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getTemplateProductPlanValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request.getTemplateProductPlan(), persistType);
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Gets the business product plan validation controller.
	 *
	 * @return The business product plan validation controller.
	 */
	public ValidationController getBusinessProductPlanValidationController()
	{
		return businessProductPlanValidationController;
	}

	/**
	 * Sets the business product plan validation controller.
	 *
	 * @param businessProductPlanValidationController The business product plan validation controller to set.
	 */
	public void setBusinessProductPlanValidationController(ValidationController businessProductPlanValidationController)
	{
		this.businessProductPlanValidationController = businessProductPlanValidationController;
	}

	/**
	 * Gets the business product plan inquiry request validation controller.
	 *
	 * @return The business product plan inquiry request validation controller
	 */
	public ValidationController getBusinessProductPlanInquiryRequestValidationController()
	{
		return businessProductPlanInquiryRequestValidationController;
	}

	/**
	 * Sets the business product plan inquiry request validation controller.
	 *
	 * @param businessProductPlanInquiryRequestValidationController The the business product plan inquiry request
	 *            validation controller to set.
	 */
	public void setBusinessProductPlanInquiryRequestValidationController(
			ValidationController businessProductPlanInquiryRequestValidationController)
	{
		this.businessProductPlanInquiryRequestValidationController =
				businessProductPlanInquiryRequestValidationController;
	}

	/**
	 * Gets the implementation of the {@link IProductPlanBAC} interface.
	 *
	 * @return The implementation of the {@link IProductPlanBAC} interface.
	 */
	public IProductPlanBAC getProductPlanBAC()
	{
		return productPlanBAC;
	}

	/**
	 * Sets the implementation of the {@link IProductPlanBAC} interface.
	 *
	 * @param productPlanBAC The implementation of the {@link IProductPlanBAC} interface to set.
	 */
	public void setProductPlanBAC(IProductPlanBAC productPlanBAC)
	{
		this.productPlanBAC = productPlanBAC;
	}

	/**
	 * Gets the template product plan validation controller.
	 *
	 * @return The template product plan validation controller.
	 */
	public ValidationController getTemplateProductPlanValidationController()
	{
		return templateProductPlanValidationController;
	}

	/**
	 * Sets the template product plan validation controller.
	 *
	 * @param templateProductPlanValidationController The template product plan validation controller to set.
	 */
	public void setTemplateProductPlanValidationController(ValidationController templateProductPlanValidationController)
	{
		this.templateProductPlanValidationController = templateProductPlanValidationController;
	}

	/**
	 * Gets the template product plan inquiry request validation controller.
	 *
	 * @return The template product plan inquiry request validation controller
	 */
	public ValidationController getTemplateProductPlanInquiryRequestValidationController()
	{
		return templateProductPlanInquiryRequestValidationController;
	}

	/**
	 * Sets the template product plan inquiry request validation controller.
	 *
	 * @param templateProductPlanInquiryRequestValidationController The the template product plan inquiry request
	 *            validation controller to set.
	 */
	public void setTemplateProductPlanInquiryRequestValidationController(
			ValidationController templateProductPlanInquiryRequestValidationController)
	{
		this.templateProductPlanInquiryRequestValidationController =
				templateProductPlanInquiryRequestValidationController;
	}

	/**
	 * Delete business product plan.
	 *
	 * @param request The maintenance request containing the {@link BusinessProductPlan} to delete.
	 * @return The {@link MaintenanceResponse} containing information about whether the delete was successful or not.
	 */
	@Override
	public MaintenanceResponse deleteBusinessProductPlan(BusinessProductPlanMaintenanceRequest request)
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
	 * Delete template product plan.
	 *
	 * @param request The maintenance request containing the {@link TemplateProductPlan} to delete.
	 * @return The {@link MaintenanceResponse} containing information about whether the delete was successful or not.
	 */
	@Override
	public MaintenanceResponse deleteTemplateProductPlan(TemplateProductPlanMaintenanceRequest request)
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
	 * Fetch a business product plan by id.
	 *
	 * @param request The request.
	 * @return The {@link BusinessProductPlanResponse} containing the {@link List} of {@link BusinessProductPlan}'s that
	 *         contained the id. Also contains information about whether the action was successful or not. If the action
	 *         was not successful, no {@link BusinessProductPlan} will be contained in the response.
	 */
	@Override
	public BusinessProductPlanResponse fetchBusinessProductPlanById(FetchByIdRequest request)
	{
		BusinessProductPlanResponse response = new BusinessProductPlanResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()))
			{
				internalResponse.addFieldErrorMessage(BUSINESS_PRODUCT_PLAN_ID_REQUIRED);
			}
			else
			{
				internalResponse = getProductPlanBAC().fetchBusinessProductPlanById(request);
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
	 * Fetch all business product plans matching the information contained in the criteria.
	 *
	 * @param request The request.
	 * @return The {@link BusinessProductPlanResponse} containing the {@link List} of {@link BusinessProductPlan}'s that
	 *         matched the criteria. Also contains information about whether the action was successful or not. If the
	 *         action was not successful, no {@link BusinessProductPlan} will be contained in the response.
	 */
	@Override
	public BusinessProductPlanResponse fetchBusinessProductPlanByRequest(BusinessProductPlanInquiryRequest request)
	{
		BusinessProductPlanResponse response = new BusinessProductPlanResponse();
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
	 * Fetch a template product plan by id.
	 *
	 * @param request The request.
	 * @return The {@link TemplateProductPlanResponse} containing the {@link List} of {@link TemplateProductPlan}'s that
	 *         contained the id. Also contains information about whether the action was successful or not. If the action
	 *         was not successful, no {@link TemplateProductPlan} will be contained in the response.
	 */
	@Override
	public TemplateProductPlanResponse fetchTemplateProductPlanById(FetchByIdRequest request)
	{
		TemplateProductPlanResponse response = new TemplateProductPlanResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()))
			{
				internalResponse.addFieldErrorMessage(TEMPLATE_PRODUCT_PLAN_ID_REQUIRED);
			}
			else
			{
				internalResponse = getProductPlanBAC().fetchTemplateProductPlanById(request);
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
	 * Fetch all template product plans matching the information contained in the criteria.
	 *
	 * @param request The request.
	 * @return The {@link TemplateProductPlanResponse} containing the {@link List} of {@link TemplateProductPlan}'s that
	 *         matched the criteria. Also contains information about whether the action was successful or not. If the
	 *         action was not successful, no {@link TemplateProductPlan} will be contained in the response.
	 */
	@Override
	public TemplateProductPlanResponse fetchTemplateProductPlanByRequest(TemplateProductPlanInquiryRequest request)
	{
		TemplateProductPlanResponse response = new TemplateProductPlanResponse();
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
	 * Insert business product plan.
	 *
	 * @param request The maintenance request containing the {@link BusinessProductPlan} to insert.
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	@Override
	public MaintenanceResponse insertBusinessProductPlan(BusinessProductPlanMaintenanceRequest request)
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
	 * Insert business product plan.
	 *
	 * @param request The maintenance request containing the {@link TemplateProductPlan} to insert.
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	@Override
	public MaintenanceResponse insertTemplateProductPlan(TemplateProductPlanMaintenanceRequest request)
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
	 * Update business product plan.
	 *
	 * @param request The maintenance request containing the {@link BusinessProductPlan} to update.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	@Override
	public MaintenanceResponse updateBusinessProductPlan(BusinessProductPlanMaintenanceRequest request)
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

	/**
	 * Update template product plan.
	 *
	 * @param request The maintenance request containing the {@link TemplateProductPlan} to update.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	@Override
	public MaintenanceResponse updateTemplateProductPlan(TemplateProductPlanMaintenanceRequest request)
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
