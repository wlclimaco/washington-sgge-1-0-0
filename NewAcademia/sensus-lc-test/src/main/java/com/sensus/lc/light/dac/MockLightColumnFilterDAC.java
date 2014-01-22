package com.sensus.lc.light.dac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.LightFilterTypeEnum;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;

/**
 * The Class MockLightColumnFilterDAC.
 */
public class MockLightColumnFilterDAC extends AbstractMockBase implements ILightColumnFilterDAC
{

	@Override
	public InternalResponse insertColumnsToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResultsResponse<ColumnFilterResponse> response = new InternalResultsResponse<ColumnFilterResponse>();
		response.setStatus(Status.OperationSuccess);
		ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();

		if (getTestControl() == "RETURN_COLUMN_FILTER")
		{
			// Column to return
			Column column = new Column();
			column.setColumnEnum(LightColumnEnum.ADDRESS);
			columnFilterResponse.setListColumn(Arrays.asList(column));

			// Filter to return
			Filter filter = new Filter();
			filter.setFilterEnum(FilterEnum.COLOR_TEMPERATURE);
			columnFilterResponse.setFilters(Arrays.asList(filter));
		}

		if (getTestControl() == "RETURN_ONLY_COLUMN")
		{
			// Column to return
			Column column = new Column();
			column.setColumnEnum(LightColumnEnum.ADDRESS);
			columnFilterResponse.setListColumn(Arrays.asList(column));
		}

		if (getTestControl() == "RETURN_ONLY_FILTER")
		{
			// Filter to return
			List<Filter> filters = new ArrayList<Filter>();
			Filter filter = new Filter();
			filter.setFilterEnum(FilterEnum.ALARM_TYPE);
			filters.add(filter);

			filter = new Filter();
			filter.setFilterEnum(FilterEnum.GROUPS);
			filters.add(filter);

			filter = new Filter();
			filter.setFilterEnum(FilterEnum.LIFECYCLE_STATE);
			filters.add(filter);

			filter = new Filter();
			filter.setFilterEnum(FilterEnum.HOUSING_COLOR);
			filters.add(filter);

			columnFilterResponse.setFilters(filters);
		}

		response.getResultsList().add(columnFilterResponse);
		return response;
	}

	@Override
	public InternalResultsResponse<LightFilterValue> fetchLightFilters(LightFilterRequest lightFilterRequest)
	{
		InternalResultsResponse<LightFilterValue> response = new InternalResultsResponse<LightFilterValue>();
		response.setStatus(Status.OperationSuccess);
		LightFilterValue lightFilterValue = new LightFilterValue();
		lightFilterValue.setLightFilterType(LightFilterTypeEnum.LAMP_TYPE);
		lightFilterValue.setFilterValue("Led");
		response.getResultsList().add(lightFilterValue);
		return response;
	}

}
