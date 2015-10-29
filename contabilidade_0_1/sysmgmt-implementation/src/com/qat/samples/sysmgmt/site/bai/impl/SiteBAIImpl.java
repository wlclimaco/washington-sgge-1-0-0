package com.qat.samples.sysmgmt.site.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.site.bac.ISiteBAC;
import com.qat.samples.sysmgmt.site.bai.ISiteBAI;
import com.qat.samples.sysmgmt.site.baid.SiteBAID;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;

/**
 * The Class SiteBAIImpl.
 */
public class SiteBAIImpl implements ISiteBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.supermercadobaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = SiteBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SiteBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The supermercado bac. */
	private ISiteBAC supermercadoBAC;

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
	public void setSiteBAC(ISiteBAC supermercadoBAC)
	{
		this.supermercadoBAC = supermercadoBAC;
	}

	/**
	 * Gets the supermercado bac.
	 * 
	 * @return the supermercado bac
	 */
	public ISiteBAC getSiteBAC()
	{
		return supermercadoBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.ISiteBAI#insertSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest
	 * )
	 */
	@Override
	public SiteResponse insertSite(SiteMaintenanceRequest request)
	{
		SiteResponse response = new SiteResponse();
		try
		{
			SiteBAID.maintainSite(getSiteBAC(), ValidationContextIndicator.INSERT,
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
	 * com.qat.samples.sysmgmt.bai.ISiteBAI#updateSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest
	 * )
	 */
	@Override
	public SiteResponse updateSite(SiteMaintenanceRequest request)
	{
		SiteResponse response = new SiteResponse();
		try
		{
			SiteBAID.maintainSite(getSiteBAC(), ValidationContextIndicator.UPDATE,
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
	 * com.qat.samples.sysmgmt.bai.ISiteBAI#deleteSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest
	 * )
	 */
	@Override
	public SiteResponse deleteSite(SiteMaintenanceRequest request)
	{
		SiteResponse response = new SiteResponse();
		try
		{
			SiteBAID.maintainSite(getSiteBAC(), ValidationContextIndicator.DELETE,
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
	 * @see com.qat.samples.sysmgmt.bai.ISiteBAI#refreshSites(com.qat.samples.sysmgmt.model.request.
	 * RefreshRequest)
	 */
	// @Override
	// public SiteResponse refreshSites(RefreshRequest request)
	// {
	// // This method is demo code only. Do not view this as a QAT Standard.
	// SiteResponse response = new SiteResponse();
	// try
	// {
	// SiteBAID.refreshSites(getSiteBAC(), request, response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.ISiteBAI#fetchAllSites(com.qat.samples.sysmgmt.model.request.
	 * FetchAllRequest)
	 */
	// @Override
	// public SiteResponse fetchAllSite(FetchAllRequest request)
	// {
	// SiteResponse response = new SiteResponse();
	// try
	// {
	// SiteBAID.fetchAllSites(getSiteBAC(), response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.ISiteBAI#fetchSiteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public SiteResponse fetchSiteById(FetchByIdRequest request)
	{
		SiteResponse response = new SiteResponse();
		try
		{
			SiteBAID.fetchSiteById(getSiteBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public SiteResponse fetchSitesByRequest(PagedInquiryRequest request)
	{
		SiteResponse response = new SiteResponse();
		try
		{
			SiteBAID.fetchSitesPaged(getSiteBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
