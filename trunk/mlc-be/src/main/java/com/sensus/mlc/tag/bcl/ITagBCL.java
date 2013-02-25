package com.sensus.mlc.tag.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Interface ITagBCL.
 */
public interface ITagBCL
{

	/**
	 * Fetch all tags.
	 *
	 * @param inquiryTagRequest the inquiry tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest);

	/**
	 * Insert tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> insertTag(TagRequest tagRequest);

	/**
	 * Fetch tag by id.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagById(TagRequest tagRequest);

	/**
	 * Fetch tag by name.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagByName(TagRequest tagRequest);

	/**
	 * Insert smart point to tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	InternalResponse insertSmartPointToTag(TagRequest tagRequest);

	/**
	 * Fetch tags by light.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagsByLight(LightRequest lightRequest);

	/**
	 * Fetch tags by smartpoint.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagsBySmartPoint(LightRequest lightRequest);

	/**
	 * Delete tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	InternalResponse deleteTag(TagRequest tagRequest);

	/**
	 * Update auto group tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> updateAutoGroupTag(TagRequest tagRequest);

	/**
	 * Delete smart point from tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	InternalResponse deleteSmartPointFromTag(TagRequest tagRequest);
}
