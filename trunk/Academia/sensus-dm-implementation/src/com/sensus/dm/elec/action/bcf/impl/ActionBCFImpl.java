package com.sensus.dm.elec.action.bcf.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.action.bcf.IActionBCF;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;

/**
 * The Class ActionBCFImpl.
 * 
 * @author QAT Brazil.
 * 
 */
public class ActionBCFImpl implements IActionBCF
{
	/** The Constant DEFAULT_ACTION_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ACTION_BCF_EXCEPTION_MSG = "sensus.epm.actionbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ActionBCFImpl.class);

	/** The action bcl. */
	private IActionBCL actionBCL;

	/** The action validation controller. */
	private ValidationController actionValidationController;

	/** The action request validation controller. */
	private ValidationController actionRequestValidationController;

	/** The order by validation controller. */
	private ValidationController orderByValidationController;

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController;

	/** The inquiry action request validation controller. */
	private ValidationController inquiryActionRequestValidationController;

	/** The device validation controller. */
	private ValidationController deviceValidationController;

	/**
	 * Gets the action bcl.
	 * 
	 * @return the action bcl
	 */
	public IActionBCL getActionBCL()
	{
		return actionBCL;
	}

	/**
	 * Sets the action bcl.
	 * 
	 * @param actionBCL the new action bcl
	 */
	@Resource
	public void setActionBCL(IActionBCL actionBCL)
	{
		this.actionBCL = actionBCL;
	}

	/**
	 * Gets the action validation controller.
	 * 
	 * @return the action validation controller
	 */
	public ValidationController getActionValidationController()
	{
		return actionValidationController;
	}

	/**
	 * Sets the action validation controller.
	 * 
	 * @param actionValidationController the new action validation controller
	 */
	public void setActionValidationController(ValidationController actionValidationController)
	{
		this.actionValidationController = actionValidationController;
	}

	/**
	 * Gets the action request validation controller.
	 * 
	 * @return the action request validation controller
	 */
	public ValidationController getActionRequestValidationController()
	{
		return actionRequestValidationController;
	}

	/**
	 * Sets the action request validation controller.
	 * 
	 * @param actionRequestValidationController the new action request validation controller
	 */
	public void setActionRequestValidationController(ValidationController actionRequestValidationController)
	{
		this.actionRequestValidationController = actionRequestValidationController;
	}

	/**
	 * Gets the order by validation controller.
	 * 
	 * @return the order by validation controller
	 */
	public ValidationController getOrderByValidationController()
	{
		return orderByValidationController;
	}

	/**
	 * Sets the order by validation controller.
	 * 
	 * @param orderByValidationController the new order by validation controller
	 */
	public void setOrderByValidationController(ValidationController orderByValidationController)
	{
		this.orderByValidationController = orderByValidationController;
	}

	/**
	 * Gets the tenant request validation controller.
	 * 
	 * @return the tenant request validation controller
	 */
	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	/**
	 * Sets the tenant request validation controller.
	 * 
	 * @param tenantRequestValidationController the new tenant request validation controller
	 */
	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	/**
	 * Gets the inquiry action request validation controller.
	 * 
	 * @return the inquiry action request validation controller
	 */
	public ValidationController getInquiryActionRequestValidationController()
	{
		return inquiryActionRequestValidationController;
	}

	/**
	 * Sets the inquiry action request validation controller.
	 * 
	 * @param inquiryActionRequestValidationController the new inquiry action request validation controller
	 */
	public void setInquiryActionRequestValidationController(
			ValidationController inquiryActionRequestValidationController)
	{
		this.inquiryActionRequestValidationController = inquiryActionRequestValidationController;
	}

	/**
	 * Gets the device validation controller.
	 * 
	 * @return the device validation controller
	 */
	public ValidationController getDeviceValidationController()
	{
		return deviceValidationController;
	}

	/**
	 * Sets the device validation controller.
	 * 
	 * @param deviceValidationController the new device validation controller
	 */
	public void setDeviceValidationController(ValidationController deviceValidationController)
	{
		this.deviceValidationController = deviceValidationController;
	}

	@Override
	public ActionResponse applyActionOnDemand(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.APPLY_ON_DEMAND);
			context.putObjectToBeValidated(DMAction.class.getSimpleName(), actionRequest.getAction());
			context.putObjectToBeValidated(ActionRequest.class.getSimpleName(), actionRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), actionRequest);

			if (getActionRequestValidationController().validate(context)
					&& getActionValidationController().validate(context))
			{
				internalResponse =
						getActionBCL().applyActionOnDemand(actionRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACTION_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcf.IActionBCF#insertDeviceFromFileToAction(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public ActionResponse insertDeviceFromFileToAction(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.ADD_SM_FROM_FILE);
			context.putObjectToBeValidated(DMAction.class.getSimpleName(), actionRequest.getAction());
			context.putObjectToBeValidated(ActionRequest.class.getSimpleName(), actionRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), actionRequest);

			if (getActionRequestValidationController().validate(context)
					&& getActionValidationController().validate(context))
			{
				internalResponse = getActionBCL().insertDeviceFromFileToAction(actionRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACTION_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcf.IActionBCF#abortAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public ActionResponse abortAction(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.ABORT);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					actionRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getActionBCL().abortAction(actionRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACTION_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcf.IActionBCF#insertDevicesOptOutList(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public ActionResponse insertDevicesOptOutList(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_OPT_OUT_LIST);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					actionRequest);
			context.putObjectToBeValidated(DMAction.class.getSimpleName(), actionRequest.getAction());

			if (getTenantRequestValidationController().validate(context)
					&& getActionValidationController().validate(context))
			{
				context.putObjectToBeValidated(Device.class.getSimpleName(), actionRequest.getAction().getFirstDevice());
				if (getDeviceValidationController().validate(context))
				{
					internalResponse = getActionBCL().insertDevicesOptOutList(actionRequest);
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACTION_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcf.IActionBCF#deleteDevicesOptOutList(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public ActionResponse deleteDevicesOptOutList(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE_OPT_OUT_LIST);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					actionRequest);
			context.putObjectToBeValidated(DMAction.class.getSimpleName(), actionRequest.getAction());

			if (getActionValidationController().validate(context))
			{
				internalResponse = getActionBCL().deleteDevicesOptOutList(actionRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACTION_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}
