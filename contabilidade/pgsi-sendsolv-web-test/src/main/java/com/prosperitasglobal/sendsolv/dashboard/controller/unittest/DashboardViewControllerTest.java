package com.prosperitasglobal.sendsolv.dashboard.controller.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;

public class DashboardViewControllerTest extends AbstractTestBase
{
	/** The URL mapping constants. */
	private static final String FETCH = "/dashboard";

	private static final String FETCH_INITIAL_LOAD = "/dashboard?initialLoad=false";

	/** The view mapping constants . */
	private static final String VIEW_DASHBOARD_MAIN = "/dashboard/dashboard_main";

	private static final String RESPONSE = "response";

	private static final Logger LOG = LoggerFactory.getLogger(DashboardViewControllerTest.class);

	@Test
	public void loadDashboard() throws Exception
	{

		try
		{

			setData("");

			performTestGet(FETCH).andExpect(view().name(containsString(VIEW_DASHBOARD_MAIN))).andExpect(
					(model().size(0)));

			performTestGet(FETCH_INITIAL_LOAD).andExpect(view().name(containsString(VIEW_DASHBOARD_MAIN))).andExpect(
					(model().size(1)));

		}
		catch (Exception e)
		{
			String msg = "UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1";
			LOG.error(msg, e);
			fail(msg + e.getMessage());
		}

	}
}
