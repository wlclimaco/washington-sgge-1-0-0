package com.qat.samples.sysmgmt.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.bad.ProcedureBAD;
import com.qat.samples.sysmgmt.dac.IProcedureDAC;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * Implementation of the IProcedureBAC leveraging a BAD, ProcedureBAD.
 */
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

	/** The procedure dac. */
	private IProcedureDAC procedureDAC; // injected by Spring through setter

	/**
	 * Spring Sets the procedure dac.
	 * 
	 * @param procedureDAC the new procedure dac
	 */
	public void setProcedureDAC(IProcedureDAC procedureDAC)
	{
		this.procedureDAC = procedureDAC;
	}

	/**
	 * Gets the procedure dac.
	 * 
	 * @return the procedure dac
	 */
	public IProcedureDAC getProcedureDAC()
	{
		return procedureDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProcedureBAC#insertProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse insertProcedure(Procedure procedure)
	{
		procedure.setPrice(ProcedureBAD.calculatePrice(INSERT_SEED));
		return getProcedureDAC().insertProcedure(procedure);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProcedureBAC#updateProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse updateProcedure(Procedure procedure)
	{
		procedure.setPrice(ProcedureBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getProcedureDAC().updateProcedure(procedure);
		// Check for error because in business case all non-success returns are failures (updating of zero rows or
		// optimistic locking error) according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse
							.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProcedureBAC#deleteProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse deleteProcedure(Procedure procedure)
	{
		InternalResponse internalResponse = getProcedureDAC().deleteProcedure(procedure);
		// Check for error because in business case all non-success returns are failures (removal of zero rows)
		// according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse
							.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IProcedureBAC#refreshProcedures(int)
	 */
	@Override
	public void refreshProcedures(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getProcedureDAC().deleteAllProcedures();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getProcedureDAC().insertProcedure(
					new Procedure("PC" + i, "ProcedureDesc" + i, ProcedureBAD.calculatePrice(REFRESH_SEED * i)));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IProcedureBAC#fetchAllProcedures()
	 */
	@Override
	public InternalResultsResponse<Procedure> fetchAllProcedures()
	{
		InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();
		response.getResultsList().addAll(getProcedureDAC().fetchAllProcedures());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProcedureBAC#fetchProcedureById(com.qat.samples.sysmgmt.base.model.Procedure
	 * )
	 */
	@Override
	public InternalResultsResponse<Procedure> fetchProcedureById(FetchByIdRequest request)
	{
		InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();
		response.getResultsList().add(getProcedureDAC().fetchProcedureById(request));
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
		return getProcedureDAC().fetchProceduresByRequest(request);
	}

}
