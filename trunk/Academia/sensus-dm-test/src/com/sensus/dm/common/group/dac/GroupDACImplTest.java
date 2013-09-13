package com.sensus.dm.common.group.dac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;

/**
 * The Class GroupDAOImplTest.
 * 
 * @author QAT Global.
 */

public class GroupDACImplTest extends AbstractTestBaseDAC
{
	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant LATITUDE_TWELVE. */
	private static final Double LATITUDE_TWELVE = 12.0000;

	/** The Constant LONGITUDE_ELEVEN. */
	private static final Double LONGITUDE_ELEVEN = 11.0000;

	/** The Constant TEST_GROUP_NAME9999. */
	private static final String TEST_GROUP_NAME9999 = "TestGroupName9999";

	/** The Constant TEST_GROUP_DESCRIPTION9999. */
	private static final String TEST_GROUP_DESCRIPTION9999 = "TestGroupDescription9999";

	/** The Constant TEST_GROUP_NAME8888. */
	private static final String TEST_GROUP_NAME8888 = "TestGroupName8888";

	/** The Constant TEST_GROUP_DESCRIPTION8888. */
	private static final String TEST_GROUP_DESCRIPTION8888 = "TestGroupDescription8888";

	/** The Constant GROUP_TEST_FETCH_BY_ID. */
	private static final String GROUP_TEST_FETCH_BY_ID = "Group test FetchGroupById";

	/** The Constant DESC_GROUP_UPD. */
	private static final String DESC_GROUP_UPD = "DescriptionGroupUpd";

	/** The Constant TEST_ADD_GROUP. */
	private static final String TEST_ADD_GROUP = "TestAddGroup";

	/** The Constant DESC_ADD_GROUP. */
	private static final String DESC_ADD_GROUP = "DescriptionAddGroup";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The group dao. */
	private IGroupDAC groupDAC;

	/**
	 * Sets the group dac.
	 * 
	 * @param iGroupDACParam the new group dac
	 */
	@Override
	@Resource
	public void setGroupDAC(IGroupDAC iGroupDACParam)
	{
		groupDAC = iGroupDACParam;
	}

	/**
	 * Gets the group dac.
	 * 
	 * @return the group dac
	 */
	@Override
	public IGroupDAC getGroupDAC()
	{
		return groupDAC;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all groups.
	 */
	@Test
	public void testFetchAllGroups()
	{
		InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);

		ArrayList<GroupTypeEnum> groupTypeslist = new ArrayList<GroupTypeEnum>();
		groupTypeslist.add(GroupTypeEnum.OPERATIONS);
		groupTypeslist.add(GroupTypeEnum.BILLING);

		DeviceSearch deviceSearch = new DeviceSearch();
		deviceSearch.setGroupTypes(groupTypeslist);

		inquiryGroupRequest.setDeviceSearch(deviceSearch);
		inquiryGroupRequest.addSortExpressions(new SortExpression(NAME, Direction.Descending));
		inquiryGroupRequest.setStartRow(ZERO);
		inquiryGroupRequest.setPageSize(TEN);
		inquiryGroupRequest.addGroup(new Group(DeviceTypeEnum.ELECTRIC_METER));

		InternalResultsResponse<Group> response = getGroupDAC().fetchAllGroups(inquiryGroupRequest);

		assertNotNull(response);
		assertNotNull(response.getResultsList());

		inquiryGroupRequest = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		inquiryGroupRequest.addSortExpressions(new SortExpression(NAME, Direction.Descending));
		inquiryGroupRequest.setStartRow(ZERO);
		inquiryGroupRequest.setPageSize(TEN);
		inquiryGroupRequest.addGroup(new Group(1, DeviceTypeEnum.ELECTRIC_METER));

		response = getGroupDAC().fetchAllGroups(inquiryGroupRequest);
		assertNotNull(response);
		assertNotNull(response.getResultsList());

		// using base search contain devices
		inquiryGroupRequest = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		inquiryGroupRequest.addSortExpressions(new SortExpression(NAME, Direction.Descending));
		inquiryGroupRequest.setStartRow(ZERO);
		inquiryGroupRequest.setPageSize(TEN);
		inquiryGroupRequest.addGroup(new Group(DeviceTypeEnum.ELECTRIC_METER));

		inquiryGroupRequest.setDeviceSearch(new DeviceSearch());
		inquiryGroupRequest.getDeviceSearch().setContainDevices(Boolean.TRUE);

		response = getGroupDAC().fetchAllGroups(inquiryGroupRequest);
		assertNotNull(response);
		assertNotNull(response.getResultsList());

		// Success situation - fetch group by id
		inquiryGroupRequest = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		inquiryGroupRequest.setTenant(TestBaseUtil.createTenant());
		inquiryGroupRequest.setUserContext(TestBaseUtil.createUserContext());
		inquiryGroupRequest.addGroup(insertGroup(GROUP_TEST_FETCH_BY_ID, GROUP_TEST_FETCH_BY_ID,
				ServiceTypeEnum.ELECTRIC, DeviceTypeEnum.ELECTRIC_METER, null));
		inquiryGroupRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getGroupDAC().fetchAllGroups(inquiryGroupRequest);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		assertEquals(GROUP_TEST_FETCH_BY_ID, response.getFirstResult().getName());

		// Success situation - fetch group by name
		inquiryGroupRequest = new InquiryGroupRequest();
		inquiryGroupRequest.setUserContext(TestBaseUtil.createUserContext());
		inquiryGroupRequest.setTenant(TestBaseUtil.createTenant());
		inquiryGroupRequest
				.addGroup(insertGroup(TEST_GROUP_NAME9999, TEST_GROUP_DESCRIPTION9999, ServiceTypeEnum.WATER, null,
						null));
		inquiryGroupRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getGroupDAC().fetchAllGroups(inquiryGroupRequest);

		TestBaseUtil.assertResultResponse(response);

		insertGroup(TEST_GROUP_NAME9999, TEST_GROUP_DESCRIPTION9999,
				ServiceTypeEnum.ELECTRIC,
				DeviceTypeEnum.ELECTRIC_METER, null);

		inquiryGroupRequest = new InquiryGroupRequest();
		inquiryGroupRequest.setUserContext(TestBaseUtil.createUserContext());
		inquiryGroupRequest.setTenant(TestBaseUtil.createTenant());
		inquiryGroupRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		inquiryGroupRequest.setDeviceSearch(new DeviceSearch(DeviceTypeEnum.ELECTRIC_METER));
		response = getGroupDAC().fetchAllGroups(inquiryGroupRequest);

		TestBaseUtil.assertResultResponse(response);

		insertGroup(TEST_GROUP_NAME8888, TEST_GROUP_DESCRIPTION8888,
				ServiceTypeEnum.ELECTRIC,
				DeviceTypeEnum.HAN_DEVICE, HanDeviceTypeEnum.IHD);

		inquiryGroupRequest = new InquiryGroupRequest();
		inquiryGroupRequest.setUserContext(TestBaseUtil.createUserContext());
		inquiryGroupRequest.setTenant(TestBaseUtil.createTenant());
		inquiryGroupRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		deviceSearch = new DeviceSearch(DeviceTypeEnum.HAN_DEVICE);
		HanDeviceSearch hanDeviceSearch = new HanDeviceSearch();
		hanDeviceSearch.setHanDeviceTypeEnumList(Arrays.asList(HanDeviceTypeEnum.IHD));
		deviceSearch.setHanDeviceSearch(hanDeviceSearch);
		inquiryGroupRequest.setDeviceSearch(deviceSearch);
		response = getGroupDAC().fetchAllGroups(inquiryGroupRequest);

		TestBaseUtil.assertResultResponse(response);

	}

	/**
	 * Test fetch can group be inserted.
	 */
	@Test
	public void testFetchCanGroupBeInserted()
	{
		// Insert new Group
		Group group = insertGroup(TEST_ADD_GROUP, DESC_ADD_GROUP, ServiceTypeEnum.ELECTRIC, DeviceTypeEnum.LCM, null);

		// Test CanGroupBeInserted
		GroupRequest request = new GroupRequest(group, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());

		InternalResultsResponse<Boolean> internalResultResponse = getGroupDAC().fetchCanGroupBeInserted(request);
		assertEquals(false, internalResultResponse.getFirstResult().booleanValue());

		group = insertGroup(TEST_ADD_GROUP, DESC_ADD_GROUP, ServiceTypeEnum.WATER, null, null);

		request = new GroupRequest(group, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		request.setTenant(TestBaseUtil.createTenant());
		internalResultResponse = getGroupDAC().fetchCanGroupBeInserted(request);
		assertEquals(false, internalResultResponse.getFirstResult().booleanValue());

		group = new Group("Test");
		request.getGroups().clear();
		request.addGroup(group);

		internalResultResponse = getGroupDAC().fetchCanGroupBeInserted(request);
		assertEquals(0, internalResultResponse.getMessageInfoList().size());

		group.setOldName(TEST_ADD_GROUP);
		request.getGroups().clear();
		request.addGroup(group);
		internalResultResponse = getGroupDAC().fetchCanGroupBeInserted(request);
		assertEquals(0, internalResultResponse.getMessageInfoList().size());
	}

	/**
	 * Test insert group.
	 */
	@Test
	public void testInsertGroup()
	{
		// Insert new Group
		insertGroup("TestInsertElectric", "DescriptionInsertElectric", ServiceTypeEnum.ELECTRIC,
				DeviceTypeEnum.ELECTRIC_METER, null);
		insertGroup("TestInsertElectricHan", "DescriptionInsertElectricHan", ServiceTypeEnum.ELECTRIC,
				DeviceTypeEnum.HAN_DEVICE, HanDeviceTypeEnum.THERMOSTAT);
		insertGroup("TestInsertWater", "DescriptionInsertWater", ServiceTypeEnum.WATER, null, null);
	}

	/**
	 * Test delete group.
	 */
	@Test
	public void testDeleteGroup()
	{
		// Insert new Group and add in group request
		GroupRequest request = new GroupRequest(insertGroup(NAME, DESCRIPTION));

		// Test delete group
		InternalResponse response = getGroupDAC().deleteGroup(request);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	/**
	 * Test update group.
	 */
	@Test
	public void testUpdateGroup()
	{
		// Insert new Group
		Group group = insertGroup("TestGroupUpd", DESC_GROUP_UPD);
		group.setOldName("TestGroup");
		// Update Name and description
		GroupRequest request = new GroupRequest(group, TestBaseUtil.createUserContext());

		InternalResponse response = getGroupDAC().updateGroup(request);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		group.setName("Downtown 3");
		group.setDescription(DESC_GROUP_UPD);
		group.setGroupTypeEnum(GroupTypeEnum.enumForValue(FOUR));
		group.setOldName("Downtown");
		// Update Name and description
		request = new GroupRequest(group, TestBaseUtil.createUserContext());
		response = getGroupDAC().updateGroup(request);

		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	/**
	 * Test delete smart point from group.
	 */
	@Test
	public void testDeleteDeviceFromGroup()
	{

		InquiryDeviceRequest request = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		request.addDevice(createMeter());
		request.addGroup(insertGroup(NAME, DESCRIPTION));

		// insert device to group
		InternalResponse responseGroup = getGroupDAC().insertDeviceToGroup(request);
		assertEquals(responseGroup.getStatus(), Status.OperationSuccess);

		// delete device from group
		InternalResponse response = getGroupDAC().deleteDeviceFromGroup(request);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		// exception situation - sending a null group ID
		request = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		request.addDevice(createMeter());
		Group g = new Group();
		g.setId(null);
		request.addGroup(g);

		response = getGroupDAC().deleteDeviceFromGroup(request);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	/**
	 * Test fetch groups by meter.
	 */
	@Test
	public void testFetchGroupsByDevice()
	{
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(createMeter(), insertGroup(NAME, DESCRIPTION),
						TestBaseUtil.createUserContext());

		// Insert device to group
		InternalResponse response = getGroupDAC().insertDeviceToGroup(request);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		getGroupDAC().fetchGroupsByDevice(new DeviceRequest(request.getFirstDevice()));
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	/**
	 * Test insert smart point to group.
	 */
	@Test
	public void testInsertDeviceToGroup()
	{
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(createMeter(), insertGroup(NAME, DESCRIPTION),
						TestBaseUtil.createUserContext());

		// insert device to group
		InternalResponse response = getGroupDAC().insertDeviceToGroup(request);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		// exception situation - sending a null group ID
		Group g = new Group();
		g.setId(null);
		request = new InquiryDeviceRequest(createMeter(), g, TestBaseUtil.createUserContext());

		response = getGroupDAC().insertDeviceToGroup(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
	}

	/**
	 * Test fetch devices by group.
	 */
	@Test
	public void testFetchDevicesByGroup()
	{
		// Insert new Group and add in group request
		GroupRequest groupRequest =
				new GroupRequest(insertGroup(NAME, DESCRIPTION), TestBaseUtil.createUserContext());

		// Test fetch device by group
		InternalResultsResponse<Device> response = getGroupDAC().fetchDevicesByGroup(groupRequest);
		assertNotNull(response);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Insert group.
	 * 
	 * @param name the name
	 * @param description the description
	 * @return the group
	 */
	private Group insertGroup(String name, String description)
	{
		return insertGroup(name, description, ServiceTypeEnum.ELECTRIC, DeviceTypeEnum.ELECTRIC_METER, null);
	}

	/**
	 * Insert group.
	 * 
	 * @param name the name
	 * @param description the description
	 * @param serviceTypeEnum the service type enum
	 * @param deviceType the device type
	 * @return the group
	 */
	private Group insertGroup(String name, String description, ServiceTypeEnum serviceTypeEnum,
			DeviceTypeEnum deviceType, HanDeviceTypeEnum hanDeviceType)
	{
		// Insert new Group
		Group group = new Group(name);
		group.setDescription(description);
		group.setGroupTypeEnum(GroupTypeEnum.enumForValue(FOUR));
		if (!ValidationUtil.isNull(deviceType))
		{
			group.setDeviceType(deviceType);
		}
		if (!ValidationUtil.isNull(hanDeviceType))
		{
			group.setHanDeviceType(hanDeviceType);
		}

		GroupRequest request = new GroupRequest(group, TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.setServiceTypeEnum(serviceTypeEnum);

		InternalResultsResponse<Group> response = getGroupDAC().insertGroup(request);

		assertEquals(response.getStatus(), Status.OperationSuccess);

		return response.getFirstResult();
	}

	/**
	 * Create meter.
	 * 
	 * @return the meter
	 */
	private ElectricMeter createMeter()
	{
		ElectricMeter meter = new ElectricMeter(new Radio(new BigInteger("1024")));
		meter.getRadio().setLocation(new Location());

		meter.getRadio().getLocation().setLatitude(LATITUDE_TWELVE);
		meter.getRadio().getLocation().setLongitude(LONGITUDE_ELEVEN);

		return meter;
	}
}
