package com.sensus.lc.group.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.bcl.IGroupBCL;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.group.model.response.GroupResponse;
import com.sensus.lc.group.model.response.InquiryGroupResponse;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Class GroupBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/group/groupbcfimpltest.xml"})
public class GroupBCFTest extends AbstractTestBaseBusiness
{
	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_GROUP_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.lightvalidator.intensity.required";

	/** The Constant FETCH_GROUPS_BY_ID_LIST. */
	private static final String FETCH_GROUPS_BY_ID_LIST = "fetchGroupsByIdList";

	/** The Constant SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED. */
	private static final String SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED =
			"sensus.mlc.groupvalidator.grouplist.required";

	/** The Constant SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED =
			"sensus.mlc.validator.selection.pagination.id.required";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

	/** The Constant USER_CONTEXT_REQUIRED. */
	private static final String USER_CONTEXT_REQUIRED = "sensus.mlc.uservalidator.user.context.required";

	/** The Constant LIGHT_REQUIRED. */
	private static final String LIGHT_REQUIRED = "sensus.mlc.lightvalidator.light.required";

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	@Resource(name = "groupBCFTarget")
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Sets the group area.
	 */
	public void setGroupsArea()
	{
		setArea(getGroupBCF(), LCAreaEnum.GROUP);
	}

	/**
	 * Removes the group area.
	 */
	@After
	public void removeGroupArea()
	{
		setArea(getGroupBCF(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Reset mocks to group area.
	 */
	@After
	public void resetMocksToGroupArea()
	{
		resetMocksData(getGroupBCF());
		setGroupsArea();
	}

	/**
	 * Test delete group.
	 */
	@Test
	public void testDeleteGroup()
	{
		// Test Fail
		GroupRequest request = new GroupRequest();
		GroupResponse response = getGroupBCF().deleteGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Success
		List<Integer> groupIdsList = new ArrayList<Integer>();
		groupIdsList.add(1);
		request.setPaginationAllSelected(false);
		request.setSelectionPaginationIds(groupIdsList);
		request.setGroup(TestBaseUtil.createGroup());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().deleteGroup(request);
		assertTrue(response.isOperationSuccess());

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		response = getGroupBCF().deleteGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert group.
	 */
	@Test
	public void testInsertGroup()
	{
		// Test Exception
		GroupRequest request = null;
		GroupResponse response = getGroupBCF().insertGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

		// Test Fail
		request = new GroupRequest(new UserContext(1));
		Group group = new Group();
		group.setDescription("description test");
		request.setGroup(group);
		response = getGroupBCF().insertGroup(request);
		response.setGroups(null);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Test Success
		group = TestBaseUtil.createGroup();
		group.setId(null);
		request.setGroup(group);
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().insertGroup(request);
		assertTrue(response.isOperationSuccess());

		// Test BCL Fail
		group.setId(1);
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class, "insertGroup");
		response = getGroupBCF().insertGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update group.
	 */
	@Test
	public void testUpdateGroup()
	{
		// Test Exception
		GroupRequest request = null;
		GroupResponse response = getGroupBCF().updateGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

		// Test Fail
		request = new GroupRequest(new UserContext(1));
		Group group = new Group();
		request.setGroup(group);
		request.setOldName("oldName1");
		response = getGroupBCF().updateGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		response = getGroupBCF().updateGroup(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		request.setOldName("oldName");
		response = getGroupBCF().updateGroup(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Test Success
		request.setGroup(TestBaseUtil.createGroup());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().updateGroup(request);
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Testdelete light from group.
	 */
	@Test
	public void testdeleteLightFromGroup()
	{
		// Test Exception
		GroupRequest request = null;
		GroupResponse response = getGroupBCF().deleteLightFromGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

		// Test Fail
		Group group = new Group();
		group.setName("All Uberaba Lights at night");
		group.setDescription("All Uberaba Lights");
		request = new GroupRequest(new UserContext(1));
		request.setGroup(group);
		response = getGroupBCF().deleteLightFromGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		response = getGroupBCF().deleteLightFromGroup(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		request.setPaginationAllSelected(true);
		response = getGroupBCF().deleteLightFromGroup(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		request.setGroup(group);
		request.setPaginationAllSelected(true);
		response = getGroupBCF().deleteLightFromGroup(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Success
		group.setId(1);
		List<Integer> lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		request.setPaginationAllSelected(false);
		request.setSelectionPaginationIds(lightIdsList);
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().deleteLightFromGroup(request);
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test fetch all groups.
	 */
	@Test
	public void testFetchAllGroups()
	{
		// Test Validator
		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest(new UserContext(1));
		InquiryGroupResponse response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation to user context
		inquiryPaginationRequest = new InquiryPaginationRequest();
		inquiryPaginationRequest.addSortExpressions(new SortExpression());
		response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation to tenant
		inquiryPaginationRequest = TestBaseUtil.createInquiryPaginationRequest();
		inquiryPaginationRequest.getUserContext().setTenant(null);
		response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Validation to pagination
		inquiryPaginationRequest = TestBaseUtil.createInquiryPaginationRequest();
		inquiryPaginationRequest.setPageSize(1);
		response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);

		// Test Success
		inquiryPaginationRequest = TestBaseUtil.createInquiryPaginationRequest();
		response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertTrue(response.isOperationSuccess());
		assertFalse(response.getGroups().isEmpty());

		// Test Success
		inquiryPaginationRequest = TestBaseUtil.createInquiryPaginationRequest();
		inquiryPaginationRequest.setPageSize(TWENTY);
		response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertTrue(response.isOperationSuccess());
		assertFalse(response.getGroups().isEmpty());

		// Test Fail
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		response = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch group by id.
	 */
	@Test
	public void testFetchGroupById()
	{
		// Test Validator
		GroupRequest request = new GroupRequest();
		Group group = new Group();
		request.setGroup(group);
		GroupResponse response = getGroupBCF().fetchGroupById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		group = new Group();
		request.setGroup(group);
		response = getGroupBCF().fetchGroupById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Success
		group = TestBaseUtil.createGroup();
		request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		response = getGroupBCF().fetchGroupById(request);
		assertTrue(response.isOperationSuccess());

		// Test Fail
		group.setId(1);
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		response = getGroupBCF().fetchGroupById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		response = getGroupBCF().fetchGroupById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch group by id list.
	 */
	@Test
	public void testFetchGroupByIdList()
	{
		// Test Validator
		GroupRequest groupRequest = new GroupRequest();
		GroupResponse response = getGroupBCF().fetchGroupsByIdList(groupRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		List<Group> groupList = new ArrayList<>();
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setGroupList(groupList);
		response = getGroupBCF().fetchGroupsByIdList(groupRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED);

		Group group = new Group();
		groupList.add(group);
		groupList.add(group);
		groupList.add(group);
		groupRequest.setGroupList(groupList);
		response = getGroupBCF().fetchGroupsByIdList(groupRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		groupList = new ArrayList<>();
		groupList.add(TestBaseUtil.createGroup());
		groupList.add(TestBaseUtil.createGroup());
		groupList.add(TestBaseUtil.createGroup());
		List<Integer> groupIdList = new ArrayList<>();
		groupIdList.add(1);
		groupIdList.add(2);
		groupIdList.add(THREE);

		// Success situation
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setAllowedGroupIdList(groupIdList);
		groupRequest.setGroupList(groupList);
		response = getGroupBCF().fetchGroupsByIdList(groupRequest);
		assertTrue(response.isOperationSuccess());

		resetMocksToGroupArea();

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				FETCH_GROUPS_BY_ID_LIST);
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setAllowedGroupIdList(groupIdList);
		groupRequest.setGroupList(groupList);
		response = getGroupBCF().fetchGroupsByIdList(groupRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToGroupArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				FETCH_GROUPS_BY_ID_LIST);
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setAllowedGroupIdList(groupIdList);
		groupRequest.setGroupList(groupList);
		response = getGroupBCF().fetchGroupsByIdList(groupRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch group by name.
	 */
	@Test
	public void testFetchGroupByName()
	{
		// Test Validator
		Group group = new Group();
		GroupRequest request = new GroupRequest();
		request.setGroup(group);
		GroupResponse response = getGroupBCF().fetchGroupByName(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		group = new Group();
		request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		response = getGroupBCF().fetchGroupByName(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Test Fail
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		group = TestBaseUtil.createGroup();
		request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		response = getGroupBCF().fetchGroupByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test Success
		resetMocksToGroupArea();
		response = getGroupBCF().fetchGroupByName(request);
		assertTrue(response.isOperationSuccess());
		assertEquals("Fetch group", 1, response.getGroups().size());

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		response = getGroupBCF().fetchGroupByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert light to group.
	 */
	@Test
	public void testinsertLightToGroup()
	{
		// Test Validator - Group ID null
		GroupRequest request = new GroupRequest();
		GroupResponse response = getGroupBCF().insertLightToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		response = getGroupBCF().insertLightToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		request.setPaginationAllSelected(true);
		response = getGroupBCF().insertLightToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		Group group = TestBaseUtil.createGroup();
		group.setId(null);
		request = TestBaseUtil.createGroupRequest();
		request.setPaginationAllSelected(true);
		request.setGroup(group);
		response = getGroupBCF().insertLightToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Error
		List<Integer> lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		group.setId(1);
		request.setPaginationAllSelected(true);
		request.setSelectionPaginationIds(lightIdsList);
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		response = getGroupBCF().insertLightToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test Success
		resetMocksToGroupArea();
		request.setGroup(group);
		response = getGroupBCF().insertLightToGroup(request);
		assertTrue(response.isOperationSuccess());

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		request.setGroup(group);
		response = getGroupBCF().insertLightToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch groups by light.
	 */
	@Test
	public void testFetchGroupsByLight()
	{
		// Test user context
		LightRequest lightRequest = setLightRequest();
		GroupResponse groupResponse = getGroupBCF().fetchGroupsByLight(lightRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		lightRequest = new LightRequest();
		lightRequest.setUserContext(new UserContext());
		groupResponse = getGroupBCF().fetchGroupsByLight(lightRequest);
		assertMessages(groupResponse, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test light required
		lightRequest = setLightRequest();
		lightRequest.setUserContext(TestBaseUtil.createUserContext());
		lightRequest.getLightCriteria().setLightIdList(null);
		groupResponse = getGroupBCF().fetchGroupsByLight(lightRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, LIGHT_REQUIRED);

		// Test Success
		lightRequest = setLightRequest();
		lightRequest.setUserContext(TestBaseUtil.createUserContext());
		groupResponse = getGroupBCF().fetchGroupsByLight(lightRequest);
		assertTrue(groupResponse.isOperationSuccess());

		// Test Fail.
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		groupResponse = getGroupBCF().fetchGroupsByLight(lightRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, ERROR_CODE);

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		groupResponse = getGroupBCF().fetchGroupsByLight(lightRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test update light intensity for group.
	 */
	@Test
	public void testUpdateLightIntensityForGroup()
	{
		// Test Validator
		GroupRequest groupRequest = new GroupRequest();
		GroupResponse groupResponse = getGroupBCF().updateLightIntensityForGroup(groupRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		Group group = TestBaseUtil.createGroup();
		group.setId(null);
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupResponse = getGroupBCF().updateLightIntensityForGroup(groupRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		group = TestBaseUtil.createGroup();
		group.setId(null);
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(true);
		groupResponse = getGroupBCF().updateLightIntensityForGroup(groupRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		group = TestBaseUtil.createGroup();
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setGroup(group);
		groupRequest.setPercentage(null);
		groupRequest.setPaginationAllSelected(true);
		groupResponse = getGroupBCF().updateLightIntensityForGroup(groupRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Test Fail
		List<Integer> groupIdsList = new ArrayList<Integer>();
		groupIdsList.add(1);
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSelectionPaginationIds(groupIdsList);
		groupResponse = getGroupBCF().updateLightIntensityForGroup(groupRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, ERROR_CODE);

		// Test Success
		resetMocksToGroupArea();
		groupResponse = getGroupBCF().updateLightIntensityForGroup(groupRequest);
		assertTrue(groupResponse.isOperationSuccess());

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		groupResponse = getGroupBCF().updateLightIntensityForGroup(groupRequest);
		assertFalse(groupResponse.isOperationSuccess());
		assertMessages(groupResponse, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test update light protected for group.
	 */
	@Test
	public void testUpdateLightProtectedForGroup()
	{
		// Test Validator
		GroupRequest request = new GroupRequest();
		GroupResponse response = getGroupBCF().updateLightProtectedForGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		Group group = TestBaseUtil.createGroup();
		request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		response = getGroupBCF().updateLightProtectedForGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		group = TestBaseUtil.createGroup();
		group.setId(null);
		request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		request.setLightProtected(true);
		request.setPaginationAllSelected(true);
		response = getGroupBCF().updateLightProtectedForGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Fail
		group = TestBaseUtil.createGroup();
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		List<Integer> groupIdsList = new ArrayList<Integer>();
		groupIdsList.add(1);
		request.setGroup(group);
		request.setPaginationAllSelected(false);
		request.setSelectionPaginationIds(groupIdsList);
		request.setLightProtected(true);
		response = getGroupBCF().updateLightProtectedForGroup(request);
		assertMessages(response, ERROR_CODE);
		assertFalse(response.isOperationSuccess());

		// Test Success
		resetMocksToGroupArea();
		response = getGroupBCF().updateLightProtectedForGroup(request);
		assertTrue(response.isOperationSuccess());

		// Test Exception
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		response = getGroupBCF().updateLightProtectedForGroup(request);
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		assertFalse(response.isOperationSuccess());
	}

	/**
	 * Test fetch count lights from groups.
	 */
	@Test
	public void testFetchCountLightsFromGroups()
	{
		// Test Validator
		GroupRequest request = new GroupRequest();
		GroupResponse response = getGroupBCF().fetchCountLightsFromGroups(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		request = TestBaseUtil.createGroupRequest();
		response = getGroupBCF().fetchCountLightsFromGroups(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED);

		// Test Success
		request.setGroupList(TestBaseUtil.createGroups(1));
		response = getGroupBCF().fetchCountLightsFromGroups(request);
		assertTrue(response.isOperationSuccess());

		// Test Fail
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class);
		response = getGroupBCF().fetchCountLightsFromGroups(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test Fail
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class);
		response = getGroupBCF().fetchCountLightsFromGroups(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Sets the light request.
	 * 
	 * @return the light request
	 */
	private LightRequest setLightRequest()
	{
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setLightIdList(Arrays.asList(TEN));

		LightRequest lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		return lightRequest;
	}
}
