package com.sensus.mlc.tabela.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_TABELA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_TABELA;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.TABELA_LIST;

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
import com.sensus.mlc.tabela.bcf.ITabelaBCF;
import com.sensus.mlc.tabela.bcl.ITabelaBCL;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
import com.sensus.mlc.tabela.model.request.TabelaRequest;
import com.sensus.mlc.tabela.model.response.InquiryTabelaResponse;
import com.sensus.mlc.tabela.model.response.TabelaResponse;

/** 
 * The Class TabelaBCFImpl.
 *
 * @author - Washington
 */
public class TabelaBCFImpl extends AbstractBaseBCF implements ITabelaBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */ 
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_TABELA_REQUEST_NAME. */ 
	private static final String INQUIRY_TABELA_REQUEST_NAME = InquiryTabelaRequest.class.getSimpleName();

	/** The Constant TABELA_REQUEST_NAME. */ 
	private static final String TABELA_REQUEST_NAME = TabelaRequest.class.getSimpleName();
 
	/** The Constant TABELA_NAME. */ 
	private static final String TABELA_NAME = Tabela.class.getSimpleName();

	/** The Constant DEFAULT_TABELA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TABELA_BCF_EXCEPTION_MSG = "sensus.mlc.tabelabcfimpl.defaultexception";
 
	/** The Constant LOG. */ 
	private static final Logger LOG = LoggerFactory.getLogger(TabelaBCFImpl.class);
 
	/** The tabela bcl. */ 
	private ITabelaBCL tabelaBCL;
 
	/** The tabela validation controller. */ 
	private ValidationController tabelaValidationController;
  
	/** The tabela list validation controller. */ 
	private ValidationController tabelaListValidationController;

	/** The tabela request validation controller. */ 
	private ValidationController tabelaRequestValidationController;
 
	/** The light request validation controller. */ 
	private ValidationController lightValidationController;

	/** The light list validation controller. */ 
	private ValidationController lightListValidationController;
 
	/** 
	 * Gets the tabela bcl.
	 *  
	 * @return the tabela bcl  
	 */ 
	public ITabelaBCL getTabelaBCL()
	{ 
		return this.tabelaBCL;
	} 
  
	/**  
	 * Sets the tabela bcl.  
	 *   
	 * @param tabelaBCLObject the new tabela bcl  
	 */ 
	public void setTabelaBCL(ITabelaBCL tabelaBCLObject) 
	{
		this.tabelaBCL = tabelaBCLObject;
	} 
 
	/**  
	 * Gets the tabela validation controller.
	 * 
	 * @return the tabela validation controller  
	 */  
	public ValidationController getTabelaValidationController()  
	{
		return this.tabelaValidationController;
	}  
  
	/**   
	 * Sets the tabela validation controller.
	 * 
	 * @param tabelaValidationController the new tabela validation controller  
	 */    
	public void setTabelaValidationController(ValidationController tabelaValidationController)   
	{
		this.tabelaValidationController = tabelaValidationController;
	} 
  
	/** 
	 * Gets the tabela list validation controller. 
	 *  
	 * @return the tabela list validation controller  
	 */   
	public ValidationController getTabelaListValidationController()
	{  
		return this.tabelaListValidationController;
	}  
   
	/**   
	 * Sets the tabela list validation controller.  
	 *  
	 * @param tabelaListValidationController the new tabela list validation controller 
	 */ 
	public void setTabelaListValidationController(ValidationController tabelaListValidationController)
	{ 
		this.tabelaListValidationController = tabelaListValidationController;
	} 
     
	/**  
	 * Gets the tabela request validation controller. 
	 *
	 * @return the tabela request validation controller 
	 */
	public ValidationController getTabelaRequestValidationController() 
	{ 
		return this.tabelaRequestValidationController;
	} 
 
	/** 
	 * Sets the tabela request validation controller. 
	 *  
	 * @param tabelaRequestValidationController the new tabela request validation controller 
	 */ 
	public void setTabelaRequestValidationController(ValidationController tabelaRequestValidationController) 
	{
		this.tabelaRequestValidationController = tabelaRequestValidationController;
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
	 * @see com.sensus.mlc.tabela.bcf.ITabelaBCF#fetchAllTabelas(com.sensus.mlc.tabela.model.request.InquiryTabelaRequest
	 */ 
	@Override 
	public InquiryTabelaResponse fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest)
	{
		InquiryTabelaResponse response = new InquiryTabelaResponse();
		InternalResultsResponse<Tabela> internalResponse = null;
 
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_TABELA_REQUEST_NAME, inquiryTabelaRequest);
 
			if (getLightingControlRequestValidationController().validate(context)) 
			{
				internalResponse = getTabelaBCL().fetchAllTabela(inquiryTabelaRequest);
			}
   
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TABELA_BCF_EXCEPTION_MSG);
		}
  
		return response;
	} 
 
	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tabela.bcf.ITabelaBCF#fetchTabelaByName(com.sensus.mlc.tabela.model.request.TabelaRequest
	 */ 
	@Override 
	public TabelaResponse fetchTabelaById(TabelaRequest tabelaRequest)
	{  
		TabelaResponse response = new TabelaResponse();
		InternalResultsResponse<Tabela> internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),  
					FETCH_BY_NAME);
			context.putObjectToBeValidated(TABELA_REQUEST_NAME, tabelaRequest);
			context.putObjectToBeValidated(TABELA_NAME, tabelaRequest.getTabela());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getTabelaValidationController().validate(context)) 
			{   
				internalResponse = getTabelaBCL().fetchTabelaByName(tabelaRequest);
				response.setTabela(internalResponse.getResultsList());
			} 
    
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}   
		catch (Exception ex)  
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TABELA_BCF_EXCEPTION_MSG);
		}  
		return response;
	}  
  
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.tabela.bcf.ITabelaBCF#insertTabela(com.sensus.mlc.tabela.model.request.TabelaRequest)
	 */ 
	@Override 
	public TabelaResponse insertTabela(TabelaRequest tabelaRequest) 
	{ 
		TabelaResponse response = new TabelaResponse();
		InternalResultsResponse<Tabela> internalResponse = null;
		try  
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(TABELA_REQUEST_NAME, tabelaRequest);
			context.putObjectToBeValidated(TABELA_NAME, tabelaRequest.getTabela());
 
			if (getLightingControlRequestValidationController().validate(context)  
					&& getTabelaValidationController().validate(context))   
			{   


   
				if (getLightListValidationController().validate(context))
				{ 
					internalResponse = getTabelaBCL().insertTabela(tabelaRequest);
					response.setTabela(internalResponse.getResultsList());
				}  
			} 
      
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}  
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TABELA_BCF_EXCEPTION_MSG);
		} 
		return response;
	}  
    
	/*   
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.tabela.bcf.ITabelaBCF#deleteTabela(com.sensus.mlc.tabela.model.request.TabelaRequest)
	 */  
	@Override  
	public TabelaResponse deleteTabela(TabelaRequest tabelaRequest)
	{  
		TabelaResponse response = new TabelaResponse();
		InternalResponse internalResponse = null;
		try 
		{  
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), 
					DELETE);
			context.putObjectToBeValidated(TABELA_REQUEST_NAME, tabelaRequest);
			context.putObjectToBeValidated(TABELA_NAME, tabelaRequest.getTabela());

			if (getLightingControlRequestValidationController().validate(context) 
					&& getTabelaValidationController().validate(context))
			{
				internalResponse = getTabelaBCL().deleteTabela(tabelaRequest);
			} 

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TABELA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tabela.bcf.ITabelaBCF#updateTabela(com.sensus.mlc.tabela.model.request.TabelaRequest
	 */
	@Override 
	public TabelaResponse updateTabela(TabelaRequest tabelaRequest)
	{ 
		TabelaResponse response = new TabelaResponse();
		InternalResponse internalResponse = null;
		try 
		{ 
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(TABELA_REQUEST_NAME, tabelaRequest);
			context.putObjectToBeValidated(TABELA_NAME, tabelaRequest.getTabela());
 
			if (getLightingControlRequestValidationController().validate(context) && 
					getTabelaRequestValidationController().validate(context) && 
					getTabelaValidationController().validate(context))
			{ 
				internalResponse = getTabelaBCL().updateTabela(tabelaRequest);
			}
 
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex) 
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TABELA_BCF_EXCEPTION_MSG);
		} 

		return response;
	}
}
