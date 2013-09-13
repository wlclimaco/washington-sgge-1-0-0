package com.sensus.dm.water.device.bcl;

import java.math.BigInteger;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class MockElectricMeterBCL.
 */
public class MockWaterMeterBCL extends AbstractMockBase implements IWaterMeterBCL
{
	/** The Constant METER_FLEXNET_ID_1. */
	private static final BigInteger METER_FLEXNET_ID_1 = new BigInteger("35531728");

	/** The Constant METER_DEVICE_ID_1. */
	private static final String METER_DEVICE_ID_1 = "1N6023214271";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#fetchAllWaterMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<WaterMeter> fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return (InternalResultsResponse<WaterMeter>)verifyOtherSituations();

		}
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getElectricMeterSearch()))
		{
			return waterMeterResultsResponse();
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getDevices()))
		{
			return waterMeterResultsResponse();
		}

		return waterMeterResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#fetchWaterMeterById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchWaterMeterById(DeviceRequest deviceRequest)
	{
		if (!ValidationUtil.isNull(deviceRequest.getFirstDevice()))
		{
			return waterMeterResultsResponse(deviceRequest.getFirstDevice().getDeviceType());
		}

		return new InternalResultsResponse<Device>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#generateDevicesCSV(com.sensus.dm.water.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcl.IWaterMeterBCL#fetchCommunication(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public InternalResultsResponse<WaterGasMeterStatusCount> fetchCommunication(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<WaterGasMeterStatusCount> response =
				new InternalResultsResponse<WaterGasMeterStatusCount>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult(TestBaseUtil.createWaterGasMeterStatusCount());
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Water meter results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<WaterMeter> waterMeterResultsResponse()
	{
		InternalResultsResponse<WaterMeter> response = new InternalResultsResponse<WaterMeter>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult((WaterMeter)TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER,
					METER_FLEXNET_ID_1,
					METER_DEVICE_ID_1));

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
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
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult(TestBaseUtil.createDevice(DeviceTypeEnum.WATER_METER, METER_FLEXNET_ID_1,
					METER_DEVICE_ID_1));

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<WaterLeak> fetchLeakReport(DeviceRequest request)
	{
		return getWaterLeakInternalResultResponse();
	}

	@Override
	public InternalResponse generateFileCSVLeakReport(DeviceRequest request)
	{
		return getWaterLeakInternalResultResponse();
	}

	/**
	 * Gets the water leak internal result response.
	 * 
	 * @return the water leak internal result response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<WaterLeak> getWaterLeakInternalResultResponse()
	{
		InternalResultsResponse<WaterLeak> response = new InternalResultsResponse<WaterLeak>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult(TestBaseUtil.createWaterLeak());

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}
}
