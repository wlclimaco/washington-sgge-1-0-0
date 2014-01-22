package com.sensus.lc.group.dac;

import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;
import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createGroupRequest;
import static com.sensus.lc.base.TestBaseUtil.createInquiryPaginationRequest;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sensus.common.model.Authority;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Class GroupDACTest.
 */
public class GroupDACTest extends AbstractTestBaseDAC
{
	/** The Constant NINE_HUNDRED_NINETY_NINE. */
	private static final Integer NINE_HUNDRED_NINETY_NINE = 999;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS =
			"sensus.mlc.groupvalidator.group.already.exists";

	/**
	 * Reset data.
	 */
	@After
	public void resetData()
	{
		resetGroupsToUser(createUserContext());
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setCacheStatementScope(getGroupDAC());
	}

	/**
	 * Test insert group.
	 */
	@Test
	public void testInsertGroup()
	{
		Group group = insertGroup();
		assertGroup(group);
		GroupRequest request = TestBaseUtil.createGroupRequest();
		request.setGroup(group);
		InternalResultsResponse<Group> response = getGroupDAC().insertGroup(request);
		this.assertMessages(response, SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS);

	}

	/**
	 * Test update light to group.
	 */
	@Test
	public void testUpdateLightToGroup()
	{
		Group group = insertGroup();
		GroupRequest groupRequest = createGroupRequest();
		groupRequest.setGroup(group);
		InternalResponse response = getGroupDAC().updateLightToGroup(groupRequest);
		assertResponse(response);
	}

	/**
	 * Test update group.
	 */
	@Test
	public void testUpdateGroup()
	{
		final String nameUpd = "TestGroupUpd " + RANDOM.nextInt(NUMBER_RANGE);
		final String descriptionUpd = "DescriptionGroupUpd" + RANDOM.nextInt(NUMBER_RANGE);

		Group group = insertGroup();
		assertGroup(group);
		group.setName(nameUpd);
		group.setDescription(descriptionUpd);

		// Update Name and description
		GroupRequest request = createGroupRequest();
		request.setGroup(group);
		request.setOldName("newName" + RANDOM.nextInt(NUMBER_RANGE));
		InternalResponse response = getGroupDAC().updateGroup(request);
		assertResponse(response);

		// Read it back
		request = createGroupRequest();
		request.setGroup(group);
		InternalResultsResponse<Group> newResponse = getGroupDAC().fetchGroupById(request);

		assertResultResponse(newResponse);
		assertEquals(nameUpd, newResponse.getFirstResult().getName());
		assertEquals(descriptionUpd, newResponse.getFirstResult().getDescription());

		// Test duplicate key
		Group group1 = insertGroup();
		request.setOldName(group1.getName());
		group1.setName(group.getName());
		request.setGroup(group1);
		response = getGroupDAC().updateGroup(request);

	}

	/**
	 * Test delete group.
	 */
	@Test
	public void testDeleteGroup()
	{
		GroupRequest request = createGroupRequest();
		Group group = insertGroup();
		assertGroup(group);
		request.setGroup(group);

		InternalResponse response = getGroupDAC().deleteGroup(request);
		assertResponse(response);

		request = new GroupRequest();
		request.setGroup(new Group(NINE_HUNDRED_NINETY_NINE));
		response = getGroupDAC().deleteGroup(request);
		assertResponse(response);
	}

	/**
	 * Test fetch all groups.
	 */
	@Test
	public void testFetchAllGroups()
	{
		InquiryPaginationRequest inquiryPaginationRequest = createInquiryPaginationRequest();

		// Add allowed group list for user
		setGroupsToUser(inquiryPaginationRequest, 2);

		InternalResultsResponse<Group> response = getGroupDAC().fetchAllGroups(inquiryPaginationRequest);
		assertResultResponse(response);

		List<Group> groups = response.getResultsList();

		assertTrue("The user should have access to only two groups previously added to it", groups.size() >= 2);

		Group group = insertGroup();
		response = getGroupDAC().fetchAllGroups(inquiryPaginationRequest);
		assertResultResponse(response);

		assertFalse("The user should not have access to the group created, since it was added to view it",
				containsInGroupList(groups, group));

		List<Integer> idList = new ArrayList<Integer>();
		idList.add(group.getId());
		inquiryPaginationRequest.setSelectionPaginationIds(idList);
		Authority authority = new Authority(group.getId(), group.getName());
		inquiryPaginationRequest.getUserContext().getAuthorities().add(authority);
		inquiryPaginationRequest.setPaginationAllSelected(false);

		response = getGroupDAC().fetchAllGroups(inquiryPaginationRequest);
		assertResultResponse(response);
		groups = response.getResultsList();

		assertTrue("The user should see only the group that requested", groups.size() == 1);

		Group newGroup = insertGroup();

		inquiryPaginationRequest = createInquiryPaginationRequest();
		inquiryPaginationRequest.getUserContext().setAuthorities(null);
		inquiryPaginationRequest.setPaginationAllSelected(true);

		inquiryPaginationRequest.setPageSize(0);
		response = getGroupDAC().fetchAllGroups(inquiryPaginationRequest);
		assertResultResponse(response);
		groups = response.getResultsList();

		assertTrue("The user should have access to all groups", groups.size() > THREE);
		assertTrue("The user should have access to new group", containsInGroupList(groups, newGroup));
	}

	/**
	 * Test fetch group by id.
	 */
	@Test
	public void testFetchGroupById()
	{
		GroupRequest request = createGroupRequest();
		setGroupToUser(request);

		InternalResultsResponse<Group> response = getGroupDAC().fetchGroupById(request);

		assertResultResponse(response);
		assertEquals("The user should have access to the group", 1, response
				.getResultsList().size());

		Group group = response.getFirstResult();
		assertGroup(group);

		group = insertGroup();
		request.setGroup(group);
		response = getGroupDAC().fetchGroupById(request);
		assertResponse(response);

		group = response.getFirstResult();
		assertNull("The user should not have access to the group was not added to view it.", group);
	}

	/**
	 * Test fetch group by id list.
	 */
	@Test
	public void testFetchGroupByIdList()
	{
		GroupRequest request = createGroupRequest();
		request.setGroupList(Arrays.asList(insertGroup(), insertGroup()));
		setGroupToUser(request);

		InternalResultsResponse<Group> response = getGroupDAC().fetchGroupsByIdList(request);
		assertResponse(response);
		assertFalse("The user should not have access to the group was not added to view it. ", response.hasResults());

		int size = request.getGroupList().size();
		this.setGroupsToUser(request.getUserContext(), request.getGroupList().toArray(new Group[size]));
		response = getGroupDAC().fetchGroupsByIdList(request);
		assertResultResponse(response);
		for (Group group : request.getGroupList())
		{
			if (!containsInGroupList(response.getResultsList(), group))
			{
				fail("The user should not have access to group: " + group.getId());
			}
		}
	}

	/**
	 * Test fetch group by name.
	 */
	@Test
	public void testFetchGroupByName()
	{
		GroupRequest request = createGroupRequest();
		setGroupToUser(request);
		InternalResultsResponse<Group> response = getGroupDAC().fetchGroupByName(request);
		assertResultResponse(response);

		assertEquals("The user should have access to the group added to view it", 1, response.getResultsList().size());
		Group group = response.getFirstResult();
		assertGroup(group);

		group = insertGroup();
		request.setGroup(group);
		response = getGroupDAC().fetchGroupByName(request);
		assertResponse(response);

		group = response.getFirstResult();
		assertNull("The user should not have access to the group was not added to view it", group);
	}

	/**
	 * Test fetch groups by light.
	 */
	@Test
	public void testFetchGroupsByLight()
	{
		Light light = insertLight();
		Group group1 = createGroup();
		Group group2 = createGroup();

		addLightToGroup(light, group1);
		addLightToGroup(light, group2);

		// Search light
		LightRequest lightRequest = setLightRequest(light);
		InternalResultsResponse<Group> responseGroupsByLight = getGroupDAC().fetchGroupsByLight(lightRequest);
		assertResultResponse(responseGroupsByLight);

		List<Group> groups = responseGroupsByLight.getResultsList();
		assertTrue("The group1 should to be at group list from light", containsInGroupList(groups, group1));
		assertTrue("The group2 should to be at group list from light", containsInGroupList(groups, group2));
	}

	/**
	 * Test delete light from group.
	 */
	@Test
	public void testDeleteLightFromGroup()
	{
		setCacheStatementScope(getGroupDAC());
		Light light = insertLight();

		Group group = insertGroup();
		addLightToGroup(light, group);
		GroupRequest request = createGroupRequest();
		setGroupsToUser(request.getUserContext(), group);
		request.setGroup(group);

		// Check group to light
		LightRequest lightRequest = TestBaseUtil.createLightRequest();
		lightRequest.setLightCriteria(new LightCriteria());
		lightRequest.getLightCriteria().setLightIdList(Arrays.asList(light.getId()));
		InternalResultsResponse<Group> resultResponse = getGroupDAC().fetchGroupsByLight(lightRequest);
		assertResultResponse(resultResponse);
		assertTrue("Group should be belong to light", group.getId().equals(resultResponse.getFirstResult().getId()));

		// Remove light from group
		request.setSelectionPaginationIds(Arrays.asList(light.getId()));
		InternalResponse response = getGroupDAC().deleteLightFromGroup(request);
		assertResponse(response);

		// Check remotion
		resultResponse = getGroupDAC().fetchGroupsByLight(lightRequest);
		assertResponse(resultResponse);
		assertFalse("Light should not be belong group list", resultResponse.hasResults());
	}

	/**
	 * Test insert light to group.
	 */
	@Test
	public void testInsertLightToGroup()
	{
		addLightToGroup(insertLight(), insertGroup());
	}

	/**
	 * Test can delete.
	 */
	@Test
	public void testCanDelete()
	{
		GroupRequest groupRequest = createGroupRequest();
		Group group = insertGroup();
		groupRequest.addAllowedGroupId(group.getId());
		groupRequest.setGroup(group);
		Boolean response = getGroupDAC().fetchCanDelete(groupRequest);
		assertTrue(response);
	}

	/**
	 * Test fetch lights belong groups max allowed.
	 */
	@Test
	public void testFetchLightsBelongGroupsMaxAllowed()
	{
		Light light = insertLight();

		GroupRequest groupRequest = createGroupRequest();
		groupRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		groupRequest.setMaxLightCount(FIVE);

		for (int i = 0; i < FIVE; i++)
		{
			addLightToGroup(light, insertGroup());
		}

		InternalResultsResponse<Light> response = getGroupDAC().fetchLightsWithGroupsMaxAllowed(groupRequest);
		assertResultResponse(response);

		Light lightSearched = response.getFirstResult();
		assertEquals("Light searched should be equals light inserted", light.getId(), lightSearched.getId());

		// Restriction test
		setGroupToUser(groupRequest);

		response = getGroupDAC().fetchLightsWithGroupsMaxAllowed(groupRequest);
		assertResponse(response);
		assertFalse("Light list should be empty", response.hasResults());
	}

	/**
	 * Test fetch lights belong group.
	 */
	@Test
	public void testFetchLightsBelongGroup()
	{
		Integer lightsAmount = 2;
		List<Light> lights = insertLights(lightsAmount);
		Group group = insertGroup();
		addLightsToGroup(lights, group);

		List<Integer> lightIdList = LCPropertiesExtractorUtil.extractLightId(lights);

		GroupRequest groupRequest = createGroupRequest();
		groupRequest.setSelectionPaginationIds(lightIdList);
		groupRequest.setGroup(group);
		groupRequest.addAllowedGroupId(group.getId());

		InternalResultsResponse<Light> response = getGroupDAC().fetchLightsBelongGroup(groupRequest);
		assertResultResponse(response);

		assertEquals("Light list result should be equals light list belong of group ", lightsAmount.intValue(),
				response.getResultsList().size());

		lights.add(insertLight());
		lightIdList = LCPropertiesExtractorUtil.extractLightId(lights);
		groupRequest.setSelectionPaginationIds(lightIdList);
		response = getGroupDAC().fetchLightsBelongGroup(groupRequest);
		assertResultResponse(response);

		assertEquals("Light list result should be equals light list belong of group", lightsAmount.intValue(), response
				.getResultsList().size());

		setGroupToUser(groupRequest);
		response = getGroupDAC().fetchLightsBelongGroup(groupRequest);
		assertResponse(response);

		assertFalse("Light list result should be empty", response.hasResults());

	}

	/**
	 * Test fetch count lights from groups.
	 */
	@Test
	public void testFetchCountLightsFromGroups()
	{
		List<Light> lights = insertLights(2);
		Group group1 = insertGroup();
		addLightsToGroup(lights, group1);

		Group group2 = insertGroup();
		addLightsToGroup(lights, group2);

		GroupRequest groupRequest = createGroupRequest();
		groupRequest.setGroupList(Arrays.asList(group1, group2));

		InternalResultsResponse<Group> response = getGroupDAC().fetchCountLightsFromGroups(groupRequest);
		assertResultResponse(response);

		// Restriction test
		setGroupToUser(groupRequest);
		response = getGroupDAC().fetchCountLightsFromGroups(groupRequest);
		assertResponse(response);

		this.setGroupsToUser(groupRequest.getUserContext(), group1, group2);
		response = getGroupDAC().fetchCountLightsFromGroups(groupRequest);
		assertResponse(response);
	}

	/**
	 * Contains in group list.
	 * 
	 * @param groups the groups
	 * @param group the group
	 * @return true, if successful
	 */
	private boolean containsInGroupList(List<Group> groups, Group group)
	{
		if (ValidationUtil.isNullOrEmpty(groups))
		{
			return false;
		}

		if (ValidationUtil.isNull(group))
		{
			return false;
		}

		for (Group g : groups)
		{
			if (!ValidationUtil.isNull(g.getId()) && g.getId().equals(group.getId()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Sets the light request.
	 * 
	 * @param light the light
	 * @return the light request
	 */
	private LightRequest setLightRequest(Light light)
	{
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setLightIdList(Arrays.asList(light.getId()));

		LightRequest lightRequest = new LightRequest(TestBaseUtil.createUserContext());
		lightRequest.setLightCriteria(lightCriteria);
		return lightRequest;
	}
}
