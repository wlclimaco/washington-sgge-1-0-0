package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFTIPOFISCCLI;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFTIPOFISCCLI;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFTIPOFISCCLI_LIST;

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
import com.sensus.mlc.lftipofisccli.bcf.ILftipofisccliBCF;
import com.sensus.mlc.lftipofisccli.bcl.ILftipofisccliBCL;
import com.sensus.mlc.lftipofisccli.model.Lftipofisccli;
import com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.response.InquiryLftipofisccliResponse;
import com.sensus.mlc.lftipofisccli.model.response.LftipofisccliResponse;

/** 
 * The Class LftipofisccliBCFImpl.
 *
 * @author - Washington
 */
public class LftipofisccliBCFImpl extends AbstractBaseBCF implements ILftipofisccliBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFTIPOFISCCLI_REQUEST_NAME. */ 
	private static final String INQUIRY_LFTIPOFISCCLI_REQUEST_NAME = InquiryLftipofisccliRequest.class.getSimpleName();

	/** The Constant LFTIPOFISCCLI_REQUEST_NAME. */ 
	private static final String LFTIPOFISCCLI_REQUEST_NAME = LftipofisccliRequest.class.getSimpleName();
 
	/** The Constant LFTIPOFISCCLI_NAME. */ 
	private static final String LFTIPOFISCCLI_NAME = Lftipofisccli.class.getSimpleName();

	/** The Constant DEFAULT_LFTIPOFISCCLI_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFTIPOFISCCLI_BCF_EXCEPTION_MSG = "sensus.mlc.lftipofiscclibcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LftipofisccliBCFImpl.class);
 
	/** The lftipofisccli bcl. */ 
	private ILftipofisccliBCL lftipofisccliBCL;
 
	/** The lftipofisccli validation controller. */ 
	private ValidationController lftipofisccliValidationController;
  
	/** The lftipofisccli list validation controller. */ 
	private ValidationController lftipofisccliListValidationController;

	/** The lftipofisccli request validation controller. */ 
	private ValidationController lftipofisccliRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lftipofisccli bcl.
	 *  
	 * @return the lftipofisccli bcl  
	 */ 
	public ILftipofisccliBCL getLftipofisccliBCL()
	{ 
		return this.lftipofisccliBCL;
	} 
  
	/**  
	 * Sets the lftipofisccli bcl.  
	 *   
	 * @param lftipofisccliBCLObject the new lftipofisccli bcl  
	 */ 
	public void setLftipofisccliBCL(ILftipofisccliBCL lftipofisccliBCLObject) 
	{
		this.lftipofisccliBCL = lftipofisccliBCLObject;
	} 
 
	/**  
	 * Gets the lftipofisccli validation controller.
	 * 
	 * @return the lftipofisccli validation controller  
	 */  
	public ValidationController getLftipofisccliValidationController()  
	{
		return this.lftipofisccliValidationController;
	}  
  
	/**   
	 * Sets the lftipofisccli validation controller.
	 * 
	 * @param lftipofisccliValidationController the new lftipofisccli validation controller  
	 */    
	public void setLftipofisccliValidationController(ValidationController lftipofisccliValidationController)   
	{
		this.lftipofisccliValidationController = lftipofisccliValidationController;
	} 
  
	/** 
	 * Gets the lftipofisccli list validation controller. 
	 *  
	 * @return the lftipofisccli list validation controller  
	 */   
	public ValidationController getLftipofisccliListValidationController()
	{  
		return this.lftipofisccliListValidationController;
	}  
   
	/**   
	 * Sets the lftipofisccli list validation controller.  
	 *  
	 * @param lftipofisccliListValidationController the new lftipofisccli list validation controller 
	 */ 
	public void setLftipofisccliListValidationController(ValidationController lftipofisccliListValidationController)
	{ 
		this.lftipofisccliListValidationController = lftipofisccliListValidationController;
	} 
     
	/**  
	 * Gets the lftipofisccli request validation controller. 
	 *
	 * @return the lftipofisccli request validation controller 
	 */
	public ValidationController getLftipofisccliRequestValidationController() 
	{ 
		return this.lftipofisccliRequestValidationController;
	} 
 
	/** 
	 * Sets the lftipofisccli request validation controller. 
	 *  
	 * @param lftipofisccliRequestValidationController the new lftipofisccli request validation controller 
	 */ 
	public void setLftipofisccliRequestValidationController(ValidationController lftipofisccliRequestValidationController) 
	{
		this.lftipofisccliRequestValidationController = lftipofisccliRequestValidationController;
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
	 * @see com.sensus.mlc.lftipofisccli.bcf.ILftipofisccliBCF#fetchAllLftipofiscclis(com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest
	 */ 
	@Override 
	public InquiryLftipofisccliResponse fetchAllLftipofisccli(InquiryLftipofisccliRequest inquiryLftipofisccliRequest)
	{
		InquiryLftipofisccliResponse response = new InquiryLftipofisccliResponse();
		InternalResultsResponse<Lftipofisccli> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFTIPOFISCCLI_REQUEST_NAME, inquiryLftipofisccliRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLftipofisccliBCL().fetchAllLftipofisccli(inquiryLftipofisccliRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTIPOFISCCLI_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftipofisccli.bcf.ILftipofisccliBCF#fetchLftipofisccliByName(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest
	 */ 
	@Override 
	public LftipofisccliResponse fetchLftipofisccliById(LftipofisccliRequest lftipofisccliRequest)
	{  
		LftipofisccliResponse response = new LftipofisccliResponse();
		InternalResultsResponse<Lftipofisccli> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFTIPOFISCCLI_REQUEST_NAME, lftipofisccliRequest);
			context.putObjectToBeValidated(LFTIPOFISCCLI_NAME, lftipofisccliRequest.getLftipofisccli());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLftipofisccliValidationController().validate(context)) 
			{   
				internalResponse = getLftipofisccliBCL().fetchLftipofisccliByName(lftipofisccliRequest);
				response.setLftipofisccli(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTIPOFISCCLI_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftipofisccli.bcf.ILftipofisccliBCF#insertLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)
	 */ 
	@Override 
	public LftipofisccliResponse insertLftipofisccli(LftipofisccliRequest lftipofisccliRequest) 
	{ 
		LftipofisccliResponse response = new LftipofisccliResponse();
		InternalResultsResponse<Lftipofisccli> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFTIPOFISCCLI_REQUEST_NAME, lftipofisccliRequest);
			context.putObjectToBeValidated(LFTIPOFISCCLI_NAME, lftipofisccliRequest.getLftipofisccli());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLftipofisccliValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLftipofisccliBCL().insertLftipofisccli(lftipofisccliRequest);
					response.setLftipofisccli(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTIPOFISCCLI_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftipofisccli.bcf.ILftipofisccliBCF#deleteLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)
	 */  
	@Override  
	public LftipofisccliResponse deleteLftipofisccli(LftipofisccliRequest lftipofisccliRequest)
	{  
		LftipofisccliResponse response = new LftipofisccliResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFTIPOFISCCLI_REQUEST_NAME, lftipofisccliRequest);
			context.putObjectToBeValidated(LFTIPOFISCCLI_NAME, lftipofisccliRequest.getLftipofisccli());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLftipofisccliValidationController().validate(context))
			{
				internalResponse = getLftipofisccliBCL().deleteLftipofisccli(lftipofisccliRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTIPOFISCCLI_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftipofisccli.bcf.ILftipofisccliBCF#updateLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest
	 */
	@Override 
	public LftipofisccliResponse updateLftipofisccli(LftipofisccliRequest lftipofisccliRequest)
	{ 
		LftipofisccliResponse response = new LftipofisccliResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFTIPOFISCCLI_REQUEST_NAME, lftipofisccliRequest);
			context.putObjectToBeValidated(LFTIPOFISCCLI_NAME, lftipofisccliRequest.getLftipofisccli());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLftipofisccliRequestValidationController().validate(context) && 
					getLftipofisccliValidationController().validate(context))
			{ 
				internalResponse = getLftipofisccliBCL().updateLftipofisccli(lftipofisccliRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTIPOFISCCLI_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
