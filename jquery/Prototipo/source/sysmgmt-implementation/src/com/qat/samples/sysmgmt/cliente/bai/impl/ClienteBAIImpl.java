package com.qat.samples.sysmgmt.cliente.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.cliente.bac.IClienteBAC;
import com.qat.samples.sysmgmt.cliente.bai.IClienteBAI;
import com.qat.samples.sysmgmt.cliente.baid.ClienteBAID;
import com.qat.samples.sysmgmt.cliente.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Class ClienteBAIImpl.
 */
public class ClienteBAIImpl implements IClienteBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cidadebaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ClienteBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClienteBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The cidade bac. */
	private IClienteBAC cidadeBAC;

	/**
	 * Gets the validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation controller.
	 * 
	 * @param countyValidationController the new validation controller
	 */
	public void setValidationController(ValidationController countyValidationController)
	{
		validationController = countyValidationController;
	}

	/**
	 * Sets the cidade bac.
	 * 
	 * @param cidadeBAC the new cidade bac
	 */
	public void setClienteBAC(IClienteBAC cidadeBAC)
	{
		this.cidadeBAC = cidadeBAC;
	}

	/**
	 * Gets the cidade bac.
	 * 
	 * @return the cidade bac
	 */
	public IClienteBAC getClienteBAC()
	{
		return cidadeBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IClienteBAI#insertCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
	 * )
	 */
	@Override
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			ClienteBAID.maintainCliente(getClienteBAC(), ValidationContextIndicator.INSERT, getValidationController(),
					PersistanceActionEnum.INSERT, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IClienteBAI#updateCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
	 * )
	 */
	@Override
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			ClienteBAID.maintainCliente(getClienteBAC(), ValidationContextIndicator.UPDATE, getValidationController(),
					PersistanceActionEnum.UPDATE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IClienteBAI#deleteCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
	 * )
	 */
	@Override
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			ClienteBAID.maintainCliente(getClienteBAC(), ValidationContextIndicator.DELETE, getValidationController(),
					PersistanceActionEnum.DELETE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IClienteBAI#refreshClientes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public ClienteResponse refreshClientes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		ClienteResponse response = new ClienteResponse();
		try
		{
			ClienteBAID.refreshClientes(getClienteBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IClienteBAI#fetchAllClientes(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public ClienteResponse fetchAllClientes(FetchAllRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			ClienteBAID.fetchAllClientes(getClienteBAC(), response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IClienteBAI#fetchClienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			ClienteBAID.fetchClienteById(getClienteBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ClienteResponse fetchClientesByRequest(PagedInquiryRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			ClienteBAID.fetchClientesPaged(getClienteBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
