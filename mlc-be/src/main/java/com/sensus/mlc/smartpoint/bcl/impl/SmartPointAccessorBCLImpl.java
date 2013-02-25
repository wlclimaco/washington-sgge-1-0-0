/*
 *
 */
package com.sensus.mlc.smartpoint.bcl.impl;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.mlc.base.util.LCHelp.treatReturnFromBCL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.group.bcl.IGroupBCL;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tag.bcl.ITagBCL;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class SmartPointAccessorBCLImpl.
 */
public class SmartPointAccessorBCLImpl extends SmartPointBCLBase implements ISmartPointAccessorBCL
{
	/** The Constant MAX_DECIMAL_PLACE_TRUNC. */
	private static final int MAX_DECIMAL_PLACE_TRUNC = 3;

	/** The Constant MAX_LIGHT_TO_FETCH_NEW_GROUP. */
	private static final int MAX_LIGHT_TO_FETCH_NEW_GROUP = 100;

	/** The Constant DISPLAY_ORDER_10. */
	private static final Integer DISPLAY_ORDER_10 = 10;

	/** The Constant DISPLAY_ORDER_9. */
	private static final Integer DISPLAY_ORDER_9 = 9;

	/** The Constant DISPLAY_ORDER_8. */
	private static final Integer DISPLAY_ORDER_8 = 8;

	/** The Constant DISPLAY_ORDER_7. */
	private static final Integer DISPLAY_ORDER_7 = 7;

	/** The Constant DISPLAY_ORDER_6. */
	private static final Integer DISPLAY_ORDER_6 = 6;

	/** The Constant DISPLAY_ORDER_5. */
	private static final Integer DISPLAY_ORDER_5 = 5;

	/** The Constant DISPLAY_ORDER_4. */
	private static final Integer DISPLAY_ORDER_4 = 4;

	/** The Constant DISPLAY_ORDER_3. */
	private static final Integer DISPLAY_ORDER_3 = 3;

	/** FINAL CONSTANTS. */
	private static final int TENANT_NOT_FOUND_IN_SYSTEM = -3;

	/** The Constant LIGHT_NOT_FOUND_IN_SYSTEM. */
	private static final int LIGHT_NOT_FOUND_IN_SYSTEM = -2;

	/** The Constant LIGHT_NOT_IN_THIS_TENANT. */
	private static final int LIGHT_NOT_IN_THIS_TENANT = -1;

	/** The Constant LIGHT_NOT_IN_TENANT. */
	private static final String LIGHT_NOT_IN_TENANT = "sensus.mlc.smartpointvalidator.light.not.in.tenant";

	/** The Constant LIGHT_NOT_FOUND. */
	private static final String LIGHT_NOT_FOUND = "sensus.mlc.smartpointvalidator.light.not.found";

	/** The Constant TENANT_NOT_FOUND. */
	private static final String TENANT_NOT_FOUND = "sensus.mlc.smartpointvalidator.tenant.not.found";

	/** The Constant SENSUS_MLC_PROCESSVALIDATOR_PROCESS_NOT_FOUND. */
	private static final String SENSUS_MLC_PROCESSVALIDATOR_PROCESS_NOT_FOUND =
			"sensus.mlc.processvalidator.process.not.found";

	/** The Constant FETCH_COLUMN_FILTER_FAILED. */
	private static final String FETCH_COLUMN_FILTER_FAILED = "sensus.mlc.smartpointbclimpl.fetch.column.filter.failed";

	/** The Constant DEFAULT_LIGHT_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCL_EXCEPTION_MSG = "sensus.mlc.smartpointbclimpl.defaultexception";

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The group bcl. */
	private IGroupBCL groupBCL; // injected by Spring through setter

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The schedule bcl. */
	private IScheduleBCL scheduleBCL; // injected by Spring through setter

	/**
	 * Gets the group bcl.
	 *
	 * @return the group bcl
	 */
	public IGroupBCL getGroupBCL()
	{
		return groupBCL;
	}

	/**
	 * Sets the group bcl.
	 *
	 * @param groupBCL the new group bcl
	 */
	public void setGroupBCL(IGroupBCL groupBCL)
	{
		this.groupBCL = groupBCL;
	}

	/**
	 * Gets the tag bcl.
	 *
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 *
	 * @param tagBCL the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * Gets the schedule bcl.
	 *
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return scheduleBCL;
	}

	/**
	 * Sets the schedule bcl.
	 *
	 * @param scheduleBCL the new schedule bcl
	 */
	public void setScheduleBCL(IScheduleBCL scheduleBCL)
	{
		this.scheduleBCL = scheduleBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllLights(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLights(InquiryLightRequest inquiryLightRequest)
	{
		// Instantiate a response
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		// if there is a process, than filter only the process smart points.
		// it will filter when the user clicks at the number at event history or monitor screen
		if (!ValidationUtil.isNull(inquiryLightRequest.getProcessId()))
		{
			inquiryLightRequest.setPaginationAllSelected(false);
			Process process = new Process();
			process.setId(inquiryLightRequest.getProcessId());
			ProcessRequest processRequest = new ProcessRequest(inquiryLightRequest.getUserContext());
			processRequest.setProcess(process);
			processRequest.setTenant(inquiryLightRequest.getTenant());
			InternalResultsResponse<Process> processResponse =
					getProcessBCL().fetchProcessById(processRequest);
			List<Integer> lightIdList = new ArrayList<Integer>();
			for (ProcessItem processItem : processResponse.getFirstResult().getProcessItems())
			{
				lightIdList.add(processItem.getLight().getId());
			}
			inquiryLightRequest.setSelectionPaginationIds(lightIdList);
			response = getSmartPointDAC().fetchAllLights(inquiryLightRequest);
		}
		else if (!ValidationUtil.isNull(inquiryLightRequest.getSearch()))
		{
			inquiryLightRequest.setSearch(verifyCustomSearch(inquiryLightRequest.getSearch()));
			response = getSmartPointDAC().fetchAllLights(inquiryLightRequest);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#countLights(com.sensus.mlc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> countLights(LightingControlRequest request)
	{
		return getSmartPointDAC().countLights(request);
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
		return getSmartPointDAC().fetchAllLightsToApplySchedule(scheduleRequest);
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
		return getSmartPointDAC().fetchLightByRniId(lightRequest);
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
		// once this method is cached (and only serializable objects can be cached), we have to change from the request
		// to a list of integer
		List<Integer> ids = new ArrayList<Integer>();
		for (PropertyEnum property : request.getProperties())
		{
			ids.add(property.getValue());
		}

		InternalResultsResponse<PropertyValidValue> response = new InternalResultsResponse<PropertyValidValue>();
		response.getResultsList().addAll(getSmartPointDAC().fetchPropertyValidValues(request));

		return response;
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
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		// get the complete Process
		Process process = getProcessBCL().fetchProcessById(processRequest).getFirstResult();
		if (process.getIsProcessComplete())
		{
			LightRequest lightRequest = new LightRequest(processRequest.getUserContext());
			lightRequest.addLight(process.getProcessItems().get(0).getLight());
			response.addResult(getSmartPointDAC().fetchLightById(lightRequest).getFirstResult());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchAllCustomSearch(com.sensus.mlc.base.model.request.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryPaginationRequest request)
	{
		return getSmartPointDAC().fetchAllCustomSearch(request);
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
		InternalResponse response = new InternalResponse();
		switch (getSmartPointDAC().isLightInTenant(request.getLightRniId(), request.getTenantRniCode(),
				request.getAllowedGroupIdList()))
		{
			// -1 = smartpoint does not belong to this tenant.
			// -2 = RniId not found.
			// -3 = Tenant not found.
					case LIGHT_NOT_IN_THIS_TENANT:
						response.addObjectErrorMessage(LIGHT_NOT_IN_TENANT,
								new Object[] {request.getLightRniId(), request.getTenantRniCode()});
						response.setStatus(Status.NoRowsFoundError);
						break;
					case LIGHT_NOT_FOUND_IN_SYSTEM:
						// If not strict validation, light not found is not an issue.
						if (strictValidation)
						{
							response.addObjectErrorMessage(LIGHT_NOT_FOUND, new Object[] {request.getLightRniId()});
							response.setStatus(Status.NoRowsFoundError);
						}
						break;
					case TENANT_NOT_FOUND_IN_SYSTEM:
						response.addObjectErrorMessage(TENANT_NOT_FOUND, new Object[] {request.getTenantRniCode()});
						response.setStatus(Status.NoRowsFoundError);
						break;
					default:
						break;
		}
		return response;
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
		SearchLight searchLight = new SearchLight();
		searchLight.setStatusList(search.getStatusList());
		searchLight.setPropertyValues(search.getPropertyValues());
		if (ValidationUtil.isNullOrEmpty(search.getSearchParameters()))
		{
			return searchLight;
		}

		for (SearchParameter param : search.getSearchParameters())
		{
			if (ValidationUtil.isNull(param.getValue()))
			{
				continue;
			}

			param.setValidParameter(true);
			if (param.getPropertyEnum().equals(PropertyEnum.GROUP_ID))
			{
				GroupRequest request = new GroupRequest(new UserContext());
				request.setGroup(new Group(new Integer(param.getValue())));
				InternalResultsResponse<Group> response = getGroupBCL().fetchGroupById(request);
				if (ValidationUtil.isNull(response.getFirstResult()))
				{
					param.setValidParameter(false);
				}
			}
			else if (param.getPropertyEnum().equals(PropertyEnum.OFFSET_SCHEDULE)
					|| param.getPropertyEnum().equals(PropertyEnum.EVENT_SCHEDULE))
			{
				ScheduleRequest request = new ScheduleRequest(new UserContext());
				Schedule schedule = new EventSchedule();
				schedule.setId(new Integer(param.getValue()));
				request.setSchedule(schedule);
				InternalResultsResponse<Schedule> response = getScheduleBCL().fetchScheduleById(request);
				if (ValidationUtil.isNull(response.getFirstResult()))
				{
					param.setValidParameter(false);
				}
			}
			else if (param.getPropertyEnum().equals(PropertyEnum.TAG_ID))
			{
				TagRequest request = new TagRequest(new UserContext());
				request.setTag(new Tag(new Integer(param.getValue())));
				InternalResultsResponse<Tag> response = getTagBCL().fetchTagById(request);
				if (ValidationUtil.isNull(response.getFirstResult()))
				{
					param.setValidParameter(false);
				}
			}
			if (param.isValidParameter())
			{
				searchLight.getSearchParameters().add(param);
			}
		}
		return searchLight;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchCanInsertCustomSearch(com.sensus.mlc.tenant.model.Tenant
	 * , com.sensus.mlc.smartpoint.model.CustomSearch, java.lang.Integer, java.util.List, java.util.List)
	 */
	@Override
	public Boolean fetchCanInsertCustomSearch(Tenant tenant, CustomSearch customSearch, Integer userId,
			List<MessageInfo> list, List<Integer> allowedGroupIdList)
	{
		return getSmartPointDAC().fetchCanInsertCustomSearch(tenant, customSearch, userId, list,
				allowedGroupIdList);
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
		return getSmartPointDAC().fetchStatusMessageByLightID(request.getFirstLight().getId(),
				request.getAllowedGroupIdList());

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#generateFileCSV(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageById(LightRequest lightRequest)
	{
		return getSmartPointDAC().fetchStatusMessageById(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#generateFileCSV(com.sensus.mlc.smartpoint.model.request.InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		persistLogDebug("Get all lights filtered finished at generate CSV File");

		InternalResultsResponse<HashMap<String, String>> responseAllLights =
				fetchAllLightsToCSV(inquiryLightRequest);

		inquiryLightRequest.setLightsToCSV(responseAllLights.getResultsList());

		InquiryLightResponse inquiryLightResponse = generateGenericFileCSV(inquiryLightRequest);

		if (!inquiryLightResponse.isOperationSuccess())
		{
			return inquiryLightResponse;
		}

		Process process = new Process(inquiryLightRequest.getProcessId());

		ProcessRequest processRequest = new ProcessRequest(inquiryLightRequest.getUserContext());
		processRequest.setProcess(process);

		InternalResultsResponse<Process> responseProcess = getProcessBCL().fetchProcessById(processRequest);

		if (responseProcess.isInError() || ValidationUtil.isNull(responseProcess.getResultsList()))
		{
			inquiryLightResponse.setOperationSuccess(Status.ExceptionError);
			inquiryLightResponse.addObjectErrorMessage(SENSUS_MLC_PROCESSVALIDATOR_PROCESS_NOT_FOUND,
					new Object[] {process.getId()});
			return inquiryLightResponse;
		}

		// update process as completed
		processRequest.getProcessList().clear();
		processRequest.setProcess(responseProcess.getResultsList().get(0));
		processRequest.getProcess().setIsProcessComplete(true);
		process.setEndTime(getNewDateUTC());

		InternalResponse internalResponse = getProcessBCL().updateProcess(processRequest);

		if (internalResponse.isInError())
		{
			treatReturnFromBCL(inquiryLightResponse, internalResponse, DEFAULT_LIGHT_BCL_EXCEPTION_MSG);
			return inquiryLightResponse;
		}

		inquiryLightResponse.setFileName(inquiryLightRequest.getFileName());
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
		return getSmartPointDAC().fetchAllLightsByProcess(processRequest);
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
		return getSmartPointDAC().generateFileCSV(inquiryLightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightIntensityByLight(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public LightIntensityEnum fetchLightIntensityByLight(Integer lightId, Integer percentage)
	{
		if (LightIntensityEnum.LEVEL_0.getValue().equals(percentage))
		{
			return LightIntensityEnum.LEVEL_0;
		}

		List<SensusPartNumberConfiguration> listLightIntensityPercentage =
				getSmartPointDAC().fetchLightIntensityPercentageByLight(lightId).getResultsList();
		Collections.sort(listLightIntensityPercentage, new SensusPartNumberConfiguration());
		if (ValidationUtil.isNullOrEmpty(listLightIntensityPercentage))
		{
			return LightIntensityEnum.LEVEL_6;

		}

		for (SensusPartNumberConfiguration spnConfig : listLightIntensityPercentage)
		{
			if (spnConfig.getPercentage() >= percentage)
			{
				LightIntensityEnum iEnum = LightIntensityEnum.enumForValue(spnConfig.getIntensityLevel());
				iEnum.setPercentage(spnConfig.getPercentage());
				return iEnum;
			}
		}

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
		return getSmartPointDAC().fetchLightHistory(inquiryLightRequest);
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
		return getSmartPointDAC().fetchLightHistoryHeader(lightRequest);
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
		return getSmartPointDAC().fetchAllLightsToCSV(inquiryLightRequest);
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
		return getSmartPointDAC().generateSummaryFileCSV(inquiryLightRequest);
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
		persistLogInfo("Generating CSV file for light history");

		InquiryLightResponse lightResponse = new InquiryLightResponse();

		InternalResponse response = getSmartPointDAC().generateLightHistoryFileCSV(inquiryLightRequest);

		if (response.isInError())
		{
			treatReturnFromBCL(lightResponse, response, DEFAULT_LIGHT_BCL_EXCEPTION_MSG);
			return lightResponse;
		}

		Process process = new Process(inquiryLightRequest.getProcessId());

		ProcessRequest processRequest = new ProcessRequest(inquiryLightRequest.getUserContext());
		processRequest.setProcess(process);

		InternalResultsResponse<Process> responseProcess = getProcessBCL().fetchProcessById(processRequest);

		if (responseProcess.isInError() || ValidationUtil.isNullOrEmpty(responseProcess.getResultsList()))
		{
			lightResponse.setOperationSuccess(Status.ExceptionError);
			lightResponse.addObjectErrorMessage(SENSUS_MLC_PROCESSVALIDATOR_PROCESS_NOT_FOUND,
					new Object[] {process.getId()});
			return lightResponse;
		}

		// update process as completed
		processRequest.getProcessList().clear();
		processRequest.setProcess(responseProcess.getFirstResult());
		processRequest.getProcess().setIsProcessComplete(true);
		processRequest.getProcess().setEndTime(getNewDateUTC());

		response = getProcessBCL().updateProcess(processRequest);

		if (response.isInError())
		{
			treatReturnFromBCL(lightResponse, response, DEFAULT_LIGHT_BCL_EXCEPTION_MSG);
			return lightResponse;
		}

		lightResponse.setFileName(inquiryLightRequest.getFileName());
		return lightResponse;
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
		InternalResultsResponse<ColumnFilterResponse> columnFilterResponse = null;
		ColumnFilterResponse responseColumn = null;

		switch (columnFilterRequest.getListTypeEnum())
		{
			case SMARTPOINTLIST:
				columnFilterResponse = getSmartPointDAC().fetchAllColumnFilters(columnFilterRequest);
				break;
			default:
				List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

				messageInfoList.add(new MessageInfo(FETCH_COLUMN_FILTER_FAILED,
						Message.MessageSeverity.Error,
						Message.MessageLevel.FieldValidation));
				columnFilterResponse = new InternalResultsResponse<ColumnFilterResponse>();
				columnFilterResponse.addMessages(messageInfoList);
				columnFilterResponse.setStatus(Status.ExceptionError);

				return columnFilterResponse;
		}

		if (columnFilterResponse.hasResults()
				&& !ValidationUtil.isNullOrEmpty(columnFilterResponse.getResultsList().get(0).getListColumn())
				&& !ValidationUtil.isNullOrEmpty(columnFilterResponse.getResultsList().get(0).getFilters()))
		{

			responseColumn = new ColumnFilterResponse();

			// Set Columns
			responseColumn.setAdditionalColumns(getAdditionalColumns(columnFilterResponse.getResultsList().get(0)
					.getListColumn()));
			responseColumn.setListColumn(columnFilterResponse.getResultsList().get(0).getListColumn());

			// Set Filters
			responseColumn.setAdditionalFilters(getAdditionalFilters(columnFilterResponse.getResultsList().get(0)
					.getFilters()));
			responseColumn.setFilters(columnFilterResponse.getResultsList().get(0).getFilters());

		}
		else
		{
			responseColumn = new ColumnFilterResponse();

			// Add Default Columns
			if (!columnFilterResponse.hasResults()
					|| ValidationUtil.isNullOrEmpty(columnFilterResponse.getResultsList().get(0).getListColumn()))
			{
				responseColumn.setListColumn(getDefaultColumns());
				responseColumn.setAdditionalColumns(getDefaultAdditionalColumns());
			}
			else
			{
				responseColumn.setAdditionalColumns(getAdditionalColumns(columnFilterResponse.getResultsList()
						.get(0)
						.getListColumn()));
				responseColumn.setListColumn(columnFilterResponse.getResultsList().get(0).getListColumn());
			}
			// Add Default Filters
			if (!columnFilterResponse.hasResults()
					|| ValidationUtil.isNullOrEmpty(columnFilterResponse.getResultsList().get(0).getFilters()))
			{
				responseColumn.setFilters(getDefaultFilters());
				responseColumn.setAdditionalFilters(getDefaultAdditionalFilters());
			}
			else
			{
				responseColumn.setAdditionalFilters(getAdditionalFilters(columnFilterResponse.getResultsList()
						.get(0)
						.getFilters()));
				responseColumn.setFilters(columnFilterResponse.getResultsList().get(0).getFilters());
			}
		}

		// Clear and set response
		columnFilterResponse.getResultsList().clear();
		columnFilterResponse.getResultsList().add(responseColumn);
		columnFilterResponse.setStatus(Status.OperationSuccess);

		return columnFilterResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchCurrentAlarmStatusMessagesByLight(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<CurrentAlarmWarningMessage> fetchCurrentAlarmStatusMessagesByLight(Integer lightID)
	{
		InternalResultsResponse<CurrentAlarmWarningMessage> response =
				new InternalResultsResponse<CurrentAlarmWarningMessage>();

		response.addResults(getSmartPointDAC().fetchCurrentAlarmStatusMessagesByLight(lightID));
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightScheduleById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightScheduleById(LightRequest lightRequest)
	{
		return getSmartPointDAC().fetchLightScheduleById(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightLastOperationDataById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLastOperationDataById(LightRequest lightRequest)
	{
		return getSmartPointDAC().fetchLightLastOperationDataById(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightLocationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLocationById(LightRequest lightRequest)
	{
		return getSmartPointDAC().fetchLightLocationById(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#fetchLightConfigurationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightConfigurationById(LightRequest lightRequest)
	{
		return getSmartPointDAC().fetchLightConfigurationById(lightRequest);
	}

	/**
	 * Gets the all columns.
	 *
	 * @return the all columns
	 */
	private List<Column> getAllColumns()
	{
		List<Column> columns = new ArrayList<Column>();

		columns.add(setColumn(SmartPointColumnEnum.BULB_SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.CITY_NAME, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.COLOR_TEMPERATURE, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.DATE_ADDED, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.FIRMWARE_VERSION, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.RNI_ID, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.HOUSING_COLOR, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.LAMP_TYPE, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.MAP_IT, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.MODEL_NUMBER, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.POLE_ID, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.PROTECTED, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.STATUS, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(SmartPointColumnEnum.INPUT_VOLTAGE_RANGE, "", ZERO));

		return columns;

	}

	/**
	 * Gets the additional columns.
	 *
	 * @param listColumn the list column
	 * @return the additional columns
	 */
	private List<Column> getAdditionalColumns(List<Column> listColumn)
	{
		List<Column> additionalColumns = getAllColumns();

		Column column = null;
		for (int i = 0; i < additionalColumns.size(); i++)
		{
			column = additionalColumns.get(i);
			for (Column column2 : listColumn)
			{
				if (column2.getColumnEnum().equals(column.getColumnEnum()))
				{
					additionalColumns.remove(column);
					i--;
				}

			}
		}

		return additionalColumns;
	}

	/**
	 * Gets the all filters.
	 *
	 * @return the all filters
	 */
	private List<Filter> getAllFilters()
	{
		List<Filter> filters = new ArrayList<Filter>();

		filters.add(setFilter(SmartPointFilterEnum.GROUPS, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.STATUS, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.ALARM_TYPE, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.WARNING_TYPE, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.LIGHT_TYPES, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.EVENT_SCHEDULE, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.OFFSET_SCHEDULE, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.TAGS, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.ADDRESS, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.CONFIGURATION, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.ECOMODE, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.FIRMWARE_VERSION, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.DATE_ADDED, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.MODEL_NUMBER, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.COLOR_TEMPERATURE, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.HOUSING_COLOR, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.VOLTAGE_RANGE, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.BULB_SERIAL_NUMBER, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.LIGHT_DRIVER_SERIAL_NUMBER, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, ZERO));
		filters.add(setFilter(SmartPointFilterEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, ZERO));

		return filters;
	}

	/**
	 * Gets the default additional filters.
	 *
	 * @return the default additional filters
	 */
	private List<Filter> getDefaultAdditionalFilters()
	{
		List<Filter> filters = new ArrayList<Filter>();

		filters.add(setFilter(SmartPointFilterEnum.FIRMWARE_VERSION, 0));
		filters.add(setFilter(SmartPointFilterEnum.DATE_ADDED, 1));
		filters.add(setFilter(SmartPointFilterEnum.MODEL_NUMBER, 2));
		filters.add(setFilter(SmartPointFilterEnum.COLOR_TEMPERATURE, DISPLAY_ORDER_3));
		filters.add(setFilter(SmartPointFilterEnum.HOUSING_COLOR, DISPLAY_ORDER_4));
		filters.add(setFilter(SmartPointFilterEnum.VOLTAGE_RANGE, DISPLAY_ORDER_5));
		filters.add(setFilter(SmartPointFilterEnum.BULB_SERIAL_NUMBER, DISPLAY_ORDER_6));
		filters.add(setFilter(SmartPointFilterEnum.LIGHT_DRIVER_SERIAL_NUMBER, DISPLAY_ORDER_7));
		filters.add(setFilter(SmartPointFilterEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, DISPLAY_ORDER_8));
		filters.add(setFilter(SmartPointFilterEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, DISPLAY_ORDER_9));

		return filters;
	}

	/**
	 * Gets the default filters.
	 *
	 * @return the default filters
	 */
	private List<Filter> getDefaultFilters()
	{
		List<Filter> filters = new ArrayList<Filter>();

		filters.add(setFilter(SmartPointFilterEnum.GROUPS, 0));
		filters.add(setFilter(SmartPointFilterEnum.STATUS, 1));
		filters.add(setFilter(SmartPointFilterEnum.ALARM_TYPE, 2));
		filters.add(setFilter(SmartPointFilterEnum.WARNING_TYPE, DISPLAY_ORDER_3));
		filters.add(setFilter(SmartPointFilterEnum.LIGHT_TYPES, DISPLAY_ORDER_4));
		filters.add(setFilter(SmartPointFilterEnum.EVENT_SCHEDULE, DISPLAY_ORDER_5));
		filters.add(setFilter(SmartPointFilterEnum.OFFSET_SCHEDULE, DISPLAY_ORDER_6));
		filters.add(setFilter(SmartPointFilterEnum.TAGS, DISPLAY_ORDER_7));
		filters.add(setFilter(SmartPointFilterEnum.ADDRESS, DISPLAY_ORDER_8));
		filters.add(setFilter(SmartPointFilterEnum.CONFIGURATION, DISPLAY_ORDER_9));
		filters.add(setFilter(SmartPointFilterEnum.ECOMODE, DISPLAY_ORDER_10));

		return filters;
	}

	/**
	 * Sets the column.
	 *
	 * @param columnEnum the column enum
	 * @param text the text
	 * @param displayOrder the display order
	 * @return the column
	 */
	private Column setColumn(SmartPointColumnEnum columnEnum, String text, Integer displayOrder)
	{
		Column column = new Column();
		column.setColumnEnum(columnEnum);
		column.setFieldName(text);
		column.setOrdered(true);
		column.setDisplayOrder(displayOrder);

		return column;
	}

	/**
	 * Sets the filter.
	 *
	 * @param filterEnum the filter enum
	 * @param displayOrder the display order
	 * @return the filter
	 */
	private Filter setFilter(SmartPointFilterEnum filterEnum, Integer displayOrder)
	{
		Filter filter = new Filter();
		filter.setFilterEnum(filterEnum);
		filter.setDisplayOrder(displayOrder);

		return filter;
	}

	/**
	 * Gets the default columns.
	 *
	 * @return the default columns
	 */
	private List<Column> getDefaultColumns()
	{
		List<Column> columns = new ArrayList<Column>();

		columns.add(setColumn(SmartPointColumnEnum.POLE_ID, "Pole ID", 0));
		columns.add(setColumn(SmartPointColumnEnum.RNI_ID, "FlexNet ID", 1));
		columns.add(setColumn(SmartPointColumnEnum.LAMP_TYPE, "Light Type", 2));
		columns.add(setColumn(SmartPointColumnEnum.DATE_ADDED, "Date Added", DISPLAY_ORDER_3));
		columns.add(setColumn(SmartPointColumnEnum.CITY_NAME, "City", DISPLAY_ORDER_4));
		columns.add(setColumn(SmartPointColumnEnum.MAP_IT, "Map It", DISPLAY_ORDER_5));
		columns.add(setColumn(SmartPointColumnEnum.PROTECTED, "Protected", DISPLAY_ORDER_6));
		columns.add(setColumn(SmartPointColumnEnum.ECOMODE, "Eco-Mode", DISPLAY_ORDER_7));
		columns.add(setColumn(SmartPointColumnEnum.STATUS, "Status", DISPLAY_ORDER_8));

		return columns;
	}

	/**
	 * Gets the default additional columns.
	 *
	 * @return the default additional columns
	 */
	private List<Column> getDefaultAdditionalColumns()
	{
		List<Column> columns = new ArrayList<Column>();

		columns.add(setColumn(SmartPointColumnEnum.FIRMWARE_VERSION, "Firmware Version", null));
		columns.add(setColumn(SmartPointColumnEnum.MODEL_NUMBER, "Model Number", null));
		columns.add(setColumn(SmartPointColumnEnum.COLOR_TEMPERATURE, "Color Temperature", null));
		columns.add(setColumn(SmartPointColumnEnum.HOUSING_COLOR, "Housing Color", null));
		columns.add(setColumn(SmartPointColumnEnum.INPUT_VOLTAGE_RANGE, "Voltage Range", null));
		columns.add(setColumn(SmartPointColumnEnum.BULB_SERIAL_NUMBER, "Bulb Serial Number", null));
		columns.add(setColumn(SmartPointColumnEnum.SERIAL_NUMBER, "Light Driver Serial Number", null));
		columns.add(setColumn(SmartPointColumnEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, "Lower Assembly Serial Number",
				null));
		columns.add(setColumn(SmartPointColumnEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, "Upper Assembly Serial Number",
				null));

		return columns;
	}

	/**
	 * Gets the additional filters.
	 *
	 * @param listFilter the list filter
	 * @return the additional filters
	 */
	private List<Filter> getAdditionalFilters(List<Filter> listFilter)
	{
		List<Filter> additionalFilters = getAllFilters();

		Filter filter = null;
		for (int i = 0; i < additionalFilters.size(); i++)
		{
			filter = additionalFilters.get(i);
			for (Filter filter2 : listFilter)
			{
				if (filter2.getFilterEnum().equals(filter.getFilterEnum()))
				{
					additionalFilters.remove(filter);
					i--;
				}

			}
		}

		return additionalFilters;
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
		InternalResultsResponse<GeocodeSmartpointInfo> response = new InternalResultsResponse<GeocodeSmartpointInfo>();
		response = getSmartPointDAC().fetchSmartpointsToMap(inquiryLightRequest);

		if (response.isInError())
		{
			return response;
		}

		/*
		 * Increasing number of decimal places to fetch more than one group of latitude and longitude
		 * Size = number of groups of latitude and longitude
		 * SmartpointsTotalByLatLong = maximum amount of light to fetch new group
		 * GeoCodeTrunc = Maximum number of decimal places to fetch new groups
		 */
		while ((response.getResultsList().size() == 1)
				&& (response.getFirstResult().getSmartpointsTotalByLatLong() >= MAX_LIGHT_TO_FETCH_NEW_GROUP)
				&& (inquiryLightRequest.getGeoCodeTrunc() < MAX_DECIMAL_PLACE_TRUNC))
		{
			inquiryLightRequest.setGeoCodeTrunc(inquiryLightRequest.getGeoCodeTrunc() + 1);
			response = getSmartPointDAC().fetchSmartpointsToMap(inquiryLightRequest);
		}

		int totalRowsByArea = getSmartPointDAC().fetchCountSmartpointsToMap(inquiryLightRequest).getFirstResult();

		// Remove geocode data to get ALL lights
		removeGeocodeData(inquiryLightRequest);

		int totalRows = getSmartPointDAC().countLights(inquiryLightRequest).getFirstResult();

		response.getResultsSetInfo().setEndRow(totalRowsByArea);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/**
	 * Removes the geocode data.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 */
	private void removeGeocodeData(InquiryLightRequest inquiryLightRequest)
	{
		inquiryLightRequest.setBottomLeftLat(null);
		inquiryLightRequest.setBottomLeftLon(null);
		inquiryLightRequest.setTopRightLat(null);
		inquiryLightRequest.setTopRightLon(null);
	}

	@Override
	public Boolean checkIfValidBindingMessage(LightRequest lightRequest)
	{
		return getSmartPointDAC().checkIfValidBindingMessage(lightRequest);
	}

}
