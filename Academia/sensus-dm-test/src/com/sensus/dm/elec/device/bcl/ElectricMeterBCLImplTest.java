package com.sensus.dm.elec.device.bcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.dac.IElectricMeterDAC;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.PeakDemandOrderByEnum;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Class ElectricMeterBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/elec/device/electricmeterbclimpltest.xml"})
public class ElectricMeterBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant FETCH_ALL_LCM. */
	private static final String FETCH_ALL_LCM = "fetchAllLCM";

	/** The Constant FETCH_ALL_HAN_DEVICES. */
	private static final String FETCH_ALL_HAN_DEVICES = "fetchAllHanDevices";

	/** The Constant STR_METHOD_NAME_FOR_FETCH_ALL_ELECTRIC_METER. */
	private static final String STR_METHOD_NAME_FOR_FETCH_ALL_ELECTRIC_METER = "fetchAllElectricMeter";

	/** The Constant MANUFACTURE. */
	private static final String MANUFACTURE = "Sensus/HAI";

	/** The Constant DEVICE_MODEL. */
	private static final String DEVICE_MODEL = "539 63 537 08002";

	/** The Constant FLEXNET_ID. */
	protected static final String FLEXNET_ID = "1010";

	/** The Constant NETWORK_ADDRESS_HANDEVICE. */
	private static final String NETWORK_ADDRESS_HANDEVICE = "00:00:00:00:00:00:03:f2";

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/**
	 * Gets the electric meter bcl.
	 * 
	 * @return the electric meter bcl
	 */
	public IElectricMeterBCL getElectricMeterBCL()
	{
		return electricMeterBCL;
	}

	/**
	 * Sets the electric meter bcl.
	 * 
	 * @param electricMeterBCL the new electric meter bcl
	 */
	@Resource(name = "electricMeterBCLTarget")
	public void setElectricMeterBCL(IElectricMeterBCL electricMeterBCL)
	{
		this.electricMeterBCL = electricMeterBCL;
	}

	/**
	 * Sets the electric meter area.
	 */
	public void setElectricMeterArea()
	{
		setArea(getElectricMeterBCL(), EPMAreaEnum.DEVICE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setElectricMeterArea();
	}

	/**
	 * Removes the device area.
	 */
	@After
	public void resetMocksToDeviceArea()
	{
		resetMocksData(getElectricMeterBCL());
		setElectricMeterArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test delete device.
	 */
	@Test
	public void testDeleteDevice()
	{
		// Success situation

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));

		InternalResponse response = getElectricMeterBCL().deleteDevice(request);

		TestBaseUtil.assertResponse(response);

		resetMocksToDeviceArea();

		// Error situation

		this.setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class);
		response = getElectricMeterBCL().deleteDevice(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchDeviceByIdForElectricMeterSuccess()
	{
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER));

		InternalResultsResponse<Device> response = getElectricMeterBCL().fetchDeviceById(request);

		TestBaseUtil.assertResultResponse(response);
		executeAssertsForDeviceGetId(response);
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchDeviceByIdForElectricMeterErrorOnDAC()
	{
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER));

		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class, "fetchElectricMeterById");
		InternalResultsResponse<Device> response = getElectricMeterBCL().fetchDeviceById(request);
		assertTrue(response.isInError());

		resetMocksToDeviceArea();
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchDeviceByIdForLcmSuccess()
	{
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM));

		InternalResultsResponse<Device> response = getElectricMeterBCL().fetchDeviceById(request);

		TestBaseUtil.assertResultResponse(response);
		executeAssertsForDeviceGetId(response);
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchDeviceByIdForLcmErrorOnDAC()
	{
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM));

		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class, "fetchLCMById");
		InternalResultsResponse<Device> response = getElectricMeterBCL().fetchDeviceById(request);
		assertTrue(response.isInError());

		resetMocksToDeviceArea();
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchDeviceByIdForHanDevicesSuccess()
	{
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));

		InternalResultsResponse<Device> response = getElectricMeterBCL().fetchDeviceById(request);

		TestBaseUtil.assertResultResponse(response);
		executeAssertsForDeviceGetId(response);
	}

	/**
	 * Test fetch device by id.
	 */
	@Test
	public void testFetchDeviceByIdForHanDevicesErrorOnDAC()
	{
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));

		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class, "fetchHanDeviceById");
		InternalResultsResponse<Device> response = getElectricMeterBCL().fetchDeviceById(request);
		assertTrue(response.isInError());

		resetMocksToDeviceArea();
	}

	/**
	 * Test fetch lifecycle states.
	 */
	@Test
	public void testFetchLifecycleStates()
	{
		// Success situation

		DeviceRequest request = new DeviceRequest();

		InternalResultsResponse<LifecycleState> response = getElectricMeterBCL().fetchLifecycleStates(request);

		TestBaseUtil.assertResultResponse(response);
		assertEquals(ONE, response.getResultsList().size());
	}

	/**
	 * Test fetch all electric meter.
	 */
	@Test
	public void testFetchAllElectricMeter()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		// Success situation
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
		electricMeterSearch.setElectricMeter((ElectricMeter)TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER));
		DeviceSearch deviceSearch = new DeviceSearch(electricMeterSearch);
		request.setDeviceSearch(deviceSearch);
		request.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);

		InternalResultsResponse<ElectricMeter> response = getElectricMeterBCL().fetchAllElectricMeter(request);

		TestBaseUtil.assertResultResponse(response);
		assertEquals(FOUR, response.getResultsList().size());

		// Error situation
		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class,
				STR_METHOD_NAME_FOR_FETCH_ALL_ELECTRIC_METER);
		request.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		response = getElectricMeterBCL().fetchAllElectricMeter(request);
		assertMessages(response, ERROR_CODE);

	}

	/**
	 * Test fetch all han device.
	 */
	@Test
	public void testFetchAllHanDevice()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		// Success situation
		HanDeviceSearch hanDeviceSearch = new HanDeviceSearch();
		hanDeviceSearch.setHanDevice((HanDevice)TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));
		DeviceSearch deviceSearch = new DeviceSearch(hanDeviceSearch);
		request.setDeviceSearch(deviceSearch);
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);

		InternalResultsResponse<HanDevice> response = getElectricMeterBCL().fetchAllHanDevices(request);

		TestBaseUtil.assertResultResponse(response);
		assertEquals(FOUR, response.getResultsList().size());

		// Error situation
		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class, FETCH_ALL_HAN_DEVICES);
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		response = getElectricMeterBCL().fetchAllHanDevices(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch all lcm.
	 */
	@Test
	public void testFetchAllLCM()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		// Success situation
		LCMSearch lcmSearch = new LCMSearch();
		lcmSearch.setLcm((LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM));
		DeviceSearch deviceSearch = new DeviceSearch(lcmSearch);
		request.setDeviceSearch(deviceSearch);
		request.setDeviceType(DeviceTypeEnum.LCM);

		InternalResultsResponse<LCM> response = getElectricMeterBCL().fetchAllLCM(request);

		TestBaseUtil.assertResultResponse(response);
		assertEquals(FOUR, response.getResultsList().size());

		// Error situation
		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class, FETCH_ALL_LCM);
		request.setDeviceType(DeviceTypeEnum.LCM);
		response = getElectricMeterBCL().fetchAllLCM(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Setup request for generate devices csv.
	 * 
	 * @param deviceType the device type
	 * @return the inquiry device request
	 */
	private InquiryDeviceRequest setupRequestForGenerateDevicesCSV(DeviceTypeEnum deviceType)
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();
		request.setFileName(FILE_NAME);

		List<DeviceColumnEnum> columns = new ArrayList<DeviceColumnEnum>();
		columns.add(DeviceColumnEnum.DEVICE_ID);
		columns.add(DeviceColumnEnum.NETWORK_ADDRESS);
		columns.add(DeviceColumnEnum.DESCRIPTION);
		columns.add(DeviceColumnEnum.DEVICE_TYPE);
		columns.add(DeviceColumnEnum.INSTALL_DATE);
		columns.add(DeviceColumnEnum.LIFECYCLE_STATE);
		columns.add(DeviceColumnEnum.ALARM);

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().addDeviceType(deviceType);

		request.setListColumn(columns);

		request.setProcessId(1);
		request.setDeviceType(deviceType);
		return request;
	}

	/**
	 * Test generate file csv.
	 * 
	 */
	@Test
	public void testGenerateFileCSVForElectricMetersSuccess()
	{
		resetMocksToDeviceArea();

		InquiryDeviceRequest request = setupRequestForGenerateDevicesCSV(DeviceTypeEnum.ELECTRIC_METER);

		InternalResponse response = getElectricMeterBCL().generateDevicesCSV(request);

		TestBaseUtil.assertResponse(response);

		List<String[]> data = fileReader(request.getFileName());

		assertEquals(SensusMessageUtil.getMessage(DEVICE_ID_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ZERO]);

		assertEquals(SensusMessageUtil.getMessage(NETWORK_ADDRESS_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ONE]);

		assertEquals(FLEXNET_ID.toString(), data.get(ONE)[ONE]);

		resetMocksToDeviceArea();
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSVForElectricMetersError()
	{
		resetMocksToDeviceArea();

		InquiryDeviceRequest request = setupRequestForGenerateDevicesCSV(DeviceTypeEnum.ELECTRIC_METER);

		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class,
				STR_METHOD_NAME_FOR_FETCH_ALL_ELECTRIC_METER);

		InternalResponse response = getElectricMeterBCL().generateDevicesCSV(request);

		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();
	}

	/**
	 * Test generate file csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVForHanDevicesSuccess() throws Exception
	{
		resetMocksToDeviceArea();

		InquiryDeviceRequest request = setupRequestForGenerateDevicesCSV(DeviceTypeEnum.HAN_DEVICE);

		InternalResponse response = getElectricMeterBCL().generateDevicesCSV(request);

		TestBaseUtil.assertResponse(response);

		List<String[]> data = fileReader(request.getFileName());

		assertEquals(SensusMessageUtil.getMessage(DEVICE_ID_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ZERO]);

		assertEquals(SensusMessageUtil.getMessage(NETWORK_ADDRESS_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ONE]);

		assertEquals(NETWORK_ADDRESS_HANDEVICE, data.get(ONE)[ONE]);

		resetMocksToDeviceArea();
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSVForHanDevicesError()
	{
		resetMocksToDeviceArea();

		InquiryDeviceRequest request = setupRequestForGenerateDevicesCSV(DeviceTypeEnum.HAN_DEVICE);

		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class, FETCH_ALL_HAN_DEVICES);

		InternalResponse response = getElectricMeterBCL().generateDevicesCSV(request);

		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();
	}

	/**
	 * Test generate file csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVForLcmsSuccess() throws Exception
	{
		InquiryDeviceRequest request = setupRequestForGenerateDevicesCSV(DeviceTypeEnum.LCM);

		InternalResponse response = getElectricMeterBCL().generateDevicesCSV(request);

		TestBaseUtil.assertResponse(response);

		List<String[]> data = fileReader(request.getFileName());

		assertEquals(SensusMessageUtil.getMessage(DEVICE_ID_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ZERO]);

		assertEquals(SensusMessageUtil.getMessage(NETWORK_ADDRESS_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ONE]);

		assertEquals(FLEXNET_ID, data.get(ONE)[ONE]);

		resetMocksToDeviceArea();
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSVForLcmsError()
	{
		InquiryDeviceRequest request = setupRequestForGenerateDevicesCSV(DeviceTypeEnum.LCM);

		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class, FETCH_ALL_LCM);

		InternalResponse response = getElectricMeterBCL().generateDevicesCSV(request);

		assertMessages(response, ERROR_CODE);

		resetMocksToDeviceArea();
	}

	/**
	 * Test fetch all devices success electric meter.
	 */
	@Test
	public void testFetchAllDevicesSuccessElectricMeter()
	{
		InquiryDeviceRequest request;
		InternalResultsResponse<Device> response;

		request = TestBaseUtil.createInquiryDeviceRequest();
		DeviceSearch deviceSearch = new DeviceSearch();
		request.setDeviceSearch(deviceSearch);

		deviceSearch.addDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		response = getElectricMeterBCL().fetchAllDevices(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch all devices error electric meter.
	 */
	@Test
	public void testFetchAllDevicesErrorElectricMeter()
	{
		InquiryDeviceRequest request;
		InternalResultsResponse<Device> response;

		request = TestBaseUtil.createInquiryDeviceRequest();
		DeviceSearch deviceSearch = new DeviceSearch();
		request.setDeviceSearch(deviceSearch);

		deviceSearch.addDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class);
		response = getElectricMeterBCL().fetchAllDevices(request);
		assertMessages(response, ERROR_CODE);
		resetMocksToDeviceArea();
	}

	/**
	 * Test fetch all devices success han device.
	 */
	@Test
	public void testFetchAllDevicesSuccessHanDevice()
	{
		InquiryDeviceRequest request;
		InternalResultsResponse<Device> response;

		request = TestBaseUtil.createInquiryDeviceRequest();
		DeviceSearch deviceSearch = new DeviceSearch();
		request.setDeviceSearch(deviceSearch);

		deviceSearch.addDeviceType(DeviceTypeEnum.HAN_DEVICE);
		response = getElectricMeterBCL().fetchAllDevices(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch all devices error han device.
	 */
	@Test
	public void testFetchAllDevicesErrorHanDevice()
	{
		InquiryDeviceRequest request;
		InternalResultsResponse<Device> response;

		request = TestBaseUtil.createInquiryDeviceRequest();
		DeviceSearch deviceSearch = new DeviceSearch();
		request.setDeviceSearch(deviceSearch);

		deviceSearch.addDeviceType(DeviceTypeEnum.HAN_DEVICE);
		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class);
		response = getElectricMeterBCL().fetchAllDevices(request);
		assertMessages(response, ERROR_CODE);
		resetMocksToDeviceArea();
	}

	/**
	 * Test fetch all devices success lcm.
	 */
	@Test
	public void testFetchAllDevicesSuccessLCM()
	{
		InquiryDeviceRequest request;
		InternalResultsResponse<Device> response;

		request = TestBaseUtil.createInquiryDeviceRequest();
		DeviceSearch deviceSearch = new DeviceSearch();
		request.setDeviceSearch(deviceSearch);

		deviceSearch.addDeviceType(DeviceTypeEnum.LCM);
		response = getElectricMeterBCL().fetchAllDevices(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch all devices error lcm.
	 */
	@Test
	public void testFetchAllDevicesErrorLCM()
	{
		InquiryDeviceRequest request;
		InternalResultsResponse<Device> response;

		request = TestBaseUtil.createInquiryDeviceRequest();
		DeviceSearch deviceSearch = new DeviceSearch();
		request.setDeviceSearch(deviceSearch);

		deviceSearch.addDeviceType(DeviceTypeEnum.LCM);
		setSituation(getElectricMeterBCL(), SituationsEnum.ERROR, IElectricMeterDAC.class);
		response = getElectricMeterBCL().fetchAllDevices(request);
		assertMessages(response, ERROR_CODE);
		resetMocksToDeviceArea();
	}

	/**
	 * Test generate file csv for peak demand success.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateFileCSVForPeakDemandSuccess() throws Exception
	{
		// Success.
		// no parameter is required for this method to work
		DeviceReadingRequest request =
				new DeviceReadingRequest(TestBaseUtil.createUserContextWithLocale());

		request.setFileName(FILE_NAME);
		request.setDateFormat(FORMATTED_DATE);
		request.setTimeZone(TIME_ZONE_N3);

		InternalResponse response = getElectricMeterBCL().generatePeakDemandCSV(request);
		TestBaseUtil.assertResponse(response);

		fileReader(request.getFileName());

		resetMocksToDeviceArea();
	}

	/**
	 * Test generate file csv for demand read error.
	 */
	@Test
	public void testGenerateFileCSVForDemandReadError()
	{
		resetMocksToDeviceArea();

		DeviceReadingRequest request =
				new DeviceReadingRequest(TestBaseUtil.createUserContext());

		request.setFileName(FILE_NAME);

	}

	/**
	 * Test fetch all han devices by meter.
	 */
	@Test
	public void testFetchAllHanDevicesByMeter()
	{
		// Success situation
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER));

		InternalResultsResponse<Device> response = getElectricMeterBCL().fetchAllHanDevicesByMeter(deviceRequest);

		TestBaseUtil.assertResultResponse(response);
		executeAssertsForHanDeviceGetId(response);
	}

	/**
	 * Test fetch all manufacture by device sub type.
	 */
	@Test
	public void testFetchAllManufactureByDeviceSubType()
	{
		// Success situation

		HanDevice hanDevice = new HanDevice();
		hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);

		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.addDevice(hanDevice);

		InternalResultsResponse<Device> response =
				getElectricMeterBCL().fetchAllManufactureByDeviceSubType(deviceRequest);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch all model by han device type.
	 */
	@Test
	public void testFetchAllModelByDeviceSubType()
	{
		// Success situation
		HanDevice hanDevice = new HanDevice();
		hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
		hanDevice.setDeviceModel(new DeviceModel(DEVICE_MODEL));
		hanDevice.getDeviceModel().setManufacture(MANUFACTURE);

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(hanDevice);

		InternalResultsResponse<Device> response =
				getElectricMeterBCL().fetchAllModelByDeviceSubType(request);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch device type id by device.
	 */
	@Test
	public void testFetchDeviceTypeIdByDevice()
	{
		// Success situation

		InternalResultsResponse<Integer> response =
				getElectricMeterBCL().fetchDeviceTypeIdByDevice(new DeviceRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch han device type id.
	 */
	@Test
	public void testFetchHanDeviceTypeId()
	{
		// Success situation

		InternalResultsResponse<Integer> response = getElectricMeterBCL().fetchHanDeviceTypeId(new DeviceRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test update device status.
	 */
	@Test
	public void testUpdateDeviceStatus()
	{
		// Success situation

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));

		InternalResponse response = getElectricMeterBCL().updateDeviceStatus(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch all connection state.
	 */
	@Test
	public void testFetchAllConnectionState()
	{
		// Success situation

		InternalResponse response = getElectricMeterBCL().fetchAllConnectionState(new DeviceRequest());

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch all peak demand.
	 */
	@Test
	public void testFetchAllPeakDemand()
	{
		// Succes situation
		DeviceReadingRequest request = new DeviceReadingRequest();
		request.setDevice(new Device(new Radio(new BigInteger(FLEXNET_ID))));
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		request.addSortExpressions(new SortExpression(PeakDemandOrderByEnum.DEMAND_RESET_COUNT.getValue(),
				Direction.Ascending));

		InternalResultsResponse<PeakDemand> response = getElectricMeterBCL().fetchAllPeakDemand(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch lcm relay by device id.
	 */
	@Test
	public void testFetchLCMRelaysByDevice()
	{
		// Success situation
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM));

		InternalResultsResponse<LCMRelay> response = getElectricMeterBCL().fetchLCMRelaysByDevice(deviceRequest);

		TestBaseUtil.assertResultResponse(response);
		TestBaseUtil.assertResponse(response);
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

	/**
	 * Execute asserts for han device get id.
	 * 
	 * @param deviceResponse the han device response
	 */
	private void executeAssertsForHanDeviceGetId(InternalResultsResponse<Device> deviceResponse)
	{
		// check specifically for the values in the DB here...
		for (Device device : deviceResponse.getResultsList())
		{
			assertNotNull(device.getRadio().getFlexNetId());

			if (DeviceTypeEnum.HAN_DEVICE.equals(device.getDeviceType()))
			{
				assertNotNull(((HanDevice)device).getHanDeviceTypeEnum());
			}
			else if (DeviceTypeEnum.LCM.equals(device.getDeviceType()))
			{
				assertNotNull(((LCM)device).getLcmTypeEnum());
			}
		}
	}

	// -------------------------------------------------------------------------
}
