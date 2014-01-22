package com.sensus.lc.light.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.lc.light.dac.ILightColumnFilterDAC;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;

public class LightColumnFilterDACImpl extends SqlSessionDaoSupport implements ILightColumnFilterDAC
{

	/**
	 * Attributes.
	 */
	private static final String FETCH_ALL_COLUMN_FILTERS = "ColumnFilter.fetchAllColumnFilters";
	private static final String INSERT_COLUMNS_TO_CUSTOM_SEARCH = "LightCustomSearch.insertColumnsToCustomSearch";
	private static final String INSERT_FILTER_TO_CUSTOM_SEARCH = "LightCustomSearch.insertFilterToCustomSearch";
	private static final String FETCH_LIGHT_FILTERS = "ColumnFilter.fetchLightFilters";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightColumnFilterDAC#insertColumnsToCustomSearch(com.sensus.mlc.light.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_COLUMNS_TO_CUSTOM_SEARCH, columnFilterRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightColumnFilterDAC#insertFiltersToCustomSearch(com.sensus.mlc.light.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_FILTER_TO_CUSTOM_SEARCH, columnFilterRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightColumnFilterDAC#fetchAllColumnFilters(com.sensus.mlc.light.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResultsResponse<ColumnFilterResponse> response = new InternalResultsResponse<ColumnFilterResponse>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_COLUMN_FILTERS, columnFilterRequest,
				response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightColumnFilterDAC#fetchLightFilters(com.sensus.mlc.light.model.request.
	 * LightFilterRequest)
	 */
	@Override
	public InternalResultsResponse<LightFilterValue> fetchLightFilters(LightFilterRequest lightFilterRequest)
	{

		InternalResultsResponse<LightFilterValue> response = new InternalResultsResponse<LightFilterValue>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_LIGHT_FILTERS, lightFilterRequest, response);
		return response;
	}

}
