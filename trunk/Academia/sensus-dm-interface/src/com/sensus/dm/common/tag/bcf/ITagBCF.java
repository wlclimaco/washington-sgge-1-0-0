package com.sensus.dm.common.tag.bcf;

import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.common.tag.model.response.TagResponse;

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
	 * Fetch tags by device.
	 * 
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse fetchTagsByDevice(InquiryTagRequest inquiryDeviceRequest);

	/**
	 * Insert device to tag.
	 * 
	 * @param inquiryDeviceRequest the device request
	 * @return the tag response
	 */
	TagResponse insertDeviceToTag(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Insert tag.
	 * 
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse insertTag(TagRequest tagRequest);

	/**
	 * Delete tag.
	 * 
	 * @param tagRequest the tag request
	 * @return the tag response
	 */
	TagResponse deleteTag(TagRequest tagRequest);

	/**
	 * Delete device from tag.
	 * 
	 * @param inquiryDeviceRequest the device request
	 * @return the tag response
	 */
	TagResponse deleteDeviceFromTag(InquiryDeviceRequest inquiryDeviceRequest);
}
