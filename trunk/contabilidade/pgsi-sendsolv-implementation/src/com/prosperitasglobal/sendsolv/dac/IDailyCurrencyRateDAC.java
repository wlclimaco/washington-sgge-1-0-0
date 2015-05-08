package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.request.DailyCurrencyRateInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IDailyCurrencyRateDAC covers operations for the daily foreign exchange rate.
 */
public interface IDailyCurrencyRateDAC
{
	/**
	 * Delete a daily currency rate.
	 *
	 * @param dailyCurrencyRate The daily currency rate to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteDailyCurrencyRate(DailyCurrencyRate dailyCurrencyRate);

	/**
	 * Fetch a daily currency rate by id.
	 *
	 * @param id The id of the daily currency rate to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link DailyCurrencyRate} along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<DailyCurrencyRate> fetchDailyCurrencyRateById(Integer id);

	/**
	 * Fetch all daily currency rate by the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link DailyCurrencyRate}'s along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<DailyCurrencyRate> fetchDailyCurrencyRateByRequest(
			DailyCurrencyRateInquiryRequest request);

	/**
	 * Insert a daily currency rate.
	 *
	 * @param dailyCurrencyRate The daily currency rate to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertDailyCurrencyRate(DailyCurrencyRate dailyCurrencyRate);

	/**
	 * Update a daily currency rate.
	 *
	 * @param dailyCurrencyRate The daily currency rate to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updateDailyCurrencyRate(DailyCurrencyRate dailyCurrencyRate);
}
