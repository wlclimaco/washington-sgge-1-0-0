package com.qat.samples.sysmgmt.util.model.request;

import com.qat.framework.model.request.InquiryRequest;

/**
 * The Model Object PagedInquiryRequest.
 */
public class PagedInquiryRequest extends InquiryRequest
{

	public PagedInquiryRequest()
	{

	}

	@Override
	public String toString()
	{
		return "PagedInquiryRequest [getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage() + ", getSortExpressions()=" + getSortExpressions()
				+ ", getSortExpression()=" + getSortExpression() + ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getUserContext()=" + getRequestContext() + "]";
	}

}
