package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFITNATOPER;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFITNATOPER;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFITNATOPER_LIST;

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
import com.sensus.mlc.lfitnatoper.bcf.ILfitnatoperBCF;
import com.sensus.mlc.lfitnatoper.bcl.ILfitnatoperBCL;
import com.sensus.mlc.lfitnatoper.model.Lfitnatoper;
import com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.response.InquiryLfitnatoperResponse;
import com.sensus.mlc.lfitnatoper.model.response.LfitnatoperResponse;

/** 
 * The Class LfitnatoperBCFImpl.
 *
 * @author - Washington
 */
public class LfitnatoperBCFImpl extends AbstractBaseBCF implements ILfitnatoperBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFITNATOPER_REQUEST_NAME. */ 
	private static final String INQUIRY_LFITNATOPER_REQUEST_NAME = InquiryLfitnatoperRequest.class.getSimpleName();

	/** The Constant LFITNATOPER_REQUEST_NAME. */ 
	private static final String LFITNATOPER_REQUEST_NAME = LfitnatoperRequest.class.getSimpleName();
 
	/** The Constant LFITNATOPER_NAME. */ 
	private static final String LFITNATOPER_NAME = Lfitnatoper.class.getSimpleName();

	/** The Constant DEFAULT_LFITNATOPER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFITNATOPER_BCF_EXCEPTION_MSG = "sensus.mlc.lfitnatoperbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfitnatoperBCFImpl.class);
 
	/** The lfitnatoper bcl. */ 
	private ILfitnatoperBCL lfitnatoperBCL;
 
	/** The lfitnatoper validation controller. */ 
	private ValidationController lfitnatoperValidationController;
  
	/** The lfitnatoper list validation controller. */ 
	private ValidationController lfitnatoperListValidationController;

	/** The lfitnatoper request validation controller. */ 
	private ValidationController lfitnatoperRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfitnatoper bcl.
	 *  
	 * @return the lfitnatoper bcl  
	 */ 
	public ILfitnatoperBCL getLfitnatoperBCL()
	{ 
		return this.lfitnatoperBCL;
	} 
  
	/**  
	 * Sets the lfitnatoper bcl.  
	 *   
	 * @param lfitnatoperBCLObject the new lfitnatoper bcl  
	 */ 
	public void setLfitnatoperBCL(ILfitnatoperBCL lfitnatoperBCLObject) 
	{
		this.lfitnatoperBCL = lfitnatoperBCLObject;
	} 
 
	/**  
	 * Gets the lfitnatoper validation controller.
	 * 
	 * @return the lfitnatoper validation controller  
	 */  
	public ValidationController getLfitnatoperValidationController()  
	{
		return this.lfitnatoperValidationController;
	}  
  
	/**   
	 * Sets the lfitnatoper validation controller.
	 * 
	 * @param lfitnatoperValidationController the new lfitnatoper validation controller  
	 */    
	public void setLfitnatoperValidationController(ValidationController lfitnatoperValidationController)   
	{
		this.lfitnatoperValidationController = lfitnatoperValidationController;
	} 
  
	/** 
	 * Gets the lfitnatoper list validation controller. 
	 *  
	 * @return the lfitnatoper list validation controller  
	 */   
	public ValidationController getLfitnatoperListValidationController()
	{  
		return this.lfitnatoperListValidationController;
	}  
   
	/**   
	 * Sets the lfitnatoper list validation controller.  
	 *  
	 * @param lfitnatoperListValidationController the new lfitnatoper list validation controller 
	 */ 
	public void setLfitnatoperListValidationController(ValidationController lfitnatoperListValidationController)
	{ 
		this.lfitnatoperListValidationController = lfitnatoperListValidationController;
	} 
     
	/**  
	 * Gets the lfitnatoper request validation controller. 
	 *
	 * @return the lfitnatoper request validation controller 
	 */
	public ValidationController getLfitnatoperRequestValidationController() 
	{ 
		return this.lfitnatoperRequestValidationController;
	} 
 
	/** 
	 * Sets the lfitnatoper request validation controller. 
	 *  
	 * @param lfitnatoperRequestValidationController the new lfitnatoper request validation controller 
	 */ 
	public void setLfitnatoperRequestValidationController(ValidationController lfitnatoperRequestValidationController) 
	{
		this.lfitnatoperRequestValidationController = lfitnatoperRequestValidationController;
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
	 * @see com.sensus.mlc.lfitnatoper.bcf.ILfitnatoperBCF#fetchAllLfitnatopers(com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest
	 */ 
	@Override 
	public InquiryLfitnatoperResponse fetchAllLfitnatoper(InquiryLfitnatoperRequest inquiryLfitnatoperRequest)
	{
		InquiryLfitnatoperResponse response = new InquiryLfitnatoperResponse();
		InternalResultsResponse<Lfitnatoper> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFITNATOPER_REQUEST_NAME, inquiryLfitnatoperRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfitnatoperBCL().fetchAllLfitnatoper(inquiryLfitnatoperRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITNATOPER_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitnatoper.bcf.ILfitnatoperBCF#fetchLfitnatoperByName(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest
	 */ 
	@Override 
	public LfitnatoperResponse fetchLfitnatoperById(LfitnatoperRequest lfitnatoperRequest)
	{  
		LfitnatoperResponse response = new LfitnatoperResponse();
		InternalResultsResponse<Lfitnatoper> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFITNATOPER_REQUEST_NAME, lfitnatoperRequest);
			context.putObjectToBeValidated(LFITNATOPER_NAME, lfitnatoperRequest.getLfitnatoper());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitnatoperValidationController().validate(context)) 
			{   
				internalResponse = getLfitnatoperBCL().fetchLfitnatoperByName(lfitnatoperRequest);
				response.setLfitnatoper(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITNATOPER_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitnatoper.bcf.ILfitnatoperBCF#insertLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)
	 */ 
	@Override 
	public LfitnatoperResponse insertLfitnatoper(LfitnatoperRequest lfitnatoperRequest) 
	{ 
		LfitnatoperResponse response = new LfitnatoperResponse();
		InternalResultsResponse<Lfitnatoper> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFITNATOPER_REQUEST_NAME, lfitnatoperRequest);
			context.putObjectToBeValidated(LFITNATOPER_NAME, lfitnatoperRequest.getLfitnatoper());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfitnatoperValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfitnatoperBCL().insertLfitnatoper(lfitnatoperRequest);
					response.setLfitnatoper(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITNATOPER_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitnatoper.bcf.ILfitnatoperBCF#deleteLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)
	 */  
	@Override  
	public LfitnatoperResponse deleteLfitnatoper(LfitnatoperRequest lfitnatoperRequest)
	{  
		LfitnatoperResponse response = new LfitnatoperResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFITNATOPER_REQUEST_NAME, lfitnatoperRequest);
			context.putObjectToBeValidated(LFITNATOPER_NAME, lfitnatoperRequest.getLfitnatoper());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitnatoperValidationController().validate(context))
			{
				internalResponse = getLfitnatoperBCL().deleteLfitnatoper(lfitnatoperRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITNATOPER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitnatoper.bcf.ILfitnatoperBCF#updateLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest
	 */
	@Override 
	public LfitnatoperResponse updateLfitnatoper(LfitnatoperRequest lfitnatoperRequest)
	{ 
		LfitnatoperResponse response = new LfitnatoperResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFITNATOPER_REQUEST_NAME, lfitnatoperRequest);
			context.putObjectToBeValidated(LFITNATOPER_NAME, lfitnatoperRequest.getLfitnatoper());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfitnatoperRequestValidationController().validate(context) && 
					getLfitnatoperValidationController().validate(context))
			{ 
				internalResponse = getLfitnatoperBCL().updateLfitnatoper(lfitnatoperRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITNATOPER_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
