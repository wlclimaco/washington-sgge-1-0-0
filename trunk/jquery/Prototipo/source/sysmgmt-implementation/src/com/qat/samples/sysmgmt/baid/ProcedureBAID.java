package com.qat.samples.sysmgmt.baid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;

/**
 * Delegate class for Procedure BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ProcedureBAID
{

	/** The Constant DEFAULT_PROCEDURE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCEDURE_BAID_EXCEPTION_MSG = "sysmgmt.base.procedurebaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private ProcedureBAID()
	{
	}

	/**
	 * Maintain procedure.
	 * 
	 * @param procedureBAC the procedure bac
	 * @param validationIndicator the validation indicator
	 * @param controller the controller
	 * @param persistType the persist type
	 * @param request the request
	 * @param response the response
	 */
	public static void maintainProcedure(IProcedureBAC procedureBAC, ValidationContextIndicator validationIndicator, ValidationController controller,
			PersistanceActionEnum persistType, ProcedureMaintenanceRequest request, ProcedureResponse response)
	{
		ValidationContext context = new ValidationContext(Procedure.class.getSimpleName(), request.getProcedure(), validationIndicator);

		InternalResponse internalResponse = new InternalResponse();
		if (controller.validate(context))
		{
			// perform persistence
			switch (persistType)
			{
				case INSERT:
					internalResponse = procedureBAC.insertProcedure(request.getProcedure());
					break;
				case UPDATE:
					internalResponse = procedureBAC.updateProcedure(request.getProcedure());
					break;
				case DELETE:
					internalResponse = procedureBAC.deleteProcedure(request.getProcedure());
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("persistType missing! Setting Unspecified Error status.");
					}
					internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
					break;
			}

			// If the persistence worked
			if (internalResponse.getStatus() == Status.OperationSuccess)
			{
				// Call maintain to see if we need to return the county list and if so whether it should be paged or not
				maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, procedureBAC);
			}
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Refresh procedures.
	 * 
	 * @param procedureBAC the procedure bac
	 * @param request the request
	 * @param response the response
	 */
	public static void refreshProcedures(IProcedureBAC procedureBAC, RefreshRequest request, ProcedureResponse response)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		procedureBAC.refreshProcedures(request.getRefreshInt());
		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, procedureBAC);
	}

	/**
	 * Fetch all procedures.
	 * 
	 * @param procedureBAC the procedure bac
	 * @param response the response
	 */
	public static void fetchAllProcedures(IProcedureBAC procedureBAC, ProcedureResponse response)
	{
		InternalResultsResponse<Procedure> internalResponse = procedureBAC.fetchAllProcedures();
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_PROCEDURE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch procedures paged.
	 * 
	 * @param procedureBAC the procedure bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchProceduresPaged(IProcedureBAC procedureBAC, PagedInquiryRequest request, ProcedureResponse response)
	{
		InternalResultsResponse<Procedure> internalResponse = procedureBAC.fetchProceduresByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_PROCEDURE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch procedure by id.
	 * 
	 * @param procedureBAC the procedure bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchProcedureById(IProcedureBAC procedureBAC, FetchByIdRequest request, ProcedureResponse response)
	{
		InternalResultsResponse<Procedure> internalResponse = new InternalResultsResponse<Procedure>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = procedureBAC.fetchProcedureById(request);
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Maintain return list.
	 * 
	 * @param listIndicator the list indicator
	 * @param pageListIndicator the page list indicator
	 * @param response the response
	 * @param procedureBAC the procedure bac
	 */
	private static void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator, ProcedureResponse response, IProcedureBAC procedureBAC)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				fetchProceduresPaged(procedureBAC, request, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllProcedures(procedureBAC, response);
			}
		}
	}
}
