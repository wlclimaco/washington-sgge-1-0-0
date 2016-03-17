package com.qat.samples.sysmgmt.arquivo.bai.impl;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.arquivo.Arquivo;
import com.qat.samples.sysmgmt.arquivo.bac.IArquivoBAC;
import com.qat.samples.sysmgmt.arquivo.bai.IArquivoBAI;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.response.ArquivoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Class ArquivoBAIImpl.
 */
public class ArquivoBAIImpl implements IArquivoBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ArquivoBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ArquivoBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.arquivovalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The arquivo bac. */
	private IArquivoBAC arquivoBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get arquivo validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the arquivo validation controller.
	 * 
	 * @param arquivoValidationController the new validation controller
	 */
	public void setValidationController(ValidationController arquivoValidationController)
	{
		validationController = arquivoValidationController;
	}

	/**
	 * Spring Sets the arquivo bac.
	 * 
	 * @param arquivoBAC the new arquivo bac
	 */
	public void setArquivoBAC(IArquivoBAC arquivoBAC)
	{
		this.arquivoBAC = arquivoBAC;
	}

	/**
	 * Gets the arquivo bac.
	 * 
	 * @return the arquivo bac
	 */
	public IArquivoBAC getArquivoBAC()
	{
		return arquivoBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#insertLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public ArquivoResponse insertArquivo(ArquivoMaintenanceRequest request)
	{
		ArquivoResponse response = new ArquivoResponse();
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
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#updateLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public ArquivoResponse updateArquivo(ArquivoMaintenanceRequest request)
	{
		ArquivoResponse response = new ArquivoResponse();
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
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#deleteLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public ArquivoResponse deleteArquivo(ArquivoMaintenanceRequest request)
	{
		ArquivoResponse response = new ArquivoResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#fetchLocaationById(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .CountyRequest)
	 */
	@Override
	public ArquivoResponse fetchArquivoById(FetchByIdRequest request)
	{
		ArquivoResponse response = new ArquivoResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getArquivoBAC().fetchArquivoById(request);
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
	 * com.prosperitasglobal.sendsolv.bai.IArquivoBAI#fetchArquivoByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public ArquivoResponse fetchArquivoByRequest(ArquivoInquiryRequest request)
	{
		ArquivoResponse response = new ArquivoResponse();
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
	 * @see com.prosperitasglobal.sendsolv.bai.IArquivoBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(ArquivoInquiryRequest request, ArquivoResponse response)
	{
		InternalResultsResponse<Arquivo> internalResponse = new InternalResultsResponse<Arquivo>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getArquivoBAC().fetchArquivoByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 * 
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the arquivo response
	 */
	private ArquivoResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			ArquivoMaintenanceRequest request)
	{
		ArquivoResponse response = new ArquivoResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Arquivo.class.getSimpleName(), request.getArquivo(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, context.getMessages(), true);
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
	private ArquivoResponse handleReturn(ArquivoResponse response, InternalResponse internalResponse,
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
	 * Do persistance.
	 * 
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(ArquivoMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getArquivoBAC().insertArquivo(request);

			case UPDATE:
				return getArquivoBAC().updateArquivo(request);

			case DELETE:
				return getArquivoBAC().deleteArquivo(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

}
