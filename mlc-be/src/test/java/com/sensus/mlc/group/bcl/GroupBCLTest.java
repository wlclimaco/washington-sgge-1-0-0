package com.sensus.mlc.group.bcl;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.dac.IGroupDAC;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

@ContextConfiguration(locations = {"classpath:com/sensus/mlc/group/groupbclimpltest.xml"})
public class GroupBCLTest extends AbstractTestBaseBusiness
{
	/** The Constant FIVE. */
	private static final int FIVE = 5;

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
		return this.groupBCL;
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
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(createLight());
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
	 * Test insert smart point to auto group.
	 */
	@Test
	public void testInsertSmartPointToAutoGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		List<Light> lights = new ArrayList<Light>();
		lights.add(createLight());
		group.setLights(lights);
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().insertSmartPointToAutoGroup(groupRequest);
		assertResponse(response);

		// Test Already exists
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "insertSmartPointToAutoGroup");
		response = getGroupBCL().insertSmartPointToAutoGroup(groupRequest);
		assertEquals("Should get Error", response.getStatus(), Status.ExceptionError);

	}

	/**
	 * Test insert smart point to group.
	 */
	@Test
	public void testInsertSmartPointToGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		List<Light> lights = new ArrayList<Light>();
		lights.add(createLight());
		group.setLights(lights);
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().insertSmartPointToGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "insertSmartPointToGroup");
		response = getGroupBCL().insertSmartPointToGroup(groupRequest);
		this.assertMessages(response, ERROR_CODE);
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
		// Insert situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		List<Light> lights = new ArrayList<Light>();
		lights.add(createLight());
		group.setLights(lights);
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().insertSmartPointToGroup(groupRequest);
		assertResponse(response);

		// Success situation
		groupRequest = TestBaseUtil.createGroupRequest();
		group = TestBaseUtil.createGroup();
		lights = new ArrayList<Light>();
		lights.add(createLight());
		lights.add(createLight());
		group.setLights(lights);
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSearchLight(new SearchLight());

		response = getGroupBCL().updateLightIntensityForGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();
		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class, "submitProcess");
		groupRequest = TestBaseUtil.createGroupRequest();
		group = TestBaseUtil.createGroup();
		lights = new ArrayList<Light>();
		lights.add(createLight());
		lights.add(createLight());
		group.setLights(lights);
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSearchLight(new SearchLight());
		response = getGroupBCL().updateLightIntensityForGroup(groupRequest);
		this.assertMessages(response, ERROR_CODE);
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
		List<Light> lights = new ArrayList<Light>();
		lights.add(createLight());
		lights.add(createLight());
		group.setLights(lights);
		groupRequest.setGroup(group);
		groupRequest.setPercentage(TEN);
		groupRequest.setPaginationAllSelected(false);
		groupRequest.setSearchLight(new SearchLight());

		InternalResponse response = getGroupBCL().updateLightProtectedForGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();

		// Error situation
		this.setSituation(getGroupBCL(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class, "updateLightProtected");
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
	}

	/**
	 * Test delete smart point from group.
	 */
	@Test
	public void testDeleteSmartPointFromGroup()
	{
		// Success situation
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		Group group = TestBaseUtil.createGroup();
		groupRequest.setGroup(group);
		InternalResponse response = getGroupBCL().deleteSmartPointFromGroup(groupRequest);
		assertResponse(response);

		resetMocksToGroupArea();

		// Error situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class, "deleteSmartPointFromGroup");
		response = getGroupBCL().deleteSmartPointFromGroup(groupRequest);
		assertMessages(response, ERROR_CODE);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Test
	public void testFetchCountLightsFromGroups()
	{
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		InternalResultsResponse<Integer> response = getGroupBCL().fetchCountLightsFromGroups(groupRequest);
		assertResultResponse(response);
		assertEquals(response.getResultsList().get(0).intValue(), FIVE);
	}

	@Test
	public void testFetchGroupsBySmartPoint()
	{

		LightRequest lightRequest = TestBaseUtil.createLightRequest();
		InternalResultsResponse<Group> response = getGroupBCL().fetchGroupsBySmartPoint(lightRequest);
		assertResultResponse(response);
		assertEquals(1, response.getResultsList().size());
	}

	@Test
	public void testFetchGroupsByIdList()
	{
		GroupRequest groupRequest = TestBaseUtil.createGroupRequest();
		InternalResultsResponse<Group> response = getGroupBCL().fetchGroupsByIdList(groupRequest);
		assertResultResponse(response);
		assertEquals(THREE, response.getResultsList().size());
	}
}
