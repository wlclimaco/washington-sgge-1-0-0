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
import com.sensus.mlc.lfitclfiscal.bcl.ILfitclfiscalBCL;
import com.sensus.mlc.lfitclfiscal.dac.ILfitclfiscalDAC;
import com.sensus.mlc.lfitclfiscal.model.Lfitclfiscal;
import com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest;

/** 
 * The Class LfitclfiscalBCLImpl. 
 */ 
public class LfitclfiscalBCLImpl implements ILfitclfiscalBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfitclfiscal dac. */ 
	private ILfitclfiscalDAC lfitclfiscalDAC;



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
	 * Spring Sets the lfitclfiscal dac. 
	 *  
	 * @param iLfitclfiscalDAC the new lfitclfiscal dac 
	 */ 
	public void setLfitclfiscalDAC(ILfitclfiscalDAC iLfitclfiscalDAC)
	{ 
		lfitclfiscalDAC = iLfitclfiscalDAC;
	} 

	/** 
	 * Gets the lfitclfiscal dac. 
	 * 
	 * @return the lfitclfiscal dac 
	 */ 
	public ILfitclfiscalDAC getLfitclfiscalDAC()
	{
		return lfitclfiscalDAC;
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
	 * @see com.sensus.mlc.lfitclfiscal.bcl.ILfitclfiscalBCL#fetchAllLfitclfiscals(InquiryLfitclfiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitclfiscal> fetchAllLfitclfiscal(InquiryLfitclfiscalRequest inquiryLfitclfiscalRequest)
	{
		return getLfitclfiscalDAC().fetchAllLfitclfiscal(inquiryLfitclfiscalRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitclfiscal.bcl.ILfitclfiscalBCL#fetchLfitclfiscalById(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfitclfiscal> fetchLfitclfiscalById(LfitclfiscalRequest lfitclfiscalRequest)
	{ 
		return getLfitclfiscalDAC().fetchLfitclfiscalById(lfitclfiscalRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitclfiscal.bcl.ILfitclfiscalBCL#insertLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitclfiscal> insertLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest) 
	{  

		InternalResultsResponse<Lfitclfiscal> response = getLfitclfiscalDAC().insertLfitclfiscal(lfitclfiscalRequest);

		if (!response.isInError())
		{  
			Lfitclfiscal lfitclfiscal = response.getFirstResult();
			lfitclfiscalRequest.setLfitclfiscal(lfitclfiscal);

			SearchParameter lfitclfiscalParameter = new SearchParameter(PropertyEnum.LFITCLFISCAL_ID, String.valueOf(lfitclfiscal.getId()));
			lfitclfiscalRequest.getSearchLight().addSearchParameter(lfitclfiscalParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfitclfiscalRequest, LCActionTypeEnum.INSERT_LFITCLFISCAL, null);
			lfitclfiscalRequest.getSearchLight().getSearchParameters().remove(lfitclfiscalParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitclfiscal.bcl.ILfitclfiscalBCL#deleteLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest)
	{
		InternalResultsResponse<Lfitclfiscal> lfitclfiscalResponse = getLfitclfiscalDAC().fetchLfitclfiscalById(lfitclfiscalRequest);
		InternalResponse response = new InternalResponse();

		if (lfitclfiscalResponse.isInError()) 
		{
			response.setStatus(lfitclfiscalResponse.getStatus());
			response.addMessages(lfitclfiscalResponse.getMessageInfoList());
			return response;
		} 

		response = getLfitclfiscalDAC().deleteLfitclfiscal(lfitclfiscalRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfitclfiscal lfitclfiscal = lfitclfiscalResponse.getFirstResult();
		lfitclfiscalRequest.setLfitclfiscal(lfitclfiscal);

		SearchParameter lfitclfiscalParameter = new SearchParameter(PropertyEnum.LFITCLFISCAL_ID, String.valueOf(lfitclfiscal.getId()));
		lfitclfiscalRequest.getSearchLight().addSearchParameter(lfitclfiscalParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfitclfiscalRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfitclfiscalRequest.getSearchLight().getSearchParameters().remove(lfitclfiscalParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitclfiscal.bcl.ILfitclfiscalBCL#updateLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitclfiscal> updateLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest)
	{ 
		InternalResultsResponse<Lfitclfiscal> response = getLfitclfiscalDAC().updateLfitclfiscal(lfitclfiscalRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfitclfiscalRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfitclfiscalRequest the lfitclfiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitclfiscalRequest lfitclfiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfitclfiscalRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfitclfiscalRequest the lfitclfiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitclfiscalRequest lfitclfiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfitclfiscalRequest.getLfitclfiscal()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfitclfiscal lfitclfiscal = lfitclfiscalRequest.getLfitclfiscal();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfitclfiscal.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfitclfiscal.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfitclfiscalRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfitclfiscalRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
