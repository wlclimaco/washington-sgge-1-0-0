package com.sensus.mlc.tabela.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_DOMINIOS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_DOMINIOS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.DOMINIOS_LIST;

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
import com.sensus.mlc.dominios.bcf.IDominiosBCF;
import com.sensus.mlc.dominios.bcl.IDominiosBCL;
import com.sensus.mlc.dominios.model.Dominios;
import com.sensus.mlc.dominios.model.request.InquiryDominiosRequest;
import com.sensus.mlc.dominios.model.request.DominiosRequest;
import com.sensus.mlc.dominios.model.response.InquiryDominiosResponse;
import com.sensus.mlc.dominios.model.response.DominiosResponse;

/** 
 * The Class DominiosBCFImpl.
 *
 * @author - Washington
 */
public class DominiosBCFImpl extends AbstractBaseBCF implements IDominiosBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_DOMINIOS_REQUEST_NAME. */ 
	private static final String INQUIRY_DOMINIOS_REQUEST_NAME = InquiryDominiosRequest.class.getSimpleName();

	/** The Constant DOMINIOS_REQUEST_NAME. */ 
	private static final String DOMINIOS_REQUEST_NAME = DominiosRequest.class.getSimpleName();
 
	/** The Constant DOMINIOS_NAME. */ 
	private static final String DOMINIOS_NAME = Dominios.class.getSimpleName();

	/** The Constant DEFAULT_DOMINIOS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DOMINIOS_BCF_EXCEPTION_MSG = "sensus.mlc.dominiosbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(DominiosBCFImpl.class);
 
	/** The dominios bcl. */ 
	private IDominiosBCL dominiosBCL;
 
	/** The dominios validation controller. */ 
	private ValidationController dominiosValidationController;
  
	/** The dominios list validation controller. */ 
	private ValidationController dominiosListValidationController;

	/** The dominios request validation controller. */ 
	private ValidationController dominiosRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the dominios bcl.
	 *  
	 * @return the dominios bcl  
	 */ 
	public IDominiosBCL getDominiosBCL()
	{ 
		return this.dominiosBCL;
	} 
  
	/**  
	 * Sets the dominios bcl.  
	 *   
	 * @param dominiosBCLObject the new dominios bcl  
	 */ 
	public void setDominiosBCL(IDominiosBCL dominiosBCLObject) 
	{
		this.dominiosBCL = dominiosBCLObject;
	} 
 
	/**  
	 * Gets the dominios validation controller.
	 * 
	 * @return the dominios validation controller  
	 */  
	public ValidationController getDominiosValidationController()  
	{
		return this.dominiosValidationController;
	}  
  
	/**   
	 * Sets the dominios validation controller.
	 * 
	 * @param dominiosValidationController the new dominios validation controller  
	 */    
	public void setDominiosValidationController(ValidationController dominiosValidationController)   
	{
		this.dominiosValidationController = dominiosValidationController;
	} 
  
	/** 
	 * Gets the dominios list validation controller. 
	 *  
	 * @return the dominios list validation controller  
	 */   
	public ValidationController getDominiosListValidationController()
	{  
		return this.dominiosListValidationController;
	}  
   
	/**   
	 * Sets the dominios list validation controller.  
	 *  
	 * @param dominiosListValidationController the new dominios list validation controller 
	 */ 
	public void setDominiosListValidationController(ValidationController dominiosListValidationController)
	{ 
		this.dominiosListValidationController = dominiosListValidationController;
	} 
     
	/**  
	 * Gets the dominios request validation controller. 
	 *
	 * @return the dominios request validation controller 
	 */
	public ValidationController getDominiosRequestValidationController() 
	{ 
		return this.dominiosRequestValidationController;
	} 
 
	/** 
	 * Sets the dominios request validation controller. 
	 *  
	 * @param dominiosRequestValidationController the new dominios request validation controller 
	 */ 
	public void setDominiosRequestValidationController(ValidationController dominiosRequestValidationController) 
	{
		this.dominiosRequestValidationController = dominiosRequestValidationController;
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
	 * @see com.sensus.mlc.dominios.bcf.IDominiosBCF#fetchAllDominioss(com.sensus.mlc.dominios.model.request.InquiryDominiosRequest
	 */ 
	@Override 
	public InquiryDominiosResponse fetchAllDominios(InquiryDominiosRequest inquiryDominiosRequest)
	{
		InquiryDominiosResponse response = new InquiryDominiosResponse();
		InternalResultsResponse<Dominios> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_DOMINIOS_REQUEST_NAME, inquiryDominiosRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getDominiosBCL().fetchAllDominios(inquiryDominiosRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DOMINIOS_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.bcf.IDominiosBCF#fetchDominiosByName(com.sensus.mlc.dominios.model.request.DominiosRequest
	 */ 
	@Override 
	public DominiosResponse fetchDominiosById(DominiosRequest dominiosRequest)
	{  
		DominiosResponse response = new DominiosResponse();
		InternalResultsResponse<Dominios> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(DOMINIOS_REQUEST_NAME, dominiosRequest);
			context.putObjectToBeValidated(DOMINIOS_NAME, dominiosRequest.getDominios());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getDominiosValidationController().validate(context)) 
			{   
				internalResponse = getDominiosBCL().fetchDominiosByName(dominiosRequest);
				response.setDominios(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DOMINIOS_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.dominios.bcf.IDominiosBCF#insertDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override 
	public DominiosResponse insertDominios(DominiosRequest dominiosRequest) 
	{ 
		DominiosResponse response = new DominiosResponse();
		InternalResultsResponse<Dominios> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(DOMINIOS_REQUEST_NAME, dominiosRequest);
			context.putObjectToBeValidated(DOMINIOS_NAME, dominiosRequest.getDominios());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getDominiosValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getDominiosBCL().insertDominios(dominiosRequest);
					response.setDominios(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DOMINIOS_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.dominios.bcf.IDominiosBCF#deleteDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */  
	@Override  
	public DominiosResponse deleteDominios(DominiosRequest dominiosRequest)
	{  
		DominiosResponse response = new DominiosResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(DOMINIOS_REQUEST_NAME, dominiosRequest);
			context.putObjectToBeValidated(DOMINIOS_NAME, dominiosRequest.getDominios());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getDominiosValidationController().validate(context))
			{
				internalResponse = getDominiosBCL().deleteDominios(dominiosRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DOMINIOS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.bcf.IDominiosBCF#updateDominios(com.sensus.mlc.dominios.model.request.DominiosRequest
	 */
	@Override 
	public DominiosResponse updateDominios(DominiosRequest dominiosRequest)
	{ 
		DominiosResponse response = new DominiosResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(DOMINIOS_REQUEST_NAME, dominiosRequest);
			context.putObjectToBeValidated(DOMINIOS_NAME, dominiosRequest.getDominios());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getDominiosRequestValidationController().validate(context) && 
					getDominiosValidationController().validate(context))
			{ 
				internalResponse = getDominiosBCL().updateDominios(dominiosRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DOMINIOS_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
