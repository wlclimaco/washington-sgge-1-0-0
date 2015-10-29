package com.qat.samples.sysmgmt.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.bai.IProcedureBAI;
import com.qat.samples.sysmgmt.bas.IProcedureBAS;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt")
public class ProcedureBAS implements IProcedureBAS
{

	/** The procedure bai. */
	private IProcedureBAI procedureBAI; // injected by Spring through setter

	/**
	 * Spring Sets the procedure bai.
	 * 
	 * @param procedureBAI the new procedure bai
	 */
	public void setProcedureBAI(IProcedureBAI procedureBAI)
	{
		this.procedureBAI = procedureBAI;
	}

	/**
	 * Gets the procedure bac.
	 * 
	 * @return the procedure bac
	 */
	public IProcedureBAI getProcedureBAI()
	{
		return procedureBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IProcedureBAS#insertProcedure(com.qat.samples.sysmgmt.model.request.
	 * ProcedureMaintenanceRequest)
	 */
	@Override
	public ProcedureResponse insertProcedure(ProcedureMaintenanceRequest request)
	{
		return getProcedureBAI().insertProcedure(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IProcedureBAS#updateProcedure(com.qat.samples.sysmgmt.model.request.
	 * ProcedureMaintenanceRequest)
	 */
	@Override
	public ProcedureResponse updateProcedure(ProcedureMaintenanceRequest request)
	{
		return getProcedureBAI().updateProcedure(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IProcedureBAS#deleteProcedure(com.qat.samples.sysmgmt.model.request.
	 * ProcedureMaintenanceRequest)
	 */
	@Override
	public ProcedureResponse deleteProcedure(ProcedureMaintenanceRequest request)
	{
		return getProcedureBAI().deleteProcedure(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IProcedureBAS#refreshProcedures(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public ProcedureResponse refreshProcedures(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		return getProcedureBAI().refreshProcedures(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IProcedureBAS#fetchAllProcedures(com.qat.samples.sysmgmt.model.request.FetchAllRequest
	 * )
	 */
	@Override
	public ProcedureResponse fetchAllProcedures(FetchAllRequest request)
	{
		return getProcedureBAI().fetchAllProcedures(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IProcedureBAS#fetchProcedureById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public ProcedureResponse fetchProcedureById(FetchByIdRequest request)
	{
		return getProcedureBAI().fetchProcedureById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IProcedureBAS#fetchProceduresByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public ProcedureResponse fetchProceduresByRequest(PagedInquiryRequest request)
	{
		return getProcedureBAI().fetchProceduresByRequest(request);
	}
}
