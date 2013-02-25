package com.sensus.mlc.mlcserver.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.APPLY_DIMMING_CONFIGURATION;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_ALARM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_APPLY_SCHEDULE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_APPLY_SMARTPOINT_PROPERTIES;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_BINDING;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_CHANNEL_SETUP_AUDIT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_CLEAR_ALARMS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_CLEAR_SCHEDULE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_FORCED_STATUS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_SETUP;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_SET_LIGHT_INTENSITY;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_UNSOLICITED_STATUS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_STATUS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.mlcserver.bcf.IMlcServerBCF;
import com.sensus.mlc.mlcserver.bcl.IMlcServerBCL;
import com.sensus.mlc.mlcserver.model.request.MlcServerRequest;
import com.sensus.mlc.smartpoint.model.PropertyEnum;

/**
 * The Class MlcServerBCFImpl.
 *
 * author Alex Barros - QAT.
 */
public class MlcServerBCFImpl extends AbstractBaseBCF implements IMlcServerBCF
{
	/** The Constant SMARTPOINT. */
	private static final String SMARTPOINT = "smartpoint";

	/** The Constant TRANSACTION_ID. */
	private static final String TRANSACTION_ID = "transactionId";

	/** The Constant CUSTUMER_ID. */
	private static final String CUSTUMER_ID = "custumerId";

	/** The Constant LIGHT_RNI_ID. */
	private static final String LIGHT_RNI_ID = "lightRniId";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.mlcserverbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MlcServerBCFImpl.class);


	/** The mlc server bcl. */
	private IMlcServerBCL mlcServerBCL; // Injected by Spring through setter.

	/** The validation controller. */
	private ValidationController mlcServerValidationController;

	/** The tenant light association validation controller. */
	private ValidationController tenantLightAssociationValidationController;

	/** The mlc gateway supervisory notification validation controller. */
	private ValidationController mlcGatewaySupervisoryNotificationValidationController;

	/** The mlc server smartpoint list validation controller. */
	private ValidationController mlcServerSmartpointListValidationController;

	/**
	 * Gets the tenant light association validation controller.
	 *
	 * @return the tenant light association validation controller
	 */
	public ValidationController getTenantLightAssociationValidationController()
	{
		return this.tenantLightAssociationValidationController;
	}

	/**
	 * Sets the tenant light association validation controller.
	 *
	 * @param tenantLightAssociationValidationController the new tenant light association validation controller
	 */
	public void setTenantLightAssociationValidationController(
			ValidationController tenantLightAssociationValidationController)
	{
		this.tenantLightAssociationValidationController = tenantLightAssociationValidationController;
	}

	/**
	 * Gets the mlc gateway supervisory notification validation controller.
	 *
	 * @return the mlc gateway supervisory notification validation controller
	 */
	public ValidationController getMlcGatewaySupervisoryNotificationValidationController()
	{
		return this.mlcGatewaySupervisoryNotificationValidationController;
	}

	/**
	 * Sets the mlc gateway supervisory notification validation controller.
	 *
	 * @param mlcGatewaySupervisoryNotificationValidationController the new mlc gateway supervisory notification
	 *            validation controller
	 */
	public void setMlcGatewaySupervisoryNotificationValidationController(
			ValidationController mlcGatewaySupervisoryNotificationValidationController)
	{
		this.mlcGatewaySupervisoryNotificationValidationController =
				mlcGatewaySupervisoryNotificationValidationController;
	}

	/**
	 * Gets the validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getMlcServerValidationController()
	{
		return this.mlcServerValidationController;
	}

	/**
	 * Sets the validation controller.
	 *
	 * @param mlcServerValidationController the new mlc server validation controller
	 */
	public void setMlcServerValidationController(ValidationController mlcServerValidationController)
	{
		this.mlcServerValidationController = mlcServerValidationController;
	}

	/**
	 * Gets the mlc server bcl.
	 *
	 * @return the mlc server bcl
	 */
	public IMlcServerBCL getMlcServerBCL()
	{
		return this.mlcServerBCL;
	}

	/**
	 * Sets the mlc server bcl.
	 *
	 * @param mlcServerBCL the new mlc server bcl
	 */
	public void setMlcServerBCL(IMlcServerBCL mlcServerBCL)
	{
		this.mlcServerBCL = mlcServerBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processChannelSetupAuditNotification(com.sensus.mlc.mlcserver.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processChannelSetupAuditNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processLightStatusNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processLightBindingNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processLightBindingNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;


		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processLightSetupNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processLightSetupNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processAlarmWarningNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processAlarmWarningNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processReadLightStatusNotification(com.sensus.mlc.mlcserver.model.
	 * request.MlcServerRequest)
	 */
	@Override
	public Response processReadLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplyLightLevelNotification(com.sensus.mlc.mlcserver.model.
	 * request.MlcServerRequest)
	 */
	@Override
	public Response processApplyLightLevelNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;


		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processClearAlarmsNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processClearAlarmsNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processClearScheduleNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processClearScheduleNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;


		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplyScheduleNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processApplyScheduleNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplySmartpointPropertiesNotification(com.sensus.mlc.mlcserver
	 * .model.request.MlcServerRequest)
	 */
	@Override
	public Response processApplySmartpointPropertiesNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplyDimmingConfiguration(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processApplyDimmingConfiguration(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processUpdateLightStatusNotification(com.sensus.mlc.mlcserver.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processUpdateLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;



		return response;
	}

	/**
	 * Gets the mlc server smartpoint list validation controller.
	 *
	 * @return the mlc server smartpoint list validation controller
	 */
	public ValidationController getMlcServerSmartpointListValidationController()
	{
		return this.mlcServerSmartpointListValidationController;
	}

	/**
	 * Sets the mlc server smartpoint list validation controller.
	 *
	 * @param mlcServerSmartpointListValidationController the new mlc server smartpoint list validation controller
	 */
	public void setMlcServerSmartpointListValidationController(
			ValidationController mlcServerSmartpointListValidationController)
	{
		this.mlcServerSmartpointListValidationController = mlcServerSmartpointListValidationController;
	}
}
