package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFITCOMPRA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFITCOMPRA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFITCOMPRA_LIST;

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
import com.sensus.mlc.lfitcompra.bcf.ILfitcompraBCF;
import com.sensus.mlc.lfitcompra.bcl.ILfitcompraBCL;
import com.sensus.mlc.lfitcompra.model.Lfitcompra;
import com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.response.InquiryLfitcompraResponse;
import com.sensus.mlc.lfitcompra.model.response.LfitcompraResponse;

/** 
 * The Class LfitcompraBCFImpl.
 *
 * @author - Washington
 */
public class LfitcompraBCFImpl extends AbstractBaseBCF implements ILfitcompraBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFITCOMPRA_REQUEST_NAME. */ 
	private static final String INQUIRY_LFITCOMPRA_REQUEST_NAME = InquiryLfitcompraRequest.class.getSimpleName();

	/** The Constant LFITCOMPRA_REQUEST_NAME. */ 
	private static final String LFITCOMPRA_REQUEST_NAME = LfitcompraRequest.class.getSimpleName();
 
	/** The Constant LFITCOMPRA_NAME. */ 
	private static final String LFITCOMPRA_NAME = Lfitcompra.class.getSimpleName();

	/** The Constant DEFAULT_LFITCOMPRA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFITCOMPRA_BCF_EXCEPTION_MSG = "sensus.mlc.lfitcomprabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfitcompraBCFImpl.class);
 
	/** The lfitcompra bcl. */ 
	private ILfitcompraBCL lfitcompraBCL;
 
	/** The lfitcompra validation controller. */ 
	private ValidationController lfitcompraValidationController;
  
	/** The lfitcompra list validation controller. */ 
	private ValidationController lfitcompraListValidationController;

	/** The lfitcompra request validation controller. */ 
	private ValidationController lfitcompraRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfitcompra bcl.
	 *  
	 * @return the lfitcompra bcl  
	 */ 
	public ILfitcompraBCL getLfitcompraBCL()
	{ 
		return this.lfitcompraBCL;
	} 
  
	/**  
	 * Sets the lfitcompra bcl.  
	 *   
	 * @param lfitcompraBCLObject the new lfitcompra bcl  
	 */ 
	public void setLfitcompraBCL(ILfitcompraBCL lfitcompraBCLObject) 
	{
		this.lfitcompraBCL = lfitcompraBCLObject;
	} 
 
	/**  
	 * Gets the lfitcompra validation controller.
	 * 
	 * @return the lfitcompra validation controller  
	 */  
	public ValidationController getLfitcompraValidationController()  
	{
		return this.lfitcompraValidationController;
	}  
  
	/**   
	 * Sets the lfitcompra validation controller.
	 * 
	 * @param lfitcompraValidationController the new lfitcompra validation controller  
	 */    
	public void setLfitcompraValidationController(ValidationController lfitcompraValidationController)   
	{
		this.lfitcompraValidationController = lfitcompraValidationController;
	} 
  
	/** 
	 * Gets the lfitcompra list validation controller. 
	 *  
	 * @return the lfitcompra list validation controller  
	 */   
	public ValidationController getLfitcompraListValidationController()
	{  
		return this.lfitcompraListValidationController;
	}  
   
	/**   
	 * Sets the lfitcompra list validation controller.  
	 *  
	 * @param lfitcompraListValidationController the new lfitcompra list validation controller 
	 */ 
	public void setLfitcompraListValidationController(ValidationController lfitcompraListValidationController)
	{ 
		this.lfitcompraListValidationController = lfitcompraListValidationController;
	} 
     
	/**  
	 * Gets the lfitcompra request validation controller. 
	 *
	 * @return the lfitcompra request validation controller 
	 */
	public ValidationController getLfitcompraRequestValidationController() 
	{ 
		return this.lfitcompraRequestValidationController;
	} 
 
	/** 
	 * Sets the lfitcompra request validation controller. 
	 *  
	 * @param lfitcompraRequestValidationController the new lfitcompra request validation controller 
	 */ 
	public void setLfitcompraRequestValidationController(ValidationController lfitcompraRequestValidationController) 
	{
		this.lfitcompraRequestValidationController = lfitcompraRequestValidationController;
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
	 * @see com.sensus.mlc.lfitcompra.bcf.ILfitcompraBCF#fetchAllLfitcompras(com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest
	 */ 
	@Override 
	public InquiryLfitcompraResponse fetchAllLfitcompra(InquiryLfitcompraRequest inquiryLfitcompraRequest)
	{
		InquiryLfitcompraResponse response = new InquiryLfitcompraResponse();
		InternalResultsResponse<Lfitcompra> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFITCOMPRA_REQUEST_NAME, inquiryLfitcompraRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfitcompraBCL().fetchAllLfitcompra(inquiryLfitcompraRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCOMPRA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcompra.bcf.ILfitcompraBCF#fetchLfitcompraByName(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest
	 */ 
	@Override 
	public LfitcompraResponse fetchLfitcompraById(LfitcompraRequest lfitcompraRequest)
	{  
		LfitcompraResponse response = new LfitcompraResponse();
		InternalResultsResponse<Lfitcompra> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFITCOMPRA_REQUEST_NAME, lfitcompraRequest);
			context.putObjectToBeValidated(LFITCOMPRA_NAME, lfitcompraRequest.getLfitcompra());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitcompraValidationController().validate(context)) 
			{   
				internalResponse = getLfitcompraBCL().fetchLfitcompraByName(lfitcompraRequest);
				response.setLfitcompra(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCOMPRA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitcompra.bcf.ILfitcompraBCF#insertLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)
	 */ 
	@Override 
	public LfitcompraResponse insertLfitcompra(LfitcompraRequest lfitcompraRequest) 
	{ 
		LfitcompraResponse response = new LfitcompraResponse();
		InternalResultsResponse<Lfitcompra> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFITCOMPRA_REQUEST_NAME, lfitcompraRequest);
			context.putObjectToBeValidated(LFITCOMPRA_NAME, lfitcompraRequest.getLfitcompra());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfitcompraValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfitcompraBCL().insertLfitcompra(lfitcompraRequest);
					response.setLfitcompra(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCOMPRA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitcompra.bcf.ILfitcompraBCF#deleteLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)
	 */  
	@Override  
	public LfitcompraResponse deleteLfitcompra(LfitcompraRequest lfitcompraRequest)
	{  
		LfitcompraResponse response = new LfitcompraResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFITCOMPRA_REQUEST_NAME, lfitcompraRequest);
			context.putObjectToBeValidated(LFITCOMPRA_NAME, lfitcompraRequest.getLfitcompra());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitcompraValidationController().validate(context))
			{
				internalResponse = getLfitcompraBCL().deleteLfitcompra(lfitcompraRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCOMPRA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcompra.bcf.ILfitcompraBCF#updateLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest
	 */
	@Override 
	public LfitcompraResponse updateLfitcompra(LfitcompraRequest lfitcompraRequest)
	{ 
		LfitcompraResponse response = new LfitcompraResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFITCOMPRA_REQUEST_NAME, lfitcompraRequest);
			context.putObjectToBeValidated(LFITCOMPRA_NAME, lfitcompraRequest.getLfitcompra());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfitcompraRequestValidationController().validate(context) && 
					getLfitcompraValidationController().validate(context))
			{ 
				internalResponse = getLfitcompraBCL().updateLfitcompra(lfitcompraRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCOMPRA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
