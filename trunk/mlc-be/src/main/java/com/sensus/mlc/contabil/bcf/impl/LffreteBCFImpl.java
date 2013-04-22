package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFFRETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFFRETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFFRETE_LIST;

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
import com.sensus.mlc.lffrete.bcf.ILffreteBCF;
import com.sensus.mlc.lffrete.bcl.ILffreteBCL;
import com.sensus.mlc.lffrete.model.Lffrete;
import com.sensus.mlc.lffrete.model.request.InquiryLffreteRequest;
import com.sensus.mlc.lffrete.model.request.LffreteRequest;
import com.sensus.mlc.lffrete.model.response.InquiryLffreteResponse;
import com.sensus.mlc.lffrete.model.response.LffreteResponse;

/** 
 * The Class LffreteBCFImpl.
 *
 * @author - Washington
 */
public class LffreteBCFImpl extends AbstractBaseBCF implements ILffreteBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFFRETE_REQUEST_NAME. */ 
	private static final String INQUIRY_LFFRETE_REQUEST_NAME = InquiryLffreteRequest.class.getSimpleName();

	/** The Constant LFFRETE_REQUEST_NAME. */ 
	private static final String LFFRETE_REQUEST_NAME = LffreteRequest.class.getSimpleName();
 
	/** The Constant LFFRETE_NAME. */ 
	private static final String LFFRETE_NAME = Lffrete.class.getSimpleName();

	/** The Constant DEFAULT_LFFRETE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFFRETE_BCF_EXCEPTION_MSG = "sensus.mlc.lffretebcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LffreteBCFImpl.class);
 
	/** The lffrete bcl. */ 
	private ILffreteBCL lffreteBCL;
 
	/** The lffrete validation controller. */ 
	private ValidationController lffreteValidationController;
  
	/** The lffrete list validation controller. */ 
	private ValidationController lffreteListValidationController;

	/** The lffrete request validation controller. */ 
	private ValidationController lffreteRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lffrete bcl.
	 *  
	 * @return the lffrete bcl  
	 */ 
	public ILffreteBCL getLffreteBCL()
	{ 
		return this.lffreteBCL;
	} 
  
	/**  
	 * Sets the lffrete bcl.  
	 *   
	 * @param lffreteBCLObject the new lffrete bcl  
	 */ 
	public void setLffreteBCL(ILffreteBCL lffreteBCLObject) 
	{
		this.lffreteBCL = lffreteBCLObject;
	} 
 
	/**  
	 * Gets the lffrete validation controller.
	 * 
	 * @return the lffrete validation controller  
	 */  
	public ValidationController getLffreteValidationController()  
	{
		return this.lffreteValidationController;
	}  
  
	/**   
	 * Sets the lffrete validation controller.
	 * 
	 * @param lffreteValidationController the new lffrete validation controller  
	 */    
	public void setLffreteValidationController(ValidationController lffreteValidationController)   
	{
		this.lffreteValidationController = lffreteValidationController;
	} 
  
	/** 
	 * Gets the lffrete list validation controller. 
	 *  
	 * @return the lffrete list validation controller  
	 */   
	public ValidationController getLffreteListValidationController()
	{  
		return this.lffreteListValidationController;
	}  
   
	/**   
	 * Sets the lffrete list validation controller.  
	 *  
	 * @param lffreteListValidationController the new lffrete list validation controller 
	 */ 
	public void setLffreteListValidationController(ValidationController lffreteListValidationController)
	{ 
		this.lffreteListValidationController = lffreteListValidationController;
	} 
     
	/**  
	 * Gets the lffrete request validation controller. 
	 *
	 * @return the lffrete request validation controller 
	 */
	public ValidationController getLffreteRequestValidationController() 
	{ 
		return this.lffreteRequestValidationController;
	} 
 
	/** 
	 * Sets the lffrete request validation controller. 
	 *  
	 * @param lffreteRequestValidationController the new lffrete request validation controller 
	 */ 
	public void setLffreteRequestValidationController(ValidationController lffreteRequestValidationController) 
	{
		this.lffreteRequestValidationController = lffreteRequestValidationController;
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
	 * @see com.sensus.mlc.lffrete.bcf.ILffreteBCF#fetchAllLffretes(com.sensus.mlc.lffrete.model.request.InquiryLffreteRequest
	 */ 
	@Override 
	public InquiryLffreteResponse fetchAllLffrete(InquiryLffreteRequest inquiryLffreteRequest)
	{
		InquiryLffreteResponse response = new InquiryLffreteResponse();
		InternalResultsResponse<Lffrete> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFFRETE_REQUEST_NAME, inquiryLffreteRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLffreteBCL().fetchAllLffrete(inquiryLffreteRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETE_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffrete.bcf.ILffreteBCF#fetchLffreteByName(com.sensus.mlc.lffrete.model.request.LffreteRequest
	 */ 
	@Override 
	public LffreteResponse fetchLffreteById(LffreteRequest lffreteRequest)
	{  
		LffreteResponse response = new LffreteResponse();
		InternalResultsResponse<Lffrete> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFFRETE_REQUEST_NAME, lffreteRequest);
			context.putObjectToBeValidated(LFFRETE_NAME, lffreteRequest.getLffrete());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLffreteValidationController().validate(context)) 
			{   
				internalResponse = getLffreteBCL().fetchLffreteByName(lffreteRequest);
				response.setLffrete(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETE_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffrete.bcf.ILffreteBCF#insertLffrete(com.sensus.mlc.lffrete.model.request.LffreteRequest)
	 */ 
	@Override 
	public LffreteResponse insertLffrete(LffreteRequest lffreteRequest) 
	{ 
		LffreteResponse response = new LffreteResponse();
		InternalResultsResponse<Lffrete> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFFRETE_REQUEST_NAME, lffreteRequest);
			context.putObjectToBeValidated(LFFRETE_NAME, lffreteRequest.getLffrete());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLffreteValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLffreteBCL().insertLffrete(lffreteRequest);
					response.setLffrete(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETE_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffrete.bcf.ILffreteBCF#deleteLffrete(com.sensus.mlc.lffrete.model.request.LffreteRequest)
	 */  
	@Override  
	public LffreteResponse deleteLffrete(LffreteRequest lffreteRequest)
	{  
		LffreteResponse response = new LffreteResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFFRETE_REQUEST_NAME, lffreteRequest);
			context.putObjectToBeValidated(LFFRETE_NAME, lffreteRequest.getLffrete());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLffreteValidationController().validate(context))
			{
				internalResponse = getLffreteBCL().deleteLffrete(lffreteRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffrete.bcf.ILffreteBCF#updateLffrete(com.sensus.mlc.lffrete.model.request.LffreteRequest
	 */
	@Override 
	public LffreteResponse updateLffrete(LffreteRequest lffreteRequest)
	{ 
		LffreteResponse response = new LffreteResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFFRETE_REQUEST_NAME, lffreteRequest);
			context.putObjectToBeValidated(LFFRETE_NAME, lffreteRequest.getLffrete());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLffreteRequestValidationController().validate(context) && 
					getLffreteValidationController().validate(context))
			{ 
				internalResponse = getLffreteBCL().updateLffrete(lffreteRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETE_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
