package com.sensus.dm.elec.device.bcf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.Message;
import com.sensus.common.model.response.Response;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.bcl.IDeviceReadingBCL;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryIntervalReadResponse;
import com.sensus.dm.elec.device.model.response.InquiryLoadProfileResponse;
import com.sensus.dm.elec.device.model.response.TOUReadResponse;

/**
 * Unit tests for the class DeviceReadingBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/elec/device/devicereadingbcfimpltest.xml"})
public class DeviceReadingBCFImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant GENERATE_FILE_CSV_SNAPSHOT. */
	private static final String GENERATE_FILE_CSV_SNAPSHOT = "generateFileCSVSnapshot";

	/** The Constant GENERATE_FILE_CSV_INTERVAL_READ. */
	private static final String GENERATE_FILE_CSV_INTERVAL_READ = "generateFileCSVIntervalRead";

	/** The Constant GENERATE_FILE_CSV_LOAD_PROFILE_READ. */
	private static final String GENERATE_FILE_CSV_LOAD_PROFILE_READ = "generateFileCSVLoadProfileRead";

	/** The Constant GENERATE_FILE_CSV_TOU_READ. */
	private static final String GENERATE_FILE_CSV_TOU_READ = "generateFileCSVTOURead";

	/** The Constant GENERATE_FILE_CSV_WATER_GAS_DATA_READ. */
	private static final String GENERATE_FILE_CSV_WATER_GAS_DATA_READ = "generateFileCSVWaterGasDataRead";

	/** The Constant FETCH_ALL_WATER_GAS_READ. */
	private static final String FETCH_ALL_WATER_GAS_READ = "fetchAllWaterGasDataRead";

	/** The Constant FETCH_ALL_TOU_READ. */
	private static final String FETCH_ALL_TOU_READ = "fetchAllTOURead";

	/** The Constant FETCH_ALL_INTERVAL_READ. */
	private static final String FETCH_ALL_INTERVAL_READ = "fetchAllIntervalRead";

	/** The Constant FETCH_ALL_LOAD_PROFILE_READ. */
	private static final String FETCH_ALL_LOAD_PROFILE_READ = "fetchAllLoadProfileRead";

	/** The Constant FETCH_ALL_SNAPSHOTS. */
	private static final String FETCH_ALL_SNAPSHOTS = "fetchAllSnapshots";

	/** The Constant FETCH_UPDATED_LOAD_PROFILE. */
	private static final String FETCH_UPDATED_LOAD_PROFILE = "fetchUpdatedMeterLoadProfile";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant START_DATE_TIME_REQUIRED. */
	private static final String START_DATE_TIME_REQUIRED = "sensus.epm.rangedatevalidator.start.date.time.required";

	/** The Constant END_DATE_TIME_REQUIRED. */
	private static final String END_DATE_TIME_REQUIRED = "sensus.epm.rangedatevalidator.end.date.time.required";

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant DATE_FORMAT_REQUIRED. */
	private static final String DATE_FORMAT_REQUIRED =
			"sensus.epm.tenantrequestvalidator.dateformat.required";

	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device reading bcf. */
	private IDeviceReadingBCF deviceReadingBCF; // injected by Spring through setter

	/**
	 * Gets the device reading bcf.
	 * 
	 * @return the device reading bcf
	 */
	public IDeviceReadingBCF getDeviceReadingBCF()
	{
		return deviceReadingBCF;
	}

	/**
	 * Sets the device reading bcf.
	 * 
	 * @param deviceReadingBCF the new device reading bcf
	 */
	@Resource
	public void setDeviceReadingBCF(IDeviceReadingBCF deviceReadingBCF)
	{
		this.deviceReadingBCF = deviceReadingBCF;
	}

	// Test Settings:

	/**
	 * Sets the device reading area.
	 */
	public void setDeviceReadingArea()
	{
		setArea(getDeviceReadingBCF(), EPMAreaEnum.DEVICE_READING);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setDeviceReadingArea();
	}

	/**
	 * Removes the custom search area.
	 */
	@After
	public void resetMock()
	{
		resetMocksData(getDeviceReadingBCF());
		setDeviceReadingArea();
	}

	// Tests:

	/**
	 * Prints the message.
	 * 
	 * @param response the response
	 */
	private void printMessage(Response response)
	{
		System.out.println("--------------------");
		for (Message m : response.getMessageList())
		{
			System.out.println(m.getMessageInfo().getCode() + " - " + m.getText());
		}

	}

	/**
	 * Test fecth all water data read.
	 */
	@Test
	public void testFecthAllWaterGasDataRead()
	{
		// Validation situation - User Context required and Service Type required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation situation - Service Type required
		request = TestBaseUtil.createDeviceReadingRequest();
		response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation situation - Device object is required
		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(DEVICE_ID_WATER_B75505999);
		response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, FETCH_ALL_WATER_GAS_READ);
		response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class, FETCH_ALL_WATER_GAS_READ);
		response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch all TOU read.
	 */
	@Test
	public void testFetchAllTOURead()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		TOUReadResponse response = getDeviceReadingBCF().fetchAllTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest();
		response = getDeviceReadingBCF().fetchAllTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().fetchAllTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().fetchAllTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().fetchAllTOURead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, FETCH_ALL_TOU_READ);
		response = getDeviceReadingBCF().fetchAllTOURead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class, FETCH_ALL_TOU_READ);
		response = getDeviceReadingBCF().fetchAllTOURead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test generate file CSV TOU read.
	 */
	@Test
	public void testGenerateFileCSVTOURead()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		TOUReadResponse response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED);

		// Validation situation - User Context required
		request = new DeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.getUserContext().setLocaleString(LOCALE);
		response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, GENERATE_FILE_CSV_TOU_READ);
		response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_TOU_READ);
		response = getDeviceReadingBCF().generateFileCSVTOURead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	// Load profile interface:
	/**
	 * Test fecth all load profile read.
	 */
	@Test
	public void testFecthAllLoadProfileRead()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest();
		response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, FETCH_ALL_LOAD_PROFILE_READ);
		response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				FETCH_ALL_LOAD_PROFILE_READ);
		response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	// Interval reads interface:

	/**
	 * Test fecth all interval read.
	 */
	@Test
	public void testFecthAllIntervalRead()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest();
		response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, FETCH_ALL_INTERVAL_READ);
		response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class, FETCH_ALL_INTERVAL_READ);
		response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test generate file csv water data read.
	 */
	@Test
	public void testgenerateFileCSVWaterGasDataRead()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED,
				SERVICE_TYPE_ENUM_REQUIRED);

		// Validation situation - User Context required
		request = new DeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest(ServiceTypeEnum.ELECTRIC);
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.getUserContext().setLocaleString(LOCALE);
		response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_WATER_GAS_DATA_READ);
		response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_WATER_GAS_DATA_READ);
		response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test generate file csv load profile read.
	 */
	@Test
	public void testGenerateFileCSVLoadProfileRead()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED, DATE_FORMAT_REQUIRED);

		// Validation situation - User Context required
		request = new DeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.setDateFormat(FORMATTED_DATE);
		response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.setDateFormat(FORMATTED_DATE);
		request.getUserContext().setLocaleString(LOCALE);
		response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_LOAD_PROFILE_READ);
		response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_LOAD_PROFILE_READ);
		response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test generate file csv interval read.
	 */
	@Test
	public void testGenerateFileCSVIntervalRead()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED, DATE_FORMAT_REQUIRED);

		// Validation situation - User Context required
		request = new DeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.setDateFormat(FORMATTED_DATE);
		response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.setDateFormat(FORMATTED_DATE);
		request.getUserContext().setLocaleString(LOCALE);
		response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_INTERVAL_READ);
		response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_INTERVAL_READ);
		response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test generate file csv snapshot.
	 */
	@Test
	public void testGenerateFileCSVSnapshot()
	{
		// Validation situation - User Context required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED, DATE_FORMAT_REQUIRED);

		// Validation situation - User Context required
		request = new DeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.setDateFormat(FORMATTED_DATE);
		response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Device object is required
		request = TestBaseUtil.createDeviceReadingRequest();
		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.setDateFormat(FORMATTED_DATE);
		request.getUserContext().setLocaleString(LOCALE);
		response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_SNAPSHOT);
		response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				GENERATE_FILE_CSV_SNAPSHOT);
		response = getDeviceReadingBCF().generateFileCSVSnapshot(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	// Snapshot interface:

	/**
	 * Test fetch snapshots.
	 */
	@Test
	public void testFetchSnapshots()
	{
		// Validation situation - User Context required and Service Type required
		DeviceReadingRequest request = new DeviceReadingRequest();
		InquiryIntervalReadResponse response = getDeviceReadingBCF().fetchAllSnapshots(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Service Type required
		request = TestBaseUtil.createDeviceReadingRequest();
		response = getDeviceReadingBCF().fetchAllSnapshots(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.setDevice(device);
		response = getDeviceReadingBCF().fetchAllSnapshots(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Validation situation - Start Date/Time is required and End Date/Time is required
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().fetchAllSnapshots(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, START_DATE_TIME_REQUIRED, END_DATE_TIME_REQUIRED);

		// Success situation
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		response = getDeviceReadingBCF().fetchAllSnapshots(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, FETCH_ALL_SNAPSHOTS);
		response = getDeviceReadingBCF().fetchAllSnapshots(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class, FETCH_ALL_SNAPSHOTS);
		response = getDeviceReadingBCF().fetchAllSnapshots(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch updated meter load profile electric.
	 */
	@Test
	public void testFetchUpdatedMeterLoadProfileElectric()
	{
		// Validation situation - User Context required and Service Type required
		DeviceRequest request = new DeviceRequest();
		InquiryLoadProfileResponse response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Service Type required
		request = TestBaseUtil.createDeviceRequest();
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER);

		// Validation situation - Device id is required
		request.addDevice(device);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Success situation
		device.setDeviceId(ELECTRIC_METER_ID);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, FETCH_UPDATED_LOAD_PROFILE);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				FETCH_UPDATED_LOAD_PROFILE);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch updated meter load profile water.
	 */
	@Test
	public void testFetchUpdatedMeterLoadProfileWater()
	{
		// Validation situation - User Context required and Service Type required
		DeviceRequest request = new DeviceRequest();
		InquiryLoadProfileResponse response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - Service Type required
		request = TestBaseUtil.createDeviceRequest();
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_REQUIRED);

		Device device = TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER);

		// Validation situation - Device id is required
		request.addDevice(device);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		printMessage(response);
		assertMessages(response, DEVICE_ID_REQUIRED);

		// Success situation
		device.setDeviceId(DEVICE_ID_WATER_B75505999);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.ERROR, IDeviceReadingBCL.class, FETCH_UPDATED_LOAD_PROFILE);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMock();

		// Exception situation
		setSituation(getDeviceReadingBCF(), SituationsEnum.EXCEPTION, IDeviceReadingBCL.class,
				FETCH_UPDATED_LOAD_PROFILE);
		response = getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);

	}
}
