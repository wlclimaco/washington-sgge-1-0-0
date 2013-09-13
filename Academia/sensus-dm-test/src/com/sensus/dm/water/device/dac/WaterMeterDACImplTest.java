package com.sensus.dm.water.device.dac;

import static com.sensus.dm.common.util.TestBaseUtil.assertResponse;
import static com.sensus.dm.common.util.TestBaseUtil.assertResultResponse;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.WaterMeterSearch;
import com.sensus.dm.water.device.model.WaterMeterTypeEnum;

/**
 * The Class WaterMeterDACImplTest.
 * 
 * @author QAT Global.
 */
public class WaterMeterDACImplTest extends AbstractTestBaseDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant CONST_MARK. */
	private static final String CONST_MARK = "###";

	/** The Constant CONST_0_DOT. */
	private static final String CONST_0_DOT = "0.";

	/** The Constant CONST_9999_DOT. */
	private static final String CONST_9999_DOT = "9999.";

	/** The Constant CONST_R. */
	private static final String CONST_R = "R";

	/** The Constant CONST_B. */
	private static final String CONST_B = "B";

	/** The Constant FIRMWARE_FLEXNET. */
	private static final String FIRMWARE_FLEXNET = "005.000.000.000.1";

	/** The Constant FIRMWARE_FLEXNET_005_010_007_004_1. */
	private static final String FIRMWARE_FLEXNET_005_010_007_004_1 = "005.010.007.004.1";

	/** The Constant FIRMWARE_METER. */
	private static final String FIRMWARE_METER = "005.000";

	/** The Constant HAN_DEVICE_FLEXNET_ID. */
	private static final String HAN_DEVICE_FLEXNET_ID = "2153943262073613";

	/** The Constant HAN_DEVICE_FLEXNET_ID_1010. */
	private static final String HAN_DEVICE_FLEXNET_ID_1010 = "1010";

	/** The Constant HAN_DEVICE_FLEXNET_ID_2153943262073425. */
	private static final String HAN_DEVICE_FLEXNET_ID_2153943262073425 = "2153943262073425";

	/** The Constant HAN_DEVICE_FLEXNET_ID_2153943262073700. */
	private static final String HAN_DEVICE_FLEXNET_ID_2153943262073700 = "2153943262073700";

	/** The Constant INVALID_DEVICE_ID. */
	private static final String INVALID_DEVICE_ID = "InvalidDeviceId";

	/** The Constant LIST_SIZE. */
	private static final String LIST_SIZE = "### list size: ";

	/** The Constant PREMISE_ID. */
	private static final String PREMISE_ID = "1011";

	/** The Constant TOTAL_AVAIABLE. */
	private static final String TOTAL_AVAIABLE = "### total avaiable: ";

	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(WaterMeterDACImplTest.class);

	// -------------------------------------------------------------------------

	/**
	 * Test firmware conversion fetched.
	 */
	@Test
	public void testFirmwareConversionFetched()
	{
		// Success situation

		String value = FIRMWARE_FLEXNET_005_010_007_004_1;

		String[] strValues = StringUtils.split(value, DOT);

		StringBuffer sb = new StringBuffer();

		if (value.substring(value.length() - 1, value.length()).equalsIgnoreCase(ONE_STR))
		{
			sb.append(CONST_B);
		}
		else
		{
			sb.append(CONST_R);
		}

		LOG.info(CONST_MARK);
		for (String str : strValues)
		{
			sb.append(Integer.valueOf(str)).append(DOT);
		}

		LOG.info(CONST_MARK + sb.substring(0, sb.length() - 1).replaceAll(CONST_9999_DOT, CONST_0_DOT));
		LOG.info(CONST_MARK + sb.substring(0, sb.length() - 1).replaceAll(CONST_9999_DOT, CONST_0_DOT));

		if (sb.substring(sb.length() - THREE, sb.length()).equals(".1."))
		{
			LOG.info(CONST_MARK + CONST_B + sb.substring(0, sb.length() - THREE));
		}
		else
		{
			LOG.info(CONST_MARK + CONST_R + sb.substring(0, sb.length() - THREE));
		}

		LOG.info(CONST_MARK + sb.substring(0, sb.length() - THREE));

	}

	/**
	 * Test firmware conversion.
	 */
	@Test
	public void testFirmwareConversion()
	{
		// Success situation - string firmwareFlexnet = "005.000.000.000.1";
		String firmwareFlexnet = "50.02.1";

		StringBuilder sb = new StringBuilder();

		String[] values = StringUtils.split(firmwareFlexnet, DOT);

		LOG.info(CONST_MARK);
		for (String str : values)
		{
			sb.append(StringUtils.leftPad(str, THREE, "0")).append(DOT);
		}

		LOG.info(sb.substring(0, sb.length() - 1));

	}

	/**
	 * Test fetch meter by id.
	 */
	@Test
	public void testFetchWaterMeterById()
	{
		// Success Situation - filtering by meter id (clientEndpointId)

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(new Device(DEVICE_ID_WATER));

		InternalResultsResponse<Device> response = getWaterMeterDAC().fetchWaterMeterById(request);

		assertResponse(response);

		System.out.println(response.getResultsList());

		// Success Situation - filtering by rep id (flexnet id)
		request = new DeviceRequest();
		request.addDevice(new Device(new Radio(new BigInteger(FLEXNET_ID_WATER))));

		response = getWaterMeterDAC().fetchWaterMeterById(request);

		assertResponse(response);

		System.out.println(response.getResultsList());

		// Error Situation - filtering by meter id (clientEndpointId)

		request = new DeviceRequest();
		request.addDevice(new Device(INVALID_DEVICE_ID));

		response = getWaterMeterDAC().fetchWaterMeterById(request);

		assertResponse(response);
	}

	/**
	 * Test fetch all devices firmware.
	 */
	@Test
	public void testFetchAllDevicesFirmware()
	{
		// Success situation

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		WaterGasMeterConfiguration configuration = new WaterGasMeterConfiguration();
		configuration.setFirmwareFlexnet(FIRMWARE_FLEXNET);

		WaterMeter waterMeter = new WaterMeter();
		waterMeter.setConfiguration(configuration);

		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);

		List<WaterGasMeterStatusEnum> waterGasMeterStatusEnumList = new ArrayList<WaterGasMeterStatusEnum>();

		waterGasMeterStatusEnumList.add(WaterGasMeterStatusEnum.FIXED_BASE_MOM);
		waterGasMeterStatusEnumList.add(WaterGasMeterStatusEnum.IDLE);
		waterMeterSearch.setWaterGasMeterStatusEnumList(waterGasMeterStatusEnumList);

		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		// // Success situation - fetching using complete firmware flexnet
		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

		LOG.info(CONST_MARK);
		LOG.info("### fetching using complete firmware flexnet ###");
		LOG.info(LIST_SIZE + response.getResultsList().size());
		LOG.info(TOTAL_AVAIABLE + response.getResultsSetInfo().getTotalRowsAvailable());

		for (Device device : response.getResultsList())
		{
			LOG.info(CONST_MARK + device.getRadio().getFlexNetId().toString() + SPACE
					+ device.getRadio().getCustomerId());
		}

		LOG.info(CONST_MARK);
		LOG.info("### fetching using part of firmware flexnet ###");

		configuration = new WaterGasMeterConfiguration();
		configuration.setFirmwareFlexnet(FIRMWARE_METER);

		waterMeter = new WaterMeter();
		waterMeter.setConfiguration(configuration);
		waterMeterSearch.setWaterMeter(waterMeter);
		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

		LOG.info(LIST_SIZE + response.getResultsList().size());
		LOG.info(TOTAL_AVAIABLE + response.getResultsSetInfo().getTotalRowsAvailable());

		for (Device device : response.getResultsList())
		{
			LOG.info(CONST_MARK + device.getRadio().getFlexNetId().toString() + SPACE
					+ device.getRadio().getCustomerId());
		}

	}

	/**
	 * Test fetch all devices ids.
	 */
	@Test
	public void testFetchAllDevicesIds()
	{
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setPaginationAllSelected(false);
		request.setSelectionPaginationIds(new ArrayList<BigInteger>());
		request.getSelectionPaginationIds().add(new BigInteger(HAN_DEVICE_FLEXNET_ID));
		request.getSelectionPaginationIds().add(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073700));
		request.getSelectionPaginationIds().add(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073425));
		request.getSelectionPaginationIds().add(new BigInteger(HAN_DEVICE_FLEXNET_ID_1010));

		request.setEndRow(0);

		Group group = new Group(SEVEN);
		group.addDevice(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID))));
		group.addDevice(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073700))));
		group.addDevice(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID_2153943262073425))));
		group.addDevice(new Device(new Radio(new BigInteger(HAN_DEVICE_FLEXNET_ID_1010))));

		List<Group> groups = new ArrayList<Group>();
		groups.add(group);

		// request.setGroups(groups);

		request.setMaxPreQueryCount(0);
		request.setDeviceSearch(new DeviceSearch());
		request.setStartPage(0);

		// in test
		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

		for (Device device : response.getResultsList())
		{
			LOG.info("************************************************************************** - "
					+ device.getRadio().getFlexNetId().toString());
		}

		// not in test
		request.setPaginationAllSelected(true);
		response = getWaterMeterDAC().fetchAllWaterMeters(request);

		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch all devices tag.
	 */
	@Test
	public void testFetchAllDevicesTag()
	{
		// Success situation

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.setTags(new ArrayList<Tag>());
		request.getTags().add(new Tag(1));

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);

		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices group.
	 */
	@Test
	public void testFetchAllDevicesGroup()
	{
		// Success situation

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.setGroups(new ArrayList<Group>());
		request.getGroups().add(new Group(SEVEN));

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);

		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices city address zip.
	 */
	@Test
	public void testFetchAllDevicesCityAddressZip()
	{
		// Success situation - all devices
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		WaterMeter waterMeter = new WaterMeter();
		waterMeter.setRadio(new Radio(new Location(ADDRESS, CITY, ZIP)));
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);
		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

		// Success situation - flexnet id
		request.setDeviceSearch(new DeviceSearch());
		waterMeter = new WaterMeter();
		waterMeter.setRadio(new Radio(new BigInteger(FLEXNET_ID)));
		waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);
		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

		// Success situation - premise id
		request.setDeviceSearch(new DeviceSearch());
		WaterGasMeterConfiguration configuration = new WaterGasMeterConfiguration();
		configuration.setPremiseId(PREMISE_ID);

		waterMeter = new WaterMeter();
		waterMeter.setConfiguration(configuration);
		waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);
		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices device types.
	 */
	@Test
	public void testFetchAllDevicesDeviceTypes()
	{
		// Success situation - filter by device type
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().addDeviceType(DeviceTypeEnum.WATER_METER);

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertResultResponse(response);

		// Success situation - filter by device types description

		request.getDeviceSearch().addDeviceType(null);

		request.getDeviceSearch().addDeviceModels(
				new DeviceModel(WaterMeterTypeEnum.NORTH_AMERICAN_2WAY_WATER.getValue(), null,
						WaterMeterTypeEnum.NORTH_AMERICAN_2WAY_WATER.getWaterMeterTypeDescription()));

		response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all devices firmware flexnet.
	 */
	@Test
	public void testFetchAllDevicesFirmwareFlexnet()
	{
		// Success situation - filter by firmware meter
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		WaterGasMeterConfiguration configuration = new WaterGasMeterConfiguration();
		configuration.setFirmwareFlexnet(FIRMWARE_FLEXNET);

		WaterMeter waterMeter = new WaterMeter();
		waterMeter.setConfiguration(configuration);

		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.setWaterMeter(waterMeter);

		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch all devices process id.
	 */
	@Test
	public void testFetchAllDevicesProcessId()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setProcessId(ONE);
		request.setDeviceSearch(new DeviceSearch());

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices quarantine.
	 */
	@Test
	public void testFetchAllDevicesQuarantine()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.setPreQueryCount(true);
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		WaterMeter waterMeter = new WaterMeter();
		waterMeter.setQuarantine(true);
		request.setDeviceSearch(new DeviceSearch(new WaterMeterSearch(waterMeter)));

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all water meter by alarms.
	 */
	@Test
	public void testFetchAllWaterMeterByAlarms()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.setPreQueryCount(true);

		// Filter by Alarm
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();
		waterMeterSearch.addAlarmEnum(AlarmEnum.BROKEN_PIPE);
		waterMeterSearch.addAlarmEnum(AlarmEnum.MULTIPLE);

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setWaterMeterSearch(waterMeterSearch);
		request.getSortExpressions().clear();

		// Sort by Alarm
		request.addSortExpressions(new SortExpression(DeviceColumnEnum.ALARM.getValue(), Direction.Ascending));
		request.addSortExpressions(new SortExpression(DeviceColumnEnum.ALARM_TIME.getValue(), Direction.Ascending));

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch all devices install date.
	 */
	@Test
	public void testFetchAllDevicesInstallDate()
	{
		// Success situation - start date only

		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequestWithPagination();
		request.getSortExpressions().add(TestBaseUtil.createSortExpression(DeviceColumnEnum.DEVICE_ID.getValue()));

		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setStartDate(new Date());

		InternalResultsResponse<WaterMeter> response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

		// Success situation - end date only
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setEndDate(new Date());
		response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

		// Success situation - both
		request.setDeviceSearch(new DeviceSearch());
		request.getDeviceSearch().setStartDate(new Date());
		request.getDeviceSearch().setEndDate(new Date());
		response = getWaterMeterDAC().fetchAllWaterMeters(request);
		assertNotNull(response.getResultsList());

	}

	/**
	 * Test fetch communication.
	 */
	@Test
	public void testFetchCommunication()
	{
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.setTenant(TestBaseUtil.createTenant());
		InternalResultsResponse<WaterGasMeterStatusCount> response = getWaterMeterDAC().fetchCommunication(request);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Test fetch leak report.
	 */
	@Test
	public void testFetchLeakReport()
	{
		// Success Situation without FlexNetId - should bring all leaks
		DeviceRequest request =
				new DeviceRequest(new WaterMeter(new Radio(null, CUSTOMER_ID)),
						TestBaseUtil.createTenant(CUSTOMER_ID));

		InternalResultsResponse<WaterLeak> response = getWaterMeterDAC().fetchLeakReport(request);
		assertResponse(response);

		for (WaterLeak wl : response.getResultsList())
		{
			System.out.println(wl);
		}
	}
}
