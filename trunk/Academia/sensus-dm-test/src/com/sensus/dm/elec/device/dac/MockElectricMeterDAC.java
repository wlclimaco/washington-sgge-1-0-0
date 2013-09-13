/*
 *
 */
package com.sensus.dm.elec.device.dac;

import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.ConnectionState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Class MockElectricMeterDAC.
 * 
 * @author QAT Global.
 */
public class MockElectricMeterDAC extends AbstractMockBase implements IElectricMeterDAC
{

	/** The Constant CONNECTION_STATE. */
	private static final String CONNECTION_STATE = "UNKONOW";

	/** The Constant DEVICE_MODEL. */
	private static final String DEVICE_MODEL = "539 63 537 08002";

	/** The Constant MANUFACTURE. */
	private static final String MANUFACTURE = "Sensus/HAI";

	/** The Constant VALUE_FOUR. */
	private static final int VALUE_FOUR = 4;

	/** The Constant TIME_ZONE_VALUE. */
	private static final String TIME_ZONE_VALUE = "-5";

	/** The Constant FORMAT_DATE. */
	private static final String FORMAT_DATE = "MM/dd/yyyy";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ElectricMeter> fetchAllElectricMeter(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		inquiryDeviceRequest.setDateFormat(FORMAT_DATE);
		inquiryDeviceRequest.setTimeZone(TIME_ZONE_VALUE);

		return new InternalResultsResponse<ElectricMeter>((List<ElectricMeter>)(List<?>)TestBaseUtil.createDeviceList(
				DeviceTypeEnum.ELECTRIC_METER, VALUE_FOUR));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllHanDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<HanDevice> fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		inquiryDeviceRequest.setDateFormat(FORMAT_DATE);
		inquiryDeviceRequest.setTimeZone(TIME_ZONE_VALUE);

		return new InternalResultsResponse<HanDevice>((List<HanDevice>)(List<?>)TestBaseUtil.createDeviceList(
				DeviceTypeEnum.HAN_DEVICE, VALUE_FOUR));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllLCM(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<LCM> fetchAllLCM(InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		inquiryDeviceRequest.setDateFormat(FORMAT_DATE);
		inquiryDeviceRequest.setTimeZone(TIME_ZONE_VALUE);

		return new InternalResultsResponse<LCM>((List<LCM>)(List<?>)TestBaseUtil.createDeviceList(
				DeviceTypeEnum.LCM, VALUE_FOUR));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchLCMById(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchLCMById(DeviceRequest deviceRequest)
	{
		if (!ValidationUtil.isNull(deviceRequest.getFirstDevice()))
		{
			return deviceResultsResponse(deviceRequest.getFirstDevice().getDeviceType());
		}

		return new InternalResultsResponse<Device>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchMeterById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchElectricMeterById(DeviceRequest deviceRequest)
	{
		return deviceResultsResponse(DeviceTypeEnum.ELECTRIC_METER);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchHanDeviceById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchHanDeviceById(DeviceRequest deviceRequest)
	{
		return deviceResultsResponse(DeviceTypeEnum.HAN_DEVICE);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchLifecycleStates(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public CachedResultsResponse<LifecycleState> fetchLifecycleStates(DeviceRequest request)
	{
		CachedResultsResponse<LifecycleState> response = new CachedResultsResponse<LifecycleState>();
		response.getResultsList().add(TestBaseUtil.createlifecycleState());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllHanDevicesByMeter(com.sensus.dm.common.device.model.request
	 * .
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllHanDevicesByMeter(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		List<Device> deviceList = TestBaseUtil.createDeviceList(DeviceTypeEnum.HAN_DEVICE, VALUE_FOUR);

		for (Device device : deviceList)
		{
			HanDevice hanDevice = (HanDevice)device;
			hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
			hanDevice.setDeviceModel(new DeviceModel(MANUFACTURE, DEVICE_MODEL));
			response.addResult(hanDevice);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllManufactureByDeviceSubType(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest)
	{
		return deviceModelResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllModelByDeviceSubType(com.sensus.dm.common.device.model.
	 * request
	 * .DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllModelByDeviceSubType(DeviceRequest deviceRequest)
	{
		return deviceModelResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchHanDeviceTypeId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Integer> fetchHanDeviceTypeId(DeviceRequest deviceRequest)
	{
		return new InternalResultsResponse<Integer>(1);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#updateDeviceStatus(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResponse updateDeviceStatus(DeviceRequest deviceRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#deleteDeviceStatus(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDeviceStatus(DeviceRequest deviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#deleteDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDevice(DeviceRequest deviceRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchDeviceTypeIdByDevice(com.sensus.dm.common.device.model.request
	 * .
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchDeviceTypeIdByDevice(DeviceRequest deviceRequest)
	{
		return new InternalResultsResponse<Integer>(1);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllConnectionState(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<ConnectionState> fetchAllConnectionState(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<ConnectionState> response =
				new InternalResultsResponse<ConnectionState>(new ConnectionState(0, CONNECTION_STATE, CONNECTION_STATE));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchEsmTouEnabled(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchEsmTouEnabled(DeviceRequest deviceRequest)
	{
		ElectricMeter electricMeter = (ElectricMeter)deviceRequest.getFirstDevice();

		if (ValidationUtil.isNull(electricMeter.getConfiguration()))
		{
			electricMeter.setConfiguration(new ElectricMeterConfiguration());
		}

		electricMeter.getConfiguration().setEsmEnable(true);
		electricMeter.getConfiguration().setTouEnable(true);

		return new InternalResultsResponse<Device>(electricMeter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllPeakDemand(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<PeakDemand> fetchAllPeakDemand(DeviceReadingRequest request)
	{
		InternalResultsResponse<PeakDemand> response = new InternalResultsResponse<PeakDemand>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response = new InternalResultsResponse<PeakDemand>();
			response.getResultsList().add(new PeakDemand());
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}
		return new InternalResultsResponse<PeakDemand>();
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
		switch (getSituationsEnum())
		{
			case SUCCESS:
				return new InternalResultsResponse<Device>(TestBaseUtil.createDevice(deviceTypeEnum));
			case ERROR:
				return getInternalResultsResponseError();
			default:
				return null;
		}
	}

	/**
	 * Device model results response.
	 * 
	 * @return the internal results response
	 */
	private InternalResultsResponse<Device> deviceModelResultsResponse()
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		List<Device> deviceList = TestBaseUtil.createDeviceList(DeviceTypeEnum.HAN_DEVICE, VALUE_FOUR);

		for (Device device : deviceList)
		{
			HanDevice hanDevice = (HanDevice)device;
			hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
			hanDevice.setDeviceModel(new DeviceModel(MANUFACTURE, DEVICE_MODEL));
			response.addResult(hanDevice);
		}

		return response;

	}
}
