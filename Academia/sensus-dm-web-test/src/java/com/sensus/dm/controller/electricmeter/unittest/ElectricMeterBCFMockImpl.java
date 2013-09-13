package com.sensus.dm.controller.electricmeter.unittest;

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
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceConfiguration;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryPeakDemandResponse;

/**
 * The Class ElectricMeterBCFMockImpl.
 */
public class ElectricMeterBCFMockImpl extends BaseMockImpl implements IElectricMeterBCF
{

	/**
	 * Fetch all.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	@Override
	public InquiryDeviceResponse fetchAllElectricMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse inquiryDeviceResponse = new InquiryDeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			ElectricMeter device = new ElectricMeter();
			device.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
			device.setRadio(new Radio(BigInteger.valueOf(254453223)));

			List<Device> listDevices = new ArrayList<Device>();

			listDevices.add(device);

			inquiryDeviceResponse.setDevices(listDevices);

			return inquiryDeviceResponse;
		}

		return (InquiryDeviceResponse)testOtherDefaultModes(inquiryDeviceResponse);
	}

	/**
	 * Generate devices csv.
	 * 
	 * @param inquiryMeterRequest the inquiry meter request
	 * @return the inquiry device response
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

	/**
	 * Fetch all lc ms.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	@Override
	public InquiryDeviceResponse fetchAllLCMs(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse inquiryDeviceResponse = new InquiryDeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			LCM device = new LCM();
			device.setDeviceType(DeviceTypeEnum.LCM);
			device.setRadio(new Radio(BigInteger.valueOf(254453223)));

			List<Device> listDevices = new ArrayList<Device>();

			listDevices.add(device);

			inquiryDeviceResponse.setDevices(listDevices);

			return inquiryDeviceResponse;
		}
		else if (getMode().equals(ModeEnum.MODE_EXCEPTION))
		{
			LCM device = new LCM();
			device.setDeviceType(DeviceTypeEnum.LCM);

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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllHanDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse inquiryDeviceResponse = new InquiryDeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			HanDevice device = new HanDevice();
			device.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
			device.setRadio(new Radio(BigInteger.valueOf(254453223)));

			List<Device> listDevices = new ArrayList<Device>();

			listDevices.add(device);

			inquiryDeviceResponse.setDevices(listDevices);

			return inquiryDeviceResponse;
		}
		else if (getMode().equals(ModeEnum.MODE_EXCEPTION))
		{
			HanDevice device = new HanDevice();
			device.setDeviceType(DeviceTypeEnum.HAN_DEVICE);

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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchDeviceById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchDeviceById(DeviceRequest deviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			ElectricMeterConfiguration meterConfiguration = new ElectricMeterConfiguration();
			ElectricMeter electricMeter = new ElectricMeter();
			DeviceModel deviceModel = new DeviceModel();

			meterConfiguration.setPremiseId("53456987");
			meterConfiguration.setInstallDate(new Date());
			meterConfiguration.setEncryptionSupported(new Boolean(true));
			meterConfiguration.setFirmwareFlexnet("002");
			electricMeter.setConfiguration(meterConfiguration);
			electricMeter.setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.INSTALLED);
			electricMeter.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
			electricMeter.setDeviceId("1010M");

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

			if (!ValidationUtil.isNull(deviceRequest.getDevices())
					&& !ValidationUtil.isNull(deviceRequest.getDevices().get(0))
					&& !ValidationUtil.isNull(deviceRequest.getDevices().get(0).getRadio())
					&& !ValidationUtil.isNull(deviceRequest.getDevices().get(0).getRadio().getFlexNetId()))
			{
				radio.setFlexNetId(deviceRequest.getDevices().get(0).getRadio().getFlexNetId());
			}

			electricMeter.setRadio(radio);
			electricMeter.setQuarantine(true);

			List<Note> notesList = new ArrayList<Note>();
			Note note = new Note();
			note.setId(1);
			note.setText("This SmartPoint has been reading stale for two billing periods now. Having been tasked with getting this baby to read again I looked at closest TGB, favorite buddies and analyized routes for a few SmartPoints nearby. I changed some configuration option and was able to get it reading again.");
			note.setCreateDate(new Date());
			note.setCreateUser("admin");
			notesList.add(note);
			note = new Note();
			note.setId(2);
			note.setText("This SmartPoint has never really worked right, looks like a hardware issue. I am going to look into sending out a meterman to have a look. Ticket created in WOM system #123456.");
			note.setCreateDate(new Date());
			note.setCreateUser("adam.admin");
			notesList.add(note);
			electricMeter.setNotes(notesList);

			deviceModel.setDescription("description");
			deviceModel.setManufacture("sensus");
			electricMeter.setDeviceModel(deviceModel);

			List<Device> devices = new ArrayList<Device>();
			devices.add(electricMeter);

			deviceResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			deviceResponse.setDevices(devices);

			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/**
	 * Generate demand read csv.
	 * 
	 * @param deviceReadingRequest the inquiry demand read request
	 * @return the inquiry demand read response
	 */
	@Override
	public InquiryPeakDemandResponse generatePeakDemandCSV(DeviceReadingRequest request)
	{
		InquiryPeakDemandResponse inquiryPeakDemandResponse = new InquiryPeakDemandResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return inquiryPeakDemandResponse;
		}

		return (InquiryPeakDemandResponse)testOtherDefaultModes(inquiryPeakDemandResponse);
	}

	/**
	 * Fetch lifecycle states.
	 * 
	 * @param request the request
	 * @return the device response
	 */
	@Override
	public DeviceResponse fetchLifecycleStates(DeviceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllHanDevicesByMeter(com.sensus.dm.elec.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllHanDevicesByMeter(DeviceRequest deviceRequest)
	{
		/** Device Response */
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<Device> hanDevices = new ArrayList<Device>();

			for (int i = 1; i < 5; i++)
			{
				HanDevice hanDevice = new HanDevice();
				HanDeviceConfiguration hanDeviceConfiguration = new HanDeviceConfiguration();
				hanDeviceConfiguration.setCreateDate(new Date());
				hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.THERMOSTAT);
				hanDevice.setElectricMeterFlexNetId(deviceRequest.getDevices().get(0).getRadio().getFlexNetId());
				hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.JOINED);
				hanDevice.setConfiguration(hanDeviceConfiguration);
				hanDevices.add(hanDevice);
			}

			deviceResponse.setDevices(hanDevices);
			deviceResponse.setOperationSuccess(true);

			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllManufactureByHanDeviceType(com.sensus.dm.elec.device.model
	 * .request
	 * .
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			List<Device> hanDevices = new ArrayList<Device>();

			for (int i = 0; i < 10; i++)
			{
				HanDevice hanDevice = new HanDevice();
				DeviceModel deviceModel = new DeviceModel();
				deviceModel.setManufacture("Man" + i);
				hanDevice.setDeviceModel(deviceModel);
				hanDevices.add(hanDevice);
			}

			response.setOperationSuccess(true);
			response.setDevices(hanDevices);

			return response;
		}

		return (DeviceResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllModelByHanDeviceType(com.sensus.dm.elec.device.model.request
	 * .
	 * DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchAllModelByDeviceSubType(DeviceRequest deviceRequest)
	{

		DeviceResponse response = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			List<Device> hanDevices = new ArrayList<Device>();

			for (int i = 0; i < 10; i++)
			{
				HanDevice hanDevice = new HanDevice();
				DeviceModel deviceModel = new DeviceModel();
				deviceModel.setDescription("Model " + i);
				hanDevice.setDeviceModel(deviceModel);
				hanDevices.add(hanDevice);
			}

			response.setOperationSuccess(true);
			response.setDevices(hanDevices);

			return response;
		}

		return (DeviceResponse)testOtherDefaultModes(response);
	}

	/**
	 * Fetch all connection state.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	@Override
	public DeviceResponse fetchAllConnectionState(DeviceRequest deviceRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllDemandRead(com.sensus.dm.elec.device.model.request.
	 * InquiryDemandReadRequest)
	 */
	@Override
	public InquiryPeakDemandResponse fetchAllPeakDemand(DeviceReadingRequest request)
	{
		InquiryPeakDemandResponse response = new InquiryPeakDemandResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			PeakDemand peakDemand;

			List<PeakDemand> peakDemandList = new ArrayList<PeakDemand>();

			peakDemand = new PeakDemand();

			peakDemand.setReadingDate(new Date());
			peakDemand.setResetDate(new Date());
			peakDemand.setPeakTime(new Date());
			peakDemand.setCounter(1);
			peakDemand.setDemandUnits("KW");

			response.setPeakDemands(peakDemandList);

			return response;

		}
		return (InquiryPeakDemandResponse)testOtherDefaultModes(response);
	}

	@Override
	public DeviceResponse fetchLCMRelaysByDevice(DeviceRequest deviceRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}