package com.sensus.mlc.wui.group.unittest.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.model.IdValuePair;
import com.sensus.mlc.wui.group.action.GroupPageAction;

/**
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class GroupPageActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant RESULT_VALUE. */
	private static final String RESULT_VALUE = "Result Value";

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
	private GroupPageAction getAction()
	{
		return (GroupPageAction)getActionProxy("/group/ajax.list.action").getAction();
	}

	/**
	 * Test execute.
	 */
	public void testExecute()
	{
		GroupPageAction action = getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

	/**
	 * Test actions list.
	 */
	public void testActionsList()
	{
		GroupPageAction action = getAction();
		logger.info("Testing success...");

		Map<String, List<IdValuePair>> actionList = action.getActionList();
		assertEquals(RESULT_COUNT, actionList.size(), 1);
		assertEquals(RESULT_VALUE, action.getText("group.actions.deleteGroup"), actionList.get("Delete Group").get(0)
				.getValue());
	}
}
