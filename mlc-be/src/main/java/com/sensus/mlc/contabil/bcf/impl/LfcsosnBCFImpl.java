package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFCSOSN;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFCSOSN;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFCSOSN_LIST;

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
import com.sensus.mlc.lfcsosn.bcf.ILfcsosnBCF;
import com.sensus.mlc.lfcsosn.bcl.ILfcsosnBCL;
import com.sensus.mlc.lfcsosn.model.Lfcsosn;
import com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.response.InquiryLfcsosnResponse;
import com.sensus.mlc.lfcsosn.model.response.LfcsosnResponse;

/** 
 * The Class LfcsosnBCFImpl.
 *
 * @author - Washington
 */
public class LfcsosnBCFImpl extends AbstractBaseBCF implements ILfcsosnBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFCSOSN_REQUEST_NAME. */ 
	private static final String INQUIRY_LFCSOSN_REQUEST_NAME = InquiryLfcsosnRequest.class.getSimpleName();

	/** The Constant LFCSOSN_REQUEST_NAME. */ 
	private static final String LFCSOSN_REQUEST_NAME = LfcsosnRequest.class.getSimpleName();
 
	/** The Constant LFCSOSN_NAME. */ 
	private static final String LFCSOSN_NAME = Lfcsosn.class.getSimpleName();

	/** The Constant DEFAULT_LFCSOSN_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFCSOSN_BCF_EXCEPTION_MSG = "sensus.mlc.lfcsosnbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfcsosnBCFImpl.class);
 
	/** The lfcsosn bcl. */ 
	private ILfcsosnBCL lfcsosnBCL;
 
	/** The lfcsosn validation controller. */ 
	private ValidationController lfcsosnValidationController;
  
	/** The lfcsosn list validation controller. */ 
	private ValidationController lfcsosnListValidationController;

	/** The lfcsosn request validation controller. */ 
	private ValidationController lfcsosnRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfcsosn bcl.
	 *  
	 * @return the lfcsosn bcl  
	 */ 
	public ILfcsosnBCL getLfcsosnBCL()
	{ 
		return this.lfcsosnBCL;
	} 
  
	/**  
	 * Sets the lfcsosn bcl.  
	 *   
	 * @param lfcsosnBCLObject the new lfcsosn bcl  
	 */ 
	public void setLfcsosnBCL(ILfcsosnBCL lfcsosnBCLObject) 
	{
		this.lfcsosnBCL = lfcsosnBCLObject;
	} 
 
	/**  
	 * Gets the lfcsosn validation controller.
	 * 
	 * @return the lfcsosn validation controller  
	 */  
	public ValidationController getLfcsosnValidationController()  
	{
		return this.lfcsosnValidationController;
	}  
  
	/**   
	 * Sets the lfcsosn validation controller.
	 * 
	 * @param lfcsosnValidationController the new lfcsosn validation controller  
	 */    
	public void setLfcsosnValidationController(ValidationController lfcsosnValidationController)   
	{
		this.lfcsosnValidationController = lfcsosnValidationController;
	} 
  
	/** 
	 * Gets the lfcsosn list validation controller. 
	 *  
	 * @return the lfcsosn list validation controller  
	 */   
	public ValidationController getLfcsosnListValidationController()
	{  
		return this.lfcsosnListValidationController;
	}  
   
	/**   
	 * Sets the lfcsosn list validation controller.  
	 *  
	 * @param lfcsosnListValidationController the new lfcsosn list validation controller 
	 */ 
	public void setLfcsosnListValidationController(ValidationController lfcsosnListValidationController)
	{ 
		this.lfcsosnListValidationController = lfcsosnListValidationController;
	} 
     
	/**  
	 * Gets the lfcsosn request validation controller. 
	 *
	 * @return the lfcsosn request validation controller 
	 */
	public ValidationController getLfcsosnRequestValidationController() 
	{ 
		return this.lfcsosnRequestValidationController;
	} 
 
	/** 
	 * Sets the lfcsosn request validation controller. 
	 *  
	 * @param lfcsosnRequestValidationController the new lfcsosn request validation controller 
	 */ 
	public void setLfcsosnRequestValidationController(ValidationController lfcsosnRequestValidationController) 
	{
		this.lfcsosnRequestValidationController = lfcsosnRequestValidationController;
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
	 * @see com.sensus.mlc.lfcsosn.bcf.ILfcsosnBCF#fetchAllLfcsosns(com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest
	 */ 
	@Override 
	public InquiryLfcsosnResponse fetchAllLfcsosn(InquiryLfcsosnRequest inquiryLfcsosnRequest)
	{
		InquiryLfcsosnResponse response = new InquiryLfcsosnResponse();
		InternalResultsResponse<Lfcsosn> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFCSOSN_REQUEST_NAME, inquiryLfcsosnRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfcsosnBCL().fetchAllLfcsosn(inquiryLfcsosnRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCSOSN_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfcsosn.bcf.ILfcsosnBCF#fetchLfcsosnByName(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest
	 */ 
	@Override 
	public LfcsosnResponse fetchLfcsosnById(LfcsosnRequest lfcsosnRequest)
	{  
		LfcsosnResponse response = new LfcsosnResponse();
		InternalResultsResponse<Lfcsosn> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFCSOSN_REQUEST_NAME, lfcsosnRequest);
			context.putObjectToBeValidated(LFCSOSN_NAME, lfcsosnRequest.getLfcsosn());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfcsosnValidationController().validate(context)) 
			{   
				internalResponse = getLfcsosnBCL().fetchLfcsosnByName(lfcsosnRequest);
				response.setLfcsosn(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCSOSN_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfcsosn.bcf.ILfcsosnBCF#insertLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)
	 */ 
	@Override 
	public LfcsosnResponse insertLfcsosn(LfcsosnRequest lfcsosnRequest) 
	{ 
		LfcsosnResponse response = new LfcsosnResponse();
		InternalResultsResponse<Lfcsosn> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFCSOSN_REQUEST_NAME, lfcsosnRequest);
			context.putObjectToBeValidated(LFCSOSN_NAME, lfcsosnRequest.getLfcsosn());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfcsosnValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfcsosnBCL().insertLfcsosn(lfcsosnRequest);
					response.setLfcsosn(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCSOSN_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfcsosn.bcf.ILfcsosnBCF#deleteLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)
	 */  
	@Override  
	public LfcsosnResponse deleteLfcsosn(LfcsosnRequest lfcsosnRequest)
	{  
		LfcsosnResponse response = new LfcsosnResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFCSOSN_REQUEST_NAME, lfcsosnRequest);
			context.putObjectToBeValidated(LFCSOSN_NAME, lfcsosnRequest.getLfcsosn());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfcsosnValidationController().validate(context))
			{
				internalResponse = getLfcsosnBCL().deleteLfcsosn(lfcsosnRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCSOSN_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfcsosn.bcf.ILfcsosnBCF#updateLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest
	 */
	@Override 
	public LfcsosnResponse updateLfcsosn(LfcsosnRequest lfcsosnRequest)
	{ 
		LfcsosnResponse response = new LfcsosnResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFCSOSN_REQUEST_NAME, lfcsosnRequest);
			context.putObjectToBeValidated(LFCSOSN_NAME, lfcsosnRequest.getLfcsosn());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfcsosnRequestValidationController().validate(context) && 
					getLfcsosnValidationController().validate(context))
			{ 
				internalResponse = getLfcsosnBCL().updateLfcsosn(lfcsosnRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFCSOSN_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
