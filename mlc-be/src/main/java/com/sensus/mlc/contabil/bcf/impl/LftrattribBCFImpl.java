package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFTRATTRIB;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFTRATTRIB;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFTRATTRIB_LIST;

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
import com.sensus.mlc.lftrattrib.bcf.ILftrattribBCF;
import com.sensus.mlc.lftrattrib.bcl.ILftrattribBCL;
import com.sensus.mlc.lftrattrib.model.Lftrattrib;
import com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest;
import com.sensus.mlc.lftrattrib.model.request.LftrattribRequest;
import com.sensus.mlc.lftrattrib.model.response.InquiryLftrattribResponse;
import com.sensus.mlc.lftrattrib.model.response.LftrattribResponse;

/** 
 * The Class LftrattribBCFImpl.
 *
 * @author - Washington
 */
public class LftrattribBCFImpl extends AbstractBaseBCF implements ILftrattribBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFTRATTRIB_REQUEST_NAME. */ 
	private static final String INQUIRY_LFTRATTRIB_REQUEST_NAME = InquiryLftrattribRequest.class.getSimpleName();

	/** The Constant LFTRATTRIB_REQUEST_NAME. */ 
	private static final String LFTRATTRIB_REQUEST_NAME = LftrattribRequest.class.getSimpleName();
 
	/** The Constant LFTRATTRIB_NAME. */ 
	private static final String LFTRATTRIB_NAME = Lftrattrib.class.getSimpleName();

	/** The Constant DEFAULT_LFTRATTRIB_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFTRATTRIB_BCF_EXCEPTION_MSG = "sensus.mlc.lftrattribbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LftrattribBCFImpl.class);
 
	/** The lftrattrib bcl. */ 
	private ILftrattribBCL lftrattribBCL;
 
	/** The lftrattrib validation controller. */ 
	private ValidationController lftrattribValidationController;
  
	/** The lftrattrib list validation controller. */ 
	private ValidationController lftrattribListValidationController;

	/** The lftrattrib request validation controller. */ 
	private ValidationController lftrattribRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lftrattrib bcl.
	 *  
	 * @return the lftrattrib bcl  
	 */ 
	public ILftrattribBCL getLftrattribBCL()
	{ 
		return this.lftrattribBCL;
	} 
  
	/**  
	 * Sets the lftrattrib bcl.  
	 *   
	 * @param lftrattribBCLObject the new lftrattrib bcl  
	 */ 
	public void setLftrattribBCL(ILftrattribBCL lftrattribBCLObject) 
	{
		this.lftrattribBCL = lftrattribBCLObject;
	} 
 
	/**  
	 * Gets the lftrattrib validation controller.
	 * 
	 * @return the lftrattrib validation controller  
	 */  
	public ValidationController getLftrattribValidationController()  
	{
		return this.lftrattribValidationController;
	}  
  
	/**   
	 * Sets the lftrattrib validation controller.
	 * 
	 * @param lftrattribValidationController the new lftrattrib validation controller  
	 */    
	public void setLftrattribValidationController(ValidationController lftrattribValidationController)   
	{
		this.lftrattribValidationController = lftrattribValidationController;
	} 
  
	/** 
	 * Gets the lftrattrib list validation controller. 
	 *  
	 * @return the lftrattrib list validation controller  
	 */   
	public ValidationController getLftrattribListValidationController()
	{  
		return this.lftrattribListValidationController;
	}  
   
	/**   
	 * Sets the lftrattrib list validation controller.  
	 *  
	 * @param lftrattribListValidationController the new lftrattrib list validation controller 
	 */ 
	public void setLftrattribListValidationController(ValidationController lftrattribListValidationController)
	{ 
		this.lftrattribListValidationController = lftrattribListValidationController;
	} 
     
	/**  
	 * Gets the lftrattrib request validation controller. 
	 *
	 * @return the lftrattrib request validation controller 
	 */
	public ValidationController getLftrattribRequestValidationController() 
	{ 
		return this.lftrattribRequestValidationController;
	} 
 
	/** 
	 * Sets the lftrattrib request validation controller. 
	 *  
	 * @param lftrattribRequestValidationController the new lftrattrib request validation controller 
	 */ 
	public void setLftrattribRequestValidationController(ValidationController lftrattribRequestValidationController) 
	{
		this.lftrattribRequestValidationController = lftrattribRequestValidationController;
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
	 * @see com.sensus.mlc.lftrattrib.bcf.ILftrattribBCF#fetchAllLftrattribs(com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest
	 */ 
	@Override 
	public InquiryLftrattribResponse fetchAllLftrattrib(InquiryLftrattribRequest inquiryLftrattribRequest)
	{
		InquiryLftrattribResponse response = new InquiryLftrattribResponse();
		InternalResultsResponse<Lftrattrib> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFTRATTRIB_REQUEST_NAME, inquiryLftrattribRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLftrattribBCL().fetchAllLftrattrib(inquiryLftrattribRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTRATTRIB_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftrattrib.bcf.ILftrattribBCF#fetchLftrattribByName(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest
	 */ 
	@Override 
	public LftrattribResponse fetchLftrattribById(LftrattribRequest lftrattribRequest)
	{  
		LftrattribResponse response = new LftrattribResponse();
		InternalResultsResponse<Lftrattrib> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFTRATTRIB_REQUEST_NAME, lftrattribRequest);
			context.putObjectToBeValidated(LFTRATTRIB_NAME, lftrattribRequest.getLftrattrib());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLftrattribValidationController().validate(context)) 
			{   
				internalResponse = getLftrattribBCL().fetchLftrattribByName(lftrattribRequest);
				response.setLftrattrib(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTRATTRIB_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftrattrib.bcf.ILftrattribBCF#insertLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)
	 */ 
	@Override 
	public LftrattribResponse insertLftrattrib(LftrattribRequest lftrattribRequest) 
	{ 
		LftrattribResponse response = new LftrattribResponse();
		InternalResultsResponse<Lftrattrib> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFTRATTRIB_REQUEST_NAME, lftrattribRequest);
			context.putObjectToBeValidated(LFTRATTRIB_NAME, lftrattribRequest.getLftrattrib());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLftrattribValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLftrattribBCL().insertLftrattrib(lftrattribRequest);
					response.setLftrattrib(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTRATTRIB_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftrattrib.bcf.ILftrattribBCF#deleteLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)
	 */  
	@Override  
	public LftrattribResponse deleteLftrattrib(LftrattribRequest lftrattribRequest)
	{  
		LftrattribResponse response = new LftrattribResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFTRATTRIB_REQUEST_NAME, lftrattribRequest);
			context.putObjectToBeValidated(LFTRATTRIB_NAME, lftrattribRequest.getLftrattrib());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLftrattribValidationController().validate(context))
			{
				internalResponse = getLftrattribBCL().deleteLftrattrib(lftrattribRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTRATTRIB_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftrattrib.bcf.ILftrattribBCF#updateLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest
	 */
	@Override 
	public LftrattribResponse updateLftrattrib(LftrattribRequest lftrattribRequest)
	{ 
		LftrattribResponse response = new LftrattribResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFTRATTRIB_REQUEST_NAME, lftrattribRequest);
			context.putObjectToBeValidated(LFTRATTRIB_NAME, lftrattribRequest.getLftrattrib());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLftrattribRequestValidationController().validate(context) && 
					getLftrattribValidationController().validate(context))
			{ 
				internalResponse = getLftrattribBCL().updateLftrattrib(lftrattribRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTRATTRIB_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
