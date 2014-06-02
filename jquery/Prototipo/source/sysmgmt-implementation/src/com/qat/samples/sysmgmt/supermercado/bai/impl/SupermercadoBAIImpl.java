package com.qat.samples.sysmgmt.supermercado.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.bac.ISupermercadoBAC;
import com.qat.samples.sysmgmt.supermercado.bai.ISupermercadoBAI;
import com.qat.samples.sysmgmt.supermercado.baid.SupermercadoBAID;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * The Class SupermercadoBAIImpl.
 */
public class SupermercadoBAIImpl implements ISupermercadoBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.supermercadobaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = SupermercadoBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SupermercadoBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The supermercado bac. */
	private ISupermercadoBAC supermercadoBAC;

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
	 * Sets the supermercado bac.
	 * 
	 * @param supermercadoBAC the new supermercado bac
	 */
	public void setSupermercadoBAC(ISupermercadoBAC supermercadoBAC)
	{
		this.supermercadoBAC = supermercadoBAC;
	}

	/**
	 * Gets the supermercado bac.
	 * 
	 * @return the supermercado bac
	 */
	public ISupermercadoBAC getSupermercadoBAC()
	{
		return supermercadoBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.ISupermercadoBAI#insertSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest
	 * )
	 */
	@Override
	public SupermercadoResponse insertSupermercado(SupermercadoMaintenanceRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			SupermercadoBAID.maintainSupermercado(getSupermercadoBAC(), ValidationContextIndicator.INSERT,
					getValidationController(),
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
	 * com.qat.samples.sysmgmt.bai.ISupermercadoBAI#updateSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest
	 * )
	 */
	@Override
	public SupermercadoResponse updateSupermercado(SupermercadoMaintenanceRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			SupermercadoBAID.maintainSupermercado(getSupermercadoBAC(), ValidationContextIndicator.UPDATE,
					getValidationController(),
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
	 * com.qat.samples.sysmgmt.bai.ISupermercadoBAI#deleteSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest
	 * )
	 */
	@Override
	public SupermercadoResponse deleteSupermercado(SupermercadoMaintenanceRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			SupermercadoBAID.maintainSupermercado(getSupermercadoBAC(), ValidationContextIndicator.DELETE,
					getValidationController(),
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
	 * @see com.qat.samples.sysmgmt.bai.ISupermercadoBAI#refreshSupermercados(com.qat.samples.sysmgmt.model.request.
	 * RefreshRequest)
	 */
	@Override
	public SupermercadoResponse refreshSupermercados(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			SupermercadoBAID.refreshSupermercados(getSupermercadoBAC(), request, response);
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
	 * com.qat.samples.sysmgmt.bai.ISupermercadoBAI#fetchAllSupermercados(com.qat.samples.sysmgmt.model.request.
	 * FetchAllRequest)
	 */
	@Override
	public SupermercadoResponse fetchAllSupermercados(FetchAllRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			SupermercadoBAID.fetchAllSupermercados(getSupermercadoBAC(), response);
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
	 * com.qat.samples.sysmgmt.bai.ISupermercadoBAI#fetchSupermercadoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public SupermercadoResponse fetchSupermercadoById(FetchByIdRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			SupermercadoBAID.fetchSupermercadoById(getSupermercadoBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SupermercadoResponse fetchSupermercadosByRequest(PagedInquiryRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			SupermercadoBAID.fetchSupermercadosPaged(getSupermercadoBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
