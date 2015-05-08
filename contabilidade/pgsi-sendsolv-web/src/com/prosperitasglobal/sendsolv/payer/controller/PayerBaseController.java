package com.prosperitasglobal.sendsolv.payer.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.payer.bai.IPayerBranchBAI;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerCountryInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerStateProvinceRegionInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerAddressResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerBranchResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerCityResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerCountryResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerStateProvinceRegionResponse;

/**
 * The Class MemberBaseController.
 */
public class PayerBaseController extends BaseController
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PayerBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "MemberBaseController";

	/** The Organization BAI. */
	private IPayerBranchBAI payerBranchBAI;

	/** The filter factory. */
	private FilterFactory filterFactory;

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory()
	{
		return filterFactory;
	}

	/**
	 * @param filterFactory the filterFactory to set
	 */
	@Resource
	public void setFilterFactory(FilterFactory filterFactory)
	{
		this.filterFactory = filterFactory;
	}

	/**
	 * @return the payerBranchBAI
	 */
	public IPayerBranchBAI getPayerBranchBAI()
	{
		return payerBranchBAI;
	}

	/**
	 * @param payerBranchBAI the payerBranchBAI to set
	 */
	@Resource
	public void setPayerBranchBAI(IPayerBranchBAI payerBranchBAI)
	{
		this.payerBranchBAI = payerBranchBAI;
	}

	public PayerCountryResponse fetchPayerCountryByRequest(PayerCountryInquiryRequest request)
	{

		PayerCountryResponse response = new PayerCountryResponse();
		try
		{

			response = getPayerBranchBAI().fetchPayerCountryByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public PayerStateProvinceRegionResponse fetchPayerStateProvinceRegionByRequest(
			PayerStateProvinceRegionInquiryRequest request)
	{

		PayerStateProvinceRegionResponse response = new PayerStateProvinceRegionResponse();
		try
		{

			response = getPayerBranchBAI().fetchPayerStateProvinceRegionByRequest(request);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public PayerBranchResponse fetchPayerBranchesByAddressId(
			FetchByIdRequest request)
	{

		PayerBranchResponse response = new PayerBranchResponse();
		try
		{

			response = getPayerBranchBAI().fetchPayerBranchesByAddressId(request);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public PayerAddressResponse fetchPayerAddressesByCityId(
			FetchByIdRequest request)
	{

		PayerAddressResponse response = new PayerAddressResponse();
		try
		{

			response = getPayerBranchBAI().fetchPayerAddressesByCityId(request);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public PayerCityResponse fetchPayerCityByStateProvinceRegionId(
			PayerStateProvinceRegionInquiryRequest request)
	{

		PayerCityResponse response = new PayerCityResponse();
		try
		{

			response = getPayerBranchBAI().fetchPayerCityByStateProvinceRegionId(request);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

}
