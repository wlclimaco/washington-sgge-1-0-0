package com.sensus.lc.ecomode.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static com.sensus.lc.base.util.CSVFileReader.convertCSVFileToSensusModelList;
import static com.sensus.lc.base.util.CSVFileReader.getRowsAmount;
import static com.sensus.lc.base.util.LCActionUtil.createMessageWarningOther;
import static com.sensus.lc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.lc.base.util.LCHelp.createProcessRequest;
import static com.sensus.lc.base.util.LCHelp.createTagRequest;
import static com.sensus.lc.ecomode.util.EcoModeUtil.calculateEcoMode;
import static com.sensus.lc.ecomode.util.EcoModeUtil.calculateEcomodeBaseline;
import static com.sensus.lc.process.util.ProcessUtil.createProcessItemWithFailure;
import static com.sensus.lc.process.util.ProcessUtil.generateProcess;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.supercsv.cellprocessor.Optional;

import com.sensus.common.csv.CSVColumn;
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
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.ConvertEcoMode;
import com.sensus.lc.base.util.LCCSVUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.ecomode.bcl.IEcoModeBCL;
import com.sensus.lc.ecomode.dac.IEcoModeDAC;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.ecomode.model.EcoModeOrderByEnum;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.util.ConvertDateToDatePattern;
import com.sensus.lc.ecomode.util.ConvertWattageToKWattage;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.SearchParameter;
import com.sensus.lc.light.model.request.CSVRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.tag.bcl.ITagBCL;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.tenant.bcl.ITenantBCL;

/**
 * The Class EcoModeBCLImpl.
 */
public class EcoModeBCLImpl implements IEcoModeBCL
{

	/** The LOG. */
	private static final Log LOG = LogFactory.getLog(EcoModeBCLImpl.class);

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
	private static final String[] CSV_ECOMODE_COLUMNS = {
			"poleId",
			"ecoModeBaseline.replacedWattage",
			"ecoModeBaseline.replacedType"};

	private static final String DAY = "day";

	private static final String BASE_LINE = "ecomodeBaseline";

	private static final String CONSUMPTION = "consumption";

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

	private List<CSVColumn> ecoModeExportColumns;

	/**
	 * Gets the eco mode dac.
	 * 
	 * @return the eco mode dac
	 */
	public IEcoModeDAC getEcoModeDAC()
	{
		return ecoModeDAC;
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
		return processBCL;
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
	 * Gets the tenant bcl.
	 * 
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return tenantBCL;
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
		return systemId;
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

	/**
	 * Gets the eco mode export columns.
	 * 
	 * @return the eco mode export columns
	 */
	public List<CSVColumn> getEcoModeExportColumns()
	{
		return ecoModeExportColumns;
	}

	/**
	 * Sets the eco mode export columns.
	 * 
	 * @param ecoModeExportColumns the new eco mode export columns
	 */
	public void setEcoModeExportColumns(List<CSVColumn> ecoModeExportColumns)
	{
		this.ecoModeExportColumns = ecoModeExportColumns;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#upsertEcoMode(com.sensus.mlc.ecomode.model.request.EcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Light> upsertEcoMode(EcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		List<Light> lights = new ArrayList<Light>(ecoModeRequest.getLights());
		List<SearchParameter> parameters = new ArrayList<SearchParameter>();
		Set<Integer> selectionPaginationIds = new HashSet<Integer>();
		Integer ecoModeErrorAmount = 0;

		for (Light light : lights)
		{
			ecoModeRequest.getLights().clear();
			ecoModeRequest.addLight(light);

			response = getDataFromLight(ecoModeRequest);
			if (response.isInError())
			{
				ecoModeErrorAmount++;
				continue;
			}

			light = response.getFirstResult();
			applyEcoMode(light);

			InternalResultsResponse<Light> ecoModeResponse = getEcoModeDAC().upsertEcoMode(ecoModeRequest);
			calculateRetroactiveEcomode(ecoModeRequest, ecoModeResponse);

			if (ecoModeResponse.isInError())
			{
				ecoModeErrorAmount++;
				addUniqueMessages(response, ecoModeResponse);
				continue;
			}

			parameters.add(new SearchParameter(PropertyEnum.POLE_ID, light.getPoleId()));
			if (isNull(ecoModeRequest.getEcoModeCSVImport()))
			{
				selectionPaginationIds.add(light.getId());
			}
		}

		int ecoModeSuccessAmount = selectionPaginationIds.size();
		ecoModeRequest.setSearchLight(new SearchLight(parameters));
		ecoModeRequest.setSelectionPaginationIds(new ArrayList<Integer>(selectionPaginationIds));
		response.getResultsSetInfo().setTotalRowsAvailable(ecoModeSuccessAmount);
		insertProcessToUpsertEcoMode(ecoModeRequest, response);

		setMessagesToResponse(ecoModeSuccessAmount, ecoModeErrorAmount, ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		setLightId(ecoModeRequest);
		return getEcoModeDAC().fetchLightConsumptionsToChart(ecoModeRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#fetchLightConsumptionsByLightId(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		setLightId(ecoModeRequest);
		setOrderByDefault(ecoModeRequest);
		return getEcoModeDAC().fetchLightConsumptionsByLightId(ecoModeRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#generateFileCSV(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest
	 * )
	 */
	@Override
	public CSVInternalResponse generateFileCSV(EcoModeCSVRequest ecoModeRequest)
	{
		persistLogInfo("Generating CSV file for eco-mode");

		InquiryEcoModeRequest inquiryEcoModeRequest = ecoModeRequest.getInquiryEcoModeRequest();
		CSVInternalResponse internalResponse = new CSVInternalResponse(ecoModeRequest.getFileName());
		setLightId(inquiryEcoModeRequest);
		inquiryEcoModeRequest.setUserContext(ecoModeRequest.getUserContext());
		InternalResultsResponse<Light> lightResponse = getEcoModeDAC().fetchEcoModeByLight(inquiryEcoModeRequest);
		if (!lightResponse.hasResults())
		{
			internalResponse.merge(lightResponse);
			return internalResponse;
		}

		// no pagination
		inquiryEcoModeRequest.setPageSize(0);
		InternalResultsResponse<Consumption> consumptionsResponse =
				fetchLightConsumptionsByLightId(inquiryEcoModeRequest);

		Status responseStatus = consumptionsResponse.getStatus();
		if (responseStatus != Status.OperationSuccess && responseStatus != Status.NoRowsFoundError)
		{
			internalResponse.merge(consumptionsResponse);
			return internalResponse;
		}

		inquiryEcoModeRequest.setLight(lightResponse.getFirstResult());

		ecoModeRequest.setInquiryEcoModeRequest(inquiryEcoModeRequest);
		ecoModeRequest.setUserContext(inquiryEcoModeRequest.getUserContext());

		prepareCSV(getEcoModeExportColumns(), ecoModeRequest);

		LCCSVUtil.processCSVRequest(ecoModeRequest, internalResponse, consumptionsResponse.getResultsList(),
				getEcoModeExportColumns());

		if (!internalResponse.isInError())
		{
			if (ecoModeRequest.getProcessId() != null)
			{
				LCCSVUtil.handleCSVResponse(ecoModeRequest, internalResponse, getProcessBCL());
			}
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#importEcoModeBaselineFromFileCSV(com.sensus.mlc.ecomode.model.request.
	 * EcoModeRequest)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public InternalResultsResponse<Light> importEcoModeBaselineFromFileCSV(EcoModeRequest ecoModeRequest)
	{
		File csvFile = ecoModeRequest.getEcoModeCSVImport();
		List<Light> lights = (List<Light>)convertCSVFileToSensusModelList(
				csvFile,
				Light.class,
				CSV_ECOMODE_COLUMNS,
				ecoModeRequest.isContainFileHeader());

		List<Light> lightWithFailure = new ArrayList<Light>();

		ecoModeRequest.setInternalProcessing(true);
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		for (Light light : lights)
		{
			ecoModeRequest.getLights().clear();
			ecoModeRequest.addLight(light);
			upsertEcoModeToImportCSV(ecoModeRequest, response, lightWithFailure);
		}

		int invalidEcoModeAmount = ecoModeRequest.getInvalidEcoModeAmount();
		ecoModeRequest.setInvalidEcoModeAmount(invalidEcoModeAmount + getRowsAmount(csvFile) - lights.size());
		ecoModeRequest.setLights(response.getResultsList());
		ecoModeRequest.setInternalProcessing(false);
		ecoModeRequest.getSearchLight().getSearchParameters().clear();

		insertLightToTags(ecoModeRequest, response);
		insertProcessToImportCSVFile(lightWithFailure, ecoModeRequest, response);

		ResultsSetInfo resultsSetInfo = response.getResultsSetInfo();
		setMessagesToResponse(resultsSetInfo.getStartRow(), resultsSetInfo.getEndRow(), ecoModeRequest, response);
		return response;
	}

	private InternalResultsResponse<Light> getDataFromLight(EcoModeRequest request)
	{
		// Case be internal processing then is not necessary fetch informations
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		if (request.isInternalProcessing())
		{
			response.addResult(request.getFirstLight());
			return response;
		}

		InquiryEcoModeRequest ecoModeRequest = new InquiryEcoModeRequest(request.getUserContext());
		ecoModeRequest.setLight(request.getFirstLight());

		response = getEcoModeDAC().fetchEcoModeByLight(ecoModeRequest);
		if (response.isInError())
		{
			return response;
		}

		if (isNull(response.getFirstResult().getLastConsumption()))
		{
			Message message = createMessageWarningOther(UPD_ECOMODE_LAST_CONSUMPTION_MISSING);
			List<MessageInfo> messages = new ArrayList<MessageInfo>();
			messages.add(message.getMessageInfo());
			response.addMessages(messages);
		}

		// Change ecomode configuration
		response.getFirstResult().setEcoModeBaseline(request.getFirstLight().getEcoModeBaseline());

		// Reset light from request
		request.getLights().clear();
		request.addLight(response.getFirstResult());
		return response;
	}

	/**
	 * Calculate retroactive ecomode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 */
	private void calculateRetroactiveEcomode(EcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response)
	{
		Light light = ecoModeRequest.getFirstLight();
		EcoModeBaseline ecoModeBaseline = light.getEcoModeBaseline();
		if ((isNull(ecoModeBaseline)
				|| !ecoModeBaseline.isCalculateRetroactiveEcomode()
				|| !isNull(ecoModeRequest.getEcoModeCSVImport()))
				&& !ecoModeRequest.isReprocessLight())
		{
			return;
		}

		// Reset filter to find all consumptions by light
		InquiryEcoModeRequest inquiryEcoModeRequest = new InquiryEcoModeRequest(ecoModeRequest.getUserContext());
		inquiryEcoModeRequest.setLight(light);

		InternalResultsResponse<Consumption> responseConsumptions =
				getEcoModeDAC().fetchAllLightConsumptionsByLightId(inquiryEcoModeRequest);

		// Calculate eco-mode to all consumptions
		List<Consumption> consumptions = responseConsumptions.getResultsList();
		Calendar calendar = Calendar.getInstance();
		Date currentDay = SensusDateUtil.truncateTime(calendar.getTime());
		for (Consumption consumption : consumptions)
		{
			if (currentDay.equals(consumption.getDay()))
			{
				continue;
			}

			calendar.setTime(consumption.getDay());
			Double calculateEcomodeBaseline = calculateEcomodeBaseline(light, calendar);
			consumption.setEcomodeBaseline(calculateEcomodeBaseline);

			Double ecoMode = calculateEcoMode(consumption);
			consumption.setEcoMode(ecoMode);

			light.setLastConsumption(consumption);
			InternalResponse internalResponse = getEcoModeDAC().updateLightConsumption(ecoModeRequest);
			if (internalResponse.isInError())
			{
				response.setStatus(internalResponse.getStatus());
			}
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
			EcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response,
			List<Light> lightWithFailure)
	{
		InquiryEcoModeRequest request = new InquiryEcoModeRequest(ecoModeRequest.getUserContext());
		request.setLight(ecoModeRequest.getFirstLight());

		InternalResultsResponse<Light> ecoModeResponse = getEcoModeDAC().fetchEcoModeByLight(request);
		if (!ecoModeResponse.hasResults())
		{
			ecoModeRequest.addInvalidEcoModeAmount(1);
			return;
		}

		ResultsSetInfo resultsSetInfo = response.getResultsSetInfo();
		Integer ecoModeErrorAmount = resultsSetInfo.getEndRow();
		Integer totalRowsAvailable = resultsSetInfo.getTotalRowsAvailable();

		Set<Integer> selectionPaginationIds = new HashSet<Integer>(ecoModeRequest.getSelectionPaginationIds());
		ecoModeRequest.getSelectionPaginationIds().clear();

		Light lightBase = ecoModeRequest.getFirstLight();
		EcoModeBaseline ecoModeBaseline = lightBase.getEcoModeBaseline();
		for (Light light : ecoModeResponse.getResultsList())
		{
			light.getEcoModeBaseline().setReplacedType(ecoModeBaseline.getReplacedType());
			light.getEcoModeBaseline().setReplacedWattage(ecoModeBaseline.getReplacedWattage());

			ecoModeRequest.getLights().clear();
			ecoModeRequest.addLight(light);
			ecoModeResponse = upsertEcoMode(ecoModeRequest);
			if (ecoModeResponse.isInError())
			{
				ecoModeErrorAmount++;
				lightWithFailure.add(light);
				addUniqueMessages(response, ecoModeResponse);
				continue;
			}

			selectionPaginationIds.add(light.getId());

			light.getEcoModeBaseline().setCalculateRetroactiveEcomode(true);
			response.setStatus(getEcoModeDAC().updateCalculationRetroactiveEcomode(ecoModeRequest).getStatus());
			response.addResult(light);
		}

		totalRowsAvailable += selectionPaginationIds.size();
		resultsSetInfo.setTotalRowsAvailable(totalRowsAvailable);
		resultsSetInfo.setStartRow(selectionPaginationIds.size());
		resultsSetInfo.setEndRow(ecoModeErrorAmount);
		ecoModeRequest.setSelectionPaginationIds(new ArrayList<Integer>(selectionPaginationIds));
	}

	/**
	 * Adds the unique messages.
	 * 
	 * @param response the response
	 * @param ecoModeResponse the eco mode response
	 */
	private void addUniqueMessages(InternalResponse response,
			InternalResponse ecoModeResponse)
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
	 * Apply eco mode.
	 * 
	 * @param light the light
	 */
	private void applyEcoMode(Light light)
	{
		if (isNull(light.getLastConsumption()))
		{
			light.setLastConsumption(new Consumption());
			light.getLastConsumption().setDay(getNewDateUTC());
		}

		Consumption lastConsumption = light.getLastConsumption();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastConsumption.getDay());

		Double calculateEcomodeBaseline = calculateEcomodeBaseline(light, calendar);
		lastConsumption.setEcomodeBaseline(calculateEcomodeBaseline);

		Double ecoMode = calculateEcoMode(lastConsumption);
		light.setEcoMode(ecoMode);
		lastConsumption.setEcoMode(ecoMode);
	}

	private void insertLightToTags(EcoModeRequest ecoModeRequest, InternalResponse response)
	{
		List<Tag> tags = ecoModeRequest.getTags();
		if (isNullOrEmpty(ecoModeRequest.getLights()) || isNullOrEmpty(tags))
		{
			return;
		}

		TagRequest tagRequest = createTagRequest(ecoModeRequest);
		tagRequest.setTags(tags);

		InternalResponse tagResponse = getTagBCL().insertLightToTag(tagRequest);
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
			EcoModeRequest ecoModeRequest,
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
	private void setSuccessMessage(EcoModeRequest ecoModeRequest, Integer ecoModeSuccessAmount,
			InternalResponse response)
	{
		if (isNullOrZero(ecoModeSuccessAmount))
		{
			return;
		}

		List<MessageInfo> messages = response.getMessageInfoList();
		if (isNull(ecoModeRequest.getEcoModeCSVImport()) && ecoModeSuccessAmount == 1)
		{
			Light light = ecoModeRequest.getFirstLight();
			EcoModeBaseline ecoModeBaseline = light.getEcoModeBaseline();
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
	private void setErrorMessage(EcoModeRequest ecoModeRequest, Integer ecoModeErrorAmount,
			InternalResponse response)
	{
		if (isNullOrZero(ecoModeErrorAmount))
		{
			return;
		}

		List<MessageInfo> messages = response.getMessageInfoList();
		if (isNull(ecoModeRequest.getEcoModeCSVImport()) && ecoModeErrorAmount == 1)
		{
			Light light = ecoModeRequest.getFirstLight();
			EcoModeBaseline ecoModeBaseline = light.getEcoModeBaseline();
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
	 * @param lightWithFailure the light with failure
	 * @return the internal response
	 */
	private InternalResponse insertProcess(
			EcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response,
			LCActionTypeEnum actionType,
			List<Light> lightWithFailure)
	{
		LCAction action = new LCAction(actionType);
		action.setActionParameters(Arrays.asList(new LCActionParameter(PropertyEnum.ECOMODE)));

		Process process = generateProcess(false, action, ecoModeRequest.getLights(), ProcessItemStatusEnum.SUCCESS);
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
	 * Insert process to upsert eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 */
	private void insertProcessToUpsertEcoMode(EcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response)
	{
		if (ecoModeRequest.isInternalProcessing() || response.isInError())
		{
			return;
		}

		InternalResponse processResponse =
				insertProcess(ecoModeRequest, response, LCActionTypeEnum.CONFIGURATION, null);
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
			EcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response)
	{
		InternalResponse processResponse =
				insertProcess(ecoModeRequest, response, LCActionTypeEnum.IMPORT_CSV_FILE, lightWithFailure);
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
			return;
		}
		ecoModeRequest.addSortExpressions(new SortExpression(EcoModeOrderByEnum.DATE.getValue(), Direction.Descending));
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
	public InternalResultsResponse<Light> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		return getEcoModeDAC().fetchAllLightsToCalculateEcoMode(ecoModeRequest);
	}

	/**
	 * Prepare csv.
	 * 
	 * @param columnsToExport the columns to export
	 * @param request the request
	 */
	private static void prepareCSV(List<CSVColumn> columnsToExport, CSVRequest request)
	{
		if (ValidationUtil.isNullOrEmpty(columnsToExport))
		{
			return;
		}

		// look for columns date to apply time zone
		for (CSVColumn col : columnsToExport)
		{
			if (DAY.equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertDateToDatePattern(LCDateUtil.DEFAULT_DATE_FORMAT)));
			}
			else if (BASE_LINE.equals(col.getName()) || CONSUMPTION.equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertWattageToKWattage()));
			}
			else if (LightColumnEnum.ECOMODE.getValue().equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertEcoMode()));
			}
		}
	}

}
