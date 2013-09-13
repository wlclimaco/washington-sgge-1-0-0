package com.sensus.dm.common.device.bcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.dac.ICustomSearchDAC;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.Column;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceFilterEnum;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.SearchParameter;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.property.bcl.IPropertyBCL;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceConfiguration;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMConfiguration;
import com.sensus.dm.elec.device.model.LCMLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.elec.device.model.RemoteConnectStatusEnum;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.gas.device.model.GasMeterSearch;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.WaterMeterSearch;

/**
 * The Class ElectricMeterBCLImplTest.
 * 
 * @author QAT Global
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/device/customsearchbclimpltest.xml"})
public class CustomSearchBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant FIRMWAREMETER. */
	private static final String FIRMWAREMETER = "000.005.000.000.0";

	/** The Constant DEVICE_MODEL. */
	private static final String DEVICE_MODEL = "539 63 537 08002";

	/** The Constant NAME. */
	private static final String NAME = "name";

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "description";

	/** The Constant SHOULD_RETURN_THE_SAME_NAME. */
	private static final String SHOULD_RETURN_THE_SAME_NAME = "should return the same name ";

	/** The Constant SHOULD_RETURN_THE_CUSTOM_SEARCH. */
	private static final String SHOULD_RETURN_THE_CUSTOM_SEARCH = "should return the custom search ";

	/** The Constant PROPERTY_PROVIDE_TYPE_EPM_GROUP. */
	private static final String PROPERTY_PROVIDE_TYPE_EPM_GROUP = "EPM.Group";

	/** The Constant USER_CONTEXT_FAIL. */
	private static final String USER_CONTEXT_FAIL = "fail";

	/** The Constant DELETE_PROPERTY_PROVIDER. */
	private static final String DELETE_PROPERTY_PROVIDER = "deletePropertyProvider";

	/** The Constant DELETE_CUSTOM_SEARCH. */
	private static final String DELETE_CUSTOM_SEARCH = "deleteCustomSearch";

	/** The Constant UPSERT_PROPERTY. */
	private static final String UPSERT_PROPERTY = "upsertProperty";

	/** The Constant INSERT_CUSTOM_SEARCH. */
	private static final String INSERT_CUSTOM_SEARCH = "insertCustomSearch";

	/** The Constant DELETE_PROPERTY. */
	private static final String DELETE_PROPERTY = "deleteProperty";

	/** The Constant FETCH_ALL_CUSTOM_SEARCH. */
	private static final String FETCH_ALL_CUSTOM_SEARCH = "fetchAllCustomSearch";

	/**
	 * The Constant DEFAULT_DATEFORMAT.
	 * Used this value at Front-End side, if change here must change there
	 */
	private static final String DEFAULT_DATEFORMAT = "MM/dd/yyyy hh:mm:ss";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED. */
	private static final String ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED =
			"sensus.epm.devicebclimpl.add.property.to.customsearch.failed";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The custom search bcl. */
	private ICustomSearchBCL customSearchBCL; // injected by Spring through setter

	/**
	 * Gets the custom search bcl.
	 * 
	 * @return the custom search bcl
	 */
	public ICustomSearchBCL getCustomSearchBCL()
	{
		return customSearchBCL;
	}

	/**
	 * Sets the custom search bcl.
	 * 
	 * @param customSearchBCL the new custom search bcl
	 */
	@Resource
	public void setCustomSearchBCL(ICustomSearchBCL customSearchBCL)
	{
		this.customSearchBCL = customSearchBCL;
	}

	/**
	 * Sets the custom search area.
	 */
	public void setCustomSearchArea()
	{
		setArea(getCustomSearchBCL(), EPMAreaEnum.CUSTOM_SEARCH);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setCustomSearchArea();
	}

	/**
	 * Removes the custom search area.
	 */
	@After
	public void resetMocksToCustomSearchArea()
	{
		resetMocksData(getCustomSearchBCL());
		setCustomSearchArea();
	}

	/**
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		// Success situation
		InquiryCustomSearchRequest request = TestBaseUtil.createInquiryCustomSearchRequest();

		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().fetchAllCustomSearch(request);
		TestBaseUtil.assertResultResponse(response);

		// Error situation
		resetMocksToCustomSearchArea();
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, ICustomSearchDAC.class, FETCH_ALL_CUSTOM_SEARCH);
		response = getCustomSearchBCL().fetchAllCustomSearch(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert column filters.
	 */
	@Test
	public void testUpdateColumnFilters()
	{
		// Success situation

		ColumnFilterRequest columnFilterRequest = TestBaseUtil.createColumnFilterRequest();
		columnFilterRequest.setPropertyEnum(PropertyEnum.ELECTRIC_METER_COLUMN);
		columnFilterRequest.setPropertyProviderType(PROPERTY_PROVIDE_TYPE_EPM_GROUP);
		columnFilterRequest.setProperties(Arrays.asList(new Property(STRING_TEN)));
		columnFilterRequest.setFilters(Arrays.asList(DeviceFilterEnum.ADDRESS));

		List<Column> listColum = new ArrayList<Column>();

		Column column = new Column(DeviceColumnEnum.CITY_NAME);
		column.setOrdered(true);
		listColum.add(column);
		columnFilterRequest.setListColumn(listColum);

		InternalResponse response = getCustomSearchBCL().updateColumnFilters(columnFilterRequest);
		TestBaseUtil.assertResponse(response);

		// Success situation - column list empty
		columnFilterRequest.getListColumn().clear();
		response = getCustomSearchBCL().updateColumnFilters(columnFilterRequest);
		TestBaseUtil.assertResponse(response);

		// Error situation
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, IPropertyBCL.class, DELETE_PROPERTY);
		response = getCustomSearchBCL().updateColumnFilters(columnFilterRequest);

		assertEquals(Status.ExceptionError, response.getStatus());
		assertEquals(1, response.getMessageInfoList().size());

		resetMocksToCustomSearchArea();
	}

	/**
	 * Test fetch all column filters.
	 */
	@Test
	public void testFetchAllColumnFilters()
	{
		// Success situation - userId
		ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest(new UserContext(USER_CONTEXT_FAIL));
		columnFilterRequest.setPropertyEnum(PropertyEnum.ELECTRIC_METER_COLUMN);

		InternalResultsResponse<ColumnFilterResponse> response =
				getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		// Success situation - CustomSearch
		columnFilterRequest.setPropertyProviderType(PROPERTY_PROVIDE_TYPE_EPM_GROUP);
		columnFilterRequest.setProperties(new ArrayList<Property>());
		Property property = new Property(STRING_TEN);
		columnFilterRequest.getProperties().add(property);

		response = getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		assertEquals(true, response.getResultsList().size() == 0);

		columnFilterRequest = new ColumnFilterRequest(new UserContext(STRING_NINETY_NINE));
		columnFilterRequest.setPropertyEnum(PropertyEnum.ELECTRIC_METER_COLUMN);
		columnFilterRequest.setProperties(new ArrayList<Property>());
		property = new Property(STRING_NINETY_NINE);
		columnFilterRequest.getProperties().add(property);

		response = new InternalResultsResponse<ColumnFilterResponse>();
		response = getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		assertEquals(true, response.getResultsList().size() == 1);
	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		// bad insert
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, ICustomSearchDAC.class);

		CustomSearchRequest request = new CustomSearchRequest();
		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().insertCustomSearch(request);

		assertEquals(Status.PersistenceError, response.getStatus());
		assertEquals(1, response.getMessageInfoList().size());

		resetMocksToCustomSearchArea();

		// bad insert
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, ICustomSearchDAC.class, INSERT_CUSTOM_SEARCH);
		request.setCustomSearch(new CustomSearch(FAIL, DESCRIPTION));

		response = getCustomSearchBCL().insertCustomSearch(request);
		assertMessages(response, ERROR_CODE);

		resetMocksToCustomSearchArea();
		CustomSearch customSearch = createDefaultCustomSearchWithSortExpression();

		List<Column> listColum = new ArrayList<Column>();
		Column column = new Column();
		column.setColumnEnum(DeviceColumnEnum.CITY_NAME);
		column.setOrdered(true);
		listColum.add(column);

		request.setTenant(TestBaseUtil.createTenant());

		// Electric
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		// good insert Electric Meter
		customSearch = createDefaultCustomSearchWithSortExpression();
		insertElectricMeter(request, response, customSearch, listColum);

		// good insert HanDevice
		customSearch = createDefaultCustomSearchWithSortExpression();
		insertHanDevice(request, customSearch, listColum);

		// good insert LCM
		customSearch = createDefaultCustomSearchWithSortExpression();
		insertLCM(request, customSearch, listColum);

		// Water Meter
		customSearch = createDefaultCustomSearchWithSortExpression();
		request.setServiceTypeEnum(ServiceTypeEnum.WATER);

		insertWaterMeter(request, customSearch, listColum);

		// Gas Meter
		customSearch = createDefaultCustomSearchWithSortExpression();
		customSearch.setDeviceType(DeviceTypeEnum.GAS_METER);

		request = insertGasMeter(request, customSearch, listColum);

		// Error situation - upsert property
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, IPropertyBCL.class, UPSERT_PROPERTY);
		response = getCustomSearchBCL().insertCustomSearch(request);

		assertEquals(Status.ExceptionError, response.getStatus());
		assertMessages(response, ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED);
		assertNotNull(SHOULD_RETURN_THE_CUSTOM_SEARCH, response.getFirstResult());
	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		CustomSearch customSearch = new CustomSearch(NAME, DESCRIPTION);

		SearchParameter param = new SearchParameter(DeviceFilterEnum.GROUP, STRING_ONE);
		customSearch.setSearchParameters(new ArrayList<SearchParameter>());
		customSearch.getSearchParameters().add(param);

		List<Column> listColum = new ArrayList<Column>();

		Column column = new Column(DeviceColumnEnum.CITY_NAME);
		column.setOrdered(true);
		listColum.add(column);

		customSearch.setListColumn(listColum);

		customSearch.setSortExpression(new SortExpression(NAME, SortExpression.Direction.Ascending));

		CustomSearchRequest request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);

		// good insert
		customSearch = createDefaultCustomSearchWithSortExpression();
		customSearch.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);

		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		request = insertElectricMeter(request, response, customSearch, listColum);

		InternalResponse responseDelete = getCustomSearchBCL().deleteCustomSearch(request);
		assertEquals(Status.OperationSuccess, responseDelete.getStatus());

		// Error situation - delete custom search
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, ICustomSearchDAC.class, DELETE_CUSTOM_SEARCH);

		responseDelete = getCustomSearchBCL().deleteCustomSearch(request);
		TestBaseUtil.assertResponse(response);

		resetMocksToCustomSearchArea();

		// Error situation - delete property provider
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, IPropertyBCL.class, DELETE_PROPERTY_PROVIDER);

		responseDelete = getCustomSearchBCL().deleteCustomSearch(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Insert electric meter.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param customSearch the custom search
	 * @param listColum the list colum
	 * @return the custom search request
	 */
	private CustomSearchRequest insertElectricMeter(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response, CustomSearch customSearch,
			List<Column> listColum)
	{
		ElectricMeter electricMeter = new ElectricMeter(new Radio(ELECTRIC_FLEXNET_ID), DEVICE_ID);
		electricMeter.getRadio().setLocation(new Location(ADDRESS, CITY, ZIP));
		electricMeter.setConfiguration(new ElectricMeterConfiguration());
		electricMeter.getConfiguration().setPremiseId(STRING_ONE);
		electricMeter.getConfiguration().setFirmwareMeter(FIRMWAREMETER);

		customSearch.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		customSearch.setElectricMeterSearch(new ElectricMeterSearch(electricMeter));
		customSearch.getElectricMeterSearch().addRemoteConnectStatusEnum(RemoteConnectStatusEnum.UNKNOWN);
		customSearch.getElectricMeterSearch().addElectricMeterLifecycleStateEnum(
				ElectricMeterLifecycleStateEnum.INSTALLED);
		customSearch.setListColumn(listColum);

		request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);
		request.setDateFormat(DEFAULT_DATEFORMAT);

		response =
				getCustomSearchBCL().insertCustomSearch(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull(SHOULD_RETURN_THE_CUSTOM_SEARCH, response.getFirstResult());
		assertEquals(SHOULD_RETURN_THE_SAME_NAME, customSearch.getName(), response.getFirstResult().getName());

		return request;
	}

	/**
	 * Insert han device.
	 * 
	 * @param request the request
	 * @param customSearch the custom search
	 * @param listColum the list colum
	 */
	private void insertHanDevice(CustomSearchRequest request, CustomSearch customSearch, List<Column> listColum)
	{
		HanDevice hanDevice = new HanDevice(new Radio(ELECTRIC_FLEXNET_ID));
		hanDevice.setDeviceId(DEVICE_ID);
		hanDevice.getRadio().setLocation(new Location(ADDRESS, CITY, ZIP));
		hanDevice.setConfiguration(new HanDeviceConfiguration());

		customSearch.setListColumn(listColum);
		customSearch = createDefaultCustomSearchWithSortExpression();
		customSearch.setHanDeviceSearch(new HanDeviceSearch(hanDevice));
		customSearch.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		customSearch.getHanDeviceSearch().addHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
		customSearch.getHanDeviceSearch().addHanLifecycleStateEnum(HanLifecycleStateEnum.PENDING_JOIN);

		request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);
		request.setDateFormat(DEFAULT_DATEFORMAT);

		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().insertCustomSearch(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull(SHOULD_RETURN_THE_CUSTOM_SEARCH, response.getFirstResult());
		assertEquals(SHOULD_RETURN_THE_SAME_NAME, customSearch.getName(), response.getFirstResult().getName());
	}

	/**
	 * Insert lcm.
	 * 
	 * @param request the request
	 * @param customSearch the custom search
	 * @param listColum the list colum
	 */
	private void insertLCM(CustomSearchRequest request, CustomSearch customSearch,
			List<Column> listColum)
	{
		LCM lcm = new LCM(new Radio(ELECTRIC_FLEXNET_ID));
		lcm.setDeviceId(DEVICE_ID);
		lcm.getRadio().setLocation(new Location(ADDRESS, CITY, ZIP));
		lcm.setConfiguration(new LCMConfiguration(FIRMWAREMETER));

		customSearch.setListColumn(listColum);
		customSearch.setLcmSearch(new LCMSearch(lcm));
		customSearch.setDeviceType(DeviceTypeEnum.LCM);
		customSearch.getLcmSearch().addLcmTypeEnum(LCMTypeEnum.LCM);
		customSearch.getLcmSearch().addAlarmEnum(AlarmEnum.BROKEN_PIPE);
		customSearch.getLcmSearch().addLcmLifecycleStateEnum(LCMLifecycleStateEnum.UNJOINED);

		request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);
		request.setDateFormat(DEFAULT_DATEFORMAT);

		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().insertCustomSearch(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull(SHOULD_RETURN_THE_CUSTOM_SEARCH, response.getFirstResult());
		assertEquals(SHOULD_RETURN_THE_SAME_NAME, customSearch.getName(), response.getFirstResult().getName());

	}

	/**
	 * Insert water meter.
	 * 
	 * @param request the request
	 * @param customSearch the custom search
	 * @param listColum the list colum
	 */
	private void insertWaterMeter(CustomSearchRequest request, CustomSearch customSearch, List<Column> listColum)
	{

		WaterMeter waterMeter = new WaterMeter();

		waterMeter.setRadio(new Radio(WATER_FLEXNET_ID));
		waterMeter.setDeviceId(DEVICE_ID);
		waterMeter.getRadio().setLocation(new Location(ADDRESS, CITY, ZIP));
		waterMeter.setConfiguration(new WaterGasMeterConfiguration(FIRMWAREMETER));
		waterMeter.getConfiguration().setPremiseId(STRING_ONE);

		customSearch.setListColumn(listColum);
		customSearch.setWaterMeterSearch(new WaterMeterSearch());
		customSearch.setDeviceType(DeviceTypeEnum.WATER_METER);
		customSearch.getWaterMeterSearch().addAlarmEnum(AlarmEnum.BROWN_OUT);
		List<WaterGasMeterStatusEnum> waterGasMeterStatusEnum = new ArrayList<WaterGasMeterStatusEnum>();
		waterGasMeterStatusEnum.add(WaterGasMeterStatusEnum.FIXED_BASE_LAT);
		customSearch.getWaterMeterSearch().setWaterGasMeterStatusEnumList(waterGasMeterStatusEnum);
		customSearch.getWaterMeterSearch().setWaterMeter(waterMeter);

		request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);
		request.setDateFormat(DEFAULT_DATEFORMAT);

		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().insertCustomSearch(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull(SHOULD_RETURN_THE_CUSTOM_SEARCH, response.getFirstResult());
		assertEquals(SHOULD_RETURN_THE_SAME_NAME, customSearch.getName(), response.getFirstResult().getName());

	}

	/**
	 * Insert gas meter.
	 * 
	 * @param request the request
	 * @param customSearch the custom search
	 * @param listColum the list colum
	 * @return the custom search request
	 */
	private CustomSearchRequest insertGasMeter(CustomSearchRequest request, CustomSearch customSearch,
			List<Column> listColum)
	{

		GasMeter gasMeter = new GasMeter(new Radio(GAS_FLEXNET_ID));
		gasMeter.setDeviceId(DEVICE_ID);
		gasMeter.getRadio().setLocation(new Location(ADDRESS, CITY, ZIP));
		gasMeter.setConfiguration(new WaterGasMeterConfiguration(FIRMWAREMETER));
		gasMeter.getConfiguration().setPremiseId(STRING_ONE);

		customSearch.setListColumn(listColum);
		customSearch.setGasMeterSearch(new GasMeterSearch(gasMeter));
		customSearch.getGasMeterSearch().addAlarmEnum(AlarmEnum.CRITICAL_HARDWARE_WARNING);
		List<WaterGasMeterStatusEnum> waterGasMeterStatusEnum = new ArrayList<WaterGasMeterStatusEnum>();
		waterGasMeterStatusEnum.add(WaterGasMeterStatusEnum.FIXED_BASE_MOM);
		customSearch.getGasMeterSearch().setWaterGasMeterStatusEnumList(waterGasMeterStatusEnum);

		request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);
		request.setDateFormat(DEFAULT_DATEFORMAT);

		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().insertCustomSearch(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull(SHOULD_RETURN_THE_CUSTOM_SEARCH, response.getFirstResult());
		assertEquals(SHOULD_RETURN_THE_SAME_NAME, customSearch.getName(), response.getFirstResult().getName());

		return request;

	}

	/**
	 * Creates the default custom search with sort expression.
	 * 
	 * @return the custom search
	 */
	private CustomSearch createDefaultCustomSearchWithSortExpression()
	{
		CustomSearch customSearch = new CustomSearch(NAME, DESCRIPTION);
		customSearch.addDeviceModels(new DeviceModel(1, null, DEVICE_MODEL));// Description
		customSearch.setStartDate(new Date());
		customSearch.setEndDate(new Date());
		customSearch.addGroup(new Group(1));
		customSearch.addTag(new Tag(1));
		customSearch.setProcessId(1);
		SortExpression sortExpression = new SortExpression(NAME, SortExpression.Direction.Ascending);
		customSearch.setSortExpression(sortExpression);

		return customSearch;
	}

}
