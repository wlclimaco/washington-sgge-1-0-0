package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.bac.IUserBAC;
import com.prosperitasglobal.sendsolv.bai.IUserBAI;
import com.prosperitasglobal.sendsolv.user.model.User;
import com.prosperitasglobal.sendsolv.user.model.request.InquiryUserRequest;
import com.prosperitasglobal.sendsolv.user.model.request.UserRequest;
import com.prosperitasglobal.sendsolv.user.model.response.InquiryUserResponse;
import com.prosperitasglobal.sendsolv.user.model.response.UserResponse;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationController;

/**
 * The Class UserBCFImpl.
 */
public class UserBAIImpl implements IUserBAI
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserBAIImpl.class);

	/** The Constant INQUIRY_USER_REQUEST_NAME. */
	private static final String INQUIRY_USER_REQUEST_NAME = InquiryUserRequest.class.getSimpleName();

	/** The Constant USER_REQUEST_NAME. */
	private static final String USER_REQUEST_NAME = UserRequest.class.getSimpleName();

	/** The Constant USER_NAME. */
	private static final String USER_NAME = User.class.getSimpleName();

	/** The Constant DEFAULT_USER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_USER_BCF_EXCEPTION_MSG = "sensus.mlc.userbcfimpl.defaultexception";

	/** The user bcl. */
	private IUserBAC userBAC;

	/** The super user name. */
	private String superUserName;

	/** The validation controller. */
	private ValidationController userValidationController;

	/** The user request validation controller. */
	private ValidationController userRequestValidationController;

	/**
	 * /**
	 * Gets the user bcl.
	 *
	 * @return the user bcl
	 */
	public IUserBAC getUserBCL()
	{
		return userBAC;
	}

	/**
	 * Sets the user bcl.
	 *
	 * @param userBAC the new user bcl
	 */
	public void setUserBCL(IUserBAC userBAC)
	{
		this.userBAC = userBAC;
	}

	/**
	 * Gets the super user name.
	 *
	 * @return the super user name
	 */
	public String getSuperUserName()
	{
		return superUserName;
	}

	/**
	 * Sets the super user name.
	 *
	 * @param superUserName the new super user name
	 */
	public void setSuperUserName(String superUserName)
	{
		this.superUserName = superUserName;
	}

	/**
	 * Gets the user validation controller.
	 *
	 * @return the user validation controller
	 */
	public ValidationController getUserValidationController()
	{
		return userValidationController;
	}

	/**
	 * Sets the user validation controller.
	 *
	 * @param userValidationController the new user validation controller
	 */
	public void setUserValidationController(ValidationController userValidationController)
	{
		this.userValidationController = userValidationController;
	}

	/**
	 * Gets the user request validation controller.
	 *
	 * @return the user request validation controller
	 */
	public ValidationController getUserRequestValidationController()
	{
		return userRequestValidationController;
	}

	/**
	 * Sets the user request validation controller.
	 *
	 * @param userRequestValidationController the new user request validation controller
	 */
	public void setUserRequestValidationController(ValidationController userRequestValidationController)
	{
		this.userRequestValidationController = userRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#fetchAllUsers(com.sensus.mlc.user.model.request.InquiryUserRequest)
	 */
	@Override
	public InquiryUserResponse fetchAllUsers(
			InquiryUserRequest inquiryUserRequest)
	{
		InquiryUserResponse response = new InquiryUserResponse();
		InternalResultsResponse<User> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_USER_REQUEST_NAME, inquiryUserRequest);

			internalResponse = getUserBCL().fetchAllUsers(inquiryUserRequest);

			handleOperationStatusAndMessagesUser(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleExceptionUser(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	private void handleExceptionUser(Logger log2, InquiryUserResponse response, Exception ex,
			String defaultUserBcfExceptionMsg)
	{
		// TODO Auto-generated method stub

	}

	private void handleOperationStatusAndMessagesUser(InquiryUserResponse response,
			InternalResultsResponse<User> internalResponse, List<MessageInfo> messages, boolean b)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#insertUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public UserResponse insertUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();
		InternalResultsResponse<User> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			internalResponse = getUserBCL().insertUser(userRequest);
			response.setUser(internalResponse.getFirstResult());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#deleteUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public UserResponse deleteUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);

			internalResponse = getUserBCL().deleteUser(userRequest);

			handleOperationStatusAndMessagesUser(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	private void handleOperationStatusAndMessagesUser(UserResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean b)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#updateUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public UserResponse updateUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			internalResponse = getUserBCL().updateUser(userRequest);

			handleOperationStatusAndMessagesUser(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#changePassword(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public UserResponse changePassword(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();
		InternalResponse internalResponse = null;

		try
		{
			String userName = userRequest.getUserContext().getUserId();
			userRequest.setUser(new User(userName));

			response = fetchUserByName(userRequest);

			if (!response.isOperationSuccess())
			{
				return response;
			}

			ValidationContext context = new ValidationContext();

			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);

			userRequest.getUser().setPassword(userRequest.getNewPassword());
			internalResponse = getUserBCL().updateUser(userRequest);

			handleOperationStatusAndMessagesUser(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#fetchUserById(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public UserResponse fetchUserById(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();
		InternalResultsResponse<User> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			internalResponse = getUserBCL().fetchUserById(userRequest);
			response.setUser(internalResponse.getFirstResult());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#fetchUserByName(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public UserResponse fetchUserByName(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();
		InternalResultsResponse<User> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			internalResponse = getUserBCL().fetchUserByName(userRequest);
			response.setUser(internalResponse.getFirstResult());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	private void handleOperationStatusAndMessages(UserResponse response,
			InternalResultsResponse<User> internalResponse, List<MessageInfo> messages, boolean b)
	{
		// TODO Auto-generated method stub

	}

	private void handleException(Logger log2, UserResponse response, Exception ex,
			String defaultUserBcfExceptionMsg)
	{
		// TODO Auto-generated method stub

	}
}
