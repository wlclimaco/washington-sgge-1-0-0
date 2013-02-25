package com.sensus.mlc.wui.search.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.search.action.SavedSearchPageAction;

/**
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Cristiane Cobo
 * 
 */
public class SavedSearchPageActionTest extends StrutsSpringTestCase
{
	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

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
	private SavedSearchPageAction getAction()
	{
		return (SavedSearchPageAction)getActionProxy("/search/ajax.list.action").getAction();
	}

	/**
	 * Test execute.
	 */
	public void testExecute()
	{
		SavedSearchPageAction action = getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

}
