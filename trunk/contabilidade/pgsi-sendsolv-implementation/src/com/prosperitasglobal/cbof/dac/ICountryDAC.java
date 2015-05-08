package com.prosperitasglobal.cbof.dac;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ICountryDAC.
 */
public interface ICountryDAC
{
	/**
	 * Fetch all country.
	 *
	 * @return the internal results response< country>
	 */
	public InternalResultsResponse<Country> fetchAllCountry();

	/**
	 * Fetch all known country.
	 *
	 * @return the internal results response< country>
	 */
	public InternalResultsResponse<Country> fetchAllKnownCountry();

	/**
	 * Fetch country by code.
	 *
	 * @param code the code
	 * @return the internal results response< country>
	 */
	public InternalResultsResponse<Country> fetchCountryByCode(String code);

	/**
	 * Fetch state province region by country code.
	 *
	 * @param code the code
	 * @return the internal results response< state province region>
	 */
	public InternalResultsResponse<StateProvinceRegion> fetchStateProvinceRegionByCountryCode(String code);

}
