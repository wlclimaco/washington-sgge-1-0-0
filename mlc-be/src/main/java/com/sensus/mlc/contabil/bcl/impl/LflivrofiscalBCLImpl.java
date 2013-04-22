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
import com.sensus.mlc.lflivrofiscal.bcl.ILflivrofiscalBCL;
import com.sensus.mlc.lflivrofiscal.dac.ILflivrofiscalDAC;
import com.sensus.mlc.lflivrofiscal.model.Lflivrofiscal;
import com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest;

/** 
 * The Class LflivrofiscalBCLImpl. 
 */ 
public class LflivrofiscalBCLImpl implements ILflivrofiscalBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lflivrofiscal dac. */ 
	private ILflivrofiscalDAC lflivrofiscalDAC;



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
	 * Spring Sets the lflivrofiscal dac. 
	 *  
	 * @param iLflivrofiscalDAC the new lflivrofiscal dac 
	 */ 
	public void setLflivrofiscalDAC(ILflivrofiscalDAC iLflivrofiscalDAC)
	{ 
		lflivrofiscalDAC = iLflivrofiscalDAC;
	} 

	/** 
	 * Gets the lflivrofiscal dac. 
	 * 
	 * @return the lflivrofiscal dac 
	 */ 
	public ILflivrofiscalDAC getLflivrofiscalDAC()
	{
		return lflivrofiscalDAC;
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
	 * @see com.sensus.mlc.lflivrofiscal.bcl.ILflivrofiscalBCL#fetchAllLflivrofiscals(InquiryLflivrofiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lflivrofiscal> fetchAllLflivrofiscal(InquiryLflivrofiscalRequest inquiryLflivrofiscalRequest)
	{
		return getLflivrofiscalDAC().fetchAllLflivrofiscal(inquiryLflivrofiscalRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lflivrofiscal.bcl.ILflivrofiscalBCL#fetchLflivrofiscalById(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lflivrofiscal> fetchLflivrofiscalById(LflivrofiscalRequest lflivrofiscalRequest)
	{ 
		return getLflivrofiscalDAC().fetchLflivrofiscalById(lflivrofiscalRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lflivrofiscal.bcl.ILflivrofiscalBCL#insertLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lflivrofiscal> insertLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest) 
	{  

		InternalResultsResponse<Lflivrofiscal> response = getLflivrofiscalDAC().insertLflivrofiscal(lflivrofiscalRequest);

		if (!response.isInError())
		{  
			Lflivrofiscal lflivrofiscal = response.getFirstResult();
			lflivrofiscalRequest.setLflivrofiscal(lflivrofiscal);

			SearchParameter lflivrofiscalParameter = new SearchParameter(PropertyEnum.LFLIVROFISCAL_ID, String.valueOf(lflivrofiscal.getId()));
			lflivrofiscalRequest.getSearchLight().addSearchParameter(lflivrofiscalParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lflivrofiscalRequest, LCActionTypeEnum.INSERT_LFLIVROFISCAL, null);
			lflivrofiscalRequest.getSearchLight().getSearchParameters().remove(lflivrofiscalParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lflivrofiscal.bcl.ILflivrofiscalBCL#deleteLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)
	 */ 
	@Override 
	public InternalResponse deleteLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest)
	{
		InternalResultsResponse<Lflivrofiscal> lflivrofiscalResponse = getLflivrofiscalDAC().fetchLflivrofiscalById(lflivrofiscalRequest);
		InternalResponse response = new InternalResponse();

		if (lflivrofiscalResponse.isInError()) 
		{
			response.setStatus(lflivrofiscalResponse.getStatus());
			response.addMessages(lflivrofiscalResponse.getMessageInfoList());
			return response;
		} 

		response = getLflivrofiscalDAC().deleteLflivrofiscal(lflivrofiscalRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lflivrofiscal lflivrofiscal = lflivrofiscalResponse.getFirstResult();
		lflivrofiscalRequest.setLflivrofiscal(lflivrofiscal);

		SearchParameter lflivrofiscalParameter = new SearchParameter(PropertyEnum.LFLIVROFISCAL_ID, String.valueOf(lflivrofiscal.getId()));
		lflivrofiscalRequest.getSearchLight().addSearchParameter(lflivrofiscalParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lflivrofiscalRequest, LCActionTypeEnum.DELETE_TAG, null);
		lflivrofiscalRequest.getSearchLight().getSearchParameters().remove(lflivrofiscalParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lflivrofiscal.bcl.ILflivrofiscalBCL#updateLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lflivrofiscal> updateLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest)
	{ 
		InternalResultsResponse<Lflivrofiscal> response = getLflivrofiscalDAC().updateLflivrofiscal(lflivrofiscalRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lflivrofiscalRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lflivrofiscalRequest the lflivrofiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LflivrofiscalRequest lflivrofiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lflivrofiscalRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lflivrofiscalRequest the lflivrofiscal request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LflivrofiscalRequest lflivrofiscalRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lflivrofiscalRequest.getLflivrofiscal()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lflivrofiscal lflivrofiscal = lflivrofiscalRequest.getLflivrofiscal();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lflivrofiscal.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lflivrofiscal.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lflivrofiscalRequest);
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

		ProcessRequest processRequest = createProcessRequest(lflivrofiscalRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
