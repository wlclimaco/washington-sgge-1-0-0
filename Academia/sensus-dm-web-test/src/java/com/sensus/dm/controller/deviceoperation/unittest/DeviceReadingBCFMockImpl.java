package com.sensus.dm.controller.deviceoperation.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.device.bcf.IDeviceReadingBCF;
import com.sensus.dm.elec.device.model.IntervalRead;
import com.sensus.dm.elec.device.model.LoadProfile;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryIntervalReadResponse;
import com.sensus.dm.elec.device.model.response.InquiryLoadProfileResponse;
import com.sensus.dm.elec.device.model.response.TOUReadResponse;

/**
 * The Class DeviceReadingBCFMockImpl.
 */
@SuppressWarnings("deprecation")
public class DeviceReadingBCFMockImpl extends BaseMockImpl implements IDeviceReadingBCF
{
	/** The Constant SMARTPOINT_COUNT. */
	public static final Integer SMARTPOINT_COUNT = 100;

	/** The Constant SMARTPOINT_NAME. */
	public static final String SMARTPOINT_NAME = "SmartPoint %d";

	/** The Constant PROPERTY_NAME. */
	public static final String PROPERTY_NAME = "Property %d";

	/** The Constant PROPERTY_COUNT. */
	public static final Integer PROPERTY_COUNT = 20;

	/** The Constant GROUP_NAME. */
	public static final String CUSTOM_SEARCH = "Search %d";

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "C:\\QATEclipseWorkSpace\\epm-wui\\WebContent\\File.csv";

	/** The Constant TWENTY. */
	public static final Integer TWENTY = 20;

	/** The Constant ONE HUNDRED. */
	public static final Integer ONE_HUNDRED = 100;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchUpdatedMeterLoadProfile(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public InquiryLoadProfileResponse fetchUpdatedMeterLoadProfile(DeviceRequest request)
	{
		InquiryLoadProfileResponse loadProfileResponse = new InquiryLoadProfileResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			ArrayList<LoadProfile> loadProfileList = new ArrayList<LoadProfile>();

			LoadProfile loadProfile = new LoadProfile();

			loadProfile.setMeterFlexnetId(123456);
			loadProfile.setCurrentReading("64338.000 KWh");
			loadProfile.setCurrentHourConsumption("0.500 KWh");
			loadProfile.setCurrentMonthConsumption("N/A");
			loadProfile.setConsumptionPeakDemand("4.928 KW");
			loadProfile.setHourlyConsumptionStart(new Date());
			loadProfile.setHourlyConsumptionEnd(new Date());
			loadProfile.setCreateDate(new Date());

			loadProfileList.add(loadProfile);

			loadProfileResponse.setLoadProfiles(loadProfileList);
			loadProfileResponse.setOperationSuccess(true);

			return loadProfileResponse;
		}

		return (InquiryLoadProfileResponse)testOtherDefaultModes(loadProfileResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllWaterGasDataRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse fetchAllWaterGasDataRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse inquiryIntervalReadResponse = new InquiryIntervalReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			IntervalRead intervalRead = new IntervalRead();
			List<IntervalRead> intervalReadList = new ArrayList<IntervalRead>();

			for (int a = 0; a < 5; a++)
			{

				intervalRead = new IntervalRead();
				intervalRead.setChannels(new ArrayList<HashMap<String, String>>());

				Date date = new Date();

				HashMap<String, String> reads = new HashMap<String, String>();

				intervalRead.setSamplePoint("Hour");

				reads.put("energy, interval, rever...ergy detection1, seg: na", "TOTAL 25412 kw");
				intervalRead.getChannels().add(reads);

				reads = new HashMap<String, String>();
				reads.put("energy, interval, rever...ergy detection2, seg: na", 100
						+ new Double(1.5 * a)
						+ " KW");
				intervalRead.getChannels().add(reads);

				reads = new HashMap<String, String>();
				reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(2.2 *
						a)
						+ " KW");
				intervalRead.getChannels().add(reads);

				reads = new HashMap<String, String>();
				reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.5 *
						a)
						+ " KW");
				intervalRead.getChannels().add(reads);

				reads = new HashMap<String, String>();
				reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
						a)
						+ " KW");
				intervalRead.getChannels().add(reads);

				reads = new HashMap<String, String>();
				reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
						a)
						+ " KW");
				intervalRead.getChannels().add(reads);

				reads = new HashMap<String, String>();
				reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
						a)
						+ " KW");
				intervalRead.getChannels().add(reads);
				intervalRead.setCreateDate(date);
				intervalReadList.add(intervalRead);

			}

			inquiryIntervalReadResponse.setIntervalReads(intervalReadList);
			return inquiryIntervalReadResponse;
		}

		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#generateFileCSVWaterGasDataRead(com.sensus.dm.elec.device.model
	 * .request.DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse generateFileCSVWaterGasDataRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse inquiryIntervalReadResponse = new InquiryIntervalReadResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			inquiryIntervalReadResponse.setOperationSuccess(true);

			return inquiryIntervalReadResponse;
		}

		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllTOURead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public TOUReadResponse fetchAllTOURead(DeviceReadingRequest request)
	{
		TOUReadResponse tOUReadResponse = new TOUReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			tOUReadResponse.setOperationSuccess(false);
			String[] rowHeaders = new String[4];
			rowHeaders[0] = "Tier ID";
			rowHeaders[1] = "K_WH";
			rowHeaders[2] = "WH";
			rowHeaders[3] = "A";

			String[] row1 = new String[4];
			row1[0] = "Tier 1";
			row1[1] = "22.45";
			row1[2] = "56.475";
			row1[3] = "53";

			String[] row2 = new String[4];
			row2[0] = "Tier 2";
			row2[1] = "456.45";
			row2[2] = "4544.4345";
			row2[3] = "25";

			String[] row3 = new String[4];
			row3[0] = "Tier 3";
			row3[1] = "2256.4544";
			row3[2] = "5456.47445";
			row3[3] = "53";

			String[] row4 = new String[4];
			row4[0] = "Tier 4";
			row4[1] = "2256.4544";
			row4[2] = "5456.47445";
			row4[3] = "53";

			String[] row5 = new String[4];
			row5[0] = "Tier 5";
			row5[1] = "2256.4544";
			row5[2] = "5456.47445";
			row5[3] = "53";

			String[] row6 = new String[4];
			row6[0] = "Tier 6";
			row6[1] = "2256.4544";
			row6[2] = "5456.47445";
			row6[3] = "53";

			String[] row7 = new String[4];
			row7[0] = "Total";
			row7[1] = "2256.4544";
			row7[2] = "5456.47445";
			row7[3] = "0";

			String[][] flatTOUData = new String[8][];
			flatTOUData[0] = rowHeaders;
			flatTOUData[1] = row1;
			flatTOUData[2] = row2;
			flatTOUData[3] = row3;
			flatTOUData[4] = row4;
			flatTOUData[5] = row5;
			flatTOUData[6] = row6;
			flatTOUData[7] = row7;

			tOUReadResponse = new TOUReadResponse(flatTOUData);

			return tOUReadResponse;
		}

		return (TOUReadResponse)testOtherDefaultModes(tOUReadResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#generateFileCSVTOURead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public TOUReadResponse generateFileCSVTOURead(DeviceReadingRequest request)
	{
		/** Create a response */
		TOUReadResponse inquiryIntervalReadResponse = new TOUReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			inquiryIntervalReadResponse.setOperationSuccess(true);

			return inquiryIntervalReadResponse;
		}
		return (TOUReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllIntervalRead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse fetchAllIntervalRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse inquiryIntervalReadResponse = new InquiryIntervalReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			IntervalRead intervalRead;
			List<IntervalRead> intervalReadList = new ArrayList<IntervalRead>();

			Date EndDate = new Date("04/11/2014  00:00:00");

			if (request.getEndDate().getDay() == EndDate.getDay())
			{
				for (int a = 0; a < 5; a++)
				{

					intervalRead = new IntervalRead();
					intervalRead.setChannels(new ArrayList<HashMap<String, String>>());

					Date date = new Date();

					HashMap<String, String> reads = new HashMap<String, String>();

					intervalRead.setSamplePoint("Hour");

					reads.put("energy, interval, rever...ergy detection1, seg: na", "TOTAL 25412 kw");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection2, seg: na", 100
							+ new Double(1.5 * a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(2.2 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.5 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);
					intervalRead.setCreateDate(date);
					intervalReadList.add(intervalRead);

				}
			}

			inquiryIntervalReadResponse.setIntervalReads(intervalReadList);
			return inquiryIntervalReadResponse;
		}

		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#generateFileCSVIntervalRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse generateFileCSVIntervalRead(DeviceReadingRequest request)
	{
		/** Create a response */
		InquiryIntervalReadResponse inquiryIntervalReadResponse = new InquiryIntervalReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			inquiryIntervalReadResponse.setOperationSuccess(true);

			return inquiryIntervalReadResponse;
		}
		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllSnapshots(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse fetchAllSnapshots(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse inquiryIntervalReadResponse = new InquiryIntervalReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			IntervalRead intervalRead;
			List<IntervalRead> intervalReadList = new ArrayList<IntervalRead>();

			Date EndDate = new Date("04/11/2014  00:00:00");

			if (request.getEndDate().getDay() == EndDate.getDay())
			{
				for (int a = 0; a < 5; a++)
				{

					intervalRead = new IntervalRead();
					intervalRead.setChannels(new ArrayList<HashMap<String, String>>());

					Date date = new Date();

					HashMap<String, String> reads = new HashMap<String, String>();

					intervalRead.setSamplePoint("Hour");

					reads.put("energy, interval, rever...ergy detection1, seg: na", 100
							+ new Double(5.5 * a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection2, seg: na", 100
							+ new Double(5.50 * a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection3, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection4, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection6, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection7, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);
					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection8, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					intervalRead.getChannels().add(reads);
					intervalRead.setCreateDate(date);
					intervalReadList.add(intervalRead);

				}
			}

			inquiryIntervalReadResponse.setIntervalReads(intervalReadList);
			return inquiryIntervalReadResponse;
		}

		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#generateFileCSVSnapshot(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse generateFileCSVSnapshot(DeviceReadingRequest request)
	{
		/** Create a response */
		InquiryIntervalReadResponse inquiryIntervalReadResponse = new InquiryIntervalReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			inquiryIntervalReadResponse.setOperationSuccess(true);

			return inquiryIntervalReadResponse;
		}
		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}

	@Override
	public InquiryIntervalReadResponse fetchAllLoadProfileRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse inquiryLoadProfileResponse = new InquiryIntervalReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			IntervalRead loadProfile;
			List<IntervalRead> loadProfileList = new ArrayList<IntervalRead>();

			Date EndDate = new Date("04/11/2014  00:00:00");

			if (request.getEndDate().getDay() == EndDate.getDay())
			{
				for (int a = 0; a < 5; a++)
				{

					loadProfile = new IntervalRead();
					loadProfile.setChannels(new ArrayList<HashMap<String, String>>());

					Date date = new Date();

					HashMap<String, String> reads = new HashMap<String, String>();

					loadProfile.setSamplePoint("Hour");

					reads.put("energy, interval, rever...ergy detection1, seg: na", "TOTAL 25412 kw");
					loadProfile.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection2, seg: na", 100
							+ new Double(1.5 * a)
							+ " KW");
					loadProfile.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(2.2 *
							a)
							+ " KW");
					loadProfile.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.5 *
							a)
							+ " KW");
					loadProfile.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					loadProfile.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					loadProfile.getChannels().add(reads);

					reads = new HashMap<String, String>();
					reads.put("energy, interval, rever...ergy detection5, seg: na", 100 + new Double(1.6 *
							a)
							+ " KW");
					loadProfile.getChannels().add(reads);
					loadProfile.setCreateDate(date);
					loadProfileList.add(loadProfile);

				}
			}

			inquiryLoadProfileResponse.setIntervalReads(loadProfileList);
			return inquiryLoadProfileResponse;
		}

		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryLoadProfileResponse);
	}

	@Override
	public InquiryIntervalReadResponse generateFileCSVLoadProfileRead(DeviceReadingRequest request)
	{
		/** Create a response */
		InquiryIntervalReadResponse inquiryIntervalReadResponse = new InquiryIntervalReadResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			inquiryIntervalReadResponse.setOperationSuccess(true);

			return inquiryIntervalReadResponse;
		}
		return (InquiryIntervalReadResponse)testOtherDefaultModes(inquiryIntervalReadResponse);
	}
}
