package com.sensus.mlc.tabela.bcl.impl;

import static com.sensus.mlc.base.util.LCActionUtil.createMessageInfoNone;
import static com.sensus.mlc.base.util.LCActionUtil.createMessageWarningOther;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;
import static com.sensus.mlc.base.util.LCPropertiesExtractorUtil.extractLightId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.dominios.bcl.IDominiosBCL;
import com.sensus.mlc.dominios.dac.IDominiosDAC;
import com.sensus.mlc.dominios.model.Dominios;
import com.sensus.mlc.dominios.model.request.InquiryDominiosRequest;
import com.sensus.mlc.dominios.model.request.DominiosRequest;

/** 
 * The Class DominiosBCLImpl. 
 */ 
public class DominiosBCLImpl implements IDominiosBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The dominios dac. */ 
	private IDominiosDAC dominiosDAC;



	/**  The process bcl. */ 
	private IProcessBCL processBCL;

	/**  The lc help. */ 
	private LCHelp lcHelp;


	/** 
	 * Gets the lc help.
	 * 
	 * @return the lc help 
	 */ 
	public LCHelp getLcHelp() 
	{ 
		return lcHelp;
	} 

	/** 
	 * Sets the lc help.
	 *
	 * @param lcHelp the new lc help  
	 */ 
	public void setLcHelp(LCHelp lcHelp) 
	{ 
		this.lcHelp = lcHelp;
	} 

	/** 
	 * Spring Sets the dominios dac. 
	 *  
	 * @param iDominiosDAC the new dominios dac 
	 */ 
	public void setDominiosDAC(IDominiosDAC iDominiosDAC)
	{ 
		dominiosDAC = iDominiosDAC;
	} 

	/** 
	 * Gets the dominios dac. 
	 * 
	 * @return the dominios dac 
	 */ 
	public IDominiosDAC getDominiosDAC()
	{
		return dominiosDAC;
	}

	/** 
	 * Gets the process bcl.
	 * 
	 * @return the process bcl 
	 */ 
	public IProcessBCL getProcessBCL() 
	{
		return processBCL;
	}

	/** 
	 * Sets the process bcl.
	 *  
	 * @param processBCL the new process bcl  
	 */ 
	public void setProcessBCL(IProcessBCL processBCL)
	{ 
		this.processBCL = processBCL;
	} 
	/*  
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#fetchAllDominioss(InquiryDominiosRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Dominios> fetchAllDominios(InquiryDominiosRequest inquiryDominiosRequest)
	{
		return getDominiosDAC().fetchAllDominios(inquiryDominiosRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#fetchDominiosById(com.sensus.mlc.dominios.model.request.DominiosRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Dominios> fetchDominiosById(DominiosRequest dominiosRequest)
	{ 
		return getDominiosDAC().fetchDominiosById(dominiosRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#insertDominios(com.sensus.mlc.dominios.model.request.DominiosRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Dominios> insertDominios(DominiosRequest dominiosRequest) 
	{  

		InternalResultsResponse<Dominios> response = getDominiosDAC().insertDominios(dominiosRequest);

		if (!response.isInError())
		{  
			Dominios dominios = response.getFirstResult();
			dominiosRequest.setDominios(dominios);

			SearchParameter dominiosParameter = new SearchParameter(PropertyEnum.DOMINIOS_ID, String.valueOf(dominios.getId()));
			dominiosRequest.getSearchLight().addSearchParameter(dominiosParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(dominiosRequest, LCActionTypeEnum.INSERT_DOMINIOS, null);
			dominiosRequest.getSearchLight().getSearchParameters().remove(dominiosParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#deleteDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override 
	public InternalResponse deleteDominios(DominiosRequest dominiosRequest)
	{
		InternalResultsResponse<Dominios> dominiosResponse = getDominiosDAC().fetchDominiosById(dominiosRequest);
		InternalResponse response = new InternalResponse();

		if (dominiosResponse.isInError()) 
		{
			response.setStatus(dominiosResponse.getStatus());
			response.addMessages(dominiosResponse.getMessageInfoList());
			return response;
		} 

		response = getDominiosDAC().deleteDominios(dominiosRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Dominios dominios = dominiosResponse.getFirstResult();
		dominiosRequest.setDominios(dominios);

		SearchParameter dominiosParameter = new SearchParameter(PropertyEnum.DOMINIOS_ID, String.valueOf(dominios.getId()));
		dominiosRequest.getSearchLight().addSearchParameter(dominiosParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(dominiosRequest, LCActionTypeEnum.DELETE_TAG, null);
		dominiosRequest.getSearchLight().getSearchParameters().remove(dominiosParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#updateDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Dominios> updateDominios(DominiosRequest dominiosRequest)
	{ 
		InternalResultsResponse<Empresa> response = getDominiosDAC().updateDominios(dominiosRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(dominiosRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param dominiosRequest the dominios request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(DominiosRequest dominiosRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(dominiosRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param dominiosRequest the dominios request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(DominiosRequest dominiosRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(dominiosRequest.getDominios()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Dominios dominios = dominiosRequest.getDominios();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, empresa.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(empresaRequest);
   Integer lightAmount = 1;

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = LCHelp.generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		if (!ValidationUtil.isNullOrEmpty(processDescription)) 
		{
			process.setDescription(processDescription);
		} 

		ProcessRequest processRequest = createProcessRequest(dominiosRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
