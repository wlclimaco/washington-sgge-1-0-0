package com.sensus.lc.process.bcf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.cbof.model.Radio;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightStatusEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.InquiryProcessResponse;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.schedule.model.OffsetSchedule;

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
	private static final String READ_LIGHT_STATUS = "processReadLightStatus";

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
	 * com.sensus.lc.process.bcf.IProcessBCF#fetchMonitoredProcesses(com.sensus.lc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#insertProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse insertProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#unmonitorProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse unmonitorProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#updateProcess(com.sensus.lc.process.model.request.ProcessRequest)
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
	 * @see com.sensus.lc.process.bcf.IProcessBCF#fetchProcessByTransactionId(com.sensus.lc.process.model.request.
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

		if (getEvaluableMethods().contains(APPLY_SCHEDULE))
		{
			return getProcessResponseApplySchedule(response);
		}
		if (getEvaluableMethods().contains(CLEAR_SCHEDULE))
		{
			return getProcessResponseClearSchedule(response);
		}
		if (getEvaluableMethods().contains(CLEAR_ALL_ALARMS))
		{
			return getProcessResponseClearAllAlarms(response);
		}
		if (getEvaluableMethods().contains(CLEAR_ONE_ALARM))
		{
			return getProcessResponseClearOneAlarm(response);
		}
		if (getEvaluableMethods().contains("ApplyLightLevel"))
		{
			return getProcessResponseApplyLightLevel(response);
		}
		if (getEvaluableMethods().contains("ApplyLightLevel4"))
		{
			return getProcessResponseApplyLightLevel4(response);
		}
		if (getEvaluableMethods().contains(READ_LIGHT_STATUS))
		{
			return getProcessResponseReadLightStatus(response);
		}
		if (getEvaluableMethods().contains(UPDATE_LIGHT_PROPERTIES))
		{
			return getProcessResponseUpdateLightProperties(response);
		}

		return getProcessResponseGood(response);
	}

	/**
	 * Gets the process response good.
	 * 
	 * @param response the response
	 * @return the process response good
	 */
	private ProcessResponse getProcessResponseGood(ProcessResponse response)
	{
		LCAction lcAction = new LCAction(LCActionTypeEnum.UPDATE_LIGHT_STATUS);
		lcAction.addActionParameter(new LCActionParameter(PropertyEnum.ACTIVE, "Active"));

		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		processItems.add(prepareProcessResult(1));

		Process process = new Process();
		process.setProcessItems(processItems);
		process.setLcAction(lcAction);

		response.setProcesses(Arrays.asList(process));
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
				new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, IntensityEnum.LEVEL_4
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
				new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, IntensityEnum.LEVEL_4.name());
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
						AlertSubTypeEnum.BROWN_OUT_DETECTED.getValue().toString());
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
		mlcAction.setActionType(LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_OFFSET);
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
		light.setRadio(new Radio());
		light.setId(lightId);
		light.getRadio().setFlexNetId(BigInteger.valueOf(lightId));
		ProcessItem pr1 = new ProcessItem();
		pr1.setProcessItemStatusEnum(ProcessItemStatusEnum.PENDING);
		pr1.setLight(light);

		return pr1;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#retryProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse retryProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcf.IProcessBCF#fetchAllProcess(com.sensus.lc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public InquiryProcessResponse fetchAllProcess(InquiryProcessRequest processRequest)
	{
		return new InquiryProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#fetchProcessByLight(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public ProcessResponse fetchProcessByLight(LightRequest lightRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#abortProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse abortProcess(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#fetchProcessById(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcf.IProcessBCF#fetchRniLinkStatus(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchRniLinkStatus(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcf.IProcessBCF#updateCSVDownloaded(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcf.IProcessBCF#generateSummaryFileCSV(com.sensus.lc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse generateSummaryFileCSV(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcf.IProcessBCF#fetchCountMonitoredProcesses(com.sensus.lc.process.model.request.
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
	 * com.sensus.lc.process.bcf.IProcessBCF#insertCSVProcess(com.sensus.lc.base.model.request.LightSelectionRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		ProcessResponse response = getProcessResponseDefault();
		response = insertProcessFromEcoMode(request, response);
		response = insertProcessFromAnalytics(request, response);
		response = insertProcessFromSmartpoint(request, response);
		return response;
	}

	@Override
	public ProcessResponse fetchSummaryByProcessId(ProcessRequest processRequest)
	{
		return getProcessResponseDefault();
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
	private ProcessResponse insertProcessFromEcoMode(InquiryPaginationRequest request,
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
	private ProcessResponse insertProcessFromSmartpoint(InquiryPaginationRequest request,
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
	private ProcessResponse insertProcessFromAnalytics(InquiryPaginationRequest request,
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

	/**
	 * Generate file csv.
	 * 
	 * @param request the request
	 * @return the cSV response
	 */
	@Override
	public CSVResponse generateFileCSV(ProcessCSVRequest request)
	{
		return new CSVResponse();
	}

}