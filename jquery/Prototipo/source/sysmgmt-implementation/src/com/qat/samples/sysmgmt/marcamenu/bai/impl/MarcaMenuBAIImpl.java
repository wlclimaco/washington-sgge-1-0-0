package com.qat.samples.sysmgmt.marcamenu.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.cidade.bac.ICidadeBAC;
import com.qat.samples.sysmgmt.cidade.bai.ICidadeBAI;
import com.qat.samples.sysmgmt.cidade.baid.CidadeBAID;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Class CidadeBAIImpl.
 */
public class MarcaMenuBAIImpl implements ICidadeBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cidadebaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = MarcaMenuBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MarcaMenuBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The cidade bac. */
	private ICidadeBAC cidadeBAC;

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
	public void setCidadeBAC(ICidadeBAC cidadeBAC)
	{
		this.cidadeBAC = cidadeBAC;
	}

	/**
	 * Gets the cidade bac.
	 * 
	 * @return the cidade bac
	 */
	public ICidadeBAC getCidadeBAC()
	{
		return cidadeBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.ICidadeBAI#insertCidade(com.qat.samples.sysmgmt.model.request.CidadeMaintenanceRequest
	 * )
	 */
	@Override
	public CidadeResponse insertCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			CidadeBAID.maintainCidade(getCidadeBAC(), ValidationContextIndicator.INSERT, getValidationController(),
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
	 * com.qat.samples.sysmgmt.bai.ICidadeBAI#updateCidade(com.qat.samples.sysmgmt.model.request.CidadeMaintenanceRequest
	 * )
	 */
	@Override
	public CidadeResponse updateCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			CidadeBAID.maintainCidade(getCidadeBAC(), ValidationContextIndicator.UPDATE, getValidationController(),
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
	 * com.qat.samples.sysmgmt.bai.ICidadeBAI#deleteCidade(com.qat.samples.sysmgmt.model.request.CidadeMaintenanceRequest
	 * )
	 */
	@Override
	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			CidadeBAID.maintainCidade(getCidadeBAC(), ValidationContextIndicator.DELETE, getValidationController(),
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
	 * @see com.qat.samples.sysmgmt.bai.ICidadeBAI#refreshCidades(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public CidadeResponse refreshCidades(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		CidadeResponse response = new CidadeResponse();
		try
		{
			CidadeBAID.refreshCidades(getCidadeBAC(), request, response);
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
	 * com.qat.samples.sysmgmt.bai.ICidadeBAI#fetchAllCidades(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public CidadeResponse fetchAllCidades(FetchAllRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			CidadeBAID.fetchAllCidades(getCidadeBAC(), response);
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
	 * com.qat.samples.sysmgmt.bai.ICidadeBAI#fetchCidadeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public CidadeResponse fetchCidadeById(FetchByIdRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			CidadeBAID.fetchCidadeById(getCidadeBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CidadeResponse fetchCidadesByRequest(PagedInquiryRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			CidadeBAID.fetchCidadesPaged(getCidadeBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
