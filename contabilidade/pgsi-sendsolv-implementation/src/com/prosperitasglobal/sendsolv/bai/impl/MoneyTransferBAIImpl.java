package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchDTO;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferAutoApprovalResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchDTOResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
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
 * The Class MoneyTransferBAIImpl.
 */
public class MoneyTransferBAIImpl implements IMoneyTransferBAI
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MoneyTransferBAIImpl.class);

	/** The money transfer bac. */
	private IMoneyTransferBAC moneyTransferBAC;

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	public static final String OL_ERROR = "sendsolv.base.optimistic.locking.error";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = MoneyTransferBAIImpl.class.getName();

	/** The Constant MONEY_TRANSFER_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_ID_REQUIRED =
			"sendsolv.base.moneytransfer.id.required";

	/** The Constant MONEY_TRANSFER_BATCH_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_BATCH_ID_REQUIRED =
			"sendsolv.base.moneytransferbatch.id.required";

	/** The money transfer inquiry request validation controller. */
	private ValidationController moneyTransferInquiryRequestValidationController;

	/** The money transfer batch inquiry request validation controller. */
	private ValidationController moneyTransferBatchInquiryRequestValidationController;

	/** The money transfer validation controller. */
	private ValidationController moneyTransferValidationController;

	/** The money transfer batch validation controller. */
	private ValidationController moneyTransferBatchValidationController;

	/** The money transfer batch status validation controller. */
	private ValidationController moneyTransferBatchStatusMaintenanceRequestValidationController;

	/** The money transfer status validation controller. */
	private ValidationController moneyTransferStatusMaintenanceRequestValidationController;

	/** The money transfer batch create request validation controller. */
	private ValidationController moneyTransferBatchCreateRequestValidationController;

	/** The money transfer create request validation controller. */
	private ValidationController moneyTransferCreateRequestValidationController;

	/** The money transfer auto approval request validation controller. */
	private ValidationController moneyTransferAutoApprovalRequestValidationController;

	/**
	 * Gets the money transfer bac.
	 *
	 * @return the money transfer bac
	 */
	public IMoneyTransferBAC getMoneyTransferBAC()
	{
		return moneyTransferBAC;
	}

	/**
	 * Sets the money transfer bac.
	 *
	 * @param moneyTransferBAC the new money transfer bac
	 */
	public void setMoneyTransferBAC(IMoneyTransferBAC moneyTransferBAC)
	{
		this.moneyTransferBAC = moneyTransferBAC;
	}

	/**
	 * @return the moneyTransferInquiryRequestValidationController
	 */
	public ValidationController getMoneyTransferInquiryRequestValidationController()
	{
		return moneyTransferInquiryRequestValidationController;
	}

	/**
	 * @param moneyTransferInquiryRequestValidationController the moneyTransferInquiryRequestValidationController to set
	 */
	public void setMoneyTransferInquiryRequestValidationController(
			ValidationController moneyTransferInquiryRequestValidationController)
	{
		this.moneyTransferInquiryRequestValidationController = moneyTransferInquiryRequestValidationController;
	}

	/**
	 * @return the moneyTransferBatchInquiryRequestValidationController
	 */
	public ValidationController getMoneyTransferBatchInquiryRequestValidationController()
	{
		return moneyTransferBatchInquiryRequestValidationController;
	}

	/**
	 * @param moneyTransferBatchInquiryRequestValidationController the
	 *            moneyTransferBatchInquiryRequestValidationController to set
	 */
	public void setMoneyTransferBatchInquiryRequestValidationController(
			ValidationController moneyTransferBatchInquiryRequestValidationController)
	{
		this.moneyTransferBatchInquiryRequestValidationController =
				moneyTransferBatchInquiryRequestValidationController;
	}

	/**
	 * @return the moneyTransferValidationController
	 */
	public ValidationController getMoneyTransferValidationController()
	{
		return moneyTransferValidationController;
	}

	/**
	 * @param moneyTransferValidationController the moneyTransferValidationController to set
	 */
	public void setMoneyTransferValidationController(ValidationController moneyTransferValidationController)
	{
		this.moneyTransferValidationController = moneyTransferValidationController;
	}

	/**
	 * @return the moneyTransferBatchValidationController
	 */
	public ValidationController getMoneyTransferBatchValidationController()
	{
		return moneyTransferBatchValidationController;
	}

	/**
	 * @param moneyTransferBatchValidationController the moneyTransferBatchValidationController to set
	 */
	public void setMoneyTransferBatchValidationController(ValidationController moneyTransferBatchValidationController)
	{
		this.moneyTransferBatchValidationController = moneyTransferBatchValidationController;
	}

	/**
	 * @return the moneyTransferBatchStatusMaintenanceRequestValidationController
	 */
	public ValidationController getMoneyTransferBatchStatusMaintenanceRequestValidationController()
	{
		return moneyTransferBatchStatusMaintenanceRequestValidationController;
	}

	/**
	 * @param moneyTransferBatchStatusMaintenanceRequestValidationController the
	 *            moneyTransferBatchStatusMaintenanceRequestValidationController to set
	 */
	public void setMoneyTransferBatchStatusMaintenanceRequestValidationController(
			ValidationController moneyTransferBatchStatusMaintenanceRequestValidationController)
	{
		this.moneyTransferBatchStatusMaintenanceRequestValidationController =
				moneyTransferBatchStatusMaintenanceRequestValidationController;
	}

	/**
	 * @return the moneyTransferStatusMaintenanceRequestValidationController
	 */
	public ValidationController getMoneyTransferStatusMaintenanceRequestValidationController()
	{
		return moneyTransferStatusMaintenanceRequestValidationController;
	}

	/**
	 * @param moneyTransferStatusMaintenanceRequestValidationController the
	 *            moneyTransferStatusMaintenanceRequestValidationController to set
	 */
	public void setMoneyTransferStatusMaintenanceRequestValidationController(
			ValidationController moneyTransferStatusMaintenanceRequestValidationController)
	{
		this.moneyTransferStatusMaintenanceRequestValidationController =
				moneyTransferStatusMaintenanceRequestValidationController;
	}

	/**
	 * @return the moneyTransferBatchCreateRequestValidationController
	 */
	public ValidationController getMoneyTransferBatchCreateRequestValidationController()
	{
		return moneyTransferBatchCreateRequestValidationController;
	}

	/**
	 * @param moneyTransferBatchCreateRequestValidationController the
	 *            moneyTransferBatchCreateRequestValidationController to set
	 */
	public void setMoneyTransferBatchCreateRequestValidationController(
			ValidationController moneyTransferBatchCreateRequestValidationController)
	{
		this.moneyTransferBatchCreateRequestValidationController = moneyTransferBatchCreateRequestValidationController;
	}

	/**
	 * @return the moneyTransferCreateRequestValidationController
	 */
	public ValidationController getMoneyTransferCreateRequestValidationController()
	{
		return moneyTransferCreateRequestValidationController;
	}

	/**
	 * @param moneyTransferCreateRequestValidationController the moneyTransferCreateRequestValidationController to set
	 */
	public void setMoneyTransferCreateRequestValidationController(
			ValidationController moneyTransferCreateRequestValidationController)
	{
		this.moneyTransferCreateRequestValidationController = moneyTransferCreateRequestValidationController;
	}

	/**
	 * @return the moneyTransferAutoApprovalRequestValidationController
	 */
	public ValidationController getMoneyTransferAutoApprovalRequestValidationController()
	{
		return moneyTransferAutoApprovalRequestValidationController;
	}

	/**
	 * @param moneyTransferAutoApprovalRequestValidationController the
	 *            moneyTransferAutoApprovalRequestValidationController to set
	 */
	public void setMoneyTransferAutoApprovalRequestValidationController(
			ValidationController moneyTransferAutoApprovalRequestValidationController)
	{
		this.moneyTransferAutoApprovalRequestValidationController =
				moneyTransferAutoApprovalRequestValidationController;
	}

	@Override
	public MoneyTransferResponse fetchMoneyTransferById(FetchByIdRequest request)
	{
		MoneyTransferResponse response = new MoneyTransferResponse();

		try
		{
			InternalResultsResponse<MoneyTransfer> internalResponse = new InternalResultsResponse<MoneyTransfer>();

			// -- validate the money transfer id field
			if (ValidationUtil.isNullOrZero(request.getId()))
			{
				internalResponse.addFieldErrorMessage(MONEY_TRANSFER_ID_REQUIRED);
			}
			else
			{
				internalResponse = getMoneyTransferBAC().fetchMoneyTransferById(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public MoneyTransferBatchResponse fetchMoneyTransferBatchById(FetchByIdRequest request)
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();

		try
		{
			InternalResultsResponse<MoneyTransferBatch> internalResponse =
					new InternalResultsResponse<MoneyTransferBatch>();

			// -- validate the money transfer batch id field
			if (ValidationUtil.isNullOrZero(request.getId()))
			{
				internalResponse.addFieldErrorMessage(MONEY_TRANSFER_BATCH_ID_REQUIRED);
			}
			else
			{
				internalResponse = getMoneyTransferBAC().fetchMoneyTransferBatchById(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public MoneyTransferResponse fetchMoneyTransferByRequest(MoneyTransferInquiryRequest request)
	{
		MoneyTransferResponse response = new MoneyTransferResponse();

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

	@Override
	public MoneyTransferBatchResponse fetchMoneyTransferBatchByRequest(MoneyTransferBatchInquiryRequest request)
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();

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

	@Override
	public MaintenanceResponse updateMoneyTransfer(MoneyTransferMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		ValidationContext context = null;
		InternalResponse internalResponse = null;

		try
		{
			context =
					new ValidationContext(MoneyTransfer.class.getSimpleName(), request.getMoneyTransfer(),
							ValidationContextIndicator.UPDATE);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getMoneyTransferValidationController().validate(context))
			{
				// do update
				internalResponse = getMoneyTransferBAC().updateMoneyTransfer(request);
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	@Override
	public MaintenanceResponse updateMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		ValidationContext context = null;
		InternalResponse internalResponse = null;

		try
		{
			context =
					new ValidationContext(MoneyTransferBatch.class.getSimpleName(), request.getMoneyTransferBatch(),
							ValidationContextIndicator.UPDATE);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getMoneyTransferBatchValidationController().validate(context))
			{
				// do update
				internalResponse = getMoneyTransferBAC().updateMoneyTransferBatch(request);
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Fetch the {@link MoneyTransfer} using the id from request.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the money transfer response
	 */
	private MoneyTransferResponse fetchPaged(MoneyTransferInquiryRequest request, MoneyTransferResponse response)
	{
		InternalResultsResponse<MoneyTransfer> internalResponse = new InternalResultsResponse<MoneyTransfer>();

		ValidationContext context =
				new ValidationContext(MoneyTransferInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getMoneyTransferInquiryRequestValidationController().validate(context))
		{
			internalResponse = getMoneyTransferBAC().fetchMoneyTransferByRequest(request);
		}

		return (MoneyTransferResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Fetch the {@link MoneyTransferBatch} using the id from request.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the money transfer response
	 */
	private MoneyTransferBatchResponse fetchPaged(MoneyTransferBatchInquiryRequest request,
			MoneyTransferBatchResponse response)
	{
		InternalResultsResponse<MoneyTransferBatch> internalResponse =
				new InternalResultsResponse<MoneyTransferBatch>();

		ValidationContext context =
				new ValidationContext(MoneyTransferBatchInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getMoneyTransferBatchInquiryRequestValidationController().validate(context))
		{
			internalResponse = getMoneyTransferBAC().fetchMoneyTransferBatchByRequest(request);
		}

		return (MoneyTransferBatchResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Handle the return from a persistence call.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
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

	@Override
	public MaintenanceResponse insertMoneyTransferBatchStatus(MoneyTransferBatchStatusMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		ValidationContext context = null;
		InternalResponse internalResponse = null;

		try
		{
			context =
					new ValidationContext(MoneyTransferBatchStatusMaintenanceRequest.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getMoneyTransferBatchStatusMaintenanceRequestValidationController().validate(context))
			{
				// do insert
				internalResponse = getMoneyTransferBAC().insertMoneyTransferBatchStatus(request);
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	@Override
	public MoneyTransferBatchDTOResponse fetchMoneyTransferBatchWithSummaryById(FetchByIdRequest request)
	{
		MoneyTransferBatchDTOResponse response = new MoneyTransferBatchDTOResponse();

		try
		{
			InternalResultsResponse<MoneyTransferBatchDTO> internalResponse =
					new InternalResultsResponse<MoneyTransferBatchDTO>();

			// -- validate the money transfer batch id field
			if (ValidationUtil.isNullOrZero(request.getId()))
			{
				internalResponse.addFieldErrorMessage(MONEY_TRANSFER_BATCH_ID_REQUIRED);
			}
			else
			{
				internalResponse = getMoneyTransferBAC().fetchMoneyTransferBatchWithSummaryById(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public MaintenanceResponse insertMoneyTransferStatus(MoneyTransferStatusMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		ValidationContext context = null;
		InternalResponse internalResponse = null;

		try
		{
			context =
					new ValidationContext(MoneyTransferStatusMaintenanceRequest.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getMoneyTransferStatusMaintenanceRequestValidationController().validate(context))
			{
				// do insert
				internalResponse = getMoneyTransferBAC().insertMoneyTransferStatus(request);
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI#insertMoneyTransfer(com.prosperitasglobal.sendsolv.model
	 * .request.MoneyTransferCreateRequest)
	 */
	@Override
	public MoneyTransferResponse insertMoneyTransfer(MoneyTransferCreateRequest request)
	{
		MoneyTransferResponse response = new MoneyTransferResponse();
		ValidationContext context = null;
		InternalResponse internalResponse = null;

		try
		{
			context =
					new ValidationContext(MoneyTransferCreateRequest.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getMoneyTransferCreateRequestValidationController().validate(context))
			{
				// do insert
				internalResponse = getMoneyTransferBAC().createOnOffMoneyTransfer(request);
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return (MoneyTransferResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI#insertMoneyTransferBatch(com.prosperitasglobal.sendsolv.
	 * model.request.MoneyTransferBatchCreateRequest)
	 */
	@Override
	public MoneyTransferBatchResponse insertMoneyTransferBatch(MoneyTransferBatchCreateRequest request)
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();
		ValidationContext context = null;
		InternalResultsResponse<MoneyTransferBatch> internalResponse = null;

		try
		{
			context =
					new ValidationContext(MoneyTransferBatchCreateRequest.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getMoneyTransferBatchCreateRequestValidationController().validate(context))
			{
				// do insert
				internalResponse = getMoneyTransferBAC().createOnOffMoneyTransferBatch(request);
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return (MoneyTransferBatchResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI#fetchMoneyTransferAutoApprovalByTransferSetting(com.
	 * prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest)
	 */
	@Override
	public MoneyTransferAutoApprovalResponse fetchMoneyTransferAutoApprovalByTransferSetting(
			MoneyTransferAutoApprovalRequest request)
	{
		MoneyTransferAutoApprovalResponse response = new MoneyTransferAutoApprovalResponse();

		try
		{
			InternalResultsResponse<Integer> internalResponse =
					new InternalResultsResponse<Integer>();

			ValidationContext context =
					new ValidationContext(MoneyTransferAutoApprovalRequest.class.getSimpleName(), request,
							ValidationContextIndicator.FETCH);

			if (getMoneyTransferAutoApprovalRequestValidationController().validate(context))
			{
				internalResponse = getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(request);
			}

			handleReturn(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}
}
