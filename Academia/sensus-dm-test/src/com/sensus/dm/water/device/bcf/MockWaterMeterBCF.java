package com.sensus.dm.water.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.water.device.model.response.WaterMeterResponse;

/**
 * The Class MockWaterMeterBCF.
 * 
 * @author QAT Global.
 */
public class MockWaterMeterBCF extends AbstractMockBase implements IWaterMeterBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchAllWaterMeters(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryDeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchWaterMeterById(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchWaterMeterById(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#generateDevicesCSV(com.sensus.dm.water.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryDeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchLeakReport(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public WaterMeterResponse fetchLeakReport(DeviceRequest request)
	{
		return new WaterMeterResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.water.device.bcf.IWaterMeterBCF#generateFileCSVLeakReport(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public WaterMeterResponse generateFileCSVLeakReport(DeviceRequest request)
	{
		return new WaterMeterResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchCommunication(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchCommunication(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}
}
