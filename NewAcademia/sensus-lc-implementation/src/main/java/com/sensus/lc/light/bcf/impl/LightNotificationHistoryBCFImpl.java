package com.sensus.lc.light.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.light.bcf.ILightNotificationHistoryBCF;
import com.sensus.lc.light.bcl.ILightNotificationHistoryBCL;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.response.LightHistoryResponse;
import com.sensus.lc.light.model.response.NotificationHistoryResponse;
import com.sensus.lc.process.model.ProcessOrderByEnum;

/**
 * The Class LightNotificationHistoryBCFImpl.
 * 
 * Implementation of ILightNotificationHistoryBCF.
 */
public class LightNotificationHistoryBCFImpl implements ILightNotificationHistoryBCF
{
	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/**
	 * BCL's attributes.
	 */
	private ILightNotificationHistoryBCL notificationHistoryBCL;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(LightNotificationHistoryBCFImpl.class);

	/** The Constant DEFAULT_LIGHT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.lightbcfimpl.defaultexception";

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/**
	 * @return the notificationHistoryBCL
	 */
	public ILightNotificationHistoryBCL getNotificationHistoryBCL()
	{
		return notificationHistoryBCL;
	}

	/**
	 * @param notificationHistoryBCL the notificationHistoryBCL to set
	 */
	public void setNotificationHistoryBCL(ILightNotificationHistoryBCL notificationHistoryBCL)
	{
		this.notificationHistoryBCL = notificationHistoryBCL;
	}

	/**
	 * @return the requestValidationController
	 */
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/**
	 * @param requestValidationController the requestValidationController to set
	 */
	public void setRequestValidationController(ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

	/**
	 * @return the inquiryRequestValidationController
	 */
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * @param inquiryRequestValidationController the inquiryRequestValidationController to set
	 */
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightNotificationHistoryBCF#fetchLightNotificationHistory(com.sensus.mlc.light.model
	 * .request.NotificationHistoryRequest)
	 */
	@Override
	public NotificationHistoryResponse fetchLightNotificationHistoryByRequest(NotificationHistoryRequest request)
	{
		InternalResultsResponse<NotificationHistory> notificationHistoryResponse = null;
		NotificationHistoryResponse response = new NotificationHistoryResponse();

		try
		{
			if (ValidationUtil.isNull(request.getNotificationHistoryCriteria()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(DEFAULT_LIGHT_BCF_EXCEPTION_MSG));
				return response;
			}

			ValidationContext context = new ValidationContext(NotificationHistoryRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH);

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
			{
				notificationHistoryResponse = getNotificationHistoryBCL().fetchNotificationHistoryByRequest(request);
			}

			handleOperationStatusAndMessages(response, notificationHistoryResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightNotificationHistoryBCF#fetchNotificationHistoryById(com.sensus.mlc.light.model
	 * .request.NotificationHistoryRequest)
	 */
	@Override
	public NotificationHistoryResponse fetchNotificationHistoryById(NotificationHistoryRequest request)
	{
		InternalResultsResponse<NotificationHistory> notificationHistoryResponse = null;
		NotificationHistoryResponse response = new NotificationHistoryResponse();

		checkFetchDefaults(request);
		try
		{
			if (ValidationUtil.isNull(request.getNotificationHistoryCriteria()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(DEFAULT_LIGHT_BCF_EXCEPTION_MSG));
				return response;
			}

			ValidationContext context = new ValidationContext(NotificationHistoryRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH);

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
			{
				notificationHistoryResponse = getNotificationHistoryBCL().fetchNotificationHistoryById(request);
			}

			handleOperationStatusAndMessages(response, notificationHistoryResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightNotificationHistoryBCF#fetchLightNotificationHistory(com.sensus.mlc.light.model
	 * .request.NotificationHistoryRequest)
	 */
	@Override
	public LightHistoryResponse fetchLightNotificationHistory(NotificationHistoryRequest request)
	{
		LightHistoryResponse response = new LightHistoryResponse();
		InternalResultsResponse<LightHistory> lightHistoryResponse = null;

		try
		{
			if (ValidationUtil.isNull(request.getNotificationHistoryCriteria()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(DEFAULT_LIGHT_BCF_EXCEPTION_MSG));
				return response;
			}

			ValidationContext context = new ValidationContext(NotificationHistoryRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH);

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
			{
				lightHistoryResponse = getNotificationHistoryBCL().fetchLightNotificationHistory(request);
			}

			handleOperationStatusAndMessages(response, lightHistoryResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/**
	 * Check fetch defaults.
	 * 
	 * @param inquiryRequest the inquiry request
	 */
	private void checkFetchDefaults(InquiryRequest inquiryRequest)
	{
		if (ValidationUtil.isNull(inquiryRequest.getPageSize())
				|| inquiryRequest.getPageSize().equals(TWENTY))
		{
			inquiryRequest.setPageSize(new Integer(0));
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryRequest.getSortExpressions()))
		{
			return;
		}

		SortExpression sortExpression =
				new SortExpression(ProcessOrderByEnum.START_TIME_COLUMN.getValue(), Direction.Descending);
		inquiryRequest.addSortExpressions(sortExpression);
	}

}
