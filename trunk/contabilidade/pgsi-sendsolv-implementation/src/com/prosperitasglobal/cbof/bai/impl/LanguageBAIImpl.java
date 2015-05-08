package com.prosperitasglobal.cbof.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.ILanguageBAI;
import com.prosperitasglobal.cbof.dac.ILanguageDAC;
import com.prosperitasglobal.cbof.model.Language;
import com.prosperitasglobal.cbof.model.response.LanguageResponse;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;

/**
 * The Class LanguageBAIImpl.
 */
public class LanguageBAIImpl implements ILanguageBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = LanguageBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LanguageBAIImpl.class);

	/** The language dac. */
	private ILanguageDAC languageDAC;

	/**
	 * Gets the language dac.
	 *
	 * @return the language dac
	 */
	public ILanguageDAC getLanguageDAC()
	{
		return languageDAC;
	}

	/**
	 * Sets the language dac.
	 *
	 * @param languageDAC the language dac
	 */
	public void setLanguageDAC(ILanguageDAC languageDAC)
	{
		this.languageDAC = languageDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IMemberBAI#fetchLanguageByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .PagedInquiryRequest)
	 */
	@Override
	public LanguageResponse fetchLanguageByRequest(PagedInquiryRequest request)
	{
		LanguageResponse response = new LanguageResponse();
		InternalResultsResponse<Language> internalResponse = new InternalResultsResponse<Language>();

		try
		{
			internalResponse = getLanguageDAC().fetchLanguageByRequest(request);

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;

	}
}
