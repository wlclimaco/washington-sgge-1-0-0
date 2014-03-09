package com.sensus.lc.histuser.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.histuser.dac.IHistUserDAC;
import com.sensus.lc.histuser.model.HistUser;
import com.sensus.lc.histuser.model.request.HistUserRequest;
import com.sensus.lc.histuser.model.request.InquiryHistUserRequest;

public class HistUserDACImpl extends SqlSessionDaoSupport implements IHistUserDAC
{

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(HistUserDACImpl.class);

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "createUser";

	/** The Constant HISTUSERID. */
	private static final String HISTUSER_ID = "histUserId";

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

	/** The Constant SENSUS_MLC_HISTUSERVALIDATOR_HISTUSER_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_HISTUSERVALIDATOR_HISTUSER_ALREADY_EXISTS =
			"sensus.mlc.histUservalidator.histUser.already.exists";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant ALLOWED_HISTUSER_ID_LIST. */
	private static final String ALLOWED_HISTUSER_ID_LIST = "allowedHistUserIdList";

	/** The Constant HISTUSER_NAMESPACE. */
	private static final String HISTUSER_NAMESPACE = "HistUser.";

	/** The Constant UPDATE_HISTUSER_CENTER. */
	private static final String UPDATE_HISTUSER_CENTER = HISTUSER_NAMESPACE + "updateHistUserCenter";

	/** The Constant INSERT_LIGHT_TO_HISTUSER. */
	private static final String INSERT_LIGHT_TO_HISTUSER = HISTUSER_NAMESPACE + "insertLightToHistUser";

	/** The Constant INSERT_TO_HISTUSER. */
	private static final String INSERT_HISTUSER = HISTUSER_NAMESPACE + "insertHistUser";

	/** The Constant UPDATE_HISTUSER. */
	private static final String UPDATE_HISTUSER = HISTUSER_NAMESPACE + "updateHistUser";

	/** The Constant DELETE_LIGHT_FROM_HISTUSER. */
	private static final String DELETE_LIGHT_FROM_HISTUSER = HISTUSER_NAMESPACE + "deleteLightFromHistUser";

	/** The Constant DELETE_HISTUSER. */
	private static final String DELETE_HISTUSER = HISTUSER_NAMESPACE + "deleteHistUser";

	/** The Constant FETCH_ALL_HISTUSERS. */
	private static final String FETCH_ALL_HISTUSERS = HISTUSER_NAMESPACE + "fetchAllHistUsers";

	/** The Constant FETCH_HISTUSER_BY_ID. */
	private static final String FETCH_HISTUSER_BY_ID = HISTUSER_NAMESPACE + "fetchHistUserById";

	/** The Constant FETCH_HISTUSER_BY_ID_LIST. */
	private static final String FETCH_HISTUSER_BY_ID_LIST = HISTUSER_NAMESPACE + "fetchHistUserByIdList";

	/** The Constant COUNT_RUNNING_PROCESS_FOR_HISTUSER. */
	private static final String COUNT_RUNNING_PROCESS_FOR_HISTUSER = HISTUSER_NAMESPACE
			+ "countRunningProcessForHistUser";

	/** The Constant FETCH_HISTUSERS_BY_LIGHT. */
	private static final String FETCH_HISTUSERS_BY_LIGHT = HISTUSER_NAMESPACE + "fetchHistUsersByLightId";

	/** The Constant FETCH_HISTUSER_BY_NAME. */
	private static final String FETCH_HISTUSER_BY_NAME = HISTUSER_NAMESPACE + "fetchHistUserByName";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = HISTUSER_NAMESPACE + "PaginationTotalRows";

	/** The Constant FETCH_LIGHTS_BELONG_HISTUSERS_MAX_ALLOWED. */
	private static final String FETCH_LIGHTS_BELONG_HISTUSERS_MAX_ALLOWED = HISTUSER_NAMESPACE
			+ "fetchLightsBelongHistUsersMaxAllowed";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_HISTUSER = HISTUSER_NAMESPACE + "fetchLightsBelongHistUser";

	/** The Constant FETCH_COUNT_LIGHTS_FROM_HISTUSERS. */
	private static final String FETCH_COUNT_LIGHTS_FROM_HISTUSERS = HISTUSER_NAMESPACE
			+ "fetchCountLightsFromHistUsers";

	private static final String userId = null;

	private static final String FETCH_ALL_HISTUSERSBYID = HISTUSER_NAMESPACE + "fetchAllGrupomusculars";

	private static final String FETCH_ALL_HISTUSERSBYUSERS = HISTUSER_NAMESPACE + "fetchAllGrupomusculars";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.histUser.dac.IHistUserDAC#insertHistUser(com.sensus.mlc.histUser.model.request.HistUserRequest)
	 */
	@Override
	public InternalResponse insertHistUser(HistUserRequest histUserRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);

		paramMap.put("cdtable", histUserRequest.getFirstHistUser().getCdTable());
		paramMap.put("id", histUserRequest.getFirstHistUser().getId());
		paramMap.put("acao", histUserRequest.getFirstHistUser().getAcao());
		paramMap.put("create_date", new Date());
		paramMap.put("create_user", histUserRequest.getFirstHistUser().getCdUser().getUserName());
		paramMap.put("tenant_id", histUserRequest.getFirstHistUser().getTenantid());

		InternalResponse response = new InternalResponse();
		doInsert(getSqlSession(), INSERT_HISTUSER, paramMap, response);
		return response;
	}

	@Override
	public InternalResultsResponse<HistUser> fetchHistUserById(InquiryHistUserRequest histUserRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(TENANTID, histUserRequest.getUserContext().getTenant().getId());
		paramMap.put(userId, histUserRequest.getPageSize());
		paramMap.put(ORDERBY, "cdHistUser");

		if (!ValidationUtil.isNullOrEmpty(histUserRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, histUserRequest.getSortExpressions().get(0));
		}

		doQueryForList(getSqlSession(), FETCH_ALL_HISTUSERSBYID, paramMap, response);

		return response;
	}

	@Override
	public InternalResultsResponse<HistUser> fetchHistUserByUser(InquiryHistUserRequest histUserRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(TENANTID, histUserRequest.getUserContext().getTenant().getId());
		paramMap.put(userId, histUserRequest.getPageSize());
		paramMap.put(ORDERBY, "cdHistUser");

		if (!ValidationUtil.isNullOrEmpty(histUserRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, histUserRequest.getSortExpressions().get(0));
		}

		doQueryForList(getSqlSession(), FETCH_ALL_HISTUSERSBYUSERS, paramMap, response);

		return response;
	}

	@Override
	public InternalResultsResponse<HistUser> fetchAllHistUsers(InquiryHistUserRequest histUserRequest)
	{
		InternalResultsResponse<HistUser> response = new InternalResultsResponse<HistUser>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(TENANTID, histUserRequest.getUserContext().getTenant().getId());
		paramMap.put(userId, histUserRequest.getPageSize());
		paramMap.put(ORDERBY, "cdHistUser");

		if (!ValidationUtil.isNullOrEmpty(histUserRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, histUserRequest.getSortExpressions().get(0));
		}

		doQueryForList(getSqlSession(), FETCH_ALL_HISTUSERS, paramMap, response);

		return response;
	}
}
