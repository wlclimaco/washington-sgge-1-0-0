package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFFRETEVENDA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFFRETEVENDA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFFRETEVENDA_LIST;

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
import com.sensus.mlc.lffretevenda.bcf.ILffretevendaBCF;
import com.sensus.mlc.lffretevenda.bcl.ILffretevendaBCL;
import com.sensus.mlc.lffretevenda.model.Lffretevenda;
import com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.response.InquiryLffretevendaResponse;
import com.sensus.mlc.lffretevenda.model.response.LffretevendaResponse;

/** 
 * The Class LffretevendaBCFImpl.
 *
 * @author - Washington
 */
public class LffretevendaBCFImpl extends AbstractBaseBCF implements ILffretevendaBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFFRETEVENDA_REQUEST_NAME. */ 
	private static final String INQUIRY_LFFRETEVENDA_REQUEST_NAME = InquiryLffretevendaRequest.class.getSimpleName();

	/** The Constant LFFRETEVENDA_REQUEST_NAME. */ 
	private static final String LFFRETEVENDA_REQUEST_NAME = LffretevendaRequest.class.getSimpleName();
 
	/** The Constant LFFRETEVENDA_NAME. */ 
	private static final String LFFRETEVENDA_NAME = Lffretevenda.class.getSimpleName();

	/** The Constant DEFAULT_LFFRETEVENDA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFFRETEVENDA_BCF_EXCEPTION_MSG = "sensus.mlc.lffretevendabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LffretevendaBCFImpl.class);
 
	/** The lffretevenda bcl. */ 
	private ILffretevendaBCL lffretevendaBCL;
 
	/** The lffretevenda validation controller. */ 
	private ValidationController lffretevendaValidationController;
  
	/** The lffretevenda list validation controller. */ 
	private ValidationController lffretevendaListValidationController;

	/** The lffretevenda request validation controller. */ 
	private ValidationController lffretevendaRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lffretevenda bcl.
	 *  
	 * @return the lffretevenda bcl  
	 */ 
	public ILffretevendaBCL getLffretevendaBCL()
	{ 
		return this.lffretevendaBCL;
	} 
  
	/**  
	 * Sets the lffretevenda bcl.  
	 *   
	 * @param lffretevendaBCLObject the new lffretevenda bcl  
	 */ 
	public void setLffretevendaBCL(ILffretevendaBCL lffretevendaBCLObject) 
	{
		this.lffretevendaBCL = lffretevendaBCLObject;
	} 
 
	/**  
	 * Gets the lffretevenda validation controller.
	 * 
	 * @return the lffretevenda validation controller  
	 */  
	public ValidationController getLffretevendaValidationController()  
	{
		return this.lffretevendaValidationController;
	}  
  
	/**   
	 * Sets the lffretevenda validation controller.
	 * 
	 * @param lffretevendaValidationController the new lffretevenda validation controller  
	 */    
	public void setLffretevendaValidationController(ValidationController lffretevendaValidationController)   
	{
		this.lffretevendaValidationController = lffretevendaValidationController;
	} 
  
	/** 
	 * Gets the lffretevenda list validation controller. 
	 *  
	 * @return the lffretevenda list validation controller  
	 */   
	public ValidationController getLffretevendaListValidationController()
	{  
		return this.lffretevendaListValidationController;
	}  
   
	/**   
	 * Sets the lffretevenda list validation controller.  
	 *  
	 * @param lffretevendaListValidationController the new lffretevenda list validation controller 
	 */ 
	public void setLffretevendaListValidationController(ValidationController lffretevendaListValidationController)
	{ 
		this.lffretevendaListValidationController = lffretevendaListValidationController;
	} 
     
	/**  
	 * Gets the lffretevenda request validation controller. 
	 *
	 * @return the lffretevenda request validation controller 
	 */
	public ValidationController getLffretevendaRequestValidationController() 
	{ 
		return this.lffretevendaRequestValidationController;
	} 
 
	/** 
	 * Sets the lffretevenda request validation controller. 
	 *  
	 * @param lffretevendaRequestValidationController the new lffretevenda request validation controller 
	 */ 
	public void setLffretevendaRequestValidationController(ValidationController lffretevendaRequestValidationController) 
	{
		this.lffretevendaRequestValidationController = lffretevendaRequestValidationController;
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
	 * @see com.sensus.mlc.lffretevenda.bcf.ILffretevendaBCF#fetchAllLffretevendas(com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest
	 */ 
	@Override 
	public InquiryLffretevendaResponse fetchAllLffretevenda(InquiryLffretevendaRequest inquiryLffretevendaRequest)
	{
		InquiryLffretevendaResponse response = new InquiryLffretevendaResponse();
		InternalResultsResponse<Lffretevenda> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFFRETEVENDA_REQUEST_NAME, inquiryLffretevendaRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLffretevendaBCL().fetchAllLffretevenda(inquiryLffretevendaRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETEVENDA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretevenda.bcf.ILffretevendaBCF#fetchLffretevendaByName(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest
	 */ 
	@Override 
	public LffretevendaResponse fetchLffretevendaById(LffretevendaRequest lffretevendaRequest)
	{  
		LffretevendaResponse response = new LffretevendaResponse();
		InternalResultsResponse<Lffretevenda> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFFRETEVENDA_REQUEST_NAME, lffretevendaRequest);
			context.putObjectToBeValidated(LFFRETEVENDA_NAME, lffretevendaRequest.getLffretevenda());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLffretevendaValidationController().validate(context)) 
			{   
				internalResponse = getLffretevendaBCL().fetchLffretevendaByName(lffretevendaRequest);
				response.setLffretevenda(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETEVENDA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffretevenda.bcf.ILffretevendaBCF#insertLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)
	 */ 
	@Override 
	public LffretevendaResponse insertLffretevenda(LffretevendaRequest lffretevendaRequest) 
	{ 
		LffretevendaResponse response = new LffretevendaResponse();
		InternalResultsResponse<Lffretevenda> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFFRETEVENDA_REQUEST_NAME, lffretevendaRequest);
			context.putObjectToBeValidated(LFFRETEVENDA_NAME, lffretevendaRequest.getLffretevenda());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLffretevendaValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLffretevendaBCL().insertLffretevenda(lffretevendaRequest);
					response.setLffretevenda(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETEVENDA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffretevenda.bcf.ILffretevendaBCF#deleteLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)
	 */  
	@Override  
	public LffretevendaResponse deleteLffretevenda(LffretevendaRequest lffretevendaRequest)
	{  
		LffretevendaResponse response = new LffretevendaResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFFRETEVENDA_REQUEST_NAME, lffretevendaRequest);
			context.putObjectToBeValidated(LFFRETEVENDA_NAME, lffretevendaRequest.getLffretevenda());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLffretevendaValidationController().validate(context))
			{
				internalResponse = getLffretevendaBCL().deleteLffretevenda(lffretevendaRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETEVENDA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretevenda.bcf.ILffretevendaBCF#updateLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest
	 */
	@Override 
	public LffretevendaResponse updateLffretevenda(LffretevendaRequest lffretevendaRequest)
	{ 
		LffretevendaResponse response = new LffretevendaResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFFRETEVENDA_REQUEST_NAME, lffretevendaRequest);
			context.putObjectToBeValidated(LFFRETEVENDA_NAME, lffretevendaRequest.getLffretevenda());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLffretevendaRequestValidationController().validate(context) && 
					getLffretevendaValidationController().validate(context))
			{ 
				internalResponse = getLffretevendaBCL().updateLffretevenda(lffretevendaRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETEVENDA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
