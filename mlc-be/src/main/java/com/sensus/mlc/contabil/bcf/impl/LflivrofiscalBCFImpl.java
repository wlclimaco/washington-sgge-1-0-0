package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFLIVROFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFLIVROFISCAL;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFLIVROFISCAL_LIST;

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
import com.sensus.mlc.lflivrofiscal.bcf.ILflivrofiscalBCF;
import com.sensus.mlc.lflivrofiscal.bcl.ILflivrofiscalBCL;
import com.sensus.mlc.lflivrofiscal.model.Lflivrofiscal;
import com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.response.InquiryLflivrofiscalResponse;
import com.sensus.mlc.lflivrofiscal.model.response.LflivrofiscalResponse;

/** 
 * The Class LflivrofiscalBCFImpl.
 *
 * @author - Washington
 */
public class LflivrofiscalBCFImpl extends AbstractBaseBCF implements ILflivrofiscalBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFLIVROFISCAL_REQUEST_NAME. */ 
	private static final String INQUIRY_LFLIVROFISCAL_REQUEST_NAME = InquiryLflivrofiscalRequest.class.getSimpleName();

	/** The Constant LFLIVROFISCAL_REQUEST_NAME. */ 
	private static final String LFLIVROFISCAL_REQUEST_NAME = LflivrofiscalRequest.class.getSimpleName();
 
	/** The Constant LFLIVROFISCAL_NAME. */ 
	private static final String LFLIVROFISCAL_NAME = Lflivrofiscal.class.getSimpleName();

	/** The Constant DEFAULT_LFLIVROFISCAL_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFLIVROFISCAL_BCF_EXCEPTION_MSG = "sensus.mlc.lflivrofiscalbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LflivrofiscalBCFImpl.class);
 
	/** The lflivrofiscal bcl. */ 
	private ILflivrofiscalBCL lflivrofiscalBCL;
 
	/** The lflivrofiscal validation controller. */ 
	private ValidationController lflivrofiscalValidationController;
  
	/** The lflivrofiscal list validation controller. */ 
	private ValidationController lflivrofiscalListValidationController;

	/** The lflivrofiscal request validation controller. */ 
	private ValidationController lflivrofiscalRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lflivrofiscal bcl.
	 *  
	 * @return the lflivrofiscal bcl  
	 */ 
	public ILflivrofiscalBCL getLflivrofiscalBCL()
	{ 
		return this.lflivrofiscalBCL;
	} 
  
	/**  
	 * Sets the lflivrofiscal bcl.  
	 *   
	 * @param lflivrofiscalBCLObject the new lflivrofiscal bcl  
	 */ 
	public void setLflivrofiscalBCL(ILflivrofiscalBCL lflivrofiscalBCLObject) 
	{
		this.lflivrofiscalBCL = lflivrofiscalBCLObject;
	} 
 
	/**  
	 * Gets the lflivrofiscal validation controller.
	 * 
	 * @return the lflivrofiscal validation controller  
	 */  
	public ValidationController getLflivrofiscalValidationController()  
	{
		return this.lflivrofiscalValidationController;
	}  
  
	/**   
	 * Sets the lflivrofiscal validation controller.
	 * 
	 * @param lflivrofiscalValidationController the new lflivrofiscal validation controller  
	 */    
	public void setLflivrofiscalValidationController(ValidationController lflivrofiscalValidationController)   
	{
		this.lflivrofiscalValidationController = lflivrofiscalValidationController;
	} 
  
	/** 
	 * Gets the lflivrofiscal list validation controller. 
	 *  
	 * @return the lflivrofiscal list validation controller  
	 */   
	public ValidationController getLflivrofiscalListValidationController()
	{  
		return this.lflivrofiscalListValidationController;
	}  
   
	/**   
	 * Sets the lflivrofiscal list validation controller.  
	 *  
	 * @param lflivrofiscalListValidationController the new lflivrofiscal list validation controller 
	 */ 
	public void setLflivrofiscalListValidationController(ValidationController lflivrofiscalListValidationController)
	{ 
		this.lflivrofiscalListValidationController = lflivrofiscalListValidationController;
	} 
     
	/**  
	 * Gets the lflivrofiscal request validation controller. 
	 *
	 * @return the lflivrofiscal request validation controller 
	 */
	public ValidationController getLflivrofiscalRequestValidationController() 
	{ 
		return this.lflivrofiscalRequestValidationController;
	} 
 
	/** 
	 * Sets the lflivrofiscal request validation controller. 
	 *  
	 * @param lflivrofiscalRequestValidationController the new lflivrofiscal request validation controller 
	 */ 
	public void setLflivrofiscalRequestValidationController(ValidationController lflivrofiscalRequestValidationController) 
	{
		this.lflivrofiscalRequestValidationController = lflivrofiscalRequestValidationController;
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
	 * @see com.sensus.mlc.lflivrofiscal.bcf.ILflivrofiscalBCF#fetchAllLflivrofiscals(com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest
	 */ 
	@Override 
	public InquiryLflivrofiscalResponse fetchAllLflivrofiscal(InquiryLflivrofiscalRequest inquiryLflivrofiscalRequest)
	{
		InquiryLflivrofiscalResponse response = new InquiryLflivrofiscalResponse();
		InternalResultsResponse<Lflivrofiscal> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFLIVROFISCAL_REQUEST_NAME, inquiryLflivrofiscalRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLflivrofiscalBCL().fetchAllLflivrofiscal(inquiryLflivrofiscalRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFLIVROFISCAL_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lflivrofiscal.bcf.ILflivrofiscalBCF#fetchLflivrofiscalByName(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest
	 */ 
	@Override 
	public LflivrofiscalResponse fetchLflivrofiscalById(LflivrofiscalRequest lflivrofiscalRequest)
	{  
		LflivrofiscalResponse response = new LflivrofiscalResponse();
		InternalResultsResponse<Lflivrofiscal> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFLIVROFISCAL_REQUEST_NAME, lflivrofiscalRequest);
			context.putObjectToBeValidated(LFLIVROFISCAL_NAME, lflivrofiscalRequest.getLflivrofiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLflivrofiscalValidationController().validate(context)) 
			{   
				internalResponse = getLflivrofiscalBCL().fetchLflivrofiscalByName(lflivrofiscalRequest);
				response.setLflivrofiscal(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFLIVROFISCAL_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lflivrofiscal.bcf.ILflivrofiscalBCF#insertLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)
	 */ 
	@Override 
	public LflivrofiscalResponse insertLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest) 
	{ 
		LflivrofiscalResponse response = new LflivrofiscalResponse();
		InternalResultsResponse<Lflivrofiscal> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFLIVROFISCAL_REQUEST_NAME, lflivrofiscalRequest);
			context.putObjectToBeValidated(LFLIVROFISCAL_NAME, lflivrofiscalRequest.getLflivrofiscal());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLflivrofiscalValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLflivrofiscalBCL().insertLflivrofiscal(lflivrofiscalRequest);
					response.setLflivrofiscal(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFLIVROFISCAL_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lflivrofiscal.bcf.ILflivrofiscalBCF#deleteLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)
	 */  
	@Override  
	public LflivrofiscalResponse deleteLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest)
	{  
		LflivrofiscalResponse response = new LflivrofiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFLIVROFISCAL_REQUEST_NAME, lflivrofiscalRequest);
			context.putObjectToBeValidated(LFLIVROFISCAL_NAME, lflivrofiscalRequest.getLflivrofiscal());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLflivrofiscalValidationController().validate(context))
			{
				internalResponse = getLflivrofiscalBCL().deleteLflivrofiscal(lflivrofiscalRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFLIVROFISCAL_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lflivrofiscal.bcf.ILflivrofiscalBCF#updateLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest
	 */
	@Override 
	public LflivrofiscalResponse updateLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest)
	{ 
		LflivrofiscalResponse response = new LflivrofiscalResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFLIVROFISCAL_REQUEST_NAME, lflivrofiscalRequest);
			context.putObjectToBeValidated(LFLIVROFISCAL_NAME, lflivrofiscalRequest.getLflivrofiscal());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLflivrofiscalRequestValidationController().validate(context) && 
					getLflivrofiscalValidationController().validate(context))
			{ 
				internalResponse = getLflivrofiscalBCL().updateLflivrofiscal(lflivrofiscalRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFLIVROFISCAL_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
