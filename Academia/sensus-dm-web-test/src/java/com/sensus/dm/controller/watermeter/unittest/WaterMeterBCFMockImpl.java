package com.sensus.dm.controller.watermeter.unittest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Note;
import com.sensus.cbof.model.Radio;
import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.response.WaterMeterResponse;

/**
 * The Class WaterMeterBCFMockImpl.
 */
public class WaterMeterBCFMockImpl extends BaseMockImpl implements IWaterMeterBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchAllWaterMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse inquiryDeviceResponse = new InquiryDeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			WaterMeter device = new WaterMeter();
			device.setDeviceType(DeviceTypeEnum.WATER_METER);
			device.setRadio(new Radio(BigInteger.valueOf(254453223)));

			List<Device> listDevices = new ArrayList<Device>();

			listDevices.add(device);

			inquiryDeviceResponse.setDevices(listDevices);

			return inquiryDeviceResponse;
		}

		return (InquiryDeviceResponse)testOtherDefaultModes(inquiryDeviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchWaterMeterById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchWaterMeterById(DeviceRequest deviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			WaterGasMeterConfiguration meterConfiguration = new WaterGasMeterConfiguration();
			WaterMeter waterMeter = new WaterMeter();
			DeviceModel deviceModel = new DeviceModel();

			meterConfiguration.setPremiseId("53456987");
			meterConfiguration.setInstallDate(new Date());
			meterConfiguration.setEncryptionSupported(new Boolean(true));
			meterConfiguration.setFirmwareFlexnet("002");
			waterMeter.setConfiguration(meterConfiguration);
			waterMeter.setDeviceId("1010M");

			Location location = new Location();
			location.setLatitude(32.72990);
			location.setLongitude(-117.05199);
			location.setTimeZoneInfo(new TimeZoneInfo("GMT"));
			location.setAddress("Street Test");
			location.setCity("Omaha");
			location.setZip("14587-780");
			location.setState("NE");

			Radio radio = new Radio(location);

			radio.setCustomerId("ACME");
			radio.setFlexNetId(deviceRequest.getDevices().get(0).getRadio().getFlexNetId());

			waterMeter.setRadio(radio);
			waterMeter.setQuarantine(true);

			List<Note> notesList = new ArrayList<Note>();
			Note note = new Note();
			note.setId(1);
			note.setText("This SmartPoint has been reading stale for two billing periods now. Having been tasked " +
					"with getting this baby to read again I looked at closest TGB, favorite buddies and analyized " +
					"routes for a few SmartPoints nearby. I changed some configuration option and was able to get " +
					"it reading again.");
			note.setCreateDate(new Date());
			note.setCreateUser("admin");
			notesList.add(note);
			note = new Note();
			note.setId(2);
			note.setText("This SmartPoint has never really worked right, looks like a hardware issue. I am going to look "
					+
					"into sending out a meterman to have a look. Ticket created in WOM system #123456.");
			note.setCreateDate(new Date());
			note.setCreateUser("adam.admin");
			notesList.add(note);
			waterMeter.setNotes(notesList);

			deviceModel.setDescription("description");
			deviceModel.setManufacture("sensus");
			waterMeter.setDeviceModel(deviceModel);

			List<Device> devices = new ArrayList<Device>();
			devices.add(waterMeter);

			deviceResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			deviceResponse.setDevices(devices);

			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#generateDevicesCSV(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse inquiryDeviceResponse = new InquiryDeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return inquiryDeviceResponse;
		}

		return (InquiryDeviceResponse)testOtherDefaultModes(inquiryDeviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchLeakReport(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public WaterMeterResponse fetchLeakReport(DeviceRequest waterMeterRequest)
	{
		WaterMeterResponse waterMeterResponse = new WaterMeterResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			List<WaterLeak> leakList = new ArrayList<WaterLeak>();

			WaterMeter waterMeter = new WaterMeter(new Radio(new BigInteger("1001"), "1001M"));

			WaterLeak leak = new WaterLeak(waterMeter, "05/10/2012 23:59:59", "10", "0", "0", "0", "0");

			leakList.add(leak);

			waterMeterResponse.setLeakList(leakList);
			waterMeterResponse.setOperationSuccess(Boolean.TRUE);

			return waterMeterResponse;
		}

		return (WaterMeterResponse)testOtherDefaultModes(waterMeterResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.water.device.bcf.IWaterMeterBCF#generateFileCSVLeakReport(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public WaterMeterResponse generateFileCSVLeakReport(DeviceRequest waterMeterRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchCommunication(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchCommunication(DeviceRequest deviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<WaterGasMeterStatusCount> waterGasMeterStatusCountList = new ArrayList<WaterGasMeterStatusCount>();
			WaterGasMeterStatusCount waterGasMeterStatusCount = new WaterGasMeterStatusCount();

			waterGasMeterStatusCount.setWaterGasMeterStatusEnum(WaterGasMeterStatusEnum.IDLE);
			waterGasMeterStatusCount.setAmount(10);
			waterGasMeterStatusCountList.add(waterGasMeterStatusCount);

			waterGasMeterStatusCount.setWaterGasMeterStatusEnum(WaterGasMeterStatusEnum.FIXED_BASE_LAT);
			waterGasMeterStatusCount.setAmount(20);
			waterGasMeterStatusCountList.add(waterGasMeterStatusCount);

			waterGasMeterStatusCount.setWaterGasMeterStatusEnum(WaterGasMeterStatusEnum.FIXED_BASE_MOM);
			waterGasMeterStatusCount.setAmount(30);
			waterGasMeterStatusCountList.add(waterGasMeterStatusCount);

			waterGasMeterStatusCount.setWaterGasMeterStatusEnum(WaterGasMeterStatusEnum.WALK_BY_DRIVE_BY);
			waterGasMeterStatusCount.setAmount(40);
			waterGasMeterStatusCountList.add(waterGasMeterStatusCount);

			deviceResponse.setWaterGasMeterStatusCount(waterGasMeterStatusCountList);

			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

}
