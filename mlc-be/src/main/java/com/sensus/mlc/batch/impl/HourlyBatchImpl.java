package com.sensus.mlc.batch.impl;

import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static com.sensus.mlc.base.util.LCDateUtil.convertDateUTCToTimezone;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;

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
import com.sensus.mlc.analytics.bcl.IAnalyticsBCL;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.batch.IHourlyBatch;
import com.sensus.mlc.ecomode.bcl.IEcoModeBCL;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.settings.bcl.ISettingsBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.tenant.bcl.ITenantBCL;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * @see com.sensus.mlc.batch.IHourlyBatch
 */
public class HourlyBatchImpl implements IHourlyBatch
{
	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(HourlyBatchImpl.class);

	/** The analytics group bcl. */
	private IAnalyticsBCL analyticsBCL; // injected by Spring through setter

	/** The calculate page size. */
	private Integer calculatePageSize; // injected by Spring through setter

	/** The eco mode bcl. */
	private IEcoModeBCL ecoModeBCL; // injected by Spring through setter

	/** The settings bcl. */
	private ISettingsBCL settingsBCL; // injected by Spring through setter

	/** The SmartPointUpdater for batch processes. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter

	/** The system id. */
	private String systemId;

	/** The tenant BCL. */
	private ITenantBCL tenantBCL;

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
		return this.settingsBCL;
	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getSystemId()
	{
		return this.systemId;
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
	 * Gets the smart point updater bcl.
	 * 
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		return this.smartPointUpdaterBCL;
	}

	/**
	 * Sets the smart point updater bcl.
	 * 
	 * @param smartPointUpdaterBCL the new smart point updater bcl
	 */
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)
	{
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
	}

	/**
	 * Gets the tenant bcl.
	 * 
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return this.tenantBCL;
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
		return this.analyticsBCL;
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

		// Iterating through all available tenants.
		List<Tenant> tenants = getTenantBCL().fetchAllTenants().getResultsList();
		for (Tenant tenant : tenants)
		{
			// verify communication failure is checked every time.
			userContext.setTenant(tenant);
			getSmartPointUpdaterBCL().verifyCommunicationMessage(userContext);

			// all the times on database are stored on UTC, We are getting UTC integer hour time for this server
			Integer thisHour = getHourByTenantTimezone(tenant);

			// check if it is time for the other batches processes for this tenant
			if (thisHour.compareTo(tenant.getBatchProcessTime()) == 0)
			{
				logInfo("Beginning daily batch for tenant id=" + tenant.getId());
				// calculate map Center for this Tenant
				getSettingsBCL().calculateMapCenter(tenant);
				// calculate DashBoardResume for this Tenant
				batchCalculateAnalyticsSummary(tenant);
				// calculate Eco Mode for this Tenant
				batchCalculateAllEcoModeBaseline(userContext);
				// Purge Old Eco Mode analytics and old consumption data and
				getSettingsBCL().deleteOldData(tenant);
				logInfo("Finished daily batch for tenant id=" + tenant.getId());
			}
		}

		logInfo("Finished hourly batch");
	}

	/**
	 * Calculates analytics summaries for each tenant and for each of their groups.
	 * 
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

			logInfo("batchCalculateAnalyticsSummary for Group: " + analyticsGroup.getId());

			response = getAnalyticsBCL().calculateDashboardResume(tenant, analyticsGroup);

			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				logWarn("batchCalculateAnalyticsSummary FAILED for Group: " + analyticsGroup.getId()
						+ ". Aborting for this tenant.");
				break;
			}
		}

		response = getAnalyticsBCL().calculateDashboardResume(tenant, null);

		logInfo("Finished batchCalculateAnalyticsSummary. Result: " + response.getFirstResult());

		return response;
	}

	/**
	 * Gets the hour by tenant time zone.
	 * 
	 * @param tenant the tenant
	 * @return the hour by tenant time zone
	 */
	private Integer getHourByTenantTimezone(Tenant tenant)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH");
		Date date = convertDateUTCToTimezone(getNewDateUTC(), tenant.getLightTimeZone());
		return Integer.parseInt(dateFormat.format(date));
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
		return this.calculatePageSize;
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
		return this.ecoModeBCL;
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

	private void batchCalculateAllEcoModeBaseline(UserContext userContext)
	{
		if (LOG.isInfoEnabled())
		{
			logInfo("Calculate all eco-mode baseline to system");
		}

		InquiryEcoModeRequest ecoModeRequest = new InquiryEcoModeRequest(userContext);
		Integer totalAmount = getEcoModeBCL().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
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
	private void batchCalculateEcoModeByTenant(InquiryEcoModeRequest ecoModeRequest, Integer totalAmount)
	{
		if (isNullOrZero(totalAmount))
		{
			return;
		}

		logInfo("Calculate all eco-mode baseline to tenant: " + ecoModeRequest.getTenant());

		Integer pageAmount = totalAmount / getCalculatePageSize();

		for (int i = 0; i <= pageAmount; i++)
		{
			logInfo("Calculate all eco-mode, page: " + i);
			ecoModeRequest.setStartPage(i);

			// reset data to request
			ecoModeRequest.setPageSize(getCalculatePageSize());
			ecoModeRequest.setInternalProcessing(true);

			InternalResultsResponse<EcoModeBaseline> response =
					getEcoModeBCL().fetchAllLightsToCalculateEcoMode(ecoModeRequest);

			if (response.isInError())
			{
				continue;
			}

			ecoModeRequest.setEcoModeBaselineList(response.getResultsList());
			getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		}
	}

	private void logInfo(String message)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(message);
		}
	}

	private void logWarn(String message)
	{
		if (LOG.isWarnEnabled())
		{
			LOG.warn(message);
		}
	}
}
