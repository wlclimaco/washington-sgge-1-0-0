package com.qat.samples.sysmgmt.cliente.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cliente.model.Cliente;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IClienteBAC. (Business Area Component - BAC)
 */
public interface IClienteBAC
{

	/**
	 * Insert procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertCliente(Cliente procedure);

	/**
	 * Update procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateCliente(Cliente procedure);

	/**
	 * Delete procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteCliente(Cliente procedure);

	/**
	 * Refresh procedures.
	 * 
	 * @param refreshNumber the value of the number of procedures you want refreshed
	 * 
	 */
	public void refreshClientes(Integer refreshNumber);

	/**
	 * Fetch procedure by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request);

	/**
	 * Fetch all procedures.
	 * 
	 * @return the internal results response< procedure>
	 */
	public InternalResultsResponse<Cliente> fetchAllClientes();

	/**
	 * Fetch procedures by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cliente> fetchClientesByRequest(PagedInquiryRequest request);
}
