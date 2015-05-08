package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.util.HashMap;
import java.util.Map;

import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.twilio.sdk.verbs.Say;

public class GreetingIvrModel extends IvrModel
{
	private static final String RELOAD = "reload";

	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		String resourceName = null;
		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		if (!ValidationUtil.isNullOrEmpty(ivrRequest.getAction()) && ivrRequest.getAction().equals(RELOAD))
		{
			resourceName = getViewName();
		}
		else
		{
			// store the call in the table only if it is new call
			if (ValidationUtil.isNull(callRecord))
			{
				callRecord = new CallRecord();
				callRecord.setCallSid(ivrRequest.getCallSid());
				callRecord.setOriginatingPhone(ivrRequest.getIvrIdentity().getPhoneNumber());
				callRecord.setStartDateTimeUTC(System.currentTimeMillis());
				InternalResponse insertResponse = getCallRecordDAC().insertCallRecord(callRecord);

				if (!insertResponse.isInError())
				{
					resourceName = getViewName();
				}
				else
				{
					// Exception
					resourceName = getExceptionViewName();
					response.merge(insertResponse);
				}
			}
			else
			{
				resourceName = getViewName();
			}
		}

		// Load first one in English every time
		return createResponse(ivrRequest, resourceName, getNextController(), "en", createAdditionalTokens(ivrRequest),
				response);
	}

	private Map<String, String> createAdditionalTokens(IvrRequest ivrRequest)
	{
		Map<String, String> tokens = new HashMap<String, String>();

		StringBuilder languageOptions = new StringBuilder();
		for (SupportedLanguage supportedLanguage : ivrRequest.getSupportedLanguagesList())
		{
			Say say = new Say(new String(supportedLanguage.getGreeting() + supportedLanguage.getIvrOption()));
			say.setVoice(supportedLanguage.getVoice());
			say.setLanguage(supportedLanguage.getLanguage());

			languageOptions.append(say.toXML());
			languageOptions.append(String.format("%n"));
			languageOptions.append(String.format("\t"));
		}

		tokens.put("LANGUAGES", languageOptions.toString());

		return tokens;
	}
}
