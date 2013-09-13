package com.sensus.dm.common.tag.dac;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockTagDAC.
 */
public class MockTagDAC extends AbstractMockBase implements ITagDAC
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#fetchAllTags(com.sensus.dm.common.tag.model.request.InquiryTagRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createTag());
			return response;
		}

		return (InternalResultsResponse<Tag>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#insertTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			tagRequest.getTags().get(0).setId(1);
			response.addResult(tagRequest.getTags().get(0));
		}
		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#insertDeviceToTag(com.sensus.dm.common.device.model.request.InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse insertDeviceToTag(InquiryDeviceRequest deviceRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(ERROR_CODE, MessageSeverity.Error, MessageLevel.Other);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#deleteTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteTag(TagRequest tagRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#deleteDeviceFromTag(com.sensus.dm.common.device.model.request.InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse deleteDeviceFromTag(InquiryDeviceRequest deviceRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#canTagBeInserted(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> canTagBeInserted(TagRequest tagRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			response.addResult(false);
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
		}
		else
		{
			response.addResult(true);
		}

		return response;
	}

}
