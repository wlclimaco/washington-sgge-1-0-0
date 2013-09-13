package com.sensus.dm.gas.device.bcl;

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
import com.sensus.dm.gas.device.dac.IGasMeterDAC;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.gas.device.model.GasMeterSearch;

/**
 * The Class ElectricMeterBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/gas/device/gasMeterbclimpltest.xml"})
public class GasMeterBCLImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device bcl. */
	private IGasMeterBCL gasMeterBCL;

	/**
	 * Gets the gas meter bcl.
	 * 
	 * @return the gas meter bcl
	 */
	public IGasMeterBCL getGasMeterBCL()
	{
		return gasMeterBCL;
	}

	/**
	 * Sets the gas meter bcl.
	 * 
	 * @param gasMeterBCL the new gas meter bcl
	 */
	@Resource(name = "gasMeterBCLTarget")
	public void setGasMeterBCL(IGasMeterBCL gasMeterBCL)
	{
		this.gasMeterBCL = gasMeterBCL;
	}

	/**
	 * Sets the device area.
	 */
	public void setGasMeterArea()
	{
		setArea(getGasMeterBCL(), EPMAreaEnum.DEVICE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setGasMeterArea();
	}

	/**
	 * Removes the device area.
	 */
	@After
	public void resetMocksToGasMeterArea()
	{
		resetMocksData(getGasMeterBCL());
		setGasMeterArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all gas meters.
	 */
	@Test
	public void testFetchAllGasMeters()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		// Success situation
		GasMeterSearch gasMeterSearch = new GasMeterSearch();
		gasMeterSearch.setGasMeter((GasMeter)TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER));
		DeviceSearch deviceSearch = new DeviceSearch(gasMeterSearch);
		request.setDeviceSearch(deviceSearch);
		request.setDeviceType(DeviceTypeEnum.GAS_METER);

		InternalResultsResponse<GasMeter> response = getGasMeterBCL().fetchAllGasMeters(request);

		TestBaseUtil.assertResultResponse(response);
		assertEquals(FOUR, response.getResultsList().size());

		// Error situation
		setSituation(getGasMeterBCL(), SituationsEnum.ERROR, IGasMeterDAC.class, "fetchAllGasMeters");
		request.setDeviceType(DeviceTypeEnum.GAS_METER);
		response = getGasMeterBCL().fetchAllGasMeters(request);
		assertMessages(response, ERROR_CODE);

	}

	/**
	 * Test fetch gas meter by id.
	 */
	@Test
	public void testFetchGasMeterById()
	{
		// Success situation - with device type gas meter

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER));

		InternalResultsResponse<Device> response = getGasMeterBCL().fetchGasMeterById(request);

		TestBaseUtil.assertResultResponse(response);
		executeAssertsForDeviceGetId(response);

		// Error situation - fetchGasMeterById
		setSituation(getGasMeterBCL(), SituationsEnum.ERROR, IGasMeterDAC.class, "fetchGasMeterById");
		response = getGasMeterBCL().fetchGasMeterById(request);
		assertMessages(response, ERROR_CODE);

		resetMocksToGasMeterArea();
	}

	/**
	 * Test generate csv.
	 */
	@Test
	public void testGenerateCSV() throws Exception
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
		InternalResponse response = getGasMeterBCL().generateDevicesCSV(request);

		TestBaseUtil.assertResponse(response);

		List<String[]> data = fileReader(request.getFileName());

		assertEquals(SensusMessageUtil.getMessage(DEVICE_ID_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ZERO]);

		assertEquals(SensusMessageUtil.getMessage(NETWORK_ADDRESS_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ONE]);

		assertEquals(FLEXNET_ID.toString(), data.get(ONE)[ONE]);

		resetMocksToGasMeterArea();

		// Error situation - fetchAllGasMeters
		setSituation(getGasMeterBCL(), SituationsEnum.ERROR, IGasMeterDAC.class, "fetchAllGasMeters");
		response = getGasMeterBCL().generateDevicesCSV(request);
		assertMessages(response, ERROR_CODE);
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
