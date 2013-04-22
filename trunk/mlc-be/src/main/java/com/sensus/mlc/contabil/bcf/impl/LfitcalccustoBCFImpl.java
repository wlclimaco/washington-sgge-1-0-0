package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_LFITCALCCUSTO;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_LFITCALCCUSTO;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LFITCALCCUSTO_LIST;

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
import com.sensus.mlc.lfitcalccusto.bcf.ILfitcalccustoBCF;
import com.sensus.mlc.lfitcalccusto.bcl.ILfitcalccustoBCL;
import com.sensus.mlc.lfitcalccusto.model.Lfitcalccusto;
import com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.response.InquiryLfitcalccustoResponse;
import com.sensus.mlc.lfitcalccusto.model.response.LfitcalccustoResponse;

/** 
 * The Class LfitcalccustoBCFImpl.
 *
 * @author - Washington
 */
public class LfitcalccustoBCFImpl extends AbstractBaseBCF implements ILfitcalccustoBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_LFITCALCCUSTO_REQUEST_NAME. */ 
	private static final String INQUIRY_LFITCALCCUSTO_REQUEST_NAME = InquiryLfitcalccustoRequest.class.getSimpleName();

	/** The Constant LFITCALCCUSTO_REQUEST_NAME. */ 
	private static final String LFITCALCCUSTO_REQUEST_NAME = LfitcalccustoRequest.class.getSimpleName();
 
	/** The Constant LFITCALCCUSTO_NAME. */ 
	private static final String LFITCALCCUSTO_NAME = Lfitcalccusto.class.getSimpleName();

	/** The Constant DEFAULT_LFITCALCCUSTO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LFITCALCCUSTO_BCF_EXCEPTION_MSG = "sensus.mlc.lfitcalccustobcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(LfitcalccustoBCFImpl.class);
 
	/** The lfitcalccusto bcl. */ 
	private ILfitcalccustoBCL lfitcalccustoBCL;
 
	/** The lfitcalccusto validation controller. */ 
	private ValidationController lfitcalccustoValidationController;
  
	/** The lfitcalccusto list validation controller. */ 
	private ValidationController lfitcalccustoListValidationController;

	/** The lfitcalccusto request validation controller. */ 
	private ValidationController lfitcalccustoRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the lfitcalccusto bcl.
	 *  
	 * @return the lfitcalccusto bcl  
	 */ 
	public ILfitcalccustoBCL getLfitcalccustoBCL()
	{ 
		return this.lfitcalccustoBCL;
	} 
  
	/**  
	 * Sets the lfitcalccusto bcl.  
	 *   
	 * @param lfitcalccustoBCLObject the new lfitcalccusto bcl  
	 */ 
	public void setLfitcalccustoBCL(ILfitcalccustoBCL lfitcalccustoBCLObject) 
	{
		this.lfitcalccustoBCL = lfitcalccustoBCLObject;
	} 
 
	/**  
	 * Gets the lfitcalccusto validation controller.
	 * 
	 * @return the lfitcalccusto validation controller  
	 */  
	public ValidationController getLfitcalccustoValidationController()  
	{
		return this.lfitcalccustoValidationController;
	}  
  
	/**   
	 * Sets the lfitcalccusto validation controller.
	 * 
	 * @param lfitcalccustoValidationController the new lfitcalccusto validation controller  
	 */    
	public void setLfitcalccustoValidationController(ValidationController lfitcalccustoValidationController)   
	{
		this.lfitcalccustoValidationController = lfitcalccustoValidationController;
	} 
  
	/** 
	 * Gets the lfitcalccusto list validation controller. 
	 *  
	 * @return the lfitcalccusto list validation controller  
	 */   
	public ValidationController getLfitcalccustoListValidationController()
	{  
		return this.lfitcalccustoListValidationController;
	}  
   
	/**   
	 * Sets the lfitcalccusto list validation controller.  
	 *  
	 * @param lfitcalccustoListValidationController the new lfitcalccusto list validation controller 
	 */ 
	public void setLfitcalccustoListValidationController(ValidationController lfitcalccustoListValidationController)
	{ 
		this.lfitcalccustoListValidationController = lfitcalccustoListValidationController;
	} 
     
	/**  
	 * Gets the lfitcalccusto request validation controller. 
	 *
	 * @return the lfitcalccusto request validation controller 
	 */
	public ValidationController getLfitcalccustoRequestValidationController() 
	{ 
		return this.lfitcalccustoRequestValidationController;
	} 
 
	/** 
	 * Sets the lfitcalccusto request validation controller. 
	 *  
	 * @param lfitcalccustoRequestValidationController the new lfitcalccusto request validation controller 
	 */ 
	public void setLfitcalccustoRequestValidationController(ValidationController lfitcalccustoRequestValidationController) 
	{
		this.lfitcalccustoRequestValidationController = lfitcalccustoRequestValidationController;
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
	 * @see com.sensus.mlc.lfitcalccusto.bcf.ILfitcalccustoBCF#fetchAllLfitcalccustos(com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest
	 */ 
	@Override 
	public InquiryLfitcalccustoResponse fetchAllLfitcalccusto(InquiryLfitcalccustoRequest inquiryLfitcalccustoRequest)
	{
		InquiryLfitcalccustoResponse response = new InquiryLfitcalccustoResponse();
		InternalResultsResponse<Lfitcalccusto> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_LFITCALCCUSTO_REQUEST_NAME, inquiryLfitcalccustoRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getLfitcalccustoBCL().fetchAllLfitcalccusto(inquiryLfitcalccustoRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCALCCUSTO_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcalccusto.bcf.ILfitcalccustoBCF#fetchLfitcalccustoByName(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest
	 */ 
	@Override 
	public LfitcalccustoResponse fetchLfitcalccustoById(LfitcalccustoRequest lfitcalccustoRequest)
	{  
		LfitcalccustoResponse response = new LfitcalccustoResponse();
		InternalResultsResponse<Lfitcalccusto> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(LFITCALCCUSTO_REQUEST_NAME, lfitcalccustoRequest);
			context.putObjectToBeValidated(LFITCALCCUSTO_NAME, lfitcalccustoRequest.getLfitcalccusto());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitcalccustoValidationController().validate(context)) 
			{   
				internalResponse = getLfitcalccustoBCL().fetchLfitcalccustoByName(lfitcalccustoRequest);
				response.setLfitcalccusto(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCALCCUSTO_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitcalccusto.bcf.ILfitcalccustoBCF#insertLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)
	 */ 
	@Override 
	public LfitcalccustoResponse insertLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest) 
	{ 
		LfitcalccustoResponse response = new LfitcalccustoResponse();
		InternalResultsResponse<Lfitcalccusto> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(LFITCALCCUSTO_REQUEST_NAME, lfitcalccustoRequest);
			context.putObjectToBeValidated(LFITCALCCUSTO_NAME, lfitcalccustoRequest.getLfitcalccusto());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getLfitcalccustoValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getLfitcalccustoBCL().insertLfitcalccusto(lfitcalccustoRequest);
					response.setLfitcalccusto(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCALCCUSTO_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitcalccusto.bcf.ILfitcalccustoBCF#deleteLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)
	 */  
	@Override  
	public LfitcalccustoResponse deleteLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest)
	{  
		LfitcalccustoResponse response = new LfitcalccustoResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(LFITCALCCUSTO_REQUEST_NAME, lfitcalccustoRequest);
			context.putObjectToBeValidated(LFITCALCCUSTO_NAME, lfitcalccustoRequest.getLfitcalccusto());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getLfitcalccustoValidationController().validate(context))
			{
				internalResponse = getLfitcalccustoBCL().deleteLfitcalccusto(lfitcalccustoRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCALCCUSTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcalccusto.bcf.ILfitcalccustoBCF#updateLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest
	 */
	@Override 
	public LfitcalccustoResponse updateLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest)
	{ 
		LfitcalccustoResponse response = new LfitcalccustoResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(LFITCALCCUSTO_REQUEST_NAME, lfitcalccustoRequest);
			context.putObjectToBeValidated(LFITCALCCUSTO_NAME, lfitcalccustoRequest.getLfitcalccusto());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getLfitcalccustoRequestValidationController().validate(context) && 
					getLfitcalccustoValidationController().validate(context))
			{ 
				internalResponse = getLfitcalccustoBCL().updateLfitcalccusto(lfitcalccustoRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LFITCALCCUSTO_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
