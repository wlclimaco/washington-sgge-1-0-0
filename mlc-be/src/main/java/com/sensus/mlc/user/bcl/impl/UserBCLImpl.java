package com.sensus.mlc.user.bcl.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.security.util.LCSecurityHandler;
import com.sensus.mlc.user.bcl.IUserBCL;
import com.sensus.mlc.user.dac.IUserDAC;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;

/**
 * The Class UserBCLImpl.
 */
public class UserBCLImpl implements IUserBCL
{

	/** The Constant USER2. */
	private static final String USER2 = " USER:";

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(UserBCLImpl.class);

	/** The Constant USER_REMOVED. */
	private static final String USER_REMOVED = "sensus.mlc.userbcfimpl.mlcuserdeleted";

	/** The Constant ARBITRARY_STRENGTH. */
	private static final Integer ARBITRARY_STRENGTH = 256;

	/** The user dac. */
	private IUserDAC userDAC; // injected by Spring through setter

	/** The password encoder. */
	private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(ARBITRARY_STRENGTH);

	/**
	 * Gets the user dac.
	 *
	 * @return the user dac
	 */
	public IUserDAC getUserDAC()
	{
		return userDAC;
	}

	/**
	 * Sets the user dac.
	 *
	 * @param userDAC the new user dac
	 */
	public void setUserDAC(IUserDAC userDAC)
	{
		this.userDAC = userDAC;
	}

	/**
	 * Gets the password encoder.
	 *
	 * @return the password encoder
	 */
	public ShaPasswordEncoder getPasswordEncoder()
	{
		return passwordEncoder;
	}

	/**
	 * Sets the password encoder.
	 *
	 * @param passwordEncoder the new password encoder
	 */
	public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder)
	{
		this.passwordEncoder = passwordEncoder;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#fetchAllUsers(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<User> fetchAllUsers(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return getUserDAC().fetchAllUsers(inquiryPaginationRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#insertUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> insertUser(UserRequest userRequest)
	{
		// Use password encoder to store the password.
		User user = userRequest.getUser();
		user.setPassword(
				LCSecurityHandler.encryptPassword(user.getPassword(),
						user.getUserName()));

		InternalResultsResponse<User> responseInsert = getUserDAC().insertUser(userRequest);
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		if (!responseInsert.isInError())
		{
			response = getUserDAC().insertRolesToUser(userRequest);
			if (!ValidationUtil.isNullOrEmpty(user.getGroups()))
			{
				response = getUserDAC().insertGroupsToUser(userRequest);
			}

			persistLogInfo("User Created by:" + userRequest.getUserContext().getUserId() + USER2 + user);
		}
		responseInsert.addMessages(response.getMessageInfoList());
		return responseInsert;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#fetchUserById(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> fetchUserById(UserRequest userRequest)
	{
		return getUserDAC().fetchUserById(userRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#updateUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse updateUser(UserRequest userRequest)
	{
		// Use password encoder to store the password.
		// Only if FE sends a new password
		User user = userRequest.getUser();
		if (!ValidationUtil.isNullOrEmpty(user.getPassword()))
		{
			user.setPassword(
					LCSecurityHandler.encryptPassword(user.getPassword(),
							user.getUserName()));
		}

		InternalResponse responseUpdate = getUserDAC().updateUser(userRequest);
		InternalResponse response = new InternalResponse();

		if (!responseUpdate.isInError())
		{
			getUserDAC().deleteRolesByUser(userRequest);
			getUserDAC().deleteGroupsByUser(userRequest);

			response = getUserDAC().insertRolesToUser(userRequest);
			if (!ValidationUtil.isNullOrEmpty(user.getGroups()))
			{
				response = getUserDAC().insertGroupsToUser(userRequest);
			}

			persistLogInfo("User Updated by:" + userRequest.getUserContext().getUserId() + USER2 + user);
		}

		responseUpdate.addMessages(response.getMessageInfoList());
		return responseUpdate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#deleteUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse deleteUser(UserRequest userRequest)
	{
		InternalResponse response = new InternalResponse();
		// build a list of groups based on the user selection - Select All requirement
		List<User> userList = fetchSelectedUsers(userRequest);
		for (User user : userList)
		{
			UserRequest request = new UserRequest(userRequest.getUserContext());
			request.setUser(user);

			if (getUserDAC().deleteUser(userRequest).getStatus().equals(Status.OperationSuccess))
			{
				response.addMessage(USER_REMOVED, MessageSeverity.Info, MessageLevel.None,
						new Object[] {user.getUserName()});
			}

			User deletedUser = userRequest.getUser();
			persistLogInfo("User Delete by:" + userRequest.getUserContext().getUserId() + USER2 + deletedUser);

		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#fetchUserByName(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResultsResponse<User> fetchUserByName(UserRequest userRequest)
	{
		return getUserDAC().fetchUserByName(userRequest);
	}

	/**
	 * Fetch selected users.
	 *
	 * @param userRequest the user request
	 * @return the list
	 */
	private List<User> fetchSelectedUsers(UserRequest userRequest)
	{
		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest(userRequest.getUserContext());
		inquiryPaginationRequest.setPageSize(0);
		inquiryPaginationRequest.setSelectionPaginationIds(userRequest.getSelectionPaginationIds());
		inquiryPaginationRequest.setPaginationAllSelected(userRequest.getPaginationAllSelected());
		inquiryPaginationRequest.setTenant(userRequest.getTenant());

		return getUserDAC().fetchAllUsers(inquiryPaginationRequest).getResultsList();
	}

	/**
	 * Persist log info.
	 *
	 * @param log the log
	 */
	private void persistLogInfo(String log)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(log);
		}
	}

}
