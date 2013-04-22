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
import com.sensus.mlc.lfregrafiscal.bcl.ILfregrafiscalBCL;
import com.sensus.mlc.lfregrafiscal.dac.ILfregrafiscalDAC;
import com.sensus.mlc.lfregrafiscal.model.Lfregrafiscal;
import com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest;

/** 
 * The Class LfregrafiscalBCLImpl. 
 */ 
public class LfregrafiscalBCLImpl implements ILfregrafiscalBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfregrafiscal dac. */ 
	private ILfregrafiscalDAC lfregrafiscalDAC;



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
	 * Spring Sets the lfregrafiscal dac. 
	 *  
	 * @param iLfregrafiscalDAC the new lfregrafiscal dac 
	 */ 
	public void setLfregrafiscalDAC(ILfregrafiscalDAC iLfregrafiscalDAC)
	{ 
		lfregrafiscalDAC = iLfregrafiscalDAC;
	} 

	/** 
	 * Gets the lfregrafiscal dac. 
	 * 
	 * @return the lfregrafiscal dac 
	 */ 
	public ILfregrafiscalDAC getLfregrafiscalDAC()
	{
		return lfregrafiscalDAC;
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
	 * @see com.sensus.mlc.lfregrafiscal.bcl.ILfregrafiscalBCL#fetchAllLfregrafiscals(InquiryLfregrafiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfregrafiscal> fetchAllLfregrafiscal(InquiryLfregrafiscalRequest inquiryLfregrafiscalRequest)
	{
		return getLfregrafiscalDAC().fetchAllLfregrafiscal(inquiryLfregrafiscalRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfregrafiscal.bcl.ILfregrafiscalBCL#fetchLfregrafiscalById(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfregrafiscal> fetchLfregrafiscalById(LfregrafiscalRequest lfregrafiscalRequest)
	{ 
		return getLfregrafiscalDAC().fetchLfregrafiscalById(lfregrafiscalRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfregrafiscal.bcl.ILfregrafiscalBCL#insertLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfregrafiscal> insertLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest) 
	{  

		InternalResultsResponse<Lfregrafiscal> response = getLfregrafiscalDAC().insertLfregrafiscal(lfregrafiscalRequest);

		if (!response.isInError())
		{  
			Lfregrafiscal lfregrafiscal = response.getFirstResult();
			lfregrafiscalRequest.setLfregrafiscal(lfregrafiscal);

			SearchParameter lfregrafiscalParameter = new SearchParameter(PropertyEnum.LFREGRAFISCAL_ID, String.valueOf(lfregrafiscal.getId()));
			lfregrafiscalRequest.getSearchLight().addSearchParameter(lfregrafiscalParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfregrafiscalRequest, LCActionTypeEnum.INSERT_LFREGRAFISCAL, null);
			lfregrafiscalRequest.getSearchLight().getSearchParameters().remove(lfregrafiscalParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfregrafiscal.bcl.ILfregrafiscalBCL#deleteLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest)
	{
		InternalResultsResponse<Lfregrafiscal> lfregrafiscalResponse = getLfregrafiscalDAC().fetchLfregrafiscalById(lfregrafiscalRequest);
		InternalResponse response = new InternalResponse();

		if (lfregrafiscalResponse.isInError()) 
		{
			response.setStatus(lfregrafiscalResponse.getStatus());
			response.addMessages(lfregrafiscalResponse.getMessageInfoList());
			return response;
		} 

		response = getLfregrafiscalDAC().deleteLfregrafiscal(lfregrafiscalRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfregrafiscal lfregrafiscal = lfregrafiscalResponse.getFirstResult();
		lfregrafiscalRequest.setLfregrafiscal(lfregrafiscal);

		SearchParameter lfregrafiscalParameter = new SearchParameter(PropertyEnum.LFREGRAFISCAL_ID, String.valueOf(lfregrafiscal.getId()));
		lfregrafiscalRequest.getSearchLight().addSearchParameter(lfregrafiscalParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfregrafiscalRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfregrafiscalRequest.getSearchLight().getSearchParameters().remove(lfregrafiscalParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfregrafiscal.bcl.ILfregrafiscalBCL#updateLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfregrafiscal> updateLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest)
	{ 
		InternalResultsResponse<Lfregrafiscal> response = getLfregrafiscalDAC().updateLfregrafiscal(lfregrafiscalRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfregrafiscalRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfregrafiscalRequest the lfregrafiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfregrafiscalRequest lfregrafiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfregrafiscalRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfregrafiscalRequest the lfregrafiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfregrafiscalRequest lfregrafiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfregrafiscalRequest.getLfregrafiscal()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfregrafiscal lfregrafiscal = lfregrafiscalRequest.getLfregrafiscal();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfregrafiscal.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfregrafiscal.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfregrafiscalRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfregrafiscalRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
