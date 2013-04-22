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
import com.sensus.mlc.lfncm.bcl.ILfncmBCL;
import com.sensus.mlc.lfncm.dac.ILfncmDAC;
import com.sensus.mlc.lfncm.model.Lfncm;
import com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest;
import com.sensus.mlc.lfncm.model.request.LfncmRequest;

/** 
 * The Class LfncmBCLImpl. 
 */ 
public class LfncmBCLImpl implements ILfncmBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfncm dac. */ 
	private ILfncmDAC lfncmDAC;



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
	 * Spring Sets the lfncm dac. 
	 *  
	 * @param iLfncmDAC the new lfncm dac 
	 */ 
	public void setLfncmDAC(ILfncmDAC iLfncmDAC)
	{ 
		lfncmDAC = iLfncmDAC;
	} 

	/** 
	 * Gets the lfncm dac. 
	 * 
	 * @return the lfncm dac 
	 */ 
	public ILfncmDAC getLfncmDAC()
	{
		return lfncmDAC;
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
	 * @see com.sensus.mlc.lfncm.bcl.ILfncmBCL#fetchAllLfncms(InquiryLfncmRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfncm> fetchAllLfncm(InquiryLfncmRequest inquiryLfncmRequest)
	{
		return getLfncmDAC().fetchAllLfncm(inquiryLfncmRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfncm.bcl.ILfncmBCL#fetchLfncmById(com.sensus.mlc.lfncm.model.request.LfncmRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfncm> fetchLfncmById(LfncmRequest lfncmRequest)
	{ 
		return getLfncmDAC().fetchLfncmById(lfncmRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncm.bcl.ILfncmBCL#insertLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfncm> insertLfncm(LfncmRequest lfncmRequest) 
	{  

		InternalResultsResponse<Lfncm> response = getLfncmDAC().insertLfncm(lfncmRequest);

		if (!response.isInError())
		{  
			Lfncm lfncm = response.getFirstResult();
			lfncmRequest.setLfncm(lfncm);

			SearchParameter lfncmParameter = new SearchParameter(PropertyEnum.LFNCM_ID, String.valueOf(lfncm.getId()));
			lfncmRequest.getSearchLight().addSearchParameter(lfncmParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfncmRequest, LCActionTypeEnum.INSERT_LFNCM, null);
			lfncmRequest.getSearchLight().getSearchParameters().remove(lfncmParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncm.bcl.ILfncmBCL#deleteLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfncm(LfncmRequest lfncmRequest)
	{
		InternalResultsResponse<Lfncm> lfncmResponse = getLfncmDAC().fetchLfncmById(lfncmRequest);
		InternalResponse response = new InternalResponse();

		if (lfncmResponse.isInError()) 
		{
			response.setStatus(lfncmResponse.getStatus());
			response.addMessages(lfncmResponse.getMessageInfoList());
			return response;
		} 

		response = getLfncmDAC().deleteLfncm(lfncmRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfncm lfncm = lfncmResponse.getFirstResult();
		lfncmRequest.setLfncm(lfncm);

		SearchParameter lfncmParameter = new SearchParameter(PropertyEnum.LFNCM_ID, String.valueOf(lfncm.getId()));
		lfncmRequest.getSearchLight().addSearchParameter(lfncmParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfncmRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfncmRequest.getSearchLight().getSearchParameters().remove(lfncmParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncm.bcl.ILfncmBCL#updateLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfncm> updateLfncm(LfncmRequest lfncmRequest)
	{ 
		InternalResultsResponse<Lfncm> response = getLfncmDAC().updateLfncm(lfncmRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfncmRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfncmRequest the lfncm request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfncmRequest lfncmRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfncmRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfncmRequest the lfncm request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfncmRequest lfncmRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfncmRequest.getLfncm()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfncm lfncm = lfncmRequest.getLfncm();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfncm.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfncm.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfncmRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfncmRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
