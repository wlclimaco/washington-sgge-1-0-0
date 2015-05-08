package com.prosperitasglobal.sendsolv.common.controller.unittest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.cbof.model.request.FetchByCodeRequest;
import com.prosperitasglobal.cbof.model.response.CountryResponse;
import com.prosperitasglobal.cbof.model.response.StateProvinceRegionResponse;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;

public class PGSIMainAPIControllerTest extends AbstractTestBase
{
	/** The Constant FETCHMESSAGES. */
	private static final String FETCHMESSAGES = "/fetchmessages?localeLanguage=en_Us";

	private ICountryBAI countryBAI;
	private static final Logger LOG = LoggerFactory.getLogger(PGSIMainAPIControllerTest.class);

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String FETCH_CURRENCY = "/fetchCurrency?code=USA";

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public ICountryBAI getCountryBAI()
	{
		return countryBAI;
	}

	@Resource
	public void setCountryBAI(ICountryBAI countryBAI)
	{
		this.countryBAI = countryBAI;
	}

	@Test
	public void fetchStates()
	{
		// Mock Response 1
		StateProvinceRegionResponse response = new StateProvinceRegionResponse();
		response.setStateProvinceRegionList(new ArrayList<StateProvinceRegion>());
		for (int i = 0; i < 5; i++)
		{
			StateProvinceRegion state = new StateProvinceRegion();
			state.setCode("ST" + i);
			state.setDescription("State " + i);
			state.setId("" + i);
			response.getStateProvinceRegionList().add(state);
		}
		try
		{
			// When BAI is invoked with any request, return response 1
			Mockito.when(
					getCountryBAI().fetchStateProvinceRegionByCountryCode(
							Matchers.any(FetchByCodeRequest.class)))
							.thenReturn(response);

			setData("");

			performTestGet("/fetchstates?code=1");

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void fetchCurrency() throws Exception
	{

		CountryResponse countryResponse = new CountryResponse();
		countryResponse.setCountryList(new ArrayList<Country>());

		List<Currency> listCurrencies = new ArrayList<Currency>();
		Currency c = new Currency();
		c.setCode("$");
		c.setName("DOLLARS");

		Country country;

		for (int i = 0; i < 5; i++)
		{
			country = new Country();
			country.setCurrencyList(listCurrencies);
			countryResponse.getCountryList().add(country);
		}

		Mockito.when(
				getCountryBAI().fetchAllCountry(
						Matchers.any(FetchByCodeRequest.class)))
						.thenReturn(countryResponse);

		performTestGet(FETCH_CURRENCY)
				.andExpect(status().isOk());

		Mockito.calls(1);

	}

	@Test
	public void fetchProductPlan()
	{

	}

}
