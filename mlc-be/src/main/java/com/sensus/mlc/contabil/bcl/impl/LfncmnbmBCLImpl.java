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
import com.sensus.mlc.lfncmnbm.bcl.ILfncmnbmBCL;
import com.sensus.mlc.lfncmnbm.dac.ILfncmnbmDAC;
import com.sensus.mlc.lfncmnbm.model.Lfncmnbm;
import com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest;

/** 
 * The Class LfncmnbmBCLImpl. 
 */ 
public class LfncmnbmBCLImpl implements ILfncmnbmBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfncmnbm dac. */ 
	private ILfncmnbmDAC lfncmnbmDAC;



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
	 * Spring Sets the lfncmnbm dac. 
	 *  
	 * @param iLfncmnbmDAC the new lfncmnbm dac 
	 */ 
	public void setLfncmnbmDAC(ILfncmnbmDAC iLfncmnbmDAC)
	{ 
		lfncmnbmDAC = iLfncmnbmDAC;
	} 

	/** 
	 * Gets the lfncmnbm dac. 
	 * 
	 * @return the lfncmnbm dac 
	 */ 
	public ILfncmnbmDAC getLfncmnbmDAC()
	{
		return lfncmnbmDAC;
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
	 * @see com.sensus.mlc.lfncmnbm.bcl.ILfncmnbmBCL#fetchAllLfncmnbms(InquiryLfncmnbmRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfncmnbm> fetchAllLfncmnbm(InquiryLfncmnbmRequest inquiryLfncmnbmRequest)
	{
		return getLfncmnbmDAC().fetchAllLfncmnbm(inquiryLfncmnbmRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfncmnbm.bcl.ILfncmnbmBCL#fetchLfncmnbmById(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfncmnbm> fetchLfncmnbmById(LfncmnbmRequest lfncmnbmRequest)
	{ 
		return getLfncmnbmDAC().fetchLfncmnbmById(lfncmnbmRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncmnbm.bcl.ILfncmnbmBCL#insertLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfncmnbm> insertLfncmnbm(LfncmnbmRequest lfncmnbmRequest) 
	{  

		InternalResultsResponse<Lfncmnbm> response = getLfncmnbmDAC().insertLfncmnbm(lfncmnbmRequest);

		if (!response.isInError())
		{  
			Lfncmnbm lfncmnbm = response.getFirstResult();
			lfncmnbmRequest.setLfncmnbm(lfncmnbm);

			SearchParameter lfncmnbmParameter = new SearchParameter(PropertyEnum.LFNCMNBM_ID, String.valueOf(lfncmnbm.getId()));
			lfncmnbmRequest.getSearchLight().addSearchParameter(lfncmnbmParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfncmnbmRequest, LCActionTypeEnum.INSERT_LFNCMNBM, null);
			lfncmnbmRequest.getSearchLight().getSearchParameters().remove(lfncmnbmParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncmnbm.bcl.ILfncmnbmBCL#deleteLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfncmnbm(LfncmnbmRequest lfncmnbmRequest)
	{
		InternalResultsResponse<Lfncmnbm> lfncmnbmResponse = getLfncmnbmDAC().fetchLfncmnbmById(lfncmnbmRequest);
		InternalResponse response = new InternalResponse();

		if (lfncmnbmResponse.isInError()) 
		{
			response.setStatus(lfncmnbmResponse.getStatus());
			response.addMessages(lfncmnbmResponse.getMessageInfoList());
			return response;
		} 

		response = getLfncmnbmDAC().deleteLfncmnbm(lfncmnbmRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfncmnbm lfncmnbm = lfncmnbmResponse.getFirstResult();
		lfncmnbmRequest.setLfncmnbm(lfncmnbm);

		SearchParameter lfncmnbmParameter = new SearchParameter(PropertyEnum.LFNCMNBM_ID, String.valueOf(lfncmnbm.getId()));
		lfncmnbmRequest.getSearchLight().addSearchParameter(lfncmnbmParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfncmnbmRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfncmnbmRequest.getSearchLight().getSearchParameters().remove(lfncmnbmParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncmnbm.bcl.ILfncmnbmBCL#updateLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfncmnbm> updateLfncmnbm(LfncmnbmRequest lfncmnbmRequest)
	{ 
		InternalResultsResponse<Lfncmnbm> response = getLfncmnbmDAC().updateLfncmnbm(lfncmnbmRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfncmnbmRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfncmnbmRequest the lfncmnbm request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfncmnbmRequest lfncmnbmRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfncmnbmRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfncmnbmRequest the lfncmnbm request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfncmnbmRequest lfncmnbmRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfncmnbmRequest.getLfncmnbm()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfncmnbm lfncmnbm = lfncmnbmRequest.getLfncmnbm();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfncmnbm.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfncmnbm.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfncmnbmRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfncmnbmRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
