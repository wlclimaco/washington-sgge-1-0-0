package com.sensus.mlc.user.bcf;

import static com.sensus.mlc.base.TestBaseUtil.createTenant;
import static com.sensus.mlc.base.TestBaseUtil.createUser;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;
import static com.sensus.mlc.base.TestBaseUtil.createUserRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.user.bcl.IUserBCL;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.user.model.response.UserResponse;

/**
 * The Class UserBCFTest.
 */
/**
 * @author QATEmployee
 *
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/user/userbcfimpltest.xml"})
public class UserBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant FETCH_USER_BY_NAME. */
	private static final String FETCH_USER_BY_NAME = "fetchUserByName";

	/** The Constant UPDATE_USER. */
	private static final String UPDATE_USER = "updateUser";

	/** The Constant DEFAULT_USER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_USER_BCF_EXCEPTION_MSG = "sensus.mlc.userbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_EMAIL_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_EMAIL_REQUIRED = "sensus.mlc.uservalidator.email.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_ROLE_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_ROLE_REQUIRED = "sensus.mlc.uservalidator.role.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_REQUIRED =
			"sensus.mlc.uservalidator.actual.password.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_NEW_PASSWORD_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_NEW_PASSWORD_REQUIRED =
			"sensus.mlc.uservalidator.new.password.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_INVALID. */
	private static final String SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_INVALID =
			"sensus.mlc.uservalidator.actual.password.invalid";

	/** The Constant ARBITRARY_NUMBER_THREE. */
	private static final Object ARBITRARY_NUMBER_THREE = 3;

	/** The Constant ARBITRARY_NUMBER_5. */
	private static final Object ARBITRARY_NUMBER_5 = 5;

	/** The Constant ARBITRARY_USER_ID_5. */
	private static final Integer ARBITRARY_USER_ID_5 = 5;

	/** The user bcf. */
	private IUserBCF userBCF;

	/**
	 * Gets the user bcf.
	 *
	 * @return the user bcf
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * Sets the user bcf.
	 *
	 * @param userBCF the new user bcf
	 */
	@Resource(name = "userBCFTarget")
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * Sets the user area.
	 */
	public void setUserArea()
	{
		setArea(getUserBCF(), LCAreaEnum.USER);
	}

	/**
	 * Reset mocks to user area.
	 */
	@After
	public void resetMocksToUserArea()
	{
		resetMocksData(getUserBCF());
		setUserArea();
	}

	/**
	 * Test fetch all users.
	 */
	@Test
	public void testFetchAllUsers()
	{
		InquiryUserRequest request = new InquiryUserRequest();

		// Fail - No Tenant
		InquiryUserResponse response = getUserBCF().fetchAllUsers(request);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception
		request = new InquiryUserRequest(createUserContext());
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class);
		response = getUserBCF().fetchAllUsers(request);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);

		// SUCCESS
		resetMocksToUserArea();
		request = new InquiryUserRequest(createUserContext());
		response = getUserBCF().fetchAllUsers(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getUsers());
		assertTrue(response.getUsers().size() > 0);

		// Error
		request = new InquiryUserRequest(createUserContext());
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class);
		response = getUserBCF().fetchAllUsers(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert user.
	 */
	@Test
	public void testInsertUser()
	{
		UserContext userContext = createUserContext();

		// Validation
		UserRequest request = new UserRequest();
		UserResponse response = getUserBCF().insertUser(request);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);
		assertEquals(SENSUS_MLC_VALIDATOR_ID_REQUIRED, response.getMessageInfoList().get(0).getCode());

		request.setTenant(createTenant());
		response = getUserBCF().insertUser(request);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);
		assertEquals(SENSUS_MLC_VALIDATOR_ID_REQUIRED, response.getMessageInfoList().get(0).getCode());

		request.setTenant(createTenant());

		request.setUserContext(userContext);
		response = getUserBCF().insertUser(request);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success
		request.setUser(createUser());
		request.setUserContext(userContext);
		response = getUserBCF().insertUser(request);
		assertNotNull(response);
		assertEquals("OK", 0, response.getMessageList().size());

		// Exception
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class);
		response = getUserBCF().insertUser(request);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);

		// Error
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class);
		response = getUserBCF().insertUser(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete user.
	 */
	@Test
	public void testDeleteUser()
	{
		// Validation
		UserRequest userRequest = new UserRequest();
		UserResponse response = getUserBCF().deleteUser(userRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);
		assertEquals("Missing User ID and Tenant", ARBITRARY_NUMBER_THREE, response.getMessageList().size());

		Tenant tenant = new Tenant();
		tenant.setId(1);
		userRequest.setTenant(tenant);
		response = getUserBCF().deleteUser(userRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);
		assertEquals("Missing User ID", 2, response.getMessageList().size());

		List<Integer> users = new ArrayList<Integer>();
		users.add(ARBITRARY_USER_ID_5);
		userRequest.setSelectionPaginationIds(users);
		response = getUserBCF().deleteUser(userRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class);
		userRequest = createUserRequest(createUser());
		userRequest.setSelectionPaginationIds(users);
		response = getUserBCF().deleteUser(userRequest);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);
		assertEquals("Valid Delete", 1, response.getMessageList().size());

		// Error
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class);
		response = getUserBCF().deleteUser(userRequest);
		assertMessages(response, ERROR_CODE);

		// Success
		userRequest.setUser(createUser());
		resetMocksToUserArea();
		response = getUserBCF().deleteUser(userRequest);
		assertEquals("Success", 0, response.getMessageList().size());
	}

	/**
	 * Test update user.
	 */
	@Test
	public void testUpdateUser()
	{
		// Validation

		// missing Tenant, UserContext and User
		UserRequest userRequest = new UserRequest();
		UserResponse response = getUserBCF().updateUser(userRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// missing UserContext and User
		Tenant tenant = new Tenant();
		tenant.setId(1);
		userRequest.setTenant(tenant);
		response = getUserBCF().updateUser(userRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// missing User
		UserContext userContext = new UserContext();
		userContext.setTenant(tenant);
		userContext.setUserId("Test 1");
		userRequest.setUserContext(userContext);
		response = getUserBCF().updateUser(userRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// With user empty
		User user = new User();
		userRequest.setUser(user);
		tenant.setRniCode("1234");
		userRequest.setTenant(tenant);
		response = getUserBCF().updateUser(userRequest);
		assertNotNull(response);
		assertEquals("Invalid Update", ARBITRARY_NUMBER_5, response.getMessageList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED
				, SENSUS_MLC_VALIDATOR_NAME_REQUIRED
				, SENSUS_MLC_VALIDATOR_NAME_REQUIRED
				, SENSUS_MLC_USERVALIDATOR_EMAIL_REQUIRED
				, SENSUS_MLC_USERVALIDATOR_ROLE_REQUIRED);

		// Update OK
		userRequest.setUser(createUser());
		userRequest.setTenant(tenant);
		response = getUserBCF().updateUser(userRequest);
		assertNotNull(response);
		assertEquals("Update Success", 0, response.getMessageList().size());

		// Exception
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class);
		response = getUserBCF().updateUser(userRequest);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);

		// Error
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class);
		response = getUserBCF().updateUser(userRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch user by id.
	 */
	@Test
	public void testFetchUserById()
	{
		UserRequest userRequest = new UserRequest();

		// Invalid Fetch User By Id
		UserResponse response = getUserBCF().fetchUserById(userRequest);
		assertNotNull(response);
		assertEquals("Missing User", ARBITRARY_NUMBER_THREE, response.getMessageList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test SUCCESS
		userRequest.setUserContext(createUserContext());
		userRequest.setUser(createUser());
		userRequest.getUser().setId(1);
		response = getUserBCF().fetchUserById(userRequest);
		assertNotNull(response);
		assertEquals("SUCCESS", 0, response.getMessageList().size());

		// Test EXCEPTION
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class);
		response = getUserBCF().fetchUserById(userRequest);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);

		// Error
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class);
		response = getUserBCF().fetchUserById(userRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch user by name.
	 */
	@Test
	public void testFetchUserByName()
	{
		// Validation
		UserRequest userRequest = new UserRequest();

		Tenant tenant = new Tenant();
		tenant.setId(1);
		tenant.setRniCode("123");
		userRequest.setTenant(tenant);
		UserResponse response = getUserBCF().fetchUserByName(userRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		User user = new User();
		userRequest.setUser(user);
		response = getUserBCF().fetchUserByName(userRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success
		user.setUserName("User test");
		response = getUserBCF().fetchUserByName(userRequest);
		assertNotNull(response);
		assertEquals("Fetch By Name OK", 0, response.getMessageList().size());

		// Exception
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class);
		response = getUserBCF().fetchUserByName(userRequest);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);

		// Error
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class);
		response = getUserBCF().fetchUserByName(userRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test change password.
	 */
	@Test
	public void testChangePassword()
	{
		// Validation
		UserRequest userRequest = new UserRequest();
		UserContext userContext = new UserContext();
		userContext.setId(1);
		userContext.setUserId("rod");

		userRequest.setUserContext(createUserContext());
		userRequest.setTenant(createTenant());
		UserResponse response = getUserBCF().changePassword(userRequest);
		assertMessages(response, SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_REQUIRED,
				SENSUS_MLC_USERVALIDATOR_NEW_PASSWORD_REQUIRED, SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_INVALID);

		userRequest.setActualPassword("test");
		response = getUserBCF().changePassword(userRequest);
		assertMessages(response, SENSUS_MLC_USERVALIDATOR_NEW_PASSWORD_REQUIRED,
				SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_INVALID);

		// Success
		userRequest.setNewPassword("QAT%123456");
		userRequest.setActualPassword("Sensus123$");
		response = getUserBCF().changePassword(userRequest);
		assertNotNull(response);
		assertEquals("missing new and actual password", 0, response.getMessageList().size());

		// Exception UpdateUser
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class, UPDATE_USER);
		response = getUserBCF().changePassword(userRequest);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);

		// Exception Fetch User By Name
		setSituation(getUserBCF(), SituationsEnum.EXCEPTION, IUserBCL.class, FETCH_USER_BY_NAME);
		response = getUserBCF().changePassword(userRequest);
		assertMessages(response, DEFAULT_USER_BCF_EXCEPTION_MSG);

		// Error UpdateUser
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class, UPDATE_USER);
		response = getUserBCF().changePassword(userRequest);
		assertMessages(response, ERROR_CODE);

		// Error Fetch User By Name
		setSituation(getUserBCF(), SituationsEnum.ERROR, IUserBCL.class, FETCH_USER_BY_NAME);
		response = getUserBCF().changePassword(userRequest);
		assertMessages(response, ERROR_CODE);
	}
}
