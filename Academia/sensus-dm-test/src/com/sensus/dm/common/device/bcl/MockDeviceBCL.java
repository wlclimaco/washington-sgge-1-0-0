/*
 *
 */
package com.sensus.dm.common.device.bcl;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.base.model.DefaultActionsPermissions;
import com.sensus.dm.common.base.model.DeviceTypeModelPermissions;
import com.sensus.dm.common.base.model.DeviceTypePermissions;
import com.sensus.dm.common.base.model.ServicesPermissions;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.base.model.UiModulesPermissions;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockElectricMeterBCL.
 * 
 * @author QAT Global.
 */
public class MockDeviceBCL extends AbstractMockBase implements IDeviceBCL
{

	/** The Constant DEVICE_COUNT. */
	private static final int DEVICE_COUNT = 10;

	/** The Constant METER_FLEXNET_ID_1. */
	private static final BigInteger METER_FLEXNET_ID_1 = new BigInteger("35531728");

	/** The Constant METER_DEVICE_ID_1. */
	private static final String METER_DEVICE_ID_1 = "1N6023214271";

	/** The Constant HAN_FLEXNET_ID. */
	private static final BigInteger HAN_FLEXNET_ID = new BigInteger("2153943262073613");

	/** The Constant HAN_DEVICE_ID. */
	private static final String HAN_DEVICE_ID = "IHD930D";

	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The Constant TWO_HUNDRED. */
	private static final Integer TWO_HUNDRED = 200;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAllDevicesTypeCount(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<DeviceTypeCount> fetchAllDevicesTypeCount(DeviceRequest deviceRequest)
	{
		return deviceTypeResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAllByPremiseId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllByPremiseId(DeviceRequest deviceRequest)
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
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchServicesByDeviceType(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<ServicesPermissions> fetchServicesByDeviceType(DeviceRequest deviceRequest)
	{
		return servicesByDeviceTypeResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchDeviceByIdImport(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Device> fetchDeviceByIdImport(DeviceRequest deviceRequest)
	{

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return deviceResultsResponse(deviceRequest.getFirstDevice().getDeviceType());
		}

		return (InternalResultsResponse<Device>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAlarmHistory(com.sensus.dm.common.device.model.request.DeviceRequest
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
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchDevicesBoundsToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesBoundsToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return geocodeDeviceInfoResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchDevicesToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return geocodeDeviceInfoResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAllDeviceTypeDescriptions(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<DeviceModel> fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest)
	{
		return deviceModelResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchQuarantineCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Integer> fetchQuarantineCount(DeviceRequest deviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<Integer>(1);
		}

		return (InternalResultsResponse<Integer>)verifyOtherSituations();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAlarmsTypesCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<AlarmsTypesCount> fetchAlarmsTypesCount(DeviceRequest deviceRequest)
	{
		return alarmsTypesCountResultsResponse();
	}

	/**
	 * Device type results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<DeviceTypeCount> deviceTypeResultsResponse()
	{
		InternalResultsResponse<DeviceTypeCount> response = new InternalResultsResponse<DeviceTypeCount>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			DeviceTypeCount deviceType = new DeviceTypeCount();
			deviceType.setDevice(new Device("0", DeviceTypeEnum.HAN_DEVICE));
			deviceType.setDeviceCount(DEVICE_COUNT);

			response.addResult(deviceType);

			deviceType = new DeviceTypeCount();
			deviceType.setDevice(new Device("1", DeviceTypeEnum.ELECTRIC_METER));
			deviceType.setDeviceCount(DEVICE_COUNT);

			response.addResult(deviceType);
			return response;
		}

		return (InternalResultsResponse<DeviceTypeCount>)verifyOtherSituations();
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
	 * Device type parameter results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<ServicesPermissions> servicesByDeviceTypeResultsResponse()
	{
		InternalResultsResponse<ServicesPermissions> response =
				new InternalResultsResponse<ServicesPermissions>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			UiModulesPermissions ump =
					new UiModulesPermissions(Arrays.asList("aboutThisDevice"), Arrays.asList("networkStatus"),
							Arrays.asList("group"));

			ActionType ap = new ActionType(ActionTypeEnum.REMOTE_CONNECT);

			DefaultActionsPermissions dap = new DefaultActionsPermissions(Arrays.asList(ap));

			DeviceTypeModelPermissions dtmp = new DeviceTypeModelPermissions(Arrays.asList(ap), ump, "ELECTRIC_METER");

			DeviceTypePermissions dtp =
					new DeviceTypePermissions(DeviceTypeEnum.ELECTRIC_METER, Arrays.asList(dtmp), dap,
							Arrays.asList(AlarmEnum.BACK_FLOW));

			ServicesTypePermissions setp =
					new ServicesTypePermissions(ServiceTypeEnum.ELECTRIC, Arrays.asList(dtp),
							Arrays.asList("activity"), Arrays.asList(ap), Arrays.asList(ap), Arrays.asList(ap));

			ServicesPermissions stp = new ServicesPermissions(Arrays.asList(setp));

			response.addResult(stp);
			response.setStatus(Status.OperationSuccess);
			return response;
		}

		return (InternalResultsResponse<ServicesPermissions>)verifyOtherSituations();
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
			default:
				return (InternalResultsResponse<Alarm>)verifyOtherSituations();
		}
	}

	/**
	 * Geocode device info results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<GeocodeDeviceInfo> geocodeDeviceInfoResultsResponse()
	{
		switch (getSituationsEnum())
		{
			case SUCCESS:
				InternalResultsResponse<GeocodeDeviceInfo> response =
						new InternalResultsResponse<GeocodeDeviceInfo>(TestBaseUtil.createGeocodeDeviceInfo());
				response.getResultsSetInfo().setEndRow(TWENTY);
				response.getResultsSetInfo().setTotalRowsAvailable(TWO_HUNDRED);
				return response;
			default:
				return (InternalResultsResponse<GeocodeDeviceInfo>)verifyOtherSituations();
		}
	}

	/**
	 * Device type results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<DeviceModel> deviceModelResultsResponse()
	{
		InternalResultsResponse<DeviceModel> response = new InternalResultsResponse<DeviceModel>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(new DeviceModel(0, null, "Device description"));
			response.addResult(new DeviceModel(0, null, "Device description 01"));

			return response;
		}

		return (InternalResultsResponse<DeviceModel>)verifyOtherSituations();
	}

	/**
	 * Alarms types count results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<AlarmsTypesCount> alarmsTypesCountResultsResponse()
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<AlarmsTypesCount>(TestBaseUtil.createAlarmsTypesCount());
		}

		return (InternalResultsResponse<AlarmsTypesCount>)verifyOtherSituations();
	}
}
