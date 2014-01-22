package com.sensus.lc.base;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.base.TestBaseUtil.CREATE_USER;
import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;
import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createAlertClassification;
import static com.sensus.lc.base.TestBaseUtil.createAlertClassificationMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createDaysOfWeek;
import static com.sensus.lc.base.TestBaseUtil.createEcoModeBaselineToLight;
import static com.sensus.lc.base.TestBaseUtil.createEcoModeRequest;
import static com.sensus.lc.base.TestBaseUtil.createEvent;
import static com.sensus.lc.base.TestBaseUtil.createEventSchedule;
import static com.sensus.lc.base.TestBaseUtil.createGroupRequest;
import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createLightConsumption;
import static com.sensus.lc.base.TestBaseUtil.createLightMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createNotificationHistory;
import static com.sensus.lc.base.TestBaseUtil.createNotificationHistoryMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createOffSetSchedule;
import static com.sensus.lc.base.TestBaseUtil.createProcess;
import static com.sensus.lc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.lc.base.TestBaseUtil.createScheduleRequest;
import static com.sensus.lc.base.TestBaseUtil.createSettingRequest;
import static com.sensus.lc.base.TestBaseUtil.createSettings;
import static com.sensus.lc.base.TestBaseUtil.createTag;
import static com.sensus.lc.base.TestBaseUtil.createTagRequest;
import static com.sensus.lc.base.TestBaseUtil.createUser;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static com.sensus.lc.base.TestBaseUtil.createUserRequest;
import static com.sensus.lc.base.TestBaseUtil.getEcoModeRandom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sensus.common.model.Authority;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.communication.dac.IAlertCommunicationDAC;
import com.sensus.lc.ecomode.dac.IEcoModeDAC;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.group.dac.IGroupDAC;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.dac.ILightColumnFilterDAC;
import com.sensus.lc.light.dac.ILightCustomSearchDAC;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.OperationalDataTypeEnum;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.OperationalDataMaintenanceRequest;
import com.sensus.lc.process.dac.IProcessDAC;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.schedule.dac.IScheduleDAC;
import com.sensus.lc.schedule.model.Event;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.settings.dac.ISettingsDAC;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.tag.dac.ITagDAC;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.user.dac.IUserDAC;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.UserRequest;

/**
 * The Class AbastractBaseTest.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@ContextConfiguration(locations = {
		"classpath:unittest-datasource-txn-context.xml",
		"classpath:unittest-abstract-base-test-dac-context.xml"})
public abstract class AbstractTestBaseDAC extends AbstractTransactionalJUnit4SpringContextTests
{
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(AbstractTestBaseDAC.class);

	/** The Constant ARBITRARY_INTENSITY_10. */
	private static final Integer ARBITRARY_INTENSITY_10 = 10;

	/** The Constant ARBITRARY_INTENSITY_20. */
	private static final Integer ARBITRARY_INTENSITY_20 = 20;

	/** The schedule dac. */
	private IScheduleDAC scheduleDAC;

	/** The settings dac. */
	private ISettingsDAC settingsDAC;

	/** The group dac. */
	private IGroupDAC groupDAC;

	/** The tag dac. */
	private ITagDAC tagDAC;

	/** The user dac. */
	private IUserDAC userDAC;

	/** The process dac. */
	private IProcessDAC processDAC;

	/** The eco mode dac. */
	private IEcoModeDAC ecoModeDAC;

	/** The light custom search dac. */
	private ILightCustomSearchDAC lightCustomSearchDAC;

	/** The notification history dac. */
	private INotificationHistoryDAC notificationHistoryDAC;

	/** The light column filter dac. */
	private ILightColumnFilterDAC lightColumnFilterDAC;

	/** Light DAC. */
	private ILightDAC lightDAC;

	/** The alert communication dac. */
	private IAlertCommunicationDAC alertCommunicationDAC;

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
	@Resource
	public void setAlertCommunicationDAC(IAlertCommunicationDAC alertCommunicationDAC)
	{
		this.alertCommunicationDAC = alertCommunicationDAC;
	}

	/**
	 * @return the lightDAC
	 */
	public ILightDAC getLightDAC()
	{
		return lightDAC;
	}

	/**
	 * @param lightDAC the lightDAC to set
	 */
	@Resource
	public void setLightDAC(ILightDAC lightDAC)
	{
		this.lightDAC = lightDAC;
	}

	/**
	 * @return the lightColumnFilterDAC
	 */
	public ILightColumnFilterDAC getLightColumnFilterDAC()
	{
		return lightColumnFilterDAC;
	}

	/**
	 * @param lightColumnFilterDAC the lightColumnFilterDAC to set
	 */
	@Resource
	public void setLightColumnFilterDAC(ILightColumnFilterDAC lightColumnFilterDAC)
	{
		this.lightColumnFilterDAC = lightColumnFilterDAC;
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
	@Resource
	public void setNotificationHistoryDAC(INotificationHistoryDAC notificationHistoryDAC)
	{
		this.notificationHistoryDAC = notificationHistoryDAC;
	}

	/**
	 * Gets the user dac.
	 * 
	 * @return the user dac
	 */
	protected IUserDAC getUserDAC()
	{
		return userDAC;
	}

	/**
	 * Sets the user dac.
	 * 
	 * @param userDAC the new user dac
	 */
	@Resource
	protected void setUserDAC(IUserDAC userDAC)
	{
		this.userDAC = userDAC;
	}

	/**
	 * Gets the settings dac.
	 * 
	 * @return the settings dac
	 */
	protected ISettingsDAC getSettingsDAC()
	{
		return settingsDAC;
	}

	/**
	 * Sets the settings dac.
	 * 
	 * @param settingsDAC the new settings dac
	 */
	@Resource
	protected void setSettingsDAC(ISettingsDAC settingsDAC)
	{
		this.settingsDAC = settingsDAC;
	}

	/**
	 * Gets the group dac.
	 * 
	 * @return the group dac
	 */
	protected IGroupDAC getGroupDAC()
	{
		return groupDAC;
	}

	/**
	 * Sets the group dac.
	 * 
	 * @param groupDAC the new group dac
	 */
	@Resource
	protected void setGroupDAC(IGroupDAC groupDAC)
	{
		this.groupDAC = groupDAC;
	}

	/**
	 * Gets the tag dac.
	 * 
	 * @return the tag dac
	 */
	protected ITagDAC getTagDAC()
	{
		return tagDAC;
	}

	/**
	 * Sets the tag dac.
	 * 
	 * @param tagDAC the new tag dac
	 */
	@Resource
	protected void setTagDAC(ITagDAC tagDAC)
	{
		this.tagDAC = tagDAC;
	}

	/**
	 * Gets the schedule dac.
	 * 
	 * @return the schedule dac
	 */
	protected IScheduleDAC getScheduleDAC()
	{
		return scheduleDAC;
	}

	/**
	 * Sets the schedule dac.
	 * 
	 * @param scheduleDAC the new schedule dac
	 */
	@Resource
	protected void setScheduleDAC(IScheduleDAC scheduleDAC)
	{
		this.scheduleDAC = scheduleDAC;
	}

	/**
	 * Sets the process dac.
	 * 
	 * @param processDAC the new process dac
	 */
	@Resource
	public void setProcessDAC(IProcessDAC processDAC)
	{
		this.processDAC = processDAC;
	}

	/**
	 * Gets the process dac.
	 * 
	 * @return the process dac
	 */
	public IProcessDAC getProcessDAC()
	{
		return processDAC;
	}

	/**
	 * Gets the eco mode dac.
	 * 
	 * @return the eco mode dac
	 */
	public IEcoModeDAC getEcoModeDAC()
	{
		return ecoModeDAC;
	}

	/**
	 * Sets the eco mode dac.
	 * 
	 * @param ecoModeDAC the new eco mode dac
	 */
	@Resource
	public void setEcoModeDAC(IEcoModeDAC ecoModeDAC)
	{
		this.ecoModeDAC = ecoModeDAC;
	}

	/**
	 * Gets the light custom search dac.
	 * 
	 * @return the light custom search dac
	 */
	public ILightCustomSearchDAC getLightCustomSearchDAC()
	{
		return lightCustomSearchDAC;
	}

	/**
	 * Sets the light custom search dac.
	 * 
	 * @param lightCustomSearchDAC the new light custom search dac
	 */
	@Resource
	public void setLightCustomSearchDAC(ILightCustomSearchDAC lightCustomSearchDAC)
	{
		this.lightCustomSearchDAC = lightCustomSearchDAC;
	}

	/**
	 * Sets the cache statement scope.
	 * 
	 * @param targetDAC the new cache statement scope
	 */
	protected void setCacheStatementScope(Object targetDAC)
	{
		if (!SqlSessionDaoSupport.class.isAssignableFrom(targetDAC.getClass()))
		{
			LOG.error("Do not possible set cache statement scope because occurred do not assignable from SqlSessionDaoSupport.class");
		}

		try
		{
			SqlSession sqlSession = ((SqlSessionDaoSupport)targetDAC).getSqlSession();
			sqlSession.getConfiguration().setLocalCacheScope(LocalCacheScope.STATEMENT);
		}
		catch (Exception e)
		{
			LOG.error("Do not possible set cache statement scope because occurred exception", e);
		}
	}

	/**
	 * Assert light.
	 * 
	 * @param light the light
	 */
	protected void assertLight(Light light)
	{
		assertNotNull("Light object should not to be null", light);
		assertNotNull("Light identifier should not to be null", light.getId());
	}

	/**
	 * Insert light.
	 * 
	 * @return the light
	 */
	protected Light insertLight()
	{
		return insertLight(createUserContext());
	}

	/**
	 * Insert light.
	 * 
	 * @param userContext the user context
	 * @return the light
	 */
	protected Light insertLight(UserContext userContext)
	{
		Light light = createLight(userContext);
		insertLight(light);
		return light;
	}

	/**
	 * Insert light.
	 * 
	 * @param light the light
	 * @return the light
	 */
	protected void insertLight(Light light)
	{
		UserContext userContext = createUserContext();

		light.setId(null);
		light.getLastOperationalData().setModelAction(PersistanceActionEnum.INSERT);
		light.getLightSchedule().setModelAction(PersistanceActionEnum.INSERT);
		light.getConfiguration().setModelAction(PersistanceActionEnum.INSERT);
		light.setRadio(TestBaseUtil.createRadio());

		LightMaintenanceRequest request = createLightMaintenanceRequest(userContext, light);
		InternalResponse response = getLightDAC().insertLight(request);
		assertResponse(response);
		assertLight(light);

		insertNotificationHistory(userContext, light);
	}

	/**
	 * Insert notification history.
	 * 
	 * @param userContext the user context
	 * @param light the light
	 */
	protected NotificationHistory insertNotificationHistory(UserContext userContext,
			Light light)
	{
		NotificationHistory notification = createNotificationHistory(userContext, light);
		NotificationHistoryMaintenanceRequest request =
				createNotificationHistoryMaintenanceRequest(userContext, notification);

		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryDAC().insertNotificationHistory(request);

		assertResultResponse(response);
		assertNotNull("Notification history should be not null", response.getFirstResult());
		assertNotNull("Notification history identifier should be not null", response.getFirstResult().getId());
		return notification;
	}

	/**
	 * Insert alert classification.
	 * 
	 * @param userContext the user context
	 * @param notificationHistoryId the notification history id
	 * @return the alert classification
	 */
	protected AlertClassification insertAlertClassification(UserContext userContext, Integer notificationHistoryId,
			AlertSubTypeEnum alertSubType)
	{
		// InsertAlertClassification
		AlertClassification alertClassification =
				createAlertClassification(userContext, notificationHistoryId, alertSubType);
		AlertClassificationMaintenanceRequest request =
				createAlertClassificationMaintenanceRequest(userContext, alertClassification);

		InternalResultsResponse<AlertClassification> response =
				getNotificationHistoryDAC().insertAlertClassification(request);
		assertResultResponse(response);

		return alertClassification;

	}

	/**
	 * Insert consumption to light.
	 * 
	 * @param light the light
	 * @param amount the amount
	 * @return the internal response
	 */
	protected InternalResponse insertConsumptionToLight(Light light, int amount)
	{
		if (ValidationUtil.isNullOrZero(amount))
		{
			return new InternalResponse();
		}

		UserContext userContext = TestBaseUtil.createUserContext();

		NotificationHistory notification = insertNotificationHistory(userContext, light);
		insertAlertClassification(userContext, notification.getId(), AlertSubTypeEnum.LAMP_FAILURE);

		OperationalDataMaintenanceRequest request = new OperationalDataMaintenanceRequest();
		request.setUserContext(userContext);
		InternalResponse response = new InternalResponse();
		float consumption = RANDOM.nextInt(NUMBER_RANGE);

		for (int i = 0; i < amount; i++)
		{
			OperationalData oData = new OperationalData();
			oData.setOperationalDataType(OperationalDataTypeEnum.CUMULATIVECONSUMPTION);
			oData.setValue(consumption);
			oData.setNotificationHistoryId(notification.getId());
			oData.setCreateDate(new Date());
			consumption += ARBITRARY_INTENSITY_10;
			request.setOperationalData(oData);
			response = getLightDAC().insertOperationalData(request);

			if (response.isInError())
			{
				return null;
			}
		}
		return response;
	}

	/**
	 * Creates the lights.
	 * 
	 * @param amount the amount
	 * @return the list
	 */
	protected List<Light> insertLights(int amount)
	{
		if (amount == 0)
		{
			return null;
		}

		List<Light> lights = new ArrayList<Light>();

		for (int i = 0; i < amount; i++)
		{
			Light light = insertLight();
			lights.add(light);
		}

		return lights;
	}

	/**
	 * Assert tag.
	 * 
	 * @param tag the tag
	 */
	protected void assertTag(Tag tag)
	{
		assertNotNull("Tag object should not to be null", tag);
		assertNotNull("Tag identifier should not to be null", tag.getId());
	}

	/**
	 * Assert user.
	 * 
	 * @param user the user
	 */
	protected void assertUser(User user)
	{
		assertNotNull("User object should not to be null", user);
		assertNotNull("User identifier should not to be null", user.getId());
	}

	/**
	 * Insert tag.
	 * 
	 * @return the tag
	 */
	protected Tag insertTag()
	{
		return insertTag(0);
	}

	/**
	 * Creates the tags.
	 * 
	 * @param amount the amount
	 * @return the list
	 */
	protected List<Tag> insertTags(int amount)
	{
		if (amount == 0)
		{
			return null;
		}

		List<Tag> tags = new ArrayList<Tag>();
		for (int i = 0; i < amount; i++)
		{
			tags.add(insertTag());
		}

		return tags;
	}

	/**
	 * Insert user.
	 * 
	 * @return the user
	 */
	protected User insertUser()
	{
		User user = createUser();

		UserRequest userRequest = createUserRequest(user);

		InternalResultsResponse<User> response = getUserDAC().insertUser(userRequest);
		assertResultResponse(response);

		user = response.getFirstResult();
		assertUser(user);
		userRequest.getUserContext().setId(user.getId());

		return user;
	}

	/**
	 * Insert tag.
	 * 
	 * @param lightsAmount the lights amount
	 * @return the tag
	 */
	protected Tag insertTag(int lightsAmount)
	{
		Tag tag = createTag();
		tag.setLights(insertLights(lightsAmount));
		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(tag);

		InternalResultsResponse<Tag> response = getTagDAC().insertTag(tagRequest);
		assertResultResponse(response);

		tag = response.getFirstResult();
		assertTag(tag);

		return tag;
	}

	/**
	 * Adds the light to tag.
	 * 
	 * @param tag the tag
	 * @param light the light
	 */
	protected void addLightToTag(Tag tag, Light... light)
	{
		for (int i = 0; i < light.length; i++)
		{
			SearchLight searchLight = new SearchLight();
			searchLight.setLightId(light[i].getId());

			TagRequest tagRequest = createTagRequest();
			tagRequest.setTag(tag);
			tagRequest.addToTags(tag);
			tagRequest.getSelectionPaginationIds().add(light[i].getId());

			InternalResponse response = getTagDAC().insertLightToTag(tagRequest);
			assertResponse(response);
		}
	}

	/**
	 * Assert group.
	 * 
	 * @param group the group
	 */
	protected void assertGroup(Group group)
	{
		assertNotNull("Group object should not to be null", group);
		assertNotNull("Group identifier object should not to be null", group.getId());
		assertNotNull("Group name object should not to be null", group.getName());
	}

	/**
	 * Insert new group.
	 * 
	 * @return the group
	 */
	protected Group insertGroup()
	{
		Group group = createGroup();
		InternalResultsResponse<Group> response = saveGroup(group);
		assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Save group.
	 * 
	 * @param group the group
	 * @return the internal results response
	 */
	protected InternalResultsResponse<Group> saveGroup(Group group)
	{
		GroupRequest request = createGroupRequest();
		request.setGroup(group);
		InternalResultsResponse<Group> response = getGroupDAC().insertGroup(request);
		assertGroup(response.getFirstResult());
		return response;
	}

	/**
	 * Creates the group, but don't save.
	 * 
	 * @return the group
	 */
	protected Group createGroup()
	{
		final String name = "TestGroup " + RANDOM.nextInt(NUMBER_RANGE);
		final String description = "DescriptionGroup " + RANDOM.nextInt(NUMBER_RANGE);

		Group group = new Group();
		group.setName(name);
		group.setDescription(description);
		group.setCreateUser(CREATE_USER);
		return group;
	}

	/**
	 * Sets the group list to user.
	 * 
	 * @param inquiryPaginationRequest the new group list to user
	 * @param amount the amount
	 */
	protected void setGroupsToUser(InquiryPaginationRequest inquiryPaginationRequest, int amount)
	{
		List<Authority> authorities = new ArrayList<Authority>();
		for (int i = 0; i < amount; i++)
		{
			Group group = insertGroup();
			authorities.add(new Authority(group.getId(), group.getName()));
		}
		inquiryPaginationRequest.getUserContext().setAuthorities(authorities);
	}

	/**
	 * Sets the groups to user.
	 * 
	 * @param request the request
	 * @param amount the amount
	 * @param light the light
	 */
	protected void setGroupsToUser(InquiryPaginationRequest request, Integer amount, Light light)
	{
		List<Authority> authorities = new ArrayList<Authority>();
		for (int i = 0; i < amount; i++)
		{
			Group group = insertGroup();
			addLightToGroup(light, group);
			authorities.add(new Authority(group.getId(), group.getName()));
		}
		request.getUserContext().setAuthorities(authorities);
	}

	/**
	 * Sets the groups to user.
	 * 
	 * @param userContext the user context
	 * @param groups the groups
	 */
	protected void setGroupsToUser(UserContext userContext, Group... groups)
	{
		if (ValidationUtil.isNullOrZero(groups.length))
		{
			return;
		}

		List<Authority> authorities = new ArrayList<Authority>();
		for (Group group : groups)
		{
			authorities.add(new Authority(group.getId(), "Group"));
		}

		userContext.setAuthorities(authorities);
	}

	/**
	 * Sets the group to user.
	 * 
	 * @param request the new group to user
	 */
	protected void setGroupToUser(GroupRequest request)
	{
		Group group = insertGroup();
		request.setGroup(group);
		request.addAllowedGroupId(group.getId());
	}

	/**
	 * Reset groups to user.
	 * 
	 * @param userContext the user context
	 */
	protected void resetGroupsToUser(UserContext userContext)
	{
		userContext.setAuthorities(null);
	}

	/**
	 * Adds the light to group.
	 * 
	 * @param light the light
	 * @param group the group
	 */
	protected void addLightToGroup(Light light, Group group)
	{
		// Add light for group
		if (ValidationUtil.isNull(group.getId()))
		{
			saveGroup(group);
		}

		// Add light to group
		GroupRequest groupRequest = createGroupRequest();
		groupRequest.setGroup(group);
		groupRequest.getSelectionPaginationIds().add(light.getId());

		InternalResponse response = getGroupDAC().insertLightToGroup(groupRequest);
		assertResponse(response);
	}

	/**
	 * Adds the lights to group.
	 * 
	 * @param lights the lights
	 * @param group the group
	 */
	protected void addLightsToGroup(List<Light> lights, Group group)
	{
		if (ValidationUtil.isNullOrEmpty(lights))
		{
			return;
		}

		for (Light light : lights)
		{
			addLightToGroup(light, group);
		}
	}

	/**
	 * Insert schedule event.
	 * 
	 * @param request the request
	 * @return the event schedule
	 */
	protected EventSchedule insertScheduleEvent(ScheduleRequest request)
	{
		// create two events
		Event eventOne = createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_10);
		Event eventTwo = createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_20);

		// create a new Schedule
		EventSchedule eventSchedule = createEventSchedule();

		List<Event> events = new ArrayList<Event>();
		events.add(eventOne);
		events.add(eventTwo);
		eventSchedule.setEvents(events);

		// create a new light and add at schedule event
		List<Light> lights = new ArrayList<Light>();
		lights.add(insertLight());
		eventSchedule.setLights(LCPropertiesExtractorUtil.extractLightId(lights));

		request.setSchedule(eventSchedule);

		InternalResultsResponse<Schedule> response = getScheduleDAC().insertSchedule(request);
		assertResultResponse(response);

		return (EventSchedule)response.getFirstResult();
	}

	/**
	 * Insert schedule event.
	 * 
	 * @param request the request
	 * @return the event schedule
	 */
	protected EventSchedule insertScheduleEvent(ScheduleRequest request, List<Light> listLight)
	{
		// create two events
		Event eventOne = createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_10);
		Event eventTwo = createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_20);

		// create a new Schedule
		EventSchedule eventSchedule = createEventSchedule();

		List<Event> events = new ArrayList<Event>();
		events.add(eventOne);
		events.add(eventTwo);
		eventSchedule.setEvents(events);

		// create a new light and add at schedule event
		eventSchedule.setLights(LCPropertiesExtractorUtil.extractLightId(listLight));

		request.setSchedule(eventSchedule);

		InternalResultsResponse<Schedule> response = getScheduleDAC().insertSchedule(request);
		assertResultResponse(response);

		return (EventSchedule)response.getFirstResult();
	}

	/**
	 * Insert schedule offset.
	 * 
	 * @param request the request
	 * @return the offset schedule
	 */
	protected OffsetSchedule insertScheduleOffset(ScheduleRequest request)
	{

		OffsetSchedule offsetSchedule = createOffSetSchedule();
		request.setSchedule(offsetSchedule);

		InternalResultsResponse<Schedule> response = getScheduleDAC().insertSchedule(request);
		assertResultResponse(response);

		return (OffsetSchedule)response.getFirstResult();
	}

	/**
	 * Apply smart point to schedule.
	 * 
	 * @param schedule the schedule
	 * @param lights the lights
	 */
	protected void applyLightToSchedule(Schedule schedule, List<Light> lights)
	{
		ScheduleRequest request = createScheduleRequest();
		request.setSelectionPaginationIds(LCPropertiesExtractorUtil.extractLightId(lights));
		request.setSchedule(schedule);

		InternalResponse response = getScheduleDAC().applyLightToSchedule(request);
		assertResponse(response);
	}

	/**
	 * Insert settings.
	 * 
	 * @param userId the user id
	 */
	protected void insertSettings(Integer userId)
	{
		SettingsRequest request = createSettingRequest();
		request.setSettings(createSettings());
		request.setUserId(userId);

		InternalResultsResponse<Setting> response = getSettingsDAC().upsertSettings(request);
		assertResultResponse(response);
	}

	/**
	 * Insert process.
	 * 
	 * @return the process
	 */
	protected Process insertProcess()
	{
		return insertProcess(null, null);
	}

	/**
	 * Insert process.
	 * 
	 * @param light the light
	 * @return the process
	 */
	protected Process insertProcess(Light light)
	{
		return insertProcess(light, null);
	}

	/**
	 * Insert process.
	 * 
	 * @param parentProcess the parent process
	 * @return the process
	 */
	protected Process insertProcess(Process parentProcess)
	{
		return insertProcess(null, parentProcess);
	}

	/**
	 * Insert process.
	 * 
	 * @param light the light
	 * @param parentProcess the parent process
	 * @return the process
	 */
	protected Process insertProcess(Light light, Process parentProcess)
	{
		return insertProcess(light, parentProcess, false);
	}

	protected Process insertProcess(Light light, Process parentProcess, boolean isFailure)
	{
		Process process = createProcess(light, parentProcess, isFailure);
		ProcessRequest request = createProcessRequest();
		request.setProcess(process);

		InternalResultsResponse<Process> response = getProcessDAC().insertProcess(request);
		assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Creates the process items.
	 * 
	 * @return the list
	 */
	protected List<ProcessItem> createProcessItems()
	{
		List<ProcessItem> processResultList = new ArrayList<ProcessItem>();
		ProcessItem pendingProcess = new ProcessItem();
		pendingProcess.setProcessItemStatusEnum(ProcessItemStatusEnum.PENDING);
		pendingProcess.setLight(insertLight());
		processResultList.add(pendingProcess);

		ProcessItem rniSyncFailureProcess = new ProcessItem();
		rniSyncFailureProcess.setProcessItemStatusEnum(ProcessItemStatusEnum.RNISYNCFAILURE);
		rniSyncFailureProcess.setLight(insertLight());
		processResultList.add(rniSyncFailureProcess);
		return processResultList;
	}

	/**
	 * Upsert eco mode.
	 * 
	 * @param light the light
	 * @return the eco mode baseline
	 */
	protected void upsertEcoMode(Light light)
	{
		light.setEcoMode(getEcoModeRandom());
		createEcoModeBaselineToLight(light);
		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);

		InternalResultsResponse<Light> response = getEcoModeDAC().upsertEcoMode(ecoModeRequest);
		assertResultResponse(response);
	}

	/**
	 * Insert light consumption.
	 * 
	 * @param light the light
	 * @param amount the amount
	 */
	protected void insertLightConsumption(Light light, int amount)
	{
		if (amount == 0)
		{
			return;
		}

		List<Consumption> lightConsumptions = light.getConsumptions();
		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);

		for (int i = 0; i < amount; i++)
		{
			Consumption lightConsumption = createLightConsumption(i);
			light.setLastConsumption(lightConsumption);

			InternalResultsResponse<Consumption> response =
					getEcoModeDAC().insertLightConsumption(ecoModeRequest);
			assertResultResponse(response);
			lightConsumptions.add(lightConsumption);
		}
	}

	/**
	 * Assert messages.
	 * 
	 * @param response the response
	 * @param messages the messages
	 */
	protected void assertMessages(InternalResponse response, String... messages)
	{
		if (isNull(response) || messages.length == 0)
		{
			return;
		}

		assertEquals("Messages amount should be equal. ", messages.length, response.getMessageInfoList().size());
		assertMessagesInfo(response.getMessageInfoList(), messages);
	}

	/**
	 * Assert messages.
	 * 
	 * @param response the response
	 * @param messages the messages
	 */
	protected void assertMessages(Response response, String... messages)
	{
		if (isNull(response) || messages.length == 0)
		{
			return;
		}

		assertEquals("Messages amount should be equal.", messages.length, response.getMessageInfoList().size());
		assertMessagesInfo(response.getMessageInfoList(), messages);
	}

	/**
	 * Assert messages info.
	 * 
	 * @param messagesInfo the messages info
	 * @param messages the messages
	 */
	private void assertMessagesInfo(List<MessageInfo> messagesInfo, String... messages)
	{
		if (isNullOrEmpty(messagesInfo))
		{
			return;
		}

		List<String> messageList = Arrays.asList(messages);

		for (MessageInfo messageInfo : messagesInfo)
		{
			if (!messageList.contains(messageInfo.getCode()))
			{
				fail("Response object should contains message code: " + messageInfo.getCode());
			}
		}
	}
}
