package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.cbof.model.request.FetchByCodeRequest;
import com.prosperitasglobal.cbof.model.response.CountryResponse;
import com.prosperitasglobal.cbof.model.response.StateProvinceRegionResponse;

public interface ICountryBAI
{
	/**
	 * Fetch all country.
	 *
	 * @param request the request
	 * @return the country response
	 */
	public CountryResponse fetchAllCountry(FetchByCodeRequest request);

	/**
	 * Fetch all country.
	 *
	 * @param request the request
	 * @return the country response
	 */
	public CountryResponse fetchAllKnownCountry(FetchByCodeRequest request);

	/**
	 * Fetch country by code.
	 *
	 * @param request the request
	 * @return the country response
	 */
	public CountryResponse fetchCountryByCode(FetchByCodeRequest request);

	/**
	 * Fetch state province region by country code.
	 *
	 * @param request the request
	 * @return the state province region response
	 */
	public StateProvinceRegionResponse fetchStateProvinceRegionByCountryCode(FetchByCodeRequest request);
}
