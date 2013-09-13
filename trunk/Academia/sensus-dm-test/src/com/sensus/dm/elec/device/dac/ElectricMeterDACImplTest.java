package com.sensus.dm.elec.device.dac;

import static com.sensus.dm.common.util.TestBaseUtil.assertResponse;
import static com.sensus.dm.common.util.TestBaseUtil.assertResultResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.GeocodeLatLongTrunc;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.ElectricMeterTypeEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceConfiguration;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.PeakDemandOrderByEnum;
import com.sensus.dm.elec.device.model.RemoteConnectStatusEnum;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Class ElectricMeterDACImplTest.
 * 
 * @author QAT Global.
 */
public class ElectricMeterDACImplTest extends AbstractTestBaseDAC
{
	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Log LOG = LogFactory.getLog(ElectricMeterDACImplTest.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant CONST_BLANK_SPACE. */
	private static final String CONST_BLANK_SPACE = " ";

	/** The Constant CONST_DOT. */
	private static final String CONST_DOT = ".";

	/** The Constant CONST_MARK. */
	private static final String CONST_MARK = "###";

	/** The Constant CONST_0_DOT. */
	private static final String CONST_0_DOT = "0.";

	/** The Constant CONST_9999_DOT. */
	private static final String CONST_9999_DOT = "9999.";

	/** The Constant CONST_R. */
	private static final String CONST_R = "R";

	/** The Constant CONST_B. */
	private static final String CONST_B = "B";

	/** The Constant ELSTER_METER. */
	private static final String ELSTER_METER = "Elster C&I Meter";

	/** The Constant FIRMWARE_FLEXNET. */
	private static final String FIRMWARE_FLEXNET = "005.000.000.000.1";

	/** The Constant FIRMWARE_FLEXNET_005_010_007_004_1. */
	private static final String FIRMWARE_FLEXNET_005_010_007_004_1 = "005.010.007.004.1";

	/** The Constant FIRMWARE_METER. */
	private static final String FIRMWARE_METER = "005.000";

	/** The Constant HAN_DEVICE_FLEXNET_ID. */
	private static final String HAN_DEVICE_FLEXNET_ID = "2153943262073613";

	/** The Constant HAN_DEVICE_FLEXNET_ID_2153943262073425. */
	private static final String HAN_DEVICE_FLEXNET_ID_2153943262073425 = "2153943262073425";

	/** The Constant HAN_DEVICE_FLEXNET_ID_2153943262073700. */
	private static final String HAN_DEVICE_FLEXNET_ID_2153943262073700 = "2153943262073700";

	/** The Constant INVALID_DEVICE_ID. */
	private static final String INVALID_DEVICE_ID = "InvalidDeviceId";

	/** The Constant LIST_SIZE. */
	private static final String LIST_SIZE = "### list size: ";

	/** The Constant RX_TX_ICON_METER. */
	private static final String RX_TX_ICON_METER = "Rx / Tx iCon Meter";

	/** The Constant TOTAL_AVAIABLE. */
	private static final String TOTAL_AVAIABLE = "### total avaiable: ";

	/** The Constant LON_TRUNC_TEST_ONE. */
	private static final double LON_TRUNC_TEST_ONE = -116.7;

	/** The Constant LAT_TRUNC_TEST_TWO. */
	private static final double LAT_TRUNC_TEST_TWO = 32.7;

	/** The Constant LAT_TRUNC_TEST_ONE. */
	private static final double LAT_TRUNC_TEST_ONE = 32.8;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test firmware conversion fetched.
	 */
	@Test
	public void testFirmwareConversionFetched()
	{
		// Success situation

		String value = FIRMWARE_FLEXNET_005_010_007_004_1;

		String[] strValues = StringUtils.split(value, CONST_DOT);

		StringBuffer sb = new StringBuffer();

		if (value.substring(value.length() - 1, value.length()).equalsIgnoreCase(ONE_STR))
		{
			sb.append(CONST_B);
		}
		else
		{
			sb.append(CONST_R);
		}

		LOG.info(CONST_MARK);
		for (String str : strValues)
		{
			sb.append(Integer.valueOf(str)).append(CONST_DOT);
		}

		LOG.info(CONST_MARK + sb.substring(0, sb.length() - 1).replaceAll(CONST_9999_DOT, CONST_0_DOT));
		LOG.info(CONST_MARK + sb.substring(0, sb.length() - 1).replaceAll(CONST_9999_DOT, CONST_0_DOT));

		if (sb.substring(sb.length() - THREE, sb.length()).equals(".1."))
		{
			LOG.info(CONST_MARK + CONST_B + sb.substring(0, sb.length() - THREE));
		}
		else
		{
			LOG.info(CONST_MARK + CONST_R + sb.substring(0, sb.length() - THREE));
		}

		LOG.info(CONST_MARK + sb.substring(0, sb.length() - THREE));

	}

	/**
	 * Test firmware conversion.
	 */
	@Test
	public void testFirmwareConversion()
	{
		// Success situation - string firmwareFlexnet = "005.000.000.000.1";
		String firmwareFlexnet = "50.02.1";

		StringBuilder sb = new StringBuilder();

		String[] values = StringUtils.split(firmwareFlexnet, CONST_DOT);

		LOG.info(CONST_MARK);
		for (String str : values)
		{
			sb.append(StringUtils.leftPad(str, THREE, "0")).append(CONST_DOT);
		}

		LOG.info(sb.substring(0, sb.length() - 1));

	}

	/**
	 * Test fetch all han devices.
	 */
	@Test
	public void testFetchAllHanDevices()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		HanDeviceSearch hanDeviceSearch = new HanDeviceSearch();
		hanDeviceSearch.addHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
		hanDeviceSearch.addHanDeviceTypeEnum(HanDeviceTypeEnum.THERMOSTAT);
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setHanDeviceSearch(hanDeviceSearch);

		InternalResultsResponse<HanDevice> response = getElectricMeterDAC().fetchAllHanDevices(request);
		assertResponse(response);

		HanDevice hanDevice = (HanDevice)TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE);
		hanDeviceSearch.setHanDevice(hanDevice);

		request.getDeviceSearch().setHanDeviceSearch(hanDeviceSearch);

		response = getElectricMeterDAC().fetchAllHanDevices(request);
		assertResponse(response);
	}

	/**
	 * Test fetch all lcm.
	 */
	@Test
	public void testFetchAllLCM()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.setDeviceSearch(new DeviceSearch());
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));
		LCMSearch lcmSearch = new LCMSearch();
		// Install = Flexnet LCM / Unknown, Joined, Unjoined, Pending Connect = LCM
		lcmSearch.addLcmLifecycleStateEnum(LCMLifecycleStateEnum.INSTALLED);
		lcmSearch.addLcmLifecycleStateEnum(LCMLifecycleStateEnum.UNJOINED);
		request.getDeviceSearch().setLcmSearch(lcmSearch);
		InternalResultsResponse<LCM> response = getElectricMeterDAC().fetchAllLCM(request);
		assertNotNull(response.getResultsList());

		request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setLcmSearch(lcmSearch);

		System.out.println(new Date());
		response = getElectricMeterDAC().fetchAllLCM(request);
		System.out.println(new Date());
		assertResponse(response);

		LCM lcm = (LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM);
		lcmSearch.setLcm(lcm);
		request.getDeviceSearch().setLcmSearch(lcmSearch);

		response = getElectricMeterDAC().fetchAllLCM(request);
		assertNotNull(response.getResultsList());

		// Filter and Sort by Alarms
		lcmSearch = new LCMSearch();
		lcmSearch.addAlarmEnum(AlarmEnum.CUT_WIRE);
		lcmSearch.addAlarmEnum(AlarmEnum.BACK_FLOW);
		request.getDeviceSearch().setLcmSearch(lcmSearch);
		request.getSortExpressions().clear();
		request.addSortExpressions(new SortExpression(DeviceColumnEnum.ALARM.getValue(), Direction.Ascending));
		request.addSortExpressions(new SortExpression(DeviceColumnEnum.ALARM_TIME.getValue(), Direction.Ascending));
		response = getElectricMeterDAC().fetchAllLCM(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices.
	 */
	@Test
	public void testFetchAllDevices()
	{
		// Success situation
		testFetchAllDevicesFirmware();
		testFetchAllDevicesIds();
		testFetchAllDevicesGroup();
		testFetchAllDevicesTag();
		testFetchAllDevicesCityAddressZip();
		testFetchAllDevicesDeviceTypes();
		testFetchAllDevicesProcessId();
		testFetchAllDevicesFirmwareFlexnet();
		testFetchAllDevicesInstallDate();
		testFetchAllDevicesLyfecycleState();
		testFetchAllDevicesRemoteDistonnect();
		testFetchAllToMap();
	}

	/**
	 * Testf fetch device by id.
	 */
	@Test
	public void testFetchLCMById()
	{
		// Success situation - filtering by meter id (clientEndpointId)

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.setTenant(TestBaseUtil.createTenant());
		request.addDevice(new Device(LCM_DEVICE_ID, DeviceTypeEnum.LCM));

		InternalResultsResponse<Device> response = getElectricMeterDAC().fetchLCMById(request);

		assertResponse(response);

		System.out.println(response.getResultsList());

		// Success situation - filtering by rep id (flexnet id)
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, new BigInteger(LCM_FLEXNET_ID), null));

		response = getElectricMeterDAC().fetchLCMById(request);

		assertResponse(response);

		System.out.println(response.getResultsList());
	}

	/**
	 * Test fetch meter by id.
	 */
	@Test
	public void testFetchElectricMeterById()
	{
		// Success Situation - filtering by meter id (clientEndpointId)

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.setTenant(TestBaseUtil.createTenant());
		request.addDevice(new Device(DEVICE_ID));

		InternalResultsResponse<Device> response = getElectricMeterDAC().fetchElectricMeterById(request);

		assertResponse(response);

		System.out.println(response.getResultsList());

		// Success Situation - filtering by rep id (flexnet id)
		request = new DeviceRequest(new Device(new Radio(new BigInteger(FLEXNET_ID))), TestBaseUtil.createTenant());

		response = getElectricMeterDAC().fetchElectricMeterById(request);

		assertResponse(response);

		System.out.println(response.getResultsList());

		// Error Situation - filtering by meter id (clientEndpointId)

		request = new DeviceRequest(new Device(INVALID_DEVICE_ID), TestBaseUtil.createTenant());

		response = getElectricMeterDAC().fetchElectricMeterById(request);

		assertResponse(response);
	}

	/**
	 * Test fetch han devices by device.
	 */
	@Test
	public void testFetchHanDeviceById()
	{
		// Success Situation - FlexNetId
		DeviceRequest request =
				new DeviceRequest(new Device(new BigInteger(HAN_DEVICE_FLEXNET_ID), DeviceTypeEnum.HAN_DEVICE),
						TestBaseUtil.createTenant());

		InternalResultsResponse<Device> response = getElectricMeterDAC().fetchHanDeviceById(request);
		assertResponse(response);

		// Success Situation - clientEndPointId
		request =
				new DeviceRequest(new Device("RepId:50402439", DeviceTypeEnum.HAN_DEVICE), TestBaseUtil.createTenant());

		response = getElectricMeterDAC().fetchHanDeviceById(request);
		assertResponse(response);

		// Success Situation - FlexNetId
		request = new DeviceRequest(new Device("IHD_936B"), TestBaseUtil.createTenant());

		response = getElectricMeterDAC().fetchHanDeviceById(request);
		assertResponse(response);

	}

	/**
	 * Test fetch device type id by device.
	 */
	@Test
	public void testFetchDeviceTypeIdByDevice()
	{
		// Success Situation

		Device device = new Device(new Radio(CUSTOMER_ID));
		device.setDeviceId(HAN_DEVICE_ID);

		InternalResultsResponse<Integer> response =
				getElectricMeterDAC().fetchDeviceTypeIdByDevice(new DeviceRequest(device));

		assertResponse(response);

		System.out.println(response.getResultsList());

	}

	/**
	 * Test fetch all devices life cycle.
	 */
	@Test
	public void testFetchAllDevicesLifeCycle()
	{
		// Success Situation - filtering all Install
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();

		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
		electricMeterSearch.addElectricMeterLifecycleStateEnum(ElectricMeterLifecycleStateEnum.INSTALLED);
		request.setDeviceSearch(new DeviceSearch(electricMeterSearch));

		request.getSortExpressions().add(
				new SortExpression(DeviceColumnEnum.FLEXNET_ID.getValue(), Direction.Ascending));

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);

		assertResultResponse(response);

		// Success Situation - filtering only Not Assigned
		request.getDeviceSearch().getElectricMeterSearch()
				.setElectricMeterLifecycleStateEnumList(new ArrayList<ElectricMeterLifecycleStateEnum>());
		request.getDeviceSearch().getElectricMeterSearch()
				.addElectricMeterLifecycleStateEnum(ElectricMeterLifecycleStateEnum.NOT_ASSIGNED);

		request.getSortExpressions().add(
				new SortExpression(DeviceColumnEnum.FLEXNET_ID.getValue(), Direction.Ascending));

		response = getElectricMeterDAC().fetchAllElectricMeter(request);

		assertResponse(response);
	}

	/**
	 * Test fetch all devices quarantine.
	 */
	@Test
	public void testFetchAllDevicesQuarantine()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		ElectricMeter electricMeter = new ElectricMeter();
		electricMeter.setQuarantine(true);
		request.setDeviceSearch(new DeviceSearch(new ElectricMeterSearch(electricMeter)));

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch lifecycle states.
	 */
	@Test
	public void testFetchLifecycleStates()
	{
		// Success Situation

		CachedResultsResponse<LifecycleState> response =
				getElectricMeterDAC().fetchLifecycleStates(new DeviceRequest());

		assertNotNull(response);
		assertNotNull(response.getFirstResult());
		assertEquals(THREE, response.getResultsList().size());

		for (LifecycleState lifecycleState : response.getResultsList())
		{
			assertNotNull(lifecycleState.getCode());
			assertNotNull(lifecycleState.getDescription());
		}
	}

	/**
	 * Tests fetching all HAN device by meter.
	 */
	@Test
	public void testfetchAllHanDevicesByMeter()
	{
		// Success Situation

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, new BigInteger(FLEXNET_ID), null));

		InternalResultsResponse<Device> response = getElectricMeterDAC().fetchAllHanDevicesByMeter(request);

		assertNotNull(response.getResultsList());

		request = new DeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER,
				new BigInteger(String.valueOf(Integer.parseInt(FLEXNET_ID) + 1)), null));

		response = getElectricMeterDAC().fetchAllHanDevicesByMeter(request);

		executeAssertsForHanDevice(response);

		request = new DeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER,
				new BigInteger(String.valueOf(Integer.parseInt(FLEXNET_ID) + 2)), null));

		response = getElectricMeterDAC().fetchAllHanDevicesByMeter(request);

		executeAssertsForHanDevice(response);
	}

	/**
	 * Test fetch all manufacture by device sub type.
	 */
	@Test
	public void testFetchAllManufactureByDeviceSubType()
	{
		// Success Situation

		Device device = null;

		for (int i = 0; i < THREE; i++)
		{
			if (i % 2 == 0)
			{
				device = new HanDevice(HanDeviceTypeEnum.enumForValue(i));
			}
			else
			{
				device = new LCM(LCMTypeEnum.LCM);
			}

			InternalResultsResponse<Device> deviceResponse = fetchManufactureHanDeviceType(device);

			executeAssertsForManufactureHanDeviceType(deviceResponse);
		}
	}

	/**
	 * Test fetch all model by device sub type.
	 */
	@Test
	public void testFetchAllModelByDeviceSubType()
	{
		// Success Situation

		DeviceRequest request = new DeviceRequest();

		for (int i = 0; i < THREE; i++)
		{

			HanDevice hanDevice = new HanDevice();

			if (!ValidationUtil.isNull(HanDeviceTypeEnum.enumForValue(i)))
			{

				hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.enumForValue(i));

				InternalResultsResponse<Device> deviceResponseManufacture =
						fetchManufactureHanDeviceType(hanDevice);

				if (DeviceTypeEnum.HAN_DEVICE.equals(deviceResponseManufacture.getFirstResult().getDeviceType()))
				{
					hanDevice.setDeviceModel(new DeviceModel(((HanDevice)deviceResponseManufacture.getFirstResult())
							.getDeviceModel()
							.getManufacture(), null));
				}
				else
				{
					hanDevice.setDeviceModel(new DeviceModel(((LCM)deviceResponseManufacture.getFirstResult())
							.getDeviceModel()
							.getManufacture(), null));
				}

				request.addDevice(hanDevice);

				executeAssertsForManufactureHanDeviceType(deviceResponseManufacture);

				InternalResultsResponse<Device> hanDeviceResponseModel =
						getElectricMeterDAC().fetchAllModelByDeviceSubType(request);

				executeAssertsForModelHanDeviceType(hanDeviceResponseModel);
			}

		}
	}

	/**
	 * Test fetch han device type id.
	 */
	@Test
	public void testFetchHanDeviceTypeId()
	{
		HanDevice hanDevice = new HanDevice(HanDeviceTypeEnum.THERMOSTAT);
		hanDevice.setDeviceModel(new DeviceModel("Sensus/HAI", "539 63 537 08002"));

		DeviceRequest deviceRequest = new DeviceRequest(hanDevice);

		InternalResultsResponse<Integer> hanDeviceResponse = getElectricMeterDAC().fetchHanDeviceTypeId(deviceRequest);
		assertEquals(false, hanDeviceResponse.isInError());
	}

	/**
	 * Test update device status.
	 */
	@Test
	public void testUpdateDeviceStatus()
	{
		// Success situation
		HanDevice hanDevice = new HanDevice(HAN_DEVICE_ID);
		hanDevice.setRadio(new Radio(CUSTOMER_ID));
		hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.JOINED);
		hanDevice.setConfiguration(new HanDeviceConfiguration(new Date()));

		DeviceRequest deviceRequest = new DeviceRequest();
		deviceRequest.addDevice(hanDevice);

		InternalResponse response = getElectricMeterDAC().updateDeviceStatus(deviceRequest);

		assertResponse(response);
	}

	/**
	 * Test delete device status.
	 */
	@Test
	public void testDeleteDeviceStatus()
	{
		// Success situation

		DeviceRequest deviceRequest = new DeviceRequest(new HanDevice(new Radio(new BigInteger(HAN_DEVICE_ID))));

		InternalResponse response = getElectricMeterDAC().deleteDeviceStatus(deviceRequest);

		assertResponse(response);
	}

	/**
	 * Test delete device.
	 */
	@Test
	public void testDeleteDevice()
	{
		// Success situation - delete device by flexnet id

		DeviceRequest deviceRequest = new DeviceRequest(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID))));

		InternalResponse response = getElectricMeterDAC().deleteDevice(deviceRequest);

		assertResponse(response);

	}

	/**
	 * Test fetch all connection state.
	 */
	@Test
	public void testFetchAllConnectionState()
	{
		// Success situation - filter device by connection state

		DeviceSearch deviceSearch = new DeviceSearch();
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
		electricMeterSearch.addRemoteConnectStatusEnum(RemoteConnectStatusEnum.CONNECT);
		electricMeterSearch.addRemoteConnectStatusEnum(RemoteConnectStatusEnum.DISCONNECT);
		electricMeterSearch.addRemoteConnectStatusEnum(RemoteConnectStatusEnum.UNKNOWN);
		deviceSearch.setElectricMeterSearch(electricMeterSearch);

		DeviceRequest deviceRequest = new DeviceRequest();
		deviceRequest.setDeviceSearch(deviceSearch);

		InternalResponse response = getElectricMeterDAC().fetchAllConnectionState(deviceRequest);

		assertResponse(response);
	}

	/**
	 * Fetch esm tou enabled.
	 */
	@Test
	public void testFetchEsmTouEnabled()
	{
		InternalResultsResponse<Device> response =
				getElectricMeterDAC().fetchEsmTouEnabled(
						new DeviceRequest(new ElectricMeter(new Radio(new BigInteger(FLEXNET_ID)))));

		assertResponse(response);
	}

	/**
	 * Test fetch all peak demand.
	 */
	@Test
	public void testFetchAllPeakDemand()
	{
		DeviceReadingRequest request = new DeviceReadingRequest();
		request.setDevice(new Device(new Radio(new BigInteger(FLEXNET_ID))));
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		request.addSortExpressions(new SortExpression(PeakDemandOrderByEnum.DEMAND_RESET_COUNT.getValue(),
				Direction.Ascending));
		request.addSortExpressions(new SortExpression(PeakDemandOrderByEnum.PEAK_DEMAND.getValue(),
				Direction.Ascending));
		request.addSortExpressions(new SortExpression(PeakDemandOrderByEnum.PEAK_TIME.getValue(),
				Direction.Ascending));
		request.addSortExpressions(new SortExpression(PeakDemandOrderByEnum.READING_DATE.getValue(),
				Direction.Ascending));
		request.addSortExpressions(new SortExpression(PeakDemandOrderByEnum.RESET_TIME.getValue(),
				Direction.Ascending));
		request.addSortExpressions(new SortExpression(PeakDemandOrderByEnum.TIER.getValue(),
				Direction.Ascending));

		InternalResultsResponse<PeakDemand> response = getElectricMeterDAC().fetchAllPeakDemand(request);
		assertResponse(response);
	}

	/**
	 * Fetch manufacture han device type.
	 * 
	 * @param device the device
	 * @return the internal results response
	 */
	private InternalResultsResponse<Device> fetchManufactureHanDeviceType(Device device)
	{
		// Success Situation

		InternalResultsResponse<Device> deviceResponse =
				getElectricMeterDAC().fetchAllManufactureByDeviceSubType(new DeviceRequest(device));

		return deviceResponse;
	}

	/**
	 * Test fetch all devices firmware.
	 */
	private void testFetchAllDevicesFirmware()
	{
		// Success situation

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		ElectricMeterConfiguration configuration = new ElectricMeterConfiguration(FIRMWARE_FLEXNET);

		ElectricMeter electricMeter = new ElectricMeter(configuration);

		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		// // Success situation - fetching using complete firmware flexnet
		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		LOG.info(CONST_MARK);
		LOG.info("### fetching using complete firmware flexnet ###");
		LOG.info(LIST_SIZE + response.getResultsList().size());
		LOG.info(TOTAL_AVAIABLE + response.getResultsSetInfo().getTotalRowsAvailable());

		for (Device device : response.getResultsList())
		{
			LOG.info(CONST_MARK + device.getRadio().getFlexNetId().toString() + CONST_BLANK_SPACE
					+ device.getRadio().getCustomerId());
		}

		LOG.info(CONST_MARK);
		LOG.info("### fetching using part of firmware flexnet ###");

		configuration = new ElectricMeterConfiguration(FIRMWARE_METER);

		electricMeter = new ElectricMeter();
		electricMeter.setConfiguration(configuration);
		electricMeterSearch.setElectricMeter(electricMeter);
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		LOG.info(LIST_SIZE + response.getResultsList().size());
		LOG.info(TOTAL_AVAIABLE + response.getResultsSetInfo().getTotalRowsAvailable());

		for (Device device : response.getResultsList())
		{
			LOG.info(CONST_MARK + device.getRadio().getFlexNetId().toString() + CONST_BLANK_SPACE
					+ device.getRadio().getCustomerId());
		}

	}

	/**
	 * Test fetch all devices remote disconnect.
	 */
	private void testFetchAllDevicesRemoteDistonnect()
	{
		// Success situation

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());

		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
		electricMeterSearch.setElectricMeter(new ElectricMeter(RemoteConnectStatusEnum.UNKNOWN));
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		System.out.println(response.getResultsSetInfo().getTotalRowsAvailable());
	}

	/**
	 * Test fetch all devices lyfecycle state.
	 */
	private void testFetchAllDevicesLyfecycleState()
	{
		// Success situation - Install

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		ElectricMeter electricMeter = new ElectricMeter();
		electricMeter.setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.INSTALLED);
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);

		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		// Success situation - Install Not Assigned

		electricMeter = new ElectricMeter();
		electricMeter.setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.NOT_ASSIGNED);
		electricMeterSearch.setElectricMeter(electricMeter);

		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);
		response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices install date.
	 */
	private void testFetchAllDevicesInstallDate()
	{
		// Success situation - start date only

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setStartDate(new Date());

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		// Success situation - end date only
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setEndDate(new Date());
		response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		// Success situation - both
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setStartDate(new Date());
		request.getDeviceSearch().setEndDate(new Date());
		response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch all devices firmware flexnet.
	 */
	private void testFetchAllDevicesFirmwareFlexnet()
	{
		// Success situation - filter by firmware meter
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		ElectricMeter electricMeter = new ElectricMeter(new ElectricMeterConfiguration(FIRMWARE_FLEXNET));

		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch all devices process id.
	 */
	private void testFetchAllDevicesProcessId()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setProcessId(ONE);
		request.setDeviceSearch(new DeviceSearch());

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices device types.
	 */
	private void testFetchAllDevicesDeviceTypes()
	{
		// Success situation - filter by device type
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().addDeviceType(DeviceTypeEnum.ELECTRIC_METER);

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertResultResponse(response);

		// Success situation - filter by device types description

		request.getDeviceSearch().addDeviceType(null);

		request.getDeviceSearch().addDeviceModels(
				new DeviceModel(ElectricMeterTypeEnum.ELSTER_A3_C_I_FLEXNET_RADIO.getValue(), null, ELSTER_METER));
		request.getDeviceSearch().addDeviceModels(
				new DeviceModel(ElectricMeterTypeEnum.RX.getValue(), null, RX_TX_ICON_METER));

		response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all devices city address zip.
	 */
	private void testFetchAllDevicesCityAddressZip()
	{
		// Success situation - all devices
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		ElectricMeter electricMeter = new ElectricMeter(new Radio(new Location(ADDRESS, CITY, ZIP)));
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		// Success situation - flexnet id
		electricMeter = new ElectricMeter(new Radio(new BigInteger(FLEXNET_ID)));
		electricMeterSearch = new ElectricMeterSearch(electricMeter);
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		// Success situation - premise id
		request.setDeviceSearch(new DeviceSearch());
		ElectricMeterConfiguration configuration = new ElectricMeterConfiguration();
		configuration.setPremiseId(ONE_STR);

		electricMeter = new ElectricMeter(configuration);
		electricMeterSearch = new ElectricMeterSearch(electricMeter);
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch all devices ids.
	 */
	private void testFetchAllDevicesIds()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setPaginationAllSelected(false);
		request.setSelectionPaginationIds(new ArrayList<BigInteger>());
		request.getSelectionPaginationIds().add(new BigInteger(HAN_DEVICE_FLEXNET_ID));
		request.getSelectionPaginationIds().add(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073700));
		request.getSelectionPaginationIds().add(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073425));
		request.getSelectionPaginationIds().add(new BigInteger(FLEXNET_ID));

		request.setEndRow(0);

		Group group = new Group(SEVEN);
		group.addDevice(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID))));
		group.addDevice(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073700))));
		group.addDevice(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073425))));
		group.addDevice(new Device(new Radio(new BigInteger(FLEXNET_ID))));

		List<Group> groups = new ArrayList<Group>();
		groups.add(group);

		// request.setGroups(groups);

		request.setMaxPreQueryCount(0);
		request.setDeviceSearch(new DeviceSearch());
		request.setStartPage(0);

		// in test
		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);
		assertNotNull(response.getResultsList());

		for (Device device : response.getResultsList())
		{
			LOG.info("************************************************************************** - "
					+ device.getRadio().getFlexNetId().toString());
		}

		// not in test
		request.setPaginationAllSelected(true);
		response = getElectricMeterDAC().fetchAllElectricMeter(request);

		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch all devices tag.
	 */
	private void testFetchAllDevicesTag()
	{
		// Success situation

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.setTags(new ArrayList<Tag>());
		request.getTags().add(new Tag(1));

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);

		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices group.
	 */
	private void testFetchAllDevicesGroup()
	{
		// Success situation

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.setGroups(new ArrayList<Group>());
		request.getGroups().add(new Group(SEVEN));

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);

		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all to map.
	 */
	private void testFetchAllToMap()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));
		request.setGeoCodeTrunc(1);
		request.setGeocodeLatLongTruncs(Arrays.asList(
				new GeocodeLatLongTrunc(LAT_TRUNC_TEST_ONE, LON_TRUNC_TEST_ONE),
				new GeocodeLatLongTrunc(LAT_TRUNC_TEST_TWO, LON_TRUNC_TEST_ONE)));

		InternalResultsResponse<ElectricMeter> response = getElectricMeterDAC().fetchAllElectricMeter(request);

		assertNotNull(response.getResultsList());
	}

	/**
	 * Execute asserts for han device.
	 * 
	 * @param deviceResponse the device response
	 */
	private void executeAssertsForHanDevice(InternalResultsResponse<Device> deviceResponse)
	{
		assertNotNull(deviceResponse.getResultsList());

		// check specifically for the values in the DB here...
		for (Device device : deviceResponse.getResultsList())
		{
			if (DeviceTypeEnum.HAN_DEVICE.equals(device.getDeviceType()))
			{
				HanDevice hanDevice = (HanDevice)device;
				assertNotNull(hanDevice.getHanDeviceId());
				assertNotNull(hanDevice.getHanDeviceTypeEnum());
				assertNotNull(hanDevice.getHanDeviceTypeEnum().getValue());
				assertNotNull(hanDevice.getLifecycleStateEnum());
				assertNotNull(hanDevice.getConfiguration().getLastDateStatus());
			}
			else if (DeviceTypeEnum.LCM.equals(device.getDeviceType()))
			{
				LCM lcm = (LCM)device;
				assertNotNull(lcm.getDeviceId());
				assertNotNull(lcm.getDeviceType());
				assertNotNull(lcm.getDeviceType().getValue());
				assertNotNull(lcm.getLifecycleStateEnum());
				assertNotNull(lcm.getConfiguration().getLastDateStatus());
			}
		}
	}

	/**
	 * Execute asserts for manufacture han device type.
	 * 
	 * @param deviceResponse the han device response
	 */
	private void executeAssertsForManufactureHanDeviceType(InternalResultsResponse<Device> deviceResponse)
	{
		executeAssertsForHanDeviceResultList(deviceResponse);

		// check specifically for the values in the DB here...
		for (Device device : deviceResponse.getResultsList())
		{
			if (DeviceTypeEnum.HAN_DEVICE.equals(device.getDeviceType()))
			{
				assertNotNull(((HanDevice)device).getDeviceModel().getManufacture());
			}
			else
			{
				assertNotNull(((LCM)device).getDeviceModel().getManufacture());
			}
		}
	}

	/**
	 * Execute asserts for model han device type.
	 * 
	 * @param deviceResponse the device response
	 */
	private void executeAssertsForModelHanDeviceType(InternalResultsResponse<Device> deviceResponse)
	{
		executeAssertsForHanDeviceResultList(deviceResponse);

		// check specifically for the values in the DB here...
		for (Device device : deviceResponse.getResultsList())
		{
			if (DeviceTypeEnum.HAN_DEVICE.equals(device.getDeviceType()))
			{
				assertNotNull(((HanDevice)device).getDeviceModel());
			}
			else
			{
				assertNotNull(((LCM)device).getDeviceModel());
			}
		}
	}

	/**
	 * Execute asserts for HAN device by device.
	 * 
	 * @param deviceResponse the han device response
	 */
	private void executeAssertsForHanDeviceResultList(InternalResultsResponse<Device> deviceResponse)
	{
		assertNotNull(deviceResponse.getResultsList());
	}
}
