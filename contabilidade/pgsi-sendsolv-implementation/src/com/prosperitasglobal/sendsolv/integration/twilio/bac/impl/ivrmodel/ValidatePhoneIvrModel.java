package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.util.HashMap;
import java.util.Map;

import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

public class ValidatePhoneIvrModel extends BinaryIvrModel
{
	private static final String USA_AREA_CODE = "1";
	private static final String DIGITS = "digits";

	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		String resourceName = null;
		String nextAction = null;
		String currentLanguage = "en";
		String phoneToValidate = null;

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		// If subAction = DIGITS use the ivr digits, not the phone from identity. This happens when user enters phone
		// number manually.
		if (!ValidationUtil.isNullOrEmpty(ivrRequest.getAction()) && ivrRequest.getAction().equals(DIGITS))
		{
			StringBuffer sb = new StringBuffer();
			sb.append(USA_AREA_CODE).append(ivrRequest.getDigits());
			phoneToValidate = sb.toString();
		}
		else
		{
			phoneToValidate = ivrRequest.getIvrIdentity().getPhoneNumber();
		}

		// Define language now
		if (!ValidationUtil.isNull(callRecord))
		{
			currentLanguage = callRecord.getLanguage();

			if (validPhone(phoneToValidate))
			{
				// if phone is valid, update callRecord because user maybe input phone manually.
				callRecord.setOriginatingPhone(phoneToValidate);

				if (getCallRecordDAC().updateCallRecord(callRecord).isInError())
				{
					resourceName = getExceptionViewName();
				}
				else
				{
					// pin
					resourceName = getViewName();
					nextAction = getNextController();
				}
			}
			else
			{
				// phone nf
				resourceName = getAdditionalViewName();
				nextAction = getAdditionalNextController();
			}
		}
		else
		{
			resourceName = getExceptionViewName();
		}

		return createResponse(ivrRequest, resourceName, nextAction, currentLanguage,
				createAdditionalTokens(phoneToValidate), response);
	}

	private Map<String, String> createAdditionalTokens(String phoneToValidate)
	{
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("PHONE",
				IvrCommonRoutines.formatPhoneForIvr(phoneToValidate));
		return tokens;
	}

	private boolean validPhone(String phoneToValidate)
	{
		// the phone in IvrIdentity has the '+' removed
		Integer theCount = getCallRecordDAC().fetchPhoneBySid(phoneToValidate);

		if (ValidationUtil.isNullOrZero(theCount))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
