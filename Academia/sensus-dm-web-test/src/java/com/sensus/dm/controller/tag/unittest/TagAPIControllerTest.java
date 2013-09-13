package com.sensus.dm.controller.tag.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.tag.TagAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class TagAPIControllerTest.
 */
public class TagAPIControllerTest extends AbstractTestBase
{

	/** The Constant API_TAG_DELETE_DEVICE. */
	private static final String API_TAG_DELETE_DEVICE = "/api/tag/deleteDevice";

	/** The Constant API_TAG_INSERT_DEVICE. */
	private static final String API_TAG_INSERT_DEVICE = "/api/tag/insertDevice";

	/** The Constant API_TAG_DELETE. */
	private static final String API_TAG_DELETE = "/api/tag/delete";

	/** The Constant API_TAG_INSERT. */
	private static final String API_TAG_INSERT = "/api/tag/insert";

	/** The Constant API_TAG_FETCH_ALL. */
	private static final String API_TAG_FETCH_ALL = "/api/tag/fetchAll";

	/** The Constant API_TAG_FETCH. */
	private static final String API_TAG_FETCH = "/api/tag/fetch";

	/** The data. */
	private String data;

	/** The Constant TAG_NAME. */
	public static final String TAG_NAME = "Tag ";

	/**
	 * Gets the tag mock.
	 * 
	 * @return the tag mock
	 */
	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext().getBean(TagAPIController.class)
				.getTagBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// set...
		setData("{\"id\": \"10\"}");

		// Success Situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_TAG_FETCH).andExpect(jsonPath("$.tags", hasSize(10)));

		// Failure Situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_TAG_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getTagMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_TAG_FETCH).andExpect(jsonPath("$.tags", nullValue()));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_TAG_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/**
	 * Fetch All.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		// set...
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_TAG_FETCH_ALL).andExpect(jsonPath("$.tags", hasSize(15)));

		// Failure Situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_TAG_FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getTagMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_TAG_FETCH_ALL).andExpect(jsonPath("$.tags", nullValue()));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_TAG_FETCH_ALL).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/**
	 * Insert.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insert() throws Exception
	{
		// set...
		setData("{\"name\":\"Junit Tag Name\"}");

		// Success Situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_TAG_INSERT)
				.andExpect(jsonPath("$.tags", hasSize(1)))
				.andExpect(jsonPath("$.tags[0].name", comparesEqualTo("Junit Tag Name")));

		// Failure Situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_TAG_INSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getTagMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_TAG_INSERT).andExpect(jsonPath("$.tags", nullValue()));

		// Exception Situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_TAG_INSERT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void delete() throws Exception
	{
		setData("{\"id\":291}");

		// Success Situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_TAG_DELETE).andExpect(jsonPath("$.tags", nullValue()));

		// Failure Situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_TAG_DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getTagMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_TAG_DELETE).andExpect(jsonPath("$.tags", nullValue()));

		// Exception Situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_TAG_DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/**
	 * Insert device.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertDevice() throws Exception
	{
		setData("{\"timeZone\":\"-4\",\"dateFormat\":\"dd/MM/yyyy\",\"sortExpressions\":[{\"field\":\"flexnet_id\","
				+
				"\"direction\":\"Ascending\"}],\"tags\":[{\"id\":\"922\"}],\"devices\":[{\"radio\":{\"flexNetId\":\"1001\"},"
				+
				"\"deviceType\":\"ELECTRIC_METER\"},{\"radio\":{\"flexNetId\":\"1002\"}," +
				"\"deviceType\":\"ELECTRIC_METER\"}]}");

		// Success Situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_TAG_INSERT_DEVICE).andExpect(jsonPath("$.tags", nullValue()));

		// Failure Situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_TAG_INSERT_DEVICE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getTagMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_TAG_INSERT_DEVICE).andExpect(jsonPath("$.tags", nullValue()));

		// Exception Situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_TAG_INSERT_DEVICE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/**
	 * Delete device.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void deleteDevice() throws Exception
	{
		// set...
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\"," +
				"\"direction\":\"Ascending\"}],\"tags\":[{\"name\":\"TAG_NAME\",\"id\":2}],\"devices\":[{}]}");

		// Success Situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_TAG_DELETE_DEVICE).andExpect(jsonPath("$.tags", nullValue()));

		// Failure Situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_TAG_DELETE_DEVICE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getTagMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_TAG_DELETE_DEVICE).andExpect(jsonPath("$.tags", nullValue()));

		// Exception Situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_TAG_DELETE_DEVICE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.epm.wui.unittest.util.AbstractTestBase#getData()
	 */
	@Override
	public String getData()
	{
		return data;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.epm.wui.unittest.util.AbstractTestBase#setData(java.lang.String)
	 */
	@Override
	public void setData(String data)
	{
		this.data = data;
	}

}