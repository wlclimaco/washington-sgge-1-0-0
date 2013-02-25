package com.sensus.mlc.tag.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

public class MockTagDAC extends AbstractMockBase implements ITagDAC
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchAllTags(com.sensus.mlc.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchTagById(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagById(TagRequest tagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchTagByName(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagByName(TagRequest tagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchTagsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsBySmartPoint(LightRequest request)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchTagsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsByLight(LightRequest request)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchTagNameById(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagNameById(TagRequest tagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#insertTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#insertSmartPointToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		return this.testSituationsTagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#updateAutoGroupTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> updateAutoGroupTag(TagRequest tagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#deleteTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> deleteTag(TagRequest tagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#deleteSmartPointFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> deleteSmartPointFromTag(TagRequest tagRequest)
	{
		return this.testSituationsTagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchLightsBelongTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightsBelongTag(TagRequest tagRequest)
	{
		return new InternalResultsResponse<Light>();
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

		if (this.getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return this.getTagResponse();
		}

		if (this.getSituationsEnum() == SituationsEnum.ERROR)
		{
			return this.getResponseResultsError();
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

		if (this.getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return this.getTagResponse();
		}

		if (this.getSituationsEnum() == SituationsEnum.ERROR)
		{
			return this.getResponseResultsError();
		}

		return response;
	}
}