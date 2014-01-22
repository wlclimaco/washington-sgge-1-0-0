package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCHelp;

public class InquiryRequestValidator implements IValidator
{
	private static final String SORT_EXPRESSION = "Sort expression";

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = ",";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The page size list. */
	private String pageSizeList;

	/**
	 * Gets the page size list.
	 * 
	 * @return the page size list
	 */
	public String getPageSizeList()
	{
		return pageSizeList;
	}

	/**
	 * Sets the page size list.
	 * 
	 * @param pageSizeList the new page size list
	 */
	public void setPageSizeList(String pageSizeList)
	{
		this.pageSizeList = pageSizeList;
	}

	@Override
	public void validate(ValidationContext context)
	{
		if (isNull(context.getObjectsToBeValidated()))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		Set<String> keys = context.getObjectsToBeValidated().keySet();
		for (String key : keys)
		{
			Object target = context.getObjectToBeValidated(key);

			if (isNull(target) || !InquiryRequest.class.isAssignableFrom(target.getClass()))
			{
				continue;
			}

			InquiryRequest request = (InquiryRequest)target;
			validatePageSizeList(list, request);
			validateSortExpressions(list, request);
		}
	}

	/**
	 * Validate page size list.
	 * 
	 * @param list the list
	 * @param request the request
	 */
	private void validatePageSizeList(List<MessageInfo> list, InquiryRequest request)
	{
		if (ValidationUtil.isNullOrZero(request.getPageSize()))
		{
			return;
		}

		List<String> pageList = Arrays.asList(StringUtils.split(getPageSizeList(), SEPARATOR));
		if (!pageList.contains(String.valueOf(request.getPageSize())))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED));
		}
	}

	/**
	 * Validate sort expressions.
	 * 
	 * @param list the list
	 * @param request the request
	 */
	private void validateSortExpressions(List<MessageInfo> list, InquiryRequest request)
	{
		LCHelp.isNullOrEmptyLC(request.getSortExpressions(), SENSUS_MLC_VALIDATOR_REQUIRED, list, SORT_EXPRESSION);
	}
}