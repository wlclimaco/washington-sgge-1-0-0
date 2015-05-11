package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.xml.ws.Response;

import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC;
import com.prosperitasglobal.sendsolv.bai.IOrdemServicoBAI;
import com.prosperitasglobal.sendsolv.model.OrdemServico;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.OrdemServicoResponse;
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

/**
 * The Class OrdemServicoBAIImpl.
 */
public class OrdemServicoBAIImpl implements IOrdemServicoBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = OrdemServicoBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = (java.util.logging.Logger)LoggerFactory.getLogger(OrdemServicoBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.ordemServicovalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED =
			"sendsolv.base.ordemServicovalidator.parentorganization.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The organization bac. */
	private IOrdemServicoBAC ordemServicoBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

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
	 * @param ordemServicoBAC the new ordemServico bac
	 */
	public void setOrdemServicoBAC(IOrdemServicoBAC ordemServicoBAC)
	{
		this.ordemServicoBAC = ordemServicoBAC;
	}

	/**
	 * Gets the ordemServico bac.
	 *
	 * @return the ordemServico bac
	 */
	public IOrdemServicoBAC getOrdemServicoBAC()
	{
		return ordemServicoBAC;
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
	public OrdemServicoResponse insertOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException((org.slf4j.Logger)LOG, response, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] {CLASS_NAME});
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
	public OrdemServicoResponse updateOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException((org.slf4j.Logger)LOG, response, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] {CLASS_NAME});
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
	public OrdemServicoResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException((org.slf4j.Logger)LOG, response, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] {CLASS_NAME});
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
	public OrdemServicoResponse fetchOrdemServicoById(FetchByIdRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
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
				internalResponse = getOrdemServicoBAC().fetchOrdemServicoById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException((org.slf4j.Logger)LOG, response, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IOrdemServicoBAI#fetchOrdemServicoByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public OrdemServicoResponse fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			fetchPaged(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException((org.slf4j.Logger)LOG, response, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IOrdemServicoBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(OrdemServicoInquiryRequest request, OrdemServicoResponse response)
	{
		InternalResultsResponse<OrdemServico> internalResponse = new InternalResultsResponse<OrdemServico>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getOrdemServicoBAC().fetchOrdemServicoByRequest(request);
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
	private OrdemServicoResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			OrdemServicoMaintenanceRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(OrdemServico.class.getSimpleName(), request.getOrdemServico(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (OrdemServicoResponse)handleReturn((Response)response, internalResponse, context.getMessages(), true);
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

		QATInterfaceUtil.handleOperationStatusAndMessages((com.qat.framework.model.response.Response)response,
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
	private InternalResponse doPersistance(OrdemServicoMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getOrdemServicoBAC().insertOrdemServico(request);

			case UPDATE:
				return getOrdemServicoBAC().updateOrdemServico(request);

			case DELETE:
				return getOrdemServicoBAC().deleteOrdemServico(request);

			default:
				if (((org.slf4j.Logger)LOG).isDebugEnabled())
				{
					((org.slf4j.Logger)LOG).debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	@Override
	public OrdemServicoResponse fetchOrdemServicoByOrganization(OrdemServicoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
