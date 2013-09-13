package com.sensus.dm.water.base;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class AbstractTestWaterBusiness.
 * 
 * @author QAT Global.
 */
public abstract class AbstractTestWaterBusiness extends AbstractTestBaseBusiness
{

	/**
	 * Creates the water meter.
	 * 
	 * @param deviceId the device id
	 * @param cityName the city name
	 * @param zipCode the zip code
	 * @param address the address
	 * @return the water meter
	 */
	protected WaterMeter createWaterMeter(String deviceId, String cityName, String zipCode, String address)
	{
		return populatedDevice(new WaterMeter(), DeviceTypeEnum.WATER_METER, deviceId, cityName, zipCode, address);
	}

}
