package com.sensus.dm.gas.device.bcf;

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
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.gas.base.AbstractTestGasBusiness;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.gas.device.model.GasMeterSearch;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;

/**
 * JUnit tests for GasMeter BCF.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/gas/device/gasmeterbcfimpltest.xml"})
public class GasMeterBCFImplTest extends AbstractTestGasBusiness
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
	private static final String DEFAULT_GASMETER_BCF_EXCEPTION_MSG = "sensus.epm.gasmeterbcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device bcf. */
	private IGasMeterBCF gasMeterBCF;

	/**
	 * Gets the gas meter bcf.
	 * 
	 * @return the gas meter bcf
	 */
	public IGasMeterBCF getGasMeterBCF()
	{
		return gasMeterBCF;
	}

	/**
	 * Sets the gas meter bcf.
	 * 
	 * @param gasMeterBCF the new gas meter bcf
	 */
	@Resource
	public void setGasMeterBCF(IGasMeterBCF gasMeterBCF)
	{
		this.gasMeterBCF = gasMeterBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Sets the gas meter area.
	 */
	public void setGasMeterArea()
	{
		setArea(getGasMeterBCF(), EPMAreaEnum.DEVICE);
	}

	/**
	 * Reset mocks to gas meter area.
	 */
	@After
	public void resetMocksToGasMeterArea()
	{
		resetMocksData(getGasMeterBCF());
		setGasMeterArea();
	}

	/**
	 * Test fetch all electric meters.
	 */
	@Test
	public void testFetchAllGasMeters()
	{
		// Validation situation - Order By and Start Row
		InquiryDeviceRequest request = new InquiryDeviceRequest();
		request.setPageSize(FIFTEEN);
		request.setStartRow(null);
		InquiryDeviceResponse response = getGasMeterBCF().fetchAllGasMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED,
				START_ROW_INVALID);

		// Validation situation - User Context Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setUserContext(null);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device id length is invalid
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		GasMeter gasMeter = new GasMeter(DEVICE_ID_INVALID);
		GasMeterSearch gasMeterSearch = new GasMeterSearch();
		gasMeterSearch.setGasMeter(gasMeter);
		DeviceSearch deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Convert Firmware
		gasMeter = new GasMeter(DEVICE_ID);
		WaterGasMeterConfiguration gasGasMeterConfiguration = new WaterGasMeterConfiguration("5.3");
		gasMeter.setConfiguration(gasGasMeterConfiguration);
		gasMeterSearch.setGasMeter(gasMeter);
		deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - Address, City and zip invalid
		Location location =
				new Location(StringUtils.repeat("OVER 50 CHARS", FOUR), StringUtils.repeat("OVER 25 CHARS",
						FOUR), ZIP_CODE_INVALID);
		Radio radio = new Radio(location);
		gasMeter.setRadio(radio);
		gasMeterSearch.setGasMeter(gasMeter);
		deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ADDRESS_INVALID, CITY_INVALID, ZIP_INVALID);

		// Success situation
		location = new Location(ADDRESS, CITY, ZIP);
		radio = new Radio(location);
		gasMeter.setRadio(radio);
		gasMeterSearch.setGasMeter(gasMeter);
		deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Device Search
		request.setDeviceSearch(null);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Water Meter Search
		gasMeterSearch = null;
		deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Water Meter
		gasMeter = null;
		gasMeterSearch = new GasMeterSearch();
		gasMeterSearch.setGasMeter(gasMeter);
		deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Water Meter Configuration
		gasMeter = new GasMeter(DEVICE_ID);
		gasMeter.setConfiguration(null);
		gasMeterSearch = new GasMeterSearch();
		gasMeterSearch.setGasMeter(gasMeter);
		deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Success situation - Without Location
		location = null;
		radio = new Radio(location);
		gasMeter.setRadio(radio);
		gasMeterSearch.setGasMeter(gasMeter);
		deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getGasMeterBCF(), SituationsEnum.ERROR, IGasMeterBCL.class);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToGasMeterArea();

		// Exception situation
		setSituation(getGasMeterBCF(), SituationsEnum.EXCEPTION, IGasMeterBCL.class);
		response = getGasMeterBCF().fetchAllGasMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GASMETER_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchGasMeterById()
	{
		// Validation Situation - device is required
		DeviceResponse response = getGasMeterBCF().fetchGasMeterById(new DeviceRequest());

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation Situation - flexnet id or device id is required
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new GasMeter());
		request.getFirstDevice().setDeviceType(null);

		response = getGasMeterBCF().fetchGasMeterById(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED, DEVICE_ID_REQUIRED, DEVICE_TYPE_ENUM_REQUIRED);

		// Test HAN Device

		// Success situation
		request.getDevices().clear();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER));
		response = getGasMeterBCF().fetchGasMeterById(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());
		assertNotNull(SHOULD_BRING_THE_FLEX_NET_ID, response.getDevices().get(ZERO).getRadio().getFlexNetId());

		// Test Meter

		// Success situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER));
		response = getGasMeterBCF().fetchGasMeterById(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());
		assertNotNull(SHOULD_BRING_THE_FLEX_NET_ID, response.getDevices().get(ZERO).getRadio().getFlexNetId());

		// Error situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER));
		setSituation(getGasMeterBCF(), SituationsEnum.ERROR, IGasMeterBCL.class);
		response = getGasMeterBCF().fetchGasMeterById(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToGasMeterArea();

		// Exception situation
		setSituation(getGasMeterBCF(), SituationsEnum.EXCEPTION, IGasMeterBCL.class);
		response = getGasMeterBCF().fetchGasMeterById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GASMETER_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateDevicesCSV()
	{
		// Validation situation - Device is required
		InquiryDeviceRequest inquiryDeviceRequest = new InquiryDeviceRequest();
		GasMeterSearch gasMeterSearch = new GasMeterSearch();
		DeviceSearch searchValue = new DeviceSearch(gasMeterSearch);
		inquiryDeviceRequest.setDeviceSearch(searchValue);
		InquiryDeviceResponse response = getGasMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation situation - Order by is required
		GasMeter gasMeter = createGasMeter(DEVICE_ID, CITY, ZIP, ADDRESS);
		gasMeterSearch.setGasMeter(gasMeter);
		searchValue = new DeviceSearch(gasMeterSearch);
		inquiryDeviceRequest.setDeviceSearch(searchValue);
		inquiryDeviceRequest.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		response = getGasMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED);

		// Validation situation - process id, file name, user context and tenant is required.
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		response = getGasMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED, TENANT_REQUIRED);

		// Success situation
		inquiryDeviceRequest = TestBaseUtil.createInquiryDeviceRequest();
		inquiryDeviceRequest.setFileName(FILE_NAME);
		inquiryDeviceRequest.setProcessId(ONE);
		inquiryDeviceRequest.setDeviceSearch(new DeviceSearch());
		inquiryDeviceRequest.getDeviceSearch().setGasMeterSearch(new GasMeterSearch());
		inquiryDeviceRequest.getDeviceSearch().getGasMeterSearch().setGasMeter(gasMeter);
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));

		response = getGasMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		resetMocksToGasMeterArea();

		// Exception situation
		setSituation(getGasMeterBCF(), SituationsEnum.EXCEPTION, IGasMeterBCL.class);
		response = getGasMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GASMETER_BCF_EXCEPTION_MSG);
	}
}
