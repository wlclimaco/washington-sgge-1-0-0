package com.sensus.dm.water.device.dac;

import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class MockElectricMeterDAC.
 */
public class MockWaterMeterDAC extends AbstractMockBase implements IWaterMeterDAC
{

	/** The Constant FORMAT_DATE. */
	private static final String FORMAT_DATE = "MM/dd/yyyy";

	/** The Constant TIME_ZONE_VALUE. */
	private static final String TIME_ZONE_VALUE = "-5";

	/** The Constant VALUE_FOUR. */
	private static final int VALUE_FOUR = 4;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.dac.IWaterMeterDAC#fetchAllWaterMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<WaterMeter> fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		inquiryDeviceRequest.setDateFormat(FORMAT_DATE);
		inquiryDeviceRequest.setTimeZone(TIME_ZONE_VALUE);

		return new InternalResultsResponse<WaterMeter>((List<WaterMeter>)(List<?>)TestBaseUtil.createDeviceList(
				DeviceTypeEnum.WATER_METER, VALUE_FOUR));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.dac.IWaterMeterDAC#fetchWaterMeterById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchWaterMeterById(DeviceRequest deviceRequest)
	{
		return waterMeterResultsResponse(DeviceTypeEnum.WATER_METER);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.dac.IWaterMeterDAC#fetchCommunication(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<WaterGasMeterStatusCount> fetchCommunication(DeviceRequest deviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<WaterGasMeterStatusCount>(TestBaseUtil.createWaterGasMeterStatusCount());
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<WaterGasMeterStatusCount>();
	}

	/**
	 * Water meter results response.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Device> waterMeterResultsResponse(DeviceTypeEnum deviceTypeEnum)
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.water.device.dac.IWaterMeterDAC#fetchLeakReport(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	@SuppressWarnings("unchecked")
	public InternalResultsResponse<WaterLeak> fetchLeakReport(DeviceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<WaterLeak>(TestBaseUtil.createWaterLeak());
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<WaterLeak>();
	}
}
