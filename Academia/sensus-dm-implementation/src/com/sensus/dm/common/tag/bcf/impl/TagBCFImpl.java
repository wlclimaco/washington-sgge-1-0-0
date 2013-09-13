package com.sensus.dm.common.tag.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.bcf.ITagBCF;
import com.sensus.dm.common.tag.bcl.ITagBCL;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.common.tag.model.response.TagResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class TagBCFImpl.
 * 
 * @author QAT Brazil
 */
public class TagBCFImpl implements ITagBCF
{

	/** The Constant DEFAULT_TAG_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TAG_BCF_EXCEPTION_MSG = "sensus.dm.elec.tagbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TagBCFImpl.class);

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController tagValidationController; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The order by validation controller. */
	private ValidationController orderByValidationController; // injected by Spring through setter

	/** The device validation controller. */
	private ValidationController deviceValidationController; // injected by Spring through setter

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController; // injected by Spring through setter

	/** The radio validation controller. */
	private ValidationController radioValidationController; // injected by Spring through setter

	/**
	 * Gets the tag bcl.
	 * 
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 * 
	 * @param tagBCLObject the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCLObject)
	{
		tagBCL = tagBCLObject;
	}

	/**
	 * Gets the tag validation controller.
	 * 
	 * @return the tag validation controller
	 */
	public ValidationController getTagValidationController()
	{
		return tagValidationController;
	}

	/**
	 * Sets the tag validation controller.
	 * 
	 * @param tagValidationController the new tag validation controller
	 */
	public void setTagValidationController(ValidationController tagValidationController)
	{
		this.tagValidationController = tagValidationController;
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

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Sets the inquiry request validation controller.
	 * 
	 * @param inquiryRequestValidationController the new inquiry request validation controller
	 */
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#fetchAllTags(com.sensus.dm.common.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{

		InquiryTagResponse response = new InquiryTagResponse();
		InternalResultsResponse<Tag> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_TAG);

			context.putObjectToBeValidated(SortExpression.class.getSimpleName(), inquiryTagRequest.getSortExpressions());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryTagRequest);
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryTagRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.PAGE_SIZE.getValue(),
					inquiryTagRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context))
			{
				// Persist
				internalResponse = getTagBCL().fetchAllTags(inquiryTagRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public TagResponse fetchTagsByDevice(InquiryTagRequest inquiryTagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResultsResponse<Tag> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_DEVICE);

			context.putObjectToBeValidated(Tag.class.getSimpleName(), inquiryTagRequest.getTag());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryTagRequest);
			Device device = inquiryTagRequest.getTag().getFirstDevice();
			context.putObjectToBeValidated(Device.class.getSimpleName(), device);

			if (getTagValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context)
					&& getDeviceValidationController().validate(context))
			{
				context.putObjectToBeValidated(Radio.class.getSimpleName(), device.getRadio());

				if (radioValidationController.validate(context))
				{
					internalResponse =
							getTagBCL().fetchAllTags(inquiryTagRequest);
					response.setTags(internalResponse.getResultsList());
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#insertTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResultsResponse<Tag> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_TAG);
			context.putObjectToBeValidated(Tag.class.getSimpleName(), tagRequest.getFirstTag());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					tagRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getTagValidationController().validate(context))
			{
				// Persist
				internalResponse = getTagBCL().insertTag(tagRequest);
				response.setTags(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.tag.bcf.ITagBCF#insertDeviceToTag(com.sensus.dm.common.device.model.request.InquiryDeviceRequest
	 * )
	 */
	@Override
	public TagResponse insertDeviceToTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.ADD_SMP_TO_TAG);

			context.putObjectToBeValidated(Tag.class.getSimpleName(), inquiryDeviceRequest.getFirstTag());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryDeviceRequest);

			if (getOrderByValidationController().validate(context) && getTagValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				// Persist
				internalResponse = getTagBCL().insertDeviceToTag(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#deleteTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE);

			context.putObjectToBeValidated(Tag.class.getSimpleName(), tagRequest.getFirstTag());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), tagRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getTagValidationController().validate(context))
			{
				// Persist
				internalResponse = getTagBCL().deleteTag(tagRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.tag.bcf.ITagBCF#deleteDeviceFromTag(com.sensus.dm.common.device.model.request.DeviceRequest)
	 */
	@Override
	public TagResponse deleteDeviceFromTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DEL_SMP_FROM_TAG);

			context.putObjectToBeValidated(Tag.class.getSimpleName(), inquiryDeviceRequest.getFirstTag());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());

			if (getOrderByValidationController().validate(context) && getTagValidationController().validate(context))
			{
				// Persist
				internalResponse = getTagBCL().deleteDeviceFromTag(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * @return the radioValidationController
	 */
	public ValidationController getRadioValidationController()
	{
		return radioValidationController;
	}

	/**
	 * @param radioValidationController the radioValidationController to set
	 */
	public void setRadioValidationController(ValidationController radioValidationController)
	{
		this.radioValidationController = radioValidationController;
	}
}
