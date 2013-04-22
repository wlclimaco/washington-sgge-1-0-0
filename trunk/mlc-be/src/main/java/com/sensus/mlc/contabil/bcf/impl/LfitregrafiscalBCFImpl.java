package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFITREGRAFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFITREGRAFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFITREGRAFISCAL_LIST;

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
import com.sensus.mlc.lfitregrafiscal.bcf.ILfitregrafiscalBCF;
import com.sensus.mlc.lfitregrafiscal.bcl.ILfitregrafiscalBCL;
import com.sensus.mlc.lfitregrafiscal.model.Lfitregrafiscal;
import com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.response.InquiryLfitregrafiscalResponse;
import com.sensus.mlc.lfitregrafiscal.model.response.LfitregrafiscalResponse;

/** 
 * The Class LfitregrafiscalBCFImpl.
 *
 * @author - Washington
 */
public class LfitregrafiscalBCFImpl extends AbstractBaseBCF implements ILfitregrafiscalBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFITREGRAFISCAL_REQUEST_NAME. */ 
	private static final String INQUIRY_LFITREGRAFISCAL_REQUEST_NAME = InquiryLfitregrafiscalRequest.class.getSimpleName();

	/** The Constant LFITREGRAFISCAL_REQUEST_NAME. */ 
	private static final String LFITREGRAFISCAL_REQUEST_NAME = LfitregrafiscalRequest.class.getSimpleName();
 
	/** The Constant LFITREGRAFISCAL_NAME. */ 
	private static final String LFITREGRAFISCAL_NAME = Lfitregrafiscal.class.getSimpleName();

	/** The Constant DEFAULT_LFITREGRAFISCAL_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFITREGRAFISCAL_BCF_EXCEPTION_MSG = "sensus.mlc.lfitregrafiscalbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfitregrafiscalBCFImpl.class);
 
	/** The lfitregrafiscal bcl. */ 
	private ILfitregrafiscalBCL lfitregrafiscalBCL;
 
	/** The lfitregrafiscal validation controller. */ 
	private ValidationController lfitregrafiscalValidationController;
  
	/** The lfitregrafiscal list validation controller. */ 
	private ValidationController lfitregrafiscalListValidationController;

	/** The lfitregrafiscal request validation controller. */ 
	private ValidationController lfitregrafiscalRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfitregrafiscal bcl.
	 *  
	 * @return the lfitregrafiscal bcl  
	 */ 
	public ILfitregrafiscalBCL getLfitregrafiscalBCL()
	{ 
		return this.lfitregrafiscalBCL;
	} 
  
	/**  
	 * Sets the lfitregrafiscal bcl.  
	 *   
	 * @param lfitregrafiscalBCLObject the new lfitregrafiscal bcl  
	 */ 
	public void setLfitregrafiscalBCL(ILfitregrafiscalBCL lfitregrafiscalBCLObject) 
	{
		this.lfitregrafiscalBCL = lfitregrafiscalBCLObject;
	} 
 
	/**  
	 * Gets the lfitregrafiscal validation controller.
	 * 
	 * @return the lfitregrafiscal validation controller  
	 */  
	public ValidationController getLfitregrafiscalValidationController()  
	{
		return this.lfitregrafiscalValidationController;
	}  
  
	/**   
	 * Sets the lfitregrafiscal validation controller.
	 * 
	 * @param lfitregrafiscalValidationController the new lfitregrafiscal validation controller  
	 */    
	public void setLfitregrafiscalValidationController(ValidationController lfitregrafiscalValidationController)   
	{
		this.lfitregrafiscalValidationController = lfitregrafiscalValidationController;
	} 
  
	/** 
	 * Gets the lfitregrafiscal list validation controller. 
	 *  
	 * @return the lfitregrafiscal list validation controller  
	 */   
	public ValidationController getLfitregrafiscalListValidationController()
	{  
		return this.lfitregrafiscalListValidationController;
	}  
   
	/**   
	 * Sets the lfitregrafiscal list validation controller.  
	 *  
	 * @param lfitregrafiscalListValidationController the new lfitregrafiscal list validation controller 
	 */ 
	public void setLfitregrafiscalListValidationController(ValidationController lfitregrafiscalListValidationController)
	{ 
		this.lfitregrafiscalListValidationController = lfitregrafiscalListValidationController;
	} 
     
	/**  
	 * Gets the lfitregrafiscal request validation controller. 
	 *
	 * @return the lfitregrafiscal request validation controller 
	 */
	public ValidationController getLfitregrafiscalRequestValidationController() 
	{ 
		return this.lfitregrafiscalRequestValidationController;
	} 
 
	/** 
	 * Sets the lfitregrafiscal request validation controller. 
	 *  
	 * @param lfitregrafiscalRequestValidationController the new lfitregrafiscal request validation controller 
	 */ 
	public void setLfitregrafiscalRequestValidationController(ValidationController lfitregrafiscalRequestValidationController) 
	{
		this.lfitregrafiscalRequestValidationController = lfitregrafiscalRequestValidationController;
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
	 * @see com.sensus.mlc.lfitregrafiscal.bcf.ILfitregrafiscalBCF#fetchAllLfitregrafiscals(com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest
	 */ 
	@Override 
	public InquiryLfitregrafiscalResponse fetchAllLfitregrafiscal(InquiryLfitregrafiscalRequest inquiryLfitregrafiscalRequest)
	{
		InquiryLfitregrafiscalResponse response = new InquiryLfitregrafiscalResponse();
		InternalResultsResponse<Lfitregrafiscal> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFITREGRAFISCAL_REQUEST_NAME, inquiryLfitregrafiscalRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfitregrafiscalBCL().fetchAllLfitregrafiscal(inquiryLfitregrafiscalRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITREGRAFISCAL_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitregrafiscal.bcf.ILfitregrafiscalBCF#fetchLfitregrafiscalByName(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest
	 */ 
	@Override 
	public LfitregrafiscalResponse fetchLfitregrafiscalById(LfitregrafiscalRequest lfitregrafiscalRequest)
	{  
		LfitregrafiscalResponse response = new LfitregrafiscalResponse();
		InternalResultsResponse<Lfitregrafiscal> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFITREGRAFISCAL_REQUEST_NAME, lfitregrafiscalRequest);
			context.putObjectToBeValidated(LFITREGRAFISCAL_NAME, lfitregrafiscalRequest.getLfitregrafiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitregrafiscalValidationController().validate(context)) 
			{   
				internalResponse = getLfitregrafiscalBCL().fetchLfitregrafiscalByName(lfitregrafiscalRequest);
				response.setLfitregrafiscal(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITREGRAFISCAL_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitregrafiscal.bcf.ILfitregrafiscalBCF#insertLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)
	 */ 
	@Override 
	public LfitregrafiscalResponse insertLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest) 
	{ 
		LfitregrafiscalResponse response = new LfitregrafiscalResponse();
		InternalResultsResponse<Lfitregrafiscal> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFITREGRAFISCAL_REQUEST_NAME, lfitregrafiscalRequest);
			context.putObjectToBeValidated(LFITREGRAFISCAL_NAME, lfitregrafiscalRequest.getLfitregrafiscal());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfitregrafiscalValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfitregrafiscalBCL().insertLfitregrafiscal(lfitregrafiscalRequest);
					response.setLfitregrafiscal(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITREGRAFISCAL_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitregrafiscal.bcf.ILfitregrafiscalBCF#deleteLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)
	 */  
	@Override  
	public LfitregrafiscalResponse deleteLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest)
	{  
		LfitregrafiscalResponse response = new LfitregrafiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFITREGRAFISCAL_REQUEST_NAME, lfitregrafiscalRequest);
			context.putObjectToBeValidated(LFITREGRAFISCAL_NAME, lfitregrafiscalRequest.getLfitregrafiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitregrafiscalValidationController().validate(context))
			{
				internalResponse = getLfitregrafiscalBCL().deleteLfitregrafiscal(lfitregrafiscalRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITREGRAFISCAL_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitregrafiscal.bcf.ILfitregrafiscalBCF#updateLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest
	 */
	@Override 
	public LfitregrafiscalResponse updateLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest)
	{ 
		LfitregrafiscalResponse response = new LfitregrafiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFITREGRAFISCAL_REQUEST_NAME, lfitregrafiscalRequest);
			context.putObjectToBeValidated(LFITREGRAFISCAL_NAME, lfitregrafiscalRequest.getLfitregrafiscal());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfitregrafiscalRequestValidationController().validate(context) && 
					getLfitregrafiscalValidationController().validate(context))
			{ 
				internalResponse = getLfitregrafiscalBCL().updateLfitregrafiscal(lfitregrafiscalRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITREGRAFISCAL_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
