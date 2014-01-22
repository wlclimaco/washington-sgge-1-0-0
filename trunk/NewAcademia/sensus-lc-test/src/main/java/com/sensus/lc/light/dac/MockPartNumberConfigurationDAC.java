package com.sensus.lc.light.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.PartNumber;
import com.sensus.lc.light.model.PartNumberConfiguration;

/**
 * Mock class for PartNumberConfigurationDAC.
 * 
 * @author Thiago
 */
public class MockPartNumberConfigurationDAC implements IPartNumberConfigurationDAC
{

	@Override
	public InternalResultsResponse<PartNumber> fetchAllPartNumbers()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<PartNumberConfiguration> fetchPartNumberConfigurationsByModelNumber(
			String modelNumber)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
