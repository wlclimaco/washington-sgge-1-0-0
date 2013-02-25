package com.sensus.mlc.ecomode.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static com.sensus.mlc.base.util.CSVFileReader.convertCSVFileToSensusModelList;
import static com.sensus.mlc.base.util.CSVFileReader.getRowsAmount;
import static com.sensus.mlc.base.util.LCActionUtil.createMessageWarningOther;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;
import static com.sensus.mlc.base.util.LCHelp.createTagRequest;
import static com.sensus.mlc.base.util.LCHelp.generateProcess;
import static com.sensus.mlc.base.util.LCHelp.treatReturnFromBCL;
import static com.sensus.mlc.ecomode.util.LCEcoModeUtil.calculateEcoMode;
import static com.sensus.mlc.ecomode.util.LCEcoModeUtil.calculateEcomodeBaseline;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.ResultsSetInfo;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.ecomode.bcl.IEcoModeBCL;
import com.sensus.mlc.ecomode.dac.IEcoModeDAC;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.EcoModeOrderByEnum;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.tag.bcl.ITagBCL;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tenant.bcl.ITenantBCL;

/**
 * The Class EcoModeBCLImpl.
 */
public class EcoModeBCLImpl implements IEcoModeBCL
{
	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(EcoModeBCLImpl.class);

	/** The Constant DEFAULT_PROCESS_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_ECOMODE_BCL_EXCEPTION_MSG = "sensus.mlc.ecomodebclimpl.defaultexception";

	/** The Constant SUCCESS_IMPORT_ECOMODE. */
	private static final String SUCCESS_IMPORT_ECOMODE = "sensus.mlc.mlc_action.success.import_ecomode";

	/** The Constant ERROR_IMPORT_ECOMODE. */
	private static final String ERROR_IMPORT_ECOMODE = "sensus.mlc.mlc_action.error.import_ecomode";

	/** The Constant INCORRECT_IMPORT_ECOMODE. */
	private static final String INCORRECT_IMPORT_ECOMODE = "sensus.mlc.mlc_action.incorrect.import_ecomode";

	/** The Constant SUCCESS_UPD_ECOMODE_BY_POLE_ID. */
	private static final String SUCCESS_UPD_ECOMODE_BY_POLE_ID =
			"sensus.mlc.mlc_action.success.upd_ecomode_by_poleid";

	/** The Constant ERROR_UPD_ECOMODE_BY_POLE_ID. */
	private static final String ERROR_UPD_ECOMODE_BY_POLE_ID =
			"sensus.mlc.mlc_action.error.upd_ecomode_by_poleid";

	private static final String UPD_ECOMODE_LAST_CONSUMPTION_MISSING =
			"sensus.mlc.mlc_action.info.upd_ecomode_last_consumption_missing";

	/** The Constant CSV_ECOMODE_COLUMNS. */
	private static final String[] CSV_ECOMODE_COLUMNS = {"poleId", "replacedWattage", "replacedType"};

	/** The eco mode dac. */
	private IEcoModeDAC ecoModeDAC; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The tenant bcl. */
	private ITenantBCL tenantBCL; // injected by Spring through setter

	/** The system id. */
	private String systemId; // injected by Spring through setter

	/**
	 * Gets the eco mode dac.
	 *
	 * @return the eco mode dac
	 */
	public IEcoModeDAC getEcoModeDAC()
	{
		return this.ecoModeDAC;
	}

	/**
	 * Sets the eco mode dac.
	 *
	 * @param ecoModeDAC the new eco mode dac
	 */
	public void setEcoModeDAC(IEcoModeDAC ecoModeDAC)
	{
		this.ecoModeDAC = ecoModeDAC;
	}

	/**
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return this.processBCL;
	}

	/**
	 * Sets the process bcl.
	 *
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the tag bcl.
	 *
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return this.tagBCL;
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
	 * Gets the tenant bcl.
	 *
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return this.tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 *
	 * @param tenantBCL the new tenant bcl
	 */
	public void setTenantBCL(ITenantBCL tenantBCL)
	{
		this.tenantBCL = tenantBCL;
	}

	/**
	 * Gets the system id.
	 *
	 * @return the system id
	 */
	public String getSystemId()
	{
		return this.systemId;
	}

	/**
	 * Sets the system id.
	 *
	 * @param systemId the new system id
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#upsertEcoMode(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest)
	 */

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#importLightConsumptionsFromFileCSV(com.sensus.mlc.ecomode.model.request
	 * .InquiryEcoModeRequest)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public InternalResponse importEcoModeBaselineFromFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		File csvFile = ecoModeRequest.getEcoModeCSVImport();
		List<EcoModeBaseline> ecoModeList = (List<EcoModeBaseline>)convertCSVFileToSensusModelList(
				csvFile,
				EcoModeBaseline.class,
				CSV_ECOMODE_COLUMNS);

		List<Light> lightWithFailure = new ArrayList<Light>();

		ecoModeRequest.setInternalProcessing(true);
		InternalResultsResponse<EcoModeBaseline> response = new InternalResultsResponse<EcoModeBaseline>();

		for (EcoModeBaseline ecoModeBaseline : ecoModeList)
		{
			ecoModeRequest.getEcoModeBaselineList().clear();
			ecoModeRequest.addEcoModeBaseline(ecoModeBaseline);
			upsertEcoModeToImportCSV(ecoModeRequest, response, lightWithFailure);
		}

		int invalidEcoModeAmount = ecoModeRequest.getInvalidEcoModeAmount();
		ecoModeRequest.setInvalidEcoModeAmount((invalidEcoModeAmount + getRowsAmount(csvFile)) - ecoModeList.size());
		ecoModeRequest.setEcoModeBaselineList(response.getResultsList());
		ecoModeRequest.setInternalProcessing(false);
		ecoModeRequest.getSearchLight().getSearchParameters().clear();

		insertSmartpointToTags(ecoModeRequest, response);
		insertProcessToImportCSVFile(lightWithFailure, ecoModeRequest, response);

		ResultsSetInfo resultsSetInfo = response.getResultsSetInfo();
		setMessagesToResponse(resultsSetInfo.getStartRow(), resultsSetInfo.getEndRow(), ecoModeRequest, response);
		return response;
	}

	/**
	 * Calculate retroactive ecomode.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 */
	private void calculateRetroactiveEcomode(InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response)
	{
		EcoModeBaseline ecoModeBaseline = ecoModeRequest.getFirstEcoModeBaseline();
		if (!ecoModeBaseline.isCalculateRetroactiveEcomode())
		{
			return;
		}

		// Reset filter to find all consumptions by light

		ecoModeRequest.setPageSize(0);
		ecoModeRequest.setInitialDate(null);
		ecoModeRequest.setEndDate(null);
		setOrderByDefault(ecoModeRequest);

		InternalResultsResponse<LightConsumption> responseConsumptions =
				fetchLightConsumptionsByLightId(ecoModeRequest);

		// Calculate eco-mode to all consumptions
		List<LightConsumption> consumptions = responseConsumptions.getResultsList();
		Calendar calendar = Calendar.getInstance();
		Date currentDay = SensusDateUtil.truncateTime(calendar.getTime());
		currentDay = LCDateUtil.convertDateToDefaultUTC(currentDay);
		for (LightConsumption consumption : consumptions)
		{
			if (currentDay.equals(consumption.getDay()))
			{
				continue;
			}

			calendar.setTime(consumption.getDay());
			Double calculateEcomodeBaseline = calculateEcomodeBaseline(ecoModeBaseline, calendar);
			consumption.setEcomodeBaseline(calculateEcomodeBaseline);

			Double ecoMode = calculateEcoMode(consumption);
			consumption.setEcoMode(ecoMode);
			ecoModeRequest.addLightConsumption(consumption);
		}

		// Update on the database
		InternalResponse internalResponse = getEcoModeDAC().updateLightConsumptions(ecoModeRequest);
		if (internalResponse.isInError())
		{
			response.setStatus(internalResponse.getStatus());
			return;
		}

		ecoModeBaseline.setCalculateRetroactiveEcomode(false);
		response.setStatus(getEcoModeDAC().updateCalculationRetroactiveEcomode(ecoModeRequest).getStatus());
	}

	/**
	 * Upsert eco mode to import csv.
	 *
	 * @param ecoModeBaseline the eco mode baseline
	 * @param ecoModeSuccessAmount the eco mode success amount
	 * @param ecoModeErrorAmount the eco mode error amount
	 * @param lightWithFailure the light with failure
	 * @param response the response
	 */
	private void upsertEcoModeToImportCSV(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response,
			List<Light> lightWithFailure)
	{
		InternalResultsResponse<EcoModeBaseline> ecoModeResponse =
				getEcoModeDAC().fetchEcoModeByPoleId(ecoModeRequest);
		if (!ecoModeResponse.hasResults())
		{
			int invalidEcoModeAmount = ecoModeRequest.getInvalidEcoModeAmount() + 1;
			ecoModeRequest.setInvalidEcoModeAmount(invalidEcoModeAmount);
			return;
		}

		ResultsSetInfo resultsSetInfo = response.getResultsSetInfo();
		int ecoModeSuccessAmount = resultsSetInfo.getStartRow();
		int ecoModeErrorAmount = resultsSetInfo.getEndRow();
		int totalRowsAvailable = resultsSetInfo.getTotalRowsAvailable();

		List<Integer> selectionPaginationIds = new ArrayList<Integer>(ecoModeRequest.getSelectionPaginationIds());
		ecoModeRequest.getSelectionPaginationIds().clear();

		EcoModeBaseline ecoModeBaseline = ecoModeRequest.getFirstEcoModeBaseline();
		for (EcoModeBaseline baseline : ecoModeResponse.getResultsList())
		{
			baseline.setReplacedType(ecoModeBaseline.getReplacedType());
			baseline.setReplacedWattage(ecoModeBaseline.getReplacedWattage());

			ecoModeRequest.getEcoModeBaselineList().clear();
			ecoModeRequest.addEcoModeBaseline(baseline);
			ecoModeResponse = upsertEcoMode(ecoModeRequest);
			if (ecoModeResponse.isInError())
			{
				ecoModeErrorAmount++;

				addUniqueMessages(response, ecoModeResponse);
				continue;
			}

			ecoModeSuccessAmount++;
			response.addResult(baseline);


			baseline.setCalculateRetroactiveEcomode(true);
			response.setStatus(getEcoModeDAC().updateCalculationRetroactiveEcomode(ecoModeRequest).getStatus());
		}

		totalRowsAvailable += ecoModeSuccessAmount;
		resultsSetInfo.setTotalRowsAvailable(totalRowsAvailable);
		resultsSetInfo.setStartRow(ecoModeSuccessAmount);
		resultsSetInfo.setEndRow(ecoModeErrorAmount);
		ecoModeRequest.setSelectionPaginationIds(selectionPaginationIds);
	}

	/**
	 * Adds the unique messages.
	 *
	 * @param response the response
	 * @param ecoModeResponse the eco mode response
	 */
	private void addUniqueMessages(InternalResultsResponse<EcoModeBaseline> response,
			InternalResultsResponse<EcoModeBaseline> ecoModeResponse)
	{
		List<MessageInfo> ecoModeResponseMessages = ecoModeResponse.getMessageInfoList();
		if (isNullOrEmpty(ecoModeResponseMessages))
		{
			return;
		}

		List<MessageInfo> responseMessages = response.getMessageInfoList();
		if (isNullOrEmpty(responseMessages))
		{
			response.addMessages(ecoModeResponseMessages);
			return;
		}

		List<MessageInfo> messages = new ArrayList<MessageInfo>();
		for (MessageInfo messageInfo : ecoModeResponseMessages)
		{
			for (MessageInfo message : responseMessages)
			{
				if (!messageInfo.getCode().equals(message.getCode()))
				{
					messages.add(messageInfo);
				}
			}
		}
		response.addMessages(messages);
	}



	/**
	 * Insert smartpoint to tags.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 */
	private void insertSmartpointToTags(InquiryEcoModeRequest ecoModeRequest, InternalResponse response)
	{
		List<EcoModeBaseline> ecoModeBaselineList = ecoModeRequest.getEcoModeBaselineList();
		List<Tag> tags = ecoModeRequest.getTags();
		if (isNullOrEmpty(ecoModeBaselineList) || isNullOrEmpty(tags))
		{
			return;
		}

		TagRequest tagRequest = createTagRequest(ecoModeRequest);
		tagRequest.setTags(tags);

		InternalResponse tagResponse = getTagBCL().insertSmartPointToTag(tagRequest);
		if (tagResponse.isInError())
		{
			response.addMessages(tagResponse.getMessageInfoList());
			response.setStatus(tagResponse.getStatus());
		}
	}

	/**
	 * Sets the messages to response.
	 *
	 * @param ecoModeSuccessAmount the eco mode success amount
	 * @param ecoModeErrorAmount the eco mode error amount
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 */
	private void setMessagesToResponse(
			Integer ecoModeSuccessAmount,
			Integer ecoModeErrorAmount,
			InquiryEcoModeRequest ecoModeRequest,
			InternalResponse response)
	{
		if (ecoModeRequest.isInternalProcessing())
		{
			return;
		}

		setSuccessMessage(ecoModeRequest, ecoModeSuccessAmount, response);
		setErrorMessage(ecoModeRequest, ecoModeErrorAmount, response);
		setIncorrectMessage(ecoModeRequest.getInvalidEcoModeAmount(), response);
	}

	/**
	 * Sets the success message.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param ecoModeSuccessAmount the eco mode success amount
	 * @param response the response
	 */
	private void setSuccessMessage(InquiryEcoModeRequest ecoModeRequest, Integer ecoModeSuccessAmount,
			InternalResponse response)
	{
		if (isNullOrZero(ecoModeSuccessAmount))
		{
			return;
		}

		List<MessageInfo> messages = response.getMessageInfoList();
		if (isNull(ecoModeRequest.getEcoModeCSVImport()) && (ecoModeSuccessAmount == 1))
		{
			EcoModeBaseline ecoModeBaseline = ecoModeRequest.getFirstEcoModeBaseline();
			Message message = new Message(SUCCESS_UPD_ECOMODE_BY_POLE_ID, MessageSeverity.Info,
					MessageLevel.Other, new Object[] {ecoModeBaseline.getReplacedType(),
							ecoModeBaseline.getReplacedWattage()});

			messages.add(message.getMessageInfo());
			return;
		}

		Message message = new Message(SUCCESS_IMPORT_ECOMODE, MessageSeverity.Info,
				MessageLevel.Other, new Object[] {ecoModeSuccessAmount});

		messages.add(message.getMessageInfo());
	}

	/**
	 * Sets the error message.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param ecoModeErrorAmount the eco mode error amount
	 * @param response the response
	 */
	private void setErrorMessage(InquiryEcoModeRequest ecoModeRequest, Integer ecoModeErrorAmount,
			InternalResponse response)
	{
		if (isNullOrZero(ecoModeErrorAmount))
		{
			return;
		}

		List<MessageInfo> messages = response.getMessageInfoList();
		if (isNull(ecoModeRequest.getEcoModeCSVImport()) && (ecoModeErrorAmount == 1))
		{
			EcoModeBaseline ecoModeBaseline = ecoModeRequest.getFirstEcoModeBaseline();
			Message message = new Message(ERROR_UPD_ECOMODE_BY_POLE_ID, MessageSeverity.Warning,
					MessageLevel.Other, new Object[] {ecoModeBaseline.getReplacedType(),
							ecoModeBaseline.getReplacedWattage()});

			messages.add(message.getMessageInfo());
			return;
		}

		Message message = new Message(ERROR_IMPORT_ECOMODE, MessageSeverity.Warning,
				MessageLevel.Other, new Object[] {ecoModeErrorAmount});

		messages.add(message.getMessageInfo());
		return;
	}

	/**
	 * Sets the incorrect message.
	 *
	 * @param lightsWithProblem the lights with problem
	 * @param response the response
	 */
	private void setIncorrectMessage(Integer lightsWithProblem, InternalResponse response)
	{
		if (isNullOrZero(lightsWithProblem))
		{
			return;
		}

		Message message = new Message(INCORRECT_IMPORT_ECOMODE, MessageSeverity.Error,
				MessageLevel.Other, new Object[] {lightsWithProblem});

		List<MessageInfo> messages = response.getMessageInfoList();
		messages.add(message.getMessageInfo());
	}

	/**
	 * Insert process.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 * @param actionType the action type
	 * @return the internal response
	 */
	private InternalResponse insertProcess(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response,
			LCActionTypeEnum actionType)
	{
		return this.insertProcess(ecoModeRequest, response, actionType, null);
	}

	/**
	 * Insert process.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param actionType the action type
	 * @return the internal response
	 */
	private InternalResponse insertProcess(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response,
			LCActionTypeEnum actionType,
			List<Light> lightWithFailure)
	{
		LCAction action = new LCAction(actionType);
		action.setActionParameters(Arrays.asList(new LCActionParameter(PropertyEnum.ECOMODE)));

		Integer lightsTotal = response.getResultsSetInfo().getTotalRowsAvailable();
		Process process = generateProcess(false, action, lightsTotal);
		process.setIsProcessComplete(true);
		process.setEndTime(getNewDateUTC());

		ProcessRequest processRequest = createProcessRequest(ecoModeRequest, process);
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						lightWithFailure,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_WITH_FAILURE));

		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process to complete.
	 *
	 * @param inquiryEcoModeRequest the inquiry eco mode request
	 * @param inquiryEcoModeResponse the inquiry eco mode response
	 * @return the internal response
	 */
	private InternalResponse setProcessToComplete(InquiryEcoModeRequest inquiryEcoModeRequest,
			InquiryEcoModeResponse inquiryEcoModeResponse)
	{
		Process process = new Process();
		process.setId(inquiryEcoModeRequest.getProcessId());

		ProcessRequest processRequest = new ProcessRequest(inquiryEcoModeRequest.getUserContext());
		processRequest.setProcess(process);

		InternalResultsResponse<Process> responseProcess = getProcessBCL().fetchProcessById(processRequest);
		if (responseProcess.isInError())
		{
			return responseProcess;
		}

		// update process as completed
		processRequest.getProcessList().clear();
		processRequest.setProcess(responseProcess.getFirstResult());
		processRequest.getProcess().setIsProcessComplete(true);
		processRequest.getProcess().setEndTime(getNewDateUTC());

		InternalResponse response = getProcessBCL().updateProcess(processRequest);
		inquiryEcoModeResponse.setOperationSuccess(!response.isInError());
		return response;
	}

	/**
	 * Insert process to upsert eco mode.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 */
	private void insertProcessToUpsertEcoMode(InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response)
	{
		if (ecoModeRequest.isInternalProcessing() || response.isInError())
		{
			return;
		}

		InternalResponse processResponse = this.insertProcess(ecoModeRequest, response, LCActionTypeEnum.CONFIGURATION);
		if (processResponse.isInError())
		{
			response.addMessages(processResponse.getMessageInfoList());
			response.setStatus(processResponse.getStatus());
		}
	}

	/**
	 * Insert process to import csv file.
	 *
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 */
	private void insertProcessToImportCSVFile(
			List<Light> lightWithFailure,
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response)
	{
		InternalResponse processResponse =
				this.insertProcess(ecoModeRequest, response, LCActionTypeEnum.IMPORT_CSV_FILE, lightWithFailure);
		if (processResponse.isInError())
		{
			response.addMessages(processResponse.getMessageInfoList());
			response.setStatus(processResponse.getStatus());
		}
	}

	/**
	 * Sets the light id.
	 *
	 * @param ecoModeRequest the new light id
	 */
	private void setLightId(InquiryEcoModeRequest ecoModeRequest)
	{
		if (isNullOrEmpty(ecoModeRequest.getSelectionPaginationIds()))
		{
			return;
		}

		Light light = new Light();
		light.setId(ecoModeRequest.getSelectionPaginationIds().get(0));
		ecoModeRequest.setLight(light);
	}

	/**
	 * Sets the order by default.
	 *
	 * @param ecoModeRequest the new order by default
	 */
	private void setOrderByDefault(InquiryEcoModeRequest ecoModeRequest)
	{
		if (!isNullOrEmpty(ecoModeRequest.getSortExpression()))
		{
			ecoModeRequest.setSortExpression(ecoModeRequest.getSortExpression().trim());
			return;
		}
		SortExpression sortExpression = new SortExpression(EcoModeOrderByEnum.DATE.getValue(), Direction.Descending);
		ecoModeRequest.setSortExpression(sortExpression.toString());
	}

	/**
	 * Persist log info.
	 *
	 * @param logText the log text
	 */
	private void persistLogInfo(String logText)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(logText);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#fetchCountAllLightsToCalculateEcoMode(com.sensus.mlc.ecomode.model.request
	 * .InquiryEcoModeRequest)
	 */
	@Override
	public Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		return getEcoModeDAC().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#updateAnalyticsData()
	 */
	@Override
	public void updateAnalyticsData()
	{
		getEcoModeDAC().updateAnalyticsData();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#fetchAllLightsToCalculateEcoMode(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<EcoModeBaseline> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		return getEcoModeDAC().fetchAllLightsToCalculateEcoMode(ecoModeRequest);
	}

	@Override
	public InternalResultsResponse<EcoModeBaseline> upsertEcoMode(
			InquiryEcoModeRequest ecoModeRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryEcoModeResponse generateFileCSV(
			InquiryEcoModeRequest ecoModeRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsToChart(
			InquiryEcoModeRequest ecoModeRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
