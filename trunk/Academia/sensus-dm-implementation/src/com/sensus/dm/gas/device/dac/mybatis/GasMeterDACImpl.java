package com.sensus.dm.gas.device.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.gas.device.dac.IGasMeterDAC;
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Class GasMeterDACImpl.
 * 
 * @author QAT Global.
 */
public class GasMeterDACImpl extends SqlSessionDaoSupport implements IGasMeterDAC
{

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant GAS_MAP. */
	private static final String GAS_MAP = "GasMeterMap.";

	/** The Constant FETCH_ALL_GAS_METERS. */
	private static final String FETCH_ALL_GAS_METERS = GAS_MAP + "fetchAllGasMeters";

	/** The Constant FETCH_ALL_GAS_METER_TOTAL_ROWS. */
	private static final String FETCH_ALL_GAS_METER_TOTAL_ROWS = GAS_MAP + "gasMetersPaginationTotalRows";

	/** The Constant FETCH_GAS_METER_BY_ID. */
	private static final String FETCH_GAS_METER_BY_ID = GAS_MAP + "fetchGasMeterById";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.dac.IGasMeterDAC#fetchAllGasMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<GasMeter> fetchAllGasMeters(InquiryDeviceRequest request)
	{
		InternalResultsResponse<GasMeter> response = new InternalResultsResponse<GasMeter>();

		if (request.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), FETCH_ALL_GAS_METER_TOTAL_ROWS, request));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_GAS_METERS, request));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.gas.device.dac.IGasMeterDAC#fetchGasMeterById(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchGasMeterById(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_GAS_METER_BY_ID,
				deviceRequest.getFirstDevice(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}
}
