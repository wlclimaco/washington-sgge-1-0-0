package com.qat.samples.sysmgmt.cliente.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cliente.model.Cliente;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.response.InternalResponseLocal;

/**
 * The Interface IClienteDAC. (Data Access Component - DAC)
 */
public interface IClienteDAC
{

	/**
	 * Insert cliente.
	 * 
	 * @param cliente the cliente
	 * @return the internal response
	 */
	public InternalResponseLocal insertCliente(Cliente cliente);

	/**
	 * Update cliente.
	 * 
	 * @param cliente the cliente
	 * 
	 * @return the internal response
	 */
	public InternalResponseLocal updateCliente(Cliente cliente);

	/**
	 * Delete cliente.
	 * 
	 * @param cliente the cliente
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteCliente(Cliente cliente);

	/**
	 * Delete all clientes.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllClientes();

	/**
	 * Fetch all clientes.
	 * 
	 * @return the list< cliente>
	 */
	public List<Cliente> fetchAllClientes();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Cliente fetchClienteById(FetchByIdRequest request);

	/**
	 * Fetch clientes by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cliente> fetchClientesByRequest(PagedInquiryRequest request);

}
