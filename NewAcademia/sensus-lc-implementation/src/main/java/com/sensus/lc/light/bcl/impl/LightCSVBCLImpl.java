package com.sensus.lc.light.bcl.impl;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.supercsv.cellprocessor.Optional;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.csv.CSVUtil;
import com.sensus.common.model.Message;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.ConvertAlerts;
import com.sensus.lc.base.util.ConvertEcoMode;
import com.sensus.lc.base.util.ConvertLifeCycleState;
import com.sensus.lc.base.util.ConvertTimeZone;
import com.sensus.lc.base.util.LCCSVUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.bcl.ILightCSVBCL;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.request.CSVRequest;
import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.light.util.ConvertAlertDate;
import com.sensus.lc.light.util.ConvertStateDate;
import com.sensus.lc.process.bcl.IProcessBCL;

/**
 * All things related to Light and CSV processing.
 */
public class LightCSVBCLImpl implements ILightCSVBCL
{

	/** The light dac. */
	private ILightDAC lightDAC;
	private INotificationHistoryDAC notificationHistoryDAC;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The columns to export. */
	private List<CSVColumn> lightSummaryExportColumns;
	private List<CSVColumn> lightHistoryExportColumns;
	private List<CSVColumn> lightDetailExportColumns;

	private static final String SENSUS_LC_EXPORT_CSV_NO_COLUMNS = "sensus.lc.csv.no.columns";
	private static final String SENSUS_LC_EXPORT_CSV_NO_FILENAME = "sensus.lc.csv.no.filename";

	private static final int FIVE = 5;
	private static final int THREE = 3;
	private static final String UNLOCKED = ".*unlocked.*";
	private static final String LOCKED = ".*locked.*";
	private static final String SENSUS_MLC_MLC_ACTION = ".*sensus.mlc.mlc_action.*";
	private static final String SENSUS_MLC_ALERT = ".*sensus.mlc.alert.*";
	private static final String IS_COMPLETED = "sensus.mlc.mlc_action.completed";
	private static final String PROCESSING = "sensus.mlc.mlc_action.processing";
	private static final String ALERT_DATE = "alert_date";
	private static final String STATE_DATE = "state_date";

	/**
	 * Gets the light dac.
	 *
	 * @return the lightDAC
	 */
	public ILightDAC getLightDAC()
	{
		return lightDAC;
	}

	/**
	 * Sets the light dac.
	 *
	 * @param lightDAC the lightDAC to set
	 */
	public void setLightDAC(ILightDAC lightDAC)
	{
		this.lightDAC = lightDAC;
	}

	/**
	 * Gets the process bcl.
	 *
	 * @return the processBCL
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 *
	 * @param processBCL the processBCL to set
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	public List<CSVColumn> getLightSummaryExportColumns()
	{
		return lightSummaryExportColumns;
	}

	public void setLightSummaryExportColumns(List<CSVColumn> lightSummaryExportColumns)
	{
		this.lightSummaryExportColumns = lightSummaryExportColumns;
	}

	public List<CSVColumn> getLightHistoryExportColumns()
	{
		return lightHistoryExportColumns;
	}

	public void setLightHistoryExportColumns(List<CSVColumn> lightHistoryExportColumns)
	{
		this.lightHistoryExportColumns = lightHistoryExportColumns;
	}

	public List<CSVColumn> getLightDetailExportColumns()
	{
		return lightDetailExportColumns;
	}

	public void setLightDetailExportColumns(List<CSVColumn> lightDetailExportColumns)
	{
		this.lightDetailExportColumns = lightDetailExportColumns;
	}

	/**
	 * @return the notificationHistoryDAC
	 */
	public INotificationHistoryDAC getNotificationHistoryDAC()
	{
		return notificationHistoryDAC;
	}

	/**
	 * @param notificationHistoryDAC the notificationHistoryDAC to set
	 */
	public void setNotificationHistoryDAC(INotificationHistoryDAC notificationHistoryDAC)
	{
		this.notificationHistoryDAC = notificationHistoryDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightCSVBCL#generateLightDetailFileCSV(com.sensus.mlc.light.model.request.LightCSVRequest
	 * )
	 */
	@Override
	public CSVInternalResponse generateLightDetailFileCSV(LightCSVRequest request)
	{
		CSVInternalResponse response = new CSVInternalResponse(request.getFileName());

		// Validate the request
		if (!validateLightCSVRequest(request, response) || !validateCommonCSVRequest(request, response))
		{
			return response;
		}

		// Get the lights
		InternalResultsResponse<Light> lightResponse = fetchLightsToExport(request);

		// If there was an error or no Lights found then return immediately.
		if (lightResponse.isInError() || !lightResponse.hasResults())
		{
			response.merge(lightResponse);
			return response;
		}

		addColumnsDateToCsvColumns(request);

		// Convert to a simple array the columns requested for CSV export.
		String[] columnsToInclude = request.getCsvColumns().toArray(new String[request.getCsvColumns().size()]);

		// Filter down the collection.
		List<CSVColumn> columnsToExport = CSVUtil.filterColumns(getLightDetailExportColumns(), columnsToInclude);

		prepareCSV(columnsToExport, request);

		// Produce the CSV file handling any errors.
		LCCSVUtil.processCSVRequest(request, response, lightResponse.getResultsList(), columnsToExport);
		if (!response.isInError())
		{
			if (request.getProcessId() != null)
			{
				LCCSVUtil.handleCSVResponse(request, response, getProcessBCL());
			}
		}

		return response;
	}

	/*
	 * Note the Lights to export are already included in the request.
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightCSVBCL#generateLightSummaryFileCSV(com.sensus.mlc.light.model.request.
	 * LightSummaryCSVRequest)
	 */
	@Override
	public CSVInternalResponse generateLightSummaryFileCSV(LightSummaryCSVRequest request)
	{
		CSVInternalResponse response = new CSVInternalResponse(request.getFileName());

		// Validate the request
		if (!validateCommonCSVRequest(request, response))
		{
			return response;
		}

		// Produce the CSV file handling any errors.
		LCCSVUtil.processCSVRequest(request, response, request.getLights(), getLightSummaryExportColumns());

		if (!response.isInError())
		{
			if (request.getProcessId() != null)
			{
				LCCSVUtil.handleCSVResponse(request, response, getProcessBCL());
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightCSVBCL#generateLightHistoryFileCSV(com.sensus.mlc.light.model.request.
	 * LightHistoryCSVRequest)
	 */
	@Override
	public CSVInternalResponse generateLightHistoryFileCSV(LightHistoryCSVRequest request)
	{
		CSVInternalResponse response = new CSVInternalResponse(request.getFileName());

		// Validate the request
		if (!validateCommonCSVRequest(request, response))
		{
			return response;
		}

		// Get the history based on the request.
		InternalResultsResponse<LightHistory> lightHistoryResponse =
				getNotificationHistoryDAC().fetchLightNotificationHistory(request.getNotificationHistoryRequest());

		// If there was an error or no history then return immediately.
		if (lightHistoryResponse.isInError() || !lightHistoryResponse.hasResults())
		{
			response.merge(lightHistoryResponse);
			return response;
		}

		translateMessage(lightHistoryResponse, request.getUserContext());

		prepareCSV(getLightHistoryExportColumns(), request);

		// Produce the CSV file handling any errors.
		LCCSVUtil.processCSVRequest(request, response, lightHistoryResponse.getResultsList(),
				getLightHistoryExportColumns());

		if (!response.isInError())
		{
			if (request.getProcessId() != null)
			{
				LCCSVUtil.handleCSVResponse(request, response, getProcessBCL());
			}
		}

		return response;
	}

	/**
	 * Translate message.
	 *
	 * @param response the response
	 * @param userContext the user context
	 */
	private void translateMessage(InternalResultsResponse<LightHistory> response, UserContext userContext)
	{
		Locale locale = getLocale(userContext);

		for (LightHistory lightHistory : response.getResultsList())
		{
			lightHistory.setStatus(SensusMessageUtil.getMessage(PROCESSING, null, null, locale));
			if (!ValidationUtil.isNull(lightHistory.isStatusComplete()) && lightHistory.isStatusComplete())
			{
				lightHistory.setStatus(SensusMessageUtil.getMessage(IS_COMPLETED, null, null, locale));
			}
			if (!checkDescriptionValue(lightHistory.getDescription()))
			{
				continue;
			}
			if (!ValidationUtil.isNullOrEmpty(lightHistory.getName())
					&& lightHistory.getName().matches(SENSUS_MLC_MLC_ACTION))
			{
				lightHistory.setName(
						SensusMessageUtil.getMessage(lightHistory.getName(), null, null, locale));
			}

			Object[] parameters = StringUtils.splitByWholeSeparator(lightHistory.getParameterValue(), ",");

			if (!ValidationUtil.isNull(parameters)
					&& (lightHistory.getDescription().matches(LOCKED) || lightHistory.getDescription()
							.matches(UNLOCKED)))
			{
				Object[] protectedParam = new Object[1];
				protectedParam[0] = parameters[0];

				parameters[0] = SensusMessageUtil.getMessage(String.valueOf(protectedParam[0]), null, null, locale);
			}

			lightHistory.setDescription(SensusMessageUtil.getMessage(lightHistory.getDescription(), parameters, null,
					locale));
		}
	}

	/**
	 * Prepare csv.
	 *
	 * @param columnsToExport the columns to export
	 * @param request the request
	 */
	private static void prepareCSV(List<CSVColumn> columnsToExport, CSVRequest request)
	{
		if (ValidationUtil.isNullOrEmpty(columnsToExport))
		{
			return;
		}

		// look for columns date to apply time zone
		for (CSVColumn col : columnsToExport)
		{
			if (LightColumnEnum.DATE_ADDED.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertTimeZone(LCDateUtil.DEFAULT_DATE_FORMAT,
						"configuration.dateAdded")));
			}
			else if (LightColumnEnum.ECOMODE.getValue().equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertEcoMode()));
			}
			else if (LightColumnEnum.CREATE_DATE.getValue().equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertTimeZone(LCDateUtil.DEFAULT_HOUR_FORMAT_TO_CSV,
						request.getTimezone(), null)));
			}
			if (LightColumnEnum.ALERTS.getValue().equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertAlerts(getLocale(request.getUserContext()))));
			}
			if (ALERT_DATE.equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertAlertDate(LCDateUtil.DEFAULT_DATETIME_FORMAT_TO_CSV,
						"lastNotificationHistory.messageDate")));

			}
			if (STATE_DATE.equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertStateDate(LCDateUtil.DEFAULT_DATETIME_FORMAT_TO_CSV,
						"lastNotificationHistory.messageDate")));
			}
			if (LightColumnEnum.LIFECYCLE_STATE.getValue().equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertLifeCycleState(
						getLocale(request.getUserContext()))));

			}
		}

	}

	/**
	 * Gets the locale.
	 *
	 * @param userContext the user context
	 * @return the locale
	 */
	private static Locale getLocale(UserContext userContext)
	{
		if (ValidationUtil.isNull(userContext)
				|| ValidationUtil.isNullOrEmpty(userContext.getLocaleString()))
		{
			return null;
		}

		Locale locale =
				new Locale(userContext.getLocaleString().substring(0, 2), userContext
						.getLocaleString().substring(
								THREE, FIVE));

		return locale;
	}

	/**
	 * Validate light csv request.
	 *
	 * @param request the request
	 * @param response the response
	 * @return true, if successful
	 */
	private boolean validateLightCSVRequest(LightCSVRequest request, CSVInternalResponse response)
	{
		// Validate the request includes columns to export.
		if (ValidationUtil.isNullOrEmpty(request.getCsvColumns()))
		{
			response.addMessage(SENSUS_LC_EXPORT_CSV_NO_COLUMNS, Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation);
			response.setStatus(Status.ValidationError);
			return false;
		}

		return true;
	}

	/**
	 * Validate common csv request.
	 *
	 * @param request the request
	 * @param response the response
	 * @return true, if successful
	 */
	private boolean validateCommonCSVRequest(CSVRequest request, CSVInternalResponse response)
	{
		// Validate filename is not null
		if (ValidationUtil.isNull(request.getFileName()))
		{
			response.addMessage(SENSUS_LC_EXPORT_CSV_NO_FILENAME, Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation);
			response.setStatus(Status.ValidationError);
			return false;
		}

		return true;
	}

	/**
	 * Fetch lights to export.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the internal results response
	 */
	private InternalResultsResponse<Light> fetchLightsToExport(LightCSVRequest request)
	{
		// Get the Light Request to query the lights which will go into the CSV.
		LightRequest lightRequest = request.getLightRequest();
		lightRequest.setUserContext(request.getUserContext());

		// Get the Lights based on the request.
		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchAllByRequest(lightRequest);

		return lightResponse;
	}

	/**
	 * Adds the columns date to csv columns.
	 *
	 * @param request the request
	 */
	private void addColumnsDateToCsvColumns(LightCSVRequest request)
	{
		if (ValidationUtil.isNullOrEmpty(request.getCsvColumns()))
		{
			return;
		}

		Integer alertIndex = request.getCsvColumns().indexOf("ALERTS");
		Integer stateIndex = request.getCsvColumns().indexOf("LIFECYCLE_STATE");

		if (alertIndex != -1)
		{
			request.getCsvColumns().add(alertIndex + 1, ALERT_DATE.toUpperCase());
		}
		if (stateIndex != -1)
		{
			request.getCsvColumns().add(stateIndex + 1, STATE_DATE.toUpperCase());
		}
	}

	/**
	 * Check description value.
	 *
	 * @param description the description
	 * @return the boolean
	 */
	private Boolean checkDescriptionValue(String description)
	{
		if (ValidationUtil.isNull(description))
		{
			return false;
		}
		if (description.matches(SENSUS_MLC_ALERT))
		{
			return true;
		}
		if (description.matches(SENSUS_MLC_MLC_ACTION))
		{
			return true;
		}
		return false;
	}

}
