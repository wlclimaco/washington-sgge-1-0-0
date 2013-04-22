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
import com.sensus.mlc.lfitcompra.bcl.ILfitcompraBCL;
import com.sensus.mlc.lfitcompra.dac.ILfitcompraDAC;
import com.sensus.mlc.lfitcompra.model.Lfitcompra;
import com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest;

/** 
 * The Class LfitcompraBCLImpl. 
 */ 
public class LfitcompraBCLImpl implements ILfitcompraBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfitcompra dac. */ 
	private ILfitcompraDAC lfitcompraDAC;



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
	 * Spring Sets the lfitcompra dac. 
	 *  
	 * @param iLfitcompraDAC the new lfitcompra dac 
	 */ 
	public void setLfitcompraDAC(ILfitcompraDAC iLfitcompraDAC)
	{ 
		lfitcompraDAC = iLfitcompraDAC;
	} 

	/** 
	 * Gets the lfitcompra dac. 
	 * 
	 * @return the lfitcompra dac 
	 */ 
	public ILfitcompraDAC getLfitcompraDAC()
	{
		return lfitcompraDAC;
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
	 * @see com.sensus.mlc.lfitcompra.bcl.ILfitcompraBCL#fetchAllLfitcompras(InquiryLfitcompraRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcompra> fetchAllLfitcompra(InquiryLfitcompraRequest inquiryLfitcompraRequest)
	{
		return getLfitcompraDAC().fetchAllLfitcompra(inquiryLfitcompraRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfitcompra.bcl.ILfitcompraBCL#fetchLfitcompraById(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfitcompra> fetchLfitcompraById(LfitcompraRequest lfitcompraRequest)
	{ 
		return getLfitcompraDAC().fetchLfitcompraById(lfitcompraRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcompra.bcl.ILfitcompraBCL#insertLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcompra> insertLfitcompra(LfitcompraRequest lfitcompraRequest) 
	{  

		InternalResultsResponse<Lfitcompra> response = getLfitcompraDAC().insertLfitcompra(lfitcompraRequest);

		if (!response.isInError())
		{  
			Lfitcompra lfitcompra = response.getFirstResult();
			lfitcompraRequest.setLfitcompra(lfitcompra);

			SearchParameter lfitcompraParameter = new SearchParameter(PropertyEnum.LFITCOMPRA_ID, String.valueOf(lfitcompra.getId()));
			lfitcompraRequest.getSearchLight().addSearchParameter(lfitcompraParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfitcompraRequest, LCActionTypeEnum.INSERT_LFITCOMPRA, null);
			lfitcompraRequest.getSearchLight().getSearchParameters().remove(lfitcompraParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcompra.bcl.ILfitcompraBCL#deleteLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfitcompra(LfitcompraRequest lfitcompraRequest)
	{
		InternalResultsResponse<Lfitcompra> lfitcompraResponse = getLfitcompraDAC().fetchLfitcompraById(lfitcompraRequest);
		InternalResponse response = new InternalResponse();

		if (lfitcompraResponse.isInError()) 
		{
			response.setStatus(lfitcompraResponse.getStatus());
			response.addMessages(lfitcompraResponse.getMessageInfoList());
			return response;
		} 

		response = getLfitcompraDAC().deleteLfitcompra(lfitcompraRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfitcompra lfitcompra = lfitcompraResponse.getFirstResult();
		lfitcompraRequest.setLfitcompra(lfitcompra);

		SearchParameter lfitcompraParameter = new SearchParameter(PropertyEnum.LFITCOMPRA_ID, String.valueOf(lfitcompra.getId()));
		lfitcompraRequest.getSearchLight().addSearchParameter(lfitcompraParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfitcompraRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfitcompraRequest.getSearchLight().getSearchParameters().remove(lfitcompraParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcompra.bcl.ILfitcompraBCL#updateLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcompra> updateLfitcompra(LfitcompraRequest lfitcompraRequest)
	{ 
		InternalResultsResponse<Lfitcompra> response = getLfitcompraDAC().updateLfitcompra(lfitcompraRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfitcompraRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfitcompraRequest the lfitcompra request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitcompraRequest lfitcompraRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfitcompraRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfitcompraRequest the lfitcompra request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfitcompraRequest lfitcompraRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfitcompraRequest.getLfitcompra()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfitcompra lfitcompra = lfitcompraRequest.getLfitcompra();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfitcompra.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfitcompra.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfitcompraRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfitcompraRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
