package com.sensus.lc.tag.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createInquiryTagRequest;
import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createTag;
import static com.sensus.lc.base.TestBaseUtil.createTagRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.group.bcl.IGroupBCL;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.dac.ITagDAC;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;

/**
 * The Class TagBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tag/tagbclimpltest.xml"})
public class TagBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant FETCH_ALL_BY_REQUEST. */
	private static final String FETCH_ALL_BY_REQUEST = "fetchAllByRequest";

	/** The Constant FETCH_TAG_NAME_BY_ID. */
	private static final String FETCH_TAG_NAME_BY_ID = "fetchTagNameById";

	/** The Constant RANDOM. */
	public static final Random RANDOM = new Random();

	/** The Constant NUMBER_RANGE. */
	public static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant LIGH_ALREADY_IN_THE_GROUP. */
	private static final String LIGHT_ALREADY_IN_TAG = "sensus.mlc.tagvalidator.light.already.exist";

	/** The Constant SENSUS_MLC_TAGVALIDATOR_ALREADY_EXIST. */
	private static final String SENSUS_MLC_TAGVALIDATOR_ALREADY_EXIST = "sensus.mlc.tagvalidator.already.exist";

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
		Tag tag = new Tag();
		tag.setName("Tag name " + RANDOM.nextInt(NUMBER_RANGE));
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
	 * Fetch tags by light.
	 */
	@Test
	public void testFetchTagsByLight()
	{
		// Success situations
		LightRequest lightRequest = setLightRequest();
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
		Tag tag = createTag();
		tagRequest.setTag(tag);
		InternalResultsResponse<Tag> response = getTagBCL().insertTag(tagRequest);
		assertResultResponse(response);
		assertTagsNotNullNotEmpty(response.getResultsList());

		resetMocksToTagArea();

		// Error situation
		setSituation(getTagBCL(), SituationsEnum.SUCCESS, ITagDAC.class, "fetchTagByName");
		tag.setId(null);
		tagRequest.setTag(tag);
		response = getTagBCL().insertTag(tagRequest);
		assertMessages(response, SENSUS_MLC_TAGVALIDATOR_ALREADY_EXIST);

		resetMocksToTagArea();

		// Error situation
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class);
		response = getTagBCL().insertTag(tagRequest);
		assertMessages(response, ERROR_CODE);

	}

	/**
	 * Test insert light to tag.
	 */
	@Test
	public void testInsertLightToTag()
	{
		// Success situations
		TagRequest tagRequest = createTagRequest();
		tagRequest.setLightRequest(TestBaseUtil.createLightRequest());
		tagRequest.getLightRequest().setLightCriteria(new LightCriteria());
		tagRequest.getLightRequest().getLightCriteria().setLifeCycleStateList(Arrays.asList(LifeCycleStateEnum.ACTIVE));

		Tag tag = createTag();
		Light light = createLight();
		tag.setLight(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		InternalResponse response = getTagBCL().insertLightToTag(tagRequest);
		assertResponse(response);

		// Validation situation auto-group
		resetMocksToTagArea();
		tagRequest = createTagRequest();
		tag = createTag();
		tag.setLight(light);
		tag.setAutoGroup(true);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		setSituation(getTagBCL(), SituationsEnum.ERROR, IGroupBCL.class, "insertGroup", "fetchGroupByName");
		setSituation(getTagBCL(), SituationsEnum.VALIDATION, ITagDAC.class, FETCH_TAG_NAME_BY_ID);
		response = getTagBCL().insertLightToTag(tagRequest);
		assertMessages(response, ERROR_CODE);

		// Validation situation
		resetMocksToTagArea();
		tagRequest = createTagRequest();
		tag = createTag();
		tag.setLight(light);
		tag.setAutoGroup(true);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		setSituation(getTagBCL(), SituationsEnum.VALIDATION, ITagDAC.class, FETCH_TAG_NAME_BY_ID);
		response = getTagBCL().insertLightToTag(tagRequest);
		assertResponse(response);

		// Validation situation
		resetMocksToTagArea();
		tagRequest = createTagRequest();
		tag = createTag();
		tag.setLight(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		setSituation(getTagBCL(), SituationsEnum.VALIDATION, ILightBCL.class, FETCH_ALL_BY_REQUEST);
		response = getTagBCL().insertLightToTag(tagRequest);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		// Validation situation
		resetMocksToTagArea();
		setSituation(getTagBCL(), SituationsEnum.VALIDATION, ITagDAC.class, "fetchLightsBelongTag");
		response = getTagBCL().insertLightToTag(tagRequest);
		assertMessages(response, LIGHT_ALREADY_IN_TAG);

		// Validation situation
		resetMocksToTagArea();
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, FETCH_TAG_NAME_BY_ID);
		response = getTagBCL().insertLightToTag(tagRequest);
		assertMessages(response, ERROR_CODE);

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "insertLightToTag");
		response = getTagBCL().insertLightToTag(tagRequest);
		assertMessages(response, ERROR_CODE);
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
		tagRequest.setIncludeLightsToGroup(true);
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
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete light from tag.
	 */
	@Test
	public void testDeleteLightFromTag()
	{
		// Success situations
		TagRequest tagRequest = createTagRequest();
		tagRequest.setLightRequest(TestBaseUtil.createLightRequest());
		tagRequest.getLightRequest().setLightCriteria(new LightCriteria());
		tagRequest.getLightRequest().getLightCriteria().setLifeCycleStateList(Arrays.asList(LifeCycleStateEnum.ACTIVE));

		Light light = createLight();
		Tag tag = createTag();
		tag.setLight(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		InternalResponse response = getTagBCL().deleteLightFromTag(tagRequest);
		assertResponse(response);

		// Validation situation
		resetMocksToTagArea();
		tagRequest = createTagRequest();

		tagRequest.setLightRequest(TestBaseUtil.createLightRequest());
		tagRequest.getLightRequest().setLightCriteria(new LightCriteria());
		tagRequest.getLightRequest().getLightCriteria().setLifeCycleStateList(Arrays.asList(LifeCycleStateEnum.ACTIVE));

		tag = createTag();
		tag.setLight(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		setSituation(getTagBCL(), SituationsEnum.VALIDATION, ILightBCL.class, FETCH_ALL_BY_REQUEST);
		response = getTagBCL().insertLightToTag(tagRequest);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		resetMocksToTagArea();

		// Error situation
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "deleteLightFromTag");
		response = getTagBCL().deleteLightFromTag(tagRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Sets the light request.
	 * 
	 * @return the light request
	 */
	private LightRequest setLightRequest()
	{
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setLightIdList(Arrays.asList(TEN));

		LightRequest lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		return lightRequest;
	}
}
