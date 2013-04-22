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
import com.sensus.mlc.lftrattrib.bcl.ILftrattribBCL;
import com.sensus.mlc.lftrattrib.dac.ILftrattribDAC;
import com.sensus.mlc.lftrattrib.model.Lftrattrib;
import com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest;
import com.sensus.mlc.lftrattrib.model.request.LftrattribRequest;

/** 
 * The Class LftrattribBCLImpl. 
 */ 
public class LftrattribBCLImpl implements ILftrattribBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lftrattrib dac. */ 
	private ILftrattribDAC lftrattribDAC;



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
	 * Spring Sets the lftrattrib dac. 
	 *  
	 * @param iLftrattribDAC the new lftrattrib dac 
	 */ 
	public void setLftrattribDAC(ILftrattribDAC iLftrattribDAC)
	{ 
		lftrattribDAC = iLftrattribDAC;
	} 

	/** 
	 * Gets the lftrattrib dac. 
	 * 
	 * @return the lftrattrib dac 
	 */ 
	public ILftrattribDAC getLftrattribDAC()
	{
		return lftrattribDAC;
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
	 * @see com.sensus.mlc.lftrattrib.bcl.ILftrattribBCL#fetchAllLftrattribs(InquiryLftrattribRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lftrattrib> fetchAllLftrattrib(InquiryLftrattribRequest inquiryLftrattribRequest)
	{
		return getLftrattribDAC().fetchAllLftrattrib(inquiryLftrattribRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftrattrib.bcl.ILftrattribBCL#fetchLftrattribById(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lftrattrib> fetchLftrattribById(LftrattribRequest lftrattribRequest)
	{ 
		return getLftrattribDAC().fetchLftrattribById(lftrattribRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftrattrib.bcl.ILftrattribBCL#insertLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lftrattrib> insertLftrattrib(LftrattribRequest lftrattribRequest) 
	{  

		InternalResultsResponse<Lftrattrib> response = getLftrattribDAC().insertLftrattrib(lftrattribRequest);

		if (!response.isInError())
		{  
			Lftrattrib lftrattrib = response.getFirstResult();
			lftrattribRequest.setLftrattrib(lftrattrib);

			SearchParameter lftrattribParameter = new SearchParameter(PropertyEnum.LFTRATTRIB_ID, String.valueOf(lftrattrib.getId()));
			lftrattribRequest.getSearchLight().addSearchParameter(lftrattribParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lftrattribRequest, LCActionTypeEnum.INSERT_LFTRATTRIB, null);
			lftrattribRequest.getSearchLight().getSearchParameters().remove(lftrattribParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftrattrib.bcl.ILftrattribBCL#deleteLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)
	 */ 
	@Override 
	public InternalResponse deleteLftrattrib(LftrattribRequest lftrattribRequest)
	{
		InternalResultsResponse<Lftrattrib> lftrattribResponse = getLftrattribDAC().fetchLftrattribById(lftrattribRequest);
		InternalResponse response = new InternalResponse();

		if (lftrattribResponse.isInError()) 
		{
			response.setStatus(lftrattribResponse.getStatus());
			response.addMessages(lftrattribResponse.getMessageInfoList());
			return response;
		} 

		response = getLftrattribDAC().deleteLftrattrib(lftrattribRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lftrattrib lftrattrib = lftrattribResponse.getFirstResult();
		lftrattribRequest.setLftrattrib(lftrattrib);

		SearchParameter lftrattribParameter = new SearchParameter(PropertyEnum.LFTRATTRIB_ID, String.valueOf(lftrattrib.getId()));
		lftrattribRequest.getSearchLight().addSearchParameter(lftrattribParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lftrattribRequest, LCActionTypeEnum.DELETE_TAG, null);
		lftrattribRequest.getSearchLight().getSearchParameters().remove(lftrattribParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftrattrib.bcl.ILftrattribBCL#updateLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lftrattrib> updateLftrattrib(LftrattribRequest lftrattribRequest)
	{ 
		InternalResultsResponse<Lftrattrib> response = getLftrattribDAC().updateLftrattrib(lftrattribRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lftrattribRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lftrattribRequest the lftrattrib request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LftrattribRequest lftrattribRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lftrattribRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lftrattribRequest the lftrattrib request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LftrattribRequest lftrattribRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lftrattribRequest.getLftrattrib()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lftrattrib lftrattrib = lftrattribRequest.getLftrattrib();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lftrattrib.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lftrattrib.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lftrattribRequest);
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

		ProcessRequest processRequest = createProcessRequest(lftrattribRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
