package com.sensus.mlc.wui.tag.unittest.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class TagBCFMockImpl extends BaseMockImpl implements ITagBCF
{
	public static final Integer TAG_COUNT = 3;
	public static final Integer TAG_FILTER_COUNT = 19;
	public static final String TAG_NAME = "Tag %d";
	public static final String SMARTPOINT_NAME = "SmartPoint %d";

	@Override
	public InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		InquiryTagResponse response = new InquiryTagResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setTags(new ArrayList<Tag>());

			Boolean autoGroup = false;

			Integer pageSize;

			if (inquiryTagRequest.getPageSize() == 0)
			{
				pageSize = 20;
			}
			else
			{
				pageSize = inquiryTagRequest.getPageSize();
			}

			for (int i = inquiryTagRequest.getStartRow(); i < (inquiryTagRequest.getStartRow()
			+ pageSize); i++)
			{
				Tag tag = new Tag();
				tag.setId(i + 1);
				tag.setName(String.format(TAG_NAME, i));
				tag.setLights(new ArrayList<Light>());
				tag.setCreateDate(new Date());
				tag.setDate(new Date());
				tag.setTotalSmartpoints(5);

				if (autoGroup)
				{
					autoGroup = false;
				}
				else
				{
					autoGroup = true;
				}

				tag.setAutoGroup(autoGroup);

				for (int ii = 1; ii <= i; ii++)
				{

					Light sp = new Light();
					sp.setId(i);
					sp.setRniId(i);
					tag.getLights().add(sp);
				}
				response.getTags().add(tag);
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(TAG_COUNT);
				response.setResultsSetInfo(resultsSetInfo);
			}

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		// Invalid inputs cover Failure scenario
		if ((tagRequest.getTag() == null)
				|| (tagRequest.getTag().getName() == null))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			List<Tag> tagList = new ArrayList<Tag>();

			Tag tag = new Tag();
			tag.setId(300);

			tagList.add(tag);

			response.setTags(tagList);
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public TagResponse fetchTagByName(TagRequest tagRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNullOrEmpty(tagRequest.getTags()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public TagResponse fetchTagsByLight(LightRequest lightRequest)
	{
		TagResponse response = new TagResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			List<Tag> tagList = new ArrayList<Tag>();
			Tag tag = new Tag();
			Light light = new Light();
			light.setId(1);
			tag.setName(TAG_NAME);
			tag.setId(1);
			tag.setLight(light);
			response.setTags(tagList);
		}
		return response;
	}

	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		// Invalid inputs cover Failure scenario
		if ((tagRequest.getTag() == null) || (tagRequest.getTag().getId() == null)
				|| (tagRequest.getTag().getId() < 1)
				|| (tagRequest.getTag().getModelAction() != PersistanceActionEnum.DELETE))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public TagResponse updateAutoGroupTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		// Invalid inputs cover Failure scenario
		if ((tagRequest.getTag() == null) || (tagRequest.getTag().getId() == null)
				|| (tagRequest.getTag().getId() < 1)
				|| (tagRequest.getTag().getModelAction() != PersistanceActionEnum.UPDATE))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public TagResponse deleteSmartPointFromTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNullOrEmpty(tagRequest.getTags()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	@Override
	public TagResponse fetchTagsBySmartPoint(LightRequest lightRequest)
	{
		TagResponse response = new TagResponse();

		// Invalid inputs cover Failure scenario
		if ((lightRequest.getFirstLight() == null) || (lightRequest.getFirstLight().getId() == null)
				|| (lightRequest.getFirstLight().getId() < 1))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}
}
