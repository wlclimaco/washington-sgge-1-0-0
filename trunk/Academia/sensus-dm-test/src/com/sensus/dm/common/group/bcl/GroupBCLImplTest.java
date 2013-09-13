package com.sensus.dm.common.group.bcl;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.dac.IGroupDAC;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;

/**
 * The Class GroupBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/group/groupbclimpltest.xml"})
public class GroupBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant IDS_FILE. */
	private static final String IDS_FILE = "idsFile";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "downtown";

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "description";

	/** The Constant GROUP. */
	private static final String GROUP = "group";

	/** The Constant FETCH_ALL_GROUPS. */
	private static final String FETCH_ALL_GROUPS = "fetchAllGroups";

	/** The Constant FETCH_GROUP_BY_ID. */
	private static final String FETCH_GROUP_BY_ID = "fetchGroupById";

	/** The Constant FETCH_GROUPS_BY_DEVICE. */
	private static final String FETCH_GROUPS_BY_DEVICE = "fetchGroupsByDevice";

	/** The Constant INSERT_GROUP. */
	private static final String INSERT_GROUP = "insertGroup";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant UPLOAD_IDS. */
	private static final String UPLOAD_IDS = "upload ids";

	/** The Constant FETCH_ALL_DEVICES. */
	private static final String FETCH_ALL_DEVICES = "fetchAllDevices";

	/** The Constant INSERT_DEVICE_TO_GROUP. */
	private static final String INSERT_DEVICE_TO_GROUP = "insertDeviceToGroup";

	/** The Constant INSERT_PROCESS_ITEMS. */
	private static final String INSERT_PROCESS_ITEMS = "insertProcessItems";

	/** The Constant FETCH_CHECK_PROCESSING. */
	private static final String FETCH_CHECK_PROCESSING = "fetchCheckProcessing";

	/** The Constant DELETE_GROUP. */
	private static final String DELETE_GROUP = "deleteGroup";

	/** The Constant UPDATE_GROUP. */
	private static final String UPDATE_GROUP = "updateGroup";

	/** The Constant DELETE_DEVICE_FROM_GROUP. */
	private static final String DELETE_DEVICE_FROM_GROUP = "deleteDeviceFromGroup";

	/** The Constant FETCH_ALL_GAS_METERS. */
	private static final String FETCH_ALL_GAS_METERS = "fetchAllGasMeters";

	/** The Constant FETCH_ALL_WATER_METERS. */
	private static final String FETCH_ALL_WATER_METERS = "fetchAllWaterMeters";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath; // injected by Spring through setter

	/** The group bcl. */
	private IGroupBCL groupBCL; // injected by Spring through setter

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL; // injected by Spring through setter

	/** The water meter bcl. */
	private IWaterMeterBCL waterMeterBCL; // injected by Spring through setter

	/** The gas meter bcl. */
	private IGasMeterBCL gasMeterBCL; // injected by Spring through setter

	/**
	 * Gets the group bs cl.
	 * 
	 * @return the group bs cl
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
	 * Gets the electric meter bcl.
	 * 
	 * @return the electric meter bcl
	 */
	public IElectricMeterBCL getElectricMeterBCL()
	{
		return electricMeterBCL;
	}

	/**
	 * Sets the electric meter bcl.
	 * 
	 * @param electricMeterBCL the new electric meter bcl
	 */
	public void setElectricMeterBCL(IElectricMeterBCL electricMeterBCL)
	{
		this.electricMeterBCL = electricMeterBCL;
	}

	/**
	 * Gets the water meter bcl.
	 * 
	 * @return the water meter bcl
	 */
	public IWaterMeterBCL getWaterMeterBCL()
	{
		return waterMeterBCL;
	}

	/**
	 * Sets the water meter bcl.
	 * 
	 * @param waterMeterBCL the new water meter bcl
	 */
	public void setWaterMeterBCL(IWaterMeterBCL waterMeterBCL)
	{
		this.waterMeterBCL = waterMeterBCL;
	}

	/**
	 * Gets the gas meter bcl.
	 * 
	 * @return the gas meter bcl
	 */
	public IGasMeterBCL getGasMeterBCL()
	{
		return gasMeterBCL;
	}

	/**
	 * Sets the gas meter bcl.
	 * 
	 * @param gasMeterBCL the new gas meter bcl
	 */
	public void setGasMeterBCL(IGasMeterBCL gasMeterBCL)
	{
		this.gasMeterBCL = gasMeterBCL;
	}

	/**
	 * Gets the temp upload file path.
	 * 
	 * @return the temp upload file path
	 */
	public String getTempUploadFilePath()
	{
		return tempUploadFilePath;
	}

	/**
	 * Sets the temp upload file path.
	 * 
	 * @param tempUploadFilePath the new temp upload file path
	 */
	public void setTempUploadFilePath(String tempUploadFilePath)
	{
		this.tempUploadFilePath = tempUploadFilePath;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests Settings:

	/**
	 * Sets the group area.
	 */
	public void setGroupArea()
	{
		setArea(getGroupBCL(), EPMAreaEnum.GROUP);
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
	 * Removes the custom search area.
	 */
	@After
	public void resetMocks()
	{
		resetMocksData(getGroupBCL());
		setGroupArea();
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
		// Error Situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				FETCH_ALL_GROUPS);

		InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest();
		inquiryGroupRequest.setTenant(TestBaseUtil.createTenant());
		InternalResultsResponse<Group> response = getGroupBCL().fetchAllGroups(inquiryGroupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Success Situation
		response = getGroupBCL().fetchAllGroups(inquiryGroupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

	}

	/**
	 * Test fetch groups by device.
	 */
	@Test
	public void testFetchGroupsByDevice()
	{
		// Error Situation
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				FETCH_GROUPS_BY_DEVICE);

		DeviceRequest deviceRequest = new DeviceRequest();
		InternalResultsResponse<Group> response = getGroupBCL().fetchGroupsByDevice(deviceRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Success Situation
		response = getGroupBCL().fetchGroupsByDevice(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test insert group.
	 */
	@Test
	public void testInsertGroup()
	{
		// Success Situation
		GroupRequest groupRequest = new GroupRequest(new Group(GROUP, DESCRIPTION, null));
		groupRequest.setTenant(TestBaseUtil.createTenant());
		InternalResponse response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Test inserting a good group
		groupRequest = new GroupRequest(new Group(1, GROUP_NAME, DESCRIPTION), TestBaseUtil.createUserContext());
		groupRequest.setIsMonitored(true);
		groupRequest.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		groupRequest = new GroupRequest(new Group(1, GROUP_NAME, DESCRIPTION), TestBaseUtil.createUserContext());
		Device device = new ElectricMeter(new Radio(), DEVICE_ID);
		groupRequest.getFirstGroup().addDevice(device);
		groupRequest.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Error Situation - for Group (Insert Group)
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				INSERT_GROUP);

		response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for Process (Insert Process)
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				INSERT_PROCESS);

		response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// add Devices to Group situation
		groupRequest.setUploadIds(UPLOAD_IDS);
		groupRequest.setIdsFile(new File(IDS_FILE));
		groupRequest.setIdFileType(PropertyEnum.DESCRIPTION);
		response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test insert device to group.
	 */
	@Test
	public void testInsertDeviceToGroup()
	{
		// Success Situation
		// Test inserting a good group
		GroupRequest groupRequest = new GroupRequest(new Group(SEVEN, GROUP_NAME, DESCRIPTION));
		groupRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		// first we have to insert the group
		InternalResultsResponse<Group> response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		InquiryDeviceRequest deviceRequest = new InquiryDeviceRequest(new UserContext());
		deviceRequest.addGroup(response.getFirstResult());
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		deviceRequest.setFileName("filename");
		deviceRequest.setUnreachableIds(new ArrayList<String>());
		deviceRequest.setPaginationAllSelected(false);
		deviceRequest.setIdFileType(PropertyEnum.NETWORK_ADDRESS);

		// then we can insert the device to the group to Electric Meter
		InternalResponse response2 = getGroupBCL().insertDeviceToGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response2.getStatus());

		// Success Situation - fetch all devices from group to Water
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response2 = getGroupBCL().insertDeviceToGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response2.getStatus());
		resetMocks();

		// Success Situation - fetch all devices from group to Gas
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.GAS);
		response2 = getGroupBCL().insertDeviceToGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response2.getStatus());
		resetMocks();

		// Error Situation - for insert device to group
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				INSERT_DEVICE_TO_GROUP);

		deviceRequest.setPaginationAllSelected(false);
		response2 = getGroupBCL().insertDeviceToGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response2.getStatus());

		resetMocks();

		// Error Situation - for insert process
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				INSERT_PROCESS);
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		response2 = getGroupBCL().insertDeviceToGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response2.getStatus());

		resetMocks();

		// Error Situation - for insert process
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				INSERT_PROCESS_ITEMS);

		deviceRequest.setFileName(null);
		deviceRequest.setPaginationAllSelected(true);
		response2 = getGroupBCL().insertDeviceToGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response2.getStatus());

		resetMocks();

	}

	/**
	 * Test insert device from file to group.
	 */
	@Test
	public void testInsertDeviceFromFileToGroup()
	{

		InternalResponse response = getGroupBCL().insertDeviceFromFileToGroup(new GroupRequest());
		assertEquals(Status.ExceptionError, response.getStatus());

		// Success Situation
		GroupRequest groupRequest = new GroupRequest(new Group(SEVEN, GROUP_NAME, DESCRIPTION));
		groupRequest.setFileName(createFile());
		groupRequest.setIdFileType(PropertyEnum.CONNECTION_STATUS);
		groupRequest.setTenant(TestBaseUtil.createTenant());
		groupRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		groupRequest.setGroups(Arrays.asList(new Group(DeviceTypeEnum.ELECTRIC_METER)));

		response = getGroupBCL().insertDeviceFromFileToGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// coverage
		groupRequest.setFileName(createFile());
		groupRequest.setIdFileType(PropertyEnum.NETWORK_ADDRESS);

		response = getGroupBCL().insertDeviceFromFileToGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// coverage
		groupRequest.setFileName(createFile());
		groupRequest.setIdFileType(PropertyEnum.DEVICE_ID);

		response = getGroupBCL().insertDeviceFromFileToGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Update Process Situation
		response = getGroupBCL().insertDeviceFromFileToGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test update group.
	 */
	@Test
	public void testUpdateGroup()
	{
		// Test inserting a good group
		Group group = new Group(GROUP, DESCRIPTION, GroupTypeEnum.BILLING);
		group.setCreateUser(USER);

		GroupRequest groupRequest = new GroupRequest(group);

		// update group with fail
		InternalResponse response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Test inserting a good group
		group.setGroupTypeEnum(GroupTypeEnum.enumForValue(1));

		response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Test updating the group
		group.setId(SEVEN);
		group.setOldName(group.getName());
		group.setName(GROUP_NAME);
		group.setDescription(DESCRIPTION);
		group.setGroupTypeEnum(GroupTypeEnum.enumForValue(1));
		groupRequest.addGroup(group);
		groupRequest.setUploadIds(STRING_ONE);
		groupRequest.setIdFileType(PropertyEnum.FILE_IDS_TYPE);

		// update group with device flexnetId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		group.setDevices(new ArrayList<Device>());
		ElectricMeter meter = new ElectricMeter(DEVICE_ID);
		group.getDevices().add(meter);

		// update group with device meterId
		groupRequest.setIdsFile(new File(IDS_FILE));

		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Error Situation - for fetch check processing
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_CHECK_PROCESSING);

		// update group with device meterId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for insert process
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				INSERT_PROCESS);

		// update group with device meterId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for update group
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				UPDATE_GROUP);

		// update group with device meterId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

	}

	/**
	 * Test update group replace.
	 */
	@Test
	public void testUpdateGroupReplace()
	{
		// Test inserting a good group
		Group group = new Group(GROUP, DESCRIPTION, GroupTypeEnum.BILLING);
		group.setCreateUser(USER);

		GroupRequest groupRequest = new GroupRequest(group);
		groupRequest.setGroupReplace(Boolean.TRUE);

		// update group with fail
		InternalResponse response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Test inserting a good group
		group.setGroupTypeEnum(GroupTypeEnum.enumForValue(1));

		response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Test updating the group
		group.setId(SEVEN);
		group.setOldName(group.getName());
		group.setName(GROUP_NAME);
		group.setDescription(DESCRIPTION);
		group.setGroupTypeEnum(GroupTypeEnum.enumForValue(1));
		groupRequest.addGroup(group);
		groupRequest.setUploadIds(STRING_ONE);
		groupRequest.setIdFileType(PropertyEnum.FILE_IDS_TYPE);

		// update group with device flexnetId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		groupRequest.getFirstGroup().addDevice(new Device(new Radio(new BigInteger(STRING_ONE))));
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		groupRequest.setIdsFile(new File(IDS_FILE));

		// Error
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		group.setDevices(new ArrayList<Device>());
		ElectricMeter meter = new ElectricMeter(DEVICE_ID);
		group.getDevices().add(meter);

		groupRequest.getGroups().clear();
		groupRequest.addGroup(group);

		// update group with device meterId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		// Error Situation - for fetch check processing
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_CHECK_PROCESSING);

		// update group with device meterId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for insert process
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				INSERT_PROCESS);

		// update group with device meterId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for update group
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				UPDATE_GROUP);

		// update group with device meterId
		response = getGroupBCL().updateGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

	}

	/**
	 * Test delete device from group.
	 */
	@Test
	public void testDeleteDeviceFromGroup()
	{
		// Test inserting a good group
		GroupRequest groupRequest = new GroupRequest(new Group(SEVEN, GROUP_NAME, DESCRIPTION));
		groupRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);

		InternalResultsResponse<Group> response = getGroupBCL().insertGroup(groupRequest);
		assertEquals(response.getResultsList().size(), 1);

		InquiryDeviceRequest deviceRequest = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		deviceRequest.addGroup(response.getFirstResult());
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);

		deviceRequest.addSelectionPaginationId(new BigInteger(STRING_ONE));
		deviceRequest.setPaginationAllSelected(false);

		// add device
		InternalResponse response2 = getGroupBCL().insertDeviceToGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response2.getStatus());

		// delete device with msg
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		Group group = new Group(1);

		deviceRequest = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		deviceRequest.addGroup(group);
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);

		deviceRequest.addSelectionPaginationId(new BigInteger(STRING_ONE));
		deviceRequest.setPaginationAllSelected(false);

		// delete all device
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Error Situation - for fetch all devices Electric Meter
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IElectricMeterBCL.class,
				FETCH_ALL_DEVICES);
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		// update group with device meterId
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		resetMocks();

		// update group with device meterId
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		resetMocks();

		// Error Situation - for fetch all Water Meter
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IWaterMeterBCL.class,
				FETCH_ALL_WATER_METERS);
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);

		// update group with device meterId
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		resetMocks();

		// Error Situation - for fetch all Gas Meter
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGasMeterBCL.class,
				FETCH_ALL_GAS_METERS);
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.GAS);

		// update group with device meterId
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		resetMocks();

		// Error Situation - for delete device from group
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				DELETE_DEVICE_FROM_GROUP);

		// update group with device meterId
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		resetMocks();

		// Error Situation - for insert process
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				INSERT_PROCESS);

		// update group with device meterId
		response2 = getGroupBCL().deleteDeviceFromGroup(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		resetMocks();
	}

	/**
	 * Test delete group.
	 */
	@Test
	public void testDeleteGroup()
	{
		// Success situation
		GroupRequest groupRequest = new GroupRequest();
		groupRequest.addGroup(new Group(1));
		groupRequest.addGroup(new Group(2));
		groupRequest.addGroup(new Group(THREE));

		InternalResponse response = getGroupBCL().deleteGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		// Error Situation - for deleteGroup
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				DELETE_GROUP);

		response = getGroupBCL().deleteGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for fetch Group by ID
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IGroupDAC.class,
				FETCH_GROUP_BY_ID);

		response = getGroupBCL().deleteGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for insert Process
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				INSERT_PROCESS);

		response = getGroupBCL().deleteGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();

		// Error Situation - for fetch Message processing
		setSituation(getGroupBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_CHECK_PROCESSING);

		response = getGroupBCL().deleteGroup(groupRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocks();
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		inquiryGroupRequest.setUserContext(TestBaseUtil.createUserContextWithLocale());
		inquiryGroupRequest.setTenant(TestBaseUtil.createTenant());
		inquiryGroupRequest.setSelectionPaginationIds(new ArrayList<BigInteger>());
		inquiryGroupRequest.getSelectionPaginationIds().add(new BigInteger("1"));
		inquiryGroupRequest.setFileName("fileTest2");
		inquiryGroupRequest.setDateFormat("yyyy/MM/dd");
		// inquiryGroupRequest.setProcessId(1);

		InternalResponse response = getGroupBCL().generateGroupsCSV(inquiryGroupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		inquiryGroupRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getGroupBCL().generateGroupsCSV(inquiryGroupRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test fetch devices by group to map.
	 */
	@Test
	public void testFetchCanGroupBeInserted()
	{
		InternalResultsResponse<Boolean> response =
				getGroupBCL().fetchCanGroupBeInserted(new GroupRequest(new Group(GROUP)));
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertEquals(false, response.getFirstResult().booleanValue());

		response = getGroupBCL().fetchCanGroupBeInserted(new GroupRequest(new Group("group test")));
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertEquals(true, response.getFirstResult().booleanValue());
	}

	/**
	 * Creates the file.
	 * 
	 * @return the string
	 */
	private String createFile()
	{
		// Exception Situation
		String fileName = "out.txt";

		try
		{
			// Create file
			FileWriter fstream = new FileWriter("/opt/flexnet-dm/upload/" + fileName);
			BufferedWriter out = new BufferedWriter(fstream);

			for (int x = ONE_HUNDRED_ONE; x <= ONE_THOUSAND_FIVE; x++)
			{
				out.write(x + ",");
			}
			out.write("1010101010");

			// Close the output stream
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		return fileName;
	}
}
