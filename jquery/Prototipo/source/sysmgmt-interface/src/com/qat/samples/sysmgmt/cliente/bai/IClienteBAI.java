package com.qat.samples.sysmgmt.cliente.bai;

import com.qat.samples.sysmgmt.cliente.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IClienteBAI. (Business Area Interface - BAI)
 */
public interface IClienteBAI
{

	/**
	 * Insert cliente.
	 * 
	 * @param request the request
	 * @return the cliente paged response
	 */
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request);

	/**
	 * Update cliente.
	 * 
	 * @param request the request
	 * @return the cliente paged response
	 */
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request);

	/**
	 * Delete cliente.
	 * 
	 * @param request the request
	 * @return the cliente paged response
	 */
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request);

	/**
	 * Fetch all clientes.
	 * 
	 * @param request the request
	 * @return the cliente response
	 */
	public ClienteResponse fetchAllClientes(FetchAllRequest request);

	/**
	 * Refresh clientes.
	 * 
	 * @param request the request
	 * @return the cliente paged response
	 */
	public ClienteResponse refreshClientes(RefreshRequest request);

	/**
	 * Fetch cliente by id.
	 * 
	 * @param request the request
	 * @return the cliente response
	 */
	public ClienteResponse fetchClienteById(FetchByIdRequest request);

	/**
	 * Fetch clientes by request.
	 * 
	 * @param request the request
	 * @return the cliente paged response
	 */
	public ClienteResponse fetchClientesByRequest(PagedInquiryRequest request);

}
