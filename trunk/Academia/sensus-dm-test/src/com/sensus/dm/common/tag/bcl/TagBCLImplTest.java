package com.sensus.dm.common.tag.bcl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.tag.dac.ITagDAC;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class TagBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/tag/tagbclimpltest.xml"})
public class TagBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant INSERT_TAG. */
	private static final String INSERT_TAG = "insertTag";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

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
		super.setArea(getTagBCL(), EPMAreaEnum.ACTION);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests Settings:

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setTagArea();
	}

	/**
	 * Reset mocks to tag area.
	 */
	@After
	public void resetMocksToActionArea()
	{
		resetMocksData(getTagBCL());
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
		// Success, no parameter is required for this method to work...
		InternalResultsResponse<Tag> actionResponse =
				getTagBCL().fetchAllTags(
						new InquiryTagRequest(TestBaseUtil.createUserContext(), TestBaseUtil.createTenant()));
		TestBaseUtil.assertResponse(actionResponse);

	}

	/**
	 * Test insert tag.
	 */
	@Test
	public void testInsertTag()
	{
		// Insert with Fail situation on insertTag
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, INSERT_TAG);

		TagRequest tagRequest = new TagRequest(TestBaseUtil.createTag(), TestBaseUtil.createUserContext());

		InternalResultsResponse<Tag> tagResponse = getTagBCL().insertTag(tagRequest);
		assertEquals(Status.ExceptionError, tagResponse.getStatus());
		resetMocksToActionArea();

		// Insert with fail situation (on method "insertProcess")
		setSituation(getTagBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);

		tagResponse = getTagBCL().insertTag(tagRequest);
		assertEquals(Status.ExceptionError, tagResponse.getStatus());
		resetMocksToActionArea();

		// Insert with fail situation: "can't be inserted"
		setSituation(getTagBCL(), SituationsEnum.EXCEPTION, ITagDAC.class, "canTagBeInserted");

		tagResponse = getTagBCL().insertTag(tagRequest);
		assertEquals(Status.ExceptionError, tagResponse.getStatus());
		resetMocksToActionArea();

		// Success
		tagResponse = getTagBCL().insertTag(tagRequest);
		TestBaseUtil.assertResultResponse(tagResponse);
		resetMocksToActionArea();
	}

	/**
	 * Test insert device to tag.
	 */
	@Test
	public void testInsertDeviceToTag()
	{
		// Success situation
		InquiryDeviceRequest deviceRequest =
				new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createTag(),
						TestBaseUtil.createUserContext());

		InternalResponse response = getTagBCL().insertDeviceToTag(deviceRequest);
		TestBaseUtil.assertResponse(response);

		// Success situation: InquiriyDeviceRequest without device
		deviceRequest = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		deviceRequest.addTag(TestBaseUtil.createTag());
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.GAS);

		response = getTagBCL().insertDeviceToTag(deviceRequest);
		TestBaseUtil.assertResponse(response);

		// Insert Success - for fetchallDevice to Water Meter
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getTagBCL().insertDeviceToTag(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
		resetMocksToActionArea();

		// Insert Success - for fetchallDevice to Electric Meter
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getTagBCL().insertDeviceToTag(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
		resetMocksToActionArea();

		// Insert with fail situation (on method "insertProcess")
		setSituation(getTagBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);

		deviceRequest =
				new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createTag(),
						TestBaseUtil.createUserContext());

		response = getTagBCL().insertDeviceToTag(deviceRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToActionArea();

		// Insert with fail situation (fail when insert device to tag)
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "insertDeviceToTag");

		response = getTagBCL().insertDeviceToTag(deviceRequest);
		TestBaseUtil.assertResponse(response);

		// Insert with fail situation (tag without ID) - occurs error on method "insertTag"
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, INSERT_TAG);

		deviceRequest =
				new InquiryDeviceRequest(TestBaseUtil.createDevice(), new Tag(), TestBaseUtil.createUserContext());
		response = getTagBCL().insertDeviceToTag(deviceRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToActionArea();

	}

	/**
	 * Test delete tag.
	 */
	@Test
	public void testDeleteTag()
	{
		// Success Situation
		TagRequest tagRequest = new TagRequest(TestBaseUtil.createTag(), TestBaseUtil.createUserContext());

		InternalResponse response = getTagBCL().deleteTag(tagRequest);
		TestBaseUtil.assertResponse(response);

		// Delete from tag with error situation (on method "insertProcess")
		setSituation(getTagBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);

		response = getTagBCL().deleteTag(tagRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToActionArea();

		// Delete from tag with error situation (on method "fetchCheckProcessing")
		setSituation(getTagBCL(), SituationsEnum.ERROR, IProcessBCL.class, "fetchCheckProcessing");

		response = getTagBCL().deleteTag(tagRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToActionArea();

		// Delete from tag with error situation
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "deleteTag");

		response = getTagBCL().deleteTag(tagRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToActionArea();

	}

	/**
	 * Test delete device from tag.
	 */
	@Test
	public void testDeleteDeviceFromTag()
	{
		// Success situation
		InquiryDeviceRequest deviceRequest =
				new InquiryDeviceRequest(TestBaseUtil.createDevice(), TestBaseUtil.createTag(),
						TestBaseUtil.createUserContext());
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.GAS);

		InternalResponse response = getTagBCL().deleteDeviceFromTag(deviceRequest);
		TestBaseUtil.assertResponse(response);

		// Success situation (without the tag name)
		deviceRequest.getTags().get(0).setName("");

		response = getTagBCL().deleteDeviceFromTag(deviceRequest);
		TestBaseUtil.assertResponse(response);

		// Success situation (without device)
		deviceRequest = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		deviceRequest.addTag(TestBaseUtil.createTag());
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.GAS);

		response = getTagBCL().deleteDeviceFromTag(deviceRequest);
		TestBaseUtil.assertResponse(response);

		// Delete from tag with error situation (on method "deleteDeviceFromTag")
		setSituation(getTagBCL(), SituationsEnum.ERROR, ITagDAC.class, "deleteDeviceFromTag");

		response = getTagBCL().deleteDeviceFromTag(deviceRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToActionArea();

		// Delete from tag with error situation (on method "insertProcess", when after of delete device from tag, try to
		// insert a process to a deleted tag)
		setSituation(getTagBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);

		response = getTagBCL().deleteDeviceFromTag(deviceRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToActionArea();

		// Insert Success - for fetchallDevice to Water Meter
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getTagBCL().deleteDeviceFromTag(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
		resetMocksToActionArea();

		// Insert Success - for fetchallDevice to Electric Meter
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getTagBCL().deleteDeviceFromTag(deviceRequest);
		assertEquals(Status.OperationSuccess, response.getStatus());
		resetMocksToActionArea();
	}

}
