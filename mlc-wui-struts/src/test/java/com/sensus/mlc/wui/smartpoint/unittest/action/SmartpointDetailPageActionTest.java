package com.sensus.mlc.wui.smartpoint.unittest.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.model.IdValuePair;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.unittest.action.GroupBCFMockImpl;
import com.sensus.mlc.wui.process.unittest.action.ProcessBCFMockImpl;
import com.sensus.mlc.wui.schedule.unittest.action.ScheduleBCFMockImpl;
import com.sensus.mlc.wui.smartpoint.action.SmartpointDetailPageAction;
import com.sensus.mlc.wui.smartpoint.action.SmartpointPageAction;
import com.sensus.mlc.wui.tag.unittest.action.TagBCFMockImpl;

/**
 * The Class SmartpointDetailPageActionTest.
 */
public class SmartpointDetailPageActionTest extends StrutsSpringTestCase
{

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant DEFAULT_MESSAGE. */
	private static final String DEFAULT_MESSAGE = "Default Message";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

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

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	private SmartpointDetailPageAction getAction(String methodAction)
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointDetailPageAction action =
				(SmartpointDetailPageAction)getActionProxy("/smartpoint/" + methodAction + ".action").getAction();
		action.setServletRequest(request);

		return action;

	}

	/**
	 * Test open smart point detail.
	 */
	@Test
	public void testOpenSmartPointDetail()
	{
		SmartpointDetailPageAction action = getAction("ajax.openSmartPointDetailHeader");

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointAccessorBCFMockImpl smartpointBCF = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();

		// Test Success
		logger.info("Testing success...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);

		/** ASSERTS */
		assertEquals(Action.SUCCESS, action.openSmartPointDetail());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSmartpointId());
		assertFalse("Valid SmartPoint", action.getSmartpointId().equals(0)); // more than zero
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getLightView());

	}

	/**
	 * Test group list.
	 * 
	 * @throws Exception the exception
	 */
	public void testGroupList() throws Exception
	{
		SmartpointDetailPageAction action = getAction("ajax.smartpoint.detail.main");

		SessionAuthenticationTestUtil.setMockSession();

		GroupBCFMockImpl groupBCF = (GroupBCFMockImpl)action.getGroupBCF();

		// Test Success
		logger.info("Testing success...");
		groupBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> groups = action.getGroupList();
		assertEquals(RESULT_COUNT, GroupBCFMockImpl.GROUP_COUNT + 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), groups.get(0)
				.getValue());

		// Test No Records
		logger.info("Testing no records returned...");
		groupBCF.setMode(BaseMockImpl.MODE_EMPTY);
		groups = action.getGroupList();
		assertEquals(RESULT_COUNT, 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), groups.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Failure...");
		groupBCF.setMode(BaseMockImpl.MODE_FAILURE);
		groups = action.getGroupList();
		assertEquals(RESULT_COUNT, 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), groups.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Exception...");
		groupBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		groups = action.getGroupList();
		assertEquals(RESULT_COUNT, 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), groups.get(0)
				.getValue());

	}

	/**
	 * Test fill Address.
	 */
	@Test
	public void testFillAddress()
	{
		SmartpointDetailPageAction action = getAction("ajax.smartpoint.detail.main");

		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl tagBCF = (TagBCFMockImpl)action.getTagBCF();

		// Test Success
		logger.info("Testing fill address success...");
		tagBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setId(1);
		assertEquals(Action.SUCCESS, action.openSmartPointDetail());
	}

	/**
	 * Test schedule list.
	 * 
	 * @throws Exception the exception
	 */
	public void testOffsetScheduleList() throws Exception
	{
		SmartpointDetailPageAction action = getAction("ajax.smartpoint.detail.main");

		SessionAuthenticationTestUtil.setMockSession();

		ScheduleBCFMockImpl scheduleBCF = (ScheduleBCFMockImpl)action.getScheduleBCF();

		// Test Success
		logger.info("Testing success...");
		scheduleBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 10, schedules.size());

		// Test No Records
		logger.info("Testing no records returned...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EMPTY);
		schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 0, schedules.size());

		// Test Fail
		logger.info("Testing Failure...");
		scheduleBCF.setMode(BaseMockImpl.MODE_FAILURE);
		schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointDetailPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Exception...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointDetailPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());
	}

	/**
	 * Test schedule list.
	 * 
	 * @throws Exception the exception
	 */
	public void testEventScheduleList() throws Exception
	{
		SmartpointDetailPageAction action = getAction("ajax.smartpoint.detail.main");

		SessionAuthenticationTestUtil.setMockSession();

		ScheduleBCFMockImpl scheduleBCF = (ScheduleBCFMockImpl)action.getScheduleBCF();

		// Test Success
		logger.info("Testing success...");
		scheduleBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 10, schedules.size());

		// Test No Records
		logger.info("Testing no records returned...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EMPTY);
		schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 0, schedules.size());

		// Test Fail
		logger.info("Testing Failure...");
		scheduleBCF.setMode(BaseMockImpl.MODE_FAILURE);
		schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointDetailPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Exception...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointDetailPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());
	}

	/**
	 * Test fetch process by id.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testFetchProcessById() throws Exception
	{
		SmartpointDetailPageAction action = getAction("include.processingSummary");

		SessionAuthenticationTestUtil.setMockSession();

		ProcessBCFMockImpl processBCF = (ProcessBCFMockImpl)action.getProcessBCF();

		// Test Success
		logger.info("Testing success...");
		processBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		assertEquals(Action.SUCCESS, action.fetchProcessById());

		// Test Failure
		logger.info("Testing failure...");
		processBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertEquals(Action.SUCCESS, action.fetchProcessById());
	}
}
