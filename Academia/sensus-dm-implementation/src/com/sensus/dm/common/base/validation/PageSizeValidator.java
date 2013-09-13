package com.sensus.dm.common.base.validation;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;

/**
 * The Class PageSizeValidator.
 * 
 * @author QAT Global
 */
public class PageSizeValidator implements IValidator
{

	/** The Constant PAGE_SIZE_INVALID. */
	private static final String PAGE_SIZE_INVALID = "sensus.epm.pagesizevalidator.page.size.invalid";

	/** The Constant START_ROW_INVALID. */
	private static final String START_ROW_INVALID = "sensus.epm.pagesizevalidator.start.row.invalid";

	/** The allowed page size. */
	private String allowedPageSize;

	/**
	 * Gets the allowed page size.
	 * 
	 * @return the allowed page size
	 */
	public String getAllowedPageSize()
	{
		return allowedPageSize;
	}

	/**
	 * Sets the allowed page size.
	 * 
	 * @param allowedPageSize the new allowed page size
	 */
	public void setAllowedPageSize(String allowedPageSize)
	{
		this.allowedPageSize = allowedPageSize;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DeviceManagerInquiryRequest request =
				(DeviceManagerInquiryRequest)validationContext
						.getObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName());

		if (ValidationUtil.isNull(request) || ValidationUtil.isNull(request.getPageSize()))
		{
			return;
		}

		ValidationUtil.isNull(request.getStartRow(), START_ROW_INVALID, validationContext.getMessages());

		List<String> pageSizeList = Arrays.asList(StringUtils.split(getAllowedPageSize(), ","));
		if (!pageSizeList.contains(String.valueOf(request.getPageSize())))
		{
			validationContext.getMessages().add(
					MessageInfo.createFieldValidationError(PAGE_SIZE_INVALID, getAllowedPageSize()));
		}
	}

}
