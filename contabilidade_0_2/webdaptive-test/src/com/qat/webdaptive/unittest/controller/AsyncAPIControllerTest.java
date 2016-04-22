/* This class run unit tests against an async spring mvc controller
 * using parameter data as the request and JSON data as the response just as the browser would do. Thus allowing us
 * to truly test the spring mvc layer for the 1st time. Not just doing it at integration time
 */
package com.qat.webdaptive.unittest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:com/qat/webdaptive/unittest/conf/unittest-controller-context.xml"})
public class AsyncAPIControllerTest extends AbstractJUnit4SpringContextTests
{
	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Before
	public void setup() throws Exception
	{
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void fetchMessages() throws Exception
	{

		// Good Data & good test
		// make the request
		MvcResult mvcResult = mockMvc.perform(get("/async/api/fetchMessages/1"))
				// request good
				.andExpect(status().isOk())
				// check if async started
				.andExpect(request().asyncStarted())
				.andReturn();

		// check response coming back
		mockMvc.perform(asyncDispatch(mvcResult))
				// did we get the response
				.andExpect(status().isOk())
				// what type of content
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				// check the data
				.andExpect(jsonPath("$..*").isArray())
				.andExpect(jsonPath("$[0]", Matchers.containsString("Msg1 @")))
				.andExpect(jsonPath("$[1]", Matchers.containsString("Msg2 @")))
				.andExpect(jsonPath("$[2]", Matchers.containsString("Msg3 @")));

		// No Data in the parameter & will get not found reply
		mockMvc.perform(get("/async/api/fetchMessages/"))
				.andExpect(status().isNotFound())
				.andReturn();

		// No Data & invalid request due to no data
		mockMvc.perform(post("/async/api-2/fetchMessages/1"))
				// 415 Error
				.andExpect(status().isNotFound());
	}

	@Test
	public void postMessage() throws Exception
	{

		// make the request and post data
		mockMvc.perform(get("/async/api/postMessage/test_message"))
				// request good
				.andExpect(status().isOk());

		// Good Data & good test
		MvcResult mvcResult = mockMvc.perform(get("/async/api/fetchMessages/1"))
				// request good
				.andExpect(status().isOk())
				// check if async started
				.andExpect(request().asyncStarted())
				.andReturn();

		// check response coming back
		mockMvc.perform(asyncDispatch(mvcResult))
				// did we get the response
				.andExpect(status().isOk())
				// what type of content
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				// check the data
				.andExpect(jsonPath("$..*").isArray())
				.andExpect(jsonPath("$[3]", Matchers.containsString("test_message")));

		// No Data in the parameter & will get not found reply
		mockMvc.perform(get("/async/api/postMessage/"))
				.andExpect(status().isNotFound())
				.andReturn();

		// No Data & invalid request due to no data
		mockMvc.perform(post("/async/api-2/postMessage/1"))
				// 415 Error
				.andExpect(status().isNotFound());
	}
}
