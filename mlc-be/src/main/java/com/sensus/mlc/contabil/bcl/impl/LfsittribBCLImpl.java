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
import com.sensus.mlc.lfsittrib.bcl.ILfsittribBCL;
import com.sensus.mlc.lfsittrib.dac.ILfsittribDAC;
import com.sensus.mlc.lfsittrib.model.Lfsittrib;
import com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest;
import com.sensus.mlc.lfsittrib.model.request.LfsittribRequest;

/** 
 * The Class LfsittribBCLImpl. 
 */ 
public class LfsittribBCLImpl implements ILfsittribBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfsittrib dac. */ 
	private ILfsittribDAC lfsittribDAC;



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
	 * Spring Sets the lfsittrib dac. 
	 *  
	 * @param iLfsittribDAC the new lfsittrib dac 
	 */ 
	public void setLfsittribDAC(ILfsittribDAC iLfsittribDAC)
	{ 
		lfsittribDAC = iLfsittribDAC;
	} 

	/** 
	 * Gets the lfsittrib dac. 
	 * 
	 * @return the lfsittrib dac 
	 */ 
	public ILfsittribDAC getLfsittribDAC()
	{
		return lfsittribDAC;
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
	 * @see com.sensus.mlc.lfsittrib.bcl.ILfsittribBCL#fetchAllLfsittribs(InquiryLfsittribRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfsittrib> fetchAllLfsittrib(InquiryLfsittribRequest inquiryLfsittribRequest)
	{
		return getLfsittribDAC().fetchAllLfsittrib(inquiryLfsittribRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfsittrib.bcl.ILfsittribBCL#fetchLfsittribById(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfsittrib> fetchLfsittribById(LfsittribRequest lfsittribRequest)
	{ 
		return getLfsittribDAC().fetchLfsittribById(lfsittribRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfsittrib.bcl.ILfsittribBCL#insertLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfsittrib> insertLfsittrib(LfsittribRequest lfsittribRequest) 
	{  

		InternalResultsResponse<Lfsittrib> response = getLfsittribDAC().insertLfsittrib(lfsittribRequest);

		if (!response.isInError())
		{  
			Lfsittrib lfsittrib = response.getFirstResult();
			lfsittribRequest.setLfsittrib(lfsittrib);

			SearchParameter lfsittribParameter = new SearchParameter(PropertyEnum.LFSITTRIB_ID, String.valueOf(lfsittrib.getId()));
			lfsittribRequest.getSearchLight().addSearchParameter(lfsittribParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfsittribRequest, LCActionTypeEnum.INSERT_LFSITTRIB, null);
			lfsittribRequest.getSearchLight().getSearchParameters().remove(lfsittribParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfsittrib.bcl.ILfsittribBCL#deleteLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfsittrib(LfsittribRequest lfsittribRequest)
	{
		InternalResultsResponse<Lfsittrib> lfsittribResponse = getLfsittribDAC().fetchLfsittribById(lfsittribRequest);
		InternalResponse response = new InternalResponse();

		if (lfsittribResponse.isInError()) 
		{
			response.setStatus(lfsittribResponse.getStatus());
			response.addMessages(lfsittribResponse.getMessageInfoList());
			return response;
		} 

		response = getLfsittribDAC().deleteLfsittrib(lfsittribRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfsittrib lfsittrib = lfsittribResponse.getFirstResult();
		lfsittribRequest.setLfsittrib(lfsittrib);

		SearchParameter lfsittribParameter = new SearchParameter(PropertyEnum.LFSITTRIB_ID, String.valueOf(lfsittrib.getId()));
		lfsittribRequest.getSearchLight().addSearchParameter(lfsittribParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfsittribRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfsittribRequest.getSearchLight().getSearchParameters().remove(lfsittribParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfsittrib.bcl.ILfsittribBCL#updateLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfsittrib> updateLfsittrib(LfsittribRequest lfsittribRequest)
	{ 
		InternalResultsResponse<Lfsittrib> response = getLfsittribDAC().updateLfsittrib(lfsittribRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfsittribRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfsittribRequest the lfsittrib request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfsittribRequest lfsittribRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfsittribRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfsittribRequest the lfsittrib request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfsittribRequest lfsittribRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfsittribRequest.getLfsittrib()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfsittrib lfsittrib = lfsittribRequest.getLfsittrib();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfsittrib.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfsittrib.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfsittribRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfsittribRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
