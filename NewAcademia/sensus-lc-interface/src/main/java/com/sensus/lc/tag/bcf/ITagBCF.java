package com.sensus.lc.tag.bcf;

import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.tag.model.response.InquiryTagResponse;
import com.sensus.lc.tag.model.response.TagResponse;

/**
 * The Interface ITagBCF.
 *
 * @author - QAT Brazil
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
	 * Insert light to tag.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse insertLightToTag(TagRequest tagRequest);

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
	 * Delete light from tag.
	 *
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse deleteLightFromTag(TagRequest tagRequest);
}
