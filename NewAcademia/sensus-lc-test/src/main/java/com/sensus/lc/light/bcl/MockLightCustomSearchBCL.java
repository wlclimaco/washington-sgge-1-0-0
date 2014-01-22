package com.sensus.lc.light.bcl;

import java.util.Arrays;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;

/**
 * The Class MockLightCustomSearchBCL.
 */
public class MockLightCustomSearchBCL extends AbstractMockBase implements ILightCustomSearchBCL
{

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCustomSearchBCL#fetchLightFilters(com.sensus.lc.light.model.request.
	 * LightFilterRequest)
	 */
	@Override
	public InternalResultsResponse<LightFilterValue> fetchLightFilters(LightFilterRequest lightFilterRequest)
	{
		return testSituationsLightFilterResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCustomSearchBCL#fetchAllCustomSearch(com.sensus.lc.light.model.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(LightRequest lightRequest)
	{
		return testSituationsCustomSearchResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCustomSearchBCL#fetchAllColumnFilters(com.sensus.lc.light.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsColumnFilterResponseResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCustomSearchBCL#insertCustomSearch(com.sensus.lc.light.model.CustomSearch)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		return getCustomSearchResultsResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCustomSearchBCL#insertColumnFilters(com.sensus.lc.light.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.bcl.ILightCustomSearchBCL#insertColumnsFiltersToCustomSearch(com.sensus.lc.light.model.
	 * request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCustomSearchBCL#deleteCustomSearch(com.sensus.lc.light.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCustomSearchBCL#verifyCustomSearch(com.sensus.lc.light.model.SearchLight)
	 */
	@Override
	public SearchLight verifyCustomSearch(SearchLight search)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Test situations custom search results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<CustomSearch> testSituationsCustomSearchResultsResponse()
	{
		InternalResultsResponse<CustomSearch> internalResultsResponse = new InternalResultsResponse<CustomSearch>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getCustomSearchResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the custom search response default.
	 * 
	 * @return the custom search response default
	 */
	private InternalResultsResponse<CustomSearch> getCustomSearchResponseDefault()
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		CustomSearch customSearch = new CustomSearch();
		customSearch.setId(TEN);
		customSearch.setName("Test Custom Search");
		response.addResults(Arrays.asList(customSearch));
		return response;
	}

	/**
	 * Test situations light filter response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<LightFilterValue> testSituationsLightFilterResponse()
	{
		InternalResultsResponse<LightFilterValue> internalResultsResponse =
				new InternalResultsResponse<LightFilterValue>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			LightFilterValue v = new LightFilterValue();
			v.setLightFilterTypeValue(2); // 2 - WATTAGE_RATING
			v.setFilterValue("LED");
			v.setId(1);
			internalResultsResponse.getResultsList().add(v);
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/**
	 * Test situations column filter response results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<ColumnFilterResponse> testSituationsColumnFilterResponseResultsResponse()
	{
		InternalResultsResponse<ColumnFilterResponse> internalResultsResponse =
				new InternalResultsResponse<ColumnFilterResponse>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getColumnFilterResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the column filter response default.
	 * 
	 * @return the column filter response default
	 */
	private InternalResultsResponse<ColumnFilterResponse> getColumnFilterResponseDefault()
	{
		InternalResultsResponse<ColumnFilterResponse> response = new InternalResultsResponse<ColumnFilterResponse>();
		response.addResult(new ColumnFilterResponse());
		return response;
	}

	/**
	 * Gets the response by situations.
	 * 
	 * @return the response by situations
	 */
	private InternalResponse getResponseBySituations()
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResponse;
	}

	/**
	 * Gets the custom search results response by situations.
	 * 
	 * @return the custom search results response by situations
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<CustomSearch> getCustomSearchResultsResponseBySituations()
	{
		InternalResultsResponse<CustomSearch> internalResultsResponse = new InternalResultsResponse<CustomSearch>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getCustomSearchResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

}
