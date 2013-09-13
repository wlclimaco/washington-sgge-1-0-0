package com.sensus.dm.water.device.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.water.device.dac.IWaterMeterDAC;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class WaterMeterDACImpl.
 *
 * @author QAT Global.
 */
public class WaterMeterDACImpl extends SqlSessionDaoSupport implements IWaterMeterDAC
{

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The Constant WATER_MAP. */
	private static final String WATER_MAP = "WaterMeterMap.";

	/** The Constant FETCH_ALL_WATER_METERS. */
	private static final String FETCH_ALL_WATER_METERS = WATER_MAP + "fetchAllWaterMeters";

	/** The Constant FETCH_ALL_WATER_METER_TOTAL_ROWS. */
	private static final String FETCH_ALL_WATER_METER_TOTAL_ROWS = WATER_MAP + "waterMetersPaginationTotalRows";

	/** The Constant FETCH_WATER_METER_BY_ID. */
	private static final String FETCH_WATER_METER_BY_ID = WATER_MAP + "fetchWaterMeterById";

	/** The Constant FETCH_WATER_METER_STATUS_COUNT. */
	private static final String FETCH_COMMUNICATION = WATER_MAP + "fetchCommunication";

	/** The Constant FETCH_LEAK_REPORT. */
	private static final String FETCH_LEAK_REPORT = WATER_MAP + "fetchLeakReport";

	/** The Constant LEAK_REPORT. */
	private static final String LEAK_REPORT = "p_leak_report";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "p_customer_id";

	/** The Constant ADDRESS. */
	private static final String ADDRESS = "p_address";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.dac.IWaterMeterDAC#fetchAllWaterMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<WaterMeter> fetchAllWaterMeters(InquiryDeviceRequest request)
	{
		InternalResultsResponse<WaterMeter> response = new InternalResultsResponse<WaterMeter>();

		if (request.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), FETCH_ALL_WATER_METER_TOTAL_ROWS, request));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_WATER_METERS, request));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.dac.IWaterMeterDAC#fetchWaterMeterById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchWaterMeterById(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_WATER_METER_BY_ID,
				deviceRequest.getFirstDevice(), response);

		DMConvertUtil.checkResult(response);

		return response;
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
		InternalResultsResponse<WaterGasMeterStatusCount> response =
				new InternalResultsResponse<WaterGasMeterStatusCount>();

		return SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_COMMUNICATION, deviceRequest, response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.dac.IWaterMeterDAC#fetchLeakReport(com.sensus.dm.water.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<WaterLeak> fetchLeakReport(DeviceRequest request)
	{
		InternalResultsResponse<WaterLeak> response = new InternalResultsResponse<WaterLeak>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(THREE);
		paramMap.put(ADDRESS, 0);
		paramMap.put(CUSTOMER_ID, request.getTenant().getKey());

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_LEAK_REPORT, paramMap, response);

		DMConvertUtil.checkResult(response, paramMap, LEAK_REPORT);

		return response;
	}
}
