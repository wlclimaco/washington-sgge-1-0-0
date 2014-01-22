package com.sensus.lc.user.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.user.bcf.IUserBCF;
import com.sensus.lc.user.bcl.IUserBCL;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.request.UserRequest;
import com.sensus.lc.user.model.response.InquiryUserResponse;
import com.sensus.lc.user.model.response.UserResponse;

/**
 * The Class UserBCFImpl.
 */
public class UserBCFImpl extends AbstractBaseBCF implements IUserBCF
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserBCFImpl.class);

	/** The Constant INQUIRY_USER_REQUEST_NAME. */
	private static final String INQUIRY_USER_REQUEST_NAME = InquiryUserRequest.class.getSimpleName();

	/** The Constant USER_REQUEST_NAME. */
	private static final String USER_REQUEST_NAME = UserRequest.class.getSimpleName();

	/** The Constant USER_NAME. */
	private static final String USER_NAME = User.class.getSimpleName();

	/** The Constant DEFAULT_USER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_USER_BCF_EXCEPTION_MSG = "sensus.mlc.userbcfimpl.defaultexception";

	/** The user bcl. */
	private IUserBCL userBCL;

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The super user name. */
	private String superUserName;

	/** The validation controller. */
	private ValidationController userValidationController;

	/** The user request validation controller. */
	private ValidationController userRequestValidationController;

	/** The light selection request validation controller. */
	private ValidationController lightSelectionRequestValidationController;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/**
	 * /**
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
	public void setUserBCL(IUserBCL userBCL)
	{
		this.userBCL = userBCL;
	}

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
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

	/**
	 * Gets the light selection request validation controller.
	 * 
	 * @return the light selection request validation controller
	 */
	@Override
	public ValidationController getLightSelectionRequestValidationController()
	{
		return lightSelectionRequestValidationController;
	}

	/**
	 * Sets the light selection request validation controller.
	 * 
	 * @param lightSelectionRequestValidationController the new light selection request validation controller
	 */
	@Override
	public void setLightSelectionRequestValidationController(
			ValidationController lightSelectionRequestValidationController)
	{
		this.lightSelectionRequestValidationController = lightSelectionRequestValidationController;
	}

	/**
	 * Gets the request validation controller.
	 * 
	 * @return the request validation controller
	 */
	@Override
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/**
	 * Sets the request validation controller.
	 * 
	 * @param requestValidationController the new request validation controller
	 */
	@Override
	public void setRequestValidationController(ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	@Override
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Sets the inquiry request validation controller.
	 * 
	 * @param inquiryRequestValidationController the new inquiry request validation controller
	 */
	@Override
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcf.IUserBCF#fetchAllUsers(com.sensus.mlc.user.model.request.InquiryUserRequest)
	 */
	@Override
	public InquiryUserResponse fetchAllUsers(InquiryUserRequest inquiryUserRequest)
	{
		InquiryUserResponse response = new InquiryUserResponse();
		InternalResultsResponse<User> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_USER_REQUEST_NAME, inquiryUserRequest);

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
			{
				internalResponse = getUserBCL().fetchAllUsers(inquiryUserRequest);
			}

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
			context.getValidationArguments().put(
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.INSERT);
			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUserValidationController().validate(context))
			{
				internalResponse = getUserBCL().insertUser(userRequest);
				response.setUser(internalResponse.getFirstResult());
			}

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

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getUserBCL().deleteUser(userRequest);
			}

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
			context.getValidationArguments().put(
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE);
			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUserValidationController().validate(context))
			{
				internalResponse = getUserBCL().updateUser(userRequest);
			}

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
			Tenant tenant = userRequest.getTenant();

			if ((getSuperUserName() != null) && getSuperUserName().equalsIgnoreCase(userName))
			{
				userRequest.setTenant(new Tenant());
			}

			response = fetchUserByName(userRequest);

			if (!response.isOperationSuccess())
			{
				return response;
			}

			userRequest.setUser(response.getUser());
			userRequest.setTenant(tenant);

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.CHANGE_PASSWORD);
			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);

			if (getLightingControlRequestValidationController().validate(context)
					&& getUserRequestValidationController().validate(context))
			{
				userRequest.getUser().setPassword(userRequest.getNewPassword());
				internalResponse = getUserBCL().updateUser(userRequest);
			}

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
			context.getValidationArguments().put(MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_BY_ID);
			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUserValidationController().validate(context))
			{
				internalResponse = getUserBCL().fetchUserById(userRequest);
				response.setUser(internalResponse.getFirstResult());
			}

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
			context.getValidationArguments().put(MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_BY_NAME);
			context.putObjectToBeValidated(USER_REQUEST_NAME, userRequest);
			context.putObjectToBeValidated(USER_NAME, userRequest.getUser());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUserValidationController().validate(context))
			{
				internalResponse = getUserBCL().fetchUserByName(userRequest);
				response.setUser(internalResponse.getFirstResult());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_USER_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
