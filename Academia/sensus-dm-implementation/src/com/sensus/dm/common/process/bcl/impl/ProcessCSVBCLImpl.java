package com.sensus.dm.common.process.bcl.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.GenerateCSVFileAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.bcl.IProcessCSVBCL;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.model.CSVProcess;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LCMTypeEnum;

/**
 * The Class ProcessBCLImpl.
 * 
 * @author QAT Global
 * 
 */
public class ProcessCSVBCLImpl implements IProcessCSVBCL
{

	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Log LOG = LogFactory.getLog(ProcessCSVBCLImpl.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant STR_DATE_FORMAT. */
	private static final String STR_DATE_FORMAT = "yyyy-MM-dd HH.mm.ss.SSS";

	/** The Constant STR_CSV. */
	private static final String STR_CSV = ".csv";

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant LOG_GET_ALL_PROC_FILTERED. */
	private static final String LOG_GET_ALL_PROC_FILTERED = "Get all processes filtered finished";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEVICES_TOTAL_COLUMNS_TO_EXPORT. */
	private static final String[] DEVICES_TOTAL_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(), CsvColumnEnum.ADDRESS.getValue()};

	/** The Constant DEVICES_TOTAL_FAIL_COLUMNS_TO_EXPORT. */
	private static final String[] DEVICES_TOTAL_FAIL_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(), CsvColumnEnum.ADDRESS.getValue(),
			CsvColumnEnum.MESSAGE_ERROR.getValue()};

	/** The Constant GROUP_ELECTRIC_COLUMNS_TO_EXPORT. */
	private static final String[] PROCESS_COLUMNS_TO_EXPORT =
	{CsvColumnEnum.ACTION_TYPE.getValue(), CsvColumnEnum.ACTION_NAME.getValue(),
			CsvColumnEnum.EVENT.getValue(), CsvColumnEnum.DEVICES_TOTAL.getValue(),
			CsvColumnEnum.DEVICES_FAILED.getValue(), CsvColumnEnum.REQUESTED_BY.getValue(),
			CsvColumnEnum.START_TIME.getValue(), CsvColumnEnum.STATUS.getValue()};

	/** The Constant DEVICE_HISTORY_COLUMNS_TO_EXPORT. */
	private static final String[] DEVICE_HISTORY_COLUMNS_TO_EXPORT =
	{CsvColumnEnum.ACTION_TYPE.getValue(), CsvColumnEnum.ACTION_NAME.getValue(),
			CsvColumnEnum.EVENT.getValue(), CsvColumnEnum.REQUESTED_BY.getValue(),
			CsvColumnEnum.START_TIME.getValue(), CsvColumnEnum.STATUS.getValue()};

	/** The Constant COMMUNICATION_COLUMNS_TO_EXPORT. */
	private static final String[] COMMUNICATION_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(), CsvColumnEnum.PREMISE_ID.getValue(),
			CsvColumnEnum.ADDRESS.getValue(),
			CsvColumnEnum.PROCESS_ITEM_STATE.getValue(), CsvColumnEnum.MESSAGE_ERROR.getValue()};

	/** The Constant DEMANDRESPONSE_COLUMNS_TO_EXPORT. */
	private static final String[] DEMANDRESPONSE_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(), CsvColumnEnum.PARENT_DEVICE_ID.getValue(),
			CsvColumnEnum.PREMISE_ID.getValue(), CsvColumnEnum.NETWORK_STATUS.getValue(),
			CsvColumnEnum.FULL_PARTICIPATION.getValue(), CsvColumnEnum.PARTICIPATION.getValue(),
			CsvColumnEnum.OPT_OUT.getValue()};

	/** The Constant DEMANDREAD_COLUMNS_TO_EXPORT. */
	private static final String[] DEMANDREAD_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(), CsvColumnEnum.PREMISE_ID.getValue(),
			CsvColumnEnum.READ_VALUE.getValue(), CsvColumnEnum.READ_TIME.getValue()};

	/** The Constant DEMANDREAD_COLUMNS_TO_EXPORT. */
	private static final String[] TAMPER_DETECT_TIME_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(), CsvColumnEnum.PREMISE_ID.getValue(),
			CsvColumnEnum.RELAY.getValue(), CsvColumnEnum.TAMPER_TIME.getValue()};

	/** The Constant DEMAND_RESPONSE_SETUP_TO_EXPORT. */
	private static final String[] DEMAND_RESPONSE_SETUP_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(),
			CsvColumnEnum.RELAY.getValue(), CsvColumnEnum.DEVICE_CLASS.getValue(),
			CsvColumnEnum.ENROLLMENT_GROUP.getValue(), CsvColumnEnum.RANDOMIZE_DURATION.getValue()};

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp file path. */
	private String tempFilePath;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The process summary bcl. */
	private IProcessSummaryBCL processSummaryBCL;

	/** The communication summary columns. */
	private List<CSVColumn> communicationSummaryColumns;

	/** The import summary columns. */
	private List<CSVColumn> importSummaryColumns;

	/** The demand response summary columns. */
	private List<CSVColumn> demandResponseSummaryColumns;

	/** The demand read summary columns. */
	private List<CSVColumn> demandReadSummaryColumns;

	/** The process item list all columns. */
	private List<CSVColumn> processItemListAllColumns;

	/** The process today list all columns. */
	private List<CSVColumn> processTodayListAllColumns;

	/** The tamper detect time list all columns. */
	private List<CSVColumn> tamperDetectTimeListAllColumns;

	/** The demand response setup list all columns. */
	private List<CSVColumn> demandResponseSetupListAllColumns;

	/**
	 * Gets the temp file path.
	 * 
	 * @return the temp file path
	 */
	public String getTempFilePath()
	{
		return tempFilePath;
	}

	/**
	 * Sets the temp file path.
	 * 
	 * @param tempFilePath the new temp file path
	 */
	public void setTempFilePath(String tempFilePath)
	{
		this.tempFilePath = tempFilePath;
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
	 * Gets the process summary bcl.
	 * 
	 * @return the process summary bcl
	 */
	public IProcessSummaryBCL getProcessSummaryBCL()
	{
		return processSummaryBCL;
	}

	/**
	 * Sets the process summary bcl.
	 * 
	 * @param processSummaryBCL the new process summary bcl
	 */
	public void setProcessSummaryBCL(IProcessSummaryBCL processSummaryBCL)
	{
		this.processSummaryBCL = processSummaryBCL;
	}

	/**
	 * Gets the communication summary columns.
	 * 
	 * @return the communication summary columns
	 */
	public List<CSVColumn> getCommunicationSummaryColumns()
	{
		return communicationSummaryColumns;
	}

	/**
	 * Sets the communication summary columns.
	 * 
	 * @param communicationSummaryColumns the new communication summary columns
	 */
	public void setCommunicationSummaryColumns(List<CSVColumn> communicationSummaryColumns)
	{
		this.communicationSummaryColumns = communicationSummaryColumns;
	}

	/**
	 * Gets the demand response summary columns.
	 * 
	 * @return the demand response summary columns
	 */
	public List<CSVColumn> getDemandResponseSummaryColumns()
	{
		return demandResponseSummaryColumns;
	}

	/**
	 * Sets the demand response summary columns.
	 * 
	 * @param demandResponseSummaryColumns the new demand response summary columns
	 */
	public void setDemandResponseSummaryColumns(List<CSVColumn> demandResponseSummaryColumns)
	{
		this.demandResponseSummaryColumns = demandResponseSummaryColumns;
	}

	/**
	 * Gets the demand read summary columns.
	 * 
	 * @return the demand read summary columns
	 */
	public List<CSVColumn> getDemandReadSummaryColumns()
	{
		return demandReadSummaryColumns;
	}

	/**
	 * Sets the demand read summary columns.
	 * 
	 * @param demandReadSummaryColumns the new demand read summary columns
	 */
	public void setDemandReadSummaryColumns(List<CSVColumn> demandReadSummaryColumns)
	{
		this.demandReadSummaryColumns = demandReadSummaryColumns;
	}

	/**
	 * Gets the import summary columns.
	 * 
	 * @return the import summary columns
	 */
	public List<CSVColumn> getImportSummaryColumns()
	{
		return importSummaryColumns;
	}

	/**
	 * Sets the import summary columns.
	 * 
	 * @param importSummaryColumns the new import summary columns
	 */
	public void setImportSummaryColumns(List<CSVColumn> importSummaryColumns)
	{
		this.importSummaryColumns = importSummaryColumns;
	}

	/**
	 * Gets the process item list all columns.
	 * 
	 * @return the process item list all columns
	 */
	public List<CSVColumn> getProcessItemListAllColumns()
	{
		return processItemListAllColumns;
	}

	/**
	 * Sets the process item list all columns.
	 * 
	 * @param processItemListAllColumns the new process item list all columns
	 */
	public void setProcessItemListAllColumns(List<CSVColumn> processItemListAllColumns)
	{
		this.processItemListAllColumns = processItemListAllColumns;
	}

	/**
	 * Gets the process today list all columns.
	 * 
	 * @return the process today list all columns
	 */
	public List<CSVColumn> getProcessTodayListAllColumns()
	{
		return processTodayListAllColumns;
	}

	/**
	 * Sets the process today list all columns.
	 * 
	 * @param processTodayListAllColumns the new process today list all columns
	 */
	public void setProcessTodayListAllColumns(List<CSVColumn> processTodayListAllColumns)
	{
		this.processTodayListAllColumns = processTodayListAllColumns;
	}

	/**
	 * Gets the tamper detect time list all columns.
	 * 
	 * @return the tamper detect time list all columns
	 */
	public List<CSVColumn> getTamperDetectTimeListAllColumns()
	{
		return tamperDetectTimeListAllColumns;
	}

	/**
	 * Sets the tamper detect time list all columns.
	 * 
	 * @param tamperDetectTimeListAllColumns the new tamper detect time list all columns
	 */
	public void setTamperDetectTimeListAllColumns(List<CSVColumn> tamperDetectTimeListAllColumns)
	{
		this.tamperDetectTimeListAllColumns = tamperDetectTimeListAllColumns;
	}

	/**
	 * Gets the demand response setup list all columns.
	 * 
	 * @return the demand response setup list all columns
	 */
	public List<CSVColumn> getDemandResponseSetupListAllColumns()
	{
		return demandResponseSetupListAllColumns;
	}

	/**
	 * Sets the demand response setup list all columns.
	 * 
	 * @param demandResponseSetupListAllColumns the new demand response setup list all columns
	 */
	public void setDemandResponseSetupListAllColumns(List<CSVColumn> demandResponseSetupListAllColumns)
	{
		this.demandResponseSetupListAllColumns = demandResponseSetupListAllColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#insertCSVProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<CSVProcess> insertCSVProcess(InquiryProcessRequest inquiryProcessRequest)
	{

		InternalResultsResponse<CSVProcess> internalResultsResponse = new InternalResultsResponse<CSVProcess>();

		// Create csv name
		SimpleDateFormat format = new SimpleDateFormat(STR_DATE_FORMAT);

		String dateFormated = format.format(new Date());
		String fileName = getTempFilePath() + inquiryProcessRequest.getUserContext().getUserId()
				+ dateFormated + STR_CSV;

		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.GENERATECSVFILEACTION.getValue(), fileName));

		// generate the process as in_process
		DMProcess process =
				DMUtil.generateProcess(false, false, null, new ProcessType(GenerateCSVFileAction.ACTION),
						inquiryProcessRequest.getProcessItems(),
						ProcessStatusEnum.IN_PROCESS, properties);

		// Insert process, if success insert the added process and file name to CSVProcess response
		InternalResultsResponse<DMProcess> response =
				getProcessBCL().insertProcess(
						new ProcessRequest(process, inquiryProcessRequest.getUserContext(), inquiryProcessRequest
								.getServiceTypeEnum(), inquiryProcessRequest.getTenant()));

		if (response.isInError())
		{
			internalResultsResponse.setStatus(response.getStatus());
			internalResultsResponse.addMessages(response.getMessageInfoList());
			return internalResultsResponse;
		}

		// add the result
		internalResultsResponse.getResultsList().add(new CSVProcess(response.getResultsList(), fileName));

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVSummary(com.sensus.dm.common.process.model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> response =
				getProcessBCL()
						.fetchProcessById(
								new ProcessRequest(inquiryProcessRequest.getFirstProcess(), inquiryProcessRequest
										.getUserContext(), inquiryProcessRequest.getProcessItemStatusEnum()));

		if (response.isInError() || !response.hasResults()
				|| ValidationUtil.isNullOrEmpty(response.getFirstResult().getProcessItems()))
		{
			return response;
		}

		if (ProcessItemStatusEnum.FAILED.equals(inquiryProcessRequest.getProcessItemStatusEnum()))
		{
			DMUtil.generateCSV(getProcessItemListAllColumns(), DEVICES_TOTAL_FAIL_COLUMNS_TO_EXPORT,
					response.getFirstResult().getProcessItems(), inquiryProcessRequest, response);
		}
		else
		{

			DMUtil.generateCSV(getProcessItemListAllColumns(), DEVICES_TOTAL_COLUMNS_TO_EXPORT,
					response.getFirstResult().getProcessItems(), inquiryProcessRequest, response);

		}

		// If file csv was not successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVEventHistory(com.sensus.dm.common.process.model
	 * .request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVEventHistory(InquiryProcessRequest inquiryProcessRequest)
	{
		// Get processes and generate file csv with this information
		InternalResultsResponse<DMProcess> responseAllProcess = getProcessBCL().fetchProcesses(inquiryProcessRequest);

		inquiryProcessRequest.setProcesses(responseAllProcess.getResultsList());

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getProcessTodayListAllColumns(), PROCESS_COLUMNS_TO_EXPORT,
				responseAllProcess.getResultsList(), inquiryProcessRequest, response);

		// If file csv was successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVDeviceHistory(com.sensus.dm.common.process.model
	 * .request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDeviceHistory(InquiryProcessRequest inquiryProcessRequest)
	{

		InternalResultsResponse<DMProcess> responseAllProcess = new InternalResultsResponse<DMProcess>();

		// Get processes and generate file csv with this information
		responseAllProcess = getProcessBCL().fetchProcesses(inquiryProcessRequest);

		if (LOG.isDebugEnabled())
		{
			LOG.debug(LOG_GET_ALL_PROC_FILTERED);
		}

		inquiryProcessRequest.setProcesses(responseAllProcess.getResultsList());

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getProcessTodayListAllColumns(), DEVICE_HISTORY_COLUMNS_TO_EXPORT,
				responseAllProcess.getResultsList(), inquiryProcessRequest, response);

		// If file csv was not successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVToday(com.sensus.dm.common.process.model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVToday(InquiryProcessRequest inquiryProcessRequest)
	{
		// Get processes and generate file csv with this information
		InternalResultsResponse<DMProcess> responseAllProcess =
				getProcessBCL().fetchTodayProcesses(inquiryProcessRequest);

		if (LOG.isDebugEnabled())
		{
			LOG.debug(LOG_GET_ALL_PROC_FILTERED);
		}

		inquiryProcessRequest.setProcesses(responseAllProcess.getResultsList());

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getProcessTodayListAllColumns(), PROCESS_COLUMNS_TO_EXPORT,
				responseAllProcess.getResultsList(), inquiryProcessRequest, response);

		// If file csv was not successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#generateFileCSVDemandResponseSummary(com.sensus.dm.common.process.model
	 * .
	 * request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDemandResponseSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> responseResult =
				getProcessSummaryBCL().fetchDemandResponseSummary(
						new ProcessRequest(inquiryProcessRequest.getFirstProcess()));

		if (responseResult.isInError())
		{
			return responseResult;
		}

		DMUtil.generateCSV(getDemandResponseSummaryColumns(), DEMANDRESPONSE_COLUMNS_TO_EXPORT,
				responseResult.getFirstResult().getProcessItems(), inquiryProcessRequest, responseResult);

		if (responseResult.isInError())
		{
			return responseResult;
		}

		// Mark the process as completed.
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVDemandReadSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDemandReadSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> responseResult =
				getProcessBCL().fetchDemandReadPingSummary(
						new ProcessRequest(inquiryProcessRequest.getFirstProcess(), inquiryProcessRequest
								.getUserContext()));

		if (responseResult.isInError())
		{
			return responseResult;
		}

		DMUtil.generateCSV(getDemandReadSummaryColumns(), DEMANDREAD_COLUMNS_TO_EXPORT,
				responseResult.getFirstResult().getProcessItems(), inquiryProcessRequest, responseResult);

		if (responseResult.isInError())
		{
			return responseResult;
		}

		// Mark the process as completed.
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#generateFileCSVImportHanSummary(com.sensus.dm.common.process.model.
	 * request
	 * .
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVImportHanSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> responseResult =
				getProcessBCL().fetchProcessById(
						new ProcessRequest(inquiryProcessRequest.getFirstProcess(), inquiryProcessRequest
								.getUserContext(), ProcessItemStatusEnum.FAILED));

		if (responseResult.isInError())
		{
			return responseResult;
		}

		DMUtil.generateCSV(getImportSummaryColumns(), COMMUNICATION_COLUMNS_TO_EXPORT,
				responseResult.getFirstResult().getProcessItems(), inquiryProcessRequest, responseResult);

		if (responseResult.isInError())
		{
			return responseResult;
		}

		// Mark the process as completed.
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#generateFileCSVCommunicationSummary(com.sensus.dm.common.process.model
	 * .
	 * request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVCommunicationSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> responseResult =
				getProcessBCL().fetchProcessById(
						new ProcessRequest(inquiryProcessRequest.getFirstProcess(), inquiryProcessRequest
								.getUserContext(), ProcessItemStatusEnum.FAILED));

		if (responseResult.isInError())
		{
			return responseResult;
		}

		DMUtil.generateCSV(getCommunicationSummaryColumns(), COMMUNICATION_COLUMNS_TO_EXPORT,
				responseResult.getFirstResult().getProcessItems(), inquiryProcessRequest, responseResult);

		if (responseResult.isInError())
		{
			return responseResult;
		}

		// Mark the process as completed.
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVTamperDetectSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVTamperDetectSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> responseResult =
				getProcessBCL().fetchProcessById(
						new ProcessRequest(inquiryProcessRequest.getFirstProcess(), inquiryProcessRequest
								.getUserContext()));

		if (responseResult.isInError() || !responseResult.hasResults()
				|| ValidationUtil.isNullOrEmpty(responseResult.getFirstResult().getProcessItems())
				|| ValidationUtil.isNullOrEmpty(responseResult.getFirstResult().getProperties()))
		{
			return responseResult;
		}

		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();
		List<LCMRelay> lcmRelayList = DMConvertUtil.convertPropertyToLCMRelay(
				responseResult.getFirstResult().getProperties());

		for (ProcessItem processItem : responseResult.getFirstResult().getProcessItems())
		{
			for (LCMRelay lcmRelay : lcmRelayList)
			{
				LCM lcm = new LCM(LCMTypeEnum.FLEXNET_LCM);
				lcm.setRadio(processItem.getDevice().getRadio());
				lcm.setDeviceId(processItem.getDevice().getDeviceId());
				lcm.addLCMRelay(lcmRelay);
				processItemList.add(new ProcessItem(lcm));
			}
		}

		if (ValidationUtil.isNullOrEmpty(processItemList))
		{
			return responseResult;
		}

		DMUtil.generateCSV(getTamperDetectTimeListAllColumns(), TAMPER_DETECT_TIME_COLUMNS_TO_EXPORT,
				processItemList, inquiryProcessRequest, responseResult);

		if (responseResult.isInError())
		{
			return responseResult;
		}

		// Mark the process as completed.
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVDemandResponseSetupSummary(com.sensus.dm.common
	 * .process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDemandResponseSetupSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> responseResult =
				getProcessBCL().fetchProcessById(
						new ProcessRequest(inquiryProcessRequest.getFirstProcess(), inquiryProcessRequest
								.getUserContext()));

		if (responseResult.isInError() || !responseResult.hasResults()
				|| ValidationUtil.isNullOrEmpty(responseResult.getFirstResult().getProcessItems())
				|| ValidationUtil.isNullOrEmpty(responseResult.getFirstResult().getProperties()))
		{
			return responseResult;
		}

		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();
		List<LCMRelay> lcmRelayList = DMConvertUtil.convertPropertyToLCMRelay(
				responseResult.getFirstResult().getProperties());

		if (ValidationUtil.isNullOrEmpty(lcmRelayList))
		{
			LCMRelay lcmRelay = new LCMRelay();
			for (Property property : responseResult.getFirstResult().getProperties())
			{
				switch (PropertyEnum.enumForValue(property.getPropertyName()))
				{
					case DEMAND_RESPONSE_ENROLLMENT_CODE:
						lcmRelay.setEnrollmentCode(Integer.parseInt(property.getPropertyValue()));
						break;

					case DEMAND_RESPONSE_RANDOMIZE_START:
						lcmRelay.setStartMinutes(Integer.parseInt(property.getPropertyValue()));
						break;

					case DEMAND_RESPONSE_RANDOMIZE_END:
						lcmRelay.setEndMinutes(Integer.parseInt(property.getPropertyValue()));
						break;

					default:
						break;
				}
			}
			lcmRelayList.add(lcmRelay);
		}

		for (ProcessItem processItem : responseResult.getFirstResult().getProcessItems())
		{
			for (LCMRelay lcmRelay : lcmRelayList)
			{
				LCM lcm = new LCM(LCMTypeEnum.FLEXNET_LCM);
				lcm.setRadio(processItem.getDevice().getRadio());
				lcm.setDeviceId(processItem.getDevice().getDeviceId());
				lcm.addLCMRelay(lcmRelay);
				processItemList.add(new ProcessItem(lcm));
			}
		}

		if (ValidationUtil.isNullOrEmpty(processItemList))
		{
			return responseResult;
		}

		DMUtil.generateCSV(getDemandResponseSetupListAllColumns(), DEMAND_RESPONSE_SETUP_TO_EXPORT,
				processItemList, inquiryProcessRequest, responseResult);

		if (responseResult.isInError())
		{
			return responseResult;
		}

		// Mark the process as completed.
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryProcessRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryProcessRequest.getUserContext(),
						inquiryProcessRequest.getFileName()));
	}
}
