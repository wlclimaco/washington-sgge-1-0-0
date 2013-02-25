package com.sensus.mlc.tag.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCPropertiesExtractorUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.dac.ITagDAC;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.TagOrderByEnum;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Class TagDACImpl.
 */
public class TagDACImpl extends SqlSessionDaoSupport implements ITagDAC
{

	/** The Constant TAG_NAMESPACE. */
	private static final String TAG_NAMESPACE = "Tag.";

	/** The Constant FETCH_TAG_BY_NAME. */
	private static final String FETCH_TAG_BY_NAME = TAG_NAMESPACE + "fetchTagByName";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = TAG_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_TAG. */
	private static final String DELETE_SMART_POINT_FROM_TAG = TAG_NAMESPACE + "deleteSmartPointFromTag";

	/** The Constant FETCH_TAG_BY_ID. */
	private static final String FETCH_TAG_BY_ID = TAG_NAMESPACE + "fetchTagById";

	/** The Constant FETCH_TAG_NAME_BY_ID. */
	private static final String FETCH_TAG_NAME_BY_ID = TAG_NAMESPACE + "fetchTagNameById";

	/** The Constant UPDATE_AUTO_GROUP_TAG. */
	private static final String UPDATE_AUTO_GROUP_TAG = TAG_NAMESPACE + "updateAutoGroupTag";

	/** The Constant DELETE_TAG. */
	private static final String DELETE_TAG = TAG_NAMESPACE + "deleteTag";

	/** The Constant FETCH_TAGS_BY_LIGHT_ID. */
	private static final String FETCH_TAGS_BY_LIGHT_ID = TAG_NAMESPACE + "fetchTagsByLightId";

	/** The Constant FETCH_TAGS_BY_SMART_POINT_ID. */
	private static final String FETCH_TAGS_BY_SMART_POINT_ID = TAG_NAMESPACE + "fetchTagsBySmartPointId";

	/** The Constant INSERT_TAG. */
	private static final String INSERT_TAG = TAG_NAMESPACE + "insertTag";

	/** The Constant INSERT_SMART_POINT_TO_TAG. */
	private static final String INSERT_SMART_POINT_TO_TAG = TAG_NAMESPACE + "insertSmartPointToTag";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_GROUP = TAG_NAMESPACE + "fetchLightsBelongGroup";

	/** The Constant FETCH_ALL_TAGS. */
	private static final String FETCH_ALL_TAGS = TAG_NAMESPACE + "fetchAllTags";

	/** The Constant GROUPID. */
	private static final String TAG_ID = "tagId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant GROUPNAME. */
	private static final String TAGNAME = "tagname";

	/** The Constant AUTOGROUP. */
	private static final String AUTOGROUP = "autogroup";

	/** The Constant AUTOGROUP. */
	private static final String ADDRESS_RELATED = "address_related";

	/** The Constant CREATEUSER. */
	private static final String CREATEUSER = "createuser";

	/** The Constant LIGHT_ID. */
	private static final String LIGHT_ID = "lightid";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant UNSELECTION_PAGINATION_IDS. */
	private static final String UNSELECTION_PAGINATION_IDS = "unselectionPaginationIds";

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE6 = 5;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#fetchAllTags(com.sensus.mlc.tag.model.request.InquiryTagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID, inquiryTagRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryTagRequest.getPageSize());
		paramMap.put(START_ROW, inquiryTagRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryTagRequest.getStartPage());
		paramMap.put(ORDERBY, TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryTagRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryTagRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryTagRequest.getTag()))
		{
			paramMap.put(TAG_ID, inquiryTagRequest.getTag().getId());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_TAGS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#fetchTagByName(com.sensus.mlc.tag.model.Tag)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagByName(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		doQueryForList(getSqlSession(), FETCH_TAG_BY_NAME, tagRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#insertSmartPointToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(tagRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(lightRequest);
		paramMap.put(TAG_ID, LCPropertiesExtractorUtil.extractTagId(tagRequest.getTags()));
		paramMap.put(CREATEUSER, tagRequest.getUserContext().getUserId());
		paramMap.put(UNSELECTION_PAGINATION_IDS, null);

		if (!ValidationUtil.isNullOrEmpty(tagRequest.getUnselectionPaginationIds()))
		{
			paramMap.put(UNSELECTION_PAGINATION_IDS, tagRequest.getUnselectionPaginationIds());
		}

		InternalResponse response = new InternalResponse();
		doInsert(getSqlSession(), INSERT_SMART_POINT_TO_TAG, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#insertTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Tag tag = tagRequest.getTag();
		paramMap.put(TAGNAME, tag.getName());
		paramMap.put(ADDRESS_RELATED, tag.getAddressRelated());
		paramMap.put(TENANT_ID, tagRequest.getTenant().getId());
		paramMap.put(CREATEUSER, tagRequest.getUserContext().getUserId());
		paramMap.put(AUTOGROUP, false);

		tag.setId((Integer)doQueryForObject(getSqlSession(), INSERT_TAG, paramMap));
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		response.addResult(tag);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#fetchTagsBySmartPoint(com.sensus.mlc.smartpoint.model.Light)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsBySmartPoint(LightRequest request)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		doQueryForList(getSqlSession(), FETCH_TAGS_BY_SMART_POINT_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#fetchTagsByLight(com.sensus.mlc.smartpoint.model.Light)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsByLight(LightRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put(LIGHT_ID, request.getFirstLight().getId());
		paramMap.put(TENANT_ID, request.getTenant().getId());

		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		doQueryForList(getSqlSession(), FETCH_TAGS_BY_LIGHT_ID, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#deleteTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteTag(TagRequest tagRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_TAG, tagRequest.getTag(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#updateAutoGroupTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> updateAutoGroupTag(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		doUpdate(getSqlSession(), UPDATE_AUTO_GROUP_TAG, tagRequest.getTag(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#fetchTagById(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagById(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		doQueryForList(getSqlSession(), FETCH_TAG_BY_ID, tagRequest.getTag(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dac.ITagDAC#fetchTagNameById(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagNameById(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();
		doQueryForList(getSqlSession(), FETCH_TAG_NAME_BY_ID, tagRequest.getTag(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.dao.ITagDAO#deleteSmartPointFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteSmartPointFromTag(TagRequest tagRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(tagRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(lightRequest);
		paramMap.put(TAG_ID, LCPropertiesExtractorUtil.extractTagId(tagRequest.getTags()));

		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), DELETE_SMART_POINT_FROM_TAG, paramMap, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Light> fetchLightsBelongTag(TagRequest tagRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(tagRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(lightRequest);
		paramMap.put(TAG_ID, tagRequest.getTag().getId());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHTS_BELONG_GROUP, paramMap, response);
		return response;
	}

}
