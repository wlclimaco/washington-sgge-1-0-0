package com.sensus.lc.user.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;

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
import com.sensus.lc.group.model.Group;
import com.sensus.lc.user.dac.IUserDAC;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.UserOrderByEnum;
import com.sensus.lc.user.model.request.UserRequest;

/**
 * The Class UserDACImpl.
 */
public class UserDACImpl extends SqlSessionDaoSupport implements IUserDAC
{

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(UserDACImpl.class);

	private static final String ALLOWED_GROUP_ID_LIST = "allowedGroupIdList";

	/** The Constant USER_PAGINATION_TOTAL_ROWS. */
	private static final String USER_PAGINATION_TOTAL_ROWS = "User.paginationTotalRows";

	/** The Constant USER_UPDATE_USER. */
	private static final String USER_UPDATE_USER = "User.updateUser";

	/** The Constant DELETE_USER. */
	private static final String DELETE_USER = "User.deleteUser";

	/** The Constant USER_FETCH_ALL_USERS. */
	private static final String USER_FETCH_ALL_USERS = "User.fetchAllUsers";

	/** The Constant USER_FETCH_USER_BY_ID. */
	private static final String USER_FETCH_USER_BY_ID = "User.fetchUserById";

	/** The Constant USER_INSERT_GROUPS_TO_USER. */
	private static final String USER_INSERT_GROUPS_TO_USER = "User.insertGroupsToUser";

	/** The Constant USER_INSERT_ROLES_TO_USER. */
	private static final String USER_INSERT_ROLES_TO_USER = "User.insertRolesToUser";

	/** The Constant USER_INSERT_USER. */
	private static final String USER_INSERT_USER = "User.insertUser";

	/** The Constant USER_FETCH_USER_BY_NAME. */
	private static final String USER_FETCH_USER_BY_NAME = "User.fetchUserByName";

	/** The Constant DELETE_GROUPS_USER. */
	private static final String DELETE_GROUPS_USER = "User.deleteGroupsByUser";

	/** The Constant DELETE_ROLES_USER. */
	private static final String DELETE_ROLES_USER = "User.deleteRolesByUser";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenant_id";

	/** The Constant USER_ID. */
	private static final String USER_ID = "user_id";

	/** The Constant USER_NAME. */
	private static final String USER_NAME = "user_name";

	/** The Constant PASSWORD. */
	private static final String PASSWORD = "password";

	/** The Constant FIRST_NAME. */
	private static final String FIRST_NAME = "first_name";

	/** The Constant LAST_NAME. */
	private static final String LAST_NAME = "last_name";

	/** The Constant EMAIL. */
	private static final String EMAIL = "email";

	/** The Constant ROLE. */
	private static final String ROLE = "role";

	/** The Constant ALL_LIGHTS. */
	private static final String ALL_LIGHTS = "all_lights";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant MODIFY_USER. */
	private static final String MODIFY_USER = "modify_user";

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "group_id";

	/** The Constant UNSELECTED_IDS. */
	private static final String UNSELECTED_IDS = "unSelectedIds";

	/** The Constant SELECTED_IDS. */
	private static final String SELECTED_IDS = "selectedIds";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "orderBy";

	/** The Constant PAGE SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE9. */
	private static final Integer PARAMSIZE9 = 9;

	/** The Constant SENSUS_MLC_USERVALIDATOR_USER_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_USERVALIDATOR_USER_ALREADY_EXISTS =
			"sensus.mlc.uservalidator.user.already.exists";

	/** The Constant SENSUS_MLC_USER_EXCEPTION_INSERT_GROUP_TO_USER. */
	private static final String SENSUS_MLC_USER_EXCEPTION_INSERT_GROUP_TO_USER =
			"sensus.mlc.userbclimpl.insert.group.exception";

	/** The Constant SENSUS_MLC_USER_EXCEPTION_INSERT_ROLE_TO_USER. */
	private static final String SENSUS_MLC_USER_EXCEPTION_INSERT_ROLE_TO_USER =
			"sensus.mlc.userbclimpl.insert.role.exception";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#fetchAllUsers(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<User> fetchAllUsers(InquiryPaginationRequest inquiryPaginationRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(PAGE_SIZE, inquiryPaginationRequest.getPageSize());
		paramMap.put(START_ROW, inquiryPaginationRequest.getStartRow());
		paramMap.put(TENANT_ID, inquiryPaginationRequest.getUserContext().getTenant().getId());
		paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryPaginationRequest.getAllowedGroupIdList());
		paramMap.put(ORDER_BY, UserOrderByEnum.USER_NAME.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryPaginationRequest.getSelectionPaginationIds()))
		{
			if (!ValidationUtil.isNull(inquiryPaginationRequest.getPaginationAllSelected())
					&& inquiryPaginationRequest.getPaginationAllSelected())
			{
				paramMap.put(UNSELECTED_IDS, inquiryPaginationRequest.getSelectionPaginationIds());
			}
			else
			{
				paramMap.put(SELECTED_IDS, inquiryPaginationRequest.getSelectionPaginationIds());
			}
		}
		if (!ValidationUtil.isNullOrEmpty(inquiryPaginationRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryPaginationRequest.getSortExpressions().get(0));
		}

		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), USER_PAGINATION_TOTAL_ROWS, paramMap);

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();
		doQueryForList(getSqlSession(), USER_FETCH_ALL_USERS, paramMap, response);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#insertUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> insertUser(UserRequest userRequest)
	{
		User user = userRequest.getUser();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE9);
		paramMap.put(USER_NAME, user.getUserName());
		paramMap.put(PASSWORD, user.getPassword());
		paramMap.put(FIRST_NAME, user.getFirstName());
		paramMap.put(LAST_NAME, user.getLastName());
		paramMap.put(EMAIL, user.getEmail());
		paramMap.put(ALL_LIGHTS, user.getAllLightsAuth());
		paramMap.put(TENANT_ID, userRequest.getUserContext().getTenant().getId());
		paramMap.put(CREATE_USER, userRequest.getUserContext().getUserId());

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		try
		{
			user.setId((Integer)doQueryForObject(getSqlSession(), USER_INSERT_USER, paramMap));
		}
		catch (DuplicateKeyException ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ex.getMessage());
			}
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_USERVALIDATOR_USER_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		response.addResult(user);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#insertGroupsToUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> insertGroupsToUser(UserRequest userRequest)
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(USER_ID, userRequest.getUser().getId());
		paramMap.put(CREATE_USER, userRequest.getUserContext().getUserId());

		for (Group group : userRequest.getUser().getGroups())
		{
			paramMap.put(GROUP_ID, group.getId());
			Integer inserted =
					(Integer)doQueryForObject(getSqlSession(), USER_INSERT_GROUPS_TO_USER, paramMap);

			if (inserted != 1)
			{
				response.setStatus(Status.ExceptionError);
				response.addMessage(SENSUS_MLC_USER_EXCEPTION_INSERT_GROUP_TO_USER, MessageSeverity.Info,
						MessageLevel.None);
			}
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#fetchUserById(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> fetchUserById(UserRequest userRequest)
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();
		doQueryForList(getSqlSession(), USER_FETCH_USER_BY_ID, userRequest.getUser(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#updateUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse updateUser(UserRequest userRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE9);
		paramMap.put(USER_ID, userRequest.getUser().getId());
		paramMap.put(USER_NAME, userRequest.getUser().getUserName());
		paramMap.put(PASSWORD, userRequest.getUser().getPassword());
		paramMap.put(FIRST_NAME, userRequest.getUser().getFirstName());
		paramMap.put(LAST_NAME, userRequest.getUser().getLastName());
		paramMap.put(EMAIL, userRequest.getUser().getEmail());
		paramMap.put(ALL_LIGHTS, userRequest.getUser().getAllLightsAuth());
		paramMap.put(MODIFY_USER, userRequest.getUserContext().getUserId());

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		try
		{
			doQueryForObject(getSqlSession(), USER_UPDATE_USER, paramMap);
		}
		catch (DuplicateKeyException ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ex.getMessage());
			}
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_USERVALIDATOR_USER_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}
		response.addResult(userRequest.getUser());
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#deleteUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse deleteUser(UserRequest userRequest)
	{
		InternalResponse response = new InternalResponse();
		if (ValidationUtil.isNullOrEmpty(userRequest.getSelectionPaginationIds()))
		{
			return response;
		}

		HashMap<String, Integer> paramMap = new HashMap<String, Integer>(PARAMSIZE2);
		paramMap.put(TENANT_ID, userRequest.getUserContext().getTenant().getId());
		for (Integer id : userRequest.getSelectionPaginationIds())
		{
			paramMap.put(USER_ID, id);
			doRemove(getSqlSession(), DELETE_USER, paramMap, response);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#deleteGroupsByUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse deleteGroupsByUser(UserRequest userRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_GROUPS_USER, userRequest.getUser(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#deleteRolesByUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse deleteRolesByUser(UserRequest userRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_ROLES_USER, userRequest.getUser(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#insertRolesToUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> insertRolesToUser(UserRequest userRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(USER_ID, userRequest.getUser().getId());
		paramMap.put(CREATE_USER, userRequest.getUserContext().getUserId());

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		paramMap.put(ROLE, userRequest.getUser().getRole());
		Integer inserted =
				(Integer)doQueryForObject(getSqlSession(), USER_INSERT_ROLES_TO_USER, paramMap);

		if (inserted != 1)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_USER_EXCEPTION_INSERT_ROLE_TO_USER, MessageSeverity.Info,
					MessageLevel.None);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#fetchUserByName(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> fetchUserByName(UserRequest userRequest)
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(USER_NAME, userRequest.getUser().getUserName());
		paramMap.put(TENANT_ID, userRequest.getUserContext().getTenant().getId());

		doQueryForList(getSqlSession(), USER_FETCH_USER_BY_NAME, paramMap, response);
		return response;
	}

}
