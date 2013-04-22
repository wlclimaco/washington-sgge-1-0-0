package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFTABICMS.TXT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFTABICMS.TXT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFTABICMS.TXT_LIST;

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
import com.sensus.mlc.lftabicms.txt.bcf.ILftabicms.txtBCF;
import com.sensus.mlc.lftabicms.txt.bcl.ILftabicms.txtBCL;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txt;
import com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.response.InquiryLftabicms.txtResponse;
import com.sensus.mlc.lftabicms.txt.model.response.Lftabicms.txtResponse;

/** 
 * The Class Lftabicms.txtBCFImpl.
 *
 * @author - Washington
 */
public class Lftabicms.txtBCFImpl extends AbstractBaseBCF implements ILftabicms.txtBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFTABICMS.TXT_REQUEST_NAME. */ 
	private static final String INQUIRY_LFTABICMS.TXT_REQUEST_NAME = InquiryLftabicms.txtRequest.class.getSimpleName();

	/** The Constant LFTABICMS.TXT_REQUEST_NAME. */ 
	private static final String LFTABICMS.TXT_REQUEST_NAME = Lftabicms.txtRequest.class.getSimpleName();
 
	/** The Constant LFTABICMS.TXT_NAME. */ 
	private static final String LFTABICMS.TXT_NAME = Lftabicms.txt.class.getSimpleName();

	/** The Constant DEFAULT_LFTABICMS.TXT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFTABICMS.TXT_BCF_EXCEPTION_MSG = "sensus.mlc.lftabicms.txtbcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(Lftabicms.txtBCFImpl.class);
 
	/** The lftabicms.txt bcl. */ 
	private ILftabicms.txtBCL lftabicms.txtBCL;
 
	/** The lftabicms.txt validation controller. */ 
	private ValidationController lftabicms.txtValidationController;
  
	/** The lftabicms.txt list validation controller. */ 
	private ValidationController lftabicms.txtListValidationController;

	/** The lftabicms.txt request validation controller. */ 
	private ValidationController lftabicms.txtRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lftabicms.txt bcl.
	 *  
	 * @return the lftabicms.txt bcl  
	 */ 
	public ILftabicms.txtBCL getLftabicms.txtBCL()
	{ 
		return this.lftabicms.txtBCL;
	} 
  
	/**  
	 * Sets the lftabicms.txt bcl.  
	 *   
	 * @param lftabicms.txtBCLObject the new lftabicms.txt bcl  
	 */ 
	public void setLftabicms.txtBCL(ILftabicms.txtBCL lftabicms.txtBCLObject) 
	{
		this.lftabicms.txtBCL = lftabicms.txtBCLObject;
	} 
 
	/**  
	 * Gets the lftabicms.txt validation controller.
	 * 
	 * @return the lftabicms.txt validation controller  
	 */  
	public ValidationController getLftabicms.txtValidationController()  
	{
		return this.lftabicms.txtValidationController;
	}  
  
	/**   
	 * Sets the lftabicms.txt validation controller.
	 * 
	 * @param lftabicms.txtValidationController the new lftabicms.txt validation controller  
	 */    
	public void setLftabicms.txtValidationController(ValidationController lftabicms.txtValidationController)   
	{
		this.lftabicms.txtValidationController = lftabicms.txtValidationController;
	} 
  
	/** 
	 * Gets the lftabicms.txt list validation controller. 
	 *  
	 * @return the lftabicms.txt list validation controller  
	 */   
	public ValidationController getLftabicms.txtListValidationController()
	{  
		return this.lftabicms.txtListValidationController;
	}  
   
	/**   
	 * Sets the lftabicms.txt list validation controller.  
	 *  
	 * @param lftabicms.txtListValidationController the new lftabicms.txt list validation controller 
	 */ 
	public void setLftabicms.txtListValidationController(ValidationController lftabicms.txtListValidationController)
	{ 
		this.lftabicms.txtListValidationController = lftabicms.txtListValidationController;
	} 
     
	/**  
	 * Gets the lftabicms.txt request validation controller. 
	 *
	 * @return the lftabicms.txt request validation controller 
	 */
	public ValidationController getLftabicms.txtRequestValidationController() 
	{ 
		return this.lftabicms.txtRequestValidationController;
	} 
 
	/** 
	 * Sets the lftabicms.txt request validation controller. 
	 *  
	 * @param lftabicms.txtRequestValidationController the new lftabicms.txt request validation controller 
	 */ 
	public void setLftabicms.txtRequestValidationController(ValidationController lftabicms.txtRequestValidationController) 
	{
		this.lftabicms.txtRequestValidationController = lftabicms.txtRequestValidationController;
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
	 * @see com.sensus.mlc.lftabicms.txt.bcf.ILftabicms.txtBCF#fetchAllLftabicms.txts(com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest
	 */ 
	@Override 
	public InquiryLftabicms.txtResponse fetchAllLftabicms.txt(InquiryLftabicms.txtRequest inquiryLftabicms.txtRequest)
	{
		InquiryLftabicms.txtResponse response = new InquiryLftabicms.txtResponse();
		InternalResultsResponse<Lftabicms.txt> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFTABICMS.TXT_REQUEST_NAME, inquiryLftabicms.txtRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLftabicms.txtBCL().fetchAllLftabicms.txt(inquiryLftabicms.txtRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTABICMS.TXT_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftabicms.txt.bcf.ILftabicms.txtBCF#fetchLftabicms.txtByName(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest
	 */ 
	@Override 
	public Lftabicms.txtResponse fetchLftabicms.txtById(Lftabicms.txtRequest lftabicms.txtRequest)
	{  
		Lftabicms.txtResponse response = new Lftabicms.txtResponse();
		InternalResultsResponse<Lftabicms.txt> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFTABICMS.TXT_REQUEST_NAME, lftabicms.txtRequest);
			context.putObjectToBeValidated(LFTABICMS.TXT_NAME, lftabicms.txtRequest.getLftabicms.txt());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLftabicms.txtValidationController().validate(context)) 
			{   
				internalResponse = getLftabicms.txtBCL().fetchLftabicms.txtByName(lftabicms.txtRequest);
				response.setLftabicms.txt(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTABICMS.TXT_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftabicms.txt.bcf.ILftabicms.txtBCF#insertLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)
	 */ 
	@Override 
	public Lftabicms.txtResponse insertLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest) 
	{ 
		Lftabicms.txtResponse response = new Lftabicms.txtResponse();
		InternalResultsResponse<Lftabicms.txt> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFTABICMS.TXT_REQUEST_NAME, lftabicms.txtRequest);
			context.putObjectToBeValidated(LFTABICMS.TXT_NAME, lftabicms.txtRequest.getLftabicms.txt());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLftabicms.txtValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLftabicms.txtBCL().insertLftabicms.txt(lftabicms.txtRequest);
					response.setLftabicms.txt(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTABICMS.TXT_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftabicms.txt.bcf.ILftabicms.txtBCF#deleteLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)
	 */  
	@Override  
	public Lftabicms.txtResponse deleteLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest)
	{  
		Lftabicms.txtResponse response = new Lftabicms.txtResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFTABICMS.TXT_REQUEST_NAME, lftabicms.txtRequest);
			context.putObjectToBeValidated(LFTABICMS.TXT_NAME, lftabicms.txtRequest.getLftabicms.txt());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLftabicms.txtValidationController().validate(context))
			{
				internalResponse = getLftabicms.txtBCL().deleteLftabicms.txt(lftabicms.txtRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTABICMS.TXT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftabicms.txt.bcf.ILftabicms.txtBCF#updateLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest
	 */
	@Override 
	public Lftabicms.txtResponse updateLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest)
	{ 
		Lftabicms.txtResponse response = new Lftabicms.txtResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFTABICMS.TXT_REQUEST_NAME, lftabicms.txtRequest);
			context.putObjectToBeValidated(LFTABICMS.TXT_NAME, lftabicms.txtRequest.getLftabicms.txt());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLftabicms.txtRequestValidationController().validate(context) && 
					getLftabicms.txtValidationController().validate(context))
			{ 
				internalResponse = getLftabicms.txtBCL().updateLftabicms.txt(lftabicms.txtRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFTABICMS.TXT_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
