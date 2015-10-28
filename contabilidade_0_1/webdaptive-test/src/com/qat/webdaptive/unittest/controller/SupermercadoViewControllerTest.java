/* This class run unit tests against a JSON & tradtional spring mvc controller
 * using JSON data as the request and JSON data as the response just as the browser would do. Thus allowing us
 * to truly test the spring mvc layer for the 1st time. Not just doing it at integration time
 */
package com.qat.webdaptive.unittest.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:com/qat/webdaptive/unittest/conf/unittest-controller-context.xml"})
public class SupermercadoViewControllerTest extends AbstractJUnit4SpringContextTests
{

	/*
	 * Note: did not test any Spring MVC BAS (Web Service) methods to allow these tests to run standalone
	 * without having the Sysmgmt Sample BE running for training purposes only. THIS IS NOT a QAT Global Standard.
	 */

	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Before
	public void setup() throws Exception
	{
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void fetchMVCCounties() throws Exception
	{
		// Check for view & model for full view
		mockMvc.perform(get("/county/fetchCounties?view=full"))
				.andExpect(status().isOk())
				.andExpect(view().name(containsString("/counties/counties_mvc")))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("countyResponse"));

		// Check for view & model for full view view is anything but mobile
		mockMvc.perform(get("/county/fetchCounties?view=something"))
				.andExpect(status().isOk())
				.andExpect(view().name(containsString("/counties/counties_mvc")))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("countyResponse"));

		// Check for view & model for mobile view
		mockMvc.perform(get("/county/fetchCounties?view=mobile"))
				.andExpect(status().isOk())
				.andExpect(view().name(containsString("/counties/counties_mvc.m")))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("countyResponse"));

		// Check for view & model, view empty
		mockMvc.perform(get("/county/fetchCounties?view="))
				.andExpect(status().isOk())
				.andExpect(view().name(containsString("/counties/counties_mvc")))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("countyResponse"));

		// Check for view & model, view missing
		mockMvc.perform(get("/county/fetchCounties"))
				.andExpect(status().isBadRequest());

		// Check for view invalid url
		mockMvc.perform(get("/county/badurl/fetchCounties/"))
				// 404 error
				.andExpect(status().isNotFound());

	}

	@Test
	public void fetchMVCCountiesBAI() throws Exception
	{
		// Check for view & model
		mockMvc.perform(get("/county/fetchAllCountiesBAI"))
				.andExpect(status().isOk())
				.andExpect(view().name(containsString("/counties/counties_mvc_bai")))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("countyResponse"));

		// Check for view invalid url
		mockMvc.perform(get("/county/badurl/fetchAllCountiesBAI/"))
				// 404 error
				.andExpect(status().isNotFound());

	}
}
