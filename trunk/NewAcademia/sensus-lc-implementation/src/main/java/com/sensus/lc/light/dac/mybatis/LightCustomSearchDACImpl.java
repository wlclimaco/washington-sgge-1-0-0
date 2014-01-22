package com.sensus.lc.light.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.dac.ILightCustomSearchDAC;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.SearchParameter;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Class LightCustomSearchDACImpl.
 */
public class LightCustomSearchDACImpl extends SqlSessionDaoSupport implements ILightCustomSearchDAC
{

	/** The Constant LIGHT_CUSTOM_SEARCH_NAMESPACE. */
	private static final String LIGHT_CUSTOM_SEARCH_NAMESPACE = "LightCustomSearch.";

	/** The Constant DELETE_CUSTOM_SEARCH. */
	private static final String DELETE_CUSTOM_SEARCH = LIGHT_CUSTOM_SEARCH_NAMESPACE + "deleteCustomSearch";

	/** The Constant FETCH_ALL_CUSTOM_SEARCH. */
	private static final String FETCH_ALL_CUSTOM_SEARCH = LIGHT_CUSTOM_SEARCH_NAMESPACE + "fetchAllCustomSearch";

	/** The Constant PAGINATION_TOTAL_ROWS_CUSTOM_SEARCH. */
	private static final String PAGINATION_TOTAL_ROWS_CUSTOM_SEARCH = LIGHT_CUSTOM_SEARCH_NAMESPACE
			+ "paginationTotalRowsCustomSearch";

	/** The Constant INSERT_COLUMNS_TO_CUSTOM_SEARCH. */
	private static final String INSERT_COLUMNS_TO_CUSTOM_SEARCH = LIGHT_CUSTOM_SEARCH_NAMESPACE
			+ "insertColumnsToCustomSearch";

	/** The Constant INSERT_FILTER_TO_CUSTOM_SEARCH. */
	private static final String INSERT_FILTER_TO_CUSTOM_SEARCH = LIGHT_CUSTOM_SEARCH_NAMESPACE
			+ "insertFilterToCustomSearch";

	/** The Constant INSERT_CUSTOM_SEARCH. */
	private static final String INSERT_CUSTOM_SEARCH = LIGHT_CUSTOM_SEARCH_NAMESPACE + "insertCustomSearch";

	/** The Constant INSERT_CUSTOM_SEARCH_PROPERTY. */
	private static final String INSERT_CUSTOM_SEARCH_PROPERTY = LIGHT_CUSTOM_SEARCH_NAMESPACE
			+ "insertCustomSearchProperty";

	/** The LOG. */
	private static Log LOG = LogFactory.getLog(LightCustomSearchDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightCustomSearchDAC#insertCustomSearch(com.sensus.mlc.light.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();

		Integer customSearchId = (Integer)doQueryForObject(getSqlSession(),
				INSERT_CUSTOM_SEARCH, request);

		if (ValidationUtil.isNullOrZero(customSearchId))
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error inserting custom search.");
			}

			response.setStatus(Status.PersistenceError);
			return response;
		}

		request.getCustomSearch().setId(customSearchId);

		// all the others parameters are propertyEnum
		if (ValidationUtil.isNullOrEmpty(request.getCustomSearch().getSearchParameters()))
		{
			response.addResult(request.getCustomSearch());
			return response;
		}
		return insertCustomSearchParameters(request, response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightCustomSearchDAC#deleteCustomSearch(com.sensus.mlc.light.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		InternalResponse response = new InternalResponse();
		SensusMyBatisDacHelper.doRemove(getSqlSession(), DELETE_CUSTOM_SEARCH, request.getCustomSearch(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightCustomSearchDAC#fetchAllCustomSearch(com.sensus.mlc.light.model.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(LightRequest lightRequest)
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_CUSTOM_SEARCH, lightRequest,
				response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS_CUSTOM_SEARCH, lightRequest);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightCustomSearchDAC#insertColumnsToCustomSearch(com.sensus.mlc.light.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(getSqlSession(), INSERT_COLUMNS_TO_CUSTOM_SEARCH, columnFilterRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightCustomSearchDAC#insertFiltersToCustomSearch(com.sensus.mlc.light.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(getSqlSession(), INSERT_FILTER_TO_CUSTOM_SEARCH, columnFilterRequest);
		return response;
	}

	/**
	 * Insert custom search parameters.
	 * 
	 * @param request the request
	 * @param response the response
	 * @return the internal results response
	 */
	private InternalResultsResponse<CustomSearch> insertCustomSearchParameters(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response)
	{
		for (SearchParameter param : request.getCustomSearch().getSearchParameters())
		{
			if (!ValidationUtil.isNull(param.getValue()))
			{
				request.getCustomSearch().setPropertyId(param.getPropertyEnum().getValue());
				request.getCustomSearch().setPropertyValue(param.getValue());

				Integer customSearchParameterId =
						(Integer)doQueryForObject(getSqlSession(), INSERT_CUSTOM_SEARCH_PROPERTY, request);
				if (ValidationUtil.isNullOrZero(customSearchParameterId))
				{
					return null;
				}
				param.setId(customSearchParameterId);
			}
		}

		response.addResult(request.getCustomSearch());
		return response;
	}
}
