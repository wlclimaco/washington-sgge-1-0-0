package com.sensus.lc.light.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.ListTypeEnum;
import com.sensus.lc.light.dac.ILightColumnFilterDAC;
import com.sensus.lc.light.dac.ILightCustomSearchDAC;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.LightFilterTypeEnum;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.SearchParameter;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;

/**
 * Test class for LightCustomSearch BCL.
 * 
 * @author Thiago
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/light/lightcustomsearchbclimpletest.xml"})
public class LightCustomSearchBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant CUSTOM_SEARCH_DESCRIPTION. */
	private static final String CUSTOM_SEARCH_DESCRIPTION = "custom search description";

	/** The Constant ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED. */
	private static final String ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED =
			"sensus.mlc.lightbclimpl.add.property.to.customsearch.failed";

	/** The Constant FETCH_COLUMN_FILTER_FAILED. */
	private static final String FETCH_COLUMN_FILTER_FAILED = "sensus.mlc.lightbclimpl.fetch.column.filter.failed";

	/** The Constant ARBITRARY_NUMBER_ONE. */
	private static final String ARBITRARY_NUMBER_ONE = "1";

	/**
	 * Attributes.
	 */
	private ILightCustomSearchBCL customSearchBCL;

	/** The Constant NAME. */
	private static final String NAME = "name";

	/**
	 * Gets the custom search bcl.
	 * 
	 * @return the customSearchBCL
	 */
	public ILightCustomSearchBCL getCustomSearchBCL()
	{
		return customSearchBCL;
	}

	/**
	 * Sets the custom search bcl.
	 * 
	 * @param customSearchBCL the customSearchBCL to set
	 */
	@Resource(name = "lightCustomSearchBCLTarget")
	public void setCustomSearchBCL(ILightCustomSearchBCL customSearchBCL)
	{
		this.customSearchBCL = customSearchBCL;
	}

	/**
	 * Fetch light filters.
	 */
	@Test
	public void testFetchLightFilters()
	{
		LightFilterRequest lightFilterRequest = new LightFilterRequest();
		List<LightFilterTypeEnum> filter = new ArrayList<LightFilterTypeEnum>();
		filter.add(LightFilterTypeEnum.LAMP_TYPE);
		lightFilterRequest.setFilterList(filter);
		InternalResultsResponse<LightFilterValue> response = getCustomSearchBCL().fetchLightFilters(lightFilterRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		LightRequest lightRequest = new LightRequest();
		InternalResultsResponse<CustomSearch> response =
				getCustomSearchBCL().fetchAllCustomSearch(lightRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch all column filters.
	 */
	@Test
	public void testFetchAllColumnFilters()
	{
		// Success Situation (with default values to Filters and Columns)
		ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		InternalResultsResponse<ColumnFilterResponse> response =
				getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		TestBaseUtil.assertResultResponse(response);

		// Success Situation (returning Filters and Columns from fetchAllColumnFilters)
		setTestControl(getCustomSearchBCL(), ILightColumnFilterDAC.class, "RETURN_COLUMN_FILTER");
		columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		TestBaseUtil.assertResultResponse(response);

		// Success Situation (returning only columns from fetchAllColumnFilters)
		setTestControl(getCustomSearchBCL(), ILightColumnFilterDAC.class, "RETURN_ONLY_COLUMN");
		columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		TestBaseUtil.assertResultResponse(response);

		// Success Situation (returning only filters from fetchAllColumnFilters)
		setTestControl(getCustomSearchBCL(), ILightColumnFilterDAC.class, "RETURN_ONLY_FILTER");
		columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		TestBaseUtil.assertResultResponse(response);
		assertEquals("the numbers of filters should be 8", response.getFirstResult().getFilters().size(), 8);

		// Error Situation - With a invalid kind of ListTypeEnum
		columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setListTypeEnum(ListTypeEnum.DEFAULT);
		response = getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
		assertTrue(response.isInError());
		assertMessages(response, FETCH_COLUMN_FILTER_FAILED);

	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName(NAME);
		customSearch.setDescription(CUSTOM_SEARCH_DESCRIPTION);

		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.GROUP_ID);
		param.setValue(ARBITRARY_NUMBER_ONE);

		customSearch.getSearchParameters().add(param);

		CustomSearchRequest customSearchRequest = new CustomSearchRequest();
		customSearchRequest.setCustomSearch(customSearch);

		// Success Situation
		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().insertCustomSearch(customSearchRequest);
		TestBaseUtil.assertResultResponse(response);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertEquals("should return the same name  ", customSearch.getName(), response.getFirstResult().getName());

		// Success situation (inserting a list of filter and column)
		customSearch = new CustomSearch();
		customSearch.setName(NAME);
		customSearch.setDescription(CUSTOM_SEARCH_DESCRIPTION);

		param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.GROUP_ID);
		param.setValue(ARBITRARY_NUMBER_ONE);

		customSearch.getSearchParameters().add(param);

		// Column
		Column column = new Column();
		column.setColumnEnum(LightColumnEnum.CITY);
		customSearch.setListColumn(Arrays.asList(column));

		// Filter
		Filter filter = new Filter();
		filter.setFilterEnum(FilterEnum.HOUSING_COLOR);
		customSearch.setListFilters(Arrays.asList(filter));

		customSearchRequest = new CustomSearchRequest();
		customSearchRequest.setCustomSearch(customSearch);

		response = getCustomSearchBCL().insertCustomSearch(customSearchRequest);
		TestBaseUtil.assertResultResponse(response);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertEquals("should return the same name  ", customSearch.getName(), response.getFirstResult().getName());

		// Error situation
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, ILightCustomSearchDAC.class,
				"insertCustomSearch");

		customSearch = new CustomSearch();
		customSearch.setName(NAME);
		customSearch.setDescription(CUSTOM_SEARCH_DESCRIPTION);
		response = getCustomSearchBCL().insertCustomSearch(customSearchRequest);
		assertTrue(response.isInError());
		assertMessages(response, ERROR_CODE);

		// Error situation during insert of the columns/filters in the custom search
		setSituation(getCustomSearchBCL(), SituationsEnum.ERROR, ILightCustomSearchDAC.class,
				"insertFiltersToCustomSearch");

		customSearch = new CustomSearch();
		customSearch.setName(NAME);
		customSearch.setDescription(CUSTOM_SEARCH_DESCRIPTION);
		response = getCustomSearchBCL().insertCustomSearch(customSearchRequest);
		assertTrue(response.isInError());
		assertMessages(response, ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED);

	}

	/**
	 * Test insert column filters.
	 */
	@Test
	public void testInsertColumnFilters()
	{
		ColumnFilterRequest request = new ColumnFilterRequest();

		// Test Success
		// The default case cannot be tested because the ListTypeEnum has just one attribute
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);

		List<Filter> filters = new ArrayList<Filter>();

		Filter filter = new Filter();
		filter.setFilterEnum(FilterEnum.ADDRESS);
		filter.setDisplayOrder(0);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.ALARM_TYPE);
		filter.setDisplayOrder(1);
		filters.add(filter);

		request.setFilters(filters);

		List<Column> listColumn = new ArrayList<Column>();
		Column column = new Column();
		column.setColumnEnum(LightColumnEnum.FLEXNET_ID);
		column.setDisplayOrder(0);
		listColumn.add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.LAMP_TYPE_WATTAGE_DIMMABLE);
		column.setDisplayOrder(0);
		listColumn.add(column);

		request.setListColumn(listColumn);

		InternalResponse response = getCustomSearchBCL().insertColumnFilters(request);
		assertResponse(response);
		assertNotNull(response);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Error situation
		request = new ColumnFilterRequest();
		request.setListTypeEnum(ListTypeEnum.DEFAULT);
		response = getCustomSearchBCL().insertColumnFilters(request);
		assertTrue(response.isInError());
		assertMessages(response, ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED);

	}

	/**
	 * Test verify custom search.
	 */
	@Test
	public void testVerifyCustomSearch()
	{
		SearchLight searchLight = new SearchLight();

		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
		searchParameters.add(new SearchParameter(PropertyEnum.GROUP_ID, ARBITRARY_NUMBER_ONE));
		searchLight.setSearchParameters(searchParameters);

		getCustomSearchBCL().verifyCustomSearch(searchLight);

		// Error situation - without search parameter
		searchLight = new SearchLight();
		getCustomSearchBCL().verifyCustomSearch(searchLight);

		// Success situation - NULL value
		searchLight = new SearchLight();

		searchParameters = new ArrayList<SearchParameter>();
		searchParameters.add(new SearchParameter(PropertyEnum.GROUP_ID, null));
		searchLight.setSearchParameters(searchParameters);

		getCustomSearchBCL().verifyCustomSearch(searchLight);

		// Success situation - OFFSET_SCHEDULE
		searchLight = new SearchLight();

		searchParameters = new ArrayList<SearchParameter>();
		searchParameters.add(new SearchParameter(PropertyEnum.OFFSET_SCHEDULE, ARBITRARY_NUMBER_ONE));
		searchLight.setSearchParameters(searchParameters);

		getCustomSearchBCL().verifyCustomSearch(searchLight);

		// Success situation - TAG_ID
		searchLight = new SearchLight();

		searchParameters = new ArrayList<SearchParameter>();
		searchParameters.add(new SearchParameter(PropertyEnum.TAG_ID, ARBITRARY_NUMBER_ONE));
		searchLight.setSearchParameters(searchParameters);

		getCustomSearchBCL().verifyCustomSearch(searchLight);

	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName(NAME);

		CustomSearchRequest request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);

		// good insert
		InternalResultsResponse<CustomSearch> response = getCustomSearchBCL().insertCustomSearch(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull("should return the custom search ", response.getFirstResult());
		assertEquals("should return the same name ", request.getCustomSearch().getName(), response.getFirstResult()
				.getName());

		InternalResponse responseDelete = getCustomSearchBCL().deleteCustomSearch(request);
		assertEquals(Status.OperationSuccess, responseDelete.getStatus());
	}

}
