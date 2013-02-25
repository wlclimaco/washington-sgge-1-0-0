package com.sensus.mlc.smartpoint.bcl;

import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class MockSmartPointAccessorBCL.
 */
public class MockSmartPointAccessorBCL extends AbstractMockBase implements ISmartPointAccessorBCL
{
	/** The Constant ARBITRARY_ID_100. */
	private static final Integer ARBITRARY_ID_100 = 100;

	/** The Constant ARBITRARY_COUNT_LIGHT_FROM_SMARTPOINT. */
	private static final Integer ARBITRARY_COUNT_LIGHT_FROM_SMARTPOINT = 200;

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllLights(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLights(InquiryLightRequest inquiryLightRequest)
	{
		return testSituationsLightResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#countLights(com.sensus.mlc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> countLights(LightingControlRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		if ((getAreaEnum() == LCAreaEnum.SMARTPOINT) || (getAreaEnum() == LCAreaEnum.GROUP)
				|| (getAreaEnum() == LCAreaEnum.DEFAULT) || (getAreaEnum() == LCAreaEnum.PROCESS))
		{
			return countLightsFromSmartPointAcessorBCF(response);
		}
		return response;
	}

	/**
	 * Count lights from smart point acessor bcf.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Integer> countLightsFromSmartPointAcessorBCF(
			InternalResultsResponse<Integer> response)
			{

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(ARBITRARY_COUNT_LIGHT_FROM_SMARTPOINT);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightById(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightById(LightRequest lightRequest)
	{
		return fetchLightByIdFromSmartPointAcessorBCF();
	}

	/**
	 * Fetch light by id from smart point acessor bcf.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> fetchLightByIdFromSmartPointAcessorBCF()
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		if (getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(new Light());
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllLightsToApplySchedule(com.sensus.mlc.schedule.model
	 * .request.ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLightsToApplySchedule(ScheduleRequest scheduleRequest)
	{
		return new InternalResultsResponse<>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightByRniId(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightByRniId(LightRequest lightRequest)
	{
		return fetchLightByRniIdFromSmartPointAcessorBCF();
	}

	/**
	 * Fetch light by rni id from smart point acessor bcf.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> fetchLightByRniIdFromSmartPointAcessorBCF()
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		if (getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			response.getResultsList().add(TestBaseUtil.createLight());
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(new Light());
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchPropertyValidValues(com.sensus.mlc.smartpoint.model
	 * .request.PropertyValidValuesRequest)
	 */
	@Override
	public InternalResultsResponse<PropertyValidValue> fetchPropertyValidValues(PropertyValidValuesRequest request)
	{
		return testSituationsPropertyValidValueResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchUpdateLightStatus(com.sensus.mlc.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchUpdateLightStatus(ProcessRequest processRequest)
	{
		return testSituationsLightResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllCustomSearch(com.sensus.mlc.base.model.request.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryPaginationRequest request)
	{
		return testSituationsCustomSearchResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#isLightInTenant(com.sensus.mlc.smartpoint.model.request.
	 * LightStatusRequest, java.lang.Boolean)
	 */
	@Override
	public InternalResponse isLightInTenant(LightStatusRequest request, Boolean strictValidation)
	{
		InternalResponse res = new InternalResponse();

		if (new Integer(1).equals(request.getLightRniId()) && "PECO".equals(request.getTenantRniCode()))
		{
			res.setStatus(Status.SystemError);
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#verifyCustomSearch(com.sensus.mlc.smartpoint.model.SearchLight
	 * )
	 */
	@Override
	public SearchLight verifyCustomSearch(SearchLight search)
	{
		return new SearchLight();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchCanInsertCustomSearch(com.sensus.mlc.tenant.model.Tenant
	 * ,
	 * com.sensus.mlc.smartpoint.model.CustomSearch, java.lang.Integer, java.util.List, java.util.List)
	 */
	@Override
	public Boolean fetchCanInsertCustomSearch(Tenant tenant, CustomSearch customSearch, Integer userId,
			List<MessageInfo> list, List<Integer> allowedGroupIdList)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchStatusMessageByLightID(com.sensus.mlc.smartpoint.model
	 * .request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageByLightID(LightRequest request)
	{
		return testSituationsStatusMessageResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#generateFileCSV(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		return testSituationsGenerateFileCSVResponse();
	}

	/**
	 * Test situations generate file csv response.
	 *
	 * @return the inquiry light response
	 */
	private InquiryLightResponse testSituationsGenerateFileCSVResponse()
	{
		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			inquiryLightResponse.setFileName("C:\\Users\\QATEmployee\\Desktop\\file\\testPOIWrite"
					+ LCDateUtil.getNewDateUTC().getTime() + ".csv");
			return inquiryLightResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			inquiryLightResponse.setOperationSuccess(Status.ExceptionError);
			inquiryLightResponse.addFieldErrorMessage(ERROR_CODE);
			return inquiryLightResponse;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return inquiryLightResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllLightsByProcess(com.sensus.mlc.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLightsByProcess(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#generateGenericFileCSV(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateGenericFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLigthingConfigurationsByPartNumber(com.sensus.mlc.
	 * smartpoint.model.request.LightingConfigurationRequest)
	 */
	@Override
	public InternalResultsResponse<SensusPartNumberConfiguration> fetchLigthingConfigurationsByPartNumber(
			LightingConfigurationRequest request)
			{
		return testSituationsSensusPartNumberConfigurationResultsResponse();
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightIntensityByLight(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public LightIntensityEnum fetchLightIntensityByLight(Integer lightId, Integer percentage)
	{
		return LightIntensityEnum.LEVEL_0;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightHistory(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<LightHistory> fetchLightHistory(InquiryLightRequest inquiryLightRequest)
	{
		return testSituationsLightHistoryResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightHistoryHeader(com.sensus.mlc.smartpoint.model.
	 * request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(LightRequest lightRequest)
	{
		return testSituationsHashMapResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllLightsToCSV(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, String>> fetchAllLightsToCSV(InquiryLightRequest inquiryLightRequest)
	{
		return new InternalResultsResponse<>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#generateSummaryFileCSV(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateSummaryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllColumnFilters(com.sensus.mlc.smartpoint.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsColumnFilterResponseResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchCurrentAlarmStatusMessagesByLight(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<CurrentAlarmWarningMessage> fetchCurrentAlarmStatusMessagesByLight(Integer lightID)
	{
		return testSituationsCurrentAlarmWarningMessageResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#generateLightHistoryFileCSV(com.sensus.mlc.smartpoint.model
	 * .request.InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		return testSituationsGenerateFileCSVResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeSmartpointInfo> fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		return testSituationsGeocodeSmartpointInfoResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchStatusMessageById(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageById(LightRequest lightRequest)
	{
		return testSituationsStatusMessageResultsResponse();
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
	 * Gets the light response default.
	 *
	 * @return the light response default
	 */
	private InternalResultsResponse<GeocodeSmartpointInfo> getGeocodeSmartpointInfoResponseDefault()
	{
		InternalResultsResponse<GeocodeSmartpointInfo> response = new InternalResultsResponse<GeocodeSmartpointInfo>();
		response.addResult(new GeocodeSmartpointInfo());
		return response;
	}

	/**
	 * Gets the status message response default.
	 *
	 * @return the status message response default
	 */
	private InternalResultsResponse<StatusMessage> getStatusMessageResponseDefault()
	{
		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
		response.addResult(TestBaseUtil.createStatusMessage());
		return response;
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
	 * Gets the light history response default.
	 *
	 * @return the light history response default
	 */
	private InternalResultsResponse<LightHistory> getLightHistoryResponseDefault()
	{
		InternalResultsResponse<LightHistory> response = new InternalResultsResponse<LightHistory>();
		response.addResult(TestBaseUtil.createLightHistory());
		return response;
	}

	/**
	 * Gets the hash map response default.
	 *
	 * @return the hash map response default
	 */
	private InternalResultsResponse<HashMap<String, Integer>> getHashMapResponseDefault()
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();
		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("ID", ARBITRARY_ID_100);
		response.addResult(hm);
		return response;
	}

	/**
	 * Gets the sensus part number configuration response default.
	 *
	 * @return the sensus part number configuration response default
	 */
	private InternalResultsResponse<SensusPartNumberConfiguration> getSensusPartNumberConfigurationResponseDefault()
	{
		InternalResultsResponse<SensusPartNumberConfiguration> response =
				new InternalResultsResponse<SensusPartNumberConfiguration>();
		response.addResult(TestBaseUtil.createSensusPartNumberConfiguration());
		return response;
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
	 * Gets the current alarm warning message response default.
	 *
	 * @return the current alarm warning message response default
	 */
	private InternalResultsResponse<CurrentAlarmWarningMessage> getCurrentAlarmWarningMessageResponseDefault()
	{
		InternalResultsResponse<CurrentAlarmWarningMessage> response =
				new InternalResultsResponse<CurrentAlarmWarningMessage>();
		response.addResult(TestBaseUtil.createCurrentAlarmWarningMessage());
		return response;
	}

	/**
	 * Gets the property valid value response default.
	 *
	 * @return the property valid value response default
	 */
	private InternalResultsResponse<PropertyValidValue> getPropertyValidValueResponseDefault()
	{
		InternalResultsResponse<PropertyValidValue> response = new InternalResultsResponse<PropertyValidValue>();
		response.addResult(TestBaseUtil.createPropertyValidValue());
		return response;
	}

	/**
	 * Test situations light results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> testSituationsLightResultsResponse()
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
	 * Test situations geocode smartpoint info results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<GeocodeSmartpointInfo> testSituationsGeocodeSmartpointInfoResultsResponse()
	{
		InternalResultsResponse<GeocodeSmartpointInfo> internalResultsResponse =
				new InternalResultsResponse<GeocodeSmartpointInfo>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getGeocodeSmartpointInfoResponseDefault();
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
	 * Test situations status message results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<StatusMessage> testSituationsStatusMessageResultsResponse()
	{
		InternalResultsResponse<StatusMessage> internalResultsResponse = new InternalResultsResponse<StatusMessage>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getStatusMessageResponseDefault();
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
	 * Test situations light history results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<LightHistory> testSituationsLightHistoryResultsResponse()
	{
		InternalResultsResponse<LightHistory> internalResultsResponse = new InternalResultsResponse<LightHistory>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getLightHistoryResponseDefault();
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
	 * Test situations hash map results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<HashMap<String, Integer>> testSituationsHashMapResultsResponse()
	{
		InternalResultsResponse<HashMap<String, Integer>> internalResultsResponse =
				new InternalResultsResponse<HashMap<String, Integer>>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getHashMapResponseDefault();
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
	 * Test situations sensus part number configuration results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<SensusPartNumberConfiguration> testSituationsSensusPartNumberConfigurationResultsResponse()
	{
		InternalResultsResponse<SensusPartNumberConfiguration> internalResultsResponse =
				new InternalResultsResponse<SensusPartNumberConfiguration>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getSensusPartNumberConfigurationResponseDefault();
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
	 * Test situations current alarm warning message results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<CurrentAlarmWarningMessage> testSituationsCurrentAlarmWarningMessageResultsResponse()
	{
		InternalResultsResponse<CurrentAlarmWarningMessage> internalResultsResponse =
				new InternalResultsResponse<CurrentAlarmWarningMessage>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getCurrentAlarmWarningMessageResponseDefault();
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
	 * Test situations property valid value results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<PropertyValidValue> testSituationsPropertyValidValueResultsResponse()
	{
		InternalResultsResponse<PropertyValidValue> internalResultsResponse =
				new InternalResultsResponse<PropertyValidValue>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getPropertyValidValueResponseDefault();
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

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightScheduleById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightScheduleById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();


		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createLight());
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightLastOperationDataById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLastOperationDataById(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightLocationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLocationById(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightConfigurationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightConfigurationById(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
