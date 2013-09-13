package com.sensus.dm.common.device.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.dac.IDeviceDAC;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;

/**
 * The Class ElectricMeterDACImpl.
 * 
 * @author QAT Global.
 * 
 */
public class DeviceDACImpl extends SqlSessionDaoSupport implements IDeviceDAC
{

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant DEVICE_MAP. */
	private static final String DEVICE_MAP = "DeviceMap.";

	/** The Constant FETCH_ALL_DEVICES_INSTALLED. */
	private static final String FETCH_ALL_DEVICES_TYPE_COUNT = DEVICE_MAP
			+ "fetchAllDevicesTypeCount";

	/** The Constant FETCH_ALL_BY_PREMISE_ID. */
	private static final String FETCH_ALL_BY_PREMISE_ID = DEVICE_MAP
			+ "fetchAllByPremiseId";

	/** The Constant FETCH_ALARM_HISTORY. */
	private static final String FETCH_ALARM_HISTORY = DEVICE_MAP + "fetchAlarmHistory";

	/** The Constant FETCH_DEVICES_BOUNDS_TO_MAP. */
	private static final String FETCH_DEVICES_BOUNDS_TO_MAP = DEVICE_MAP + "fetchDevicesBoundsToMap";

	/** The Constant FETCH_DEVICES_TO_MAP. */
	private static final String FETCH_DEVICES_TO_MAP = DEVICE_MAP + "fetchDevicesToMap";

	/** The Constant FETCH_COUNT_DEVICES. */
	private static final String FETCH_COUNT_DEVICES = DEVICE_MAP + "fetchCountDevices";

	/** The Constant FETCH_DEVICE_BY_ID_IMPORT. */
	private static final String FETCH_DEVICE_BY_ID_IMPORT = DEVICE_MAP
			+ "fetchDeviceByIdImport";

	/** The Constant FETCH_ALL_DEVICE_TYPE_DESCRIPTION. */
	private static final String FETCH_ALL_DEVICE_TYPE_DESCRIPTION = DEVICE_MAP
			+ "fetchAllDeviceTypesDescription";

	/** The Constant FETCH_QUARANTINE_COUNT. */
	private static final String FETCH_QUARANTINE_COUNT = DEVICE_MAP + "fetchQuarantineCount";
	
	/** The Constant FETCH_ALARMS_TYPES_COUNT. */
	private static final String FETCH_ALARMS_TYPES_COUNT = DEVICE_MAP
			+ "fetchAlarmsTypesCount";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchAllByPremiseId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllByPremiseId(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_ALL_BY_PREMISE_ID, deviceRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.IDeviceDAC#fetchAllDevicesTypeCount(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DeviceTypeCount> fetchAllDevicesTypeCount(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<DeviceTypeCount> response = new InternalResultsResponse<DeviceTypeCount>();

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_DEVICES_TYPE_COUNT,
						deviceRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchDeviceByIdImport(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchDeviceByIdImport(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_DEVICE_BY_ID_IMPORT, deviceRequest, response);

		DMConvertUtil.checkResult(response);

		return response;
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
		InternalResultsResponse<Alarm> response = new InternalResultsResponse<Alarm>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_ALARM_HISTORY, deviceRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.IDeviceDAC#fetchDevicesBoundsToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesBoundsToMap(InquiryDeviceRequest request)
	{
		InternalResultsResponse<GeocodeDeviceInfo> response = new InternalResultsResponse<GeocodeDeviceInfo>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_DEVICES_BOUNDS_TO_MAP, request, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchDevicesToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesToMap(InquiryDeviceRequest request)
	{
		InternalResultsResponse<GeocodeDeviceInfo> response = new InternalResultsResponse<GeocodeDeviceInfo>();

		response.getResultsSetInfo().setEndRow(
				(Integer)SensusMyBatisDacHelper.doQueryForObject(
						getSqlSession(), FETCH_COUNT_DEVICES, request));

		if (!ValidationUtil.isNullOrZero(response.getResultsSetInfo().getEndRow()))
		{
			SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_DEVICES_TO_MAP, request, response);
		}

		// Remove geocode data to get ALL Devices
		removeGeocodeData(request);

		response.getResultsSetInfo().setTotalRowsAvailable(
				(Integer)SensusMyBatisDacHelper.doQueryForObject(
						getSqlSession(), FETCH_COUNT_DEVICES, request));

		return response;
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
		InternalResultsResponse<DeviceModel> response = new InternalResultsResponse<DeviceModel>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_ALL_DEVICE_TYPE_DESCRIPTION, deviceRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchQuarantineCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchQuarantineCount(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();

		response.addResult(
				(Integer)SensusMyBatisDacHelper.doQueryForObject(
						getSqlSession(), FETCH_QUARANTINE_COUNT, deviceRequest));

		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.dac.IDeviceDAC#fetchAlarmsTypesCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<AlarmsTypesCount> fetchAlarmsTypesCount(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<AlarmsTypesCount> response = new InternalResultsResponse<AlarmsTypesCount>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALARMS_TYPES_COUNT, deviceRequest, response);

		return response;
	}

	/**
	 * Removes the geocode data.
	 * 
	 * @param request the request
	 */
	private void removeGeocodeData(InquiryDeviceRequest request)
	{
		request.setBottomLeftLat(null);
		request.setBottomLeftLon(null);
		request.setTopRightLat(null);
		request.setTopRightLon(null);
	}
}