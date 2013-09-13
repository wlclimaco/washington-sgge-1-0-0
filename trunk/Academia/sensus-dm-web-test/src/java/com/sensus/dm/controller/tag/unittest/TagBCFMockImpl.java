package com.sensus.dm.controller.tag.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.response.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.bcf.ITagBCF;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.common.tag.model.response.TagResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;

/**
 * The Class TagBCFMockImpl.
 */
public class TagBCFMockImpl extends BaseMockImpl implements ITagBCF
{

	/** The Constant TAG_COUNT. */
	public static final Integer TAG_COUNT = 14;

	/** The Constant TAG_FILTER_COUNT. */
	public static final Integer TAG_FILTER_COUNT = 19;

	/** The Constant TAG_NAME. */
	public static final String TAG_NAME = "Tag ";

	/** The Constant SMARTPOINT_NAME. */
	public static final String SMARTPOINT_NAME = "SmartPoint %d";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#fetchAllTags(com.sensus.dm.common.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		InquiryTagResponse response = new InquiryTagResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			response.setTags(new ArrayList<Tag>());

			Integer pageSize;

			if (ValidationUtil.isNullOrZero(inquiryTagRequest.getPageSize()))
			{
				pageSize = 20;
			}
			else
			{
				pageSize = 15;
			}

			for (int i = inquiryTagRequest.getStartRow();
					i < (inquiryTagRequest.getStartRow() + pageSize); i++)
			{
				Tag tag = new Tag();
				tag.setId(i + 1);
				tag.setName(String.format(TAG_NAME, i));
				tag.setCreateDate(new Date());
				tag.setDevicesCount(5 * i);
				response.getTags().add(tag);
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(TAG_COUNT);
				response.setResultsSetInfo(resultsSetInfo);
			}
			return response;
		}

		return (InquiryTagResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#insertTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setTags(tagRequest.getTags());

			return response;
		}

		return (TagResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#deleteTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (TagResponse)testOtherDefaultModes(response);
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
		TagResponse response = new TagResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			return response;
		}
		return (TagResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcf.ITagBCF#deleteDeviceFromTag(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public TagResponse deleteDeviceFromTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		TagResponse response = new TagResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			return response;
		}
		return (TagResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.tag.bcf.ITagBCF#fetchTagsByDevice(com.sensus.dm.common.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public TagResponse fetchTagsByDevice(InquiryTagRequest inquiryDeviceRequest)
	{
		TagResponse response = new TagResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			List<Tag> tagList = new ArrayList<Tag>();
			for (int i = 0; i < 10; i++)
			{
				Tag tag = new Tag();
				tag.setName(TAG_NAME + i);
				tag.setId(i);
				tag.setDevicesCount(i * 28);
				tagList.add(tag);
			}
			response.setTags(tagList);
			response.setOperationSuccess(true);
			return response;
		}
		return (TagResponse)testOtherDefaultModes(response);
	}
}
