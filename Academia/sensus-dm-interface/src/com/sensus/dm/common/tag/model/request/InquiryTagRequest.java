package com.sensus.dm.common.tag.model.request;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryTagRequest.
 * 
 * @author QAT Brazil
 * 
 */
public class InquiryTagRequest extends InquiryPaginationRequest
{
	/** The tag. */
	private Tag tag;

	/** The base search. */
	private BaseSearch baseSearch;

	/**
	 * Instantiates a new inquiry tag request.
	 */
	public InquiryTagRequest()
	{
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryTagRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public InquiryTagRequest(UserContext userContext, DMTenant tenant)
	{
		super(userContext);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param baseSearchParam the base search
	 */
	public InquiryTagRequest(BaseSearch baseSearchParam)
	{
		setBaseSearch(baseSearchParam);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param tagParam the tag
	 */
	public InquiryTagRequest(Tag tagParam)
	{
		setTag(tagParam);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param sortExpression the sort expression
	 */
	public InquiryTagRequest(SortExpression sortExpression)
	{
		addSortExpressions(sortExpression);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param tagParam the tag
	 * @param userContextParam the user context
	 */
	public InquiryTagRequest(Tag tagParam, UserContext userContextParam)
	{
		setTag(tagParam);
		setUserContext(userContextParam);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param tagParam the tag param
	 * @param userContextParam the user context param
	 * @param serviceTypeEnumParam the service type enum param
	 * @param tenant the tenant
	 */
	public InquiryTagRequest(Tag tagParam, UserContext userContextParam, ServiceTypeEnum serviceTypeEnumParam,
			DMTenant tenant)
	{
		setTag(tagParam);
		setUserContext(userContextParam);
		setServiceTypeEnum(serviceTypeEnumParam);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param tagParam the tag
	 * @param userContextParam the user context
	 * @param sortExpressionParam the sort expression
	 */
	public InquiryTagRequest(Tag tagParam, UserContext userContextParam, SortExpression sortExpressionParam)
	{
		setTag(tagParam);
		setUserContext(userContextParam);
		addSortExpressions(sortExpressionParam);
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param tagParam the tag param
	 * @param userContextParam the user context param
	 * @param sortExpressionParam the sort expression param
	 * @param serviceTypeEnumParam the service type enum param
	 * @param tenant the tenant
	 */
	public InquiryTagRequest(Tag tagParam, UserContext userContextParam, SortExpression sortExpressionParam,
			ServiceTypeEnum serviceTypeEnumParam, DMTenant tenant)
	{
		setTag(tagParam);
		setUserContext(userContextParam);
		addSortExpressions(sortExpressionParam);
		setServiceTypeEnum(serviceTypeEnumParam);
		setTenant(tenant);
	}

	/**
	 * Gets the tag.
	 * 
	 * @return the tag
	 */
	public Tag getTag()
	{
		return tag;
	}

	/**
	 * Sets the tag.
	 * 
	 * @param tagObject the new tag
	 */
	public void setTag(Tag tagObject)
	{
		tag = tagObject;
	}

	/**
	 * Gets the base search.
	 * 
	 * @return the base search
	 */
	public BaseSearch getBaseSearch()
	{
		return baseSearch;
	}

	/**
	 * Sets the base search.
	 * 
	 * @param baseSearch the new base search
	 */
	public void setBaseSearch(BaseSearch baseSearch)
	{
		this.baseSearch = baseSearch;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryTagRequest [getTag()=" + getTag() + ", getBaseSearch()=" + getBaseSearch() + ", toString()="
				+ super.toString() + "]";
	}

}
