/*
 *
 */
package com.sensus.dm.elec.device.bcl;

import java.math.BigInteger;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ConnectionState;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Class MockElectricMeterBCL.
 * 
 * @author QAT Global.
 */
public class MockElectricMeterBCL extends AbstractMockBase implements IElectricMeterBCL
{

	/** The Constant DEVICE_MODEL. */
	private static final String DEVICE_MODEL = "539 63 537 08002";

	/** The Constant MANUFACTURE. */
	private static final String MANUFACTURE = "Sensus/HAI";

	/** The Constant VALUE_ONE. */
	private static final int VALUE_ONE = 1;

	/** The Constant VALUE_TWO. */
	private static final int VALUE_TWO = 2;

	/** The Constant VALUE_THREE. */
	private static final int VALUE_THREE = 3;

	/** The Constant METER_FLEXNET_ID_1. */
	private static final BigInteger METER_FLEXNET_ID_1 = new BigInteger("35531728");

	/** The Constant METER_FLEXNET_ID_2. */
	private static final BigInteger METER_FLEXNET_ID_2 = new BigInteger("36531836");

	/** The Constant METER_DEVICE_ID_1. */
	private static final String METER_DEVICE_ID_1 = "1N6023214271";

	/** The Constant METER_DEVICE_ID_2. */
	private static final String METER_DEVICE_ID_2 = "1N7028731881";

	/** The Constant HAN_FLEXNET_ID. */
	private static final BigInteger HAN_FLEXNET_ID = new BigInteger("2153943262073613");

	/** The Constant HAN_DEVICE_ID. */
	private static final String HAN_DEVICE_ID = "IHD930D";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllElectricMeter(com.sensus.dm.common.device.model.request
	 * .InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ElectricMeter> fetchAllElectricMeter(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return (InternalResultsResponse<ElectricMeter>)verifyOtherSituations();

		}
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getElectricMeterSearch()))
		{
			return electricMeterResultsResponse();
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getDevices()))
		{
			return electricMeterResultsResponse();
		}

		return electricMeterResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllHanDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<HanDevice> fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return (InternalResultsResponse<HanDevice>)verifyOtherSituations();

		}
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getHanDeviceSearch()))
		{
			return hanDeviceInternalResultsResponse();
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getDevices()))
		{
			return hanDeviceInternalResultsResponse();
		}

		return hanDeviceInternalResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllLCM(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<LCM> fetchAllLCM(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return (InternalResultsResponse<LCM>)verifyOtherSituations();

		}
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getLcmSearch()))
		{
			return lcmResultsResponse();
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getDevices()))
		{
			return lcmResultsResponse();
		}

		return lcmResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchDeviceById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchDeviceById(DeviceRequest deviceRequest)
	{
		if (!ValidationUtil.isNull(deviceRequest.getFirstDevice()))
		{
			if (!ValidationUtil.isNull(deviceRequest.getFirstDevice().getRadio())
					&& OTA_METER_FLEXNET2_ID.equals(deviceRequest.getFirstDevice().getRadio().getFlexNetId()))
			{
				return new InternalResultsResponse<Device>();
			}

			return deviceResultsResponse(deviceRequest.getFirstDevice().getDeviceType());
		}

		return new InternalResultsResponse<Device>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#generateDevicesCSV(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse generateDevicesCSV(InquiryDeviceRequest inquiryMeterRequest)
	{
		return internalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchLifecycleStates(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<LifecycleState> fetchLifecycleStates(DeviceRequest request)
	{
		return lifecycleStateResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllHanDevicesByMeter(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Device> fetchAllHanDevicesByMeter(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);

			for (Integer i = 1; i <= VALUE_THREE; i++)
			{
				if (i % 2 == 0)
				{
					HanDevice hanDevice = new HanDevice(new Radio(new BigInteger(i.toString())));
					DeviceModel deviceModel = new DeviceModel(MANUFACTURE, DEVICE_MODEL);
					hanDevice.setDeviceModel(deviceModel);
					response.addResult(hanDevice);
				}
				else
				{
					LCM lcm = new LCM(new Radio(new BigInteger(i.toString())));
					DeviceModel deviceModel = new DeviceModel(MANUFACTURE, DEVICE_MODEL);
					lcm.setDeviceModel(deviceModel);
					response.addResult(lcm);
				}

			}

			return response;
		}

		return (InternalResultsResponse<Device>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllManufactureByDeviceSubType(com.sensus.dm.common.device
	 * .model.request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest)
	{
		return deviceModelResultsResponse(VALUE_ONE);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllModelByDeviceSubType(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllModelByDeviceSubType(DeviceRequest deviceRequest)
	{
		return deviceModelResultsResponse(VALUE_TWO);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchHanDeviceTypeId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Integer> fetchHanDeviceTypeId(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		response.addResult(1);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#updateDeviceStatus(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResponse updateDeviceStatus(DeviceRequest deviceRequest)
	{
		return internalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#deleteDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDevice(DeviceRequest deviceRequest)
	{
		return internalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchDeviceTypeIdByDevice(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchDeviceTypeIdByDevice(DeviceRequest deviceRequest)
	{
		return new InternalResultsResponse<Integer>(1);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllConnectionState(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ConnectionState> fetchAllConnectionState(DeviceRequest deviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<ConnectionState>();
		}

		return (InternalResultsResponse<ConnectionState>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllPeakDemand(DeviceReadingRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<PeakDemand> fetchAllPeakDemand(DeviceReadingRequest request)
	{
		InternalResultsResponse<PeakDemand> response = new InternalResultsResponse<PeakDemand>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{

			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult(new PeakDemand());

			return response;
		}

		return (InternalResultsResponse<PeakDemand>)verifyOtherSituations();
	}

	/**
	 * Internal response.
	 * 
	 * @return the internal response
	 */
	private InternalResponse internalResponse()
	{

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			return new InternalResponse();
		}

		return verifyOtherSituations();
	}

	/**
	 * Device results response.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Device> deviceResultsResponse(DeviceTypeEnum deviceTypeEnum)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			if (DeviceTypeEnum.HAN_DEVICE.equals(deviceTypeEnum))
			{
				// success response
				response.setStatus(Status.OperationSuccess);
				response.addResult(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, HAN_FLEXNET_ID, HAN_DEVICE_ID));
			}
			else
			{
				// success response
				response.setStatus(Status.OperationSuccess);
				response.addResult(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID_1,
						METER_DEVICE_ID_1));
			}

			return response;
		}

		return (InternalResultsResponse<Device>)verifyOtherSituations();
	}

	/**
	 * Electric meter results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<ElectricMeter> electricMeterResultsResponse()
	{
		InternalResultsResponse<ElectricMeter> response = new InternalResultsResponse<ElectricMeter>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult((ElectricMeter)TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER,
					METER_FLEXNET_ID_1, METER_DEVICE_ID_1));

			return response;
		}

		return (InternalResultsResponse<ElectricMeter>)verifyOtherSituations();
	}

	/**
	 * Han device internal results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<HanDevice> hanDeviceInternalResultsResponse()
	{
		InternalResultsResponse<HanDevice> response = new InternalResultsResponse<HanDevice>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult((HanDevice)TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE,
					METER_FLEXNET_ID_1, METER_DEVICE_ID_1));

			return response;
		}

		return (InternalResultsResponse<HanDevice>)verifyOtherSituations();
	}

	/**
	 * LCM results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<LCM> lcmResultsResponse()
	{
		InternalResultsResponse<LCM> response = new InternalResultsResponse<LCM>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult((LCM)TestBaseUtil.createDevice(DeviceTypeEnum.LCM,
					METER_FLEXNET_ID_1, METER_DEVICE_ID_1));

			return response;
		}

		return (InternalResultsResponse<LCM>)verifyOtherSituations();
	}

	/**
	 * Device model results response.
	 * 
	 * @param amount the amount
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Device> deviceModelResultsResponse(Integer amount)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);

			for (Integer i = 1; i <= amount; i++)
			{
				HanDevice hanDevice = new HanDevice(new Radio(new BigInteger(i.toString())));
				DeviceModel deviceModel = new DeviceModel(MANUFACTURE, DEVICE_MODEL);
				hanDevice.setDeviceModel(deviceModel);

				response.addResult(hanDevice);
			}

			return response;
		}

		return (InternalResultsResponse<Device>)verifyOtherSituations();
	}

	/**
	 * Lifecycle state results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<LifecycleState> lifecycleStateResultsResponse()
	{
		InternalResultsResponse<LifecycleState> response = new InternalResultsResponse<LifecycleState>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(TestBaseUtil.createlifecycleState());
			return response;
		}

		return (InternalResultsResponse<LifecycleState>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Device> fetchAllDevices(InquiryDeviceRequest request)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResult(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, METER_FLEXNET_ID_1, METER_DEVICE_ID_1));
			response.addResult(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID_2,
					METER_DEVICE_ID_2));
			response.addResult(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, METER_FLEXNET_ID_2,
					METER_DEVICE_ID_2));

			return response;
		}

		return (InternalResultsResponse<Device>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#generatePeakDemandCSV(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generatePeakDemandCSV(DeviceReadingRequest request)
	{
		return internalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchLCMRelaysByDevice(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<LCMRelay> fetchLCMRelaysByDevice(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<LCMRelay> response = new InternalResultsResponse<LCMRelay>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			response.addResults(TestBaseUtil.createLCMRelay());

			return response;
		}

		return (InternalResultsResponse<LCMRelay>)verifyOtherSituations();
	}
}
