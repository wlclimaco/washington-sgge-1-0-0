package com.sensus.dm.elec.action.dac;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.dac.mybatis.ActionDACImpl;
import com.sensus.dm.elec.action.model.request.ActionRequest;

/**
 * The Class ActionDACImplTest.
 */

public class ActionDACImplTest extends AbstractTestBaseDAC
{
	/** The Constant EPM_USER_CONTEXT. */
	private static final UserContext EPM_USER_CONTEXT;

	/** The Constant EPM_USER_CONTEXT_LIST. */
	private static final List<UserContext> EPM_USER_CONTEXT_LIST;

	/** The Constant TAG. */
	private static final String TAG = "Tag";

	/** The Constant GROUP. */
	private static final String GROUP = "group";

	static
	{
		EPM_USER_CONTEXT = TestBaseUtil.createUserContext();

		EPM_USER_CONTEXT_LIST = new ArrayList<UserContext>();
		EPM_USER_CONTEXT_LIST.add(EPM_USER_CONTEXT);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The action dac. */
	private IActionDAC actionDAC;

	/**
	 * Gets the action dac.
	 * 
	 * @return the action dac
	 */
	@Override
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
	@Override
	@Resource
	public void setActionDAC(IActionDAC iActionDACParam)
	{
		actionDAC = iActionDACParam;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test delete device from action.
	 */
	@Test
	public void testDeleteDevicesFromAction()
	{
		// first insert an action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea = insertAction(ea);

		ea.addDevice(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER));
		ea.getFirstDevice().setRadio(new Radio(new BigInteger(FLEXNET_ID)));

		// insert the device that we will delete
		InternalResponse insertResponse = getActionDAC().insertDevicesToAction(new ActionRequest(ea, EPM_USER_CONTEXT));

		TestBaseUtil.assertResponse(insertResponse);

		// now run the delete device from action
		insertResponse =
				getActionDAC().deleteDevicesFromAction(new ActionRequest(ea, EPM_USER_CONTEXT));

		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test delete group from action.
	 */
	@Test
	public void testDeleteGroupFromAction()
	{
		// first insert an action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea = insertAction(ea);

		// first insert the group to the action in the db
		ea.addGroup(insertGroup(new Group(GROUP, GROUP, GroupTypeEnum.BILLING)));

		// check if it was successfully inserted
		ActionRequest actionRequest = new ActionRequest(ea, EPM_USER_CONTEXT);
		InternalResponse insertActionResponse = getActionDAC().insertGroupsToAction(actionRequest);

		TestBaseUtil.assertResponse(insertActionResponse);

		// now delete the device we just inserted...should be possible if was
		// inserted
		insertActionResponse = getActionDAC().deleteGroupsFromAction(actionRequest);

		TestBaseUtil.assertResponse(insertActionResponse);
	}

	/**
	 * Test delete tags from action.
	 */
	@Test
	public void testDeleteTagsFromAction()
	{
		// first insert an action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea = insertAction(ea);

		// first insert the tag to the action in the db
		ea.addTag(insertTag(new Tag(TAG)));

		// check if it was successfully inserted
		ActionRequest actionRequest = new ActionRequest(ea, EPM_USER_CONTEXT);
		InternalResponse insertActionResponse = ((ActionDACImpl)getActionDAC())
				.insertTagsToAction(actionRequest);

		TestBaseUtil.assertResponse(insertActionResponse);

		// now delete the device we just inserted...should be possible if was
		// inserted
		insertActionResponse = getActionDAC()
				.deleteTagsFromAction(actionRequest);

		TestBaseUtil.assertResponse(insertActionResponse);
	}

	/**
	 * Test insert device to action.
	 */
	@Test
	public void testInsertDevicesToAction()
	{
		// first insert an action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea = insertAction(ea);

		ea.addDevice(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER));
		ea.getFirstDevice().setRadio(new Radio(new BigInteger(FLEXNET_ID)));
		// now insert the device to the action
		ActionRequest actionRequest = new ActionRequest(ea, EPM_USER_CONTEXT);
		InternalResponse insertResponse = getActionDAC().insertDevicesToAction(actionRequest);

		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test insert group to action.
	 */
	@Test
	public void testInsertGroupsToAction()
	{
		// first insert an action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea = insertAction(ea);

		// first insert the group to the action in the db
		ea.addGroup(insertGroup(new Group(GROUP, GROUP, GroupTypeEnum.BILLING)));

		// now insert the group to the action
		ActionRequest actionRequest = new ActionRequest(ea, EPM_USER_CONTEXT);
		InternalResponse insertActionResponse = ((ActionDACImpl)getActionDAC())
				.insertGroupsToAction(actionRequest);

		TestBaseUtil.assertResponse(insertActionResponse);
	}

	/**
	 * Test insert tags to action.
	 */
	@Test
	public void testInsertTagsToAction()
	{
		// first insert an action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea = insertAction(ea);

		// first insert the tag to the action in the db
		ea.addTag(insertTag(new Tag(TAG)));

		// check if it was successfully inserted
		ActionRequest actionRequest = new ActionRequest(ea, EPM_USER_CONTEXT);
		InternalResponse insertActionResponse = ((ActionDACImpl)getActionDAC())
				.insertTagsToAction(actionRequest);

		TestBaseUtil.assertResponse(insertActionResponse);

		// check if it was successfully inserted
		insertActionResponse = ((ActionDACImpl)getActionDAC())
				.insertTagsToAction(actionRequest);

		TestBaseUtil.assertResponse(insertActionResponse);
	}

	/**
	 * Test insert action.
	 */
	@Test
	public void testInsertAction()
	{
		// Success
		// insert a new action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));

		InternalResultsResponse<DMAction> response =
				getActionDAC().insertAction(new ActionRequest(ea, EPM_USER_CONTEXT));

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch action by id.
	 */
	@Test
	public void testFetchActionById()
	{
		// first insert an action
		DMAction ea = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		ea = insertAction(ea);

		// now fetch the action with that ID and let`s make sure it is found
		InternalResultsResponse<DMAction> actionResponse = getActionDAC()
				.fetchActionById(new ActionRequest(ea, EPM_USER_CONTEXT));
		TestBaseUtil.assertResultResponse(actionResponse);
	}

	/**
	 * Test insert devices opt out list.
	 */
	@Test
	public void testInsertDevicesOptOutList()
	{
		// first insert an action
		DMAction action = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		action = insertAction(action);
		action.addDevice(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER, new BigInteger(FLEXNET_ID)));

		// now insert the device to the action
		ActionRequest actionRequest = new ActionRequest(action, EPM_USER_CONTEXT);
		InternalResponse insertResponse = getActionDAC().insertDevicesOptOutList(actionRequest);

		TestBaseUtil.assertResponse(insertResponse);
	}

	/**
	 * Test delete devices opt out list.
	 */
	@Test
	public void testDeleteDevicesOptOutList()
	{
		// With Device Test
		// first insert an action
		DMAction action = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		action = insertAction(action);
		action.addDevice(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER, new BigInteger(FLEXNET_ID)));

		// insert the device that we will delete
		InternalResponse insertResponse =
				getActionDAC().insertDevicesToAction(new ActionRequest(action, EPM_USER_CONTEXT));

		TestBaseUtil.assertResponse(insertResponse);

		// now run the delete device from Opt-Out List
		insertResponse =
				getActionDAC().deleteDevicesOptOutList(new ActionRequest(action, EPM_USER_CONTEXT));

		TestBaseUtil.assertResponse(insertResponse);

		// Without Device Test
		// insert the device that we will delete
		insertResponse = getActionDAC().insertDevicesToAction(new ActionRequest(action, EPM_USER_CONTEXT));
		TestBaseUtil.assertResponse(insertResponse);

		action = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
		action = insertAction(action);

		// now run the delete all device from Opt-Out List
		insertResponse =
				getActionDAC().deleteDevicesOptOutList(new ActionRequest(action, EPM_USER_CONTEXT));

		TestBaseUtil.assertResponse(insertResponse);
	}

	// -------------------------------------------------------------------------
}
