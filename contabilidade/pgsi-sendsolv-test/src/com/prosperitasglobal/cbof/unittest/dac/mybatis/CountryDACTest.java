package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CountryDACTest.
 */
public class CountryDACTest extends AbstractTestBaseDAC
{

	/** The Constant OZ. */
	private static final String OZ = "OZ";

	/** The Constant USA. */
	private static final String USA = "USA";

	/** The Constant SIXTY. */
	private static final Integer SIXTY = 60;

	/** The Constant SIZE_NEEDS_TO_BE_0. */
	private static final String SIZE_NEEDS_TO_BE_0 = "Size needs to be 0";

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(CountryDACTest.class);

	/**
	 * Test fetch all countries.
	 */
	@Test
	public void testFetchAllCountries()

	{
		LOG.info("Start FetchAllCountries");
		InternalResultsResponse<Country> response = getCountryDAC().fetchAllCountry();

		CommonTestRoutines.assertResponse(response);

	}

	@Test
	public void testFetchAllKnownCountries()

	{
		LOG.info("Start FetchAllKnownCountries");
		InternalResultsResponse<Country> response = getCountryDAC().fetchAllKnownCountry();

		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch country by code.
	 */
	@Test
	public void testFetchCountryByCode()
	{
		// Send a valid country code
		InternalResultsResponse<Country> response = getCountryDAC().fetchCountryByCode(USA);
		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch country by code with invalid code.
	 */
	@Test
	public void testFetchCountryByCodeWithInvalidCode()
	{
		// Send an invalid country code
		InternalResultsResponse<Country> response = getCountryDAC().fetchCountryByCode(OZ);
		Assert.assertTrue(SIZE_NEEDS_TO_BE_0, response.getResultsList().size() == 0);
	}

	/**
	 * Test fetch state province region by code.
	 */
	@Test
	public void testFetchStateProvinceRegionByCode()
	{
		// Send a valid country code
		InternalResultsResponse<StateProvinceRegion> response =
				getCountryDAC().fetchStateProvinceRegionByCountryCode(USA);
		Assert.assertTrue("Size needs to be 60", response.getResultsList().size() == SIXTY);
	}

	/**
	 * Test fetch state province region by code with invalid code.
	 */
	@Test
	public void testFetchStateProvinceRegionByCodeWithInvalidCode()
	{
		// Send an invalid country code
		InternalResultsResponse<StateProvinceRegion> response =
				getCountryDAC().fetchStateProvinceRegionByCountryCode(OZ);
		Assert.assertTrue(SIZE_NEEDS_TO_BE_0, response.getResultsList().size() == 0);
	}
}
