package com.sensus.mlc.tag.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Class MockTagBCL.
 */
public class MockTagBCL extends AbstractMockBase implements ITagBCL
{

	/** The Constant TEST_EXCEPTION2. */
	private static final String TEST_EXCEPTION2 = "Test Exception";

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test exception";

	/**
	 * Instantiates a new mock tag bcl.
	 */
	public MockTagBCL()
	{
	}

	/**
	 * Gets the tag result response by situations.
	 *
	 * @return the tag result response by situations
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tag> getTagResultResponseBySituations()
	{
		InternalResultsResponse<Tag> internalResultsResponse = new InternalResultsResponse<Tag>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResultResponseTag();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION2);
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the response by situation.
	 *
	 * @return the response by situation
	 */
	private InternalResponse getResponseBySituation()
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION2);
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchAllTags(com.sensus.mlc.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		InternalResultsResponse<Tag> response = getResultResponseTag();
		response = fetchAllTagsFromTag(inquiryTagRequest, response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#insertTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = getResultResponseTag();
		response = insertTagFromTag(tagRequest, response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagByName(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagByName(TagRequest tagRequest)
	{

		InternalResultsResponse<Tag> response = getTagResultResponseBySituations();
		if (getAreaEnum() == LCAreaEnum.GROUP)
		{
			response.getFirstResult().setAutoGroup(true);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagById(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagById(TagRequest tagRequest)
	{
		return getTagResultResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#insertSmartPointToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		return getResponseBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsByLight(LightRequest lightRequest)
	{
		return getTagResultResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#deleteTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteTag(TagRequest tagRequest)
	{
		return getResponseBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#updateAutoGroupTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> updateAutoGroupTag(TagRequest tagRequest)
	{
		return getTagResultResponseBySituations();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#deleteSmartPointFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteSmartPointFromTag(TagRequest tagRequest)
	{
		return getResponseBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsBySmartPoint(LightRequest lightRequest)
	{
		return getTagResultResponseBySituations();
	}

	/**
	 * Gets the response default.
	 *
	 * @return the response default
	 */
	private InternalResponse getResponseDefault()
	{
		return new InternalResponse();
	}

	/**
	 * Fetch all tags from tag.
	 *
	 * @param inquiryTagRequest the inquiry tag request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tag> fetchAllTagsFromTag(InquiryTagRequest inquiryTagRequest,
			InternalResultsResponse<Tag> response)
	{
		if (getAreaEnum() != LCAreaEnum.TAG)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResultResponseTag();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Insert tag from tag.
	 *
	 * @param tagRequest the tag request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tag> insertTagFromTag(TagRequest tagRequest, InternalResultsResponse<Tag> response)
	{
		if (getAreaEnum() != LCAreaEnum.TAG)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResultResponseTag();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}
}