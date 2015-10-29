package com.qat.samples.sysmgmt.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.bac.IBundleBAC;
import com.qat.samples.sysmgmt.bai.IBundleBAI;
import com.qat.samples.sysmgmt.baid.BundleBAID;
import com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;

/**
 * The Class BundleBAIImpl.
 */
public class BundleBAIImpl implements IBundleBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.bundlebaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = BundleBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BundleBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The bundle bac. */
	private IBundleBAC bundleBAC;

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
	 * Sets the bundle bac.
	 * 
	 * @param bundleBAC the new bundle bac
	 */
	public void setBundleBAC(IBundleBAC bundleBAC)
	{
		this.bundleBAC = bundleBAC;
	}

	/**
	 * Gets the bundle bac.
	 * 
	 * @return the bundle bac
	 */
	public IBundleBAC getBundleBAC()
	{
		return bundleBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IBundleBAI#insertBundle(com.qat.samples.sysmgmt.model.request.
	 * BundleMaintenanceRequest)
	 */
	@Override
	public BundleResponse insertBundle(BundleMaintenanceRequest request)
	{
		BundleResponse response = new BundleResponse();
		try
		{
			BundleBAID.maintainBundle(getBundleBAC(), ValidationContextIndicator.INSERT,
					getValidationController(), PersistanceActionEnum.INSERT, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IBundleBAI#updateBundle(com.qat.samples.sysmgmt.model.request.
	 * BundleMaintenanceRequest)
	 */
	@Override
	public BundleResponse updateBundle(BundleMaintenanceRequest request)
	{
		BundleResponse response = new BundleResponse();
		try
		{
			BundleBAID.maintainBundle(getBundleBAC(), ValidationContextIndicator.UPDATE,
					getValidationController(), PersistanceActionEnum.UPDATE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IBundleBAI#deleteBundle(com.qat.samples.sysmgmt.model.request.
	 * BundleMaintenanceRequest)
	 */
	@Override
	public BundleResponse deleteBundle(BundleMaintenanceRequest request)
	{
		BundleResponse response = new BundleResponse();
		try
		{
			BundleBAID.maintainBundle(getBundleBAC(), ValidationContextIndicator.DELETE,
					getValidationController(), PersistanceActionEnum.DELETE, request, response);
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
	 * com.qat.samples.sysmgmt.bai.IBundleBAI#refreshBundles(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public BundleResponse refreshBundles(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		BundleResponse response = new BundleResponse();
		try
		{
			BundleBAID.refreshBundles(getBundleBAC(), request, response);
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
	 * com.qat.samples.sysmgmt.bai.IBundleBAI#fetchAllBundles(com.qat.samples.sysmgmt.model.request.FetchAllRequest
	 * )
	 */
	@Override
	public BundleResponse fetchAllBundles(FetchAllRequest request)
	{
		BundleResponse response = new BundleResponse();
		try
		{
			BundleBAID.fetchAllBundles(getBundleBAC(), response);
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
	 * com.qat.samples.sysmgmt.bai.IBundleBAI#fetchBundleById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public BundleResponse fetchBundleById(FetchByIdRequest request)
	{
		BundleResponse response = new BundleResponse();
		try
		{
			BundleBAID.fetchBundleById(getBundleBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public BundleResponse fetchBundlesByRequest(PagedInquiryRequest request)
	{
		BundleResponse response = new BundleResponse();
		try
		{
			BundleBAID.fetchBundlesPaged(getBundleBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

}
