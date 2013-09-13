package com.sensus.dm.water.device.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.water.base.AbstractTestWaterBusiness;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.WaterMeterSearch;
import com.sensus.dm.water.device.model.response.WaterMeterResponse;

/**
 * JUnit tests for WaterMeter BCF.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/water/device/watermeterbcfimpltest.xml"})
public class WaterMeterBCFImplTest extends AbstractTestWaterBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant DEVICE_ID_INVALID. */
	private static final String DEVICE_ID_INVALID = "123456789012345678901234567890M";

	/** The Constant ZIP_CODE_INVALID. */
	private static final String ZIP_CODE_INVALID = "100010000000";

	/** The Constant SHOULD_HAVE_NO_MESSAGES. */
	private static final String SHOULD_HAVE_NO_MESSAGES = "should have no messages";

	/** The Constant SHOULD_BE_TRUE. */
	private static final String SHOULD_BE_TRUE = "should be true ";

	/** The Constant SHOULD_BRING_ONE_DEVICE. */
	private static final String SHOULD_BRING_ONE_DEVICE = "should bring one device ";

	/** The Constant SHOULD_BRING_THE_FLEX_NET_ID. */
	private static final String SHOULD_BRING_THE_FLEX_NET_ID = "should bring the FlexNet ID";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_WATERMETER_BCF_EXCEPTION_MSG = "sensus.epm.watermeterbcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device bcf. */
	private IWaterMeterBCF waterMeterBCF;

	/**
	 * Gets the water meter bcf.
	 * 
	 * @return the water meter bcf
	 */
	public IWaterMeterBCF getWaterMeterBCF()
	{
		return waterMeterBCF;
	}

	/**
	 * Sets the water meter bcf.
	 * 
	 * @param waterMeterBCF the new water meter bcf
	 */
	@Resource
	public void setWaterMeterBCF(IWaterMeterBCF waterMeterBCF)
	{
		this.waterMeterBCF = waterMeterBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Sets the water meter area.
	 */
	public void setWaterMeterArea()
	{
		setArea(getWaterMeterBCF(), EPMAreaEnum.DEVICE);
	}

	/**
	 * Reset mocks to water meter area.
	 */
	@After
	public void resetMocksToWaterMeterArea()
	{
		resetMocksData(getWaterMeterBCF());
		setWaterMeterArea();
	}

	/**
	 * Test fetch all electric meters.
	 */
	@Test
	public void testFetchAllWaterMeters()
	{
		// Validation situation - Order By and Start Row
		InquiryDeviceRequest request = new InquiryDeviceRequest();
		request.setPageSize(FIFTEEN);
		request.setStartRow(null);
		InquiryDeviceResponse response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED, START_ROW_INVALID);

		// Validation situation - User Context Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setUserContext(null);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Tenant Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(null);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Validation situation - Customer Id Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(new DMTenant(null));
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Validation situation - Device id length is invalid
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		WaterMeter waterMeter = new WaterMeter(DEVICE_ID_INVALID);
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);
		DeviceSearch deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Convert Firmware
		waterMeter = new WaterMeter(DEVICE_ID);
		WaterGasMeterConfiguration waterGasMeterConfiguration = new WaterGasMeterConfiguration("5.3");
		waterMeter.setConfiguration(waterGasMeterConfiguration);
		waterMeterSearch.setWaterMeter(waterMeter);
		deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - Address, City and zip invalid
		Location location =
				new Location(StringUtils.repeat("OVER 50 CHARS", FOUR), StringUtils.repeat("OVER 25 CHARS",
						FOUR), ZIP_CODE_INVALID);
		Radio radio = new Radio(location);
		waterMeter.setRadio(radio);
		waterMeterSearch.setWaterMeter(waterMeter);
		deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ADDRESS_INVALID, CITY_INVALID, ZIP_INVALID);

		// Success situation
		location = new Location(ADDRESS, CITY, ZIP);
		radio = new Radio(location);
		waterMeter.setRadio(radio);
		waterMeterSearch.setWaterMeter(waterMeter);
		deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Device Search
		request.setDeviceSearch(null);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Water Meter Search
		waterMeterSearch = null;
		deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Water Meter
		waterMeter = null;
		waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);
		deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Water Meter Configuration
		waterMeter = new WaterMeter(DEVICE_ID);
		waterMeter.setConfiguration(null);
		waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);
		deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Location
		location = null;
		radio = new Radio(location);
		waterMeter.setRadio(radio);
		waterMeterSearch.setWaterMeter(waterMeter);
		deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getWaterMeterBCF(), SituationsEnum.ERROR, IWaterMeterBCL.class);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToWaterMeterArea();

		// Exception situation
		setSituation(getWaterMeterBCF(), SituationsEnum.EXCEPTION, IWaterMeterBCL.class);
		response = getWaterMeterBCF().fetchAllWaterMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchWaterMeterById()
	{
		// Validation Situation - device is required
		DeviceResponse response = getWaterMeterBCF().fetchWaterMeterById(new DeviceRequest());

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation Situation - flexnet id or device id is required
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new WaterMeter());
		request.getFirstDevice().setDeviceType(null);

		response = getWaterMeterBCF().fetchWaterMeterById(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED, DEVICE_ID_REQUIRED, DEVICE_TYPE_ENUM_REQUIRED);

		// Test HAN Device

		// Success situation
		request.getDevices().clear();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER));
		response = getWaterMeterBCF().fetchWaterMeterById(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());
		assertNotNull(SHOULD_BRING_THE_FLEX_NET_ID, response.getDevices().get(ZERO).getRadio().getFlexNetId());

		// Test Meter

		// Success situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER));
		response = getWaterMeterBCF().fetchWaterMeterById(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());
		assertNotNull(SHOULD_BRING_THE_FLEX_NET_ID, response.getDevices().get(ZERO).getRadio().getFlexNetId());

		// Error situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER));
		setSituation(getWaterMeterBCF(), SituationsEnum.ERROR, IWaterMeterBCL.class);
		response = getWaterMeterBCF().fetchWaterMeterById(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToWaterMeterArea();

		// Exception situation
		setSituation(getWaterMeterBCF(), SituationsEnum.EXCEPTION, IWaterMeterBCL.class);
		response = getWaterMeterBCF().fetchWaterMeterById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch leak report.
	 */
	@Test
	public void testFetchLeakReport()
	{
		// Validation Situation - customer id required
		WaterMeterResponse response = getWaterMeterBCF().fetchLeakReport(new DeviceRequest());

		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Success situation
		DeviceRequest request = new DeviceRequest(new WaterMeter(new Radio(null, CUSTOMER_ID)));
		request.setTenant(TestBaseUtil.createTenant(CUSTOMER_ID));
		response = getWaterMeterBCF().fetchLeakReport(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getWaterMeterBCF(), SituationsEnum.ERROR, IWaterMeterBCL.class);
		response = getWaterMeterBCF().fetchLeakReport(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToWaterMeterArea();

		// Exception situation
		setSituation(getWaterMeterBCF(), SituationsEnum.EXCEPTION, IWaterMeterBCL.class);
		response = getWaterMeterBCF().fetchLeakReport(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv leak report.
	 */
	@Test
	public void testGenerateFileCSVLeakReport()
	{
		// Validation Situation - customer id required
		WaterMeterResponse response = getWaterMeterBCF().generateFileCSVLeakReport(new DeviceRequest());

		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Success situation
		DeviceRequest request = new DeviceRequest(new WaterMeter(new Radio(null, CUSTOMER_ID)));
		request.setTenant(TestBaseUtil.createTenant(CUSTOMER_ID));
		response = getWaterMeterBCF().generateFileCSVLeakReport(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getWaterMeterBCF(), SituationsEnum.ERROR, IWaterMeterBCL.class);
		response = getWaterMeterBCF().generateFileCSVLeakReport(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToWaterMeterArea();

		// Exception situation
		setSituation(getWaterMeterBCF(), SituationsEnum.EXCEPTION, IWaterMeterBCL.class);
		response = getWaterMeterBCF().generateFileCSVLeakReport(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateDevicesCSV()
	{
		// Validation situation - Device is required
		InquiryDeviceRequest inquiryDeviceRequest = new InquiryDeviceRequest();
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();
		DeviceSearch searchValue = new DeviceSearch(waterMeterSearch);
		inquiryDeviceRequest.setDeviceSearch(searchValue);
		InquiryDeviceResponse response = getWaterMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation situation - Order by is required
		WaterMeter waterMeter = createWaterMeter(DEVICE_ID, CITY, ZIP, ADDRESS);
		waterMeterSearch.setWaterMeter(waterMeter);
		searchValue = new DeviceSearch(waterMeterSearch);
		inquiryDeviceRequest.setDeviceSearch(searchValue);
		inquiryDeviceRequest.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		response = getWaterMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED);

		// Validation situation - process id, file name, user context and tenant is required.
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		response = getWaterMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED, TENANT_REQUIRED);

		// Success situation
		inquiryDeviceRequest = TestBaseUtil.createInquiryDeviceRequest();
		inquiryDeviceRequest.setFileName(FILE_NAME);
		inquiryDeviceRequest.setProcessId(ONE);
		inquiryDeviceRequest.setDeviceSearch(new DeviceSearch());
		inquiryDeviceRequest.getDeviceSearch().setWaterMeterSearch(new WaterMeterSearch());
		inquiryDeviceRequest.getDeviceSearch().getWaterMeterSearch().setWaterMeter(waterMeter);
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));

		response = getWaterMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		resetMocksToWaterMeterArea();

		// Exception situation
		setSituation(getWaterMeterBCF(), SituationsEnum.EXCEPTION, IWaterMeterBCL.class);
		response = getWaterMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch communication.
	 */
	@Test
	public void testFetchCommunication()
	{
		// Validation situation - User Context Required
		DeviceRequest request = new DeviceRequest();

		request = TestBaseUtil.createDeviceRequest();
		request.setTenant(TestBaseUtil.createTenant());
		request.setUserContext(null);
		DeviceResponse response = getWaterMeterBCF().fetchCommunication(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Tenant Required
		request = TestBaseUtil.createDeviceRequest();
		request.setTenant(null);
		response = getWaterMeterBCF().fetchCommunication(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Validation situation - Customer Id Required
		request = TestBaseUtil.createDeviceRequest();
		request.setTenant(new DMTenant(null));
		response = getWaterMeterBCF().fetchCommunication(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Success situation
		request = new DeviceRequest();
		request = TestBaseUtil.createDeviceRequest();
		request.setTenant(TestBaseUtil.createTenant());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getWaterMeterBCF().fetchCommunication(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		resetMocksToWaterMeterArea();

		// Exception situation
		setSituation(getWaterMeterBCF(), SituationsEnum.EXCEPTION, IWaterMeterBCL.class);
		response = getWaterMeterBCF().fetchCommunication(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
	}

}
