package com.sensus.mlc.tag.bcf;

import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.tag.model.response.TagResponse;

/**
 * The Interface ITagBCF.
 *
 * @author - Alex Barros - QAT
 */
public interface ITagBCF
{

	/**
	 * Fetch all tags.
	 *
	 * @param inquiryTagRequest the inquiry tag request
	 * @return the inquiry tag response
	 */
	InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest);

	/**
	 * Insert tag.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse insertTag(TagRequest tagRequest);

	/**
	 * Fetch tag by name.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse fetchTagByName(TagRequest tagRequest);

	/**
	 * Insert smart point to tag.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse insertSmartPointToTag(TagRequest tagRequest);

	/**
	 * Fetch tags by smartpoint.
	 *
	 * @param lightRequest the light request
	 * @return the tag response
	 */
	TagResponse fetchTagsBySmartPoint(LightRequest lightRequest);

	/**
	 * Fetch tags by light.
	 *
	 * @param lightRequest the light request
	 * @return the tag response
	 */
	TagResponse fetchTagsByLight(LightRequest lightRequest);

	/**
	 * Delete tag.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse deleteTag(TagRequest tagRequest);

	/**
	 * Update auto group tag.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse updateAutoGroupTag(TagRequest tagRequest);

	/**
	 * Delete smart point from tag.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse deleteSmartPointFromTag(TagRequest tagRequest);
}
