package com.sensus.dm.water.device.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.device.model.ReceiveModeEnum;
import com.sensus.dm.common.device.model.TransmitModeEnum;

/**
 * The Class WaterGasMeterConfiguration.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class WaterGasMeterConfiguration extends SensusModel
{

	/** The firmware zigbee. */
	private String firmwareZigbee;

	/** The firmware bootflasher. */
	private String firmwareBootflasher;

	/** The firmware flexnet. */
	private String firmwareFlexnet;

	/** The firmware meter. */
	private String firmwareMeter;

	/** The premise id. */
	private String premiseId;

	/** The premise id device count. */
	private Integer premiseIdDeviceCount;

	/** The encrypted. */
	private String encrypted;

	/** The encryption supported. */
	private Boolean encryptionSupported;

	/** The install date. */
	private Date installDate;

	/** The meter serial number. */
	private String meterSerialNumber;

	/** The history scale. */
	private WaterHistoryScaleEnum historyScale;

	/** The meter unit. */
	private WaterMeterUnitEnum meterUnit;

	/** The reading resolution. */
	private WaterMeterResolutionEnum readingResolution;

	/** The leak detection threshold. */
	private WaterLeakDetectionThresholdEnum leakDetectionThreshold;

	/** The leak consecutive reads. */
	private WaterLeakDetectionConsecutiveReadsEnum leakConsecutiveReads;

	/** The broken pipe. */
	private BrokenPipeThresholdEnum brokenPipe;

	/** The reverse flow threshold. */
	private ReverseFlowDetectionThresholdEnum reverseFlowThreshold;

	/** The sample rate. Interval, in minutes, between samples. */
	private Integer sampleRate;

	/** The billing cycle. */
	private String billingCycle;

	/** The receiver channel frequency. */
	private BigDecimal receiverChannel;

	/** The transmit channel mask. */
	private Integer transmitChannelMask;

	/** The transmitter operational mode. */
	private TransmitModeEnum transmitterOperationalModeEnum;

	/** The receiver operational mode. */
	private ReceiveModeEnum receiverOperationalModeEnum;

	/**
	 * Instantiates a new water gas meter configuration.
	 */
	public WaterGasMeterConfiguration()
	{
	}

	/**
	 * Instantiates a new water gas meter configuration.
	 * 
	 * @param firmwareZigbeeParam the firmware zigbee param
	 */
	public WaterGasMeterConfiguration(String firmwareZigbeeParam)
	{
		setFirmwareZigbee(firmwareZigbeeParam);
	}

	/**
	 * Gets the firmware zigbee.
	 * 
	 * @return the firmware zigbee
	 */
	public String getFirmwareZigbee()
	{
		return firmwareZigbee;
	}

	/**
	 * Sets the firmware zigbee.
	 * 
	 * @param firmwareZigbee the new firmware zigbee
	 */
	public void setFirmwareZigbee(String firmwareZigbee)
	{
		this.firmwareZigbee = firmwareZigbee;
	}

	/**
	 * Gets the firmware bootflasher.
	 * 
	 * @return the firmware bootflasher
	 */
	public String getFirmwareBootflasher()
	{
		return firmwareBootflasher;
	}

	/**
	 * Sets the firmware bootflasher.
	 * 
	 * @param firmwareBootflasher the new firmware bootflasher
	 */
	public void setFirmwareBootflasher(String firmwareBootflasher)
	{
		this.firmwareBootflasher = firmwareBootflasher;
	}

	/**
	 * Gets the firmware flexnet.
	 * 
	 * @return the firmware flexnet
	 */
	public String getFirmwareFlexnet()
	{
		return firmwareFlexnet;
	}

	/**
	 * Sets the firmware flexnet.
	 * 
	 * @param firmwareFlexnet the new firmware flexnet
	 */
	public void setFirmwareFlexnet(String firmwareFlexnet)
	{
		this.firmwareFlexnet = firmwareFlexnet;
	}

	/**
	 * Gets the firmware meter.
	 * 
	 * @return the firmware meter
	 */
	public String getFirmwareMeter()
	{
		return firmwareMeter;
	}

	/**
	 * Sets the firmware meter.
	 * 
	 * @param firmwareMeter the new firmware meter
	 */
	public void setFirmwareMeter(String firmwareMeter)
	{
		this.firmwareMeter = firmwareMeter;
	}

	/**
	 * Gets the premise id.
	 * 
	 * @return the premise id
	 */
	public String getPremiseId()
	{
		return premiseId;
	}

	/**
	 * Sets the premise id.
	 * 
	 * @param premiseId the new premise id
	 */
	public void setPremiseId(String premiseId)
	{
		this.premiseId = premiseId;
	}

	/**
	 * Gets the premise id device count.
	 * 
	 * @return the premise id device count
	 */
	public Integer getPremiseIdDeviceCount()
	{
		return premiseIdDeviceCount;
	}

	/**
	 * Sets the premise id device count.
	 * 
	 * @param premiseIdDeviceCount the new premise id device count
	 */
	public void setPremiseIdDeviceCount(Integer premiseIdDeviceCount)
	{
		this.premiseIdDeviceCount = premiseIdDeviceCount;
	}

	/**
	 * Gets the encrypted.
	 * 
	 * @return the encrypted
	 */
	public String getEncrypted()
	{
		return encrypted;
	}

	/**
	 * Sets the encrypted.
	 * 
	 * @param encrypted the new encrypted
	 */
	public void setEncrypted(String encrypted)
	{
		this.encrypted = encrypted;
	}

	/**
	 * Gets the encryption supported.
	 * 
	 * @return the encryption supported
	 */
	public Boolean getEncryptionSupported()
	{
		return encryptionSupported;
	}

	/**
	 * Sets the encryption supported.
	 * 
	 * @param encryptionSupported the new encryption supported
	 */
	public void setEncryptionSupported(Boolean encryptionSupported)
	{
		this.encryptionSupported = encryptionSupported;
	}

	/**
	 * Gets the install date.
	 * 
	 * @return the install date
	 */
	public Date getInstallDate()
	{
		return installDate;
	}

	/**
	 * Sets the install date.
	 * 
	 * @param installDate the new install date
	 */
	public void setInstallDate(Date installDate)
	{
		this.installDate = installDate;
	}

	/**
	 * Gets the meter serial number.
	 * 
	 * @return the meter serial number
	 */
	public String getMeterSerialNumber()
	{
		return meterSerialNumber;
	}

	/**
	 * Sets the meter serial number.
	 * 
	 * @param meterSerialNumber the new meter serial number
	 */
	public void setMeterSerialNumber(String meterSerialNumber)
	{
		this.meterSerialNumber = meterSerialNumber;
	}

	/**
	 * Gets the reverse flow threshold.
	 * 
	 * @return the reverse flow threshold
	 */
	public ReverseFlowDetectionThresholdEnum getReverseFlowThreshold()
	{
		return reverseFlowThreshold;
	}

	/**
	 * Sets the reverse flow threshold.
	 * 
	 * @param reverseFlowThreshold the new reverse flow threshold
	 */
	public void setReverseFlowThreshold(ReverseFlowDetectionThresholdEnum reverseFlowThreshold)
	{
		this.reverseFlowThreshold = reverseFlowThreshold;
	}

	/**
	 * Sets the reverse flow threshold enum value.
	 * 
	 * @param value the new reverse flow threshold enum value
	 */
	public void setReverseFlowThresholdEnumValue(Integer value)
	{
		reverseFlowThreshold = ReverseFlowDetectionThresholdEnum.enumFromValue(value);
	}

	/**
	 * Gets the reverse flow threshold enum value.
	 * 
	 * @return the reverse flow threshold enum value
	 */
	public Integer getReverseFlowThresholdEnumValue()
	{
		if (getReverseFlowThreshold() != null)
		{
			return getReverseFlowThreshold().getValue();
		}

		return null;
	}

	/**
	 * Gets the sample rate.
	 * 
	 * @return the sampleRate
	 */
	public Integer getSampleRate()
	{
		return sampleRate;
	}

	/**
	 * Sets the sample rate.
	 * 
	 * @param sampleRate the sampleRate to set
	 */
	public void setSampleRate(Integer sampleRate)
	{
		this.sampleRate = sampleRate;
	}

	/**
	 * Gets the billing cycle.
	 * 
	 * @return the billingCycle
	 */
	public String getBillingCycle()
	{
		return billingCycle;
	}

	/**
	 * Sets the billing cycle.
	 * 
	 * @param billingCycle the billingCycle to set
	 */
	public void setBillingCycle(String billingCycle)
	{
		this.billingCycle = billingCycle;
	}

	/**
	 * Gets the receiver channel.
	 * 
	 * @return the receiverChannel
	 */
	public BigDecimal getReceiverChannel()
	{
		return receiverChannel;
	}

	/**
	 * Sets the receiver channel.
	 * 
	 * @param receiverChannel the receiverChannel to set
	 */
	public void setReceiverChannel(BigDecimal receiverChannel)
	{
		this.receiverChannel = receiverChannel;
	}

	/**
	 * Gets the transmit channel mask.
	 * 
	 * @return the transmitChannelMask
	 */
	public Integer getTransmitChannelMask()
	{
		return transmitChannelMask;
	}

	/**
	 * Sets the transmit channel mask.
	 * 
	 * @param transmitChannelMask the transmitChannelMask to set
	 */
	public void setTransmitChannelMask(Integer transmitChannelMask)
	{
		this.transmitChannelMask = transmitChannelMask;
	}

	/**
	 * Gets the transmitter operational mode.
	 * 
	 * @return the transmitterOperationalModeEnum
	 */
	public TransmitModeEnum getTransmitterOperationalModeEnum()
	{
		return transmitterOperationalModeEnum;
	}

	/**
	 * Sets the transmitter operational mode.
	 * 
	 * @param transmitterOperationalModeEnum the transmitterOperationalModeEnum to set
	 */
	public void setTransmitterOperationalModeEnum(TransmitModeEnum transmitterOperationalModeEnum)
	{
		this.transmitterOperationalModeEnum = transmitterOperationalModeEnum;
	}

	/**
	 * Sets the transmitter operational mode enum value.
	 * 
	 * @param waterMeterTypeEnumValue the new transmitter operational mode enum value
	 */
	public void setTransmitterOperationalModeEnumValue(Integer waterMeterTypeEnumValue)
	{
		transmitterOperationalModeEnum = TransmitModeEnum.enumForValue(waterMeterTypeEnumValue);
	}

	/**
	 * Gets the transmitter operational mode enum value.
	 * 
	 * @return the transmitter operational mode enum value
	 */
	public Integer getTransmitterOperationalModeEnumValue()
	{
		if (getTransmitterOperationalModeEnum() != null)
		{
			return getTransmitterOperationalModeEnum().getValue();
		}

		return null;
	}

	/**
	 * Gets the receiver operational mode.
	 * 
	 * @return the receiverOperationalModeEnum
	 */
	public ReceiveModeEnum getReceiverOperationalModeEnum()
	{
		return receiverOperationalModeEnum;
	}

	/**
	 * Sets the receiver operational mode.
	 * 
	 * @param receiverOperationalModeEnum the receiverOperationalModeEnum to set
	 */
	public void setReceiverOperationalModeEnum(ReceiveModeEnum receiverOperationalModeEnum)
	{
		this.receiverOperationalModeEnum = receiverOperationalModeEnum;
	}

	/**
	 * Sets the receiver operational mode enum value.
	 * 
	 * @param receiverOperationalModeEnumValue the new receiver operational mode enum value
	 */
	public void setReceiverOperationalModeEnumValue(Integer receiverOperationalModeEnumValue)
	{
		receiverOperationalModeEnum = ReceiveModeEnum.enumForValue(receiverOperationalModeEnumValue);
	}

	/**
	 * Gets the receiver operational mode enum value.
	 * 
	 * @return the receiver operational mode enum value
	 */
	public Integer getReceiverOperationalModeEnumValue()
	{
		if (getReceiverOperationalModeEnum() != null)
		{
			return getReceiverOperationalModeEnum().getValue();
		}

		return null;
	}

	/**
	 * Gets the history scale.
	 * 
	 * @return the history scale
	 */
	public WaterHistoryScaleEnum getHistoryScale()
	{
		return historyScale;
	}

	/**
	 * Sets the history scale.
	 * 
	 * @param historyScale the new history scale
	 */
	public void setHistoryScale(WaterHistoryScaleEnum historyScale)
	{
		this.historyScale = historyScale;
	}

	/**
	 * Sets the history scale value.
	 * 
	 * @param value the new history scale value
	 */
	public void setHistoryScaleValue(Integer value)
	{
		historyScale = WaterHistoryScaleEnum.enumFromValue(value);
	}

	/**
	 * Gets the history scale value.
	 * 
	 * @return the history scale value
	 */
	public Integer getHistoryScaleValue()
	{
		if (getHistoryScale() != null)
		{
			return getHistoryScale().getValue();
		}

		return null;
	}

	/**
	 * Gets the meter unit.
	 * 
	 * @return the meter unit
	 */
	public WaterMeterUnitEnum getMeterUnit()
	{
		return meterUnit;
	}

	/**
	 * Sets the meter unit.
	 * 
	 * @param meterUnit the new meter unit
	 */
	public void setMeterUnit(WaterMeterUnitEnum meterUnit)
	{
		this.meterUnit = meterUnit;
	}

	/**
	 * Sets the meter unit value.
	 * 
	 * @param value the new meter unit value
	 */
	public void setMeterUnitValue(Integer value)
	{
		meterUnit = WaterMeterUnitEnum.enumFromValue(value);
	}

	/**
	 * Gets the meter unit value.
	 * 
	 * @return the meter unit value
	 */
	public Integer getMeterUnitValue()
	{
		if (getMeterUnit() != null)
		{
			return getMeterUnit().getValue();
		}

		return null;
	}

	/**
	 * Gets the reading resolution.
	 * 
	 * @return the reading resolution
	 */
	public WaterMeterResolutionEnum getReadingResolution()
	{
		return readingResolution;
	}

	/**
	 * Sets the reading resolution.
	 * 
	 * @param readingResolution the new reading resolution
	 */
	public void setReadingResolution(WaterMeterResolutionEnum readingResolution)
	{
		this.readingResolution = readingResolution;
	}

	/**
	 * Sets the reading resolution value.
	 * 
	 * @param value the new reading resolution value
	 */
	public void setReadingResolutionValue(Integer value)
	{
		readingResolution = WaterMeterResolutionEnum.enumFromValue(value);
	}

	/**
	 * Gets the reading resolution value.
	 * 
	 * @return the reading resolution value
	 */
	public Integer getReadingResolutionValue()
	{
		if (getReadingResolution() != null)
		{
			return getReadingResolution().getValue();
		}

		return null;
	}

	/**
	 * Gets the leak detection threshold.
	 * 
	 * @return the leak detection threshold
	 */
	public WaterLeakDetectionThresholdEnum getLeakDetectionThreshold()
	{
		return leakDetectionThreshold;
	}

	/**
	 * Sets the leak detection threshold.
	 * 
	 * @param leakDetectionThreshold the new leak detection threshold
	 */
	public void setLeakDetectionThreshold(WaterLeakDetectionThresholdEnum leakDetectionThreshold)
	{
		this.leakDetectionThreshold = leakDetectionThreshold;
	}

	/**
	 * Sets the leak detection threshold value.
	 * 
	 * @param value the new leak detection threshold value
	 */
	public void setLeakDetectionThresholdValue(Integer value)
	{
		leakDetectionThreshold = WaterLeakDetectionThresholdEnum.enumFromValue(value);
	}

	/**
	 * Gets the leak detection threshold value.
	 * 
	 * @return the leak detection threshold value
	 */
	public Integer getLeakDetectionThresholdValue()
	{
		if (getLeakDetectionThreshold() != null)
		{
			return getLeakDetectionThreshold().getValue();
		}

		return null;
	}

	/**
	 * Gets the leak consecutive reads.
	 * 
	 * @return the leak consecutive reads
	 */
	public WaterLeakDetectionConsecutiveReadsEnum getLeakConsecutiveReads()
	{
		return leakConsecutiveReads;
	}

	/**
	 * Sets the leak consecutive reads.
	 * 
	 * @param leakConsecutiveReads the new leak consecutive reads
	 */
	public void setLeakConsecutiveReads(WaterLeakDetectionConsecutiveReadsEnum leakConsecutiveReads)
	{
		this.leakConsecutiveReads = leakConsecutiveReads;
	}

	/**
	 * Sets the leak consecutive reads value.
	 * 
	 * @param value the new leak consecutive reads value
	 */
	public void setLeakConsecutiveReadsValue(Integer value)
	{
		leakConsecutiveReads = WaterLeakDetectionConsecutiveReadsEnum.enumFromValue(value);
	}

	/**
	 * Gets the leak consecutive reads value.
	 * 
	 * @return the leak consecutive reads value
	 */
	public Integer getLeakConsecutiveReadsValue()
	{
		if (getLeakConsecutiveReads() != null)
		{
			return getLeakConsecutiveReads().getValue();
		}

		return null;
	}

	/**
	 * Gets the broken pipe.
	 * 
	 * @return the broken pipe
	 */
	public BrokenPipeThresholdEnum getBrokenPipe()
	{
		return brokenPipe;
	}

	/**
	 * Sets the broken pipe.
	 * 
	 * @param brokenPipe the new broken pipe
	 */
	public void setBrokenPipe(BrokenPipeThresholdEnum brokenPipe)
	{
		this.brokenPipe = brokenPipe;
	}

	/**
	 * Sets the broken pipe value.
	 * 
	 * @param value the new broken pipe value
	 */
	public void setBrokenPipeValue(Integer value)
	{
		brokenPipe = BrokenPipeThresholdEnum.enumFromValue(value);
	}

	/**
	 * Gets the broken pipe value.
	 * 
	 * @return the broken pipe value
	 */
	public Integer getBrokenPipeValue()
	{
		if (getBrokenPipe() != null)
		{
			return getBrokenPipe().getValue();
		}

		return null;
	}

	@Override
	public String toString()
	{
		return "WaterGasMeterConfiguration [getFirmwareZigbee()=" + getFirmwareZigbee() + ", getFirmwareBootflasher()="
				+ getFirmwareBootflasher() + ", getFirmwareFlexnet()=" + getFirmwareFlexnet() + ", getFirmwareMeter()="
				+ getFirmwareMeter() + ", getPremiseId()=" + getPremiseId() + ", getPremiseIdDeviceCount()="
				+ getPremiseIdDeviceCount() + ", getEncrypted()=" + getEncrypted()
				+ ", getEncryptionSupported()=" + getEncryptionSupported() + ", getInstallDate()=" + getInstallDate()
				+ ", getMeterSerialNumber()=" + getMeterSerialNumber() + ", getReverseFlowThreshold()="
				+ getReverseFlowThreshold() + ", getSampleRate()=" + getSampleRate() + ", getBillingCycle()="
				+ getBillingCycle() + ", getReceiverChannel()=" + getReceiverChannel() + ", getTransmitChannelMask()="
				+ getTransmitChannelMask() + ", getTransmitterOperationalModeEnum()="
				+ getTransmitterOperationalModeEnum() + ", getTransmitterOperationalModeEnumValue()="
				+ getTransmitterOperationalModeEnumValue() + ", getReceiverOperationalModeEnum()="
				+ getReceiverOperationalModeEnum() + ", getReceiverOperationalModeEnumValue()="
				+ getReceiverOperationalModeEnumValue() + ", getHistoryScale()=" + getHistoryScale()
				+ ", getHistoryScaleValue()=" + getHistoryScaleValue() + ", getMeterUnit()=" + getMeterUnit()
				+ ", getMeterUnitValue()=" + getMeterUnitValue() + ", getReadingResolution()=" + getReadingResolution()
				+ ", getReadingResolutionValue()=" + getReadingResolutionValue() + ", getLeakDetectionThreshold()="
				+ getLeakDetectionThreshold() + ", getLeakDetectionThresholdValue()="
				+ getLeakDetectionThresholdValue() + ", getLeakConsecutiveReads()=" + getLeakConsecutiveReads()
				+ ", getLeakConsecutiveReadsValue()=" + getLeakConsecutiveReadsValue() + ", getBrokenPipe()="
				+ getBrokenPipe() + ", getBrokenPipeValue()=" + getBrokenPipeValue() + ", toString()="
				+ super.toString() + "]";
	}

}
