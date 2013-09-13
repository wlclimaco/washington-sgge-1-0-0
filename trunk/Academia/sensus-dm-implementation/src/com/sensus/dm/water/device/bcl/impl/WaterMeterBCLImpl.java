package com.sensus.dm.water.device.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.common.csv.CSVColumn;
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
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;
import com.sensus.dm.water.device.dac.IWaterMeterDAC;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class WaterMeterBCLImpl.
 * 
 * @author QAT Global.
 */
public class WaterMeterBCLImpl implements IWaterMeterBCL
{
	/** The Constant WATER_LEAK_COLUMNS_TO_EXPORT. */
	private static final String[] WATER_LEAK_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.FLEXNET.getValue(), CsvColumnEnum.LEAK_TIME.getValue(),
			CsvColumnEnum.RECENT_CONSUMPTION.getValue(),
			CsvColumnEnum.RECENT_CONSUMPTION_PERCENTAGE.getValue(), CsvColumnEnum.PRIOR_CONSUMPTION.getValue(),
			CsvColumnEnum.PRIOR_CONSUMPTION_PERCENTAGE.getValue(), CsvColumnEnum.DAILY_CONSUMPTION_AVERAGE.getValue()};

	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(WaterMeterBCLImpl.class);

	// -------------------------------------------------------------------------

	// Not i18n messages/words.

	/** The Constant GENERATE_DEVICES_CSV. */
	private static final String GENERATE_DEVICES_CSV = "generateDevicesCSV";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The water meter dac. */
	private IWaterMeterDAC waterMeterDAC;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The water meter list all columns. */
	private List<CSVColumn> waterMeterListAllColumns;

	/** The water leak list all columns. */
	private List<CSVColumn> waterLeakListAllColumns;

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
	 * Gets the water meter dac.
	 * 
	 * @return the water meter dac
	 */
	public IWaterMeterDAC getWaterMeterDAC()
	{
		return waterMeterDAC;
	}

	/**
	 * Sets the water meter dac.
	 * 
	 * @param waterMeterDAC the new water meter dac
	 */
	public void setWaterMeterDAC(IWaterMeterDAC waterMeterDAC)
	{
		this.waterMeterDAC = waterMeterDAC;
	}

	/**
	 * Gets the water meter list all columns.
	 * 
	 * @return the water meter list all columns
	 */
	public List<CSVColumn> getWaterMeterListAllColumns()
	{
		return waterMeterListAllColumns;
	}

	/**
	 * Sets the water meter list all columns.
	 * 
	 * @param waterMeterListAllColumns the new water meter list all columns
	 */
	public void setWaterMeterListAllColumns(List<CSVColumn> waterMeterListAllColumns)
	{
		this.waterMeterListAllColumns = waterMeterListAllColumns;
	}

	/**
	 * Gets the water leak list all columns.
	 * 
	 * @return the water leak list all columns
	 */
	public List<CSVColumn> getWaterLeakListAllColumns()
	{
		return waterLeakListAllColumns;
	}

	/**
	 * Sets the water leak list all columns.
	 * 
	 * @param waterLeakListAllColumns the new water leak list all columns
	 */
	public void setWaterLeakListAllColumns(List<CSVColumn> waterLeakListAllColumns)
	{
		this.waterLeakListAllColumns = waterLeakListAllColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#fetchAllWaterMeters(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<WaterMeter> fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return getWaterMeterDAC().fetchAllWaterMeters(inquiryDeviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#fetchWaterMeterById(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchWaterMeterById(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = getWaterMeterDAC().fetchWaterMeterById(deviceRequest);
		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		applyFirmwareDisplay((WaterMeter)response.getFirstResult());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#generateDevicesCSV(com.sensus.dm.water.device.model.request.
	 * InquiryWaterMeterRequest)
	 */
	@Override
	public InternalResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(GENERATE_DEVICES_CSV + inquiryDeviceRequest.getFileName());
		}

		InternalResponse response = new InternalResponse();

		InternalResultsResponse<WaterMeter> waterMetersInternalResponse = fetchAllWaterMeters(inquiryDeviceRequest);
		if (waterMetersInternalResponse.isInError())
		{
			return waterMetersInternalResponse;
		}

		DMUtil.generateCSV(getWaterMeterListAllColumns(),
				DMConvertUtil.convertListToArrayColumns(inquiryDeviceRequest.getListColumn()),
				waterMetersInternalResponse.getResultsList(), inquiryDeviceRequest, response);

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
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#fetchCommunication(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<WaterGasMeterStatusCount> fetchCommunication(DeviceRequest deviceRequest)
	{
		return getWaterMeterDAC().fetchCommunication(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#fetchLeakReport(com.sensus.dm.water.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<WaterLeak> fetchLeakReport(DeviceRequest request)
	{
		return getWaterMeterDAC().fetchLeakReport(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.water.device.bcl.IWaterMeterBCL#generateFileCSVLeakReport(com.sensus.dm.water.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResponse generateFileCSVLeakReport(DeviceRequest request)
	{
		InternalResultsResponse<WaterLeak> fetchResponse = fetchLeakReport(request);
		if (fetchResponse.isInError())
		{
			return fetchResponse;
		}

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getWaterLeakListAllColumns(), WATER_LEAK_COLUMNS_TO_EXPORT, fetchResponse.getResultsList(),
				request, response);
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(request.getProcessId(),
						ProcessStatusEnum.COMPLETED),
						request.getUserContext(), request.getFileName()));
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private:

	/**
	 * Apply firmware display.
	 * 
	 * @param waterMeter the water meter
	 */
	private void applyFirmwareDisplay(WaterMeter waterMeter)
	{
		if (ValidationUtil.isNull(waterMeter.getConfiguration()))
		{
			return;
		}

		waterMeter.getConfiguration().setFirmwareMeter(DMConvertUtil.convertFirmwareDisplay(true,
				waterMeter.getConfiguration().getFirmwareMeter()));

		waterMeter.getConfiguration().setFirmwareFlexnet(
				DMConvertUtil.convertFirmwareDisplay(true, waterMeter.getConfiguration().getFirmwareFlexnet()));

		waterMeter.getConfiguration().setFirmwareBootflasher(
				DMConvertUtil.convertFirmwareDisplay(true, waterMeter.getConfiguration().getFirmwareBootflasher()));

		waterMeter.getConfiguration().setFirmwareZigbee(
				DMConvertUtil.convertFirmwareDisplay(true, waterMeter.getConfiguration().getFirmwareZigbee()));
	}
}
