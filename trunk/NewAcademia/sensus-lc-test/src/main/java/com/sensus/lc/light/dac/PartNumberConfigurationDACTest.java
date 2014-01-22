package com.sensus.lc.light.dac;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.PartNumber;
import com.sensus.lc.light.model.PartNumberConfiguration;

/**
 * The Class PartNumberConfigurationDACTest.
 */
@ContextConfiguration(locations = {
		"classpath:sensus-slc-dim-table-config-context.xml", "classpath:sensus-slc-part-number-context.xml"})
public class PartNumberConfigurationDACTest extends AbstractTestBaseDAC
{

	/** The Constant INVALID_MODEL_NUMBER. */
	private static final String INVALID_MODEL_NUMBER = "99999999999999";

	/** The Constant VALID_MODEL_NUMBER. */
	private static final String VALID_MODEL_NUMBER = "539449037C2310";

	/** The Constant MODEL_NUMBER_INVALID. */
	private static final String MODEL_NUMBER_INVALID = "sensus.mlc.lightvalidator.light.model.number.invalid";

	/** The part number configuration dac. */
	private IPartNumberConfigurationDAC partNumberConfigurationDAC;

	/**
	 * Gets the part number configuration dac.
	 * 
	 * @return the partNumberConfigurationDAC
	 */
	public IPartNumberConfigurationDAC getPartNumberConfigurationDAC()
	{
		return partNumberConfigurationDAC;
	}

	/**
	 * Sets the part number configuration dac.
	 * 
	 * @param partNumberConfigurationDAC the partNumberConfigurationDAC to set
	 */
	@Resource
	public void setPartNumberConfigurationDAC(IPartNumberConfigurationDAC partNumberConfigurationDAC)
	{
		this.partNumberConfigurationDAC = partNumberConfigurationDAC;
	}

	/**
	 * Test fetch all part numbers.
	 */
	@Test
	public void testFetchAllPartNumbers()
	{
		// Success situation
		InternalResultsResponse<PartNumber> response = getPartNumberConfigurationDAC().fetchAllPartNumbers();
		TestBaseUtil.assertResultResponse(response);
		Assert.assertTrue(response.hasResults());

	}

	/**
	 * Test fetch part number configurations by model number.
	 */
	@Test
	public void testFetchPartNumberConfigurationsByModelNumber()
	{
		// Success situation
		InternalResultsResponse<PartNumberConfiguration> response =
				getPartNumberConfigurationDAC().fetchPartNumberConfigurationsByModelNumber(VALID_MODEL_NUMBER);
		TestBaseUtil.assertResultResponse(response);

		// Error situation (with a wrong model number)
		response = getPartNumberConfigurationDAC().fetchPartNumberConfigurationsByModelNumber(INVALID_MODEL_NUMBER);
		assertMessages(response, MODEL_NUMBER_INVALID);

		// Error situation (with a wrong model number)
		response = getPartNumberConfigurationDAC().fetchPartNumberConfigurationsByModelNumber("539449044W2316");
		assertMessages(response, MODEL_NUMBER_INVALID);

	}

}
