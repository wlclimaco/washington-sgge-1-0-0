package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.xml.ws.Response;

import com.prosperitasglobal.sendsolv.bac.ITabelaBAC;

/**
 * The Class TabelaBAIImpl.
 */
public class TabelaBAIImpl implements ITabelaBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = TabelaBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TabelaBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.locationvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED =
			"sendsolv.base.locationvalidator.parentorganization.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The organization bac. */
	private ITabelaBAC locationBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/** The risk validation controller. */
	private ValidationController riskValidationController;

	/**
	 * Gets the risk validation controller.
	 *
	 * @return the risk validation controller
	 */
	public ValidationController getRiskValidationController()
	{
		return riskValidationController;
	}

	/**
	 * Sets the risk validation controller.
	 *
	 * @param riskValidationController the risk validation controller
	 */
	public void setRiskValidationController(ValidationController riskValidationController)
	{
		this.riskValidationController = riskValidationController;
	}

	/**
	 * Get organization validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the organization validation controller.
	 *
	 * @param organizationValidationController the new validation controller
	 */
	public void setValidationController(ValidationController organizationValidationController)
	{
		validationController = organizationValidationController;
	}

	/**
	 * Spring Sets the organization bac.
	 *
	 * @param locationBAC the new location bac
	 */
	public void setTabelaBAC(ITabelaBAC locationBAC)
	{
		this.locationBAC = locationBAC;
	}

	/**
	 * Gets the location bac.
	 *
	 * @return the location bac
	 */
	public ITabelaBAC getTabelaBAC()
	{
		return locationBAC;
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
	public TabelaResponse insertTabela(TabelaMaintenanceRequest request)
	{
		TabelaResponse response = new TabelaResponse();
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
	public TabelaResponse updateTabela(TabelaMaintenanceRequest request)
	{
		TabelaResponse response = new TabelaResponse();
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
	public TabelaResponse deleteTabela(TabelaMaintenanceRequest request)
	{
		TabelaResponse response = new TabelaResponse();
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
	public TabelaResponse fetchTabelaById(FetchByIdRequest request)
	{
		TabelaResponse response = new TabelaResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getTabelaBAC().fetchTabelaById(request);
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
	 * com.prosperitasglobal.sendsolv.bai.ITabelaBAI#fetchTabelaByOrganization(com.prosperitasglobal.sendsolv.
	 * model.request.PagedInquiryRequest)
	 */
	@Override
	public TabelaResponse fetchTabelaByOrganization(PagedInquiryRequest request)
	{
		TabelaResponse response = new TabelaResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();

			// validate fetchId field
			if (ValidationUtil.isNull(request.getParentId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED);
			}
			else
			{
				internalResponse = getTabelaBAC().fetchTabelaByRequest(request);
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
	 * com.prosperitasglobal.sendsolv.bai.ITabelaBAI#fetchTabelaByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public TabelaResponse fetchTabelaByRequest(PagedInquiryRequest request)
	{
		TabelaResponse response = new TabelaResponse();
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
	 * @see com.prosperitasglobal.sendsolv.bai.ITabelaBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(PagedInquiryRequest request, TabelaResponse response)
	{
		InternalResultsResponse<Tabela> internalResponse = new InternalResultsResponse<Tabela>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getTabelaBAC().fetchTabelaByRequest(request);
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
	 * @return the organization response
	 */
	private TabelaResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			TabelaMaintenanceRequest request)
	{
		TabelaResponse response = new TabelaResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Tabela.class.getSimpleName(), request.getTabela(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (TabelaResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(TabelaMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getTabelaBAC().insertTabela(request);

			case UPDATE:
				return getTabelaBAC().updateTabela(request);

			case DELETE:
				return getTabelaBAC().deleteTabela(request);

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
