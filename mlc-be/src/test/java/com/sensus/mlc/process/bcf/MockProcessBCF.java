package com.sensus.mlc.process.bcf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Class MockProcessBCF.
 */
public class MockProcessBCF extends AbstractMockBase implements IProcessBCF
{

	/** The Constant JAVA_IO_TMPDIR. */
	private static final String JAVA_IO_TMPDIR = "java.io.tmpdir";

	/** The Constant FILE_CSV. */
	private static final String FILE_CSV = "file.csv";

	/** The Constant SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION. */
	private static final String SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.processbclimpl.defaultexception";

	/** The Constant UPDATE_LIGHT_PROPERTIES. */
	private static final String UPDATE_LIGHT_PROPERTIES = "UpdateLightProperties";

	/** The Constant READ_LIGHT_STATUS. */
	private static final String READ_LIGHT_STATUS = "ReadLightStatus";

	/** The Constant CLEAR_SCHEDULE. */
	private static final String CLEAR_SCHEDULE = "ClearSchedule";

	/** The Constant CLEAR_ONE_ALARM. */
	private static final String CLEAR_ONE_ALARM = "ClearOneAlarm";

	/** The Constant CLEAR_ALL_ALARMS. */
	private static final String CLEAR_ALL_ALARMS = "ClearAllAlarms";

	/** The Constant APPLY_SCHEDULE. */
	private static final String APPLY_SCHEDULE = "ApplySchedule";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchMonitoredProcesses(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchMonitoredProcesses(ProcessRequest processRequest)
	{

		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#insertProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse insertProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#unmonitorProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse unmonitorProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#updateProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = getProcessResponseDefault();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(true);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(false);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchProcessByTransactionId(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessByTransactionId(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getProcessResponseError();
		}

		ProcessResponse response = getProcessResponseDefault();
		Process process = processRequest.getProcess();

		if (process.getRniCorrelationId().equalsIgnoreCase(APPLY_SCHEDULE))
		{
			response = getProcessResponseApplySchedule(response);
		}
		if (process.getRniCorrelationId().equalsIgnoreCase(CLEAR_SCHEDULE))
		{
			response = getProcessResponseClearSchedule(response);
		}
		if (process.getRniCorrelationId().equalsIgnoreCase(CLEAR_ALL_ALARMS))
		{
			response = getProcessResponseClearAllAlarms(response);
		}
		if (process.getRniCorrelationId().equalsIgnoreCase(CLEAR_ONE_ALARM))
		{
			response = getProcessResponseClearOneAlarm(response);
		}
		if (process.getRniCorrelationId().equalsIgnoreCase("ApplyLightLevel"))
		{
			response = getProcessResponseApplyLightLevel(response);
		}
		if (process.getRniCorrelationId().equalsIgnoreCase("ApplyLightLevel4"))
		{
			response = getProcessResponseApplyLightLevel4(response);
		}
		if (process.getRniCorrelationId().equalsIgnoreCase(READ_LIGHT_STATUS))
		{
			response = getProcessResponseReadLightStatus(response);
		}
		if (process.getRniCorrelationId().equalsIgnoreCase(UPDATE_LIGHT_PROPERTIES))
		{
			response = getProcessResponseUpdateLightProperties(response);
		}
		else
		{
			if (process.getRniCorrelationId().equalsIgnoreCase("GOOD"))
			{
				response = getProcessResponseGood(response);
			}
		}

		return response;
	}

	/**
	 * Gets the process response good.
	 * 
	 * @param response the response
	 * @return the process response good
	 */
	private ProcessResponse getProcessResponseGood(ProcessResponse response)
	{
		Process groupLrp = new Process();

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		listPr.add(prepareProcessResult(2));
		groupLrp.setProcessItems(listPr);
		LCAction lcAction = new LCAction();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter parameter = new LCActionParameter();
		parameter.setProperty(PropertyEnum.ACTIVE);
		parameter.setActionValue("Test");

		actionParameters.add(parameter);
		lcAction.setActionParameters(actionParameters);
		lcAction.setActionType(LCActionTypeEnum.UPDATE_LIGHT_STATUS);
		groupLrp.setLcAction(lcAction);
		List<Process> list = new ArrayList<Process>();
		list.add(groupLrp);

		response.setProcesses(list);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Gets the process response update light properties.
	 * 
	 * @param response the response
	 * @return the process response update light properties
	 */
	private ProcessResponse getProcessResponseUpdateLightProperties(ProcessResponse response)
	{
		Process lightLrp = new Process();

		LCAction mlcAction = new LCAction();
		LCActionParameter actionParameterLightIntensity =
				new LCActionParameter(PropertyEnum.POLE_ID, "P1");
		mlcAction.setActionType(LCActionTypeEnum.UPDATE_LIGHT_POLE_ID);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameterLightIntensity);
		mlcAction.setActionParameters(actionParameters);
		lightLrp.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		lightLrp.setProcessItems(listPr);

		response.getProcesses().add(lightLrp);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Gets the process response read light status.
	 * 
	 * @param response the response
	 * @return the process response read light status
	 */
	private ProcessResponse getProcessResponseReadLightStatus(ProcessResponse response)
	{
		Process lightLrp = new Process();

		LCAction mlcAction = new LCAction();
		LCActionParameter actionParameterLightIntensity =
				new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, LightIntensityEnum.LEVEL_4
						.getValue().toString());
		mlcAction.setActionType(LCActionTypeEnum.GET_LIGHT_STATUS);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameterLightIntensity);
		mlcAction.setActionParameters(actionParameters);
		lightLrp.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		lightLrp.setProcessItems(listPr);

		response.getProcesses().add(lightLrp);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Gets the process response apply light level4.
	 * 
	 * @param response the response
	 * @return the process response apply light level4
	 */
	private ProcessResponse getProcessResponseApplyLightLevel4(ProcessResponse response)
	{
		Process turnOnOffLRP = new Process();
		Group group = new Group();
		group.setId(1);
		LCActionParameter actionParameter =
				new LCActionParameter(PropertyEnum.GROUP_ID, group.getId().toString());
		LCActionParameter actionParameterLightIntensity =
				new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, LightIntensityEnum.LEVEL_4.name());
		LCAction mlcAction = new LCAction();
		mlcAction.setActionType(LCActionTypeEnum.DEL_SCHEDULE);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameter);
		actionParameters.add(actionParameterLightIntensity);
		mlcAction.setActionParameters(actionParameters);
		turnOnOffLRP.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		listPr.add(prepareProcessResult(2));
		turnOnOffLRP.setProcessItems(listPr);

		response.getProcesses().add(turnOnOffLRP);
		response.setOperationSuccess(false);
		return response;
	}

	/**
	 * Gets the process response apply light level.
	 * 
	 * @param response the response
	 * @return the process response apply light level
	 */
	private ProcessResponse getProcessResponseApplyLightLevel(ProcessResponse response)
	{
		Process turnOnOffLRP = new Process();
		Group group = new Group();
		group.setId(1);
		LCActionParameter actionParameter =
				new LCActionParameter(PropertyEnum.GROUP_ID, group.getId().toString());
		LCActionParameter actionParameterLightIntensity =
				new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, "10");
		LCAction mlcAction = new LCAction();
		mlcAction.setActionType(LCActionTypeEnum.SET_INTENSITY_BY_GRP);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameter);
		actionParameters.add(actionParameterLightIntensity);
		mlcAction.setActionParameters(actionParameters);
		turnOnOffLRP.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		listPr.add(prepareProcessResult(2));
		turnOnOffLRP.setProcessItems(listPr);

		response.getProcesses().add(turnOnOffLRP);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Gets the process response clear one alarm.
	 * 
	 * @param response the response
	 * @return the process response clear one alarm
	 */
	private ProcessResponse getProcessResponseClearOneAlarm(ProcessResponse response)
	{
		Process clearLRP = new Process();

		LCAction mlcAction = new LCAction();
		mlcAction.setActionType(LCActionTypeEnum.CLEAR_ALARM);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameter2 =
				new LCActionParameter(PropertyEnum.STATUS_SUBTYPE_ID,
						StatusExceptionTypeEnum.BROWN_OUT_DETECTED
								.getValue().toString());
		actionParameters.add(actionParameter2);
		mlcAction.setActionParameters(actionParameters);
		clearLRP.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		listPr.add(prepareProcessResult(2));
		clearLRP.setProcessItems(listPr);

		response.getProcesses().add(clearLRP);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Gets the process response clear all alarms.
	 * 
	 * @param response the response
	 * @return the process response clear all alarms
	 */
	private ProcessResponse getProcessResponseClearAllAlarms(ProcessResponse response)
	{
		Process clearLRP = new Process();
		LCActionParameter actionParameter =
				new LCActionParameter(PropertyEnum.STATUS_MESSAGE_ID, LightStatusEnum.ACTIVE.getValue()
						.toString());
		LCAction mlcAction = new LCAction();
		mlcAction.setActionType(LCActionTypeEnum.CLEAR_ALL_ALARMS);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameter);
		mlcAction.setActionParameters(actionParameters);
		clearLRP.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		listPr.add(prepareProcessResult(2));
		clearLRP.setProcessItems(listPr);

		response.getProcesses().add(clearLRP);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Gets the process response clear schedule.
	 * 
	 * @param response the response
	 * @return the process response clear schedule
	 */
	private ProcessResponse getProcessResponseClearSchedule(ProcessResponse response)
	{
		Process scheduleLRP = new Process();
		OffsetSchedule schedule = new OffsetSchedule();
		schedule.setId(1);
		LCActionParameter actionParameter =
				new LCActionParameter(PropertyEnum.SCHEDULE_ID, schedule.getId().toString());
		LCAction mlcAction = new LCAction();
		mlcAction.setActionType(LCActionTypeEnum.DEL_SCHEDULE);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameter);
		mlcAction.setActionParameters(actionParameters);
		scheduleLRP.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		listPr.add(prepareProcessResult(2));
		scheduleLRP.setProcessItems(listPr);

		response.getProcesses().add(scheduleLRP);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Gets the process response apply schedule.
	 * 
	 * @param response the response
	 * @return the process response apply schedule
	 */
	private ProcessResponse getProcessResponseApplySchedule(ProcessResponse response)
	{
		Process scheduleLRP = new Process();
		OffsetSchedule schedule = new OffsetSchedule();
		schedule.setId(1);
		LCActionParameter actionParameter =
				new LCActionParameter(PropertyEnum.SCHEDULE_ID, schedule.getId().toString());
		LCAction mlcAction = new LCAction();
		mlcAction.setActionType(LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_OFFSET);
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameter);
		mlcAction.setActionParameters(actionParameters);
		scheduleLRP.setLcAction(mlcAction);

		List<ProcessItem> listPr = new ArrayList<ProcessItem>();

		listPr.add(prepareProcessResult(1));
		listPr.add(prepareProcessResult(2));
		scheduleLRP.setProcessItems(listPr);

		response.getProcesses().add(scheduleLRP);
		response.setOperationSuccess(true);
		return response;
	}

	/**
	 * Prepare process result.
	 * 
	 * @param lightId the light id
	 * @return the process item
	 */
	private ProcessItem prepareProcessResult(Integer lightId)
	{
		Light light = new Light();
		light.setId(lightId);
		light.setRniId(lightId);
		ProcessItem pr1 = new ProcessItem();
		pr1.setProcessItemStatusEnum(ProcessItemStatusEnum.PENDING);
		pr1.setLight(light);
		return pr1;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#retryProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse retryProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchProcesses(com.sensus.mlc.process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcesses(InquiryProcessRequest processRequest)
	{
		return new InquiryProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchProcessByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public ProcessResponse fetchProcessByLight(LightRequest lightRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#abortProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse abortProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchProcessById(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchTenantByRniCode(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public TenantResponse fetchTenantByRniCode(LightingControlRequest request)
	{
		TenantResponse response = new TenantResponse();
		Tenant tenant = new Tenant(request.getTenant().getRniCode());
		tenant.setId(1);
		tenant.setLightTimeZone("US/Eastern");
		response.setTenant(tenant);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchRniLinkStatus(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchRniLinkStatus(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#updateCSVDownloaded(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#generateSummaryFileCSV(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse generateSummaryFileCSV(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchCountMonitoredProcesses(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#insertCSVProcess(com.sensus.mlc.base.model.request.LightSelectionRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		ProcessResponse response = getProcessResponseDefault();
		response = insertProcessFromEcoMode(lightSelectionRequest, response);
		response = insertProcessFromAnalytics(lightSelectionRequest, response);
		response = insertProcessFromSmartpoint(lightSelectionRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#generateFileCSV(com.sensus.mlc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public InquiryProcessResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InquiryProcessResponse();
	}

	/**
	 * Gets the process response default.
	 * 
	 * @return the process response default
	 */
	private ProcessResponse getProcessResponseDefault()
	{
		return new ProcessResponse();
	}

	/**
	 * Gets the process response error.
	 * 
	 * @return the process response error
	 */
	private ProcessResponse getProcessResponseError()
	{
		ProcessResponse response = new ProcessResponse();
		response.setOperationSuccess(false);
		response.addFieldErrorMessage(ERROR_CODE);
		return response;
	}

	/**
	 * Gets the process response exception.
	 * 
	 * @return the process response exception
	 */
	private ProcessResponse getProcessResponseException()
	{
		ProcessResponse response = new ProcessResponse();
		response.setOperationSuccess(false);
		response.addFieldErrorMessage(SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION);
		return response;
	}

	/**
	 * Insert process from eco mode.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @param response the response
	 * @return the process response
	 */
	private ProcessResponse insertProcessFromEcoMode(LightSelectionRequest lightSelectionRequest,
			ProcessResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Light light = TestBaseUtil.createLight();

			response = getProcessResponseDefault();
			response.setFileName(System.getProperty(JAVA_IO_TMPDIR) + FILE_CSV);
			response.setProcesses(Arrays.asList(TestBaseUtil.createProcess(light, null)));
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getProcessResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			return getProcessResponseException();
		}

		return response;
	}

	/**
	 * Insert process from smartpoint.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @param response the response
	 * @return the process response
	 */
	private ProcessResponse insertProcessFromSmartpoint(LightSelectionRequest lightSelectionRequest,
			ProcessResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Light light = TestBaseUtil.createLight();

			response = getProcessResponseDefault();
			response.setFileName(System.getProperty(JAVA_IO_TMPDIR) + FILE_CSV);
			response.setProcesses(Arrays.asList(TestBaseUtil.createProcess(light, null)));
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getProcessResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			return getProcessResponseException();
		}

		return response;
	}

	/**
	 * Insert process from analytics.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @param response the response
	 * @return the process response
	 */
	private ProcessResponse insertProcessFromAnalytics(LightSelectionRequest lightSelectionRequest,
			ProcessResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Light light = TestBaseUtil.createLight();

			response = getProcessResponseDefault();
			response.setFileName(System.getProperty(JAVA_IO_TMPDIR) + FILE_CSV);
			response.setProcesses(Arrays.asList(TestBaseUtil.createProcess(light, null)));
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getProcessResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			return getProcessResponseException();
		}

		return response;
	}
}