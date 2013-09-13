package com.sensus.dm.controller.unittest.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sensus.dm.common.device.model.ServiceTypeEnum;

/**
 * The Class AbstractTestBase.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:sensus-dm-wui-unittest-context-test.xml"})
public abstract class AbstractTestBase
{

	/** The Constant REFRESH. */
	public static final String REFRESH = "refresh";

	/** The Constant RESPONSE. */
	public static final String RESPONSE = "response";

	/** The wac. */
	@Autowired
	private WebApplicationContext wac;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/** The service type enum. */
	private ServiceTypeEnum serviceTypeEnum;

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
	 * Gets the service type enum.
	 * 
	 * @return the service type enum
	 */
	public ServiceTypeEnum getServiceTypeEnum()
	{
		return serviceTypeEnum;
	}

	/**
	 * Sets the service type enum.
	 * 
	 * @param serviceTypeEnum the new service type enum
	 */
	public void setServiceTypeEnum(ServiceTypeEnum serviceTypeEnum)
	{
		this.serviceTypeEnum = serviceTypeEnum;
	}

	/**
	 * Setup.
	 * Load Mocks XML configuration to inject into Controllers.
	 * 
	 */
	@Before
	public void setup()
	{
		setMockMvc(MockMvcBuilders.webAppContextSetup(wac).build());
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
						.session(SessionAuthenticationTestUtil.getSessionTest(serviceTypeEnum))
						.content(getData().getBytes()))
				.andExpect(status().isOk());
	}

	/**
	 * Perform test get.
	 * 
	 * @param methodToExecute the method to execute
	 * @return the result actions
	 * @throws Exception the exception
	 */
	protected ResultActions performTestGet(String methodToExecute) throws Exception
	{
		return getMockMvc().perform(
				get(methodToExecute).contentType(MediaType.APPLICATION_JSON).param("localeLanguage", "en_US")
						.session(SessionAuthenticationTestUtil.getSessionTest(serviceTypeEnum))).andExpect(
				status().isOk());
	}

	/**
	 * Perform test get.
	 * 
	 * @param methodToExecute the method to execute
	 * @return the result actions
	 * @throws Exception the exception
	 */
	protected ResultActions performTestGetPage(String methodToExecute) throws Exception
	{
		return getMockMvc().perform(
				get(methodToExecute).session(SessionAuthenticationTestUtil.getSessionTest(serviceTypeEnum))).andExpect(
				status().isOk());
	}

}