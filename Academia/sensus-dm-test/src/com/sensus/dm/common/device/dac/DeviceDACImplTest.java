package com.sensus.dm.common.device.dac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.gas.device.model.GasMeterSearch;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.WaterMeterSearch;

/**
 * The Class ElectricMeterDACImplTest.
 * 
 * @author QAT Global.
 */
public class DeviceDACImplTest extends AbstractTestBaseDAC
{
	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant FIRMWARE_FLEXNET. */
	private static final String FIRMWARE_FLEXNET = "005.000.000.000.1";

	/** The Constant TOP_RIGHT_MAP. */
	private static final double TOP_RIGHT_MAP = 200.00;

	/** The Constant BOTTOM_LEFT_MAP. */
	private static final double BOTTOM_LEFT_MAP = -100.00;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all by premise id.
	 */
	@Test
	public void testFetchAllByPremiseId()
	{
		// Success situation
		ElectricMeterConfiguration configuration = new ElectricMeterConfiguration();
		configuration.setPremiseId(ONE_STR);

		ElectricMeter electricMeter = new ElectricMeter(configuration);

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		request.addDevice(electricMeter);

		// // Success situation - all devices
		InternalResultsResponse<Device> response = getDeviceDAC().fetchAllByPremiseId(request);
		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch all devices installed.
	 */
	@Test
	public void testFetchAllDevicesTypeCount()
	{
		// Success Situation - WaterMeter
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		deviceRequest.setTenant(TestBaseUtil.createTenant());

		deviceRequest.setDeviceSearch(new DeviceSearch());
		WaterMeter waterMeter = new WaterMeter(new Radio(new BigInteger(FLEXNET_ID_WATER)));
		waterMeter.setDeviceId(DEVICE_ID_WATER);
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch(waterMeter, Arrays.asList(AlarmEnum.LEAK_DETECTED));
		deviceRequest.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		InternalResultsResponse<DeviceTypeCount> response = getDeviceDAC().fetchAllDevicesTypeCount(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Success Situation - GasMeter
		deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.GAS);
		deviceRequest.setTenant(TestBaseUtil.createTenant());

		deviceRequest.setDeviceSearch(new DeviceSearch());
		GasMeter gasMeter = new GasMeter(new Radio(new BigInteger(FLEXNET_ID_WATER)));
		gasMeter.setDeviceId(DEVICE_ID_WATER);
		GasMeterSearch gasMeterSearch = new GasMeterSearch(gasMeter, Arrays.asList(AlarmEnum.TILT));
		deviceRequest.getDeviceSearch().setGasMeterSearch(gasMeterSearch);

		response = getDeviceDAC().fetchAllDevicesTypeCount(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Success Situation - ElectricMeter
		deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		deviceRequest.setDeviceSearch(new DeviceSearch());
		ElectricMeter electricMeter = new ElectricMeter(new Radio(new BigInteger(FLEXNET_ID)));
		electricMeter.setDeviceId(DEVICE_ID);

		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);
		electricMeterSearch.setElectricMeterLifecycleStateEnumList(Arrays
				.asList(ElectricMeterLifecycleStateEnum.INSTALLED));
		deviceRequest.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		response = getDeviceDAC().fetchAllDevicesTypeCount(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Success Situation - LCM
		deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		deviceRequest.setDeviceSearch(new DeviceSearch());
		LCM lcm = new LCM(DEVICE_ID);

		LCMSearch lCMSearch = new LCMSearch(lcm, Arrays.asList(AlarmEnum.RELAY_CURRENT_TAMPER));
		deviceRequest.getDeviceSearch().setLcmSearch(lCMSearch);

		response = getDeviceDAC().fetchAllDevicesTypeCount(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());

	}

	/**
	 * Test fetch device by id import.
	 */
	@Test
	public void testFetchDeviceByIdImport()
	{
		// Success situation - Electric Meter
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new Device(DEVICE_ID));
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(new DMTenant(CUSTOMER_ID));
		request.setDeviceSearch(new DeviceSearch(DeviceTypeEnum.ELECTRIC_METER));

		InternalResultsResponse<Device> response = getDeviceDAC().fetchDeviceByIdImport(request);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Success situation - LCM
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new Device(LCM_DEVICE_ID));
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(new DMTenant(CUSTOMER_ID));

		response = getDeviceDAC().fetchDeviceByIdImport(request);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Validation situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new Device(DEVICE_ID));
		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		request.setTenant(new DMTenant(CUSTOMER_ID));

		response = getDeviceDAC().fetchDeviceByIdImport(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test fetch alarm history.
	 */
	@Test
	public void testFetchAlarmHistory()
	{
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, NEGATIVE_TEN);

		Device meter =
				new WaterMeter(new Radio(new BigInteger(FLEXNET_ID_WATER)), new Alarm(AlarmEnum.BROKEN_PIPE,
						c.getTime()));
		deviceRequest.setDevices(Arrays.asList(meter));

		InternalResultsResponse<Alarm> response = getDeviceDAC().fetchAlarmHistory(deviceRequest);
		assertNotNull(response.getResultsList());

		meter = new GasMeter(new Radio(new BigInteger(FLEXNET_ID)), new Alarm(AlarmEnum.LEAK_DETECTED, c.getTime()));
		deviceRequest.setDevices(Arrays.asList(meter));

		response = getDeviceDAC().fetchAlarmHistory(deviceRequest);
		assertNotNull(response.getResultsList());

		meter = new LCM(new Radio(new BigInteger(FLEXNET_ID)), new Alarm(AlarmEnum.HIST_OVER_FLOW, c.getTime()));
		deviceRequest.setDevices(Arrays.asList(meter));

		response = getDeviceDAC().fetchAlarmHistory(deviceRequest);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch alarms types count.
	 */
	@Test
	public void testFetchAlarmsTypesCount()
	{
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();

		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		deviceRequest.setDeviceSearch(new DeviceSearch(new WaterMeterSearch(new WaterMeter(), Arrays
				.asList(AlarmEnum.LEAK_DETECTED))));
		InternalResultsResponse<AlarmsTypesCount> response = getDeviceDAC().fetchAlarmsTypesCount(deviceRequest);
		assertNotNull(response.getResultsList());

		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.GAS);
		deviceRequest.setDeviceSearch(new DeviceSearch(new GasMeterSearch(new GasMeter(), Arrays
				.asList(AlarmEnum.LEAK_DETECTED))));
		response = getDeviceDAC().fetchAlarmsTypesCount(deviceRequest);
		assertNotNull(response.getResultsList());

		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		deviceRequest.setDeviceSearch(new DeviceSearch(new LCMSearch(new LCM(), Arrays
				.asList(AlarmEnum.RELAY_CURRENT_TAMPER))));
		response = getDeviceDAC().fetchAlarmsTypesCount(deviceRequest);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch electric meters bounds to map.
	 */
	@Test
	public void testFetchElectricMetersBoundsToMap()
	{
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(DeviceTypeEnum.ELECTRIC_METER, TestBaseUtil.createTenant());

		ElectricMeterConfiguration configuration = new ElectricMeterConfiguration(FIRMWARE_FLEXNET);
		ElectricMeter electricMeter = new ElectricMeter(configuration);
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		testFetchDevicesBoundsToMap(request);
	}

	/**
	 * Test fetch han devices bounds to map.
	 */
	@Test
	public void testFetchHanDevicesBoundsToMap()
	{
		InquiryDeviceRequest request = new InquiryDeviceRequest(DeviceTypeEnum.HAN_DEVICE, TestBaseUtil.createTenant());
		testFetchDevicesBoundsToMap(request);
	}

	/**
	 * Test fetch lcm bounds to map.
	 */
	@Test
	public void testFetchLcmBoundsToMap()
	{
		InquiryDeviceRequest request = new InquiryDeviceRequest(DeviceTypeEnum.LCM, TestBaseUtil.createTenant());
		testFetchDevicesBoundsToMap(request);
	}

	/**
	 * Test fetch water meters bounds to map.
	 */
	@Test
	public void testFetchWaterMetersBoundsToMap()
	{
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(DeviceTypeEnum.WATER_METER, TestBaseUtil.createTenant());
		request.setTenant(TestBaseUtil.createTenant());

		request.setDeviceSearch(new DeviceSearch());
		WaterMeter waterMeter = new WaterMeter(new Radio(new BigInteger(FLEXNET_ID_WATER)));
		waterMeter.setDeviceId(DEVICE_ID_WATER);
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch(waterMeter);
		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		testFetchDevicesBoundsToMap(request);
	}

	/**
	 * Test fetch gas meters bounds to map.
	 */
	@Test
	public void testFetchGasMetersBoundsToMap()
	{
		InquiryDeviceRequest request = new InquiryDeviceRequest(DeviceTypeEnum.GAS_METER, TestBaseUtil.createTenant());
		request.setTenant(TestBaseUtil.createTenant());

		testFetchDevicesBoundsToMap(request);
	}

	/**
	 * Test fetch electric meters to map.
	 */
	@Test
	public void testFetchElectricMetersToMap()
	{
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(DeviceTypeEnum.ELECTRIC_METER, TestBaseUtil.createTenant());
		request = setRequestToMap(request, 0);

		ElectricMeterConfiguration configuration = new ElectricMeterConfiguration(FIRMWARE_FLEXNET);
		ElectricMeter electricMeter = new ElectricMeter(configuration);
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setElectricMeterSearch(electricMeterSearch);

		testFetchDevicesToMap(request);

		request = setRequestToMap(request, 2);
		testFetchDevicesToMap(request);
	}

	/**
	 * Test fetch han devices to map.
	 */
	@Test
	public void testFetchHanDevicesToMap()
	{
		InquiryDeviceRequest request = new InquiryDeviceRequest(DeviceTypeEnum.HAN_DEVICE, TestBaseUtil.createTenant());
		request = setRequestToMap(request, 1);

		testFetchDevicesToMap(request);

		request = setRequestToMap(request, 2);
		testFetchDevicesToMap(request);
	}

	/**
	 * Test fetch lcm to map.
	 */
	@Test
	public void testFetchLcmToMap()
	{
		InquiryDeviceRequest request = new InquiryDeviceRequest(DeviceTypeEnum.LCM, TestBaseUtil.createTenant());
		request = setRequestToMap(request, 1);

		testFetchDevicesToMap(request);

		request = setRequestToMap(request, 2);
		testFetchDevicesToMap(request);
	}

	/**
	 * Test fetch water meters to map.
	 */
	@Test
	public void testFetchWaterMetersToMap()
	{
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(DeviceTypeEnum.WATER_METER, TestBaseUtil.createTenant());
		request.setTenant(TestBaseUtil.createTenant());
		request = setRequestToMap(request, 1);

		testFetchDevicesToMap(request);

		request = setRequestToMap(request, 2);
		testFetchDevicesToMap(request);
	}

	/**
	 * Test fetch gas meters to map.
	 */
	@Test
	public void testFetchGasMetersToMap()
	{
		InquiryDeviceRequest request = new InquiryDeviceRequest(DeviceTypeEnum.GAS_METER, TestBaseUtil.createTenant());
		request.setTenant(TestBaseUtil.createTenant());
		request = setRequestToMap(request, 1);

		testFetchDevicesToMap(request);

		request = setRequestToMap(request, 2);
		testFetchDevicesToMap(request);
	}

	/**
	 * Test fetch all device type descriptions.
	 */
	@Test
	public void testFetchAllDeviceTypeDescriptions()
	{
		InternalResultsResponse<DeviceModel> response =
				getDeviceDAC().fetchAllDeviceTypeDescriptions(
						new DeviceRequest(new DeviceSearch(DeviceTypeEnum.ELECTRIC_METER)));

		assertNotNull(response);
		assertEquals(0, response.getMessageInfoList().size());
		assertTrue(response.getResultsList().size() > 0);

		// check specifically for the values in the DB here...
		for (DeviceModel dt : response.getResultsList())
		{
			assertNotNull(dt.getDescription());
		}

		response =
				getDeviceDAC().fetchAllDeviceTypeDescriptions(
						new DeviceRequest(new DeviceSearch(DeviceTypeEnum.HAN_DEVICE,
								new HanDeviceSearch(Arrays.asList(HanDeviceTypeEnum.THERMOSTAT)))));

		assertNotNull(response);
		assertEquals(0, response.getMessageInfoList().size());
		if (response.hasResults())
		{
			// check specifically for the values in the DB here...
			for (DeviceModel dt : response.getResultsList())
			{
				assertNotNull(dt.getDescription());
			}
		}
	}

	/**
	 * Test Fetch quarantine count.
	 */
	@Test
	public void testFetchQuarantineCount()
	{
		DeviceRequest request = new DeviceRequest();
		request.setTenant(TestBaseUtil.createTenant());

		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		response = getDeviceDAC().fetchQuarantineCount(request);

		assertNotNull(response);
		assertEquals(0, response.getMessageInfoList().size());
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Test fetch devices bounds to map.
	 * 
	 * @param request the request
	 */
	private void testFetchDevicesBoundsToMap(InquiryDeviceRequest request)
	{
		InternalResultsResponse<GeocodeDeviceInfo> response = getDeviceDAC().fetchDevicesBoundsToMap(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch devices to map.
	 * 
	 * @param request the request
	 */
	private void testFetchDevicesToMap(InquiryDeviceRequest request)
	{
		InternalResultsResponse<GeocodeDeviceInfo> response = getDeviceDAC().fetchDevicesToMap(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Sets the request to map.
	 * 
	 * @param request the request
	 * @param geoCodeTrunc the geo code trunc
	 * @return the inquiry device request
	 */
	private InquiryDeviceRequest setRequestToMap(InquiryDeviceRequest request, Integer geoCodeTrunc)
	{
		request.setGeoCodeTrunc(geoCodeTrunc);
		request.setBottomLeftLat(BOTTOM_LEFT_MAP);
		request.setBottomLeftLon(BOTTOM_LEFT_MAP);
		request.setTopRightLat(TOP_RIGHT_MAP);
		request.setTopRightLon(TOP_RIGHT_MAP);

		return request;
	}
}