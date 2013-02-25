package com.sensus.mlc.tag.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Interface ITagDAC.
 */
public interface ITagDAC
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
	 * Fetch tags by smartpoint.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagsBySmartPoint(LightRequest request);

	/**
	 * Fetch tags by light.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagsByLight(LightRequest request);

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
	 * Fetch tag by id.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagById(TagRequest tagRequest);

	/**
	 * Fetch tag name by id.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tag> fetchTagNameById(TagRequest tagRequest);

	/**
	 * Delete smart point from tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	InternalResponse deleteSmartPointFromTag(TagRequest tagRequest);

	/**
	 * Fetch lights belong tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightsBelongTag(TagRequest tagRequest);

}
