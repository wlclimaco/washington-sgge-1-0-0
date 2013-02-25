package com.sensus.mlc.wui.dashboard.unittest;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
@ContextConfiguration(locations = {"classpath:sensus-slc-wui-unittest-context-test.xml"})
public class DashBoardAPIControllerTest extends AbstractJUnit4SpringContextTests {

	/** The data. */
	private String data;

	/** The mock mvc. */
	private MockMvc mockMvc;

	@Before
	public void setup()
	{
		mockMvc =
				MockMvcBuilders.xmlConfigSetup("classpath:sensus-slc-wui-unittest-context-test.xml").build();

	}

	@Test
	public void fetch() throws Exception
	{
		// Set the JsonObject of the Request
		setData("{\"lowerDate\":\"Fri, 01 Jun 2012 03:00:00 GMT\",\"upperDate\":\"Mon, 04 Jun 2012 03:00:00 GMT\",\"series\":[16,30,31,17],\"searchCriteriaList\":[{\"filterType\":0,\"dataType\":1,\"value\":3489821}],\"contextEnum\":0}");
		mockMvc.perform(
				post("/api/dashboard/fetch").contentType(MediaType.APPLICATION_JSON).body(
						getData().getBytes()));

	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
