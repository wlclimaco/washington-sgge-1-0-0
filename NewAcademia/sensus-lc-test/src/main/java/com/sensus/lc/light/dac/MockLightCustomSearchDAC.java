package com.sensus.lc.light.dac;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Class MockLightCustomSearchDAC.
 */
public class MockLightCustomSearchDAC extends AbstractMockBase implements ILightCustomSearchDAC
{

	/** The Constant ARBITRARY_CUSTOM_SEARCH_ID_100. */
	private static final Integer ARBITRARY_CUSTOM_SEARCH_ID_100 = 10;

	/** The Constant NAME. */
	private static final String NAME = "name";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightCustomSearchDAC#insertCustomSearch(com.sensus.lc.light.model.CustomSearch)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		Filter filter = new Filter();
		filter.setFilterEnum(FilterEnum.ADDRESS);
		List<Filter> listFilters = new ArrayList<Filter>();
		listFilters.add(filter);

		Column column = new Column();
		column.setColumnEnum(LightColumnEnum.CITY);
		List<Column> listColumns = new ArrayList<Column>();
		listColumns.add(column);

		CustomSearch customSearch = new CustomSearch();

		customSearch.setId(ARBITRARY_CUSTOM_SEARCH_ID_100);
		customSearch.setListFilters(listFilters);
		customSearch.setListColumn(listColumns);
		customSearch.setName(NAME);

		response.addResult(customSearch);
		response.setStatus(Status.OperationSuccess);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightCustomSearchDAC#deleteCustomSearch(com.sensus.lc.light.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.OperationSuccess);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightCustomSearchDAC#fetchAllCustomSearch(com.sensus.lc.light.model.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(LightRequest lightRequest)
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		response.getResultsList().add(new CustomSearch("Name", "Description"));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.dac.ILightCustomSearchDAC#insertColumnsToCustomSearch(com.sensus.lc.light.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.OperationSuccess);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.dac.ILightCustomSearchDAC#insertFiltersToCustomSearch(com.sensus.lc.light.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		response.setStatus(Status.OperationSuccess);
		return response;
	}

}
