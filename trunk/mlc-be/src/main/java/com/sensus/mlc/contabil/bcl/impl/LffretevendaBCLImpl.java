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
import com.sensus.mlc.lffretevenda.bcl.ILffretevendaBCL;
import com.sensus.mlc.lffretevenda.dac.ILffretevendaDAC;
import com.sensus.mlc.lffretevenda.model.Lffretevenda;
import com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest;

/** 
 * The Class LffretevendaBCLImpl. 
 */ 
public class LffretevendaBCLImpl implements ILffretevendaBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lffretevenda dac. */ 
	private ILffretevendaDAC lffretevendaDAC;



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
	 * Spring Sets the lffretevenda dac. 
	 *  
	 * @param iLffretevendaDAC the new lffretevenda dac 
	 */ 
	public void setLffretevendaDAC(ILffretevendaDAC iLffretevendaDAC)
	{ 
		lffretevendaDAC = iLffretevendaDAC;
	} 

	/** 
	 * Gets the lffretevenda dac. 
	 * 
	 * @return the lffretevenda dac 
	 */ 
	public ILffretevendaDAC getLffretevendaDAC()
	{
		return lffretevendaDAC;
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
	 * @see com.sensus.mlc.lffretevenda.bcl.ILffretevendaBCL#fetchAllLffretevendas(InquiryLffretevendaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lffretevenda> fetchAllLffretevenda(InquiryLffretevendaRequest inquiryLffretevendaRequest)
	{
		return getLffretevendaDAC().fetchAllLffretevenda(inquiryLffretevendaRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffretevenda.bcl.ILffretevendaBCL#fetchLffretevendaById(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lffretevenda> fetchLffretevendaById(LffretevendaRequest lffretevendaRequest)
	{ 
		return getLffretevendaDAC().fetchLffretevendaById(lffretevendaRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretevenda.bcl.ILffretevendaBCL#insertLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lffretevenda> insertLffretevenda(LffretevendaRequest lffretevendaRequest) 
	{  

		InternalResultsResponse<Lffretevenda> response = getLffretevendaDAC().insertLffretevenda(lffretevendaRequest);

		if (!response.isInError())
		{  
			Lffretevenda lffretevenda = response.getFirstResult();
			lffretevendaRequest.setLffretevenda(lffretevenda);

			SearchParameter lffretevendaParameter = new SearchParameter(PropertyEnum.LFFRETEVENDA_ID, String.valueOf(lffretevenda.getId()));
			lffretevendaRequest.getSearchLight().addSearchParameter(lffretevendaParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lffretevendaRequest, LCActionTypeEnum.INSERT_LFFRETEVENDA, null);
			lffretevendaRequest.getSearchLight().getSearchParameters().remove(lffretevendaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretevenda.bcl.ILffretevendaBCL#deleteLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)
	 */ 
	@Override 
	public InternalResponse deleteLffretevenda(LffretevendaRequest lffretevendaRequest)
	{
		InternalResultsResponse<Lffretevenda> lffretevendaResponse = getLffretevendaDAC().fetchLffretevendaById(lffretevendaRequest);
		InternalResponse response = new InternalResponse();

		if (lffretevendaResponse.isInError()) 
		{
			response.setStatus(lffretevendaResponse.getStatus());
			response.addMessages(lffretevendaResponse.getMessageInfoList());
			return response;
		} 

		response = getLffretevendaDAC().deleteLffretevenda(lffretevendaRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lffretevenda lffretevenda = lffretevendaResponse.getFirstResult();
		lffretevendaRequest.setLffretevenda(lffretevenda);

		SearchParameter lffretevendaParameter = new SearchParameter(PropertyEnum.LFFRETEVENDA_ID, String.valueOf(lffretevenda.getId()));
		lffretevendaRequest.getSearchLight().addSearchParameter(lffretevendaParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lffretevendaRequest, LCActionTypeEnum.DELETE_TAG, null);
		lffretevendaRequest.getSearchLight().getSearchParameters().remove(lffretevendaParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretevenda.bcl.ILffretevendaBCL#updateLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lffretevenda> updateLffretevenda(LffretevendaRequest lffretevendaRequest)
	{ 
		InternalResultsResponse<Lffretevenda> response = getLffretevendaDAC().updateLffretevenda(lffretevendaRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lffretevendaRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lffretevendaRequest the lffretevenda request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LffretevendaRequest lffretevendaRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lffretevendaRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lffretevendaRequest the lffretevenda request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LffretevendaRequest lffretevendaRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lffretevendaRequest.getLffretevenda()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lffretevenda lffretevenda = lffretevendaRequest.getLffretevenda();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lffretevenda.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lffretevenda.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lffretevendaRequest);
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

		ProcessRequest processRequest = createProcessRequest(lffretevendaRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
