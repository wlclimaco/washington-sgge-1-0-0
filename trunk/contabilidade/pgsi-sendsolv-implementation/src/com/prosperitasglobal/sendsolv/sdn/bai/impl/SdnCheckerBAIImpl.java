package com.prosperitasglobal.sendsolv.sdn.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.bai.ISdnCheckerBAI;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnCheckerResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnHistoryResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnStatusHistoryResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.Response;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SdnCheckerBAIImpl.
 */
public class SdnCheckerBAIImpl implements ISdnCheckerBAI
{
	private static final String ERROR_IF_NULL = "errorIfNull";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	public static final String OL_ERROR = "sendsolv.base.optimistic.locking.error";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = SdnCheckerBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SdnCheckerBAIImpl.class);

	/** The sdn checker bac. */
	private ISdnCheckerBAC sdnCheckerBAC;

	/** The sdn match validation controller. */
	private ValidationController sdnMatchValidationController;

	/** The sdn status history controller. */
	private ValidationController sdnStatusHistoryController;

	/** The sdn history controller. */
	private ValidationController sdnHistoryController;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bai.ISdnCheckerBAI#checkSdn(com.prosperitasglobal.sendsolv.sdn.model.request
	 * .SdnMatchRequest)
	 */
	@Override
	public SdnCheckerResponse checkSdn(SdnCheckerRequest request)
	{
		SdnCheckerResponse response = new SdnCheckerResponse();
		try
		{
			InternalResultsResponse<SdnStatusHistory> internalResponse =
					new InternalResultsResponse<SdnStatusHistory>();

			ValidationContext context =
					new ValidationContext(SdnMatch.class.getSimpleName(), request.getSdnMatch(),
							ValidationContextIndicator.INSERT);

			if (getSdnMatchValidationController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().checkSdn(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SdnCheckerResponse checkMemberSdn(SdnCheckerRequest<Member> request)
	{
		SdnCheckerResponse response = new SdnCheckerResponse();
		try
		{
			InternalResultsResponse<SdnStatusHistory> internalResponse =
					new InternalResultsResponse<SdnStatusHistory>();

			// Use the underlying class name as key, because the class for the Request is the same for all 4 types:
			// Member, Recipient, Liaison, Business
			ValidationContext context =
					new ValidationContext(Member.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			// Tell the Validator not to error out if object is null
			context.getValidationArguments().put(ERROR_IF_NULL, false);

			if (getSdnMatchValidationController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().checkMemberSdn(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SdnCheckerResponse checkRecipientSdn(SdnCheckerRequest<Recipient> request)
	{
		SdnCheckerResponse response = new SdnCheckerResponse();
		try
		{
			InternalResultsResponse<SdnStatusHistory> internalResponse =
					new InternalResultsResponse<SdnStatusHistory>();

			ValidationContext context =
					new ValidationContext(Recipient.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());
			context.getValidationArguments().put(ERROR_IF_NULL, false);

			if (getSdnMatchValidationController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().checkRecipientSdn(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SdnCheckerResponse checkLiaisonSdn(SdnCheckerRequest<Liaison> request)
	{
		SdnCheckerResponse response = new SdnCheckerResponse();
		try
		{
			InternalResultsResponse<SdnStatusHistory> internalResponse =
					new InternalResultsResponse<SdnStatusHistory>();

			ValidationContext context =
					new ValidationContext(Liaison.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());
			context.getValidationArguments().put(ERROR_IF_NULL, false);

			if (getSdnMatchValidationController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().checkLiaisonSdn(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SdnCheckerResponse checkBusinessSdn(SdnCheckerRequest<Business> request)
	{
		SdnCheckerResponse response = new SdnCheckerResponse();
		try
		{
			InternalResultsResponse<SdnStatusHistory> internalResponse =
					new InternalResultsResponse<SdnStatusHistory>();

			ValidationContext context =
					new ValidationContext(Business.class.getSimpleName(), request,
							ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());
			context.getValidationArguments().put(ERROR_IF_NULL, false);

			if (getSdnMatchValidationController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().checkBusinessSdn(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bai.ISdnCheckerBAI#checkForNewSdnFile(com.qat.framework.model.request.Request)
	 */
	@Override
	public Response checkForNewSdnFile(Request request)
	{
		InternalResponse internalResponse = null;
		Response response = new Response();

		try
		{
			internalResponse = getSdnCheckerBAC().checkForNewSdnFile(request);

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public SdnStatusHistoryResponse fetchCurrentSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		SdnStatusHistoryResponse response = new SdnStatusHistoryResponse();
		try
		{
			InternalResultsResponse<SdnStatusHistory> internalResponse =
					new InternalResultsResponse<SdnStatusHistory>();

			ValidationContext context =
					new ValidationContext(SdnStatusHistoryRequest.class.getSimpleName(), request,
							ValidationContextIndicator.FETCH_BY_ID);

			if (getSdnStatusHistoryController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().fetchCurrentSdnStatusHistory(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SdnStatusHistoryResponse fetchFullSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		SdnStatusHistoryResponse response = new SdnStatusHistoryResponse();
		try
		{
			InternalResultsResponse<SdnStatusHistory> internalResponse =
					new InternalResultsResponse<SdnStatusHistory>();

			ValidationContext context =
					new ValidationContext(SdnStatusHistoryRequest.class.getSimpleName(), request,
							ValidationContextIndicator.FETCH_BY_ID);

			if (getSdnStatusHistoryController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().fetchFullSdnStatusHistory(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SdnStatusHistoryResponse updateSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		SdnStatusHistoryResponse response = new SdnStatusHistoryResponse();
		try
		{
			InternalResponse internalResponse =
					new InternalResponse();

			ValidationContext context =
					new ValidationContext(SdnStatusHistoryRequest.class.getSimpleName(), request,
							ValidationContextIndicator.UPDATE);

			if (getSdnStatusHistoryController().validate(context))
			{
				internalResponse = getSdnCheckerBAC().updateSdnStatusHistory(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SdnHistoryResponse fetchSdnStatusHistoryByRequest(SdnStatusHistoryInquiryRequest request)
	{
		SdnHistoryResponse response = new SdnHistoryResponse();

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
	 * Fetch the {@link MoneyTransfer} using the id from request.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the money transfer response
	 */
	private SdnHistoryResponse fetchPaged(SdnStatusHistoryInquiryRequest request,
			SdnHistoryResponse response)
	{
		InternalResultsResponse<SdnHistory> internalResponse = new InternalResultsResponse<SdnHistory>();

		ValidationContext context =
				new ValidationContext(SdnStatusHistoryInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getSdnHistoryController().validate(context))
		{
			internalResponse = getSdnCheckerBAC().fetchSdnStatusHistoryByRequest(request);
		}

		return (SdnHistoryResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

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
	 * Gets the sdn checker bac.
	 *
	 * @return the sdn checker bac
	 */
	public ISdnCheckerBAC getSdnCheckerBAC()
	{
		return sdnCheckerBAC;
	}

	/**
	 * Sets the sdn checker bac.
	 *
	 * @param sdnCheckerBAC the sdnCheckerBAC to set
	 */
	public void setSdnCheckerBAC(ISdnCheckerBAC sdnCheckerBAC)
	{
		this.sdnCheckerBAC = sdnCheckerBAC;
	}

	/**
	 * Gets the sdn match validation controller.
	 *
	 * @return the sdnMatchValidationController
	 */
	public ValidationController getSdnMatchValidationController()
	{
		return sdnMatchValidationController;
	}

	/**
	 * Sets the sdn match validation controller.
	 *
	 * @param sdnMatchValidationController the sdnMatchValidationController to set
	 */
	public void setSdnMatchValidationController(ValidationController sdnMatchValidationController)
	{
		this.sdnMatchValidationController = sdnMatchValidationController;
	}

	/**
	 * Gets the sdn status history controller.
	 *
	 * @return the sdn status history controller
	 */
	public ValidationController getSdnStatusHistoryController()
	{
		return sdnStatusHistoryController;
	}

	/**
	 * Sets the sdn status history controller.
	 *
	 * @param sdnStatusHistoryController the new sdn status history controller
	 */
	public void setSdnStatusHistoryController(ValidationController sdnStatusHistoryController)
	{
		this.sdnStatusHistoryController = sdnStatusHistoryController;
	}

	/**
	 * @return the sdnHistoryController
	 */
	public ValidationController getSdnHistoryController()
	{
		return sdnHistoryController;
	}

	/**
	 * @param sdnHistoryController the sdnHistoryController to set
	 */
	public void setSdnHistoryController(ValidationController sdnHistoryController)
	{
		this.sdnHistoryController = sdnHistoryController;
	}

}
