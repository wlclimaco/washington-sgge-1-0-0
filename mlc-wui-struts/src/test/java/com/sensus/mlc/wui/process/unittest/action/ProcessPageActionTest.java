package com.sensus.mlc.wui.process.unittest.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.sensus.mlc.wui.base.model.IdValuePair;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.process.action.ProcessPageAction;
import com.sensus.mlc.wui.user.unittest.action.UserBCFMockImpl;

/**
 * The Class LongRunningProcessPageActionTest.
 */
public class ProcessPageActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private final Logger logger = Logger.getLogger(this.getClass());

	/** The Constant SEVEN. */
	private static final int SEVEN = 7;

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant RESULT_VALUE. */
	private static final String RESULT_VALUE = "Result Value";

	/** The Constant TESTING_SUCCESS. */
	private static final String TESTING_SUCCESS = "TESTING success...";

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
	private ProcessPageAction getAction()
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ProcessPageAction action =
				(ProcessPageAction)getActionProxy("/process/include.process.main.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	/**
	 * Test filter user list.
	 * 
	 * @throws Exception the exception
	 */
	public void testFilterUserList() throws Exception
	{
		ProcessPageAction action = getAction();

		SessionAuthenticationTestUtil.setMockSession();

		UserBCFMockImpl userBCF = (UserBCFMockImpl)action.getUserBCF();

		// Test Success
		logger.info(TESTING_SUCCESS);
		userBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> user = action.getFilterUserList();
		assertEquals(RESULT_COUNT, 15, user.size());

		// Test No Records
		logger.info("Testing no records returned...");
		userBCF.setMode(BaseMockImpl.MODE_EMPTY);
		user = action.getFilterUserList();
		assertEquals(RESULT_COUNT, 0, user.size());

		// Test Fail
		logger.info("Testing Failure...");
		userBCF.setMode(BaseMockImpl.MODE_FAILURE);
		user = action.getFilterUserList();
		assertEquals(RESULT_COUNT, 0, user.size());

		// Test Fail
		logger.info("Testing Exception...");
		userBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		user = action.getFilterUserList();
		assertEquals(RESULT_COUNT, 1, user.size());

	}

	/**
	 * Test actions list.
	 */
	public void testProcessTypeList()
	{
		ProcessPageAction action = getAction();
		logger.info(TESTING_SUCCESS);

		List<IdValuePair> actionList = action.getProcessTypeList();
		assertEquals(RESULT_COUNT, actionList.size(), SEVEN);
		assertEquals(RESULT_VALUE, action.getText("process.page.filter.type.clearalerts"), actionList.get(0)
				.getValue());
	}

}
