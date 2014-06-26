package com.qat.samples.sysmgmt.cliente.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.cliente.bai.IClienteBAI;
import com.qat.samples.sysmgmt.cliente.bas.IClienteBAS;
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
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class ClienteBAS implements IClienteBAS
{

	/** The bundle bai. */
	private IClienteBAI bundleBAI; // injected by Spring through setter

	/**
	 * Spring Sets the bundle bai.
	 * 
	 * @param bundleBAI the new bundle bai
	 */
	public void setClienteBAI(IClienteBAI bundleBAI)
	{
		this.bundleBAI = bundleBAI;
	}

	/**
	 * Gets the bundle bac.
	 * 
	 * @return the bundle bac
	 */
	public IClienteBAI getClienteBAI()
	{
		return bundleBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteBAS#insertCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
	 * )
	 */
	@Override
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		return getClienteBAI().insertCliente(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteBAS#updateCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
	 * )
	 */
	@Override
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		return getClienteBAI().updateCliente(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteBAS#deleteCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
	 * )
	 */
	@Override
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		return getClienteBAI().deleteCliente(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteBAS#refreshClientes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
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
	 * com.qat.samples.sysmgmt.bas.IClienteBAS#fetchAllClientes(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public ClienteResponse fetchAllClientes(FetchAllRequest request)
	{
		return getClienteBAI().fetchAllClientes(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IClienteBAS#fetchClienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		return getClienteBAI().fetchClienteById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IClienteBAS#fetchClientesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public ClienteResponse fetchClientesByRequest(PagedInquiryRequest request)
	{
		return getClienteBAI().fetchClientesByRequest(request);
	}

}
