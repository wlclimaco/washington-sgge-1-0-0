package com.sensus.dm.elec.device.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.elec.device.dac.IElectricMeterDAC;
import com.sensus.dm.elec.device.model.ConnectionState;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Class ElectricMeterDACImpl.
 * 
 * @author QAT Global.
 * 
 */
public class ElectricMeterDACImpl extends SqlSessionDaoSupport implements IElectricMeterDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant PARAMSIZE1. */
	private static final Integer PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customer_id";

	/** The Constant DEVICE_ID. */
	private static final String DEVICE_ID = "device_id";

	/** The Constant DEVICE_STATUS. */
	private static final String DEVICE_STATUS = "device_status";

	/** The Constant ESM_ENABLED. */
	private static final String ESM_ENABLED = "esm_enabled";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant MAC_ADDRESS. */
	private static final String MAC_ADDRESS = "mac_address";

	/** The Constant TOU_ENABLED. */
	private static final String TOU_ENABLED = "tou_enabled";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant DEVICE_MAP. */
	private static final String DEVICE_MAP = "ElectricMap.";

	/** The Constant FETCH_ALL_HAN_DEVICES_TOTAL_ROWS. */
	private static final String FETCH_ALL_HAN_DEVICES_TOTAL_ROWS = DEVICE_MAP
			+ "hanDevicesPaginationTotalRows";

	/** The Constant FETCH_ALL_HAN_DEVICES. */
	private static final String FETCH_ALL_HAN_DEVICES = DEVICE_MAP
			+ "fetchAllHanDevices";

	/** The Constant FETCH_ALL_ELECTRIC_METERS. */
	private static final String FETCH_ALL_ELECTRIC_METERS = DEVICE_MAP
			+ "fetchAllElectricMeters";

	/** The Constant FETCH_ALL_ELECTRIC_METER_TOTAL_ROWS. */
	private static final String FETCH_ALL_ELECTRIC_METER_TOTAL_ROWS = DEVICE_MAP
			+ "electricMetersPaginationTotalRows";

	/** The Constant FETCH_ALL_LCM. */
	private static final String FETCH_ALL_LCM = DEVICE_MAP + "fetchAllLCM";

	/** The Constant FETCH_ALL_LCM_TOTAL_ROWS. */
	private static final String FETCH_ALL_LCM_TOTAL_ROWS = DEVICE_MAP
			+ "lcmPaginationTotalRows";

	/** The Constant FETCH_LCM_BY_ID. */
	private static final String FETCH_LCM_BY_ID = DEVICE_MAP
			+ "fetchLCMById";

	/** The Constant FETCH_ELECTRIC_METER_BY_ID. */
	private static final String FETCH_ELECTRIC_METER_BY_ID = DEVICE_MAP
			+ "fetchElectricMeterById";

	/** The Constant FETCH_HAN_BY_ID. */
	private static final String FETCH_HAN_BY_ID = DEVICE_MAP
			+ "fetchHanDeviceById";

	/** The Constant FETCH_ESM_TOU_ENABLED. */
	private static final String FETCH_ESM_TOU_ENABLED = DEVICE_MAP
			+ "fetchEsmTouEnabled";

	/** The Constant FETCH_LIFECYCLE_STATES. */
	private static final String FETCH_LIFECYCLE_STATES = DEVICE_MAP
			+ "fetchLifecycleStates";

	/** The Constant FETCH_ALL_HAN_DEVICES_BY_METER. */
	private static final String FETCH_ALL_HAN_DEVICES_BY_METER = DEVICE_MAP
			+ "fetchAllHanDevicesByMeter";

	/** The Constant FETCH_ALL_MANUFACTURE_BY_DEVICE_SUB_TYPE. */
	private static final String FETCH_ALL_MANUFACTURE_BY_DEVICE_SUB_TYPE = DEVICE_MAP
			+ "fetchAllManufactureByDeviceSubType";

	/** The Constant FETCH_ALL_CONNECTION_STATE. */
	private static final String FETCH_ALL_CONNECTION_STATE = DEVICE_MAP
			+ "fetchAllConnectionState";

	/** The Constant FETCH_ALL_MODEL_BY_DEVICE_SUB_TYPE. */
	private static final String FETCH_ALL_MODEL_BY_DEVICE_SUB_TYPE = DEVICE_MAP
			+ "fetchAllModelByDeviceSubType";

	/** The Constant FETCH_HAN_DEVICE_TYPE_ID. */
	private static final String FETCH_HAN_DEVICE_TYPE_ID = DEVICE_MAP
			+ "fetchHanDeviceTypeId";

	/** The Constant DELETE_DEVICE. */
	private static final String DELETE_DEVICE = DEVICE_MAP + "deleteDevice";

	/** The Constant UPSERT_DEVICE_STATUS. */
	private static final String UPSERT_DEVICE_STATUS = DEVICE_MAP
			+ "upsertDeviceStatus";

	/** The Constant DELETE_DEVICE_STATUS. */
	private static final String DELETE_DEVICE_STATUS = DEVICE_MAP
			+ "deleteDeviceStatus";

	/** The Constant FETCH_DEVICE_TYPE_BY_DEVICE. */
	private static final String FETCH_DEVICE_TYPE_BY_DEVICE = DEVICE_MAP
			+ "fetchDeviceTypeIdByDevice";

	/** The Constant FETCH_ALL_PEAK_DEMAND. */
	private static final String FETCH_ALL_PEAK_DEMAND = DEVICE_MAP + "fetchAllPeakDemand";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllElectricMeter(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ElectricMeter> fetchAllElectricMeter(InquiryDeviceRequest request)
	{
		InternalResultsResponse<ElectricMeter> response = new InternalResultsResponse<ElectricMeter>();

		if (request.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), FETCH_ALL_ELECTRIC_METER_TOTAL_ROWS, request));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_ELECTRIC_METERS,
						request));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllHanDevices(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<HanDevice> fetchAllHanDevices(InquiryDeviceRequest request)
	{
		InternalResultsResponse<HanDevice> response = new InternalResultsResponse<HanDevice>();

		if (request.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), FETCH_ALL_HAN_DEVICES_TOTAL_ROWS, request));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_HAN_DEVICES,
						request));

		return response;
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
	public InternalResultsResponse<LCM> fetchAllLCM(InquiryDeviceRequest request)
	{
		InternalResultsResponse<LCM> response = new InternalResultsResponse<LCM>();

		if (request.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), FETCH_ALL_LCM_TOTAL_ROWS, request));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_LCM,
						request));

		return response;
	}

	/**
	 * Fetch LCM by id.
	 * 
	 * @param deviceRequest the device request
	 * 
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Device> fetchLCMById(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_LCM_BY_ID, deviceRequest, response);

		DMConvertUtil.checkResult(response);

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchElectricMeterById(com.sensus.dm.elec.device
	 * .model.request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchElectricMeterById(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ELECTRIC_METER_BY_ID,
				deviceRequest, response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchHanDeviceById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchHanDeviceById(
			DeviceRequest deviceRequest)
	{

		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_HAN_BY_ID, deviceRequest, response);

		DMConvertUtil.checkResult(response);

		return response;

	}

	/**
	 * Fetch lifecycle states.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CachedResultsResponse<LifecycleState> fetchLifecycleStates(
			DeviceRequest request)
	{
		CachedResultsResponse<LifecycleState> response = new CachedResultsResponse<LifecycleState>();

		response.getResultsList().addAll(SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_LIFECYCLE_STATES));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllHanDevicesByMeter(com.sensus
	 * .dm.elec.device.model.request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllHanDevicesByMeter(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_ALL_HAN_DEVICES_BY_METER, deviceRequest.getFirstDevice(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllManufactureByDeviceSubType(com.sensus.dm.common.device
	 * .model.request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllManufactureByDeviceSubType(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_MANUFACTURE_BY_DEVICE_SUB_TYPE,
				deviceRequest.getFirstDevice(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllModelByDeviceSubType(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllModelByDeviceSubType(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_MODEL_BY_DEVICE_SUB_TYPE,
				deviceRequest.getFirstDevice(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchHanDeviceTypeId(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchHanDeviceTypeId(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_HAN_DEVICE_TYPE_ID,
				deviceRequest.getFirstDevice(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#updateDeviceStatus(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResponse updateDeviceStatus(DeviceRequest deviceRequest)
	{
		InternalResponse response = new InternalResultsResponse<Device>();

		for (Device device : deviceRequest.getDevices())
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
			paramMap.put(DEVICE_ID, device.getDeviceId());
			paramMap.put(CUSTOMER_ID, device.getRadio().getCustomerId());

			if (device.getDeviceType().equals(DeviceTypeEnum.LCM))
			{
				if (!ValidationUtil.isNull(((LCM)device).getLifecycleStateEnum()))
				{
					paramMap.put(DEVICE_STATUS, ((LCM)device).getLifecycleStateEnum().getValue().toString());
				}
			}
			else if (!ValidationUtil.isNull(((HanDevice)device).getLifecycleStateEnum()))
			{
				paramMap.put(DEVICE_STATUS, ((HanDevice)device).getLifecycleStateEnum().getValue().toString());
			}

			SensusMyBatisDacHelper.doInsert(getSqlSession(), UPSERT_DEVICE_STATUS, paramMap, response);

			DMConvertUtil.checkResult(response, paramMap);

			if (response.isInError())
			{
				return response;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#deleteDeviceStatus(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResponse deleteDeviceStatus(DeviceRequest deviceRequest)
	{
		InternalResponse response = new InternalResultsResponse<Device>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(
				PARAMSIZE1);
		paramMap.put(MAC_ADDRESS, deviceRequest.getFirstDevice().getRadio().getFlexNetId());

		SensusMyBatisDacHelper.doInsert(getSqlSession(), DELETE_DEVICE_STATUS,
				paramMap, response);

		DMConvertUtil.checkResult(response, paramMap);

		return response;
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
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(FLEXNET_ID, deviceRequest.getFirstDevice().getRadio().getFlexNetId());

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_DEVICE,
				paramMap);

		InternalResponse response = new InternalResponse();

		DMConvertUtil.checkResult(response, paramMap);

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchDeviceTypeIdByDevice(com.sensus
	 * .dm.elec.device.model.request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchDeviceTypeIdByDevice(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_DEVICE_TYPE_BY_DEVICE,
				deviceRequest.getFirstDevice(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllConnectionState(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<ConnectionState> fetchAllConnectionState(
			DeviceRequest deviceRequest)
	{
		InternalResultsResponse<ConnectionState> response = new InternalResultsResponse<ConnectionState>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_CONNECTION_STATE, deviceRequest, response);

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

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(FLEXNET_ID, electricMeter.getRadio().getFlexNetId());

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ESM_TOU_ENABLED, paramMap);

		if (ValidationUtil.isNull(electricMeter.getConfiguration()))
		{
			electricMeter.setConfiguration(new ElectricMeterConfiguration());
		}

		electricMeter.getConfiguration().setEsmEnable((Integer)paramMap.get(ESM_ENABLED) == 1);
		electricMeter.getConfiguration().setTouEnable((Integer)paramMap.get(TOU_ENABLED) == 1);

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

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_PEAK_DEMAND,
						request));

		return response;
	}
}
