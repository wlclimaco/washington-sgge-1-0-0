package com.sensus.mlc.wui.smartpoint.unittest.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightTypeEnum;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.ecomode.unittest.action.EcoModeBCFMockImpl;
import com.sensus.mlc.wui.schedule.unittest.action.ScheduleBCFMockImpl;
import com.sensus.mlc.wui.smartpoint.action.SmartpointDetailAjaxAction;
import com.sensus.mlc.wui.tag.unittest.action.TagBCFMockImpl;

/**
 * The Class SmartpointDetailAjaxActionTest.
 */
public class SmartpointDetailAjaxActionTest extends StrutsSpringTestCase
{

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "No Response object";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Has Response Messages";

	/** The Constant MESSAGE_MESSAGE_COUNT. */
	private static final String MESSAGE_MESSAGE_COUNT = "Count Response Messages";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	private List<StatusMessage> generateStatusMessages()
	{

		StatusException statusException = new StatusException();
		statusException.setStatusExceptionTypeEnumValue(1);

		List<StatusException> statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(statusException);

		StatusMessage statusMessage = new StatusMessage();

		statusMessage.setId(7600);
		statusMessage.setLightStatusEnumValue(1);
		statusMessage.setStatusExceptions(statusExceptions);

		List<StatusMessage> listStatusMessages = new ArrayList<StatusMessage>();

		listStatusMessages.add(statusMessage);

		return listStatusMessages;

	}

	private SmartpointDetailAjaxAction getApplyScheduleAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/applySchedule.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	private SmartpointDetailAjaxAction getRemoveScheduleAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/removeSchedule.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	private SmartpointDetailAjaxAction getInsertSmartpointToTagAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/insertSmartpointToTag.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	private SmartpointDetailAjaxAction getRemoveSmartpointToTagAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/removeSmartpointToTag.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	private SmartpointDetailAjaxAction getClearStatusAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/clearStatus.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	private SmartpointDetailAjaxAction getUpdateLightProtectedAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/updateLightProtected.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	private SmartpointDetailAjaxAction resetValuesAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/ajax.resetValues.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	private SmartpointDetailAjaxAction getUpdateLightProperty()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/updateLightProperty.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	private SmartpointDetailAjaxAction getFetchEcoMode()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/ajax.fetchEcoMode.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	private SmartpointDetailAjaxAction getFetchEcoModeChart()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/ajax.fetchEcoModeChart.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	private SmartpointDetailAjaxAction getInsertProcessEcoMode()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/ajax.insertProcessEcoMode.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	private SmartpointDetailAjaxAction getGenerateFileCSVEcoMode()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/generateFileEcoMode.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	private SmartpointDetailAjaxAction getUpdateBaseLine()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailAjaxAction action =
				(SmartpointDetailAjaxAction)getActionProxy("/smartpoint/ajax.updateEcoModeBaseLine.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	@Test
	public void testApplySchedule() throws Exception
	{
		SmartpointDetailAjaxAction action = getApplyScheduleAction();

		LightRequest lightRequest = new LightRequest();
		Light light = new Light();

		light.setStatusMessages(generateStatusMessages());

		lightRequest.addLight(light);

		action.setLightRequest(lightRequest);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointProcessorBCFMockImpl smartpointBCF =
				(SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();

		// Test insert smartPoint to tag error
		logger.info("Testing clear status error...");
		smartpointBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointDetailResult());

		// Test Insert Event Schedule Success
		logger.info("Testing clear status success...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		action.setStatusExceptionTypeEnumValue(1);
		assertEquals(Action.SUCCESS, action.clearStatus());

	}

	@Test
	public void testRemoveSchedule() throws Exception
	{
		SmartpointDetailAjaxAction action = getRemoveScheduleAction();

		ScheduleRequest request = new ScheduleRequest();
		OffsetSchedule schedule = new OffsetSchedule();
		schedule.setId(1);

		request.setOffsetSchedule(schedule);

		List<Integer> ids = new ArrayList<Integer>();

		for (int i = 0; i <= 10; i++)
		{
			ids.add(i);
		}

		request.setLightIdList(ids);

		action.setScheduleRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		ScheduleBCFMockImpl smartpointBCF =
				(ScheduleBCFMockImpl)action.getScheduleBCF();

		// Test insert smartPoint to tag error
		logger.info("Testing clear status error...");
		smartpointBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());

		// Test Insert Event Schedule Success
		logger.info("Testing clear status success...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		action.setStatusExceptionTypeEnumValue(1);
		assertEquals(Action.SUCCESS, action.clearStatus());

	}

	/**
	 * Test force light status.
	 * 
	 * @throws Exception the exception
	 */
	public void testForceLightStatus() throws Exception
	{
	}

	/**
	 * Test check updated light status.
	 * 
	 * @throws Exception the exception
	 */
	public void testCheckUpdatedLightStatus() throws Exception
	{
	}

	@Test
	public void testInsertSmartpointToTag()
	{
		SmartpointDetailAjaxAction action = getInsertSmartpointToTagAction();

		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl tagBCF = (TagBCFMockImpl)action.getTagBCF();

		// Test insert smartPoint to tag error
		logger.info("Testing insert smartPoint to tag error...");
		tagBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointDetailResult());

		// Test Insert Event Schedule Success
		logger.info("Testing insert smartPoint to tag success...");
		tagBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		action.setTagId(1);
		assertEquals(Action.SUCCESS, action.insertSmartpointToTag());

	}

	@Test
	public void testClearStatus()
	{

		SmartpointDetailAjaxAction action = getClearStatusAction();

		LightRequest lightRequest = new LightRequest();
		Light light = new Light();

		light.setStatusMessages(generateStatusMessages());

		lightRequest.addLight(light);

		action.setLightRequest(lightRequest);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointProcessorBCFMockImpl smartpointBCF =
				(SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();

		// Test insert smartPoint to tag error
		logger.info("Testing clear status error...");
		smartpointBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointDetailResult());

		// Test Insert Event Schedule Success
		logger.info("Testing clear status success...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		action.setStatusExceptionTypeEnumValue(1);
		assertEquals(Action.SUCCESS, action.clearStatus());
	}

	@Test
	public void testResetValues()
	{
		SmartpointDetailAjaxAction action = resetValuesAction();

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointUpdaterBCFMockImpl smartpointBCF = (SmartPointUpdaterBCFMockImpl)action.getSmartPointUpdaterBCF();

		logger.info("Testing reset values error...");
		smartpointBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointDetailResult());

		logger.info("Testing reset values for light success...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		assertEquals(Action.SUCCESS, action.resetValues());
	}

	@Test
	public void testUpdateLightProtected()
	{
		SmartpointDetailAjaxAction action = getUpdateLightProtectedAction();

		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl tagBCF = (TagBCFMockImpl)action.getTagBCF();

		// Test insert smartPoint to tag error
		logger.info("Testing updated light protected error...");
		tagBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointDetailResult());

		// Test Insert Event Schedule Success
		logger.info("Testing updated light protected success...");
		tagBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		action.setLightProtected(true);
		assertEquals(Action.SUCCESS, action.updateLightProtected());
	}

	@Test
	public void testRemoveSmartpointToTag()
	{
		SmartpointDetailAjaxAction action = getRemoveSmartpointToTagAction();

		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl tagBCF = (TagBCFMockImpl)action.getTagBCF();

		// Test remove smartPoint to tag error
		logger.info("Testing remove smartPoint to tag error...");
		tagBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointDetailResult());

		// Test Insert Event Schedule Success
		logger.info("Testing remove smartPoint to tag success...");
		tagBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		action.setTagId(1);
		assertEquals(Action.SUCCESS, action.removeSmartpointToTag());
	}

	@Test
	public void testUpdateLightProperty() throws Exception
	{

		SmartpointDetailAjaxAction action = getUpdateLightProperty();

		SmartPointProcessorBCFMockImpl smartpointBCF =
				(SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();

		// Test update light property error
		logger.info("Testing update light property error...");
		smartpointBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointDetailResult());

		// Test update light property success (latitude and longitude)
		logger.info("Testing updated light property success (latitude and longitude)...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setIsLocation(true);
		action.setId(1);
		action.setLatitude("100");
		action.setLongitude("-200");
		assertEquals(Action.SUCCESS, action.updateLightProperty());

		// Test update light property success (poleId)
		logger.info("Testing updated light property success (poleId)...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		action.setPoleId("1234567");
		assertEquals(Action.SUCCESS, action.updateLightProperty());
	}

	@Test
	public void testFetchEcoMode() throws Exception
	{

		SmartpointDetailAjaxAction action = getFetchEcoMode();
		EcoModeBCFMockImpl ecoModeBCF = (EcoModeBCFMockImpl)action.getEcoModeBCF();
		logger.info("Testing fetch ecomode error...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_FAILURE);

		logger.info("Testing updated light property success (latitude and longitude)...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		InquiryEcoModeRequest inquiryEcoModeRequest = new InquiryEcoModeRequest();

		SessionAuthenticationTestUtil.setMockSession();
		inquiryEcoModeRequest.setSelectionPaginationIds(new ArrayList<Integer>());
		inquiryEcoModeRequest.getSelectionPaginationIds().add(1);
		inquiryEcoModeRequest.setEndDate(new Date());
		inquiryEcoModeRequest.setInitialDate(new Date());
		inquiryEcoModeRequest.setEndRow(0);
		inquiryEcoModeRequest.setStartRow(0);

		assertEquals(Action.SUCCESS, action.fetchEcoMode());

	}

	@Test
	public void testFetchEcoModeChart() throws Exception
	{

		SmartpointDetailAjaxAction action = getFetchEcoModeChart();
		EcoModeBCFMockImpl ecoModeBCF = (EcoModeBCFMockImpl)action.getEcoModeBCF();
		logger.info("Testing fetch ecomode error...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_FAILURE);

		logger.info("Testing updated light property success (latitude and longitude)...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		InquiryEcoModeRequest inquiryEcoModeRequest = new InquiryEcoModeRequest();

		SessionAuthenticationTestUtil.setMockSession();
		inquiryEcoModeRequest.setSelectionPaginationIds(new ArrayList<Integer>());
		inquiryEcoModeRequest.getSelectionPaginationIds().add(1);
		inquiryEcoModeRequest.setEndDate(new Date());
		inquiryEcoModeRequest.setInitialDate(new Date());
		inquiryEcoModeRequest.setEndRow(0);
		inquiryEcoModeRequest.setStartRow(0);

		assertEquals(Action.SUCCESS, action.fetchEcoMode());

	}

	@Test
	public void testInsertProcessEcoMode() throws Exception
	{

		SmartpointDetailAjaxAction action = getInsertProcessEcoMode();
		EcoModeBCFMockImpl ecoModeBCF = (EcoModeBCFMockImpl)action.getEcoModeBCF();
		logger.info("Testing fetch ecomode error...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_FAILURE);

		logger.info("Testing updated light property success (latitude and longitude)...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		LightSelectionRequest request = new LightSelectionRequest();

		SessionAuthenticationTestUtil.setMockSession();
		request.setIsMonitored(true);

		assertEquals(Action.SUCCESS, action.fetchEcoMode());

	}

	@Test
	public void testGenerateFileCSVEcoMode() throws Exception
	{

		SmartpointDetailAjaxAction action = getGenerateFileCSVEcoMode();
		EcoModeBCFMockImpl ecoModeBCF = (EcoModeBCFMockImpl)action.getEcoModeBCF();
		logger.info("Testing fetch ecomode error...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_FAILURE);

		logger.info("Testing updated light property success (latitude and longitude)...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		InquiryEcoModeRequest request = new InquiryEcoModeRequest();

		SessionAuthenticationTestUtil.setMockSession();
		request.setFileName("test.csv");

		assertEquals(Action.SUCCESS, action.fetchEcoMode());

	}

	@Test
	public void testUpdateBaseLine() throws Exception
	{

		SmartpointDetailAjaxAction action = getUpdateBaseLine();
		EcoModeBCFMockImpl ecoModeBCF = (EcoModeBCFMockImpl)action.getEcoModeBCF();
		logger.info("Testing fetch ecomode error...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_FAILURE);

		logger.info("Testing updated light property success (latitude and longitude)...");
		ecoModeBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		InquiryEcoModeRequest inquiryEcoModeRequest = new InquiryEcoModeRequest();

		SessionAuthenticationTestUtil.setMockSession();
		inquiryEcoModeRequest.setSelectionPaginationIds(new ArrayList<Integer>());
		inquiryEcoModeRequest.getSelectionPaginationIds().add(1);
		inquiryEcoModeRequest.setEcoModeBaselineList(createEcoModeBaselineList(1));
		assertEquals(Action.SUCCESS, action.fetchEcoMode());

	}

	private List<EcoModeBaseline> createEcoModeBaselineList(int amount)
	{

		List<EcoModeBaseline> list = new ArrayList<EcoModeBaseline>();
		for (int i = 0; i < amount; i++)
		{
			list.add(createEcoModeBaseline());
		}
		return list;
	}

	private EcoModeBaseline createEcoModeBaseline()
	{

		return new EcoModeBaseline(createLight(), LightTypeEnum.MH, Double.valueOf(111), false);
	}

	private Light createLight()
	{
		Light light = new Light();
		light.addParameterValue(PropertyEnum.POLE_ID, "123456");
		return light;
	}

	@Test
	public void testCurrentAlarmStatus() throws Exception
	{
		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/ajax.currentAlarmStatus.action");

		SmartpointDetailAjaxAction action = (SmartpointDetailAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);
		action.setLightRequest(new LightRequest());

		SmartPointAccessorBCFMockImpl bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/ajax.currentAlarmStatus.action");
		action = (SmartpointDetailAjaxAction)proxy.getAction();

		LightRequest lightRequest = new LightRequest();

		Light light = new Light();
		light.setId(20);

		List<Light> lightList = new ArrayList<Light>();
		lightList.add(light);

		lightRequest.setLights(lightList);

		action.setLightRequest(lightRequest);

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		CurrentAlarmWarningMessageResponse resp = (CurrentAlarmWarningMessageResponse)action.getResponse();

		assertTrue("Result Count", resp.getCurrentAlarmWarningMessages().size() != 0);

	}
}