package com.sensus.lc.base;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.Response;

/**
 * The Class AbstractTestBaseBCL.
 */
@ContextConfiguration(locations = {
		"classpath:conf/sensus-lc-resourcebundles-context.xml",
		"classpath:unittest-validators-context.xml",
		"classpath:unittest-abstract-base-test-bcf-bcl-context.xml",
		"classpath:sensus-mlc-part-number-context.xml",
		"classpath:sensus-mlc-dim-table-config-context.xml",
		"classpath:conf/sensus-lc-csv-context.xml"})
public abstract class AbstractTestBaseBusiness extends AbstractJUnit4SpringContextTests
{
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(AbstractTestBaseBusiness.class);

	/** The Constant SITUACTION_SET_METHOD_NAME. */
	private static final String SITUACTION_METHOD_NAME = "setSituationsEnum";

	/** The Constant EVALUABLE_SET_METHOD_NAME. */
	private static final String EVALUABLE_METHOD_NAME = "setEvaluableMethods";

	/** The Constant EVALUABLE_CLASS_NAME. */
	private static final String EVALUABLE_CLASS_NAME = "setEvaluableClass";

	/** The Constant TEST_CONTROL_NAME. */
	private static final String TEST_CONTROL_NAME = "setTestControl";

	/** The Constant AREA_SET_METHOD_NAME. */
	private static final String AREA_SET_METHOD_NAME = "setAreaEnum";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	protected static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	protected static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	protected static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant USER_CONTEXT_REQUIRED. */
	protected static final String USER_CONTEXT_REQUIRED = "sensus.mlc.uservalidator.user.context.required";

	/** The Constant ERROR_CODE. */
	protected static final String ERROR_CODE = "error";

	/** The Constant SUCCESS_UPD_ECOMODE_BY_POLE_ID. */
	protected static final String SUCCESS_UPD_ECOMODE_BY_POLE_ID =
			"sensus.mlc.mlc_action.success.upd_ecomode_by_poleid";

	/** The Constant ERROR_UPD_ECOMODE_BY_POLE_ID. */
	protected static final String ERROR_UPD_ECOMODE_BY_POLE_ID =
			"sensus.mlc.mlc_action.error.upd_ecomode_by_poleid";

	/** The Constant ERROR_UPD_ECOMODE_WATTAGE_RATING_REQUIRED. */
	protected static final String WATTAGE_RATING_REQUIRED =
			"sensus.mlc.mlc_action.error.upd_ecomode_wattage_rating_required";

	/** The Constant INCORRECT_IMPORT_ECOMODE. */
	protected static final String INCORRECT_IMPORT_ECOMODE = "sensus.mlc.mlc_action.incorrect.import_ecomode";

	/** The Constant ERROR_IMPORT_ECOMODE. */
	protected static final String ERROR_IMPORT_ECOMODE = "sensus.mlc.mlc_action.error.import_ecomode";

	/** The Constant SUCCESS_IMPORT_ECOMODE. */
	protected static final String SUCCESS_IMPORT_ECOMODE = "sensus.mlc.mlc_action.success.import_ecomode";

	/** The Constant DEFAULT_PROCESS_BCL_EXCEPTION_MSG. */
	protected static final String DEFAULT_ECOMODE_BCL_EXCEPTION_MSG = "sensus.mlc.ecomodebclimpl.defaultexception";

	/** The Constant SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION. */
	protected static final String SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.processbclimpl.defaultexception";

	/**
	 * Sets the area.
	 * 
	 * @param businessObject the business object
	 * @param areaEnum the area enum
	 */
	protected void setArea(Object businessObject, LCAreaEnum areaEnum)
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
			Method areaSetMethod = mockBaseClass.getDeclaredMethod(AREA_SET_METHOD_NAME, LCAreaEnum.class);

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
			LOG.error("Do not possible set area enum because occurred exception.", e);
		}
	}

	/**
	 * Sets the test control.
	 * 
	 * @param businessObject the business object
	 * @param areasInterfaceClass the areas interface class
	 * @param testControl the test control
	 */
	protected void setTestControl(Object businessObject, Class<?> areasInterfaceClass, String testControl)
	{
		if (isNull(testControl))
		{
			return;
		}

		List<Field> fields = new ArrayList<Field>();
		CollectionUtils.addAll(fields, businessObject.getClass().getDeclaredFields());
		CollectionUtils.addAll(fields, businessObject.getClass().getSuperclass().getDeclaredFields());

		try
		{
			Class<AbstractMockBase> mockBaseClass = AbstractMockBase.class;
			Method testControlSetMethod = mockBaseClass.getDeclaredMethod(TEST_CONTROL_NAME, String.class);

			for (Field field : fields)
			{
				field.setAccessible(true);
				Object mockObj = field.get(businessObject);

				if (!mockBaseClass.isInstance(mockObj) || !isApplicable(mockObj.getClass(), areasInterfaceClass))
				{
					continue;
				}

				testControlSetMethod.invoke(mockObj, testControl);
			}
		}
		catch (Exception e)
		{
			LOG.error("Do not possible set area enum because occurred exception", e);
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
			LOG.error("Do not possible set area enum because occurred exception ", e);
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
			LOG.error("Do not possible set area enum because occurred exception", e);
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
			Method areaSetMethod = mockBaseClass.getDeclaredMethod(AREA_SET_METHOD_NAME, LCAreaEnum.class);
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

				areaSetMethod.invoke(mockObj, LCAreaEnum.DEFAULT);
				situationSetMethod.invoke(mockObj, SituationsEnum.SUCCESS);
				evaluableSetMethod.invoke(mockObj, new ArrayList<>());
			}
		}
		catch (Exception e)
		{
			LOG.error("Do not possible reset data enum because occurred exception", e);
		}
	}

	/**
	 * Assert messages.
	 * 
	 * @param internal response the response
	 * @param messages the messages
	 */
	protected void assertMessages(InternalResponse internalResponse, String... messages)
	{
		assertNotNull("Internal response should not be null", internalResponse);

		Response response = new Response();
		handleOperationStatusAndMessages(response, internalResponse, false);
		assertMessages(response, messages);
	}

	/**
	 * Assert messages.
	 * 
	 * @param response the response
	 * @param messages the messages
	 */
	protected void assertMessages(Response response, String... messages)
	{
		assertNotNull("Response should not be null", response);

		List<MessageInfo> messageInfoList = response.getMessageInfoList();
		assertNotNull("Messages from response should not be null", messageInfoList);
		assertEquals("Messages amount should be equal", messages.length, messageInfoList.size());

		assertMessagesInfo(messageInfoList, messages);
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
				fail("Response object should contains message code: " + messageInfo.getCode());
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
