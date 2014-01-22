package com.sensus.lc.user.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createInquiryPaginationRequest;
import static com.sensus.lc.base.TestBaseUtil.createUser;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static com.sensus.lc.base.TestBaseUtil.createUserRequest;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.UserRequest;

/**
 * The Class UserDACImplTest.
 */
public class UserDACImplTest extends AbstractTestBaseDAC
{

	/** The Constant RESPONSE_IS_NOT_IN_ERROR. */
	private static final String RESPONSE_IS_NOT_IN_ERROR = "Response is not in Error";

	/** The Constant TOTAL_LIGHTS. */
	private static final String TOTAL_LIGHTS = "total_lights";

	/** The Constant ADMIN. */
	private static final String ADMIN = "Admin";

	/** The Constant SENSUS_MLC_USERVALIDATOR_USER_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_USERVALIDATOR_USER_ALREADY_EXISTS =
			"sensus.mlc.uservalidator.user.already.exists";

	/** The Constant ARBITRARY_USER_ID_4. */
	private static final Integer ARBITRARY_USER_ID_4 = 4;

	/** The Constant ARBITRARY_USER_ID_3. */
	private static final Integer ARBITRARY_USER_ID_3 = 3;

	/** The user dac. */
	private IUserDAC userDAC;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.AbstractTestBaseDAC#getUserDAC()
	 */
	@Override
	public IUserDAC getUserDAC()
	{
		return userDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.AbstractTestBaseDAC#setUserDAC(com.sensus.lc.user.dac.IUserDAC)
	 */
	@Override
	@Resource
	public void setUserDAC(IUserDAC userDAC)
	{
		this.userDAC = userDAC;
	}

	/** The user default. */
	private User userDefault;

	/**
	 * Gets the user default.
	 * 
	 * @return the user default
	 */
	public User getUserDefault()
	{
		return userDefault;
	}

	/**
	 * Sets the user default.
	 * 
	 * @param userDefault the new user default
	 */
	public void setUserDefault(User userDefault)
	{
		this.userDefault = userDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		if (getUserDefault() == null)
		{
			setUserDefault(insertUser());
			createUserContext().setUserId(getUserDefault().getUserName());
		}
	}

	/**
	 * Test fetch all users.
	 */
	@Test
	public void testFetchAllUsers()
	{
		InquiryPaginationRequest request = createInquiryPaginationRequest();

		InternalResultsResponse<User> response = getUserDAC().fetchAllUsers(request);
		assertResultResponse(response);

		// Fetch all without filter and Pagination All Selected = True
		SortExpression sortExpression = new SortExpression();
		sortExpression.setField(TOTAL_LIGHTS);
		request.addSortExpressions(sortExpression);
		List<Integer> listIds = new ArrayList<Integer>();

		listIds.add(1);
		listIds.add(2);

		request.setSelectionPaginationIds(listIds);
		request.setPaginationAllSelected(Boolean.TRUE);
		response = getUserDAC().fetchAllUsers(request);
		assertResultResponse(response);

		// Fetch all without filter and Pagination All Selected = False
		sortExpression = new SortExpression();
		sortExpression.setField(TOTAL_LIGHTS);
		request.addSortExpressions(sortExpression);
		listIds = new ArrayList<Integer>();

		listIds.add(ARBITRARY_USER_ID_3);
		listIds.add(ARBITRARY_USER_ID_4);

		request.setSelectionPaginationIds(listIds);
		request.setPaginationAllSelected(Boolean.FALSE);
		response = getUserDAC().fetchAllUsers(request);
		assertResponse(response);
		assertEquals(response.getMessageInfoList().size(), 0);
	}

	/**
	 * Test insert user already exists.
	 */
	@Test
	public void testInsertUserAlreadyExists()
	{
		User user = getUserDefault();
		UserRequest userRequest = new UserRequest();
		userRequest.setUser(user);
		userRequest.setUserContext(TestBaseUtil.createUserContext());
		InternalResultsResponse<User> response = getUserDAC().insertUser(userRequest);
		assertEquals("Try to insert One user that already exists", 1, response.getMessageInfoList().size());
		assertNotNull(response);

	}

	/**
	 * Test insert user.
	 */
	@Test
	public void testInsertUser()
	{
		UserRequest userRequest = new UserRequest();
		userRequest.setUser(createUser());
		userRequest.setUserContext(TestBaseUtil.createUserContext());
		InternalResultsResponse<User> response = getUserDAC().insertUser(userRequest);
		assertEquals("Insert user Sucess", 0, response.getMessageInfoList().size());
		assertResultResponse(response);
	}

	/**
	 * Test insert group to user.
	 */
	@Test
	public void testInsertGroupToUser()
	{
		User user = getUserDefault();
		user.setGroups(Arrays.asList(insertGroup(), insertGroup(), insertGroup()));
		UserRequest userRequest = createUserRequest(user);
		userRequest.setUser(user);

		InternalResultsResponse<User> response = getUserDAC().insertGroupsToUser(userRequest);
		assertResponse(response);
		assertFalse(RESPONSE_IS_NOT_IN_ERROR, response.isInError());

	}

	/**
	 * Test insert roles to user.
	 */
	@Test
	public void testInsertRolesToUser()
	{
		User user = getUserDefault();
		user.setRole(ADMIN);

		UserRequest userRequest = createUserRequest(user);
		userRequest.setUser(user);

		InternalResultsResponse<User> response = getUserDAC().insertRolesToUser(userRequest);
		assertResponse(response);
		assertFalse(RESPONSE_IS_NOT_IN_ERROR, response.isInError());
	}

	/**
	 * Test fetch user by id.
	 */
	@Test
	public void testFetchUserById()
	{
		User user = getUserDefault();
		UserRequest userRequest = createUserRequest(user);
		userRequest.setUser(user);

		userRequest.setUser(user);
		InternalResultsResponse<User> response = getUserDAC().fetchUserById(userRequest);
		assertResultResponse(response);
	}

	/**
	 * Test delete user.
	 */
	@Test
	public void testDeleteUser()
	{
		User user = getUserDefault();
		UserRequest userRequest = createUserRequest(user);
		userRequest.setUser(user);

		// Insert User to be delete
		InternalResultsResponse<User> response = getUserDAC().insertUser(userRequest);
		assertResponse(response);

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(response.getFirstResult().getId());
		userRequest.setSelectionPaginationIds(ids);

		InternalResponse deleteUser = getUserDAC().deleteUser(userRequest);
		assertResponse(deleteUser);
	}

	/**
	 * Test update user.
	 */
	@Test
	public void testUpdateUser()
	{
		// Success
		User user = getUserDefault();
		user.setUserName("TestUserUpdNew");
		user.setEmail("emailUserUpd1");
		user.setModifyUser("QAT test1");

		UserRequest userRequestUpdate = createUserRequest(user);
		InternalResponse response = getUserDAC().updateUser(userRequestUpdate);
		assertResponse(response);

		// User already exists
		UserRequest userRequest = new UserRequest();
		userRequest.setUserContext(TestBaseUtil.createUserContext());
		userRequest.setUser(user);
		response = getUserDAC().insertUser(userRequest);
		assertMessages(response, SENSUS_MLC_USERVALIDATOR_USER_ALREADY_EXISTS);
	}

	/**
	 * Test fetch user by name.
	 */
	@Test
	public void testFetchUserByName()
	{
		User user = getUserDefault();
		UserRequest userRequest = createUserRequest(user);
		userRequest.setUser(user);

		InternalResultsResponse<User> response = getUserDAC().fetchUserByName(userRequest);
		assertResultResponse(response);
	}

	/**
	 * Test delete groups by user.
	 */
	@Test
	public void testDeleteGroupsByUser()
	{
		User user = getUserDefault();
		user.setGroups(Arrays.asList(insertGroup(), insertGroup()));

		UserRequest userRequest = createUserRequest(user);
		userRequest.setUser(user);

		InternalResultsResponse<User> response = getUserDAC().insertGroupsToUser(userRequest);
		assertResponse(response);
		assertFalse(RESPONSE_IS_NOT_IN_ERROR, response.isInError());

		InternalResponse deleteResponse = getUserDAC().deleteGroupsByUser(userRequest);
		assertResponse(deleteResponse);
	}

	/**
	 * Test delete roles user.
	 */
	@Test
	public void testDeleteRolesUser()
	{
		User user = getUserDefault();
		UserRequest userRequest = createUserRequest(user);
		userRequest.setUser(user);

		user.setRole(ADMIN);

		InternalResultsResponse<User> response = getUserDAC().insertRolesToUser(userRequest);
		assertResponse(response);
		assertFalse(RESPONSE_IS_NOT_IN_ERROR, response.isInError());

		userRequest.setUser(user);
		InternalResponse deleteResponse = getUserDAC().deleteRolesByUser(userRequest);
		assertResponse(deleteResponse);
	}

}
