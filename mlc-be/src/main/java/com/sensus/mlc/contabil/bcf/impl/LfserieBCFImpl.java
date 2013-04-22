package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFSERIE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFSERIE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFSERIE_LIST;

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
import com.sensus.mlc.lfserie.bcf.ILfserieBCF;
import com.sensus.mlc.lfserie.bcl.ILfserieBCL;
import com.sensus.mlc.lfserie.model.Lfserie;
import com.sensus.mlc.lfserie.model.request.InquiryLfserieRequest;
import com.sensus.mlc.lfserie.model.request.LfserieRequest;
import com.sensus.mlc.lfserie.model.response.InquiryLfserieResponse;
import com.sensus.mlc.lfserie.model.response.LfserieResponse;

/** 
 * The Class LfserieBCFImpl.
 *
 * @author - Washington
 */
public class LfserieBCFImpl extends AbstractBaseBCF implements ILfserieBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFSERIE_REQUEST_NAME. */ 
	private static final String INQUIRY_LFSERIE_REQUEST_NAME = InquiryLfserieRequest.class.getSimpleName();

	/** The Constant LFSERIE_REQUEST_NAME. */ 
	private static final String LFSERIE_REQUEST_NAME = LfserieRequest.class.getSimpleName();
 
	/** The Constant LFSERIE_NAME. */ 
	private static final String LFSERIE_NAME = Lfserie.class.getSimpleName();

	/** The Constant DEFAULT_LFSERIE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFSERIE_BCF_EXCEPTION_MSG = "sensus.mlc.lfseriebcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfserieBCFImpl.class);
 
	/** The lfserie bcl. */ 
	private ILfserieBCL lfserieBCL;
 
	/** The lfserie validation controller. */ 
	private ValidationController lfserieValidationController;
  
	/** The lfserie list validation controller. */ 
	private ValidationController lfserieListValidationController;

	/** The lfserie request validation controller. */ 
	private ValidationController lfserieRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfserie bcl.
	 *  
	 * @return the lfserie bcl  
	 */ 
	public ILfserieBCL getLfserieBCL()
	{ 
		return this.lfserieBCL;
	} 
  
	/**  
	 * Sets the lfserie bcl.  
	 *   
	 * @param lfserieBCLObject the new lfserie bcl  
	 */ 
	public void setLfserieBCL(ILfserieBCL lfserieBCLObject) 
	{
		this.lfserieBCL = lfserieBCLObject;
	} 
 
	/**  
	 * Gets the lfserie validation controller.
	 * 
	 * @return the lfserie validation controller  
	 */  
	public ValidationController getLfserieValidationController()  
	{
		return this.lfserieValidationController;
	}  
  
	/**   
	 * Sets the lfserie validation controller.
	 * 
	 * @param lfserieValidationController the new lfserie validation controller  
	 */    
	public void setLfserieValidationController(ValidationController lfserieValidationController)   
	{
		this.lfserieValidationController = lfserieValidationController;
	} 
  
	/** 
	 * Gets the lfserie list validation controller. 
	 *  
	 * @return the lfserie list validation controller  
	 */   
	public ValidationController getLfserieListValidationController()
	{  
		return this.lfserieListValidationController;
	}  
   
	/**   
	 * Sets the lfserie list validation controller.  
	 *  
	 * @param lfserieListValidationController the new lfserie list validation controller 
	 */ 
	public void setLfserieListValidationController(ValidationController lfserieListValidationController)
	{ 
		this.lfserieListValidationController = lfserieListValidationController;
	} 
     
	/**  
	 * Gets the lfserie request validation controller. 
	 *
	 * @return the lfserie request validation controller 
	 */
	public ValidationController getLfserieRequestValidationController() 
	{ 
		return this.lfserieRequestValidationController;
	} 
 
	/** 
	 * Sets the lfserie request validation controller. 
	 *  
	 * @param lfserieRequestValidationController the new lfserie request validation controller 
	 */ 
	public void setLfserieRequestValidationController(ValidationController lfserieRequestValidationController) 
	{
		this.lfserieRequestValidationController = lfserieRequestValidationController;
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
	 * @see com.sensus.mlc.lfserie.bcf.ILfserieBCF#fetchAllLfseries(com.sensus.mlc.lfserie.model.request.InquiryLfserieRequest
	 */ 
	@Override 
	public InquiryLfserieResponse fetchAllLfserie(InquiryLfserieRequest inquiryLfserieRequest)
	{
		InquiryLfserieResponse response = new InquiryLfserieResponse();
		InternalResultsResponse<Lfserie> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFSERIE_REQUEST_NAME, inquiryLfserieRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfserieBCL().fetchAllLfserie(inquiryLfserieRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSERIE_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfserie.bcf.ILfserieBCF#fetchLfserieByName(com.sensus.mlc.lfserie.model.request.LfserieRequest
	 */ 
	@Override 
	public LfserieResponse fetchLfserieById(LfserieRequest lfserieRequest)
	{  
		LfserieResponse response = new LfserieResponse();
		InternalResultsResponse<Lfserie> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFSERIE_REQUEST_NAME, lfserieRequest);
			context.putObjectToBeValidated(LFSERIE_NAME, lfserieRequest.getLfserie());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfserieValidationController().validate(context)) 
			{   
				internalResponse = getLfserieBCL().fetchLfserieByName(lfserieRequest);
				response.setLfserie(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSERIE_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfserie.bcf.ILfserieBCF#insertLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest)
	 */ 
	@Override 
	public LfserieResponse insertLfserie(LfserieRequest lfserieRequest) 
	{ 
		LfserieResponse response = new LfserieResponse();
		InternalResultsResponse<Lfserie> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFSERIE_REQUEST_NAME, lfserieRequest);
			context.putObjectToBeValidated(LFSERIE_NAME, lfserieRequest.getLfserie());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfserieValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfserieBCL().insertLfserie(lfserieRequest);
					response.setLfserie(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSERIE_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfserie.bcf.ILfserieBCF#deleteLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest)
	 */  
	@Override  
	public LfserieResponse deleteLfserie(LfserieRequest lfserieRequest)
	{  
		LfserieResponse response = new LfserieResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFSERIE_REQUEST_NAME, lfserieRequest);
			context.putObjectToBeValidated(LFSERIE_NAME, lfserieRequest.getLfserie());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfserieValidationController().validate(context))
			{
				internalResponse = getLfserieBCL().deleteLfserie(lfserieRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSERIE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfserie.bcf.ILfserieBCF#updateLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest
	 */
	@Override 
	public LfserieResponse updateLfserie(LfserieRequest lfserieRequest)
	{ 
		LfserieResponse response = new LfserieResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFSERIE_REQUEST_NAME, lfserieRequest);
			context.putObjectToBeValidated(LFSERIE_NAME, lfserieRequest.getLfserie());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfserieRequestValidationController().validate(context) && 
					getLfserieValidationController().validate(context))
			{ 
				internalResponse = getLfserieBCL().updateLfserie(lfserieRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSERIE_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
