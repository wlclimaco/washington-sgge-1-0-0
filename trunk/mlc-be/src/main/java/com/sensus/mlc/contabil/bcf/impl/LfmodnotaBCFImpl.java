package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFMODNOTA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFMODNOTA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFMODNOTA_LIST;

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
import com.sensus.mlc.lfmodnota.bcf.ILfmodnotaBCF;
import com.sensus.mlc.lfmodnota.bcl.ILfmodnotaBCL;
import com.sensus.mlc.lfmodnota.model.Lfmodnota;
import com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.response.InquiryLfmodnotaResponse;
import com.sensus.mlc.lfmodnota.model.response.LfmodnotaResponse;

/** 
 * The Class LfmodnotaBCFImpl.
 *
 * @author - Washington
 */
public class LfmodnotaBCFImpl extends AbstractBaseBCF implements ILfmodnotaBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFMODNOTA_REQUEST_NAME. */ 
	private static final String INQUIRY_LFMODNOTA_REQUEST_NAME = InquiryLfmodnotaRequest.class.getSimpleName();

	/** The Constant LFMODNOTA_REQUEST_NAME. */ 
	private static final String LFMODNOTA_REQUEST_NAME = LfmodnotaRequest.class.getSimpleName();
 
	/** The Constant LFMODNOTA_NAME. */ 
	private static final String LFMODNOTA_NAME = Lfmodnota.class.getSimpleName();

	/** The Constant DEFAULT_LFMODNOTA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFMODNOTA_BCF_EXCEPTION_MSG = "sensus.mlc.lfmodnotabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfmodnotaBCFImpl.class);
 
	/** The lfmodnota bcl. */ 
	private ILfmodnotaBCL lfmodnotaBCL;
 
	/** The lfmodnota validation controller. */ 
	private ValidationController lfmodnotaValidationController;
  
	/** The lfmodnota list validation controller. */ 
	private ValidationController lfmodnotaListValidationController;

	/** The lfmodnota request validation controller. */ 
	private ValidationController lfmodnotaRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfmodnota bcl.
	 *  
	 * @return the lfmodnota bcl  
	 */ 
	public ILfmodnotaBCL getLfmodnotaBCL()
	{ 
		return this.lfmodnotaBCL;
	} 
  
	/**  
	 * Sets the lfmodnota bcl.  
	 *   
	 * @param lfmodnotaBCLObject the new lfmodnota bcl  
	 */ 
	public void setLfmodnotaBCL(ILfmodnotaBCL lfmodnotaBCLObject) 
	{
		this.lfmodnotaBCL = lfmodnotaBCLObject;
	} 
 
	/**  
	 * Gets the lfmodnota validation controller.
	 * 
	 * @return the lfmodnota validation controller  
	 */  
	public ValidationController getLfmodnotaValidationController()  
	{
		return this.lfmodnotaValidationController;
	}  
  
	/**   
	 * Sets the lfmodnota validation controller.
	 * 
	 * @param lfmodnotaValidationController the new lfmodnota validation controller  
	 */    
	public void setLfmodnotaValidationController(ValidationController lfmodnotaValidationController)   
	{
		this.lfmodnotaValidationController = lfmodnotaValidationController;
	} 
  
	/** 
	 * Gets the lfmodnota list validation controller. 
	 *  
	 * @return the lfmodnota list validation controller  
	 */   
	public ValidationController getLfmodnotaListValidationController()
	{  
		return this.lfmodnotaListValidationController;
	}  
   
	/**   
	 * Sets the lfmodnota list validation controller.  
	 *  
	 * @param lfmodnotaListValidationController the new lfmodnota list validation controller 
	 */ 
	public void setLfmodnotaListValidationController(ValidationController lfmodnotaListValidationController)
	{ 
		this.lfmodnotaListValidationController = lfmodnotaListValidationController;
	} 
     
	/**  
	 * Gets the lfmodnota request validation controller. 
	 *
	 * @return the lfmodnota request validation controller 
	 */
	public ValidationController getLfmodnotaRequestValidationController() 
	{ 
		return this.lfmodnotaRequestValidationController;
	} 
 
	/** 
	 * Sets the lfmodnota request validation controller. 
	 *  
	 * @param lfmodnotaRequestValidationController the new lfmodnota request validation controller 
	 */ 
	public void setLfmodnotaRequestValidationController(ValidationController lfmodnotaRequestValidationController) 
	{
		this.lfmodnotaRequestValidationController = lfmodnotaRequestValidationController;
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
	 * @see com.sensus.mlc.lfmodnota.bcf.ILfmodnotaBCF#fetchAllLfmodnotas(com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest
	 */ 
	@Override 
	public InquiryLfmodnotaResponse fetchAllLfmodnota(InquiryLfmodnotaRequest inquiryLfmodnotaRequest)
	{
		InquiryLfmodnotaResponse response = new InquiryLfmodnotaResponse();
		InternalResultsResponse<Lfmodnota> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFMODNOTA_REQUEST_NAME, inquiryLfmodnotaRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfmodnotaBCL().fetchAllLfmodnota(inquiryLfmodnotaRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODNOTA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmodnota.bcf.ILfmodnotaBCF#fetchLfmodnotaByName(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest
	 */ 
	@Override 
	public LfmodnotaResponse fetchLfmodnotaById(LfmodnotaRequest lfmodnotaRequest)
	{  
		LfmodnotaResponse response = new LfmodnotaResponse();
		InternalResultsResponse<Lfmodnota> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFMODNOTA_REQUEST_NAME, lfmodnotaRequest);
			context.putObjectToBeValidated(LFMODNOTA_NAME, lfmodnotaRequest.getLfmodnota());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfmodnotaValidationController().validate(context)) 
			{   
				internalResponse = getLfmodnotaBCL().fetchLfmodnotaByName(lfmodnotaRequest);
				response.setLfmodnota(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODNOTA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmodnota.bcf.ILfmodnotaBCF#insertLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)
	 */ 
	@Override 
	public LfmodnotaResponse insertLfmodnota(LfmodnotaRequest lfmodnotaRequest) 
	{ 
		LfmodnotaResponse response = new LfmodnotaResponse();
		InternalResultsResponse<Lfmodnota> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFMODNOTA_REQUEST_NAME, lfmodnotaRequest);
			context.putObjectToBeValidated(LFMODNOTA_NAME, lfmodnotaRequest.getLfmodnota());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfmodnotaValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfmodnotaBCL().insertLfmodnota(lfmodnotaRequest);
					response.setLfmodnota(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODNOTA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmodnota.bcf.ILfmodnotaBCF#deleteLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)
	 */  
	@Override  
	public LfmodnotaResponse deleteLfmodnota(LfmodnotaRequest lfmodnotaRequest)
	{  
		LfmodnotaResponse response = new LfmodnotaResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFMODNOTA_REQUEST_NAME, lfmodnotaRequest);
			context.putObjectToBeValidated(LFMODNOTA_NAME, lfmodnotaRequest.getLfmodnota());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfmodnotaValidationController().validate(context))
			{
				internalResponse = getLfmodnotaBCL().deleteLfmodnota(lfmodnotaRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODNOTA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmodnota.bcf.ILfmodnotaBCF#updateLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest
	 */
	@Override 
	public LfmodnotaResponse updateLfmodnota(LfmodnotaRequest lfmodnotaRequest)
	{ 
		LfmodnotaResponse response = new LfmodnotaResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFMODNOTA_REQUEST_NAME, lfmodnotaRequest);
			context.putObjectToBeValidated(LFMODNOTA_NAME, lfmodnotaRequest.getLfmodnota());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfmodnotaRequestValidationController().validate(context) && 
					getLfmodnotaValidationController().validate(context))
			{ 
				internalResponse = getLfmodnotaBCL().updateLfmodnota(lfmodnotaRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMODNOTA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
