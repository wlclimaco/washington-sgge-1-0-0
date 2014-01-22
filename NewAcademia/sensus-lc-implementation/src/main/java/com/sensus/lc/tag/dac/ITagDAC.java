package com.sensus.lc.tag.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;

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
	 * Insert light to tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	InternalResponse insertLightToTag(TagRequest tagRequest);

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
	 * Delete light from tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	InternalResponse deleteLightFromTag(TagRequest tagRequest);

	/**
	 * Fetch lights belong tag.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightsBelongTag(TagRequest tagRequest);

}
