package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.IvrIdentity;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

public class ValidatePinIvrModel extends BinaryIvrModel
{
	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		String resourceName = null;
		String nextController = null;
		String currentLanguage = null;

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		// Define language now
		if (!ValidationUtil.isNull(callRecord))
		{
			currentLanguage = callRecord.getLanguage();

			ivrRequest.getIvrIdentity().setPinNumber(ivrRequest.getDigits());
			ivrRequest.getIvrIdentity().setPhoneNumber(callRecord.getOriginatingPhone());

			if (validPin(ivrRequest.getIvrIdentity(), callRecord))
			{
				// authenticated
				resourceName = getViewName();
				nextController = getNextController();
			}
			else
			{
				// reenter pin
				resourceName = getAdditionalViewName();
				nextController = getAdditionalNextController();
			}
		}
		else
		{
			resourceName = getExceptionViewName();
		}

		return createResponse(ivrRequest, resourceName, nextController, currentLanguage, null, response);
	}

	private boolean validPin(IvrIdentity ivrIdentity, CallRecord callRecord)
	{
		Integer personId = getCallRecordDAC().fetchPersonIdByIvrIdentity(ivrIdentity);

		if (ValidationUtil.isNullOrZero(personId))
		{
			return false;
		}
		else
		{
			callRecord.setParentPersonId(personId);

			InternalResponse updateResponse = getCallRecordDAC().updateCallRecord(callRecord);

			if (updateResponse.isInError())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
}
