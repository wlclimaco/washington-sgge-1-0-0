package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFNCM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFNCM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFNCM_LIST;

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
import com.sensus.mlc.lfncm.bcf.ILfncmBCF;
import com.sensus.mlc.lfncm.bcl.ILfncmBCL;
import com.sensus.mlc.lfncm.model.Lfncm;
import com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest;
import com.sensus.mlc.lfncm.model.request.LfncmRequest;
import com.sensus.mlc.lfncm.model.response.InquiryLfncmResponse;
import com.sensus.mlc.lfncm.model.response.LfncmResponse;

/** 
 * The Class LfncmBCFImpl.
 *
 * @author - Washington
 */
public class LfncmBCFImpl extends AbstractBaseBCF implements ILfncmBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFNCM_REQUEST_NAME. */ 
	private static final String INQUIRY_LFNCM_REQUEST_NAME = InquiryLfncmRequest.class.getSimpleName();

	/** The Constant LFNCM_REQUEST_NAME. */ 
	private static final String LFNCM_REQUEST_NAME = LfncmRequest.class.getSimpleName();
 
	/** The Constant LFNCM_NAME. */ 
	private static final String LFNCM_NAME = Lfncm.class.getSimpleName();

	/** The Constant DEFAULT_LFNCM_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFNCM_BCF_EXCEPTION_MSG = "sensus.mlc.lfncmbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfncmBCFImpl.class);
 
	/** The lfncm bcl. */ 
	private ILfncmBCL lfncmBCL;
 
	/** The lfncm validation controller. */ 
	private ValidationController lfncmValidationController;
  
	/** The lfncm list validation controller. */ 
	private ValidationController lfncmListValidationController;

	/** The lfncm request validation controller. */ 
	private ValidationController lfncmRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfncm bcl.
	 *  
	 * @return the lfncm bcl  
	 */ 
	public ILfncmBCL getLfncmBCL()
	{ 
		return this.lfncmBCL;
	} 
  
	/**  
	 * Sets the lfncm bcl.  
	 *   
	 * @param lfncmBCLObject the new lfncm bcl  
	 */ 
	public void setLfncmBCL(ILfncmBCL lfncmBCLObject) 
	{
		this.lfncmBCL = lfncmBCLObject;
	} 
 
	/**  
	 * Gets the lfncm validation controller.
	 * 
	 * @return the lfncm validation controller  
	 */  
	public ValidationController getLfncmValidationController()  
	{
		return this.lfncmValidationController;
	}  
  
	/**   
	 * Sets the lfncm validation controller.
	 * 
	 * @param lfncmValidationController the new lfncm validation controller  
	 */    
	public void setLfncmValidationController(ValidationController lfncmValidationController)   
	{
		this.lfncmValidationController = lfncmValidationController;
	} 
  
	/** 
	 * Gets the lfncm list validation controller. 
	 *  
	 * @return the lfncm list validation controller  
	 */   
	public ValidationController getLfncmListValidationController()
	{  
		return this.lfncmListValidationController;
	}  
   
	/**   
	 * Sets the lfncm list validation controller.  
	 *  
	 * @param lfncmListValidationController the new lfncm list validation controller 
	 */ 
	public void setLfncmListValidationController(ValidationController lfncmListValidationController)
	{ 
		this.lfncmListValidationController = lfncmListValidationController;
	} 
     
	/**  
	 * Gets the lfncm request validation controller. 
	 *
	 * @return the lfncm request validation controller 
	 */
	public ValidationController getLfncmRequestValidationController() 
	{ 
		return this.lfncmRequestValidationController;
	} 
 
	/** 
	 * Sets the lfncm request validation controller. 
	 *  
	 * @param lfncmRequestValidationController the new lfncm request validation controller 
	 */ 
	public void setLfncmRequestValidationController(ValidationController lfncmRequestValidationController) 
	{
		this.lfncmRequestValidationController = lfncmRequestValidationController;
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
	 * @see com.sensus.mlc.lfncm.bcf.ILfncmBCF#fetchAllLfncms(com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest
	 */ 
	@Override 
	public InquiryLfncmResponse fetchAllLfncm(InquiryLfncmRequest inquiryLfncmRequest)
	{
		InquiryLfncmResponse response = new InquiryLfncmResponse();
		InternalResultsResponse<Lfncm> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFNCM_REQUEST_NAME, inquiryLfncmRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfncmBCL().fetchAllLfncm(inquiryLfncmRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCM_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncm.bcf.ILfncmBCF#fetchLfncmByName(com.sensus.mlc.lfncm.model.request.LfncmRequest
	 */ 
	@Override 
	public LfncmResponse fetchLfncmById(LfncmRequest lfncmRequest)
	{  
		LfncmResponse response = new LfncmResponse();
		InternalResultsResponse<Lfncm> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFNCM_REQUEST_NAME, lfncmRequest);
			context.putObjectToBeValidated(LFNCM_NAME, lfncmRequest.getLfncm());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfncmValidationController().validate(context)) 
			{   
				internalResponse = getLfncmBCL().fetchLfncmByName(lfncmRequest);
				response.setLfncm(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCM_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfncm.bcf.ILfncmBCF#insertLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest)
	 */ 
	@Override 
	public LfncmResponse insertLfncm(LfncmRequest lfncmRequest) 
	{ 
		LfncmResponse response = new LfncmResponse();
		InternalResultsResponse<Lfncm> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFNCM_REQUEST_NAME, lfncmRequest);
			context.putObjectToBeValidated(LFNCM_NAME, lfncmRequest.getLfncm());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfncmValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfncmBCL().insertLfncm(lfncmRequest);
					response.setLfncm(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCM_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfncm.bcf.ILfncmBCF#deleteLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest)
	 */  
	@Override  
	public LfncmResponse deleteLfncm(LfncmRequest lfncmRequest)
	{  
		LfncmResponse response = new LfncmResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFNCM_REQUEST_NAME, lfncmRequest);
			context.putObjectToBeValidated(LFNCM_NAME, lfncmRequest.getLfncm());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfncmValidationController().validate(context))
			{
				internalResponse = getLfncmBCL().deleteLfncm(lfncmRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCM_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncm.bcf.ILfncmBCF#updateLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest
	 */
	@Override 
	public LfncmResponse updateLfncm(LfncmRequest lfncmRequest)
	{ 
		LfncmResponse response = new LfncmResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFNCM_REQUEST_NAME, lfncmRequest);
			context.putObjectToBeValidated(LFNCM_NAME, lfncmRequest.getLfncm());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfncmRequestValidationController().validate(context) && 
					getLfncmValidationController().validate(context))
			{ 
				internalResponse = getLfncmBCL().updateLfncm(lfncmRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNCM_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
