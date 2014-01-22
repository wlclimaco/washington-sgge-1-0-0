package com.sensus.lc.controller.util.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.lc.controller.util.AbstractTestBase;

public class SessionAPIControllerTest extends AbstractTestBase
{
	@Test
	public void fetch() throws Exception
	{
		// set request object
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"USER_NAME\",\"direction\":\"Ascending\"}],\"action\":\"table\"}");

		// Success situation
		performTest("/api/session/fetch").andExpect(jsonPath("$.operationSuccess", equalTo(true)));

	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insert() throws Exception
	{
		// set request object
		setData("{\"id\":\"\"}");

		// Success situation
		performTest("/api/session/insert").andExpect(jsonPath("$.operationSuccess", equalTo(true)));

	}

	/**
	 * Delete.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void remove() throws Exception
	{
		// set request object
		setData("{\"selectionPaginationIds\":[89]}");

		// Success situation
		performTest("/api/session/remove").andExpect(jsonPath("$.operationSuccess", equalTo(true)));

	}

}
