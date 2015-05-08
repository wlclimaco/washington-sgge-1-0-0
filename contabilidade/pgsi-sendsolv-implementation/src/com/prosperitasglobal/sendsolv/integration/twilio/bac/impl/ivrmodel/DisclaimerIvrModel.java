package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.util.HashMap;
import java.util.Map;

import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.qat.framework.model.response.InternalResultsResponse;

public class DisclaimerIvrModel extends IvrModel
{
	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		// Define language
		SupportedLanguage currentLanguage =
				IvrCommonRoutines.getCurrentLanguage(callRecord.getLanguage(),
						ivrRequest.getSupportedLanguagesList());

		response =
				createResponse(ivrRequest, getViewName(), getNextController(),
						currentLanguage.getLanguage(),
						createAdditionalAudioFileToken(ivrRequest, currentLanguage),
						response);

		return response;
	}

	private Map<String, String> createAdditionalAudioFileToken(IvrRequest ivrRequest, SupportedLanguage currentLanguage)
	{
		Map<String, String> tokens = new HashMap<String, String>();

		String resourceFullpath =
				ivrRequest.getMainUrl() + ivrRequest.getStaticResourcePath() + "/" + currentLanguage.getLanguage()
				+ "/" + getAudioFileName();

		tokens.put("AUDIO_PATH", resourceFullpath);

		return tokens;
	}
}
