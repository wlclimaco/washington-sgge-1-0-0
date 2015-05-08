package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ILiaisonBAC;
import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
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
 * The Class LiaisonBAIImpl.
 */
public class LiaisonBAIImpl implements ILiaisonBAI
{

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_PARENT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_PARENT_ID_REQUIRED =
			"sendsolv.base.liaisonvalidator.parent.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.liaisonvalidator.id.required";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = LiaisonBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LiaisonBAIImpl.class);

	/** The liaison bac. */
	private ILiaisonBAC liaisonBAC;

	/** The liaison validation controller. */
	private ValidationController liaisonValidationController;

	/**
	 * Gets the liaison bac.
	 *
	 * @return the liaison bac
	 */
	public ILiaisonBAC getLiaisonBAC()
	{
		return liaisonBAC;
	}

	/**
	 * Gets the liaison validation controller.
	 *
	 * @return the liaison validation controller
	 */
	public ValidationController getLiaisonValidationController()
	{
		return liaisonValidationController;
	}

	/**
	 * Sets the liaison validation controller.
	 *
	 * @param liaisonValidationController the liaison validation controller
	 */
	public void setLiaisonValidationController(ValidationController liaisonValidationController)
	{
		this.liaisonValidationController = liaisonValidationController;
	}

	/**
	 * Sets the liaison bac.
	 *
	 * @param liaisonBAC the liaison bac
	 */
	public void setLiaisonBAC(ILiaisonBAC liaisonBAC)
	{
		this.liaisonBAC = liaisonBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.ILiaisonBAI#insertLiaison(com.prosperitasglobal.sendsolv.model.request.
	 * LiaisonRequest)
	 */
	@Override
	public LiaisonResponse insertLiaison(LiaisonMaintenanceRequest request)
	{
		LiaisonResponse response = new LiaisonResponse();
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

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.ILiaisonBAI#updateLiaison(com.prosperitasglobal.sendsolv.model.request.
	 * LiaisonRequest)
	 */
	@Override
	public LiaisonResponse updateLiaison(LiaisonMaintenanceRequest request)
	{
		LiaisonResponse response = new LiaisonResponse();
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

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.ILiaisonBAI#deleteLiaison(com.prosperitasglobal.sendsolv.model.request.
	 * LiaisonRequest)
	 */
	@Override
	public LiaisonResponse deleteLiaison(LiaisonMaintenanceRequest request)
	{
		LiaisonResponse response = new LiaisonResponse();
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILiaisonBAI#fetchLiaisonById(com.prosperitasglobal.sendsolv.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public LiaisonResponse fetchLiaisonById(FetchByIdRequest request)
	{
		LiaisonResponse response = new LiaisonResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getLiaisonBAC().fetchLiaisonById(request);
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILiaisonBAI#fetchLiaisonByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .PagedInquiryRequest)
	 */
	@Override
	public LiaisonResponse fetchLiaisonByRequest(PagedInquiryRequest request)
	{
		LiaisonResponse response = new LiaisonResponse();
		try
		{
			fetchPaged(request, response);
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
	 * com.prosperitasglobal.sendsolv.bai.ILiaisonBAI#fetchLiaisonByOrganization(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public LiaisonResponse fetchLiaisonByOrganization(PagedInquiryRequest request)
	{
		return fetchLiaisonByOrgLoc(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILiaisonBAI#fetchLiaisonByLocation
	 * (com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest)
	 */
	@Override
	public LiaisonResponse fetchLiaisonByLocation(PagedInquiryRequest request)
	{
		return fetchLiaisonByOrgLoc(request);
	}

	/**
	 * Validates a request and process persistence.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the liaison response
	 */
	private LiaisonResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			LiaisonMaintenanceRequest request)
	{
		LiaisonResponse response = new LiaisonResponse();
		InternalResponse internalResponse = null;

		// Validate.
		ValidationContext context =
				new ValidationContext(Liaison.class.getSimpleName(), request.getLiaison(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getLiaisonValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		return (LiaisonResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private Response handleReturn(Response response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		// In the case there was an Optimistic Locking error, add the specific message
		if (!ValidationUtil.isNull(internalResponse) && !ValidationUtil.isNull(internalResponse.getStatus())
				&& Status.OptimisticLockingError.equals(internalResponse.getStatus()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_OL_ERROR, MessageSeverity.Error,
					MessageLevel.Object));
		}

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do database persistence.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(LiaisonMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getLiaisonBAC().insertLiaison(request);

			case UPDATE:
				return getLiaisonBAC().updateLiaison(request);

			case DELETE:
				return getLiaisonBAC().deleteLiaison(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	/**
	 * Fetch {@link List} of {@link Liaison}.
	 *
	 * @param request the request
	 */
	private void fetchPaged(PagedInquiryRequest request, LiaisonResponse response)
	{
		InternalResultsResponse<Liaison> internalResponse = new InternalResultsResponse<Liaison>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getLiaisonBAC().fetchLiaisonByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch {@link List} of {@link Liaison} by {@link Organization} or {@link Location}.
	 *
	 * @param request the request
	 * @return {@link LiaisonResponse}
	 */
	private LiaisonResponse fetchLiaisonByOrgLoc(PagedInquiryRequest request)
	{
		LiaisonResponse response = new LiaisonResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();

			// validate fetchId field
			if (ValidationUtil.isNull(request.getParentId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_PARENT_ID_REQUIRED);
			}
			else
			{
				internalResponse = getLiaisonBAC().fetchLiaisonByRequest(request);
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
}
