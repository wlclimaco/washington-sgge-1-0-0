package com.qat.samples.sysmgmt.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:conf/countyapicontrollertest-context.xml",
		"classpath:conf/qat-sysmgmt-controller-security-context.xml", "classpath:conf/unittest-base-context.xml" })
public class CountyAPIControllerTest extends AbstractJUnit4SpringContextTests {
	@Resource
	private FilterChainProxy springSecurityFilterChain;

	private String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("rod:koala").getBytes()));

	private String emptyData = "{}";

	private String badData = "{\"userContext\"}";

	private String fetchPagedData = "{\"pageSize\":20,\"startPage\":0,\"sortExpressions\":null,\"preQueryCount\":true,\"maxPreQueryCount\":0}";

	private String countyMaintenanceData = "{\"county\": {\"id\": 123, \"description\": \"asdsdasdsads\"}, \"returnList\": true, \"returnListPaged\": true}";

	private String badCountyMaintenanceData = "{\"county\": {\"id\": , \"description\": \"asdsdasdsads\"}, \"returnList\": true, \"returnListPaged\": true}";

	private MockMvc mockMvc;
	private ICountyBAC countyBAC;

	@Autowired
	protected WebApplicationContext wac;

	public ICountyBAC getMockCountyBAC() {
		return countyBAC;
	}

	@Resource
	public void setMockCountyBAC(ICountyBAC mockBAC) {
		countyBAC = mockBAC;
	}

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
	}

	private InternalResultsResponse<County> createFetchResponse() {
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		List<County> list = new ArrayList<>();
		list.add(new County(1, "county1desc"));
		list.add(new County(2, "county2desc"));
		response.addResults(list);
		return response;
	}

	@Test
	public void fetchCountiesPaged() throws Exception {
		Mockito.when(getMockCountyBAC().fetchCountiesByRequest(Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(createFetchResponse());
		// Good Data & good test
		mockMvc.perform(post("/county/api/fetchPage").contentType(MediaType.APPLICATION_JSON)
				.content(fetchPagedData.getBytes()).header("Authorization", basicDigestHeaderValue))
				.andExpect(status().isOk()).andExpect(jsonPath("$.counties", hasSize(2)))
				.andExpect(jsonPath("$.counties[0].description", containsString("county1desc"))).andDo(print());

		// Bad Data & invalid request due to bad data
		mockMvc.perform(post("/county/api/fetchPage").contentType(MediaType.APPLICATION_JSON)
				.content(badData.getBytes()).header("Authorization", basicDigestHeaderValue))
				// 400 Error
				.andExpect(status().is4xxClientError()).andDo(print());

		// No Data & invalid request due to unsupported media type
		mockMvc.perform(post("/county/api/fetchPage").header("Authorization", basicDigestHeaderValue))
				// 415 Error
				.andExpect(status().is4xxClientError()).andDo(print());

		// Bad Data, Missing Security & invalid request due to bad security
		mockMvc.perform(
				post("/county/api/fetchPage").contentType(MediaType.APPLICATION_JSON).content(badData.getBytes()))
				// 30X error
				.andExpect(status().is3xxRedirection()).andDo(print());

		// ensures the method on the mock was called 1 time.
		Mockito.verify(getMockCountyBAC()).fetchCountiesByRequest(Matchers.any(PagedInquiryRequest.class));
	}

	@Test
	public void insertCounty() throws Exception {

		Mockito.when(getMockCountyBAC().insertCounty(Matchers.any(CountyMaintenanceRequest.class)))
				.thenReturn(createFetchResponse());
		// Good Data & good test
		mockMvc.perform(post("/county/api/insert").contentType(MediaType.APPLICATION_JSON)
				.content(countyMaintenanceData.getBytes()).header("Authorization", basicDigestHeaderValue))
				.andExpect(status().isOk()).andExpect(jsonPath("$.counties", hasSize(2)))
				.andExpect(jsonPath("$.counties[0].description", containsString("county1desc"))).andDo(print());

		// Bad Data & good test
		mockMvc.perform(post("/county/api/insert").contentType(MediaType.APPLICATION_JSON)
				.content(badCountyMaintenanceData.getBytes()).header("Authorization", basicDigestHeaderValue))
				.andExpect(status().is4xxClientError()).andDo(print());

		// ensures the method on the mock was called 1 time.
		Mockito.verify(getMockCountyBAC()).insertCounty(Matchers.any(CountyMaintenanceRequest.class));
	}

	@Test
	public void updateCounty() throws Exception {

		Mockito.when(getMockCountyBAC().updateCounty(Matchers.any(CountyMaintenanceRequest.class)))
				.thenReturn(createFetchResponse());
		// Good Data & good test
		mockMvc.perform(post("/county/api/update").contentType(MediaType.APPLICATION_JSON)
				.content(countyMaintenanceData.getBytes()).header("Authorization", basicDigestHeaderValue))
				.andExpect(status().isOk()).andExpect(jsonPath("$.counties", hasSize(2)))
				.andExpect(jsonPath("$.counties[0].description", containsString("county1desc"))).andDo(print());

		// Bad Data & good test
		mockMvc.perform(post("/county/api/update").contentType(MediaType.APPLICATION_JSON)
				.content(badCountyMaintenanceData.getBytes()).header("Authorization", basicDigestHeaderValue))
				.andExpect(status().is4xxClientError()).andDo(print());

		// ensures the method on the mock was called 1 time.
		Mockito.verify(getMockCountyBAC()).updateCounty(Matchers.any(CountyMaintenanceRequest.class));
	}

	@Test
	public void deleteCounty() throws Exception {

		Mockito.when(getMockCountyBAC().deleteCounty(Matchers.any(CountyMaintenanceRequest.class)))
				.thenReturn(createFetchResponse());
		// Good Data & good test
		mockMvc.perform(get("/county/api/delete").param("countyId", "1").param("retList", "true")
				.param("retPaged", "true").header("Authorization", basicDigestHeaderValue)).andExpect(status().isOk())
				.andExpect(jsonPath("$.counties", hasSize(2)))
				.andExpect(jsonPath("$.counties[0].description", containsString("county1desc"))).andDo(print());

		// Bad Data & good test
		mockMvc.perform(get("/county/api/delete").param("countyId2", "1").param("retList", "true")
				.param("retPaged", "true").header("Authorization", basicDigestHeaderValue))
				.andExpect(status().is4xxClientError()).andDo(print());

		// ensures the method on the mock was called 1 time.
		Mockito.verify(getMockCountyBAC()).deleteCounty(Matchers.any(CountyMaintenanceRequest.class));
	}

	// @Test
	// public void refreshCounties() throws Exception
	// {
	//
	// Mockito.when(getMockCountyBAC().refreshCounties(Matchers.any(RefreshRequest.class))).thenReturn(
	// createFetchResponse());
	// // Good Data & good test
	// mockMvc.perform(
	// get("/county/api/refresh").param("refreshInt", 1).param("retList",
	// "true").param("retPaged", "true")
	// .header("Authorization", basicDigestHeaderValue))
	// .andExpect(status().isOk())
	// .andExpect(jsonPath("$.counties", hasSize(2)))
	// .andExpect(jsonPath("$.counties[0].description",
	// containsString("county1desc")))
	// .andDo(print());
	//
	// // Bad Data & good test
	// mockMvc.perform(
	// get("/county/api/refresh").param("refreshInt2", 1).param("retList",
	// "true").param("retPaged", "true")
	// .header("Authorization", basicDigestHeaderValue))
	// .andExpect(status().is4xxClientError())
	// .andDo(print());
	//
	// // ensures the method on the mock was called 1 time.
	// Mockito.verify(getMockCountyBAC()).refreshCounties(Matchers.any(RefreshRequest.class));
	// }

}
