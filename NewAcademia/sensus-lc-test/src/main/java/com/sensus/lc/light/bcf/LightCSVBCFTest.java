package com.sensus.lc.light.bcf;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.response.CSVResponse;

/**
 * The Class LightCSVBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/light/lightcsvbcfimpltest.xml"})
public class LightCSVBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant FILE_NAME_TEMP_CSV. */
	private static final String FILE_NAME_TEMP_CSV = "temp.csv";

	/** The light csvbcf. */
	private ILightCSVBCF lightCSVBCF;

	/**
	 * Gets the light csvbcf.
	 * 
	 * @return the lightCSVBCF
	 */
	public ILightCSVBCF getLightCSVBCF()
	{
		return lightCSVBCF;
	}

	/**
	 * Sets the light csvbcf.
	 * 
	 * @param lightCSVBCF the lightCSVBCF to set
	 */
	@Resource(name = "lightCSVBCFTarget")
	public void setLightCSVBCF(ILightCSVBCF lightCSVBCF)
	{
		this.lightCSVBCF = lightCSVBCF;
	}

	/**
	 * Test generate light history file csv.
	 */
	@Test
	public void testGenerateLightHistoryFileCSV()
	{
		// Success situation
		LightHistoryCSVRequest request = new LightHistoryCSVRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setFileName(FILE_NAME_TEMP_CSV);

		CSVResponse response = getLightCSVBCF().generateLightHistoryFileCSV(request);
		Assert.assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test generate light summary file csv.
	 */
	@Test
	public void testGenerateLightSummaryFileCSV()
	{
		// Success situation
		LightSummaryCSVRequest request = new LightSummaryCSVRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setFileName(FILE_NAME_TEMP_CSV);

		CSVResponse response = getLightCSVBCF().generateLightSummaryFileCSV(request);
		Assert.assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test generate light detail file csv.
	 */
	@Test
	public void testGenerateLightDetailFileCSV()
	{
		// Success situation
		LightCSVRequest request = new LightCSVRequest();
		request.setFileName(FILE_NAME_TEMP_CSV);
		request.setUserContext(TestBaseUtil.createUserContext());

		CSVResponse response = getLightCSVBCF().generateLightDetailFileCSV(request);
		Assert.assertTrue(response.isOperationSuccess());

	}

}
