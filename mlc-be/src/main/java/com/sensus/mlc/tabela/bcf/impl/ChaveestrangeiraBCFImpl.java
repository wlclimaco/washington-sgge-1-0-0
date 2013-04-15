package com.sensus.mlc.tabela.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

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
import com.sensus.mlc.tabela.bcf.IChaveestrangeiraBCF;
import com.sensus.mlc.tabela.bcl.IChaveestrangeiraBCL;
import com.sensus.mlc.tabela.model.Chaveestrangeira;
import com.sensus.mlc.tabela.model.request.ChaveestrangeiraRequest;
import com.sensus.mlc.tabela.model.request.InquiryChaveestrangeiraRequest;
import com.sensus.mlc.tabela.model.response.ChaveestrangeiraResponse;
import com.sensus.mlc.tabela.model.response.InquiryChaveestrangeiraResponse;

/** 
 * The Class ChaveestrangeiraBCFImpl.
 *
 * @author - Washington
 */
public class ChaveestrangeiraBCFImpl extends AbstractBaseBCF implements IChaveestrangeiraBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_CHAVEESTRANGEIRA_REQUEST_NAME. */ 
	private static final String INQUIRY_CHAVEESTRANGEIRA_REQUEST_NAME = InquiryChaveestrangeiraRequest.class.getSimpleName();

	/** The Constant CHAVEESTRANGEIRA_REQUEST_NAME. */ 
	private static final String CHAVEESTRANGEIRA_REQUEST_NAME = ChaveestrangeiraRequest.class.getSimpleName();
 
	/** The Constant CHAVEESTRANGEIRA_NAME. */ 
	private static final String CHAVEESTRANGEIRA_NAME = Chaveestrangeira.class.getSimpleName();

	/** The Constant DEFAULT_CHAVEESTRANGEIRA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_CHAVEESTRANGEIRA_BCF_EXCEPTION_MSG = "sensus.mlc.chaveestrangeirabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(ChaveestrangeiraBCFImpl.class);
 
	/** The chaveestrangeira bcl. */ 
	private IChaveestrangeiraBCL chaveestrangeiraBCL;
 
	/** The chaveestrangeira validation controller. */ 
	private ValidationController chaveestrangeiraValidationController;
  
	/** The chaveestrangeira list validation controller. */ 
	private ValidationController chaveestrangeiraListValidationController;

	/** The chaveestrangeira request validation controller. */ 
	private ValidationController chaveestrangeiraRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the chaveestrangeira bcl.
	 *  
	 * @return the chaveestrangeira bcl  
	 */ 
	public IChaveestrangeiraBCL getChaveestrangeiraBCL()
	{ 
		return this.chaveestrangeiraBCL;
	} 
  
	/**  
	 * Sets the chaveestrangeira bcl.  
	 *   
	 * @param chaveestrangeiraBCLObject the new chaveestrangeira bcl  
	 */ 
	public void setChaveestrangeiraBCL(IChaveestrangeiraBCL chaveestrangeiraBCLObject) 
	{
		this.chaveestrangeiraBCL = chaveestrangeiraBCLObject;
	} 
 
	/**  
	 * Gets the chaveestrangeira validation controller.
	 * 
	 * @return the chaveestrangeira validation controller  
	 */  
	public ValidationController getChaveestrangeiraValidationController()  
	{
		return this.chaveestrangeiraValidationController;
	}  
  
	/**   
	 * Sets the chaveestrangeira validation controller.
	 * 
	 * @param chaveestrangeiraValidationController the new chaveestrangeira validation controller  
	 */    
	public void setChaveestrangeiraValidationController(ValidationController chaveestrangeiraValidationController)   
	{
		this.chaveestrangeiraValidationController = chaveestrangeiraValidationController;
	} 
  
	/** 
	 * Gets the chaveestrangeira list validation controller. 
	 *  
	 * @return the chaveestrangeira list validation controller  
	 */   
	public ValidationController getChaveestrangeiraListValidationController()
	{  
		return this.chaveestrangeiraListValidationController;
	}  
   
	/**   
	 * Sets the chaveestrangeira list validation controller.  
	 *  
	 * @param chaveestrangeiraListValidationController the new chaveestrangeira list validation controller 
	 */ 
	public void setChaveestrangeiraListValidationController(ValidationController chaveestrangeiraListValidationController)
	{ 
		this.chaveestrangeiraListValidationController = chaveestrangeiraListValidationController;
	} 
     
	/**  
	 * Gets the chaveestrangeira request validation controller. 
	 *
	 * @return the chaveestrangeira request validation controller 
	 */
	public ValidationController getChaveestrangeiraRequestValidationController() 
	{ 
		return this.chaveestrangeiraRequestValidationController;
	} 
 
	/** 
	 * Sets the chaveestrangeira request validation controller. 
	 *  
	 * @param chaveestrangeiraRequestValidationController the new chaveestrangeira request validation controller 
	 */ 
	public void setChaveestrangeiraRequestValidationController(ValidationController chaveestrangeiraRequestValidationController) 
	{
		this.chaveestrangeiraRequestValidationController = chaveestrangeiraRequestValidationController;
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
	 * @see 
com.sensus.mlc.chaveestrangeira.bcf.IChaveestrangeiraBCF#fetchAllChaveestrangeiras(com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest
	 */ 
	@Override 
	public InquiryChaveestrangeiraResponse fetchAllChaveestrangeira(InquiryChaveestrangeiraRequest inquiryChaveestrangeiraRequest)
	{
		InquiryChaveestrangeiraResponse response = new InquiryChaveestrangeiraResponse();
		InternalResultsResponse<Chaveestrangeira> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_CHAVEESTRANGEIRA_REQUEST_NAME, inquiryChaveestrangeiraRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getChaveestrangeiraBCL().fetchAllChaveestrangeira(inquiryChaveestrangeiraRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEESTRANGEIRA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveestrangeira.bcf.IChaveestrangeiraBCF#fetchChaveestrangeiraByName(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest
	 */ 
	@Override 
	public ChaveestrangeiraResponse fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{  
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse();
		InternalResultsResponse<Chaveestrangeira> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_REQUEST_NAME, chaveestrangeiraRequest);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_NAME, chaveestrangeiraRequest.getChaveestrangeira());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getChaveestrangeiraValidationController().validate(context)) 
			{   
				internalResponse = getChaveestrangeiraBCL().fetchChaveestrangeiraByName(chaveestrangeiraRequest);
				response.setChaveestrangeira(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEESTRANGEIRA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveestrangeira.bcf.IChaveestrangeiraBCF#insertChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override 
	public ChaveestrangeiraResponse insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{ 
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse();
		InternalResultsResponse<Chaveestrangeira> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_REQUEST_NAME, chaveestrangeiraRequest);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_NAME, chaveestrangeiraRequest.getChaveestrangeira());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getChaveestrangeiraValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getChaveestrangeiraBCL().insertChaveestrangeira(chaveestrangeiraRequest);
					response.setChaveestrangeira(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEESTRANGEIRA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveestrangeira.bcf.IChaveestrangeiraBCF#deleteChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */  
	@Override  
	public ChaveestrangeiraResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{  
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_REQUEST_NAME, chaveestrangeiraRequest);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_NAME, chaveestrangeiraRequest.getChaveestrangeira());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getChaveestrangeiraValidationController().validate(context))
			{
				internalResponse = getChaveestrangeiraBCL().deleteChaveestrangeira(chaveestrangeiraRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEESTRANGEIRA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveestrangeira.bcf.IChaveestrangeiraBCF#updateChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest
	 */
	@Override 
	public ChaveestrangeiraResponse updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{ 
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_REQUEST_NAME, chaveestrangeiraRequest);
			context.putObjectToBeValidated(CHAVEESTRANGEIRA_NAME, chaveestrangeiraRequest.getChaveestrangeira());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getChaveestrangeiraRequestValidationController().validate(context) && 
					getChaveestrangeiraValidationController().validate(context))
			{ 
				internalResponse = getChaveestrangeiraBCL().updateChaveestrangeira(chaveestrangeiraRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CHAVEESTRANGEIRA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
