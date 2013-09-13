package com.sensus.dm.gas.base;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Class AbstractTestGasBusiness.
 * 
 * @author QAT Global.
 */
public abstract class AbstractTestGasBusiness extends AbstractTestBaseBusiness
{

	/**
	 * Creates the gas meter.
	 * 
	 * @param deviceId the device id
	 * @param cityName the city name
	 * @param zipCode the zip code
	 * @param address the address
	 * @return the gas meter
	 */
	protected GasMeter createGasMeter(String deviceId, String cityName, String zipCode, String address)
	{
		return populatedDevice(new GasMeter(), DeviceTypeEnum.GAS_METER, deviceId, cityName, zipCode, address);
	}

}
