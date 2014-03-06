package com.sensus.lc.histuser.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.lc.histuser.bcf.IHistUserBCF;
import com.sensus.lc.histuser.bcl.IHistUserBCL;
import com.sensus.lc.histuser.model.HistUser;
import com.sensus.lc.histuser.model.request.HistUserRequest;
import com.sensus.lc.histuser.model.request.InquiryHistUserRequest;
import com.sensus.lc.histuser.model.response.HistUserResponse;
import com.sensus.lc.histuser.model.response.InquiryHistUserResponse;

public class HistUserBCFImpl extends AbstractBaseBCF implements IHistUserBCF
{

	/** The Constant DEFAULT_HISTUSER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_HISTUSER_BCF_EXCEPTION_MSG = "sensus.mlc.histUserbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistUserBCFImpl.class);

	/** The histUser bcl. */
	private IHistUserBCL histUserBCL; // injected by Spring through setter

	/** The histUser validator controller. */
	private ValidationController histUserValidationController;

	/** The histUser request validator controller. */
	private ValidationController histUserRequestValidationController;

	/** The histUser list validator controller. */
	private ValidationController histUserListValidationController;

	/** The light validation controller. */
	private ValidationController lightValidationController;

	/** The map data validation controller. */
	private ValidationController mapDataValidationController;

	/** The light criteria validation controller. */
	private ValidationController lightCriteriaValidationController;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/**
	 * Sets the histUser bcl.
	 * 
	 * @param paramHistUserBCL the new histUser bcl
	 */
	public void setHistUserBCL(IHistUserBCL paramHistUserBCL)
	{
		histUserBCL = paramHistUserBCL;
	}

	/**
	 * Gets the histUser bcl.
	 * 
	 * @return the histUser bcl
	 */
	public IHistUserBCL getHistUserBCL()
	{
		return histUserBCL;
	}

	/**
	 * Gets the histUser validation controller.
	 * 
	 * @return the histUser validation controller
	 */
	public ValidationController getHistUserValidationController()
	{
		return histUserValidationController;
	}

	/**
	 * Sets the histUser validation controller.
	 * 
	 * @param histUserValidationController the new histUser validation controller
	 */
	public void setHistUserValidationController(ValidationController histUserValidationController)
	{
		this.histUserValidationController = histUserValidationController;
	}

	/**
	 * Gets the histUser request validation controller.
	 * 
	 * @return the histUser request validation controller
	 */
	public ValidationController getHistUserRequestValidationController()
	{
		return histUserRequestValidationController;
	}

	/**
	 * Sets the histUser request validation controller.
	 * 
	 * @param histUserRequestValidationController the new histUser request validation controller
	 */
	public void setHistUserRequestValidationController(ValidationController histUserRequestValidationController)
	{
		this.histUserRequestValidationController = histUserRequestValidationController;
	}

	/**
	 * Gets the histUser list validation controller.
	 * 
	 * @return the histUser list validation controller
	 */
	public ValidationController getHistUserListValidationController()
	{
		return histUserListValidationController;
	}

	/**
	 * Sets the histUser list validation controller.
	 * 
	 * @param histUserListValidationController the new histUser list validation controller
	 */
	public void setHistUserListValidationController(ValidationController histUserListValidationController)
	{
		this.histUserListValidationController = histUserListValidationController;
	}

	/**
	 * Gets the light validation controller.
	 * 
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return lightValidationController;
	}

	/**
	 * Sets the light validation controller.
	 * 
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
	}

	/**
	 * Gets the map data validation controller.
	 * 
	 * @return the map data validation controller
	 */
	public ValidationController getMapDataValidationController()
	{
		return mapDataValidationController;
	}

	/**
	 * Sets the map data validation controller.
	 * 
	 * @param mapDataValidationController the new map data validation controller
	 */
	public void setMapDataValidationController(ValidationController mapDataValidationController)
	{
		this.mapDataValidationController = mapDataValidationController;
	}

	/**
	 * Gets the light criteria validation controller.
	 * 
	 * @return the light criteria validation controller
	 */
	public ValidationController getLightCriteriaValidationController()
	{
		return lightCriteriaValidationController;
	}

	/**
	 * Sets the light criteria validation controller.
	 * 
	 * @param lightCriteriaValidationController the new light criteria validation controller
	 */
	public void setLightCriteriaValidationController(ValidationController lightCriteriaValidationController)
	{
		this.lightCriteriaValidationController = lightCriteriaValidationController;
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
	 * @see
	 * com.sensus.mlc.histUser.bcf.IHistUserBCF#insertHistUser(com.sensus.mlc.histUser.model.request.HistUserRequest)
	 */
	@Override
	public HistUserResponse insertHistUser(HistUserRequest histUserRequest)
	{
		HistUserResponse response = new HistUserResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			internalResponse = getHistUserBCL().insertHistUser(histUserRequest);
			// response.setHistUsers(internalResponse.getResu);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_HISTUSER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.histUser.bcf.IHistUserBCF#fetchAllHistUsers(com.sensus.mlc.base.model.request.InquiryPaginationRequest
	 * )
	 */
	@Override
	public InquiryHistUserResponse fetchAllHistUsers(InquiryHistUserRequest inquiryPaginationRequest)
	{
		InquiryHistUserResponse response = new InquiryHistUserResponse();
		InternalResultsResponse<HistUser> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			internalResponse = getHistUserBCL().fetchAllHistUsers(inquiryPaginationRequest);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_HISTUSER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.histUser.bcf.IHistUserBCF#fetchHistUserById(com.sensus.mlc.histUser.model.request.HistUserRequest)
	 */
	@Override
	public HistUserResponse fetchHistUserById(InquiryHistUserRequest inquiryHistUserRequest)
	{
		HistUserResponse response = new HistUserResponse();
		InternalResultsResponse<HistUser> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			internalResponse = getHistUserBCL().fetchHistUserById(inquiryHistUserRequest);
			response.setHistUsers(internalResponse.getResultsList());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_HISTUSER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public InquiryHistUserResponse fetchAllHistUserByUser(InquiryHistUserRequest inquiryHistUserRequest)
	{
		InquiryHistUserResponse response = new InquiryHistUserResponse();
		InternalResultsResponse<HistUser> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			internalResponse = getHistUserBCL().fetchAllHistUsers(inquiryHistUserRequest);
			response.setHistUsers(internalResponse.getResultsList());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_HISTUSER_BCF_EXCEPTION_MSG);
		}

		return response;
	}

}
