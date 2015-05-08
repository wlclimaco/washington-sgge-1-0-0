package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.util.HashMap;
import java.util.Map;

import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

public class LanguageIvrModel extends IvrModel
{
	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		String resourceName = null;
		String nextAction = null;
		SupportedLanguage currentLanguage = null;

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		String language = ivrRequest.getDigits();

		// Define language now
		if (!ValidationUtil.isNull(callRecord))
		{
			if (ValidationUtil.isNullOrEmpty(callRecord.getLanguage()))
			{
				for (SupportedLanguage supportedLanguage : ivrRequest.getSupportedLanguagesList())
				{
					if (language.equalsIgnoreCase(supportedLanguage.getIvrOption()))
					{
						currentLanguage = supportedLanguage;
						callRecord.setLanguage(currentLanguage.getLanguage());
						break;
					}
				}

				// update language in table
				InternalResponse updateResponse = getCallRecordDAC().updateCallRecord(callRecord);

				if (!updateResponse.isInError())
				{
					resourceName = getViewName();
					nextAction = getNextController();
				}
				else
				{
					resourceName = getExceptionViewName();
					response.merge(updateResponse);
				}
			}
			else
			{
				// Grab language from recordCall
				for (SupportedLanguage supportedLanguage : ivrRequest.getSupportedLanguagesList())
				{
					if (callRecord.getLanguage().equalsIgnoreCase(supportedLanguage.getLanguage()))
					{
						currentLanguage = supportedLanguage;
						break;
					}
				}

				resourceName = getViewName();
				nextAction = getNextController();
			}
		}
		else
		{
			resourceName = getExceptionViewName();
		}

		return createResponse(ivrRequest, resourceName, nextAction, currentLanguage.getLanguage(),
				createAdditionalTokens(ivrRequest), response);
	}

	private Map<String, String> createAdditionalTokens(IvrRequest ivrRequest)
	{
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("PHONE", IvrCommonRoutines.formatPhoneForIvr(ivrRequest.getIvrIdentity().getPhoneNumber()));

		return tokens;
	}
}
