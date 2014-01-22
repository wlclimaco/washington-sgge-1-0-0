package com.sensus.lc.group.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.dac.IGroupDAC;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

/**
 * The Class GroupBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/group/groupbclimpltest.xml"})
public class GroupBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant PROCESS_RUNNING. */
	private static final String PROCESS_RUNNING = "sensus.mlc.groupbcfimpl.processrunning";

	/** The Constant GROUP_REMOVED. */
	private static final String GROUP_REMOVED = "sensus.mlc.groupbcfimpl.mlcgroupdeleted";

	/** The Constant ALL_GROUPS_REMOVED. */
	private static final String ALL_GROUPS_REMOVED = "sensus.mlc.groupbcfimpl.groups.deleted";

	/** The Constant AUTO_GROUP_REMOVED. */
	private static final String AUTO_GROUP_REMOVED = "sensus.mlc.tagvalidator.autogroup.removed";

	/** The Constant NO_LIGHTS_IN_GROUPS. */
	private static final String NO_LIGHTS_IN_GROUPS = "sensus.mlc.mlc_action.light_status.no.lights_in_groups";

	/** The Constant FETCH_ALL_BY_REQUEST. */
	private static final String FETCH_ALL_BY_REQUEST = "fetchAllByRequest";

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The group bcl. */
	private IGroupBCL groupBCL;

	/**
	 * Gets the group bcl.
	 * 
	 * @return the group bcl
	 */
	public IGroupBCL getGroupBCL()
	{
		return groupBCL;
	}

	/**
	 * Sets the group bcl.
	 * 
	 * @param groupBCL the new group bcl
	 */
	@Resource(name = "groupBCLTarget")
	public void setGroupBCL(IGroupBCL groupBCL)
	{
		this.groupBCL = groupBCL;
	}

	/**
	 * Sets the group area.
	 */
	public void setGroupArea()
	{
		setArea(getGroupBCL(), LCAreaEnum.GROUP);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setGroupArea();
	}

	/**
	 * Reset mocks to tag area.
	 */
	@After
	public void resetMocksToGroupArea()
	{
		resetMocksData(getGroupBCL());
		setGroupArea();
	}

	/**
	 * Assert groups not null and not empty.
	 * 
	 * @param groups the groups
	 */
	private void assertGroupsNotNullNotEmpty(List<Group> groups)
	{
		assertNotNull("Group should not be null.", groups);
		assertTrue("Group should not be empty.", groups.size() > 0);
	}

	/**
	 * Test fetch all groups.
	 */
	@Test
	public void testFetchAllGroups()
	{
		// Success situation
		InquiryPaginationRequest inquiryPaginationRequest = TestBaseUtil.createInquiryPaginationRequest();
		InternalResultsResponse<Group> response = getGroupBCL().fetchAllGroups(inquiryPaginationRequest);
		assertResultResponse(response);
		assertGroupsNotNullNotEmpty(response.getResultsList());

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class);
		response = getGroupBCL().fetchAllGroups(inquiryPaginationRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch group by id.
	 */
	@Test
	public void testFetchGroupById()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResultsResponse<Group> response = getGroupBCL().fetchGroupById(groupRequest);
		assertResultResponse(response);
		assertGroupsNotNullNotEmpty(response.getResultsList());

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class);
		response = getGroupBCL().fetchGroupById(groupRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch group by name.
	 */
	@Test
	public void testFetchGroupByName()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResultsResponse<Group> response = getGroupBCL().fetchGroupByName(groupRequest);
		assertResultResponse(response);
		assertGroupsNotNullNotEmpty(response.getResultsList());

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class);
		response = getGroupBCL().fetchGroupByName(groupRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch groups by light.
	 */
	@Test
	public void testFetchGroupsByLight()
	{
		// Success situation
		LightRequest lightRequest = setLightRequest();
		InternalResultsResponse<Group> response = getGroupBCL().fetchGroupsByLight(lightRequest);
		assertResultResponse(response);
		assertGroupsNotNullNotEmpty(response.getResultsList());

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class);
		response = getGroupBCL().fetchGroupsByLight(lightRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert group.
	 */
	@Test
	public void testInsertGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResultsResponse<Group> response = getGroupBCL().insertGroup(groupRequest);
		assertResultResponse(response);
		assertGroupsNotNullNotEmpty(response.getResultsList());

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "insertGroup");
		response = getGroupBCL().insertGroup(groupRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert light to group.
	 */
	@Test
	public void testInsertLightToGroup()
	{
		// Success situations
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setLightRequest(TestBaseUtil.createLightRequest());
		groupRequest.getLightRequest().setLightCriteria(new LightCriteria());
		groupRequest.getLightRequest().getLightCriteria()
				.setLifeCycleStateList(Arrays.asList(LifeCycleStateEnum.ACTIVE));

		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().insertLightToGroup(groupRequest);
		assertResponse(response);

		// Situation with light deactivated
		setTestControl(getGroupBCL(), ILightBCL.class, "LIGHT_DEACTIVATED");
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertResponse(response);

		// Situation with max groups per light
		setTestControl(getGroupBCL(), ILightBCL.class, "");
		setTestControl(getGroupBCL(), IGroupDAC.class, "MAX_GROUPS_PER_LIGHT");
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertResponse(response);

		// Situation that the light already belongs to the group
		setTestControl(getGroupBCL(), ILightBCL.class, "");
		setTestControl(getGroupBCL(), IGroupDAC.class, "LIGHT_BELONG_GROUP");
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertResponse(response);

		// Validation situation
		setTestControl(getGroupBCL(), ILightBCL.class, "");
		setTestControl(getGroupBCL(), IGroupDAC.class, "");

		resetMocksToGroupArea();
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setGroup(group);
		setSituation(getGroupBCL(), SituationsEnum.ERROR, ILightBCL.class, FETCH_ALL_BY_REQUEST);
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertMessages(response, ERROR_CODE);

		resetMocksToGroupArea();
		groupRequest = TestBaseUtil.createGroupRequest();
		groupRequest.setGroup(group);
		setSituation(getGroupBCL(), SituationsEnum.VALIDATION, ILightBCL.class, FETCH_ALL_BY_REQUEST);
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertResponse(response);

		// Validation situation
		resetMocksToGroupArea();
		setSituation(getGroupBCL(), SituationsEnum.VALIDATION, IGroupDAC.class, "fetchLightsWithGroupsMaxAllowed");
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertResponse(response);

		// Validation situation
		resetMocksToGroupArea();
		setSituation(getGroupBCL(), SituationsEnum.VALIDATION, IGroupDAC.class, "fetchLightsBelongGroup");
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertResponse(response);

		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "insertLightToGroup");
		response = getGroupBCL().insertLightToGroup(groupRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update group.
	 */
	@Test
	public void testUpdateGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().updateGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "updateGroup");
		response = getGroupBCL().updateGroup(groupRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update light intensity for group.
	 */
	@Test
	public void testUpdateLightIntensityForGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		Light light = new Light();
		light.setId(TEN);
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSearchLight(new SearchLight());

		InternalResponse response = getGroupBCL().updateLightIntensityForGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();
		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class, "submitProcess");
		groupRequest = TestBaseUtil.createGroupRequest();
		group = TestBaseUtil.createGroup();
		light = new Light();
		light.setId(TEN);
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSearchLight(new SearchLight());
		response = getGroupBCL().updateLightIntensityForGroup(groupRequest);
		assertMessages(response, ERROR_CODE);

		resetMocksToGroupArea();
		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, ILightBCL.class, "countAllByRequest");
		groupRequest = TestBaseUtil.createGroupRequest();
		group = TestBaseUtil.createGroup();
		light = new Light();
		light.setId(TEN);
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSearchLight(new SearchLight());
		response = getGroupBCL().updateLightIntensityForGroup(groupRequest);
		assertMessages(response, NO_LIGHTS_IN_GROUPS);
	}

	/**
	 * Test update light protected for group.
	 */
	@Test
	public void testUpdateLightProtectedForGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		Light light = new Light();
		light.setId(TEN);
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSearchLight(new SearchLight());

		InternalResponse response = getGroupBCL().updateLightProtectedForGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, ILightBCL.class, "updateLightBase");
		response = getGroupBCL().updateLightProtectedForGroup(groupRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete group.
	 */
	@Test
	public void testDeleteGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().deleteGroup(groupRequest);
		assertResponse(response);

		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "fetchCanDelete");
		response = getGroupBCL().deleteGroup(groupRequest);
		assertMessages(response, PROCESS_RUNNING, GROUP_REMOVED);

		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "deleteGroup");
		response = getGroupBCL().deleteGroup(groupRequest);
		assertMessages(response, ERROR_CODE, AUTO_GROUP_REMOVED);

		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "fetchAllGroups");
		response = getGroupBCL().deleteGroup(groupRequest);
		assertMessages(response, ALL_GROUPS_REMOVED);

	}

	/**
	 * Test delete light from group.
	 */
	@Test
	public void testDeleteLightFromGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();

		groupRequest.setLightRequest(TestBaseUtil.createLightRequest());
		groupRequest.getLightRequest().setLightCriteria(new LightCriteria());
		groupRequest.getLightRequest().getLightCriteria()
				.setLifeCycleStateList(Arrays.asList(LifeCycleStateEnum.ACTIVE));

		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().deleteLightFromGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();

		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "deleteLightFromGroup");
		response = getGroupBCL().deleteLightFromGroup(groupRequest);
		assertMessages(response, ERROR_CODE);

		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, ILightBCL.class, "fetchAllByRequest");
		response = getGroupBCL().deleteLightFromGroup(groupRequest);
		assertMessages(response, ERROR_CODE);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.group.bcl.IGroupBCL#fetchCountLightsFromGroups(com.sensus.lc.group.model.request.GroupRequest)
	 */
	/**
	 * Test fetch count lights from groups.
	 */
	@Test
	public void testFetchCountLightsFromGroups()
	{
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		InternalResultsResponse<Group> response = getGroupBCL().fetchCountLightsFromGroups(groupRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch groups by id list.
	 */
	@Test
	public void testFetchGroupsByIdList()
	{
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		InternalResultsResponse<Group> response = getGroupBCL().fetchGroupsByIdList(groupRequest);
		assertResultResponse(response);
		assertEquals(THREE, response.getResultsList().size());
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
