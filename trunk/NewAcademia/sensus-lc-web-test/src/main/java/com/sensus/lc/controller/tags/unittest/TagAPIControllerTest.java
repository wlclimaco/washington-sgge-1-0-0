package com.sensus.lc.controller.tags.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.groups.unittest.GroupBCFMockImpl;
import com.sensus.lc.controller.tags.TagAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class TagAPIControllerTest.
 */
public class TagAPIControllerTest extends AbstractTestBase
{

	/** The fetch. */
	private final String FETCH = "/api/tag/fetch";

	/** The insert. */
	private final String INSERT = "/api/tag/insert";

	/** The delete. */
	private final String DELETE = "/api/tag/delete";

	/** The Constant MAP_VERIFY_GROUP. */
	private static final String VERIFY_GROUP = "/api/tag/verifyautogroup";

	/** The Constant MAP_UPDATE_AUTO_GROUP. */
	private static final String UPDATE_AUTO_GROUP = "/api/tag/updateautogroup";

	/** The Constant MAP_LIGHT_TO_TAG. */
	private static final String MAP_LIGHT_TO_TAG = "/api/tag/insertlights";

	/** The Constant MAP_REMOVE_LIGHT_TO_TAG. */
	private static final String MAP_REMOVE_LIGHT_TO_TAG = "/api/tag/deletelights";

	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(TagAPIController.class).getTagBCF();
	}

	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(TagAPIController.class).getGroupBCF();
	}

	/**
	 * Fetch all.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// set request object
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}]}");

		// Success situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.tags", hasSize(50)));

		// Failure situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getTagMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.tags", nullValue()));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void addTag() throws Exception
	{
		// set request object
		setData("{\"tag\":{\"id\":48,\"name\":null},\"tags\":null,\"paginationAllSelected\":null,\"selectionPaginationIds\":null,\"searchLight\":null}");

		// Success situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT).andExpect(jsonPath("$.tags", hasSize(50)));

		// Failure situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Update.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void updateAutoGroup() throws Exception
	{
		// set request object
		setData("{\"id\":170,\"autoGroup\":\"true\",\"includeSmartpointsToGroup\":\"false\"}");

		// Success situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE_AUTO_GROUP).andExpect(jsonPath("$.tags", hasSize(50)));

		// Failure situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE_AUTO_GROUP).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE_AUTO_GROUP).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void deleteTag() throws Exception
	{
		// set request object
		setData("{\"tag\":{\"id\":48,\"name\":null},\"tags\":null,\"paginationAllSelected\":null,\"selectionPaginationIds\":null,\"searchLight\":null}");

		// Success situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE).andExpect(jsonPath("$.tags", hasSize(50)));

		// Failure situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Verify group with tag name.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void verifyGroupWithTagName() throws Exception
	{
		// set request object
		setData("{\"group\":{\"name\":\"abc\"},\"tagName\":\"27560\",\"selectionPaginationIds\":[]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(VERIFY_GROUP).andExpect(
				jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(VERIFY_GROUP).andExpect(
				jsonPath("$.operationSuccess", equalTo(false)));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(VERIFY_GROUP).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void insertSmartpointToTag() throws Exception
	{
		// set request object
		setData("{\"tag\":{\"id\":null,\"name\":null},\"tags\":[{\"id\":126,\"lights\":[{\"id\":1046}],\"name\":\"27560\"}],\"paginationAllSelected\":false,\"selectionPaginationIds\":[1046],\"searchLight\":{\"searchParameters\":[]}}");

		// Success situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_LIGHT_TO_TAG).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_LIGHT_TO_TAG).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_LIGHT_TO_TAG).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void removeSmartpointFromTag() throws Exception
	{
		// set request object
		setData("{\"selectionPaginationIds\":[1046],\"tags\":[{\"id\":126,\"name\":\"27560\"}],\"paginationAllSelected\":false}");

		// Success situation
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_REMOVE_LIGHT_TO_TAG).andExpect(jsonPath("$.tags", hasSize(50)));

		// Failure situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_REMOVE_LIGHT_TO_TAG).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_REMOVE_LIGHT_TO_TAG).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

}
