package com.sensus.lc.controller.light.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.bcf.ILightCustomSearchBCF;
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
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.light.model.response.CustomSearchResponse;
import com.sensus.lc.light.model.response.LightFilterResponse;

public class LightCustomSearchBCFMockImpl extends BaseMockImpl implements ILightCustomSearchBCF
{

	@Override
	public CustomSearchResponse fetchAllCustomSearch(LightRequest lightRequest)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setCustomSearchList(generateCustomSearch());
			return response;
		}

		return (CustomSearchResponse)testOtherDefaultModes(response);

	}

	@Override
	public LightFilterResponse fetchLightFilters(LightFilterRequest lightFilterRequest)
	{
		LightFilterResponse response = new LightFilterResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setLightFilterValueList(generateFiltersValue());
			return response;
		}

		return (LightFilterResponse)testOtherDefaultModes(response);
	}

	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return generateColumnsFilters();
		}

		return (ColumnFilterResponse)testOtherDefaultModes(response);
	}

	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (CustomSearchResponse)testOtherDefaultModes(response);
	}

	@Override
	public ColumnFilterResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (CustomSearchResponse)testOtherDefaultModes(response);
	}

	private ColumnFilterResponse generateColumnsFilters()
	{
		ColumnFilterResponse response = new ColumnFilterResponse();
		response.setListColumn(new ArrayList<Column>());
		response.setFilters(new ArrayList<Filter>());

		Column column = new Column();

		column.setColumnEnum(LightColumnEnum.ADDRESS);
		response.getListColumn().add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.ALERTS);
		response.getListColumn().add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.BLINK_LEVEL);
		response.getListColumn().add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.BULB_SERIAL_NUMBER);
		response.getListColumn().add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.CITY);
		response.getListColumn().add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.COLOR_TEMPERATURE);
		response.getListColumn().add(column);

		Filter filter = new Filter();
		filter.setFilterEnum(FilterEnum.ADDRESS);
		filter.setFilterEnumValue("0");
		filter.setDisplayOrder(0);
		response.getFilters().add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.ALARM_TYPE);
		filter.setFilterEnumValue("0");
		filter.setDisplayOrder(0);
		response.getFilters().add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.ALERTS);
		filter.setFilterEnumValue("0");
		filter.setDisplayOrder(0);
		response.getFilters().add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.BULB_SERIAL_NUMBER);
		filter.setFilterEnumValue("0");
		filter.setDisplayOrder(0);
		response.getFilters().add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.COLOR_TEMPERATURE);
		filter.setFilterEnumValue("0");
		filter.setDisplayOrder(0);
		response.getFilters().add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.CONFIGURATION);
		filter.setFilterEnumValue("0");
		filter.setDisplayOrder(0);
		response.getFilters().add(filter);

		return response;

	}

	private List<LightFilterValue> generateFiltersValue()
	{
		List<LightFilterValue> filtersValue = new ArrayList<LightFilterValue>();

		LightFilterValue filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.COLOR_TEMPERATURE);
		filterValue.setFilterValue(LightFilterTypeEnum.COLOR_TEMPERATURE.name());
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.DIMMABLE);
		filterValue.setFilterValue(LightFilterTypeEnum.DIMMABLE.name());
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.FIRMWARE_VERSION);
		filterValue.setFilterValue(LightFilterTypeEnum.FIRMWARE_VERSION.name());
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.HOUSING);
		filterValue.setFilterValue(LightFilterTypeEnum.HOUSING.name());
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.HOUSING_COLOR);
		filterValue.setFilterValue(LightFilterTypeEnum.HOUSING_COLOR.name());
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.INPUT_VOLTAGE_RANGE);
		filterValue.setFilterValue(LightFilterTypeEnum.INPUT_VOLTAGE_RANGE.name());
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.LAMP_TYPE);
		filterValue.setFilterValue(LightFilterTypeEnum.LAMP_TYPE.name());
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.LAMP_TYPE_WATTAGE_DIMMABLE);
		filterValue.setFilterValue("100 W");
		filtersValue.add(filterValue);

		filterValue = new LightFilterValue();
		filterValue.setLightFilterType(LightFilterTypeEnum.WATTAGE_RATING);
		filterValue.setFilterValue("100 W");
		filtersValue.add(filterValue);

		return filtersValue;
	}

	private List<CustomSearch> generateCustomSearch()
	{
		List<CustomSearch> customSearchList = new ArrayList<CustomSearch>();
		CustomSearch customSearch;

		for (int i = 0; i < 25; i++)
		{
			customSearch = new CustomSearch();
			customSearch.setId(i);
			customSearch.setName("Search Test");

			customSearchList.add(customSearch);
		}

		return customSearchList;

	}
}
