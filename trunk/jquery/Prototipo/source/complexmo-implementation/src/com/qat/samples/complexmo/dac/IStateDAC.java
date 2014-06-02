package com.qat.samples.complexmo.dac;

import com.qat.samples.complexmo.model.State;

/**
 * The Interface IStateDAC.
 */
public interface IStateDAC
{

	/**
	 * Fetch state by code.
	 * 
	 * @param code the code
	 * @return the state
	 */
	State fetchStateByCode(String code);
}
