package com.sensus.mlc.wui.util;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.ResultActions;
import org.springframework.test.web.server.setup.MockMvcBuilders;

/**
 * The Class AbstractTestBase.
 */
@ContextConfiguration(locations = {"classpath:sensus-Dicionario-wui-unittest-context-test.xml"})
public abstract class AbstractTestBase
{

	/** The mock mvc. */
	private MockMvc mockMvc;

	/** The data. */
	private String data;

	/**
	 * Constructs a new Base Mock Object.
	 */
	public AbstractTestBase()
	{
		super();
	}

	/**
	 * Gets the mock mvc.
	 * 
	 * @return the mock mvc
	 */
	public MockMvc getMockMvc()
	{
		return mockMvc;
	}

	/**
	 * Sets the mock mvc.
	 * 
	 * @param mockMvc the new mock mvc
	 */
	public void setMockMvc(MockMvc mockMvc)
	{
		this.mockMvc = mockMvc;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the new data
	 */
	public void setData(String data)
	{
		this.data = data;
	}

	/**
	 * Setup.
	 * Load Mocks XML configuration to inject into Controllers.
	 * 
	 */
	@Before
	public void setup()
	{
		setMockMvc(MockMvcBuilders.xmlConfigSetup("classpath:sensus-Dicionario-wui-unittest-context-test.xml").build());
	}

	/**
	 * Perform test.
	 * 
	 * @param methodToExecute the method to execute
	 * @return the result actions
	 * @throws Exception the exception
	 */
	protected ResultActions performTest(String methodToExecute) throws Exception
	{
		return getMockMvc().perform(
				post(methodToExecute).contentType(MediaType.APPLICATION_JSON)
						.session(SessionAuthenticationTestUtil.getSessionTest()).body(getData().getBytes())).andExpect(
				status().isOk());
	}

	protected ResultActions performTestGet(String methodToExecute) throws Exception
	{
		return getMockMvc().perform(
				get(methodToExecute).contentType(MediaType.APPLICATION_JSON)
						.session(SessionAuthenticationTestUtil.getSessionTest()).body(getData().getBytes())).andExpect(
				status().isOk());
	}

}