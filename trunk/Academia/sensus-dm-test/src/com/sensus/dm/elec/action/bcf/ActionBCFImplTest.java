package com.sensus.dm.elec.action.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;

/**
 * The Class ActionBCFImplTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/elec/action/actionbcfimpltest.xml"})
public class ActionBCFImplTest extends AbstractTestBaseBusiness
{

	/** The Constant EPM_ACTION_HAN_DEVICES_REQUIRED. */
	private static final String EPM_ACTION_HAN_DEVICES_REQUIRED =
			"sensus.epm.actionvalidator.han.devices.required";

	/** The Constant DEFAULT_ACTION_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ACTION_BCF_EXCEPTION_MSG = "sensus.epm.actionbcfimpl.defaultexception";

	/** The Constant SENSUS_EPM_ACTION_IDFILETYPE_REQUIRED. */
	private static final String EPM_ACTION_IDFILETYPE_REQUIRED = "sensus.epm.actionvalidator.id.file.type.required";

	/** The Constant HAN_DEVICES_FILE. */
	private static final String HAN_DEVICES_FILE = "/opt/flexnet-epm/file";

	/** The action bcf. */
	private IActionBCF actionBCF;

	/**
	 * Gets the action bcf.
	 * 
	 * @return the action bcf
	 */
	public IActionBCF getActionBCF()
	{
		return actionBCF;
	}

	/**
	 * Sets the action bcf.
	 * 
	 * @param actionBCF the new action bcf
	 */
	@Resource
	public void setActionBCF(IActionBCF actionBCF)
	{
		this.actionBCF = actionBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:

	/**
	 * Sets the custom search area.
	 */
	public void setActionArea()
	{
		setArea(getActionBCF(), EPMAreaEnum.ACTION);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setActionArea();
	}

	/**
	 * Removes the custom search area.
	 */
	@After
	public void resetMocksToActionArea()
	{
		resetMocksData(getActionBCF());
		setActionArea();
	}

	/**
	 * Test abort action.
	 */
	@Test
	public void testAbortAction()
	{
		// fail userContext required
		ActionRequest ar = new ActionRequest();
		ar.setTenant(TestBaseUtil.createTenant());
		ActionResponse insertResponse = getActionBCF().abortAction(ar);
		assertFalse(insertResponse.isOperationSuccess());
		assertMessages(insertResponse, USER_CONTEXT_REQUIRED);

		// fail locale required
		ar = new ActionRequest(TestBaseUtil.createUserContext(), new Integer(ONE));
		ar.setTenant(TestBaseUtil.createTenant());
		insertResponse = getActionBCF().abortAction(ar);
		assertFalse(insertResponse.isOperationSuccess());
		assertMessages(insertResponse, LOCALE_REQUIRED);

		// Success
		ar = new ActionRequest(TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE), new Integer(ONE));
		ar.setTenant(TestBaseUtil.createTenant());
		insertResponse = getActionBCF().abortAction(ar);
		assertEquals(true, insertResponse.isOperationSuccess());
		assertEquals(ZERO, insertResponse.getMessageInfoList().size());

	}

	/**
	 * <<<<<<< .mine
	 * =======
	 * Test apply device to action.
	 * 
	 */
	@Test
	public void testApplyActionOnDemand()
	{
		ActionRequest inquiryAction = new ActionRequest();
		inquiryAction.setTenant(TestBaseUtil.createTenant());
		// 3 Fail - action required and userContext required
		ActionResponse insertResponse = getActionBCF().applyActionOnDemand(inquiryAction);

		assertFalse(insertResponse.isOperationSuccess());
		assertMessages(insertResponse, EPM_ACTION_REQUIRED);

		// 4 fail - action: on_demand, monitored, time and action_type_description
		inquiryAction =
				new ActionRequest(new DMAction(new ActionType(ActionTypeEnum.IMPORT_HAN_DEVICE)),
						TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		inquiryAction.setTenant(TestBaseUtil.createTenant());
		insertResponse = getActionBCF().applyActionOnDemand(inquiryAction);
		assertFalse(insertResponse.isOperationSuccess());
		assertMessages(insertResponse, EPM_ACTION_HAN_DEVICES_REQUIRED);

		// Success
		// insert action instance
		DMAction ea = inquiryAction.getAction();
		ea.setOnDemand(Boolean.TRUE);
		ea.setIsMonitored(Boolean.TRUE);
		ea.setActionTime(new Date());
		ea.setId(new Integer(ONE));
		ea.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, METER_FLEXNET_ID, METER_DEVICE_ID));
		insertResponse = getActionBCF().applyActionOnDemand(inquiryAction);
		assertEquals(true, insertResponse.isOperationSuccess());
		assertEquals(true, insertResponse.getMessageInfoList().size() == 0);

		// // Success
		// // insert action instance
		// DMAction ea = inquiryAction.getAction();
		// ea.setActionType(new ActionType(ActionTypeEnum.RETRY_IMPORT_HAN_DEVICE));
		// ea.setOnDemand(Boolean.TRUE);
		// ea.setIsMonitored(Boolean.TRUE);
		// ea.setActionTime(new Date());
		// ea.setId(new Integer(ONE));
		// ea.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, METER_FLEXNET_ID, METER_DEVICE_ID));
		//
		// insertResponse = getActionBCF().applyActionOnDemand(inquiryAction);
		// assertEquals(true, insertResponse.isOperationSuccess());

	}

	/**
	 * >>>>>>> .r172596
	 * Test insert device from file to action.
	 */
	@Test
	public void insertDeviceFromFileToAction()
	{
		// Exception
		ActionResponse insertResponse = getActionBCF().insertDeviceFromFileToAction(null);
		assertFalse(insertResponse.isOperationSuccess());
		assertMessages(insertResponse, DEFAULT_ACTION_BCF_EXCEPTION_MSG);

		// fail id_file_type required
		ActionRequest actionRequest =
				new ActionRequest(new DMAction(ONE), TestBaseUtil.createUserContext());
		actionRequest.setTenant(TestBaseUtil.createTenant());
		insertResponse = getActionBCF().insertDeviceFromFileToAction(actionRequest);
		assertFalse(insertResponse.isOperationSuccess());
		assertMessages(insertResponse, EPM_ACTION_IDFILETYPE_REQUIRED, FILE_NAME_REQUIRED, PROCESS_ID_REQUIRED);

		// fail process_id and file_name required
		actionRequest.setIdFileType(PropertyEnum.FILE_IDS_TYPE);
		insertResponse = getActionBCF().insertDeviceFromFileToAction(actionRequest);
		assertFalse(insertResponse.isOperationSuccess());
		assertMessages(insertResponse, FILE_NAME_REQUIRED, PROCESS_ID_REQUIRED);

		// success test
		// passing all needed values
		actionRequest.setFileName(HAN_DEVICES_FILE);
		actionRequest.setProcessId(new Integer(ONE));
		insertResponse = getActionBCF().insertDeviceFromFileToAction(actionRequest);
		assertEquals(true, insertResponse.isOperationSuccess());

	}

	/**
	 * Test insert device opt out list.
	 */
	@Test
	public void testInsertDeviceOptOutList()
	{
		// Validation with fail situation - user context required
		ActionRequest actionRequest = new ActionRequest();
		ActionResponse actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, USER_CONTEXT_REQUIRED);

		// Validation with fail situation - action required
		actionRequest = new ActionRequest(TestBaseUtil.createUserContext());
		actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, EPM_ACTION_REQUIRED);

		// Validation with fail situation - action id required
		actionRequest =
				new ActionRequest(TestBaseUtil.createAction(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT),
						TestBaseUtil.createUserContext());
		actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, EPM_ACTION_ID_REQUIRED);

		// Validation with fail situation - device required
		actionRequest =
				new ActionRequest(new DMAction(ONE, new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)),
						TestBaseUtil.createUserContext());
		actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, DEVICE_REQUIRED);

		// Success situation
		actionRequest =
				new ActionRequest(new DMAction(ONE, new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT),
						TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER)),
						TestBaseUtil.createUserContext());
		actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		assertTrue(actionResponse.isOperationSuccess());

		resetMocksToActionArea();
		// Error situation
		setSituation(getActionBCF(), SituationsEnum.ERROR, IActionBCL.class);
		actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, ERROR_CODE);

		resetMocksToActionArea();
		// Exception situation
		setSituation(getActionBCF(), SituationsEnum.EXCEPTION, IActionBCL.class);
		actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, DEFAULT_ACTION_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test delete devices opt out list.
	 */
	@Test
	public void testDeleteDevicesOptOutList()
	{
		// Validation with fail situation - action required
		ActionRequest actionRequest = new ActionRequest(TestBaseUtil.createUserContext());
		ActionResponse actionResponse = getActionBCF().deleteDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, EPM_ACTION_REQUIRED);

		// Validation with fail situation - action id required
		actionRequest =
				new ActionRequest(TestBaseUtil.createAction(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT),
						TestBaseUtil.createUserContext());
		actionResponse = getActionBCF().deleteDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, EPM_ACTION_ID_REQUIRED);

		// Success situation
		actionRequest =
				new ActionRequest(new DMAction(ONE, new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT),
						TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER)),
						TestBaseUtil.createUserContext());
		actionResponse = getActionBCF().deleteDevicesOptOutList(actionRequest);
		assertTrue(actionResponse.isOperationSuccess());

		resetMocksToActionArea();

		// Error situation
		setSituation(getActionBCF(), SituationsEnum.ERROR, IActionBCL.class);
		actionResponse = getActionBCF().deleteDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, ERROR_CODE);

		resetMocksToActionArea();

		// Exception situation
		setSituation(getActionBCF(), SituationsEnum.EXCEPTION, IActionBCL.class);
		actionResponse = getActionBCF().deleteDevicesOptOutList(actionRequest);
		assertFalse(actionResponse.isOperationSuccess());
		assertMessages(actionResponse, DEFAULT_ACTION_BCF_EXCEPTION_MSG);
	}
}