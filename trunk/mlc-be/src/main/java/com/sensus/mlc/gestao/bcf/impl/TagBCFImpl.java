/*
 *
 */
package com.sensus.mlc.gestao.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_TAG;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_TAG;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.TAG_LIST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.bcl.ITagBCL;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.tag.model.response.TagResponse;

/**
 * The Class TagBCFImpl.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class TagBCFImpl extends AbstractBaseBCF implements ITagBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_TAG_REQUEST_NAME. */
	private static final String INQUIRY_TAG_REQUEST_NAME = InquiryTagRequest.class.getSimpleName();

	/** The Constant TAG_REQUEST_NAME. */
	private static final String TAG_REQUEST_NAME = TagRequest.class.getSimpleName();

	/** The Constant TAG_NAME. */
	private static final String TAG_NAME = Tag.class.getSimpleName();

	/** The Constant DEFAULT_TAG_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TAG_BCF_EXCEPTION_MSG = "sensus.mlc.tagbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TagBCFImpl.class);

	/** The tag bcl. */
	private ITagBCL tagBCL;

	/** The tag validation controller. */
	private ValidationController tagValidationController;

	/** The tag list validation controller. */
	private ValidationController tagListValidationController;

	/** The tag request validation controller. */
	private ValidationController tagRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the tag bcl.
	 * 
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return this.tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 * 
	 * @param tagBCLObject the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCLObject)
	{
		this.tagBCL = tagBCLObject;
	}

	/**
	 * Gets the tag validation controller.
	 * 
	 * @return the tag validation controller
	 */
	public ValidationController getTagValidationController()
	{
		return this.tagValidationController;
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
	 * Gets the tag list validation controller.
	 * 
	 * @return the tag list validation controller
	 */
	public ValidationController getTagListValidationController()
	{
		return this.tagListValidationController;
	}

	/**
	 * Sets the tag list validation controller.
	 * 
	 * @param tagListValidationController the new tag list validation controller
	 */
	public void setTagListValidationController(ValidationController tagListValidationController)
	{
		this.tagListValidationController = tagListValidationController;
	}

	/**
	 * Gets the tag request validation controller.
	 * 
	 * @return the tag request validation controller
	 */
	public ValidationController getTagRequestValidationController()
	{
		return this.tagRequestValidationController;
	}

	/**
	 * Sets the tag request validation controller.
	 * 
	 * @param tagRequestValidationController the new tag request validation controller
	 */
	public void setTagRequestValidationController(ValidationController tagRequestValidationController)
	{
		this.tagRequestValidationController = tagRequestValidationController;
	}

	/**
	 * Gets the light validation controller.
	 * 
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return this.lightValidationController;
	}

	/**
	 * Sets the light validation controller.
	 * 
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
	}

	/**
	 * Gets the light list validation controller.
	 * 
	 * @return the light list validation controller
	 */
	public ValidationController getLightListValidationController()
	{
		return this.lightListValidationController;
	}

	/**
	 * Sets the light list validation controller.
	 * 
	 * @param lightListValidationController the new light list validation controller
	 */
	public void setLightListValidationController(ValidationController lightListValidationController)
	{
		this.lightListValidationController = lightListValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchAllTags(com.sensus.mlc.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		InquiryTagResponse response = new InquiryTagResponse();
		InternalResultsResponse<Tag> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_TAG_REQUEST_NAME, inquiryTagRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getTagBCL().fetchAllTags(inquiryTagRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchTagByName(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse fetchTagByName(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResultsResponse<Tag> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_NAME, tagRequest.getTag());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTagValidationController().validate(context))
			{
				internalResponse = getTagBCL().fetchTagByName(tagRequest);
				response.setTags(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#insertTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResultsResponse<Tag> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_NAME, tagRequest.getTag());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTagValidationController().validate(context))
			{
				context.getValidationArguments().put(getSlcActionName(), ADD_SMP_TO_TAG);
				context.putObjectToBeValidated(LIGHT_LIST.getValue(), tagRequest.getTag().getLights());

				if (getLightListValidationController().validate(context))
				{
					internalResponse = getTagBCL().insertTag(tagRequest);
					response.setTags(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchTagsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public TagResponse fetchTagsBySmartPoint(LightRequest lightRequest)
	{
		TagResponse response = new TagResponse();
		InternalResultsResponse<Tag> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_SMARTPOINT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getTagBCL().fetchTagsBySmartPoint(lightRequest);
				response.setTags(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchTagsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public TagResponse fetchTagsByLight(LightRequest lightRequest)
	{
		TagResponse response = new TagResponse();
		InternalResultsResponse<Tag> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_LIGHT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getTagBCL().fetchTagsByLight(lightRequest);
				response.setTags(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#deleteTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_NAME, tagRequest.getTag());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTagValidationController().validate(context))
			{
				internalResponse = getTagBCL().deleteTag(tagRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#updateAutoGroupTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse updateAutoGroupTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResultsResponse<Tag> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					UPDATE);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_NAME, tagRequest.getTag());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTagRequestValidationController().validate(context)
					&& getTagValidationController().validate(context))
			{
				internalResponse = getTagBCL().updateAutoGroupTag(tagRequest);
				response.setTags(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#insertSmartPointToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), ADD_SMP_TO_TAG);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_LIST.getValue(), tagRequest.getTags());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getTagListValidationController().validate(context))
			{
				internalResponse = getTagBCL().insertSmartPointToTag(tagRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#deleteSmartPointFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteSmartPointFromTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DEL_SMP_FROM_TAG);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_NAME, tagRequest.getTag());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getTagValidationController().validate(context))
			{
				internalResponse = getTagBCL().deleteSmartPointFromTag(tagRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}
}
