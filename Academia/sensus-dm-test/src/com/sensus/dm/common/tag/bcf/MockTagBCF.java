package com.sensus.dm.common.tag.bcf;

import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.common.tag.model.response.TagResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockTagBCF.
 */
public class MockTagBCF extends AbstractMockBase implements ITagBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#fetchAllTags(com.sensus.dm.common.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		return new InquiryTagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#fetchTagsByDevice(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse fetchTagsByDevice(InquiryTagRequest inquiryTagRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.tag.bcf.ITagBCF#insertDeviceToTag(com.sensus.dm.common.device.model.request.InquiryDeviceRequest
	 * )
	 */
	@Override
	public TagResponse insertDeviceToTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#insertTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#deleteTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		return new TagResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#deleteDeviceFromTag(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public TagResponse deleteDeviceFromTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return new TagResponse();
	}

}
