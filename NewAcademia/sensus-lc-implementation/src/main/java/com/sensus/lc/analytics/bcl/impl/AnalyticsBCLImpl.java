/*
 *
 */
package com.sensus.lc.analytics.bcl.impl;

import static com.sensus.lc.base.util.LCHelp.convertToDouble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.supercsv.cellprocessor.Optional;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.bcl.IAnalyticsBCL;
import com.sensus.lc.analytics.dac.IAnalyticsDAC;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.lc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.lc.analytics.model.AnalyticsGroupColumns;
import com.sensus.lc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.lc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.lc.analytics.model.AnalyticsGroupWarning;
import com.sensus.lc.analytics.model.AnalyticsTypeEnum;
import com.sensus.lc.analytics.model.DashboardViewTypeEnum;
import com.sensus.lc.analytics.model.request.AnalyticsCSVRequest;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.analytics.util.ConvertAnalyticsColumns;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.util.LCCSVUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class AnalyticsBCLImpl.
 * 
 * @author - QAT Brazil.
 * 
 */
public class AnalyticsBCLImpl implements IAnalyticsBCL
{
	private static final Log LOG = LogFactory.getLog(AnalyticsBCLImpl.class);

	private static final String PIPE_SEPARATOR = "|";
	private static final String COMMA_SEPARATOR = ",";

	private String tempFilePath; // injected by Spring - come from

	private static final String ENERGY_SAVED = "Energy Saved";
	private static final String BARRELS_OF_OIL_SAVED = "Barrels of oil Saved";
	private static final String TONS_OF_CO_SAVED = "Metric Tons of CO Saved";
	private static final String INDUCTION = "Induction";
	private static final String LED = "LED";
	private static final String OTHER = "Other";
	private static final String TOTAL = "Total";
	private static final String LAMP_FAILURE = "Lamp Failure";
	private static final String POWER_FAILURE = "Power Failure";
	private static final String BOARD_FAILURE = "Board Failure";
	private static final String METROLOGY_ERROR = "Metrology Error";
	private static final String METROLOGY_COM_FAILURE = "Metrology COM Failure";
	private static final String POWER_SURGE = "Power Surge";
	private static final String BROWNOUT_DETECTED = "Brownout Detected";
	private static final String HIGH_CURRENT = "High Current";
	private static final String LOW_CURRENT = "Low Current";
	private static final String REVERSE_ENERGY = "Reverse Energy";
	private static final String METROLOGY_RESET = "Metrology Reset";
	private static final String COMMUNICATION_FAIL = "Communication Fail";

	private static final String VALUE_COLUMN = "value";
	private static final String AVERAGE_COLUMN = "average";
	private static final String CHANGE_COLUMN = "change";
	private static final String TRENDS_COLUMN = "trends";
	private static final String MEASURED_CONSUMPTION = "Measured Consumption";
	private static final String BASELINE_CONSUMPTION = "Baseline Consumption";

	private static final String NAME = "name";

	private IAnalyticsDAC analyticsDAC; // injected by Spring through setter
	private IProcessBCL processBCL; // injected by Spring through setter
	private Double carbonCreditsFactor; // injected by Spring through setter
	private Double barrelsOfOilFactor; // injected by Spring through setter
	private Double metricOfCOFactor; // injected by Spring through setter
	private List<CSVColumn> analyticsAlarmExportColumns;
	private List<CSVColumn> analyticsWarningsExportColumns;
	private List<CSVColumn> analyticsInstalledAndConsumptionExportColumns;
	private List<CSVColumn> analyticsAllEcoModeColumns;
	private List<CSVColumn> analyticsAllCarbonCreditsColumns;

	/**
	 * Gets the analytics dac.
	 * 
	 * @return the analytics dac
	 */
	public IAnalyticsDAC getAnalyticsDAC()
	{
		return analyticsDAC;
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
		return processBCL;
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
		return carbonCreditsFactor;
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
		return barrelsOfOilFactor;
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
		return metricOfCOFactor;
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
	 * Gets the temp file path.
	 * 
	 * @return the temp file path
	 */
	public String getTempFilePath()
	{
		return tempFilePath;
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

	/**
	 * Gets the analytics alarm export columns.
	 * 
	 * @return the analytics alarm export columns
	 */
	public List<CSVColumn> getAnalyticsAlarmExportColumns()
	{
		return analyticsAlarmExportColumns;
	}

	/**
	 * Sets the analytics alarm export columns.
	 * 
	 * @param analyticsAlarmExportColumns the new analytics alarm export columns
	 */
	public void setAnalyticsAlarmExportColumns(List<CSVColumn> analyticsAlarmExportColumns)
	{
		this.analyticsAlarmExportColumns = analyticsAlarmExportColumns;
	}

	/**
	 * Gets the analytics warnings export columns.
	 * 
	 * @return the analytics warnings export columns
	 */
	public List<CSVColumn> getAnalyticsWarningsExportColumns()
	{
		return analyticsWarningsExportColumns;
	}

	/**
	 * Sets the analytics warnings export columns.
	 * 
	 * @param analyticsWarningsExportColumns the new analytics warnings export columns
	 */
	public void setAnalyticsWarningsExportColumns(List<CSVColumn> analyticsWarningsExportColumns)
	{
		this.analyticsWarningsExportColumns = analyticsWarningsExportColumns;
	}

	/**
	 * Gets the analytics installed and consumption export columns.
	 * 
	 * @return the analytics installed and consumption export columns
	 */
	public List<CSVColumn> getAnalyticsInstalledAndConsumptionExportColumns()
	{
		return analyticsInstalledAndConsumptionExportColumns;
	}

	/**
	 * Sets the analytics installed and consumption export columns.
	 * 
	 * @param analyticsInstalledAndConsumptionExportColumns the new analytics installed and consumption export columns
	 */
	public void setAnalyticsInstalledAndConsumptionExportColumns(
			List<CSVColumn> analyticsInstalledAndConsumptionExportColumns)
	{
		this.analyticsInstalledAndConsumptionExportColumns = analyticsInstalledAndConsumptionExportColumns;
	}

	/**
	 * Gets the analytics all eco mode columns.
	 * 
	 * @return the analytics all eco mode columns
	 */
	public List<CSVColumn> getAnalyticsAllEcoModeColumns()
	{
		return analyticsAllEcoModeColumns;
	}

	/**
	 * Sets the analytics all eco mode columns.
	 * 
	 * @param analyticsAllEcoModeColumns the new analytics all eco mode columns
	 */
	public void setAnalyticsAllEcoModeColumns(List<CSVColumn> analyticsAllEcoModeColumns)
	{
		this.analyticsAllEcoModeColumns = analyticsAllEcoModeColumns;
	}

	/**
	 * Gets the analytics all carbon credits columns.
	 * 
	 * @return the analytics all carbon credits columns
	 */
	public List<CSVColumn> getAnalyticsAllCarbonCreditsColumns()
	{
		return analyticsAllCarbonCreditsColumns;
	}

	/**
	 * Sets the analytics all carbon credits columns.
	 * 
	 * @param analyticsAllCarbonCreditsColumns the new analytics all carbon credits columns
	 */
	public void setAnalyticsAllCarbonCreditsColumns(List<CSVColumn> analyticsAllCarbonCreditsColumns)
	{
		this.analyticsAllCarbonCreditsColumns = analyticsAllCarbonCreditsColumns;
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
	@SuppressWarnings({"unchecked"})
	@Override
	public InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByType(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<Map<String, Integer>> response =
				getAnalyticsDAC().fetchAnalyticsAlertsByType(analyticsRequest);

		Map<String, Integer> map = new HashMap<String, Integer>();

		Map<String, Integer> alertType = response.getFirstResult();
		if (!ValidationUtil.isNull(alertType))
		{
			map.putAll(alertType);
		}

		Map<String, Integer> sortResult = sortByComparator(map);
		response = new InternalResultsResponse<Map<String, Integer>>();
		response.addResult(sortResult);

		return response;
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
	 * com.sensus.lc.analytics.bcl.IAnalyticsBCL#generateFileCSV(com.sensus.lc.analytics.model.request.AnalyticsCSVRequest
	 * )
	 */
	@Override
	public CSVInternalResponse generateFileCSV(AnalyticsCSVRequest analyticsCSVRequest)
	{

		CSVInternalResponse internalResponse = new CSVInternalResponse(analyticsCSVRequest.getFileName());
		InquiryAnalyticsRequest inquiryAnalyticsRequest = analyticsCSVRequest.getInquiryAnalyticsRequest();
		inquiryAnalyticsRequest.setUserContext(analyticsCSVRequest.getUserContext());
		InternalResultsResponse<AnalyticsGroup> responseAllGroups = fetchAllAnalyticsByGroup(inquiryAnalyticsRequest);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Get all lights filtered finished");
		}

		List<CSVColumn> columnsToExport = getColumnsToExport(inquiryAnalyticsRequest);

		analyticsCSVRequest.setInquiryAnalyticsRequest(inquiryAnalyticsRequest);

		prepareCSV(columnsToExport);

		LCCSVUtil.processCSVRequest(analyticsCSVRequest, internalResponse, responseAllGroups.getResultsList(),
				columnsToExport);

		if (!internalResponse.isInError())
		{
			if (analyticsCSVRequest.getProcessId() != null)
			{
				LCCSVUtil.handleCSVResponse(analyticsCSVRequest, internalResponse, getProcessBCL());
			}
		}
		return internalResponse;
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
			ag.addColumn(new AnalyticsGroupColumns(TOTAL, agem.getEcoModePercent()));
			ag.addColumn(new AnalyticsGroupColumns(MEASURED_CONSUMPTION, agem.getMeasuredConsumption()));
			ag.addColumn(new AnalyticsGroupColumns(BASELINE_CONSUMPTION, agem.getBaselineConsumption()));
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
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#insertCSVProcess(com.sensus.mlc.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		return getProcessBCL().insertCSVProcess(request);
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

	@Override
	public InternalResponse updateAnalyticsAlarmWarning(AnalyticsRequest request)
	{
		return getAnalyticsDAC().updateAnalyticsAlarmWarning(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.bcl.IAnalyticsBCL#insertAnalyticsSummarized(com.sensus.lc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResponse insertAnalyticsSummarized(AnalyticsRequest request)
	{
		return getAnalyticsDAC().insertAnalyticsSummarized(request);
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

	/**
	 * Prepare csv.
	 * 
	 * @param columnsToExport the columns to export
	 */
	private static void prepareCSV(List<CSVColumn> columnsToExport)
	{
		if (ValidationUtil.isNullOrEmpty(columnsToExport))
		{
			return;
		}

		// look for columns date to apply time zone
		for (CSVColumn col : columnsToExport)
		{
			if (!NAME.equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertAnalyticsColumns()));
			}
		}
	}

	/**
	 * Gets the columns to export.
	 * 
	 * @param request the request
	 * @return the columns to export
	 */
	private List<CSVColumn> getColumnsToExport(InquiryAnalyticsRequest request)
	{
		switch (request.getAnalyticsTypeEnum())
		{
			case LIGHTING_ALARM:

				return getAnalyticsAlarmExportColumns();

			case LIGHTING_WARNING:

				return getAnalyticsWarningsExportColumns();

			case LIGHTING_INSTALLED:
				return getAnalyticsInstalledAndConsumptionExportColumns();

			case ECOMODE_CONSUMPTION:

				return getAnalyticsInstalledAndConsumptionExportColumns();

			case LIGHTING_ECOMODE:

				return getAnalyticsAllEcoModeColumns();

			case ECOMODE_CARBON_CREDITS:

				return getAnalyticsAllCarbonCreditsColumns();

			default:
				break;
		}

		return null;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static Map sortByComparator(Map unsortMap)
	{

		List list = new LinkedList(unsortMap.entrySet());

		// sort list based on comparator
		Collections.sort(list, new Comparator()
		{
			@Override
			public int compare(Object o1, Object o2)
			{
				return ((Comparable)((Map.Entry)o2).getValue())
						.compareTo(((Map.Entry)o1).getValue());
			}
		});

		// put sorted list into map again
		// LinkedHashMap make sure order in which keys were inserted
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry)it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
