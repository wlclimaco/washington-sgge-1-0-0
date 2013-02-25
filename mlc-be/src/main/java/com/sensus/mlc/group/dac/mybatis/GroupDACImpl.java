package com.sensus.mlc.group.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;
import static com.sensus.mlc.base.util.LCConvertUtil.convertAllowedGroupIdsToStringList;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.dac.IGroupDAC;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.GroupOrderByEnum;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class GroupDACImpl.
 * 
 * @author Gustavo Aragao - QAT.
 */
public class GroupDACImpl extends SqlSessionDaoSupport implements IGroupDAC
{

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "createUser";

	/** The Constant UNSELECTION_PAGINATION_IDS. */
	private static final String UNSELECTION_PAGINATION_IDS = "unselectionPaginationIds";

	/** The Constant GROUPID. */
	private static final String GROUP_ID = "groupId";

	/** The Constant PROPERTY_ENUM_VALUE. */
	private static final String PROPERTY_ENUM_VALUE = "propertyEnumValue";

	/** The Constant TENANTID. */
	private static final String TENANTID = "tenantid";

	/** The Constant SELECTED_IDS. */
	private static final String PAGINATION_ALL_SELECTED = "paginationAllSelected";

	/** The Constant UNSELECTED_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant MAX_SMARTPOINT_COUNT. */
	private static final String MAX_SMARTPOINT_COUNT = "maxSmartpointCount";

	/** The Constant SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS =
			"sensus.mlc.groupvalidator.group.already.exists";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant ALLOWED_GROUP_ID_LIST. */
	private static final String ALLOWED_GROUP_ID_LIST = "allowedGroupIdList";

	/** The Constant GROUP_NAMESPACE. */
	private static final String GROUP_NAMESPACE = "Group.";

	/** The Constant UPDATE_GROUP_CENTER. */
	private static final String UPDATE_GROUP_CENTER = GROUP_NAMESPACE + "updateGroupCenter";

	/** The Constant INSERT_SMARTPOINT_TO_GROUP. */
	private static final String INSERT_SMARTPOINT_TO_GROUP = GROUP_NAMESPACE + "insertSmartPointToGroup";

	/** The Constant INSERT_TO_GROUP. */
	private static final String INSERT_GROUP = GROUP_NAMESPACE + "insertGroup";

	/** The Constant UPDATE_GROUP. */
	private static final String UPDATE_GROUP = GROUP_NAMESPACE + "updateGroup";

	/** The Constant DELETE_SMARTPOINT_FROM_GROUP. */
	private static final String DELETE_SMARTPOINT_FROM_GROUP = GROUP_NAMESPACE + "deleteSmartPointFromGroup";

	/** The Constant DELETE_GROUP. */
	private static final String DELETE_GROUP = GROUP_NAMESPACE + "deleteGroup";

	/** The Constant FETCH_ALL_GROUPS. */
	private static final String FETCH_ALL_GROUPS = GROUP_NAMESPACE + "fetchAllGroups";

	/** The Constant FETCH_GROUP_BY_ID. */
	private static final String FETCH_GROUP_BY_ID = GROUP_NAMESPACE + "fetchGroupById";

	/** The Constant FETCH_GROUP_BY_ID_LIST. */
	private static final String FETCH_GROUP_BY_ID_LIST = GROUP_NAMESPACE + "fetchGroupByIdList";

	/** The Constant COUNT_RUNNING_PROCESS_FOR_GROUP. */
	private static final String COUNT_RUNNING_PROCESS_FOR_GROUP = GROUP_NAMESPACE + "countRunningProcessForGroup";

	/** The Constant COUNT_LIGHT_GROUPS. */
	private static final String COUNT_LIGHT_GROUPS = GROUP_NAMESPACE + "countLightGroups";

	/** The Constant FETCH_GROUPS_BY_LIGHT. */
	private static final String FETCH_GROUPS_BY_LIGHT = GROUP_NAMESPACE + "fetchGroupsByLightId";

	/** The Constant FETCH_GROUPS_BY_SMARTPOINT. */
	private static final String FETCH_GROUPS_BY_SMARTPOINT = GROUP_NAMESPACE + "fetchGroupsBySmartPointId";

	/** The Constant FETCH_GROUP_BY_NAME. */
	private static final String FETCH_GROUP_BY_NAME = GROUP_NAMESPACE + "fetchGroupByName";

	/** The Constant INSERT_SMARTPOINT_TO_AUTO_GROUP. */
	private static final String INSERT_SMARTPOINT_TO_AUTO_GROUP = GROUP_NAMESPACE + "insertSmartPointToAutoGroup";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = GROUP_NAMESPACE + "PaginationTotalRows";

	/** The Constant FETCH_LIGHTS_BELONG_GROUPS_MAX_ALLOWED. */
	private static final String FETCH_LIGHTS_BELONG_GROUPS_MAX_ALLOWED = GROUP_NAMESPACE
			+ "fetchLightsBelongGroupsMaxAllowed";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_GROUP = GROUP_NAMESPACE + "fetchLightsBelongGroup";

	/** The Constant FETCH_COUNT_LIGHTS_FROM_GROUPS. */
	private static final String FETCH_COUNT_LIGHTS_FROM_GROUPS = GROUP_NAMESPACE + "fetchCountLightsFromGroups";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#insertGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		Group group = groupRequest.getGroup();
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		try
		{
			group.setId((Integer)doQueryForObject(getSqlSession(), INSERT_GROUP, groupRequest));
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		response.addResult(group);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#insertSmartPointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToGroup(GroupRequest groupRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(groupRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(lightRequest);
		paramMap.put(GROUP_ID, groupRequest.getGroup().getId());
		paramMap.put(CREATE_USER, groupRequest.getUserContext().getUserId());
		paramMap.put(UNSELECTION_PAGINATION_IDS, null);

		if (!ValidationUtil.isNullOrEmpty(groupRequest.getUnselectionPaginationIds()))
		{
			paramMap.put(UNSELECTION_PAGINATION_IDS, groupRequest.getUnselectionPaginationIds());
		}

		InternalResponse response = new InternalResponse();
		doInsert(getSqlSession(), INSERT_SMARTPOINT_TO_GROUP, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#insertSmartPointToAutoGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> insertSmartPointToAutoGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		doQueryForObject(getSqlSession(), INSERT_SMARTPOINT_TO_AUTO_GROUP, groupRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#updateGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateGroup(GroupRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_GROUP, groupRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#updateSmartPointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateSmartPointToGroup(GroupRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_GROUP_CENTER, groupRequest.getGroup(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#deleteSmartPointFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(groupRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(lightRequest);
		paramMap.put(GROUP_ID, groupRequest.getGroup().getId());

		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_SMARTPOINT_FROM_GROUP, paramMap, response);

		// Any time a point is deleted from a Group, need to update the Groups' center point
		doUpdate(getSqlSession(), UPDATE_GROUP_CENTER, groupRequest.getGroup(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#deleteGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_GROUP, groupRequest.getGroup(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchAllGroups(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryPaginationRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryPaginationRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryPaginationRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryPaginationRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryPaginationRequest.getStartPage());
		paramMap.put(START_ROW, inquiryPaginationRequest.getStartRow());
		paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryPaginationRequest.getAllowedGroupIdList());
		paramMap.put(ORDERBY, GroupOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryPaginationRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryPaginationRequest.getSortExpressions().get(0));
		}

		doQueryForList(getSqlSession(), FETCH_ALL_GROUPS, paramMap, response);
		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryPaginationRequest);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupById(com.sensus.mlc.group.model.Group)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupById(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		doQueryForList(getSqlSession(), FETCH_GROUP_BY_ID, groupRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupsByIdList(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByIdList(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		doQueryForList(getSqlSession(), FETCH_GROUP_BY_ID_LIST, groupRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#canDelete(com.sensus.mlc.group.model.Group)
	 */
	@Override
	public Boolean fetchCanDelete(GroupRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(PROPERTY_ENUM_VALUE, PropertyEnum.GROUP_ID.getValue());
		paramMap.put(GROUP_ID, String.valueOf(groupRequest.getGroup().getId()));
		paramMap.put(ALLOWED_GROUP_ID_LIST, convertAllowedGroupIdsToStringList(groupRequest.getAllowedGroupIdList()));

		// Check if there are any Process running for this group
		return (Integer)doQueryForObject(getSqlSession(), COUNT_RUNNING_PROCESS_FOR_GROUP, paramMap) == 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#countLightGroups(com.sensus.mlc.smartpoint.model.Light)
	 */
	@Override
	public Integer countLightGroups(LightRequest lightRequest)
	{
		return (Integer)doQueryForObject(getSqlSession(), COUNT_LIGHT_GROUPS, lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByLight(LightRequest request)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		doQueryForList(getSqlSession(), FETCH_GROUPS_BY_LIGHT, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchGroupsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsBySmartPoint(LightRequest request)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		doQueryForList(getSqlSession(), FETCH_GROUPS_BY_SMARTPOINT, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupByName(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupByName(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		doQueryForList(getSqlSession(), FETCH_GROUP_BY_NAME, groupRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchLightsWithGroupsMaxAllowed(com.sensus.mlc.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightsWithGroupsMaxAllowed(GroupRequest groupRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(groupRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(lightRequest);
		paramMap.put(MAX_SMARTPOINT_COUNT, groupRequest.getMaxSmartpointCount());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHTS_BELONG_GROUPS_MAX_ALLOWED, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchLightsBelongGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightsBelongGroup(GroupRequest groupRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(groupRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(lightRequest);
		paramMap.put(GROUP_ID, groupRequest.getGroup().getId());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHTS_BELONG_GROUP, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(getSqlSession(), FETCH_COUNT_LIGHTS_FROM_GROUPS, groupRequest, response);
		return response;
	}
}
