package com.sensus.mlc.tag.dac;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryTagRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createTagRequest;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.util.LCPropertiesExtractorUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Class TagDACTest.
 */
public class TagDACTest extends AbstractTestBaseDAC
{

	/** The Constant TAG_OBJECT_SHOULD_TO_BE_NULL. */
	private static final String TAG_OBJECT_SHOULD_TO_BE_NULL = "Tag object should to be null";

	/** The Constant AMOUNT_TAG_TO_INSERT. */
	private static final Integer AMOUNT_TAG_TO_INSERT = 3;

	/** The Constant ARBITRARY_NUMBER_THREE. */
	private static final Integer ARBITRARY_NUMBER_THREE = 3;

	/**
	 * Test fetch all tags.
	 */
	@Test
	public void testFetchAllTags()
	{
		insertTags(AMOUNT_TAG_TO_INSERT);
		InquiryTagRequest inquiryTagRequest = createInquiryTagRequest();
		InternalResultsResponse<Tag> response = getTagDAC().fetchAllTags(inquiryTagRequest);
		assertResultResponse(response);

		assertTrue("The tag list should have at least 3", response.getResultsList().size() >= ARBITRARY_NUMBER_THREE);

		// No Rows Found
		List<SortExpression> listSortExpression = new ArrayList<SortExpression>();
		listSortExpression.add(new SortExpression());
		inquiryTagRequest.setSortExpressions(listSortExpression);
		inquiryTagRequest.setTag(TestBaseUtil.createTag());
		response = getTagDAC().fetchAllTags(inquiryTagRequest);
		assertTrue("The tag list should not found rows", response.getResultsList().size() == 0);
	}

	/**
	 * Test insert tag.
	 */
	@Test
	public void testInsertTag()
	{
		insertLight();
	}

	/**
	 * Fetch tag by name.
	 */
	@Test
	public void fetchTagByName()
	{
		Tag tag = this.insertTag(2);
		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(tag);
		InternalResultsResponse<Tag> response = getTagDAC().fetchTagByName(tagRequest);

		assertResultResponse(response);

		tag.setName("FindMeNot");
		response = getTagDAC().fetchTagByName(tagRequest);

		assertNull(TAG_OBJECT_SHOULD_TO_BE_NULL, response.getFirstResult());
	}

	/**
	 * Test insert smart point to tag.
	 */
	@Test
	public void testInsertSmartPointToTag()
	{
		// Success
		addLightToTag(this.insertTag(), insertLight());

		// Success With UnSelectionPaginationIds
		SearchLight searchLight = new SearchLight();
		searchLight.setLight(insertLight());

		TagRequest tagRequest = createTagRequest();
		tagRequest.addToTags(this.insertTag());
		tagRequest.getUnselectionPaginationIds().add(this.insertTag().getId());

		InternalResponse response = getTagDAC().insertSmartPointToTag(tagRequest);
		assertResponse(response);
	}

	/**
	 * Test fetch tag by id.
	 */
	@Test
	public void testFetchTagById()
	{
		Tag tag = this.insertTag();
		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(tag);
		InternalResultsResponse<Tag> response = getTagDAC().fetchTagById(tagRequest);
		assertResponse(response);

		// Without Result
		tag.setId(-1);
		tagRequest.setTag(tag);
		response = getTagDAC().fetchTagById(tagRequest);
		assertNull(TAG_OBJECT_SHOULD_TO_BE_NULL, response.getFirstResult());
	}

	/**
	 * Test fetch tag name by id.
	 */
	@Test
	public void testFetchTagNameById()
	{
		// Success Without Result
		Tag tag = new Tag();
		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(tag);
		InternalResultsResponse<Tag> response = getTagDAC().fetchTagNameById(tagRequest);
		assertNull(TAG_OBJECT_SHOULD_TO_BE_NULL, response.getFirstResult());

		// Success
		tag = this.insertTag();
		tagRequest = createTagRequest();
		tagRequest.setTag(tag);
		response = getTagDAC().fetchTagNameById(tagRequest);
		assertResponse(response);
	}

	/**
	 * Test fetch tags by light.
	 */
	@Test
	public void testFetchTagsByLight()
	{
		// Success Without Result
		Tag tag = this.insertTag();
		Light light = new Light();

		addLightToTag(tag, light);

		LightRequest request = createLightRequest();
		request.addLight(light);

		InternalResultsResponse<Tag> response = getTagDAC().fetchTagsByLight(request);
		assertNull(TAG_OBJECT_SHOULD_TO_BE_NULL, response.getFirstResult());

		// Success
		tag = this.insertTag();
		light = insertLight();

		addLightToTag(tag, light);

		request = createLightRequest();
		request.addLight(light);

		response = getTagDAC().fetchTagsByLight(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch tags by smart point.
	 */
	@Test
	public void testFetchTagsBySmartPoint()
	{
		// Success Without Result
		Tag tag = this.insertTag();
		Light light = new Light();

		addLightToTag(tag, light);

		LightRequest request = createLightRequest();
		request.addLight(light);

		InternalResultsResponse<Tag> response = getTagDAC().fetchTagsBySmartPoint(request);
		assertNull(TAG_OBJECT_SHOULD_TO_BE_NULL, response.getFirstResult());

		// Success
		tag = this.insertTag();
		light = insertLight();

		addLightToTag(tag, light);

		request = createLightRequest();
		request.addLight(light);

		response = getTagDAC().fetchTagsBySmartPoint(request);
		assertResultResponse(response);
	}

	/**
	 * Test update auto group tag.
	 */
	@Test
	public void testUpdateAutoGroupTag()
	{
		Tag tag = this.insertTag();
		tag.setAutoGroup(true);

		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(tag);

		InternalResultsResponse<Tag> response = getTagDAC().updateAutoGroupTag(tagRequest);
		assertResponse(response);
	}

	/**
	 * Test delete tag.
	 */
	@Test
	public void testDeleteTag()
	{
		Tag tag = this.insertTag();

		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(tag);

		InternalResponse response = getTagDAC().deleteTag(tagRequest);
		assertResponse(response);
	}

	/**
	 * Test delete smart point from tag.
	 */
	@Test
	public void testDeleteSmartPointFromTag()
	{
		Light light = insertLight();

		Tag tag = insertTag();
		TagRequest tagRequest = createTagRequest();
		tagRequest.setTag(tag);

		addLightToTag(tag, light);

		tag = getTagDAC().fetchTagByName(tagRequest).getFirstResult();
		assertFalse("The lights to tag should not be empty", ValidationUtil.isNullOrEmpty(tag.getLights()));

		tagRequest = createTagRequest();
		tagRequest.addToTags(tag);

		InternalResponse response = getTagDAC().deleteSmartPointFromTag(tagRequest);
		assertResponse(response);

		tagRequest.setTag(tag);
		tag = getTagDAC().fetchTagByName(tagRequest).getFirstResult();
		assertTrue("The lights to tag should is empty", ValidationUtil.isNullOrEmpty(tag.getLights()));
	}

	/**
	 * Test fetch lights belong group.
	 */
	@Test
	public void testFetchLightsBelongGroup()
	{
		Integer lightsAmount = 2;
		Tag tag = this.insertTag();
		List<Light> lights = insertLights(lightsAmount);
		addLightToTag(tag, lights.toArray(new Light[lights.size()]));

		List<Integer> lightIdList = LCPropertiesExtractorUtil.extractLightId(lights);

		TagRequest tagRequest = createTagRequest();
		tagRequest.setSelectionPaginationIds(lightIdList);
		tagRequest.setTag(tag);

		InternalResultsResponse<Light> response = getTagDAC().fetchLightsBelongTag(tagRequest);
		assertResultResponse(response);

		assertEquals("Light list result should be equals light list belong of tag", lightsAmount.intValue(), response
				.getResultsList().size());

		lights.add(insertLight());
		lightIdList = LCPropertiesExtractorUtil.extractLightId(lights);
		tagRequest.setSelectionPaginationIds(lightIdList);
		response = getTagDAC().fetchLightsBelongTag(tagRequest);
		assertResultResponse(response);
	}
}
