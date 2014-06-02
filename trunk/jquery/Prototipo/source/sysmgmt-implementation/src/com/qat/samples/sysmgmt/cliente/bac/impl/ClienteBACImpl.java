package com.qat.samples.sysmgmt.cliente.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cliente.bac.IClienteBAC;
import com.qat.samples.sysmgmt.cliente.dac.IClienteDAC;
import com.qat.samples.sysmgmt.cliente.model.Cliente;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * Implementation of the IClienteBAC leveraging a BAD, ClienteBAD.
 */
public class ClienteBACImpl implements IClienteBAC
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
	private static final String DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG = "sysmgmt.base.cidadebacimpl.defaultexception";

	/** The cidade dac. */
	private IClienteDAC cidadeDAC; // injected by Spring through setter

	/**
	 * Spring Sets the cidade dac.
	 * 
	 * @param cidadeDAC the new cidade dac
	 */
	public void setClienteDAC(IClienteDAC cidadeDAC)
	{
		this.cidadeDAC = cidadeDAC;
	}

	/**
	 * Gets the cidade dac.
	 * 
	 * @return the cidade dac
	 */
	public IClienteDAC getClienteDAC()
	{
		return cidadeDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IClienteBAC#insertCliente(com.qat.samples.sysmgmt.base.model.Cliente)
	 */
	@Override
	public InternalResponse insertCliente(Cliente cidade)
	{
		// cidade.setPrice(ClienteBAD.calculatePrice(INSERT_SEED));
		return getClienteDAC().insertCliente(cidade);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IClienteBAC#updateCliente(com.qat.samples.sysmgmt.base.model.Cliente)
	 */
	@Override
	public InternalResponse updateCliente(Cliente cidade)
	{
		// cidade.setPrice(ClienteBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getClienteDAC().updateCliente(cidade);
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
	 * com.qat.samples.sysmgmt.base.bac.IClienteBAC#deleteCliente(com.qat.samples.sysmgmt.base.model.Cliente)
	 */
	@Override
	public InternalResponse deleteCliente(Cliente cidade)
	{
		InternalResponse internalResponse = getClienteDAC().deleteCliente(cidade);
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
	 * @see com.qat.samples.sysmgmt.base.bac.IClienteBAC#refreshClientes(int)
	 */
	@Override
	public void refreshClientes(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getClienteDAC().deleteAllClientes();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getClienteDAC().insertCliente(new Cliente(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IClienteBAC#fetchAllClientes()
	 */
	@Override
	public InternalResultsResponse<Cliente> fetchAllClientes()
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
		response.getResultsList().addAll(getClienteDAC().fetchAllClientes());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IClienteBAC#fetchClienteById(com.qat.samples.sysmgmt.base.model.Cliente
	 * )
	 */
	@Override
	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
		response.getResultsList().add(getClienteDAC().fetchClienteById(request));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IClienteBAC#fetchClientesByRequest(com.qat.samples.sysmgmt.model.request.
	 * ClienteInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cliente> fetchClientesByRequest(PagedInquiryRequest request)
	{
		return getClienteDAC().fetchClientesByRequest(request);
	}

}
