package com.sensus.lc.light.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.LightFilterTypeEnum;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.user.model.User;

/**
 * Unit test for Light DAC.
 * 
 * @author Thiago
 * 
 */
@ContextConfiguration(locations = {
		"classpath:sensus-slc-dim-table-config-context.xml", "classpath:sensus-slc-part-number-context.xml"})
public class ColumnFilterDACTest extends AbstractTestBaseDAC
{

	/** The Constant RANDOM. */
	public static final Random RANDOM = new Random();

	/** The Constant NUMBER_RANGE. */
	public static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/**
	 * Test fetch light notification history header.
	 */
	@Test
	public void testFetchLightFilters()
	{
		LightFilterRequest lightFilterRequest = new LightFilterRequest();
		List<LightFilterTypeEnum> filterList = new ArrayList<LightFilterTypeEnum>();
		filterList.add(LightFilterTypeEnum.LAMP_TYPE_WATTAGE_DIMMABLE);
		lightFilterRequest.setFilterList(filterList);

		InternalResultsResponse<LightFilterValue> response =
				getLightColumnFilterDAC().fetchLightFilters(lightFilterRequest);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Tesfetch all column filters.
	 */
	@Test
	public void tesfetchAllColumnFilters()
	{
		ColumnFilterRequest request = new ColumnFilterRequest();
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResultsResponse<ColumnFilterResponse> response =
				getLightColumnFilterDAC().fetchAllColumnFilters(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test insert columns to custom search.
	 */
	@Test
	public void testInsertColumnsToCustomSearch()
	{
		// Insert the custom search and after we will try to insert the columns to this new custom search
		CustomSearchRequest customSearchRequest = new CustomSearchRequest();

		User user = insertUser();
		UserContext userContext = TestBaseUtil.createUserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());
		customSearchRequest.setUserContext(userContext);

		CustomSearch customSearch = insertCustomSearch(userContext);

		customSearchRequest.setCustomSearch(customSearch);

		// Insert the columns to the custom search
		ColumnFilterRequest request = new ColumnFilterRequest();
		request.setCustomSearchId(customSearch.getId());
		request.setUserContext(userContext);

		List<Column> listColumns = new ArrayList<Column>();

		Column column = new Column();
		column.setColumnEnum(LightColumnEnum.MODEL_NUMBER);
		listColumns.add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.LAMP_TYPE_WATTAGE_DIMMABLE);
		listColumns.add(column);

		request.setListColumn(listColumns);

		InternalResponse internalResponse = getLightColumnFilterDAC().insertColumnsToCustomSearch(request);
		TestBaseUtil.assertResponse(internalResponse);
	}

	/**
	 * Test insert filters to custom search.
	 */
	@Test
	public void testInsertFiltersToCustomSearch()
	{
		// Insert the custom search and after we will try to insert the filters to this new custom search
		CustomSearchRequest customSearchRequest = new CustomSearchRequest();

		User user = insertUser();
		UserContext userContext = TestBaseUtil.createUserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());
		customSearchRequest.setUserContext(userContext);

		CustomSearch customSearch = insertCustomSearch(userContext);

		customSearchRequest.setCustomSearch(customSearch);

		// Insert the columns to the custom search
		ColumnFilterRequest request = new ColumnFilterRequest();
		request.setCustomSearchId(customSearch.getId());
		request.setUserContext(userContext);

		List<Filter> listFilters = new ArrayList<Filter>();

		Filter filter = new Filter();
		filter.setFilterEnum(FilterEnum.ADDRESS);
		listFilters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.BULB_SERIAL_NUMBER);
		listFilters.add(filter);

		request.setFilters(listFilters);

		InternalResponse internalResponse = getLightColumnFilterDAC().insertFiltersToCustomSearch(request);
		TestBaseUtil.assertResponse(internalResponse);

	}

	/**
	 * Creates the custom search.
	 * 
	 * @return the custom search
	 */
	private CustomSearch createCustomSearch()
	{
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName("CustomSearch" + RANDOM.nextInt(NUMBER_RANGE));
		customSearch.setDescription("Description Custom Search " + RANDOM.nextInt(NUMBER_RANGE));

		return customSearch;
	}

	/**
	 * Insert custom search.
	 * 
	 * @param userContext the user context
	 * @return the custom search
	 */
	private CustomSearch insertCustomSearch(UserContext userContext)
	{
		CustomSearch customSearch = createCustomSearch();

		CustomSearchRequest customSearchRequest = new CustomSearchRequest();
		customSearchRequest.setUserContext(userContext);
		customSearchRequest.setCustomSearch(customSearch);
		InternalResultsResponse<CustomSearch> response =
				getLightCustomSearchDAC().insertCustomSearch(customSearchRequest);
		assertResultResponse(response);
		assertNotNull("Custom search should be not null", customSearch);
		assertNotNull("Custom search identifier should be not null", customSearch.getId());
		return customSearch;
	}
}
