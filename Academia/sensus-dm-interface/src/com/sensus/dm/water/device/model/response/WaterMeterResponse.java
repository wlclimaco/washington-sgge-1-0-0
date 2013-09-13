package com.sensus.dm.water.device.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.water.device.model.WaterLeak;

/**
 * The Class WaterMeterResponse.
 * 
 * @author QAT Global
 */
public class WaterMeterResponse extends Response
{

	/** The leak list. */
	private List<WaterLeak> leakList;

	/**
	 * Gets the leak list.
	 * 
	 * @return the leak list
	 */
	public List<WaterLeak> getLeakList()
	{
		return leakList;
	}

	/**
	 * Sets the leak list.
	 * 
	 * @param leakList the new leak list
	 */
	public void setLeakList(List<WaterLeak> leakList)
	{
		this.leakList = leakList;
	}

	@Override
	public String toString()
	{
		return "WaterMeterResponse [getLeakList()=" + getLeakList() + ", toString()=" + super.toString() + "]";
	}

}
