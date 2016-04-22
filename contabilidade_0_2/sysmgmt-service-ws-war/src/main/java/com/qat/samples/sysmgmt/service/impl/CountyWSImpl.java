package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class CountyWSImpl implements com.qat.samples.sysmgmt.service.ICountyWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.countywsimpl.defaultexception";

	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.countywsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CountyWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CountyWSImpl.class);

	/** The county bai. */
	private ICountyBAC countyBAC; // injected by Spring through setter

	/**
	 * Sets the county BAC.
	 *
	 * @param countyBAC the new county BAC
	 */
	public void setCountyBAC(ICountyBAC countyBAC)
	{
		this.countyBAC = countyBAC;
	}

	/**
	 * Gets the county BAC.
	 *
	 * @return the county BAC
	 */
	public ICountyBAC getCountyBAC()
	{
		return countyBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.ICountyWS#insertCounty(com.qat.samples.sysmgmt.model.request.
	 * CountyMaintenanceRequest
	 * )
	 */
	@Override
	public CountyResponse insertCounty(CountyMaintenanceRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().insertCounty(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.ICountyWS#updateCounty(com.qat.samples.sysmgmt.model.request.
	 * CountyMaintenanceRequest
	 * )
	 */
	@Override
	public CountyResponse updateCounty(CountyMaintenanceRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().updateCounty(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.ICountyWS#deleteCounty(com.qat.samples.sysmgmt.model.request.
	 * CountyMaintenanceRequest
	 * )
	 */
	@Override
	public CountyResponse deleteCounty(CountyMaintenanceRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().deleteCounty(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.ICountyWS#refreshCounties(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public CountyResponse refreshCounties(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().refreshCounties(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.ICountyWS#fetchAllCounties(com.qat.samples.sysmgmt.model.request.FetchAllRequest
	 * )
	 */
	@Override
	public CountyResponse fetchAllCounties(FetchAllRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().fetchAllCounties();
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.ICountyWS#fetchCountyById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public CountyResponse fetchCountyById(FetchByIdRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = new InternalResultsResponse<County>();

			internalResponse = getCountyBAC().fetchCountyById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.service.ICountyWS#fetchCountiesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public CountyResponse fetchCountiesByRequest(PagedInquiryRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().fetchCountiesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}
}
