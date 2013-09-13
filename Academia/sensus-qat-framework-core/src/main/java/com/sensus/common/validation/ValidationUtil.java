package com.sensus.common.validation;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.common.util.SensusDateUtil;

/**
 * This class is used to facilitate various types of validation including:
 * <ol>
 * <li>The use of the validation pattern where a validator is loaded from the Spring context based on a given object
 * requiring validation. The validator is a Spring bean loaded at run time and queried to see if it supports validation
 * of a given object.
 * <li>Similar to the first but instead of validating the entire object individual attributes can be validated using the
 * same technique of loading the validator from the Spring context based on the class type.
 * <li>Utility field type validation methods can be used to perform simple validation upon a given String object. This
 * includes simple validation like checking for not null, minimum and maximum length, numeric, valid date, email etc.
 * <li>As a last result there is a "validateOther" concept where given a validator bean name the bean is loaded and
 * objects are passed into the bean for validation. This concept should be used only as a last resort when one of the
 * other domain or business or attribute validation methods will not work or will not meet the needs of the application.
 * This approach makes use of a Spring bean defined in the Spring context and loads the bean and class instance and
 * invokes the validation method passing in the given arguments. This method helps take advantage of the validation
 * pattern for non-standard validations.<br/>
 * </ol>
 * The Validator class, also defined as a Spring bean, must implement the {@link IValidator} interface.
 * There are many convenience methods included in this class for the various types of validation listed above which in
 * turn makes it easier to leverage this class and its functionality.
 */
public final class ValidationUtil
{
	/** The Constant PERIOD. */
	private static final String PERIOD = ".";

	/** The Constant FWD_SLASH. */
	private static final String FWD_SLASH = "/";

	/**
	 * Checks if a List is null or empty.
	 * 
	 * @param aList the list to be checked
	 * 
	 * @return true, if List is null or empty
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(List aList)
	{
		return isNull(aList) || aList.isEmpty();
	}

	/**
	 * Check if the List is null or empty and if so add a field validation error message to the message list
	 * using the included message code.
	 * 
	 * @param aList the a list
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	@SuppressWarnings("rawtypes")
	public static List<MessageInfo> isNullOrEmpty(List aList, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNullOrEmpty(aList))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}

		return msgList;
	}

	/**
	 * Checks if a String is null or empty.
	 * 
	 * @param value the string to be checked
	 * 
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(String value)
	{
		return isNull(value) || (value.trim().length() <= 0);
	}

	/**
	 * Check if the String is null or empty and if so add a field validation error message to the message list
	 * using the included message code.
	 * 
	 * @param value the value
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isNullOrEmpty(String value, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNull(value) || (value.trim().length() <= 0))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}

		return msgList;
	}

	/**
	 * Checks if a String is null or empty.<br/>
	 * 
	 * @param value The String value to check
	 * @param defaultValue The default value to be returned if the input value fails the test.
	 * @return If the value is not null or empty then the parameter value will be returned.
	 *         If the value is null or empty then the parameter default-value will be returned.
	 */
	public static String isNullOrEmpty(String value, String defaultValue)
	{
		if (isNullOrEmpty(value))
		{
			return defaultValue;
		}
		return value;
	}

	/**
	 * Checks if an Object passed in is null.
	 * 
	 * @param object to be checked
	 * 
	 * @return true, if is null
	 */
	public static boolean isNull(Object object)
	{
		return object == null;
	}

	/**
	 * Check if the Object is null and if so add a field validation error message to the message list
	 * using the included message code.
	 * 
	 * @param object the object
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isNull(Object object, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNull(object))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}

		return msgList;
	}

	/**
	 * Checks if a Integer is null or zero.
	 * 
	 * @param value The value to be checked
	 * 
	 * @return true, if is null or zero
	 */
	public static boolean isNullOrZero(Integer value)
	{
		return isNull(value) || (value == 0);
	}

	/**
	 * Check if the Integer is null or zero and if so add a field validation error message to the message list
	 * using the included message code.
	 * 
	 * @param value the value
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isNullOrZero(Integer value, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNullOrZero(value))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}
		return msgList;
	}

	/**
	 * Checks if a Integer is null or empty.<br/>
	 * 
	 * @param value The Integer value to check
	 * @param defaultValue The default value to be returned if the input value fails the test.
	 * @return If the value is not null or empty then the parameter value will be returned.
	 *         If the value is null or empty then the parameter default-value will be returned.
	 */
	public static Integer isNullOrZero(Integer value, Integer defaultValue)
	{
		if (isNullOrZero(value))
		{
			return defaultValue;
		}
		return value;
	}

	/**
	 * Checks if the Long value is null or zero.
	 * 
	 * @param value The long value
	 * @return true, if is null or zero
	 */
	public static boolean isNullOrZero(Long value)
	{
		return isNull(value) || (value == 0);
	}

	/**
	 * Check if the Long is null or zero and if so add a field validation error message to the message list
	 * using the included message code.
	 * 
	 * @param value the value
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isNullOrZero(Long value, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNullOrZero(value))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}
		return msgList;
	}

	/**
	 * Checks if a Long is null or empty.<br/>
	 * 
	 * @param value The Long value to check
	 * @param defaultValue The default value to be returned if the input value fails the test.
	 * @return If the value is not null or empty then the parameter value will be returned.
	 *         If the value is null or empty then the parameter default-value will be returned.
	 */
	public static Long isNullOrZero(Long value, Long defaultValue)
	{
		if (isNullOrZero(value))
		{
			return defaultValue;
		}
		return value;
	}

	/**
	 * Checks if Double value is null or zero.
	 * 
	 * @param value The double value
	 * @return true, if is null or zero
	 */
	public static boolean isNullOrZero(Double value)
	{
		return isNull(value) || (value == 0);
	}

	/**
	 * Checks if is null or zero.
	 * 
	 * @param value The Double value to check
	 * @param defaultValue The default value to be returned if the input value fails the test.
	 * @return If the value is not null or empty then the parameter value will be returned.
	 *         If the value is null or empty then the parameter default-value will be returned.
	 */
	public static Double isNullOrZero(Double value, Double defaultValue)
	{
		if (isNullOrZero(value))
		{
			return defaultValue;
		}
		return value;
	}

	/**
	 * Check if the Double is null or zero and if so add a field validation error message to the message list
	 * using the included message code.
	 * 
	 * @param value the value
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isNullOrZero(Double value, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNullOrZero(value))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}
		return msgList;
	}

	/**
	 * Checks if BigDecimal is null or zero.
	 * 
	 * @param value The BigDecimal value
	 * @return true, if is null or zero
	 */
	public static boolean isNullOrZero(BigDecimal value)
	{
		return isNull(value) || value.equals(BigDecimal.ZERO);
	}

	/**
	 * Check if the BigDecimal is null or zero and if so add a field validation error message to the message list
	 * using the included message code.
	 * 
	 * @param value the value
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isNullOrZero(BigDecimal value, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNullOrZero(value))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}
		return msgList;
	}

	/**
	 * Checks if a BigDecimal is null or empty.<br/>
	 * 
	 * @param value The BigDecimal value to check
	 * @param defaultValue The default value to be returned if the input value fails the test.
	 * @return If the value is not null or empty then the parameter value will be returned.
	 *         If the value is null or empty then the parameter default-value will be returned.
	 */
	public static BigDecimal isNullOrZero(BigDecimal value, BigDecimal defaultValue)
	{
		if (isNullOrZero(value))
		{
			return defaultValue;
		}
		return value;
	}

	/**
	 * Checks if the length of string is valid and update message list with error
	 * and message if it fails.
	 * 
	 * @param field the field
	 * @param maxLength the max length
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isValidLength(String field, int maxLength, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (field.length() > maxLength)
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}

		return msgList;
	}

	/**
	 * Checks to insure the date parameters are not null and that the fromDate is less than the toDate.
	 * 
	 * @param fromDate The from date
	 * @param toDate The to date
	 * @return true if the dates are not null and the fromDate is before the toDate.
	 */
	public static boolean isValidEffectiveDates(Date fromDate, Date toDate)
	{
		if (fromDate == null)
		{
			return false;
		}
		if (toDate == null)
		{
			return false;
		}

		if (SensusDateUtil.compare(fromDate, toDate) >= 0)
		{
			return false;
		}

		return true;
	}

	/**
	 * Checks if the input data is in proper MMDDYYY format.
	 * 
	 * @param dateValue the date value
	 * @param lenient See the SimpleDateFormat found in the java.lang package for more information about lenient.
	 * @return true, if the value is in the property format.
	 */
	public static boolean isDateMMDDYYYY(String dateValue, boolean lenient)
	{
		SimpleDateFormat simpleDateFormat = null;

		// Does the input use slashes ?
		if (dateValue.indexOf(FWD_SLASH) > 0)
		{
			simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		}
		else if (dateValue.indexOf(PERIOD) > 0)
		{
			simpleDateFormat = new SimpleDateFormat("MM.dd.yyyy");
		}
		else
		{
			simpleDateFormat = new SimpleDateFormat("MMddyyyy");
		}

		try
		{
			simpleDateFormat.setLenient(lenient);
			simpleDateFormat.parse(dateValue);
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Checks to see if the data value is in the property YYYYMMDD format.
	 * 
	 * @param dateValue the date value
	 * @param lenient See the SimpleDateFormat found in the java.lang package for more information about lenient.
	 * @return true, if is in the proper format.
	 */
	public static boolean isDateYYYYMMDD(String dateValue, boolean lenient)
	{
		SimpleDateFormat simpleDateFormat = null;
		// Does the input use slashes ?
		if (dateValue.indexOf(FWD_SLASH) > 0)
		{
			simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		}
		else if (dateValue.indexOf(PERIOD) > 0)
		{
			simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		}
		else
		{
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		}

		try
		{
			simpleDateFormat.setLenient(lenient);
			simpleDateFormat.parse(dateValue);
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Used to verify that a "new" set of dates is within an "existing" set of dates.<br/>
	 * If the "new" dates are NOT within the existing dates then a field validation message is added to the given
	 * message list. Basically the logic is as follows:
	 * 
	 * <pre>
	 * if new-from-date greater-than or equal to existing-from-date...
	 *     if new-from-date less-than or equal to existing-from-date then the new-from-date is within the existing dates.
	 *         if new-to-date greater-than or equal to existing-from-date...
	 *             if new-to-date less-than or equal to existing-from-date then the new-from-date is within the
	 *                 Everything is good validation passes.
	 *                 
	 * If any of the if statements fails then a new field validation message is added to the list.
	 * </pre>
	 * 
	 * @param existingFromDate The existing from date
	 * @param existingToDate The existing to date
	 * @param newFromDate The new from date
	 * @param newToDate The new to date
	 * @param msgCode The message code to post if validation fails
	 * @param msgInfo A collection of {@link MessageInfo} objects. The collection cannot be null.
	 * @return true If the method was able to make the comparisons, false if one of the dates are null.
	 */
	public static boolean areNewDatesWithin(Date existingFromDate, Date existingToDate, Date newFromDate,
			Date newToDate, String msgCode, List<MessageInfo> msgInfo)
	{
		// Make sure the dates are all not-null
		if ((existingFromDate == null) || (existingToDate == null) || (newFromDate == null) || (newToDate == null))
		{
			return false;
		}

		// if new-from-date greater-than or equal to existing-from-date...
		if (SensusDateUtil.compare(newFromDate, existingFromDate) >= 0)
		{
			// if new-from-date less-than or equal to existing-from-date then the new-from-date is within the existing
			// dates.
			if (SensusDateUtil.compare(newFromDate, existingToDate) <= 0)
			{
				// if new-to-date greater-than or equal to existing-from-date...
				if (SensusDateUtil.compare(newToDate, existingFromDate) >= 0)
				{
					// if new-to-date less-than or equal to existing-from-date then the new-from-date is within the
					// existing dates.
					if (SensusDateUtil.compare(newToDate, existingToDate) <= 0)
					{
						return true;
					}
				}
			}
		}

		msgInfo.add(MessageInfo.createFieldValidationError(msgCode, existingFromDate, existingToDate,
				newFromDate, newToDate));

		return true;
	}

	/**
	 * Check to make sure a new set of effective from and to dates do not overlap existing from and to dates.<br/>
	 * If there is an overlap then a message will added to the given message-list using the given message code.<br/>
	 * Basically the logic is this:<br/>
	 * 
	 * <pre>
	 * if the new-from-date is less-than or equal-to the existing date
	 *     if the new-to-date is greater-than the existing-from-date...
	 *         add new field validation error to message list.
	 * </pre>
	 * 
	 * @param existingFromDate The existing from date
	 * @param existingToDate The existing to date
	 * @param newFromDate The new from date
	 * @param newToDate The new to date
	 * @param msgCode The message code to post if validation fails
	 * @param msgInfo A collection of {@link MessageInfo} objects. The collection cannot be null.
	 * @return true If the method was able to make the comparisons, false if one of the dates are null.
	 */
	public static boolean areDatesOverlapping(Date existingFromDate, Date existingToDate, Date newFromDate,
			Date newToDate, String msgCode, List<MessageInfo> msgInfo)
	{
		// Make sure the dates are all not-null
		if ((existingFromDate == null) || (existingToDate == null) || (newFromDate == null) || (newToDate == null))
		{
			return false;
		}

		// if the new-from-date is less-than or equal-to the existing date...
		if (SensusDateUtil.compare(newFromDate, existingToDate) <= 0)
		{
			// if the new-to-date is greater-than or equal-to the existing-from-date...
			if (SensusDateUtil.compare(newToDate, existingFromDate) >= 0)
			{
				msgInfo.add(MessageInfo.createFieldValidationError(msgCode, existingFromDate, existingToDate,
						newFromDate,
						newToDate));
			}
		}

		return true;
	}

	// [end] More convenience methods - "is" methods
	/**
	 * Checks if the parameter value is a valid long value.
	 * 
	 * @param longValue The value to check
	 * @return true, if the value is valid.
	 */
	public static boolean isLong(String longValue)
	{
		try
		{
			Long.parseLong(longValue);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks if the parameter value is a valid Short value.
	 * 
	 * @param shortValue The value to check
	 * @return true, if the value is valid.
	 */
	public static boolean isShort(String shortValue)
	{
		try
		{
			Short.parseShort(shortValue);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks if given value is an integer.
	 * 
	 * @param integerValue The value to check
	 * @return true, if the value is valid.
	 */
	public static boolean isInteger(String integerValue)
	{
		try
		{
			Integer.parseInt(integerValue);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * Checks to see if the value is a valid double value.
	 * 
	 * @param doubleValue the double value
	 * @return true, if is double
	 */
	public static boolean isDouble(String doubleValue)
	{
		try
		{
			Double.parseDouble(doubleValue);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * Checks if the value is a valid decimal value.
	 * 
	 * @param decimalValue The value to check
	 * @return true, if the value is valid.
	 */
	public static boolean isDecimal(String decimalValue)
	{
		try
		{
			new BigDecimal(decimalValue);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks if the value is a valid float value.
	 * 
	 * @param floatValue the float value
	 * @return true, if is float
	 */
	public static boolean isFloat(String floatValue)
	{
		try
		{
			Float.parseFloat(floatValue);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks if value is a valid currency value.
	 * 
	 * @param currencyValue The value to check
	 * @return true, if the value is valid.
	 */
	public static boolean isCurrency(String currencyValue)
	{
		return isDecimal(currencyValue);
	}

	/**
	 * Validate sort fields.
	 * /**
	 * Used to validate the sort fields within an instance of {@link InquiryRequest}.<br/>
	 * If validation fails and one of the sort fields contained in the inquiry request is not in the validSortFields
	 * collection than the given messageId parameter will be used to post as new field validation error message to
	 * the MessageList collection. Along with the message two arguments will be included with the message.
	 * <ul>
	 * <li>The first message argument will be a string containing a comma separated list of the sort fields which are
	 * invalid.
	 * <li>The second message argument will be a string containing a comma separated list of all the valid sort fields
	 * based on the validSortFields parameter passed into this method.
	 * </ul>
	 * This allows for some type of message like: "Invalid sort fields[first-name, last-name], valid sort fields include
	 * [last name, zip, phone]" to be created. Note the caller of the method must provide a message which formats the
	 * text accordingly.<br/>
	 * The validSortFields parameter should be injected into the validator which invokes this method. This same map
	 * should also be injected into the DAC so that the sort fields can be translated onto their corresponding column
	 * names. This map should be defined as a bean so it can re-used between the validator and DAC as follows:
	 * 
	 * <pre>
	 * 	&lt;util:map id="providerInquiryValidSortFields">
	 * 	    &lt;entry key="firstName" value="first_name"/>
	 *     	&lt;entry key="lastName" value="last_name"/>
	 *     	&lt;entry key="middleInitial" value="mi"/>
	 * 	&lt;/util:map>
	 * </pre>
	 * 
	 * To leverage the "util" schema make sure your context file includes in the header the following namespace
	 * definition: xmlns:util="http://www.springframework.org/schema/util<br/>
	 * As well as the following XSD reference:
	 * http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd <br/>
	 * Then within the validator add a validSortFields property with getter/setter methods.
	 * Finally, inject the map into the validator as follows:
	 * 
	 * <pre>
	 * &lt;bean id="validator2" class="com.hp.hc.frameworktest.base.unittest.Validator2">
	 * 		&lt;property name="validSortFields" ref="providerInquiryValidSortFields"/>
	 * 	&lt;/bean>
	 * </pre>
	 * 
	 * With the map defined as a bean it can be reused in the DAC.
	 * 
	 * @param inquiryRequest The inquiry request
	 * @param validSortFields The valid sort fields map.
	 * @return the list
	 */
	public static List<SortExpression> validateSortFields(InquiryRequest inquiryRequest,
			Map<String, String> validSortFields)
	{
		return validateSortFields(inquiryRequest.getSortExpressions(), validSortFields);
	}

	/**
	 * Validate sort fields.
	 * 
	 * @param sortExpressions the sort expressions
	 * @param validSortFields the valid sort fields where the sortExpression.field is located in the validSortField
	 *            Map looking for a corresponding key.
	 * @return the list
	 */
	public static List<SortExpression> validateSortFields(List<SortExpression> sortExpressions,
			Map<String, String> validSortFields)
	{
		List<SortExpression> badExpressions = new ArrayList<SortExpression>();

		// nothing to do.
		if ((sortExpressions == null) || (sortExpressions.size() == 0))
		{
			return badExpressions;
		}

		for (SortExpression expression : sortExpressions)
		{
			if (expression.getField() != null)
			{
				if (!validSortFields.containsKey(expression.getField()))
				{
					badExpressions.add(expression);
				}
			}
			else
			{
				badExpressions.add(expression);
			}
		}

		return badExpressions;
	}

}
