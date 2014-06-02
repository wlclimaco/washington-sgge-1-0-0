package com.qat.samples.sysmgmt.marcamenu.bai;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.Response;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;

/**
 * Abstract BAI class. General purpose base class for use with other BAI's abstracting away from implementations
 * common functionality and methods.
 * Note this class includes Spring injected properties. Make sure the BAI implementation configuration includes these
 * properties.
 */
public abstract class BaseBAI
{

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the validation controller.
	 * 
	 * @param theValidationController the new validation controller
	 */
	public void setValidationController(ValidationController theValidationController)
	{
		validationController = theValidationController;
	}

	/**
	 * Common method for handling the processing of a persistence type operation.
	 * Including validation, the actual persistence operation and the optional retrieval of data.
	 * 
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @param getList the get list
	 * @return the county response
	 */
	protected Response process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			Request request, Response response)
	{
		InternalResponse internalResponse = null;

		Object objectToBeValidated = getObjectToBeValidated(request);

		// Validate
		ValidationContext context =
				new ValidationContext(objectToBeValidated.getClass().getSimpleName(), objectToBeValidated, indicator);
		if (!getValidationController().validate(context))
		{
			return handleReturn(response, internalResponse, context.getMessages(), true);
		}

		// Persist
		internalResponse = doPersistance(request, persistType);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			return handleReturn(response, internalResponse, context.getMessages(), true);
		}

		// Call maintainReturnList to see if we need to return the object list and if so whether it should be paged or
		// not
		maintainReturnList(request, response);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Gets the object to be validated based on the request.
	 * 
	 * @param request the request
	 * @return the object to be validated
	 */
	protected abstract Object getObjectToBeValidated(Request request);

	/**
	 * Maintain return list.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	protected abstract void maintainReturnList(Object request, Object response);

	/**
	 * Do the actual persistence operation.
	 * 
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	protected abstract InternalResponse doPersistance(Request request, PersistanceActionEnum updateType);

	/**
	 * Handles the population of the Response object based on the parameters.
	 * 
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the county response
	 */
	private Response handleReturn(Response response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

}
