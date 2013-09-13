package com.sensus.dm.common.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.Response;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class AbstractTestBaseBCL.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:conf/sensus-dm-resourcebundles-context.xml",
		"classpath:unittest-abstract-base-test-bcf-bcl-context.xml"})
public abstract class AbstractTestBaseBusiness extends AbstractJUnit4SpringContextTests
{
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(AbstractTestBaseBusiness.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant NEGATIVE_TEN. */
	protected static final int NEGATIVE_TEN = -10;

	/** The Constant ZERO. */
	protected static final int ZERO = 0;

	/** The Constant ONE. */
	protected static final int ONE = 1;

	/** The Constant TWO. */
	protected static final int TWO = 2;

	/** The Constant THREE. */
	protected static final int THREE = 3;

	/** The Constant FOUR. */
	protected static final int FOUR = 4;

	/** The Constant FIVE. */
	protected static final int FIVE = 5;

	/** The Constant SIX. */
	protected static final int SIX = 6;

	/** The Constant SEVEN. */
	protected static final int SEVEN = 7;

	/** The Constant EIGHT. */
	protected static final int EIGHT = 8;

	/** The Constant NINE. */
	protected static final int NINE = 9;

	/** The Constant TEN. */
	protected static final int TEN = 10;

	/** The Constant ELEVEN. */
	protected static final int ELEVEN = 11;

	/** The Constant TWELVE. */
	protected static final int TWELVE = 12;

	/** The Constant THIRTEEN. */
	protected static final Integer THIRTEEN = 13;

	/** The Constant FOURTEEN. */
	protected static final Integer FOURTEEN = 14;

	/** The Constant FIFTEEN. */
	protected static final Integer FIFTEEN = 15;

	/** The Constant SIXTEEN. */
	protected static final Integer SIXTEEN = 16;

	/** The Constant TWENTY. */
	protected static final Integer TWENTY = 20;

	/** The Constant TWENTY_FOUR. */
	protected static final Integer TWENTY_FOUR = 24;

	/** The Constant TWENTY_FIVE. */
	protected static final Integer TWENTY_FIVE = 25;

	/** The Constant TWENTY_SIX. */
	protected static final Integer TWENTY_SIX = 26;

	/** The Constant THIRTY. */
	protected static final Integer THIRTY = 30;

	/** The Constant FORTY_FIVE. */
	protected static final Integer FORTY_FIVE = 45;

	/** The Constant FIFTY. */
	protected static final Integer FIFTY = 50;

	/** The Constant SIXTY_TWO. */
	protected static final Integer SIXTY_TWO = 62;

	/** The Constant NINETY_EIGHT. */
	protected static final Integer NINETY_EIGHT = 98;

	/** The Constant NINETY_NINE. */
	protected static final Integer NINETY_NINE = 99;

	/** The Constant ONE_HUNDRED. */
	protected static final int ONE_HUNDRED = 100;

	/** The Constant ONE_HUNDRED_ONE. */
	protected static final int ONE_HUNDRED_ONE = 101;

	/** The Constant HUNDRED_FIFTY_ONE. */
	protected static final int HUNDRED_FIFTY_ONE = 151;

	/** The Constant TWO_HUNDRED. */
	protected static final Integer TWO_HUNDRED = 200;

	/** The Constant TWO_HUNDRED_ONE. */
	protected static final Integer TWO_HUNDRED_ONE = 201;

	/** The Constant TWO_HUNDRED_FIFTY_FIVE. */
	protected static final Integer TWO_HUNDRED_FIFTY_FIVE = 255;

	/** The Constant TWO_HUNDRED_FIFTY_SIX. */
	protected static final Integer TWO_HUNDRED_FIFTY_SIX = 256;

	/** The Constant ONE_THOUSAND. */
	protected static final Integer ONE_THOUSAND = 1000;

	/** The Constant ONE_THOUSAND_FIVE. */
	protected static final int ONE_THOUSAND_FIVE = 1005;

	/** The Constant ONE_THOUSAND_THREE_HUNDRED_SEVENTY_NINE. */
	protected static final Integer ONE_THOUSAND_THREE_HUNDRED_SEVENTY_NINE = 1379;

	/** The Constant ONE_THOUSAND_FOUR_HUNDRED_FOURTY. */
	protected static final Integer ONE_THOUSAND_FOUR_HUNDRED_FOURTY = 1440;

	/** The Constant TWO_THOUSAND_THIRTEEN. */
	protected static final Integer TWO_THOUSAND_THIRTEEN = 2013;

	/** The Constant ONE. */
	protected static final String STRING_ONE = "1";

	/** The Constant TWO. */
	protected static final String STRING_TWO = "2";

	/** The Constant THREE. */
	protected static final String STRING_THREE = "3";

	/** The Constant STRING_TEN. */
	protected static final String STRING_TEN = "10";

	/** The Constant STRING_ONE_HUNDRED. */
	protected static final String STRING_ONE_HUNDRED = "100";

	/** The Constant STRING_NINETY_NINE. */
	protected static final String STRING_NINETY_NINE = "99";

	/** The Constant DEVICE_ID. */
	protected static final String DEVICE_ID = "1010M";

	/** The Constant FLEXNET_ID. */
	protected static final BigInteger ELECTRIC_FLEXNET_ID = new BigInteger("1001");

	/** The Constant ELECTRIC_METER_ID. */
	protected static final String ELECTRIC_METER_ID = "1N6029416458";

	/** The Constant WATER_FLEXNET_ID. */
	protected static final BigInteger WATER_FLEXNET_ID = new BigInteger("234561299");

	/** The Constant DEVICE_ID_WATER_B75505999. */
	protected static final String DEVICE_ID_WATER_B75505999 = "B75505999";

	/** The Constant GAS_FLEXNET_ID. */
	protected static final BigInteger GAS_FLEXNET_ID = new BigInteger("1330");

	/** The Constant GAS_DEVICE_ID. */
	protected static final String GAS_DEVICE_ID = "1330M";

	/** The Constant FLEXNET_ID_1002. */
	protected static final String FLEXNET_ID_1002 = "1002";

	/** The Constant FLEXNET_ID. */
	protected static final String FLEXNET_ID = "1010";

	/** The Constant HAN_FLEXNET_ID. */
	protected static final BigInteger HAN_FLEXNET_ID = new BigInteger("2153943262073435");

	/** The Constant HAN_DEVICE_ID. */
	protected static final String HAN_DEVICE_ID = "IHD925B";

	/** The Constant METER_FLEXNET_ID. */
	protected static final BigInteger METER_FLEXNET_ID = new BigInteger("46722565");

	/** The Constant METER_DEVICE_ID. */
	protected static final String METER_DEVICE_ID = "1N6028953757";

	/** The Constant ENTEK_FLEXNET_ID. */
	protected static final BigInteger ENTEK_FLEXNET_ID = new BigInteger("50402376");

	/** The Constant ENTEK_DEVICE_ID. */
	protected static final String ENTEK_DEVICE_ID = "560375";

	/** The Constant PREMISE_ID. */
	protected static final String PREMISE_ID = "3";

	/** The Constant METER. */
	protected static final String METER = "METER";

	/** The Constant IHD. */
	protected static final String IHD = "IHD";

	/** The Constant LCM. */
	protected static final String LCM = "LCM";

	/** The Constant METER_ID_2. */
	protected static final String METER_ID_2 = "1002M";

	/** The Constant DOT. */
	protected static final String DOT = ".";

	/** The Constant COMMA. */
	protected static final String COMMA = ",";

	/** The Constant SPACE. */
	protected static final String SPACE = " ";

	/** The Constant STR_PARENTHESIS_CLOSE. */
	protected static final String STR_PARENTHESIS_CLOSE = ") ";

	/** The Constant STR_PARENTHESIS_OPEN. */
	protected static final String STR_PARENTHESIS_OPEN = "(";

	/** The Constant ASTERISK_VALUE. */
	protected static final String ASTERISK_VALUE = "*";

	/** The Constant FORMATTED_DATE_TIME. */
	protected static final String FORMATTED_DATE_TIME = "yyyy-MM-dd h:mma";

	/** The Constant FORMATTED_DATE. */
	protected static final String FORMATTED_DATE = "yyyy-MM-dd";

	/** The Constant TIME_ZONE_N3. */
	protected static final String TIME_ZONE_N3 = "-3";

	/** The Constant TIME_ZONE_AMERICA_SAO_PAULO. */
	protected static final String TIME_ZONE_AMERICA_SAO_PAULO = "America/Sao_Paulo";

	/** The Constant ORDER_BY. */
	protected static final String ORDER_BY = "name";

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant SITUACTION_SET_METHOD_NAME. */
	private static final String SITUACTION_METHOD_NAME = "setSituationsEnum";

	/** The Constant EVALUABLE_SET_METHOD_NAME. */
	private static final String EVALUABLE_METHOD_NAME = "setEvaluableMethods";

	/** The Constant EVALUABLE_CLASS_NAME. */
	private static final String EVALUABLE_CLASS_NAME = "setEvaluableClass";

	/** The Constant AREA_SET_METHOD_NAME. */
	private static final String AREA_SET_METHOD_NAME = "setAreaEnum";

	/** The Constant ERROR_CODE. */
	protected static final String ERROR_CODE = "error";

	/** The Constant FAIL. */
	protected static final String FAIL = "Fail";

	/** The Constant LOG_EXCEPTION_MESSAGE. */
	private static final Object LOG_EXCEPTION_MESSAGE = "Can not set area enum because an exception occurred";

	/** The Constant CUSTOMER_ID. */
	protected static final String CUSTOMER_ID = "ACME";

	/** The Constant USER_CONTEXT_LOCALE. */
	protected static final String USER_CONTEXT_LOCALE = "us";

	/** The Constant DM_TENANT. */
	protected static final DMTenant DM_TENANT = new DMTenant(CUSTOMER_ID);

	/** The USER. */
	protected static final String USER = "QAT";

	/** The Constant LOCALE. */
	protected static final String LOCALE = "Locale";

	/** The Constant FILE_NAME. */
	protected static final String FILE_NAME = "fileTest.csv";

	/** The Constant ADDRESS. */
	protected static final String ADDRESS = "address";

	/** The Constant CITY. */
	protected static final String CITY = "city";

	/** The Constant ZIP. */
	protected static final String ZIP = "zip";

	/** The Constant ASSERT_MESSAGES_EQUAL. */
	private static final String ASSERT_MESSAGES_EQUAL = "Messages amount should be equal.";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEVICE_TYPE_ENUM_REQUIRED. */
	protected static final String DEVICE_TYPE_ENUM_REQUIRED = "sensus.epm.devicevalidator.devicetype.required";

	/** The Constant SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED. */
	protected static final String ORDERBY_REQUIRED = "sensus.epm.orderbyvalidator.sort.required";

	/** The Constant PAGE_SIZE_INVALID. */
	protected static final String PAGE_SIZE_INVALID = "sensus.epm.pagesizevalidator.page.size.invalid";

	/** The Constant START_ROW_INVALID. */
	protected static final String START_ROW_INVALID = "sensus.epm.pagesizevalidator.start.row.invalid";

	/** The Constant CUSTOMER_ID_REQUIRED. */
	protected static final String CUSTOMER_ID_REQUIRED =
			"sensus.epm.tenantrequestvalidator.customer.id.required";

	/** The Constant USER_CONTEXT_REQUIRED. */
	protected static final String USER_CONTEXT_REQUIRED =
			"sensus.epm.tenantrequestvalidator.user.context.required";

	/** The Constant USER_ID_REQUIRED. */
	protected static final String USER_ID_REQUIRED =
			"sensus.epm.tenantrequestvalidator.user.id.required";

	/** The Constant SERVICE_TYPE_ENUM_REQUIRED. */
	protected static final String SERVICE_TYPE_ENUM_REQUIRED =
			"sensus.epm.tenantrequestvalidator.service.type.required";

	/** The Constant LOCALE_REQUIRED. */
	protected static final String LOCALE_REQUIRED =
			"sensus.epm.tenantrequestvalidator.locale.required";

	/** The Constant FILE_NAME_REQUIRED. */
	protected static final String FILE_NAME_REQUIRED =
			"sensus.epm.tenantrequestvalidator.filename.required";

	/** The Constant FILE_NAME_INVALID. */
	protected static final String FILE_NAME_INVALID =
			"sensus.epm.tenantrequestvalidator.filename.invalid";

	/** The Constant PROCESS_ID_REQUIRED. */
	protected static final String PROCESS_ID_REQUIRED =
			"sensus.epm.tenantrequestvalidator.processid.required";

	/** The Constant RANGEDATE_INVALID. */
	protected static final String RANGEDATE_INVALID =
			"sensus.epm.rangedatevalidator.rangedate.invalid";

	/** The Constant REQUIRED. */
	protected static final String EPM_ACTION_REQUIRED = "sensus.epm.actionvalidator.action.required";

	/** The Constant ID_REQUIRED. */
	protected static final String EPM_ACTION_ID_REQUIRED = "sensus.epm.actionvalidator.action.id.required";

	/** The Constant EPM_ACTION_TYPE_DESCRIPTION_REQUIRED. */
	protected static final String EPM_ACTION_TYPE_DESCRIPTION_REQUIRED =
			"sensus.epm.actionvalidator.epm_action_type_description_required";

	/** The Constant EPM_ACTION_ON_DEMAND_REQUIRED. */
	protected static final String EPM_ACTION_ON_DEMAND_REQUIRED = "sensus.epm.actionvalidator.is.on.demand.required";

	/** The Constant EPM_ACTION_MONITORED_REQUIRED. */
	protected static final String EPM_ACTION_MONITORED_REQUIRED = "sensus.epm.actionvalidator.monitored.required";

	/** The Constant EPM_ACTION_TIME_REQUIRED. */
	protected static final String EPM_ACTION_TIME_REQUIRED = "sensus.epm.actionvalidator.time.required";

	/** The Constant EPM_ACTION_ENROLLMENTCODE_REQUIRED. */
	protected static final String EPM_ACTION_ENROLLMENTCODE_REQUIRED =
			"sensus.epm.actionvalidator.enrollmentcode.required";

	/** The Constant EPM_ACTION_DURATION_REQUIRED. */
	protected static final String EPM_ACTION_DURATION_REQUIRED = "sensus.epm.actionvalidator.duration.required";

	/** The Constant EPM_ACTION_ENROLLMENTCODE_INVALID. */
	protected static final String EPM_ACTION_ENROLLMENTCODE_INVALID =
			"sensus.epm.actionvalidator.enrollmentcode.invalid";

	/** The Constant EPM_ACTION_SEND_TEXT_REQUIRED. */
	protected static final String EPM_ACTION_SEND_TEXT_REQUIRED = "sensus.epm.actionvalidator.send.text.required";

	/** The Constant FLEXNET_ID_REQUIRED. */
	protected static final String FLEXNET_ID_REQUIRED = "sensus.epm.radiovalidator.flexnet.id.required";

	/** The Constant ALARM_REQUIRED. */
	protected static final String ALARM_REQUIRED = "sensus.epm.alarmvalidator.alarm.required";

	/** The Constant DEVICE_REQUIRED. */
	protected static final String RADIO_REQUIRED = "sensus.epm.radiovalidator.radio.required";

	/** The Constant ALARM_NAME_REQUIRED. */
	protected static final String ALARM_NAME_REQUIRED = "sensus.epm.alarmvalidator.name.required";

	/** The Constant FLEXNET_ID_REQUIRED. */
	protected static final String ALARM_DATE_REQUIRED = "sensus.epm.alarmvalidator.date.required";

	/** The Constant TENANT_REQUIRED. */
	protected static final String TENANT_REQUIRED =
			"sensus.epm.tenantrequestvalidator.tenant.required";

	/** The Constant INQUIRY_DEVICE_REQUEST_REQUIRED. */
	protected static final String INQUIRY_DEVICE_REQUEST_REQUIRED =
			"sensus.epm.inquirydevicerequestvalidator.inquirydevicerequest.required";

	/** The Constant GEOCODE_REQUIRED_REQUIRED. */
	protected static final String GEOCODE_REQUIRED_REQUIRED =
			"sensus.epm.inquirydevicerequestvalidator.geocodetrunc.required";

	/** The Constant DEVICE_REQUIRED. */
	protected static final String DEVICE_REQUIRED = "sensus.epm.devicevalidator.device.required";

	/** The Constant DEVICE_ID_REQUIRED. */
	protected static final String DEVICE_ID_REQUIRED = "sensus.epm.devicevalidator.device.id.required";

	/** The Constant NETWORK_ADDRESS_HEADER. */
	protected static final String NETWORK_ADDRESS_HEADER = "dm.elec.device.csv.network.address";

	/** The Constant DEVICE_ID_HEADER. */
	protected static final String DEVICE_ID_HEADER = "dm.common.device.csv.device.id";

	/** The Constant ACTION_NAME. */
	protected static final String ACTION_NAME = "dm.common.process.csv.action.name";

	/** The Constant ACTION_TYPE. */
	protected static final String ACTION_TYPE = "dm.common.process.csv.action.type";

	/** The Constant ADDRESS_INVALID. */
	protected static final String ADDRESS_INVALID = "sensus.epm.devicevalidator.address.invalid";

	/** The Constant CITY_INVALID. */
	protected static final String CITY_INVALID = "sensus.epm.devicevalidator.city.invalid";

	/** The Constant ZIP_INVALID. */
	protected static final String ZIP_INVALID = "sensus.epm.devicevalidator.zip.invalid";

	/**
	 * Sets the area.
	 * 
	 * @param businessObject the business object
	 * @param areaEnum the area enum
	 */
	protected void setArea(Object businessObject, EPMAreaEnum areaEnum)
	{
		if (isNull(businessObject) || isNull(areaEnum))
		{
			return;
		}

		List<Field> fields = new ArrayList<Field>();
		CollectionUtils.addAll(fields, businessObject.getClass().getDeclaredFields());
		CollectionUtils.addAll(fields, businessObject.getClass().getSuperclass().getDeclaredFields());

		try
		{
			Class<AbstractMockBase> mockBaseClass = AbstractMockBase.class;
			Method areaSetMethod = mockBaseClass.getDeclaredMethod(AREA_SET_METHOD_NAME, EPMAreaEnum.class);

			for (Field field : fields)
			{
				field.setAccessible(true);
				Object mockObj = field.get(businessObject);

				if (!mockBaseClass.isInstance(mockObj))
				{
					continue;
				}

				areaSetMethod.invoke(mockObj, areaEnum);
			}
		}
		catch (Exception e)
		{
			LOG.error(LOG_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Sets the situation.
	 * 
	 * @param businessObject the business object
	 * @param situation the situation
	 * @param areasInterfaceClass the areas interface class
	 */
	protected void setSituation(Object businessObject, SituationsEnum situation, Class<?> areasInterfaceClass)
	{
		List<Field> fields = new ArrayList<Field>();
		CollectionUtils.addAll(fields, businessObject.getClass().getDeclaredFields());
		CollectionUtils.addAll(fields, businessObject.getClass().getSuperclass().getDeclaredFields());

		try
		{
			Class<AbstractMockBase> mockBaseClass = AbstractMockBase.class;
			Method situationSetMethod = mockBaseClass.getDeclaredMethod(SITUACTION_METHOD_NAME, SituationsEnum.class);

			for (Field field : fields)
			{
				field.setAccessible(true);
				Object mockObj = field.get(businessObject);

				if (!mockBaseClass.isInstance(mockObj) || !isApplicable(mockObj.getClass(), areasInterfaceClass))
				{
					continue;
				}

				situationSetMethod.invoke(mockObj, situation);
			}
		}
		catch (Exception e)
		{
			LOG.error(LOG_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Sets the situation.
	 * 
	 * @param businessObject the business object
	 * @param situation the situation
	 * @param areasInterfaceClass the areas interface class
	 * @param evaluableMethods the evaluable methods
	 */
	protected void setSituation(Object businessObject, SituationsEnum situation, Class<?> areasInterfaceClass,
			String... evaluableMethods)
	{
		List<Field> fields = new ArrayList<Field>();
		CollectionUtils.addAll(fields, businessObject.getClass().getDeclaredFields());
		CollectionUtils.addAll(fields, businessObject.getClass().getSuperclass().getDeclaredFields());

		try
		{
			Class<AbstractMockBase> mockBaseClass = AbstractMockBase.class;
			Method situationSetMethod = mockBaseClass.getDeclaredMethod(SITUACTION_METHOD_NAME, SituationsEnum.class);
			Method evaluableSetMethod = mockBaseClass.getDeclaredMethod(EVALUABLE_METHOD_NAME, List.class);
			Method classSetMethod = mockBaseClass.getDeclaredMethod(EVALUABLE_CLASS_NAME, Class.class);

			for (Field field : fields)
			{
				field.setAccessible(true);
				Object mockObj = field.get(businessObject);

				if (!mockBaseClass.isInstance(mockObj) || !isApplicable(mockObj.getClass(), areasInterfaceClass))
				{
					continue;
				}

				situationSetMethod.invoke(mockObj, situation);
				if (evaluableMethods.length > 0)
				{
					evaluableSetMethod.invoke(mockObj, Arrays.asList(evaluableMethods));
				}

				classSetMethod.invoke(mockObj, areasInterfaceClass);
			}
		}
		catch (Exception e)
		{
			LOG.error(LOG_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Reset mocks data.
	 * 
	 * @param businessObject the business object
	 */
	protected void resetMocksData(Object businessObject)
	{
		List<Field> fields = new ArrayList<Field>();
		CollectionUtils.addAll(fields, businessObject.getClass().getDeclaredFields());
		CollectionUtils.addAll(fields, businessObject.getClass().getSuperclass().getDeclaredFields());

		try
		{
			Class<AbstractMockBase> mockBaseClass = AbstractMockBase.class;
			Method areaSetMethod = mockBaseClass.getDeclaredMethod(AREA_SET_METHOD_NAME, EPMAreaEnum.class);
			Method situationSetMethod = mockBaseClass.getDeclaredMethod(SITUACTION_METHOD_NAME, SituationsEnum.class);
			Method evaluableSetMethod = mockBaseClass.getDeclaredMethod(EVALUABLE_METHOD_NAME, List.class);

			for (Field field : fields)
			{
				field.setAccessible(true);
				Object mockObj = field.get(businessObject);

				if (!mockBaseClass.isInstance(mockObj))
				{
					continue;
				}

				areaSetMethod.invoke(mockObj, EPMAreaEnum.DEFAULT);
				situationSetMethod.invoke(mockObj, SituationsEnum.SUCCESS);
				evaluableSetMethod.invoke(mockObj, new ArrayList<String>());
			}
		}
		catch (Exception e)
		{
			LOG.error(LOG_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Assert messages.
	 * 
	 * @param response the response
	 * @param messages the messages
	 */
	protected void assertMessages(InternalResponse response, String... messages)
	{
		if (isNull(response) || messages.length == 0)
		{
			return;
		}

		assertEquals(ASSERT_MESSAGES_EQUAL, messages.length, response.getMessageInfoList().size());
		assertMessagesInfo(response.getMessageInfoList(), messages);
	}

	/**
	 * Assert messages.
	 * 
	 * @param response the response
	 * @param messages the messages
	 */
	protected void assertMessages(Response response, String... messages)
	{
		if (isNull(response) || messages.length == 0)
		{
			return;
		}

		assertEquals(ASSERT_MESSAGES_EQUAL, messages.length, response.getMessageInfoList().size());
		assertMessagesInfo(response.getMessageInfoList(), messages);
	}

	/**
	 * Assert messages.
	 * 
	 * @param response the response
	 * @param message the message
	 * @param arguments the arguments
	 */
	protected void assertMessages(Response response, String message, List<String> arguments)
	{
		assertMessages(response, message);

		MessageInfo messageInfo = response.getMessageInfoList().get(0);
		Object[] messageInfoArguments = messageInfo.getArguments();
		assertNotNull("Arguments from Message Info should not be null.", messageInfoArguments);
		assertEquals("Arguments amount should be equal.", arguments.size(), messageInfoArguments.length);

		for (Object argument : messageInfoArguments)
		{
			if (!arguments.contains(String.valueOf(argument)))
			{
				fail("Message Info should contains argument: " + argument);
			}
		}
	}

	/**
	 * Creates the device.
	 * 
	 * @param <T> the generic type
	 * @param device the device
	 * @param typeEnum the type enum
	 * @param deviceId the device id
	 * @param cityName the city name
	 * @param zip the zip
	 * @param address the address
	 * @return the t
	 */
	protected <T extends Device> T populatedDevice(T device, DeviceTypeEnum typeEnum, String deviceId, String cityName,
			String zip, String address)
	{
		device.setDeviceId(deviceId);
		device.setRadio(new Radio(new Location(address, cityName, zip)));
		device.setDeviceType(typeEnum);

		return device;
	}

	/**
	 * File reader.
	 * 
	 * @param fileName the file name
	 * @return the list
	 */
	protected List<String[]> fileReader(String fileName)
	{
		List<String[]> columns = new ArrayList<String[]>();
		File file = new File(fileName);
		Scanner arq = null;
		try
		{
			arq = new Scanner(file);
			while (arq.hasNextLine())
			{
				String value = arq.nextLine();
				columns.add(StringUtils.splitByWholeSeparatorPreserveAllTokens(value, COMMA));
				System.out.println(value);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (arq != null)
			{
				arq.close();
			}
		}
		assertTrue(columns.size() > 1);

		return columns;
	}

	/**
	 * Assert messages info.
	 * 
	 * @param messagesInfo the messages info
	 * @param messages the messages
	 */
	private void assertMessagesInfo(List<MessageInfo> messagesInfo, String... messages)
	{
		if (isNullOrEmpty(messagesInfo))
		{
			return;
		}

		List<String> messageList = Arrays.asList(messages);

		for (MessageInfo messageInfo : messagesInfo)
		{
			if (!messageList.contains(messageInfo.getCode()))
			{
				fail("Response object should contain message code: " + messageInfo.getCode());
			}
		}
	}

	/**
	 * Checks if is applicable.
	 * 
	 * @param mockAreaClass the mock area class
	 * @param areasInterfaceClass the areas interface class
	 * @return true, if is applicable
	 */
	private boolean isApplicable(Class<?> mockAreaClass, Class<?>... areasInterfaceClass)
	{
		if (isNull(mockAreaClass))
		{
			return false;
		}

		// Case is not possible evaluate then return true
		if (areasInterfaceClass.length == 0)
		{
			return true;
		}

		Collection<Class<?>> mockInterfaceList = Arrays.asList(mockAreaClass.getInterfaces());

		for (Class<?> areaInterfaceClass : areasInterfaceClass)
		{
			if (mockInterfaceList.contains(areaInterfaceClass))
			{
				return true;
			}
		}
		return false;
	}
}
