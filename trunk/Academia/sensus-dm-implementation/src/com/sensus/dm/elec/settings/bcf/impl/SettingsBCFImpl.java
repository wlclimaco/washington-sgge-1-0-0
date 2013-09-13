package com.sensus.dm.elec.settings.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.settings.bcf.ISettingsBCF;
import com.sensus.dm.elec.settings.bcl.ISettingsBCL;
import com.sensus.dm.elec.settings.model.response.InquiryUserResponse;

/**
 * The Class SettingsBCFimpl.
 * 
 * @author QAT Brazil.
 */
public class SettingsBCFImpl implements ISettingsBCF
{

	/** The Constant DEFAULT_SETTINGS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_SETTINGS_BCF_EXCEPTION_MSG = "sensus.dm.elec.settingsbcfimpl.defaultexception";

	/** The Constant DEFAULT_SETTINGS_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_SETTINGS_BCL_EXCEPTION_MSG = "sensus.dm.elec.settingsbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SettingsBCFImpl.class);

	/** The settings bcl. */
	private ISettingsBCL settingsBCL; // injected by Spring through setter

	/** The property validation controller. */
	private ValidationController propertyValidationController; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/**
	 * Sets the settings bcl.
	 * 
	 * @param settingsBCL the new settings bcl
	 */
	public void setSettingsBCL(ISettingsBCL settingsBCL)
	{
		this.settingsBCL = settingsBCL;
	}

	/**
	 * Gets the settings bcl.
	 * 
	 * @return the settings bcl
	 */
	public ISettingsBCL getSettingsBCL()
	{
		return settingsBCL;
	}

	/**
	 * Gets the property validation controller.
	 * 
	 * @return the property validation controller
	 */
	public ValidationController getPropertyValidationController()
	{
		return propertyValidationController;
	}

	/**
	 * Sets the property validation controller.
	 * 
	 * @param propertyValidationController the new property validation controller
	 */
	public void setPropertyValidationController(ValidationController propertyValidationController)
	{
		this.propertyValidationController = propertyValidationController;
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#fetchUserSettings(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@Override
	public PropertyResponse fetchUserSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();
		InternalResultsResponse<Property> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_USER_SETTINGS);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), propertyRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				// Persist
				internalResponse = getSettingsBCL().fetchUserSettings(propertyRequest);
				response.setProperties(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#fetchSystemSettings(com.sensus.dm.common.property.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public PropertyResponse fetchSystemSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();
		try
		{
			InternalResultsResponse<Property> internalResponse =
					getSettingsBCL().fetchSystemSettings(propertyRequest);
			response.setProperties(internalResponse.getResultsList());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
					DEFAULT_SETTINGS_BCL_EXCEPTION_MSG);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#fetchAllUsers(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@Override
	public InquiryUserResponse fetchAllUsers(PropertyRequest propertyRequest)
	{
		InquiryUserResponse response = new InquiryUserResponse();
		try
		{
			InternalResultsResponse<UserContext> internalResponse =
					getSettingsBCL().fetchAllUsers(propertyRequest);
			response.setUsers(internalResponse.getResultsList());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
					DEFAULT_SETTINGS_BCL_EXCEPTION_MSG);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#upsertSystemSettings(com.sensus.dm.elec.settings.model.request.
	 * SettingsRequest
	 * )
	 */
	@Override
	public PropertyResponse upsertSystemSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPSERT_SETTINGS);

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), propertyRequest);
			context.putObjectToBeValidated(Property.class.getSimpleName(), propertyRequest.getProperties());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.PROPERTY_PROVIDER_TYPE.getValue(),
					propertyRequest.getPropertyProviderType());

			if (getTenantRequestValidationController().validate(context)
					&& getPropertyValidationController().validate(context))
			{
				// Persist
				internalResponse = getSettingsBCL().upsertSystemSettings(propertyRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#upsertUserSettings(com.sensus.dm.elec.settings.model.request.
	 * SettingsRequest)
	 */
	@Override
	public PropertyResponse upsertUserSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPSERT_SETTINGS);

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), propertyRequest);
			context.putObjectToBeValidated(Property.class.getSimpleName(), propertyRequest.getProperties());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.PROPERTY_PROVIDER_TYPE.getValue(),
					propertyRequest.getPropertyProviderType());

			if (getTenantRequestValidationController().validate(context)
					&& getPropertyValidationController().validate(context))
			{
				internalResponse = getSettingsBCL().upsertUserSettings(propertyRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#insertUser(com.sensus.dm.common.property.model.request.PropertyRequest
	 * )
	 */
	@Override
	public PropertyResponse insertUser(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_USER);

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), propertyRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				// Persist
				internalResponse = getSettingsBCL().insertUser(propertyRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}