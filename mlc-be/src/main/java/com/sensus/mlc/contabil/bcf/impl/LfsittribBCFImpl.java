package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFSITTRIB;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFSITTRIB;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFSITTRIB_LIST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.lfsittrib.bcf.ILfsittribBCF;
import com.sensus.mlc.lfsittrib.bcl.ILfsittribBCL;
import com.sensus.mlc.lfsittrib.model.Lfsittrib;
import com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest;
import com.sensus.mlc.lfsittrib.model.request.LfsittribRequest;
import com.sensus.mlc.lfsittrib.model.response.InquiryLfsittribResponse;
import com.sensus.mlc.lfsittrib.model.response.LfsittribResponse;

/** 
 * The Class LfsittribBCFImpl.
 *
 * @author - Washington
 */
public class LfsittribBCFImpl extends AbstractBaseBCF implements ILfsittribBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFSITTRIB_REQUEST_NAME. */ 
	private static final String INQUIRY_LFSITTRIB_REQUEST_NAME = InquiryLfsittribRequest.class.getSimpleName();

	/** The Constant LFSITTRIB_REQUEST_NAME. */ 
	private static final String LFSITTRIB_REQUEST_NAME = LfsittribRequest.class.getSimpleName();
 
	/** The Constant LFSITTRIB_NAME. */ 
	private static final String LFSITTRIB_NAME = Lfsittrib.class.getSimpleName();

	/** The Constant DEFAULT_LFSITTRIB_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFSITTRIB_BCF_EXCEPTION_MSG = "sensus.mlc.lfsittribbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfsittribBCFImpl.class);
 
	/** The lfsittrib bcl. */ 
	private ILfsittribBCL lfsittribBCL;
 
	/** The lfsittrib validation controller. */ 
	private ValidationController lfsittribValidationController;
  
	/** The lfsittrib list validation controller. */ 
	private ValidationController lfsittribListValidationController;

	/** The lfsittrib request validation controller. */ 
	private ValidationController lfsittribRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfsittrib bcl.
	 *  
	 * @return the lfsittrib bcl  
	 */ 
	public ILfsittribBCL getLfsittribBCL()
	{ 
		return this.lfsittribBCL;
	} 
  
	/**  
	 * Sets the lfsittrib bcl.  
	 *   
	 * @param lfsittribBCLObject the new lfsittrib bcl  
	 */ 
	public void setLfsittribBCL(ILfsittribBCL lfsittribBCLObject) 
	{
		this.lfsittribBCL = lfsittribBCLObject;
	} 
 
	/**  
	 * Gets the lfsittrib validation controller.
	 * 
	 * @return the lfsittrib validation controller  
	 */  
	public ValidationController getLfsittribValidationController()  
	{
		return this.lfsittribValidationController;
	}  
  
	/**   
	 * Sets the lfsittrib validation controller.
	 * 
	 * @param lfsittribValidationController the new lfsittrib validation controller  
	 */    
	public void setLfsittribValidationController(ValidationController lfsittribValidationController)   
	{
		this.lfsittribValidationController = lfsittribValidationController;
	} 
  
	/** 
	 * Gets the lfsittrib list validation controller. 
	 *  
	 * @return the lfsittrib list validation controller  
	 */   
	public ValidationController getLfsittribListValidationController()
	{  
		return this.lfsittribListValidationController;
	}  
   
	/**   
	 * Sets the lfsittrib list validation controller.  
	 *  
	 * @param lfsittribListValidationController the new lfsittrib list validation controller 
	 */ 
	public void setLfsittribListValidationController(ValidationController lfsittribListValidationController)
	{ 
		this.lfsittribListValidationController = lfsittribListValidationController;
	} 
     
	/**  
	 * Gets the lfsittrib request validation controller. 
	 *
	 * @return the lfsittrib request validation controller 
	 */
	public ValidationController getLfsittribRequestValidationController() 
	{ 
		return this.lfsittribRequestValidationController;
	} 
 
	/** 
	 * Sets the lfsittrib request validation controller. 
	 *  
	 * @param lfsittribRequestValidationController the new lfsittrib request validation controller 
	 */ 
	public void setLfsittribRequestValidationController(ValidationController lfsittribRequestValidationController) 
	{
		this.lfsittribRequestValidationController = lfsittribRequestValidationController;
	} 
   
	/**
	 * Gets the light validation controller.
	 *
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{ 
		return this.lightValidationController;
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
	 * Gets the light list validation controller. 
	 *
	 * @return the light list validation controller 
	 */ 
	public ValidationController getLightListValidationController() 
	{ 
		return this.lightListValidationController;
	} 
   
	/**  
	 * Sets the light list validation controller.  
	 *     
	 * @param lightListValidationController the new light list validation controller 
	 */ 
	public void setLightListValidationController(ValidationController lightListValidationController) 
	{
		this.lightListValidationController = lightListValidationController;
	}
   
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfsittrib.bcf.ILfsittribBCF#fetchAllLfsittribs(com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest
	 */ 
	@Override 
	public InquiryLfsittribResponse fetchAllLfsittrib(InquiryLfsittribRequest inquiryLfsittribRequest)
	{
		InquiryLfsittribResponse response = new InquiryLfsittribResponse();
		InternalResultsResponse<Lfsittrib> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFSITTRIB_REQUEST_NAME, inquiryLfsittribRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfsittribBCL().fetchAllLfsittrib(inquiryLfsittribRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSITTRIB_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfsittrib.bcf.ILfsittribBCF#fetchLfsittribByName(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest
	 */ 
	@Override 
	public LfsittribResponse fetchLfsittribById(LfsittribRequest lfsittribRequest)
	{  
		LfsittribResponse response = new LfsittribResponse();
		InternalResultsResponse<Lfsittrib> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFSITTRIB_REQUEST_NAME, lfsittribRequest);
			context.putObjectToBeValidated(LFSITTRIB_NAME, lfsittribRequest.getLfsittrib());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfsittribValidationController().validate(context)) 
			{   
				internalResponse = getLfsittribBCL().fetchLfsittribByName(lfsittribRequest);
				response.setLfsittrib(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSITTRIB_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfsittrib.bcf.ILfsittribBCF#insertLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)
	 */ 
	@Override 
	public LfsittribResponse insertLfsittrib(LfsittribRequest lfsittribRequest) 
	{ 
		LfsittribResponse response = new LfsittribResponse();
		InternalResultsResponse<Lfsittrib> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFSITTRIB_REQUEST_NAME, lfsittribRequest);
			context.putObjectToBeValidated(LFSITTRIB_NAME, lfsittribRequest.getLfsittrib());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfsittribValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfsittribBCL().insertLfsittrib(lfsittribRequest);
					response.setLfsittrib(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSITTRIB_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfsittrib.bcf.ILfsittribBCF#deleteLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)
	 */  
	@Override  
	public LfsittribResponse deleteLfsittrib(LfsittribRequest lfsittribRequest)
	{  
		LfsittribResponse response = new LfsittribResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFSITTRIB_REQUEST_NAME, lfsittribRequest);
			context.putObjectToBeValidated(LFSITTRIB_NAME, lfsittribRequest.getLfsittrib());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfsittribValidationController().validate(context))
			{
				internalResponse = getLfsittribBCL().deleteLfsittrib(lfsittribRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSITTRIB_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfsittrib.bcf.ILfsittribBCF#updateLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest
	 */
	@Override 
	public LfsittribResponse updateLfsittrib(LfsittribRequest lfsittribRequest)
	{ 
		LfsittribResponse response = new LfsittribResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFSITTRIB_REQUEST_NAME, lfsittribRequest);
			context.putObjectToBeValidated(LFSITTRIB_NAME, lfsittribRequest.getLfsittrib());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfsittribRequestValidationController().validate(context) && 
					getLfsittribValidationController().validate(context))
			{ 
				internalResponse = getLfsittribBCL().updateLfsittrib(lfsittribRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSITTRIB_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
