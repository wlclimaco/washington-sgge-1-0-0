package com.sensus.dm.common.tag.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

/**
 * The Class MockTagBCL.
 */
public class MockTagBCL extends AbstractMockBase implements ITagBCL
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#fetchAllTags(com.sensus.dm.common.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		return tagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#insertTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		return tagResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#insertDeviceToTag(com.sensus.dm.common.device.model.request.InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse insertDeviceToTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#deleteTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteTag(TagRequest tagRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#deleteDeviceFromTag(com.sensus.dm.common.device.model.request.InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse deleteDeviceFromTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return verifyOtherSituations();
	}

	/**
	 * Tag results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tag> tagResultsResponse()
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Tag tag = new Tag();
			tag.setId(1);
			tag.setName("Tag");
			tag.setDescription("Tag for test");
			response.addResult(tag);

			return response;
		}

		return (InternalResultsResponse<Tag>)verifyOtherSituations();
	}

}
