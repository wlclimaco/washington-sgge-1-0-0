package com.sensus.dm.common.tag.dac;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class TagDACImplTest.
 * 
 * @author QAT Global.
 */
public class TagDACImplTest extends AbstractTestBaseDAC
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant TAG_FOR_TEST. */
	private static final String TAG_FOR_TEST = "TAG FOR TEST";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant METER_ALREADY_IN_TAG. */
	private static final String METER_ALREADY_IN_TAG = "sensus.dm.common.tagvalidator.meter.already.exist.tag";

	/** The Constant SENSUS_EPM_TAGVALIDATOR_DEVICE_NOT_IN_TAG. */
	private static final String SENSUS_EPM_TAGVALIDATOR_DEVICE_NOT_IN_TAG =
			"summary.text.processStatusMessage.single.device.not.in.tag";

	/** The Constant SENSUS_EPM_TAGVALIDATOR_TAG_ALREADY_EXISTS. */
	private static final String SENSUS_EPM_TAGVALIDATOR_TAG_ALREADY_EXISTS =
			"sensus.dm.common.tagvalidator.tag.already.exists";

	/** The Constant SENSUS_EPM_TAGVALIDATOR_DEVICE_NOT_IN_TAG. */
	private static final String SENSUS_EPM_TAGVALIDATOR_ID_INVALID_TAG =
			"sensus.dm.common.tagvalidator.meter.id.invalid.tag";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The tag default. */
	private Tag tagDefault;

	/**
	 * Gets the tag default.
	 * 
	 * @return the tag default
	 */
	public Tag getTagDefault()
	{
		return tagDefault;
	}

	/**
	 * Sets the tag default.
	 * 
	 * @param tagDefault the new tag default
	 */
	public void setTagDefault(Tag tagDefault)
	{
		this.tagDefault = tagDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		// Before the test begin... inserts a DEFAULT TAG on DB to re-use after
		// on the methods that will need this object
		setCacheStatementScope(getTagDAC());
		setTagDefault(insertDeviceToTag());
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
		// Success situation
		InquiryTagRequest request =
				new InquiryTagRequest(null, TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());

		InternalResultsResponse<Tag> response =
				getTagDAC().fetchAllTags(request);
		TestBaseUtil.assertResultResponse(response);

		request =
				new InquiryTagRequest(new Tag(), TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());

		response = getTagDAC().fetchAllTags(request);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test can insert tag.
	 */
	@Test
	public void testCanInsertTag()
	{
		// Situation that custom search can be inserted
		Tag tag = new Tag("TAG TEST NAME");
		tag.setDescription(TAG_FOR_TEST);
		TagRequest request =
				new TagRequest(tag, TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		InternalResultsResponse<Boolean> response = getTagDAC().canTagBeInserted(request);
		TestBaseUtil.assertResultResponse(response);

		// Situation that custom search cannot be inserted - TAG ALREADY EXISTS
		request =
				new TagRequest(TestBaseUtil.createTag(), TestBaseUtil.createUserContext(), ServiceTypeEnum.ELECTRIC,
						TestBaseUtil.createTenant());
		response = getTagDAC().canTagBeInserted(request);
		assertMessages(response, SENSUS_EPM_TAGVALIDATOR_TAG_ALREADY_EXISTS);
	}

	/**
	 * Test insert tag.
	 */
	@Test
	public void testInsertTag()
	{
		insertTag(TestBaseUtil.createTag(), ServiceTypeEnum.ELECTRIC);
		insertTag(TestBaseUtil.createTag(), ServiceTypeEnum.WATER);
	}

	/**
	 * Test insert smart point to tag.
	 */
	@Test
	public void testInsertDeviceToTag()
	{
		// insert device to tag.

		// Success situation
		insertDeviceToTag();

		// Error situation: meter already exist on tag.
		InquiryDeviceRequest deviceRequest = new InquiryDeviceRequest(TestBaseUtil.createUserContext());
		deviceRequest.addTag(getTagDefault());
		deviceRequest.addDevice(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER));

		InternalResponse deviceResponse = getTagDAC().insertDeviceToTag(deviceRequest);
		assertEquals(deviceResponse.getMessageInfoList().get(0).getCode(), METER_ALREADY_IN_TAG);

		// Error situation: insert data results in violation of an integrity constraint

		// create a tag
		Tag tag = new Tag("TAG NAME TEST");
		tag.setDescription(TAG_FOR_TEST);

		// create a device
		deviceRequest =
				new InquiryDeviceRequest(new Device(new Radio(BigInteger.valueOf(1))), tag,
						TestBaseUtil.createUserContext());
		deviceResponse = getTagDAC().insertDeviceToTag(deviceRequest);
		assertEquals(deviceResponse.getMessageInfoList().get(0).getCode(), SENSUS_EPM_TAGVALIDATOR_ID_INVALID_TAG);

	}

	/**
	 * Test delete tag.
	 */
	@Test
	public void testDeleteTag()
	{
		// Success Situation
		TagRequest request = new TagRequest(getTagDefault());

		InternalResponse response = getTagDAC().deleteTag(request);
		TestBaseUtil.assertResponse(response);

		// Error Situation (This error will happens because the TAG was deleted earlier).
		response = getTagDAC().deleteTag(request);
		assertEquals(InternalResponse.Status.NoRowsRemovedError, response.getStatus());

	}

	/**
	 * Test delete smart point from tag.
	 */
	@Test
	public void testDeleteDeviceFromTag()
	{
		// delete device from tag...

		// Success situation...
		InquiryDeviceRequest deviceRequest =
				new InquiryDeviceRequest(getFirstDevice(DeviceTypeEnum.ELECTRIC_METER), getTagDefault(),
						TestBaseUtil.createUserContext());

		InternalResponse response = getTagDAC().deleteDeviceFromTag(deviceRequest);
		TestBaseUtil.assertResponse(response);

		// "NO DELETE SITUATION" this situation occurs because the DEVICE was deleted from tag earlier.
		response = getTagDAC().deleteDeviceFromTag(deviceRequest);
		assertEquals(response.getMessageInfoList().get(0).getCode(), SENSUS_EPM_TAGVALIDATOR_DEVICE_NOT_IN_TAG);

	}
}
