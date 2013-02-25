package com.sensus.mlc.batch;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * Hourly batch routines.
 */
public interface IHourlyBatch
{

	/**
	 * Hourly batch orchestrator.
	 */
	void batchHourlyProcess();

	/**
	 * Calculates analytics summaries for each tenant and for each of their groups.
	 * @param tenant the tenant
	 * @return the internal results response
	 */
	InternalResultsResponse<String> batchCalculateAnalyticsSummary(Tenant tenant);
}
