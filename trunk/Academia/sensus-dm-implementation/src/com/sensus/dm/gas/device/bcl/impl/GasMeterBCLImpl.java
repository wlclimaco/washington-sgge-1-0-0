package com.sensus.dm.gas.device.bcl.impl;

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
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.gas.device.dac.IGasMeterDAC;
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Class GasMeterBCLImpl.
 * 
 * @author QAT Global.
 */
public class GasMeterBCLImpl implements IGasMeterBCL
{

	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(GasMeterBCLImpl.class);

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

	/** The gas meter dac. */
	private IGasMeterDAC gasMeterDAC;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The gas meter list all columns. */
	private List<CSVColumn> gasMeterListAllColumns;

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
	 * Gets the gas meter dac.
	 * 
	 * @return the gas meter dac
	 */
	public IGasMeterDAC getGasMeterDAC()
	{
		return gasMeterDAC;
	}

	/**
	 * Sets the gas meter dac.
	 * 
	 * @param gasMeterDAC the new gas meter dac
	 */
	public void setGasMeterDAC(IGasMeterDAC gasMeterDAC)
	{
		this.gasMeterDAC = gasMeterDAC;
	}

	/**
	 * Gets the gas meter list all columns.
	 * 
	 * @return the gas meter list all columns
	 */
	public List<CSVColumn> getGasMeterListAllColumns()
	{
		return gasMeterListAllColumns;
	}

	/**
	 * Sets the gas meter list all columns.
	 * 
	 * @param gasMeterListAllColumns the new gas meter list all columns
	 */
	public void setGasMeterListAllColumns(List<CSVColumn> gasMeterListAllColumns)
	{
		this.gasMeterListAllColumns = gasMeterListAllColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcl.IGasMeterBCL#fetchAllGasMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GasMeter> fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return getGasMeterDAC().fetchAllGasMeters(inquiryDeviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.gas.device.bcl.IGasMeterBCL#fetchGasMeterById(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchGasMeterById(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = getGasMeterDAC().fetchGasMeterById(deviceRequest);
		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		applyFirmwareDisplay((GasMeter)response.getFirstResult());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcl.IGasMeterBCL#generateDevicesCSV(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(GENERATE_DEVICES_CSV + inquiryDeviceRequest.getFileName());
		}

		InternalResponse response = new InternalResponse();

		InternalResultsResponse<GasMeter> gasMetersInternalResponse = fetchAllGasMeters(inquiryDeviceRequest);
		if (gasMetersInternalResponse.isInError())
		{
			return gasMetersInternalResponse;
		}

		DMUtil.generateCSV(getGasMeterListAllColumns(),
				DMConvertUtil.convertListToArrayColumns(inquiryDeviceRequest.getListColumn()),
				gasMetersInternalResponse.getResultsList(), inquiryDeviceRequest, response);

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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private:

	/**
	 * Apply firmware display.
	 * 
	 * @param gasMeter the gas meter
	 */
	private void applyFirmwareDisplay(GasMeter gasMeter)
	{
		if (ValidationUtil.isNull(gasMeter.getConfiguration()))
		{
			return;
		}

		gasMeter.getConfiguration().setFirmwareMeter(DMConvertUtil.convertFirmwareDisplay(true,
				gasMeter.getConfiguration().getFirmwareMeter()));

		gasMeter.getConfiguration().setFirmwareFlexnet(
				DMConvertUtil.convertFirmwareDisplay(true, gasMeter.getConfiguration().getFirmwareFlexnet()));

		gasMeter.getConfiguration().setFirmwareBootflasher(
				DMConvertUtil.convertFirmwareDisplay(true, gasMeter.getConfiguration().getFirmwareBootflasher()));

		gasMeter.getConfiguration().setFirmwareZigbee(
				DMConvertUtil.convertFirmwareDisplay(true, gasMeter.getConfiguration().getFirmwareZigbee()));
	}

}
