package com.sensus.dm.common.tag.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.dac.ITagDAC;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;

/**
 * The Class TagDACImpl.
 * 
 * @author QAT Global
 */
public class TagDACImpl extends SqlSessionDaoSupport implements ITagDAC
{

	/** The Constant TAG. */
	private static final String TAG = "tag";

	/** The Constant GROUPSET_ID. */
	private static final String GROUPSET_ID = "groupset_id";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "group_name";

	/** The Constant GROUP_TYPE. */
	private static final String GROUP_TYPE = "group_type";

	/** The Constant GROUP_TYPE_VALUE. */
	private static final Integer GROUP_TYPE_VALUE = 2;

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant MODIFIED_USER. */
	private static final String SERVICE_TYPE = "service_type";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customer_id";

	/** The Constant INSERT_TYPE. */
	private static final String INSERT_TYPE = "insert_type";

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE1. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE7. */
	private static final Integer PARAMSIZE7 = 7;

	/** The Constant METER_ALREADY_IN_TAG. */
	private static final String METER_ALREADY_IN_TAG = "sensus.dm.common.tagvalidator.meter.already.exist.tag";

	/** The Constant TAG_ALREADY_EXISTS. */
	private static final String TAG_ALREADY_EXISTS = "sensus.dm.common.tagvalidator.tag.already.exists";

	/** The Constant TAG_MAP. */
	private static final String TAG_MAP = "TagMap.";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = TAG_MAP + "fetchAllTags";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = TAG_MAP + "paginationTotalRows";

	/** The Constant GROUP_MAP. */
	private static final String GROUP_MAP = "GroupMap.";

	/** The Constant DELETE_TAG. */
	private static final String DELETE_TAG = GROUP_MAP + "deleteGroup";

	/** The Constant DELETE_DEVICE_FROM_TAG. */
	private static final String DELETE_DEVICE_FROM_TAG = GROUP_MAP + "deleteDeviceFromGroup";

	/** The Constant INSERT_DEVICE_TO_TAG. */
	private static final String INSERT_DEVICE_TO_TAG = GROUP_MAP + "insertDeviceToGroup";

	/** The Constant INSERT_TAG. */
	private static final String INSERT_TAG = GROUP_MAP + "insertGroup";

	/** The Constant COUNT_TAG_BY_NAME. */
	private static final String COUNT_TAG_BY_NAME = GROUP_MAP + "countGroupByName";

	/** The Constant DEVICE_NOT_IN_TAG. */
	private static final String DEVICE_NOT_IN_TAG = "summary.text.processStatusMessage.single.device.not.in.tag";

	/** The Constant METER_ID_INVALID_TAG. */
	private static final String METER_ID_INVALID_TAG = "sensus.dm.common.tagvalidator.meter.id.invalid.tag";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#fetchAllTags(com.sensus.dm.common.tag.model.request.InquiryTagRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();

		if (inquiryTagRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryTagRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL, inquiryTagRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.tag.dac.ITagDAC#insertDeviceToTag(com.sensus.dm.common.device.model.request.DeviceRequest)
	 */
	@Override
	public InternalResponse insertDeviceToTag(InquiryDeviceRequest deviceRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(GROUPSET_ID, deviceRequest.getFirstTag().getId());
		paramMap.put(FLEXNET_ID, deviceRequest.getFirstDevice().getRadio().getFlexNetId());
		paramMap.put(CREATE_USER, deviceRequest.getUserContext().getUserId());

		try
		{
			Integer result =
					(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_DEVICE_TO_TAG,
							paramMap);

			if (ValidationUtil.isNullOrZero(result))
			{
				response.setStatus(InternalResponse.Status.NoRowsInsertedError);
			}
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(METER_ALREADY_IN_TAG, MessageSeverity.Error, MessageLevel.None);
		}
		catch (DataIntegrityViolationException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(METER_ID_INVALID_TAG, MessageSeverity.Error, MessageLevel.None);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#insertTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);

		paramMap.put(GROUP_NAME, tagRequest.getFirstTag().getName());
		paramMap.put(GROUP_TYPE, GROUP_TYPE_VALUE);
		paramMap.put(CREATE_USER, tagRequest.getUserContext().getUserId());
		paramMap.put(SERVICE_TYPE, tagRequest.getServiceTypeEnum().getServiceGroupType());
		paramMap.put(CUSTOMER_ID, tagRequest.getTenant().getKey());
		paramMap.put(INSERT_TYPE, TAG);

		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();

		Integer tagId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_TAG, paramMap);

		if (!ValidationUtil.isNull(tagId))
		{
			tagRequest.getFirstTag().setId(tagId);
			response.addResult(tagRequest.getFirstTag());
		}
		else
		{
			response.setStatus(Status.NoRowsInsertedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#deleteTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteTag(TagRequest tagRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(GROUPSET_ID, tagRequest.getFirstTag().getId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_TAG, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.tag.dac.ITagDAC#deleteDeviceFromTag(com.sensus.dm.common.device.model.request.DeviceRequest)
	 */
	@Override
	public InternalResponse deleteDeviceFromTag(InquiryDeviceRequest deviceRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(GROUPSET_ID, deviceRequest.getFirstTag().getId());
		paramMap.put(FLEXNET_ID, deviceRequest.getFirstDevice().getRadio().getFlexNetId());

		Integer result =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_DEVICE_FROM_TAG, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.getMessageInfoList().add(new MessageInfo(DEVICE_NOT_IN_TAG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.dac.ITagDAC#canTagBeInserted(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> canTagBeInserted(TagRequest tagRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();
		if (isTagNameUnique(tagRequest))
		{
			response.getResultsList().add(Boolean.TRUE);
			return response;
		}

		response.getResultsList().add(Boolean.FALSE);

		response.getMessageInfoList().add(new MessageInfo(TAG_ALREADY_EXISTS,
				Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation));

		response.setStatus(Status.ExceptionError);

		return response;
	}

	/**
	 * Checks if is tag name unique.
	 * 
	 * @param tagRequest the tagRequest
	 * @return the boolean
	 */
	private Boolean isTagNameUnique(TagRequest tagRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(GROUP_NAME, tagRequest.getFirstTag().getName());
		paramMap.put(GROUP_TYPE, GROUP_TYPE_VALUE);
		paramMap.put(SERVICE_TYPE, tagRequest.getServiceTypeEnum().getServiceGroupType());
		paramMap.put(CUSTOMER_ID, tagRequest.getTenant().getKey());

		return ValidationUtil.isNullOrZero(
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), COUNT_TAG_BY_NAME, paramMap));

	}
}
