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
import com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL;
import com.sensus.mlc.chaveestrangeira.dac.IChaveestrangeiraDAC;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira;
import com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest;

/** 
 * The Class ChaveestrangeiraBCLImpl. 
 */ 
public class ChaveestrangeiraBCLImpl implements IChaveestrangeiraBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The chaveestrangeira dac. */ 
	private IChaveestrangeiraDAC chaveestrangeiraDAC;



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
	 * Spring Sets the chaveestrangeira dac. 
	 *  
	 * @param iChaveestrangeiraDAC the new chaveestrangeira dac 
	 */ 
	public void setChaveestrangeiraDAC(IChaveestrangeiraDAC iChaveestrangeiraDAC)
	{ 
		chaveestrangeiraDAC = iChaveestrangeiraDAC;
	} 

	/** 
	 * Gets the chaveestrangeira dac. 
	 * 
	 * @return the chaveestrangeira dac 
	 */ 
	public IChaveestrangeiraDAC getChaveestrangeiraDAC()
	{
		return chaveestrangeiraDAC;
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
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#fetchAllChaveestrangeiras(InquiryChaveestrangeiraRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Chaveestrangeira> fetchAllChaveestrangeira(InquiryChaveestrangeiraRequest inquiryChaveestrangeiraRequest)
	{
		return getChaveestrangeiraDAC().fetchAllChaveestrangeira(inquiryChaveestrangeiraRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#fetchChaveestrangeiraById(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{ 
		return getChaveestrangeiraDAC().fetchChaveestrangeiraById(chaveestrangeiraRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#insertChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Chaveestrangeira> insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{  

		InternalResultsResponse<Chaveestrangeira> response = getChaveestrangeiraDAC().insertChaveestrangeira(chaveestrangeiraRequest);

		if (!response.isInError())
		{  
			Chaveestrangeira chaveestrangeira = response.getFirstResult();
			chaveestrangeiraRequest.setChaveestrangeira(chaveestrangeira);

			SearchParameter chaveestrangeiraParameter = new SearchParameter(PropertyEnum.CHAVEESTRANGEIRA_ID, String.valueOf(chaveestrangeira.getId()));
			chaveestrangeiraRequest.getSearchLight().addSearchParameter(chaveestrangeiraParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(chaveestrangeiraRequest, LCActionTypeEnum.INSERT_CHAVEESTRANGEIRA, null);
			chaveestrangeiraRequest.getSearchLight().getSearchParameters().remove(chaveestrangeiraParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#deleteChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override 
	public InternalResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{
		InternalResultsResponse<Chaveestrangeira> chaveestrangeiraResponse = getChaveestrangeiraDAC().fetchChaveestrangeiraById(chaveestrangeiraRequest);
		InternalResponse response = new InternalResponse();

		if (chaveestrangeiraResponse.isInError()) 
		{
			response.setStatus(chaveestrangeiraResponse.getStatus());
			response.addMessages(chaveestrangeiraResponse.getMessageInfoList());
			return response;
		} 

		response = getChaveestrangeiraDAC().deleteChaveestrangeira(chaveestrangeiraRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Chaveestrangeira chaveestrangeira = chaveestrangeiraResponse.getFirstResult();
		chaveestrangeiraRequest.setChaveestrangeira(chaveestrangeira);

		SearchParameter chaveestrangeiraParameter = new SearchParameter(PropertyEnum.CHAVEESTRANGEIRA_ID, String.valueOf(chaveestrangeira.getId()));
		chaveestrangeiraRequest.getSearchLight().addSearchParameter(chaveestrangeiraParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(chaveestrangeiraRequest, LCActionTypeEnum.DELETE_TAG, null);
		chaveestrangeiraRequest.getSearchLight().getSearchParameters().remove(chaveestrangeiraParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#updateChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Chaveestrangeira> updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{ 
		InternalResultsResponse<Chaveestrangeira> response = getChaveestrangeiraDAC().updateChaveestrangeira(chaveestrangeiraRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(chaveestrangeiraRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param chaveestrangeiraRequest the chaveestrangeira request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(ChaveestrangeiraRequest chaveestrangeiraRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(chaveestrangeiraRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param chaveestrangeiraRequest the chaveestrangeira request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(ChaveestrangeiraRequest chaveestrangeiraRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(chaveestrangeiraRequest.getChaveestrangeira()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Chaveestrangeira chaveestrangeira = chaveestrangeiraRequest.getChaveestrangeira();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(chaveestrangeira.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, chaveestrangeira.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(chaveestrangeiraRequest);
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

		ProcessRequest processRequest = createProcessRequest(chaveestrangeiraRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
