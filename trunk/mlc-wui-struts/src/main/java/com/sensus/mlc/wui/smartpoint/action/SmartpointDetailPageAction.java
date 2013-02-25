package com.sensus.mlc.wui.smartpoint.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Message;
import com.sensus.common.model.Response;
import com.sensus.common.util.SensusStringUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.TimeZoneInfo;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.LightPropertyUtil;
import com.sensus.mlc.wui.base.util.LocalizationUtil;
import com.sensus.mlc.wui.base.util.ResultUtil;
import com.sensus.mlc.wui.smartpoint.model.SmartpointHistory;
import com.sensus.mlc.wui.smartpoint.model.ViewEventSchedule;
import com.sensus.mlc.wui.smartpoint.model.ViewExceptionMessage;
import com.sensus.mlc.wui.smartpoint.model.ViewLight;
import com.sensus.mlc.wui.smartpoint.model.ViewOffsetSchedule;

/**
 * Struts action for SmartPoint Detail Page. It extends LayoutBase
 * which provides the JavaScript and CSS Imports if the page is used with the Sitemesh decorator.
 * 
 * @author Alexandre Tiveron
 */

@SuppressWarnings("serial")
public class SmartpointDetailPageAction extends LayoutBase
{

	/** CONSTANTS **/

	/** The Constant DAY_SECONDS. */
	private static final Integer DAY_SECONDS = 86400;

	/** The Constant HOUR_SECONDS. */
	private static final Integer HOUR_SECONDS = 3600;

	/** The Constant MINUTE_SECONDS. */
	private static final Integer MINUTE_SECONDS = 60;

	/** The Constant MILISECONDS. */
	private static final Integer MILISECONDS = 1000;

	private static final Integer HUNDRED = 100;

	/** The Constant HOURS. */
	private static final String HOURS = "process.page.hours";

	/** The Constant MINUTES. */
	private static final String MINUTES = "process.page.minutes";

	/** The Constant SECONDS. */
	private static final String SECONDS = "process.page.seconds";

	/** The Constant DAYS. */
	private static final String DAYS = "process.page.days";

	/** The Constant EAST. */
	private static final String EAST = "process.page.east";

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant SIX. */
	private static final Integer SIX = 6;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant THIRTEEN. */
	private static final Integer THIRTEEN = 13;

	/**
	 * The default "empty" value for droplists.
	 */
	private static final String DEFAULT_VALUE = "0";
	/**
	 * The key for the default "loading error" droplist prompt.
	 */
	public static final String DEFAULT_ERROR_KEY = "widgets.combobox.errorprompt2";

	/** The Constant GROUP_LIST_ERROR. */
	private static final String GROUP_LIST_ERROR = "Error loading Group List";

	/** The Constant SCHEDULE_LIST_ERROR. */
	private static final String SCHEDULE_LIST_ERROR = "Error loading Schedule List";

	/**
	 * The key for the default "empty" droplist prompt.
	 */
	public static final String DEFAULT_DESCRIPTION_KEY = "widgets.combobox.prompt2";

	/** The Constant HOUR_FORMAT. */
	public static final String HOUR_FORMAT = "HH:mm:ss";

	/** The Constant SPACER. */
	public static final String SPACER = " ";

	/** The Constant DATE. */
	public static final String DATE = "Date";

	/** The Constant FLOAT. */
	public static final String FLOAT = "float";

	/** The Constant DATE_SHORT. */
	public static final String DATE_SHORT = "Date_Short";

	/** The Constant TZ. */
	public static final String TZ = "Tz";

	/** The Constant DEFAULT. */
	public static final String DEFAULT = "Default";

	/** The Constant COORDINATES. */
	public static final String COORDINATES = "Coordinates";

	/** The Constant DIMMABLE. */
	public static final String DIMMABLE = "Dimmable";

	/** The Constant MODEL_NUMBER. */
	public static final String MODEL_NUMBER = "Model_Number";

	/** The Constant SUNSET. */
	public static final String OFFSET = "Offset";

	/** The Constant HIFEN. */
	public static final String HIFEN = "-";

	/** The Constant LIGHT_INTENSITY. */
	public static final String LIGHT_INTENSITY = "intensity";

	/**
	 * The logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(SmartpointDetailPageAction.class);

	/** The id. */
	private Integer id;

	/** The smartpoint id. */
	private Integer smartpointId;

	/** The process id. */
	private Integer processId;

	/** The count alerts. */
	private Integer countAlerts = 0;

	/** The count alarms. */
	private Integer countAlarms = 0;

	/** The count warning. */
	private Integer countWarning = 0;

	/** The count power failure. */
	private Integer countPowerFailure = 0;

	/** The TIMEINMILLIS. */
	private final Integer TIMEINMILLIS = 3600000;

	/** The date today. */
	private String dateToday;

	/** The date today formated. */
	private String dateTodayFormated;

	/** The date yesterday. */
	private String dateYesterday;

	/** The smartpoint bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The settings bcf. */
	private ISettingsBCF settingsBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The lc help. */
	private transient LCHelp lcHelp;

	/** The light. */
	private ViewLight lightView;

	/** The error message. */
	private List<String> errorMessage = null;

	/** The time zone different. */
	private String timeZoneDifferent;

	/** The tags. */
	private List<Tag> tags = new ArrayList<Tag>();

	/** The update light status result. */
	private SearchJsonResult smartpointDetailResult;

	/** The timezone. */
	private final SimpleDateFormat tz = new SimpleDateFormat("zzz");

	/** The hour. */
	private final SimpleDateFormat hour = new SimpleDateFormat(HOUR_FORMAT);

	/** The map group. */
	private List<Group> tableGroups = new ArrayList<Group>();

	/** The smartpoint history. */
	private SmartpointHistory smartpointHistory;

	/** The comunication failure. */
	private String comunicationFailure;

	/** The tag list. */
	private List<IdValuePair> tagList = new ArrayList<IdValuePair>();

	/** The light request. */
	private LightRequest lightRequest;

	/** The response. */
	private Response response;

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	private Light getLight()
	{
		setSmartpointDetailResult(new SearchJsonResult());

		LightRequest request = new LightRequest(getUserContext());

		Light light = new Light();

		light.setId(getId());

		request.addLight(light);

		LightResponse response = getSmartPointAccessorBCF().fetchLightById(request);

		if (!response.isOperationSuccess() && !ValidationUtil.isNullOrEmpty(getErrorMessage()))
		{
			for (Message message : response.getMessageList())
			{
				getErrorMessage().add(message.getText());
			}
		}

		ResultUtil.setMessages(getSmartpointDetailResult(), response);

		return response.getFirstLight();

	}

	public String fetchLightById()
	{
		LightResponse response = getSmartPointAccessorBCF().fetchLightById(getLightRequest());
		setResponse(response);
		return SUCCESS;

	}

	/**
	 * Open smart point detail.
	 * 
	 * @return the string
	 */
	public String openSmartPointDetail()
	{
		setLightView(new ViewLight());

		Light light = getLight();

		if (!ValidationUtil.isNull(light))
		{
			try
			{
				fillHeaderPage(light);
				fillCountAlarms(light);
				fillStatusMessage(light);
			}
			catch (Exception e)
			{
				LOG.error(e);
			}

		}

		return SUCCESS;
	}

	/**
	 * Valid property.
	 * 
	 * @param light the light
	 * @param property the property
	 * @param type the type
	 * @return the string
	 */
	private String validProperty(Light light, PropertyEnum property, String type)
	{
		DecimalFormat coordinatesFormat = new DecimalFormat("0.0000");

		if (type.equals(DATE_SHORT))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property)))
			{
				return DEFAULT_VALUE;
			}

			return SensusStringUtil.createToString(LightPropertyUtil.getLightParameterValue(light.getParameters(),
					property));
		}

		else if (type.equals(FLOAT))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property)))
			{
				return DEFAULT_VALUE;
			}

			return SensusStringUtil.createToString(LightPropertyUtil.getLightParameterValue(light.getParameters(),
					property));
		}

		else if (type.equals(DATE))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property)))
			{
				return "";
			}

			try
			{
				DateFormat df = LocalizationUtil.getUIDateFormat(getServletRequest());
				return new Date(df.parse(LightPropertyUtil.getLightParameterValue(light.getParameters(),
						property)).getTime()).toString();
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}

		}

		else if (type.equals(LIGHT_INTENSITY))
		{
			if (!ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(),
					PropertyEnum.DIMMABLE)))
			{
				if (LightPropertyUtil.getLightParameterValue(light.getParameters(), PropertyEnum.DIMMABLE).equals(
						"False"))
				{
					return light.getLightStateEnum().name();
				}
				else if (LightPropertyUtil.getLightParameterValue(light.getParameters(), PropertyEnum.DIMMABLE).equals(
						"True"))
				{
					if (light.getLightIntensityEnum().getPercentage() == 0)
					{
						return "OFF";
					}
					else if (light.getLightIntensityEnum().getPercentage().equals(HUNDRED))
					{
						return "ON 100%";
					}
					else
					{
						return "DIM " + light.getLightIntensityEnum().getPercentage() + "%";
					}
				}
			}
		}

		else if (type.equals(TZ))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property)))
			{
				return "";
			}

			return SensusStringUtil.createToString(LightPropertyUtil.getLightParameterValue(
					light.getParameters(),
					property));
		}

		else if (type.equals(COORDINATES))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property)))
			{
				return "";
			}

			return SensusStringUtil.createToString(coordinatesFormat.format(new Double(LightPropertyUtil
					.getLightParameterValue(
							light.getParameters(), property))));
		}

		else if (type.equals(MODEL_NUMBER))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property)))
			{
				return "";
			}

			StringBuilder format =
					new StringBuilder(SensusStringUtil.createToString(LightPropertyUtil
							.getLightParameterValue(light.getParameters(), property)));

			if (format.length() >= THIRTEEN)
			{
				format.insert(THREE, HIFEN);
				format.insert(SIX, HIFEN);
				format.insert(TEN, HIFEN);
			}

			return format.toString();
		}

		else if (type.equals(DIMMABLE))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property))
					|| (LightPropertyUtil.getLightParameterValue(light.getParameters(), property).toString())
							.equalsIgnoreCase("false"))
			{
				return "";
			}

			return "Dim";
		}

		else if (type.equals(DEFAULT))
		{
			if (ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(), property)))
			{
				return "";
			}

			return SensusStringUtil.createToString(LightPropertyUtil.getLightParameterValue(
					light.getParameters(), property));

		}

		return "";
	}

	/**
	 * Convert time.
	 * 
	 * @param process the process
	 * @return the string
	 */
	private String getCompletedIn(Process process)
	{

		if (!process.getIsProcessComplete() && (!ValidationUtil.isNull(process.getEstimatedSecondsToComplete())))
		{
			Integer s = process.getEstimatedSecondsToComplete();

			if (s >= DAY_SECONDS)
			{
				return (s / DAY_SECONDS) + SPACER + getText(DAYS);
			}
			else if ((s >= HOUR_SECONDS) && (s < DAY_SECONDS))
			{
				return (s / HOUR_SECONDS) + SPACER + getText(HOURS);
			}
			else if ((s >= MINUTE_SECONDS) && (s < HOUR_SECONDS))

			{
				return getText(EAST) + SPACER + (s / MINUTE_SECONDS) + SPACER + getText(MINUTES);
			}
			else if (s < MINUTE_SECONDS)
			{
				return getText(EAST) + SPACER + s + SPACER + getText(SECONDS);
			}
		}
		else if (process.getIsProcessComplete() && (!ValidationUtil.isNull(process.getEndTime())))
		{
			Long s = process.getEndTime().getTime() - process.getStartTime().getTime();
			s /= MILISECONDS;

			if (s >= DAY_SECONDS)
			{
				return (s / DAY_SECONDS) + SPACER + getText(DAYS);
			}
			else if ((s >= HOUR_SECONDS) && (s < DAY_SECONDS))
			{
				return (s / HOUR_SECONDS) + SPACER + getText(HOURS);
			}
			else if ((s >= MINUTE_SECONDS) && (s < HOUR_SECONDS))
			{
				return (s / MINUTE_SECONDS) + SPACER + getText(MINUTES);
			}
			else if (s < MINUTE_SECONDS)
			{
				return s + SPACER + getText(SECONDS);
			}
		}
		else if (process.getIsProcessComplete() && ValidationUtil.isNull(process.getEndTime()))
		{
			return 0 + SPACER + getText(SECONDS);
		}

		return null;
	}

	/**
	 * Fill header page.
	 * 
	 * @param light the light
	 */
	private void fillHeaderPage(Light light)
	{

		setSmartpointId(light.getSmartPointId());
		setDateToday(new Date().toString());

		getLightView().setStateLight(light.getLightStateEnum());

		setDateTodayFormated(LocalizationUtil.getUIDateFormat(getServletRequest()).format(new Date()));
		getLightView().setPoleId(validProperty(light, PropertyEnum.POLE_ID, DEFAULT));
		getLightView().setLongitude(validProperty(light, PropertyEnum.LONGITUDE, COORDINATES));
		getLightView().setLatitude(validProperty(light, PropertyEnum.LATITUDE, COORDINATES));
		getLightView().setAddress(address(light));
		getLightView().setRniId(SensusStringUtil.createToString(light.getRniId()));
		getLightView().setId(SensusStringUtil.createToString(light.getId()));
		getLightView().setLightIntensity(validProperty(light, PropertyEnum.LIGHT_INTENSITY, LIGHT_INTENSITY));

		List<String> records = new ArrayList<String>();

		if (!ValidationUtil.isNullOrEmpty(light.getLightIntensitylevels()))
		{
			for (SensusPartNumberConfiguration dimming : light.getLightIntensitylevels())
			{
				records.add(dimming.getPercentage().toString());
			}
		}

		// Set JSON Result
		getLightView().setLightIntensitylevels(records);

	}

	/**
	 * Fill tags.
	 * 
	 * @param light the light
	 * @return the string
	 */
	private String fillTags(Light light)
	{
		setSmartpointDetailResult(new SearchJsonResult());

		List<Tag> listTag = new ArrayList<Tag>();

		setTagList(new ArrayList<IdValuePair>());

		try
		{
			InquiryTagRequest tagRequest = new InquiryTagRequest(getUserContext());

			tagRequest.setPageSize(0);

			listTag.addAll(getTagBCF().fetchAllTags(tagRequest).getTags());

			LightRequest lightRequest = new LightRequest(getUserContext());

			lightRequest.addLight(light);

			TagResponse tagResponse = getTagBCF().fetchTagsBySmartPoint(lightRequest);

			if (tagResponse.isOperationSuccess())
			{
				Integer selected;
				for (Tag allTags : listTag)
				{
					selected = 0;
					for (Tag tagLight : tagResponse.getTags())
					{
						if (allTags.getId().equals(tagLight.getId()))
						{
							selected = 1;
						}
					}

					IdValuePair tag = new IdValuePair();
					tag.setId(allTags.getId().toString());
					tag.setValue(allTags.getName());
					tag.setIntValue(selected);

					getTagList().add(tag);
				}

				getSmartpointDetailResult().setResult(SUCCESS);
			}
			else
			{
				getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
			}
		}
		catch (Exception e)
		{
			LOG.error("Error fetching tags ", e);
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Address.
	 * 
	 * @param light the light
	 * @return the string
	 */
	private String address(Light light)
	{
		StringBuilder address = new StringBuilder();

		if (!ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(),
				PropertyEnum.STREET_NAME)))
		{
			address.append(LightPropertyUtil.getLightParameterValue(light.getParameters(), PropertyEnum.STREET_NAME));
		}

		if (address.length() != 0)
		{
			address.append(SPACER);
		}

		if (!ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(),
				PropertyEnum.COUNTY_NAME)))
		{
			address.append(LightPropertyUtil.getLightParameterValue(light.getParameters(), PropertyEnum.COUNTY_NAME));
		}

		if (address.length() != 0)
		{
			address.append(SPACER);
		}

		if (!ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(),
				PropertyEnum.CITY_NAME)))
		{
			address.append(LightPropertyUtil.getLightParameterValue(light.getParameters(), PropertyEnum.CITY_NAME));
		}

		if (address.length() != 0)
		{
			address.append(SPACER);
		}

		if (!ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(),
				PropertyEnum.STATE_NAME)))
		{
			address.append(LightPropertyUtil.getLightParameterValue(light.getParameters(), PropertyEnum.STATE_NAME));
		}

		if (address.length() != 0)
		{
			address.append(SPACER);
		}

		if (!ValidationUtil.isNull(LightPropertyUtil.getLightParameterValue(light.getParameters(),
				PropertyEnum.ZIP_CODE)))
		{
			address.append(LightPropertyUtil.getLightParameterValue(light.getParameters(), PropertyEnum.ZIP_CODE));
		}

		return address.toString();
	}

	/**
	 * Check time zone.
	 * 
	 * @param timeZoneLight the time zone light
	 */
	private void checkTimeZone(String timeZoneLight)
	{
		LightingControlRequest request = new LightingControlRequest(getUserContext());
		String timeZoneUser = null;

		List<Setting> userSettings = getSettingsBCF().fetchUserSettings(request).getSettings();

		for (Setting setting : userSettings)
		{
			if (setting.getPropertyEnum().equals(PropertyEnum.TIME_ZONE))
			{
				timeZoneUser = setting.getPropertyValue();
			}
		}

		if (ValidationUtil.isNull(timeZoneUser))
		{
			List<Setting> systemSettings = getSettingsBCF().fetchSystemSettings(request).getSettings();

			for (Setting setting : systemSettings)
			{
				if (setting.getPropertyEnum().equals(PropertyEnum.TIME_ZONE))
				{
					timeZoneUser = setting.getPropertyValue();
				}
			}
		}

		if (!new TimeZoneInfo(timeZoneLight).getDisplayNameGMT()
				.equals(new TimeZoneInfo(timeZoneUser).getDisplayNameGMT()))
		{
			List<TimeZoneInfo> timezones = getLcHelp().getTimeZoneInfoList();

			for (TimeZoneInfo timezone : timezones)
			{
				if (timezone.getTimeZone().getID().equals(timeZoneLight))
				{
					setTimeZoneDifferent(timezone.getDisplayName());
				}
			}
		}
	}

	/**
	 * Fill status message.
	 * 
	 * @param light the light
	 */
	private void fillStatusMessage(Light light)
	{
		if (!ValidationUtil.isNull(light.getCurrentStatusMessage()))
		{

			LightRequest lightRequest = new LightRequest();

			List<Light> lightList = new ArrayList<Light>();
			lightList.add(light);

			lightRequest.setLights(lightList);

			CurrentAlarmWarningMessageResponse currentMsgResponse =
					getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(lightRequest);

			if (!ValidationUtil.isNull(currentMsgResponse)
					&& currentMsgResponse.isOperationSuccess()
					&& !ValidationUtil.isNullOrEmpty(currentMsgResponse.getCurrentAlarmWarningMessages()))
			{

				CurrentAlarmWarningMessage currentAlarmWarningMessage =
						currentMsgResponse.getCurrentAlarmWarningMessages().get(0);

				getLightView().setLightStatus(light.getCurrentStatusMessage().getLightStatusEnumValue());

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentAlarmWarningMessage.getMessageDate());
				calendar.getTimeZone();

				getLightView().setStatusMessageDate(SensusStringUtil.createToString(calendar.getTime()));

			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(light.getCurrentStatusMessage().getDate());
			calendar.getTimeZone();

			getLightView().setStatusAlertsMessageDate(SensusStringUtil.createToString(calendar.getTime()));

			if (!ValidationUtil.isNull(light.getCurrentStatusMessage().getStatusExceptions()))
			{
				// Set status message list
				List<ViewExceptionMessage> exceptionMessages = new ArrayList<ViewExceptionMessage>();

				for (StatusException statusException : light.getCurrentStatusMessage().getStatusExceptions())
				{
					if (statusException.getId() == THREE)
					{
						Calendar dateFinal = Calendar.getInstance();
						Calendar dateInit = Calendar.getInstance();
						dateInit.setTime(light.getCurrentStatusMessage().getDate());
						BigDecimal mili =
								BigDecimal.valueOf(dateFinal.getTimeInMillis()).subtract(
										BigDecimal.valueOf(dateInit.getTimeInMillis()));
						BigDecimal time = mili.divide(BigDecimal.valueOf(TIMEINMILLIS));
						setComunicationFailure(time.toString());
					}

					ViewExceptionMessage message = new ViewExceptionMessage();

					message.setId(statusException.getId());
					message.setMessage(getText(statusException.getLabelKey()));
					message.setDate(LocalizationUtil.getUIDateFormat(getServletRequest()).format(
							light.getCurrentStatusMessage().getDate()));
					exceptionMessages.add(message);
				}

				getLightView().setCurrentAlarmWarningMessageList(light.getCurrentAlarmWarningMessageList());
				getLightView().setStatusExceptions(exceptionMessages);

			}
		}
	}

	/**
	 * Fill light view.
	 * 
	 * @param light the light
	 * @throws ParseException the parse exception
	 */
	private void fillLightView(Light light) throws ParseException
	{

		getLightView().setStateLight(light.getLightStateEnum());

		// getLightView().setLightIntensitylevels(light.getLightIntensitylevels());
		getLightView().setOffsetSchedule(new ViewOffsetSchedule());
		getLightView().setEventSchedule(new ViewEventSchedule());
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		setDateYesterday(LocalizationUtil.getUIDateFormat(getServletRequest()).format(c.getTime()));

		checkTimeZone(validProperty(light, PropertyEnum.TIME_ZONE, TZ));
		fillConsumption(light);
		fillTags(light);

		getLightView().setSunsetTime(validProperty(light, PropertyEnum.SUNSET_TIME, DATE_SHORT));
		getLightView().setSunsetTimeTz(validProperty(light, PropertyEnum.SUNSET_TIME, TZ));
		getLightView().setSunriseTime(validProperty(light, PropertyEnum.SUNRISE_TIME, DATE_SHORT));
		getLightView().setSunriseTimeTz(validProperty(light, PropertyEnum.SUNRISE_TIME, TZ));
		getLightView().setLampType(validProperty(light, PropertyEnum.LAMP_TYPE, DEFAULT));
		getLightView().setInputVoltageRange(validProperty(light, PropertyEnum.INPUT_VOLTAGE_RANGE, DEFAULT));
		getLightView().setTemperatureColor(validProperty(light, PropertyEnum.COLOR_TEMPERATURE, DEFAULT));
		getLightView().setSensusPartNumber(validProperty(light, PropertyEnum.MODEL_NUMBER, MODEL_NUMBER));
		getLightView().setDateAdded(validProperty(light, PropertyEnum.DATE_ADDED, DATE));
		getLightView().setDateInstalled(validProperty(light, PropertyEnum.DATE_INSTALLED, DATE));
		getLightView().setFirmware(validProperty(light, PropertyEnum.FIRMWARE_VERSION, DEFAULT));
		getLightView().setLongitude(validProperty(light, PropertyEnum.LONGITUDE, COORDINATES));
		getLightView().setLatitude(validProperty(light, PropertyEnum.LATITUDE, COORDINATES));
		getLightView().setWattage(validProperty(light, PropertyEnum.WATTAGE_RATING, DEFAULT));
		getLightView().setHousing(validProperty(light, PropertyEnum.HOUSING, DEFAULT));
		getLightView().setDimmable(validProperty(light, PropertyEnum.DIMMABLE, DIMMABLE));
		getLightView().setHousingColor(validProperty(light, PropertyEnum.HOUSING_COLOR, DEFAULT));
		getLightView().setManufacturer(validProperty(light, PropertyEnum.MANUFACTURER, DEFAULT));
		getLightView().setBulbSerialNumber(validProperty(light, PropertyEnum.BULB_SERIAL_NUMBER, DEFAULT));
		getLightView().setLightDriverSerial(validProperty(light, PropertyEnum.BALLAST_SERIAL_NUMBER, DEFAULT));
		getLightView().setLowerSerialNumber(validProperty(light, PropertyEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, DEFAULT));
		getLightView().setUpperSerialNumber(validProperty(light, PropertyEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, DEFAULT));

		getLightView().setIsProtected(light.getProtect());

		// load schedules data
		fillSchedules(light);

	}

	/**
	 * Fill schedules.
	 * 
	 * @param light the light
	 */
	private void fillSchedules(Light light)
	{
		if (!ValidationUtil.isNull(light.getOffSetSchedule()))
		{
			ScheduleRequest request = new ScheduleRequest(getUserContext());

			OffsetSchedule schedule = new OffsetSchedule();
			schedule.setId(light.getOffSetSchedule().getId());
			request.setSchedule(schedule);

			schedule = new OffsetSchedule();
			schedule = (OffsetSchedule)getScheduleBCF().fetchScheduleById(request).getSchedules().get(0);

			getLightView().getOffsetSchedule().setDescription(schedule.getDescription());
			getLightView().getOffsetSchedule().setId(schedule.getId());
			getLightView().getOffsetSchedule().setName(schedule.getName());

			if ((!ValidationUtil.isNull(schedule.getSunriseOffsetMinutes()))
					&& (!ValidationUtil.isNull(schedule.getSunriseBefore())))
			{
				StringBuilder sunrise =
						new StringBuilder(getText("smartpointdetail.sunrisesunset.turnlightsoff"));
				sunrise.append(SPACER + Math.abs(schedule.getSunriseOffsetMinutes()));

				if (schedule.getSunriseBefore())
				{
					sunrise.append(SPACER
							+ getText("smartpointdetail.sunrisesunset.beforesunrise"));
				}
				else
				{
					sunrise.append(SPACER + getText("smartpointdetail.sunrisesunset.aftersunrise"));
				}

				getLightView().setSunriseOffset(sunrise.toString());
			}

			if ((!ValidationUtil.isNull(schedule.getSunsetOffsetMinutes()))
					&& (!ValidationUtil.isNull(schedule.getSunsetBefore())))
			{
				StringBuilder sunset =
						new StringBuilder(getText("smartpointdetail.sunrisesunset.turnlightson"));
				sunset.append(SPACER + Math.abs(schedule.getSunsetOffsetMinutes()));

				if (schedule.getSunsetBefore())
				{
					sunset.append(SPACER
							+ getText("smartpointdetail.sunrisesunset.beforesunset"));
				}
				else
				{
					sunset.append(SPACER
							+ getText("smartpointdetail.sunrisesunset.aftersunset"));
				}
				getLightView().setSunsetOffset(sunset.toString());
			}
		}

		if (!ValidationUtil.isNull(light.getEventSchedule()))
		{
			getLightView().getEventSchedule().setDescription(light.getEventSchedule().getDescription());
			getLightView().getEventSchedule().setId(light.getEventSchedule().getId());
			getLightView().getEventSchedule().setName(light.getEventSchedule().getName());
		}
	}

	/**
	 * Fill count alarms.
	 * 
	 * @return the string
	 */
	public void fillCountAlarms(Light light)
	{
		if (!ValidationUtil.isNull(light.getCurrentStatusMessage()))
		{
			for (StatusException messages : light.getCurrentStatusMessage().getStatusExceptions())
			{
				if ((messages.getStatusExceptionTypeEnumValue().equals(ONE))
						|| (messages.getStatusExceptionTypeEnumValue().equals(TWO))
						|| (messages.getStatusExceptionTypeEnumValue().equals(THREE)))
				{
					setCountAlarms(getCountAlarms() + 1);
				}
				else
				{
					setCountWarning(getCountWarning() + 1);
				}
			}
		}
	}

	/**
	 * Fetch process by light.
	 * 
	 * @param light the light
	 * @return the list
	 */
	public List<Process> fetchProcessByLight(Light light)
	{
		LightRequest request = new LightRequest(getUserContext());
		request.addLight(light);
		ProcessResponse response = getProcessBCF().fetchProcessByLight(request);
		List<Process> listProcess = response.getProcesses();

		return listProcess;
	}

	/**
	 * Fill consumption.
	 * 
	 * @param light the light
	 */
	private void fillConsumption(Light light)
	{
		getLightView().setCurrentLast(
				String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.AC_CURRENT, FLOAT))));

		getLightView().setCurrentMin(
				String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.AC_CURRENT_MIN, FLOAT))));

		getLightView().setCurrentMax(
				String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.AC_CURRENT_MAX, FLOAT))));

		getLightView().setVoltageLast(
				String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.AC_VOLTAGE, FLOAT))));

		getLightView().setVoltageMin(
				String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.AC_VOLTAGE_MIN, FLOAT))));

		getLightView().setVoltageMax(
				String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.AC_VOLTAGE_MAX, FLOAT))));

		// getLightView().setConsumptionLast(
		// String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.CONSUMPTION, FLOAT))));

		// getLightView().setConsumptionMin(
		// String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.CONSUMPTION_MIN, FLOAT))));

		// getLightView().setConsumptionMax(
		// String.valueOf(Float.parseFloat(validProperty(light, PropertyEnum.CONSUMPTION_MAX, FLOAT))));

	}

	/**
	 * Provides data for a droplist of all available Groups. If an error occurs, an error prompt will be added to the
	 * list.
	 * 
	 * @return Always "SUCCESS".
	 */
	public List<IdValuePair> getGroupList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryPaginationRequest request = new InquiryPaginationRequest(getUserContext());

			request.setPageSize(0);

			InquiryGroupResponse response = getGroupBCF().fetchAllGroups(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(GROUP_LIST_ERROR);
				}
			}
			else
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
				if (!ValidationUtil.isNull(response.getGroups()))
				{
					for (Group group : response.getGroups())
					{
						list.add(new IdValuePair(group.getId(), group.getName()));
					}
				}
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(GROUP_LIST_ERROR, e);
			}
		}
		return list;
	}

	/**
	 * Gets the offset schedule list.
	 * 
	 * @return the schedule list
	 */
	public List<IdValuePair> getOffsetScheduleList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryScheduleRequest request = new InquiryScheduleRequest(getUserContext());

			request.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);

			InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(SCHEDULE_LIST_ERROR);
				}
			}
			else
			{
				if (!ValidationUtil.isNull(response.getSchedules()))
				{
					for (Schedule schedule : response.getSchedules())
					{
						list.add(new IdValuePair(schedule.getId(), schedule.getName()));
					}
				}
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(SCHEDULE_LIST_ERROR, e);
			}
		}
		return list;
	}

	/**
	 * Gets the offset schedule list.
	 * 
	 * @return the schedule list
	 */
	public List<IdValuePair> getEventScheduleList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryScheduleRequest request = new InquiryScheduleRequest(getUserContext());

			request.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);

			InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(SCHEDULE_LIST_ERROR);
				}
			}
			else
			{
				if (!ValidationUtil.isNull(response.getSchedules()))
				{
					for (Schedule schedule : response.getSchedules())
					{
						list.add(new IdValuePair(schedule.getId(), schedule.getName()));
					}
				}
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(SCHEDULE_LIST_ERROR, e);
			}
		}
		return list;
	}

	/**
	 * Fetch process by id.
	 * 
	 * @return the string
	 */
	public String fetchProcessById()
	{
		ProcessRequest request = new ProcessRequest(getUserContext());

		Process process = new Process();

		process.setId(getProcessId());

		request.setProcess(process);

		ProcessResponse response = getProcessBCF().fetchProcessById(request);

		if (response.isOperationSuccess())
		{
			if (!ValidationUtil.isNullOrEmpty(response.getProcesses())
					&& !ValidationUtil.isNull(response.getProcesses().get(0)))
			{
				process = response.getProcesses().get(0);
				setSmartpointHistory(new SmartpointHistory());
				getSmartpointHistory().setName(ValidationUtil.isNullOrEmpty(
						SensusStringUtil.createToString(process.getLcAction().getDescription()), ""));
				getSmartpointHistory().setDate(process.getCreateDate());
				getSmartpointHistory().setTime(hour.format(process.getCreateDate())
						+ SPACER + tz.format(process.getCreateDate()));
				getSmartpointHistory().setProcessCompleted(ValidationUtil.isNullOrEmpty(
						SensusStringUtil.createToString(process.getIsProcessComplete().toString()), ""));
				getSmartpointHistory().setDescription(ValidationUtil.isNullOrEmpty(
						SensusStringUtil.createToString(process.getDescription()), ""));
				getSmartpointHistory().setProcessCompletedIn(getCompletedIn(process));
				getSmartpointHistory().setProcessId(ValidationUtil.isNullOrEmpty(
						SensusStringUtil.createToString(process.getId().toString()), ""));
				getSmartpointHistory().setCreateUser(ValidationUtil.isNullOrEmpty(
						SensusStringUtil.createToString(process.getCreateUser()), ""));
			}

		}

		return SUCCESS;
	}

	/**
	 * Provides data for a droplist of all available Actions.
	 * 
	 * @return Always "SUCCESS".
	 */
	public List<IdValuePair> getActionList()
	{
		List<IdValuePair> actionList = new ArrayList<IdValuePair>();

		actionList.add(new IdValuePair("clearManualOverride", getText("smartpoint.actions.clearManual")));
		actionList.add(new IdValuePair("editLightStatusDetail", getText("smartpoint.actions.editLight")));
		actionList.add(new IdValuePair("getDataFromLight", getText("smartpoint.actions.getDataLight")));
		actionList.add(new IdValuePair("resetValue", getText("smartpoint.actions.reset")));

		return actionList;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the update light status result.
	 * 
	 * @return the update light status result
	 */
	public SearchJsonResult getSmartpointDetailResult()
	{
		return smartpointDetailResult;
	}

	/**
	 * Sets the update light status result.
	 * 
	 * @param smartpointDetailResult the new force light status result
	 */
	public void setSmartpointDetailResult(SearchJsonResult smartpointDetailResult)
	{
		this.smartpointDetailResult = smartpointDetailResult;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
	}

	/**
	 * Gets the light view.
	 * 
	 * @return the light view
	 */
	public ViewLight getLightView()
	{
		return lightView;
	}

	/**
	 * Sets the light view.
	 * 
	 * @param lightView the new light view
	 */
	public void setLightView(ViewLight lightView)
	{
		this.lightView = lightView;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(List<String> errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the error message
	 */
	public List<String> getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tagBCF
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the tagBCF to set
	 */
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the new schedule bcf
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Gets the count alerts.
	 * 
	 * @return the count alerts
	 */
	public Integer getCountAlerts()
	{
		return countAlerts;
	}

	/**
	 * Sets the count alerts.
	 * 
	 * @param countAlerts the new count alerts
	 */
	public void setCountAlerts(Integer countAlerts)
	{
		this.countAlerts = countAlerts;
	}

	/**
	 * Gets the count alarms.
	 * 
	 * @return the count alarms
	 */
	public Integer getCountAlarms()
	{
		return countAlarms;
	}

	/**
	 * Sets the count alarms.
	 * 
	 * @param countAlarms the new count alarms
	 */
	public void setCountAlarms(Integer countAlarms)
	{
		this.countAlarms = countAlarms;
	}

	/**
	 * Gets the count warning.
	 * 
	 * @return the count warning
	 */
	public Integer getCountWarning()
	{
		return countWarning;
	}

	/**
	 * Sets the count warning.
	 * 
	 * @param countWarning the new count warning
	 */
	public void setCountWarning(Integer countWarning)
	{
		this.countWarning = countWarning;
	}

	/**
	 * Gets the date today.
	 * 
	 * @return the date today
	 */
	public String getDateToday()
	{
		return dateToday;
	}

	/**
	 * Sets the date today.
	 * 
	 * @param dateToday the new date today
	 */
	public void setDateToday(String dateToday)
	{
		this.dateToday = dateToday;
	}

	/**
	 * Gets the date yesterday.
	 * 
	 * @return the date yesterday
	 */
	public String getDateYesterday()
	{
		return dateYesterday;
	}

	/**
	 * Sets the date yesterday.
	 * 
	 * @param dateYesterday the new date yesterday
	 */
	public void setDateYesterday(String dateYesterday)
	{
		this.dateYesterday = dateYesterday;
	}

	/**
	 * Sets the count power failure.
	 * 
	 * @param countPowerFailure the new count power failure
	 */
	public void setCountPowerFailure(Integer countPowerFailure)
	{
		this.countPowerFailure = countPowerFailure;
	}

	/**
	 * Gets the count power failure.
	 * 
	 * @return the count power failure
	 */
	public Integer getCountPowerFailure()
	{
		return countPowerFailure;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return tags;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the processBCF to set
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the processBCF
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF the settingsBCF to set
	 */
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settingsBCF
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the time zone different.
	 * 
	 * @param timeZoneDifferent the timeZoneDifferent to set
	 */
	public void setTimeZoneDifferent(String timeZoneDifferent)
	{
		this.timeZoneDifferent = timeZoneDifferent;
	}

	/**
	 * Gets the time zone different.
	 * 
	 * @return the timeZoneDifferent
	 */
	public String getTimeZoneDifferent()
	{
		return timeZoneDifferent;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the lcHelp to set
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the lc help.
	 * 
	 * @return the lcHelp
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the table groups.
	 * 
	 * @param tableGroups the tableGroups to set
	 */
	public void setTableGroups(List<Group> tableGroups)
	{
		this.tableGroups = tableGroups;
	}

	/**
	 * Gets the table groups.
	 * 
	 * @return the tableGroups
	 */
	public List<Group> getTableGroups()
	{
		return tableGroups;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the smartpoint history.
	 * 
	 * @return the smartpoint history
	 */
	public SmartpointHistory getSmartpointHistory()
	{
		return smartpointHistory;
	}

	/**
	 * Sets the smartpoint history.
	 * 
	 * @param smartpointHistory the new smartpoint history
	 */
	public void setSmartpointHistory(SmartpointHistory smartpointHistory)
	{
		this.smartpointHistory = smartpointHistory;
	}

	/**
	 * Gets the smartpoint id.
	 * 
	 * @return the smartpoint id
	 */
	public Integer getSmartpointId()
	{
		return smartpointId;
	}

	/**
	 * Sets the smartpoint id.
	 * 
	 * @param smartpointId the new smartpoint id
	 */
	public void setSmartpointId(Integer smartpointId)
	{
		this.smartpointId = smartpointId;
	}

	/**
	 * Gets the date today formated.
	 * 
	 * @return the dateTodayFormated
	 */
	public String getDateTodayFormated()
	{
		return dateTodayFormated;
	}

	/**
	 * Sets the date today formated.
	 * 
	 * @param dateTodayFormated the dateTodayFormated to set
	 */
	public void setDateTodayFormated(String dateTodayFormated)
	{
		this.dateTodayFormated = dateTodayFormated;
	}

	/**
	 * @return the comunicationFailure
	 */
	public String getComunicationFailure()
	{
		return comunicationFailure;
	}

	/**
	 * @param comunicationFailure the comunicationFailure to set
	 */
	public void setComunicationFailure(String comunicationFailure)
	{
		this.comunicationFailure = comunicationFailure;
	}

	/**
	 * @return the tagList
	 */
	public List<IdValuePair> getTagList()
	{
		return tagList;
	}

	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(List<IdValuePair> tagList)
	{
		this.tagList = tagList;
	}

	/**
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * @return the lightRequest
	 */
	public LightRequest getLightRequest()
	{
		return lightRequest;
	}

	/**
	 * @param lightRequest the lightRequest to set
	 */
	public void setLightRequest(LightRequest lightRequest)
	{
		this.lightRequest = lightRequest;
	}

}