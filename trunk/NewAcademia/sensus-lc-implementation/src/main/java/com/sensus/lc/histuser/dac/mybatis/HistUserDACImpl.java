package com.sensus.lc.histuser.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.histuser.dac.IHistUserDAC;
import com.sensus.lc.histuser.model.HistUser;
import com.sensus.lc.histuser.model.request.HistUserRequest;
import com.sensus.lc.histuser.model.request.InquiryHistUserRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.request.LightRequest;

public class HistUserDACImpl extends SqlSessionDaoSupport implements IHistUserDAC
{

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(HistUserDACImpl.class);

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "createUser";

	/** The Constant GROUPID. */
	private static final String GROUP_ID = "groupId";

	/** The Constant PROPERTY_ENUM_VALUE. */
	private static final String PROPERTY_ENUM_VALUE = "propertyEnumValue";

	/** The Constant TENANTID. */
	private static final String TENANTID = "tenantid";

	/** The Constant SELECTED_IDS. */
	private static final String PAGINATION_ALL_SELECTED = "paginationAllSelected";

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant MAX_LIGHT_COUNT. */
	private static final String MAX_LIGHT_COUNT = "maxLightCount";

	/** The Constant SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS =
			"sensus.mlc.groupvalidator.group.already.exists";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant ALLOWED_GROUP_ID_LIST. */
	private static final String ALLOWED_GROUP_ID_LIST = "allowedHistUserIdList";

	/** The Constant GROUP_NAMESPACE. */
	private static final String GROUP_NAMESPACE = "HistUser.";

	/** The Constant UPDATE_GROUP_CENTER. */
	private static final String UPDATE_GROUP_CENTER = GROUP_NAMESPACE + "updateHistUserCenter";

	/** The Constant INSERT_LIGHT_TO_GROUP. */
	private static final String INSERT_LIGHT_TO_GROUP = GROUP_NAMESPACE + "insertLightToHistUser";

	/** The Constant INSERT_TO_GROUP. */
	private static final String INSERT_GROUP = GROUP_NAMESPACE + "insertHistUser";

	/** The Constant UPDATE_GROUP. */
	private static final String UPDATE_GROUP = GROUP_NAMESPACE + "updateHistUser";

	/** The Constant DELETE_LIGHT_FROM_GROUP. */
	private static final String DELETE_LIGHT_FROM_GROUP = GROUP_NAMESPACE + "deleteLightFromHistUser";

	/** The Constant DELETE_GROUP. */
	private static final String DELETE_GROUP = GROUP_NAMESPACE + "deleteHistUser";

	/** The Constant FETCH_ALL_GROUPS. */
	private static final String FETCH_ALL_GROUPS = GROUP_NAMESPACE + "fetchAllHistUsers";

	/** The Constant FETCH_GROUP_BY_ID. */
	private static final String FETCH_GROUP_BY_ID = GROUP_NAMESPACE + "fetchHistUserById";

	/** The Constant FETCH_GROUP_BY_ID_LIST. */
	private static final String FETCH_GROUP_BY_ID_LIST = GROUP_NAMESPACE + "fetchHistUserByIdList";

	/** The Constant COUNT_RUNNING_PROCESS_FOR_GROUP. */
	private static final String COUNT_RUNNING_PROCESS_FOR_GROUP = GROUP_NAMESPACE + "countRunningProcessForHistUser";

	/** The Constant FETCH_GROUPS_BY_LIGHT. */
	private static final String FETCH_GROUPS_BY_LIGHT = GROUP_NAMESPACE + "fetchHistUsersByLightId";

	/** The Constant FETCH_GROUP_BY_NAME. */
	private static final String FETCH_GROUP_BY_NAME = GROUP_NAMESPACE + "fetchHistUserByName";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = GROUP_NAMESPACE + "PaginationTotalRows";

	/** The Constant FETCH_LIGHTS_BELONG_GROUPS_MAX_ALLOWED. */
	private static final String FETCH_LIGHTS_BELONG_GROUPS_MAX_ALLOWED = GROUP_NAMESPACE
			+ "fetchLightsBelongHistUsersMaxAllowed";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_GROUP = GROUP_NAMESPACE + "fetchLightsBelongHistUser";

	/** The Constant FETCH_COUNT_LIGHTS_FROM_GROUPS. */
	private static final String FETCH_COUNT_LIGHTS_FROM_GROUPS = GROUP_NAMESPACE + "fetchCountLightsFromHistUsers";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IHistUserDAC#insertHistUser(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResultsResponse<HistUser> insertHistUser(HistUserRequest groupRequest)
	{
		HistUser group = groupRequest.getHistUser();
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		try
		{
			group.setId((Integer)doQueryForObject(getSqlSession(), INSERT_GROUP, groupRequest));
		}
		catch (DuplicateKeyException ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ex.getMessage());
			}
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		response.addResult(group);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#insertLightToHistUser(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResponse insertLightToHistUser(HistUserRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);

		paramMap.put(GROUP_ID, groupRequest.getHistUser().getId());
		paramMap.put(CREATE_USER, groupRequest.getUserContext().getUserId());
		paramMap.put(SELECTION_PAGINATION_IDS, groupRequest.getSelectionPaginationIds());

		InternalResponse response = new InternalResponse();
		doInsert(getSqlSession(), INSERT_LIGHT_TO_GROUP, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IHistUserDAC#updateHistUser(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResponse updateHistUser(HistUserRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_GROUP, groupRequest);
		}
		catch (DuplicateKeyException ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ex.getMessage());
			}
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_GROUPVALIDATOR_GROUP_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#updateLightToHistUser(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResponse updateLightToHistUser(HistUserRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_GROUP_CENTER, groupRequest.getHistUser(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#deleteLightFromHistUser(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResponse deleteLightFromHistUser(HistUserRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);

		paramMap.put(GROUP_ID, groupRequest.getHistUser().getId());
		paramMap.put(SELECTION_PAGINATION_IDS, groupRequest.getSelectionPaginationIds());

		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LIGHT_FROM_GROUP, paramMap, response);

		// Any time a point is deleted from a HistUser, need to update the HistUsers' center point
		doUpdate(getSqlSession(), UPDATE_GROUP_CENTER, groupRequest.getHistUser(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IHistUserDAC#deleteHistUser(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResponse deleteHistUser(HistUserRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_GROUP, groupRequest.getHistUser(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#fetchAllHistUsers(com.sensus.mlc.base.model.request.InquiryPaginationRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<HistUser> fetchAllHistUsers(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryPaginationRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryPaginationRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryPaginationRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryPaginationRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryPaginationRequest.getStartPage());
		paramMap.put(START_ROW, inquiryPaginationRequest.getStartRow());
		paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryPaginationRequest.getAllowedHistUserIdList());
		paramMap.put(ORDERBY, HistUserOrderByEnum.NAME_COLUMN.getValue());

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
	 * @see com.sensus.mlc.group.dac.IHistUserDAC#fetchHistUserById(com.sensus.mlc.group.model.HistUser)
	 */
	@Override
	public InternalResultsResponse<HistUser> fetchHistUserById(HistUserRequest groupRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		doQueryForList(getSqlSession(), FETCH_GROUP_BY_ID, groupRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#fetchHistUsersByIdList(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResultsResponse<HistUser> fetchHistUsersByIdList(HistUserRequest groupRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		doQueryForList(getSqlSession(), FETCH_GROUP_BY_ID_LIST, groupRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IHistUserDAC#canDelete(com.sensus.mlc.group.model.HistUser)
	 */
	@Override
	public Boolean fetchCanDelete(HistUserRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(PROPERTY_ENUM_VALUE, PropertyEnum.GROUP_ID.getValue());
		paramMap.put(GROUP_ID, String.valueOf(groupRequest.getHistUser().getId()));
		paramMap.put(ALLOWED_GROUP_ID_LIST,
				convertAllowedHistUserIdsToStringList(groupRequest.getAllowedHistUserIdList()));

		// Check if there are any Process running for this group
		return (Integer)doQueryForObject(getSqlSession(), COUNT_RUNNING_PROCESS_FOR_GROUP, paramMap) == 0;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#fetchHistUserByName(com.sensus.mlc.group.model.request.HistUserRequest)
	 */
	@Override
	public InternalResultsResponse<HistUser> fetchHistUserByName(HistUserRequest groupRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		doQueryForList(getSqlSession(), FETCH_GROUP_BY_NAME, groupRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#fetchLightsWithHistUsersMaxAllowed(com.sensus.mlc.group.model.request.
	 * HistUserRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightsWithHistUsersMaxAllowed(HistUserRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);

		paramMap.put(MAX_LIGHT_COUNT, groupRequest.getMaxLightCount());
		paramMap.put(ALLOWED_GROUP_ID_LIST, groupRequest.getAllowedHistUserIdList());
		paramMap.put(SELECTION_PAGINATION_IDS, groupRequest.getSelectionPaginationIds());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHTS_BELONG_GROUPS_MAX_ALLOWED, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#fetchLightsBelongHistUser(com.sensus.mlc.group.model.request.HistUserRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightsBelongHistUser(HistUserRequest groupRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(GROUP_ID, groupRequest.getHistUser().getId());
		paramMap.put(ALLOWED_GROUP_ID_LIST, groupRequest.getAllowedHistUserIdList());
		paramMap.put(SELECTION_PAGINATION_IDS, groupRequest.getSelectionPaginationIds());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHTS_BELONG_GROUP, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IHistUserDAC#fetchCountLightsFromHistUsers(com.sensus.mlc.group.model.request.
	 * HistUserRequest)
	 */
	@Override
	public InternalResultsResponse<HistUser> fetchCountLightsFromHistUsers(HistUserRequest groupRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		doQueryForList(getSqlSession(), FETCH_COUNT_LIGHTS_FROM_GROUPS, groupRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IHistUserDAC#fetchHistUsersByLight(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<HistUser> fetchHistUsersByLight(LightRequest request)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		doQueryForList(getSqlSession(), FETCH_GROUPS_BY_LIGHT, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<HistUser> fetchHistUserById(InquiryHistUserRequest histUserRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<HistUser> fetchHistUserByUser(InquiryHistUserRequest histUserRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<HistUser> fetchAllHistUsers(InquiryHistUserRequest histUserRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
