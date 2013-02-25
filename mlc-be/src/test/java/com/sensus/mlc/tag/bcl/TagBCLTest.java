package com.sensus.mlc.tag.bcl;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryTagRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createTag;
import static com.sensus.mlc.base.TestBaseUtil.createTagRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.dac.ITagDAC;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Class TagBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tag/tagbclimpltest.xml"})
public class TagBCLTest extends AbstractTestBaseBusiness
{

	/** The tag bcl. */
	private ITagBCL tagBCL;

	/**
	 * Gets the tag bcl.
	 *
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 *
	 * @param tagBCL the new tag bcl
	 */
	@Resource(name = "tagBCLTarget")
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * Sets the tag area.
	 */
	public void setTagArea()
	{
		setArea(getTagBCL(), LCAreaEnum.TAG);
	}

	/**
	 * Removes the tag area.
	 */
	@After
	public void removeTagArea()
	{
		setArea(getTagBCL(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Reset mocks to tag area.
	 */
	@After
	public void resetMocksToTagArea()
	{
		resetMocksData(getTagBCL());
		setTagArea();
	}

	/**
	 * Assert tags.
	 *
	 * @param tags the tags
	 */
	private void assertTagsNotNullNotEmpty(List<Tag> tags)
	{
		assertNotNull("Tag should not be null.", tags);
		assertTrue("Tag should not be empty.", tags.size() > 0);
	}

	/**
	 * Test fetch all tags.
	 */
	@Test
	public void testFetchAllTags()
	{
		// Success situation
		InquiryTagRequest inquiryTagRequest = createInquiryTagRequest();
		InternalResultsResponse<Tag> response = getTagBCL().fetchAllTags(inquiryTagRequest);
		assertResultResponse(response);
		assertTagsNotNullNotEmpty(response.getResultsList());

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class);
		response = getTagBCL().fetchAllTags(inquiryTagRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch tag by id.
	 */
	@Test
	public void testFetchTagById()
	{
		// Success situation
		TagRequest tagRequest = createTagRequest();
		Tag tag = createTag();
		tagRequest.setTag(tag);
		InternalResultsResponse<Tag> response = getTagBCL().fetchTagById(tagRequest);
		assertResultResponse(response);
		assertTagsNotNullNotEmpty(response.getResultsList());

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class);
		response = getTagBCL().fetchTagById(tagRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch tag by name.
	 */
	@Test
	public void testFetchTagByName()
	{
		// Success situation
		TagRequest tagRequest = createTagRequest();
		Tag tag = createTag();
		tagRequest.setTag(tag);
		InternalResultsResponse<Tag> response = getTagBCL().fetchTagByName(tagRequest);
		assertResultResponse(response);
		assertTagsNotNullNotEmpty(response.getResultsList());

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class);
		response = getTagBCL().fetchTagByName(tagRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Fetch tags by smart point.
	 */
	@Test
	public void testFetchTagsBySmartPoint()
	{
		// Success validation
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(createLight());
		InternalResultsResponse<Tag> response = getTagBCL().fetchTagsBySmartPoint(lightRequest);
		assertResultResponse(response);
		assertTagsNotNullNotEmpty(response.getResultsList());

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class);
		response = getTagBCL().fetchTagsBySmartPoint(lightRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Fetch tags by light.
	 */
	@Test
	public void testFetchTagsByLight()
	{
		// Success situations
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(createLight());
		InternalResultsResponse<Tag> response = getTagBCL().fetchTagsByLight(lightRequest);
		assertResultResponse(response);
		assertTagsNotNullNotEmpty(response.getResultsList());

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class);
		response = getTagBCL().fetchTagsByLight(lightRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert tag.
	 */
	@Test
	public void testInsertTag()
	{
		// Success situations
		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(createTag());
		InternalResultsResponse<Tag> response = getTagBCL().insertTag(tagRequest);
		assertResultResponse(response);
		assertTagsNotNullNotEmpty(response.getResultsList());

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class);
		response = getTagBCL().insertTag(tagRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert smartpoint to tag.
	 */
	@Test
	public void testInsertSmartpointToTag()
	{
		// Success situations
		TagRequest tagRequest = createTagRequest();
		Tag tag = createTag();
		tag.setLight(createLight());
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		InternalResponse response = getTagBCL().insertSmartPointToTag(tagRequest);
		assertResponse(response);

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "insertSmartPointToTag",
				"fetchTagNameById");
		response = getTagBCL().insertSmartPointToTag(tagRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update auto group tag.
	 */
	@Test
	public void testUpdateAutoGroupTag()
	{
		// Success situations
		TagRequest tagRequest = createTagRequest();
		Tag tag = createTag();
		tag.setAutoGroup(true);
		tag.setLight(createLight());
		tagRequest.setTag(tag);
		tagRequest.setIncludeSmartPointsToGroup(true);
		InternalResponse response = getTagBCL().updateAutoGroupTag(tagRequest);
		assertResponse(response);

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "updateAutoGroupTag");
		response = getTagBCL().updateAutoGroupTag(tagRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete tag.
	 */
	@Test
	public void testDeleteTag()
	{
		// Success situations
		TagRequest tagRequest = createTagRequest();
		Tag tag = createTag();
		tagRequest.setTag(tag);
		InternalResponse response = getTagBCL().deleteTag(tagRequest);
		assertResponse(response);

		resetMocksToTagArea();

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "deleteTag");
		response = getTagBCL().deleteTag(tagRequest);
		this.assertMessages(response, ERROR_CODE);

		// Error situation
		this.setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "fetchTagById");
		response = getTagBCL().deleteTag(tagRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete smart point from tag.
	 */
	@Test
	public void testDeleteSmartPointFromTag()
	{
		// Success situations
		TagRequest tagRequest = createTagRequest();
		Tag tag = createTag();
		tag.setLight(createLight());
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		InternalResponse response = getTagBCL().deleteSmartPointFromTag(tagRequest);
		assertResponse(response);

		resetMocksToTagArea();

		// Error situation
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "deleteSmartPointFromTag");
		response = getTagBCL().deleteSmartPointFromTag(tagRequest);
		assertEquals("Should get Error", response.getStatus(), Status.ExceptionError);
	}
}
