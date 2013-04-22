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
import com.sensus.mlc.lfitregrafiscal.bcl.ILfitregrafiscalBCL;
import com.sensus.mlc.lfitregrafiscal.dac.ILfitregrafiscalDAC;
import com.sensus.mlc.lfitregrafiscal.model.Lfitregrafiscal;
import com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest;

/** 
 * The Class LfitregrafiscalBCLImpl. 
 */ 
public class LfitregrafiscalBCLImpl implements ILfitregrafiscalBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfitregrafiscal dac. */ 
	private ILfitregrafiscalDAC lfitregrafiscalDAC;



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
	 * Spring Sets the lfitregrafiscal dac. 
	 *  
	 * @param iLfitregrafiscalDAC the new lfitregrafiscal dac 
	 */ 
	public void setLfitregrafiscalDAC(ILfitregrafiscalDAC iLfitregrafiscalDAC)
	{ 
		lfitregrafiscalDAC = iLfitregrafiscalDAC;
	} 

	/** 
	 * Gets the lfitregrafiscal dac. 
	 * 
	 * @return the lfitregrafiscal dac 
	 */ 
	public ILfitregrafiscalDAC getLfitregrafiscalDAC()
	{
		return lfitregrafiscalDAC;
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
	 * @see com.sensus.mlc.lfitregrafiscal.bcl.ILfitregrafiscalBCL#fetchAllLfitregrafiscals(InquiryLfitregrafiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitregrafiscal> fetchAllLfitregrafiscal(InquiryLfitregrafiscalRequest inquiryLfitregrafiscalRequest)
	{
		return getLfitregrafiscalDAC().fetchAllLfitregrafiscal(inquiryLfitregrafiscalRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitregrafiscal.bcl.ILfitregrafiscalBCL#fetchLfitregrafiscalById(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfitregrafiscal> fetchLfitregrafiscalById(LfitregrafiscalRequest lfitregrafiscalRequest)
	{ 
		return getLfitregrafiscalDAC().fetchLfitregrafiscalById(lfitregrafiscalRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitregrafiscal.bcl.ILfitregrafiscalBCL#insertLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitregrafiscal> insertLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest) 
	{  

		InternalResultsResponse<Lfitregrafiscal> response = getLfitregrafiscalDAC().insertLfitregrafiscal(lfitregrafiscalRequest);

		if (!response.isInError())
		{  
			Lfitregrafiscal lfitregrafiscal = response.getFirstResult();
			lfitregrafiscalRequest.setLfitregrafiscal(lfitregrafiscal);

			SearchParameter lfitregrafiscalParameter = new SearchParameter(PropertyEnum.LFITREGRAFISCAL_ID, String.valueOf(lfitregrafiscal.getId()));
			lfitregrafiscalRequest.getSearchLight().addSearchParameter(lfitregrafiscalParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfitregrafiscalRequest, LCActionTypeEnum.INSERT_LFITREGRAFISCAL, null);
			lfitregrafiscalRequest.getSearchLight().getSearchParameters().remove(lfitregrafiscalParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitregrafiscal.bcl.ILfitregrafiscalBCL#deleteLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest)
	{
		InternalResultsResponse<Lfitregrafiscal> lfitregrafiscalResponse = getLfitregrafiscalDAC().fetchLfitregrafiscalById(lfitregrafiscalRequest);
		InternalResponse response = new InternalResponse();

		if (lfitregrafiscalResponse.isInError()) 
		{
			response.setStatus(lfitregrafiscalResponse.getStatus());
			response.addMessages(lfitregrafiscalResponse.getMessageInfoList());
			return response;
		} 

		response = getLfitregrafiscalDAC().deleteLfitregrafiscal(lfitregrafiscalRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfitregrafiscal lfitregrafiscal = lfitregrafiscalResponse.getFirstResult();
		lfitregrafiscalRequest.setLfitregrafiscal(lfitregrafiscal);

		SearchParameter lfitregrafiscalParameter = new SearchParameter(PropertyEnum.LFITREGRAFISCAL_ID, String.valueOf(lfitregrafiscal.getId()));
		lfitregrafiscalRequest.getSearchLight().addSearchParameter(lfitregrafiscalParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfitregrafiscalRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfitregrafiscalRequest.getSearchLight().getSearchParameters().remove(lfitregrafiscalParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitregrafiscal.bcl.ILfitregrafiscalBCL#updateLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitregrafiscal> updateLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest)
	{ 
		InternalResultsResponse<Lfitregrafiscal> response = getLfitregrafiscalDAC().updateLfitregrafiscal(lfitregrafiscalRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfitregrafiscalRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfitregrafiscalRequest the lfitregrafiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitregrafiscalRequest lfitregrafiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfitregrafiscalRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfitregrafiscalRequest the lfitregrafiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitregrafiscalRequest lfitregrafiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfitregrafiscalRequest.getLfitregrafiscal()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfitregrafiscal lfitregrafiscal = lfitregrafiscalRequest.getLfitregrafiscal();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfitregrafiscal.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfitregrafiscal.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfitregrafiscalRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfitregrafiscalRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
