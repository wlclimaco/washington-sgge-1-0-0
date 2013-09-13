package com.sensus.dm.gas.device.dac;

import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Class MockElectricMeterDAC.
 */
public class MockGasMeterDAC extends AbstractMockBase implements IGasMeterDAC
{

	/** The Constant FORMAT_DATE. */
	private static final String FORMAT_DATE = "MM/dd/yyyy";

	/** The Constant TIME_ZONE_VALUE. */
	private static final String TIME_ZONE_VALUE = "-5";

	/** The Constant VALUE_FOUR. */
	private static final int VALUE_FOUR = 4;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.dac.IGasMeterDAC#fetchAllGasMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<GasMeter> fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		inquiryDeviceRequest.setDateFormat(FORMAT_DATE);
		inquiryDeviceRequest.setTimeZone(TIME_ZONE_VALUE);

		return new InternalResultsResponse<GasMeter>((List<GasMeter>)(List<?>)TestBaseUtil.createDeviceList(
				DeviceTypeEnum.WATER_METER, VALUE_FOUR));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.dac.IGasMeterDAC#fetchGasMeterById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchGasMeterById(DeviceRequest deviceRequest)
	{
		return gasMeterResultsResponse(DeviceTypeEnum.GAS_METER);
	}

	/**
	 * Gas meter results response.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Device> gasMeterResultsResponse(DeviceTypeEnum deviceTypeEnum)
	{

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<Device>(TestBaseUtil.createDevice(deviceTypeEnum));
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<Device>();
	}

}
