package com.sensus.mlc.tag.bcf;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.bcl.ITagBCL;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class TagBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tag/tagbcfimpltest.xml"})
public class TagBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant PECO. */
	private static final String PECO = "PECO";

	/** The Constant FETCH_TAGS_BY_LIGHT. */
	private static final String FETCH_TAGS_BY_LIGHT = "fetchTagsByLight";

	/** The Constant INSERT_SMART_POINT_TO_TAG. */
	private static final String INSERT_SMART_POINT_TO_TAG = "insertSmartPointToTag";

	/** The Constant UPDATE_AUTO_GROUP_TAG. */
	private static final String UPDATE_AUTO_GROUP_TAG = "updateAutoGroupTag";

	/** The Constant FETCH_TAG_BY_NAME. */
	private static final String FETCH_TAG_BY_NAME = "fetchTagByName";

	/** The Constant DELETE_TAG. */
	private static final String DELETE_TAG = "deleteTag";

	/** The Constant FETCH_TAGS_BY_SMART_POINT. */
	private static final String FETCH_TAGS_BY_SMART_POINT = "fetchTagsBySmartPoint";

	/** The Constant DELETE_SMART_POINT_FROM_TAG. */
	private static final String DELETE_SMART_POINT_FROM_TAG = "deleteSmartPointFromTag";

	/** The Constant DEFAULT_TAG_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TAG_BCF_EXCEPTION_MSG =
			"sensus.mlc.tagbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/**
	 * Gets the tag bcf.
	 *
	 * @return the tag bcf
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 *
	 * @param tagBCF the new tag bcf
	 */
	@Resource(name = "tagBCFTarget")
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Sets the tag area.
	 */
	public void setTagArea()
	{
		setArea(getTagBCF(), LCAreaEnum.TAG);
	}

	/**
	 * Removes the eco mode area.
	 */
	@After
	public void resetMocksToTagArea()
	{
		resetMocksData(getTagBCF());
		setTagArea();
	}

	/**
	 * Test fetch all tags.
	 */
	@Test
	public void testFetchAllTags()
	{
		// Validation situation
		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest();
		InquiryTagResponse response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response);
		assertNotNull(response.getTags());
		assertEquals(2, response.getTags().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class);
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class);
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert tag.
	 */
	@Test
	public void testInsertTag()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest();
		TagResponse response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		tagRequest = TestBaseUtil.createTagRequest();
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		tagRequest = TestBaseUtil.createTagRequest();
		Tag tag = new Tag();
		tag.setLight(new Light());
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setLight(new Light());
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Success situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch tag by name.
	 */
	@Test
	public void testFetchTagByName()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest(new UserContext(1));
		Tag tag = new Tag();
		tagRequest.setTag(tag);
		TagResponse response = getTagBCF().fetchTagByName(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().fetchTagByName(tagRequest);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, FETCH_TAG_BY_NAME);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().fetchTagByName(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		response = getTagBCF().fetchTagByName(tagRequest);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, FETCH_TAG_BY_NAME);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().fetchTagByName(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert smart point to tag.
	 */
	@Test
	public void testInsertSmartPointToTag()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest(new UserContext(1));
		Tag tag = new Tag();
		tagRequest.getTags().add(tag);
		TagResponse response = getTagBCF().insertSmartPointToTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		resetMocksToTagArea();
		tagRequest = TestBaseUtil.createTagRequest();
		Light light = TestBaseUtil.createLight();
		tag = TestBaseUtil.createTag();
		List<Integer> smartpointIdsList = new ArrayList<Integer>();
		smartpointIdsList.add(1);
		tag.getLights().add(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(smartpointIdsList);
		response = getTagBCF().insertSmartPointToTag(tagRequest);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, INSERT_SMART_POINT_TO_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		light = TestBaseUtil.createLight();
		tag = TestBaseUtil.createTag();
		smartpointIdsList = new ArrayList<Integer>();
		smartpointIdsList.add(1);
		tag.getLights().add(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(smartpointIdsList);
		response = getTagBCF().insertSmartPointToTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		resetMocksToTagArea();
		tagRequest = TestBaseUtil.createTagRequest();
		light = TestBaseUtil.createLight();
		smartpointIdsList = new ArrayList<Integer>();
		smartpointIdsList.add(1);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(smartpointIdsList);
		response = getTagBCF().insertSmartPointToTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, INSERT_SMART_POINT_TO_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		light = TestBaseUtil.createLight();
		tag = TestBaseUtil.createTag();
		smartpointIdsList = new ArrayList<Integer>();
		smartpointIdsList.add(1);
		tag.getLights().add(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(smartpointIdsList);
		response = getTagBCF().insertSmartPointToTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch tags by smart point.
	 */
	@Test
	public void testFetchTagsBySmartPoint()
	{
		// Validation situation
		LightRequest lightRequest = new LightRequest(new UserContext(1));
		lightRequest.addLight(new Light());
		TagResponse response = getTagBCF().fetchTagsBySmartPoint(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		lightRequest = TestBaseUtil.createLightRequest();
		Light light = TestBaseUtil.createLight();
		light.setSmartPointId(light.getId());
		lightRequest.getLights().add(light);
		response = getTagBCF().fetchTagsBySmartPoint(lightRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, FETCH_TAGS_BY_SMART_POINT);
		lightRequest = TestBaseUtil.createLightRequest();
		light = TestBaseUtil.createLight();
		light.setSmartPointId(light.getId());
		lightRequest.getLights().add(light);
		response = getTagBCF().fetchTagsBySmartPoint(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		resetMocksToTagArea();
		lightRequest = TestBaseUtil.createLightRequest();
		response = getTagBCF().fetchTagsBySmartPoint(lightRequest);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, FETCH_TAGS_BY_SMART_POINT);
		lightRequest = TestBaseUtil.createLightRequest();
		light = TestBaseUtil.createLight();
		light.setSmartPointId(light.getId());
		lightRequest.getLights().add(light);
		response = getTagBCF().fetchTagsBySmartPoint(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch tags by light.
	 */
	@Test
	public void testFetchTagsByLight()
	{
		// Validation situation
		LightRequest lightRequest = new LightRequest(new UserContext(1));
		lightRequest.addLight(new Light());
		TagResponse response = getTagBCF().fetchTagsByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		lightRequest = TestBaseUtil.createLightRequest();
		lightRequest.addLight(TestBaseUtil.createLight());
		response = getTagBCF().fetchTagsByLight(lightRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, FETCH_TAGS_BY_LIGHT);
		lightRequest = TestBaseUtil.createLightRequest();
		lightRequest.addLight(TestBaseUtil.createLight());
		response = getTagBCF().fetchTagsByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		lightRequest = TestBaseUtil.createLightRequest();
		response = getTagBCF().fetchTagsByLight(lightRequest);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, FETCH_TAGS_BY_LIGHT);
		lightRequest = TestBaseUtil.createLightRequest();
		lightRequest.addLight(TestBaseUtil.createLight());
		response = getTagBCF().fetchTagsByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test update auto group tag.
	 */
	@Test
	public void testUpdateAutoGroupTag()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest(new UserContext(1));
		Tag tag = new Tag();
		tagRequest.setTag(tag);
		TagResponse response = getTagBCF().updateAutoGroupTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setAutoGroup(true);
		tagRequest.setTag(tag);
		tagRequest.setIncludeSmartPointsToGroup(true);
		response = getTagBCF().updateAutoGroupTag(tagRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, UPDATE_AUTO_GROUP_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setAutoGroup(true);
		tagRequest.setTag(tag);
		tagRequest.setIncludeSmartPointsToGroup(true);
		response = getTagBCF().updateAutoGroupTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setId(null);
		tagRequest.setTag(tag);
		tagRequest.setIncludeSmartPointsToGroup(true);
		response = getTagBCF().updateAutoGroupTag(tagRequest);
		assertFalse(response.isOperationSuccess());

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		response = getTagBCF().updateAutoGroupTag(tagRequest);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, UPDATE_AUTO_GROUP_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setAutoGroup(true);
		tagRequest.setTag(tag);
		tagRequest.setIncludeSmartPointsToGroup(true);
		response = getTagBCF().updateAutoGroupTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test delete tag.
	 */
	@Test
	public void testDeleteTag()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest(new UserContext(1));
		Tag tag = new Tag();
		tagRequest.setTag(tag);
		TagResponse response = getTagBCF().deleteTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().deleteTag(tagRequest);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, DELETE_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().deleteTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		response = getTagBCF().deleteTag(tagRequest);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, DELETE_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tagRequest.setTag(tag);
		response = getTagBCF().deleteTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test delete smart point from tag.
	 */
	@Test
	public void testDeleteSmartPointFromTag()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest(new UserContext(1));
		Tag tag = new Tag();
		tagRequest.getTags().add(tag);
		TagResponse response = getTagBCF().deleteSmartPointFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		tagRequest = TestBaseUtil.createTagRequest();
		Light light = TestBaseUtil.createLight();
		tag = TestBaseUtil.createTag();
		List<Integer> smartpointIdsList = new ArrayList<Integer>();
		smartpointIdsList.add(1);
		tag.getLights().add(light);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(smartpointIdsList);
		response = getTagBCF().deleteSmartPointFromTag(tagRequest);
		assertTrue(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, DELETE_SMART_POINT_FROM_TAG);
		response = getTagBCF().deleteSmartPointFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, DELETE_SMART_POINT_FROM_TAG);
		response = getTagBCF().deleteSmartPointFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		tagRequest =  TestBaseUtil.createTagRequest();
		response = getTagBCF().deleteSmartPointFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setId(null);
		smartpointIdsList = new ArrayList<Integer>();
		smartpointIdsList.add(1);
		tagRequest.setTag(tag);
		tagRequest.getTags().add(tag);
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(smartpointIdsList);
		response = getTagBCF().deleteSmartPointFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());

	}
}
