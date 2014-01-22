package com.sensus.lc.light.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.PartNumber;
import com.sensus.lc.light.model.PartNumberConfiguration;

/**
 * The Interface ILightingConfigurationDAC.
 */
public interface IPartNumberConfigurationDAC
{

	/**
	 * Fetch all Lighting Configuration Part Number instances.
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<PartNumber> fetchAllPartNumbers();

	/**
	 * Fetch a specific instance of Lighting Configuration Part Number based on the model number from a Light.
	 * 
	 * @param modelNumber the model number
	 * @return the internal results response
	 */
	InternalResultsResponse<PartNumberConfiguration> fetchPartNumberConfigurationsByModelNumber(String modelNumber);
}
