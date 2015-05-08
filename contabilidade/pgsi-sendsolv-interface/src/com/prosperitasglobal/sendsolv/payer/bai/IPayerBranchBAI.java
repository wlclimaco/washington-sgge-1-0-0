package com.prosperitasglobal.sendsolv.payer.bai;

import com.prosperitasglobal.cbof.model.request.FetchByCodeRequest;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerCountryInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerStateProvinceRegionInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerAddressResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerBranchResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerCityResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerCountryResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerStateProvinceRegionResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerZipcodeResponse;

public interface IPayerBranchBAI
{
	/**
	 * Fetch payer countries.
	 *
	 * @return The PayerCountry Response.
	 */

	public PayerCountryResponse fetchAllPayerCountries();

	/**
	 * Fetch payer state province region by country code.
	 *
	 * @param request The request.
	 * @return The PayerStateProvinceRegion Response.
	 */

	public PayerStateProvinceRegionResponse fetchPayerStateProvinceRegionByCountryCode(FetchByCodeRequest request);

	/**
	 * Fetch payer countries by request.
	 *
	 * @return The Payer Country Response.
	 */

	public PayerCountryResponse fetchPayerCountryByRequest(PayerCountryInquiryRequest request);

	public PayerCityResponse fetchPayerCityByStateProvinceRegionId(PayerStateProvinceRegionInquiryRequest request);

	/**
	 * Fetch payer state/province/regions by request.
	 *
	 * @return The Payer State Province Region Response.
	 */

	public PayerStateProvinceRegionResponse fetchPayerStateProvinceRegionByRequest(
			PayerStateProvinceRegionInquiryRequest request);

	/**
	 * Fetch payer zipcodes.
	 *
	 * @return The Payer Zipcode Response.
	 */

	public PayerZipcodeResponse fetchPayerZipcodeByCityId(FetchByIdRequest request);

	/**
	 * Fetch payer addresses.
	 *
	 * @return The Payer Address Response.
	 */

	public PayerAddressResponse fetchPayerAddressesByCityId(FetchByIdRequest request);

	/**
	 * Fetch payer countries.
	 *
	 * @return The PayerCountry Response.
	 */

	public PayerAddressResponse fetchPayerAddressesByZipcodeId(FetchByIdRequest request);

	/**
	 * Fetch payer countries.
	 *
	 * @return The PayerCountry Response.
	 */

	public PayerBranchResponse fetchPayerBranchesByAddressId(FetchByIdRequest request);
}
