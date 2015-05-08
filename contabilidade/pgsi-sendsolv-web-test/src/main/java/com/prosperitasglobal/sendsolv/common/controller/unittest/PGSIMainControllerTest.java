package com.prosperitasglobal.sendsolv.common.controller.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.userdetails.InetOrgPerson;

import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;

// TODO: Auto-generated Javadoc
/**
 * The Class PGSIMainControllerTest.
 */
public class PGSIMainControllerTest extends AbstractTestBase
{
	/** The URL mapping. */
	private static final String FETCH_BAR = "/";

	/** The view mapping constants . */
	private static final String VIEW_MAIN = "pgsi_main";

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PGSIMainAPIControllerTest.class);

	/**
	 * Fetch main.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void fetchMain() throws Exception
	{

		InetOrgPerson inetOrgPerson = Mockito.mock(InetOrgPerson.class);
		Authentication auth = Mockito.mock(Authentication.class);

		Mockito.when(
				inetOrgPerson.getDisplayName())
				.thenReturn("DisplayName");

		Mockito.when(
				auth.getPrincipal())
				.thenReturn(new Object());

		try
		{

			setData("");

			performTestGet(FETCH_BAR).andExpect(view().name(containsString(VIEW_MAIN))).andExpect(
					(model().size(1))).andExpect(model().attributeExists(RESPONSE));

		}
		catch (Exception e)
		{
			String msg = "UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1";
			LOG.error(msg, e);
			fail(msg + e.getMessage());
		}
	}
}
