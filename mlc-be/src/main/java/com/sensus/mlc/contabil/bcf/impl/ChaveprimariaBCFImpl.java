package com.sensus.mlc.tabela.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_CHAVEPRIMARIA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_CHAVEPRIMARIA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.CHAVEPRIMARIA_LIST;

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
import com.sensus.mlc.chaveprimaria.bcf.IChaveprimariaBCF;
import com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL;
import com.sensus.mlc.chaveprimaria.model.Chaveprimaria;
import com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.response.InquiryChaveprimariaResponse;
import com.sensus.mlc.chaveprimaria.model.response.ChaveprimariaResponse;

/** 
 * The Class ChaveprimariaBCFImpl.
 *
 * @author - Washington
 */
public class ChaveprimariaBCFImpl extends AbstractBaseBCF implements IChaveprimariaBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_CHAVEPRIMARIA_REQUEST_NAME. */ 
	private static final String INQUIRY_CHAVEPRIMARIA_REQUEST_NAME = InquiryChaveprimariaRequest.class.getSimpleName();

	/** The Constant CHAVEPRIMARIA_REQUEST_NAME. */ 
	private static final String CHAVEPRIMARIA_REQUEST_NAME = ChaveprimariaRequest.class.getSimpleName();
 
	/** The Constant CHAVEPRIMARIA_NAME. */ 
	private static final String CHAVEPRIMARIA_NAME = Chaveprimaria.class.getSimpleName();

	/** The Constant DEFAULT_CHAVEPRIMARIA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_CHAVEPRIMARIA_BCF_EXCEPTION_MSG = "sensus.mlc.chaveprimariabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(ChaveprimariaBCFImpl.class);
 
	/** The chaveprimaria bcl. */ 
	private IChaveprimariaBCL chaveprimariaBCL;
 
	/** The chaveprimaria validation controller. */ 
	private ValidationController chaveprimariaValidationController;
  
	/** The chaveprimaria list validation controller. */ 
	private ValidationController chaveprimariaListValidationController;

	/** The chaveprimaria request validation controller. */ 
	private ValidationController chaveprimariaRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the chaveprimaria bcl.
	 *  
	 * @return the chaveprimaria bcl  
	 */ 
	public IChaveprimariaBCL getChaveprimariaBCL()
	{ 
		return this.chaveprimariaBCL;
	} 
  
	/**  
	 * Sets the chaveprimaria bcl.  
	 *   
	 * @param chaveprimariaBCLObject the new chaveprimaria bcl  
	 */ 
	public void setChaveprimariaBCL(IChaveprimariaBCL chaveprimariaBCLObject) 
	{
		this.chaveprimariaBCL = chaveprimariaBCLObject;
	} 
 
	/**  
	 * Gets the chaveprimaria validation controller.
	 * 
	 * @return the chaveprimaria validation controller  
	 */  
	public ValidationController getChaveprimariaValidationController()  
	{
		return this.chaveprimariaValidationController;
	}  
  
	/**   
	 * Sets the chaveprimaria validation controller.
	 * 
	 * @param chaveprimariaValidationController the new chaveprimaria validation controller  
	 */    
	public void setChaveprimariaValidationController(ValidationController chaveprimariaValidationController)   
	{
		this.chaveprimariaValidationController = chaveprimariaValidationController;
	} 
  
	/** 
	 * Gets the chaveprimaria list validation controller. 
	 *  
	 * @return the chaveprimaria list validation controller  
	 */   
	public ValidationController getChaveprimariaListValidationController()
	{  
		return this.chaveprimariaListValidationController;
	}  
   
	/**   
	 * Sets the chaveprimaria list validation controller.  
	 *  
	 * @param chaveprimariaListValidationController the new chaveprimaria list validation controller 
	 */ 
	public void setChaveprimariaListValidationController(ValidationController chaveprimariaListValidationController)
	{ 
		this.chaveprimariaListValidationController = chaveprimariaListValidationController;
	} 
     
	/**  
	 * Gets the chaveprimaria request validation controller. 
	 *
	 * @return the chaveprimaria request validation controller 
	 */
	public ValidationController getChaveprimariaRequestValidationController() 
	{ 
		return this.chaveprimariaRequestValidationController;
	} 
 
	/** 
	 * Sets the chaveprimaria request validation controller. 
	 *  
	 * @param chaveprimariaRequestValidationController the new chaveprimaria request validation controller 
	 */ 
	public void setChaveprimariaRequestValidationController(ValidationController chaveprimariaRequestValidationController) 
	{
		this.chaveprimariaRequestValidationController = chaveprimariaRequestValidationController;
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
	 * @see com.sensus.mlc.chaveprimaria.bcf.IChaveprimariaBCF#fetchAllChaveprimarias(com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest
	 */ 
	@Override 
	public InquiryChaveprimariaResponse fetchAllChaveprimaria(InquiryChaveprimariaRequest inquiryChaveprimariaRequest)
	{
		InquiryChaveprimariaResponse response = new InquiryChaveprimariaResponse();
		InternalResultsResponse<Chaveprimaria> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_CHAVEPRIMARIA_REQUEST_NAME, inquiryChaveprimariaRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getChaveprimariaBCL().fetchAllChaveprimaria(inquiryChaveprimariaRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEPRIMARIA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveprimaria.bcf.IChaveprimariaBCF#fetchChaveprimariaByName(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest
	 */ 
	@Override 
	public ChaveprimariaResponse fetchChaveprimariaById(ChaveprimariaRequest chaveprimariaRequest)
	{  
		ChaveprimariaResponse response = new ChaveprimariaResponse();
		InternalResultsResponse<Chaveprimaria> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(CHAVEPRIMARIA_REQUEST_NAME, chaveprimariaRequest);
			context.putObjectToBeValidated(CHAVEPRIMARIA_NAME, chaveprimariaRequest.getChaveprimaria());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getChaveprimariaValidationController().validate(context)) 
			{   
				internalResponse = getChaveprimariaBCL().fetchChaveprimariaByName(chaveprimariaRequest);
				response.setChaveprimaria(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEPRIMARIA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveprimaria.bcf.IChaveprimariaBCF#insertChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)
	 */ 
	@Override 
	public ChaveprimariaResponse insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest) 
	{ 
		ChaveprimariaResponse response = new ChaveprimariaResponse();
		InternalResultsResponse<Chaveprimaria> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(CHAVEPRIMARIA_REQUEST_NAME, chaveprimariaRequest);
			context.putObjectToBeValidated(CHAVEPRIMARIA_NAME, chaveprimariaRequest.getChaveprimaria());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getChaveprimariaValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getChaveprimariaBCL().insertChaveprimaria(chaveprimariaRequest);
					response.setChaveprimaria(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEPRIMARIA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveprimaria.bcf.IChaveprimariaBCF#deleteChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)
	 */  
	@Override  
	public ChaveprimariaResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{  
		ChaveprimariaResponse response = new ChaveprimariaResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(CHAVEPRIMARIA_REQUEST_NAME, chaveprimariaRequest);
			context.putObjectToBeValidated(CHAVEPRIMARIA_NAME, chaveprimariaRequest.getChaveprimaria());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getChaveprimariaValidationController().validate(context))
			{
				internalResponse = getChaveprimariaBCL().deleteChaveprimaria(chaveprimariaRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEPRIMARIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveprimaria.bcf.IChaveprimariaBCF#updateChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest
	 */
	@Override 
	public ChaveprimariaResponse updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{ 
		ChaveprimariaResponse response = new ChaveprimariaResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(CHAVEPRIMARIA_REQUEST_NAME, chaveprimariaRequest);
			context.putObjectToBeValidated(CHAVEPRIMARIA_NAME, chaveprimariaRequest.getChaveprimaria());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getChaveprimariaRequestValidationController().validate(context) && 
					getChaveprimariaValidationController().validate(context))
			{ 
				internalResponse = getChaveprimariaBCL().updateChaveprimaria(chaveprimariaRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEPRIMARIA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
