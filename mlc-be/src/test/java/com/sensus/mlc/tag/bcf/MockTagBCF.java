package com.sensus.mlc.tag.bcf;

import java.util.ArrayList;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.tag.model.response.TagResponse;

/**
 * The Class MockTagBCF.
 */
public class MockTagBCF extends AbstractMockBase implements ITagBCF
{

	/** The Constant VALID. */
	private static final String VALID = "valid";

	/** The Constant INCOMPLETE_DATA. */
	private static final String INCOMPLETE_DATA = "Incomplete Data";

	/** The Constant NEBRASKA. */
	private static final String NEBRASKA = "Nebraska";
	/** The Constant REVERSE_LOOKUP_FAILED. */
	private static final String REVERSE_LOOKUP_FAILED = "sensus.mlc.smartpointbclimpl.reverse.lookup.failed";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchAllTags(com.sensus.mlc.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		return new InquiryTagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchTagByName(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse fetchTagByName(TagRequest tagRequest)
	{
		TagResponse tagResponse = new TagResponse();

		if ("".equals(tagRequest.getTag().getName()))
		{
			tagResponse.addOperationFailedMessage(REVERSE_LOOKUP_FAILED);
		}

		if (tagRequest.getTag().getName().equalsIgnoreCase(VALID))
		{
			Tag tag = new Tag();
			tag.setName(VALID);
			tag.setId(1);
			ArrayList<Tag> listTag = new ArrayList<Tag>();
			listTag.add(tag);
			tagResponse.setTags(listTag);
		}

		if (tagRequest.getTag().getName().equalsIgnoreCase(INCOMPLETE_DATA))
		{
			Tag tag = new Tag();
			tag.setName(INCOMPLETE_DATA);
			tag.setId(1);
			ArrayList<Tag> listTag = new ArrayList<Tag>();
			listTag.add(tag);
			tagResponse.setTags(listTag);
		}

		if (tagRequest.getTag().getName().equalsIgnoreCase("68102"))
		{
			tagResponse.addOperationFailedMessage(REVERSE_LOOKUP_FAILED);
		}

		if (tagRequest.getTag().getName().equalsIgnoreCase(NEBRASKA))
		{
			Tag tag = new Tag();
			tag.setName(NEBRASKA);
			tag.setId(1);
			ArrayList<Tag> listTag = new ArrayList<Tag>();
			listTag.add(tag);
			tagResponse.setTags(listTag);
		}

		return tagResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#insertSmartPointToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		TagResponse tagResponse = new TagResponse();
		return tagResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#insertTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		TagResponse tagResponse = new TagResponse();
		Tag tag = new Tag();
		tag.setId(1);
		tag.setName(tagRequest.getTag().getName());
		tagResponse.getTags().add(tag);
		return tagResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#fetchTagsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public TagResponse fetchTagsByLight(LightRequest lightRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#deleteTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#updateAutoGroupTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse updateAutoGroupTag(TagRequest tagRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcf.ITagBCF#deleteSmartPointFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteSmartPointFromTag(TagRequest tagRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.tag.bcf.ITagBCF#fetchTagsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public TagResponse fetchTagsBySmartPoint(LightRequest lightRequest)
	{
		return new TagResponse();
	}
}