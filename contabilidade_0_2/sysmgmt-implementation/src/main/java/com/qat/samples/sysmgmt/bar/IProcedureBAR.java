package com.qat.samples.sysmgmt.bar;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface IProcedureBAR. (Data Access Component - DAC)
 */
public interface IProcedureBAR
{

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 * @return the procedure
	 */
	public Procedure fetchProcedureById(FetchByIdRequest request);

	/**
	 * Insert procedure.
	 *
	 * @param procedure the procedure
	 *
	 * @return the internal response
	 */
	public InternalResponse insertProcedure(Procedure procedure);

	/**
	 * Update procedure.
	 *
	 * @param procedure the procedure
	 *
	 * @return the internal response
	 */
	public InternalResponse updateProcedure(Procedure procedure);

	/**
	 * Delete procedure.
	 *
	 * @param procedure the procedure
	 *
	 * @return the internal response
	 */
	public InternalResponse deleteProcedure(Procedure procedure);

	/**
	 * Delete all procedures.
	 *
	 * @return the internal response
	 */
	public InternalResponse deleteAllProcedures();

	/**
	 * Fetch all procedures.
	 *
	 * @return the list< procedure>
	 */
	public List<Procedure> fetchAllProcedures();

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Procedure> fetchProceduresByRequest(PagedInquiryRequest request);

}
