package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.integration.twilio.model.MoneyTransferSummary;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.prosperitasglobal.sendsolv.integration.twilio.util.PGSiDateUtil;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.twilio.sdk.verbs.Say;

public class PriorTransactionIvrModel extends TernaryIvrModel
{
	private IPersonDAC personDAC;

	private static final String TRANSACTION_NOUN = "TRANSACTION_NOUN";
	private static final String TRANSACTIONS = "transactions";
	private static final String TRANSACTION = "transaction";

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

			// check for multiple Locations.
			InternalResultsResponse<Member> memberResponse =
					getPersonDAC().fetchMemberById(new FetchByIdRequest(callRecord.getParentPersonId()));

			if (!memberResponse.isInError())
			{
				// check number of locations in active List<EmploymentInfo> employmentInfoList;

				// if 1, this is the one, just call the action passing ivr option
				// if > 1, go to pick location menu

				if (countActiveLocationForMember(memberResponse.getFirstResult()) > 1)
				{
					// Generates a redirect that goes to Location pick step.
					response = createResponse(ivrRequest, getAdditionalViewName2(), getAdditionalNextController2(),
							currentLanguage.getLanguage(), null, response);
				}
				else
				{
					// Save Location to DB ?

					// Prepare to get summary info: need personId and date
					MoneyTransferSummary moneyTransferSummary = new MoneyTransferSummary();
					moneyTransferSummary.setPersonId(callRecord.getParentPersonId());

					// If no CurrentSearchDate stored on recordCall, then start with current date
					// Else use the one from recordCall as CurrentSearchDate
					if (ValidationUtil.isNullOrZero(callRecord.getCurrentSearchDate()))
					{
						moneyTransferSummary.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
					}
					else
					{
						moneyTransferSummary.setCreateDateUTC(callRecord.getCurrentSearchDate());
					}

					// Get summary info
					InternalResultsResponse<MoneyTransferSummary> moneyTransferSummaryListResponse =
							getCallRecordDAC().fetchMoneyTransferSummaryByMember(moneyTransferSummary);

					if (!moneyTransferSummaryListResponse.isInError())
					{
						// Set the new CurrentSearchDate
						callRecord.setCurrentSearchDate(moneyTransferSummary.getCreateDateUTC());

						// Update callRecord with new CurrentSearchDate
						if (getCallRecordDAC().updateCallRecord(callRecord).isInError())
						{
							exception = true;
						}
						else
						{
							// Records were returned, create SummaryResponse
							response =
									createSummaryResponse(ivrRequest, currentLanguage,
											moneyTransferSummaryListResponse,
											callRecord);
						}
					}
					else if (moneyTransferSummaryListResponse.getStatus().equals(Status.NoRowsFoundError))
					{
						// Deal with 0 records
						response =
								createResponse(ivrRequest, getAdditionalViewName(), nextController,
										currentLanguage.getLanguage(), null, response);
					}
					else
					{
						exception = true;
					}
				}
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
	 * Count number of active {@link Location} for a {@link Member}.
	 *
	 * @param member the member
	 * @return the int
	 */
	private int countActiveLocationForMember(Member member)
	{
		int locationCount = 0;

		for (EmploymentInfo employmentInfo : member.getEmploymentInfoList())
		{
			if (StatusEnum.ACTIVE.equals(employmentInfo.getEmploymentInfoStatus()))
			{
				locationCount++;
			}
		}

		return locationCount;
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
	 * Creates the summary response.
	 *
	 * @param ivrRequest the ivr request
	 * @param currentLanguage the current language
	 * @param transferSummaryList the transfer summary list
	 * @param callRecord the call record
	 * @return the internal results response
	 */
	private InternalResultsResponse<String> createSummaryResponse(IvrRequest ivrRequest,
			SupportedLanguage currentLanguage, InternalResultsResponse<MoneyTransferSummary> transferSummaryList,
			CallRecord callRecord)
	{

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		// TODO Handle when cannot load resource
		String message =
				loadResource(ivrRequest.getResourcePath(), getViewName(), currentLanguage.getLanguage());

		message =
				replaceParameters(message, getNextController(), ivrRequest,
						createAdditionalTokens(transferSummaryList));

		// Now extract the pieces that are hard-coded and marked with <!-- -->
		String sayMessage = IvrCommonRoutines.extractTextFromTemplate(message);
		Integer index = 1;

		List<Say> sayList = new ArrayList<Say>();
		CallRecordContext callRecordContext = new CallRecordContext();
		callRecordContext.setParentId(callRecord.getId());
		for (MoneyTransferSummary moneyTransferSummary : transferSummaryList.getResultsList())
		{
			// Make sure to record the ids in the DB for when coming back later
			callRecordContext.setIvrOption(index);
			callRecordContext.setMoneyTransferId(moneyTransferSummary.getMoneyTransferId());
			callRecordContext.setRecipientFirstName(moneyTransferSummary.getFirstName());
			callRecordContext.setRecipientLastName(moneyTransferSummary.getLastName());

			// If inserted OK, add to response, otherwise skip it
			if (!getCallRecordDAC().insertCallRecordContext(callRecordContext).isInError())
			{
				String msg =
						sayMessage.replace("%FIRST_LAST_NAME%",
								moneyTransferSummary.getFirstName() + " " + moneyTransferSummary.getLastName());
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

	/**
	 * Creates the additional tokens.
	 *
	 * @param transferSummaryList the transfer summary list
	 * @return the map
	 */
	private Map<String, String> createAdditionalTokens(InternalResultsResponse<MoneyTransferSummary> transferSummaryList)
	{
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("NUMBER_OF_TRANSACTIONS", String.valueOf(transferSummaryList.getResultsList().size()));

		if (transferSummaryList.getResultsList().size() == 1)
		{
			tokens.put(TRANSACTION_NOUN, TRANSACTION);
		}
		else
		{
			tokens.put(TRANSACTION_NOUN, TRANSACTIONS);
		}

		tokens.put("TRANSACTION_DATE", IvrCommonRoutines.formatDateForIvr(PGSiDateUtil
				.truncateTimeUTC(transferSummaryList.getFirstResult().getCreateDateUTC())));

		return tokens;
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
