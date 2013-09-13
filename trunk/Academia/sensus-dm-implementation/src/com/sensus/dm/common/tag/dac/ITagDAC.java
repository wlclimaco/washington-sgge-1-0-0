package com.sensus.dm.common.tag.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;

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
	 * Insert Device to tag.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse insertDeviceToTag(InquiryDeviceRequest deviceRequest);

	/**
	 * Delete tag.
	 * 
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	InternalResponse deleteTag(TagRequest tagRequest);

	/**
	 * Delete Device from tag.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse deleteDeviceFromTag(InquiryDeviceRequest deviceRequest);

	/**
	 * Can insert Tag.
	 * 
	 * @param tagRequest the tagp
	 * @return the boolean
	 */
	InternalResultsResponse<Boolean> canTagBeInserted(TagRequest tagRequest);
}
