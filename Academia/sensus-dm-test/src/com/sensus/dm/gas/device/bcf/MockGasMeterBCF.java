package com.sensus.dm.gas.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockGasMeterBCF.
 * 
 * @author QAT Global.
 */
public class MockGasMeterBCF extends AbstractMockBase implements IGasMeterBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcf.IGasMeterBCF#fetchAllGasMeters(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryDeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcf.IGasMeterBCF#fetchGasMeterById(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchGasMeterById(DeviceRequest deviceRequest)
	{
		return new DeviceResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcf.IGasMeterBCF#generateDevicesCSV(com.sensus.dm.gas.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new InquiryDeviceResponse();
	}
}
