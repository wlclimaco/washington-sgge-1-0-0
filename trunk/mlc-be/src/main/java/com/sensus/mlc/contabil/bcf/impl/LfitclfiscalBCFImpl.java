package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFITCLFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFITCLFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFITCLFISCAL_LIST;

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
import com.sensus.mlc.lfitclfiscal.bcf.ILfitclfiscalBCF;
import com.sensus.mlc.lfitclfiscal.bcl.ILfitclfiscalBCL;
import com.sensus.mlc.lfitclfiscal.model.Lfitclfiscal;
import com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.response.InquiryLfitclfiscalResponse;
import com.sensus.mlc.lfitclfiscal.model.response.LfitclfiscalResponse;

/** 
 * The Class LfitclfiscalBCFImpl.
 *
 * @author - Washington
 */
public class LfitclfiscalBCFImpl extends AbstractBaseBCF implements ILfitclfiscalBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFITCLFISCAL_REQUEST_NAME. */ 
	private static final String INQUIRY_LFITCLFISCAL_REQUEST_NAME = InquiryLfitclfiscalRequest.class.getSimpleName();

	/** The Constant LFITCLFISCAL_REQUEST_NAME. */ 
	private static final String LFITCLFISCAL_REQUEST_NAME = LfitclfiscalRequest.class.getSimpleName();
 
	/** The Constant LFITCLFISCAL_NAME. */ 
	private static final String LFITCLFISCAL_NAME = Lfitclfiscal.class.getSimpleName();

	/** The Constant DEFAULT_LFITCLFISCAL_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFITCLFISCAL_BCF_EXCEPTION_MSG = "sensus.mlc.lfitclfiscalbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfitclfiscalBCFImpl.class);
 
	/** The lfitclfiscal bcl. */ 
	private ILfitclfiscalBCL lfitclfiscalBCL;
 
	/** The lfitclfiscal validation controller. */ 
	private ValidationController lfitclfiscalValidationController;
  
	/** The lfitclfiscal list validation controller. */ 
	private ValidationController lfitclfiscalListValidationController;

	/** The lfitclfiscal request validation controller. */ 
	private ValidationController lfitclfiscalRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfitclfiscal bcl.
	 *  
	 * @return the lfitclfiscal bcl  
	 */ 
	public ILfitclfiscalBCL getLfitclfiscalBCL()
	{ 
		return this.lfitclfiscalBCL;
	} 
  
	/**  
	 * Sets the lfitclfiscal bcl.  
	 *   
	 * @param lfitclfiscalBCLObject the new lfitclfiscal bcl  
	 */ 
	public void setLfitclfiscalBCL(ILfitclfiscalBCL lfitclfiscalBCLObject) 
	{
		this.lfitclfiscalBCL = lfitclfiscalBCLObject;
	} 
 
	/**  
	 * Gets the lfitclfiscal validation controller.
	 * 
	 * @return the lfitclfiscal validation controller  
	 */  
	public ValidationController getLfitclfiscalValidationController()  
	{
		return this.lfitclfiscalValidationController;
	}  
  
	/**   
	 * Sets the lfitclfiscal validation controller.
	 * 
	 * @param lfitclfiscalValidationController the new lfitclfiscal validation controller  
	 */    
	public void setLfitclfiscalValidationController(ValidationController lfitclfiscalValidationController)   
	{
		this.lfitclfiscalValidationController = lfitclfiscalValidationController;
	} 
  
	/** 
	 * Gets the lfitclfiscal list validation controller. 
	 *  
	 * @return the lfitclfiscal list validation controller  
	 */   
	public ValidationController getLfitclfiscalListValidationController()
	{  
		return this.lfitclfiscalListValidationController;
	}  
   
	/**   
	 * Sets the lfitclfiscal list validation controller.  
	 *  
	 * @param lfitclfiscalListValidationController the new lfitclfiscal list validation controller 
	 */ 
	public void setLfitclfiscalListValidationController(ValidationController lfitclfiscalListValidationController)
	{ 
		this.lfitclfiscalListValidationController = lfitclfiscalListValidationController;
	} 
     
	/**  
	 * Gets the lfitclfiscal request validation controller. 
	 *
	 * @return the lfitclfiscal request validation controller 
	 */
	public ValidationController getLfitclfiscalRequestValidationController() 
	{ 
		return this.lfitclfiscalRequestValidationController;
	} 
 
	/** 
	 * Sets the lfitclfiscal request validation controller. 
	 *  
	 * @param lfitclfiscalRequestValidationController the new lfitclfiscal request validation controller 
	 */ 
	public void setLfitclfiscalRequestValidationController(ValidationController lfitclfiscalRequestValidationController) 
	{
		this.lfitclfiscalRequestValidationController = lfitclfiscalRequestValidationController;
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
	 * @see com.sensus.mlc.lfitclfiscal.bcf.ILfitclfiscalBCF#fetchAllLfitclfiscals(com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest
	 */ 
	@Override 
	public InquiryLfitclfiscalResponse fetchAllLfitclfiscal(InquiryLfitclfiscalRequest inquiryLfitclfiscalRequest)
	{
		InquiryLfitclfiscalResponse response = new InquiryLfitclfiscalResponse();
		InternalResultsResponse<Lfitclfiscal> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFITCLFISCAL_REQUEST_NAME, inquiryLfitclfiscalRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfitclfiscalBCL().fetchAllLfitclfiscal(inquiryLfitclfiscalRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCLFISCAL_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitclfiscal.bcf.ILfitclfiscalBCF#fetchLfitclfiscalByName(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest
	 */ 
	@Override 
	public LfitclfiscalResponse fetchLfitclfiscalById(LfitclfiscalRequest lfitclfiscalRequest)
	{  
		LfitclfiscalResponse response = new LfitclfiscalResponse();
		InternalResultsResponse<Lfitclfiscal> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFITCLFISCAL_REQUEST_NAME, lfitclfiscalRequest);
			context.putObjectToBeValidated(LFITCLFISCAL_NAME, lfitclfiscalRequest.getLfitclfiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitclfiscalValidationController().validate(context)) 
			{   
				internalResponse = getLfitclfiscalBCL().fetchLfitclfiscalByName(lfitclfiscalRequest);
				response.setLfitclfiscal(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCLFISCAL_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitclfiscal.bcf.ILfitclfiscalBCF#insertLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)
	 */ 
	@Override 
	public LfitclfiscalResponse insertLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest) 
	{ 
		LfitclfiscalResponse response = new LfitclfiscalResponse();
		InternalResultsResponse<Lfitclfiscal> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFITCLFISCAL_REQUEST_NAME, lfitclfiscalRequest);
			context.putObjectToBeValidated(LFITCLFISCAL_NAME, lfitclfiscalRequest.getLfitclfiscal());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfitclfiscalValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfitclfiscalBCL().insertLfitclfiscal(lfitclfiscalRequest);
					response.setLfitclfiscal(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCLFISCAL_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitclfiscal.bcf.ILfitclfiscalBCF#deleteLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)
	 */  
	@Override  
	public LfitclfiscalResponse deleteLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest)
	{  
		LfitclfiscalResponse response = new LfitclfiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFITCLFISCAL_REQUEST_NAME, lfitclfiscalRequest);
			context.putObjectToBeValidated(LFITCLFISCAL_NAME, lfitclfiscalRequest.getLfitclfiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitclfiscalValidationController().validate(context))
			{
				internalResponse = getLfitclfiscalBCL().deleteLfitclfiscal(lfitclfiscalRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCLFISCAL_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitclfiscal.bcf.ILfitclfiscalBCF#updateLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest
	 */
	@Override 
	public LfitclfiscalResponse updateLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest)
	{ 
		LfitclfiscalResponse response = new LfitclfiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFITCLFISCAL_REQUEST_NAME, lfitclfiscalRequest);
			context.putObjectToBeValidated(LFITCLFISCAL_NAME, lfitclfiscalRequest.getLfitclfiscal());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfitclfiscalRequestValidationController().validate(context) && 
					getLfitclfiscalValidationController().validate(context))
			{ 
				internalResponse = getLfitclfiscalBCL().updateLfitclfiscal(lfitclfiscalRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCLFISCAL_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
