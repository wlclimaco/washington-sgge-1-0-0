package com.sensus.mlc.smartpoint.bcl;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class MockSmartPointUpdaterBCL.
 */
public class MockSmartPointUpdaterBCL extends AbstractMockBase implements ISmartPointUpdaterBCL
{

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertColumnFilters(com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return getResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertColumnsFiltersToCustomSearch(com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertStatusMessage(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse insertStatusMessage(LightRequest request)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertCustomSearch(com.sensus.mlc.smartpoint.model.request.CustomSearchRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		return getCustomSearchResultsResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightStatus(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> updateLightStatus(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = getResultResponseLight();
		response = updateLightStatusFromSmartpoint(response);
		response = updateLightStatusFromMlcServer(response);

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightLatLng(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightLatLng(LightRequest lightRequest)
	{
		return getResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#deleteCustomSearch(com.sensus.mlc.smartpoint.model.request.CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		return getResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightProtected(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> updateLightProtected(LightRequest lightRequest)
	{
		return getLightResultsResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#verifyCommunicationMessage(com.sensus.common.model.UserContext)
	 */
	@Override
	public void verifyCommunicationMessage(UserContext userContext)
	{
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#resetMinMaxValue(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> resetMinMaxValue(LightRequest lightRequest)
	{
		return getLightResultsResponseBySituations();
	}

	/**
	 * Gets the response default.
	 *
	 * @return the response default
	 */
	private InternalResponse getResponseDefault()
	{
		return new InternalResponse();
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
			return getResponseDefault();
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
	 * Gets the light response default.
	 *
	 * @return the light response default
	 */
	private InternalResultsResponse<Light> getLightResponseDefault()
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response.addResult(TestBaseUtil.createLight());
		return response;
	}

	/**
	 * Gets the light results response by situations.
	 *
	 * @return the light results response by situations
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> getLightResultsResponseBySituations()
	{
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getLightResponseDefault();
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
		response.addResult(TestBaseUtil.createCustomSearch());
		return response;
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

	/**
	 * Update light status from smartpoint.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	private InternalResultsResponse<Light> updateLightStatusFromSmartpoint(InternalResultsResponse<Light> response)
	{
		if (getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response = new InternalResultsResponse<Light>();
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException("JUnit1");
		}

		return response;
	}

	/**
	 * Update light status from mlc server.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	private InternalResultsResponse<Light> updateLightStatusFromMlcServer(InternalResultsResponse<Light> response)
	{
		if (getAreaEnum() != LCAreaEnum.MLC_SERVER)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response = new InternalResultsResponse<Light>();
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException("JUnit");
		}

		return response;
	}

	@Override
	public InternalResponse updateLight(LightRequest lightRequest)
	{
		return null;
	}

	@Override
	public InternalResponse updateLightSchedule(LightRequest lightRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response = new InternalResultsResponse<Light>();
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);

			return response;
		}
		return response;
	}

	@Override
	public InternalResponse updateLightLastOperationData(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse updateLightLocation(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse updateLightConfiguration(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertLightSchedule(GuaranteeLightExistenceRequest lightRequest)
	{
	}
}
