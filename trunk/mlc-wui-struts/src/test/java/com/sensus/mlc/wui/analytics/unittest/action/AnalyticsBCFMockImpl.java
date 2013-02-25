package com.sensus.mlc.wui.analytics.unittest.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.DashboardViewTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.DataTypeEnum;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class AnalyticsBCFMockImpl extends BaseMockImpl implements IAnalyticsBCF
{

	private static final Integer LATITUDE = 16;
	private static final Integer LONGITUDE = 17;
	public static final Integer ANALYTICS_COUNT = 20;

	public static final String FILE_NAME = "File.csv";

	/** The PAG e_ size. */
	private Integer pageSize = 20;

	@Override
	public AnalyticsResponse fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			if (!ValidationUtil.isNull(analyticsRequest.getStatusExceptionTypeEnum()))
			{
				List<Light> lights = new ArrayList<Light>();
				List<LightParameter> lightParameters = new ArrayList<LightParameter>();
				lightParameters.add(new LightParameter(LATITUDE, "21.00"));
				lightParameters.add(new LightParameter(LONGITUDE, "21.00"));
				Light light = new Light();

				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setDate(new Date());

				if (analyticsRequest.getStatusExceptionTypeEnum().equals(StatusExceptionTypeEnum.LAMP_FAILURE))
				{
					statusMessage.setLightStatusEnumValue(1);
					LightParameter poleId = new LightParameter();
					poleId.setValue("L-98765-4321-0");
					poleId.setDataTypeEnum(DataTypeEnum.STRING);
					poleId.setPropertyEnum(PropertyEnum.POLE_ID);
					poleId.setLabelKey("");
					lightParameters.add(poleId);

					light.setId(1234);
					light.setCurrentStatusMessage(statusMessage);
					light.setRniId(1);
					light.setParameters(lightParameters);
					light.setCurrentStatusMessage(statusMessage);
					lights.add(light);

					light = new Light();
					light.setId(1505);
					light.setRniId(1);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					lights.add(light);

					light = new Light();
					light.setId(1111);
					light.setRniId(1);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					lights.add(light);

					light = new Light();
					light.setId(0001);
					light.setRniId(1);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					lights.add(light);

					light = new Light();
					light.setId(2020);
					light.setRniId(1);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					lights.add(light);
				}
				else if (analyticsRequest.getStatusExceptionTypeEnum().equals(StatusExceptionTypeEnum.POWER_FAILURE))
				{
					statusMessage.setLightStatusEnumValue(2);
					LightParameter poleId = new LightParameter();
					poleId.setValue("P-98765-4321-0");
					poleId.setDataTypeEnum(DataTypeEnum.STRING);
					poleId.setPropertyEnum(PropertyEnum.POLE_ID);
					poleId.setLabelKey("");
					lightParameters.add(poleId);

					light.setId(5);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					light.setRniId(1);
					lights.add(light);

					light = new Light();
					light.setId(6);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					light.setRniId(1);
					lights.add(light);

					light = new Light();
					light.setId(7);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					light.setRniId(1);
					lights.add(light);

					light = new Light();
					light.setId(8);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					light.setRniId(1);
					lights.add(light);

					light = new Light();
					light.setId(9);
					light.setCurrentStatusMessage(statusMessage);
					light.setParameters(lightParameters);
					light.setRniId(1);
					lights.add(light);
				}

				response.setLights(lights);
			}

			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	/** The Constant GROUP_NAME. */
	public static final String GROUP_NAME = "Group %d";

	@Override
	public InquiryAnalyticsResponse fetchAllAnalyticsByGroup(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InquiryAnalyticsResponse response = new InquiryAnalyticsResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setGroups(new ArrayList<AnalyticsGroup>());
			if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getPageSize())
					&& (inquiryAnalyticsRequest.getPageSize() > 0))
			{
				setPageSize(inquiryAnalyticsRequest.getPageSize());
			}
			else
			{
				setPageSize(20);
			}
			Integer startRow = inquiryAnalyticsRequest.getStartRow();

			for (int i = startRow; i < (startRow + getPageSize()); i++)
			{
				AnalyticsGroup group = new AnalyticsGroup();
				group.setId(i);
				if (i == 0)
				{
					group.setName("All Lights");
				}
				else
				{
					group.setName(String.format(GROUP_NAME, i));
				}

				List<AnalyticsGroupColumns> listAnalyticsGroupColumns = new ArrayList<AnalyticsGroupColumns>();

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_MONTH, i);
				Date date = calendar.getTime();

				AnalyticsGroupColumns analyticsGroupColumns = new AnalyticsGroupColumns();

				if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAnalyticsTypeEnum()))
				{
					switch (inquiryAnalyticsRequest.getAnalyticsTypeEnum())
					{
						case LIGHTING_ALARM:
							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Total");
							analyticsGroupColumns.setValue(20.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Lamp Failure");
							analyticsGroupColumns.setValue(10.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Power Failure");
							analyticsGroupColumns.setValue(10.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);
							break;
						case LIGHTING_WARNING:
							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Total");
							analyticsGroupColumns.setValue(30.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Power Surge ");
							analyticsGroupColumns.setValue(30.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Brownout Detected ");
							analyticsGroupColumns.setValue(0.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);
							break;
						case LIGHTING_INSTALLED:
							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Total");
							analyticsGroupColumns.setValue(30.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Induction");
							analyticsGroupColumns.setValue(30.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("LED");
							analyticsGroupColumns.setValue(0.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("OTHER");
							analyticsGroupColumns.setValue(50.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);
							break;
						case ECOMODE_CONSUMPTION:
							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Total");
							analyticsGroupColumns.setValue(16.54);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Induction");
							analyticsGroupColumns.setValue(496.7);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("LED");
							analyticsGroupColumns.setValue(0.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("OTHER");
							analyticsGroupColumns.setValue(5.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);
							break;
						case LIGHTING_ECOMODE:
							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Total");
							analyticsGroupColumns.setValue(123.8);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Induction");
							analyticsGroupColumns.setValue(950.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("LED");
							analyticsGroupColumns.setValue(0.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("OTHER");
							analyticsGroupColumns.setValue(5.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);
							break;
						case ECOMODE_CARBON_CREDITS:
							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Credits Created");
							analyticsGroupColumns.setValue(35.75);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Energy Saved");
							analyticsGroupColumns.setValue(348.6);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							analyticsGroupColumns = new AnalyticsGroupColumns();

							analyticsGroupColumns.setDate(date);
							analyticsGroupColumns.setDescription("Barrels of Oil Saved");
							analyticsGroupColumns.setValue(0.0);
							listAnalyticsGroupColumns.add(analyticsGroupColumns);

							// analyticsGroupColumns = new AnalyticsGroupColumns();

							// analyticsGroupColumns.setDate(date);
							// analyticsGroupColumns.setDescription("Metric Tons of CO");
							// analyticsGroupColumns.setValue(5.0);
							// listAnalyticsGroupColumns.add(analyticsGroupColumns);
							break;
						default:
							break;

					}
				}

				group.setColumns(listAnalyticsGroupColumns);

				group.setCreateDate((i % 2) == 0 ? new Date() : null);

				response.getGroups().add(group);
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(ANALYTICS_COUNT);
				response.setResultsSetInfo(resultsSetInfo);
			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public AnalyticsResponse fetchAnalyticsAlertsByType(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		Map<String, Integer> alertsByTypes = new HashMap<String, Integer>();

		if (MODE_SUCCESS.equals(getMode()))
		{
			alertsByTypes.put("0", 55);
			alertsByTypes.put("1", 38);
			alertsByTypes.put("2", 10);
			alertsByTypes.put("3", 2);

			response.setAlertsByType(alertsByTypes);
			response.setOperationSuccess(true);

			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public AnalyticsResponse fetchAllAnalyticsByDate(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setColumns(new ArrayList<AnalyticsGroupColumns>());
			List<AnalyticsGroupColumns> listAnalyticsGroupColumns = new ArrayList<AnalyticsGroupColumns>();

			Date initDate = analyticsRequest.getInitialDate();
			Date endDate = analyticsRequest.getEndDate();

			Calendar init = Calendar.getInstance();
			init.setTime(initDate);

			Calendar end = Calendar.getInstance();
			end.setTime(endDate);

			Calendar threeDays = Calendar.getInstance();
			threeDays.setTime(endDate);
			threeDays.add(Calendar.DATE, -2);

			Calendar week = Calendar.getInstance();
			week.setTime(endDate);
			week.add(Calendar.DATE, -6);

			Calendar month = Calendar.getInstance();
			month.setTime(endDate);
			month.add(Calendar.DATE, -29);

			Calendar threeMonth = Calendar.getInstance();
			threeMonth.setTime(endDate);
			threeMonth.add(Calendar.DATE, -89);

			Calendar year = Calendar.getInstance();
			year.setTime(endDate);
			year.add(Calendar.DATE, -364);

			int num = 0;
			int format = 0;

			if (init.compareTo(end) == 1)
			{
				num = 24;
				format = Calendar.HOUR;
			}
			else if (init.compareTo(threeDays) == 1)
			{
				num = 3;
				format = Calendar.DATE;
			}
			else if (init.compareTo(week) == 1)
			{
				num = 7;
				format = Calendar.DATE;
			}
			else if (init.compareTo(month) == 1)
			{
				num = 30;
				format = Calendar.DATE;
			}
			else if (init.compareTo(threeMonth) == 1)
			{
				num = 3;
				format = Calendar.MONTH;
			}
			else if (init.compareTo(year) == 1)
			{
				num = 12;
				format = Calendar.MONTH;
			}

			for (int i = 0; i < num; i++)
			{

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(endDate);
				calendar.add(format, -(i));

				AnalyticsGroupColumns analyticsGroupColumns = new AnalyticsGroupColumns();
				analyticsGroupColumns.setDate(calendar.getTime());
				analyticsGroupColumns.setDescription("Total Alarms");
				analyticsGroupColumns.setValue(i + 2 + 10.0);

				listAnalyticsGroupColumns.add(analyticsGroupColumns);
			}

			for (int i = 0; i < num; i++)
			{

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(endDate);
				calendar.add(format, -(i));

				AnalyticsGroupColumns analyticsGroupColumns = new AnalyticsGroupColumns();
				analyticsGroupColumns.setDate(calendar.getTime());
				analyticsGroupColumns.setDescription("Lamp Failure");
				analyticsGroupColumns.setValue(i + 5 + 10.0);

				listAnalyticsGroupColumns.add(analyticsGroupColumns);
			}

			response.setColumns(listAnalyticsGroupColumns);
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public AnalyticsResponse fetchDashboardResume(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		AnalyticsGroupColumns column = new AnalyticsGroupColumns();
		List<AnalyticsGroupColumns> columns = new ArrayList<AnalyticsGroupColumns>();

		String trend = "1,5,4,6.5";

		if (MODE_SUCCESS.equals(getMode()))
		{

			columns = new ArrayList<AnalyticsGroupColumns>();
			column = new AnalyticsGroupColumns();
			column.setDescription("Alarm");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
			column.setValue(new Double(3));
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.TODAY);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Warning");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_WARNING);
			column.setValue(new Double(5));
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.TODAY);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Instaled");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_INSTALLED);
			column.setValue(new Double(8));
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.TODAY);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Consumption");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CONSUMPTION);
			column.setValue(new Double(100));
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.TODAY);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Energy Savings");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ECOMODE);
			column.setValue(new Double(1125));
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.TODAY);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Carbon Credits");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS);
			column.setValue(new Double(123));
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.TODAY);
			columns.add(column);

			columns = new ArrayList<AnalyticsGroupColumns>();
			column = new AnalyticsGroupColumns();
			column.setDescription("Alarm");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
			column.setValue(new Double(3));
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.WEEK);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Warning");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_WARNING);
			column.setValue(new Double(5));
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.WEEK);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Instaled");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_INSTALLED);
			column.setValue(new Double(8));
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.WEEK);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Consumption");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CONSUMPTION);
			column.setValue(new Double(100));
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.WEEK);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Energy Savings");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ECOMODE);
			column.setValue(new Double(1125));
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.WEEK);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Carbon Credits");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS);
			column.setValue(new Double(123));
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.WEEK);
			columns.add(column);

			columns = new ArrayList<AnalyticsGroupColumns>();
			column = new AnalyticsGroupColumns();
			column.setDescription("Alarm");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
			column.setValue(new Double(3));
			column.setDate(new Date());
			column.setChange(new Double(-25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.MONTH);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Warning");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_WARNING);
			column.setValue(new Double(5));
			column.setDate(new Date());
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.MONTH);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Instaled");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_INSTALLED);
			column.setValue(new Double(8));
			column.setDate(new Date());
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.MONTH);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Consumption");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CONSUMPTION);
			column.setValue(new Double(100));
			column.setDate(new Date());
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.MONTH);
			column.setTrends(trend);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Energy Savings");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ECOMODE);
			column.setValue(new Double(1125));
			column.setDate(new Date());
			column.setChange(new Double(25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.MONTH);
			columns.add(column);

			column = new AnalyticsGroupColumns();
			column.setDescription("Carbon Credits");
			column.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS);
			column.setValue(new Double(123));
			column.setDate(new Date());
			column.setChange(new Double(-25));
			column.setAverage(new Double(25));
			column.setTrends(trend);
			column.setDashboardViewTypeEnum(DashboardViewTypeEnum.MONTH);
			columns.add(column);

			response.setColumns(new ArrayList<AnalyticsGroupColumns>());
			response.setColumns(columns);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public AnalyticsResponse fetchDashboardHeader(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			List<AnalyticsGroupColumns> list = new ArrayList<AnalyticsGroupColumns>();
			AnalyticsGroupColumns analyticsGroup = new AnalyticsGroupColumns();
			analyticsGroup.setDescription("Total Installed");
			analyticsGroup.setValue(new Double(2228.0));
			analyticsGroup.setDate(new Date());

			list.add(analyticsGroup);

			analyticsGroup = new AnalyticsGroupColumns();
			analyticsGroup.setDescription("Total Alarms");
			analyticsGroup.setValue(new Double(25.231));
			analyticsGroup.setDate(new Date());
			list.add(analyticsGroup);

			analyticsGroup = new AnalyticsGroupColumns();
			analyticsGroup.setDescription("Total Warnings");
			analyticsGroup.setValue(new Double(225.231));
			analyticsGroup.setDate(new Date());
			list.add(analyticsGroup);

			analyticsGroup = new AnalyticsGroupColumns();
			analyticsGroup.setDescription("Total Consumption");
			analyticsGroup.setValue(new Double(296.831));
			analyticsGroup.setDate(new Date());
			list.add(analyticsGroup);

			analyticsGroup = new AnalyticsGroupColumns();
			analyticsGroup.setDescription("Total Energy Savings");
			analyticsGroup.setValue(new Double(188.03772));
			analyticsGroup.setDate(new Date());
			list.add(analyticsGroup);

			analyticsGroup = new AnalyticsGroupColumns();
			analyticsGroup.setDescription("Total Carbon Credits");
			analyticsGroup.setValue(new Double(0.13497922));
			analyticsGroup.setDate(new Date());
			list.add(analyticsGroup);

			response.setColumns(list);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	@Override
	public InquiryAnalyticsResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InquiryAnalyticsResponse response = new InquiryAnalyticsResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{

			response.setFileName(FILE_NAME);
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public AnalyticsResponse fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setAnalyticsGroups(new ArrayList<AnalyticsGroup>());

			for (int i = 0; i < AnalyticsBCFMockImpl.ANALYTICS_COUNT; i++)
			{
				AnalyticsGroup group = new AnalyticsGroup();
				group.setId(i);
				if (i == 0)
				{
					group.setName("All Lights");
				}
				else
				{
					group.setName(String.format(GROUP_NAME, i));
				}

				response.getAnalyticsGroups().add(group);
			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return null;
	}
}
