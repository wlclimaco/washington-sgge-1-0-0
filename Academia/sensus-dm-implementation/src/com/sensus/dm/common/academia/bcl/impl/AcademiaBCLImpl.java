package com.sensus.dm.common.academia.bcl.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.Message;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.academia.bcl.IAcademiaBCL;
import com.sensus.dm.common.academia.dac.IAcademiaDAC;
import com.sensus.dm.common.academia.model.Academia;
import com.sensus.dm.common.academia.model.request.AcademiaRequest;
import com.sensus.dm.common.academia.model.request.InquiryAcademiaRequest;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class AcademiaBCLImpl.
 * 
 * @author Washington.
 */
public class AcademiaBCLImpl implements IAcademiaBCL
{

	/** The log. */
	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(AcademiaBCLImpl.class);
	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant STR_CSV. */
	private static final String STR_CSV = ".csv";

	/** The Constant STR_HIFEN. */
	private static final String STR_HIFEN = "-";

	/** The Constant STR_COMMA. */
	private static final String STR_COMMA = ",";

	/** The Constant INT_0. */
	private static final Integer INT_0 = 0;

	/** The Constant PROCESS_NAME_SEPARATOR. */
	private static final String PROCESS_NAME_SEPARATOR = "|";

	/** The Constant PROCESSING_ACADEMIA. */
	private static final String PROCESSING_ACADEMIA =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingAcademia";

	/** The Constant FILE_CREATION_FAILED. */
	private static final String FILE_CREATION_FAILED = "sensus.epm.processbclimpl.file.creation.failed";

	// /** The Constant ACADEMIA_ELECTRIC_COLUMNS_TO_EXPORT. */
	// private static final String[] ACADEMIA_ELECTRIC_COLUMNS_TO_EXPORT = {CsvColumnEnum.NAME.getValue(),
	// CsvColumnEnum.DESCRIPTION.getValue(), CsvColumnEnum.DEVICE_TYPE.getValue(),
	// CsvColumnEnum.ACADEMIA_TYPE.getValue(),
	// CsvColumnEnum.DEVICE_COUNT.getValue(), CsvColumnEnum.DATE_ADDED.getValue()};
	//
	// /** The Constant ACADEMIA_COLUMNS_TO_EXPORT. */
	// private static final String[] ACADEMIA_COLUMNS_TO_EXPORT = {CsvColumnEnum.NAME.getValue(),
	// CsvColumnEnum.DESCRIPTION.getValue(), CsvColumnEnum.ACADEMIA_TYPE.getValue(),
	// CsvColumnEnum.DEVICE_COUNT.getValue(), CsvColumnEnum.DATE_ADDED.getValue()};

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath;

	/** The academia dac. */
	private IAcademiaDAC academiaDAC;

	/** The action bcl. */
	private IActionBCL actionBCL;

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/** The device bcl. */
	private IDeviceBCL deviceBCL;

	/** The water meter bcl. */
	private IWaterMeterBCL waterMeterBCL;

	/** The gas meter bcl. */
	private IGasMeterBCL gasMeterBCL;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The all possible columns. */
	private List<CSVColumn> allPossibleColumns;

	/**
	 * Gets the temp upload file path.
	 * 
	 * @return the temp upload file path
	 */
	public String getTempUploadFilePath()
	{
		return tempUploadFilePath;
	}

	/**
	 * Sets the temp upload file path.
	 * 
	 * @param tempUploadFilePath the new temp upload file path
	 */
	public void setTempUploadFilePath(String tempUploadFilePath)
	{
		this.tempUploadFilePath = tempUploadFilePath;
	}

	/**
	 * Gets the academia dac.
	 * 
	 * @return the academia dac
	 */
	public IAcademiaDAC getAcademiaDAC()
	{
		return academiaDAC;
	}

	/**
	 * Sets the academia dac.
	 * 
	 * @param academiaDAC the new academia dac
	 */
	public void setAcademiaDAC(IAcademiaDAC academiaDAC)
	{
		this.academiaDAC = academiaDAC;
	}

	/**
	 * Gets the action bcl.
	 * 
	 * @return the action bcl
	 */
	public IActionBCL getActionBCL()
	{
		return actionBCL;
	}

	/**
	 * Sets the action bcl.
	 * 
	 * @param actionBCL the new action bcl
	 */
	public void setActionBCL(IActionBCL actionBCL)
	{
		this.actionBCL = actionBCL;
	}

	/**
	 * Gets the electric meter bcl.
	 * 
	 * @return the electric meter bcl
	 */
	public IElectricMeterBCL getElectricMeterBCL()
	{
		return electricMeterBCL;
	}

	/**
	 * Sets the electric meter bcl.
	 * 
	 * @param electricMeterBCL the new electric meter bcl
	 */
	public void setElectricMeterBCL(IElectricMeterBCL electricMeterBCL)
	{
		this.electricMeterBCL = electricMeterBCL;
	}

	/**
	 * Gets the device bcl.
	 * 
	 * @return the device bcl
	 */
	public IDeviceBCL getDeviceBCL()
	{
		return deviceBCL;
	}

	/**
	 * Sets the device bcl.
	 * 
	 * @param deviceBCL the new device bcl
	 */
	public void setDeviceBCL(IDeviceBCL deviceBCL)
	{
		this.deviceBCL = deviceBCL;
	}

	/**
	 * Gets the water meter bcl.
	 * 
	 * @return the water meter bcl
	 */
	public IWaterMeterBCL getWaterMeterBCL()
	{
		return waterMeterBCL;
	}

	/**
	 * Sets the water meter bcl.
	 * 
	 * @param waterMeterBCL the new water meter bcl
	 */
	public void setWaterMeterBCL(IWaterMeterBCL waterMeterBCL)
	{
		this.waterMeterBCL = waterMeterBCL;
	}

	/**
	 * Gets the gas meter bcl.
	 * 
	 * @return the gas meter bcl
	 */
	public IGasMeterBCL getGasMeterBCL()
	{
		return gasMeterBCL;
	}

	/**
	 * Sets the gas meter bcl.
	 * 
	 * @param gasMeterBCL the new gas meter bcl
	 */
	public void setGasMeterBCL(IGasMeterBCL gasMeterBCL)
	{
		this.gasMeterBCL = gasMeterBCL;
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
	 * Gets the all possible columns.
	 * 
	 * @return the all possible columns
	 */
	public List<CSVColumn> getAllPossibleColumns()
	{
		return allPossibleColumns;
	}

	/**
	 * Sets the all possible columns.
	 * 
	 * @param allPossibleColumns the new all possible columns
	 */
	public void setAllPossibleColumns(List<CSVColumn> allPossibleColumns)
	{
		this.allPossibleColumns = allPossibleColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcl.IAcademiaBCL#deleteAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResponse deleteAcademia(AcademiaRequest academiaRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Academia academia : academiaRequest.getAcademias())
		{

			// Are there something running for this academia ?
			response = fetchMessageProcessing(academia);
			if (response.isInError())
			{
				return response;
			}

			// the academia still exists
			InternalResultsResponse<Academia> academiaResponse =
					fetchAllAcademias(new InquiryAcademiaRequest(academia, academiaRequest.getServiceTypeEnum(),
							academiaRequest.getTenant()));
			if (academiaResponse.isInError())
			{
				return academiaResponse;
			}

			// deletes the academia
			response = getAcademiaDAC().deleteAcademia(new AcademiaRequest(academia));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del academia
			InternalResultsResponse<DMProcess> processResponse =
					insertAcademiaProcess(academiaResponse.getFirstResult(), academiaRequest.getIsMonitored(),
							"RemoveAcademiaAction.ACTION", ProcessStatusEnum.COMPLETED,
							null, academiaRequest);

			if (processResponse.isInError())
			{
				response.setStatus(processResponse.getStatus());
				response.addMessages(processResponse.getMessageInfoList());
				return response;
			}

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcl.IAcademiaBCL#insertAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> insertAcademia(AcademiaRequest academiaRequest)
	{

		InternalResultsResponse<Academia> academiaResponse = getAcademiaDAC().insertAcademia(academiaRequest);

		if (ValidationUtil.isNull(academiaResponse))
		{
			academiaResponse.setStatus(academiaResponse.getStatus());
			academiaResponse.addMessages(academiaResponse.getMessageInfoList());
			return academiaResponse;
		}

		// insert academia
		academiaResponse = getAcademiaDAC().insertAcademia(academiaRequest);
		if (academiaResponse.isInError())
		{
			return academiaResponse;
		}

		// insert process for academia creation

		InternalResultsResponse<DMProcess> processResponse =
				insertAcademiaProcess(academiaRequest.getFirstAcademia(), false,
						"CreateAcademiaAction.ACTION", ProcessStatusEnum.COMPLETED,
						null, academiaRequest);

		if (processResponse.isInError())
		{
			academiaResponse.setStatus(processResponse.getStatus());
			academiaResponse.addMessages(processResponse.getMessageInfoList());
			return academiaResponse;
		}

		academiaRequest.setProcessId(processResponse.getFirstResult().getId());

		return academiaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcl.IAcademiaBCL#updateAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> updateAcademia(AcademiaRequest academiaRequest)
	{
		InternalResultsResponse<Academia> academiaResponse = new InternalResultsResponse<Academia>();

		// inserts the academia
		academiaResponse = getAcademiaDAC().updateAcademia(academiaRequest);
		if (academiaResponse.isInError())
		{
			return academiaResponse;
		}

		// insert process for edit academia
		InternalResultsResponse<DMProcess> processResponse =
				insertAcademiaProcess(academiaRequest.getFirstAcademia(), false,
						"EditAcademiaAction.ACTION", ProcessStatusEnum.COMPLETED, null, academiaRequest);

		if (processResponse.isInError())
		{
			academiaResponse.setStatus(processResponse.getStatus());
			academiaResponse.addMessages(processResponse.getMessageInfoList());
			return academiaResponse;
		}

		return academiaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.bcl.IAcademiaBCL#fetchAllAcademias(com.sensus.dm.common.academia.model.request.
	 * InquiryAcademiaRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Academia> fetchAllAcademias(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		return getAcademiaDAC().fetchAllAcademias(inquiryAcademiaRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.bcl.IAcademiaBCL#fetchAcademiasByDevice(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	/**
	 * Fetch academias by id.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Academia> fetchAcademiasById(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		return getAcademiaDAC().fetchAcademiaById(inquiryAcademiaRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param academia the academia
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Academia academia)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), academia.getCdacad().toString()));

		InternalResponse response = getProcessBCL().fetchCheckProcessing(
				new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));

		if (response.isInError())
		{
			response.addMessage(PROCESSING_ACADEMIA,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation);

		}

		return response;

	}

	/**
	 * Insert academia process.
	 * 
	 * @param academia the academia
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> insertAcademiaProcess(Academia academia, Boolean isMonitored,
			String actionType,
			ProcessStatusEnum processStatusEnum,
			List<ProcessItem> processItems, TenantRequest tenantRequest)
	{

		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), academia.getCdacad()
				.toString()));
		properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), academia.getAcadem()));

		// create a Process for academia
		return getProcessBCL().insertProcess(
				new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
						new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
						.getUserContext(),
						tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));

	}

	@Override
	public InternalResultsResponse<Academia> fetchDevicesByName(AcademiaRequest academiaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
