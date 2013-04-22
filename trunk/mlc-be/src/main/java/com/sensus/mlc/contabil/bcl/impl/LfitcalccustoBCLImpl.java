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
import com.sensus.mlc.lfitcalccusto.bcl.ILfitcalccustoBCL;
import com.sensus.mlc.lfitcalccusto.dac.ILfitcalccustoDAC;
import com.sensus.mlc.lfitcalccusto.model.Lfitcalccusto;
import com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest;

/** 
 * The Class LfitcalccustoBCLImpl. 
 */ 
public class LfitcalccustoBCLImpl implements ILfitcalccustoBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfitcalccusto dac. */ 
	private ILfitcalccustoDAC lfitcalccustoDAC;



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
	 * Spring Sets the lfitcalccusto dac. 
	 *  
	 * @param iLfitcalccustoDAC the new lfitcalccusto dac 
	 */ 
	public void setLfitcalccustoDAC(ILfitcalccustoDAC iLfitcalccustoDAC)
	{ 
		lfitcalccustoDAC = iLfitcalccustoDAC;
	} 

	/** 
	 * Gets the lfitcalccusto dac. 
	 * 
	 * @return the lfitcalccusto dac 
	 */ 
	public ILfitcalccustoDAC getLfitcalccustoDAC()
	{
		return lfitcalccustoDAC;
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
	 * @see com.sensus.mlc.lfitcalccusto.bcl.ILfitcalccustoBCL#fetchAllLfitcalccustos(InquiryLfitcalccustoRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcalccusto> fetchAllLfitcalccusto(InquiryLfitcalccustoRequest inquiryLfitcalccustoRequest)
	{
		return getLfitcalccustoDAC().fetchAllLfitcalccusto(inquiryLfitcalccustoRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitcalccusto.bcl.ILfitcalccustoBCL#fetchLfitcalccustoById(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfitcalccusto> fetchLfitcalccustoById(LfitcalccustoRequest lfitcalccustoRequest)
	{ 
		return getLfitcalccustoDAC().fetchLfitcalccustoById(lfitcalccustoRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcalccusto.bcl.ILfitcalccustoBCL#insertLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcalccusto> insertLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest) 
	{  

		InternalResultsResponse<Lfitcalccusto> response = getLfitcalccustoDAC().insertLfitcalccusto(lfitcalccustoRequest);

		if (!response.isInError())
		{  
			Lfitcalccusto lfitcalccusto = response.getFirstResult();
			lfitcalccustoRequest.setLfitcalccusto(lfitcalccusto);

			SearchParameter lfitcalccustoParameter = new SearchParameter(PropertyEnum.LFITCALCCUSTO_ID, String.valueOf(lfitcalccusto.getId()));
			lfitcalccustoRequest.getSearchLight().addSearchParameter(lfitcalccustoParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfitcalccustoRequest, LCActionTypeEnum.INSERT_LFITCALCCUSTO, null);
			lfitcalccustoRequest.getSearchLight().getSearchParameters().remove(lfitcalccustoParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcalccusto.bcl.ILfitcalccustoBCL#deleteLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest)
	{
		InternalResultsResponse<Lfitcalccusto> lfitcalccustoResponse = getLfitcalccustoDAC().fetchLfitcalccustoById(lfitcalccustoRequest);
		InternalResponse response = new InternalResponse();

		if (lfitcalccustoResponse.isInError()) 
		{
			response.setStatus(lfitcalccustoResponse.getStatus());
			response.addMessages(lfitcalccustoResponse.getMessageInfoList());
			return response;
		} 

		response = getLfitcalccustoDAC().deleteLfitcalccusto(lfitcalccustoRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfitcalccusto lfitcalccusto = lfitcalccustoResponse.getFirstResult();
		lfitcalccustoRequest.setLfitcalccusto(lfitcalccusto);

		SearchParameter lfitcalccustoParameter = new SearchParameter(PropertyEnum.LFITCALCCUSTO_ID, String.valueOf(lfitcalccusto.getId()));
		lfitcalccustoRequest.getSearchLight().addSearchParameter(lfitcalccustoParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfitcalccustoRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfitcalccustoRequest.getSearchLight().getSearchParameters().remove(lfitcalccustoParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcalccusto.bcl.ILfitcalccustoBCL#updateLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcalccusto> updateLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest)
	{ 
		InternalResultsResponse<Lfitcalccusto> response = getLfitcalccustoDAC().updateLfitcalccusto(lfitcalccustoRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfitcalccustoRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfitcalccustoRequest the lfitcalccusto request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitcalccustoRequest lfitcalccustoRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfitcalccustoRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfitcalccustoRequest the lfitcalccusto request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitcalccustoRequest lfitcalccustoRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfitcalccustoRequest.getLfitcalccusto()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfitcalccusto lfitcalccusto = lfitcalccustoRequest.getLfitcalccusto();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfitcalccusto.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfitcalccusto.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfitcalccustoRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfitcalccustoRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
