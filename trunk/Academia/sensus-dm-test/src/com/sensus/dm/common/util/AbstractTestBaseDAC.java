package com.sensus.dm.common.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.dm.common.util.TestBaseUtil.assertResultResponse;
import static com.sensus.dm.common.util.TestBaseUtil.createCustomSearch;
import static com.sensus.dm.common.util.TestBaseUtil.createCustomSearchRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.dac.ICustomSearchDAC;
import com.sensus.dm.common.device.dac.IDeviceDAC;
import com.sensus.dm.common.device.dac.INoteDAC;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.dac.IGroupDAC;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.process.dac.IProcessDAC;
import com.sensus.dm.common.process.dac.IProcessSummaryDAC;
import com.sensus.dm.common.process.dac.IProcessTypeDAC;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemHistory;
import com.sensus.dm.common.process.model.ProcessItemHistoryStatusEnum;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.dac.IPropertyDAC;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.schedule.dac.IScheduleDAC;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.tag.dac.ITagDAC;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.action.dac.IActionDAC;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.device.dac.IElectricMeterDAC;
import com.sensus.dm.water.device.dac.IWaterMeterDAC;

/**
 * The Class AbstractBaseTest.
 * 
 * @author QAT Global.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@ContextConfiguration(locations = {
		"classpath:unittest-datasource-txn-context.xml",
		"classpath:conf/sensus-dm-data-access-context.xml",
		"classpath:conf/sensus-dm-resourcebundles-context.xml"})
public abstract class AbstractTestBaseDAC extends AbstractTransactionalJUnit4SpringContextTests
{
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(AbstractTestBaseDAC.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant NEGATIVE_TEN. */
	protected static final int NEGATIVE_TEN = -10;

	/** The Constant NEGATIVE_TWO. */
	protected static final int NEGATIVE_TWO = -2;

	/** The Constant ZERO. */
	protected static final Integer ZERO = 0;

	/** The Constant ONE. */
	protected static final Integer ONE = 1;

	/** The Constant TWO. */
	protected static final Integer TWO = 2;

	/** The Constant THREE. */
	protected static final int THREE = 3;

	/** The Constant FOUR. */
	protected static final int FOUR = 4;

	/** The Constant FIVE. */
	protected static final int FIVE = 5;

	/** The Constant SIX. */
	protected static final int SIX = 6;

	/** The Constant SEVEN. */
	protected static final int SEVEN = 7;

	/** The Constant EIGHT. */
	protected static final int EIGHT = 8;

	/** The Constant NINE. */
	protected static final int NINE = 9;

	/** The Constant TEN. */
	protected static final int TEN = 10;

	/** The Constant ELEVEN. */
	protected static final int ELEVEN = 11;

	/** The Constant TWELVE. */
	protected static final int TWELVE = 12;

	/** The Constant SIXTEEN. */
	protected static final int SIXTEEN = 16;

	/** The Constant SEVENTEEN. */
	protected static final int SEVENTEEN = 17;

	/** The Constant TWENTY_THREE. */
	protected static final int TWENTY_THREE = 23;

	/** The Constant TWENTY_FIVE. */
	protected static final Integer TWENTY_FIVE = 25;

	/** The Constant FIFTY_NINE. */
	protected static final int FIFTY_NINE = 59;

	/** The Constant ONE_HUNDRED. */
	private static final int ONE_HUNDRED = 100;

	/** The Constant ONE_THOUSAND. */
	protected static final Integer ONE_THOUSAND = 1000;

	/** The Constant ONE_STR. */
	protected static final String ONE_STR = "1";

	/** The Constant DOT. */
	protected static final String DOT = ".";

	/** The Constant SPACE. */
	protected static final String SPACE = " ";

	/** The Constant CUSTOMER_ID. */
	protected static final String CUSTOMER_ID = "ACME";

	/** The Constant DEVICE_ID. */
	protected static final String DEVICE_ID = "1010M";

	/** The Constant DEVICE_ID_WATER. */
	protected static final String DEVICE_ID_WATER = "B100125145508";

	/** The Constant FLEXNET_ID. */
	protected static final String FLEXNET_ID = "1010";

	/** The Constant FLEXNET_ID_WATER. */
	protected static final String FLEXNET_ID_WATER = "16500165";

	/** The Constant HAN_DEVICE_DEVICE_ID. */
	protected static final String HAN_DEVICE_ID = "44919932";

	/** The Constant LCM_DEVICE_ID. */
	protected static final String LCM_DEVICE_ID = "LCM9353";

	/** The Constant LCM_FLEXNET_ID. */
	protected static final String LCM_FLEXNET_ID = "50402439";

	/** The Constant GAS_FLEXNET_ID. */
	protected static final BigInteger GAS_FLEXNET_ID = new BigInteger("1330");

	/** The Constant FLEXNET_ID_1001. */
	protected static final BigInteger FLEXNET_ID_1001 = new BigInteger("1001");

	/** The Constant GAS_DEVICE_ID. */
	protected static final String GAS_DEVICE_ID = "1330M";

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant NAME. */
	protected static final String NAME = "name";

	/** The Constant DESCRIPTION. */
	protected static final String DESCRIPTION = "description";

	/** The Constant ASSERT_MESSAGES_EQUAL. */
	private static final String ASSERT_MESSAGES_EQUAL = "Messages amount should be equal.";

	/** The Constant METER. */
	protected static final String METER = "METER";

	/** The Constant ADDRESS. */
	protected static final String ADDRESS = "address";

	/** The Constant CITY. */
	protected static final String CITY = "city";

	/** The Constant ZIP. */
	protected static final String ZIP = "zip";

	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The custom search dac. */
	private ICustomSearchDAC customSearchDAC;

	/** The property dac. */
	private IPropertyDAC propertyDAC;

	/** The process dac. */
	private IProcessDAC processDAC;

	/** The process summary dac. */
	private IProcessSummaryDAC processSummaryDAC;

	/** The process type dac. */
	private IProcessTypeDAC processTypeDAC;

	/** The action dac. */
	private IActionDAC actionDAC;

	/** The electric meter dac. */
	private IElectricMeterDAC electricMeterDAC;

	/** The device dac. */
	private IDeviceDAC deviceDAC;

	/** The group dac. */
	private IGroupDAC groupDAC;

	/** The tag dac. */
	private ITagDAC tagDAC;

	/** The schedule dac. */
	private IScheduleDAC scheduleDAC;

	/** The water meter dac. */
	private IWaterMeterDAC waterMeterDAC;

	/** The note dac. */
	private INoteDAC noteDAC;

	/**
	 * Gets the note dac.
	 * 
	 * @return the note dac
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Sets the note dac.
	 * 
	 * @param noteDAC the new note dac
	 */
	@Resource
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Gets the action dac.
	 * 
	 * @return the action dac
	 */
	public IActionDAC getActionDAC()
	{
		return actionDAC;
	}

	/**
	 * Sets the action dac.
	 * 
	 * @param iActionDACParam
	 *            the new action dac
	 */
	@Resource
	public void setActionDAC(IActionDAC iActionDACParam)
	{
		actionDAC = iActionDACParam;
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
	 * Gets the process summary dac.
	 * 
	 * @return the process summary dac
	 */
	public IProcessSummaryDAC getProcessSummaryDAC()
	{
		return processSummaryDAC;
	}

	/**
	 * Sets the process summary dac.
	 * 
	 * @param processSummaryDAC the new process summary dac
	 */
	@Resource
	public void setProcessSummaryDAC(IProcessSummaryDAC processSummaryDAC)
	{
		this.processSummaryDAC = processSummaryDAC;
	}

	/**
	 * Gets the process type dac.
	 * 
	 * @return the process type dac
	 */
	public IProcessTypeDAC getProcessTypeDAC()
	{
		return processTypeDAC;
	}

	/**
	 * Sets the process type dac.
	 * 
	 * @param processTypeDAC the new process type dac
	 */
	@Resource
	public void setProcessTypeDAC(IProcessTypeDAC processTypeDAC)
	{
		this.processTypeDAC = processTypeDAC;
	}

	/**
	 * Gets the property dac.
	 * 
	 * @return the property dac
	 */
	public IPropertyDAC getPropertyDAC()
	{
		return propertyDAC;
	}

	/**
	 * Sets the property dac.
	 * 
	 * @param propertyDAC the new property dac
	 */
	@Resource
	public void setPropertyDAC(IPropertyDAC propertyDAC)
	{
		this.propertyDAC = propertyDAC;
	}

	/**
	 * Gets the custom search dac.
	 * 
	 * @return the custom search dac
	 */
	protected ICustomSearchDAC getCustomSearchDAC()
	{
		return customSearchDAC;
	}

	/**
	 * Sets the custom search dac.
	 * 
	 * @param customSearchDAC the new custom search dac
	 */
	@Resource
	protected void setCustomSearchDAC(ICustomSearchDAC customSearchDAC)
	{
		this.customSearchDAC = customSearchDAC;
	}

	/**
	 * Gets the electric meter dac.
	 * 
	 * @return the electric meter dac
	 */
	public IElectricMeterDAC getElectricMeterDAC()
	{
		return electricMeterDAC;
	}

	/**
	 * Sets the electric meter dac.
	 * 
	 * @param electricMeterDAC the new electric meter dac
	 */
	@Resource
	public void setElectricMeterDAC(IElectricMeterDAC electricMeterDAC)
	{
		this.electricMeterDAC = electricMeterDAC;
	}

	/**
	 * Gets the device dac.
	 * 
	 * @return the device dac
	 */
	public IDeviceDAC getDeviceDAC()
	{
		return deviceDAC;
	}

	/**
	 * Sets the device dac.
	 * 
	 * @param deviceDAC the new device dac
	 */
	@Resource
	public void setDeviceDAC(IDeviceDAC deviceDAC)
	{
		this.deviceDAC = deviceDAC;
	}

	/**
	 * Gets the group dac.
	 * 
	 * @return the group dac
	 */
	public IGroupDAC getGroupDAC()
	{
		return groupDAC;
	}

	/**
	 * Sets the group dac.
	 * 
	 * @param groupDAC the new group dac
	 */
	@Resource
	public void setGroupDAC(IGroupDAC groupDAC)
	{
		this.groupDAC = groupDAC;
	}

	/**
	 * Gets the tag dac.
	 * 
	 * @return the tag dac
	 */
	public ITagDAC getTagDAC()
	{
		return tagDAC;
	}

	/**
	 * Sets the tag dac.
	 * 
	 * @param tagDAC the new tag dac
	 */
	@Resource
	public void setTagDAC(ITagDAC tagDAC)
	{
		this.tagDAC = tagDAC;
	}

	/**
	 * Gets the schedule dac.
	 * 
	 * @return the schedule dac
	 */
	public IScheduleDAC getScheduleDAC()
	{
		return scheduleDAC;
	}

	/**
	 * Sets the schedule dac.
	 * 
	 * @param scheduleDAC the new schedule dac
	 */
	@Resource
	public void setScheduleDAC(IScheduleDAC scheduleDAC)
	{
		this.scheduleDAC = scheduleDAC;
	}

	/**
	 * Gets the water meter dac.
	 * 
	 * @return the water meter dac
	 */
	public IWaterMeterDAC getWaterMeterDAC()
	{
		return waterMeterDAC;
	}

	/**
	 * Sets the water meter dac.
	 * 
	 * @param waterMeterDAC the new water meter dac
	 */
	@Resource
	public void setWaterMeterDAC(IWaterMeterDAC waterMeterDAC)
	{
		this.waterMeterDAC = waterMeterDAC;
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
	 * Insert custom search.
	 * 
	 * @return the custom search
	 */
	protected CustomSearch insertCustomSearch()
	{
		CustomSearchRequest customSearchRequest = createCustomSearchRequest();
		customSearchRequest.setCustomSearch(createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
		customSearchRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		customSearchRequest.setTenant(TestBaseUtil.createTenant());
		InternalResultsResponse<CustomSearch> response =
				getCustomSearchDAC().insertCustomSearch(customSearchRequest);
		assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Upsert property.
	 */
	protected void upsertProperty()
	{
		upsertProperty(PropertyEnum.LANGUAGE);
	}

	/**
	 * Upsert property.
	 * 
	 * @param propertyEnum the property enum
	 */
	protected void upsertProperty(PropertyEnum propertyEnum)
	{
		PropertyRequest request = TestBaseUtil.createPropertyRequest();
		request.setProperties(TestBaseUtil.createPropertyList(propertyEnum));
		request.setPropertyEnum(propertyEnum);

		InternalResponse response = getPropertyDAC().upsertProperty(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Insert process.
	 * 
	 * @param process the process
	 * @return the ePM process
	 */
	protected DMProcess insertProcess(DMProcess process)
	{
		return insertProcess(process, ServiceTypeEnum.ELECTRIC);
	}

	/**
	 * Insert process.
	 * 
	 * @param process the process
	 * @param serviceType the service type
	 * @return the ePM process
	 */
	protected DMProcess insertProcess(DMProcess process, ServiceTypeEnum serviceType)
	{
		if (!ValidationUtil.isNull(process.getAction()))
		{
			insertAction(process.getAction());
		}

		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.setServiceTypeEnum(serviceType);
		processRequest.addProcessAsFirstElement(process);
		processRequest.setTenant(TestBaseUtil.createTenant());

		InternalResultsResponse<DMProcess> response =
				getProcessDAC().insertProcess(processRequest);
		assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Insert process items.
	 * 
	 * @param processRequest the process request
	 */
	protected void insertProcessItems(ProcessRequest processRequest)
	{
		Device device = TestBaseUtil.createDevice();
		device.setDeviceId(DEVICE_ID);
		processRequest.getFirstProcess().addProcessItem(
				new ProcessItem(device, ProcessItemStatusEnum.RUNNING, null));
		processRequest.getFirstProcess().getFirstProcessItem()
				.addProcessItemHistory(new ProcessItemHistory(ProcessItemHistoryStatusEnum.EVENTRECEIVED, new Date()));
		processRequest.getFirstProcess().getFirstProcessItem().setMessage(StringUtils.repeat('m', ONE_HUNDRED));
		InternalResponse internalResponse = getProcessDAC().insertProcessItems(processRequest);
		TestBaseUtil.assertResponse(internalResponse);
	}

	/**
	 * Insert process items.
	 * 
	 * @param processRequest the process request
	 * @param deviceType the device type
	 */
	protected void insertProcessItems(ProcessRequest processRequest, DeviceTypeEnum deviceType)
	{
		Device device = TestBaseUtil.createDevice(deviceType);
		device.setDeviceId(DEVICE_ID);
		processRequest.getFirstProcess().addProcessItem(
				new ProcessItem(device, ProcessItemStatusEnum.RUNNING, null));
		processRequest.getFirstProcess().getFirstProcessItem()
				.addProcessItemHistory(new ProcessItemHistory(ProcessItemHistoryStatusEnum.EVENTRECEIVED, new Date()));
		processRequest.getFirstProcess().getFirstProcessItem().setMessage(StringUtils.repeat('m', ONE_HUNDRED));
		InternalResponse internalResponse = getProcessDAC().insertProcessItems(processRequest);
		TestBaseUtil.assertResponse(internalResponse);
	}

	/**
	 * Insert action.
	 * 
	 * @param action the action
	 * @return the ePM action
	 */
	protected DMAction insertAction(DMAction action)
	{
		ActionRequest actionRequest = TestBaseUtil.createActionRequest();
		actionRequest.setAction(action);

		InternalResultsResponse<DMAction> response =
				getActionDAC().insertAction(actionRequest);
		assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Insert action with group.
	 * 
	 * @param action the action
	 * @return the ePM action
	 */
	protected DMAction insertActionWithGroup(DMAction action)
	{
		DMAction dmAction = insertAction(action);
		dmAction.addGroup(insertGroup(new Group(NAME, DESCRIPTION, GroupTypeEnum.BILLING)));

		InternalResponse response =
				getActionDAC().insertGroupsToAction(new ActionRequest(dmAction, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(response);

		return dmAction;
	}

	/**
	 * Insert action with tag.
	 * 
	 * @param action the action
	 * @return the ePM action
	 */
	protected DMAction insertActionWithTag(DMAction action)
	{
		DMAction dmAction = insertAction(action);
		dmAction.addTag(insertTag(new Tag(NAME)));

		InternalResponse response =
				getActionDAC().insertTagsToAction(new ActionRequest(dmAction, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(response);

		return dmAction;
	}

	/**
	 * Insert action with device.
	 * 
	 * @param action the action
	 * @return the ePM action
	 */
	protected DMAction insertActionWithDevice(DMAction action)
	{
		DMAction dmAction = insertAction(action);
		dmAction.addDevice(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER));

		InternalResponse response =
				getActionDAC().insertDevicesToAction(new ActionRequest(dmAction, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(response);

		return dmAction;
	}

	/**
	 * Insert process property.
	 * 
	 * @param request the request
	 */
	protected void insertProcessProperty(ProcessRequest request)
	{
		InternalResponse response = getProcessDAC().insertProcessProperty(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Gets the first device.
	 * 
	 * @param deviceType the device type
	 * @return the first device
	 */
	protected Device getFirstDevice(DeviceTypeEnum deviceType)
	{
		InquiryDeviceRequest inquiryRequest = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		inquiryRequest.addSortExpressions(new SortExpression(PropertyEnum.FLEXNET_ID.getValue(), Direction.Ascending));
		inquiryRequest.setPageSize(1);
		inquiryRequest.setStartRow(0);
		inquiryRequest.setDeviceSearch(new DeviceSearch());
		inquiryRequest.setTenant(TestBaseUtil.createTenant());

		if (DeviceTypeEnum.ELECTRIC_METER.equals(deviceType))
		{
			inquiryRequest.getDeviceSearch().addDeviceType(DeviceTypeEnum.ELECTRIC_METER);

			return getElectricMeterDAC().fetchAllElectricMeter(inquiryRequest).getFirstResult();
		}

		inquiryRequest.getDeviceSearch().addDeviceType(DeviceTypeEnum.HAN_DEVICE);

		return getElectricMeterDAC().fetchAllElectricMeter(inquiryRequest).getFirstResult();
	}

	/**
	 * Gets the first device.
	 * 
	 * @param deviceType the device type
	 * @param flexnetID the flexnet id
	 * @return the first device
	 */
	protected Device getFirstDevice(DeviceTypeEnum deviceType, BigInteger flexnetID)
	{
		Device device = getFirstDevice(deviceType);
		device.getRadio().setFlexNetId(flexnetID);
		return device;
	}

	/**
	 * Insert group.
	 * 
	 * @param group the group
	 * @return the group
	 */
	protected Group insertGroup(Group group)
	{
		return insertGroup(group, ServiceTypeEnum.ELECTRIC);
	}

	/**
	 * Insert group.
	 * 
	 * @param group the group
	 * @param serviceType the service type
	 * @return the group
	 */
	protected Group insertGroup(Group group, ServiceTypeEnum serviceType)
	{
		GroupRequest request = new GroupRequest(group, TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.setServiceTypeEnum(serviceType);
		return getGroupDAC().insertGroup(request).getFirstResult();
	}

	/**
	 * Insert tag.
	 * 
	 * @param tag the tag
	 * @return the tag
	 */
	protected Tag insertTag(Tag tag)
	{
		return insertTag(tag, ServiceTypeEnum.ELECTRIC);
	}

	/**
	 * Insert tag.
	 * 
	 * @param tag the tag
	 * @param serviceTypeEnum the service type enum
	 * @return the tag
	 */
	protected Tag insertTag(Tag tag, ServiceTypeEnum serviceTypeEnum)
	{
		TagRequest request = new TagRequest(tag, TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.setServiceTypeEnum(serviceTypeEnum);
		return getTagDAC().insertTag(request).getFirstResult();
	}

	/**
	 * Insert schedule.
	 * 
	 * @param schedule the schedule
	 * @param scheduleProperty the schedule property
	 * @return the ePM schedule
	 */
	protected DMSchedule insertSchedule(DMSchedule schedule, Boolean scheduleProperty)
	{
		return insertSchedule(schedule, scheduleProperty, ServiceTypeEnum.ELECTRIC);
	}

	/**
	 * Insert schedule.
	 * 
	 * @param schedule the schedule
	 * @param scheduleProperty the schedule property
	 * @param serviceTypeEnum the service type enum
	 * @return the ePM schedule
	 */
	protected DMSchedule insertSchedule(DMSchedule schedule, Boolean scheduleProperty, ServiceTypeEnum serviceTypeEnum)
	{
		if (!ValidationUtil.isNull(schedule.getDmAction())
				&& ValidationUtil.isNullOrZero(schedule.getDmAction().getId()))
		{
			schedule.setDmAction(insertAction(schedule.getDmAction()));
		}

		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(serviceTypeEnum);
		request.setTenant(new DMTenant(CUSTOMER_ID));

		getScheduleDAC().insertSchedule(request);

		if (scheduleProperty)
		{
			schedule.setProperties(createPropertySchedule(schedule));
			getScheduleDAC().insertScheduleProperty(new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));
		}

		return schedule;
	}

	/**
	 * Creates the property schedule.
	 * 
	 * @param schedule the schedule
	 * @return the list
	 */
	protected List<Property> createPropertySchedule(DMSchedule schedule)
	{
		List<Property> properties = new ArrayList<Property>();

		properties.add(new Property(PropertyEnum.IS_MONITORED.getValue()
				, schedule.getDmAction().getIsMonitored().toString()));

		DMConvertUtil.convertActionToProperty(schedule.getDmAction(), properties);

		DMConvertUtil.convertFrequencyToProperty(schedule.getFrequency(), properties);
		return properties;
	}

	/**
	 * Insert device to tag.
	 * 
	 * @return the tag
	 */
	protected Tag insertDeviceToTag()
	{
		// insert the tag...
		Tag tag = insertTag(TestBaseUtil.createTag());

		// insert the device...
		InquiryDeviceRequest deviceRequest = new InquiryDeviceRequest();
		deviceRequest.addTag(tag);
		deviceRequest.addDevice(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER));
		deviceRequest.setUserContext(TestBaseUtil.createUserContext());

		InternalResponse deviceResponse = getTagDAC().insertDeviceToTag(deviceRequest);
		TestBaseUtil.assertResponse(deviceResponse);
		tag.addDevice(deviceRequest.getFirstDevice());

		return tag;
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

		assertEquals(ASSERT_MESSAGES_EQUAL, messages.length, response.getMessageInfoList().size());
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

		assertEquals(ASSERT_MESSAGES_EQUAL, messages.length, response.getMessageInfoList().size());
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
