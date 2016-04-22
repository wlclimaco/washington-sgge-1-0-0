package com.qat.samples.sysmgmt.bac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface IProcedureBAC
{

	/**
	 * Insert procedure.
	 *
	 * @param request the procedure maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Procedure> insertProcedure(ProcedureMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the procedure maintenance request
	 *
	 * @return the internal response
	 */
	public InternalResultsResponse<Procedure> updateProcedure(ProcedureMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the procedure maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Procedure> deleteProcedure(ProcedureMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request refreshRequest containing the value of the number of procedures you want refreshed
	 * @return internal results response
	 */
	public InternalResultsResponse<Procedure> refreshProcedures(RefreshRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Procedure> fetchProcedureById(FetchByIdRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @return the internal results response< procedure>
	 */
	public InternalResultsResponse<Procedure> fetchAllProcedures();

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Procedure> fetchProceduresByRequest(PagedInquiryRequest request);
}
