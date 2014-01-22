/*
 *
 */
package com.sensus.lc.tag.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.ADD_LIGHT_TO_TAG;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.DEL_LIGHT_FROM_TAG;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.TAG_LIST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.bcf.ITagBCF;
import com.sensus.lc.tag.bcl.ITagBCL;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.TagOrderByEnum;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.tag.model.response.InquiryTagResponse;
import com.sensus.lc.tag.model.response.TagResponse;

/**
 * The Class TagBCFImpl.
 *
 * @author - Gustavo Aragao - QAT Brazil
 */
public class TagBCFImpl extends AbstractBaseBCF implements ITagBCF
{
	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_CRITERIA_NAME. */
	private static final String LIGHT_CRITERIA_NAME = LightCriteria.class.getSimpleName();

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

	/** The light criteria validation controller. */
	private ValidationController lightCriteriaValidationController;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

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
	 * Gets the tag list validation controller.
	 *
	 * @return the tag list validation controller
	 */
	public ValidationController getTagListValidationController()
	{
		return tagListValidationController;
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
		return tagRequestValidationController;
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
		return lightValidationController;
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
		return lightListValidationController;
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

	/**
	 * Gets the light criteria validation controller.
	 *
	 * @return the light criteria validation controller
	 */
	public ValidationController getLightCriteriaValidationController()
	{
		return lightCriteriaValidationController;
	}

	/**
	 * Sets the light criteria validation controller.
	 *
	 * @param lightCriteriaValidationController the new light criteria validation controller
	 */
	public void setLightCriteriaValidationController(ValidationController lightCriteriaValidationController)
	{
		this.lightCriteriaValidationController = lightCriteriaValidationController;
	}

	/**
	 * Gets the request validation controller.
	 *
	 * @return the request validation controller
	 */
	@Override
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/**
	 * Sets the request validation controller.
	 *
	 * @param requestValidationController the new request validation controller
	 */
	@Override
	public void setRequestValidationController(ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

	/**
	 * Gets the inquiry request validation controller.
	 *
	 * @return the inquiry request validation controller
	 */
	@Override
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Sets the inquiry request validation controller.
	 *
	 * @param inquiryRequestValidationController the new inquiry request validation controller
	 */
	@Override
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
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

			checkDefaultsFetchAll(inquiryTagRequest);
			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
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
				internalResponse = getTagBCL().insertTag(tagRequest);
			}
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchTagsByLight(com.sensus.mlc.light.model.request.LightRequest)
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
			context.putObjectToBeValidated(LIGHT_CRITERIA_NAME, lightRequest.getLightCriteria());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightCriteriaValidationController().validate(context))
			{
				internalResponse = getTagBCL().fetchTagsByLight(lightRequest);
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
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#insertLightToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertLightToTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), ADD_LIGHT_TO_TAG);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_LIST.getValue(), tagRequest.getTags());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getTagListValidationController().validate(context))
			{
				internalResponse = getTagBCL().insertLightToTag(tagRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#deleteLightFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteLightFromTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DEL_LIGHT_FROM_TAG);
			context.putObjectToBeValidated(TAG_REQUEST_NAME, tagRequest);
			context.putObjectToBeValidated(TAG_LIST.getValue(), tagRequest.getTags());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getTagListValidationController().validate(context))
			{
				internalResponse = getTagBCL().deleteLightFromTag(tagRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TAG_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Check defaults fetch all.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 */
	private void checkDefaultsFetchAll(InquiryPaginationRequest inquiryPaginationRequest)
	{
		if(ValidationUtil.isNull(inquiryPaginationRequest.getPageSize())
				|| inquiryPaginationRequest.getPageSize().equals(TWENTY))
		{
			inquiryPaginationRequest.setPageSize(new Integer(0));
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryPaginationRequest.getSortExpressions()))
		{
			return;
		}

		SortExpression sortExpression = new SortExpression();
		sortExpression.setField(TagOrderByEnum.NAME_COLUMN.getValue());
		sortExpression.setDirection(Direction.Ascending);
		inquiryPaginationRequest.addSortExpressions(sortExpression);
	}
}
