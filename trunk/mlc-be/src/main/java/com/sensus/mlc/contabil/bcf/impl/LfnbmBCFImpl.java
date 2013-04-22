package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFNBM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFNBM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFNBM_LIST;

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
import com.sensus.mlc.lfnbm.bcf.ILfnbmBCF;
import com.sensus.mlc.lfnbm.bcl.ILfnbmBCL;
import com.sensus.mlc.lfnbm.model.Lfnbm;
import com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest;
import com.sensus.mlc.lfnbm.model.request.LfnbmRequest;
import com.sensus.mlc.lfnbm.model.response.InquiryLfnbmResponse;
import com.sensus.mlc.lfnbm.model.response.LfnbmResponse;

/** 
 * The Class LfnbmBCFImpl.
 *
 * @author - Washington
 */
public class LfnbmBCFImpl extends AbstractBaseBCF implements ILfnbmBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFNBM_REQUEST_NAME. */ 
	private static final String INQUIRY_LFNBM_REQUEST_NAME = InquiryLfnbmRequest.class.getSimpleName();

	/** The Constant LFNBM_REQUEST_NAME. */ 
	private static final String LFNBM_REQUEST_NAME = LfnbmRequest.class.getSimpleName();
 
	/** The Constant LFNBM_NAME. */ 
	private static final String LFNBM_NAME = Lfnbm.class.getSimpleName();

	/** The Constant DEFAULT_LFNBM_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFNBM_BCF_EXCEPTION_MSG = "sensus.mlc.lfnbmbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfnbmBCFImpl.class);
 
	/** The lfnbm bcl. */ 
	private ILfnbmBCL lfnbmBCL;
 
	/** The lfnbm validation controller. */ 
	private ValidationController lfnbmValidationController;
  
	/** The lfnbm list validation controller. */ 
	private ValidationController lfnbmListValidationController;

	/** The lfnbm request validation controller. */ 
	private ValidationController lfnbmRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfnbm bcl.
	 *  
	 * @return the lfnbm bcl  
	 */ 
	public ILfnbmBCL getLfnbmBCL()
	{ 
		return this.lfnbmBCL;
	} 
  
	/**  
	 * Sets the lfnbm bcl.  
	 *   
	 * @param lfnbmBCLObject the new lfnbm bcl  
	 */ 
	public void setLfnbmBCL(ILfnbmBCL lfnbmBCLObject) 
	{
		this.lfnbmBCL = lfnbmBCLObject;
	} 
 
	/**  
	 * Gets the lfnbm validation controller.
	 * 
	 * @return the lfnbm validation controller  
	 */  
	public ValidationController getLfnbmValidationController()  
	{
		return this.lfnbmValidationController;
	}  
  
	/**   
	 * Sets the lfnbm validation controller.
	 * 
	 * @param lfnbmValidationController the new lfnbm validation controller  
	 */    
	public void setLfnbmValidationController(ValidationController lfnbmValidationController)   
	{
		this.lfnbmValidationController = lfnbmValidationController;
	} 
  
	/** 
	 * Gets the lfnbm list validation controller. 
	 *  
	 * @return the lfnbm list validation controller  
	 */   
	public ValidationController getLfnbmListValidationController()
	{  
		return this.lfnbmListValidationController;
	}  
   
	/**   
	 * Sets the lfnbm list validation controller.  
	 *  
	 * @param lfnbmListValidationController the new lfnbm list validation controller 
	 */ 
	public void setLfnbmListValidationController(ValidationController lfnbmListValidationController)
	{ 
		this.lfnbmListValidationController = lfnbmListValidationController;
	} 
     
	/**  
	 * Gets the lfnbm request validation controller. 
	 *
	 * @return the lfnbm request validation controller 
	 */
	public ValidationController getLfnbmRequestValidationController() 
	{ 
		return this.lfnbmRequestValidationController;
	} 
 
	/** 
	 * Sets the lfnbm request validation controller. 
	 *  
	 * @param lfnbmRequestValidationController the new lfnbm request validation controller 
	 */ 
	public void setLfnbmRequestValidationController(ValidationController lfnbmRequestValidationController) 
	{
		this.lfnbmRequestValidationController = lfnbmRequestValidationController;
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
	 * @see com.sensus.mlc.lfnbm.bcf.ILfnbmBCF#fetchAllLfnbms(com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest
	 */ 
	@Override 
	public InquiryLfnbmResponse fetchAllLfnbm(InquiryLfnbmRequest inquiryLfnbmRequest)
	{
		InquiryLfnbmResponse response = new InquiryLfnbmResponse();
		InternalResultsResponse<Lfnbm> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFNBM_REQUEST_NAME, inquiryLfnbmRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfnbmBCL().fetchAllLfnbm(inquiryLfnbmRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNBM_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnbm.bcf.ILfnbmBCF#fetchLfnbmByName(com.sensus.mlc.lfnbm.model.request.LfnbmRequest
	 */ 
	@Override 
	public LfnbmResponse fetchLfnbmById(LfnbmRequest lfnbmRequest)
	{  
		LfnbmResponse response = new LfnbmResponse();
		InternalResultsResponse<Lfnbm> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFNBM_REQUEST_NAME, lfnbmRequest);
			context.putObjectToBeValidated(LFNBM_NAME, lfnbmRequest.getLfnbm());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfnbmValidationController().validate(context)) 
			{   
				internalResponse = getLfnbmBCL().fetchLfnbmByName(lfnbmRequest);
				response.setLfnbm(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNBM_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfnbm.bcf.ILfnbmBCF#insertLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)
	 */ 
	@Override 
	public LfnbmResponse insertLfnbm(LfnbmRequest lfnbmRequest) 
	{ 
		LfnbmResponse response = new LfnbmResponse();
		InternalResultsResponse<Lfnbm> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFNBM_REQUEST_NAME, lfnbmRequest);
			context.putObjectToBeValidated(LFNBM_NAME, lfnbmRequest.getLfnbm());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfnbmValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfnbmBCL().insertLfnbm(lfnbmRequest);
					response.setLfnbm(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNBM_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfnbm.bcf.ILfnbmBCF#deleteLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)
	 */  
	@Override  
	public LfnbmResponse deleteLfnbm(LfnbmRequest lfnbmRequest)
	{  
		LfnbmResponse response = new LfnbmResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFNBM_REQUEST_NAME, lfnbmRequest);
			context.putObjectToBeValidated(LFNBM_NAME, lfnbmRequest.getLfnbm());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfnbmValidationController().validate(context))
			{
				internalResponse = getLfnbmBCL().deleteLfnbm(lfnbmRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNBM_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnbm.bcf.ILfnbmBCF#updateLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest
	 */
	@Override 
	public LfnbmResponse updateLfnbm(LfnbmRequest lfnbmRequest)
	{ 
		LfnbmResponse response = new LfnbmResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFNBM_REQUEST_NAME, lfnbmRequest);
			context.putObjectToBeValidated(LFNBM_NAME, lfnbmRequest.getLfnbm());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfnbmRequestValidationController().validate(context) && 
					getLfnbmValidationController().validate(context))
			{ 
				internalResponse = getLfnbmBCL().updateLfnbm(lfnbmRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFNBM_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
