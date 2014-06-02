package com.qat.samples.sysmgmt.cliente.bas.rest;

import com.qat.samples.sysmgmt.cliente.bai.IClienteBAI;
import com.qat.samples.sysmgmt.cliente.bas.IClienteRESTBAS;
import com.qat.samples.sysmgmt.cliente.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class ClienteBAS implements IClienteRESTBAS
{

	/** The cliente bai. */
	private IClienteBAI clienteBAI; // injected by Spring through setter

	/**
	 * Sets the cliente bai.
	 * 
	 * @param clienteBAI the new cliente bai
	 */
	public void setClienteBAI(IClienteBAI clienteBAI)
	{
		this.clienteBAI = clienteBAI;
	}

	/**
	 * Gets the cliente bai.
	 * 
	 * @return the cliente bai
	 */
	public IClienteBAI getClienteBAI()
	{
		return clienteBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IClienteRESTBAS#insertCliente(com.qat.samples.sysmgmt.model.request.
	 * ClienteMaintenanceRequest)
	 */
	@Override
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		return getClienteBAI().insertCliente(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IClienteRESTBAS#updateCliente(com.qat.samples.sysmgmt.model.request.
	 * ClienteMaintenanceRequest)
	 */
	@Override
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		return getClienteBAI().updateCliente(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IClienteRESTBAS#deleteCliente(com.qat.samples.sysmgmt.model.request.
	 * ClienteMaintenanceRequest)
	 */
	@Override
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		return getClienteBAI().deleteCliente(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteRESTBAS#refreshClientes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public ClienteResponse refreshClientes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		return getClienteBAI().refreshClientes(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteRESTBAS#fetchAllClientes(com.qat.samples.sysmgmt.model.request.FetchAllRequest
	 * )
	 */
	@Override
	public ClienteResponse fetchAllClientes(FetchAllRequest request)
	{
		return getClienteBAI().fetchAllClientes(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteRESTBAS#fetchClienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		return getClienteBAI().fetchClienteById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IClienteRESTBAS#fetchClientesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public ClienteResponse fetchClientesByRequest(PagedInquiryRequest request)
	{
		return getClienteBAI().fetchClientesByRequest(request);
	}
}
