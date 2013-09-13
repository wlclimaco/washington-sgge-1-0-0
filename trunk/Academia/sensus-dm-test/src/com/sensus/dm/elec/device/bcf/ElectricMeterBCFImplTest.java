package com.sensus.dm.elec.device.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMConfiguration;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.elec.device.model.PeakDemandOrderByEnum;
import com.sensus.dm.elec.device.model.RemoteConnectStatusEnum;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryPeakDemandResponse;

/**
 * The Class ElectricMeterBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/elec/device/electricmeterbcfimpltest.xml"})
public class ElectricMeterBCFImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant OVERFLOW_ADDRESS_LENGTH = 1 more than the maximum address length. */
	private static final int OVERFLOW_ADDRESS_LENGTH = 51;

	/** The Constant MANUFACTURE. */
	private static final String MANUFACTURE = "Sensus/HAI";

	/** The Constant SHOULD_BE_TRUE. */
	private static final String SHOULD_BE_TRUE = "should be true ";

	/** The Constant SHOULD_BRING_TWO_HAN_DEVICE. */
	private static final String SHOULD_BRING_TWO_HAN_DEVICE = "should bring two han device ";

	/** The Constant SHOULD_BRING_ONE_HAN_DEVICE. */
	private static final String SHOULD_BRING_ONE_HAN_DEVICE = "should bring one han device ";

	/** The Constant SHOULD_BRING_THE_FLEX_NET_ID. */
	private static final String SHOULD_BRING_THE_FLEX_NET_ID = "should bring the FlexNet ID";

	/** The Constant SHOULD_BRING_ONE_DEVICE. */
	private static final String SHOULD_BRING_ONE_DEVICE = "should bring one device ";

	/** The Constant SHOULD_HAVE_NO_MESSAGES. */
	private static final String SHOULD_HAVE_NO_MESSAGES = "should have no messages";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "name";

	/** The Constant DEVICE_ID_INVALID. */
	private static final String DEVICE_ID_INVALID = "123456789012345678901234567890M";

	/** The Constant ZIP_CODE_INVALID. */
	private static final String ZIP_CODE_INVALID = "100010000000";

	/** The Constant DUMMY_FIRMWARE_VERSION. */
	private static final String DUMMY_FIRMWARE_VERSION = "1.0";

	/** The Constant AN_X. */
	private static final String AN_X = "x";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant HAN_DEVICES_TYPE_REQUIRED. */
	private static final String DEVICE_MODEL_MANUFACTURE_REQUIRED =
			"sensus.epm.devicemodelvalidator.manufacture.required";

	/** The Constant HAN_DEVICES_TYPE_REQUIRED. */
	private static final String HAN_DEVICES_TYPE_REQUIRED = "sensus.epm.handevicevalidator.han.devices.type.required";

	/** The Constant LCM_TYPE_REQUIRED. */
	private static final String LCM_TYPE_REQUIRED = "sensus.epm.lcmvalidator.lcm.type.required";

	/** The Constant DEVICE_SEARCH_REQUIRED. */
	private static final String DEVICE_SEARCH_REQUIRED = "sensus.epm.devicesearchvalidator.device.search.required";

	/** The Constant START_DATE_TIME_REQUIRED. */
	private static final String START_DATE_TIME_REQUIRED = "sensus.epm.rangedatevalidator.start.date.time.required";

	/** The Constant END_DATE_TIME_REQUIRED. */
	private static final String END_DATE_TIME_REQUIRED = "sensus.epm.rangedatevalidator.end.date.time.required";

	/** The Constant CUSTOMER_ID_REQUIRED. */
	private static final String RADIO_CUSTOMER_ID_REQUIRED = "sensus.epm.radiovalidator.customer.id.required";

	/** The Constant HAN_DEVICE_SEARCH_REQUIRED. */
	private static final String HAN_DEVICE_SEARCH_REQUIRED =
			"sensus.epm.devicesearchvalidator.han.device.search.required";

	/** The Constant HAN_DEVICE_TYPE_REQUIRED. */
	private static final String HAN_DEVICE_TYPE_REQUIRED = "sensus.epm.devicesearchvalidator.han.device.type.required";

	/** The Constant DEVICE_TYPE_REQUIRED. */
	private static final String DEVICE_TYPE_REQUIRED = "sensus.epm.devicevalidator.devicetype.required";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF; // injected by Spring through setter

	/**
	 * Gets the electric meter bcf.
	 * 
	 * @return the electric meter bcf
	 */
	public IElectricMeterBCF getElectricMeterBCF()
	{
		return electricMeterBCF;
	}

	/**
	 * Sets the electric meter bcf.
	 * 
	 * @param electricMeterBCF the new electric meter bcf
	 */
	@Resource
	public void setElectricMeterBCF(IElectricMeterBCF electricMeterBCF)
	{
		this.electricMeterBCF = electricMeterBCF;
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
		setArea(getElectricMeterBCF(), EPMAreaEnum.DEVICE);
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
		resetMocksData(getElectricMeterBCF());
		setDeviceArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all electric meters success.
	 */
	@Test
	public void testFetchAllElectricMetersSuccess()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		Radio radio = new Radio(ELECTRIC_FLEXNET_ID);
		radio.setLocation(new Location(ADDRESS, CITY, ZIP));
		ElectricMeter electric = new ElectricMeter(radio);

		DeviceSearch search = new DeviceSearch(new ElectricMeterSearch(electric));
		request.setDeviceSearch(search);

		request.getSortExpressions().add(new SortExpression(ORDER_BY, Direction.Ascending));

		electric.setConfiguration(new ElectricMeterConfiguration(DUMMY_FIRMWARE_VERSION));

		InquiryDeviceResponse response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test fetch all han devices success.
	 */
	@Test
	public void testFetchAllHanDevicesSuccess()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		Radio radio = new Radio(ELECTRIC_FLEXNET_ID);
		radio.setLocation(new Location(ADDRESS, CITY, ZIP));
		HanDevice hanDevice = new HanDevice(radio);

		DeviceSearch search = new DeviceSearch(new HanDeviceSearch(hanDevice));
		search.getHanDeviceSearch().setHanDeviceTypeEnumList(new ArrayList<HanDeviceTypeEnum>());
		search.getHanDeviceSearch().getHanDeviceTypeEnumList().add(HanDeviceTypeEnum.IHD);
		request.setDeviceSearch(search);
		request.getSortExpressions().add(new SortExpression(ORDER_BY, Direction.Ascending));

		InquiryDeviceResponse response = getElectricMeterBCF().fetchAllHanDevices(request);
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test fetch all lcm success.
	 */
	@Test
	public void testFetchAllLCMSuccess()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		Radio radio = new Radio(ELECTRIC_FLEXNET_ID);
		radio.setLocation(new Location(ADDRESS, CITY, ZIP));
		LCM lcm = new LCM(radio);

		DeviceSearch search = new DeviceSearch(new LCMSearch(lcm));
		request.setDeviceSearch(search);

		request.getSortExpressions().add(new SortExpression(ORDER_BY, Direction.Ascending));

		InquiryDeviceResponse response = getElectricMeterBCF().fetchAllLCMs(request);
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test fetch all electric meters.
	 */
	@Test
	public void testFetchAllElectricMeters()
	{
		// Validation situation - Order By and Start Row
		InquiryDeviceRequest request = new InquiryDeviceRequest();
		request.setPageSize(FIFTEEN);
		request.setStartRow(null);

		InquiryDeviceResponse response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED, START_ROW_INVALID);

		// Validation situation - User Context Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setUserContext(null);
		response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// // Validation situation - Device id length is invalid
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		// ElectricMeterSearch eletricMeterSearch = new ElectricMeterSearch(new ElectricMeter(DEVICE_ID_INVALID));
		// request.setDeviceSearch(new DeviceSearch(eletricMeterSearch));
		// response = getElectricMeterBCF().fetchAllElectricMeters(request);
		// assertFalse(response.isOperationSuccess());
		// assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Convert Firmware
		ElectricMeter electricMeter = new ElectricMeter(DEVICE_ID);
		electricMeter.setConfiguration(new ElectricMeterConfiguration(DUMMY_FIRMWARE_VERSION));
		ElectricMeterSearch eletricMeterSearch = new ElectricMeterSearch(electricMeter);
		// eletricMeterSearch.setElectricMeter(electricMeter);
		request.setDeviceSearch(new DeviceSearch(eletricMeterSearch));

		response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - Address, City and zip invalid
		Location location = new Location(
				StringUtils.repeat(AN_X, OVERFLOW_ADDRESS_LENGTH),
				StringUtils.repeat(AN_X, TWENTY_SIX),
				ZIP_CODE_INVALID);
		electricMeter.setRadio(new Radio(location));
		eletricMeterSearch.setElectricMeter(electricMeter);
		request.setDeviceSearch(new DeviceSearch(eletricMeterSearch));
		response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ADDRESS_INVALID, CITY_INVALID, ZIP_INVALID);

		// Success situation
		electricMeter.setRadio(new Radio(new Location(ADDRESS, CITY, ZIP)));
		eletricMeterSearch.setElectricMeter(electricMeter);
		request.setDeviceSearch(new DeviceSearch(eletricMeterSearch));
		response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllElectricMeters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all electric meters.
	 */
	@Test
	public void testFetchAllLCMs()
	{
		// Validation situation - Order By and Start Row
		InquiryDeviceRequest request = new InquiryDeviceRequest();
		request.setPageSize(FIFTEEN);
		request.setStartRow(null);
		InquiryDeviceResponse response = getElectricMeterBCF().fetchAllLCMs(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED,
				START_ROW_INVALID);

		// Validation situation - User Context Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setUserContext(null);
		response = getElectricMeterBCF().fetchAllLCMs(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device id length is invalid
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setDeviceSearch(new DeviceSearch(new LCMSearch(new LCM(DEVICE_ID_INVALID))));
		response = getElectricMeterBCF().fetchAllLCMs(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Convert Firmware
		LCM lcm = new LCM(DEVICE_ID);
		lcm.setConfiguration(new LCMConfiguration("5.3"));
		request.setDeviceSearch(new DeviceSearch(new LCMSearch(lcm)));

		response = getElectricMeterBCF().fetchAllLCMs(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - Address, City and zip invalid
		lcm.setRadio(new Radio(new Location(StringUtils.repeat(AN_X, OVERFLOW_ADDRESS_LENGTH),
				StringUtils.repeat(AN_X, TWENTY_SIX), ZIP_CODE_INVALID)));
		request.setDeviceSearch(new DeviceSearch(new LCMSearch(lcm)));
		response = getElectricMeterBCF().fetchAllLCMs(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ADDRESS_INVALID, CITY_INVALID, ZIP_INVALID);

		// Success situation
		lcm.setRadio(new Radio(new Location(ADDRESS, CITY, ZIP)));
		request.setDeviceSearch(new DeviceSearch(new LCMSearch(lcm)));
		response = getElectricMeterBCF().fetchAllLCMs(request);

		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllLCMs(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllLCMs(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all electric meters.
	 */
	@Test
	public void testFetchAllHanDevices()
	{
		// Validation situation - Order By and Start Row
		InquiryDeviceRequest request = new InquiryDeviceRequest();
		request.setPageSize(FIFTEEN);
		request.setStartRow(null);
		InquiryDeviceResponse response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED,
				START_ROW_INVALID);

		// Validation situation - User Context Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setUserContext(null);
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device Type
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_TYPE_REQUIRED);

		// Validation situation - Device search
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_SEARCH_REQUIRED);

		// Validation situation - Han Device Search Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setDeviceSearch(new DeviceSearch());
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, HAN_DEVICE_SEARCH_REQUIRED);

		// Validation situation - Han Device Type Required
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setDeviceSearch(new DeviceSearch(new HanDeviceSearch()));
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, HAN_DEVICE_TYPE_REQUIRED);

		// Validation situation - success
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		HanDeviceSearch hanDeviceSearch = new HanDeviceSearch();
		hanDeviceSearch.addHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
		request.setDeviceSearch(new DeviceSearch(hanDeviceSearch));
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Validation situation - Device id length is invalid
		request = TestBaseUtil.createInquiryDeviceRequest();
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		HanDevice hanDevice = new HanDevice();
		hanDevice.setDeviceId(DEVICE_ID_INVALID);
		request.setDeviceSearch(new DeviceSearch(new HanDeviceSearch(hanDevice)));
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Address, City and zip invalid
		Location location =
				new Location(StringUtils.repeat("OVER 50 CHARS", FOUR), StringUtils.repeat("OVER 25 CHARS",
						FOUR), ZIP_CODE_INVALID);
		Radio radio = new Radio(location);
		hanDevice.setDeviceId(DEVICE_ID);
		hanDevice.setRadio(radio);
		request.setDeviceSearch(new DeviceSearch(new HanDeviceSearch(hanDevice)));
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ADDRESS_INVALID, CITY_INVALID, ZIP_INVALID);

		// Success situation

		hanDevice.setRadio(new Radio(new Location(ADDRESS, CITY, ZIP)));
		hanDeviceSearch.setHanDevice(hanDevice);
		request.setDeviceSearch(new DeviceSearch(hanDeviceSearch));
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllHanDevices(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchDeviceById()
	{
		// Validation Situation - device is required
		DeviceResponse response = getElectricMeterBCF().fetchDeviceById(new DeviceRequest());

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation Situation - flexnet id or device id is required
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new HanDevice());
		request.getFirstDevice().setDeviceType(null);

		response = getElectricMeterBCF().fetchDeviceById(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED, DEVICE_ID_REQUIRED, DEVICE_TYPE_ENUM_REQUIRED);

		// Test HAN Device

		// Success situation
		request.getDevices().clear();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, ELECTRIC_FLEXNET_ID, null));
		response = getElectricMeterBCF().fetchDeviceById(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());
		assertNotNull(SHOULD_BRING_THE_FLEX_NET_ID, response.getDevices().get(ZERO).getRadio().getFlexNetId());

		// Test Meter

		// Success situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, ELECTRIC_FLEXNET_ID, null));
		response = getElectricMeterBCF().fetchDeviceById(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_DEVICE, ONE, response.getDevices().size());
		assertNotNull(SHOULD_BRING_THE_FLEX_NET_ID, response.getDevices().get(ZERO).getRadio().getFlexNetId());

		// Error situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchDeviceById(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchDeviceById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateDevicesCSV()
	{
		// Validation situation - process id, file name, tenant and user context is required.
		InquiryDeviceRequest inquiryDeviceRequest = new InquiryDeviceRequest();
		inquiryDeviceRequest.setDeviceSearch(new DeviceSearch(new ElectricMeterSearch(createMeter(DEVICE_ID,
				CITY, ZIP, ADDRESS))));
		inquiryDeviceRequest.getDeviceSearch().getElectricMeterSearch().setElectricMeter(new ElectricMeter("1"));
		inquiryDeviceRequest.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));

		InquiryDeviceResponse response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED, TENANT_REQUIRED);

		// Validation situation - process id, tenant customer id and file name is required.
		inquiryDeviceRequest = TestBaseUtil.createInquiryDeviceRequest();
		inquiryDeviceRequest.setDeviceSearch(new DeviceSearch(new ElectricMeterSearch(createMeter(DEVICE_ID,
				CITY, ZIP, ADDRESS))));
		inquiryDeviceRequest.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		inquiryDeviceRequest.setTenant(TestBaseUtil.createEmptyTenant());
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, CUSTOMER_ID_REQUIRED);

		// Validation situation - process id and file name is required.
		inquiryDeviceRequest = TestBaseUtil.createInquiryDeviceRequest();
		inquiryDeviceRequest.setDeviceSearch(new DeviceSearch(new ElectricMeterSearch(createMeter(DEVICE_ID,
				CITY, ZIP, ADDRESS))));
		inquiryDeviceRequest.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		inquiryDeviceRequest.setTenant(TestBaseUtil.createTenant());
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED);

		// Success situation
		inquiryDeviceRequest.setFileName(FILE_NAME);
		inquiryDeviceRequest.setProcessId(ONE);
		response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

		resetMocksToDeviceArea();
		// Error
		// Validation situation - process id and file name is required.
		inquiryDeviceRequest = TestBaseUtil.createInquiryDeviceRequest();
		inquiryDeviceRequest.setDeviceSearch(new DeviceSearch(new HanDeviceSearch()));
		inquiryDeviceRequest.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		inquiryDeviceRequest.setTenant(TestBaseUtil.createTenant());
		inquiryDeviceRequest.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		inquiryDeviceRequest.setFileName(FILE_NAME);
		inquiryDeviceRequest.setProcessId(ONE);
		response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, HAN_DEVICE_TYPE_REQUIRED);

		// Success situation
		inquiryDeviceRequest.getDeviceSearch().getHanDeviceSearch().addHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
		response = getElectricMeterBCF().generateDevicesCSV(inquiryDeviceRequest);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

	}

	/**
	 * Test generate peak demands csv.
	 */
	@Test
	public void testGeneratePeakDemandsCSV()
	{
		// Validation situation - process id, file name, tenant and user context is required.
		DeviceReadingRequest request = new DeviceReadingRequest();
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setDateFormat(FORMATTED_DATE);

		InquiryPeakDemandResponse response = getElectricMeterBCF().generatePeakDemandCSV(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED, TENANT_REQUIRED);

		// Validation situation - process id, tenant customer id and file name is required.
		request = TestBaseUtil.createDeviceReadingRequest();
		request.setTenant(TestBaseUtil.createEmptyTenant());
		request.addSortExpressions(new SortExpression(ORDER_BY, SortExpression.Direction.Ascending));
		request.setDateFormat(FORMATTED_DATE);
		response = getElectricMeterBCF().generatePeakDemandCSV(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, CUSTOMER_ID_REQUIRED);

		// Without starDate and endDate
		request.setDevice(TestBaseUtil.createDevice());
		request.getDevice().setDeviceId(DEVICE_ID);
		request.setTenant(TestBaseUtil.createTenant());
		request.setFileName(FILE_NAME);
		request.setProcessId(ONE);
		request.setDateFormat(FORMATTED_DATE);
		response = getElectricMeterBCF().generatePeakDemandCSV(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setEndDate(new Date());
		request.setInitialDate(new Date());

		response = getElectricMeterBCF().generatePeakDemandCSV(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().generatePeakDemandCSV(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().generatePeakDemandCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch lifecycle states.
	 */
	@Test
	public void testFetchLifecycleStates()
	{
		// Success situation
		DeviceRequest request = new DeviceRequest();
		DeviceResponse response = getElectricMeterBCF().fetchLifecycleStates(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals("should bring one lifecycle states ", ONE, response.getLifecycleStates().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchLifecycleStates(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchLifecycleStates(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Testfetch all han devices by meter.
	 */
	@Test
	public void testfetchAllHanDevicesByMeter()
	{
		// Validation situation - flexnet id is required.
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new Device(new Radio()));
		DeviceResponse response = getElectricMeterBCF().fetchAllHanDevicesByMeter(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED);

		// Success situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER));

		response = getElectricMeterBCF().fetchAllHanDevicesByMeter(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals("should bring three han device ", THREE, response.getDevices().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllHanDevicesByMeter(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllHanDevicesByMeter(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all manufacture by device sub type.
	 */
	@Test
	public void testFetchAllManufactureByDeviceSubType()
	{
		// Validation situation - HanDevice type enum is required
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		HanDevice hanDevice = new HanDevice();
		request.addDevice(hanDevice);
		DeviceResponse response = getElectricMeterBCF().fetchAllManufactureByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, HAN_DEVICES_TYPE_REQUIRED);

		// Success situation
		hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
		response = getElectricMeterBCF().fetchAllManufactureByDeviceSubType(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(SHOULD_BRING_ONE_HAN_DEVICE, ONE, response.getDevices().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllManufactureByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllManufactureByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

		resetMocksToDeviceArea();

		// Test LCM
		// Validation situation - LCM type enum is required
		request = TestBaseUtil.createDeviceRequest();
		LCM lcm = new LCM();
		request.addDevice(lcm);
		response = getElectricMeterBCF().fetchAllManufactureByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, LCM_TYPE_REQUIRED);

		// Success situation
		lcm.setLcmTypeEnum(LCMTypeEnum.LCM);
		response = getElectricMeterBCF().fetchAllManufactureByDeviceSubType(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(SHOULD_BRING_ONE_HAN_DEVICE, ONE, response.getDevices().size());
	}

	/**
	 * Test fetch all model by device sub type.
	 */
	@Test
	public void testFetchAllModelByDeviceSubType()
	{
		// Validation situation - HanDevice type and manufacture is required
		HanDevice hanDevice = new HanDevice(new DeviceModel());

		DeviceRequest request = new DeviceRequest(hanDevice);

		DeviceResponse response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, HAN_DEVICES_TYPE_REQUIRED);

		// Validation situation - manufacture is required
		hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.THERMOSTAT);

		response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_MODEL_MANUFACTURE_REQUIRED);

		// Success situation
		hanDevice.getDeviceModel().setManufacture(MANUFACTURE);

		response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(SHOULD_BRING_TWO_HAN_DEVICE, TWO, response.getDevices().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

		resetMocksToDeviceArea();

		// Test LCM
		// Validation situation - LCM type and manufacture is required
		LCM lcm = new LCM();
		lcm.setDeviceModel(new DeviceModel());

		request = new DeviceRequest();
		request.addDevice(lcm);

		response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, LCM_TYPE_REQUIRED);

		// Validation situation - manufacture is required
		lcm.setLcmTypeEnum(LCMTypeEnum.LCM);

		response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_MODEL_MANUFACTURE_REQUIRED);

		// Success situation
		lcm.getDeviceModel().setManufacture(MANUFACTURE);

		response = getElectricMeterBCF().fetchAllModelByDeviceSubType(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals(SHOULD_BRING_TWO_HAN_DEVICE, TWO, response.getDevices().size());
	}

	/**
	 * Test fetch all connection state.
	 */
	@Test
	public void testFetchAllConnectionState()
	{
		// Success
		DeviceRequest request = new DeviceRequest();
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
		electricMeterSearch.addRemoteConnectStatusEnum(RemoteConnectStatusEnum.UNKNOWN);
		electricMeterSearch.addRemoteConnectStatusEnum(RemoteConnectStatusEnum.CONNECT);
		electricMeterSearch.addRemoteConnectStatusEnum(RemoteConnectStatusEnum.DISCONNECT);
		DeviceSearch deviceSearch = new DeviceSearch(electricMeterSearch);

		request.setDeviceSearch(deviceSearch);

		DeviceResponse response = getElectricMeterBCF().fetchAllConnectionState(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllConnectionState(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllConnectionState(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch all peak demand.
	 */
	@Test
	public void testFetchAllPeakDemand()
	{
		// test fail (without device)
		DeviceReadingRequest request = TestBaseUtil.createDeviceReadingRequest();
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		InquiryPeakDemandResponse response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// test fail (without deviceID)
		request.setDevice(new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID)));
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_ID_REQUIRED);

		// test fail (without order by)
		request = TestBaseUtil.createDeviceReadingRequest();
		request.setDevice(new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID)));
		request.getDevice().setDeviceId(DEVICE_ID);
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED);

		// test fail (without customer ID)
		request = TestBaseUtil.createDeviceReadingRequest(PeakDemandOrderByEnum.DEMAND_RESET_COUNT);
		request.setDevice(new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID)));
		request.getDevice().setDeviceId(DEVICE_ID);
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, RADIO_CUSTOMER_ID_REQUIRED);

		// test fail (without start date)
		request = TestBaseUtil.createDeviceReadingRequest(PeakDemandOrderByEnum.DEMAND_RESET_COUNT);
		request.setDevice(new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID)));
		request.getDevice().setDeviceId(DEVICE_ID);
		request.setEndDate(new Date());
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, START_DATE_TIME_REQUIRED);

		// test fail (without end date)
		request.setInitialDate(new Date());
		request.setEndDate(null);
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, END_DATE_TIME_REQUIRED);

		// test fail (without flexnet ID)
		request = TestBaseUtil.createDeviceReadingRequest(PeakDemandOrderByEnum.DEMAND_RESET_COUNT);
		request.setDevice(new ElectricMeter(new Radio(CUSTOMER_ID)));
		request.getDevice().setDeviceId(DEVICE_ID);
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED);

		// test success
		request.getDevice().getRadio().setFlexNetId(ELECTRIC_FLEXNET_ID);
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchAllPeakDemand(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch lcm relay by device id.
	 */
	@Test
	public void testfetchLCMRelaysByDevice()
	{
		// Validation situation - flexnet id is required.
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new Device(new Radio()));
		DeviceResponse response = getElectricMeterBCF().fetchLCMRelaysByDevice(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED);

		// Success situation
		request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM));

		response = getElectricMeterBCF().fetchLCMRelaysByDevice(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertEquals("should bring three lcm relay ", THREE, response.getLcmRelays().size());

		// Error situation
		setSituation(getElectricMeterBCF(), SituationsEnum.ERROR, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchLCMRelaysByDevice(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();

		// Exception situation
		setSituation(getElectricMeterBCF(), SituationsEnum.EXCEPTION, IElectricMeterBCL.class);
		response = getElectricMeterBCF().fetchLCMRelaysByDevice(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Create the meter.
	 * 
	 * @param deviceId the device id
	 * @param cityName the city name
	 * @param zip the zip
	 * @param address the address
	 * @return the meter
	 */
	private ElectricMeter createMeter(String deviceId, String cityName, String zip, String address)
	{
		return populatedDevice(new ElectricMeter(), DeviceTypeEnum.ELECTRIC_METER, deviceId, cityName, zip, address);
	}
}
