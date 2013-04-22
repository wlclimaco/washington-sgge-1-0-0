package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFMENSAGEM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFMENSAGEM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFMENSAGEM_LIST;

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
import com.sensus.mlc.lfmensagem.bcf.ILfmensagemBCF;
import com.sensus.mlc.lfmensagem.bcl.ILfmensagemBCL;
import com.sensus.mlc.lfmensagem.model.Lfmensagem;
import com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.response.InquiryLfmensagemResponse;
import com.sensus.mlc.lfmensagem.model.response.LfmensagemResponse;

/** 
 * The Class LfmensagemBCFImpl.
 *
 * @author - Washington
 */
public class LfmensagemBCFImpl extends AbstractBaseBCF implements ILfmensagemBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFMENSAGEM_REQUEST_NAME. */ 
	private static final String INQUIRY_LFMENSAGEM_REQUEST_NAME = InquiryLfmensagemRequest.class.getSimpleName();

	/** The Constant LFMENSAGEM_REQUEST_NAME. */ 
	private static final String LFMENSAGEM_REQUEST_NAME = LfmensagemRequest.class.getSimpleName();
 
	/** The Constant LFMENSAGEM_NAME. */ 
	private static final String LFMENSAGEM_NAME = Lfmensagem.class.getSimpleName();

	/** The Constant DEFAULT_LFMENSAGEM_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFMENSAGEM_BCF_EXCEPTION_MSG = "sensus.mlc.lfmensagembcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfmensagemBCFImpl.class);
 
	/** The lfmensagem bcl. */ 
	private ILfmensagemBCL lfmensagemBCL;
 
	/** The lfmensagem validation controller. */ 
	private ValidationController lfmensagemValidationController;
  
	/** The lfmensagem list validation controller. */ 
	private ValidationController lfmensagemListValidationController;

	/** The lfmensagem request validation controller. */ 
	private ValidationController lfmensagemRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfmensagem bcl.
	 *  
	 * @return the lfmensagem bcl  
	 */ 
	public ILfmensagemBCL getLfmensagemBCL()
	{ 
		return this.lfmensagemBCL;
	} 
  
	/**  
	 * Sets the lfmensagem bcl.  
	 *   
	 * @param lfmensagemBCLObject the new lfmensagem bcl  
	 */ 
	public void setLfmensagemBCL(ILfmensagemBCL lfmensagemBCLObject) 
	{
		this.lfmensagemBCL = lfmensagemBCLObject;
	} 
 
	/**  
	 * Gets the lfmensagem validation controller.
	 * 
	 * @return the lfmensagem validation controller  
	 */  
	public ValidationController getLfmensagemValidationController()  
	{
		return this.lfmensagemValidationController;
	}  
  
	/**   
	 * Sets the lfmensagem validation controller.
	 * 
	 * @param lfmensagemValidationController the new lfmensagem validation controller  
	 */    
	public void setLfmensagemValidationController(ValidationController lfmensagemValidationController)   
	{
		this.lfmensagemValidationController = lfmensagemValidationController;
	} 
  
	/** 
	 * Gets the lfmensagem list validation controller. 
	 *  
	 * @return the lfmensagem list validation controller  
	 */   
	public ValidationController getLfmensagemListValidationController()
	{  
		return this.lfmensagemListValidationController;
	}  
   
	/**   
	 * Sets the lfmensagem list validation controller.  
	 *  
	 * @param lfmensagemListValidationController the new lfmensagem list validation controller 
	 */ 
	public void setLfmensagemListValidationController(ValidationController lfmensagemListValidationController)
	{ 
		this.lfmensagemListValidationController = lfmensagemListValidationController;
	} 
     
	/**  
	 * Gets the lfmensagem request validation controller. 
	 *
	 * @return the lfmensagem request validation controller 
	 */
	public ValidationController getLfmensagemRequestValidationController() 
	{ 
		return this.lfmensagemRequestValidationController;
	} 
 
	/** 
	 * Sets the lfmensagem request validation controller. 
	 *  
	 * @param lfmensagemRequestValidationController the new lfmensagem request validation controller 
	 */ 
	public void setLfmensagemRequestValidationController(ValidationController lfmensagemRequestValidationController) 
	{
		this.lfmensagemRequestValidationController = lfmensagemRequestValidationController;
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
	 * @see com.sensus.mlc.lfmensagem.bcf.ILfmensagemBCF#fetchAllLfmensagems(com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest
	 */ 
	@Override 
	public InquiryLfmensagemResponse fetchAllLfmensagem(InquiryLfmensagemRequest inquiryLfmensagemRequest)
	{
		InquiryLfmensagemResponse response = new InquiryLfmensagemResponse();
		InternalResultsResponse<Lfmensagem> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFMENSAGEM_REQUEST_NAME, inquiryLfmensagemRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfmensagemBCL().fetchAllLfmensagem(inquiryLfmensagemRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMENSAGEM_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmensagem.bcf.ILfmensagemBCF#fetchLfmensagemByName(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest
	 */ 
	@Override 
	public LfmensagemResponse fetchLfmensagemById(LfmensagemRequest lfmensagemRequest)
	{  
		LfmensagemResponse response = new LfmensagemResponse();
		InternalResultsResponse<Lfmensagem> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFMENSAGEM_REQUEST_NAME, lfmensagemRequest);
			context.putObjectToBeValidated(LFMENSAGEM_NAME, lfmensagemRequest.getLfmensagem());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfmensagemValidationController().validate(context)) 
			{   
				internalResponse = getLfmensagemBCL().fetchLfmensagemByName(lfmensagemRequest);
				response.setLfmensagem(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMENSAGEM_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmensagem.bcf.ILfmensagemBCF#insertLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)
	 */ 
	@Override 
	public LfmensagemResponse insertLfmensagem(LfmensagemRequest lfmensagemRequest) 
	{ 
		LfmensagemResponse response = new LfmensagemResponse();
		InternalResultsResponse<Lfmensagem> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFMENSAGEM_REQUEST_NAME, lfmensagemRequest);
			context.putObjectToBeValidated(LFMENSAGEM_NAME, lfmensagemRequest.getLfmensagem());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfmensagemValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfmensagemBCL().insertLfmensagem(lfmensagemRequest);
					response.setLfmensagem(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMENSAGEM_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmensagem.bcf.ILfmensagemBCF#deleteLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)
	 */  
	@Override  
	public LfmensagemResponse deleteLfmensagem(LfmensagemRequest lfmensagemRequest)
	{  
		LfmensagemResponse response = new LfmensagemResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFMENSAGEM_REQUEST_NAME, lfmensagemRequest);
			context.putObjectToBeValidated(LFMENSAGEM_NAME, lfmensagemRequest.getLfmensagem());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfmensagemValidationController().validate(context))
			{
				internalResponse = getLfmensagemBCL().deleteLfmensagem(lfmensagemRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMENSAGEM_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmensagem.bcf.ILfmensagemBCF#updateLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest
	 */
	@Override 
	public LfmensagemResponse updateLfmensagem(LfmensagemRequest lfmensagemRequest)
	{ 
		LfmensagemResponse response = new LfmensagemResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFMENSAGEM_REQUEST_NAME, lfmensagemRequest);
			context.putObjectToBeValidated(LFMENSAGEM_NAME, lfmensagemRequest.getLfmensagem());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfmensagemRequestValidationController().validate(context) && 
					getLfmensagemValidationController().validate(context))
			{ 
				internalResponse = getLfmensagemBCL().updateLfmensagem(lfmensagemRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFMENSAGEM_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
