package com.qat.samples.sysmgmt.controle.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.controle.bac.IControleBAC;
import com.qat.samples.sysmgmt.controle.bai.IControleBAI;
import com.qat.samples.sysmgmt.controle.baid.ControleBAID;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.response.AcessosResponse;
import com.qat.samples.sysmgmt.controle.model.response.ControleResponse;

/**
 * The Class CidadeBAIImpl.
 */
public class ControleBAIImpl implements IControleBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cidadebaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ControleBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ControleBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The controle bac. */
	private IControleBAC controleBAC;

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

	public IControleBAC getControleBAC()
	{
		return controleBAC;
	}

	public void setControleBAC(IControleBAC controleBAC)
	{
		this.controleBAC = controleBAC;
	}

	@Override
	public ControleResponse fetchAllControles(ControleInquiryRequest request)
	{

		ControleResponse response = new ControleResponse();
		try
		{
			ControleBAID.fetchAllControles(getControleBAC(), response, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public AcessosResponse fetchAllAcessos(AcessosInquiryRequest request)
	{
		AcessosResponse response = new AcessosResponse();
		try
		{
			ControleBAID.fetchAllAcessos(getControleBAC(), response, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ControleResponse fetchControleByPage(ControleInquiryRequest request)
	{
		ControleResponse response = new ControleResponse();
		try
		{
			ControleBAID.fetchAllControles(getControleBAC(), response, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ControleResponse fetchControlesByRequest(ControleInquiryRequest request)
	{
		ControleResponse response = new ControleResponse();
		try
		{
			ControleBAID.fetchAllControles(getControleBAC(), response, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ControleResponse fetchControleByAction(ControleInquiryRequest request)
	{
		ControleResponse response = new ControleResponse();
		try
		{
			ControleBAID.fetchAllControles(getControleBAC(), response, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
