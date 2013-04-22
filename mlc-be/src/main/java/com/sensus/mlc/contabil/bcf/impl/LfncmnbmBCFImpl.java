package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFNCMNBM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFNCMNBM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFNCMNBM_LIST;

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
import com.sensus.mlc.lfncmnbm.bcf.ILfncmnbmBCF;
import com.sensus.mlc.lfncmnbm.bcl.ILfncmnbmBCL;
import com.sensus.mlc.lfncmnbm.model.Lfncmnbm;
import com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.response.InquiryLfncmnbmResponse;
import com.sensus.mlc.lfncmnbm.model.response.LfncmnbmResponse;

/** 
 * The Class LfncmnbmBCFImpl.
 *
 * @author - Washington
 */
public class LfncmnbmBCFImpl extends AbstractBaseBCF implements ILfncmnbmBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFNCMNBM_REQUEST_NAME. */ 
	private static final String INQUIRY_LFNCMNBM_REQUEST_NAME = InquiryLfncmnbmRequest.class.getSimpleName();

	/** The Constant LFNCMNBM_REQUEST_NAME. */ 
	private static final String LFNCMNBM_REQUEST_NAME = LfncmnbmRequest.class.getSimpleName();
 
	/** The Constant LFNCMNBM_NAME. */ 
	private static final String LFNCMNBM_NAME = Lfncmnbm.class.getSimpleName();

	/** The Constant DEFAULT_LFNCMNBM_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFNCMNBM_BCF_EXCEPTION_MSG = "sensus.mlc.lfncmnbmbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfncmnbmBCFImpl.class);
 
	/** The lfncmnbm bcl. */ 
	private ILfncmnbmBCL lfncmnbmBCL;
 
	/** The lfncmnbm validation controller. */ 
	private ValidationController lfncmnbmValidationController;
  
	/** The lfncmnbm list validation controller. */ 
	private ValidationController lfncmnbmListValidationController;

	/** The lfncmnbm request validation controller. */ 
	private ValidationController lfncmnbmRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfncmnbm bcl.
	 *  
	 * @return the lfncmnbm bcl  
	 */ 
	public ILfncmnbmBCL getLfncmnbmBCL()
	{ 
		return this.lfncmnbmBCL;
	} 
  
	/**  
	 * Sets the lfncmnbm bcl.  
	 *   
	 * @param lfncmnbmBCLObject the new lfncmnbm bcl  
	 */ 
	public void setLfncmnbmBCL(ILfncmnbmBCL lfncmnbmBCLObject) 
	{
		this.lfncmnbmBCL = lfncmnbmBCLObject;
	} 
 
	/**  
	 * Gets the lfncmnbm validation controller.
	 * 
	 * @return the lfncmnbm validation controller  
	 */  
	public ValidationController getLfncmnbmValidationController()  
	{
		return this.lfncmnbmValidationController;
	}  
  
	/**   
	 * Sets the lfncmnbm validation controller.
	 * 
	 * @param lfncmnbmValidationController the new lfncmnbm validation controller  
	 */    
	public void setLfncmnbmValidationController(ValidationController lfncmnbmValidationController)   
	{
		this.lfncmnbmValidationController = lfncmnbmValidationController;
	} 
  
	/** 
	 * Gets the lfncmnbm list validation controller. 
	 *  
	 * @return the lfncmnbm list validation controller  
	 */   
	public ValidationController getLfncmnbmListValidationController()
	{  
		return this.lfncmnbmListValidationController;
	}  
   
	/**   
	 * Sets the lfncmnbm list validation controller.  
	 *  
	 * @param lfncmnbmListValidationController the new lfncmnbm list validation controller 
	 */ 
	public void setLfncmnbmListValidationController(ValidationController lfncmnbmListValidationController)
	{ 
		this.lfncmnbmListValidationController = lfncmnbmListValidationController;
	} 
     
	/**  
	 * Gets the lfncmnbm request validation controller. 
	 *
	 * @return the lfncmnbm request validation controller 
	 */
	public ValidationController getLfncmnbmRequestValidationController() 
	{ 
		return this.lfncmnbmRequestValidationController;
	} 
 
	/** 
	 * Sets the lfncmnbm request validation controller. 
	 *  
	 * @param lfncmnbmRequestValidationController the new lfncmnbm request validation controller 
	 */ 
	public void setLfncmnbmRequestValidationController(ValidationController lfncmnbmRequestValidationController) 
	{
		this.lfncmnbmRequestValidationController = lfncmnbmRequestValidationController;
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
	 * @see com.sensus.mlc.lfncmnbm.bcf.ILfncmnbmBCF#fetchAllLfncmnbms(com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest
	 */ 
	@Override 
	public InquiryLfncmnbmResponse fetchAllLfncmnbm(InquiryLfncmnbmRequest inquiryLfncmnbmRequest)
	{
		InquiryLfncmnbmResponse response = new InquiryLfncmnbmResponse();
		InternalResultsResponse<Lfncmnbm> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFNCMNBM_REQUEST_NAME, inquiryLfncmnbmRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfncmnbmBCL().fetchAllLfncmnbm(inquiryLfncmnbmRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCMNBM_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncmnbm.bcf.ILfncmnbmBCF#fetchLfncmnbmByName(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest
	 */ 
	@Override 
	public LfncmnbmResponse fetchLfncmnbmById(LfncmnbmRequest lfncmnbmRequest)
	{  
		LfncmnbmResponse response = new LfncmnbmResponse();
		InternalResultsResponse<Lfncmnbm> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFNCMNBM_REQUEST_NAME, lfncmnbmRequest);
			context.putObjectToBeValidated(LFNCMNBM_NAME, lfncmnbmRequest.getLfncmnbm());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfncmnbmValidationController().validate(context)) 
			{   
				internalResponse = getLfncmnbmBCL().fetchLfncmnbmByName(lfncmnbmRequest);
				response.setLfncmnbm(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCMNBM_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfncmnbm.bcf.ILfncmnbmBCF#insertLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)
	 */ 
	@Override 
	public LfncmnbmResponse insertLfncmnbm(LfncmnbmRequest lfncmnbmRequest) 
	{ 
		LfncmnbmResponse response = new LfncmnbmResponse();
		InternalResultsResponse<Lfncmnbm> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFNCMNBM_REQUEST_NAME, lfncmnbmRequest);
			context.putObjectToBeValidated(LFNCMNBM_NAME, lfncmnbmRequest.getLfncmnbm());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfncmnbmValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfncmnbmBCL().insertLfncmnbm(lfncmnbmRequest);
					response.setLfncmnbm(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCMNBM_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfncmnbm.bcf.ILfncmnbmBCF#deleteLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)
	 */  
	@Override  
	public LfncmnbmResponse deleteLfncmnbm(LfncmnbmRequest lfncmnbmRequest)
	{  
		LfncmnbmResponse response = new LfncmnbmResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFNCMNBM_REQUEST_NAME, lfncmnbmRequest);
			context.putObjectToBeValidated(LFNCMNBM_NAME, lfncmnbmRequest.getLfncmnbm());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfncmnbmValidationController().validate(context))
			{
				internalResponse = getLfncmnbmBCL().deleteLfncmnbm(lfncmnbmRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCMNBM_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncmnbm.bcf.ILfncmnbmBCF#updateLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest
	 */
	@Override 
	public LfncmnbmResponse updateLfncmnbm(LfncmnbmRequest lfncmnbmRequest)
	{ 
		LfncmnbmResponse response = new LfncmnbmResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFNCMNBM_REQUEST_NAME, lfncmnbmRequest);
			context.putObjectToBeValidated(LFNCMNBM_NAME, lfncmnbmRequest.getLfncmnbm());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfncmnbmRequestValidationController().validate(context) && 
					getLfncmnbmValidationController().validate(context))
			{ 
				internalResponse = getLfncmnbmBCL().updateLfncmnbm(lfncmnbmRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCMNBM_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
