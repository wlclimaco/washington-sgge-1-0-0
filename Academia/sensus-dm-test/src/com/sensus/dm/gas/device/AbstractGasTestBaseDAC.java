/**
 *
 */
package com.sensus.dm.gas.device;

import javax.annotation.Resource;

import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.gas.device.dac.IGasMeterDAC;

/**
 * The Class AbstractGasTestBaseDAC.
 *
 * @author QATEmployee
 */
public abstract class AbstractGasTestBaseDAC extends AbstractTestBaseDAC
{

	/** The gas meter dac. */
	private IGasMeterDAC gasMeterDAC;

	/**
	 * Gets the gas meter dac.
	 *
	 * @return the gasMeterDAC
	 */
	public IGasMeterDAC getGasMeterDAC()
	{
		return gasMeterDAC;
	}

	/**
	 * Sets the gas meter dac.
	 *
	 * @param gasMeterDAC the gasMeterDAC to set
	 */
	@Resource
	public void setGasMeterDAC(IGasMeterDAC gasMeterDAC)
	{
		this.gasMeterDAC = gasMeterDAC;
	}

}
