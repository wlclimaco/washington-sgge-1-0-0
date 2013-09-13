package com.sensus.dm.elec.action.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.elec.action.dac.IActionDAC;
import com.sensus.dm.elec.action.model.request.ActionRequest;

/**
 * The Class ActionDACImpl.
 * 
 * @author QAT Global
 */
public class ActionDACImpl extends SqlSessionDaoSupport implements IActionDAC
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant ACTION_ID. */
	private static final String ACTION_ID = "action_instance_id";

	/** The Constant ACTION_TYPE_ID. */
	private static final String ACTION_TYPE_ID = "action_type_id";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant DEVICE_ID. */
	private static final String DEVICE_ID = "device_id";

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "group_id";

	/** The Constant PARAM_ACTION_INSTANCE_ID. */
	private static final String PARAM_ACTION_INSTANCE_ID = "p_action_instance_id";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant ADD_GROUP_TO_ACTION_FAILED. */
	private static final String ADD_GROUP_TO_ACTION_FAILED = "sensus.epm.actionbclimpl.add.group.to.schedule.failed";

	/** The Constant DEL_DEVICE_FROM_ACTION_FAILED. */
	private static final String DEL_DEVICE_FROM_ACTION_FAILED =
			"sensus.epm.actionbclimpl.del.device.from.schedule.failed";

	/** The Constant DEL_GROUP_FROM_ACTION_FAILED. */
	private static final String DEL_GROUP_FROM_ACTION_FAILED =
			"sensus.epm.actionbclimpl.del.group.from.schedule.failed";

	/** The Constant ADD_TAG_TO_ACTION_FAILED. */
	private static final String ADD_TAG_TO_ACTION_FAILED = "sensus.epm.actionbclimpl.add.tag.to.schedule.failed";

	/** The Constant DEL_TAG_FROM_ACTION_FAILED. */
	private static final String DEL_TAG_FROM_ACTION_FAILED = "sensus.epm.actionbclimpl.del.tag.from.schedule.failed";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant ACTION_MAP. */
	private static final String ACTION_MAP = "ActionMap.";

	/** The Constant DEL_DEVICE_FROM_ACTION. */
	private static final String DEL_DEVICE_FROM_ACTION = ACTION_MAP + "deleteDeviceFromAction";

	/** The Constant DEL_GROUP_FROM_ACTION. */
	private static final String DEL_GROUP_FROM_ACTION = ACTION_MAP + "deleteGroupFromAction";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID = ACTION_MAP + "fetchActionById";

	/** The Constant INSERT_ACTION. */
	private static final String INSERT_ACTION = ACTION_MAP + "insertAction";

	/** The Constant INSERT_DEVICE_TO_ACTION. */
	private static final String INSERT_DEVICE_TO_ACTION = ACTION_MAP + "insertDeviceToAction";

	/** The Constant INSERT_GROUP_TO_ACTION. */
	private static final String INSERT_GROUP_TO_ACTION = ACTION_MAP + "insertGroupToAction";

	/** The Constant INSERT_DEVICE_OPTOUT_LIST. */
	private static final String INSERT_DEVICE_OPTOUT_LIST = ACTION_MAP
			+ "insertDeviceOptOutList";

	/** The Constant DELETE_DEVICE_OPTOUT_LIST. */
	private static final String DELETE_DEVICE_OPTOUT_LIST = ACTION_MAP
			+ "deleteDeviceOptOutList";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.dac.IActionDAC#insertAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResultsResponse<DMAction> insertAction(ActionRequest actionRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(ACTION_TYPE_ID, actionRequest.getAction().getActionType().getActionTypeEnum().getActionType());
		paramMap.put(CREATE_USER, actionRequest.getUserContext().getUserId());

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_ACTION, paramMap);

		InternalResultsResponse<DMAction> response = new InternalResultsResponse<DMAction>();

		actionRequest.getAction().setId(DMConvertUtil.checkResult(response, paramMap, PARAM_ACTION_INSTANCE_ID));

		response.addResult(actionRequest.getAction());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertGroupsToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertGroupsToAction(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);

		for (Group group : actionRequest.getAction().getGroups())
		{
			paramMap.clear();
			paramMap.put(GROUP_ID, group.getId());
			paramMap.put(ACTION_ID, actionRequest.getAction().getId());
			paramMap.put(CREATE_USER, actionRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_GROUP_TO_ACTION, paramMap);

			DMConvertUtil.checkResult(
					response, paramMap, ADD_GROUP_TO_ACTION_FAILED, new Object[] {group.getId(),
							actionRequest.getAction().getId()});

			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertTagsToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertTagsToAction(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);

		for (Tag tag : actionRequest.getAction().getTags())
		{
			paramMap.clear();
			paramMap.put(GROUP_ID, tag.getId());
			paramMap.put(ACTION_ID, actionRequest.getAction().getId());
			paramMap.put(CREATE_USER, actionRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_GROUP_TO_ACTION, paramMap);

			DMConvertUtil.checkResult(
					response, paramMap, ADD_TAG_TO_ACTION_FAILED, new Object[] {tag.getId(),
							actionRequest.getAction().getId()});

			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertDevicesToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertDevicesToAction(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Device device : actionRequest.getAction().getDevices())
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
			paramMap.put(ACTION_ID, actionRequest.getAction().getId());
			paramMap.put(DEVICE_ID, device.getRadio().getFlexNetId());
			paramMap.put(CREATE_USER, actionRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_DEVICE_TO_ACTION, paramMap);

			DMConvertUtil.checkResult(response, paramMap, null);
			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertDevicesOptOutList(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Device device : actionRequest.getAction().getDevices())
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
			paramMap.put(ACTION_ID, actionRequest.getAction().getId());
			paramMap.put(DEVICE_ID, device.getRadio().getFlexNetId());
			paramMap.put(CREATE_USER, actionRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_DEVICE_OPTOUT_LIST, paramMap);

			DMConvertUtil.checkResult(response, paramMap, null);
			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#deleteDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDevicesOptOutList(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(ACTION_ID, actionRequest.getAction().getId());

		if (!ValidationUtil.isNull(actionRequest.getAction().getFirstDevice()))
		{
			paramMap.put(DEVICE_ID, actionRequest.getAction().getFirstDevice().getRadio().getFlexNetId());
		}

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_DEVICE_OPTOUT_LIST, paramMap);

		DMConvertUtil.checkResult(response, paramMap, DEL_DEVICE_FROM_ACTION_FAILED, null);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#deleteGroupsFromAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse deleteGroupsFromAction(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(ACTION_ID, actionRequest.getAction().getId());

		// deleting all groups related to this action in one shot
		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DEL_GROUP_FROM_ACTION, paramMap);

		DMConvertUtil.checkResult(response, paramMap, DEL_GROUP_FROM_ACTION_FAILED, null);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#deleteTagsFromAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse deleteTagsFromAction(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(ACTION_ID, actionRequest.getAction().getId());

		// deleting all tags related to this action in one shot
		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DEL_GROUP_FROM_ACTION, paramMap);

		DMConvertUtil.checkResult(response, paramMap, DEL_TAG_FROM_ACTION_FAILED, null);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.dac.IActionDAC#deleteDevicesFromAction(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse deleteDevicesFromAction(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(ACTION_ID, actionRequest.getAction().getId());

		// deleting all devices related to this action in one shot
		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DEL_DEVICE_FROM_ACTION, paramMap);

		DMConvertUtil.checkResult(response, paramMap, DEL_DEVICE_FROM_ACTION_FAILED, null);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#fetchActionById(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMAction> fetchActionById(ActionRequest actionRequest)
	{
		return SensusMyBatisDacHelper.doQueryForList(
				getSqlSession(), FETCH_BY_ID, actionRequest.getAction(),
				new InternalResultsResponse<DMAction>());
	}
}
