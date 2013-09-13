package com.sensus.dm.common.device.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryGeocodeDeviceInfoResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class ElectricMeterBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/device/devicebcfimpltest.xml"})
public class DeviceBCFImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant SHOULD_BE_TRUE. */
	private static final String SHOULD_BE_TRUE = "should be true ";

	/** The Constant SHOULD_BRING_TWO_DEVICE_TYPE. */
	private static final String SHOULD_BRING_TWO_DEVICE_TYPE = "should bring two device type ";

	/** The Constant SHOULD_BRING_ONE_DEVICE. */
	private static final String SHOULD_BRING_ONE_DEVICE = "should bring one device ";

	/** The Constant SHOULD_HAVE_NO_MESSAGES. */
	private static final String SHOULD_HAVE_NO_MESSAGES = "should have no messages";

	/** The Constant SHOULD_BE_NOT_NULL. */
	private static final String SHOULD_BE_NOT_NULL = "should be not null";

	/** The Constant SHOULD_BRING_THE_FLEX_NET_ID. */
	private static final String SHOULD_BRING_THE_FLEX_NET_ID = "should bring the FlexNet ID";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant PREMISE_ID_REQUIRED. */
	private static final String PREMISE_ID_REQUIRED = "sensus.epm.devicevalidator.device.premise.id.required";

	/** The Constant GRANTED_AUTHORITY_REQUIRED. */
	private static final String GRANTED_AUTHORITY_REQUIRED =
			"sensus.epm.devicerequestvalidator.granted.authority.required";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device bcf. */
	private IDeviceBCF deviceBCF; // injected by Spring through setter

	/**
	 * Gets the device bcf.
	 * 
	 * @return the device bcf
	 */
	public IDeviceBCF getDeviceBCF()
	{
		return deviceBCF;
	}

	/**
	 * Sets the device bcf.
	 * 
	 * @param deviceBCF the new device bcf
	 */
	@Resource
	public void setDeviceBCF(IDeviceBCF deviceBCF)
	{
		this.deviceBCF = deviceBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests Settings:

	/**
	 * Sets the device area.
	 */
	public void setDeviceArea()
	{
		setArea(getDeviceBCF(), EPMAreaEnum.DEVICE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setDeviceArea();
	}

	/**
	 * Removes the device area.
	 */
	@After
	public void resetMocksToDeviceArea()
	{
		resetMocksData(getDeviceBCF());
		setDeviceArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Testfetch all by premise id.
	 */
	@Test
	public void testfetchAllByPremiseId()
	{
		// Validation situation - premise id is required.
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);
		request.addDevice(device);

		DeviceResponse response = getDeviceBCF().fetchAllByPremiseId(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PREMISE_ID_REQUIRED);

		// Success situation
		ElectricMeter electricMeter = (ElectricMeter)request.getFirstDevice();
		electricMeter.getConfiguration().setPremiseId(PREMISE_ID);

		response = getDeviceBCF().fetchAllByPremiseId(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());

		// Error situation
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		response = getDeviceBCF().fetchAllByPremiseId(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchAllByPremiseId(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all devices type count.
	 */
	@Test
	public void testFetchAllDevicesTypeCount()
	{
		// Success situation
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		DeviceResponse response = getDeviceBCF().fetchAllDevicesTypeCount(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(SHOULD_BRING_TWO_DEVICE_TYPE, TWO, response.getDeviceTypeCountList().size());

		// Validation situation (missing Tenant)
		request = TestBaseUtil.createDeviceRequest();
		request.setTenant(null);
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getDeviceBCF().fetchAllDevicesTypeCount(request);
		assertMessages(response, TENANT_REQUIRED);

		// Validation situation (missing Tenant Customer ID)
		request.setTenant(TestBaseUtil.createEmptyTenant());
		response = getDeviceBCF().fetchAllDevicesTypeCount(request);
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Error situation
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		request.setTenant(TestBaseUtil.createTenant());
		response = getDeviceBCF().fetchAllDevicesTypeCount(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchAllDevicesTypeCount(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

		// Test Coverage
		resetMocksToDeviceArea();
		request.setServiceTypeEnum(null);
		response = getDeviceBCF().fetchAllDevicesTypeCount(request);
		assertMessages(response, SERVICE_TYPE_ENUM_REQUIRED);
	}

	/**
	 * Testfetch services by device type.
	 */
	@Test
	public void testfetchServicesByDeviceType()
	{
		// Validation situation - granted authority is required.

		DeviceRequest request = new DeviceRequest();

		DeviceResponse response = getDeviceBCF().fetchServicesByDeviceType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, GRANTED_AUTHORITY_REQUIRED);

		// Success situation.
		request.addGrantedAuthority("ROLE_Role.EPM_ADMIN");
		request.addGrantedAuthority("ROLE_Role.EPM_SERVICE_ELECTRIC");
		response = getDeviceBCF().fetchServicesByDeviceType(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		assertNotNull(SHOULD_BE_NOT_NULL, response.getServicesPermissions());
		assertNotNull(SHOULD_BE_NOT_NULL, response.getServicesPermissions().getServicesTypePermissions());
		assertNotNull(SHOULD_BE_NOT_NULL, response.getServicesPermissions().getServicesTypePermissions()
				.get(0).getDeviceTypePermissions().get(0).getDeviceType());
		assertNotNull(SHOULD_BE_NOT_NULL, response.getServicesPermissions().getServicesTypePermissions()
				.get(0).getDeviceTypePermissions().get(0).getDefaultActions());
		assertNotNull(SHOULD_BE_NOT_NULL, response.getServicesPermissions().getServicesTypePermissions()
				.get(0).getDeviceTypePermissions().get(0).getDeviceTypeModels());

		// Error situation
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		response = getDeviceBCF().fetchServicesByDeviceType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchServicesByDeviceType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch device by id import.
	 */
	@Test
	public void testFetchDeviceByIdImport()
	{
		// Validation Situation - device is required
		DeviceResponse response = getDeviceBCF().fetchDeviceByIdImport(new DeviceRequest());

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation Situation - flexnet id or device id is required
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new HanDevice());
		request.getFirstDevice().setDeviceType(null);

		response = getDeviceBCF().fetchDeviceByIdImport(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED, DEVICE_ID_REQUIRED);

		// Test HAN Device

		// Success situation
		request.getDevices().clear();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, ELECTRIC_FLEXNET_ID, null));
		request.setTenant(TestBaseUtil.createTenant());
		response = getDeviceBCF().fetchDeviceByIdImport(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());
		assertNotNull(SHOULD_BRING_THE_FLEX_NET_ID, response.getDevices().get(ZERO).getRadio().getFlexNetId());

		// Error situation
		resetMocksToDeviceArea();

		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));
		request.setTenant(TestBaseUtil.createTenant());
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		response = getDeviceBCF().fetchDeviceByIdImport(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchDeviceByIdImport(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch alarm history.
	 */
	@Test
	public void testFetchAlarmHistory()
	{
		DeviceRequest request = new DeviceRequest();
		DeviceResponse response = getDeviceBCF().fetchAlarmHistory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

		request.addDevice(new WaterMeter());
		response = getDeviceBCF().fetchAlarmHistory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ALARM_REQUIRED, RADIO_REQUIRED);

		WaterMeter waterMeter = new WaterMeter(new Radio());
		waterMeter.addAlarm(new Alarm());
		request = new DeviceRequest(waterMeter);
		response = getDeviceBCF().fetchAlarmHistory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ALARM_NAME_REQUIRED, ALARM_DATE_REQUIRED, FLEXNET_ID_REQUIRED);

		waterMeter.getRadio().setFlexNetId(WATER_FLEXNET_ID);
		waterMeter.getFirstAlarm().setAlarmEnum(AlarmEnum.LEAK_DETECTED);
		waterMeter.getFirstAlarm().setAlarmTime(new Date());
		response = getDeviceBCF().fetchAlarmHistory(request);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
	}

	/**
	 * Test fetch devices bounds to map.
	 */
	@Test
	public void testFetchDevicesBoundsToMap()
	{
		// Validation situation - Inquiry Device Request Required
		InquiryDeviceRequest request = null;
		InquiryGeocodeDeviceInfoResponse response = getDeviceBCF().fetchDevicesBoundsToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, INQUIRY_DEVICE_REQUEST_REQUIRED);

		// Validation situation - Device Type Required
		request = new InquiryDeviceRequest();
		response = getDeviceBCF().fetchDevicesBoundsToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_TYPE_ENUM_REQUIRED);

		// Success situation
		request = new InquiryDeviceRequest(DeviceTypeEnum.ELECTRIC_METER);
		response = getDeviceBCF().fetchDevicesBoundsToMap(request);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getBottomLeftLat());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getBottomLeftLon());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getTopRightLat());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getTopRightLon());

		resetMocksToDeviceArea();

		// Error situation
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		response = getDeviceBCF().fetchDevicesBoundsToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchDevicesBoundsToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch devices to map.
	 */
	@Test
	public void testFetchDevicesToMap()
	{
		// Validation situation - Inquiry Device Request Required
		InquiryDeviceRequest request = null;
		InquiryGeocodeDeviceInfoResponse response = getDeviceBCF().fetchDevicesToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, INQUIRY_DEVICE_REQUEST_REQUIRED);

		// Validation situation - Device Type Required / Geocode Trunc
		request = new InquiryDeviceRequest();
		response = getDeviceBCF().fetchDevicesToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_TYPE_ENUM_REQUIRED, GEOCODE_REQUIRED_REQUIRED);

		// Success situation
		request = new InquiryDeviceRequest(DeviceTypeEnum.ELECTRIC_METER);
		request.setGeoCodeTrunc(1);
		response = getDeviceBCF().fetchDevicesToMap(request);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getBottomLeftLat());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getBottomLeftLon());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getTopRightLat());
		assertNotNull(response.getFirstGeocodeDeviceInfo().getTopRightLon());
		assertNotNull(response.getResultsSetInfo().getEndRow());
		assertNotNull(response.getResultsSetInfo().getTotalRowsAvailable());

		resetMocksToDeviceArea();

		// Error situation
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		response = getDeviceBCF().fetchDevicesToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchDevicesToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all device type descriptions.
	 */
	@Test
	public void testFetchAllDeviceTypeDescriptions()
	{
		// Success situation
		DeviceRequest request = new DeviceRequest();
		DeviceResponse response = getDeviceBCF().fetchAllDeviceTypeDescriptions(request);
		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(SHOULD_BRING_TWO_DEVICE_TYPE, TWO, response.getDeviceModels().size());
		assertNotNull("should bring the description", response.getDeviceModels().get(ZERO).getDescription());

		// Error situation
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		response = getDeviceBCF().fetchAllDeviceTypeDescriptions(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchAllDeviceTypeDescriptions(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch quarantine count.
	 */
	@Test
	public void testFetchQuarantineCount()
	{
		// Success situation
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		DeviceResponse response = getDeviceBCF().fetchQuarantineCount(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(1, response.getQuarantineAmount().intValue());

		// Validation situation (missing Tenant)
		request = TestBaseUtil.createDeviceRequest();
		request.setTenant(null);
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getDeviceBCF().fetchQuarantineCount(request);
		assertMessages(response, TENANT_REQUIRED);

		// Validation situation (missing Tenant Customer ID)
		request.setTenant(TestBaseUtil.createEmptyTenant());
		response = getDeviceBCF().fetchQuarantineCount(request);
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Error situation
		setSituation(getDeviceBCF(), SituationsEnum.ERROR, IDeviceBCL.class);
		request.setTenant(TestBaseUtil.createTenant());
		response = getDeviceBCF().fetchQuarantineCount(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getDeviceBCF(), SituationsEnum.EXCEPTION, IDeviceBCL.class);
		response = getDeviceBCF().fetchQuarantineCount(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

		// Test Coverage
		resetMocksToDeviceArea();
		request.setServiceTypeEnum(null);
		response = getDeviceBCF().fetchQuarantineCount(request);
		assertMessages(response, SERVICE_TYPE_ENUM_REQUIRED);
	}
}
