package com.sensus.mlc.wui.ecomode.unittest.action;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.systemsettings.ecomode.action.EcoModeAjaxAction;

/**
 * Test the functionality of GroupAjaxAction.
 * 
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class EcoModeAjaxActionTest extends StrutsSpringTestCase
{

	/**
	 * Load the Spring configuration.
	 * 
	 * @return the context locations
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant EXCEPTION. */
	private static final String EXCEPTION = "EXCEPTION";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Test adding SmartPoints to Groups.
	 * 
	 * @throws Exception the exception
	 */
	public void testImportEcoMode() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/systemsettings/ajax.importEcoMode.action");

		EcoModeAjaxAction action = (EcoModeAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		EcoModeBCFMockImpl bcf = (EcoModeBCFMockImpl)action.getEcoModeBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, EXCEPTION, actionResponse);

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/systemsettings/ajax.importEcoMode.action");
		action = (EcoModeAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		action.setUploadEcoModeFile(new File("arquivo.csv"));
		action.setUploadTag("1,2,3,4");
		// Create REQUEST mock ---- END

		bcf = (EcoModeBCFMockImpl)action.getEcoModeBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, EXCEPTION, actionResponse);

	}
}
