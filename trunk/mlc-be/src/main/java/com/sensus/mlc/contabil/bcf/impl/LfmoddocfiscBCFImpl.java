package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFMODDOCFISC;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFMODDOCFISC;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFMODDOCFISC_LIST;

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
import com.sensus.mlc.lfmoddocfisc.bcf.ILfmoddocfiscBCF;
import com.sensus.mlc.lfmoddocfisc.bcl.ILfmoddocfiscBCL;
import com.sensus.mlc.lfmoddocfisc.model.Lfmoddocfisc;
import com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.response.InquiryLfmoddocfiscResponse;
import com.sensus.mlc.lfmoddocfisc.model.response.LfmoddocfiscResponse;

/** 
 * The Class LfmoddocfiscBCFImpl.
 *
 * @author - Washington
 */
public class LfmoddocfiscBCFImpl extends AbstractBaseBCF implements ILfmoddocfiscBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFMODDOCFISC_REQUEST_NAME. */ 
	private static final String INQUIRY_LFMODDOCFISC_REQUEST_NAME = InquiryLfmoddocfiscRequest.class.getSimpleName();

	/** The Constant LFMODDOCFISC_REQUEST_NAME. */ 
	private static final String LFMODDOCFISC_REQUEST_NAME = LfmoddocfiscRequest.class.getSimpleName();
 
	/** The Constant LFMODDOCFISC_NAME. */ 
	private static final String LFMODDOCFISC_NAME = Lfmoddocfisc.class.getSimpleName();

	/** The Constant DEFAULT_LFMODDOCFISC_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFMODDOCFISC_BCF_EXCEPTION_MSG = "sensus.mlc.lfmoddocfiscbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfmoddocfiscBCFImpl.class);
 
	/** The lfmoddocfisc bcl. */ 
	private ILfmoddocfiscBCL lfmoddocfiscBCL;
 
	/** The lfmoddocfisc validation controller. */ 
	private ValidationController lfmoddocfiscValidationController;
  
	/** The lfmoddocfisc list validation controller. */ 
	private ValidationController lfmoddocfiscListValidationController;

	/** The lfmoddocfisc request validation controller. */ 
	private ValidationController lfmoddocfiscRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfmoddocfisc bcl.
	 *  
	 * @return the lfmoddocfisc bcl  
	 */ 
	public ILfmoddocfiscBCL getLfmoddocfiscBCL()
	{ 
		return this.lfmoddocfiscBCL;
	} 
  
	/**  
	 * Sets the lfmoddocfisc bcl.  
	 *   
	 * @param lfmoddocfiscBCLObject the new lfmoddocfisc bcl  
	 */ 
	public void setLfmoddocfiscBCL(ILfmoddocfiscBCL lfmoddocfiscBCLObject) 
	{
		this.lfmoddocfiscBCL = lfmoddocfiscBCLObject;
	} 
 
	/**  
	 * Gets the lfmoddocfisc validation controller.
	 * 
	 * @return the lfmoddocfisc validation controller  
	 */  
	public ValidationController getLfmoddocfiscValidationController()  
	{
		return this.lfmoddocfiscValidationController;
	}  
  
	/**   
	 * Sets the lfmoddocfisc validation controller.
	 * 
	 * @param lfmoddocfiscValidationController the new lfmoddocfisc validation controller  
	 */    
	public void setLfmoddocfiscValidationController(ValidationController lfmoddocfiscValidationController)   
	{
		this.lfmoddocfiscValidationController = lfmoddocfiscValidationController;
	} 
  
	/** 
	 * Gets the lfmoddocfisc list validation controller. 
	 *  
	 * @return the lfmoddocfisc list validation controller  
	 */   
	public ValidationController getLfmoddocfiscListValidationController()
	{  
		return this.lfmoddocfiscListValidationController;
	}  
   
	/**   
	 * Sets the lfmoddocfisc list validation controller.  
	 *  
	 * @param lfmoddocfiscListValidationController the new lfmoddocfisc list validation controller 
	 */ 
	public void setLfmoddocfiscListValidationController(ValidationController lfmoddocfiscListValidationController)
	{ 
		this.lfmoddocfiscListValidationController = lfmoddocfiscListValidationController;
	} 
     
	/**  
	 * Gets the lfmoddocfisc request validation controller. 
	 *
	 * @return the lfmoddocfisc request validation controller 
	 */
	public ValidationController getLfmoddocfiscRequestValidationController() 
	{ 
		return this.lfmoddocfiscRequestValidationController;
	} 
 
	/** 
	 * Sets the lfmoddocfisc request validation controller. 
	 *  
	 * @param lfmoddocfiscRequestValidationController the new lfmoddocfisc request validation controller 
	 */ 
	public void setLfmoddocfiscRequestValidationController(ValidationController lfmoddocfiscRequestValidationController) 
	{
		this.lfmoddocfiscRequestValidationController = lfmoddocfiscRequestValidationController;
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
	 * @see com.sensus.mlc.lfmoddocfisc.bcf.ILfmoddocfiscBCF#fetchAllLfmoddocfiscs(com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest
	 */ 
	@Override 
	public InquiryLfmoddocfiscResponse fetchAllLfmoddocfisc(InquiryLfmoddocfiscRequest inquiryLfmoddocfiscRequest)
	{
		InquiryLfmoddocfiscResponse response = new InquiryLfmoddocfiscResponse();
		InternalResultsResponse<Lfmoddocfisc> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFMODDOCFISC_REQUEST_NAME, inquiryLfmoddocfiscRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfmoddocfiscBCL().fetchAllLfmoddocfisc(inquiryLfmoddocfiscRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODDOCFISC_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmoddocfisc.bcf.ILfmoddocfiscBCF#fetchLfmoddocfiscByName(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest
	 */ 
	@Override 
	public LfmoddocfiscResponse fetchLfmoddocfiscById(LfmoddocfiscRequest lfmoddocfiscRequest)
	{  
		LfmoddocfiscResponse response = new LfmoddocfiscResponse();
		InternalResultsResponse<Lfmoddocfisc> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFMODDOCFISC_REQUEST_NAME, lfmoddocfiscRequest);
			context.putObjectToBeValidated(LFMODDOCFISC_NAME, lfmoddocfiscRequest.getLfmoddocfisc());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfmoddocfiscValidationController().validate(context)) 
			{   
				internalResponse = getLfmoddocfiscBCL().fetchLfmoddocfiscByName(lfmoddocfiscRequest);
				response.setLfmoddocfisc(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODDOCFISC_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmoddocfisc.bcf.ILfmoddocfiscBCF#insertLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)
	 */ 
	@Override 
	public LfmoddocfiscResponse insertLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest) 
	{ 
		LfmoddocfiscResponse response = new LfmoddocfiscResponse();
		InternalResultsResponse<Lfmoddocfisc> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFMODDOCFISC_REQUEST_NAME, lfmoddocfiscRequest);
			context.putObjectToBeValidated(LFMODDOCFISC_NAME, lfmoddocfiscRequest.getLfmoddocfisc());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfmoddocfiscValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfmoddocfiscBCL().insertLfmoddocfisc(lfmoddocfiscRequest);
					response.setLfmoddocfisc(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODDOCFISC_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmoddocfisc.bcf.ILfmoddocfiscBCF#deleteLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)
	 */  
	@Override  
	public LfmoddocfiscResponse deleteLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest)
	{  
		LfmoddocfiscResponse response = new LfmoddocfiscResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFMODDOCFISC_REQUEST_NAME, lfmoddocfiscRequest);
			context.putObjectToBeValidated(LFMODDOCFISC_NAME, lfmoddocfiscRequest.getLfmoddocfisc());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfmoddocfiscValidationController().validate(context))
			{
				internalResponse = getLfmoddocfiscBCL().deleteLfmoddocfisc(lfmoddocfiscRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODDOCFISC_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmoddocfisc.bcf.ILfmoddocfiscBCF#updateLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest
	 */
	@Override 
	public LfmoddocfiscResponse updateLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest)
	{ 
		LfmoddocfiscResponse response = new LfmoddocfiscResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFMODDOCFISC_REQUEST_NAME, lfmoddocfiscRequest);
			context.putObjectToBeValidated(LFMODDOCFISC_NAME, lfmoddocfiscRequest.getLfmoddocfisc());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfmoddocfiscRequestValidationController().validate(context) && 
					getLfmoddocfiscValidationController().validate(context))
			{ 
				internalResponse = getLfmoddocfiscBCL().updateLfmoddocfisc(lfmoddocfiscRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODDOCFISC_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
