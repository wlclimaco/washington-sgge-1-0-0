package com.qat.samples.sysmgmt.bai;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;

/**
 * The Interface IProcedureBAI. (Business Area Interface - BAI)
 */
public interface IProcedureBAI
{

	/**
	 * Insert procedure.
	 * 
	 * @param request the request
	 * @return the procedure paged response
	 */
	public ProcedureResponse insertProcedure(ProcedureMaintenanceRequest request);

	/**
	 * Update procedure.
	 * 
	 * @param request the request
	 * @return the procedure paged response
	 */
	public ProcedureResponse updateProcedure(ProcedureMaintenanceRequest request);

	/**
	 * Delete procedure.
	 * 
	 * @param request the request
	 * @return the procedure paged response
	 */
	public ProcedureResponse deleteProcedure(ProcedureMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 * 
	 * @param request the request
	 * @return the procedure paged response
	 */
	public ProcedureResponse refreshProcedures(RefreshRequest request);

	/**
	 * Fetch all procedures.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	public ProcedureResponse fetchAllProcedures(FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	public ProcedureResponse fetchProcedureById(FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 * 
	 * @param request the request
	 * @return the procedure paged response
	 */
	public ProcedureResponse fetchProceduresByRequest(PagedInquiryRequest request);

}
