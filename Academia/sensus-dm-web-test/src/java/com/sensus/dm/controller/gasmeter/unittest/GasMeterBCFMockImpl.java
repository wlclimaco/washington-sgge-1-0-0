package com.sensus.dm.controller.gasmeter.unittest;

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
import com.sensus.dm.gas.device.bcf.IGasMeterBCF;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;

/**
 * The Class GasMeterBCFMockImpl.
 */
public class GasMeterBCFMockImpl extends BaseMockImpl implements IGasMeterBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcf.IGasMeterBCF#fetchAllGasMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse inquiryDeviceResponse = new InquiryDeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			GasMeter device = new GasMeter();
			device.setDeviceType(DeviceTypeEnum.GAS_METER);
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
	 * @see
	 * com.sensus.dm.gas.device.bcf.IGasMeterBCF#fetchGasMeterById(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchGasMeterById(DeviceRequest deviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			WaterGasMeterConfiguration meterConfiguration = new WaterGasMeterConfiguration();
			GasMeter gasMeter = new GasMeter();
			DeviceModel deviceModel = new DeviceModel();

			meterConfiguration.setPremiseId("53456987");
			meterConfiguration.setInstallDate(new Date());
			meterConfiguration.setEncryptionSupported(new Boolean(true));
			meterConfiguration.setFirmwareFlexnet("002");
			gasMeter.setConfiguration(meterConfiguration);
			gasMeter.setDeviceId("1010M");

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

			gasMeter.setRadio(radio);
			gasMeter.setQuarantine(true);

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
			gasMeter.setNotes(notesList);

			deviceModel.setDescription("description");
			deviceModel.setManufacture("sensus");
			gasMeter.setDeviceModel(deviceModel);

			List<Device> devices = new ArrayList<Device>();
			devices.add(gasMeter);

			deviceResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			deviceResponse.setDevices(devices);

			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcf.IGasMeterBCF#generateDevicesCSV(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryMeterRequest)
	{
		InquiryDeviceResponse inquiryDeviceResponse = new InquiryDeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return inquiryDeviceResponse;
		}

		return (InquiryDeviceResponse)testOtherDefaultModes(inquiryDeviceResponse);
	}

}