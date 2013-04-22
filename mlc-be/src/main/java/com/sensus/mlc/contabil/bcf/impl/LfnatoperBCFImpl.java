package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFNATOPER;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFNATOPER;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFNATOPER_LIST;

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
import com.sensus.mlc.lfnatoper.bcf.ILfnatoperBCF;
import com.sensus.mlc.lfnatoper.bcl.ILfnatoperBCL;
import com.sensus.mlc.lfnatoper.model.Lfnatoper;
import com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.response.InquiryLfnatoperResponse;
import com.sensus.mlc.lfnatoper.model.response.LfnatoperResponse;

/** 
 * The Class LfnatoperBCFImpl.
 *
 * @author - Washington
 */
public class LfnatoperBCFImpl extends AbstractBaseBCF implements ILfnatoperBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFNATOPER_REQUEST_NAME. */ 
	private static final String INQUIRY_LFNATOPER_REQUEST_NAME = InquiryLfnatoperRequest.class.getSimpleName();

	/** The Constant LFNATOPER_REQUEST_NAME. */ 
	private static final String LFNATOPER_REQUEST_NAME = LfnatoperRequest.class.getSimpleName();
 
	/** The Constant LFNATOPER_NAME. */ 
	private static final String LFNATOPER_NAME = Lfnatoper.class.getSimpleName();

	/** The Constant DEFAULT_LFNATOPER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFNATOPER_BCF_EXCEPTION_MSG = "sensus.mlc.lfnatoperbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfnatoperBCFImpl.class);
 
	/** The lfnatoper bcl. */ 
	private ILfnatoperBCL lfnatoperBCL;
 
	/** The lfnatoper validation controller. */ 
	private ValidationController lfnatoperValidationController;
  
	/** The lfnatoper list validation controller. */ 
	private ValidationController lfnatoperListValidationController;

	/** The lfnatoper request validation controller. */ 
	private ValidationController lfnatoperRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfnatoper bcl.
	 *  
	 * @return the lfnatoper bcl  
	 */ 
	public ILfnatoperBCL getLfnatoperBCL()
	{ 
		return this.lfnatoperBCL;
	} 
  
	/**  
	 * Sets the lfnatoper bcl.  
	 *   
	 * @param lfnatoperBCLObject the new lfnatoper bcl  
	 */ 
	public void setLfnatoperBCL(ILfnatoperBCL lfnatoperBCLObject) 
	{
		this.lfnatoperBCL = lfnatoperBCLObject;
	} 
 
	/**  
	 * Gets the lfnatoper validation controller.
	 * 
	 * @return the lfnatoper validation controller  
	 */  
	public ValidationController getLfnatoperValidationController()  
	{
		return this.lfnatoperValidationController;
	}  
  
	/**   
	 * Sets the lfnatoper validation controller.
	 * 
	 * @param lfnatoperValidationController the new lfnatoper validation controller  
	 */    
	public void setLfnatoperValidationController(ValidationController lfnatoperValidationController)   
	{
		this.lfnatoperValidationController = lfnatoperValidationController;
	} 
  
	/** 
	 * Gets the lfnatoper list validation controller. 
	 *  
	 * @return the lfnatoper list validation controller  
	 */   
	public ValidationController getLfnatoperListValidationController()
	{  
		return this.lfnatoperListValidationController;
	}  
   
	/**   
	 * Sets the lfnatoper list validation controller.  
	 *  
	 * @param lfnatoperListValidationController the new lfnatoper list validation controller 
	 */ 
	public void setLfnatoperListValidationController(ValidationController lfnatoperListValidationController)
	{ 
		this.lfnatoperListValidationController = lfnatoperListValidationController;
	} 
     
	/**  
	 * Gets the lfnatoper request validation controller. 
	 *
	 * @return the lfnatoper request validation controller 
	 */
	public ValidationController getLfnatoperRequestValidationController() 
	{ 
		return this.lfnatoperRequestValidationController;
	} 
 
	/** 
	 * Sets the lfnatoper request validation controller. 
	 *  
	 * @param lfnatoperRequestValidationController the new lfnatoper request validation controller 
	 */ 
	public void setLfnatoperRequestValidationController(ValidationController lfnatoperRequestValidationController) 
	{
		this.lfnatoperRequestValidationController = lfnatoperRequestValidationController;
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
	 * @see com.sensus.mlc.lfnatoper.bcf.ILfnatoperBCF#fetchAllLfnatopers(com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest
	 */ 
	@Override 
	public InquiryLfnatoperResponse fetchAllLfnatoper(InquiryLfnatoperRequest inquiryLfnatoperRequest)
	{
		InquiryLfnatoperResponse response = new InquiryLfnatoperResponse();
		InternalResultsResponse<Lfnatoper> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFNATOPER_REQUEST_NAME, inquiryLfnatoperRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfnatoperBCL().fetchAllLfnatoper(inquiryLfnatoperRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNATOPER_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnatoper.bcf.ILfnatoperBCF#fetchLfnatoperByName(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest
	 */ 
	@Override 
	public LfnatoperResponse fetchLfnatoperById(LfnatoperRequest lfnatoperRequest)
	{  
		LfnatoperResponse response = new LfnatoperResponse();
		InternalResultsResponse<Lfnatoper> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFNATOPER_REQUEST_NAME, lfnatoperRequest);
			context.putObjectToBeValidated(LFNATOPER_NAME, lfnatoperRequest.getLfnatoper());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfnatoperValidationController().validate(context)) 
			{   
				internalResponse = getLfnatoperBCL().fetchLfnatoperByName(lfnatoperRequest);
				response.setLfnatoper(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNATOPER_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfnatoper.bcf.ILfnatoperBCF#insertLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)
	 */ 
	@Override 
	public LfnatoperResponse insertLfnatoper(LfnatoperRequest lfnatoperRequest) 
	{ 
		LfnatoperResponse response = new LfnatoperResponse();
		InternalResultsResponse<Lfnatoper> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFNATOPER_REQUEST_NAME, lfnatoperRequest);
			context.putObjectToBeValidated(LFNATOPER_NAME, lfnatoperRequest.getLfnatoper());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfnatoperValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfnatoperBCL().insertLfnatoper(lfnatoperRequest);
					response.setLfnatoper(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNATOPER_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfnatoper.bcf.ILfnatoperBCF#deleteLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)
	 */  
	@Override  
	public LfnatoperResponse deleteLfnatoper(LfnatoperRequest lfnatoperRequest)
	{  
		LfnatoperResponse response = new LfnatoperResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFNATOPER_REQUEST_NAME, lfnatoperRequest);
			context.putObjectToBeValidated(LFNATOPER_NAME, lfnatoperRequest.getLfnatoper());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfnatoperValidationController().validate(context))
			{
				internalResponse = getLfnatoperBCL().deleteLfnatoper(lfnatoperRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNATOPER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnatoper.bcf.ILfnatoperBCF#updateLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest
	 */
	@Override 
	public LfnatoperResponse updateLfnatoper(LfnatoperRequest lfnatoperRequest)
	{ 
		LfnatoperResponse response = new LfnatoperResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFNATOPER_REQUEST_NAME, lfnatoperRequest);
			context.putObjectToBeValidated(LFNATOPER_NAME, lfnatoperRequest.getLfnatoper());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfnatoperRequestValidationController().validate(context) && 
					getLfnatoperValidationController().validate(context))
			{ 
				internalResponse = getLfnatoperBCL().updateLfnatoper(lfnatoperRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNATOPER_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
