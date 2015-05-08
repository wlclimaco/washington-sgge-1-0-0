package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IOrganizationBAC;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.prosperitasglobal.sendsolv.model.response.RiskResponse;
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
 * The Class OrganizationBAIImpl.
 */
public class OrganizationBAIImpl implements IOrganizationBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = OrganizationBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.organizationvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The organization bac. */
	private IOrganizationBAC organizationBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/** The organization paged inquiry request validation controller. */
	private ValidationController organizationPagedInquiryRequestValidationController;

	/** The note validation controller. */
	private ValidationController noteValidationController;

	/** The document validation controller. */
	private ValidationController documentValidationController;

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
	 * Gets the note validation controller.
	 *
	 * @return the note validation controller
	 */
	public ValidationController getNoteValidationController()
	{
		return noteValidationController;
	}

	/**
	 * Sets the note validation controller.
	 *
	 * @param noteValidationController the note validation controller
	 */
	public void setNoteValidationController(ValidationController noteValidationController)
	{
		this.noteValidationController = noteValidationController;
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
	 * Gets the organization paged inquiry request validation controller.
	 *
	 * @return the organization paged inquiry request validation controller
	 */
	public ValidationController getOrganizationPagedInquiryRequestValidationController()
	{
		return organizationPagedInquiryRequestValidationController;
	}

	/**
	 * Sets the organization paged inquiry request validation controller.
	 *
	 * @param organizationPagedInquiryRequestValidationController the organization paged inquiry request validation
	 *            controller
	 */
	public void setOrganizationPagedInquiryRequestValidationController(
			ValidationController organizationPagedInquiryRequestValidationController)
	{
		this.organizationPagedInquiryRequestValidationController = organizationPagedInquiryRequestValidationController;
	}

	/**
	 * Spring Sets the organization bac.
	 *
	 * @param organizationBAC the new organization bac
	 */
	public void setOrganizationBAC(IOrganizationBAC organizationBAC)
	{
		this.organizationBAC = organizationBAC;
	}

	/**
	 * Gets the organization bac.
	 *
	 * @return the organization bac
	 */
	public IOrganizationBAC getOrganizationBAC()
	{
		return organizationBAC;
	}

	/**
	 * Gets the document validation controller.
	 *
	 * @return the document validation controller
	 */
	public ValidationController getDocumentValidationController()
	{
		return documentValidationController;
	}

	/**
	 * Sets the document validation controller.
	 *
	 * @param documentValidationController the document validation controller
	 */
	public void setDocumentValidationController(ValidationController documentValidationController)
	{
		this.documentValidationController = documentValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#insertOrganization(com.prosperitasglobal.sendsolv.model
	 * .request.OrganizationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public OrganizationResponse insertOrganization(OrganizationMaintenanceRequest request)
	{
		OrganizationResponse response = new OrganizationResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#updateOrganization(com.prosperitasglobal.sendsolv.model
	 * .request.OrganizationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public OrganizationResponse updateOrganization(OrganizationMaintenanceRequest request)
	{
		OrganizationResponse response = new OrganizationResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#deleteOrganization(com.prosperitasglobal.sendsolv.model
	 * .request.OrganizationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public OrganizationResponse deleteOrganization(OrganizationMaintenanceRequest request)
	{
		OrganizationResponse response = new OrganizationResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#fetchOrganizationById(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .OrganizationRequest)
	 */
	@Override
	public OrganizationResponse fetchOrganizationById(FetchByIdRequest request)
	{
		OrganizationResponse response = new OrganizationResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getOrganizationBAC().fetchOrganizationById(request);
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
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#fetchOrganizationByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public OrganizationResponse fetchOrganizationByRequest(PagedInquiryRequest request)
	{
		OrganizationResponse response = new OrganizationResponse();
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#insertDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public DocumentResponse insertDocument(DocumentMaintenanceRequest request)
	{
		DocumentResponse response = new DocumentResponse();
		InternalResultsResponse<Document> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext(ValidationContextIndicator.INSERT);
			context.putObjectToBeValidated(Document.class.getSimpleName(), request.getDocument());
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getDocumentValidationController().validate(context))
			{
				internalResponse = getOrganizationBAC().insertDocument(request);
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
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#updateDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public DocumentResponse updateDocument(DocumentMaintenanceRequest request)
	{
		DocumentResponse response = new DocumentResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext(ValidationContextIndicator.UPDATE);
			context.putObjectToBeValidated(Document.class.getSimpleName(), request.getDocument());
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getDocumentValidationController().validate(context))
			{
				internalResponse = getOrganizationBAC().updateDocument(request);
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
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#deleteDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public DocumentResponse deleteDocument(DocumentMaintenanceRequest request)
	{
		DocumentResponse response = new DocumentResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext(ValidationContextIndicator.DELETE);
			context.putObjectToBeValidated(Document.class.getSimpleName(), request.getDocument());

			if (getDocumentValidationController().validate(context))
			{
				internalResponse = getOrganizationBAC().deleteDocument(request);
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
	 * @see com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */
	@Override
	public RiskResponse updateRisk(RiskMaintenanceRequest request)
	{
		RiskResponse response = new RiskResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext(ValidationContextIndicator.UPDATE);
			context.putObjectToBeValidated(Risk.class.getSimpleName(), request.getRisk());
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getRiskValidationController().validate(context))
			{
				internalResponse = getOrganizationBAC().updateRisk(request);
			}

			response = (RiskResponse)handleReturn(response, internalResponse, context.getMessages(), true);

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
	 * com.prosperitasglobal.sendsolv.bai.IOrganizationBAI#applyStatus(com.prosperitasglobal.sendsolv.model.request.
	 * OrganizationMaintenanceRequest)
	 */
	@Override
	public OrganizationResponse applyStatus(OrganizationMaintenanceRequest request)
	{
		OrganizationResponse response = new OrganizationResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate.
			ValidationContext context =
					new ValidationContext(Organization.class.getSimpleName(), request.getOrganization(),
							ValidationContextIndicator.FETCH_BY_ID);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getValidationController().validate(context))
			{
				internalResponse = getOrganizationBAC().applyOrganizationStatus(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private OrganizationResponse fetchPaged(PagedInquiryRequest request, OrganizationResponse response)
	{
		InternalResultsResponse<Organization> internalResponse = new InternalResultsResponse<Organization>();

		ValidationContext context =
				new ValidationContext(PagedInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getOrganizationPagedInquiryRequestValidationController().validate(context))
		{
			internalResponse = getOrganizationBAC().fetchOrganizationByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (OrganizationResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the organization response
	 */
	private OrganizationResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			OrganizationMaintenanceRequest request)
	{
		OrganizationResponse response = new OrganizationResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Organization.class.getSimpleName(), request.getOrganization(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (OrganizationResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the organization response
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
	private InternalResponse doPersistance(OrganizationMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getOrganizationBAC().insertOrganization(request);

			case UPDATE:
				return getOrganizationBAC().updateOrganization(request);

			case DELETE:
				return getOrganizationBAC().deleteOrganization(request);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType for Organization missing!");
				}
				break;
		}

		return null;
	}
}
