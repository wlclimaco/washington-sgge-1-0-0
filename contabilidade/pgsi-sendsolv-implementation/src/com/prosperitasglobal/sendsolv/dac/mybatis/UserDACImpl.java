package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IUserDAC;
import com.prosperitasglobal.sendsolv.user.model.User;
import com.prosperitasglobal.sendsolv.user.model.request.InquiryUserRequest;
import com.prosperitasglobal.sendsolv.user.model.request.UserRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
	public InternalResultsResponse<User> fetchAllUsers(InquiryUserRequest inquiryPaginationRequest)
	{

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#insertUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> insertUser(UserRequest userRequest)
	{

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

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

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#updateUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse updateUser(UserRequest userRequest)
	{

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

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
		// doRemove(getSqlSession(), DELETE_GROUPS_USER, userRequest.getUser(), response);
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
		// doRemove(getSqlSession(), DELETE_ROLES_USER, userRequest.getUser(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.dac.IUserDAC#insertRolesToUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> insertRolesToUser(UserRequest userRequest)
	{

		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

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

		return response;
	}

}
