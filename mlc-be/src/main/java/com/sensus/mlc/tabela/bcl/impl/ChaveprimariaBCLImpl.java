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
import com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL;
import com.sensus.mlc.chaveprimaria.dac.IChaveprimariaDAC;
import com.sensus.mlc.chaveprimaria.model.Chaveprimaria;
import com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest;

/** 
 * The Class ChaveprimariaBCLImpl. 
 */ 
public class ChaveprimariaBCLImpl implements IChaveprimariaBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The chaveprimaria dac. */ 
	private IChaveprimariaDAC chaveprimariaDAC;



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
	 * Spring Sets the chaveprimaria dac. 
	 *  
	 * @param iChaveprimariaDAC the new chaveprimaria dac 
	 */ 
	public void setChaveprimariaDAC(IChaveprimariaDAC iChaveprimariaDAC)
	{ 
		chaveprimariaDAC = iChaveprimariaDAC;
	} 

	/** 
	 * Gets the chaveprimaria dac. 
	 * 
	 * @return the chaveprimaria dac 
	 */ 
	public IChaveprimariaDAC getChaveprimariaDAC()
	{
		return chaveprimariaDAC;
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
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#fetchAllChaveprimarias(InquiryChaveprimariaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Chaveprimaria> fetchAllChaveprimaria(InquiryChaveprimariaRequest inquiryChaveprimariaRequest)
	{
		return getChaveprimariaDAC().fetchAllChaveprimaria(inquiryChaveprimariaRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#fetchChaveprimariaById(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Chaveprimaria> fetchChaveprimariaById(ChaveprimariaRequest chaveprimariaRequest)
	{ 
		return getChaveprimariaDAC().fetchChaveprimariaById(chaveprimariaRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#insertChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Chaveprimaria> insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest) 
	{  

		InternalResultsResponse<Chaveprimaria> response = getChaveprimariaDAC().insertChaveprimaria(chaveprimariaRequest);

		if (!response.isInError())
		{  
			Chaveprimaria chaveprimaria = response.getFirstResult();
			chaveprimariaRequest.setChaveprimaria(chaveprimaria);

			SearchParameter chaveprimariaParameter = new SearchParameter(PropertyEnum.CHAVEPRIMARIA_ID, String.valueOf(chaveprimaria.getId()));
			chaveprimariaRequest.getSearchLight().addSearchParameter(chaveprimariaParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(chaveprimariaRequest, LCActionTypeEnum.INSERT_CHAVEPRIMARIA, null);
			chaveprimariaRequest.getSearchLight().getSearchParameters().remove(chaveprimariaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#deleteChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)
	 */ 
	@Override 
	public InternalResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{
		InternalResultsResponse<Chaveprimaria> chaveprimariaResponse = getChaveprimariaDAC().fetchChaveprimariaById(chaveprimariaRequest);
		InternalResponse response = new InternalResponse();

		if (chaveprimariaResponse.isInError()) 
		{
			response.setStatus(chaveprimariaResponse.getStatus());
			response.addMessages(chaveprimariaResponse.getMessageInfoList());
			return response;
		} 

		response = getChaveprimariaDAC().deleteChaveprimaria(chaveprimariaRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Chaveprimaria chaveprimaria = chaveprimariaResponse.getFirstResult();
		chaveprimariaRequest.setChaveprimaria(chaveprimaria);

		SearchParameter chaveprimariaParameter = new SearchParameter(PropertyEnum.CHAVEPRIMARIA_ID, String.valueOf(chaveprimaria.getId()));
		chaveprimariaRequest.getSearchLight().addSearchParameter(chaveprimariaParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(chaveprimariaRequest, LCActionTypeEnum.DELETE_TAG, null);
		chaveprimariaRequest.getSearchLight().getSearchParameters().remove(chaveprimariaParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#updateChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Chaveprimaria> updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{ 
		InternalResultsResponse<Empresa> response = getChaveprimariaDAC().updateChaveprimaria(chaveprimariaRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(chaveprimariaRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param chaveprimariaRequest the chaveprimaria request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(ChaveprimariaRequest chaveprimariaRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(chaveprimariaRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param chaveprimariaRequest the chaveprimaria request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(ChaveprimariaRequest chaveprimariaRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(chaveprimariaRequest.getChaveprimaria()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Chaveprimaria chaveprimaria = chaveprimariaRequest.getChaveprimaria();

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

		ProcessRequest processRequest = createProcessRequest(chaveprimariaRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
