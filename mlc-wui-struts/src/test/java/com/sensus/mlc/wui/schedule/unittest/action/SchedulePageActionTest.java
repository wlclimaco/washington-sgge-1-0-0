package com.sensus.mlc.wui.schedule.unittest.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.model.IdValuePair;
import com.sensus.mlc.wui.schedule.action.SchedulePageAction;

/**
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class SchedulePageActionTest extends StrutsSpringTestCase
{
	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant RESULT_VALUE. */
	private static final String RESULT_VALUE = "Result Value";

	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	private SchedulePageAction getAction()
	{
		return (SchedulePageAction)getActionProxy("/schedule/ajax.list.action").getAction();
	}

	public void testExecute()
	{
		SchedulePageAction action = (SchedulePageAction)getActionProxy("/schedule/ajax.list.action").getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

	/**
	 * Test actions list.
	 */
	public void testActionsList()
	{
		SchedulePageAction action = getAction();
		logger.info("Testing success...");

		List<IdValuePair> actionList = action.getActionList();
		assertEquals(RESULT_COUNT, actionList.size(), 1);
		assertEquals(RESULT_VALUE, action.getText("schedule.actions.deleteSchedule"), actionList.get(0)
				.getValue());
	}
}
