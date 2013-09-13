package com.sensus.dm.common.group.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.group.bcl.IGroupBCL;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;

/**
 * The Class GroupBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/group/groupbcfimpltest.xml"})
public class GroupBCFImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant FETCH_DEVICES_BY_GROUP. */
	private static final String FETCH_DEVICES_BY_GROUP = "fetchDevicesByGroup";

	/** The Constant DELETE_DEVICE_FROM_GROUP. */
	private static final String DELETE_DEVICE_FROM_GROUP = "deleteDeviceFromGroup";

	/** The Constant INSERT_DEVICE_FROM_FILE_TO_GROUP. */
	private static final String INSERT_DEVICE_FROM_FILE_TO_GROUP = "insertDeviceFromFileToGroup";

	/** The Constant FETCH_CAN_GROUP_BE_INSERTED. */
	private static final String FETCH_CAN_GROUP_BE_INSERTED = "fetchCanGroupBeInserted";

	/** The Constant GENERATE_GROUPS_CSV. */
	private static final String GENERATE_GROUPS_CSV = "generateGroupsCSV";

	/** The Constant INSERT_DEVICE_TO_GROUP. */
	private static final String INSERT_DEVICE_TO_GROUP = "insertDeviceToGroup";

	/** The Constant SHOULD_BE_TRUE. */
	private static final String SHOULD_BE_TRUE = "should be true ";

	/** The Constant SHOULD_BE_FALSE. */
	private static final String SHOULD_BE_FALSE = "should be false ";

	/** The Constant UPDATE_GROUP. */
	private static final String UPDATE_GROUP = "updateGroup";

	/** The Constant GROUP_FOR_TESTS. */
	private static final String GROUP_FOR_TESTS = "Group for tests";

	/** The Constant GROUP_OLD_NAME. */
	private static final String GROUP_OLD_NAME = "Group Old Name";

	/** The Constant INSERT_GROUP. */
	private static final String INSERT_GROUP = "insertGroup";

	/** The Constant GROUP_FOR_TEST. */
	private static final String GROUP_FOR_TEST = "Group for test";

	/** The Constant LETTER_N_TO_DESCRIPTION. */
	private static final String LETTER_N_TO_DESCRIPTION = "n";

	/** The Constant FETCH_ALL_GROUPS. */
	private static final String FETCH_ALL_GROUPS = "fetchAllGroups";

	/** The Constant FETCH_GROUPS_BY_DEVICE. */
	private static final String FETCH_GROUPS_BY_DEVICE = "fetchGroupsByDevice";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_GROUP_BCF_EXCEPTION_MSG = "sensus.dm.elec.groupbcfimpl.defaultexception";

	/** The Constant SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED. */
	private static final String ORDERBY_REQUIRED =
			"sensus.epm.orderbyvalidator.sort.required";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_ID_REQUIRED. */
	private static final String GROUP_ID_REQUIRED = "sensus.dm.common.groupvalidator.id.required";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_GROUP_REQUIRED. */
	private static final String GROUP_REQUIRED = "sensus.dm.common.groupvalidator.group.required";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_NAME_REQUIRED. */
	private static final String GROUP_NAME_REQUIRED = "sensus.dm.common.groupvalidator.name.required";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_NAME_INVALID. */
	private static final String GROUP_NAME_INVALID = "sensus.dm.common.groupvalidator.group.name.invalid";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_PROCESS_ID_REQUIRED. */
	private static final String GROUP_SUBTYPE_REQUIRED = "sensus.dm.common.groupvalidator.subtype.required";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_DESCRIPTION_INVALID. */
	private static final String GROUP_DESCRIPTION_INVALID = "sensus.dm.common.groupvalidator.description.invalid";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_OLD_NAME_REQUIRED. */
	private static final String GROUP_OLD_NAME_REQUIRED = "sensus.dm.common.groupvalidator.old.name.required";

	/** The Constant DEVICE_TYPE_REQUIRED. */
	private static final String DEVICE_TYPE_REQUIRED = "sensus.epm.devicevalidator.devicetype.required";

	/** The Constant DEVICE_SEARCH_REQUIRED. */
	private static final String DEVICE_SEARCH_REQUIRED = "sensus.epm.devicesearchvalidator.device.search.required";

	/** The Constant HAN_DEVICE_SEARCH_REQUIRED. */
	private static final String HAN_DEVICE_SEARCH_REQUIRED =
			"sensus.epm.devicesearchvalidator.han.device.search.required";

	/** The Constant HAN_DEVICE_TYPE_REQUIRED. */
	private static final String HAN_DEVICE_TYPE_REQUIRED = "sensus.epm.devicesearchvalidator.han.device.type.required";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The group bcf. */
	private IGroupBCF groupBCF; // injected by Spring through setter

	/**
	 * Sets the group bcf.
	 * 
	 * @param iGroupBCFParam the new group bcf
	 */
	@Resource
	public void setGroupBCF(IGroupBCF iGroupBCFParam)
	{
		groupBCF = iGroupBCFParam;
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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests Settings:

	/**
	 * Sets the group area.
	 */
	public void setGroupArea()
	{
		setArea(getGroupBCF(), EPMAreaEnum.GROUP);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setGroupArea();
	}

	/**
	 * Removes the tag area.
	 */
	@After
	public void resetMocksToTagArea()
	{
		resetMocksData(getGroupBCF());
		setGroupArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all groups.
	 */
	@Test
	public void testFetchAllGroups()
	{
		// Validation with fail situation - user context required
		InquiryGroupRequest request = new InquiryGroupRequest();
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setTenant(TestBaseUtil.createTenant());
		InquiryGroupResponse response = getGroupBCF().fetchAllGroups(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation with fail situation - tenant required.
		request = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(null);
		response = getGroupBCF().fetchAllGroups(request);
		assertMessages(response, TENANT_REQUIRED);

		// Validation with fail situation - customer id required.
		request = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(new DMTenant(null));
		response = getGroupBCF().fetchAllGroups(request);
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Validation with fail situation - sort expression required
		request = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().fetchAllGroups(request);
		assertMessages(response, ORDERBY_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - page size and start row
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		TestBaseUtil.createInvalidPageSizeStartRow(request);
		response = getGroupBCF().fetchAllGroups(request);
		assertMessages(response, START_ROW_INVALID, PAGE_SIZE_INVALID);

		// Success situation
		request = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		request.setUserContext(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().fetchAllGroups(request);
		assertEquals("should bring one group ", 1, response.getGroups().size());
		assertNotNull("must have the ID", response.getGroups().get(0).getId());

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				FETCH_ALL_GROUPS);
		response = getGroupBCF().fetchAllGroups(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				FETCH_ALL_GROUPS);
		response = getGroupBCF().fetchAllGroups(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch group by id.
	 */
	@Test
	public void testFetchGroupById()
	{
		// Success situation
		InquiryGroupRequest request =
				new InquiryGroupRequest(TestBaseUtil.createGroup());
		request.setUserContext(TestBaseUtil.createUserContext());

		GroupResponse response = getGroupBCF().fetchGroupById(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - group id is required
		request = new InquiryGroupRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());

		response = getGroupBCF().fetchGroupById(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user context required
		request = new InquiryGroupRequest();
		request.addGroup(TestBaseUtil.createGroup());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().fetchGroupById(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				FETCH_ALL_GROUPS);

		request = new InquiryGroupRequest(TestBaseUtil.createGroup());
		request.setUserContext(TestBaseUtil.createUserContext());

		response = getGroupBCF().fetchGroupById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				FETCH_ALL_GROUPS);

		request = new InquiryGroupRequest(TestBaseUtil.createGroup());
		request.setUserContext(TestBaseUtil.createUserContext());

		response = getGroupBCF().fetchGroupById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch groups by meter.
	 */
	@Test
	public void testFetchGroupsByDevice()
	{
		// Success situation
		DeviceRequest request = new DeviceRequest(TestBaseUtil.createDevice());
		request.setUserContext(TestBaseUtil.createUserContext());

		GroupResponse response = getGroupBCF().fetchGroupsByDevice(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - device is required
		request = new DeviceRequest();
		request.setUserContext(TestBaseUtil.createUserContext());

		response = getGroupBCF().fetchGroupsByDevice(request);
		assertMessages(response, DEVICE_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user context is required
		request = new DeviceRequest(TestBaseUtil.createDevice());
		response = getGroupBCF().fetchGroupsByDevice(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id is required
		request.setUserContext(new UserContext());
		response = getGroupBCF().fetchGroupsByDevice(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - device flexnet id is required
		request = new DeviceRequest(TestBaseUtil.createDevice());
		request.getFirstDevice().getRadio().setFlexNetId(null); // force validation failure by removing the flexnet id
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchGroupsByDevice(request);
		assertMessages(response, FLEXNET_ID_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				FETCH_GROUPS_BY_DEVICE);

		request = new DeviceRequest(TestBaseUtil.createDevice());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchGroupsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				FETCH_GROUPS_BY_DEVICE);

		response = getGroupBCF().fetchGroupsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch group by name.
	 */
	@Test
	public void testFetchGroupByName()
	{
		// Success situation
		InquiryGroupRequest request =
				new InquiryGroupRequest(TestBaseUtil.createGroup(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		request.setUserContext(TestBaseUtil.createUserContext());
		GroupResponse response = getGroupBCF().fetchGroupByName(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - user context is required
		request = new InquiryGroupRequest();
		request.addGroup(TestBaseUtil.createGroup());
		response = getGroupBCF().fetchGroupByName(request);
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id is required
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setUserContext(new UserContext());
		response = getGroupBCF().fetchGroupByName(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group is required
		request = new InquiryGroupRequest(ServiceTypeEnum.ELECTRIC);
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchGroupByName(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group name is required
		request =
				new InquiryGroupRequest(TestBaseUtil.createGroup(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		request.getFirstGroup().setName(null);
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchGroupByName(request);
		assertMessages(response, GROUP_NAME_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group name is invalid
		request =
				new InquiryGroupRequest(new Group(1, StringUtils.repeat(LETTER_N_TO_DESCRIPTION, ONE_HUNDRED_ONE)),
						ServiceTypeEnum.ELECTRIC, TestBaseUtil.createTenant());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchGroupByName(request);
		assertMessages(response, GROUP_NAME_INVALID);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				FETCH_ALL_GROUPS);

		request =
				new InquiryGroupRequest(TestBaseUtil.createGroup(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchGroupByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				FETCH_ALL_GROUPS);

		request =
				new InquiryGroupRequest(TestBaseUtil.createGroup(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchGroupByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test insert group.
	 */
	@Test
	public void testInsertGroup()
	{
		// Success situation
		GroupRequest request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.getGroups().get(0).setGroupTypeEnum(GroupTypeEnum.BILLING);
		request.setTenant(TestBaseUtil.createTenant());
		GroupResponse response = getGroupBCF().insertGroup(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - user context and service type enum required
		request = new GroupRequest(TestBaseUtil.createGroup());
		request.getGroups().get(0).setGroupTypeEnum(GroupTypeEnum.BILLING);
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id required
		request = new GroupRequest(TestBaseUtil.createGroup(), new UserContext());
		request.getGroups().get(0).setGroupTypeEnum(GroupTypeEnum.BILLING);
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, USER_ID_REQUIRED, TENANT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group required
		request = new GroupRequest(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group name required
		request = new GroupRequest(new Group(1, GroupTypeEnum.BILLING), TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, GROUP_NAME_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group name is invalid
		request = new GroupRequest(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		Group group = new Group(1, GroupTypeEnum.BILLING);
		group.setName(StringUtils.repeat(LETTER_N_TO_DESCRIPTION, ONE_HUNDRED_ONE));

		request.addGroup(group);
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, GROUP_NAME_INVALID);
		assertNotNull(response);

		// Validation with fail situation - group description is invalid
		request = new GroupRequest(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		group = new Group(1, GroupTypeEnum.BILLING);
		group.setName(GROUP_FOR_TEST);
		group.setDescription(StringUtils.repeat(LETTER_N_TO_DESCRIPTION, TWO_HUNDRED_ONE));

		request.addGroup(group);
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, GROUP_DESCRIPTION_INVALID);
		assertNotNull(response);

		// Validation with fail situation - group sub-type is required
		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().insertGroup(request);
		assertMessages(response, GROUP_SUBTYPE_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				INSERT_GROUP);

		request.getGroups().get(0).setGroupTypeEnum(GroupTypeEnum.BILLING);
		response = getGroupBCF().insertGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				INSERT_GROUP);

		response = getGroupBCF().insertGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test update group.
	 */
	@Test
	public void testUpdateGroup()
	{
		// Success situation
		GroupRequest request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.getGroups().get(0).setOldName(GROUP_OLD_NAME);

		GroupResponse response = getGroupBCF().updateGroup(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - user context required
		request = new GroupRequest(TestBaseUtil.createGroup());
		request.getGroups().get(0).setOldName(GROUP_OLD_NAME);
		response = getGroupBCF().updateGroup(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id required
		request = new GroupRequest(TestBaseUtil.createGroup(), new UserContext());
		request.getGroups().get(0).setOldName(GROUP_OLD_NAME);
		response = getGroupBCF().updateGroup(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group required
		request = new GroupRequest(TestBaseUtil.createUserContext());
		response = getGroupBCF().updateGroup(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group name required
		request = new GroupRequest(new Group(1), TestBaseUtil.createUserContext());
		request.getGroups().get(0).setOldName(GROUP_OLD_NAME);

		response = getGroupBCF().updateGroup(request);
		assertMessages(response, GROUP_NAME_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group name invalid
		request = new GroupRequest();
		request.setUserContext(TestBaseUtil.createUserContext());

		Group group = new Group(1, StringUtils.repeat(LETTER_N_TO_DESCRIPTION, ONE_HUNDRED_ONE));
		group.setOldName(GROUP_OLD_NAME);
		request.addGroup(group);

		response = getGroupBCF().updateGroup(request);
		assertMessages(response, GROUP_NAME_INVALID);
		assertNotNull(response);

		// Validation with fail situation - group with description invalid
		request = new GroupRequest(TestBaseUtil.createUserContext());

		group = new Group(1, GROUP_FOR_TESTS);
		group.setOldName(GROUP_OLD_NAME);
		group.setDescription(StringUtils.repeat(LETTER_N_TO_DESCRIPTION, TWO_HUNDRED_ONE));
		request.addGroup(group);

		response = getGroupBCF().updateGroup(request);
		assertMessages(response, GROUP_DESCRIPTION_INVALID);
		assertNotNull(response);

		// Validation with fail situation - group without id
		request = new GroupRequest(TestBaseUtil.createUserContext());
		group = new Group(GROUP_FOR_TESTS);
		group.setOldName(GROUP_OLD_NAME);
		request.addGroup(group);

		response = getGroupBCF().updateGroup(request);
		assertMessages(response, GROUP_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group old name is required
		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		response = getGroupBCF().updateGroup(request);
		assertMessages(response, GROUP_OLD_NAME_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				UPDATE_GROUP);

		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.getGroups().get(0).setOldName(GROUP_OLD_NAME);
		response = getGroupBCF().updateGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				UPDATE_GROUP);

		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.getGroups().get(0).setOldName(GROUP_OLD_NAME);

		response = getGroupBCF().updateGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test delete group.
	 */
	@Test
	public void testDeleteGroup()
	{
		// Exception Situation
		GroupRequest request = null;
		GroupResponse response = getGroupBCF().deleteGroup(request);
		assertEquals(DEFAULT_GROUP_BCF_EXCEPTION_MSG, response.getMessageInfoList().get(0).getCode());
		assertFalse(SHOULD_BE_FALSE, response.isOperationSuccess());

		// Validation Situation - user context required
		request = new GroupRequest();
		response = getGroupBCF().deleteGroup(request);
		assertNotNull(response);
		assertEquals(USER_CONTEXT_REQUIRED, response.getMessageInfoList().get(0).getCode());
		assertFalse(SHOULD_BE_FALSE, response.isOperationSuccess());

		// Validation Situation - user id required
		request.setUserContext(new UserContext());
		response = getGroupBCF().deleteGroup(request);
		assertNotNull(response);
		assertEquals(USER_ID_REQUIRED, response.getMessageInfoList().get(0).getCode());
		assertFalse(SHOULD_BE_FALSE, response.isOperationSuccess());

		// Validation Situation - group required
		request.getUserContext().setUserId(USER);
		response = getGroupBCF().deleteGroup(request);
		assertNotNull(response);
		assertEquals(GROUP_REQUIRED, response.getMessageInfoList().get(0)
				.getCode());
		assertFalse(SHOULD_BE_FALSE, response.isOperationSuccess());

		// Validation Situation - group id required
		request.addGroup(new Group());
		response = getGroupBCF().deleteGroup(request);
		assertNotNull(response);
		assertEquals(GROUP_ID_REQUIRED, response.getMessageInfoList().get(0)
				.getCode());
		assertFalse(SHOULD_BE_FALSE, response.isOperationSuccess());

		// Success Situation
		request.getGroups().get(0).setId(1);
		response = getGroupBCF().deleteGroup(request);
		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
	}

	/**
	 * Test insert smart point to group.
	 */
	@Test
	public void testInsertDeviceToGroup()
	{
		// Success situation
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createGroup(),
						TestBaseUtil.createUserContext());
		request.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		request.addSortExpressions(TestBaseUtil.createSortExpression());

		DeviceResponse response = getGroupBCF().insertDeviceToGroup(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - user context required
		request = new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createGroup(), null);
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id is required
		request = new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createGroup(), new UserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group is required
		request = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		request.addDevice(TestBaseUtil.createDevice());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group id is required
		request = new InquiryDeviceRequest(TestBaseUtil.createDevice(), new Group(), TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, GROUP_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - sort expression is required
		request =
				new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createGroup(),
						TestBaseUtil.createUserContext());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, ORDERBY_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - device type required
		request = new InquiryDeviceRequest(null, TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, DEVICE_TYPE_REQUIRED);
		assertNotNull(response);

		// validation with fail situation - device search required
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, DEVICE_SEARCH_REQUIRED);
		assertNotNull(response);

		// validation with fail situation - device search required
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		request.setDeviceSearch(new DeviceSearch());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, HAN_DEVICE_SEARCH_REQUIRED);
		assertNotNull(response);

		request.getDeviceSearch().setHanDeviceSearch(new HanDeviceSearch());
		response = getGroupBCF().insertDeviceToGroup(request);
		assertMessages(response, HAN_DEVICE_TYPE_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				INSERT_DEVICE_TO_GROUP);

		request =
				new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createGroup(),
						TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);

		response = getGroupBCF().insertDeviceToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				INSERT_DEVICE_TO_GROUP);

		response = getGroupBCF().insertDeviceToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert device from file to group.
	 */
	@Test
	public void testInsertDeviceFromFileToGroup()
	{
		// Success situation
		GroupRequest request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.getFirstGroup().setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		request.setTenant(TestBaseUtil.createTenant());
		request.setFileName(FILE_NAME);

		GroupResponse response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - user context required
		request = new GroupRequest(TestBaseUtil.createGroup());
		request.setFileName(FILE_NAME);
		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertMessages(response, USER_CONTEXT_REQUIRED, TENANT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id required
		request = new GroupRequest(TestBaseUtil.createGroup(), new UserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.setFileName(FILE_NAME);
		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group required
		request = new GroupRequest(TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.setFileName(FILE_NAME);
		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group id required
		request = new GroupRequest(new Group(), TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.setFileName(FILE_NAME);
		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertMessages(response, GROUP_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - file name required
		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.getFirstGroup().setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertMessages(response, FILE_NAME_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - file name invalid
		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.setFileName(StringUtils.repeat(LETTER_N_TO_DESCRIPTION, TWO_HUNDRED_FIFTY_SIX));
		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertMessages(response, FILE_NAME_INVALID);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				INSERT_DEVICE_FROM_FILE_TO_GROUP);

		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.getFirstGroup().setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		request.setTenant(TestBaseUtil.createTenant());
		request.setFileName(FILE_NAME);
		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertFalse(response.isOperationSuccess());

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				INSERT_DEVICE_FROM_FILE_TO_GROUP);

		response = getGroupBCF().insertDeviceFromFileToGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test delete smart point from group.
	 */
	@Test
	public void testDeleteDeviceFromGroup()
	{
		// Success situation
		InquiryDeviceRequest request =
				new InquiryDeviceRequest(null, TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		request.addSortExpressions(TestBaseUtil.createSortExpression());

		DeviceResponse response = getGroupBCF().deleteDeviceFromGroup(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - user context required
		request = new InquiryDeviceRequest(null, TestBaseUtil.createGroup(), null);
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id required
		request = new InquiryDeviceRequest(null, TestBaseUtil.createGroup(), new UserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group required
		request = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group id required
		request.addGroup(new Group());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, GROUP_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - sort expression required
		request = new InquiryDeviceRequest(null, TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, ORDERBY_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - device type required
		request = new InquiryDeviceRequest(null, TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, DEVICE_TYPE_REQUIRED);
		assertNotNull(response);

		// validation with fail situation - device search required
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, DEVICE_SEARCH_REQUIRED);
		assertNotNull(response);

		// validation with fail situation - device search required
		request.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
		request.setDeviceSearch(new DeviceSearch());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, HAN_DEVICE_SEARCH_REQUIRED);
		assertNotNull(response);

		request.getDeviceSearch().setHanDeviceSearch(new HanDeviceSearch());
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertMessages(response, HAN_DEVICE_TYPE_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				DELETE_DEVICE_FROM_GROUP);
		request.getDeviceSearch().getHanDeviceSearch().addHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				DELETE_DEVICE_FROM_GROUP);

		response = getGroupBCF().deleteDeviceFromGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		setSituation(getGroupBCF(), SituationsEnum.SUCCESS, IGroupBCL.class,
				GENERATE_GROUPS_CSV);

		InquiryGroupRequest request = new InquiryGroupRequest();
		request.setUserContext(TestBaseUtil.createUserContext(LOCALE));
		request.setFileName(FILE_NAME);
		request.setProcessId(1);
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setTenant(TestBaseUtil.createTenant());
		InquiryGroupResponse response = getGroupBCF().generateGroupsCSV(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - file name required
		request.setFileName(null);
		response = getGroupBCF().generateGroupsCSV(request);
		assertMessages(response, FILE_NAME_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - file name invalid
		request.setFileName(StringUtils.repeat(LETTER_N_TO_DESCRIPTION, TWO_HUNDRED_FIFTY_SIX));
		response = getGroupBCF().generateGroupsCSV(request);
		assertMessages(response, FILE_NAME_INVALID);
		assertNotNull(response);

		// Validation with fail situation - user context required
		request = new InquiryGroupRequest();
		request.setProcessId(1);
		request.setFileName(FILE_NAME);
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().generateGroupsCSV(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id required
		request.setUserContext(new UserContext());
		request.getUserContext().setLocaleString(LOCALE);
		request.setFileName(FILE_NAME);
		response = getGroupBCF().generateGroupsCSV(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - locale required
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getGroupBCF().generateGroupsCSV(request);
		assertMessages(response, LOCALE_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - tenant required
		request.setUserContext(TestBaseUtil.createUserContext(LOCALE));
		request.setFileName(FILE_NAME);
		request.setTenant(null);
		response = getGroupBCF().generateGroupsCSV(request);
		assertMessages(response, TENANT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - tenant required
		request.setUserContext(new UserContext());
		request.setUserContext(TestBaseUtil.createUserContext(LOCALE));
		request.setFileName(FILE_NAME);
		request.setTenant(new DMTenant(null));
		response = getGroupBCF().generateGroupsCSV(request);
		assertMessages(response, CUSTOMER_ID_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				GENERATE_GROUPS_CSV);

		request = new InquiryGroupRequest();
		request.setUserContext(TestBaseUtil.createUserContext(LOCALE));
		request.setFileName(FILE_NAME);
		request.setProcessId(ONE);
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setTenant(TestBaseUtil.createTenant());
		response = getGroupBCF().generateGroupsCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				GENERATE_GROUPS_CSV);

		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getGroupBCF().generateGroupsCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch devices by group.
	 */
	@Test
	public void testFetchDevicesByGroup()
	{
		// Success situation
		GroupRequest request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		DeviceResponse response = getGroupBCF().fetchDevicesByGroup(request);
		assertTrue(response.isOperationSuccess());

		// Validation with fail situation - user context required
		request.setUserContext(null);
		response = getGroupBCF().fetchDevicesByGroup(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - user id required
		request.setUserContext(new UserContext());
		response = getGroupBCF().fetchDevicesByGroup(request);
		assertMessages(response, USER_ID_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group required
		request = new GroupRequest(TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchDevicesByGroup(request);
		assertMessages(response, GROUP_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - group id required
		request.addGroup(new Group());
		response = getGroupBCF().fetchDevicesByGroup(request);
		assertMessages(response, GROUP_ID_REQUIRED);
		assertNotNull(response);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				FETCH_DEVICES_BY_GROUP);

		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		response = getGroupBCF().fetchDevicesByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				FETCH_DEVICES_BY_GROUP);

		response = getGroupBCF().fetchDevicesByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch can group be inserted.
	 */
	@Test
	public void testFetchCanGroupBeInserted()
	{
		// Success situation
		setSituation(getGroupBCF(), SituationsEnum.SUCCESS, IGroupBCL.class,
				FETCH_CAN_GROUP_BE_INSERTED);

		GroupRequest request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		GroupResponse response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertTrue(response.isOperationSuccess());

		// Validation Situation - user context required
		request = new GroupRequest(TestBaseUtil.createGroup());
		response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(new UserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Validation Situation - group required
		request = new GroupRequest(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, GROUP_REQUIRED);

		// Validation Situation - group name required
		request.addGroup(new Group());
		response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, GROUP_NAME_REQUIRED);

		// Validation Situation - group name invalid
		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.getGroups().get(0).setName(StringUtils.repeat(LETTER_N_TO_DESCRIPTION, ONE_HUNDRED_ONE));
		response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, GROUP_NAME_INVALID);

		// Error situation
		setSituation(getGroupBCF(), SituationsEnum.ERROR, IGroupBCL.class,
				FETCH_CAN_GROUP_BE_INSERTED);

		request = new GroupRequest(TestBaseUtil.createGroup(), TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getGroupBCF(), SituationsEnum.EXCEPTION, IGroupBCL.class,
				FETCH_CAN_GROUP_BE_INSERTED);

		response = getGroupBCF().fetchCanGroupBeInserted(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_GROUP_BCF_EXCEPTION_MSG);

	}
}
