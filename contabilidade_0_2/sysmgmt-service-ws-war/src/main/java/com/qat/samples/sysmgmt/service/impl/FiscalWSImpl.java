package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class ProcedureWSImpl implements com.qat.samples.sysmgmt.service.IProcedureWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.procedurewsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.procedurewsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ProcedureWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureWSImpl.class);

	/** The procedure BAC. */
	private IProcedureBAC procedureBAC; // injected by Spring through setter

	/**
	 * Spring Sets the procedure BAC.
	 *
	 * @param procedureBAC the new procedure BAC
	 */
	public void setProcedureBAC(IProcedureBAC procedureBAC)
	{
		this.procedureBAC = procedureBAC;
	}

	/**
	 * Gets the procedure bac.
	 *
	 * @return the procedure bac
	 */
	public IProcedureBAC getProcedureBAC()
	{
		return procedureBAC;
	}

	@Override
	public ProcedureResponse insertProcedure(ProcedureMaintenanceRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();

		try
		{
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().insertProcedure(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProcedureResponse updateProcedure(ProcedureMaintenanceRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();

		try
		{
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().updateProcedure(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProcedureResponse deleteProcedure(ProcedureMaintenanceRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();

		try
		{
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().deleteProcedure(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProcedureResponse refreshProcedures(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcedureResponse response = new ProcedureResponse();

		try
		{
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().refreshProcedures(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProcedureResponse fetchAllProcedures(FetchAllRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();

		try
		{
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().fetchAllProcedures();
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IProcedureWS#fetchProcedureById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ProcedureResponse fetchProcedureById(FetchByIdRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();

		try
		{
			InternalResultsResponse<Procedure> internalResponse = new InternalResultsResponse<Procedure>();

			internalResponse = getProcedureBAC().fetchProcedureById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProcedureResponse fetchProceduresByRequest(PagedInquiryRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();

		try
		{
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().fetchProceduresByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}
}
