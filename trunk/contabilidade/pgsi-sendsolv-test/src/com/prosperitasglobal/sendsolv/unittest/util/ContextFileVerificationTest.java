package com.prosperitasglobal.sendsolv.unittest.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * The Class PayerBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/contextfileverificationtest.xml"})
@ActiveProfiles({"sqlserver", "batch"})
public class ContextFileVerificationTest extends AbstractJUnit4SpringContextTests
{
	/**
	 * Test context wiring. There is nothing to perform here. This is testing the wiring of the context members to make
	 * sure that all gets resolved.
	 *
	 * View file com/prosperitasglobal/sendsolv/unittest/conf/contextfileverificationtest.xml for the context members
	 * are being included in the verification.
	 */
	@Test
	public void testContextWiring()
	{
		Assert.assertTrue(true);
	}
}
