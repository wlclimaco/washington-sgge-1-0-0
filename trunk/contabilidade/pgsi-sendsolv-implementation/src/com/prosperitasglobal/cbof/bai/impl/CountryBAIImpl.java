package com.prosperitasglobal.cbof.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.dac.ICountryDAC;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.cbof.model.request.FetchByCodeRequest;
import com.prosperitasglobal.cbof.model.response.CountryResponse;
import com.prosperitasglobal.cbof.model.response.StateProvinceRegionResponse;
import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CountryBAIImpl.
 */
public class CountryBAIImpl implements ICountryBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	private static final String DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG =
			"sendsolve.base.commonbusinessobjects.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CountryBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CountryBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED =
			"cbof.base.organizationvalidator.code.required";

	private ICountryDAC countryDAC; // injected by Spring through setter

	/**
	 * Gets the country dac.
	 *
	 * @return the country dac
	 */
	public ICountryDAC getCountryDAC()
	{
		return countryDAC;
	}

	/**
	 * Sets the country dac.
	 *
	 * @param countryDAC the country dac
	 */
	public void setCountryDAC(ICountryDAC countryDAC)
	{
		this.countryDAC = countryDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.bai.ICountryBAI#fetchAllCountry(com.prosperitasglobal.sendsolve.model.request.
	 * FetchByCodeRequest)
	 */
	@Override
	public CountryResponse fetchAllCountry(FetchByCodeRequest request)
	{
		CountryResponse response = new CountryResponse();
		try
		{
			InternalResultsResponse<Country> internalResponse = getCountryDAC().fetchAllCountry();
			verifyError(internalResponse);

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
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
	 * com.prosperitasglobal.cbof.bai.ICountryBAI#fetchAllKnownCountry(com.prosperitasglobal.sendsolve.model.request.
	 * FetchByCodeRequest)
	 */
	@Override
	public CountryResponse fetchAllKnownCountry(FetchByCodeRequest request)
	{
		CountryResponse response = new CountryResponse();
		try
		{
			InternalResultsResponse<Country> internalResponse = getCountryDAC().fetchAllKnownCountry();
			verifyError(internalResponse);

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.bai.ICountryBAI#fetchCountryByCode(com.prosperitasglobal.sendsolve.model.request.
	 * FetchByCodeRequest)
	 */
	@Override
	public CountryResponse fetchCountryByCode(FetchByCodeRequest request)
	{
		CountryResponse response = new CountryResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getCode()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED);
			}
			else
			{
				internalResponse = getCountryDAC().fetchCountryByCode(request.getCode());
				verifyError(internalResponse);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
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
	 * com.prosperitasglobal.cbof.bai.ICountryBAI#fetchStateProvinceRegionByCountryCode(com.prosperitasglobal.sendsolve
	 * .model.request.FetchByCodeRequest)
	 */
	@Override
	public StateProvinceRegionResponse fetchStateProvinceRegionByCountryCode(FetchByCodeRequest request)
	{
		StateProvinceRegionResponse response = new StateProvinceRegionResponse();

		try
		{
			InternalResultsResponse<StateProvinceRegion> internalResponse =
					new InternalResultsResponse<StateProvinceRegion>();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getCode()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED);
			}
			else
			{
				internalResponse = getCountryDAC().fetchStateProvinceRegionByCountryCode(request.getCode());
				verifyError(internalResponse);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Verify error.
	 *
	 * @param internalResponse the internal response
	 */
	private void verifyError(InternalResponse internalResponse)
	{
		if (internalResponse.isInError())
		{
			internalResponse.addMessage(DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse.getStatus().toString()});
		}
	}

}
