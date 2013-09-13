package com.sensus.dm.elec.action.bcl;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.dac.IActionDAC;
import com.sensus.dm.elec.action.model.CancelDemandResponseEventAction;
import com.sensus.dm.elec.action.model.CancelSendHanTextMessageAction;
import com.sensus.dm.elec.action.model.ConnectHanDeviceAction;
import com.sensus.dm.elec.action.model.DeleteHanDeviceAction;
import com.sensus.dm.elec.action.model.DemandResetEventAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;
import com.sensus.dm.elec.action.model.DisconnectHanDeviceAction;
import com.sensus.dm.elec.action.model.GetDemandResponseEventStatusAction;
import com.sensus.dm.elec.action.model.GetDemandResponseSetupStatusAction;
import com.sensus.dm.elec.action.model.GetHanConnectionStatusAction;
import com.sensus.dm.elec.action.model.GetRemoteConnectStatusAction;
import com.sensus.dm.elec.action.model.GetTamperDetectTimerAction;
import com.sensus.dm.elec.action.model.ImportHanDeviceAction;
import com.sensus.dm.elec.action.model.RemoteArmConnectAction;
import com.sensus.dm.elec.action.model.RemoteConnectAction;
import com.sensus.dm.elec.action.model.RemoteDisconnectAction;
import com.sensus.dm.elec.action.model.RetryImportHanDeviceAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;
import com.sensus.dm.elec.action.model.SetTamperDetectTimerAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;
import com.sensus.dm.elec.device.model.DeviceClassEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceConfiguration;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.water.action.model.DemandReadAction;

/**
 * The Class ActionBCLImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/elec/action/actionbclimpltest.xml"})
public class ActionBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant TEMP_UPLOAD_FILE_PATH. */
	private static final String TEMP_UPLOAD_FILE_PATH = "/opt/flexnet-dm/upload/";

	/** The Constant FILE_IMPORT. */
	private static final String FILE_IMPORT = "importHanDevice.csv";

	/** The Constant FILE_IMPORT_ACTION_DEVICE. */
	private static final String FILE_IMPORT_ACTION_DEVICE = "actionDevice.csv";

	/** The Constant SEND_HAN_TEXT. */
	private static final String SEND_HAN_TEXT = "Hello this is QAT Brazil";

	/** The Constant OTA_METER_FLEXNET_ID. */
	private static final BigInteger OTA_METER_FLEXNET_ID = new BigInteger("46549474");

	/** The Constant OTA_METER_FLEXNET2_ID. */
	private static final BigInteger OTA_METER_FLEXNET2_ID = new BigInteger("46549476");

	/** The Constant OTA_METER_DEVICE_ID. */
	private static final String OTA_METER_DEVICE_ID = "1N6028900474";

	/** The Constant OTA_ENTEK_FLEXNET_ID. */
	private static final BigInteger OTA_ENTEK_FLEXNET_ID = new BigInteger("50402376");

	/** The Constant OTA_ENTEK_DEVICE_ID. */
	private static final String OTA_ENTEK_DEVICE_ID = "560375";

	/** The Constant OTA_HAN_FLEXNET_ID. */
	private static final BigInteger OTA_HAN_FLEXNET_ID = new BigInteger("2153943262073613");

	/** The Constant OTA_HAN_DEVICE_ID. */
	private static final String OTA_HAN_DEVICE_ID = "IHD925B";

	/** The Constant INSTALL_CODE. */
	private static final String INSTALL_CODE = "00925b016b98c5ec266e";

	/** The Constant INSTALL_CODE_ERROR. */
	private static final String INSTALL_CODE_ERROR = "00925b016b98g5ec266e";

	/** The Constant ALL. */
	private static final String ALL = "All";

	/** The Constant INSERT_ACTION. */
	private static final String INSERT_ACTION = "insertAction";

	/** The Constant INSERT_GROUPS_TO_ACTION. */
	private static final String INSERT_GROUPS_TO_ACTION = "insertGroupsToAction";

	/** The Constant INSERT_TAGS_TO_ACTION. */
	private static final String INSERT_TAGS_TO_ACTION = "insertTagsToAction";

	/** The Constant INSERT_DEVICES_TO_ACTION. */
	private static final String INSERT_DEVICES_TO_ACTION = "insertDevicesToAction";

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = "fetchProcessById";

	/** The Constant DELETE_GROUPS_FROM_ACTION. */
	private static final String DELETE_GROUPS_FROM_ACTION = "deleteGroupsFromAction";

	/** The Constant DELETE_DEVICES_FROM_ACTION. */
	private static final String DELETE_DEVICES_FROM_ACTION = "deleteDevicesFromAction";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The action bcl. */
	private IActionBCL actionBCL;

	/**
	 * Gets the action bcl.
	 * 
	 * @return the action bcl
	 */
	public IActionBCL getActionBCL()
	{
		return actionBCL;
	}

	/**
	 * Sets the action bcl.
	 * 
	 * @param actionBCL the new action bcl
	 */
	@Resource(name = "actionBCLTarget")
	public void setActionBCL(IActionBCL actionBCL)
	{
		this.actionBCL = actionBCL;
	}

	/**
	 * Sets the action area.
	 */
	public void setActionArea()
	{
		super.setArea(getActionBCL(), EPMAreaEnum.ACTION);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setActionArea();
	}

	/**
	 * Reset mocks to action area.
	 */
	@After
	public void resetMocksToActionArea()
	{
		resetMocksData(getActionBCL());
		setActionArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// To be tested here are all Over The Air (OTA) commands:
	// From BCF-HAN Server
	// 1) Delete Han Device 2) Cancel Send Text Message
	// 3) import HAN device (Create HAN Endpoint) 4) send Text message
	// 5) Disconnect HAN device (leave HAN)
	// 6) Get Han Connection Status (HAN Device) getLoadControlParameter
	// 7) Get Han Connection Status (Meter) 8) Connect HAN device (Join HAN )
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// From BCF-DR Server
	// 9) initLoadControlEvent (Demand Response) 10) getLoadControlEventReport
	// Get Demand Response Event Status
	// 11) setLoadControlParameter (Demand Response Set up) 12) cancelLoadControlEvent
	//
	// 13) Remote Connect/Disconnect/Arm
	// 14) Get Remote Connection Status
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Creates the file import han device.
	 * 
	 * @return the file
	 */
	private File createFileImportHanDevice()
	{
		// Create file
		File file = new File(TEMP_UPLOAD_FILE_PATH + FILE_IMPORT);
		try
		{
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			// first device TestDevice 1 is a valid HAN device without MeterId or Install Code
			// second device TestDevice 2 is a valid HAN device with Install Code but no Meter ID
			// third device TestDevice 3 is an invalid HAN device with invalid MacAddress and no Meter ID or Install
			// Code
			// fourth device TestDevice 4 is a valid HAN device with valid Install Code and Meter ID
			out.write("00:07:a6:ff:ff:00:92:5b,,Sensus/HAI,539 63 537 08002,,TestDevice 1,"
					+
					"\r\n00:07:a6:ff:ff:00:c2:49,,Sensus/HAI,81A00-1FT,00C249000B9F8F581300,TestDevice 2,"
					+
					"\r\n00:07:a6:ff:fG:00:92:59,,Sensus/HAI,539 63 537 08002,,TestDevice 3,"
					+
					"\r\n00:07:a6:ff:ff:00:93:57,1N6028953757,Sensus/HAI,539 63 537 08002,00935700cbb38f2095f4,TestDevice 4");
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * Creates the file import device to action.
	 * 
	 * @param strDevice the str device
	 * @return the string
	 */
	private String createFile(String strDevice)
	{
		// Create file
		File file = new File(TEMP_UPLOAD_FILE_PATH + FILE_IMPORT_ACTION_DEVICE);
		try
		{
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(strDevice);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return FILE_IMPORT_ACTION_DEVICE;
	}

	/**
	 * 
	 * Test execute delete han device.
	 */
	@Test
	public void testExecuteDeleteDevice()
	{
		// success case
		// this is valid for disconnected HAN Devices and Entek LCMs
		// delete disconnected HanDevice
		DMAction action = new DeleteHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));

		InquiryActionRequest request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);
		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// success case
		// this is valid for connected HAN Devices and Entek LCMs
		// delete entek
		action = new DeleteHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID, OTA_ENTEK_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Exception
		action = new DeleteHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_METER_FLEXNET2_ID, OTA_HAN_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Fail case
		// this is valid for connected HAN Devices and Entek LCMs
		// delete Meter
		action = new DeleteHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute remote connect disconnect.
	 */
	@Test
	public void testExecuteRemoteConnectDisconnect()
	{
		// Fail case
		// this is only valid for meters, not HAN devices or Entek LCMs
		// HanDevice
		RemoteArmConnectAction remoteArmConnect = new RemoteArmConnectAction(ONE, true, true, null);
		remoteArmConnect.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID,
				OTA_HAN_DEVICE_ID));

		InquiryActionRequest request =
				new InquiryActionRequest(remoteArmConnect, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Fail case
		// this is only valid for meters, not HAN devices or Entek LCMs
		// Entek LCM
		remoteArmConnect = new RemoteArmConnectAction(ONE, true, true, null);
		remoteArmConnect.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID,
				OTA_ENTEK_DEVICE_ID));

		request = new InquiryActionRequest(remoteArmConnect, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Success case
		// this is only valid for meters, not HAN devices or Entek LCMs
		// ArmConnect
		remoteArmConnect = new RemoteArmConnectAction(ONE, true, true, null);
		remoteArmConnect.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(remoteArmConnect, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Success case
		// this is only valid for meters, not HAN devices or Entek LCMs
		// Connect
		RemoteConnectAction remoteConnect = new RemoteConnectAction(ONE, true, true, null);
		remoteConnect.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(remoteConnect, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Success case
		// this is only valid for meters, not HAN devices or Entek LCMs
		// Disconnect
		RemoteDisconnectAction remoteDisconnect = new RemoteDisconnectAction(ONE, true, true, null);
		remoteDisconnect.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(remoteDisconnect, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Success case
		// this is only valid for meters, not HAN devices or Entek LCMs
		// RemoteGetConnectStatus
		GetRemoteConnectStatusAction getRemoteConnectStatus = new GetRemoteConnectStatusAction(ONE, true, true);
		getRemoteConnectStatus.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		request =
				new InquiryActionRequest(getRemoteConnectStatus, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute demand response setup.
	 */
	@Test
	public void testExecuteDemandResponseSetup()
	{
		Date actionTime = Calendar.getInstance().getTime();

		// success case
		// this is valid for HAN Devices and Entek LCMs
		// Demand Response Setup Entek
		DemandResponseSetupAction demandResponseSetup = new DemandResponseSetupAction(ONE, true, true);
		demandResponseSetup.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID,
				OTA_ENTEK_DEVICE_ID));
		demandResponseSetup.setEnrollmentCode(0);
		demandResponseSetup.setStartMinutes(FIVE);
		demandResponseSetup.setEndMinutes(FIVE);
		demandResponseSetup.setActionTime(actionTime);

		InquiryActionRequest request =
				new InquiryActionRequest(demandResponseSetup, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// success case
		// this is valid for HAN Devices and Entek LCMs
		// Demand Response Setup HanDevice
		demandResponseSetup = new DemandResponseSetupAction(ONE, true, true);
		demandResponseSetup.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID,
				OTA_HAN_DEVICE_ID));

		demandResponseSetup.setEnrollmentCode(0);
		demandResponseSetup.setStartMinutes(FIVE);
		demandResponseSetup.setEndMinutes(FIVE);
		demandResponseSetup.setActionTime(actionTime);

		request =
				new InquiryActionRequest(demandResponseSetup, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Fail case
		// this is valid for HAN Devices and Entek LCMs
		// Demand Response Setup Meter
		demandResponseSetup = new DemandResponseSetupAction(ONE, true, true);
		demandResponseSetup.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));
		demandResponseSetup.setEnrollmentCode(0);
		demandResponseSetup.setStartMinutes(FIVE);
		demandResponseSetup.setEndMinutes(FIVE);
		demandResponseSetup.setActionTime(actionTime);

		request =
				new InquiryActionRequest(demandResponseSetup, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute demand response setup.
	 * Success case for setting up one relay on an Entek LCM
	 */
	@Test
	public void testMultipleRelayDemandResponseSetupForOneRelay()
	{
		Date actionTime = Calendar.getInstance().getTime();

		// success case
		// for relay number 2
		// setting all parameters (enrollment code, startMinutes, endMinutes, deviceClass)
		// this is valid for Entek LCMs only
		DemandResponseSetupAction demandResponseSetup = new DemandResponseSetupAction(ONE, true, true);
		com.sensus.dm.elec.device.model.LCM entekLCM =
				(com.sensus.dm.elec.device.model.LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM, new BigInteger(
						"50410022"), "2198");
		demandResponseSetup.addDevice(entekLCM);
		demandResponseSetup.setActionTime(actionTime);
		demandResponseSetup.setProcessId(TEN);

		// pass only the relay which has demand response setup parameters
		ArrayList<LCMRelay> relayList = new ArrayList<LCMRelay>();
		LCMRelay relayTwo = new LCMRelay();
		relayList.add(relayTwo);
		demandResponseSetup.setLcmRelays(relayList);

		relayTwo.setRelay(2);
		relayTwo.setEnrollmentCode(13);
		relayTwo.setStartMinutes(FIVE);
		relayTwo.setEndMinutes(FIFTEEN);

		// we will get a jar with device class int values from Sensus, until then just pass an int
		// 1 = HVACCompressor
		// 2 = StripHeaters
		// 4 = WaterHeater
		// 8 = PoolPump
		// 16 = SmartAppliances
		// 32 = IrrigationPump
		// 64 = IndustrialLoads, etc
		relayTwo.setDeviceClass(DeviceClassEnum.EXTERIOR_LIGHTING);

		InquiryActionRequest request =
				new InquiryActionRequest(demandResponseSetup, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute demand response setup.
	 * Success case for setting up all 3 relays on an Entek LCM
	 * However, not all parameters are set for each relay
	 */
	@Test
	public void testMultipleRelayDemandResponseSetupForThreeRelays()
	{
		Date actionTime = Calendar.getInstance().getTime();

		// success case
		// for all 3 relays
		// setting all parameters (enrollment code, startMinutes, endMinutes, deviceClass)
		// this is valid for Entek LCMs only
		DemandResponseSetupAction demandResponseSetup = new DemandResponseSetupAction(ONE, true, true);
		com.sensus.dm.elec.device.model.LCM entekLCM =
				(com.sensus.dm.elec.device.model.LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM, new BigInteger(
						"50410022"), "2198");
		demandResponseSetup.addDevice(entekLCM);
		demandResponseSetup.setActionTime(actionTime);

		// pass only the relay which has demand response setup parameters
		ArrayList<LCMRelay> relayList = new ArrayList<LCMRelay>();

		// create all relays
		LCMRelay relayOne = new LCMRelay();
		LCMRelay relayTwo = new LCMRelay();
		LCMRelay relayThree = new LCMRelay();

		// add relays to the list and add the list to the entek LCM
		relayList.add(relayOne);
		relayList.add(relayTwo);
		relayList.add(relayThree);
		demandResponseSetup.setLcmRelays(relayList);

		// identify each relay
		relayOne.setRelay(1);
		relayTwo.setRelay(2);
		relayThree.setRelay(2);

		// set the parameters for the first relay
		relayOne.setEnrollmentCode(0);

		// set the parameters for the second relay
		relayTwo.setEnrollmentCode(0);
		// we will get a jar with device class int values from Sensus, until then just pass an int
		// 16 = SmartAppliances
		relayTwo.setDeviceClass(DeviceClassEnum.STRIP_HEATER);

		// set the parameters for the third relay
		relayThree.setEnrollmentCode(0);
		relayThree.setStartMinutes(FIFTEEN);
		relayThree.setEndMinutes(FIFTEEN);

		InquiryActionRequest request =
				new InquiryActionRequest(demandResponseSetup, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	@Test
	public void testConvertTamperRelaysToProperty()
	{
		// create an LCM with some properties for us to convert
		List<LCMRelay> relays = new ArrayList<LCMRelay>();

		// we want to add properties to all 3 relays
		LCMRelay relay1 = new LCMRelay(ONE, "" + ONE);
		LCMRelay relay2 = new LCMRelay(TWO, "" + TWO);
		LCMRelay relay3 = new LCMRelay(THREE, "" + THREE);

		// and add all three relays to our relayList
		relays.add(relay1);
		relays.add(relay2);
		relays.add(relay3);

		List<Property> properties = new ArrayList<Property>();
		DMConvertUtil.convertTamperTimerToProperty(relays, properties);

		// now the LCM relays have been converted, let`s make sure it was done correctly...
		// to do that, we`ll change back from properties to an LCM and then compare all values...
		List<LCMRelay> newRelays = DMConvertUtil.convertPropertyToTamperTimer(properties);

		// now the oldLCM and the newLCM should have all the same values
		// let`s check the old against the new...

		int quantityTestsPassed = 0;

		for (LCMRelay oldLcmRelay : relays)
		{
			for (LCMRelay newLcmRelay : newRelays)
			{
				if (oldLcmRelay.getRelay().equals(newLcmRelay.getRelay()))
				{
					assertEquals(oldLcmRelay.getTamperDetectTimer(), newLcmRelay.getTamperDetectTimer());
					quantityTestsPassed++;
				}
			}
		}

		assertEquals(quantityTestsPassed, THREE);
	}

	/**
	 * Success case for converting properties to/from Entek LCM.
	 */
	@Test
	public void testConvertRelaysToProperty1()
	{
		// create an LCM with some properties for us to convert
		List<LCMRelay> relays = new ArrayList<LCMRelay>();

		// we want to add properties to all 3 relays
		LCMRelay relay1 = new LCMRelay(ONE);
		LCMRelay relay2 = new LCMRelay(TWO);
		LCMRelay relay3 = new LCMRelay(THREE);

		relay1.setDeviceClass(DeviceClassEnum.HVAC_COMPRESSOR);
		relay1.setEnrollmentCode(FIFTEEN);
		relay1.setStartMinutes(THIRTY);
		relay1.setEndMinutes(FORTY_FIVE);

		relay2.setDeviceClass(DeviceClassEnum.ELECTRIC_VEHICLE);
		relay2.setEnrollmentCode(ONE_THOUSAND_FOUR_HUNDRED_FOURTY);
		relay2.setStartMinutes(ONE_THOUSAND_FOUR_HUNDRED_FOURTY);
		relay2.setEndMinutes(ONE_THOUSAND_FOUR_HUNDRED_FOURTY);

		relay3.setDeviceClass(DeviceClassEnum.POOL_PUMP);
		relay3.setEnrollmentCode(ONE);
		relay3.setStartMinutes(TWO);
		relay3.setEndMinutes(THREE);

		// and add all three relays to our relayList
		relays.add(relay1);
		relays.add(relay2);
		relays.add(relay3);

		List<Property> properties = new ArrayList<Property>();
		DMConvertUtil.convertLCMRelayToProperty(relays, properties);

		// now the oldLCM and the newLCM should have all the same values
		// let`s check the old against the new...
		List<LCMRelay> newRelays = DMConvertUtil.convertPropertyToLCMRelay(properties);

		int quantityTestsPassed = 0;

		for (LCMRelay oldLcmRelay : relays)
		{
			for (LCMRelay newLcmRelay : newRelays)
			{
				if (oldLcmRelay.getRelay().equals(newLcmRelay.getRelay()))
				{
					assertEquals(oldLcmRelay.getDeviceClassValue(), newLcmRelay.getDeviceClassValue());
					quantityTestsPassed++;
					assertEquals(oldLcmRelay.getEnrollmentCode(), newLcmRelay.getEnrollmentCode());
					quantityTestsPassed++;
					assertEquals(oldLcmRelay.getStartMinutes(), newLcmRelay.getStartMinutes());
					quantityTestsPassed++;
					assertEquals(oldLcmRelay.getEndMinutes(), newLcmRelay.getEndMinutes());
					quantityTestsPassed++;
				}
			}
		}

		assertEquals(quantityTestsPassed, TWELVE);
	}

	@Test
	public void testExecuteDemandRead()
	{

		// successful test case for a Meter
		DemandReadAction action = new DemandReadAction(ONE, true, true, null);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER, new BigInteger("23001806"),
				"GAS 1806"));
		// gas meter: flexnetID: 23001806, MeterID: GAS 1806

		// ########## water ############
		// action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER, new BigInteger("13000658"),
		// "9122110529"));
		// action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER, new BigInteger("234561299"),
		// "234561299M"));

		// FlexNet ID: 13281780
		// Meter ID: B100421050951

		// FlexNet ID: 13000658
		// Meter ID: 9122110529

		InquiryActionRequest request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);

		TestBaseUtil.assertResponse(insertResponse);

		// action = new DemandReadPingAction();
		// action.setId(ONE);
		// action.setOnDemand(true);
		// action.setActionTypeName(DemandReadPingAction.ACTION);
		// // action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER, new BigInteger("13000658"),
		// // "9122110529"));
		// action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER, new BigInteger("234561299"),
		// "234561299M"));
		//
		// //FlexNet ID: 13281780
		// //Meter ID: B100421050951
		//
		// //FlexNet ID: 13000658
		// //Meter ID: 9122110529
		//
		// request =
		// new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		//
		// insertResponse = getActionBCL().applyDeviceToAction(request);
		//
		// TestBaseUtil.assertResponse(insertResponse);

		// Demand Response Meter
		// only Meters and Enteks will actually be sent OTA
		//
		// successful test case for a Entek LCM
		// action = new DemandReadPingAction();
		// action.setId(ONE);
		// action.setOnDemand(true);
		// action.setActionTypeName(DemandReadPingAction.ACTION);
		// action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER, new BigInteger("234561299"),
		// "234561299M"));
		//
		//
		// request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE),
		// null);
		//
		// insertResponse = getActionBCL().applyDeviceToAction(request);
		// TestBaseUtil.assertResponse(insertResponse);

		// Demand Response Meter
		// only Meters and Enteks will actually be sent OTA
		//
		// Fail test case for a HAN device
		// demandResponse = new DemandResponseEventAction();
		// demandResponse.setId(ONE);
		// demandResponse.setActionTypeName(DemandResponseEventAction.ACTION);
		// demandResponse.setIsMonitored(Boolean.TRUE);
		// demandResponse.setOnDemand(Boolean.TRUE);
		// demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID,
		// OTA_HAN_DEVICE_ID));
		//
		//
		// request = new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE),
		// null);
		//
		// insertResponse = getActionBCL().applyDeviceToAction(request);
		// TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute demand response.
	 */
	@Test
	public void testExecuteDemandResponse()
	{
		// Demand Response Meter
		// only Meters and Enteks will actually be sent OTA
		//
		// successful test case for a Meter
		DemandResponseEventAction demandResponse = new DemandResponseEventAction(ONE, true, true, null);
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		demandResponse.setActionTime(c.getTime());

		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));
		demandResponse.setEnrollmentCode(0);
		demandResponse.setDemandResponseDuration(FIVE);
		demandResponse.setRandomizeStart(Boolean.TRUE);
		demandResponse.setRandomizeEnd(Boolean.FALSE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);
		demandResponse.addDeviceClass(ALL);

		InquiryActionRequest request =
				new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Demand Response Meter
		// only Meters and Enteks will actually be sent OTA
		//
		// successful test case for a Entek LCM
		demandResponse = new DemandResponseEventAction(ONE, true, true, null);
		c = new GregorianCalendar();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);
		demandResponse.setActionTime(c.getTime());

		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID,
				OTA_ENTEK_DEVICE_ID));
		demandResponse.setEnrollmentCode(0);
		demandResponse.setDemandResponseDuration(FIVE);
		demandResponse.setRandomizeStart(Boolean.TRUE);
		demandResponse.setRandomizeEnd(Boolean.FALSE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);
		demandResponse.addDeviceClass(DeviceClassEnum.EXTERIOR_LIGHTING.getDeviceClassDescription());
		demandResponse.addDeviceClass(DeviceClassEnum.HVAC_COMPRESSOR.getDeviceClassDescription());
		demandResponse.addDeviceClass(DeviceClassEnum.POOL_PUMP.getDeviceClassDescription());
		demandResponse.addDeviceClass(DeviceClassEnum.SIMPLE_MISC.getDeviceClassDescription());
		demandResponse.addDeviceClass(DeviceClassEnum.IRRIGATION_PUMP.getDeviceClassDescription());
		demandResponse.addDeviceClass(DeviceClassEnum.WATER_HEATER.getDeviceClassDescription());

		request = new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Demand Response Meter
		// only Meters and Enteks will actually be sent OTA
		//
		// Fail test case for a HAN device
		demandResponse = new DemandResponseEventAction(ONE, true, true, null);
		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID,
				OTA_HAN_DEVICE_ID));
		demandResponse.setEnrollmentCode(0);
		demandResponse.setDemandResponseDuration(FIVE);
		demandResponse.setRandomizeStart(Boolean.TRUE);
		demandResponse.setRandomizeEnd(Boolean.FALSE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);

		demandResponse.setDeviceClasses(new ArrayList<String>());
		request = new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute cancel demand response.
	 */
	@Test
	public void testExecuteCancelDemandResponse()
	{
		// First run a Demand Response
		// so we can cancel it...
		//
		//

		CancelDemandResponseEventAction cancelAction =
				new CancelDemandResponseEventAction(USER, true, true, 1, new Device(OTA_METER_FLEXNET_ID,
						DeviceTypeEnum.ELECTRIC_METER));
		cancelAction.setId(ONE);

		InquiryActionRequest request =
				new InquiryActionRequest(cancelAction, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().addDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

	}

	/**
	 * Test execute demand response.
	 */
	@Test
	public void testExecuteDemandResponseStatus()
	{
		// Demand Response Status OnDemand
		// Electric Meter
		// Success case - this is valid for Meters, connected HAN devices, and Entek LCMs
		GetDemandResponseEventStatusAction demandResponse = new GetDemandResponseEventStatusAction(ONE, true, true);
		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		InquiryActionRequest request =
				new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Demand Response Status OnDemand
		// HAN Device
		// Success case - this is valid for Meters, connected HAN devices, and Entek LCMs
		demandResponse = new GetDemandResponseEventStatusAction(ONE, true, true);
		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID,
				OTA_HAN_DEVICE_ID));

		request = new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Demand Response Status OnDemand
		// Entek LCM
		// Success case - this is valid for Meters, connected HAN devices, and Entek LCMs
		demandResponse = new GetDemandResponseEventStatusAction(ONE, true, true);
		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID,
				OTA_ENTEK_DEVICE_ID));

		request =
				new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute connect han.
	 */
	@Test
	public void testExecuteConnectHan()
	{
		// Success case
		// this is only valid for disconnected HAN devices, not Meters or Entek LCMs
		// HAN device
		ConnectHanDeviceAction action = new ConnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setConfiguration(new HanDeviceConfiguration());
		((HanDevice)action.getFirstDevice()).getConfiguration().setInstallCode(INSTALL_CODE);
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(null);
		((HanDevice)action.getFirstDevice()).getRadio().addDevice(
				new Device(OTA_METER_DEVICE_ID, new Radio(OTA_METER_FLEXNET_ID), DeviceTypeEnum.ELECTRIC_METER));

		InquiryActionRequest request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Not find meter
		action = new ConnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setConfiguration(new HanDeviceConfiguration());
		((HanDevice)action.getFirstDevice()).getConfiguration().setInstallCode(INSTALL_CODE);
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(null);
		((HanDevice)action.getFirstDevice()).getRadio().addDevice(
				new Device(OTA_METER_DEVICE_ID, new Radio(OTA_METER_FLEXNET2_ID), DeviceTypeEnum.ELECTRIC_METER));

		request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Exception call service bcf
		action = new ConnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_METER_FLEXNET2_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setConfiguration(new HanDeviceConfiguration());
		((HanDevice)action.getFirstDevice()).getConfiguration().setInstallCode(INSTALL_CODE);
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(null);
		((HanDevice)action.getFirstDevice()).getRadio().addDevice(
				new Device(OTA_METER_DEVICE_ID, new Radio(OTA_METER_FLEXNET_ID), DeviceTypeEnum.ELECTRIC_METER));

		request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Exception convert to hex
		action = new ConnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_METER_FLEXNET2_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setConfiguration(new HanDeviceConfiguration());
		((HanDevice)action.getFirstDevice()).getConfiguration().setInstallCode(INSTALL_CODE_ERROR);
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(null);
		((HanDevice)action.getFirstDevice()).getRadio().addDevice(
				new Device(OTA_METER_DEVICE_ID, new Radio(OTA_METER_FLEXNET_ID), DeviceTypeEnum.ELECTRIC_METER));

		request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// // Fail case
		// // this is only valid for connected HAN devices, not Meters or Entek LCMs
		// // Meter
		// action = new ConnectHanDeviceAction(ONE, true, true);
		// action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
		// OTA_METER_DEVICE_ID));
		// action.getFirstDevice().getRadio().addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER,
		// OTA_METER_FLEXNET_ID,
		// OTA_METER_DEVICE_ID))
		//
		// request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		// request.setTenant(DM_TENANT);
		//
		// insertResponse = getActionBCL().applyDeviceToAction(request);
		// TestBaseUtil.assertResponse(insertResponse);

		// // Fail case
		// // this is only valid for connected HAN devices, not Meters or Entek LCMs
		// // Entek LCM
		// action = new ConnectHanDeviceAction(ONE, true, true);
		// action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID, OTA_ENTEK_DEVICE_ID));
		//
		// request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		// request.setTenant(DM_TENANT);
		//
		// insertResponse = getActionBCL().applyDeviceToAction(request);
		// TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute dis connect han.
	 */
	@Test
	public void testExecuteDisconnectHan()
	{
		// Success case
		// this is only valid for connected HAN devices, not Meters or Entek LCMs
		// HAN device
		DisconnectHanDeviceAction action = new DisconnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(OTA_METER_FLEXNET_ID);
		InquiryActionRequest request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Exception
		action = new DisconnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_METER_FLEXNET2_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(OTA_METER_FLEXNET_ID);
		request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		//
		action = new DisconnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(null);
		request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Fail case
		// this is only valid for connected HAN devices, not Meters or Entek LCMs
		// Meter
		action = new DisconnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));
		request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Fail case
		// this is only valid for connected HAN devices, not Meters or Entek LCMs
		// Entek LCM
		action = new DisconnectHanDeviceAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID, OTA_ENTEK_DEVICE_ID));
		request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	@Test
	public void testExecuteGetDemandResponseSetupStatus()
	{
		GetDemandResponseSetupStatusAction getDemandResponseSetup =
				new GetDemandResponseSetupStatusAction(ONE, true, true);
		getDemandResponseSetup.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID,
				OTA_ENTEK_DEVICE_ID));
		getDemandResponseSetup.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));

		LCMRelay lcmRelay = new LCMRelay(ONE);
		lcmRelay.setEnrollmentCode(ONE);
		lcmRelay.setStartMinutes(ONE);
		lcmRelay.setEndMinutes(ONE);
		lcmRelay.setDeviceClass(DeviceClassEnum.HVAC_COMPRESSOR);

		LCMRelay lcmRelay2 = new LCMRelay(TWO);
		lcmRelay2.setEnrollmentCode(ONE);
		lcmRelay2.setStartMinutes(ONE);
		lcmRelay2.setEndMinutes(ONE);
		lcmRelay2.setDeviceClass(DeviceClassEnum.HVAC_COMPRESSOR);

		LCMRelay lcmRelay3 = new LCMRelay(ONE);
		lcmRelay3.setEnrollmentCode(ONE);
		lcmRelay3.setStartMinutes(ONE);
		lcmRelay3.setEndMinutes(ONE);
		lcmRelay3.setDeviceClass(DeviceClassEnum.HVAC_COMPRESSOR);

		getDemandResponseSetup.setLcmRelays(new ArrayList<LCMRelay>());
		getDemandResponseSetup.getLcmRelays().add(lcmRelay);
		getDemandResponseSetup.getLcmRelays().add(lcmRelay2);
		getDemandResponseSetup.getLcmRelays().add(lcmRelay3);

		InquiryActionRequest request =
				new InquiryActionRequest(getDemandResponseSetup, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// sucess handevice
		getDemandResponseSetup = new GetDemandResponseSetupStatusAction(ONE, true, true);
		getDemandResponseSetup.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID,
				OTA_HAN_DEVICE_ID));
		getDemandResponseSetup.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		getDemandResponseSetup.setEnrollmentCode(ONE);
		getDemandResponseSetup.setStartMinutes(ONE);
		getDemandResponseSetup.setEndMinutes(ONE);

		request =
				new InquiryActionRequest(getDemandResponseSetup, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

	}

	/**
	 * Test execute connect status han.
	 */
	@Test
	public void testExecuteConnectStatusHan()
	{
		// Success case
		// this is available for Meters and HAN devices but not available for Entek LCMs
		// HAN device
		GetHanConnectionStatusAction action = new GetHanConnectionStatusAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(OTA_METER_FLEXNET_ID);

		InquiryActionRequest request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Success case
		// this is available for Meters and HAN devices but not available for Entek LCMs
		// Meter
		action = new GetHanConnectionStatusAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Exception
		action = new GetHanConnectionStatusAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET2_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Fail case
		// this is available for Meters and HAN devices but not available for Entek LCMs
		// Entek LCM
		action = new GetHanConnectionStatusAction(ONE, true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID, OTA_ENTEK_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test send han text message.
	 */
	@Test
	public void testSendHanTextMessage()
	{
		// HAN Device
		// Success case - this is valid for Meters, connected IHD and PCT, but not LCM or Entek LCMs
		SendHanTextMessageAction action = new SendHanTextMessageAction(ONE, true, true, new Date());
		action.setTextMessage(SEND_HAN_TEXT);
		action.setDurationHANTextMessage(TWO);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));
		((HanDevice)action.getFirstDevice()).setElectricMeterFlexNetId(OTA_METER_FLEXNET_ID);

		InquiryActionRequest request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Electric Meter
		// Success case - this is valid for Meters, connected IHD and PCT, but not LCM or Entek LCMs
		action = new SendHanTextMessageAction(ONE, true, true, new Date());
		action.setTextMessage(SEND_HAN_TEXT);
		action.setDurationHANTextMessage(TWO);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Exception calling bcf service
		// Electric Meter
		action = new SendHanTextMessageAction(ONE, true, true, new Date());
		action.setTextMessage(SEND_HAN_TEXT);
		action.setDurationHANTextMessage(TWO);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET2_ID,
				OTA_METER_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Electric Meter
		// Fail case - this is valid for Meters, connected IHD and PCT, but not LCM or Entek LCMs
		action = new SendHanTextMessageAction(ONE, true, true, new Date());
		action.setTextMessage(SEND_HAN_TEXT);
		action.setDurationHANTextMessage(TWO);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID,
				OTA_ENTEK_DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test send han text message.
	 */
	@Test
	public void testCancelHanTextMessage()
	{
		CancelSendHanTextMessageAction cancelAction =
				new CancelSendHanTextMessageAction(USER, true, true, ONE, new Device(METER_FLEXNET_ID,
						DeviceTypeEnum.ELECTRIC_METER));
		cancelAction.addDevice(new Device(METER_FLEXNET_ID, DeviceTypeEnum.ELECTRIC_METER));

		InquiryActionRequest inquiryActionRequest =
				new InquiryActionRequest(cancelAction, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		inquiryActionRequest.setTenant(DM_TENANT);
		inquiryActionRequest.setProcessId(TWO);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(inquiryActionRequest);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test execute demand reset.
	 */
	@Test
	public void testExecuteDemandReset()
	{
		// Only valid for Meters, not HAN Devices or Entek LCMs
		// Demand Reset meter
		// success case
		DMAction dmAction = new DemandResetEventAction(ONE, true, true, null);
		dmAction.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));

		InquiryActionRequest request =
				new InquiryActionRequest(dmAction, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Only valid for Meters, not HAN Devices or Entek LCMs
		// Entek LCM
		// Fail case
		dmAction = new DemandResetEventAction(ONE, true, true, null);
		dmAction.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID, OTA_ENTEK_DEVICE_ID));

		request = new InquiryActionRequest(dmAction, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// Only valid for Meters, not HAN Devices or Entek LCMs
		// HAN Device
		// Fail case
		dmAction = new DemandResetEventAction(ONE, true, true, null);
		dmAction
				.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID, OTA_HAN_DEVICE_ID));

		request = new InquiryActionRequest(dmAction, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test insert device from file to action.
	 */
	@Test
	public void testInsertDeviceFromFileToAction()
	{
		// insert action instance
		ActionRequest actionRequest =
				new ActionRequest(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)),
						TestBaseUtil.createUserContext());
		actionRequest.setFileName(createFile(DEVICE_ID + COMMA + METER_ID_2));
		actionRequest.setIdFileType(PropertyEnum.METER_ID);
		actionRequest.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().insertDeviceFromFileToAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);

		actionRequest =
				new ActionRequest(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)),
						TestBaseUtil.createUserContext());
		actionRequest.setFileName(createFile(DEVICE_ID + COMMA + METER_ID_2));
		actionRequest.setIdFileType(PropertyEnum.NETWORK_ADDRESS);
		actionRequest.setTenant(DM_TENANT);

		insertResponse = getActionBCL().insertDeviceFromFileToAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);

		actionRequest =
				new ActionRequest(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)),
						TestBaseUtil.createUserContext());
		actionRequest.setFileName(createFile(ELECTRIC_FLEXNET_ID + COMMA + FLEXNET_ID_1002));
		actionRequest.setIdFileType(PropertyEnum.NETWORK_ADDRESS);
		actionRequest.setTenant(DM_TENANT);

		insertResponse = getActionBCL().insertDeviceFromFileToAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);

		actionRequest =
				new ActionRequest(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)),
						TestBaseUtil.createUserContext());
		actionRequest.setFileName(createFile(DEVICE_ID + COMMA + METER_ID_2));
		actionRequest.setIdFileType(PropertyEnum.DEVICE_ID);
		actionRequest.setTenant(DM_TENANT);

		insertResponse = getActionBCL().insertDeviceFromFileToAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test import han device.
	 */
	@Test
	public void testExecuteImportHanDevice()
	{
		// import for file
		ActionRequest actionRequest =
				new ActionRequest(new ImportHanDeviceAction(Boolean.TRUE, Boolean.TRUE),
						TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		actionRequest.setHanDevicesFile(createFileImportHanDevice());

		actionRequest.setTags(new ArrayList<Tag>());
		actionRequest.getTags().add(new Tag(ONE));
		actionRequest.setTenant(DM_TENANT);

		System.out.println("-------------------begin import from file----------------");
		InternalResponse insertResponse = getActionBCL().importHanDeviceFromAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);
		System.out.println("--------------------End inport from file------------------");

		DMAction dmAction = new ImportHanDeviceAction(true, true);
		HanDevice device = (HanDevice)TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE);
		device.setConfiguration(new HanDeviceConfiguration("123456789123456789"));
		device.setDeviceModel(new DeviceModel("111", "321"));
		dmAction.addDevice(device);

		// import using the application

		actionRequest = new ActionRequest(dmAction, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE), DM_TENANT);

		insertResponse = getActionBCL().importHanDeviceFromAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);

	}

	@Test
	public void testExecuteRetryImportHanDevice()
	{
		DMAction dmAction = new RetryImportHanDeviceAction(true, true);
		dmAction.setProcessId(1);

		// import using the application

		ActionRequest actionRequest =
				new ActionRequest(dmAction, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE), DM_TENANT);

		InternalResponse insertResponse = getActionBCL().importHanDeviceFromAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);

	}

	/**
	 * Test insert action.
	 */
	@Test
	public void testInsertAction()
	{
		// Fail insertAction
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, INSERT_ACTION);
		ActionRequest actionRequest =
				new ActionRequest(new DMAction(new Group(ONE)), TestBaseUtil.createUserContext());
		InternalResultsResponse<DMAction> actionResponse = getActionBCL().insertAction(actionRequest);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());
		resetMocksToActionArea();

		// fail insertGroup
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, INSERT_GROUPS_TO_ACTION);
		actionResponse = getActionBCL().insertAction(actionRequest);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());
		resetMocksToActionArea();

		// Success with group
		actionResponse = getActionBCL().insertAction(actionRequest);
		TestBaseUtil.assertResultResponse(actionResponse);

		// Fail insertTag
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, INSERT_TAGS_TO_ACTION);
		actionRequest = new ActionRequest(new DMAction(new Tag(ONE)), TestBaseUtil.createUserContext());
		actionResponse = getActionBCL().insertAction(actionRequest);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());
		resetMocksToActionArea();

		// Success with tag
		actionResponse = getActionBCL().insertAction(actionRequest);
		TestBaseUtil.assertResultResponse(actionResponse);

	}

	/**
	 * Test insert groupset to action.
	 */
	@Test
	public void testInsertGroupToAction()
	{
		// Success Group
		InternalResponse actionResponse =
				getActionBCL().insertGroupSetToAction(
						new ActionRequest(new DMAction(new Group(ONE, GroupTypeEnum.OPERATIONS)), TestBaseUtil
								.createUserContext()));
		TestBaseUtil.assertResponse(actionResponse);

		// Success Tag
		actionResponse =
				getActionBCL().insertGroupSetToAction(
						new ActionRequest(new DMAction(new Tag(ONE)), TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(actionResponse);

	}

	/**
	 * Test apply device to action.
	 */
	@Test
	public void testApplyDeviceToAction()
	{
		// Action with Device
		DMAction action = new GetDemandResponseEventStatusAction(true, true);
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID, METER_DEVICE_ID));

		InquiryActionRequest request =
				new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// select All Devices using fetch inside
		action = new DemandResetEventAction(ONE, true, true, null);

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().addDeviceType(DeviceTypeEnum.GAS_METER);
		request.setPaginationAllSelected(false);
		request.setSelectionPaginationIds(new ArrayList<BigInteger>());
		request.getSelectionPaginationIds().add(new BigInteger("1001"));
		request.getSelectionPaginationIds().add(new BigInteger("1002"));
		request.getSelectionPaginationIds().add(new BigInteger("1003"));

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		action = new DemandResetEventAction(ONE, true, true, new Date());
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID, METER_DEVICE_ID));
		action.addDevicesExcluded(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, ELECTRIC_FLEXNET_ID,
				DEVICE_ID));

		request = new InquiryActionRequest(action, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test insert device to action.
	 */
	@Test
	public void testInsertDeviceToAction()
	{
		// fail device
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID, METER_DEVICE_ID));
		ActionRequest actionRequest = new ActionRequest(ea, TestBaseUtil.createUserContext());

		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, INSERT_DEVICES_TO_ACTION);
		InternalResponse insertResponse = getActionBCL().insertDevicesToAction(actionRequest);
		assertEquals(Status.ExceptionError, insertResponse.getStatus());
		resetMocksToActionArea();

		// Fail process_id
		actionRequest = new ActionRequest(ea, TestBaseUtil.createUserContext(), NINETY_NINE);
		setSituation(getActionBCL(), SituationsEnum.ERROR, IProcessBCL.class, FETCH_PROCESS_BY_ID);
		insertResponse = getActionBCL().insertDevicesToAction(actionRequest);
		resetMocksToActionArea();

		// Success
		ea.setOnDemand(Boolean.FALSE);
		actionRequest = new ActionRequest(ea, TestBaseUtil.createUserContext(), ONE);
		// parameter is required
		actionRequest.setProcessId(ONE);
		actionRequest.setIdFileType(PropertyEnum.FLEXNET_ID);

		// invalid id
		actionRequest.setUnreachableIds(new ArrayList<String>());
		actionRequest.getUnreachableIds().add("99");

		insertResponse = getActionBCL().insertDevicesToAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);

	}

	/**
	 * Test delete device from action.
	 */
	@Test
	public void testDeleteDeviceFromAction()
	{
		// Success
		ActionRequest actionRequest = new ActionRequest(new DMAction(), TestBaseUtil.createUserContext());
		InternalResponse insertResponse = getActionBCL().deleteDevicesFromAction(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);

	}

	/**
	 * Test delete groupset from action.
	 */
	@Test
	public void testDeleteGroupFromAction()
	{
		// Success Group
		InternalResponse actionResponse =
				getActionBCL().deleteGroupsFromAction(
						new ActionRequest(new DMAction(new Group(ONE, GroupTypeEnum.BILLING)), TestBaseUtil
								.createUserContext()));
		TestBaseUtil.assertResponse(actionResponse);

		// Success Tag
		actionResponse =
				getActionBCL().deleteGroupsFromAction(
						new ActionRequest(new DMAction(new Tag(ONE)), TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(actionResponse);
	}

	/**
	 * Test fetch action by id.
	 */
	@Test
	public void testFetchActionById()
	{
		// 0007a6ffff009304

		// String macString1 = "00:07:a6:ff:ff:00:93:04";
		// String macString2 = "00:07:a6:ff:ff:00:92:5b";
		// String macString3 = "00:07:a6:ff:ff:00:93:4f";
		// [07/06/2012 2:33:39 PM] SENSUS Jim McCrae: Here is a device you can connet to either meter:
		// [07/06/2012 2:33:59 PM] SENSUS Jim McCrae: Thermostat 0007a6ffff00934f
		// [07/06/2012 2:35:13 PM] SENSUS Jim McCrae: installation code 00934f03bfe7716d633d
		// BigInteger bigInt = new BigInteger("2153943262073679");
		// BigInteger macInt = DMConvertUtil.convertMacAddress(macString3);
		// System.out.println("macString3 = " + macInt.toString());
		// System.out.println("bigInt to macAddress = " + DMConvertUtil.convertMacAddress(bigInt));

		ActionRequest actionRequest = new ActionRequest(new DMAction(ONE), TestBaseUtil.createUserContext());
		// now fetch the action with that ID and let`s make sure it is found
		InternalResultsResponse<DMAction> actionResponse = getActionBCL().fetchActionById(actionRequest);
		TestBaseUtil.assertResponse(actionResponse);
	}

	/**
	 * Test abort action.
	 */
	@Test
	public void testAbortAction()
	{
		// fail
		ActionRequest request =
				new ActionRequest(new CancelDemandResponseEventAction(), TestBaseUtil.createUserContext());
		request.setProcessId(ONE);
		setSituation(getActionBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_PROCESS_BY_ID);

		InternalResponse actionResponse = getActionBCL().abortAction(request);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());

		resetMocksToActionArea();

		// Success abort process
		request =
				new ActionRequest(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)),
						TestBaseUtil.createUserContext());
		request.setProcessId(ONE);
		actionResponse = getActionBCL().abortAction(request);
		TestBaseUtil.assertResponse(actionResponse);
	}

	/**
	 * Test update action.
	 */
	@Test
	public void testUpdateAction()
	{
		// Fail delete group for update action
		ActionRequest actionRequest =
				new ActionRequest(new DMAction(new Group(ONE)), TestBaseUtil.createUserContext());
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, DELETE_GROUPS_FROM_ACTION);
		InternalResponse actionResponse = getActionBCL().updateAction(actionRequest);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());
		resetMocksToActionArea();

		// Fail insert group for update action
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, INSERT_GROUPS_TO_ACTION);
		actionResponse = getActionBCL().updateAction(actionRequest);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());
		resetMocksToActionArea();

		// Success with group
		actionResponse = getActionBCL().updateAction(actionRequest);
		TestBaseUtil.assertResponse(actionResponse);

		// Fail insert tag for update action
		actionRequest = new ActionRequest(new DMAction(new Tag(ONE)), TestBaseUtil.createUserContext());
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, DELETE_DEVICES_FROM_ACTION);
		actionResponse = getActionBCL().updateAction(actionRequest);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());
		resetMocksToActionArea();

		// Fail insert tag for update action
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class, INSERT_TAGS_TO_ACTION);
		actionResponse = getActionBCL().updateAction(actionRequest);
		assertEquals(Status.ExceptionError, actionResponse.getStatus());
		resetMocksToActionArea();

		// Success with tag
		actionResponse = getActionBCL().updateAction(actionRequest);
		TestBaseUtil.assertResponse(actionResponse);
	}

	/**
	 * Test insert devices opt out list.
	 */
	@Test
	public void testInsertDevicesOptOutList()
	{
		DMAction action = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID, METER_DEVICE_ID));
		ActionRequest actionRequest = new ActionRequest(action, TestBaseUtil.createUserContext());

		// Error Situation
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class);
		InternalResponse insertResponse = getActionBCL().insertDevicesOptOutList(actionRequest);
		assertEquals(Status.ExceptionError, insertResponse.getStatus());

		resetMocksToActionArea();

		// Success Situation
		insertResponse = getActionBCL().insertDevicesOptOutList(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test delete devices opt out list.
	 */
	@Test
	public void testDeleteDevicesOptOutList()
	{
		DMAction action = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		action.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID, METER_DEVICE_ID));
		ActionRequest actionRequest = new ActionRequest(action, TestBaseUtil.createUserContext());

		// Error Situation
		setSituation(getActionBCL(), SituationsEnum.ERROR, IActionDAC.class);
		InternalResponse insertResponse = getActionBCL().deleteDevicesOptOutList(actionRequest);
		assertEquals(Status.ExceptionError, insertResponse.getStatus());

		resetMocksToActionArea();

		// Success Situation
		insertResponse = getActionBCL().deleteDevicesOptOutList(actionRequest);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test get tamper detect timeout.
	 */
	@Test
	public void testGetTamperDetectTimeout()
	{
		// success case
		// this is valid for Entek LCMs only
		Date actionTime = Calendar.getInstance().getTime();
		GetTamperDetectTimerAction getTamperDetectTimeout = new GetTamperDetectTimerAction(Boolean.TRUE, Boolean.TRUE);
		getTamperDetectTimeout.setId(1);
		getTamperDetectTimeout.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, new BigInteger("50410023"),
				"2199"));

		getTamperDetectTimeout.setLcmRelays(new ArrayList<LCMRelay>());
		getTamperDetectTimeout.getLcmRelays().add(new LCMRelay(1));
		getTamperDetectTimeout.getLcmRelays().add(new LCMRelay(2));
		getTamperDetectTimeout.getLcmRelays().add(new LCMRelay(3));

		getTamperDetectTimeout.setActionTime(actionTime);

		InquiryActionRequest request =
				new InquiryActionRequest(getTamperDetectTimeout, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);
		request.setProcessId(TWO);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

		// device is not flexnet
		getTamperDetectTimeout = new GetTamperDetectTimerAction(Boolean.TRUE, Boolean.TRUE);
		getTamperDetectTimeout.setId(1);
		LCM lcmDevice =
				(LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM, new BigInteger("2153943262073407"), "LCM 923F");
		lcmDevice.setLcmTypeEnum(LCMTypeEnum.LCM);
		getTamperDetectTimeout.addDevice(lcmDevice);

		getTamperDetectTimeout.setLcmRelays(new ArrayList<LCMRelay>());
		getTamperDetectTimeout.getLcmRelays().add(new LCMRelay(1));
		getTamperDetectTimeout.getLcmRelays().add(new LCMRelay(2));
		getTamperDetectTimeout.getLcmRelays().add(new LCMRelay(3));

		getTamperDetectTimeout.setActionTime(actionTime);

		request =
				new InquiryActionRequest(getTamperDetectTimeout, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);
		request.setProcessId(TWO);

		insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);

	}

	/**
	 * Test setTamperDetectTimeout.
	 * Success case for setting Tamper Detect Timeout.
	 * This test case is for all 3 relays.
	 */
	@Test
	public void testSetTamperDetectTimeoutForThreeRelaysSuccess()
	{
		Date actionTime = Calendar.getInstance().getTime();

		// success case
		// for all 3 relays
		// setting all parameters (relayId, tamperDetectTimeout)
		// this is valid for Entek LCMs only
		SetTamperDetectTimerAction setTamperDetectTimeout =
				new SetTamperDetectTimerAction(Boolean.TRUE, Boolean.TRUE);
		setTamperDetectTimeout.setId(1);
		LCM entekLCM =
				(LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM, new BigInteger("50410022"), "2198");
		setTamperDetectTimeout.addDevice(entekLCM);
		setTamperDetectTimeout.setActionTime(actionTime);

		// pass only the relay which has demand response setup parameters
		ArrayList<LCMRelay> relayList = new ArrayList<LCMRelay>();

		// create all relays
		LCMRelay relayOne = new LCMRelay();
		LCMRelay relayTwo = new LCMRelay();
		LCMRelay relayThree = new LCMRelay();

		// add relays to the list and add the list to the entek LCM
		relayList.add(relayOne);
		relayList.add(relayTwo);
		relayList.add(relayThree);
		setTamperDetectTimeout.setLcmRelays(relayList);
		// entekLCM.setLcmRelays(relayList);

		// identify each relay
		relayOne.setRelay(1);
		relayTwo.setRelay(2);
		relayThree.setRelay(2);

		// set the test parameters required for this action
		relayOne.setTamperDetectTimer("" + THIRTY);
		relayTwo.setTamperDetectTimer("" + FIFTEEN);
		relayThree.setTamperDetectTimer("" + TWENTY);

		InquiryActionRequest request =
				new InquiryActionRequest(setTamperDetectTimeout, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);
		request.setProcessId(TWO);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test setTamperDetectTimeout.
	 * Success case for setting Tamper Detect Timeout.
	 * This test case is for one relay only.
	 */
	@Test
	public void testSetTamperDetectTimeoutForOneRelaySuccess()
	{
		Date actionTime = Calendar.getInstance().getTime();

		// success case
		// for relay number 2
		// setting all parameters (relayId, tamperDetectTimeout)
		// this is valid for Entek LCMs only
		SetTamperDetectTimerAction setTamperDetectTimeout =
				new SetTamperDetectTimerAction(ONE, Boolean.TRUE, Boolean.TRUE);
		setTamperDetectTimeout.setId(1);
		com.sensus.dm.elec.device.model.LCM entekLCM =
				(com.sensus.dm.elec.device.model.LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM, new BigInteger(
						"50410022"), "2198");
		setTamperDetectTimeout.addDevice(entekLCM);
		setTamperDetectTimeout.setActionTime(actionTime);

		// pass only the relay which has tamper detect timeout parameters
		ArrayList<LCMRelay> relayList = new ArrayList<LCMRelay>();
		LCMRelay relayTwo = new LCMRelay();
		relayList.add(relayTwo);
		setTamperDetectTimeout.setLcmRelays(relayList);
		// entekLCM.setLcmRelays(relayList);

		// set the test parameters required for this action
		relayTwo.setRelay(2);
		relayTwo.setTamperDetectTimer("" + FIFTEEN);

		InquiryActionRequest request =
				new InquiryActionRequest(setTamperDetectTimeout, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);
		request.setProcessId(TWO);

		InternalResponse insertResponse = getActionBCL().applyDeviceToAction(request);
		TestBaseUtil.assertResponse(insertResponse);
	}
}