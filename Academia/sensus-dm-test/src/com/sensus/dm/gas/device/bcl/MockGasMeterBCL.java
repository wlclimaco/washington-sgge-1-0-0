package com.sensus.dm.gas.device.bcl;

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
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Class MockElectricMeterBCL.
 * 
 * @author QAT Global.
 */
public class MockGasMeterBCL extends AbstractMockBase implements IGasMeterBCL
{
	/** The Constant METER_FLEXNET_ID_1. */
	private static final BigInteger METER_FLEXNET_ID_1 = new BigInteger("35531728");

	/** The Constant METER_DEVICE_ID_1. */
	private static final String METER_DEVICE_ID_1 = "1N6023214271";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcl.IGasMeterBCL#fetchAllGasMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<GasMeter> fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return (InternalResultsResponse<GasMeter>)verifyOtherSituations();

		}
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getElectricMeterSearch()))
		{
			return gasMeterResultsResponse();
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getDevices()))
		{
			return gasMeterResultsResponse();
		}

		return gasMeterResultsResponse();
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
		if (!ValidationUtil.isNull(deviceRequest.getFirstDevice()))
		{
			return gasMeterResultsResponse(deviceRequest.getFirstDevice().getDeviceType());
		}

		return new InternalResultsResponse<Device>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcl.IGasMeterBCL#generateDevicesCSV(com.sensus.dm.gas.device.model.request.
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

	/**
	 * Gas meter results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<GasMeter> gasMeterResultsResponse()
	{
		InternalResultsResponse<GasMeter> response = new InternalResultsResponse<GasMeter>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult((GasMeter)TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER,
					METER_FLEXNET_ID_1, METER_DEVICE_ID_1));

			return response;
		}

		return (InternalResultsResponse<GasMeter>)verifyOtherSituations();
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
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult(TestBaseUtil.createDevice(DeviceTypeEnum.GAS_METER, METER_FLEXNET_ID_1,
					METER_DEVICE_ID_1));

			return response;
		}

		return (InternalResultsResponse<Device>)verifyOtherSituations();
	}

}
