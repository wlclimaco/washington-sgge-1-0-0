package com.prosperitasglobal.sendsolv.integration.twilio.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrModel;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.twilio.sdk.verbs.Say;

/**
 * The Class CommonRoutines.
 */
public final class IvrCommonRoutines
{
	private static final String END_XML_COMMENT = "-->";

	private static final String START_XML_COMMENT = "<!--";

	private static final int FOUR = 4;

	private static IIvrModel exceptionModel;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrCommonRoutines.class);

	private static TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");

	/**
	 * A private default constructor.
	 */
	private IvrCommonRoutines()
	{
	}

	public static String formatPhoneForIvr(String phone)
	{
		// Strips the 1 --> Only works for US !!!
		phone = phone.substring(1, phone.length());
		return String.format("(%s) %s-%s", phone.substring(0, 3), phone.substring(3, 6),
				phone.substring(6, 10));
	}

	public static String formatDateForIvr(Long dateValue)
	{
		if (dateValue == null)
		{
			return null;
		}

		DateFormat df = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		df.setTimeZone(UTC_TIMEZONE);
		return df.format(dateValue);
	}

	public static SupportedLanguage getCurrentLanguage(String languageText, List<SupportedLanguage> languagesList)
	{
		SupportedLanguage currentLanguage = null;

		for (SupportedLanguage supportedLanguage : languagesList)
		{
			if (languageText.equalsIgnoreCase(supportedLanguage.getLanguage()))
			{
				currentLanguage = supportedLanguage;

			}
		}

		return currentLanguage;
	}

	public static InternalResultsResponse<String> createCatchAllResponse(IvrRequest request, CallRecord callRecord)
	{
		InternalResultsResponse<String> internalResponse = new InternalResultsResponse<String>();

		internalResponse = getExceptionModel().resolveAction(request, callRecord);

		return internalResponse;
	}

	/**
	 * Given a String, extracts whatever text is between the following symbols: <!-- and -->.
	 *
	 * @param message the message
	 * @return the string
	 */
	public static String extractTextFromTemplate(String message)
	{
		int start = message.indexOf(START_XML_COMMENT);
		int endIndex = message.indexOf(END_XML_COMMENT);

		return message.substring(start + FOUR, endIndex);
	}

	/**
	 * Replace text from template.
	 *
	 * @param message the message
	 * @param sayList the say list
	 * @return the string
	 */
	public static String replaceTextFromTemplate(String message, List<Say> sayList)
	{
		int start = message.indexOf("{");
		int endIndex = message.indexOf("}");

		String templateText = message.substring(start, endIndex + 1);

		StringBuffer newText = new StringBuffer();

		for (Say say : sayList)
		{
			newText.append(say.toXML());
		}

		return message.replace(templateText, newText.toString());
	}

	public static IIvrModel getExceptionModel()
	{
		return exceptionModel;
	}

	public static void setExceptionModel(IIvrModel exceptionModel)
	{
		IvrCommonRoutines.exceptionModel = exceptionModel;
	}
}