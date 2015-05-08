package com.prosperitasglobal.sendsolv.validation;

import java.util.List;
import java.util.Map;

import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.UserContext;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PagedInquiryRequestValidator. Validates the {@link PagedInquiryRequest} for a fetch.
 */
public abstract class PagedInquiryRequestValidator implements IValidator
{
	/** The message property used when when requested sorted map key not available. */
	public static final String INVALID_SORT_FIELD =
			"sendsolv.base.pagedinquiryrequestvalidator.sortfield.invalid";

	/** The message property used when the request is missing. */
	public static final String PAGED_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.pagedinquiryrequestvalidator.pagedinquiryrequest.required";

	/** The message property used when the paging parameters are missing. */
	public static final String PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.pagedinquiryrequestvalidator.paging.parameters.required";

	/**
	 * Get the valid sort fields for the inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the inquiry.
	 */
	public abstract Map<String, String> getInquiryValidSortFields();

	/**
	 * Set the valid sort fields for the inquiry. Attribute injected by Spring.
	 *
	 * @param inquiryValidSortFields The valid sort fields for the inquiry to set.
	 */
	public abstract void setInquiryValidSortFields(Map<String, String> inquiryValidSortFields);

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		PagedInquiryRequest pagedInquiryRequest =
				(PagedInquiryRequest)context.getObjectToBeValidated(PagedInquiryRequest.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(pagedInquiryRequest))
		{
			context.getMessages().add(new MessageInfo(PAGED_INQUIRY_REQUEST_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		performValidation(context, pagedInquiryRequest);
	}

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param pagedInquiryRequest The {@link PagedInquiryRequest} to validate.
	 */
	protected void performValidation(ValidationContext validationContext, PagedInquiryRequest pagedInquiryRequest)
	{
		validatePagingFields(validationContext.getMessages(), pagedInquiryRequest);
		validateSortFields(validationContext.getMessages(), pagedInquiryRequest);

		validationContext.putObjectToBeValidated(UserContext.class.getSimpleName(),
				pagedInquiryRequest.getUserContext());
	}

	/**
	 * Validates the the paging attributes.
	 *
	 * @param messageList The message list.
	 * @param request The request.
	 */
	protected void validatePagingFields(List<MessageInfo> messageList, PagedInquiryRequest request)
	{
		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			messageList.add(new MessageInfo(PAGING_PARAMETERS_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validates the the sort expression is valid.
	 *
	 * @param messageList The message list.
	 * @param request The request.
	 */
	protected void validateSortFields(List<MessageInfo> messageList, PagedInquiryRequest request)
	{
		/*
		 * If sort fields are available, then we must edit them to make sure they are valid. If none, it means
		 * sorting isn't performed.
		 */
		if (!ValidationUtil.isNull(getInquiryValidSortFields()))
		{
			List<SortExpression> badSortExpressions =
					ValidationUtil.validateSortFields(request.getSortExpressions(), getInquiryValidSortFields());

			if (badSortExpressions.size() > 0)
			{
				for (SortExpression badExpression : badSortExpressions)
				{
					messageList
							.add(MessageInfo.createFieldValidationError(INVALID_SORT_FIELD, badExpression.getField()));
				}
			}
		}
	}
}
