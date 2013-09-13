package com.sensus.dm.elec.device.bcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.bcf.meterreading.service.MeterReadingService;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.IntervalRead;
import com.sensus.dm.elec.device.model.LoadProfile;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * Unit tests for the Class DeviceReadingBCLImpl.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/elec/device/devicereadingbclimpltest.xml"})
public class DeviceReadingBCLImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant PRINT_DATE. */
	private static final String PRINT_DATE = "Date:" + SPACE;

	/** The Constant YEAR_2013. */
	private static final int YEAR_2013 = 2013;

	/** The Constant MONTH_3. */
	private static final int MONTH_3 = 3;

	/** The Constant DAY_OF_MONTH_14. */
	private static final int DAY_OF_MONTH_14 = 14;

	/** The Constant STR_SEPARATOR. */
	private static final String STR_SEPARATOR = "#########";

	/** The Constant STR_A_DEVICE_ID_234561299M. */
	private static final String STR_A_DEVICE_ID_234561299M = "234561299M";

	/** The Constant STR_A_DEVICE_ID_GAS_1806. */
	private static final String STR_A_DEVICE_ID_GAS_1806 = "GAS 1806";

	/** The Constant GET_METER_READINGS. */
	private static final String GET_METER_READINGS = "getMeterReadings";

	/** The Constant GET_LATEST_METER_READING. */
	private static final String GET_LATEST_METER_READING = "getLatestMeterReading";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION. */
	private static final String SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION =
			"sensus.dm.common.process.nc.defaultexception";

	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device reading bcl. */
	private IDeviceReadingBCL deviceReadingBCL;

	/** The process bcf. */
	private IProcessBCF processBCF;

	/**
	 * Gets the device reading bcl.
	 * 
	 * @return the device reading bcl
	 */
	public IDeviceReadingBCL getDeviceReadingBCL()
	{
		return deviceReadingBCL;
	}

	/**
	 * Sets the device reading bcl.
	 * 
	 * @param deviceReadingBCL the new device reading bcl
	 */
	@Resource(name = "deviceReadingBCLTarget")
	public void setDeviceReadingBCL(IDeviceReadingBCL deviceReadingBCL)
	{
		this.deviceReadingBCL = deviceReadingBCL;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	// Test Settings:

	/**
	 * Sets the device reading area.
	 */
	public void setDeviceReadingArea()
	{
		setArea(getDeviceReadingBCL(), EPMAreaEnum.DEVICE_READING);
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
		resetMocksData(getDeviceReadingBCL());
		setDeviceReadingArea();
	}

	// TOU (Time Of Use) interface:

	/**
	 * Test fetch all tou read.
	 */
	@Test
	public void testFetchAllTOURead()
	{
		// Success situation
		DeviceReadingRequest request = new DeviceReadingRequest();

		TimeZoneInfo timeZoneInfo = new TimeZoneInfo();
		timeZoneInfo.setDisplayName(TIME_ZONE_AMERICA_SAO_PAULO);

		ElectricMeter meter = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(
				timeZoneInfo)), DEVICE_ID);

		// real meter: comment this part when running against mock
		// ElectricMeter meter = new ElectricMeter(new Radio(new BigInteger("4021568"), CUSTOMER_ID, new Location(
		// timeZoneInfo)), "13169804");

		request.setDevice(meter);

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, NEGATIVE_TEN);

		request.setInitialDate(calendar.getTime());
		request.setEndDate(new Date());

		InternalResultsResponse<String[][]> response = getDeviceReadingBCL().fetchAllTOURead(request);

		assertFalse(response.isInError());
		assertTrue(response.getResultsList().size() > 0);

		// comment this part when NOT running against mock
		// assertEquals("Tier Id", resultArray[0][0]);
		// assertEquals("Tier 1", resultArray2[1][0]);
		// assertEquals("11", resultArray2[2][0]);

		// Exception situation
		// Note: a runtime exception in MeterReadingService does not cause an error message
		// in DeviceReadingBCL, just an empty array to be returned
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_METER_READINGS);

		response = getDeviceReadingBCL().fetchAllTOURead(request);

		// comment this part when running against mock
		// String[][] strs = response.getFirstResult();
		//
		// for (int line = 0; line < strs.length; line++)
		// {
		// for (int col = 0; col < strs[line].length; col++)
		// {
		// System.out.println(strs[line][col]);
		// }
		// System.out.println("---");
		//
		// }

		// comment this part when NOT running against mock
		assertEquals(response.getStatus(), Status.ExternalError);
		assertMessages(response, SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION);
	}

	/**
	 * Test generate file csvtou read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVTOURead() throws Exception
	{
		// Success situation
		DeviceReadingRequest request = new DeviceReadingRequest();

		ElectricMeter meter = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(
				TestBaseUtil.createTimeZoneInfo())), DEVICE_ID);
		request.setDevice(meter);

		request.setProcessId(FIFTEEN);
		request.setFileName(FILE_NAME);
		request.setUserContext(TestBaseUtil.createUserContext());

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, NEGATIVE_TEN);

		request.setInitialDate(calendar.getTime());
		request.setEndDate(new Date());

		request.setInitialDate(calendar.getTime());
		request.setEndDate(new Date());

		InternalResponse response = getDeviceReadingBCL().generateFileCSVTOURead(request);

		assertFalse(response.isInError());

		// comment this part when NOT running against mock
		// assertEquals("Tier Id", resultArray[0][0]);
		// assertEquals("Tier 1", resultArray2[1][0]);
		// assertEquals("11", resultArray2[2][0]);

		// Exception situation
		// Note: a runtime exception in MeterReadingService does not cause an error message
		// in DeviceReadingBCL, just an empty array to be returned
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_METER_READINGS);

		response = getDeviceReadingBCL().generateFileCSVTOURead(request);

		assertTrue(response.isInError());
	}

	// Water Data Reading interface:

	/**
	 * Test fetch all water data read.
	 */
	@Test
	public void testFetchAllWaterGasDataRead()
	{
		// Exception situation
		// Note: a runtime exception in MeterReadingService does not cause an error message
		// in DeviceReadingBCL, just an empty array to be returned
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_METER_READINGS);

		DeviceReadingRequest request = new DeviceReadingRequest(ServiceTypeEnum.WATER);

		TimeZoneInfo timeZoneInfo = new TimeZoneInfo();
		timeZoneInfo.setDisplayName(TIME_ZONE_AMERICA_SAO_PAULO);
		WaterMeter meter =
				new WaterMeter(DEVICE_ID_WATER_B75505999, new Radio(WATER_FLEXNET_ID, CUSTOMER_ID,
						new Location(timeZoneInfo)));

		request.setDevice(meter);

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH_14);
		c.set(Calendar.MONTH, MONTH_3);
		c.set(Calendar.YEAR, YEAR_2013);
		request.setInitialDate(c.getTime());
		request.setEndDate(c.getTime());

		InternalResultsResponse<IntervalRead> response =
				getDeviceReadingBCL().fetchAllWaterGasDataRead(request);

		if (!ValidationUtil.isNull(response))
		{
			for (IntervalRead ir : response.getResultsList())
			{
				for (HashMap<String, String> channels : ir.getChannels())
				{
					System.out.println(channels.toString());
				}

				System.out.println(PRINT_DATE + ir.getReadingDate());
				System.out.println(SPACE);
			}
		}

		assertTrue(response.getResultsList().size() == 0);

		resetMock();

		// Error situation
		// Note: a runtime exception in MeterReadingService does not cause an error message
		// in DeviceReadingBCL, just an empty array to be returned
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_METER_READINGS);
		request = new DeviceReadingRequest(ServiceTypeEnum.GAS);

		GasMeter meterGas =
				new GasMeter(STR_A_DEVICE_ID_234561299M, new Radio(ELECTRIC_FLEXNET_ID,
						CUSTOMER_ID,
						new Location(
								timeZoneInfo)));

		TestBaseUtil.createDefaultDeviceReadingRequest(meterGas, request);

		response = getDeviceReadingBCL().fetchAllWaterGasDataRead(request);

		assertTrue(response.getResultsList().size() > 0);

		resetMock();

		// Success situation
		response = getDeviceReadingBCL().fetchAllWaterGasDataRead(request);

		if (!ValidationUtil.isNull(response))
		{
			for (IntervalRead ir : response.getResultsList())
			{
				System.out.print(ir.getReadingDate().toString() + ' ');
				for (HashMap<String, String> channels : ir.getChannels())
				{
					System.out.println(channels.toString());
				}
			}
		}

		assertNotNull(response.getFirstResult());
		assertTrue(response.getResultsList().size() > 0);
	}

	/**
	 * Test generate file csv water data read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVWaterGasDataRead() throws Exception
	{
		// Success situation - Water
		WaterMeter meterWater =
				new WaterMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(
						TestBaseUtil.createTimeZoneInfo())));

		DeviceReadingRequest request = TestBaseUtil.createDeviceReadingRequest(ServiceTypeEnum.WATER);
		TestBaseUtil.createDefaultDeviceReadingRequest(meterWater, request);

		InternalResponse internalResponse =
				getDeviceReadingBCL().generateFileCSVWaterGasDataRead(request);

		assertEquals(false, internalResponse.isInError());

		// Success situation - Gas

		request = TestBaseUtil.createDeviceReadingRequest(ServiceTypeEnum.WATER);
		TestBaseUtil.createDefaultDeviceReadingRequest(new GasMeter(new Radio(GAS_FLEXNET_ID, CUSTOMER_ID,
				new Location(TestBaseUtil.createTimeZoneInfo()))), request);

		internalResponse = getDeviceReadingBCL().generateFileCSVWaterGasDataRead(request);

		assertEquals(false, internalResponse.isInError());
	}

	// Interval reads interface:

	/**
	 * Test fetch all interval read.
	 */
	@Test
	public void testFetchAllIntervalRead()
	{
		// Exception situation
		// Note: a runtime exception in MeterReadingService does not cause an error message
		// in DeviceReadingBCL, just an empty array to be returned
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_METER_READINGS);

		TimeZoneInfo timeZoneInfo = new TimeZoneInfo();
		timeZoneInfo.setDisplayName(TIME_ZONE_AMERICA_SAO_PAULO);
		ElectricMeter meter = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(
				timeZoneInfo)));
		meter.getRadio().setFlexNetId(new BigInteger(DEVICE_ID.replace("M", "")));

		DeviceReadingRequest request = new DeviceReadingRequest(meter);

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, NEGATIVE_TEN);

		request.setInitialDate(calendar.getTime());
		request.setEndDate(new Date());

		InternalResultsResponse<IntervalRead> response =
				getDeviceReadingBCL().fetchAllIntervalRead(request);

		assertTrue(response.getResultsList().size() == 0);

		resetMock();

		// Error situation
		// Note: a runtime exception in MeterReadingService does not cause an error message
		// in DeviceReadingBCL, just an empty array to be returned
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_METER_READINGS);
		request = new DeviceReadingRequest();

		meter = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(
				timeZoneInfo)));
		meter.setDeviceId(DEVICE_ID);
		request.setDevice(meter);

		request.setInitialDate(new Date());
		request.setEndDate(new Date());

		response = getDeviceReadingBCL().fetchAllIntervalRead(request);

		// ####################assertTrue(response.getResultsList().size() > 0);

		resetMock();

		// Success situation
		// meter = new ElectricMeter(new Radio(new BigInteger("3376855"), CUSTOMER_ID));
		// meter.setDeviceId("13139104");

		meter = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(timeZoneInfo)), DEVICE_ID);

		request = new DeviceReadingRequest();
		request.setDevice(meter);

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH_14);
		c.set(Calendar.MONTH, MONTH_3);
		c.set(Calendar.YEAR, YEAR_2013);
		request.setInitialDate(c.getTime());
		request.setEndDate(c.getTime());

		response =
				getDeviceReadingBCL().fetchAllIntervalRead(request);

		if (!ValidationUtil.isNull(response))
		{
			for (IntervalRead ir : response.getResultsList())
			{
				for (HashMap<String, String> channels : ir.getChannels())
				{
					System.out.println(channels.toString());
				}

				System.out.println(PRINT_DATE + ir.getReadingDate());
				System.out.println(SPACE);
			}
		}

		assertNotNull(response.getFirstResult());
		assertTrue(response.getResultsList().size() > 0);
	}

	/**
	 * Test generate file csv interval read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVIntervalRead() throws Exception
	{
		// Success situation
		ElectricMeter meter = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(
				TestBaseUtil.createTimeZoneInfo())));

		DeviceReadingRequest request = TestBaseUtil.createDeviceReadingRequest();

		TestBaseUtil.createDefaultDeviceReadingRequest(meter, request);

		InternalResponse internalResponse =
				getDeviceReadingBCL().generateFileCSVIntervalRead(request);

		assertEquals(false, internalResponse.isInError());

		fileReader(request.getFileName());

		// Exception situation
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_METER_READINGS);

		internalResponse = getDeviceReadingBCL().generateFileCSVIntervalRead(request);

		assertTrue(internalResponse.isInError());

		resetMock();

		// Error situation
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_METER_READINGS);

		internalResponse =
				getDeviceReadingBCL().generateFileCSVIntervalRead(request);

		assertFalse(internalResponse.isInError());

		resetMock();
	}

	// Snapshot interface:

	/**
	 * Test fetch snapshots.
	 */
	@Test
	public void testFetchSnapshots()
	{
		// Exception situation
		// Note: a runtime exception in MeterReadingService does not cause an error message
		// in DeviceReadingBCL, just an empty array to be returned
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_METER_READINGS);
		DeviceReadingRequest request = new DeviceReadingRequest();

		TimeZoneInfo timeZoneInfo = new TimeZoneInfo();
		timeZoneInfo.setDisplayName(TIME_ZONE_AMERICA_SAO_PAULO);
		ElectricMeter meter =
				new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID, new Location(
						timeZoneInfo)), DEVICE_ID);
		request.setDevice(meter);

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, NEGATIVE_TEN);

		request.setInitialDate(calendar.getTime());
		request.setEndDate(new Date());

		InternalResultsResponse<IntervalRead> response =
				getDeviceReadingBCL().fetchAllSnapshots(request);

		assertTrue(response.getResultsList().size() == 0);

		resetMock();

		// Success situation
		response =
				getDeviceReadingBCL().fetchAllSnapshots(request);

		if (!ValidationUtil.isNull(response))
		{
			for (IntervalRead ir : response.getResultsList())
			{

				System.out.println("-----");

				for (HashMap<String, String> channels : ir.getChannels())
				{
					System.out.println(channels.toString());
				}

			}
		}

		// assertNotNull(response.getFirstResult());
		// assertTrue(response.getResultsList().size() > 0);

	}

	/**
	 * Test generate file csv snapshot.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVSnapshot() throws Exception
	{

		// Success situation
		DeviceReadingRequest request = new DeviceReadingRequest();

		TestBaseUtil.createDefaultDeviceReadingRequest(new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID,
				new Location(TestBaseUtil.createTimeZoneInfo()))), request);

		InternalResponse response =
				getDeviceReadingBCL().generateFileCSVSnapshot(request);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Exception situation
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_METER_READINGS);

		response = getDeviceReadingBCL().generateFileCSVSnapshot(request);

		assertTrue(response.isInError());

		fileReader(request.getFileName());

		resetMock();

		// Error situation
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_METER_READINGS);

		response = getDeviceReadingBCL().generateFileCSVSnapshot(request);

		assertFalse(response.isInError());

		resetMock();
	}

	// Load profile interface:

	/**
	 * Test fetch updated meter load profile electric.
	 */
	@Test
	public void testFetchUpdatedMeterLoadProfileElectric()
	{
		// Error situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_LATEST_METER_READING);

		ElectricMeter em =
				new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID), STRING_ONE);
		em.setDeviceId(DEVICE_ID);
		em.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);

		InternalResultsResponse<LoadProfile> response = getDeviceReadingBCL().
				fetchUpdatedMeterLoadProfile(new DeviceRequest(em));

		assertNotNull(response.getResultsList());
		resetMock();

		// Success situation
		setSituation(getDeviceReadingBCL(), SituationsEnum.SUCCESS, MeterReadingService.class,
				GET_LATEST_METER_READING);

		em = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID), STRING_ONE);
		em.setDeviceId(DEVICE_ID);
		em.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(new DeviceRequest(em));

		assertNotNull(response.getResultsList());
		assertTrue(response.getResultsList().size() > 0);

		for (LoadProfile lp : response.getResultsList())
		{
			System.out.println(lp);
		}
		resetMock();

		// Error situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_METER_READINGS);

		em = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID), STRING_ONE);
		em.setDeviceId(DEVICE_ID);
		em.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(new DeviceRequest(em));

		assertNotNull(response.getResultsList());
		resetMock();

		// Exception situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_LATEST_METER_READING);

		em = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID, CUSTOMER_ID), STRING_ONE);
		em.setDeviceId(DEVICE_ID);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(new DeviceRequest(em));

		assertNotNull(response.getResultsList());
		resetMock();

	}

	/**
	 * Test fetch updated meter load profile water.
	 */
	@Test
	public void testFetchUpdatedMeterLoadProfileWater()
	{
		// Error situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_LATEST_METER_READING);

		WaterMeter meter =
				new WaterMeter(STR_A_DEVICE_ID_234561299M, new Radio(ELECTRIC_FLEXNET_ID,
						CUSTOMER_ID));

		DeviceRequest request = new DeviceRequest(meter);

		InternalResultsResponse<LoadProfile> response = getDeviceReadingBCL().
				fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		resetMock();

		// Success situation
		setSituation(getDeviceReadingBCL(), SituationsEnum.SUCCESS, MeterReadingService.class,
				GET_LATEST_METER_READING);

		// meter = new WaterMeter(new Radio(new BigInteger("15600266"), CUSTOMER_ID));
		// meter.setDeviceId("B41106282");

		meter = new WaterMeter(new Radio(WATER_FLEXNET_ID, CUSTOMER_ID));
		meter.setDeviceId(DEVICE_ID_WATER_B75505999);

		request = new DeviceRequest(meter);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		// assertTrue(response.getResultsList().size() > 0);

		System.out.println(STR_SEPARATOR);
		System.out.println("### response for water reading:");

		for (LoadProfile lp : response.getResultsList())
		{
			System.out.println("### " + lp.getLastReadTime() + ' ' + lp.getLastReadValue());
		}

		System.out.println(STR_SEPARATOR);
		resetMock();

		// Error situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_METER_READINGS);

		meter =
				new WaterMeter(STR_A_DEVICE_ID_234561299M, new Radio(ELECTRIC_FLEXNET_ID,
						CUSTOMER_ID));

		request = new DeviceRequest(meter);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		resetMock();

		// Exception situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_LATEST_METER_READING);

		meter =
				new WaterMeter(STR_A_DEVICE_ID_234561299M, new Radio(ELECTRIC_FLEXNET_ID,
						CUSTOMER_ID));

		request = new DeviceRequest();
		request.addDevice(meter);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		resetMock();
	}

	/**
	 * Test fetch updated meter load profile gas.
	 */
	@Test
	public void testFetchUpdatedMeterLoadProfileGas()
	{
		// Error situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_LATEST_METER_READING);

		GasMeter meter =
				new GasMeter(STR_A_DEVICE_ID_GAS_1806, new Radio(GAS_FLEXNET_ID,
						CUSTOMER_ID));

		DeviceRequest request = new DeviceRequest(meter);

		InternalResultsResponse<LoadProfile> response = getDeviceReadingBCL().
				fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		resetMock();

		// Success situation
		setSituation(getDeviceReadingBCL(), SituationsEnum.SUCCESS, MeterReadingService.class,
				GET_LATEST_METER_READING);

		meter = new GasMeter("GAS1712", new Radio(new BigInteger("23001712"), CUSTOMER_ID));

		request = new DeviceRequest(meter);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		// assertTrue(response.getResultsList().size() > 0);

		System.out.println(STR_SEPARATOR);
		System.out.println("### response for gas reading:");

		for (LoadProfile lp : response.getResultsList())
		{
			System.out.println(STR_SEPARATOR + lp.getLastReadTime() + ' ' + lp.getLastReadValue());
		}

		System.out.println(STR_SEPARATOR);
		resetMock();

		// Error situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.ERROR, MeterReadingService.class,
				GET_METER_READINGS);

		meter =
				new GasMeter(STR_A_DEVICE_ID_GAS_1806, new Radio(GAS_FLEXNET_ID, CUSTOMER_ID));

		request = new DeviceRequest(meter);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		resetMock();

		// Exception situation
		// this flag is used to provide different readings (not produce an error)
		// to provide for better code coverage..getLatestMeterReading
		setSituation(getDeviceReadingBCL(), SituationsEnum.EXCEPTION, MeterReadingService.class,
				GET_LATEST_METER_READING);

		meter =
				new GasMeter(STR_A_DEVICE_ID_GAS_1806, new Radio(GAS_FLEXNET_ID, CUSTOMER_ID));

		request = new DeviceRequest();
		request.addDevice(meter);

		response = getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(request);

		assertNotNull(response.getResultsList());
		resetMock();
	}
}
