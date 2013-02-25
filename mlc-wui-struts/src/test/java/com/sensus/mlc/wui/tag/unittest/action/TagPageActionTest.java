package com.sensus.mlc.wui.tag.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.systemsettings.tags.action.TagPageAction;

public class TagPageActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	@Test
	private TagPageAction getAction()
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		TagPageAction action =
				(TagPageAction)getActionProxy("/systemsettings/ajax.tagPageAction.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	@Test
	public void testExecute()
	{
		TagPageAction action = getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

}
