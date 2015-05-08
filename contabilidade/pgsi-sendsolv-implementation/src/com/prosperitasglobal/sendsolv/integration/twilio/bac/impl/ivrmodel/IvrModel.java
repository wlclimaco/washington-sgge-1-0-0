package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrModel;
import com.prosperitasglobal.sendsolv.integration.twilio.dac.IIvrDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.validation.ValidationUtil;

public abstract class IvrModel implements IIvrModel
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrModel.class);

	private String viewName;

	private String nextController;

	private String exceptionViewName;

	private IIvrDAC callRecordDAC;

	private String audioFileName;

	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		String resourceName = null;
		String nextAction = null;
		String defaultLanguage = "en";
		String currentLanguage;

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		// Define language now
		if (!ValidationUtil.isNull(callRecord))
		{
			// if language was not yet defined (this could happen if an invalid option is selected for language in the
			// IVR), use default
			if (ValidationUtil.isNull(callRecord.getLanguage()))
			{
				currentLanguage = defaultLanguage;
			}
			else
			{
				currentLanguage = callRecord.getLanguage();
			}

			resourceName = getViewName();
			nextAction = getNextController();
		}
		else
		{
			resourceName = getExceptionViewName();
			currentLanguage = defaultLanguage;
		}

		return createResponse(ivrRequest, resourceName, nextAction, currentLanguage, null, response);
	}

	protected InternalResultsResponse<String> createResponse(IvrRequest ivrRequest, String resourceName,
			String nextController, String currentLanguage, Map<String, String> additionalTokens,
			InternalResultsResponse<String> response)
			{
		String message =
				loadResource(ivrRequest.getResourcePath(), resourceName, currentLanguage);

		// Handle when cannot load resource
		message = replaceParameters(message, nextController, ivrRequest, additionalTokens);

		response.addResult(message);

		if (LOG.isDebugEnabled())
		{
			LOG.debug("the message to be returned will be: " + message);
		}
		return response;
			}

	protected String replaceParameters(String message, String nextController, IvrRequest request,
			Map<String, String> additionalTokens)
	{

		Map<String, String> tokens = new HashMap<String, String>();

		tokens.put("RESPONSEURL", request.getMainUrl() + request.getControllerPath());

		if (!ValidationUtil.isNullOrEmpty(nextController))
		{
			tokens.put("ACTION", nextController);
		}

		tokens.put("CURRENTACTION", request.getIvrController());

		// insert any additionalTokens passed in
		if (!ValidationUtil.isNull(additionalTokens))
		{
			tokens.putAll(additionalTokens);
		}

		// Create pattern of the format "[(token1|token2)]"
		String patternString = "%(" + StringUtils.join(tokens.keySet(), "|") + ")%";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(message);

		StringBuffer sb = new StringBuffer();
		while (matcher.find())
		{
			matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);

		return sb.toString();
	}

	protected String loadResource(String resourcePath, String resourceName, String language)
	{
		String resourceAsString = null;

		ClassPathResource theFile =
				(ClassPathResource)QATAppContext.getApplicationContext().getResource(
						"classpath:" + resourcePath + "/" + language + "/" + resourceName);

		if (theFile.exists())
		{
			try
			{
				InputStream inputStream = theFile.getInputStream();
				resourceAsString = IOUtils.toString(inputStream, "UTF-8");
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("**** Resource Path = " + resourcePath + "/" + language + "/" + resourceName);
			LOG.debug("**** The result of the load was = " + resourceAsString);
		}

		return resourceAsString;
	}

	protected CallRecord fetchCallRecord(IvrRequest ivrRequest, InternalResponse response)
	{
		CallRecord callRecord = null;

		// Retrieve the call from DB
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

		return callRecord;
	}

	@Override
	public String getViewName()
	{
		return viewName;
	}

	public void setViewName(String resourceName)
	{
		viewName = resourceName;
	}

	public IIvrDAC getCallRecordDAC()
	{
		return callRecordDAC;
	}

	public void setCallRecordDAC(IIvrDAC callRecordDAC)
	{
		this.callRecordDAC = callRecordDAC;
	}

	public String getExceptionViewName()
	{
		return exceptionViewName;
	}

	public void setExceptionViewName(String exceptionResourceName)
	{
		exceptionViewName = exceptionResourceName;
	}

	public String getNextController()
	{
		return nextController;
	}

	public void setNextController(String nextAction)
	{
		nextController = nextAction;
	}

	public String getAudioFileName()
	{
		return audioFileName;
	}

	public void setAudioFileName(String audioFileName)
	{
		this.audioFileName = audioFileName;
	}
}
