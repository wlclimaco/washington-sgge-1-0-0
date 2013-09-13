package com.sensus.dm.elec.device.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.dac.IElectricMeterDAC;
import com.sensus.dm.elec.device.model.ConnectionState;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Class ElectricMeterBCLImpl.
 * 
 * @author QAT Global.
 * 
 */
public class ElectricMeterBCLImpl implements IElectricMeterBCL
{

	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(ElectricMeterBCLImpl.class);

	// -------------------------------------------------------------------------

	// Not i18n messages/words.

	/** The Constant GENERATE_DEVICES_CSV. */
	private static final String GENERATE_DEVICES_CSV = "generateDevicesCSV";

	/** The Constant GENERATE_PEAK_DEMAND_CSV. */
	private static final String GENERATE_PEAK_DEMAND_CSV = "generatePeakDemandCSV";

	/** The Constant PEAK_DEMAND_COLUMNS_TO_EXPORT. */
	private static final String[] PEAK_DEMAND_COLUMNS_TO_EXPORT = {CsvColumnEnum.TIER.getValue(),
			CsvColumnEnum.PEAK_DEMAND.getValue(),
			CsvColumnEnum.DEMAND_UNITS.getValue(), CsvColumnEnum.PEAK_TIME.getValue(),
			CsvColumnEnum.READING_DATE.getValue(),
			CsvColumnEnum.RESET_DATE.getValue(), CsvColumnEnum.COUNTER.getValue()};

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The electric meter dac. */
	private IElectricMeterDAC electricMeterDAC; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The devices total columns. */
	private List<CSVColumn> electricMeterListAllColumns;

	/** The han device list all columns. */
	private List<CSVColumn> hanDeviceListAllColumns;

	/** The lcm list all columns. */
	private List<CSVColumn> lcmListAllColumns;

	/** The peak demand list all columns. */
	private List<CSVColumn> peakDemandListAllColumns;

	/** The default lcm relays. */
	private List<LCMRelay> defaultLcmRelays;

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
	 * Gets the electric meter dac.
	 * 
	 * @return the electric meter dac
	 */
	public IElectricMeterDAC getElectricMeterDAC()
	{
		return electricMeterDAC;
	}

	/**
	 * Sets the electric meter dac.
	 * 
	 * @param electricMeterDAC the new electric meter dac
	 */
	public void setElectricMeterDAC(IElectricMeterDAC electricMeterDAC)
	{
		this.electricMeterDAC = electricMeterDAC;
	}

	/**
	 * Gets the han device list all columns.
	 * 
	 * @return the han device list all columns
	 */
	public List<CSVColumn> getHanDeviceListAllColumns()
	{
		return hanDeviceListAllColumns;
	}

	/**
	 * Sets the han device list all columns.
	 * 
	 * @param hanDeviceListAllColumns the new han device list all columns
	 */
	public void setHanDeviceListAllColumns(List<CSVColumn> hanDeviceListAllColumns)
	{
		this.hanDeviceListAllColumns = hanDeviceListAllColumns;
	}

	/**
	 * Gets the lcm list all columns.
	 * 
	 * @return the lcm list all columns
	 */
	public List<CSVColumn> getLcmListAllColumns()
	{
		return lcmListAllColumns;
	}

	/**
	 * Sets the lcm list all columns.
	 * 
	 * @param lcmListAllColumns the new lcm list all columns
	 */
	public void setLcmListAllColumns(List<CSVColumn> lcmListAllColumns)
	{
		this.lcmListAllColumns = lcmListAllColumns;
	}

	/**
	 * Gets the default lcm relays.
	 * 
	 * @return the default lcm relays
	 */
	public List<LCMRelay> getDefaultLcmRelays()
	{
		return defaultLcmRelays;
	}

	/**
	 * Sets the default lcm relays.
	 * 
	 * @param defaultLcmRelays the new default lcm relays
	 */
	public void setDefaultLcmRelays(List<LCMRelay> defaultLcmRelays)
	{
		this.defaultLcmRelays = defaultLcmRelays;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/**
	 * Gets the electric meter list all columns.
	 * 
	 * @return the electric meter list all columns
	 */
	public List<CSVColumn> getElectricMeterListAllColumns()
	{
		return electricMeterListAllColumns;
	}

	/**
	 * Sets the electric meter list all columns.
	 * 
	 * @param electricMeterListAllColumns the new electric meter list all columns
	 */
	public void setElectricMeterListAllColumns(List<CSVColumn> electricMeterListAllColumns)
	{
		this.electricMeterListAllColumns = electricMeterListAllColumns;
	}

	/**
	 * Gets the peak demand list all columns.
	 * 
	 * @return the peak demand list all columns
	 */
	public List<CSVColumn> getPeakDemandListAllColumns()
	{
		return peakDemandListAllColumns;
	}

	/**
	 * Sets the peak demand list all columns.
	 * 
	 * @param peakDemandListAllColumns the new peak demand list all columns
	 */
	public void setPeakDemandListAllColumns(List<CSVColumn> peakDemandListAllColumns)
	{
		this.peakDemandListAllColumns = peakDemandListAllColumns;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchDeviceById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchDeviceById(DeviceRequest deviceRequest)
	{
		Device device = deviceRequest.getFirstDevice();
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		switch (device.getDeviceType())
		{
			case HAN_DEVICE:

				return getElectricMeterDAC().fetchHanDeviceById(deviceRequest);

			case LCM:

				response = getElectricMeterDAC().fetchLCMById(deviceRequest);
				if (response.isInError() || !response.hasResults())
				{
					return response;
				}

				applyFirmwareDisplay((LCM)response.getFirstResult());
				return response;

			default: // default is ELECTRIC_METER

				response = getElectricMeterDAC().fetchElectricMeterById(deviceRequest);

				if (response.isInError() || !response.hasResults())
				{
					return response;
				}

				applyFirmwareDisplay((ElectricMeter)response.getFirstResult());
				return getElectricMeterDAC().fetchEsmTouEnabled(new DeviceRequest(response.getFirstResult()));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllDevices(InquiryDeviceRequest request)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		switch (request.getDeviceSearch().getFirstDeviceType())
		{
			case ELECTRIC_METER:

				InternalResultsResponse<ElectricMeter> electricResponse = fetchAllElectricMeter(request);
				if (electricResponse.isInError())
				{
					return handleError(response, electricResponse);
				}

				response.getResultsList().addAll(electricResponse.getResultsList());
				break;

			case HAN_DEVICE:

				InternalResultsResponse<HanDevice> hanResponse = fetchAllHanDevices(request);
				if (hanResponse.isInError())
				{
					return handleError(response, hanResponse);
				}

				response.getResultsList().addAll(hanResponse.getResultsList());
				break;

			case LCM:

				InternalResultsResponse<LCM> lcmResponse = fetchAllLCM(request);
				if (lcmResponse.isInError())
				{
					return handleError(response, lcmResponse);
				}

				response.getResultsList().addAll(lcmResponse.getResultsList());
				break;

			default:
				break;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#generatePeakDemandCSV(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generatePeakDemandCSV(DeviceReadingRequest request)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(GENERATE_PEAK_DEMAND_CSV + request.getFileName());
		}

		InternalResponse response = new InternalResponse();

		// Get data and generate file csv
		InternalResultsResponse<PeakDemand> peakDemandResponse = fetchAllPeakDemand(request);
		if (peakDemandResponse.isInError() || !peakDemandResponse.hasResults())
		{
			return peakDemandResponse;
		}

		DMUtil.generateCSV(getPeakDemandListAllColumns(), PEAK_DEMAND_COLUMNS_TO_EXPORT,
				peakDemandResponse.getResultsList(),
				request, response);

		// If file csv was successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(request.getProcessId(),
						ProcessStatusEnum.COMPLETED), request.getUserContext(), request
						.getFileName()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#generateFileCSV(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InternalResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{

		if (LOG.isDebugEnabled())
		{
			LOG.debug(GENERATE_DEVICES_CSV + inquiryDeviceRequest.getFileName());
		}

		InternalResponse response = new InternalResponse();

		switch (inquiryDeviceRequest.getDeviceSearch().getFirstDeviceType())
		{
			case HAN_DEVICE:

				// Get meters and generate file csv with this meters information
				InternalResultsResponse<HanDevice> hanDeviceResponse = fetchAllHanDevices(inquiryDeviceRequest);
				if (hanDeviceResponse.isInError() || !hanDeviceResponse.hasResults())
				{
					return hanDeviceResponse;
				}

				DMUtil.generateCSV(getHanDeviceListAllColumns(),
						DMConvertUtil.convertListToArrayColumns(inquiryDeviceRequest.getListColumn()),
						hanDeviceResponse.getResultsList(), inquiryDeviceRequest, response);
				break;
			case LCM:

				// Get meters and generate file csv with this meters information
				InternalResultsResponse<LCM> lcmResponse = fetchAllLCM(inquiryDeviceRequest);
				if (lcmResponse.isInError() || !lcmResponse.hasResults())
				{
					return lcmResponse;
				}

				DMUtil.generateCSV(getLcmListAllColumns(),
						DMConvertUtil.convertListToArrayColumns(inquiryDeviceRequest.getListColumn()),
						lcmResponse.getResultsList(), inquiryDeviceRequest, response);
				break;
			default:

				// Get meters and generate file csv with this meters information
				InternalResultsResponse<ElectricMeter> electricMeterResponse =
						fetchAllElectricMeter(inquiryDeviceRequest);

				if (electricMeterResponse.isInError() || !electricMeterResponse.hasResults())
				{
					return electricMeterResponse;
				}

				DMUtil.generateCSV(getElectricMeterListAllColumns(),
						DMConvertUtil.convertListToArrayColumns(inquiryDeviceRequest.getListColumn()),
						electricMeterResponse.getResultsList(), inquiryDeviceRequest, response);
				break;

		}

		// If file csv was successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryDeviceRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryDeviceRequest.getUserContext(), inquiryDeviceRequest
						.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchLifecycleStates(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<LifecycleState> fetchLifecycleStates(DeviceRequest request)
	{
		CachedResultsResponse<LifecycleState> response = getElectricMeterDAC().fetchLifecycleStates(request);

		InternalResultsResponse<LifecycleState> internalResponse = new InternalResultsResponse<LifecycleState>();
		internalResponse.addResults(response.getResultsList());

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllElectricMeter(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ElectricMeter> fetchAllElectricMeter(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return getElectricMeterDAC().fetchAllElectricMeter(inquiryDeviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllLCM(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<LCM> fetchAllLCM(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return getElectricMeterDAC().fetchAllLCM(inquiryDeviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllHanDevices(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<HanDevice> fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return getElectricMeterDAC().fetchAllHanDevices(inquiryDeviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllHanDevicesByMeter(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllHanDevicesByMeter(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().fetchAllHanDevicesByMeter(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllManufactureByDeviceSubType(com.sensus.dm.common.device
	 * .model.request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().fetchAllManufactureByDeviceSubType(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllModelByDeviceSubType(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllModelByDeviceSubType(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().fetchAllModelByDeviceSubType(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchHanDeviceTypeId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Integer> fetchHanDeviceTypeId(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().fetchHanDeviceTypeId(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#updateDeviceStatus(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResponse updateDeviceStatus(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().updateDeviceStatus(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#deleteDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDevice(DeviceRequest deviceRequest)
	{
		InternalResponse response = deleteDeviceStatus(deviceRequest);

		if (response.isInError())
		{
			return response;
		}
		return getElectricMeterDAC().deleteDevice(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchDeviceTypeIdByDevice(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchDeviceTypeIdByDevice(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().fetchDeviceTypeIdByDevice(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllConnectionState(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<ConnectionState> fetchAllConnectionState(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().fetchAllConnectionState(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllPeakDemand(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<PeakDemand> fetchAllPeakDemand(DeviceReadingRequest request)
	{
		return getElectricMeterDAC().fetchAllPeakDemand(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchLCMRelaysByDevice(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<LCMRelay> fetchLCMRelaysByDevice(DeviceRequest request)
	{
		return new InternalResultsResponse<LCMRelay>(getDefaultLcmRelays());
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private:

	/**
	 * Delete device status.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	private InternalResponse deleteDeviceStatus(DeviceRequest deviceRequest)
	{
		return getElectricMeterDAC().deleteDeviceStatus(deviceRequest);
	}

	/**
	 * Apply firmware display.
	 * 
	 * @param lcm the lcm
	 */
	private void applyFirmwareDisplay(LCM lcm)
	{
		if (ValidationUtil.isNull(lcm.getConfiguration()))
		{
			return;
		}

		lcm.getConfiguration().setFirmwareFlexnet(
				DMConvertUtil.convertFirmwareDisplay(true, lcm.getConfiguration().getFirmwareFlexnet()));

		lcm.getConfiguration().setFirmwareMeter(DMConvertUtil.convertFirmwareDisplay(true,
				lcm.getConfiguration().getFirmwareMeter()));

		lcm.getConfiguration().setFirmwareBootflasher(DMConvertUtil
				.convertFirmwareDisplay(true, lcm.getConfiguration().getFirmwareBootflasher()));

		lcm.getConfiguration().setFirmwareZigbee(DMConvertUtil.convertFirmwareDisplay(
				true, lcm.getConfiguration().getFirmwareZigbee()));
	}

	/**
	 * Apply firmware display.
	 * 
	 * @param electricMeter the electric meter
	 */
	private void applyFirmwareDisplay(ElectricMeter electricMeter)
	{
		if (ValidationUtil.isNull(electricMeter.getConfiguration()))
		{
			return;
		}

		electricMeter.getConfiguration().setFirmwareFlexnet(
				DMConvertUtil.convertFirmwareDisplay(true, electricMeter.getConfiguration().getFirmwareFlexnet()));

		electricMeter.getConfiguration().setFirmwareMeter(DMConvertUtil.convertFirmwareDisplay(true,
				electricMeter.getConfiguration().getFirmwareMeter()));

		electricMeter.getConfiguration().setFirmwareBootflasher(DMConvertUtil
				.convertFirmwareDisplay(true, electricMeter.getConfiguration().getFirmwareBootflasher()));

		electricMeter.getConfiguration().setFirmwareZigbee(DMConvertUtil.convertFirmwareDisplay(
				true, electricMeter.getConfiguration().getFirmwareZigbee()));
	}

	/**
	 * Handle error.
	 * 
	 * @param response the response
	 * @param responseOld the response old
	 * @return the internal results response
	 */
	private InternalResultsResponse<Device> handleError(InternalResultsResponse<Device> response,
			InternalResponse responseOld)
	{
		response.setStatus(responseOld.getStatus());
		response.addMessages(responseOld.getMessageInfoList());
		return response;
	}
}
