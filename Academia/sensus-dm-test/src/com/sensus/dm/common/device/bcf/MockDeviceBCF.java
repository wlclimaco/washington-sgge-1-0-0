package com.sensus.dm.common.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryGeocodeDeviceInfoResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockElectricMeterBCF.
 * 
 * @author QAT Global.
 */
public class MockDeviceBCF extends AbstractMockBase implements IDeviceBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllDevicesTypeCount(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllDevicesTypeCount(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllByPremiseId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllByPremiseId(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchServicesByDeviceType(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchServicesByDeviceType(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDeviceByIdImport(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchDeviceByIdImport(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAlarmHistory(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchAlarmHistory(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDevicesBoundsToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryGeocodeDeviceInfoResponse fetchDevicesBoundsToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryGeocodeDeviceInfoResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDevicesToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryGeocodeDeviceInfoResponse fetchDevicesToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryGeocodeDeviceInfoResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllDeviceTypeDescriptions(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchQuarantineCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchQuarantineCount(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAlarmsTypesCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAlarmsTypesCount(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}
}
