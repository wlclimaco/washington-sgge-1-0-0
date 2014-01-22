package com.sensus.lc.communication.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.csv.CSVUtil;
import com.sensus.common.model.Message;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusConvertUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.communication.bcl.IAlertCommunicationBCL;
import com.sensus.lc.communication.bcl.IEmailServiceBCL;
import com.sensus.lc.communication.dac.IAlertCommunicationDAC;
import com.sensus.lc.communication.model.AlertCommunication;
import com.sensus.lc.communication.model.AlertCommunicationConfig;
import com.sensus.lc.communication.model.LightAlertEmail;
import com.sensus.lc.communication.model.request.AlertCommunicationConfigRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationMaintenanceRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationRequest;
import com.sensus.lc.communication.model.request.EmailRequest;
import com.sensus.lc.group.bcl.IGroupBCL;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.bcl.ILightNotificationHistoryBCL;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.criteria.AlertCommunicationCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.settings.bcl.ISettingsBCL;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.tenant.dac.ITenantDAC;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.user.bcl.IUserBCL;
import com.sensus.lc.user.model.User;

/**
 * Implementation for AlertCommunication business area.
 */
public class AlertCommunicationBCLImpl implements IAlertCommunicationBCL
{

	/**
	 * BCL's.
	 */
	private ILightBCL lightBCL;
	private ILightNotificationHistoryBCL notificationHistoryBCL;
	private IEmailServiceBCL emailServiceBCL;
	private ISettingsBCL settingsBCL;
	private IUserBCL userBCL;
	private IGroupBCL groupBCL;

	/**
	 * DAC's.
	 */
	private IAlertCommunicationDAC alertCommunicationDAC;
	private ITenantDAC tenantDAC;

	/**
	 * CSV Column set.
	 */
	private List<CSVColumn> alertCSVColumns;
	private static final String CSV_FILE_EXTENSION = ".csv";
	private static final String[] ALERT_EMAIL_CSV_COLUMNS = new String[] {
			"poleId", "flexNetId", "address", "city", "alertType", "alertSubType", "messageDate", "voltage",
			"current", "lifeCycleState"};

	/**
	 * Mail template attributes.
	 */
	private static final String TEMPLATE_SUBJECT_PROPERTY_SINGLE = "communication.mail.subject.single";
	private static final String TEMPLATE_BODY_PROPERTY_SINGLE = "communication.mail.body.single";
	private static final String TEMPLATE_SUBJECT_PROPERTY_SPECIFIC = "communication.mail.subject.specific";
	private static final String TEMPLATE_BODY_PROPERTY_SPECIFIC = "communication.mail.body.specific";
	private static final Integer TOTAL_PREVIEW_ALERTS = 10;
	private static final String HOUR_FORMAT = "HH:mm a";
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
	private static final Integer BODY_TEXT_PADDING_SIZE = 20;
	private static final String LINE_BREAK = "\n\n";

	/**
	 * Phase constants.
	 */
	private static final Integer PHASE_0 = 0;
	private static final Integer PHASE_1 = 1;
	private static final Integer PHASE_2 = 2;
	private static final Integer PHASE_4 = 4;

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(AlertCommunicationBCLImpl.class);

	/**
	 * General constants.
	 */
	private static final Integer MINUTES_MILLIS = 60000;
	private static final String DEFAULT_LOCALE = "en_US";
	private static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
	private static final String DEFAULT_EXCEPTION_MSG = "Error sending e-mails: ";
	private static final String BCL_EXCEPTION_MSG = "AlertCommunicationBCLImpl";

	private MessageSource messageSource;
	private String fromAddress;

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource()
	{
		return messageSource;
	}

	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress()
	{
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress)
	{
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the groupBCL
	 */
	public IGroupBCL getGroupBCL()
	{
		return groupBCL;
	}

	/**
	 * @param groupBCL the groupBCL to set
	 */
	public void setGroupBCL(IGroupBCL groupBCL)
	{
		this.groupBCL = groupBCL;
	}

	/**
	 * @return the userBCL
	 */
	public IUserBCL getUserBCL()
	{
		return userBCL;
	}

	/**
	 * @param userBCL the userBCL to set
	 */
	public void setUserBCL(IUserBCL userBCL)
	{
		this.userBCL = userBCL;
	}

	/**
	 * @return the settingsBCL
	 */
	public ISettingsBCL getSettingsBCL()
	{
		return settingsBCL;
	}

	/**
	 * @param settingsBCL the settingsBCL to set
	 */
	public void setSettingsBCL(ISettingsBCL settingsBCL)
	{
		this.settingsBCL = settingsBCL;
	}

	/**
	 * @return the lightBCL
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * @param lightBCL the lightBCL to set
	 */
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * @return the notificationHistoryBCL
	 */
	public ILightNotificationHistoryBCL getNotificationHistoryBCL()
	{
		return notificationHistoryBCL;
	}

	/**
	 * @param notificationHistoryBCL the notificationHistoryBCL to set
	 */
	public void setNotificationHistoryBCL(ILightNotificationHistoryBCL notificationHistoryBCL)
	{
		this.notificationHistoryBCL = notificationHistoryBCL;
	}

	/**
	 * @return the tenantDAC
	 */
	public ITenantDAC getTenantDAC()
	{
		return tenantDAC;
	}

	/**
	 * @param tenantDAC the tenantDAC to set
	 */
	public void setTenantDAC(ITenantDAC tenantDAC)
	{
		this.tenantDAC = tenantDAC;
	}

	/**
	 * @return the alertCSVColumns
	 */
	public List<CSVColumn> getAlertCSVColumns()
	{
		return alertCSVColumns;
	}

	/**
	 * @param alertCSVColumns the alertCSVColumns to set
	 */
	public void setAlertCSVColumns(List<CSVColumn> alertCSVColumns)
	{
		this.alertCSVColumns = alertCSVColumns;
	}

	/**
	 * @return the alertCommunicationDAC
	 */
	public IAlertCommunicationDAC getAlertCommunicationDAC()
	{
		return alertCommunicationDAC;
	}

	/**
	 * @param alertCommunicationDAC the alertCommunicationDAC to set
	 */
	public void setAlertCommunicationDAC(IAlertCommunicationDAC alertCommunicationDAC)
	{
		this.alertCommunicationDAC = alertCommunicationDAC;
	}

	/**
	 * @return the emailServiceBCL
	 */
	public IEmailServiceBCL getEmailServiceBCL()
	{
		return emailServiceBCL;
	}

	/**
	 * @param emailServiceBCL the emailServiceBCL to set
	 */
	public void setEmailServiceBCL(IEmailServiceBCL emailServiceBCL)
	{
		this.emailServiceBCL = emailServiceBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.communication.bcl.IAlertCommunicationBCL#fetchAlertCommunicationByRequest(com.sensus.mlc.communication
	 * .model.request.AlertCommunicationRequest)
	 */
	@Override
	public InternalResultsResponse<AlertCommunication> fetchAlertCommunicationByRequest(
			AlertCommunicationRequest request)
	{
		return getAlertCommunicationDAC().fetchAlertCommunicationByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.communication.bcl.IAlertCommunicationBCL#fetchAlertCommunicationConfigByRequest(com.sensus.mlc
	 * .communication.model.request.AlertCommunicationConfigRequest)
	 */
	@Override
	public InternalResultsResponse<AlertCommunicationConfig> fetchAlertCommunicationConfigByRequest(
			AlertCommunicationConfigRequest request)
	{
		return getAlertCommunicationDAC().fetchAlertCommunicationConfigByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.communication.bcl.IAlertCommunicationBCL#updateAlertCommunication(com.sensus.mlc.communication
	 * .model.request.AlertCommunicationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateAlertCommunication(AlertCommunicationMaintenanceRequest request)
	{
		return getAlertCommunicationDAC().updateAlertCommunication(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.communication.bcl.IAlertCommunicationBCL#sendEmail(com.sensus.mlc.communication.model.request.
	 * EmailRequest)
	 */
	@Override
	public InternalResponse sendEmail(EmailRequest request)
	{
		return getEmailServiceBCL().sendEmail(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#verifyAlertCommunication()
	 */
	@Override
	public void verifyAlertCommunication()
	{

		/*
		 * Fetch all tenants to verify alerts.
		 */
		InternalResultsResponse<Tenant> tenantResponse = getTenantDAC().fetchAllTenant();

		if (tenantResponse.isInError()) // Log error.
		{
			logError("error on fetchAllTenant().");
			return;
		}

		List<Tenant> tenants = tenantResponse.getResultsList();
		for (Tenant tenant : tenants)
		{
			/*
			 * 1) Fetch the alert communication entry for each tenant
			 */
			AlertCommunicationRequest alertCommunicationRequest = new AlertCommunicationRequest(tenant.getId());

			InternalResultsResponse<AlertCommunication> response =
					fetchAlertCommunicationByRequest(alertCommunicationRequest);

			if (response.isInError() || ValidationUtil.isNullOrEmpty(response.getResultsList()))
			{
				continue; // not found go to next tenant.
			}
			AlertCommunication alertCommunication = response.getFirstResult();

			// Difference from last execution (in minutes).
			Long differenceFromLastExecution =
					(LCDateUtil.getNewDateUTC().getTime() - alertCommunication.getPhaseStartTime().getTime())
							/ MINUTES_MILLIS;
			/*
			 * 2) fetch alerts since the phase beginning.
			 */
			List<NotificationHistory> notifications =
					fetchAlertsForEmail(alertCommunication.getPhaseStartTime(), tenant.getId());

			/* Test timeout to move back to phase 0. */
			if (!ValidationUtil.isNull(tenant.getCommunicationCycleTimeout())
					&& (differenceFromLastExecution >= tenant.getCommunicationCycleTimeout()))
			{
				alertCommunication.setPhaseStartTime(LCDateUtil.getNewDateUTC());
				alertCommunication.setCycleStartTime(LCDateUtil.getNewDateUTC());
				alertCommunication.setPhaseIndicator(PHASE_0);
				updateAlertCommunication(new AlertCommunicationMaintenanceRequest(alertCommunication));
				continue;
			}

			/*
			 * 3) Fetch configuration set.
			 */
			AlertCommunicationConfigRequest configRequest = new AlertCommunicationConfigRequest();
			configRequest.setTenantId(tenant.getId());

			InternalResultsResponse<AlertCommunicationConfig> configResponse =
					fetchAlertCommunicationConfigByRequest(configRequest);
			if (configResponse.isInError()) // Log error and move forward to the next tenant.
			{
				logError("Error executing fetchAlertCommunicationConfigByRequest.");
				continue;
			}

			Boolean flagMovePhase = Boolean.FALSE; // Used to mark and move to the next phase.

			for (AlertCommunicationConfig alertConfig : configResponse.getResultsList())
			{

				// Special treatment for phase 2, should pick alerts from phase 1 and 2.
				if (alertCommunication.getPhaseIndicator().equals(PHASE_2))
				{
					notifications = fetchAlertsForEmail(alertCommunication.getCycleStartTime(), tenant.getId());
				}

				// should update to this new phase.
				if (flagMovePhase)
				{
					alertCommunication.setPhaseStartTime(LCDateUtil.getNewDateUTC());
					alertCommunication.setPhaseIndicator(alertConfig.getPhase());
					updateAlertCommunication(new AlertCommunicationMaintenanceRequest(alertCommunication));
					break;
				}

				// is the current phase?
				if (alertCommunication.getPhaseIndicator().equals(alertConfig.getPhase()))
				{
					// time reached, time to execute this phase otherwise just ignore.
					if (differenceFromLastExecution >= alertConfig.getElapsedTime())
					{
						// Update to next phase.
						flagMovePhase = Boolean.TRUE;

						/*
						 * 4) If current phase isn't 0 or 1 then treat user subscriptions and send email.
						 */
						if (!alertCommunication.getPhaseIndicator().equals(PHASE_0) &&
								!alertCommunication.getPhaseIndicator().equals(PHASE_1) &&
								!ValidationUtil.isNullOrEmpty(notifications))
						{

							treatUsersSubscriptions(tenant.getId(), notifications, differenceFromLastExecution,
									calculateDateTz(tenant.getLightTimeZone(), alertCommunication.getPhaseStartTime()),
									tenant.getLightTimeZone());
						}

						// Last phase detected, special rule to move back to phase 0.
						if (alertCommunication.getPhaseIndicator().equals(PHASE_4))
						{
							alertCommunication.setPhaseStartTime(LCDateUtil.getNewDateUTC());
							alertCommunication.setCycleStartTime(LCDateUtil.getNewDateUTC());
							alertCommunication.setPhaseIndicator(PHASE_0);
							updateAlertCommunication(new AlertCommunicationMaintenanceRequest(alertCommunication));
						}

					}
				}
			} // end of alert configs.
		}
	}

	/**
	 * Treat users subscriptions.
	 * 
	 * @param tenantId the tenant id
	 * @param notifications the notifications
	 * @param differenceFromLastExecution the difference from last execution
	 * @param startTime the start time
	 */
	private void treatUsersSubscriptions(Integer tenantId, List<NotificationHistory> notifications,
			Long diffFromLastExecution, Date startTime, String tenantTz)
	{
		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest();
		UserContext userContext = new UserContext();
		userContext.setTenant(new Tenant(tenantId));

		inquiryPaginationRequest.setUserContext(userContext);
		InternalResultsResponse<User> userResponse = getUserBCL().fetchAllUsers(inquiryPaginationRequest);

		if (userResponse.isInError()) // Can't fetch users, log error and move back.
		{
			logError("Error executing fetchAllUsers.");
			return;
		}

		// List that contains users email and their subscriptions.
		List<PropertyEnum> userSubscriptionProperties = null;

		// For each user get it's locale settings, email address, alert preferences then send email.
		for (User user : userResponse.getResultsList())
		{
			userContext.setId(user.getId());
			LightingControlRequest controlRequest = new LightingControlRequest();
			controlRequest.setTenant(new Tenant(tenantId));
			controlRequest.setUserId(user.getId());
			controlRequest.setUserContext(userContext);
			InternalResultsResponse<Setting> settingsResponse = getSettingsBCL().fetchUserSettings(controlRequest);

			if (settingsResponse.isInError()) // Log error and move to the next user.
			{
				logError("Error or missing settings for user " + user.getUserName());
				continue;
			}

			String locale = null;
			userSubscriptionProperties = new ArrayList<PropertyEnum>();

			// Test subscribe
			for (Setting setting : settingsResponse.getResultsList())
			{
				// Email template internationalization. Read properties file according to choosen language.
				if ((setting.getPropertyEnum() == PropertyEnum.LANGUAGE) && !isNullOrEmpty(setting.getPropertyValue()))
				{
					locale = setting.getPropertyValue();
				}
				if ("true".equals(setting.getPropertyValue()))
				{
					// Test every subscription for every notification history to send emails.
					userSubscriptionProperties.add(setting.getPropertyEnum());
				}
			}

			// No subscription for this user, go to next
			if (isNullOrEmpty(userSubscriptionProperties))
			{
				continue;
			}

			// Generate CSV and Send email.
			generateAlertCSV(notifications, userSubscriptionProperties, user,
					diffFromLastExecution, startTime, locale, tenantTz, tenantId);
		}

	}

	/**
	 * Generate alert csv.
	 * 
	 * @param notifications the notifications
	 * @return the string
	 */
	private void generateAlertCSV(List<NotificationHistory> notifications, List<PropertyEnum> subscriptedProperties,
			User user, Long diffFromLastExecution, Date startTime, String locale, String tenantTz, Integer tenantId)
	{
		// CSV file name and location
		String fileName = System.getProperty(JAVA_IO_TMPDIR) + new Date().getTime() + CSV_FILE_EXTENSION;

		// Alert list, will be filled according user subscription
		Map<String, List<LightAlertEmail>> classificationMAP = new HashMap<String, List<LightAlertEmail>>();

		Light uniqueLight = null;
		for (NotificationHistory notification : notifications)
		{
			Light light = getLightBCL().fetchById(new FetchByIdRequest(notification.getLightId())).getFirstResult();

			// User is not in the same group.
			if (!ValidationUtil.isNullOrEmpty(user.getGroups()) && !hasUserGroup(user, light, tenantId))
			{
				continue;
			}

			for (AlertClassification classification : notification.getAlertClassifications())
			{
				LightAlertEmail lightAlertEmail = new LightAlertEmail();
				String alertSubType = classification.getAlertSubType().name();

				lightAlertEmail.setPoleId(light.getPoleId());
				lightAlertEmail.setFlexNetId(String.valueOf(light.getRadio().getFlexNetId()));
				lightAlertEmail.setAddress(light.getRadio().getLocation().getAddress());
				lightAlertEmail.setCity(light.getRadio().getLocation().getCity());
				lightAlertEmail.setAlertType(classification.getAlertType().name());
				lightAlertEmail.setAlertSubType(alertSubType);
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				lightAlertEmail.setMessageDate(sdf.format(calculateDateTz(tenantTz, classification.getMessageDate())));
				lightAlertEmail.setVoltage(String.valueOf(light.getLastOperationalData().getAcVoltage()));
				lightAlertEmail.setCurrent(String.valueOf(light.getLastOperationalData().getAcCurrent()));
				lightAlertEmail.setLifeCycleState(light.getLifeCycleState().toString());

				/*
				 * Test alert type and subscriptions.
				 */
				if (((classification.getAlertSubType() == AlertSubTypeEnum.BOARD_FAILURE)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_ALARM_BOARD_FAILURE)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.LAMP_FAILURE)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_ALARM_LAMP_FAILURE)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.POWER_FAILURE)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_ALARM_POWER_FAILURE)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.BROWN_OUT_DETECTED)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_WARN_BROWNOUT_DETECTED)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.COMMUNICATION_FAIL)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_WARN_COMMN_FAIL)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.POWER_SURGE_DETECTED)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_WARN_POWER_SURGE)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.METROLOGY_COM_FAILURE)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.METROLOGY_ERROR)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_ALARM_METROLOGY_ERROR)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.HIGH_CURRENT)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_WARN_HIGH_CURRENT)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.LOW_CURRENT)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_WARN_LOW_CURRENT)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.METROLOGY_RESET)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_WARN_METROLOGY_RESET)) ||
						((classification.getAlertSubType() == AlertSubTypeEnum.REVERSE_ENERGY)
						&& subscriptedProperties.contains(PropertyEnum.SUBSCRIBE_WARN_REVERSE_ENERGY)))
				{

					// Create new List and insert it on alerts map.
					if (ValidationUtil.isNullOrEmpty(classificationMAP.get(alertSubType)))
					{
						List<LightAlertEmail> newAlertList = new ArrayList<LightAlertEmail>();
						newAlertList.add(lightAlertEmail);
						classificationMAP.put(alertSubType, newAlertList);
					}
					else
					{
						// Get the current List and add the new alert found.
						List<LightAlertEmail> alertList = classificationMAP.get(alertSubType);
						alertList.add(lightAlertEmail);
						classificationMAP.put(alertSubType, alertList);
					}
				}

			}
			uniqueLight = light;
		}

		// For each alert subtype get alerts from Map and send emails.
		Set<String> mapKeys = classificationMAP.keySet();
		for (String key : mapKeys)
		{
			// Exports CSV file.
			List<CSVColumn> selectedColumns = CSVUtil.filterColumns(alertCSVColumns, ALERT_EMAIL_CSV_COLUMNS);
			CSVUtil.exportFile(selectedColumns, fileName, classificationMAP.get(key), null);

			// Log into log file.
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Sending alerts to e-mail: " + user.getEmail()
						+ " total alerts found: "
						+ classificationMAP.get(key).size());
			}

			sendEmail(locale, classificationMAP.get(key), uniqueLight, startTime, diffFromLastExecution, fileName,
					user.getEmail());

		}

	}

	/**
	 * Prepare template and send email.
	 * 
	 * @param locale the locale
	 * @param classifications the classifications
	 * @param uniqueLight the unique light
	 * @param startTime the start time
	 * @param diffFromLastExecution the diff from last execution
	 * @param fileName the file name
	 * @param email the email
	 */
	private void sendEmail(String locale, List<LightAlertEmail> classifications, Light uniqueLight, Date startTime,
			Long diffFromLastExecution, String fileName, String email)
	{
		EmailRequest emailRequest = new EmailRequest();
		String mailSubject = null;
		String mailBody = null;

		// Format email template subject and body.
		if (classifications.size() == 1)
		{
			// Prapare template for single alert.
			mailSubject = formatMailSubjectSingle(classifications.get(0), uniqueLight, locale);
			mailBody = formatMailBodySingle(classifications.get(0), startTime, uniqueLight, locale);
		}
		else
		{ // Prepare template for multiple alerts.
			mailSubject = formatMailSubjectSpecific(classifications, diffFromLastExecution, startTime, locale);
			mailBody = formatMailBodySpecific(classifications, diffFromLastExecution, startTime, locale);

			mailBody = mailBody + LINE_BREAK
					+ padRight("Pole Id", BODY_TEXT_PADDING_SIZE)
					+ padRight("Flexnet Id", BODY_TEXT_PADDING_SIZE)
					+ padRight("Address", BODY_TEXT_PADDING_SIZE)
					+ LINE_BREAK
					+ "---------------------------------------------------------------"
					+ LINE_BREAK;

			Integer idx = 0;
			for (LightAlertEmail alert : classifications)
			{
				mailBody += padRight(alert.getPoleId(), BODY_TEXT_PADDING_SIZE)
						+ padRight(alert.getFlexNetId(), BODY_TEXT_PADDING_SIZE)
						+ padRight(alert.getAddress(), BODY_TEXT_PADDING_SIZE)
						+ LINE_BREAK;
				idx++;
				if (idx.equals(TOTAL_PREVIEW_ALERTS))
					break;
			}
			emailRequest.setAttachmentFileName(fileName);
		}

		List<String> cc = new ArrayList<String>();
		cc.add(email);
		emailRequest.setCc(cc);
		emailRequest.setBody(mailBody);
		emailRequest.setSubject(mailSubject);
		emailRequest.setFrom(getFromAddress());
		InternalResponse emailResponse = getEmailServiceBCL().sendEmail(emailRequest);

		if (emailResponse.isInError())
		{
			logError("Error sending e-mail.");
		}

	}

	/**
	 * Format mail subject single.
	 * 
	 * @param subType the sub type
	 * @param light the light
	 * @return the string
	 */
	private String formatMailSubjectSingle(LightAlertEmail alert, Light light, String locale)
	{
		return MessageFormat.format(loadEmailTemplate(TEMPLATE_SUBJECT_PROPERTY_SINGLE, locale),
				alert.getAlertSubType(), light
						.getRadio()
						.getLocation().getAddress(), light.getRadio().getLocation().getCity(), light.getRadio()
						.getLocation()
						.getState(), light.getPoleId());
	}

	/**
	 * Format mail body single.
	 * 
	 * @param subType the sub type
	 * @param startPhaseTime the start phase time
	 * @param light the light
	 * @return the string
	 */
	private String formatMailBodySingle(LightAlertEmail alert, Date startPhaseTime, Light light, String locale)
	{
		String startTime = SensusConvertUtil.toDateString(startPhaseTime, HOUR_FORMAT);

		return MessageFormat.format(loadEmailTemplate(TEMPLATE_BODY_PROPERTY_SINGLE, locale)
				, alert.getAlertSubType()
				, startTime
				, light.getRadio().getLocation().getAddress()
				, light.getRadio().getLocation().getCity()
				, light.getRadio().getLocation().getState()
				, light.getPoleId()
				, light.getRadio().getFlexNetId()
				, light.getRadio().getLocation().getAddress()
				, light.getRadio().getLocation().getCity()
				, alert.getAlertType()
				, alert.getAlertSubType()
				, LCDateUtil.convertDateToString(startPhaseTime, DATE_FORMAT)
				, light.getLastOperationalData().getAcVoltage()
				, light.getLastOperationalData().getAcCurrent()
				, light.getLifeCycleState().toString());
	}

	/**
	 * Format mail subject specific.
	 * 
	 * @param differenceFromLastExecution the difference from last execution
	 * @param startPhaseTime the start phase time
	 * @param totalAlerts the total alerts
	 * @return the string
	 */
	private String formatMailSubjectSpecific(List<LightAlertEmail> alerts, Long diffFromLastExecution,
			Date startPhaseTime, String locale)
	{
		String startTime = SensusConvertUtil.toDateString(startPhaseTime, HOUR_FORMAT);
		String endTime = SensusConvertUtil.toDateString(new Date(), HOUR_FORMAT);
		return MessageFormat.format(loadEmailTemplate(TEMPLATE_SUBJECT_PROPERTY_SPECIFIC, locale),
				alerts.size(),
				alerts.get(0).getAlertSubType(),
				startTime,
				endTime,
				diffFromLastExecution);
	}

	/**
	 * Format mail body specific.
	 * 
	 * @param differenceFromLastExecution the difference from last execution
	 * @param startPhaseTime the start phase time
	 * @param totalAlerts the total alerts
	 * @return the string
	 */
	private String formatMailBodySpecific(List<LightAlertEmail> alerts, Long diffFromLastExecution,
			Date startPhaseTime, String locale)
	{
		String startTime = SensusConvertUtil.toDateString(startPhaseTime, HOUR_FORMAT);
		String endTime = SensusConvertUtil.toDateString(new Date(), HOUR_FORMAT);
		return MessageFormat.format(loadEmailTemplate(TEMPLATE_BODY_PROPERTY_SPECIFIC, locale),
				alerts.get(0).getAlertSubType(), startTime, alerts.size(),
				alerts.get(0).getAlertSubType(), startTime, endTime, diffFromLastExecution,
				alerts.size());
	}

	/**
	 * Fetch alerts for email.
	 * 
	 * @param fromDate the from date
	 * @param tenantId the tenant id
	 * @return the list
	 */
	private List<NotificationHistory> fetchAlertsForEmail(Date fromDate, Integer tenantId)
	{
		// Fetch alerts between the execution period. (from last phase execution starts until now)
		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();
		AlertCommunicationCriteria alertCommunicationCriteria = new AlertCommunicationCriteria();
		alertCommunicationCriteria.setTenantId(tenantId);
		alertCommunicationCriteria.setStartDate(fromDate);
		notificationHistoryRequest.setAlertCommunicationCriteria(alertCommunicationCriteria);

		NotificationHistoryCriteria historyCriteria = new NotificationHistoryCriteria();
		notificationHistoryRequest.setNotificationHistoryCriteria(historyCriteria);

		InternalResultsResponse<NotificationHistory> historyResponse =
				getNotificationHistoryBCL().fetchNotificationHistoryByRequest(notificationHistoryRequest);

		// No histories or alerts detected, should ignore this notification.
		if (historyResponse.isInError())
		{
			return null;
		}

		if (!ValidationUtil.isNullOrEmpty(historyResponse.getResultsList()))
		{
			for (NotificationHistory history : historyResponse.getResultsList())
			{
				NotificationHistoryCriteria alertCriteria = new NotificationHistoryCriteria();
				alertCriteria.setNotificationHistoryId(history.getId());
				NotificationHistoryRequest historyRequest = new NotificationHistoryRequest();
				historyRequest.setNotificationHistoryCriteria(alertCriteria);
				InternalResultsResponse<AlertClassification> response =
						getNotificationHistoryBCL().fetchNotificationHistoryAlertById(historyRequest);

				if (!ValidationUtil.isNull(response) && !isNullOrEmpty(response.getResultsList()))
				{
					history.setAlertClassifications(new ArrayList<AlertClassification>());
					for (AlertClassification alert : response.getResultsList())
					{
						// Consider only the latest alerts.
						if (alert.getUpdateDate().compareTo(fromDate) > 0)
						{
							if (!history.getAlertClassifications().contains(alert))
							{
								history.getAlertClassifications().add(alert);
							}
						}
					}
				}
			}
		}
		List<NotificationHistory> notificationList = new ArrayList<NotificationHistory>();
		notificationList.addAll(historyResponse.getResultsList());
		return notificationList;
	}

	/**
	 * Load email template propertie.
	 * 
	 * @param localeString the locale string
	 */
	private String loadEmailTemplate(String key, String localeString)
	{
		if (isNullOrEmpty(localeString))
		{
			localeString = DEFAULT_LOCALE;
		}

		Locale locale = new Locale(localeString.substring(0, 2), localeString.substring(3, 4));
		return messageSource.getMessage(key, null, locale);
	}

	/**
	 * Log error.
	 * 
	 * @param message the message
	 */
	private void logError(String message)
	{
		Message msg = new Message(DEFAULT_EXCEPTION_MSG + message,
				Message.MessageSeverity.Fatal,
				Message.MessageLevel.Object,
				new Object[] {BCL_EXCEPTION_MSG});
		LOG.error(msg.toString());
	}

	/**
	 * Calculate date tz.
	 * 
	 * @param tzstr the tzstr
	 * @param date the date
	 * @return the date
	 */
	private Date calculateDateTz(String tzstr, Date date)
	{
		TimeZone tz = TimeZone.getTimeZone(tzstr);
		Integer tzOffset = tz.getOffset(date.getTime());
		tzOffset = (tzOffset / 60000) / 60;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, tzOffset);
		return calendar.getTime();
	}

	/**
	 * Checks for user group.
	 * 
	 * @param user the user
	 * @param light the light
	 * @return the boolean
	 */
	private Boolean hasUserGroup(User user, Light light, Integer tenantId)
	{

		// has user the same grouping than light?
		if (!ValidationUtil.isNullOrEmpty(user.getGroups()))
		{
			UserContext context = new UserContext();
			context.setTenant(new Tenant(tenantId));

			LightRequest lightRequest = new LightRequest();
			LightCriteria lightCriteria = new LightCriteria();
			lightCriteria.getLightIdList().add(light.getId());
			lightRequest.setLightCriteria(lightCriteria);
			lightRequest.setUserContext(context);

			InternalResultsResponse<Group> groupingResponse = groupBCL.fetchGroupsByLight(lightRequest);
			if (!groupingResponse.isInError()
					&& !ValidationUtil.isNullOrEmpty(groupingResponse.getResultsList()))
			{
				for (Group group : groupingResponse.getResultsList())
				{
					for (Group usrGroup : user.getGroups())
					{
						if (usrGroup.getId().equals(group.getId()))
						{
							return Boolean.TRUE;
						}
					}
				}
			}
		}
		return Boolean.FALSE;
	}

	private String padRight(String s, int n)
	{
		return String.format("%1$-" + n + "s", s);
	}

}
