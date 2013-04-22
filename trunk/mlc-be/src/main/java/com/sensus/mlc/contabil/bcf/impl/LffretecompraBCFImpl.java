package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFFRETECOMPRA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFFRETECOMPRA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFFRETECOMPRA_LIST;

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
import com.sensus.mlc.lffretecompra.bcf.ILffretecompraBCF;
import com.sensus.mlc.lffretecompra.bcl.ILffretecompraBCL;
import com.sensus.mlc.lffretecompra.model.Lffretecompra;
import com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.response.InquiryLffretecompraResponse;
import com.sensus.mlc.lffretecompra.model.response.LffretecompraResponse;

/** 
 * The Class LffretecompraBCFImpl.
 *
 * @author - Washington
 */
public class LffretecompraBCFImpl extends AbstractBaseBCF implements ILffretecompraBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFFRETECOMPRA_REQUEST_NAME. */ 
	private static final String INQUIRY_LFFRETECOMPRA_REQUEST_NAME = InquiryLffretecompraRequest.class.getSimpleName();

	/** The Constant LFFRETECOMPRA_REQUEST_NAME. */ 
	private static final String LFFRETECOMPRA_REQUEST_NAME = LffretecompraRequest.class.getSimpleName();
 
	/** The Constant LFFRETECOMPRA_NAME. */ 
	private static final String LFFRETECOMPRA_NAME = Lffretecompra.class.getSimpleName();

	/** The Constant DEFAULT_LFFRETECOMPRA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFFRETECOMPRA_BCF_EXCEPTION_MSG = "sensus.mlc.lffretecomprabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LffretecompraBCFImpl.class);
 
	/** The lffretecompra bcl. */ 
	private ILffretecompraBCL lffretecompraBCL;
 
	/** The lffretecompra validation controller. */ 
	private ValidationController lffretecompraValidationController;
  
	/** The lffretecompra list validation controller. */ 
	private ValidationController lffretecompraListValidationController;

	/** The lffretecompra request validation controller. */ 
	private ValidationController lffretecompraRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lffretecompra bcl.
	 *  
	 * @return the lffretecompra bcl  
	 */ 
	public ILffretecompraBCL getLffretecompraBCL()
	{ 
		return this.lffretecompraBCL;
	} 
  
	/**  
	 * Sets the lffretecompra bcl.  
	 *   
	 * @param lffretecompraBCLObject the new lffretecompra bcl  
	 */ 
	public void setLffretecompraBCL(ILffretecompraBCL lffretecompraBCLObject) 
	{
		this.lffretecompraBCL = lffretecompraBCLObject;
	} 
 
	/**  
	 * Gets the lffretecompra validation controller.
	 * 
	 * @return the lffretecompra validation controller  
	 */  
	public ValidationController getLffretecompraValidationController()  
	{
		return this.lffretecompraValidationController;
	}  
  
	/**   
	 * Sets the lffretecompra validation controller.
	 * 
	 * @param lffretecompraValidationController the new lffretecompra validation controller  
	 */    
	public void setLffretecompraValidationController(ValidationController lffretecompraValidationController)   
	{
		this.lffretecompraValidationController = lffretecompraValidationController;
	} 
  
	/** 
	 * Gets the lffretecompra list validation controller. 
	 *  
	 * @return the lffretecompra list validation controller  
	 */   
	public ValidationController getLffretecompraListValidationController()
	{  
		return this.lffretecompraListValidationController;
	}  
   
	/**   
	 * Sets the lffretecompra list validation controller.  
	 *  
	 * @param lffretecompraListValidationController the new lffretecompra list validation controller 
	 */ 
	public void setLffretecompraListValidationController(ValidationController lffretecompraListValidationController)
	{ 
		this.lffretecompraListValidationController = lffretecompraListValidationController;
	} 
     
	/**  
	 * Gets the lffretecompra request validation controller. 
	 *
	 * @return the lffretecompra request validation controller 
	 */
	public ValidationController getLffretecompraRequestValidationController() 
	{ 
		return this.lffretecompraRequestValidationController;
	} 
 
	/** 
	 * Sets the lffretecompra request validation controller. 
	 *  
	 * @param lffretecompraRequestValidationController the new lffretecompra request validation controller 
	 */ 
	public void setLffretecompraRequestValidationController(ValidationController lffretecompraRequestValidationController) 
	{
		this.lffretecompraRequestValidationController = lffretecompraRequestValidationController;
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
	 * @see com.sensus.mlc.lffretecompra.bcf.ILffretecompraBCF#fetchAllLffretecompras(com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest
	 */ 
	@Override 
	public InquiryLffretecompraResponse fetchAllLffretecompra(InquiryLffretecompraRequest inquiryLffretecompraRequest)
	{
		InquiryLffretecompraResponse response = new InquiryLffretecompraResponse();
		InternalResultsResponse<Lffretecompra> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFFRETECOMPRA_REQUEST_NAME, inquiryLffretecompraRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLffretecompraBCL().fetchAllLffretecompra(inquiryLffretecompraRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETECOMPRA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretecompra.bcf.ILffretecompraBCF#fetchLffretecompraByName(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest
	 */ 
	@Override 
	public LffretecompraResponse fetchLffretecompraById(LffretecompraRequest lffretecompraRequest)
	{  
		LffretecompraResponse response = new LffretecompraResponse();
		InternalResultsResponse<Lffretecompra> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFFRETECOMPRA_REQUEST_NAME, lffretecompraRequest);
			context.putObjectToBeValidated(LFFRETECOMPRA_NAME, lffretecompraRequest.getLffretecompra());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLffretecompraValidationController().validate(context)) 
			{   
				internalResponse = getLffretecompraBCL().fetchLffretecompraByName(lffretecompraRequest);
				response.setLffretecompra(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETECOMPRA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffretecompra.bcf.ILffretecompraBCF#insertLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)
	 */ 
	@Override 
	public LffretecompraResponse insertLffretecompra(LffretecompraRequest lffretecompraRequest) 
	{ 
		LffretecompraResponse response = new LffretecompraResponse();
		InternalResultsResponse<Lffretecompra> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFFRETECOMPRA_REQUEST_NAME, lffretecompraRequest);
			context.putObjectToBeValidated(LFFRETECOMPRA_NAME, lffretecompraRequest.getLffretecompra());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLffretecompraValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLffretecompraBCL().insertLffretecompra(lffretecompraRequest);
					response.setLffretecompra(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETECOMPRA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffretecompra.bcf.ILffretecompraBCF#deleteLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)
	 */  
	@Override  
	public LffretecompraResponse deleteLffretecompra(LffretecompraRequest lffretecompraRequest)
	{  
		LffretecompraResponse response = new LffretecompraResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFFRETECOMPRA_REQUEST_NAME, lffretecompraRequest);
			context.putObjectToBeValidated(LFFRETECOMPRA_NAME, lffretecompraRequest.getLffretecompra());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLffretecompraValidationController().validate(context))
			{
				internalResponse = getLffretecompraBCL().deleteLffretecompra(lffretecompraRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETECOMPRA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretecompra.bcf.ILffretecompraBCF#updateLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest
	 */
	@Override 
	public LffretecompraResponse updateLffretecompra(LffretecompraRequest lffretecompraRequest)
	{ 
		LffretecompraResponse response = new LffretecompraResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFFRETECOMPRA_REQUEST_NAME, lffretecompraRequest);
			context.putObjectToBeValidated(LFFRETECOMPRA_NAME, lffretecompraRequest.getLffretecompra());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLffretecompraRequestValidationController().validate(context) && 
					getLffretecompraValidationController().validate(context))
			{ 
				internalResponse = getLffretecompraBCL().updateLffretecompra(lffretecompraRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFFRETECOMPRA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
