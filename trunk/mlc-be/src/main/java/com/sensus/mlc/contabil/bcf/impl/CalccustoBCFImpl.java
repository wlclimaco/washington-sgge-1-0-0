package com.sensus.mlc.contabil.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_CALCCUSTO;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_CALCCUSTO;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.CALCCUSTO_LIST;

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
import com.sensus.mlc.calccusto.bcf.ICalccustoBCF;
import com.sensus.mlc.calccusto.bcl.ICalccustoBCL;
import com.sensus.mlc.calccusto.model.Calccusto;
import com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest;
import com.sensus.mlc.calccusto.model.request.CalccustoRequest;
import com.sensus.mlc.calccusto.model.response.InquiryCalccustoResponse;
import com.sensus.mlc.calccusto.model.response.CalccustoResponse;

/** 
 * The Class CalccustoBCFImpl.
 *
 * @author - Washington
 */
public class CalccustoBCFImpl extends AbstractBaseBCF implements ICalccustoBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_CALCCUSTO_REQUEST_NAME. */ 
	private static final String INQUIRY_CALCCUSTO_REQUEST_NAME = InquiryCalccustoRequest.class.getSimpleName();

	/** The Constant CALCCUSTO_REQUEST_NAME. */ 
	private static final String CALCCUSTO_REQUEST_NAME = CalccustoRequest.class.getSimpleName();
 
	/** The Constant CALCCUSTO_NAME. */ 
	private static final String CALCCUSTO_NAME = Calccusto.class.getSimpleName();

	/** The Constant DEFAULT_CALCCUSTO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_CALCCUSTO_BCF_EXCEPTION_MSG = "sensus.mlc.calccustobcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(CalccustoBCFImpl.class);
 
	/** The calccusto bcl. */ 
	private ICalccustoBCL calccustoBCL;
 
	/** The calccusto validation controller. */ 
	private ValidationController calccustoValidationController;
  
	/** The calccusto list validation controller. */ 
	private ValidationController calccustoListValidationController;

	/** The calccusto request validation controller. */ 
	private ValidationController calccustoRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the calccusto bcl.
	 *  
	 * @return the calccusto bcl  
	 */ 
	public ICalccustoBCL getCalccustoBCL()
	{ 
		return this.calccustoBCL;
	} 
  
	/**  
	 * Sets the calccusto bcl.  
	 *   
	 * @param calccustoBCLObject the new calccusto bcl  
	 */ 
	public void setCalccustoBCL(ICalccustoBCL calccustoBCLObject) 
	{
		this.calccustoBCL = calccustoBCLObject;
	} 
 
	/**  
	 * Gets the calccusto validation controller.
	 * 
	 * @return the calccusto validation controller  
	 */  
	public ValidationController getCalccustoValidationController()  
	{
		return this.calccustoValidationController;
	}  
  
	/**   
	 * Sets the calccusto validation controller.
	 * 
	 * @param calccustoValidationController the new calccusto validation controller  
	 */    
	public void setCalccustoValidationController(ValidationController calccustoValidationController)   
	{
		this.calccustoValidationController = calccustoValidationController;
	} 
  
	/** 
	 * Gets the calccusto list validation controller. 
	 *  
	 * @return the calccusto list validation controller  
	 */   
	public ValidationController getCalccustoListValidationController()
	{  
		return this.calccustoListValidationController;
	}  
   
	/**   
	 * Sets the calccusto list validation controller.  
	 *  
	 * @param calccustoListValidationController the new calccusto list validation controller 
	 */ 
	public void setCalccustoListValidationController(ValidationController calccustoListValidationController)
	{ 
		this.calccustoListValidationController = calccustoListValidationController;
	} 
     
	/**  
	 * Gets the calccusto request validation controller. 
	 *
	 * @return the calccusto request validation controller 
	 */
	public ValidationController getCalccustoRequestValidationController() 
	{ 
		return this.calccustoRequestValidationController;
	} 
 
	/** 
	 * Sets the calccusto request validation controller. 
	 *  
	 * @param calccustoRequestValidationController the new calccusto request validation controller 
	 */ 
	public void setCalccustoRequestValidationController(ValidationController calccustoRequestValidationController) 
	{
		this.calccustoRequestValidationController = calccustoRequestValidationController;
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
	 * @see com.sensus.mlc.calccusto.bcf.ICalccustoBCF#fetchAllCalccustos(com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest
	 */ 
	@Override 
	public InquiryCalccustoResponse fetchAllCalccusto(InquiryCalccustoRequest inquiryCalccustoRequest)
	{
		InquiryCalccustoResponse response = new InquiryCalccustoResponse();
		InternalResultsResponse<Calccusto> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_CALCCUSTO_REQUEST_NAME, inquiryCalccustoRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getCalccustoBCL().fetchAllCalccusto(inquiryCalccustoRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CALCCUSTO_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.calccusto.bcf.ICalccustoBCF#fetchCalccustoByName(com.sensus.mlc.calccusto.model.request.CalccustoRequest
	 */ 
	@Override 
	public CalccustoResponse fetchCalccustoById(CalccustoRequest calccustoRequest)
	{  
		CalccustoResponse response = new CalccustoResponse();
		InternalResultsResponse<Calccusto> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(CALCCUSTO_REQUEST_NAME, calccustoRequest);
			context.putObjectToBeValidated(CALCCUSTO_NAME, calccustoRequest.getCalccusto());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getCalccustoValidationController().validate(context)) 
			{   
				internalResponse = getCalccustoBCL().fetchCalccustoByName(calccustoRequest);
				response.setCalccusto(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CALCCUSTO_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.calccusto.bcf.ICalccustoBCF#insertCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest)
	 */ 
	@Override 
	public CalccustoResponse insertCalccusto(CalccustoRequest calccustoRequest) 
	{ 
		CalccustoResponse response = new CalccustoResponse();
		InternalResultsResponse<Calccusto> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(CALCCUSTO_REQUEST_NAME, calccustoRequest);
			context.putObjectToBeValidated(CALCCUSTO_NAME, calccustoRequest.getCalccusto());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getCalccustoValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getCalccustoBCL().insertCalccusto(calccustoRequest);
					response.setCalccusto(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CALCCUSTO_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.calccusto.bcf.ICalccustoBCF#deleteCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest)
	 */  
	@Override  
	public CalccustoResponse deleteCalccusto(CalccustoRequest calccustoRequest)
	{  
		CalccustoResponse response = new CalccustoResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(CALCCUSTO_REQUEST_NAME, calccustoRequest);
			context.putObjectToBeValidated(CALCCUSTO_NAME, calccustoRequest.getCalccusto());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getCalccustoValidationController().validate(context))
			{
				internalResponse = getCalccustoBCL().deleteCalccusto(calccustoRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CALCCUSTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.calccusto.bcf.ICalccustoBCF#updateCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest
	 */
	@Override 
	public CalccustoResponse updateCalccusto(CalccustoRequest calccustoRequest)
	{ 
		CalccustoResponse response = new CalccustoResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(CALCCUSTO_REQUEST_NAME, calccustoRequest);
			context.putObjectToBeValidated(CALCCUSTO_NAME, calccustoRequest.getCalccusto());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getCalccustoRequestValidationController().validate(context) && 
					getCalccustoValidationController().validate(context))
			{ 
				internalResponse = getCalccustoBCL().updateCalccusto(calccustoRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CALCCUSTO_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
