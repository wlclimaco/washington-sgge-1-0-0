/*
 *
 */
package com.sensus.dm.common.device.dac;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockElectricMeterDAC.
 * 
 * @author QAT Global.
 */
public class MockDeviceDAC extends AbstractMockBase implements IDeviceDAC
{

	/** The Constant DEVICE_MODEL. */
	private static final String DEVICE_MODEL = "539 63 537 08002";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.IDeviceDAC#fetchAllDevicesTypeCount(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DeviceTypeCount> fetchAllDevicesTypeCount(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<DeviceTypeCount> response = new InternalResultsResponse<DeviceTypeCount>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response = deviceCountResponse();
			response.getResultsList().add(new DeviceTypeCount());
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<DeviceTypeCount>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchAllByPremiseId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllByPremiseId(DeviceRequest deviceRequest)
	{
		return deviceResultsResponse(DeviceTypeEnum.HAN_DEVICE);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchDeviceByIdImport(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchDeviceByIdImport(DeviceRequest deviceRequest)
	{
		return deviceResultsResponse(DeviceTypeEnum.ELECTRIC_METER);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.IDeviceDAC#fetchAlarmHistory(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Alarm> fetchAlarmHistory(DeviceRequest deviceRequest)
	{
		return alarmResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.IDeviceDAC#fetchDevicesBoundsToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesBoundsToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return geocodeResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchDevicesToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return geocodeResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.IDeviceDAC#fetchAllDeviceTypeDescriptions(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<DeviceModel> fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<DeviceModel> response = deviceTypeResultsResponse();
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchAlarmsTypesCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<AlarmsTypesCount> fetchAlarmsTypesCount(DeviceRequest deviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<AlarmsTypesCount>(TestBaseUtil.createAlarmsTypesCount());
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<AlarmsTypesCount>();
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
	 * Device count response.
	 * 
	 * @return the internal results response
	 */
	private InternalResultsResponse<DeviceTypeCount> deviceCountResponse()
	{
		return new InternalResultsResponse<DeviceTypeCount>();
	}

	/**
	 * Alarm results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Alarm> alarmResultsResponse()
	{
		switch (getSituationsEnum())
		{
			case SUCCESS:
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.DATE, 1);
				return new InternalResultsResponse<Alarm>(Arrays.asList(new Alarm(AlarmEnum.BACK_FLOW, c.getTime()),
						new Alarm(AlarmEnum.BACK_FLOW, new Date())));
			case ERROR:
				return getInternalResultsResponseError();
			default:
				return null;
		}
	}

	/**
	 * Geocode results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<GeocodeDeviceInfo> geocodeResultsResponse()
	{
		switch (getSituationsEnum())
		{
			case SUCCESS:
				return new InternalResultsResponse<GeocodeDeviceInfo>(TestBaseUtil.createGeocodeDeviceInfo());
			case ERROR:
				return getInternalResultsResponseError();
			default:
				return null;
		}
	}

	/**
	 * Device type results response.
	 * 
	 * @return the internal results response
	 */
	private InternalResultsResponse<DeviceModel> deviceTypeResultsResponse()
	{
		DeviceModel deviceModel = TestBaseUtil.createDeviceModel();

		deviceModel.setDescription(DEVICE_MODEL);

		return new InternalResultsResponse<DeviceModel>(deviceModel);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchQuarantineCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchQuarantineCount(DeviceRequest deviceRequest)
	{
		return new InternalResultsResponse<Integer>(1);
	}
}
