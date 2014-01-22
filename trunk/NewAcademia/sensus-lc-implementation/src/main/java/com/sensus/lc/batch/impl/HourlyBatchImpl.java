package com.sensus.lc.batch.impl;

import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static com.sensus.lc.base.util.LCDateUtil.convertDateUTCToTimezone;
import static com.sensus.lc.base.util.LCDateUtil.getNewDateUTC;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.bcl.IAnalyticsBCL;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.batch.IHourlyBatch;
import com.sensus.lc.ecomode.bcl.IEcoModeBCL;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.light.bcl.ILightProcessorBCL;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.settings.bcl.ISettingsBCL;
import com.sensus.lc.tenant.bcl.ITenantBCL;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class HourlyBatchImpl.
 * 
 * @see com.sensus.mlc.batch.IHourlyBatch
 */
public class HourlyBatchImpl implements IHourlyBatch
{
	/** The LOG. */
	private static final Log LOG = LogFactory.getLog(HourlyBatchImpl.class);

	/** BCL attributes. */
	private ILightProcessorBCL lightProcessorBCL;
	private IAnalyticsBCL analyticsBCL; // injected by Spring through setter
	private IEcoModeBCL ecoModeBCL; // injected by Spring through setter
	private ISettingsBCL settingsBCL; // injected by Spring through setter
	private ITenantBCL tenantBCL; // injected by Spring through setter

	/** Others attributes. */
	private Integer calculatePageSize; // injected by Spring through setter
	private String systemId; // injected by Spring through setter

	/**
	 * Sets the settings bcl.
	 * 
	 * @param settingsBCL the new settings bcl
	 */
	public void setSettingsBCL(ISettingsBCL settingsBCL)
	{
		this.settingsBCL = settingsBCL;
	}

	/**
	 * Gets the settings bcl.
	 * 
	 * @return the settings bcl
	 */
	public ISettingsBCL getSettingsBCL()
	{
		return settingsBCL;
	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getSystemId()
	{
		return systemId;
	}

	/**
	 * Sets the system id.
	 * 
	 * @param systemId the new system id
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}

	/**
	 * Gets the light processor bcl.
	 * 
	 * @return the light processor bcl
	 */
	public ILightProcessorBCL getLightProcessorBCL()
	{
		return lightProcessorBCL;
	}

	/**
	 * Sets the light processor bcl.
	 * 
	 * @param lightProcessorBCL the new light processor bcl
	 */
	public void setLightProcessorBCL(ILightProcessorBCL lightProcessorBCL)
	{
		this.lightProcessorBCL = lightProcessorBCL;
	}

	/**
	 * Gets the tenant bcl.
	 * 
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 * 
	 * @param tenantBCL the new tenant bcl
	 */
	public void setTenantBCL(ITenantBCL tenantBCL)
	{
		this.tenantBCL = tenantBCL;
	}

	/**
	 * Gets the analytics bcl.
	 * 
	 * @return the analyticsBCL
	 */
	public IAnalyticsBCL getAnalyticsBCL()
	{
		return analyticsBCL;
	}

	/**
	 * Sets the analytics bcl.
	 * 
	 * @param analyticsBCL the analyticsBCL to set
	 */
	public void setAnalyticsBCL(IAnalyticsBCL analyticsBCL)
	{
		this.analyticsBCL = analyticsBCL;
	}

	/**
	 * Performs batch routines and, accordingly with Tenant time, its daily routine.
	 * This routine is called by Spring Quartz, and it must be configured to call this,
	 * at most, once a hour.
	 * 
	 * @see com.sensus.mlc.batch.IHourlyBatch#batchHourlyProcess()
	 */
	@Override
	public void batchHourlyProcess()
	{
		logInfo("Beginning hourly batch");

		// Set user 'sysuser'.
		UserContext userContext = new UserContext();
		userContext.setUserId(getSystemId());

		// Get and save the current date so it will not change within the loop.
		Date currentDate = getNewDateUTC();

		// Iterating through all available tenants.
		List<Tenant> tenants = getTenantBCL().fetchAllTenant().getResultsList();
		for (Tenant tenant : tenants)
		{
			try
			{
				// verify communication failure is checked every time.
				userContext.setTenant(tenant);
				getLightProcessorBCL().verifyCommunicationFailure(userContext);

				// insert zero of the consumption to lights that are more than one day in communication failure.
				getLightProcessorBCL().insertConsumptionToLightInCommunicationFailure(userContext);

				// Get the hour of the day based on the tenants timezone using the date-time captured earlier.
				Integer thisHour = convertDateToTenantTimeZoneHour(currentDate, tenant);

				// check if it is time for the other batches processes for this tenant
				if (!ValidationUtil.isNull(thisHour) && thisHour.compareTo(tenant.getBatchProcessTime()) == 0)
				{
					logInfo("Beginning daily batch for tenant id=" + tenant.getId());
					// calculate map Center for this Tenant
					getSettingsBCL().calculateMapCenter(tenant);
					// calculate Eco Mode for this Tenant
					batchCalculateAllEcoModeBaseline(userContext);
					// calculate DashBoardResume for this Tenant
					batchCalculateAnalyticsSummary(tenant);
					logInfo("Finished daily batch for tenant id=" + tenant.getId());
				}
			}
			catch (Exception ex)
			{
				LOG.error("Exception occured while processing tenant[" + tenant.getId() + "]", ex);
			}
		}

		logInfo("Finished hourly batch");
	}

	/**
	 * Calculates analytics summaries for each tenant and for each of their groups.
	 * 
	 * @param tenant the tenant
	 * @return the internal results response
	 * @see com.sensus.mlc.analytics.batch.IDashboardSummaryBatch#calculateDashboardResume(com.sensus.mlc.tenant.model.Tenant
	 *      )
	 */
	@Override
	public InternalResultsResponse<String> batchCalculateAnalyticsSummary(Tenant tenant)
	{
		logInfo("Beginning batchCalculateAnalyticsSummary. Tenant: " + tenant.getId());

		InternalResultsResponse<String> response = null;

		// Cleaning dashboard_resume and dashboard_resume_chart tables.
		InternalResponse internalResponse = getAnalyticsBCL().deleteDashboardResumeByTenant(tenant);
		if (!internalResponse.getStatus().equals(Status.OperationSuccess))
		{
			logWarn("Failed Delete Dashboard Resume batchCalculateAnalyticsSummary.Tenant:" + tenant.getId());
		}

		// Call Dashboard Resume for all groups in tenant.
		for (AnalyticsGroup analyticsGroup : fetchAllGroupsByTenantForDashboard(tenant))
		{
			try
			{
				logInfo("batchCalculateAnalyticsSummary for Group: " + analyticsGroup.getId());

				response = getAnalyticsBCL().calculateDashboardResume(tenant, analyticsGroup);

				if (!response.getStatus().equals(Status.OperationSuccess))
				{
					logWarn("batchCalculateAnalyticsSummary FAILED for Group: " + analyticsGroup.getId()
							+ ". Aborting for this tenant.");
					break;
				}
			}
			catch (Exception ex)
			{
				LOG.error("Exception occured while processing analytics group[" + analyticsGroup.getId() + "], Name["
						+ analyticsGroup.getName() + "]", ex);
			}
		}

		response = getAnalyticsBCL().calculateDashboardResume(tenant, null);

		logInfo("Finished batchCalculateAnalyticsSummary. Result: " + response.getFirstResult());

		return response;
	}

	/**
	 * Gets the hour by tenant time zone.
	 * 
	 * @param date The date to convert to the tenant's timezone and then extract the hour from.
	 * @param tenant the tenant
	 * @return the hour of the day for the Tenant's timezone.
	 */
	private Integer convertDateToTenantTimeZoneHour(Date date, Tenant tenant)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH");
		Date convertedDate = convertDateUTCToTimezone(date, tenant.getLightTimeZone());

		if (ValidationUtil.isNull(convertedDate))
		{
			return null;
		}
		return Integer.parseInt(dateFormat.format(convertedDate));
	}

	/**
	 * Fetch all groups by tenant for dashboard.
	 * 
	 * @param tenant the tenant
	 * @return the list
	 */
	private List<AnalyticsGroup> fetchAllGroupsByTenantForDashboard(
			Tenant tenant)
	{
		InternalResultsResponse<AnalyticsGroup> response = getAnalyticsBCL()
				.fetchAllGroupsByTenantForDashboard(tenant);

		if (!response.getStatus().equals(Status.OperationSuccess)
				|| !response.hasResults())
		{
			logWarn("Failed to fetch Analytics Group to batchCalculateAnalyticsSummary. Tenant:"
					+ tenant.getId());
		}

		return response.getResultsList();
	}

	/**
	 * Gets the calculate page size.
	 * 
	 * @return the calculate page size
	 */
	public Integer getCalculatePageSize()
	{
		return calculatePageSize;
	}

	/**
	 * Sets the calculate page size.
	 * 
	 * @param calculatePageSize the new calculate page size
	 */
	public void setCalculatePageSize(Integer calculatePageSize)
	{
		this.calculatePageSize = calculatePageSize;
	}

	/**
	 * Gets the eco mode bcl.
	 * 
	 * @return the eco mode bcl
	 */
	public IEcoModeBCL getEcoModeBCL()
	{
		return ecoModeBCL;
	}

	/**
	 * Sets the eco mode bcl.
	 * 
	 * @param ecoModeBCL the new eco mode bcl
	 */
	public void setEcoModeBCL(IEcoModeBCL ecoModeBCL)
	{
		this.ecoModeBCL = ecoModeBCL;
	}

	/**
	 * Batch calculate all eco mode baseline.
	 * 
	 * @param userContext the user context
	 */
	private void batchCalculateAllEcoModeBaseline(UserContext userContext)
	{
		if (LOG.isInfoEnabled())
		{
			logInfo("Calculate all eco-mode baseline to system");
		}

		// This process calculate last consumption or to light with retroactive = true
		InquiryEcoModeRequest ecoModeRequest = new InquiryEcoModeRequest(userContext);
		Integer totalAmount = getEcoModeBCL().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
		batchCalculateEcoModeByTenant(ecoModeRequest, totalAmount);

		// This process calculate lights that any reason did not calculate ecomode retroactive
		ecoModeRequest.setLightsToReprocess(true);
		totalAmount = getEcoModeBCL().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
		batchCalculateEcoModeByTenant(ecoModeRequest, totalAmount);

		// After upsert ecomode then update analytics data
		getEcoModeBCL().updateAnalyticsData();
	}

	/**
	 * Calculate eco mode by tenant.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @param totalAmount the total amount
	 */
	private void batchCalculateEcoModeByTenant(InquiryEcoModeRequest inquiryEcoModeRequest, Integer totalAmount)
	{
		if (isNullOrZero(totalAmount))
		{
			return;
		}

		logInfo("Calculate all eco-mode baseline to tenant: " + inquiryEcoModeRequest.getUserContext().getTenant());

		Integer pageAmount = totalAmount / getCalculatePageSize();
		EcoModeRequest ecoModeRequest = new EcoModeRequest(inquiryEcoModeRequest.getUserContext());
		ecoModeRequest.setInternalProcessing(true);
		ecoModeRequest.setReprocessLight(inquiryEcoModeRequest.getLightsToReprocess());
		inquiryEcoModeRequest.setPageSize(getCalculatePageSize());

		for (int page = pageAmount; page >= 0; page--)
		{
			logInfo("Calculate all eco-mode, page: " + page);

			// set data to request
			int startRow = page * getCalculatePageSize();
			inquiryEcoModeRequest.setStartRow(startRow);

			InternalResultsResponse<Light> response =
					getEcoModeBCL().fetchAllLightsToCalculateEcoMode(inquiryEcoModeRequest);
			if (response.isInError())
			{
				continue;
			}

			ecoModeRequest.setLights(response.getResultsList());
			getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		}
	}

	/**
	 * Log info.
	 * 
	 * @param message the message
	 */
	private void logInfo(String message)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(message);
		}
	}

	/**
	 * Log warn.
	 * 
	 * @param message the message
	 */
	private void logWarn(String message)
	{
		if (LOG.isWarnEnabled())
		{
			LOG.warn(message);
		}
	}
}
