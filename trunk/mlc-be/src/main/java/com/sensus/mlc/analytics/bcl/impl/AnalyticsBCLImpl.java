package com.sensus.mlc.analytics.bcl.impl;

import static com.sensus.mlc.base.util.LCConvertUtil.convertWattageToKWattage;
import static com.sensus.mlc.base.util.LCHelp.convertToDouble;
import static com.sensus.mlc.base.util.LCHelp.treatReturnFromBCL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.analytics.bcl.IAnalyticsBCL;
import com.sensus.mlc.analytics.dac.IAnalyticsDAC;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.mlc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.mlc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.mlc.analytics.model.AnalyticsGroupWarning;
import com.sensus.mlc.analytics.model.DashboardViewTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class AnalyticsBCLImpl.
 * 
 * @author Guilherme Vicentine - QAT Brazil.
 * 
 */
public class AnalyticsBCLImpl implements IAnalyticsBCL
{

	/** The LOG. */
	private static transient Log LOG = LogFactory
			.getLog(AnalyticsBCLImpl.class);

	/** The Constant SEPARATOR. */
	private static final String PIPE_SEPARATOR = "|";

	/** The Constant COMMA_SEPARATOR. */
	private static final String COMMA_SEPARATOR = ",";

	/** The analytics group dac. */
	private IAnalyticsDAC analyticsDAC; // injected by Spring through setter

	/** The process blc. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The carbon credits factor. */
	private Double carbonCreditsFactor; // injected by Spring through setter

	/** The barrels of oil factor. */
	private Double barrelsOfOilFactor; // injected by Spring through setter

	/** The metric of co factor. */
	private Double metricOfCOFactor; // injected by Spring through setter

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The tempFilePath. */
	private String tempFilePath; // injected by Spring - come from
	// sensus-lc.properties;

	/** The Constant ENERGY_SAVED. */
	private static final String ENERGY_SAVED = "Energy Saved";

	/** The Constant BARRELS_OF_OIL_SAVED. */
	private static final String BARRELS_OF_OIL_SAVED = "Barrels of oil Saved";

	/** The Constant TONS_OF_CO_SAVED. */
	private static final String TONS_OF_CO_SAVED = "Metric Tons of CO Saved";

	/** The Constant INDUCTION. */
	private static final String INDUCTION = "Induction";

	/** The Constant LED. */
	private static final String LED = "LED";

	/** The Constant OTHER. */
	private static final String OTHER = "Other";

	/** The Constant TOTAL. */
	private static final String TOTAL = "Total";

	/** The Constant LAMP_FAILURE. */
	private static final String LAMP_FAILURE = "Lamp Failure";

	/** The Constant POWER_FAILURE. */
	private static final String POWER_FAILURE = "Power Failure";

	/** The Constant COMMUNICATION_FAIL. */
	private static final String BOARD_FAILURE = "Board Failure";

	/** The Constant METROLOGY_ERROR. */
	private static final String METROLOGY_ERROR = "Metrology Error";

	/** The Constant METROLOGY_COM_FAILURE. */
	private static final String METROLOGY_COM_FAILURE = "Metrology COM Failure";

	/** The Constant POWER_SURGE. */
	private static final String POWER_SURGE = "Power Surge";

	/** The Constant BROWNOUT_DETECTED. */
	private static final String BROWNOUT_DETECTED = "Brownout Detected";

	/** The Constant HIGH_CURRENT. */
	private static final String HIGH_CURRENT = "High Current";

	/** The Constant LOW_CURRENT. */
	private static final String LOW_CURRENT = "Low Current";

	/** The Constant REVERSE_ENERGY. */
	private static final String REVERSE_ENERGY = "Reverse Energy";

	/** The Constant METROLOGY_RESET. */
	private static final String METROLOGY_RESET = "Metrology Reset";

	/** The Constant COMMUNICATION_FAIL. */
	private static final String COMMUNICATION_FAIL = "Communication Fail";

	/** The Constant DEFAULT_LIGHT_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_ANALYTICS_BCL_EXCEPTION_MSG = "sensus.mlc.smartpointbclimpl.defaultexception";

	/** The Constant VALUE_COLUMN. */
	private static final String VALUE_COLUMN = "value";

	/** The Constant AVERAGE_COLUMN. */
	private static final String AVERAGE_COLUMN = "average";

	/** The Constant CHANGE_COLUMN. */
	private static final String CHANGE_COLUMN = "change";

	/** The Constant TRENDS_COLUMN. */
	private static final String TRENDS_COLUMN = "trends";

	/** The Constant MEASURED_CONSUMPTION. */
	private static final String MEASURED_CONSUMPTION = "Measured Consumption";

	/** The Constant BASELINE_CONSUMPTION. */
	private static final String BASELINE_CONSUMPTION = "Baseline Consumption";

	/**
	 * Gets the analytics dac.
	 * 
	 * @return the analytics dac
	 */
	public IAnalyticsDAC getAnalyticsDAC()
	{
		return this.analyticsDAC;
	}

	/**
	 * Sets the analytics dac.
	 * 
	 * @param analyticsDAC the new analytics dac
	 */
	public void setAnalyticsDAC(IAnalyticsDAC analyticsDAC)
	{
		this.analyticsDAC = analyticsDAC;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return this.processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the carbon credits factor.
	 * 
	 * @return the carbon credits factor
	 */
	public Double getCarbonCreditsFactor()
	{
		return this.carbonCreditsFactor;
	}

	/**
	 * Sets the carbon credits factor.
	 * 
	 * @param carbonCreditsFactor the new carbon credits factor
	 */
	public void setCarbonCreditsFactor(Double carbonCreditsFactor)
	{
		this.carbonCreditsFactor = carbonCreditsFactor;
	}

	/**
	 * Gets the barrels of oil factor.
	 * 
	 * @return the barrels of oil factor
	 */
	public Double getBarrelsOfOilFactor()
	{
		return this.barrelsOfOilFactor;
	}

	/**
	 * Sets the barrels of oil factor.
	 * 
	 * @param barrelsOfOilFactor the new barrels of oil factor
	 */
	public void setBarrelsOfOilFactor(Double barrelsOfOilFactor)
	{
		this.barrelsOfOilFactor = barrelsOfOilFactor;
	}

	/**
	 * Gets the metric of co factor.
	 * 
	 * @return the metric of co factor
	 */
	public Double getMetricOfCOFactor()
	{
		return this.metricOfCOFactor;
	}

	/**
	 * Sets the metric of co factor.
	 * 
	 * @param metricOfCOFactor the new metric of co factor
	 */
	public void setMetricOfCOFactor(Double metricOfCOFactor)
	{
		this.metricOfCOFactor = metricOfCOFactor;
	}

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return this.lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the temp file path.
	 * 
	 * @return the temp file path
	 */
	public String getTempFilePath()
	{
		return this.tempFilePath;
	}

	/**
	 * Sets the temp file path.
	 * 
	 * @param tempFilePath the new temp file path
	 */
	public void setTempFilePath(String tempFilePath)
	{
		this.tempFilePath = tempFilePath;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAllAnalyticsByGroup(com
	 * .sensus.mlc.analytics.model.request. InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroup> response = new InternalResultsResponse<AnalyticsGroup>();

		switch (inquiryAnalyticsRequest.getAnalyticsTypeEnum())
		{
			case LIGHTING_ALARM:

				InternalResultsResponse<AnalyticsGroupAlarm> internalResponseAlarm =
						getAnalyticsDAC().fetchAllAnalyticsAlarmsByGroup(inquiryAnalyticsRequest);
				response.getResultsList().addAll(parseAnalyticsGroupAlarm(internalResponseAlarm.getResultsList()));
				response.getResultsSetInfo().setTotalRowsAvailable(
						internalResponseAlarm.getResultsSetInfo()
								.getTotalRowsAvailable());
				return response;

			case LIGHTING_WARNING:

				InternalResultsResponse<AnalyticsGroupWarning> internalResponseWarning =
						getAnalyticsDAC().fetchAllAnalyticsWarningsByGroup(inquiryAnalyticsRequest);
				response.getResultsList().addAll(parseAnalyticsGroupWarning(internalResponseWarning.getResultsList()));
				response.getResultsSetInfo().setTotalRowsAvailable(
						internalResponseWarning.getResultsSetInfo()
								.getTotalRowsAvailable());
				return response;

			case LIGHTING_INSTALLED:

				InternalResultsResponse<AnalyticsGroupTypeLight> internalResponseInstalled = getAnalyticsDAC()
						.fetchAllAnalyticsInstalledByGroup(inquiryAnalyticsRequest);
				response.getResultsList().addAll(
						parseAnalyticsGroupTypeLight(internalResponseInstalled
								.getResultsList()));
				response.getResultsSetInfo().setTotalRowsAvailable(
						internalResponseInstalled.getResultsSetInfo()
								.getTotalRowsAvailable());
				return response;

			case ECOMODE_CONSUMPTION:

				InternalResultsResponse<AnalyticsGroupTypeLight> internalResponseConsumption =
						getAnalyticsDAC().fetchAllAnalyticsConsumptionByGroup(inquiryAnalyticsRequest);
				response.getResultsList().addAll(
						parseAnalyticsGroupTypeLight(internalResponseConsumption
								.getResultsList()));
				response.getResultsSetInfo().setTotalRowsAvailable(
						internalResponseConsumption.getResultsSetInfo()
								.getTotalRowsAvailable());
				return response;

			case LIGHTING_ECOMODE:

				InternalResultsResponse<AnalyticsGroupEcoMode> internalResponseEcoMode =
						getAnalyticsDAC().fetchAllAnalyticsEcoModeGroup(inquiryAnalyticsRequest);
				response.getResultsList().addAll(parseAnalyticsEcoMode(internalResponseEcoMode.getResultsList()));
				response.getResultsSetInfo().setTotalRowsAvailable(
						internalResponseEcoMode.getResultsSetInfo()
								.getTotalRowsAvailable());
				return response;

			case ECOMODE_CARBON_CREDITS:

				InternalResultsResponse<AnalyticsGroupCarbonCredits> internalResponseCarbonCredits =
						getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByGroup(inquiryAnalyticsRequest,
								getCarbonCreditsFactor(), getBarrelsOfOilFactor(), getMetricOfCOFactor());
				response.getResultsList().addAll(
						parseAnalyticsGroupCarbonCredits(internalResponseCarbonCredits.getResultsList()));
				response.getResultsSetInfo().setTotalRowsAvailable(
						internalResponseCarbonCredits.getResultsSetInfo()
								.getTotalRowsAvailable());
				return response;

			default:
				break;
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAnalyticsAlarmsByStatusId
	 * (com.sensus.mlc.analytics.model.request .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest)
	{
		return getAnalyticsDAC().fetchAnalyticsAlarmsByStatusId(
				analyticsRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAnalyticsAlertsByType
	 * (com.sensus.mlc.analytics.model.request. AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByType(AnalyticsRequest analyticsRequest)
	{
		return getAnalyticsDAC().fetchAnalyticsAlertsByType(analyticsRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchDashboardResume(com.sensus
	 * .mlc.analytics.model.request. AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardResume(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response = getAnalyticsDAC()
				.fetchDashboardResume(analyticsRequest,
						getCarbonCreditsFactor());

		Map<String, Map<String, Object>> resultMap = depurateData(response
				.getResultsList());

		InternalResultsResponse<AnalyticsGroupColumns> newResponse =
				new InternalResultsResponse<AnalyticsGroupColumns>();
		newResponse.setStatus(response.getStatus());
		newResponse.addResults(buildNewResultList(resultMap));

		return newResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAllAnalyticsByDate(com
	 * .sensus.mlc.analytics.model.request. AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsByDate(AnalyticsRequest analyticsRequest)
	{

		if (!ValidationUtil.isNull(analyticsRequest.getAnalyticsTypeEnum()))
		{
			switch (analyticsRequest.getAnalyticsTypeEnum())
			{
				case LIGHTING_ALARM:
					return getAnalyticsDAC().fetchAllAnalyticsAlarmsByDate(
							analyticsRequest);
				case LIGHTING_WARNING:
					return getAnalyticsDAC().fetchAllAnalyticsWarningsByDate(
							analyticsRequest);
				case LIGHTING_INSTALLED:
					return getAnalyticsDAC().fetchAllAnalyticsInstalledByDate(
							analyticsRequest);
				case ECOMODE_CONSUMPTION:
					return getAnalyticsDAC().fetchAllAnalyticsConsumptionByDate(
							analyticsRequest);
				case LIGHTING_ECOMODE:
					return getAnalyticsDAC().fetchAllAnalyticsEcoModeByDate(
							analyticsRequest);
				case ECOMODE_CARBON_CREDITS:
					return getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByDate(
							analyticsRequest, getCarbonCreditsFactor(),
							getBarrelsOfOilFactor(), getMetricOfCOFactor());
				default:
					break;
			}
		}

		return new InternalResultsResponse<AnalyticsGroupColumns>();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchDashboarHeader(com.sensus
	 * .mlc.analytics.model.request. AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardHeader(AnalyticsRequest analyticsRequest)
	{
		return getAnalyticsDAC().fetchDashboarHeader(analyticsRequest,
				getCarbonCreditsFactor());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupAlarm(java
	 * .util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupAlarm(List<AnalyticsGroupAlarm> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return returnList;
		}

		for (AnalyticsGroupAlarm aga : list)
		{
			AnalyticsGroup ag = new AnalyticsGroup(aga.getId(), aga.getName());
			ag.addColumn(new AnalyticsGroupColumns(TOTAL, convertToDouble(aga
					.getTotal())));
			ag.addColumn(new AnalyticsGroupColumns(LAMP_FAILURE,
					convertToDouble(aga.getLampFailure())));
			ag.addColumn(new AnalyticsGroupColumns(POWER_FAILURE,
					convertToDouble(aga.getPowerFailure())));
			ag.addColumn(new AnalyticsGroupColumns(BOARD_FAILURE,
					convertToDouble(aga.getBoardFailure())));
			ag.addColumn(new AnalyticsGroupColumns(METROLOGY_ERROR,
					convertToDouble(aga.getMetrologyError())));
			ag.addColumn(new AnalyticsGroupColumns(METROLOGY_COM_FAILURE,
					convertToDouble(aga.getMetrologyComFailure())));

			returnList.add(ag);
		}

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupWarning
	 * (java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupWarning(List<AnalyticsGroupWarning> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return returnList;
		}

		for (AnalyticsGroupWarning agw : list)
		{
			AnalyticsGroup ag = new AnalyticsGroup(agw.getId(), agw.getName());
			ag.addColumn(new AnalyticsGroupColumns(TOTAL, convertToDouble(agw
					.getTotal())));
			ag.addColumn(new AnalyticsGroupColumns(POWER_SURGE,
					convertToDouble(agw.getPowerSurge())));
			ag.addColumn(new AnalyticsGroupColumns(BROWNOUT_DETECTED,
					convertToDouble(agw.getBrownoutDetected())));
			ag.addColumn(new AnalyticsGroupColumns(COMMUNICATION_FAIL,
					convertToDouble(agw.getCommunicationFail())));
			ag.addColumn(new AnalyticsGroupColumns(HIGH_CURRENT,
					convertToDouble(agw.getHighCurrent())));
			ag.addColumn(new AnalyticsGroupColumns(LOW_CURRENT,
					convertToDouble(agw.getLowCurrent())));
			ag.addColumn(new AnalyticsGroupColumns(REVERSE_ENERGY,
					convertToDouble(agw.getReverseEnergy())));
			ag.addColumn(new AnalyticsGroupColumns(METROLOGY_RESET,
					convertToDouble(agw.getMetrologyReset())));

			returnList.add(ag);
		}
		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupTypeLight
	 * (java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupTypeLight(List<AnalyticsGroupTypeLight> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return returnList;
		}
		for (AnalyticsGroupTypeLight agtl : list)
		{
			AnalyticsGroup ag = new AnalyticsGroup(agtl.getId(), agtl.getName());
			ag.addColumn(new AnalyticsGroupColumns(TOTAL, agtl.getTotal()));
			ag.addColumn(new AnalyticsGroupColumns(INDUCTION, agtl
					.getInduction()));
			ag.addColumn(new AnalyticsGroupColumns(LED, agtl.getLed()));
			ag.addColumn(new AnalyticsGroupColumns(OTHER, agtl.getOther()));

			returnList.add(ag);
		}

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#generateFileCSV(com.sensus
	 * .mlc.analytics.model.request. InquiryAnalyticsRequest)
	 */
	@Override
	public InquiryAnalyticsResponse generateFileCSV(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{

		InternalResultsResponse<AnalyticsGroup> responseAllGroups = fetchAllAnalyticsByGroup(inquiryAnalyticsRequest);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Get all lights filtered finished");
		}

		inquiryAnalyticsRequest.setGroups(responseAllGroups.getResultsList());

		InternalResponse response = getAnalyticsDAC().generateFileCSV(
				inquiryAnalyticsRequest);
		InquiryAnalyticsResponse inquiryAnalyticsResponse = new InquiryAnalyticsResponse();
		inquiryAnalyticsResponse.setFileName(inquiryAnalyticsRequest
				.getFileName());
		inquiryAnalyticsResponse.setGroups(responseAllGroups.getResultsList());

		if (response.isInError())
		{
			treatReturnFromBCL(inquiryAnalyticsResponse, response,
					DEFAULT_ANALYTICS_BCL_EXCEPTION_MSG);
			return inquiryAnalyticsResponse;
		}

		Process process = new Process();
		process.setId(inquiryAnalyticsRequest.getProcessId());

		ProcessRequest processRequest = new ProcessRequest(
				inquiryAnalyticsRequest.getUserContext());
		processRequest.setProcess(process);

		InternalResultsResponse<Process> responseProcess = getProcessBCL()
				.fetchProcessById(processRequest);
		if (responseProcess.hasResults() && !responseProcess.isInError())
		{
			processRequest.setProcess(responseProcess.getFirstResult());
		}

		// update process as completed
		processRequest.getProcess().setIsProcessComplete(true);
		processRequest.getProcess().setEndTime(LCDateUtil.getNewDateUTC());

		response = getProcessBCL().updateProcess(processRequest);
		inquiryAnalyticsResponse.setOperationSuccess(!response.isInError());

		treatReturnFromBCL(inquiryAnalyticsResponse, response,
				DEFAULT_ANALYTICS_BCL_EXCEPTION_MSG);
		return inquiryAnalyticsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupCarbonCredits
	 * (java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupCarbonCredits(List<AnalyticsGroupCarbonCredits> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return returnList;
		}
		for (AnalyticsGroupCarbonCredits agcc : list)
		{
			AnalyticsGroup ag = new AnalyticsGroup(agcc.getId(), agcc.getName());
			ag.addColumn(new AnalyticsGroupColumns(TOTAL, agcc
					.getCreditsCreated()));
			ag.addColumn(new AnalyticsGroupColumns(ENERGY_SAVED, agcc
					.getEnergySaved()));
			ag.addColumn(new AnalyticsGroupColumns(BARRELS_OF_OIL_SAVED, agcc
					.getBarrelsOfOilSaved()));
			ag.addColumn(new AnalyticsGroupColumns(TONS_OF_CO_SAVED, agcc
					.getTonsOfCOSaved()));

			returnList.add(ag);
		}

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsEcoMode(java
	 * .util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsEcoMode(List<AnalyticsGroupEcoMode> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return returnList;
		}
		for (AnalyticsGroupEcoMode agem : list)
		{
			AnalyticsGroup ag = new AnalyticsGroup(agem.getId(), agem.getName());
			ag.addColumn(new AnalyticsGroupColumns(TOTAL, agem
					.getEcoModePercent()));
			ag.addColumn(new AnalyticsGroupColumns(MEASURED_CONSUMPTION,
					convertWattageToKWattage(agem.getMeasuredConsumption())));
			ag.addColumn(new AnalyticsGroupColumns(BASELINE_CONSUMPTION,
					convertWattageToKWattage(agem.getBaselineConsumption())));

			returnList.add(ag);
		}

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAllAnalyticsGroup(com
	 * .sensus.mlc.analytics.model.request. AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsGroup(
			AnalyticsRequest analyticsRequest)
	{
		return getAnalyticsDAC().fetchAllAnalyticsGroup(analyticsRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#insertCSVProcess(com.sensus
	 * .mlc.analytics.model.request. InquiryAnalyticsRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(
			LightSelectionRequest lightSelectionRequest)
	{
		return getProcessBCL().insertCSVProcess(lightSelectionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#deleteDashboarResumeByTenant(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public InternalResponse deleteDashboardResumeByTenant(Tenant tenant)
	{
		return getAnalyticsDAC().deleteDashboarResumeByTenant(tenant);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#calculateDashboardResume(java.lang.Double,
	 * com.sensus.mlc.tenant.model.Tenant, com.sensus.mlc.analytics.model.AnalyticsGroup)
	 */
	@Override
	public InternalResultsResponse<String> calculateDashboardResume(Tenant tenant, AnalyticsGroup analyticsGroup)
	{
		return getAnalyticsDAC().calculateDashboardResume(getCarbonCreditsFactor(), tenant, analyticsGroup);
	}

	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllGroupsByTenantForDashboard(Tenant tenant)
	{
		return getAnalyticsDAC().fetchAllGroupsByTenantForDashboard(tenant);
	}

	/**
	 * Depurate data.
	 * 
	 * @param analyticsGroup the analytics group
	 * @return the map
	 */
	private Map<String, Map<String, Object>> depurateData(List<AnalyticsGroupColumns> analyticsGroup)
	{
		if (ValidationUtil.isNullOrEmpty(analyticsGroup))
		{
			return null;
		}

		Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();

		for (AnalyticsGroupColumns analytic : analyticsGroup)
		{
			AnalyticsTypeEnum analyticsType = analytic.getAnalyticsTypeEnum();
			DashboardViewTypeEnum dashboardType = analytic.getDashboardViewTypeEnum();
			String key = analyticsType.getValue() + PIPE_SEPARATOR + dashboardType.getValue();
			Map<String, Object> register = resultMap.get(key);

			if (register == null)
			{
				register = new HashMap<String, Object>();
			}

			Double value = (Double)getRegisterValue(register, VALUE_COLUMN, new Double(0));
			Double average = (Double)getRegisterValue(register, AVERAGE_COLUMN, new Double(0));
			Double change = (Double)getRegisterValue(register, CHANGE_COLUMN, new Double(0));
			String trends = (String)getRegisterValue(register, TRENDS_COLUMN, "");

			register.put(VALUE_COLUMN, value + analytic.getValue());
			register.put(AVERAGE_COLUMN, average + analytic.getAverage());
			register.put(CHANGE_COLUMN, change + analytic.getChange());
			register.put(TRENDS_COLUMN, calculateTrends(analytic.getTrends(), trends));

			resultMap.put(key, register);
		}

		return resultMap;
	}

	/**
	 * Gets the register value.
	 * 
	 * @param registerMap the register map
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the register value
	 */
	private Object getRegisterValue(Map<String, Object> registerMap, String key, Object defaultValue)
	{
		Object value = registerMap.get(key);

		if (ValidationUtil.isNull(value))
		{
			return defaultValue;
		}
		return value;
	}

	/**
	 * Calculate trends.
	 * 
	 * @param trends the trends
	 * @param oldTrends the old trends
	 * @return the string
	 */
	private String calculateTrends(String trends, String oldTrends)
	{
		if (ValidationUtil.isNullOrEmpty(trends)
				|| ValidationUtil.isNullOrEmpty(oldTrends))
		{
			return trends;
		}

		String[] trendsSplited = StringUtils.split(trends, COMMA_SEPARATOR);
		String[] oldTrendsSplited = StringUtils.split(oldTrends, COMMA_SEPARATOR);

		if (trendsSplited.length != oldTrendsSplited.length)
		{
			return oldTrends;
		}

		for (int i = 0; i < oldTrendsSplited.length; i++)
		{
			int oldValue = Integer.valueOf(oldTrendsSplited[i]);
			int value = Integer.valueOf(trendsSplited[i]);
			oldTrendsSplited[i] = String.valueOf(oldValue + value);
		}

		return StringUtils.join(oldTrendsSplited, COMMA_SEPARATOR);
	}

	/**
	 * Builds the new result list.
	 * 
	 * @param resultMap the result map
	 * @return the list
	 */
	private List<AnalyticsGroupColumns> buildNewResultList(Map<String, Map<String, Object>> resultMap)
	{
		if (ValidationUtil.isNull(resultMap)
				|| CollectionUtils.isEmpty(resultMap.keySet()))
		{
			return null;
		}

		List<AnalyticsGroupColumns> analyticsGroup = new ArrayList<AnalyticsGroupColumns>();

		for (String key : resultMap.keySet())
		{
			Map<String, Object> register = resultMap.get(key);

			AnalyticsGroupColumns analytic = new AnalyticsGroupColumns();
			analytic.setValue((Double)register.get(VALUE_COLUMN));
			analytic.setAverage((Double)register.get(AVERAGE_COLUMN));
			analytic.setChange((Double)register.get(CHANGE_COLUMN));
			analytic.setTrends((String)register.get(TRENDS_COLUMN));

			String[] keySplited = StringUtils.split(key, PIPE_SEPARATOR);
			AnalyticsTypeEnum analyticsType = AnalyticsTypeEnum.enumForValue(keySplited[0]);
			DashboardViewTypeEnum dashboardType = DashboardViewTypeEnum.enumForValue(keySplited[1]);

			analytic.setAnalyticsTypeEnum(analyticsType);
			analytic.setDashboardViewTypeEnum(dashboardType);

			analyticsGroup.add(analytic);
		}

		return analyticsGroup;
	}
}
