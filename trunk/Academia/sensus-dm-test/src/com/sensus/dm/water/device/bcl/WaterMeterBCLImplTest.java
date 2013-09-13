package com.sensus.dm.water.device.bcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.water.device.dac.IWaterMeterDAC;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.WaterMeterSearch;

/**
 * The Class ElectricMeterBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/water/device/waterMeterbclimpltest.xml"})
public class WaterMeterBCLImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant STR_METHOD_NAME_FOR_FETCH_LEAK_REPORT. */
	private static final String STR_METHOD_NAME_FOR_FETCH_LEAK_REPORT = "fetchLeakReport";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device bcl. */
	private IWaterMeterBCL waterMeterBCL;

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
	@Resource(name = "waterMeterBCLTarget")
	public void setWaterMeterBCL(IWaterMeterBCL waterMeterBCL)
	{
		this.waterMeterBCL = waterMeterBCL;
	}

	/**
	 * Sets the device area.
	 */
	public void setWaterMeterArea()
	{
		setArea(getWaterMeterBCL(), EPMAreaEnum.DEVICE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setWaterMeterArea();
	}

	/**
	 * Removes the device area.
	 */
	@After
	public void resetMocksToWaterMeterArea()
	{
		resetMocksData(getWaterMeterBCL());
		setWaterMeterArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all water meters.
	 */
	@Test
	public void testFetchAllWaterMeters()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		// Success situation
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter((WaterMeter)TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER));
		DeviceSearch deviceSearch = new DeviceSearch(waterMeterSearch);
		request.setDeviceSearch(deviceSearch);
		request.setDeviceType(DeviceTypeEnum.WATER_METER);

		InternalResultsResponse<WaterMeter> response = getWaterMeterBCL().fetchAllWaterMeters(request);

		TestBaseUtil.assertResultResponse(response);
		assertEquals(FOUR, response.getResultsList().size());

		// Error situation
		setSituation(getWaterMeterBCL(), SituationsEnum.ERROR, IWaterMeterDAC.class, "fetchAllWaterMeters");
		response = getWaterMeterBCL().fetchAllWaterMeters(request);
		assertMessages(response, ERROR_CODE);

	}

	/**
	 * Test fetch leak report.
	 */
	@Test
	public void testFetchLeakReport()
	{
		DeviceRequest request =
				new DeviceRequest(new WaterMeter(new Radio(null, CUSTOMER_ID)),
						TestBaseUtil.createTenant(CUSTOMER_ID));

		// Success situation
		InternalResultsResponse<WaterLeak> response = getWaterMeterBCL().fetchLeakReport(request);
		TestBaseUtil.assertResultResponse(response);
		assertEquals(1, response.getResultsList().size());

		// Error situation
		setSituation(getWaterMeterBCL(), SituationsEnum.ERROR, IWaterMeterDAC.class,
				STR_METHOD_NAME_FOR_FETCH_LEAK_REPORT);
		response = getWaterMeterBCL().fetchLeakReport(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test generate file csv leak report success.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVLeakReportSuccess() throws Exception
	{
		// Success situation
		DeviceRequest request = setupRequestForGenerateFileCSVLeakReport();

		InternalResponse response = getWaterMeterBCL().generateFileCSVLeakReport(request);

		TestBaseUtil.assertResponse(response);

		List<String[]> data = fileReader(request.getFileName());

		assertEquals(SensusMessageUtil.getMessage(DEVICE_ID_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ZERO]);

		assertEquals(SensusMessageUtil.getMessage(NETWORK_ADDRESS_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ONE]);

		assertEquals(DEVICE_ID, data.get(ONE)[ZERO]);

	}

	/**
	 * Test generate file csv leak report error.
	 */
	@Test
	public void testGenerateFileCSVLeakReportError()
	{
		DeviceRequest request = setupRequestForGenerateFileCSVLeakReport();

		setSituation(getWaterMeterBCL(), SituationsEnum.ERROR, IWaterMeterDAC.class,
				STR_METHOD_NAME_FOR_FETCH_LEAK_REPORT);

		InternalResponse response = getWaterMeterBCL().generateFileCSVLeakReport(request);

		assertMessages(response, ERROR_CODE);

	}

	/**
	 * Test fetch water meter by id.
	 */
	@Test
	public void testFetchWaterMeterById()
	{
		// Success situation - with device type water meter

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER));

		InternalResultsResponse<Device> response = getWaterMeterBCL().fetchWaterMeterById(request);

		TestBaseUtil.assertResultResponse(response);
		executeAssertsForDeviceGetId(response);

		// Error situation - fetchWaterMeterById
		setSituation(getWaterMeterBCL(), SituationsEnum.ERROR, IWaterMeterDAC.class, "fetchWaterMeterById");
		response = getWaterMeterBCL().fetchWaterMeterById(request);
		assertMessages(response, ERROR_CODE);

		resetMocksToWaterMeterArea();
	}

	/**
	 * Test generate csv.
	 */
	@Test
	public void testGenerateCSV()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();
		request.setFileName(FILE_NAME);

		List<DeviceColumnEnum> columns = new ArrayList<DeviceColumnEnum>();
		columns.add(DeviceColumnEnum.DEVICE_ID);
		columns.add(DeviceColumnEnum.NETWORK_ADDRESS);
		columns.add(DeviceColumnEnum.DESCRIPTION);
		columns.add(DeviceColumnEnum.INSTALL_DATE);
		columns.add(DeviceColumnEnum.STATUS);
		columns.add(DeviceColumnEnum.ALARM);
		columns.add(DeviceColumnEnum.ADDRESS);
		columns.add(DeviceColumnEnum.CITY_NAME);
		columns.add(DeviceColumnEnum.ENCRYPTION_SUPPORT);
		columns.add(DeviceColumnEnum.LATITUDE);
		columns.add(DeviceColumnEnum.LONGITUDE);
		columns.add(DeviceColumnEnum.PREMISE_ID);
		columns.add(DeviceColumnEnum.ZIP_CODE);
		columns.add(DeviceColumnEnum.REMOTE_DISCONNECT);
		columns.add(DeviceColumnEnum.LAST_HEARD);
		columns.add(DeviceColumnEnum.QUARANTINE);

		request.setDeviceSearch(new DeviceSearch());

		request.setListColumn(columns);

		request.setProcessId(1);
		InternalResponse response = getWaterMeterBCL().generateDevicesCSV(request);

		TestBaseUtil.assertResponse(response);

		resetMocksToWaterMeterArea();

		// Error situation - fetchAllWaterMeters
		setSituation(getWaterMeterBCL(), SituationsEnum.ERROR, IWaterMeterDAC.class, "fetchAllWaterMeters");
		response = getWaterMeterBCL().generateDevicesCSV(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch communication.
	 */
	@Test
	public void testFetchCommunication()
	{
		// Success situation
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.setTenant(TestBaseUtil.createTenant());
		InternalResultsResponse<WaterGasMeterStatusCount> response = getWaterMeterBCL().fetchCommunication(request);
		TestBaseUtil.assertResultResponse(response);

		// Error situation
		setSituation(getWaterMeterBCL(), SituationsEnum.ERROR, IWaterMeterDAC.class, "fetchCommunication");
		response = getWaterMeterBCL().fetchCommunication(request);
		assertMessages(response, ERROR_CODE);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private methods:

	/**
	 * Setup request for generate file csv leak report.
	 * 
	 * @return the device request
	 */
	private DeviceRequest setupRequestForGenerateFileCSVLeakReport()
	{
		DeviceRequest request =
				new DeviceRequest(new WaterMeter(new Radio(null, CUSTOMER_ID)));

		request.setFileName(FILE_NAME);
		request.setProcessId(1);
		request.setDateFormat(FORMATTED_DATE_TIME);
		return request;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Execute asserts for device get id.
	 * 
	 * @param deviceResponse the device response
	 */
	private void executeAssertsForDeviceGetId(InternalResultsResponse<Device> deviceResponse)
	{
		// check specifically for the values in the DB here...
		for (Device device : deviceResponse.getResultsList())
		{
			assertNotNull(device.getRadio().getFlexNetId());
		}
	}

	// -------------------------------------------------------------------------
}
