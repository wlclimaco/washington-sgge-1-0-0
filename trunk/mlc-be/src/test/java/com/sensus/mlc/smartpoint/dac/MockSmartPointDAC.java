package com.sensus.mlc.smartpoint.dac;

import static com.sensus.mlc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.SmartpointMiddleMap;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Class MockSmartPointDAC.
 */
public class MockSmartPointDAC extends AbstractMockBase implements ISmartPointDAC
{
	/** The Constant ARBITRARY_COUNT_LIGHTS. */
	private static final Integer ARBITRARY_COUNT_LIGHTS = 12;

	/** The Constant ARBITRARY_COUNT_SMARTPOINT_TO_MAP. */
	private static final Integer ARBITRARY_COUNT_SMARTPOINT_TO_MAP = 200;

	/** The Constant ARBITRARY_DIM_ON_DELAY_60. */
	private static final Integer ARBITRARY_DIM_ON_DELAY_60 = 60;

	/** The Constant ARBITRARY_DIM_ON_DELAY_50. */
	private static final Integer ARBITRARY_DIM_ON_DELAY_50 = 50;

	/** The Constant ARBITRARY_DIM_ON_DELAY_40. */
	private static final Integer ARBITRARY_DIM_ON_DELAY_40 = 40;

	/** The Constant ARBITRARY_DIM_ON_DELAY_30. */
	private static final Integer ARBITRARY_DIM_ON_DELAY_30 = 30;

	/** The Constant ARBITRARY_DIM_ON_DELAY_20. */
	private static final Integer ARBITRARY_DIM_ON_DELAY_20 = 20;

	/** The Constant ARBITRARY_DIM_ON_DELAY_10. */
	private static final Integer ARBITRARY_DIM_ON_DELAY_10 = 10;

	/** The Constant ARBITRARY_CURRENT_SCALE_42. */
	private static final Integer ARBITRARY_CURRENT_SCALE_42 = 42;

	/** The Constant ARBITRARY_CURRENT_SCALE_62. */
	private static final Integer ARBITRARY_CURRENT_SCALE_62 = 62;

	/** The Constant ARBITRARY_CURRENT_SCALE_52. */
	private static final Integer ARBITRARY_CURRENT_SCALE_52 = 52;

	/** The Constant ARBITRARY_CURRENT_SCALE_32. */
	private static final Integer ARBITRARY_CURRENT_SCALE_32 = 32;

	/** The Constant ARBITRARY_CURRENT_SCALE_22. */
	private static final Integer ARBITRARY_CURRENT_SCALE_22 = 22;

	/** The Constant ARBITRARY_CURRENT_SCALE_12. */
	private static final Integer ARBITRARY_CURRENT_SCALE_12 = 12;

	/** The Constant ARBITRARY_HARDWARE_SETTING_61. */
	private static final Integer ARBITRARY_HARDWARE_SETTING_61 = 61;

	/** The Constant ARBITRARY_HARDWARE_SETTING_51. */
	private static final Integer ARBITRARY_HARDWARE_SETTING_51 = 51;

	/** The Constant ARBITRARY_HARDWARE_SETTING_41. */
	private static final Integer ARBITRARY_HARDWARE_SETTING_41 = 41;

	/** The Constant ARBITRARY_HARDWARE_SETTING_31. */
	private static final Integer ARBITRARY_HARDWARE_SETTING_31 = 31;

	/** The Constant ARBITRARY_HARDWARE_SETTING_21. */
	private static final Integer ARBITRARY_HARDWARE_SETTING_21 = 21;

	/** The Constant ARBITRARY_HARDWARE_SETTING_11. */
	private static final Integer ARBITRARY_HARDWARE_SETTING_11 = 11;

	/** The Constant ARBITRARY_INTENSITY_LEVEL_6. */
	private static final Integer ARBITRARY_INTENSITY_LEVEL_6 = 6;

	/** The Constant ARBITRARY_INTENSITY_LEVEL_5. */
	private static final Integer ARBITRARY_INTENSITY_LEVEL_5 = 5;

	/** The Constant ARBITRARY_INTENSITY_LEVEL_4. */
	private static final Integer ARBITRARY_INTENSITY_LEVEL_4 = 4;

	/** The Constant ARBITRARY_INTENSITY_LEVEL_3. */
	private static final Integer ARBITRARY_INTENSITY_LEVEL_3 = 3;

	/** The Constant ARBITRARY_PERCENTAGE_VALUE_100. */
	private static final Integer ARBITRARY_PERCENTAGE_VALUE_100 = 100;

	/** The Constant ARBITRARY_PERCENTAGE_VALUE_80. */
	private static final Integer ARBITRARY_PERCENTAGE_VALUE_80 = 80;

	/** The Constant ARBITRARY_PERCENTAGE_VALUE_60. */
	private static final Integer ARBITRARY_PERCENTAGE_VALUE_60 = 60;

	/** The Constant ARBITRARY_PERCENTAGE_VALUE_40. */
	private static final Integer ARBITRARY_PERCENTAGE_VALUE_40 = 40;

	/** The Constant ARBITRARY_PERCENTAGE_VALUE_20. */
	private static final Integer ARBITRARY_PERCENTAGE_VALUE_20 = 20;

	/** The Constant ARBITRARY_PERCENTAGE_VALUE_10. */
	private static final Integer ARBITRARY_PERCENTAGE_VALUE_10 = 10;

	/** The Constant ARBITRARY_IS_LIGHT_IN_TENANT_N_1. */
	private static final Integer ARBITRARY_IS_LIGHT_IN_TENANT_N_1 = -1;

	/** The Constant ARBITRARY_IS_LIGHT_IN_TENANT_N_3. */
	private static final Integer ARBITRARY_IS_LIGHT_IN_TENANT_N_3 = -3;

	/** The Constant ARBITRARY_IS_LIGHT_IN_TENANT_N_2. */
	private static final Integer ARBITRARY_IS_LIGHT_IN_TENANT_N_2 = -2;

	/** The Constant ARBITRARY_CUSTOM_SEARCH_ID_100. */
	private static final Integer ARBITRARY_CUSTOM_SEARCH_ID_100 = 10;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightByRniId(java.lang.Integer, java.util.List)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightByRniId(LightRequest lightRequest)
	{
		return getLightResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLight(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public Integer insertLight(GuaranteeLightExistenceRequest lightRequest)
	{
		Integer lightId = null;
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			lightId = new Random().nextInt(NUMBER_RANGE);
			return lightId;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return lightId;
		}

		return lightId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchAllLights(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLights(InquiryLightRequest inquiryLightRequest)
	{
		return getLightResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#countLights(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Integer> countLights(LightingControlRequest request)
	{
		return getIntegerResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightById(com.sensus.mlc.smartpoint.model.request.LightRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = getLightResponseBySituation();
		if (!ValidationUtil.isNullOrEmpty(lightRequest.getLights())
				&& !ValidationUtil.isNull(lightRequest.getLights().get(0).getLightLocation())
				&& !ValidationUtil.isNullOrEmpty(lightRequest.getLights().get(0).getLightLocation().getZipCode()))
		{
			response.getFirstResult().getOffSetSchedule().setSunriseOffsetMinutes(ARBITRARY_IS_LIGHT_IN_TENANT_N_1);
			response.getFirstResult().getOffSetSchedule().setSunsetOffsetMinutes(ARBITRARY_IS_LIGHT_IN_TENANT_N_1);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchPropertyValidValues(com.sensus.mlc.smartpoint.model.request
	 * .PropertyValidValuesRequest)
	 */
	@Override
	public List<PropertyValidValue> fetchPropertyValidValues(PropertyValidValuesRequest request)
	{
		List<PropertyValidValue> validValues = new ArrayList<PropertyValidValue>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			validValues.add(new PropertyValidValue(1, "300W LED"));
			validValues.add(new PropertyValidValue(1, "100W LED"));
			return validValues;
		}

		return validValues;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightProtected(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Light> updateLightProtected(LightRequest lightRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response.setStatus(Status.OperationSuccess);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertCustomSearch(com.sensus.mlc.smartpoint.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		Filter filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.ADDRESS);
		List<Filter> listFilters = new ArrayList<Filter>();
		listFilters.add(filter);

		Column column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.CITY_NAME);
		List<Column> listColumns = new ArrayList<Column>();
		listColumns.add(column);

		CustomSearch customSearch = request.getCustomSearch();
		customSearch.setId(ARBITRARY_CUSTOM_SEARCH_ID_100);
		customSearch.setListFilters(listFilters);
		customSearch.setListColumn(listColumns);

		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		response.addResult(customSearch);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#deleteCustomSearch(com.sensus.mlc.smartpoint.model.request.
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
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchAllCustomSearch(com.sensus.mlc.base.model.request.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(
			InquiryPaginationRequest inquiryPaginationRequest)
			{
		return getCustomSearchResponseBySituation();
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#isLightInTenant(java.lang.Integer, java.lang.String,
	 * java.util.List)
	 */
	@Override
	public Integer isLightInTenant(Integer rniId, String tenantRniCode, List<Integer> allowedGroupIdList)
	{
		// RniId not found.
		if (rniId == 0)
		{
			return ARBITRARY_IS_LIGHT_IN_TENANT_N_2;
		}
		// Tenant not found.
		if (tenantRniCode.equalsIgnoreCase("Invalid"))
		{
			return ARBITRARY_IS_LIGHT_IN_TENANT_N_3;
		}
		// SmartPoint does not belong to this tenant.
		else if (tenantRniCode.equalsIgnoreCase("Acme"))
		{
			return ARBITRARY_IS_LIGHT_IN_TENANT_N_1;
		}
		// is light in tenant
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#locationHasChanged(java.lang.Integer, java.lang.Double,
	 * java.lang.Double, java.util.List)
	 */
	@Override
	public Boolean locationHasChanged(Integer rniId, Double latitude, Double longitude,
			List<Integer> allowedGroupIdList)
	{
		if ((latitude == 0.0) && (longitude == 0.0))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#deleteAddrTagsForLight(java.lang.Integer)
	 */
	@Override
	public InternalResponse deleteAddrTagsForLight(Integer rniId)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightLatLng(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResponse updateLightLatLng(LightRequest lightRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchStatusMessageByLightID(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageByLightID(Integer lightId,
			List<Integer> allowedGroupIdList)
			{
		return getStatusMessageResponseBySituation();
			}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertStatusMessage(com.sensus.mlc.smartpoint.model.StatusMessage
	 * , java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public Integer insertStatusMessage(StatusMessage statusMessage, String userId, Integer tenantId,
			Integer lightId, Boolean simpleNotification)
	{
		if (tenantId == 1)
		{
			return TestBaseUtil.createStatusMessage().getId();
		}
		else
		{
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertOperationalData(java.lang.String, java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public InternalResponse insertOperationalData(String userId, Integer idStatusMessage,
			List<OperationalData> operationalData)
	{
		return getInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#deleteLightFromAllTagScheduleGroup(java.lang.Integer)
	 */
	@Override
	public InternalResponse deleteLightFromAllTagScheduleGroup(Integer rniId)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertStatusMessageStatusSubtype(java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public void insertStatusMessageStatusSubtype(Integer idStatusMessage,
			Integer statusExceptionTypeEnumValue,
			String userId, Boolean updateAnalytics)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateAnalyticsAlarmWarning(java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public InternalResponse updateAnalyticsAlarmWarning(Integer statusExceptionTypeEnumValue,
			Integer idStatusMessage,
			String userId, String operator)
	{
		return new InternalResponse();

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#generateFileCSV(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			String path = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
			inquiryLightResponse.setFileName(path + "export.csv");
			return inquiryLightResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInquiryLightResponseError();
		}

		return new InquiryLightResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchAllLightsByProcess(com.sensus.mlc.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLightsByProcess(ProcessRequest request)
	{
		return getLightResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLigthingConfigurationsByPartNumber(com.sensus.mlc.smartpoint
	 * .model.request.LightingConfigurationRequest)
	 */
	@Override
	public InternalResultsResponse<SensusPartNumberConfiguration> fetchLigthingConfigurationsByPartNumber(
			LightingConfigurationRequest request)
			{
		InternalResultsResponse<SensusPartNumberConfiguration> response =
				new InternalResultsResponse<SensusPartNumberConfiguration>();

		List<SensusPartNumberConfiguration> list = new ArrayList<SensusPartNumberConfiguration>();

		SensusPartNumberConfiguration config = new SensusPartNumberConfiguration();
		config.setIntensityLevel(1);
		config.setHardwareSetting(ARBITRARY_HARDWARE_SETTING_11);
		config.setCurrentScale(ARBITRARY_CURRENT_SCALE_12);
		config.setDimOnDelay(ARBITRARY_DIM_ON_DELAY_10);
		config.setFullOnRequired(true);
		list.add(config);

		config = new SensusPartNumberConfiguration();
		config.setIntensityLevel(2);
		config.setHardwareSetting(ARBITRARY_HARDWARE_SETTING_21);
		config.setCurrentScale(ARBITRARY_CURRENT_SCALE_22);
		config.setDimOnDelay(ARBITRARY_DIM_ON_DELAY_20);
		config.setFullOnRequired(false);
		list.add(config);

		config = new SensusPartNumberConfiguration();
		config.setIntensityLevel(ARBITRARY_INTENSITY_LEVEL_3);
		config.setHardwareSetting(ARBITRARY_HARDWARE_SETTING_31);
		config.setCurrentScale(ARBITRARY_CURRENT_SCALE_32);
		config.setDimOnDelay(ARBITRARY_DIM_ON_DELAY_30);
		config.setFullOnRequired(true);
		list.add(config);

		config = new SensusPartNumberConfiguration();
		config.setIntensityLevel(ARBITRARY_INTENSITY_LEVEL_4);
		config.setHardwareSetting(ARBITRARY_HARDWARE_SETTING_41);
		config.setCurrentScale(ARBITRARY_CURRENT_SCALE_42);
		config.setDimOnDelay(ARBITRARY_DIM_ON_DELAY_40);
		config.setFullOnRequired(true);
		list.add(config);

		config = new SensusPartNumberConfiguration();
		config.setIntensityLevel(ARBITRARY_INTENSITY_LEVEL_5);
		config.setHardwareSetting(ARBITRARY_HARDWARE_SETTING_51);
		config.setCurrentScale(ARBITRARY_CURRENT_SCALE_52);
		config.setDimOnDelay(ARBITRARY_DIM_ON_DELAY_50);
		config.setFullOnRequired(true);
		list.add(config);

		config = new SensusPartNumberConfiguration();
		config.setIntensityLevel(ARBITRARY_INTENSITY_LEVEL_6);
		config.setHardwareSetting(ARBITRARY_HARDWARE_SETTING_61);
		config.setCurrentScale(ARBITRARY_CURRENT_SCALE_62);
		config.setDimOnDelay(ARBITRARY_DIM_ON_DELAY_60);
		config.setFullOnRequired(true);
		list.add(config);

		response.getResultsList().addAll(list);
		return response;
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightIntensityPercentageByLight(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<SensusPartNumberConfiguration> fetchLightIntensityPercentageByLight(
			Integer lightId)
			{
		InternalResultsResponse<SensusPartNumberConfiguration> response =
				new InternalResultsResponse<SensusPartNumberConfiguration>();

		List<SensusPartNumberConfiguration> listLightIntensityPercentage =
				new ArrayList<SensusPartNumberConfiguration>();
		listLightIntensityPercentage.add(new SensusPartNumberConfiguration(LightIntensityEnum.LEVEL_1.getValue(),
				ARBITRARY_PERCENTAGE_VALUE_10));
		listLightIntensityPercentage.add(new SensusPartNumberConfiguration(LightIntensityEnum.LEVEL_2.getValue(),
				ARBITRARY_PERCENTAGE_VALUE_20));
		listLightIntensityPercentage.add(new SensusPartNumberConfiguration(LightIntensityEnum.LEVEL_3.getValue(),
				ARBITRARY_PERCENTAGE_VALUE_40));
		listLightIntensityPercentage.add(new SensusPartNumberConfiguration(LightIntensityEnum.LEVEL_4.getValue(),
				ARBITRARY_PERCENTAGE_VALUE_60));
		listLightIntensityPercentage.add(new SensusPartNumberConfiguration(LightIntensityEnum.LEVEL_5.getValue(),
				ARBITRARY_PERCENTAGE_VALUE_80));
		listLightIntensityPercentage.add(new SensusPartNumberConfiguration(LightIntensityEnum.LEVEL_5.getValue(),
				ARBITRARY_PERCENTAGE_VALUE_100));

		response.getResultsList().addAll(listLightIntensityPercentage);
		response.setStatus(Status.OperationSuccess);

		return response;
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchStatusMessageByLightIDMessageType(java.lang.Integer,
	 * com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum, java.util.List)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageByLightIDMessageType(Integer lightId,
			StatusMessageCategoryEnum categoryEnum, List<Integer> allowedGroupIdList)
			{
		return new InternalResultsResponse<>();
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightHistory(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<LightHistory> fetchLightHistory(InquiryLightRequest inquiryLightRequest)
	{
		return getLightHistoryResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightHistoryHeader(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(LightRequest lightRequest)
	{
		return getHashMapResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchAllLightsToCSV(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, String>> fetchAllLightsToCSV(
			InquiryLightRequest inquiryLightRequest)
			{
		InternalResultsResponse<HashMap<String, String>> internalResultsResponse =
				new InternalResultsResponse<HashMap<String, String>>();

		List<HashMap<String, String>> listExport = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("rni_id", "2");
		map.put("pole_id", "12");
		map.put("light_type", "40W Induction");
		map.put("date_added", "01/23/2011");
		map.put("city", "Portland");
		map.put("protect", "True");
		map.put("light_status", "ACTIVE");
		map.put("address", "SE Stark St. USA Portland Oregon 553447");
		map.put("latitude", "41.2554708723836");
		map.put("longitude", "-95.945500104623");
		map.put("last_message_received", "2012-01-22 10:08:07.397");
		map.put("lightState", "ON");
		map.put("light_voltage", "0");
		map.put("light_voltage_monthly", "0.05");
		map.put("light_current", "0.1");
		map.put("light_current_monthly", "0.04");
		map.put("light_consumption", "1");
		map.put("light_consumption_monthly", "13");
		map.put("alarm", "");
		map.put("warnings", "");
		map.put("sunset_time", "20:24:23");
		map.put("sunrise_time", "06:24:23");
		map.put("offset_schedule", "10");
		map.put("event_schedule", "-20");
		map.put("schedule_offset", "Regular Schedule");
		map.put("schedule_event", "");
		map.put("light_type_detail", "40W Induction Wall Pack Dim");
		map.put("input_voltage", "90V AC to 300V AC");
		map.put("color_temperature", "3,000K");
		map.put("housing_color", "Gray");
		map.put("manufacturer", "QATBOT");
		map.put("model_number", "539449037C2321");
		map.put("bulb_number", "");
		map.put("light_driver_number", "");
		map.put("lower_assembly", "");
		map.put("upper_assembly", "");
		map.put("firmware_version", "1.0.0");

		listExport.add(map);
		internalResultsResponse.getResultsList().addAll(listExport);

		return internalResultsResponse;
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#resetMinMaxValue(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResponse resetMinMaxValue(LightRequest lightRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#generateSummaryFileCSV(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateSummaryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();
		inquiryLightResponse.setFileName(TestBaseUtil.FILE_NAME);
		return inquiryLightResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchAllLightsToApplySchedule(com.sensus.mlc.schedule.model.
	 * request.ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLightsToApplySchedule(ScheduleRequest scheduleRequest)
	{
		return getLightResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightsByTenant(java.lang.Integer)
	 */
	@Override
	public List<Light> fetchLightsByTenant(Integer tenantID)
	{
		return new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLastStatusMessageDateFromLight(java.lang.Integer)
	 */
	@Override
	public Date fetchLastStatusMessageDateFromLight(Integer lightID)
	{
		return LCDateUtil.getNewDateUTC();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLastStatusMessageCalendarFromLight(java.lang.Integer)
	 */
	@Override
	public Calendar fetchLastStatusMessageCalendarFromLight(Integer lightID)
	{
		return Calendar.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLastStatusSubtypeIDFromLight(java.lang.Integer)
	 */
	@Override
	public Integer fetchLastStatusSubtypeIDFromLight(Integer lightID)
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertColumnsToCustomSearch(com.sensus.mlc.smartpoint.model.
	 * request.ColumnFilterRequest)
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
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertFiltersToCustomSearch(com.sensus.mlc.smartpoint.model.
	 * request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.OperationSuccess);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchAllColumnFilters(com.sensus.mlc.smartpoint.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(
			ColumnFilterRequest columnFilterRequest)
			{
		return getColumnFilterResponseBySituation();
			}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchCurrentAlarmStatusMessagesByLight(java.lang.Integer)
	 */
	@Override
	public List<CurrentAlarmWarningMessage> fetchCurrentAlarmStatusMessagesByLight(Integer lightID)
	{
		return getCurrentAlarmWarningMessageBySituation().getResultsList();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertCurrentAlarmStatusMessage(com.sensus.mlc.smartpoint.model
	 * .CurrentAlarmWarningMessage)
	 */
	@Override
	public void insertCurrentAlarmStatusMessage(CurrentAlarmWarningMessage currentAlarmWarningMessage)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#deleteCurrentAlarmWarningMessageByLightID(java.lang.Integer)
	 */
	@Override
	public void deleteCurrentAlarmWarningMessageByLightID(Integer lightID)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertCommunicationFailureWarnings(com.sensus.mlc.smartpoint
	 * .model.request.LightRequest)
	 */
	@Override
	public void insertCommunicationFailureWarnings(LightRequest lightRequest)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchCurrentLightMessagesByTenant(com.sensus.mlc.tenant.model
	 * .request.TenantRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchCurrentLightMessagesByTenant(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();

		internalResultsResponse = getLightResponse();
		internalResultsResponse.getFirstResult().setCurrentStatusMessage(TestBaseUtil.createStatusMessage());
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateAnalyticsAlarmWarning(java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public InternalResponse updateAnalyticsAlarmWarning(Integer tenantId, Integer lightId,
			Integer statusExceptionTypeEnumValue, String userId, String operator)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightState(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResponse updateLightState(LightRequest lightRequest)
	{
		return getInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchCanInsertCustomSearch(com.sensus.mlc.tenant.model.Tenant,
	 * com.sensus.mlc.smartpoint.model.CustomSearch, java.lang.Integer, java.util.List, java.util.List)
	 */
	@Override
	public Boolean fetchCanInsertCustomSearch(Tenant tenant, CustomSearch customSearch, Integer userId,
			List<MessageInfo> list, List<Integer> allowedGroupIdList)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#generateLightHistoryFileCSV(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchSmartpointsToMap(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeSmartpointInfo> fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		return getGeocodeSmartpointInfoBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchStatusMessageById(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageById(LightRequest lightRequest)
	{
		return getStatusMessageResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateCurrentStatusMessage(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public InternalResponse updateCurrentStatusMessage(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response.setStatus(Status.OperationSuccess);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchCountSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchCountSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		response.getResultsList().add(ARBITRARY_COUNT_SMARTPOINT_TO_MAP);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLimitedSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLimitedSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		return getLightResponseBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchMiddleMap(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<SmartpointMiddleMap> fetchMiddleMap(InquiryLightRequest inquiryLightRequest)
	{
		return new InternalResultsResponse<SmartpointMiddleMap>();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchMiddleSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchMiddleSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		return getLightResponseBySituation();
	}

	/**
	 * Gets the inquiry light response error.
	 *
	 * @return the inquiry light response error
	 */
	private InquiryLightResponse getInquiryLightResponseError()
	{
		InquiryLightResponse response = new InquiryLightResponse();
		response.setOperationSuccess(Status.ExceptionError);
		response.addFieldErrorMessage(ERROR_CODE);
		return response;
	}

	/**
	 * Gets the light response.
	 *
	 * @return the light response
	 */
	private InternalResultsResponse<Light> getLightResponse()
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		Light light = TestBaseUtil.createLight();
		light.setOffSetSchedule(TestBaseUtil.createOffSetSchedule());
		light.setProtect(true);
		light.setCreateDate(getNewDateUTC());
		response.addResult(light);
		return response;
	}

	/**
	 * Gets the custom search response.
	 *
	 * @return the custom search response
	 */
	private InternalResultsResponse<CustomSearch> getCustomSearchResponse()
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		CustomSearch customSearch = TestBaseUtil.createCustomSearch();
		SearchParameter searchParameterSort = TestBaseUtil.createSearchParameter();
		SearchParameter searchParameterSmartpointFilter = TestBaseUtil.createSearchParameter();
		searchParameterSmartpointFilter.setPropertyEnum(PropertyEnum.SMARTPOINT_FILTER);
		SearchParameter searchParameterSmartpointColumn = TestBaseUtil.createSearchParameter();
		searchParameterSmartpointColumn.setPropertyEnum(PropertyEnum.SMARTPOINT_COLUMN);
		customSearch.getSearchParameters().add(searchParameterSort);
		customSearch.getSearchParameters().add(searchParameterSmartpointFilter);
		customSearch.getSearchParameters().add(searchParameterSmartpointColumn);
		response.addResult(customSearch);
		return response;
	}

	/**
	 * Gets the status message response.
	 *
	 * @return the status message response
	 */
	private InternalResultsResponse<StatusMessage> getStatusMessageResponse()
	{
		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
		response.addResult(TestBaseUtil.createStatusMessage());
		return response;
	}

	/**
	 * Gets the light history response.
	 *
	 * @return the light history response
	 */
	private InternalResultsResponse<LightHistory> getLightHistoryResponse()
	{
		InternalResultsResponse<LightHistory> response = new InternalResultsResponse<LightHistory>();
		response.addResult(TestBaseUtil.createLightHistory());
		return response;
	}

	/**
	 * Gets the column filter response.
	 *
	 * @return the column filter response
	 */
	private InternalResultsResponse<ColumnFilterResponse> getColumnFilterResponse()
	{
		InternalResultsResponse<ColumnFilterResponse> response = new InternalResultsResponse<ColumnFilterResponse>();
		response.addResult(TestBaseUtil.createColumnFilterResponse());
		return response;
	}

	/**
	 * Gets the current alarm warning message response.
	 *
	 * @return the current alarm warning message response
	 */
	private InternalResultsResponse<CurrentAlarmWarningMessage> getCurrentAlarmWarningMessageResponse()
	{
		InternalResultsResponse<CurrentAlarmWarningMessage> response =
				new InternalResultsResponse<CurrentAlarmWarningMessage>();
		response.addResult(TestBaseUtil.createCurrentAlarmWarningMessage());
		return response;
	}

	/**
	 * Gets the light response by situation.
	 *
	 * @return the light response by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> getLightResponseBySituation()
	{
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getLightResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			internalResultsResponse = getLightResponse();
			internalResultsResponse.getFirstResult().setProtect(false);
			internalResultsResponse = getResponseResultsError();
			return internalResultsResponse;
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the current alarm warning message by situation.
	 *
	 * @return the current alarm warning message by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<GeocodeSmartpointInfo> getGeocodeSmartpointInfoBySituation()
	{
		InternalResultsResponse<GeocodeSmartpointInfo> internalResultsResponse =
				new InternalResultsResponse<GeocodeSmartpointInfo>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getGeocodeSmartpointInfoResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse = getResponseResultsError();
			internalResultsResponse.getResultsList().clear();
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the current alarm warning message response.
	 *
	 * @return the current alarm warning message response
	 */
	private InternalResultsResponse<GeocodeSmartpointInfo> getGeocodeSmartpointInfoResponse()
	{
		InternalResultsResponse<GeocodeSmartpointInfo> response =
				new InternalResultsResponse<GeocodeSmartpointInfo>();
		GeocodeSmartpointInfo geoCode = new GeocodeSmartpointInfo();
		geoCode.setSmartpointsTotalByLatLong(200);
		geoCode.setLatitudeTrunc(10.00);
		geoCode.setLongitudeTrunc(10.00);
		response.addResult(geoCode);
		return response;
	}

	/**
	 * Gets the integer response by situation.
	 *
	 * @return the integer response by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Integer> getIntegerResponseBySituation()
	{
		InternalResultsResponse<Integer> internalResultsResponse = new InternalResultsResponse<Integer>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResultsResponse.getResultsList().add(ARBITRARY_COUNT_LIGHTS);
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the custom search response by situation.
	 *
	 * @return the custom search response by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<CustomSearch> getCustomSearchResponseBySituation()
	{
		InternalResultsResponse<CustomSearch> internalResultsResponse = new InternalResultsResponse<CustomSearch>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getCustomSearchResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the status message response by situation.
	 *
	 * @return the status message response by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<StatusMessage> getStatusMessageResponseBySituation()
	{
		InternalResultsResponse<StatusMessage> internalResultsResponse = new InternalResultsResponse<StatusMessage>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getStatusMessageResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the light history response by situation.
	 *
	 * @return the light history response by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<LightHistory> getLightHistoryResponseBySituation()
	{
		InternalResultsResponse<LightHistory> internalResultsResponse = new InternalResultsResponse<LightHistory>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getLightHistoryResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the hash map response by situation.
	 *
	 * @return the hash map response by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<HashMap<String, Integer>> getHashMapResponseBySituation()
	{
		InternalResultsResponse<HashMap<String, Integer>> internalResultsResponse =
				new InternalResultsResponse<HashMap<String, Integer>>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the column filter response by situation.
	 *
	 * @return the column filter response by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<ColumnFilterResponse> getColumnFilterResponseBySituation()
	{
		InternalResultsResponse<ColumnFilterResponse> internalResultsResponse =
				new InternalResultsResponse<ColumnFilterResponse>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getColumnFilterResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse = getResponseResultsError();
			internalResultsResponse.getResultsList().clear();
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the current alarm warning message by situation.
	 *
	 * @return the current alarm warning message by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<CurrentAlarmWarningMessage> getCurrentAlarmWarningMessageBySituation()
	{
		InternalResultsResponse<CurrentAlarmWarningMessage> internalResultsResponse =
				new InternalResultsResponse<CurrentAlarmWarningMessage>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getCurrentAlarmWarningMessageResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse = getResponseResultsError();
			internalResultsResponse.getResultsList().clear();
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the internal response.
	 *
	 * @return the internal response
	 */
	private InternalResponse getInternalResponse()
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertSmartPoint(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public Integer insertSmartPoint(GuaranteeLightExistenceRequest lightRequest)
	{
		Integer smartPointId = null;
		if ((getSituationsEnum() == SituationsEnum.SUCCESS) || (getSituationsEnum() == SituationsEnum.VALIDATION))
		{
			smartPointId = new Random().nextInt(NUMBER_RANGE);
			return smartPointId;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return smartPointId;
		}
		return smartPointId;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightConfiguration(com.sensus.mlc.smartpoint.model.request
	 * .GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightConfiguration(GuaranteeLightExistenceRequest lightRequest)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightSchedule(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightSchedule(GuaranteeLightExistenceRequest lightRequest)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightLastOperationalData(com.sensus.mlc.smartpoint.model.request
	 * .GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightLastOperationalData(GuaranteeLightExistenceRequest lightRequest)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightLocation(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightLocation(GuaranteeLightExistenceRequest lightRequest)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightToInsert(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public Light fetchLightToInsert(GuaranteeLightExistenceRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		Light light = TestBaseUtil.createLight();
		light.setProtect(false);
		light.setCreateDate(getNewDateUTC());
		response.addResult(light);
		response.setStatus(Status.OperationSuccess);

		return light;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchStatusMessageStatusSubtype(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Integer fetchStatusMessageStatusSubtype(Integer idStatusMessage, Integer statusExceptionTypeEnumValue,
			String userId)
	{
		Integer responseSubtype = 1;
		return responseSubtype;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLight(LightRequest lightRequest)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightSchedule(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightSchedule(LightRequest lightRequest)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightLastOperationData(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightLastOperationData(LightRequest lightRequest)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightLocation(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightLocation(LightRequest lightRequest)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightConfiguration(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightConfiguration(LightRequest lightRequest)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightScheduleById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightScheduleById(LightRequest lightRequest)
	{
		return getLightResponseBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightLastOperationDataById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLastOperationDataById(LightRequest lightRequest)
	{
		return getLightResponseBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightLocationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLocationById(LightRequest lightRequest)
	{
		return getLightResponseBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightConfigurationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightConfigurationById(LightRequest lightRequest)
	{
		return getLightResponseBySituation();
	}

	@Override
	public Boolean checkIfValidBindingMessage(LightRequest lightRequest)
	{
		return true;
	}
}