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
import com.sensus.mlc.lfmoddocfisc.bcl.ILfmoddocfiscBCL;
import com.sensus.mlc.lfmoddocfisc.dac.ILfmoddocfiscDAC;
import com.sensus.mlc.lfmoddocfisc.model.Lfmoddocfisc;
import com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest;

/** 
 * The Class LfmoddocfiscBCLImpl. 
 */ 
public class LfmoddocfiscBCLImpl implements ILfmoddocfiscBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfmoddocfisc dac. */ 
	private ILfmoddocfiscDAC lfmoddocfiscDAC;



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
	 * Spring Sets the lfmoddocfisc dac. 
	 *  
	 * @param iLfmoddocfiscDAC the new lfmoddocfisc dac 
	 */ 
	public void setLfmoddocfiscDAC(ILfmoddocfiscDAC iLfmoddocfiscDAC)
	{ 
		lfmoddocfiscDAC = iLfmoddocfiscDAC;
	} 

	/** 
	 * Gets the lfmoddocfisc dac. 
	 * 
	 * @return the lfmoddocfisc dac 
	 */ 
	public ILfmoddocfiscDAC getLfmoddocfiscDAC()
	{
		return lfmoddocfiscDAC;
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
	 * @see com.sensus.mlc.lfmoddocfisc.bcl.ILfmoddocfiscBCL#fetchAllLfmoddocfiscs(InquiryLfmoddocfiscRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfmoddocfisc> fetchAllLfmoddocfisc(InquiryLfmoddocfiscRequest inquiryLfmoddocfiscRequest)
	{
		return getLfmoddocfiscDAC().fetchAllLfmoddocfisc(inquiryLfmoddocfiscRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmoddocfisc.bcl.ILfmoddocfiscBCL#fetchLfmoddocfiscById(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfmoddocfisc> fetchLfmoddocfiscById(LfmoddocfiscRequest lfmoddocfiscRequest)
	{ 
		return getLfmoddocfiscDAC().fetchLfmoddocfiscById(lfmoddocfiscRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmoddocfisc.bcl.ILfmoddocfiscBCL#insertLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfmoddocfisc> insertLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest) 
	{  

		InternalResultsResponse<Lfmoddocfisc> response = getLfmoddocfiscDAC().insertLfmoddocfisc(lfmoddocfiscRequest);

		if (!response.isInError())
		{  
			Lfmoddocfisc lfmoddocfisc = response.getFirstResult();
			lfmoddocfiscRequest.setLfmoddocfisc(lfmoddocfisc);

			SearchParameter lfmoddocfiscParameter = new SearchParameter(PropertyEnum.LFMODDOCFISC_ID, String.valueOf(lfmoddocfisc.getId()));
			lfmoddocfiscRequest.getSearchLight().addSearchParameter(lfmoddocfiscParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfmoddocfiscRequest, LCActionTypeEnum.INSERT_LFMODDOCFISC, null);
			lfmoddocfiscRequest.getSearchLight().getSearchParameters().remove(lfmoddocfiscParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmoddocfisc.bcl.ILfmoddocfiscBCL#deleteLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest)
	{
		InternalResultsResponse<Lfmoddocfisc> lfmoddocfiscResponse = getLfmoddocfiscDAC().fetchLfmoddocfiscById(lfmoddocfiscRequest);
		InternalResponse response = new InternalResponse();

		if (lfmoddocfiscResponse.isInError()) 
		{
			response.setStatus(lfmoddocfiscResponse.getStatus());
			response.addMessages(lfmoddocfiscResponse.getMessageInfoList());
			return response;
		} 

		response = getLfmoddocfiscDAC().deleteLfmoddocfisc(lfmoddocfiscRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfmoddocfisc lfmoddocfisc = lfmoddocfiscResponse.getFirstResult();
		lfmoddocfiscRequest.setLfmoddocfisc(lfmoddocfisc);

		SearchParameter lfmoddocfiscParameter = new SearchParameter(PropertyEnum.LFMODDOCFISC_ID, String.valueOf(lfmoddocfisc.getId()));
		lfmoddocfiscRequest.getSearchLight().addSearchParameter(lfmoddocfiscParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfmoddocfiscRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfmoddocfiscRequest.getSearchLight().getSearchParameters().remove(lfmoddocfiscParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmoddocfisc.bcl.ILfmoddocfiscBCL#updateLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfmoddocfisc> updateLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest)
	{ 
		InternalResultsResponse<Lfmoddocfisc> response = getLfmoddocfiscDAC().updateLfmoddocfisc(lfmoddocfiscRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfmoddocfiscRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfmoddocfiscRequest the lfmoddocfisc request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfmoddocfiscRequest lfmoddocfiscRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfmoddocfiscRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfmoddocfiscRequest the lfmoddocfisc request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfmoddocfiscRequest lfmoddocfiscRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfmoddocfiscRequest.getLfmoddocfisc()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfmoddocfisc lfmoddocfisc = lfmoddocfiscRequest.getLfmoddocfisc();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfmoddocfisc.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfmoddocfisc.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfmoddocfiscRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfmoddocfiscRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
