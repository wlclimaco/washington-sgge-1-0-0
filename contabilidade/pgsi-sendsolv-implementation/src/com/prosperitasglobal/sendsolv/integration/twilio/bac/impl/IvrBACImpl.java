package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrBAC;
import com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrModel;
import com.prosperitasglobal.sendsolv.integration.twilio.dac.IIvrDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class IvrBACImpl.
 */
public class IvrBACImpl implements IIvrBAC
{

	private static final String GREETING = "greeting";

	/** The Constant DEFAULT. */
	private static final String DEFAULT = "default";

	/** The Constant INVALID. */
	private static final String INVALID = "invalid";

	/** The Constant EXCEPTION. */
	private static final String EXCEPTION = "exception";

	private static final String DYNAMIC = "dynamic";

	/** The call record dac. */
	private IIvrDAC callRecordDAC;

	/** The language properties. */
	private HashMap<String, ResourceBundle> languageProperties = new HashMap<String, ResourceBundle>();

	/** The options list. */
	private HashMap<String, HashMap<String, IIvrModel>> controllerList;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrBACImpl.class);

	/**
	 * Instantiates a new ivr bac impl.
	 */
	public IvrBACImpl()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrBAC#processIvrOption(com.prosperitasglobal.sendsolv
	 * .integration.twilio.model.request.IvrRequest)
	 */
	@Override
	public InternalResultsResponse<String> processIvrOption(IvrRequest request)
	{
		IIvrModel iIvrModel = null;

		HashMap<String, IIvrModel> possibleOptions = null;

		InternalResponse response = new InternalResponse();

		// Retrieve information of the current call, if it exists
		CallRecord callRecord = fetchCallRecord(request, response);

		if (!response.isInError())
		{
			if (ValidationUtil.isNullOrEmpty(request.getIvrController()))
			{
				iIvrModel = getAbnormalModel(EXCEPTION);
				// should have only one
			}
			else
			{
				// Retrieve possible options for action requested
				possibleOptions = getControllerList().get(request.getIvrController());

				if (ValidationUtil.isNull(possibleOptions) || (possibleOptions.size() == 0))
				{
					return IvrCommonRoutines.createCatchAllResponse(request, callRecord);
				}
				else
				{
					if (possibleOptions.size() == 1)
					{
						// Only one action, this is it
						iIvrModel = possibleOptions.get(DEFAULT);
					}
					else
					{
						String ivrOption = request.getDigits();

						if (ValidationUtil.isNullOrEmpty(ivrOption))
						{
							// Blank option, get default
							iIvrModel = possibleOptions.get(DEFAULT);
						}
						else
						{
							if (validOption(ivrOption, possibleOptions, callRecord))
							{
								// define action to use
								for (Map.Entry<String, IIvrModel> entry : possibleOptions.entrySet())
								{
									if (entry.getKey().equalsIgnoreCase(ivrOption))
									{
										iIvrModel = entry.getValue();
										break;
									}
								}

								// If it got here with no action, it means the option selected was dynamic. Select the
								// DYNAMIC action then
								if (ValidationUtil.isNull(iIvrModel))
								{
									iIvrModel = possibleOptions.get(DYNAMIC);
								}
							}
							else
							{

								iIvrModel = getAbnormalModel(INVALID);
								iIvrModel.setNextController(request.getPreviousController());
							}
						}
					}
				}
			}
		}
		else
		{
			// Load some generic error
			iIvrModel = getAbnormalModel(EXCEPTION);
		}

		if (ValidationUtil.isNull(iIvrModel))
		{
			LOG.debug(" ********** iIvrModel is null.");
			return IvrCommonRoutines.createCatchAllResponse(request, callRecord);
		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(" **********************************************************************************");
				LOG.debug(" ********** The model to be used is = " + iIvrModel.getClass().getName());
				LOG.debug(" ********** About to resolve Model with resource = " + iIvrModel.getViewName());
				LOG.debug(" **********************************************************************************");
			}

			return iIvrModel.resolveAction(request, callRecord);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrBAC#processDisconnect(com.prosperitasglobal.sendsolv
	 * .integration.twilio.model.request.IvrRequest)
	 */
	@Override
	public InternalResponse processDisconnect(IvrRequest request)
	{
		InternalResponse response = new InternalResponse();

		CallRecord callRecord = new CallRecord();
		callRecord.setCallDurationSeconds(request.getDurationInSeconds());
		callRecord.setCallSid(request.getCallSid());
		callRecord.setStopDateTimeUTC(System.currentTimeMillis());

		response = getCallRecordDAC().updateCallRecord(callRecord);

		return response;
	}

	/**
	 * Fetch call record from Database.
	 *
	 * @param ivrRequest the ivr request
	 * @param response the response
	 * @return the call record
	 */
	protected CallRecord fetchCallRecord(IvrRequest ivrRequest, InternalResponse response)
	{
		CallRecord callRecord = null;

		// If it is the first action, the call does not exist in the database yet
		if (ivrRequest.getIvrController().equalsIgnoreCase(GREETING))
		{
			response.setStatus(Status.OperationSuccess);
		}
		else
		{
			// Retrieve call from DB
			InternalResultsResponse<CallRecord> callRecordListResponse =
					getCallRecordDAC().fetchCallRecordBySid(ivrRequest.getCallSid());

			if (Status.OperationSuccess.equals(callRecordListResponse.getStatus()))
			{
				callRecord = callRecordListResponse.getFirstResult();
			}
			else
			{
				response.merge(callRecordListResponse);
			}
		}

		return callRecord;
	}

	/**
	 * Gets the abnormal action (Invalid or Exception)
	 *
	 * @param name the name
	 * @return the abnormal action
	 */
	private IIvrModel getAbnormalModel(String name)
	{
		// Retrieve the general 'name' option
		HashMap<String, IIvrModel> possibleOptions = getControllerList().get(name);

		// inside it, get the default, should have only one
		IIvrModel ivrAction = possibleOptions.get(DEFAULT);

		return ivrAction;
	}

	/**
	 * Verifies if the selected option is one among the valid ones.
	 * Valid ones are static (hard-coded) or dynamic (generated accordingly to records in the database).
	 * Example of dynamic are options that list {@link MoneyTransfer}, since we can have any number of these.
	 *
	 * @param option the option
	 * @param possibleActions the possible actions
	 * @param callRecord the call record
	 * @return true, if successful
	 */
	private boolean validOption(String option, HashMap<String, IIvrModel> possibleActions, CallRecord callRecord)
	{
		// These are 'static' options
		for (Map.Entry<String, IIvrModel> entry : possibleActions.entrySet())
		{
			if (entry.getKey().equalsIgnoreCase(option))
			{
				return true;
			}
		}

		// These are 'dynamic' options
		for (CallRecordContext callRecordContext : callRecord.getCallRecordContextList())
		{
			if (callRecordContext.getIvrOption().intValue() == Integer.parseInt(option))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets the language properties.
	 *
	 * @return the language properties
	 */
	public HashMap<String, ResourceBundle> getLanguageProperties()
	{
		return languageProperties;
	}

	/**
	 * Sets the language properties.
	 *
	 * @param languageProperties the language properties
	 */
	public void setLanguageProperties(HashMap<String, ResourceBundle> languageProperties)
	{
		this.languageProperties = languageProperties;
	}

	/**
	 * Gets the call record dac.
	 *
	 * @return the call record dac
	 */
	public IIvrDAC getCallRecordDAC()
	{
		return callRecordDAC;
	}

	/**
	 * Sets the call record dac.
	 *
	 * @param callRecordDAC the new call record dac
	 */
	public void setCallRecordDAC(IIvrDAC callRecordDAC)
	{
		this.callRecordDAC = callRecordDAC;
	}

	/**
	 * Gets the options list.
	 *
	 * @return the options list
	 */
	public HashMap<String, HashMap<String, IIvrModel>> getControllerList()
	{
		return controllerList;
	}

	/**
	 * Sets the options list.
	 *
	 * @param controllerList the options list
	 */
	public void setControllerList(HashMap<String, HashMap<String, IIvrModel>> controllerList)
	{
		this.controllerList = controllerList;
	}
}
