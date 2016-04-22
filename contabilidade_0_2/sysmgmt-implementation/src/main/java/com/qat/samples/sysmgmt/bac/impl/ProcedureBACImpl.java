package com.qat.samples.sysmgmt.bac.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.bac.delegate.ProcedureBACD;
import com.qat.samples.sysmgmt.bar.IProcedureBAR;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Implementation of the IProcedureBAC leveraging a BAD, ProcedureBACD.
 */
@Component
public class ProcedureBACImpl implements IProcedureBAC
{

	/** The Constant REFRESH_SEED. */
	private static final int REFRESH_SEED = 1050;

	/** The Constant UPDATE_SEED. */
	private static final int UPDATE_SEED = 3000;

	/** The Constant INSERT_SEED. */
	private static final int INSERT_SEED = 9000;

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG = "sysmgmt.base.procedurebacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureBACImpl.class);

	/** The procedure BAR. */
	private IProcedureBAR procedureBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the procedure BAR.
	 *
	 * @param procedureBAR the new procedure BAR
	 */
	public void setProcedureBAR(IProcedureBAR procedureBAR)
	{
		this.procedureBAR = procedureBAR;
	}

	/**
	 * Gets the procedure BAR.
	 *
	 * @return the procedure BAR
	 */
	public IProcedureBAR getProcedureBAR()
	{
		return procedureBAR;
	}

	/**
	 * Gets the validation controller
	 *
	 * @return ValidationController
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation controller
	 *
	 * @param validationController
	 */
	public void setValidationController(ValidationController validationController)
	{
		this.validationController = validationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IProcedureBAC#insertProcedure(com.qat.samples.sysmgmt.model.request.
	 * ProcedureMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Procedure> insertProcedure(ProcedureMaintenanceRequest request)
	{
		request.getProcedure().setPrice(ProcedureBACD.calculatePrice(INSERT_SEED));

		InternalResultsResponse<Procedure> response =
				process(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IProcedureBAC#updateProcedure(com.qat.samples.sysmgmt.model.request.
	 * ProcedureMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Procedure> updateProcedure(ProcedureMaintenanceRequest request)
	{
		request.getProcedure().setPrice(ProcedureBACD.calculatePrice(UPDATE_SEED));

		InternalResultsResponse<Procedure> response =
				process(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IProcedureBAC#deleteProcedure(com.qat.samples.sysmgmt.model.request.
	 * ProcedureMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Procedure> deleteProcedure(ProcedureMaintenanceRequest request)
	{
		InternalResultsResponse<Procedure> response =
				process(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IProcedureBAC#refreshProcedures(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Procedure> refreshProcedures(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getProcedureBAR().deleteAllProcedures();

		int refreshNumber = request.getRefreshInt();

		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getProcedureBAR().insertProcedure(
					new Procedure("PC" + i, "ProcedureDesc" + i, ProcedureBACD.calculatePrice(REFRESH_SEED * i)));
		}

		// Call maintain to see if we need to return the drug list and if so whether it should be paged or not
		return maintainReturnList(request.getReturnList(), request.getReturnListPaged());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IProcedureBAC#fetchAllProcedures()
	 */
	@Override
	public InternalResultsResponse<Procedure> fetchAllProcedures()
	{
		InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();
		response.getResultsList().addAll(getProcedureBAR().fetchAllProcedures());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IProcedureBAC#fetchProcedureById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Procedure> fetchProcedureById(FetchByIdRequest request)
	{
		InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();

		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getProcedureBAR().fetchProcedureById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IProcedureBAC#fetchProceduresByRequest(com.qat.samples.sysmgmt.model.request.
	 * ProcedureInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Procedure> fetchProceduresByRequest(PagedInquiryRequest request)
	{
		return getProcedureBAR().fetchProceduresByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the procedure response
	 */
	private InternalResultsResponse<Procedure> process(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			ProcedureMaintenanceRequest request)
	{
		InternalResultsResponse<Procedure> response = null;

		// Validate
		ValidationContext context =
				new ValidationContext(Procedure.class.getSimpleName(), request.getProcedure(), indicator);
		if (!getValidationController().validate(context))
		{
			response = new InternalResultsResponse<Procedure>();
			response.setStatus(SystemErrorCategory.SystemValidation);
			response.addMessages(context.getMessages());
			return response;
		}

		// Persist
		InternalResponse internalResponse = doPersistence(request.getProcedure(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Procedure>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the county list and if so whether it should be paged or
		// not
		response = maintainReturnList(request.getReturnList(), request.getReturnListPaged());

		return response;
	}

	/**
	 * Do persistence.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistence(Procedure procedure, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProcedureBAR().insertProcedure(procedure);

			case UPDATE:
				return getProcedureBAR().updateProcedure(procedure);

			case DELETE:
				return getProcedureBAR().deleteProcedure(procedure);
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
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Procedure> maintainReturnList(Boolean listIndicator, Boolean pageListIndicator)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchProceduresByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllProcedures();
			}
		}
		else
		{
			return new InternalResultsResponse<Procedure>();
		}
	}
}
