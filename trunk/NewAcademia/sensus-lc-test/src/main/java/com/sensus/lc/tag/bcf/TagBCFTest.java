package com.sensus.lc.tag.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.bcl.ITagBCL;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.tag.model.response.InquiryTagResponse;
import com.sensus.lc.tag.model.response.TagResponse;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class TagBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tag/tagbcfimpltest.xml"})
public class TagBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant ONE_HUNDRED_ONE. */
	private static final int ONE_HUNDRED_ONE = 101;

	/** The Constant PECO. */
	private static final String PECO = "PECO";

	/** The Constant FETCH_TAGS_BY_LIGHT. */
	private static final String FETCH_TAGS_BY_LIGHT = "fetchTagsByLight";

	/** The Constant INSERT_LIGHT_TO_TAG. */
	private static final String INSERT_LIGHT_TO_TAG = "insertLightToTag";

	/** The Constant UPDATE_AUTO_GROUP_TAG. */
	private static final String UPDATE_AUTO_GROUP_TAG = "updateAutoGroupTag";

	/** The Constant FETCH_TAG_BY_NAME. */
	private static final String FETCH_TAG_BY_NAME = "fetchTagByName";

	/** The Constant DELETE_TAG. */
	private static final String DELETE_TAG = "deleteTag";

	/** The Constant DELETE_LIGHT_FROM_TAG. */
	private static final String DELETE_LIGHT_FROM_TAG = "deleteLightFromTag";

	/** The Constant DEFAULT_TAG_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TAG_BCF_EXCEPTION_MSG =
			"sensus.mlc.tagbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_LENGTH_INVALID. */
	private static final String SENSUS_MLC_VALIDATOR_LENGTH_INVALID = "sensus.mlc.validator.name.length.invalid";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

	/** The Constant TEN. */
	private static final Integer TEN = 10;

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
		// Test Validator
		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest(new UserContext(1));
		inquiryTagRequest.addSortExpressions(new SortExpression());
		InquiryTagResponse response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation to user context
		inquiryTagRequest = new InquiryTagRequest();
		inquiryTagRequest.addSortExpressions(new SortExpression());
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation to tenant
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		inquiryTagRequest.getUserContext().setTenant(null);
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Validation to pagination
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		inquiryTagRequest.setPageSize(1);
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);

		// Success situation
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		inquiryTagRequest.addSortExpressions(new SortExpression());
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response);
		assertNotNull(response.getTags());
		assertEquals(2, response.getTags().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class);
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		inquiryTagRequest.addSortExpressions(new SortExpression());
		response = getTagBCF().fetchAllTags(inquiryTagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class);
		inquiryTagRequest = TestBaseUtil.createInquiryTagRequest();
		inquiryTagRequest.addSortExpressions(new SortExpression());
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

		// Success situation
		tagRequest = TestBaseUtil.createTagRequest();
		Tag tag = TestBaseUtil.createTag();
		tag.setLights(Arrays.asList(TestBaseUtil.createLight()));
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setLights(Arrays.asList(TestBaseUtil.createLight()));
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class);
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setLights(Arrays.asList(TestBaseUtil.createLight()));
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);

		// Error situation - Name length invalid
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setName(StringUtils.repeat("n", ONE_HUNDRED_ONE));
		tagRequest.setTag(tag);
		response = getTagBCF().insertTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_LENGTH_INVALID);
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
	public void testInsertLightToTag()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest(new UserContext(1));
		Tag tag = new Tag();
		tagRequest.getTags().add(tag);
		TagResponse response = getTagBCF().insertLightToTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		resetMocksToTagArea();
		tagRequest = TestBaseUtil.createTagRequest();
		Light light = TestBaseUtil.createLight();
		tag = TestBaseUtil.createTag();
		List<Integer> lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		tag.getLights().add(light);
		tagRequest.getTags().add(tag);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(lightIdsList);
		response = getTagBCF().insertLightToTag(tagRequest);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, INSERT_LIGHT_TO_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		light = TestBaseUtil.createLight();
		tag = TestBaseUtil.createTag();
		lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		tag.getLights().add(light);
		tagRequest.getTags().add(tag);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(lightIdsList);
		response = getTagBCF().insertLightToTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		resetMocksToTagArea();
		tagRequest = TestBaseUtil.createTagRequest();
		light = TestBaseUtil.createLight();
		lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(lightIdsList);
		response = getTagBCF().insertLightToTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, INSERT_LIGHT_TO_TAG);
		tagRequest = TestBaseUtil.createTagRequest();
		light = TestBaseUtil.createLight();
		tag = TestBaseUtil.createTag();
		lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		tag.getLights().add(light);
		tagRequest.getTags().add(tag);
		tagRequest.setTenant(new Tenant(1, null, null, PECO));
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(lightIdsList);
		response = getTagBCF().insertLightToTag(tagRequest);
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
		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(new UserContext(1));

		TagResponse response = getTagBCF().fetchTagsByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		lightRequest = setLightRequest();
		lightRequest.setUserContext(TestBaseUtil.createUserContext());
		LightCriteria lightCriteria = new LightCriteria();
		Light light = TestBaseUtil.createLight();
		lightCriteria.setLightIdList(Arrays.asList(light.getId()));
		lightRequest.setLightCriteria(lightCriteria);
		response = getTagBCF().fetchTagsByLight(lightRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, FETCH_TAGS_BY_LIGHT);
		lightRequest = setLightRequest();
		response = getTagBCF().fetchTagsByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		lightRequest = setLightRequest();
		response = getTagBCF().fetchTagsByLight(lightRequest);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, FETCH_TAGS_BY_LIGHT);
		lightRequest = setLightRequest();
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
		tagRequest.setIncludeLightsToGroup(true);
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
		tagRequest.setIncludeLightsToGroup(true);
		response = getTagBCF().updateAutoGroupTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setId(null);
		tagRequest.setTag(tag);
		tagRequest.setIncludeLightsToGroup(true);
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
		tagRequest.setIncludeLightsToGroup(true);
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
	public void testDeleteLightFromTag()
	{
		// Validation situation
		TagRequest tagRequest = new TagRequest(new UserContext(1));
		Tag tag = new Tag();
		TagResponse response = getTagBCF().deleteLightFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		tagRequest = TestBaseUtil.createTagRequest();
		Light light = TestBaseUtil.createLight();
		List<Integer> lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		tag.getLights().add(light);
		List<Tag> tagList = new ArrayList<Tag>();
		tagList.add(TestBaseUtil.createTag());
		tagRequest.setTags(tagList);
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(lightIdsList);
		response = getTagBCF().deleteLightFromTag(tagRequest);
		assertTrue(response.isOperationSuccess());

		// Exception situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class, DELETE_LIGHT_FROM_TAG);
		response = getTagBCF().deleteLightFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);

		// Error situation
		resetMocksToTagArea();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class, DELETE_LIGHT_FROM_TAG);
		response = getTagBCF().deleteLightFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		response = getTagBCF().deleteLightFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());

		// Error situation
		tagRequest = TestBaseUtil.createTagRequest();
		tag = TestBaseUtil.createTag();
		tag.setId(null);
		lightIdsList = new ArrayList<Integer>();
		lightIdsList.add(1);
		tagRequest.setTag(tag);
		tagRequest.setPaginationAllSelected(true);
		tagRequest.setSelectionPaginationIds(lightIdsList);
		response = getTagBCF().deleteLightFromTag(tagRequest);
		assertFalse(response.isOperationSuccess());

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
