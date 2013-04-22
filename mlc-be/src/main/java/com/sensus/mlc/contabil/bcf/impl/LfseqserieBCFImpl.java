package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFSEQSERIE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFSEQSERIE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFSEQSERIE_LIST;

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
import com.sensus.mlc.lfseqserie.bcf.ILfseqserieBCF;
import com.sensus.mlc.lfseqserie.bcl.ILfseqserieBCL;
import com.sensus.mlc.lfseqserie.model.Lfseqserie;
import com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.response.InquiryLfseqserieResponse;
import com.sensus.mlc.lfseqserie.model.response.LfseqserieResponse;

/** 
 * The Class LfseqserieBCFImpl.
 *
 * @author - Washington
 */
public class LfseqserieBCFImpl extends AbstractBaseBCF implements ILfseqserieBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFSEQSERIE_REQUEST_NAME. */ 
	private static final String INQUIRY_LFSEQSERIE_REQUEST_NAME = InquiryLfseqserieRequest.class.getSimpleName();

	/** The Constant LFSEQSERIE_REQUEST_NAME. */ 
	private static final String LFSEQSERIE_REQUEST_NAME = LfseqserieRequest.class.getSimpleName();
 
	/** The Constant LFSEQSERIE_NAME. */ 
	private static final String LFSEQSERIE_NAME = Lfseqserie.class.getSimpleName();

	/** The Constant DEFAULT_LFSEQSERIE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFSEQSERIE_BCF_EXCEPTION_MSG = "sensus.mlc.lfseqseriebcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfseqserieBCFImpl.class);
 
	/** The lfseqserie bcl. */ 
	private ILfseqserieBCL lfseqserieBCL;
 
	/** The lfseqserie validation controller. */ 
	private ValidationController lfseqserieValidationController;
  
	/** The lfseqserie list validation controller. */ 
	private ValidationController lfseqserieListValidationController;

	/** The lfseqserie request validation controller. */ 
	private ValidationController lfseqserieRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfseqserie bcl.
	 *  
	 * @return the lfseqserie bcl  
	 */ 
	public ILfseqserieBCL getLfseqserieBCL()
	{ 
		return this.lfseqserieBCL;
	} 
  
	/**  
	 * Sets the lfseqserie bcl.  
	 *   
	 * @param lfseqserieBCLObject the new lfseqserie bcl  
	 */ 
	public void setLfseqserieBCL(ILfseqserieBCL lfseqserieBCLObject) 
	{
		this.lfseqserieBCL = lfseqserieBCLObject;
	} 
 
	/**  
	 * Gets the lfseqserie validation controller.
	 * 
	 * @return the lfseqserie validation controller  
	 */  
	public ValidationController getLfseqserieValidationController()  
	{
		return this.lfseqserieValidationController;
	}  
  
	/**   
	 * Sets the lfseqserie validation controller.
	 * 
	 * @param lfseqserieValidationController the new lfseqserie validation controller  
	 */    
	public void setLfseqserieValidationController(ValidationController lfseqserieValidationController)   
	{
		this.lfseqserieValidationController = lfseqserieValidationController;
	} 
  
	/** 
	 * Gets the lfseqserie list validation controller. 
	 *  
	 * @return the lfseqserie list validation controller  
	 */   
	public ValidationController getLfseqserieListValidationController()
	{  
		return this.lfseqserieListValidationController;
	}  
   
	/**   
	 * Sets the lfseqserie list validation controller.  
	 *  
	 * @param lfseqserieListValidationController the new lfseqserie list validation controller 
	 */ 
	public void setLfseqserieListValidationController(ValidationController lfseqserieListValidationController)
	{ 
		this.lfseqserieListValidationController = lfseqserieListValidationController;
	} 
     
	/**  
	 * Gets the lfseqserie request validation controller. 
	 *
	 * @return the lfseqserie request validation controller 
	 */
	public ValidationController getLfseqserieRequestValidationController() 
	{ 
		return this.lfseqserieRequestValidationController;
	} 
 
	/** 
	 * Sets the lfseqserie request validation controller. 
	 *  
	 * @param lfseqserieRequestValidationController the new lfseqserie request validation controller 
	 */ 
	public void setLfseqserieRequestValidationController(ValidationController lfseqserieRequestValidationController) 
	{
		this.lfseqserieRequestValidationController = lfseqserieRequestValidationController;
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
	 * @see com.sensus.mlc.lfseqserie.bcf.ILfseqserieBCF#fetchAllLfseqseries(com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest
	 */ 
	@Override 
	public InquiryLfseqserieResponse fetchAllLfseqserie(InquiryLfseqserieRequest inquiryLfseqserieRequest)
	{
		InquiryLfseqserieResponse response = new InquiryLfseqserieResponse();
		InternalResultsResponse<Lfseqserie> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFSEQSERIE_REQUEST_NAME, inquiryLfseqserieRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfseqserieBCL().fetchAllLfseqserie(inquiryLfseqserieRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSEQSERIE_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfseqserie.bcf.ILfseqserieBCF#fetchLfseqserieByName(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest
	 */ 
	@Override 
	public LfseqserieResponse fetchLfseqserieById(LfseqserieRequest lfseqserieRequest)
	{  
		LfseqserieResponse response = new LfseqserieResponse();
		InternalResultsResponse<Lfseqserie> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFSEQSERIE_REQUEST_NAME, lfseqserieRequest);
			context.putObjectToBeValidated(LFSEQSERIE_NAME, lfseqserieRequest.getLfseqserie());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfseqserieValidationController().validate(context)) 
			{   
				internalResponse = getLfseqserieBCL().fetchLfseqserieByName(lfseqserieRequest);
				response.setLfseqserie(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSEQSERIE_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfseqserie.bcf.ILfseqserieBCF#insertLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)
	 */ 
	@Override 
	public LfseqserieResponse insertLfseqserie(LfseqserieRequest lfseqserieRequest) 
	{ 
		LfseqserieResponse response = new LfseqserieResponse();
		InternalResultsResponse<Lfseqserie> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFSEQSERIE_REQUEST_NAME, lfseqserieRequest);
			context.putObjectToBeValidated(LFSEQSERIE_NAME, lfseqserieRequest.getLfseqserie());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfseqserieValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfseqserieBCL().insertLfseqserie(lfseqserieRequest);
					response.setLfseqserie(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSEQSERIE_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfseqserie.bcf.ILfseqserieBCF#deleteLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)
	 */  
	@Override  
	public LfseqserieResponse deleteLfseqserie(LfseqserieRequest lfseqserieRequest)
	{  
		LfseqserieResponse response = new LfseqserieResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFSEQSERIE_REQUEST_NAME, lfseqserieRequest);
			context.putObjectToBeValidated(LFSEQSERIE_NAME, lfseqserieRequest.getLfseqserie());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfseqserieValidationController().validate(context))
			{
				internalResponse = getLfseqserieBCL().deleteLfseqserie(lfseqserieRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSEQSERIE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfseqserie.bcf.ILfseqserieBCF#updateLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest
	 */
	@Override 
	public LfseqserieResponse updateLfseqserie(LfseqserieRequest lfseqserieRequest)
	{ 
		LfseqserieResponse response = new LfseqserieResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFSEQSERIE_REQUEST_NAME, lfseqserieRequest);
			context.putObjectToBeValidated(LFSEQSERIE_NAME, lfseqserieRequest.getLfseqserie());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfseqserieRequestValidationController().validate(context) && 
					getLfseqserieValidationController().validate(context))
			{ 
				internalResponse = getLfseqserieBCL().updateLfseqserie(lfseqserieRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFSEQSERIE_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
