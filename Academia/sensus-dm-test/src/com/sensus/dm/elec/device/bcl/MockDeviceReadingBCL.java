package com.sensus.dm.elec.device.bcl;

import com.sensus.bcf.meterreading.service.MeterReadingService;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.IntervalRead;
import com.sensus.dm.elec.device.model.LoadProfile;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Class MockDeviceReadingBCL.
 * 
 * @author QAT Global.
 */
public class MockDeviceReadingBCL extends AbstractMockBase implements IDeviceReadingBCL
{

	/** The meter reading service. */
	private MeterReadingService meterReadingService;

	/**
	 * Gets the meter reading service.
	 * 
	 * @return the meter reading service
	 */
	public MeterReadingService getMeterReadingService()
	{
		return meterReadingService;
	}

	/**
	 * Sets the meter reading service.
	 * 
	 * @param meterReadingService the new meter reading service
	 */
	public void setMeterReadingService(MeterReadingService meterReadingService)
	{
		this.meterReadingService = meterReadingService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllTOURead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public InternalResultsResponse<String[][]> fetchAllTOURead(DeviceReadingRequest request)
	{

		InternalResultsResponse<String[][]> response = new InternalResultsResponse<String[][]>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createTOURead());
			return response;
		}

		return (InternalResultsResponse<String[][]>)verifyOtherSituations();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#generateFileCSVTOURead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVTOURead(DeviceReadingRequest request)
	{
		InternalResultsResponse<String[][]> response = new InternalResultsResponse<String[][]>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createTOURead());
			return response;
		}

		return verifyOtherSituations();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllIntervalRead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<IntervalRead> fetchAllIntervalRead(DeviceReadingRequest request)
	{
		return createIntervalReadResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#generateFileCSVIntervalRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVIntervalRead(DeviceReadingRequest request)
	{
		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createSnapshot());
			return response;
		}

		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllSnapshots(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<IntervalRead> fetchAllSnapshots(DeviceReadingRequest request)
	{
		return createIntervalReadResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllWaterGasDataRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<IntervalRead> fetchAllWaterGasDataRead(DeviceReadingRequest request)
	{
		return createWaterDataReadingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#generateFileCSVWaterGasDataRead(com.sensus.dm.elec.device.model
	 * .request.DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVWaterGasDataRead(DeviceReadingRequest request)
	{
		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createSnapshot());
			return response;
		}

		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#generateFileCSVSnapshot(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVSnapshot(DeviceReadingRequest request)
	{
		return createSnapshotResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchUpdatedMeterLoadProfile(com.sensus.dm.elec.device.model.request
	 * .
	 * MeterRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<LoadProfile> fetchUpdatedMeterLoadProfile(DeviceRequest request)
	{
		InternalResultsResponse<LoadProfile> response = new InternalResultsResponse<LoadProfile>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			switch (request.getFirstDevice().getDeviceType())
			{
				case ELECTRIC_METER:
					response.addResult(TestBaseUtil.createElectricLoadProfile());
					break;
				case WATER_METER:
					response.addResult(TestBaseUtil.createWaterLoadProfile());
					break;
				default:
					break;
			}
			return response;
		}

		return (InternalResultsResponse<LoadProfile>)verifyOtherSituations();
	}

	/**
	 * Creates the interval read response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<IntervalRead> createIntervalReadResponse()
	{
		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createIntervalRead(ServiceTypeEnum.ELECTRIC));
			return response;
		}

		return (InternalResultsResponse<IntervalRead>)verifyOtherSituations();
	}

	/**
	 * Creates the water data reading response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<IntervalRead> createWaterDataReadingResponse()
	{
		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createIntervalRead(ServiceTypeEnum.WATER));
			return response;
		}

		return (InternalResultsResponse<IntervalRead>)verifyOtherSituations();
	}

	/**
	 * Creates the snapshot response.
	 * 
	 * @return the internal response
	 */
	private InternalResponse createSnapshotResponse()
	{
		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createSnapshot());
			return response;
		}

		return verifyOtherSituations();
	}

	@Override
	public InternalResultsResponse<IntervalRead> fetchAllLoadProfileRead(DeviceReadingRequest request)
	{
		return createIntervalReadResponse();
	}

	@Override
	public InternalResponse generateFileCSVLoadProfileRead(DeviceReadingRequest request)
	{

		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createSnapshot());
			return response;
		}

		return verifyOtherSituations();
	}

}
