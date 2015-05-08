package com.prosperitasglobal.cbof.dac.mybatis;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.ICountryDAC;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class CountryDACImpl.
 */
public class CountryDACImpl extends SqlSessionDaoSupport implements ICountryDAC
{

	/** The Constant COUNTRY_NAMESPACE. */
	private static final String COUNTRY_NAMESPACE = "countryMap.";

	/** The Constant COUNTRY_STMT_FETCH_STATE_BY_COUNTRY_CODE. */
	private static final String COUNTRY_STMT_FETCH_STATE_BY_COUNTRY_CODE = COUNTRY_NAMESPACE
			+ "fetchStateProvinceRegionByCountryCode";

	/** The Constant COUNTRY_STMT_FETCH_COUNTRY_BY_CODE. */
	private static final String COUNTRY_STMT_FETCH_COUNTRY_BY_CODE = COUNTRY_NAMESPACE + "fetchCountryByCode";

	/** The Constant COUNTRY_STMT_FETCH_ALL_COUNTRY. */
	private static final String COUNTRY_STMT_FETCH_ALL_COUNTRY = COUNTRY_NAMESPACE + "fetchAllCountries";

	/** The Constant COUNTRY_STMT_FETCH_ALL_KNOWN_COUNTRY. */
	private static final String COUNTRY_STMT_FETCH_ALL_KNOWN_COUNTRY = COUNTRY_NAMESPACE + "fetchAllKnownCountries";

	private String countryExclusionCodes;

	public String getCountryExclusionCodes()
	{
		return countryExclusionCodes;
	}

	public void setCountryExclusionCodes(String countryExclusionCodes)
	{
		this.countryExclusionCodes = countryExclusionCodes;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICountryDAC#fetchAllCountry()
	 */
	@Override
	public InternalResultsResponse<Country> fetchAllCountry()
	{
		InternalResultsResponse<Country> response = new InternalResultsResponse<Country>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), COUNTRY_STMT_FETCH_ALL_COUNTRY, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICountryDAC#fetchAllKnownCountry()
	 */
	@Override
	public InternalResultsResponse<Country> fetchAllKnownCountry()
	{
		InternalResultsResponse<Country> response = new InternalResultsResponse<Country>();

		String[] exclusionItems = getCountryExclusionCodes().split(",");

		List<String> exclusionList = Arrays.asList(exclusionItems);

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), COUNTRY_STMT_FETCH_ALL_KNOWN_COUNTRY,
				exclusionList, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICountryDAC#fetchCountryByCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Country> fetchCountryByCode(String code)
	{
		InternalResultsResponse<Country> response = new InternalResultsResponse<Country>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), COUNTRY_STMT_FETCH_COUNTRY_BY_CODE, code,
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICountryDAC#fetchStateProvinceRegionByCountryCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<StateProvinceRegion> fetchStateProvinceRegionByCountryCode(String code)
	{
		InternalResultsResponse<StateProvinceRegion> response = new InternalResultsResponse<StateProvinceRegion>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), COUNTRY_STMT_FETCH_STATE_BY_COUNTRY_CODE, code,
				response);

		return response;
	}

}
