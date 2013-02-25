package com.sensus.mlc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.request.LightingControlInquiryRequest;

/**
 * The Class InquiryPaginationRequestValidator.
 */
public class LightingControlInquiryRequestValidator implements IValidator
{
	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = ",";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

	/** The page size list. */
	private String pageSizeList;

	/**
	 * Gets the page size list.
	 * 
	 * @return the page size list
	 */
	public String getPageSizeList()
	{
		return this.pageSizeList;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@Override
	public void validate(ValidationContext context)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		List<MessageInfo> list = context.getMessages();
		if (isNull(context.getObjectsToBeValidated()) || isNull(action) || !isNullOrEmpty(list))
		{
			return;
		}

		Set<String> keys = context.getObjectsToBeValidated().keySet();
		for (String key : keys)
		{
			Object target = context.getObjectToBeValidated(key);

			if (isNull(target) || !LightingControlInquiryRequest.class.isAssignableFrom(target.getClass()))
			{
				continue;
			}

			// Cast the object so it's easy to work with.
			LightingControlInquiryRequest request = (LightingControlInquiryRequest)target;

			switch (action)
			{
				case FETCH:
				case FETCH_ANALYTICS_BY_GROUP:

					validatePageSizeList(list, request);
					break;

				default:
					break;
			}
			treatSortExpressions(request);
		}

	}

	/**
	 * Validate page size list.
	 * 
	 * @param list the list
	 * @param request the request
	 */
	private void validatePageSizeList(List<MessageInfo> list, LightingControlInquiryRequest request)
	{
		if (ValidationUtil.isNullOrZero(request.getPageSize()))
		{
			return;
		}

		String[] pageList = StringUtils.split(getPageSizeList(), SEPARATOR);

		boolean exists = false;
		for (int i = 0; i < pageList.length; i++)
		{
			if (pageList[i].equals(String.valueOf(request.getPageSize())))
			{
				exists = true;
			}
		}

		if (!exists)
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED));
		}
	}

	/**
	 * Treat sort expressions.
	 * 
	 * @param request the request
	 */
	private void treatSortExpressions(LightingControlInquiryRequest request)
	{
		List<SortExpression> sortExpressions = request.getSortExpressions();
		if (ValidationUtil.isNullOrEmpty(sortExpressions))
		{
			return;
		}

		StringBuilder sb = new StringBuilder();

		Collections.sort(sortExpressions, new LightingControlInquiryComparator());

		for (SortExpression exp : sortExpressions)
		{
			sb.append(", ").append(String.valueOf(exp));
		}

		if (sb.length() == 0)
		{
			return;
		}

		request.setSortExpression(StringUtils.substringAfter(sb.toString(), SEPARATOR));
	}
}

class LightingControlInquiryComparator implements Comparator<SortExpression>
{
	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(SortExpression exp1, SortExpression exp2)
	{
		if ((exp1.getPriority() != null) && (exp2.getPriority() != null))
		{
			return exp1.getPriority().compareTo(exp2.getPriority());
		}

		if ((exp1.getPriority() == null) && (exp2.getPriority() == null))
		{
			return 0;
		}

		if (exp1.getPriority() == null)
		{
			return 1;
		}
		return -1;
	}
}