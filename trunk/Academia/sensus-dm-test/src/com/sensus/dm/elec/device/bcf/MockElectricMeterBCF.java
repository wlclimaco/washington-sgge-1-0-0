package com.sensus.dm.elec.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryPeakDemandResponse;

/**
 * The Class MockElectricMeterBCF.
 * 
 * @author QAT Global.
 */
public class MockElectricMeterBCF extends AbstractMockBase implements IElectricMeterBCF
{
	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchDeviceById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchDeviceById(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#generateDevicesCSV(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryMeterRequest)
	{
		return new InquiryDeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchLifecycleStates(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchLifecycleStates(DeviceRequest request)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllHanDevicesByMeter(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllHanDevicesByMeter(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllManufactureByDeviceSubType(com.sensus.dm.common.device
	 * .model.request.DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllModelByDeviceSubType(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllModelByDeviceSubType(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllConnectionState(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllConnectionState(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllElectricMeters(com.sensus.dm.common.device.model.request
	 * .InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllElectricMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryDeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllLCMs(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InquiryDeviceResponse fetchAllLCMs(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryDeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllHanDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryDeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllPeakDemand(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryPeakDemandResponse fetchAllPeakDemand(DeviceReadingRequest request)
	{
		return new InquiryPeakDemandResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#generatePeakDemandCSV(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InquiryPeakDemandResponse generatePeakDemandCSV(DeviceReadingRequest request)
	{
		return new InquiryPeakDemandResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchLCMRelaysByDevice(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchLCMRelaysByDevice(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

}
