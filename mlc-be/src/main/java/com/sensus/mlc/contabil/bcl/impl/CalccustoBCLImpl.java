package com.sensus.mlc.contabil.bcl.impl;

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
import com.sensus.mlc.calccusto.bcl.ICalccustoBCL;
import com.sensus.mlc.calccusto.dac.ICalccustoDAC;
import com.sensus.mlc.calccusto.model.Calccusto;
import com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest;
import com.sensus.mlc.calccusto.model.request.CalccustoRequest;

/** 
 * The Class CalccustoBCLImpl. 
 */ 
public class CalccustoBCLImpl implements ICalccustoBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The calccusto dac. */ 
	private ICalccustoDAC calccustoDAC;



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
	 * Spring Sets the calccusto dac. 
	 *  
	 * @param iCalccustoDAC the new calccusto dac 
	 */ 
	public void setCalccustoDAC(ICalccustoDAC iCalccustoDAC)
	{ 
		calccustoDAC = iCalccustoDAC;
	} 

	/** 
	 * Gets the calccusto dac. 
	 * 
	 * @return the calccusto dac 
	 */ 
	public ICalccustoDAC getCalccustoDAC()
	{
		return calccustoDAC;
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
	 * @see com.sensus.mlc.calccusto.bcl.ICalccustoBCL#fetchAllCalccustos(InquiryCalccustoRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Calccusto> fetchAllCalccusto(InquiryCalccustoRequest inquiryCalccustoRequest)
	{
		return getCalccustoDAC().fetchAllCalccusto(inquiryCalccustoRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.calccusto.bcl.ICalccustoBCL#fetchCalccustoById(com.sensus.mlc.calccusto.model.request.CalccustoRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Calccusto> fetchCalccustoById(CalccustoRequest calccustoRequest)
	{ 
		return getCalccustoDAC().fetchCalccustoById(calccustoRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.calccusto.bcl.ICalccustoBCL#insertCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Calccusto> insertCalccusto(CalccustoRequest calccustoRequest) 
	{  

		InternalResultsResponse<Calccusto> response = getCalccustoDAC().insertCalccusto(calccustoRequest);

		if (!response.isInError())
		{  
			Calccusto calccusto = response.getFirstResult();
			calccustoRequest.setCalccusto(calccusto);

			SearchParameter calccustoParameter = new SearchParameter(PropertyEnum.CALCCUSTO_ID, String.valueOf(calccusto.getId()));
			calccustoRequest.getSearchLight().addSearchParameter(calccustoParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(calccustoRequest, LCActionTypeEnum.INSERT_CALCCUSTO, null);
			calccustoRequest.getSearchLight().getSearchParameters().remove(calccustoParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.calccusto.bcl.ICalccustoBCL#deleteCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest)
	 */ 
	@Override 
	public InternalResponse deleteCalccusto(CalccustoRequest calccustoRequest)
	{
		InternalResultsResponse<Calccusto> calccustoResponse = getCalccustoDAC().fetchCalccustoById(calccustoRequest);
		InternalResponse response = new InternalResponse();

		if (calccustoResponse.isInError()) 
		{
			response.setStatus(calccustoResponse.getStatus());
			response.addMessages(calccustoResponse.getMessageInfoList());
			return response;
		} 

		response = getCalccustoDAC().deleteCalccusto(calccustoRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Calccusto calccusto = calccustoResponse.getFirstResult();
		calccustoRequest.setCalccusto(calccusto);

		SearchParameter calccustoParameter = new SearchParameter(PropertyEnum.CALCCUSTO_ID, String.valueOf(calccusto.getId()));
		calccustoRequest.getSearchLight().addSearchParameter(calccustoParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(calccustoRequest, LCActionTypeEnum.DELETE_TAG, null);
		calccustoRequest.getSearchLight().getSearchParameters().remove(calccustoParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.calccusto.bcl.ICalccustoBCL#updateCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Calccusto> updateCalccusto(CalccustoRequest calccustoRequest)
	{ 
		InternalResultsResponse<Calccusto> response = getCalccustoDAC().updateCalccusto(calccustoRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(calccustoRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param calccustoRequest the calccusto request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(CalccustoRequest calccustoRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(calccustoRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param calccustoRequest the calccusto request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(CalccustoRequest calccustoRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(calccustoRequest.getCalccusto()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Calccusto calccusto = calccustoRequest.getCalccusto();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(calccusto.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, calccusto.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(calccustoRequest);
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

		ProcessRequest processRequest = createProcessRequest(calccustoRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
