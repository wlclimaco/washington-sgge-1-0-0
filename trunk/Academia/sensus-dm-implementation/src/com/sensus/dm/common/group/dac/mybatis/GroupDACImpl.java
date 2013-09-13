package com.sensus.dm.common.group.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.dac.IGroupDAC;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;

/**
 * The Class GroupDACImpl.
 * 
 * @author QAT Brazil
 */
public class GroupDACImpl extends SqlSessionDaoSupport implements IGroupDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant GROUP_TYPE_VALUE. */
	private static final Integer GROUP_TYPE_VALUE = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final int PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE10. */
	private static final Integer PARAMSIZE10 = 10;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant GROUP. */
	private static final String GROUP = "group";

	/** The Constant GROUP_DESC. */
	private static final String GROUP_DESC = "group_desc";

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "group_id";

	/** The Constant GROUPSET_ID. */
	private static final String GROUPSET_ID = "groupset_id";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "group_name";

	/** The Constant GROUP_TYPE. */
	private static final String GROUP_TYPE = "group_type";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customer_id";

	/** The Constant MODIFY_USER. */
	private static final String MODIFY_USER = "modify_user";

	/** The Constant OLD_NAME. */
	private static final String OLD_NAME = "old_name";

	/** The Constant SUBGROUP_TYPE. */
	private static final String SUBGROUP_TYPE = "subgroup_type";

	/** The Constant MODIFIED_USER. */
	private static final String SERVICE_TYPE = "service_type";

	/** The Constant DEVICE_TYPE. */
	private static final String DEVICE_TYPE = "device_type";

	/** The Constant HAN_DEVICE_TYPE. */
	private static final String HAN_DEVICE_TYPE = "han_device_type";

	/** The Constant INSERT_TYPE. */
	private static final String INSERT_TYPE = "insert_type";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant METER_ALREADY_IN_GROUP. */
	private static final String METER_ALREADY_IN_GROUP = "sensus.dm.common.groupvalidator.meter.already.exist.group";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_GROUP_ALREADY_EXISTS. */
	private static final String SENSUS_EPM_GROUPVALIDATOR_GROUP_ALREADY_EXISTS =
			"sensus.dm.common.groupvalidator.group.already.exists";

	private static final String SENSUS_EPM_GROUPVALIDATOR_DEVICE_NOT_IN_GROUP =
			"summary.text.processStatusMessage.single.device.not.in.group";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant GROUP_MAP. */
	private static final String GROUP_MAP = "GroupMap.";

	/** The Constant COUNT_GROUP_BY_NAME. */
	private static final String COUNT_GROUP_BY_NAME = GROUP_MAP + "countGroupByName";

	/** The Constant DELETE_DEVICE_FROM_GROUP. */
	private static final String DELETE_DEVICE_FROM_GROUP = GROUP_MAP + "deleteDeviceFromGroup";

	/** The Constant DELETE_GROUP. */
	private static final String DELETE_GROUP = GROUP_MAP + "deleteGroup";

	/** The Constant FETCH_ALL_GROUPS. */
	private static final String FETCH_ALL_GROUPS = GROUP_MAP + "fetchAllGroups";

	/** The Constant FETCH_DEVICES_BY_GROUPS. */
	private static final String FETCH_DEVICES_BY_GROUPS = GROUP_MAP + "fetchDevicesByGroups";

	/** The Constant FETCH_GROUPS_BY_DEVICE_ID. */
	private static final String FETCH_GROUPS_BY_DEVICE_ID = GROUP_MAP + "fetchGroupsByDeviceId";

	/** The Constant INSERT_DEVICE_TO_GROUP. */
	private static final String INSERT_DEVICE_TO_GROUP = GROUP_MAP + "insertDeviceToGroup";

	/** The Constant INSERT_GROUP. */
	private static final String INSERT_GROUP = GROUP_MAP + "insertGroup";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = GROUP_MAP + "paginationTotalRows";

	/** The Constant UPDATE_GROUP. */
	private static final String UPDATE_GROUP = GROUP_MAP + "updateGroup";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#deleteGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(GROUPSET_ID, groupRequest.getFirstGroup().getId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_GROUP, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#insertGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(GROUP_NAME, groupRequest.getFirstGroup().getName());
		paramMap.put(GROUP_DESC, groupRequest.getFirstGroup().getDescription());
		paramMap.put(CREATE_USER, groupRequest.getUserContext().getUserId());
		paramMap.put(GROUP_TYPE, GROUP_TYPE_VALUE);
		paramMap.put(SUBGROUP_TYPE, groupRequest.getFirstGroup().getGroupTypeEnum().getValue());
		paramMap.put(SERVICE_TYPE, groupRequest.getServiceTypeEnum().getServiceGroupType());
		paramMap.put(CUSTOMER_ID, groupRequest.getTenant().getKey());
		paramMap.put(INSERT_TYPE, GROUP);

		if (!ValidationUtil.isNull(groupRequest.getFirstGroup().getDeviceType()))
		{
			paramMap.put(DEVICE_TYPE, groupRequest.getFirstGroup().getDeviceType().getValue());
			paramMap.put(HAN_DEVICE_TYPE, groupRequest.getFirstGroup().getHanDeviceTypeValue());
		}

		Integer groupId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_GROUP, paramMap);

		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();

		if (!ValidationUtil.isNull(groupId))
		{
			groupRequest.getFirstGroup().setId(groupId);
			response.addResult(groupRequest.getFirstGroup());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#fetchCanGroupBeInserted(com.sensus.dm.common.group.model.request.
	 * GroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Boolean> fetchCanGroupBeInserted(GroupRequest groupRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (isGroupNameUnique(groupRequest)
				|| groupRequest.getFirstGroup().getName().equals(groupRequest.getFirstGroup().getOldName()))
		{
			response.getResultsList().add(true);
			return response;
		}

		response.getResultsList().add(false);

		response.addMessage(
				SENSUS_EPM_GROUPVALIDATOR_GROUP_ALREADY_EXISTS,
				Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#updateGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> updateGroup(GroupRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(GROUP_ID, groupRequest.getFirstGroup().getId());
		paramMap.put(GROUP_NAME, groupRequest.getFirstGroup().getName());
		paramMap.put(GROUP_DESC, groupRequest.getFirstGroup().getDescription());
		paramMap.put(MODIFY_USER, groupRequest.getUserContext().getUserId());
		paramMap.put(OLD_NAME, groupRequest.getFirstGroup().getOldName());
		paramMap.put(SUBGROUP_TYPE, groupRequest.getFirstGroup().getGroupTypeEnum().getValue());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_GROUP, paramMap);

		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(groupRequest.getFirstGroup());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#fetchAllGroups(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryGroupRequest inquiryGroupRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();

		if (inquiryGroupRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryGroupRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_GROUPS, inquiryGroupRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#deleteDeviceFromGroup(com.sensus.dm.elec.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDeviceFromGroup(InquiryDeviceRequest deviceRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(GROUPSET_ID, deviceRequest.getFirstGroup().getId());
		paramMap.put(FLEXNET_ID, deviceRequest.getFirstDevice().getRadio().getFlexNetId());

		Integer result =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_DEVICE_FROM_GROUP, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.getMessageInfoList().add(new MessageInfo(SENSUS_EPM_GROUPVALIDATOR_DEVICE_NOT_IN_GROUP,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#insertDeviceToGroup(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse insertDeviceToGroup(InquiryDeviceRequest deviceRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(GROUPSET_ID, deviceRequest.getFirstGroup().getId());
		paramMap.put(FLEXNET_ID, deviceRequest.getFirstDevice().getRadio().getFlexNetId());
		paramMap.put(CREATE_USER, deviceRequest.getUserContext().getUserId());

		try
		{
			Integer result =
					(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_DEVICE_TO_GROUP, paramMap);

			if (ValidationUtil.isNullOrZero(result))
			{
				response.setStatus(InternalResponse.Status.NoRowsInsertedError);
			}
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(METER_ALREADY_IN_GROUP, MessageSeverity.Error, MessageLevel.None);
		}
		catch (DataIntegrityViolationException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(METER_ALREADY_IN_GROUP, MessageSeverity.Error, MessageLevel.None);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#fetchGroupsByDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Group> fetchGroupsByDevice(DeviceRequest request)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_GROUPS_BY_DEVICE_ID, request));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#fetchDevicesByGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Device> fetchDevicesByGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		response.getResultsList().addAll(SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_DEVICES_BY_GROUPS, groupRequest));

		return response;

	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Checks if is group name unique.
	 * 
	 * @param groupRequest the group request
	 * @return the boolean
	 */
	private Boolean isGroupNameUnique(GroupRequest groupRequest)
	{

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(GROUP_NAME, groupRequest.getFirstGroup().getName());
		paramMap.put(GROUP_TYPE, GROUP_TYPE_VALUE);
		paramMap.put(SERVICE_TYPE, groupRequest.getServiceTypeEnum().getServiceGroupType());
		paramMap.put(CUSTOMER_ID, groupRequest.getTenant().getKey());

		return ValidationUtil.isNullOrZero(
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), COUNT_GROUP_BY_NAME, paramMap));
	}

	// -------------------------------------------------------------------------
}
