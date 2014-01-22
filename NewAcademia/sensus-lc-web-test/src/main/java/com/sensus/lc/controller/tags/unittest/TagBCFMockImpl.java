package com.sensus.lc.controller.tags.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.bcf.ITagBCF;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.tag.model.response.InquiryTagResponse;
import com.sensus.lc.tag.model.response.TagResponse;

public class TagBCFMockImpl extends BaseMockImpl implements ITagBCF
{

	public static final String TAG_NAME = "TAG ";

	@Override
	public TagResponse deleteLightFromTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setTags(generateTagList());
			return response;
		}

		return (TagResponse)testOtherDefaultModes(response);
	}

	@Override
	public TagResponse deleteTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

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
	public TagResponse fetchTagByName(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse fetchTagsByLight(LightRequest lightRequest)
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
			tag.setTotalLights(10);

			List<Light> lights = new ArrayList<Light>();
			Light light;

			for (int x = 0; x < 10; x++)
			{
				light = new Light();
				light.setId(x);
				light.setCreateDate(new Date());
				light.setPoleId("POLE_ID_" + i);
				lights.add(light);
			}

			tag.setLights(lights);

			if ((i % 2) == 0)
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

	@Override
	public TagResponse insertLightToTag(TagRequest tagRequest)
	{
		TagResponse response = new TagResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setTags(generateTagList());
			return response;
		}

		return (TagResponse)testOtherDefaultModes(response);
	}

	@Override
	public TagResponse insertTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}

	@Override
	public TagResponse updateAutoGroupTag(TagRequest tagRequest)
	{
		return getTagResponseDefault();
	}
}
