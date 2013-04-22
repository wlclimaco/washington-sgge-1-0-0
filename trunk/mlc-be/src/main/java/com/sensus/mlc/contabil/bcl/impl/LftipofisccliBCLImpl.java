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
import com.sensus.mlc.lftipofisccli.bcl.ILftipofisccliBCL;
import com.sensus.mlc.lftipofisccli.dac.ILftipofisccliDAC;
import com.sensus.mlc.lftipofisccli.model.Lftipofisccli;
import com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest;

/** 
 * The Class LftipofisccliBCLImpl. 
 */ 
public class LftipofisccliBCLImpl implements ILftipofisccliBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lftipofisccli dac. */ 
	private ILftipofisccliDAC lftipofisccliDAC;



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
	 * Spring Sets the lftipofisccli dac. 
	 *  
	 * @param iLftipofisccliDAC the new lftipofisccli dac 
	 */ 
	public void setLftipofisccliDAC(ILftipofisccliDAC iLftipofisccliDAC)
	{ 
		lftipofisccliDAC = iLftipofisccliDAC;
	} 

	/** 
	 * Gets the lftipofisccli dac. 
	 * 
	 * @return the lftipofisccli dac 
	 */ 
	public ILftipofisccliDAC getLftipofisccliDAC()
	{
		return lftipofisccliDAC;
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
	 * @see com.sensus.mlc.lftipofisccli.bcl.ILftipofisccliBCL#fetchAllLftipofiscclis(InquiryLftipofisccliRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lftipofisccli> fetchAllLftipofisccli(InquiryLftipofisccliRequest inquiryLftipofisccliRequest)
	{
		return getLftipofisccliDAC().fetchAllLftipofisccli(inquiryLftipofisccliRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftipofisccli.bcl.ILftipofisccliBCL#fetchLftipofisccliById(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lftipofisccli> fetchLftipofisccliById(LftipofisccliRequest lftipofisccliRequest)
	{ 
		return getLftipofisccliDAC().fetchLftipofisccliById(lftipofisccliRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftipofisccli.bcl.ILftipofisccliBCL#insertLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lftipofisccli> insertLftipofisccli(LftipofisccliRequest lftipofisccliRequest) 
	{  

		InternalResultsResponse<Lftipofisccli> response = getLftipofisccliDAC().insertLftipofisccli(lftipofisccliRequest);

		if (!response.isInError())
		{  
			Lftipofisccli lftipofisccli = response.getFirstResult();
			lftipofisccliRequest.setLftipofisccli(lftipofisccli);

			SearchParameter lftipofisccliParameter = new SearchParameter(PropertyEnum.LFTIPOFISCCLI_ID, String.valueOf(lftipofisccli.getId()));
			lftipofisccliRequest.getSearchLight().addSearchParameter(lftipofisccliParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lftipofisccliRequest, LCActionTypeEnum.INSERT_LFTIPOFISCCLI, null);
			lftipofisccliRequest.getSearchLight().getSearchParameters().remove(lftipofisccliParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftipofisccli.bcl.ILftipofisccliBCL#deleteLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)
	 */ 
	@Override 
	public InternalResponse deleteLftipofisccli(LftipofisccliRequest lftipofisccliRequest)
	{
		InternalResultsResponse<Lftipofisccli> lftipofisccliResponse = getLftipofisccliDAC().fetchLftipofisccliById(lftipofisccliRequest);
		InternalResponse response = new InternalResponse();

		if (lftipofisccliResponse.isInError()) 
		{
			response.setStatus(lftipofisccliResponse.getStatus());
			response.addMessages(lftipofisccliResponse.getMessageInfoList());
			return response;
		} 

		response = getLftipofisccliDAC().deleteLftipofisccli(lftipofisccliRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lftipofisccli lftipofisccli = lftipofisccliResponse.getFirstResult();
		lftipofisccliRequest.setLftipofisccli(lftipofisccli);

		SearchParameter lftipofisccliParameter = new SearchParameter(PropertyEnum.LFTIPOFISCCLI_ID, String.valueOf(lftipofisccli.getId()));
		lftipofisccliRequest.getSearchLight().addSearchParameter(lftipofisccliParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lftipofisccliRequest, LCActionTypeEnum.DELETE_TAG, null);
		lftipofisccliRequest.getSearchLight().getSearchParameters().remove(lftipofisccliParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftipofisccli.bcl.ILftipofisccliBCL#updateLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lftipofisccli> updateLftipofisccli(LftipofisccliRequest lftipofisccliRequest)
	{ 
		InternalResultsResponse<Lftipofisccli> response = getLftipofisccliDAC().updateLftipofisccli(lftipofisccliRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lftipofisccliRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lftipofisccliRequest the lftipofisccli request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LftipofisccliRequest lftipofisccliRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lftipofisccliRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lftipofisccliRequest the lftipofisccli request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LftipofisccliRequest lftipofisccliRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lftipofisccliRequest.getLftipofisccli()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lftipofisccli lftipofisccli = lftipofisccliRequest.getLftipofisccli();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lftipofisccli.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lftipofisccli.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lftipofisccliRequest);
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

		ProcessRequest processRequest = createProcessRequest(lftipofisccliRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
