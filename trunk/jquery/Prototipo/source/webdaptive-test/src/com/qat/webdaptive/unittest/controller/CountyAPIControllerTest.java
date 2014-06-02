/* This class run unit tests against a JSON & tradtional spring mvc controller
 * using JSON data as the request and JSON data as the response just as the browser would do. Thus allowing us
 * to truly test the spring mvc layer for the 1st time. Not just doing it at integration time
 */
package com.qat.webdaptive.unittest.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:com/qat/webdaptive/unittest/conf/unittest-controller-context.xml"})
public class CountyAPIControllerTest extends AbstractJUnit4SpringContextTests
{
	/*
	 * Note: did not test any Spring MVC BAS (Web Service or REST Service) methods to allow these tests to run standalone
	 * without having the Sysmgmt Sample BE running for training purposes only. THIS IS NOT a QAT Global Standard.
	 */

	private String emptyData = "{}";

	private String badData = "{\"userContext\"}";

	private String fetchAllData =
			"{\"userContext\":{\"userId\":\"scott\",\"userRole\":\"[ROLE_DOMAIN ADMINS, ROLE_DOMAIN USERS]\",\"id\":null,\"localeString\":\"en_US\",\"tenant\":{\"modelAction\":null,\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"id\":null},\"authorities\":[{\"id\":null,\"name\":null}]}}";
	private String refreshData =
			"{\"userContext\":{\"userId\":\"scott\",\"userRole\":\"[ROLE_DOMAIN ADMINS, ROLE_DOMAIN USERS]\",\"id\":null,\"localeString\":\"en_US\",\"tenant\":{\"modelAction\":null,\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"id\":null},\"authorities\":[{\"id\":null,\"name\":null}]},\"refreshInt\":45}";

	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Before
	public void setup() throws Exception
	{
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void fetchAllBAI() throws Exception
	{
		// Empty Data & good test
		mockMvc.perform(post("/county/api/fetchAllBAI").contentType(MediaType.APPLICATION_JSON).content(emptyData.getBytes()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.counties", hasSize(15)))
				.andExpect(jsonPath("$.counties[1].description", containsString("fetch description 1")));

		// Good Data & good test
		mockMvc.perform(post("/county/api/fetchAllBAI").contentType(MediaType.APPLICATION_JSON).content(fetchAllData.getBytes()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.counties", hasSize(15)))
				.andExpect(jsonPath("$.counties[1].description", containsString("fetch description 1")));

		// Bad Data & invalid request due to bad data
		mockMvc.perform(post("/county/api/fetchAllBAI").contentType(MediaType.APPLICATION_JSON).content(badData.getBytes()))
				// 400 Error
				.andExpect(status().isBadRequest());

		// No Data & invalid request due to no data
		mockMvc.perform(post("/county/api/fetchAllBAI"))
				// 415 Error
				.andExpect(status().isUnsupportedMediaType());
	}

	@Test
	public void refreshBAI() throws Exception
	{
		// empty countyData & good test
		mockMvc.perform(post("/county/api/refreshBAI").contentType(MediaType.APPLICATION_JSON).content(emptyData.getBytes()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.counties", hasSize(15)))
				.andExpect(jsonPath("$.counties[12].description", containsString("refresh description 12")));

		// good countyData & good test
		mockMvc.perform(post("/county/api/refreshBAI").contentType(MediaType.APPLICATION_JSON).content(refreshData.getBytes()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.counties", hasSize(45)))
				.andExpect(jsonPath("$.counties[42].description", containsString("refresh description 42")));

		// Bad Data & invalid request due to bad data
		mockMvc.perform(post("/county/api/refreshBAI").contentType(MediaType.APPLICATION_JSON).content(badData.getBytes()))
				// 400 Error
				.andExpect(status().isBadRequest());

		// No Data & invalid request due to no data content type
		mockMvc.perform(post("/county/api/refreshBAI"))
				// 415 Error
				.andExpect(status().isUnsupportedMediaType());
	}
}
