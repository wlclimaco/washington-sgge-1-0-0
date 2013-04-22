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
import com.sensus.mlc.lfnatoper.bcl.ILfnatoperBCL;
import com.sensus.mlc.lfnatoper.dac.ILfnatoperDAC;
import com.sensus.mlc.lfnatoper.model.Lfnatoper;
import com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest;

/** 
 * The Class LfnatoperBCLImpl. 
 */ 
public class LfnatoperBCLImpl implements ILfnatoperBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfnatoper dac. */ 
	private ILfnatoperDAC lfnatoperDAC;



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
	 * Spring Sets the lfnatoper dac. 
	 *  
	 * @param iLfnatoperDAC the new lfnatoper dac 
	 */ 
	public void setLfnatoperDAC(ILfnatoperDAC iLfnatoperDAC)
	{ 
		lfnatoperDAC = iLfnatoperDAC;
	} 

	/** 
	 * Gets the lfnatoper dac. 
	 * 
	 * @return the lfnatoper dac 
	 */ 
	public ILfnatoperDAC getLfnatoperDAC()
	{
		return lfnatoperDAC;
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
	 * @see com.sensus.mlc.lfnatoper.bcl.ILfnatoperBCL#fetchAllLfnatopers(InquiryLfnatoperRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfnatoper> fetchAllLfnatoper(InquiryLfnatoperRequest inquiryLfnatoperRequest)
	{
		return getLfnatoperDAC().fetchAllLfnatoper(inquiryLfnatoperRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfnatoper.bcl.ILfnatoperBCL#fetchLfnatoperById(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfnatoper> fetchLfnatoperById(LfnatoperRequest lfnatoperRequest)
	{ 
		return getLfnatoperDAC().fetchLfnatoperById(lfnatoperRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnatoper.bcl.ILfnatoperBCL#insertLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfnatoper> insertLfnatoper(LfnatoperRequest lfnatoperRequest) 
	{  

		InternalResultsResponse<Lfnatoper> response = getLfnatoperDAC().insertLfnatoper(lfnatoperRequest);

		if (!response.isInError())
		{  
			Lfnatoper lfnatoper = response.getFirstResult();
			lfnatoperRequest.setLfnatoper(lfnatoper);

			SearchParameter lfnatoperParameter = new SearchParameter(PropertyEnum.LFNATOPER_ID, String.valueOf(lfnatoper.getId()));
			lfnatoperRequest.getSearchLight().addSearchParameter(lfnatoperParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfnatoperRequest, LCActionTypeEnum.INSERT_LFNATOPER, null);
			lfnatoperRequest.getSearchLight().getSearchParameters().remove(lfnatoperParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnatoper.bcl.ILfnatoperBCL#deleteLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfnatoper(LfnatoperRequest lfnatoperRequest)
	{
		InternalResultsResponse<Lfnatoper> lfnatoperResponse = getLfnatoperDAC().fetchLfnatoperById(lfnatoperRequest);
		InternalResponse response = new InternalResponse();

		if (lfnatoperResponse.isInError()) 
		{
			response.setStatus(lfnatoperResponse.getStatus());
			response.addMessages(lfnatoperResponse.getMessageInfoList());
			return response;
		} 

		response = getLfnatoperDAC().deleteLfnatoper(lfnatoperRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfnatoper lfnatoper = lfnatoperResponse.getFirstResult();
		lfnatoperRequest.setLfnatoper(lfnatoper);

		SearchParameter lfnatoperParameter = new SearchParameter(PropertyEnum.LFNATOPER_ID, String.valueOf(lfnatoper.getId()));
		lfnatoperRequest.getSearchLight().addSearchParameter(lfnatoperParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfnatoperRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfnatoperRequest.getSearchLight().getSearchParameters().remove(lfnatoperParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnatoper.bcl.ILfnatoperBCL#updateLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfnatoper> updateLfnatoper(LfnatoperRequest lfnatoperRequest)
	{ 
		InternalResultsResponse<Lfnatoper> response = getLfnatoperDAC().updateLfnatoper(lfnatoperRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfnatoperRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfnatoperRequest the lfnatoper request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfnatoperRequest lfnatoperRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfnatoperRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfnatoperRequest the lfnatoper request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfnatoperRequest lfnatoperRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfnatoperRequest.getLfnatoper()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfnatoper lfnatoper = lfnatoperRequest.getLfnatoper();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfnatoper.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfnatoper.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfnatoperRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfnatoperRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
