package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IHistoricoBAC;
import com.prosperitasglobal.sendsolv.bai.IHistoricoBAI;
import com.prosperitasglobal.sendsolv.model.Alertas;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.request.AlertasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.AlertasResponse;
import com.prosperitasglobal.sendsolv.model.response.HistoricoResponse;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class HistoricoBAIImpl.
 */
public class HistoricoBAIImpl implements IHistoricoBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = HistoricoBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HistoricoBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.historicovalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The historico bac. */
	private IHistoricoBAC historicoBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get historico validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the historico validation controller.
	 *
	 * @param historicoValidationController the new validation controller
	 */
	public void setValidationController(ValidationController historicoValidationController)
	{
		validationController = historicoValidationController;
	}

	/**
	 * Spring Sets the historico bac.
	 *
	 * @param historicoBAC the new historico bac
	 */
	public void setHistoricoBAC(IHistoricoBAC historicoBAC)
	{
		this.historicoBAC = historicoBAC;
	}

	/**
	 * Gets the historico bac.
	 *
	 * @return the historico bac
	 */
	public IHistoricoBAC getHistoricoBAC()
	{
		return historicoBAC;
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
	public HistoricoResponse insertHistorico(HistoricoMaintenanceRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();
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
	public HistoricoResponse updateHistorico(HistoricoMaintenanceRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();
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
	public HistoricoResponse deleteHistorico(HistoricoMaintenanceRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();
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
	public HistoricoResponse fetchHistoricoById(FetchByIdRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();
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
				internalResponse = getHistoricoBAC().fetchHistoricoById(request);
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
	 * com.prosperitasglobal.sendsolv.bai.IHistoricoBAI#fetchHistoricoByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public HistoricoResponse fetchHistoricoByRequest(HistoricoInquiryRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();
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
	 * @see com.prosperitasglobal.sendsolv.bai.IHistoricoBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(HistoricoInquiryRequest request, HistoricoResponse response)
	{
		InternalResultsResponse<Historico> internalResponse = new InternalResultsResponse<Historico>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getHistoricoBAC().fetchHistoricoByRequest(request);
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
	 * @return the historico response
	 */
	private HistoricoResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			HistoricoMaintenanceRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Historico.class.getSimpleName(), request.getHistorico(), indicator);
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
	private HistoricoResponse handleReturn(HistoricoResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response,
				internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(HistoricoMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getHistoricoBAC().insertHistorico(request);

			case UPDATE:
				return getHistoricoBAC().updateHistorico(request);

			case DELETE:
				return getHistoricoBAC().deleteHistorico(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	@Override
	public AlertasResponse fetchAlertasByRequest(AlertasInquiryRequest request)
	{
		AlertasResponse response = new AlertasResponse();
		try
		{
			fetchPagedAlertas(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IHistoricoBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedAlertas(AlertasInquiryRequest request, AlertasResponse response)
	{
		InternalResultsResponse<Alertas> internalResponse = new InternalResultsResponse<Alertas>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getHistoricoBAC().fetchAlertasByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}
}
