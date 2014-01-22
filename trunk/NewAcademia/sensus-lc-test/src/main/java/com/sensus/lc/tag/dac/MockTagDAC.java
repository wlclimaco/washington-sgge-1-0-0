package com.sensus.lc.tag.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;

public class MockTagDAC extends AbstractMockBase implements ITagDAC
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#fetchAllTags(com.sensus.lc.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		return testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#fetchTagById(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagById(TagRequest tagRequest)
	{
		return testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#fetchTagByName(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagByName(TagRequest tagRequest)
	{
		// return a response with a Tag Object inflated only if the tagID is not null
		if (!ValidationUtil.isNull(tagRequest.getTag().getId()))
		{
			return new InternalResultsResponse<Tag>();
		}

		return testSituationsTagResultsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#fetchTagsByLight(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsByLight(LightRequest request)
	{
		return testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#fetchTagNameById(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagNameById(TagRequest tagRequest)
	{
		return testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#insertTag(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		return testSituationsTagResultsResponse();
	}


	/* (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#insertLightToTag(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse insertLightToTag(TagRequest tagRequest)
	{
		return testSituationsTagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#updateAutoGroupTag(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> updateAutoGroupTag(TagRequest tagRequest)
	{
		return testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#deleteTag(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> deleteTag(TagRequest tagRequest)
	{
		return testSituationsTagResultsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#deleteLightFromTag(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> deleteLightFromTag(TagRequest tagRequest)
	{
		return testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tag.dac.ITagDAC#fetchLightsBelongTag(com.sensus.lc.tag.model.request.TagRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Light> fetchLightsBelongTag(TagRequest tagRequest)
	{

		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<Light>();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		if(getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			Light light = TestBaseUtil.createLight();
			internalResultsResponse.addResult(light);
			return internalResultsResponse;
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the tag response.
	 *
	 * @return the tag response
	 */
	private InternalResultsResponse<Tag> getTagResponse()
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		response.addResult(TestBaseUtil.createTag());
		return response;
	}

	/**
	 * Test situations tag results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tag> testSituationsTagResultsResponse()
	{
		InternalResultsResponse<Tag> internalResultsResponse = new InternalResultsResponse<Tag>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getTagResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			internalResultsResponse = getTagResponse();
			internalResultsResponse.getFirstResult().setAutoGroup(true);
			return internalResultsResponse;
		}

		return internalResultsResponse;
	}

	/**
	 * Test situations tag response.
	 *
	 * @return the internal response
	 */
	private InternalResponse testSituationsTagResponse()
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getTagResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}
}