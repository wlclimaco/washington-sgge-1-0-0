package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFITVENDA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFITVENDA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFITVENDA_LIST;

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
import com.sensus.mlc.lfitvenda.bcf.ILfitvendaBCF;
import com.sensus.mlc.lfitvenda.bcl.ILfitvendaBCL;
import com.sensus.mlc.lfitvenda.model.Lfitvenda;
import com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.response.InquiryLfitvendaResponse;
import com.sensus.mlc.lfitvenda.model.response.LfitvendaResponse;

/** 
 * The Class LfitvendaBCFImpl.
 *
 * @author - Washington
 */
public class LfitvendaBCFImpl extends AbstractBaseBCF implements ILfitvendaBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFITVENDA_REQUEST_NAME. */ 
	private static final String INQUIRY_LFITVENDA_REQUEST_NAME = InquiryLfitvendaRequest.class.getSimpleName();

	/** The Constant LFITVENDA_REQUEST_NAME. */ 
	private static final String LFITVENDA_REQUEST_NAME = LfitvendaRequest.class.getSimpleName();
 
	/** The Constant LFITVENDA_NAME. */ 
	private static final String LFITVENDA_NAME = Lfitvenda.class.getSimpleName();

	/** The Constant DEFAULT_LFITVENDA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFITVENDA_BCF_EXCEPTION_MSG = "sensus.mlc.lfitvendabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfitvendaBCFImpl.class);
 
	/** The lfitvenda bcl. */ 
	private ILfitvendaBCL lfitvendaBCL;
 
	/** The lfitvenda validation controller. */ 
	private ValidationController lfitvendaValidationController;
  
	/** The lfitvenda list validation controller. */ 
	private ValidationController lfitvendaListValidationController;

	/** The lfitvenda request validation controller. */ 
	private ValidationController lfitvendaRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfitvenda bcl.
	 *  
	 * @return the lfitvenda bcl  
	 */ 
	public ILfitvendaBCL getLfitvendaBCL()
	{ 
		return this.lfitvendaBCL;
	} 
  
	/**  
	 * Sets the lfitvenda bcl.  
	 *   
	 * @param lfitvendaBCLObject the new lfitvenda bcl  
	 */ 
	public void setLfitvendaBCL(ILfitvendaBCL lfitvendaBCLObject) 
	{
		this.lfitvendaBCL = lfitvendaBCLObject;
	} 
 
	/**  
	 * Gets the lfitvenda validation controller.
	 * 
	 * @return the lfitvenda validation controller  
	 */  
	public ValidationController getLfitvendaValidationController()  
	{
		return this.lfitvendaValidationController;
	}  
  
	/**   
	 * Sets the lfitvenda validation controller.
	 * 
	 * @param lfitvendaValidationController the new lfitvenda validation controller  
	 */    
	public void setLfitvendaValidationController(ValidationController lfitvendaValidationController)   
	{
		this.lfitvendaValidationController = lfitvendaValidationController;
	} 
  
	/** 
	 * Gets the lfitvenda list validation controller. 
	 *  
	 * @return the lfitvenda list validation controller  
	 */   
	public ValidationController getLfitvendaListValidationController()
	{  
		return this.lfitvendaListValidationController;
	}  
   
	/**   
	 * Sets the lfitvenda list validation controller.  
	 *  
	 * @param lfitvendaListValidationController the new lfitvenda list validation controller 
	 */ 
	public void setLfitvendaListValidationController(ValidationController lfitvendaListValidationController)
	{ 
		this.lfitvendaListValidationController = lfitvendaListValidationController;
	} 
     
	/**  
	 * Gets the lfitvenda request validation controller. 
	 *
	 * @return the lfitvenda request validation controller 
	 */
	public ValidationController getLfitvendaRequestValidationController() 
	{ 
		return this.lfitvendaRequestValidationController;
	} 
 
	/** 
	 * Sets the lfitvenda request validation controller. 
	 *  
	 * @param lfitvendaRequestValidationController the new lfitvenda request validation controller 
	 */ 
	public void setLfitvendaRequestValidationController(ValidationController lfitvendaRequestValidationController) 
	{
		this.lfitvendaRequestValidationController = lfitvendaRequestValidationController;
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
	 * @see com.sensus.mlc.lfitvenda.bcf.ILfitvendaBCF#fetchAllLfitvendas(com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest
	 */ 
	@Override 
	public InquiryLfitvendaResponse fetchAllLfitvenda(InquiryLfitvendaRequest inquiryLfitvendaRequest)
	{
		InquiryLfitvendaResponse response = new InquiryLfitvendaResponse();
		InternalResultsResponse<Lfitvenda> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFITVENDA_REQUEST_NAME, inquiryLfitvendaRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfitvendaBCL().fetchAllLfitvenda(inquiryLfitvendaRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITVENDA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitvenda.bcf.ILfitvendaBCF#fetchLfitvendaByName(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest
	 */ 
	@Override 
	public LfitvendaResponse fetchLfitvendaById(LfitvendaRequest lfitvendaRequest)
	{  
		LfitvendaResponse response = new LfitvendaResponse();
		InternalResultsResponse<Lfitvenda> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFITVENDA_REQUEST_NAME, lfitvendaRequest);
			context.putObjectToBeValidated(LFITVENDA_NAME, lfitvendaRequest.getLfitvenda());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitvendaValidationController().validate(context)) 
			{   
				internalResponse = getLfitvendaBCL().fetchLfitvendaByName(lfitvendaRequest);
				response.setLfitvenda(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITVENDA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitvenda.bcf.ILfitvendaBCF#insertLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)
	 */ 
	@Override 
	public LfitvendaResponse insertLfitvenda(LfitvendaRequest lfitvendaRequest) 
	{ 
		LfitvendaResponse response = new LfitvendaResponse();
		InternalResultsResponse<Lfitvenda> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFITVENDA_REQUEST_NAME, lfitvendaRequest);
			context.putObjectToBeValidated(LFITVENDA_NAME, lfitvendaRequest.getLfitvenda());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfitvendaValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfitvendaBCL().insertLfitvenda(lfitvendaRequest);
					response.setLfitvenda(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITVENDA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitvenda.bcf.ILfitvendaBCF#deleteLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)
	 */  
	@Override  
	public LfitvendaResponse deleteLfitvenda(LfitvendaRequest lfitvendaRequest)
	{  
		LfitvendaResponse response = new LfitvendaResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFITVENDA_REQUEST_NAME, lfitvendaRequest);
			context.putObjectToBeValidated(LFITVENDA_NAME, lfitvendaRequest.getLfitvenda());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitvendaValidationController().validate(context))
			{
				internalResponse = getLfitvendaBCL().deleteLfitvenda(lfitvendaRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITVENDA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitvenda.bcf.ILfitvendaBCF#updateLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest
	 */
	@Override 
	public LfitvendaResponse updateLfitvenda(LfitvendaRequest lfitvendaRequest)
	{ 
		LfitvendaResponse response = new LfitvendaResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFITVENDA_REQUEST_NAME, lfitvendaRequest);
			context.putObjectToBeValidated(LFITVENDA_NAME, lfitvendaRequest.getLfitvenda());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfitvendaRequestValidationController().validate(context) && 
					getLfitvendaValidationController().validate(context))
			{ 
				internalResponse = getLfitvendaBCL().updateLfitvenda(lfitvendaRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITVENDA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
