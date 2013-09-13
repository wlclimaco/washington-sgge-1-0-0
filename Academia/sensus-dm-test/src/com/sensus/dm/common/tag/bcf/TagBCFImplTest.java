package com.sensus.dm.common.tag.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.bcl.ITagBCL;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.common.tag.model.response.TagResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class TagBCFImplTest.
 * 
 * @author QAT Brazil.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/tag/tagbcfimpltest.xml"})
public class TagBCFImplTest extends AbstractTestBaseBusiness
{

	/** The Constant FETCH_ALL_TAGS. */
	private static final String FETCH_ALL_TAGS = "fetchAllTags";

	/** The Constant DELETE_DEVICE_FROM_TAG. */
	private static final String DELETE_DEVICE_FROM_TAG = "deleteDeviceFromTag";

	/** The Constant DELETE_TAG. */
	private static final String DELETE_TAG = "deleteTag";

	/** The Constant INSERT_DEVICE_TO_TAG. */
	private static final String INSERT_DEVICE_TO_TAG = "insertDeviceToTag";

	/** The Constant INSERT_TAG. */
	private static final String INSERT_TAG = "insertTag";

	/** The Constant NUMBER_TO_REPEAT. */
	private static final Integer NUMBER_TO_REPEAT = 101;

	/** The Constant SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED. */
	private static final String SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED =
			"sensus.epm.orderbyvalidator.sort.required";

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TAG_BCF_EXCEPTION_MSG = "sensus.dm.elec.tagbcfimpl.defaultexception";

	/** The Constant TAG_ID_REQUIRED. */
	private static final String SENSUS_EPM_TAGVALIDATOR_TAG_ID_REQUIRED = "sensus.dm.common.tagvalidator.id.required";

	/** The Constant TAG_NAME_REQUIRED. */
	private static final String SENSUS_EPM_TAGVALIDATOR_TAG_NAME_REQUIRED =
			"sensus.dm.common.tagvalidator.name.required";

	/** The Constant TAG_NAME_INVALID. */
	private static final String SENSUS_EPM_TAGVALIDATOR_TAG_NAME_INVALID = "sensus.dm.common.tagvalidator.name.invalid";

	/** The Constant TAG_NAME_OR_ID_REQUIRED. */
	private static final String TAG_NAME_OR_ID_REQUIRED = "sensus.dm.common.tagvalidator.name.id.required";

	/** The Constant ERROR_CODE. */
	protected static final String ERROR_CODE = "error";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The tag bcf. */
	private ITagBCF tagBCF; // injected by Spring through setter

	/**
	 * Sets the tag bcf.
	 * 
	 * @param iTagBCFParam the new tag bcf
	 */
	@Resource
	public void setTagBCF(ITagBCF iTagBCFParam)
	{
		tagBCF = iTagBCFParam;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tag bcf
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:
	/**
	 * Sets the tag area.
	 */
	public void setTagArea()
	{
		setArea(getTagBCF(), EPMAreaEnum.TAG);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setTagArea();
	}

	/**
	 * Removes the tag area.
	 */
	@After
	public void resetMocksToTagArea()
	{
		resetMocksData(getTagBCF());
		setTagArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all tags.
	 */
	@Test
	public void testFetchAllTags()
	{
		// Validation with fail situation - user context required
		InquiryTagResponse response =
				getTagBCF().fetchAllTags(new InquiryTagRequest(null, TestBaseUtil.createTenant()));
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation with fail situation - sort expression required
		InquiryTagRequest request =
				new InquiryTagRequest(null, TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		response = getTagBCF().fetchAllTags(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED);
		assertNotNull(response);

		// Validation with fail situation - page size and start row
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		TestBaseUtil.createInvalidPageSizeStartRow(request);
		response = getTagBCF().fetchAllTags(request);
		assertMessages(response, START_ROW_INVALID, PAGE_SIZE_INVALID);

		// Validation with fail situation - tenant required
		request = TestBaseUtil.createInquiryTagRequest();
		request.setTenant(null);
		response = getTagBCF().fetchAllTags(request);
		assertMessages(response, TENANT_REQUIRED);

		// Validation with fail situation - customer id required
		request = TestBaseUtil.createInquiryTagRequest();
		request.getTenant().setKey(null);
		response = getTagBCF().fetchAllTags(request);
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Success Situation
		request = TestBaseUtil.createInquiryTagRequest();
		response = getTagBCF().fetchAllTags(request);
		assertTrue(response.isOperationSuccess());
		assertEquals("should bring one tag ", 1, response.getTags().size());
		assertNotNull("must be the ID", response.getTags().get(0).getId());

		// Error situation
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class,
				FETCH_ALL_TAGS);
		response = getTagBCF().fetchAllTags(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class,
				FETCH_ALL_TAGS);
		response = getTagBCF().fetchAllTags(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch tags by device.
	 */
	@Test
	public void testFetchTagsByDevice()
	{
		// Validation with exception situation - tag required
		InquiryTagRequest request = new InquiryTagRequest();
		TagResponse response = getTagBCF().fetchTagsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);

		// Validation with fail situation - user context required
		request.setTag(new Tag());
		response = getTagBCF().fetchTagsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation with fail situation - user id required
		request.setUserContext(new UserContext());
		response = getTagBCF().fetchTagsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Validation with fail situation - device required
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getTagBCF().fetchTagsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation with fail situation - device required
		request.getTag().addDevice(new Device(new Radio()));
		response = getTagBCF().fetchTagsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED);

		// Success situation
		request.getTag().getDevices().clear();
		request.getTag().addDevice(TestBaseUtil.createDevice());
		response = getTagBCF().fetchTagsByDevice(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class,
				FETCH_ALL_TAGS);
		response = getTagBCF().fetchTagsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class,
				FETCH_ALL_TAGS);
		response = getTagBCF().fetchTagsByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert tag.
	 */
	@Test
	public void testInsertTag()
	{
		// Validation with fail situation - User and Service
		TagResponse response = getTagBCF().insertTag(new TagRequest(null, null, null, TestBaseUtil.createTenant()));
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation with fail situation - tag id required
		TagRequest request =
				new TagRequest(null, TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		response = getTagBCF().insertTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_TAGVALIDATOR_TAG_ID_REQUIRED);

		// Validation with fail situation - tag name required
		Tag tag = new Tag(1);
		request = new TagRequest(tag, TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
				TestBaseUtil.createTenant());
		response = getTagBCF().insertTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_TAGVALIDATOR_TAG_NAME_REQUIRED);

		// Validation with fail situation - name length (too large)
		tag.setName(StringUtils.repeat("n", NUMBER_TO_REPEAT));
		request.addTag(tag);
		response = getTagBCF().insertTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_TAGVALIDATOR_TAG_NAME_INVALID);

		// Validation with fail situation - tenant required
		request = new TagRequest(TestBaseUtil.createTag(), TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC);
		response = getTagBCF().insertTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Validation with fail situation - customer required
		request = new TagRequest(TestBaseUtil.createTag(), TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC);
		request.setTenant(new DMTenant(null));
		response = getTagBCF().insertTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Success situation
		request = new TagRequest(TestBaseUtil.createTag(), TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
				TestBaseUtil.createTenant());
		response = getTagBCF().insertTag(request);
		assertTrue(response.isOperationSuccess());
		assertEquals("should bring one custom search ", 1, response.getTags().size());
		assertNotNull("should bring the ID", response.getTags().get(0).getId());

		// Error situation
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class,
				INSERT_TAG);
		response = getTagBCF().insertTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class,
				INSERT_TAG);
		response = getTagBCF().insertTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert device to tag.
	 */
	@Test
	public void testInsertDeviceToTag()
	{
		// Validation with fail situation - sort expression required
		InquiryDeviceRequest request = new InquiryDeviceRequest();
		TagResponse response = getTagBCF().insertDeviceToTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED);

		// Validation with fail situation - name or id required
		request.addTag(new Tag());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getTagBCF().insertDeviceToTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TAG_NAME_OR_ID_REQUIRED);

		// Success situation
		setSituation(getTagBCF(), SituationsEnum.SUCCESS, ITagBCL.class,
				INSERT_DEVICE_TO_TAG);
		request = new InquiryDeviceRequest(null, TestBaseUtil.createTag(), new UserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setTenant(TestBaseUtil.createTenant());
		response = getTagBCF().insertDeviceToTag(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class,
				INSERT_DEVICE_TO_TAG);

		response = getTagBCF().insertDeviceToTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class,
				INSERT_DEVICE_TO_TAG);

		response = getTagBCF().insertDeviceToTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test delete tag.
	 */
	@Test
	public void testDeleteTag()
	{
		// Validation with fail situation - user context required
		TagRequest request = new TagRequest();
		TagResponse response = getTagBCF().deleteTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation with fail situation - user id required
		request.setUserContext(new UserContext());
		response = getTagBCF().deleteTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Validation with fail situation - tag id required
		request = new TagRequest(new Tag(), TestBaseUtil.createUserContext());
		response = getTagBCF().deleteTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_TAGVALIDATOR_TAG_ID_REQUIRED);

		// Success situation
		request = new TagRequest(TestBaseUtil.createTag(), TestBaseUtil.createUserContext());
		response = getTagBCF().deleteTag(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class,
				DELETE_TAG);
		response = getTagBCF().deleteTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class,
				DELETE_TAG);
		response = getTagBCF().deleteTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test delete device from tag.
	 */
	@Test
	public void testDeleteDeviceFromTag()
	{
		// Validation with fail situation - sort expression required
		InquiryDeviceRequest request = new InquiryDeviceRequest();
		TagResponse response = getTagBCF().deleteDeviceFromTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED);

		// Validation with fail situation - tag name or id required
		request = new InquiryDeviceRequest(null, new Tag(), new UserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getTagBCF().insertDeviceToTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TAG_NAME_OR_ID_REQUIRED);

		// Success situation
		request = new InquiryDeviceRequest(null, TestBaseUtil.createTag(), TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getTagBCF().deleteDeviceFromTag(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		request = new InquiryDeviceRequest();
		setSituation(getTagBCF(), SituationsEnum.ERROR, ITagBCL.class,
				DELETE_DEVICE_FROM_TAG);
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.addTag(TestBaseUtil.createTag());
		response = getTagBCF().deleteDeviceFromTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTagArea();

		// Exception situation
		request = new InquiryDeviceRequest(null, TestBaseUtil.createTag(), new UserContext());
		setSituation(getTagBCF(), SituationsEnum.EXCEPTION, ITagBCL.class,
				DELETE_DEVICE_FROM_TAG);
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		response = getTagBCF().deleteDeviceFromTag(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TAG_BCF_EXCEPTION_MSG);
	}
}
