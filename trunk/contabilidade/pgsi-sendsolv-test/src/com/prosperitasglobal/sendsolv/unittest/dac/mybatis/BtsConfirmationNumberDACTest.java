package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.dac.IBtsConfirmationNumberDAC;
import com.prosperitasglobal.sendsolv.model.BtsConfirmationNumber;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class BtsConfirmationNumberDACTest. This test covers BTS confirmation numbers.
 */
public class BtsConfirmationNumberDACTest extends AbstractTestBaseDAC
{
	/** The location dac. */
	private IBtsConfirmationNumberDAC btsConfirmationNumberDAC;

	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(BtsConfirmationNumberDACTest.class);

	/** The prefix number assigned by BTS. */
	private static final int PGSI_PREFIX_NUMBER = 1685;

	/**
	 * Common routine for checking the results of the DAC method against the expected results.
	 *
	 * @param response The response from the DAC method call.
	 * @param btsConfirmationNumber The BTS confirmation number object from the DAC method call.
	 * @param prefixNumber The prefix number assigned by BTS.
	 * @param sequence The expected sequence.
	 */
	private static void checkResults(InternalResponse response, BtsConfirmationNumber btsConfirmationNumber,
			Integer prefixNumber, Integer sequence)
	{
		CommonTestRoutines.assertResponse(response);

		LOG.debug("BTS Confirmation Number: " + CommonRoutines.formatBtsConfirmationNumber(btsConfirmationNumber));

		Assert.assertEquals("Error, PrefixNumber " + btsConfirmationNumber.getPrefixNumber() + " not PrefixNumber "
				+ prefixNumber, btsConfirmationNumber.getPrefixNumber(), prefixNumber);
		Assert.assertEquals("Error, Sequence " + btsConfirmationNumber.getSequence() + " not Sequence " + sequence,
				Integer.toString(btsConfirmationNumber.getSequence()), Integer.toString(sequence));
	}

	/**
	 * Gets the BTS confirmation number dac.
	 *
	 * @return The BTS confirmation number dac
	 */
	public IBtsConfirmationNumberDAC getBtsConfirmationNumberDAC()
	{
		return btsConfirmationNumberDAC;
	}

	/**
	 * Sets the BTS confirmation number dac.
	 *
	 * @param btsConfirmationNumberDAC The BTS confirmation number dac
	 */
	@Resource
	public void setBtsConfirmationNumberDAC(IBtsConfirmationNumberDAC btsConfirmationNumberDAC)
	{
		this.btsConfirmationNumberDAC = btsConfirmationNumberDAC;
	}

	/**
	 * This method is executed before each @Test annotated method.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBtsConfirmationNumber.sql",
				false);
	}

	/**
	 * Test fetching of next BTS confirmation number when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextBtsConfirmationNumberInitialRequest()
	{
		InternalResultsResponse<BtsConfirmationNumber> response =
				getBtsConfirmationNumberDAC().fetchNextBtsConfirmationNumber(PGSI_PREFIX_NUMBER);
		checkResults(response, response.getFirstResult(), PGSI_PREFIX_NUMBER, 1);
	}

	/**
	 * Test fetching of next BTS confirmation number when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextBtsConfirmationNumberSecondRequest()
	{
		InternalResultsResponse<BtsConfirmationNumber> response =
				getBtsConfirmationNumberDAC().fetchNextBtsConfirmationNumber(PGSI_PREFIX_NUMBER);
		checkResults(response, response.getFirstResult(), PGSI_PREFIX_NUMBER, 1);
		response = getBtsConfirmationNumberDAC().fetchNextBtsConfirmationNumber(PGSI_PREFIX_NUMBER);
		checkResults(response, response.getFirstResult(), PGSI_PREFIX_NUMBER, 2);
	}
}
