package com.sensus.dm.common.device.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.cbof.model.Device;

/**
 * The Class InquiryDeviceResponse.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class InquiryDeviceResponse extends InquiryResponse
{

	/** The devices. */
	private List<Device> devices;

	/**
	 * @return the devices
	 */
	public List<Device> getDevices()
	{
		return devices;
	}

	/**
	 * @param devices the devices to set
	 * 
	 */
	public void setDevices(List<Device> devices)
	{
		this.devices = devices;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.common.model.response.InquiryResponse#addResults(java.util
	 * .Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setDevices(new ArrayList<Device>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryDeviceResponse [devices=" + devices + ", getDevices()="
				+ getDevices() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
