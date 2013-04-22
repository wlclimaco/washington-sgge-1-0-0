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
import com.sensus.mlc.lfnbm.bcl.ILfnbmBCL;
import com.sensus.mlc.lfnbm.dac.ILfnbmDAC;
import com.sensus.mlc.lfnbm.model.Lfnbm;
import com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest;
import com.sensus.mlc.lfnbm.model.request.LfnbmRequest;

/** 
 * The Class LfnbmBCLImpl. 
 */ 
public class LfnbmBCLImpl implements ILfnbmBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfnbm dac. */ 
	private ILfnbmDAC lfnbmDAC;



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
	 * Spring Sets the lfnbm dac. 
	 *  
	 * @param iLfnbmDAC the new lfnbm dac 
	 */ 
	public void setLfnbmDAC(ILfnbmDAC iLfnbmDAC)
	{ 
		lfnbmDAC = iLfnbmDAC;
	} 

	/** 
	 * Gets the lfnbm dac. 
	 * 
	 * @return the lfnbm dac 
	 */ 
	public ILfnbmDAC getLfnbmDAC()
	{
		return lfnbmDAC;
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
	 * @see com.sensus.mlc.lfnbm.bcl.ILfnbmBCL#fetchAllLfnbms(InquiryLfnbmRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfnbm> fetchAllLfnbm(InquiryLfnbmRequest inquiryLfnbmRequest)
	{
		return getLfnbmDAC().fetchAllLfnbm(inquiryLfnbmRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfnbm.bcl.ILfnbmBCL#fetchLfnbmById(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfnbm> fetchLfnbmById(LfnbmRequest lfnbmRequest)
	{ 
		return getLfnbmDAC().fetchLfnbmById(lfnbmRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnbm.bcl.ILfnbmBCL#insertLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfnbm> insertLfnbm(LfnbmRequest lfnbmRequest) 
	{  

		InternalResultsResponse<Lfnbm> response = getLfnbmDAC().insertLfnbm(lfnbmRequest);

		if (!response.isInError())
		{  
			Lfnbm lfnbm = response.getFirstResult();
			lfnbmRequest.setLfnbm(lfnbm);

			SearchParameter lfnbmParameter = new SearchParameter(PropertyEnum.LFNBM_ID, String.valueOf(lfnbm.getId()));
			lfnbmRequest.getSearchLight().addSearchParameter(lfnbmParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfnbmRequest, LCActionTypeEnum.INSERT_LFNBM, null);
			lfnbmRequest.getSearchLight().getSearchParameters().remove(lfnbmParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnbm.bcl.ILfnbmBCL#deleteLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfnbm(LfnbmRequest lfnbmRequest)
	{
		InternalResultsResponse<Lfnbm> lfnbmResponse = getLfnbmDAC().fetchLfnbmById(lfnbmRequest);
		InternalResponse response = new InternalResponse();

		if (lfnbmResponse.isInError()) 
		{
			response.setStatus(lfnbmResponse.getStatus());
			response.addMessages(lfnbmResponse.getMessageInfoList());
			return response;
		} 

		response = getLfnbmDAC().deleteLfnbm(lfnbmRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfnbm lfnbm = lfnbmResponse.getFirstResult();
		lfnbmRequest.setLfnbm(lfnbm);

		SearchParameter lfnbmParameter = new SearchParameter(PropertyEnum.LFNBM_ID, String.valueOf(lfnbm.getId()));
		lfnbmRequest.getSearchLight().addSearchParameter(lfnbmParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfnbmRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfnbmRequest.getSearchLight().getSearchParameters().remove(lfnbmParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnbm.bcl.ILfnbmBCL#updateLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfnbm> updateLfnbm(LfnbmRequest lfnbmRequest)
	{ 
		InternalResultsResponse<Lfnbm> response = getLfnbmDAC().updateLfnbm(lfnbmRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfnbmRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfnbmRequest the lfnbm request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfnbmRequest lfnbmRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfnbmRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfnbmRequest the lfnbm request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfnbmRequest lfnbmRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfnbmRequest.getLfnbm()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfnbm lfnbm = lfnbmRequest.getLfnbm();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfnbm.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfnbm.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfnbmRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfnbmRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
