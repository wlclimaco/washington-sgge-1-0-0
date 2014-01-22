package com.sensus.lc.user.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createGroups;
import static com.sensus.lc.base.TestBaseUtil.createUser;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.user.dac.IUserDAC;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.request.UserRequest;

/**
 * The Class UserBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/user/userbclimpltest.xml"})
public class UserBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant INSERT_GROUPS_TO_USER. */
	private static final String INSERT_GROUPS_TO_USER = "insertGroupsToUser";

	/** The Constant USER_REMOVED. */
	private static final String USER_REMOVED = "sensus.mlc.userbcfimpl.mlcuserdeleted";

	/** The user bcl. */
	private IUserBCL userBCL;

	/**
	 * Gets the user bcl.
	 * 
	 * @return the user bcl
	 */
	public IUserBCL getUserBCL()
	{
		return userBCL;
	}

	/**
	 * Sets the user bcl.
	 * 
	 * @param userBCL the new user bcl
	 */
	@Resource(name = "userBCLTarget")
	public void setUserBCL(IUserBCL userBCL)
	{
		this.userBCL = userBCL;
	}

	/**
	 * Sets the user area.
	 */
	public void setUserArea()
	{
		setArea(getUserBCL(), LCAreaEnum.USER);
	}

	/**
	 * Removes the user area.
	 */
	@After
	public void removeUserArea()
	{
		setArea(getUserBCL(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Reset mocks to user area.
	 */
	@After
	public void resetMocksToUserArea()
	{
		resetMocksData(getUserBCL());
		setUserArea();
	}

	/**
	 * Test fetch all users.
	 */
	@Test
	public void testFetchAllUsers()
	{
		// Success
		InquiryPaginationRequest request = new InquiryUserRequest(createUserContext());
		InternalResultsResponse<User> response = getUserBCL().fetchAllUsers(request);
		assertResultResponse(response);

		resetMocksToUserArea();

		// Error
		request = new InquiryUserRequest(createUserContext());
		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class);
		response = getUserBCL().fetchAllUsers(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert user.
	 */
	@Test
	public void testInsertUser()
	{
		// Success
		UserRequest userRequest = TestBaseUtil.createUserRequest(createUser());
		userRequest.setUser(createUser());
		InternalResultsResponse<User> response = getUserBCL().insertUser(userRequest);
		assertResultResponse(response);
		userRequest.getUser().setGroups(createGroups(1));
		response = getUserBCL().insertUser(userRequest);
		assertResultResponse(response);

		resetMocksToUserArea();

		// Error
		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class, "insertUser");
		response = getUserBCL().insertUser(userRequest);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToUserArea();

		// Error
		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class, INSERT_GROUPS_TO_USER);
		response = getUserBCL().insertUser(userRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch user by id.
	 */
	@Test
	public void testFetchUserById()
	{
		// Success
		UserRequest request = TestBaseUtil.createUserRequest(createUser());
		InternalResultsResponse<User> response = getUserBCL().fetchUserById(request);
		assertResultResponse(response);

		resetMocksToUserArea();

		// Error
		request = TestBaseUtil.createUserRequest(createUser());
		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class);
		response = getUserBCL().fetchUserById(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update user.
	 */
	@Test
	public void testUpdateUser()
	{
		UserRequest userRequest = new UserRequest();
		userRequest.setUser(createUser());
		InternalResultsResponse<User> responseInsert = getUserBCL().insertUser(userRequest);
		assertResultResponse(responseInsert);

		// Success
		userRequest.setUser(responseInsert.getFirstResult());
		userRequest.getUser().setGroups(createGroups(1));

		InternalResponse response = getUserBCL().updateUser(userRequest);
		assertResponse(response);

		resetMocksToUserArea();

		// Error
		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class, "updateUser");
		response = getUserBCL().updateUser(userRequest);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToUserArea();

		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class, INSERT_GROUPS_TO_USER);
		response = getUserBCL().updateUser(userRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete user.
	 */
	@Test
	public void testDeleteUser()
	{
		// Success
		UserRequest userRequest = new UserRequest();
		userRequest.setUser(createUser());
		InternalResponse responseInsert = getUserBCL().deleteUser(userRequest);
		assertNotNull(responseInsert);
		assertEquals("Operation Success", Status.OperationSuccess, responseInsert.getStatus());
		this.assertMessages(responseInsert, USER_REMOVED);

		resetMocksToUserArea();

		// Error
		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class, "deleteUser");
		responseInsert = getUserBCL().deleteUser(userRequest);
		this.assertMessages(responseInsert, "error", "sensus.mlc.userbcfimpl.users.deleted");
	}

	/**
	 * Test fetch user by name.
	 */
	@Test
	public void testFetchUserByName()
	{
		// Success
		UserRequest request = TestBaseUtil.createUserRequest(createUser());
		InternalResultsResponse<User> response = getUserBCL().fetchUserByName(request);
		assertResultResponse(response);

		resetMocksToUserArea();

		// Error
		request = TestBaseUtil.createUserRequest(createUser());
		this.setSituation(getUserBCL(), SituationsEnum.ERROR, IUserDAC.class);
		response = getUserBCL().fetchUserByName(request);
		this.assertMessages(response, ERROR_CODE);
	}

}
