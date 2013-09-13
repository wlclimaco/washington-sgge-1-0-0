package com.sensus.dm.common.pessoa.bcl.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.Message;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.pessoa.bcl.IPessoaBCL;
import com.sensus.dm.common.pessoa.dac.IPessoaDAC;
import com.sensus.dm.common.pessoa.model.Pessoa;
import com.sensus.dm.common.pessoa.model.request.InquiryPessoaRequest;
import com.sensus.dm.common.pessoa.model.request.PessoaRequest;
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
 * The Class PessoaBCLImpl.
 * 
 * @author Washington.
 */
public class PessoaBCLImpl implements IPessoaBCL
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(PessoaBCLImpl.class);

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

	/** The Constant PROCESSING_PESSOA. */
	private static final String PROCESSING_PESSOA =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingPessoa";

	/** The Constant FILE_CREATION_FAILED. */
	private static final String FILE_CREATION_FAILED = "sensus.epm.processbclimpl.file.creation.failed";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath;

	/** The pessoa dac. */
	private IPessoaDAC pessoaDAC;

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
	 * Gets the pessoa dac.
	 * 
	 * @return the pessoa dac
	 */
	public IPessoaDAC getPessoaDAC()
	{
		return pessoaDAC;
	}

	/**
	 * Sets the pessoa dac.
	 * 
	 * @param pessoaDAC the new pessoa dac
	 */
	public void setPessoaDAC(IPessoaDAC pessoaDAC)
	{
		this.pessoaDAC = pessoaDAC;
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
	 * @see
	 * com.sensus.dm.common.pessoa.bcl.IPessoaBCL#deletePessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public InternalResponse deletePessoa(PessoaRequest pessoaRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Pessoa pessoa : pessoaRequest.getPessoas())
		{

			// Are there something running for this pessoa ?
			response = fetchMessageProcessing(pessoa);
			if (response.isInError())
			{
				return response;
			}

			// the pessoa still exists
			InternalResultsResponse<Pessoa> pessoaResponse =
					fetchAllPessoas(new InquiryPessoaRequest(pessoa, pessoaRequest.getServiceTypeEnum(),
							pessoaRequest.getTenant()));
			if (pessoaResponse.isInError())
			{
				return pessoaResponse;
			}

			// deletes the pessoa
			response = getPessoaDAC().deletePessoa(new PessoaRequest(pessoa));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del pessoa
			InternalResultsResponse<DMProcess> processResponse =
					insertPessoaProcess(pessoaResponse.getFirstResult(), pessoaRequest.getIsMonitored(),
							"", ProcessStatusEnum.COMPLETED,
							null, pessoaRequest);

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
	 * @see
	 * com.sensus.dm.common.pessoa.bcl.IPessoaBCL#insertPessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> insertPessoa(PessoaRequest pessoaRequest)
	{

		InternalResultsResponse<Pessoa> pessoaResponse = new InternalResultsResponse<Pessoa>();

		// insert pessoa
		pessoaResponse = getPessoaDAC().insertPessoa(pessoaRequest);
		if (pessoaResponse.isInError())
		{
			return pessoaResponse;
		}

		// insert process for pessoa creation

		InternalResultsResponse<DMProcess> processResponse =
				insertPessoaProcess(pessoaRequest.getFirstPessoa(), false,
						"", ProcessStatusEnum.COMPLETED,
						null, pessoaRequest);

		if (processResponse.isInError())
		{
			pessoaResponse.setStatus(processResponse.getStatus());
			pessoaResponse.addMessages(processResponse.getMessageInfoList());
			return pessoaResponse;
		}

		pessoaRequest.setProcessId(processResponse.getFirstResult().getId());

		return pessoaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcl.IPessoaBCL#updatePessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> updatePessoa(PessoaRequest pessoaRequest)
	{
		InternalResultsResponse<Pessoa> pessoaResponse = new InternalResultsResponse<Pessoa>();

		// inserts the pessoa
		pessoaResponse = getPessoaDAC().updatePessoa(pessoaRequest);
		if (pessoaResponse.isInError())
		{
			return pessoaResponse;
		}

		// insert process for edit pessoa
		InternalResultsResponse<DMProcess> processResponse =
				insertPessoaProcess(pessoaRequest.getFirstPessoa(), false,
						"", ProcessStatusEnum.COMPLETED, null, pessoaRequest);

		if (processResponse.isInError())
		{
			pessoaResponse.setStatus(processResponse.getStatus());
			pessoaResponse.addMessages(processResponse.getMessageInfoList());
			return pessoaResponse;
		}

		return pessoaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcl.IPessoaBCL#fetchAllPessoas(com.sensus.dm.common.pessoa.model.request.
	 * InquiryPessoaRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Pessoa> fetchAllPessoas(InquiryPessoaRequest inquiryPessoaRequest)
	{
		return getPessoaDAC().fetchAllPessoas(inquiryPessoaRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcl.IPessoaBCL#fetchPessoasByDevice(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	/**
	 * Fetch pessoas by id.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Pessoa> fetchPessoasById(InquiryPessoaRequest inquiryPessoaRequest)
	{
		return getPessoaDAC().fetchPessoasById(inquiryPessoaRequest);

	}

	/**
	 * Fetch pessoas by name.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Pessoa> fetchPessoasByName(InquiryPessoaRequest inquiryPessoaRequest)
	{
		return getPessoaDAC().fetchPessoasByName(inquiryPessoaRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param pessoa the pessoa
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Pessoa pessoa)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), pessoa.getCdpessoa().toString()));

		InternalResponse response = getProcessBCL().fetchCheckProcessing(
				new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));

		if (response.isInError())
		{
			response.addMessage(PROCESSING_PESSOA,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation);

		}

		return response;

	}

	/**
	 * Insert pessoa process.
	 * 
	 * @param pessoa the pessoa
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> insertPessoaProcess(Pessoa pessoa, Boolean isMonitored,
			String actionType,
			ProcessStatusEnum processStatusEnum,
			List<ProcessItem> processItems, TenantRequest tenantRequest)
	{

		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), pessoa.getCdpessoa()
				.toString()));
		properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), pessoa.getFirstFoto().getFoto()));

		// create a Process for pessoa
		return getProcessBCL().insertProcess(
				new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
						new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
						.getUserContext(),
						tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));

	}
}
