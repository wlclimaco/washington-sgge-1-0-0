package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFREGRAFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFREGRAFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFREGRAFISCAL_LIST;

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
import com.sensus.mlc.lfregrafiscal.bcf.ILfregrafiscalBCF;
import com.sensus.mlc.lfregrafiscal.bcl.ILfregrafiscalBCL;
import com.sensus.mlc.lfregrafiscal.model.Lfregrafiscal;
import com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.response.InquiryLfregrafiscalResponse;
import com.sensus.mlc.lfregrafiscal.model.response.LfregrafiscalResponse;

/** 
 * The Class LfregrafiscalBCFImpl.
 *
 * @author - Washington
 */
public class LfregrafiscalBCFImpl extends AbstractBaseBCF implements ILfregrafiscalBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFREGRAFISCAL_REQUEST_NAME. */ 
	private static final String INQUIRY_LFREGRAFISCAL_REQUEST_NAME = InquiryLfregrafiscalRequest.class.getSimpleName();

	/** The Constant LFREGRAFISCAL_REQUEST_NAME. */ 
	private static final String LFREGRAFISCAL_REQUEST_NAME = LfregrafiscalRequest.class.getSimpleName();
 
	/** The Constant LFREGRAFISCAL_NAME. */ 
	private static final String LFREGRAFISCAL_NAME = Lfregrafiscal.class.getSimpleName();

	/** The Constant DEFAULT_LFREGRAFISCAL_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFREGRAFISCAL_BCF_EXCEPTION_MSG = "sensus.mlc.lfregrafiscalbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfregrafiscalBCFImpl.class);
 
	/** The lfregrafiscal bcl. */ 
	private ILfregrafiscalBCL lfregrafiscalBCL;
 
	/** The lfregrafiscal validation controller. */ 
	private ValidationController lfregrafiscalValidationController;
  
	/** The lfregrafiscal list validation controller. */ 
	private ValidationController lfregrafiscalListValidationController;

	/** The lfregrafiscal request validation controller. */ 
	private ValidationController lfregrafiscalRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfregrafiscal bcl.
	 *  
	 * @return the lfregrafiscal bcl  
	 */ 
	public ILfregrafiscalBCL getLfregrafiscalBCL()
	{ 
		return this.lfregrafiscalBCL;
	} 
  
	/**  
	 * Sets the lfregrafiscal bcl.  
	 *   
	 * @param lfregrafiscalBCLObject the new lfregrafiscal bcl  
	 */ 
	public void setLfregrafiscalBCL(ILfregrafiscalBCL lfregrafiscalBCLObject) 
	{
		this.lfregrafiscalBCL = lfregrafiscalBCLObject;
	} 
 
	/**  
	 * Gets the lfregrafiscal validation controller.
	 * 
	 * @return the lfregrafiscal validation controller  
	 */  
	public ValidationController getLfregrafiscalValidationController()  
	{
		return this.lfregrafiscalValidationController;
	}  
  
	/**   
	 * Sets the lfregrafiscal validation controller.
	 * 
	 * @param lfregrafiscalValidationController the new lfregrafiscal validation controller  
	 */    
	public void setLfregrafiscalValidationController(ValidationController lfregrafiscalValidationController)   
	{
		this.lfregrafiscalValidationController = lfregrafiscalValidationController;
	} 
  
	/** 
	 * Gets the lfregrafiscal list validation controller. 
	 *  
	 * @return the lfregrafiscal list validation controller  
	 */   
	public ValidationController getLfregrafiscalListValidationController()
	{  
		return this.lfregrafiscalListValidationController;
	}  
   
	/**   
	 * Sets the lfregrafiscal list validation controller.  
	 *  
	 * @param lfregrafiscalListValidationController the new lfregrafiscal list validation controller 
	 */ 
	public void setLfregrafiscalListValidationController(ValidationController lfregrafiscalListValidationController)
	{ 
		this.lfregrafiscalListValidationController = lfregrafiscalListValidationController;
	} 
     
	/**  
	 * Gets the lfregrafiscal request validation controller. 
	 *
	 * @return the lfregrafiscal request validation controller 
	 */
	public ValidationController getLfregrafiscalRequestValidationController() 
	{ 
		return this.lfregrafiscalRequestValidationController;
	} 
 
	/** 
	 * Sets the lfregrafiscal request validation controller. 
	 *  
	 * @param lfregrafiscalRequestValidationController the new lfregrafiscal request validation controller 
	 */ 
	public void setLfregrafiscalRequestValidationController(ValidationController lfregrafiscalRequestValidationController) 
	{
		this.lfregrafiscalRequestValidationController = lfregrafiscalRequestValidationController;
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
	 * @see com.sensus.mlc.lfregrafiscal.bcf.ILfregrafiscalBCF#fetchAllLfregrafiscals(com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest
	 */ 
	@Override 
	public InquiryLfregrafiscalResponse fetchAllLfregrafiscal(InquiryLfregrafiscalRequest inquiryLfregrafiscalRequest)
	{
		InquiryLfregrafiscalResponse response = new InquiryLfregrafiscalResponse();
		InternalResultsResponse<Lfregrafiscal> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFREGRAFISCAL_REQUEST_NAME, inquiryLfregrafiscalRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfregrafiscalBCL().fetchAllLfregrafiscal(inquiryLfregrafiscalRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFREGRAFISCAL_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfregrafiscal.bcf.ILfregrafiscalBCF#fetchLfregrafiscalByName(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest
	 */ 
	@Override 
	public LfregrafiscalResponse fetchLfregrafiscalById(LfregrafiscalRequest lfregrafiscalRequest)
	{  
		LfregrafiscalResponse response = new LfregrafiscalResponse();
		InternalResultsResponse<Lfregrafiscal> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFREGRAFISCAL_REQUEST_NAME, lfregrafiscalRequest);
			context.putObjectToBeValidated(LFREGRAFISCAL_NAME, lfregrafiscalRequest.getLfregrafiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfregrafiscalValidationController().validate(context)) 
			{   
				internalResponse = getLfregrafiscalBCL().fetchLfregrafiscalByName(lfregrafiscalRequest);
				response.setLfregrafiscal(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFREGRAFISCAL_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfregrafiscal.bcf.ILfregrafiscalBCF#insertLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)
	 */ 
	@Override 
	public LfregrafiscalResponse insertLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest) 
	{ 
		LfregrafiscalResponse response = new LfregrafiscalResponse();
		InternalResultsResponse<Lfregrafiscal> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFREGRAFISCAL_REQUEST_NAME, lfregrafiscalRequest);
			context.putObjectToBeValidated(LFREGRAFISCAL_NAME, lfregrafiscalRequest.getLfregrafiscal());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfregrafiscalValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfregrafiscalBCL().insertLfregrafiscal(lfregrafiscalRequest);
					response.setLfregrafiscal(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFREGRAFISCAL_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfregrafiscal.bcf.ILfregrafiscalBCF#deleteLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)
	 */  
	@Override  
	public LfregrafiscalResponse deleteLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest)
	{  
		LfregrafiscalResponse response = new LfregrafiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFREGRAFISCAL_REQUEST_NAME, lfregrafiscalRequest);
			context.putObjectToBeValidated(LFREGRAFISCAL_NAME, lfregrafiscalRequest.getLfregrafiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfregrafiscalValidationController().validate(context))
			{
				internalResponse = getLfregrafiscalBCL().deleteLfregrafiscal(lfregrafiscalRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFREGRAFISCAL_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfregrafiscal.bcf.ILfregrafiscalBCF#updateLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest
	 */
	@Override 
	public LfregrafiscalResponse updateLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest)
	{ 
		LfregrafiscalResponse response = new LfregrafiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFREGRAFISCAL_REQUEST_NAME, lfregrafiscalRequest);
			context.putObjectToBeValidated(LFREGRAFISCAL_NAME, lfregrafiscalRequest.getLfregrafiscal());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfregrafiscalRequestValidationController().validate(context) && 
					getLfregrafiscalValidationController().validate(context))
			{ 
				internalResponse = getLfregrafiscalBCL().updateLfregrafiscal(lfregrafiscalRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFREGRAFISCAL_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
