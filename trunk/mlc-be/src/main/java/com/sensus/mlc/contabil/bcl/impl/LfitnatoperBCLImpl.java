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
import com.sensus.mlc.lfitnatoper.bcl.ILfitnatoperBCL;
import com.sensus.mlc.lfitnatoper.dac.ILfitnatoperDAC;
import com.sensus.mlc.lfitnatoper.model.Lfitnatoper;
import com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest;

/** 
 * The Class LfitnatoperBCLImpl. 
 */ 
public class LfitnatoperBCLImpl implements ILfitnatoperBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfitnatoper dac. */ 
	private ILfitnatoperDAC lfitnatoperDAC;



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
	 * Spring Sets the lfitnatoper dac. 
	 *  
	 * @param iLfitnatoperDAC the new lfitnatoper dac 
	 */ 
	public void setLfitnatoperDAC(ILfitnatoperDAC iLfitnatoperDAC)
	{ 
		lfitnatoperDAC = iLfitnatoperDAC;
	} 

	/** 
	 * Gets the lfitnatoper dac. 
	 * 
	 * @return the lfitnatoper dac 
	 */ 
	public ILfitnatoperDAC getLfitnatoperDAC()
	{
		return lfitnatoperDAC;
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
	 * @see com.sensus.mlc.lfitnatoper.bcl.ILfitnatoperBCL#fetchAllLfitnatopers(InquiryLfitnatoperRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitnatoper> fetchAllLfitnatoper(InquiryLfitnatoperRequest inquiryLfitnatoperRequest)
	{
		return getLfitnatoperDAC().fetchAllLfitnatoper(inquiryLfitnatoperRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitnatoper.bcl.ILfitnatoperBCL#fetchLfitnatoperById(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfitnatoper> fetchLfitnatoperById(LfitnatoperRequest lfitnatoperRequest)
	{ 
		return getLfitnatoperDAC().fetchLfitnatoperById(lfitnatoperRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitnatoper.bcl.ILfitnatoperBCL#insertLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitnatoper> insertLfitnatoper(LfitnatoperRequest lfitnatoperRequest) 
	{  

		InternalResultsResponse<Lfitnatoper> response = getLfitnatoperDAC().insertLfitnatoper(lfitnatoperRequest);

		if (!response.isInError())
		{  
			Lfitnatoper lfitnatoper = response.getFirstResult();
			lfitnatoperRequest.setLfitnatoper(lfitnatoper);

			SearchParameter lfitnatoperParameter = new SearchParameter(PropertyEnum.LFITNATOPER_ID, String.valueOf(lfitnatoper.getId()));
			lfitnatoperRequest.getSearchLight().addSearchParameter(lfitnatoperParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfitnatoperRequest, LCActionTypeEnum.INSERT_LFITNATOPER, null);
			lfitnatoperRequest.getSearchLight().getSearchParameters().remove(lfitnatoperParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitnatoper.bcl.ILfitnatoperBCL#deleteLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfitnatoper(LfitnatoperRequest lfitnatoperRequest)
	{
		InternalResultsResponse<Lfitnatoper> lfitnatoperResponse = getLfitnatoperDAC().fetchLfitnatoperById(lfitnatoperRequest);
		InternalResponse response = new InternalResponse();

		if (lfitnatoperResponse.isInError()) 
		{
			response.setStatus(lfitnatoperResponse.getStatus());
			response.addMessages(lfitnatoperResponse.getMessageInfoList());
			return response;
		} 

		response = getLfitnatoperDAC().deleteLfitnatoper(lfitnatoperRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfitnatoper lfitnatoper = lfitnatoperResponse.getFirstResult();
		lfitnatoperRequest.setLfitnatoper(lfitnatoper);

		SearchParameter lfitnatoperParameter = new SearchParameter(PropertyEnum.LFITNATOPER_ID, String.valueOf(lfitnatoper.getId()));
		lfitnatoperRequest.getSearchLight().addSearchParameter(lfitnatoperParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfitnatoperRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfitnatoperRequest.getSearchLight().getSearchParameters().remove(lfitnatoperParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitnatoper.bcl.ILfitnatoperBCL#updateLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitnatoper> updateLfitnatoper(LfitnatoperRequest lfitnatoperRequest)
	{ 
		InternalResultsResponse<Lfitnatoper> response = getLfitnatoperDAC().updateLfitnatoper(lfitnatoperRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfitnatoperRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfitnatoperRequest the lfitnatoper request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitnatoperRequest lfitnatoperRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfitnatoperRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfitnatoperRequest the lfitnatoper request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitnatoperRequest lfitnatoperRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfitnatoperRequest.getLfitnatoper()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfitnatoper lfitnatoper = lfitnatoperRequest.getLfitnatoper();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfitnatoper.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfitnatoper.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfitnatoperRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfitnatoperRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
