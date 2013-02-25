package com.sensus.mlc.base;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.TestBaseUtil.CREATE_USER;
import static com.sensus.mlc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.mlc.base.TestBaseUtil.RANDOM;
import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createDaysOfWeek;
import static com.sensus.mlc.base.TestBaseUtil.createEcoModeBaseline;
import static com.sensus.mlc.base.TestBaseUtil.createEvent;
import static com.sensus.mlc.base.TestBaseUtil.createGroupRequest;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryEcoModeRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightConsumption;
import static com.sensus.mlc.base.TestBaseUtil.createOffSetSchedule;
import static com.sensus.mlc.base.TestBaseUtil.createProcess;
import static com.sensus.mlc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.mlc.base.TestBaseUtil.createSchedule;
import static com.sensus.mlc.base.TestBaseUtil.createScheduleRequest;
import static com.sensus.mlc.base.TestBaseUtil.createSettingRequest;
import static com.sensus.mlc.base.TestBaseUtil.createSettings;
import static com.sensus.mlc.base.TestBaseUtil.createTag;
import static com.sensus.mlc.base.TestBaseUtil.createTagRequest;
import static com.sensus.mlc.base.TestBaseUtil.createUser;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;
import static com.sensus.mlc.base.TestBaseUtil.createUserRequest;
import static com.sensus.mlc.base.TestBaseUtil.getEcoModeRandom;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
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
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.ecomode.dac.IEcoModeDAC;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.group.dac.IGroupDAC;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.process.dac.IProcessDAC;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.dac.IScheduleDAC;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.settings.dac.ISettingsDAC;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.dac.ISmartPointDAC;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.tag.dac.ITagDAC;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.user.dac.IUserDAC;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;

/**
 * The Class AbastractBaseTest.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
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

	/** The smartpoint dac. */
	private ISmartPointDAC smartpointDAC;

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
	 * Gets the smartpoint dac.
	 *
	 * @return the smartpoint dac
	 */
	protected ISmartPointDAC getSmartpointDAC()
	{
		return smartpointDAC;
	}

	/**
	 * Sets the smartpoint dac.
	 *
	 * @param smartpointDAC the new smartpoint dac
	 */
	@Resource
	protected void setSmartpointDAC(ISmartPointDAC smartpointDAC)
	{
		this.smartpointDAC = smartpointDAC;
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
		Light light = insertWholeLight(createLight());
		assertLight(light);

		List<Light> lightList = new ArrayList<Light>();
		lightList.add(light);

		insertStatusMessage(light);

		return light;
	}

	/**
	 * Insert status message.
	 *
	 * @param light the light
	 * @return the status message
	 */
	protected StatusMessage insertStatusMessage(Light light)
	{
		return insertStatusMessage(light, StatusMessageCategoryEnum.SETUP, LightStatusEnum.ACTIVE);
	}

	/**
	 * Assert status message.
	 *
	 * @param statusMessage the status message
	 */
	protected void assertStatusMessage(StatusMessage statusMessage)
	{
		assertNotNull("Status message object should be not null", statusMessage);
		assertNotNull("Status message identifier should be not null", statusMessage.getId());
	}

	/**
	 * Insert status message.
	 *
	 * @param light the light
	 * @param statusMessageCategory the status message category
	 * @param lightStatus the light status
	 * @param statusExceptions the status exceptions
	 * @return the status message
	 */
	protected StatusMessage insertStatusMessage(Light light, StatusMessageCategoryEnum statusMessageCategory,
			LightStatusEnum lightStatus, StatusExceptionTypeEnum... statusExceptions)
	{
		StatusMessage statusMessage =
				createStatusMessage(statusMessageCategory, lightStatus, statusExceptions);

		UserContext userContext = createUserContext();
		Integer statusMessageId = getSmartpointDAC().insertStatusMessage(
				statusMessage,
				userContext.getUserId(),
				userContext.getTenant().getId(),
				light.getId(),
				false);

		assertNotNull(statusMessageId);

		statusMessage.setId(statusMessageId);

		assertStatusMessage(statusMessage);

		if (statusExceptions.length > 0)
		{
			for (StatusExceptionTypeEnum subtype : statusExceptions)
			{

				// fetch in status message sub-type.
				Integer responseSubtype =
						getSmartpointDAC().fetchStatusMessageStatusSubtype(
								statusMessageId,
								subtype.getValue(),
								userContext.getUserId());
				assertEquals(responseSubtype == 0, true);

				getSmartpointDAC().insertStatusMessageStatusSubtype(
						statusMessageId,
						subtype.getValue(),
						userContext.getUserId(),
						false);

				insertCurrentAlarmWarningStatusMessage(light, statusMessage, statusMessageCategory, lightStatus,
						subtype);
			}
		}
		else
		{
			insertCurrentAlarmWarningStatusMessage(light, statusMessage, statusMessageCategory, lightStatus, null);
		}

		statusMessage = getSmartpointDAC().fetchStatusMessageByLightID(light.getId(), null).getFirstResult();
		assertStatusMessage(statusMessage);

		return statusMessage;
	}

	/**
	 * Insert current alarm warning status message.
	 *
	 * @param light the light
	 * @param statusMessage the status message
	 * @param statusMessageCategory the status message category
	 * @param lightStatus the light status
	 * @param statusException the status exception
	 */
	protected void insertCurrentAlarmWarningStatusMessage(Light light, StatusMessage statusMessage,
			StatusMessageCategoryEnum statusMessageCategory, LightStatusEnum lightStatus,
			StatusExceptionTypeEnum statusException)
	{
		getSmartpointDAC().deleteCurrentAlarmWarningMessageByLightID(light.getId());

		getSmartpointDAC().insertCurrentAlarmStatusMessage(
				new CurrentAlarmWarningMessage(
						light.getId(),
						statusMessage.getId(),
						statusMessageCategory,
						lightStatus,
						statusException,
						getNewDateUTC(),
						createUserContext().getTenant().getId()));
	}

	/**
	 * Creates the status message.
	 *
	 * @param statusMessageCategory the status message category
	 * @param lightStatus the light status
	 * @param statusExceptions the status exceptions
	 * @return the status message
	 */
	protected StatusMessage createStatusMessage(StatusMessageCategoryEnum statusMessageCategory,
			LightStatusEnum lightStatus, StatusExceptionTypeEnum... statusExceptions)
	{
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setStatusMessageCategoryEnum(statusMessageCategory);
		statusMessage.setLightStatusEnum(lightStatus);
		for (int i = 0; i < statusExceptions.length; i++)
		{
			statusMessage.getStatusExceptions().add(new StatusException(statusExceptions[i]));
		}
		return statusMessage;
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
		return this.insertTag(0);
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
			tags.add(this.insertTag());
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
			searchLight.setLight(light[i]);

			TagRequest tagRequest = createTagRequest();
			tagRequest.addToTags(tag);
			tagRequest.getSelectionPaginationIds().add(light[i].getId());

			InternalResponse response = getTagDAC().insertSmartPointToTag(tagRequest);
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
		group.setLights(insertLights(2));
		group.setCreateUser(CREATE_USER);
		return group;
	}

	/**
	 * Sets the group list to user.
	 *
	 * @param request the new group list to user
	 * @param amount the amount
	 */
	protected void setGroupsToUser(LightingControlRequest request, int amount)
	{
		List<Group> groups = new ArrayList<Group>();
		for (int i = 0; i < amount; i++)
		{
			groups.add(insertGroup());
		}

		request.setAllowedGroups(groups);
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
		group.addLight(light);

		// Add light to group
		GroupRequest groupRequest = createGroupRequest();
		groupRequest.setGroup(group);
		groupRequest.getSelectionPaginationIds().add(light.getId());

		InternalResponse response = getGroupDAC().insertSmartPointToGroup(groupRequest);
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
		EventSchedule eventSchedule = createSchedule();

		List<Event> events = new ArrayList<Event>();
		events.add(eventOne);
		events.add(eventTwo);
		eventSchedule.setEvents(events);

		// create a new light and add at schedule event
		List<Light> lights = new ArrayList<Light>();
		lights.add(insertLight());
		eventSchedule.setLights(lights);

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
	protected void applySmartPointToSchedule(Schedule schedule, List<Light> lights)
	{
		schedule.setLights(lights);

		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(schedule);

		InternalResponse response = getScheduleDAC().applySmartPointToSchedule(request);
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
		request.getUserContext().setId(userId);
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
		return this.insertProcess(null, null);
	}

	/**
	 * Insert process.
	 *
	 * @param light the light
	 * @return the process
	 */
	protected Process insertProcess(Light light)
	{
		return this.insertProcess(light, null);
	}

	/**
	 * Insert process.
	 *
	 * @param parentProcess the parent process
	 * @return the process
	 */
	protected Process insertProcess(Process parentProcess)
	{
		return this.insertProcess(null, parentProcess);
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
		Process process = createProcess(light, parentProcess);
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
	protected EcoModeBaseline upsertEcoMode(Light light)
	{
		light.setEcoMode(getEcoModeRandom());
		EcoModeBaseline baseline = createEcoModeBaseline(light);
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);

		InternalResultsResponse<EcoModeBaseline> response = getEcoModeDAC().upsertEcoMode(ecoModeRequest);
		assertResultResponse(response);
		return response.getFirstResult();
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

		List<LightConsumption> lightConsumptions = light.getConsumptions();
		lightConsumptions = new ArrayList<LightConsumption>();

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();

		for (int i = 0; i < amount; i++)
		{
			LightConsumption lightConsumption = createLightConsumption(light, i);
			ecoModeRequest.setLightConsumptions(Arrays.asList(lightConsumption));

			InternalResultsResponse<LightConsumption> response =
					getEcoModeDAC().insertLightConsumption(ecoModeRequest);
			assertResultResponse(response);
			lightConsumptions.add(lightConsumption);
		}

		light.setConsumptions(lightConsumptions);

		// Set last consumption
		LightConsumption lastConsumption = lightConsumptions.get(amount - 1);
		light.setLastConsumption(lastConsumption);
	}

	/**
	 * Assert messages.
	 *
	 * @param response the response
	 * @param messages the messages
	 */
	protected void assertMessages(InternalResponse response, String... messages)
	{
		if (isNull(response) || (messages.length == 0))
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
		if (isNull(response) || (messages.length == 0))
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

	/**
	 * Insert light whole.
	 *
	 * @param light the light
	 * @return the light
	 */
	private Light insertWholeLight(Light light)
	{
		List<Light> lightList = new ArrayList<Light>();
		lightList.add(light);

		GuaranteeLightExistenceRequest guaranteeLightExistenceRequest = TestBaseUtil.createGuaranteeLightExistenceRequest();
		guaranteeLightExistenceRequest.setLights(lightList);

		// necessary insert smartPoint first.
		Integer smartPointId = getSmartpointDAC().insertSmartPoint(guaranteeLightExistenceRequest);

		if(ValidationUtil.isNullOrZero(smartPointId))
		{
			return light;
		}

		//insert Light
		guaranteeLightExistenceRequest.getFirstLight().setSmartPointId(smartPointId);
		Integer lightId = getSmartpointDAC().insertLight(guaranteeLightExistenceRequest);
		light.setId(lightId);

		if(ValidationUtil.isNullOrZero(lightId))
		{
			return light;
		}

		//insert lightConfiguration
		light.getLightConfiguration().setLightId(lightId);

		getSmartpointDAC().insertLightConfiguration(guaranteeLightExistenceRequest);

		//insert lightLocation
		light.getLightLocation().setLightId(lightId);

		getSmartpointDAC().insertLightLocation(guaranteeLightExistenceRequest);

		//insert lightSchedule
		light.getLightSchedule().setLightId(lightId);

		getSmartpointDAC().insertLightSchedule(guaranteeLightExistenceRequest);

		//insert lightLastOperationalData
		light.getLightLastOperationalData().setLightId(lightId);

		getSmartpointDAC().insertLightLastOperationalData(guaranteeLightExistenceRequest);

		//fetch light which just inserted
		guaranteeLightExistenceRequest.getFirstLight().setSmartPointId(smartPointId);
		light = getSmartpointDAC().fetchLightToInsert(guaranteeLightExistenceRequest);

		return light;
	}
}
