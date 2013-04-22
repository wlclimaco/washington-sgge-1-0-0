package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFCLFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFCLFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFCLFISCAL_LIST;

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
import com.sensus.mlc.lfclfiscal.bcf.ILfclfiscalBCF;
import com.sensus.mlc.lfclfiscal.bcl.ILfclfiscalBCL;
import com.sensus.mlc.lfclfiscal.model.Lfclfiscal;
import com.sensus.mlc.lfclfiscal.model.request.InquiryLfclfiscalRequest;
import com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest;
import com.sensus.mlc.lfclfiscal.model.response.InquiryLfclfiscalResponse;
import com.sensus.mlc.lfclfiscal.model.response.LfclfiscalResponse;

/** 
 * The Class LfclfiscalBCFImpl.
 *
 * @author - Washington
 */
public class LfclfiscalBCFImpl extends AbstractBaseBCF implements ILfclfiscalBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFCLFISCAL_REQUEST_NAME. */ 
	private static final String INQUIRY_LFCLFISCAL_REQUEST_NAME = InquiryLfclfiscalRequest.class.getSimpleName();

	/** The Constant LFCLFISCAL_REQUEST_NAME. */ 
	private static final String LFCLFISCAL_REQUEST_NAME = LfclfiscalRequest.class.getSimpleName();
 
	/** The Constant LFCLFISCAL_NAME. */ 
	private static final String LFCLFISCAL_NAME = Lfclfiscal.class.getSimpleName();

	/** The Constant DEFAULT_LFCLFISCAL_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFCLFISCAL_BCF_EXCEPTION_MSG = "sensus.mlc.lfclfiscalbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfclfiscalBCFImpl.class);
 
	/** The lfclfiscal bcl. */ 
	private ILfclfiscalBCL lfclfiscalBCL;
 
	/** The lfclfiscal validation controller. */ 
	private ValidationController lfclfiscalValidationController;
  
	/** The lfclfiscal list validation controller. */ 
	private ValidationController lfclfiscalListValidationController;

	/** The lfclfiscal request validation controller. */ 
	private ValidationController lfclfiscalRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfclfiscal bcl.
	 *  
	 * @return the lfclfiscal bcl  
	 */ 
	public ILfclfiscalBCL getLfclfiscalBCL()
	{ 
		return this.lfclfiscalBCL;
	} 
  
	/**  
	 * Sets the lfclfiscal bcl.  
	 *   
	 * @param lfclfiscalBCLObject the new lfclfiscal bcl  
	 */ 
	public void setLfclfiscalBCL(ILfclfiscalBCL lfclfiscalBCLObject) 
	{
		this.lfclfiscalBCL = lfclfiscalBCLObject;
	} 
 
	/**  
	 * Gets the lfclfiscal validation controller.
	 * 
	 * @return the lfclfiscal validation controller  
	 */  
	public ValidationController getLfclfiscalValidationController()  
	{
		return this.lfclfiscalValidationController;
	}  
  
	/**   
	 * Sets the lfclfiscal validation controller.
	 * 
	 * @param lfclfiscalValidationController the new lfclfiscal validation controller  
	 */    
	public void setLfclfiscalValidationController(ValidationController lfclfiscalValidationController)   
	{
		this.lfclfiscalValidationController = lfclfiscalValidationController;
	} 
  
	/** 
	 * Gets the lfclfiscal list validation controller. 
	 *  
	 * @return the lfclfiscal list validation controller  
	 */   
	public ValidationController getLfclfiscalListValidationController()
	{  
		return this.lfclfiscalListValidationController;
	}  
   
	/**   
	 * Sets the lfclfiscal list validation controller.  
	 *  
	 * @param lfclfiscalListValidationController the new lfclfiscal list validation controller 
	 */ 
	public void setLfclfiscalListValidationController(ValidationController lfclfiscalListValidationController)
	{ 
		this.lfclfiscalListValidationController = lfclfiscalListValidationController;
	} 
     
	/**  
	 * Gets the lfclfiscal request validation controller. 
	 *
	 * @return the lfclfiscal request validation controller 
	 */
	public ValidationController getLfclfiscalRequestValidationController() 
	{ 
		return this.lfclfiscalRequestValidationController;
	} 
 
	/** 
	 * Sets the lfclfiscal request validation controller. 
	 *  
	 * @param lfclfiscalRequestValidationController the new lfclfiscal request validation controller 
	 */ 
	public void setLfclfiscalRequestValidationController(ValidationController lfclfiscalRequestValidationController) 
	{
		this.lfclfiscalRequestValidationController = lfclfiscalRequestValidationController;
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
	 * @see com.sensus.mlc.lfclfiscal.bcf.ILfclfiscalBCF#fetchAllLfclfiscals(com.sensus.mlc.lfclfiscal.model.request.InquiryLfclfiscalRequest
	 */ 
	@Override 
	public InquiryLfclfiscalResponse fetchAllLfclfiscal(InquiryLfclfiscalRequest inquiryLfclfiscalRequest)
	{
		InquiryLfclfiscalResponse response = new InquiryLfclfiscalResponse();
		InternalResultsResponse<Lfclfiscal> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFCLFISCAL_REQUEST_NAME, inquiryLfclfiscalRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfclfiscalBCL().fetchAllLfclfiscal(inquiryLfclfiscalRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCLFISCAL_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfclfiscal.bcf.ILfclfiscalBCF#fetchLfclfiscalByName(com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest
	 */ 
	@Override 
	public LfclfiscalResponse fetchLfclfiscalById(LfclfiscalRequest lfclfiscalRequest)
	{  
		LfclfiscalResponse response = new LfclfiscalResponse();
		InternalResultsResponse<Lfclfiscal> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFCLFISCAL_REQUEST_NAME, lfclfiscalRequest);
			context.putObjectToBeValidated(LFCLFISCAL_NAME, lfclfiscalRequest.getLfclfiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfclfiscalValidationController().validate(context)) 
			{   
				internalResponse = getLfclfiscalBCL().fetchLfclfiscalByName(lfclfiscalRequest);
				response.setLfclfiscal(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCLFISCAL_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfclfiscal.bcf.ILfclfiscalBCF#insertLfclfiscal(com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest)
	 */ 
	@Override 
	public LfclfiscalResponse insertLfclfiscal(LfclfiscalRequest lfclfiscalRequest) 
	{ 
		LfclfiscalResponse response = new LfclfiscalResponse();
		InternalResultsResponse<Lfclfiscal> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFCLFISCAL_REQUEST_NAME, lfclfiscalRequest);
			context.putObjectToBeValidated(LFCLFISCAL_NAME, lfclfiscalRequest.getLfclfiscal());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfclfiscalValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfclfiscalBCL().insertLfclfiscal(lfclfiscalRequest);
					response.setLfclfiscal(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCLFISCAL_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfclfiscal.bcf.ILfclfiscalBCF#deleteLfclfiscal(com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest)
	 */  
	@Override  
	public LfclfiscalResponse deleteLfclfiscal(LfclfiscalRequest lfclfiscalRequest)
	{  
		LfclfiscalResponse response = new LfclfiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFCLFISCAL_REQUEST_NAME, lfclfiscalRequest);
			context.putObjectToBeValidated(LFCLFISCAL_NAME, lfclfiscalRequest.getLfclfiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfclfiscalValidationController().validate(context))
			{
				internalResponse = getLfclfiscalBCL().deleteLfclfiscal(lfclfiscalRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCLFISCAL_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfclfiscal.bcf.ILfclfiscalBCF#updateLfclfiscal(com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest
	 */
	@Override 
	public LfclfiscalResponse updateLfclfiscal(LfclfiscalRequest lfclfiscalRequest)
	{ 
		LfclfiscalResponse response = new LfclfiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFCLFISCAL_REQUEST_NAME, lfclfiscalRequest);
			context.putObjectToBeValidated(LFCLFISCAL_NAME, lfclfiscalRequest.getLfclfiscal());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfclfiscalRequestValidationController().validate(context) && 
					getLfclfiscalValidationController().validate(context))
			{ 
				internalResponse = getLfclfiscalBCL().updateLfclfiscal(lfclfiscalRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCLFISCAL_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
