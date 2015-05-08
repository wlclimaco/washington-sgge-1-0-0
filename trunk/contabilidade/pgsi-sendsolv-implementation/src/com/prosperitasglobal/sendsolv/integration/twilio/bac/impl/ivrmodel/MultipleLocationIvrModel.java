package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.twilio.sdk.verbs.Say;

public class MultipleLocationIvrModel extends SimpleIvrModel
{
	private IPersonDAC personDAC;

	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		boolean exception = false;
		String nextController = null;
		String defaultLanguageText = "en";
		SupportedLanguage currentLanguage = null;

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		// If there were context rows before, make sure they are deleted at this point
		if (!ValidationUtil.isNullOrEmpty(callRecord.getCallRecordContextList())
				&& (callRecord.getCallRecordContextList().size() > 0))
		{
			response =
					deleteExistingCallRecordContext(ivrRequest, callRecord, nextController, defaultLanguageText);
		}

		// Define language now
		if (!response.isInError())
		{
			currentLanguage =
					IvrCommonRoutines.getCurrentLanguage(callRecord.getLanguage(),
							ivrRequest.getSupportedLanguagesList());

			// read Member to get Locations.
			InternalResultsResponse<Member> memberResponse =
					getPersonDAC().fetchMemberById(new FetchByIdRequest(callRecord.getParentPersonId()));

			if (!memberResponse.isInError())
			{
				// Records were returned, create LocationResponse
				response =
						createLocationResponse(ivrRequest, currentLanguage, memberResponse.getFirstResult(), callRecord);
			}
			else
			{
				exception = true;
			}
		}
		else
		{
			exception = true;
		}

		// CatchAll for exception
		if (exception)
		{
			response =
					createResponse(ivrRequest, getExceptionViewName(), nextController, defaultLanguageText, null,
							response);
		}

		return response;
	}

	/**
	 * Delete existing call record context.
	 *
	 * @param ivrRequest the ivr request
	 * @param callRecord the call record
	 * @param nextController the next controller
	 * @param defaultLanguageText the default language text
	 * @return the internal results response
	 */
	private InternalResultsResponse<String> deleteExistingCallRecordContext(IvrRequest ivrRequest,
			CallRecord callRecord, String nextController, String defaultLanguageText)
	{
		InternalResultsResponse<String> response = new InternalResultsResponse<String>();
		InternalResponse deleteResponse = getCallRecordDAC().deleteCallRecordContext(callRecord);

		if (deleteResponse.isInError())
		{
			response.setStatus(Status.ExceptionError);
		}
		return response;
	}

	/**
	 * Creates the location response.
	 *
	 * @param ivrRequest the ivr request
	 * @param currentLanguage the current language
	 * @param member the member
	 * @param callRecord the call record
	 * @return the internal results response
	 */
	private InternalResultsResponse<String> createLocationResponse(IvrRequest ivrRequest,
			SupportedLanguage currentLanguage, Member member,
			CallRecord callRecord)
	{

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		// TODO Handle when cannot load resource
		String message =
				loadResource(ivrRequest.getResourcePath(), getViewName(), currentLanguage.getLanguage());

		message =
				replaceParameters(message, getNextController(), ivrRequest, null);

		// Now extract the pieces that are hard-coded and marked with <!-- -->
		String sayMessage = IvrCommonRoutines.extractTextFromTemplate(message);
		Integer index = 1;

		List<Say> sayList = new ArrayList<Say>();
		CallRecordContext callRecordContext = new CallRecordContext();
		callRecordContext.setParentId(callRecord.getId());
		for (EmploymentInfo employmentInfo : member.getEmploymentInfoList())
		{
			if (StatusEnum.ACTIVE.equals(employmentInfo.getEmploymentInfoStatus()))
			{
				String msg =
						sayMessage.replace("%LOCATION_NAME%", employmentInfo.getLocationName());
				msg = msg.replace("%INDEX%", index.toString());

				Say say = new Say(msg);
				say.setLanguage(currentLanguage.getLanguage());
				say.setVoice(currentLanguage.getVoice());
				sayList.add(say);

				index++;
			}
		}

		response.addResult(IvrCommonRoutines.replaceTextFromTemplate(message, sayList));

		return response;
	}

	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}
}
