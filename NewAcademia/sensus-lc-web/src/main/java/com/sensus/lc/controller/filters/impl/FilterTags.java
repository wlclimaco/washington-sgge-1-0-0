package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.tag.bcf.ITagBCF;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.response.InquiryTagResponse;

/**
 * The Class FilterTags.
 */
@Component
public class FilterTags extends AbstractFilterBase
{
	/** The Constant TAGS. */
	private static final String TAGS = "TAGS";

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tagBCF
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the tagBCF to set
	 */
	@Resource
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return TAGS.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		InquiryTagRequest tagRequest = new InquiryTagRequest(userContext);
		tagRequest.setPageSize(0);
		InquiryTagResponse tagResponse = getTagBCF().fetchAllTags(tagRequest);
		Map<String, Object> records = new HashMap<String, Object>();
		if (!tagResponse.isOperationSuccess() || ValidationUtil.isNullOrEmpty(tagResponse.getTags()))
		{
			filtersResponse.setTags(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
			return;
		}

		for (Tag tag : tagResponse.getTags())
		{
			records.put(tag.getId().toString(), tag.getName());
		}

		filtersResponse.setTags(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
