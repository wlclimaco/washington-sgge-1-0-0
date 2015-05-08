package com.prosperitasglobal.cbof.unittest.bai;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.dac.ICountryDAC;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.request.FetchByCodeRequest;
import com.prosperitasglobal.cbof.model.response.CountryResponse;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CountryBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
public class CountryBAITest extends AbstractJUnit4SpringContextTests
{

	/** The Constant USA. */
	private static final String USA = "USA";

	/** The country bai. */
	private ICountryBAI countryBAI;

	/** The mock country dac. */
	private ICountryDAC mockCountryDAC;

	/**
	 * Gets the country bai.
	 *
	 * @return the country bai
	 */
	public ICountryBAI getCountryBAI()
	{
		return countryBAI;
	}

	/**
	 * Sets the country bai.
	 *
	 * @param countryBAI the country bai
	 */
	@Resource
	public void setCountryBAI(ICountryBAI countryBAI)
	{
		this.countryBAI = countryBAI;
	}

	/**
	 * Gets the mock country dac.
	 *
	 * @return the mock country dac
	 */
	public ICountryDAC getMockCountryDAC()
	{
		return mockCountryDAC;
	}

	/**
	 * Sets the mock country dac.
	 *
	 * @param mockCountryDAC the mock country dac
	 */
	@Resource
	public void getMockCountryDAC(ICountryDAC mockCountryDAC)
	{
		this.mockCountryDAC = mockCountryDAC;
	}

	/**
	 * Test fetch all country.
	 */
	@Test
	public void testFetchAllCountry()
	{
		FetchByCodeRequest request = new FetchByCodeRequest();
		Mockito.when(getMockCountryDAC().fetchAllCountry()).thenReturn(
				new InternalResultsResponse<Country>(Arrays.asList(new Country(),
						new Country(), new Country(), new Country())));

		CountryResponse results = getCountryBAI().fetchAllCountry(request);
		CommonTestRoutines.assertResponse(results);

	}

	/**
	 * Test fetch all country.
	 */
	@Test
	public void testFetchAllKnownCountry()
	{
		FetchByCodeRequest request = new FetchByCodeRequest();
		Mockito.when(getMockCountryDAC().fetchAllKnownCountry()).thenReturn(
				new InternalResultsResponse<Country>(Arrays.asList(new Country(),
						new Country(), new Country(), new Country())));

		CountryResponse results = getCountryBAI().fetchAllKnownCountry(request);
		CommonTestRoutines.assertResponse(results);

	}

	/**
	 * Test fetch all country with error.
	 */
	@Test
	public void testFetchAllCountryWithError()
	{
		FetchByCodeRequest request = new FetchByCodeRequest();
		Mockito.when(getMockCountryDAC().fetchAllCountry()).then(
				new Answer<InternalResultsResponse<Country>>()
				{
					@Override
					public InternalResultsResponse<Country> answer(InvocationOnMock invocation) throws Throwable
					{
						InternalResultsResponse<Country> result = new InternalResultsResponse<Country>();
						result.setStatus(Status.ExceptionError);
						return result;
					}
				});

		CountryResponse results = getCountryBAI().fetchAllCountry(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCountryList());

		Mockito.verify(getMockCountryDAC()).fetchAllCountry();
	}

	/**
	 * Test fetch all country with error.
	 */
	@Test
	public void testFetchAllKnownCountryWithError()
	{
		FetchByCodeRequest request = new FetchByCodeRequest();
		Mockito.when(getMockCountryDAC().fetchAllKnownCountry()).then(
				new Answer<InternalResultsResponse<Country>>()
				{
					@Override
					public InternalResultsResponse<Country> answer(InvocationOnMock invocation) throws Throwable
					{
						InternalResultsResponse<Country> result = new InternalResultsResponse<Country>();
						result.setStatus(Status.ExceptionError);
						return result;
					}
				});

		CountryResponse results = getCountryBAI().fetchAllKnownCountry(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCountryList());

		Mockito.verify(getMockCountryDAC(), Mockito.atLeastOnce()).fetchAllKnownCountry();
	}

	/**
	 * Test fetch country by code.
	 */
	@Test
	public void testFetchCountryByCode()
	{
		String countryCode = USA;

		FetchByCodeRequest request = new FetchByCodeRequest();
		CountryResponse results = getCountryBAI().fetchCountryByCode(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.when(getMockCountryDAC().fetchCountryByCode(countryCode)).thenReturn(
				new InternalResultsResponse<Country>(Arrays.asList(new Country())));

		results = getCountryBAI().fetchCountryByCode(request);
		CommonTestRoutines.assertResponse(results);

	}

	/**
	 * Test fetch country by code with error.
	 */
	@Test
	public void testFetchCountryByCodeWithError()
	{
		FetchByCodeRequest request = new FetchByCodeRequest();

		Mockito.when(getMockCountryDAC().fetchCountryByCode(request.getCode())).then(
				new Answer<InternalResultsResponse<Country>>()
				{
					@Override
					public InternalResultsResponse<Country> answer(InvocationOnMock invocation) throws Throwable
					{
						InternalResultsResponse<Country> result = new InternalResultsResponse<Country>();
						result.setStatus(Status.ExceptionError);
						return result;
					}
				});

		CountryResponse results = getCountryBAI().fetchCountryByCode(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCountryList());

		Mockito.verify(getMockCountryDAC(), Mockito.never()).fetchCountryByCode(request.getCode());
	}

	/**
	 * Test fetch state province region by country code.
	 */
	@Test
	public void testFetchStateProvinceRegionByCountryCode()
	{

	}
}
