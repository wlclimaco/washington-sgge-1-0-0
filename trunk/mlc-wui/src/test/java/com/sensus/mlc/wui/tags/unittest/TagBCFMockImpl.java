package com.sensus.mlc.wui.tags.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.response.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.util.ModeEnum;

public class TagBCFMockImpl extends BaseMockImpl implements ITagBCF
{

	public static final String TAG_NAME = "TAG ";

	@Override
	public InquiryTagResponse fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{

		InquiryTagResponse response = new InquiryTagResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setTags(generateTagList());
			return response;
		}

		return (InquiryTagResponse)testOtherDefaultModes(response);

	}

	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse fetchTagByName(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse fetchTagsByLight(LightRequest lightRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse updateAutoGroupTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse deleteSmartPointFromTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse fetchTagsBySmartPoint(LightRequest lightRequest)
	{
		return getTagResponseDefault();
	}

	private TagResponse getTagResponseDefault()
	{
		TagResponse response = new TagResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setTags(generateTagList());
			return response;
		}

		return (TagResponse)testOtherDefaultModes(response);
	}

	private List<Tag> generateTagList()
	{
		List<Tag> tags = new ArrayList<Tag>();

		Tag tag;

		for (int i = 0; i < 50; i++)
		{
			tag = new Tag();
			tag.setName(TAG_NAME + i);
			tag.setId(i);
			tag.setCreateDate(new Date());
			tag.setTotalSmartpoints(10);

			List<Light> lights = new ArrayList<Light>();
			Light light;

			for (int x = 0; x < 10; x++)
			{
				light = new Light();
				light.setId(x);
				light.setCreateDate(new Date());
				light.setPoleId("POLE_ID_"+i);
				lights.add(light);
			}

			tag.setLights(lights);

			if (i % 2 == 0)
			{
				tag.setAutoGroup(true);
			}
			else
			{
				tag.setAutoGroup(false);
			}

			tags.add(tag);
		}

		return tags;

	}
}
