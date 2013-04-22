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
import com.sensus.mlc.lfitvenda.bcl.ILfitvendaBCL;
import com.sensus.mlc.lfitvenda.dac.ILfitvendaDAC;
import com.sensus.mlc.lfitvenda.model.Lfitvenda;
import com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest;

/** 
 * The Class LfitvendaBCLImpl. 
 */ 
public class LfitvendaBCLImpl implements ILfitvendaBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfitvenda dac. */ 
	private ILfitvendaDAC lfitvendaDAC;



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
	 * Spring Sets the lfitvenda dac. 
	 *  
	 * @param iLfitvendaDAC the new lfitvenda dac 
	 */ 
	public void setLfitvendaDAC(ILfitvendaDAC iLfitvendaDAC)
	{ 
		lfitvendaDAC = iLfitvendaDAC;
	} 

	/** 
	 * Gets the lfitvenda dac. 
	 * 
	 * @return the lfitvenda dac 
	 */ 
	public ILfitvendaDAC getLfitvendaDAC()
	{
		return lfitvendaDAC;
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
	 * @see com.sensus.mlc.lfitvenda.bcl.ILfitvendaBCL#fetchAllLfitvendas(InquiryLfitvendaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitvenda> fetchAllLfitvenda(InquiryLfitvendaRequest inquiryLfitvendaRequest)
	{
		return getLfitvendaDAC().fetchAllLfitvenda(inquiryLfitvendaRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitvenda.bcl.ILfitvendaBCL#fetchLfitvendaById(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfitvenda> fetchLfitvendaById(LfitvendaRequest lfitvendaRequest)
	{ 
		return getLfitvendaDAC().fetchLfitvendaById(lfitvendaRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitvenda.bcl.ILfitvendaBCL#insertLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitvenda> insertLfitvenda(LfitvendaRequest lfitvendaRequest) 
	{  

		InternalResultsResponse<Lfitvenda> response = getLfitvendaDAC().insertLfitvenda(lfitvendaRequest);

		if (!response.isInError())
		{  
			Lfitvenda lfitvenda = response.getFirstResult();
			lfitvendaRequest.setLfitvenda(lfitvenda);

			SearchParameter lfitvendaParameter = new SearchParameter(PropertyEnum.LFITVENDA_ID, String.valueOf(lfitvenda.getId()));
			lfitvendaRequest.getSearchLight().addSearchParameter(lfitvendaParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfitvendaRequest, LCActionTypeEnum.INSERT_LFITVENDA, null);
			lfitvendaRequest.getSearchLight().getSearchParameters().remove(lfitvendaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitvenda.bcl.ILfitvendaBCL#deleteLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfitvenda(LfitvendaRequest lfitvendaRequest)
	{
		InternalResultsResponse<Lfitvenda> lfitvendaResponse = getLfitvendaDAC().fetchLfitvendaById(lfitvendaRequest);
		InternalResponse response = new InternalResponse();

		if (lfitvendaResponse.isInError()) 
		{
			response.setStatus(lfitvendaResponse.getStatus());
			response.addMessages(lfitvendaResponse.getMessageInfoList());
			return response;
		} 

		response = getLfitvendaDAC().deleteLfitvenda(lfitvendaRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfitvenda lfitvenda = lfitvendaResponse.getFirstResult();
		lfitvendaRequest.setLfitvenda(lfitvenda);

		SearchParameter lfitvendaParameter = new SearchParameter(PropertyEnum.LFITVENDA_ID, String.valueOf(lfitvenda.getId()));
		lfitvendaRequest.getSearchLight().addSearchParameter(lfitvendaParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfitvendaRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfitvendaRequest.getSearchLight().getSearchParameters().remove(lfitvendaParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitvenda.bcl.ILfitvendaBCL#updateLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitvenda> updateLfitvenda(LfitvendaRequest lfitvendaRequest)
	{ 
		InternalResultsResponse<Lfitvenda> response = getLfitvendaDAC().updateLfitvenda(lfitvendaRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfitvendaRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfitvendaRequest the lfitvenda request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitvendaRequest lfitvendaRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfitvendaRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfitvendaRequest the lfitvenda request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitvendaRequest lfitvendaRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfitvendaRequest.getLfitvenda()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfitvenda lfitvenda = lfitvendaRequest.getLfitvenda();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfitvenda.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfitvenda.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfitvendaRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfitvendaRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
