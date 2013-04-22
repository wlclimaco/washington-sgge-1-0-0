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
import com.sensus.mlc.lffretecompra.bcl.ILffretecompraBCL;
import com.sensus.mlc.lffretecompra.dac.ILffretecompraDAC;
import com.sensus.mlc.lffretecompra.model.Lffretecompra;
import com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest;

/** 
 * The Class LffretecompraBCLImpl. 
 */ 
public class LffretecompraBCLImpl implements ILffretecompraBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lffretecompra dac. */ 
	private ILffretecompraDAC lffretecompraDAC;



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
	 * Spring Sets the lffretecompra dac. 
	 *  
	 * @param iLffretecompraDAC the new lffretecompra dac 
	 */ 
	public void setLffretecompraDAC(ILffretecompraDAC iLffretecompraDAC)
	{ 
		lffretecompraDAC = iLffretecompraDAC;
	} 

	/** 
	 * Gets the lffretecompra dac. 
	 * 
	 * @return the lffretecompra dac 
	 */ 
	public ILffretecompraDAC getLffretecompraDAC()
	{
		return lffretecompraDAC;
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
	 * @see com.sensus.mlc.lffretecompra.bcl.ILffretecompraBCL#fetchAllLffretecompras(InquiryLffretecompraRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lffretecompra> fetchAllLffretecompra(InquiryLffretecompraRequest inquiryLffretecompraRequest)
	{
		return getLffretecompraDAC().fetchAllLffretecompra(inquiryLffretecompraRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lffretecompra.bcl.ILffretecompraBCL#fetchLffretecompraById(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lffretecompra> fetchLffretecompraById(LffretecompraRequest lffretecompraRequest)
	{ 
		return getLffretecompraDAC().fetchLffretecompraById(lffretecompraRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretecompra.bcl.ILffretecompraBCL#insertLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lffretecompra> insertLffretecompra(LffretecompraRequest lffretecompraRequest) 
	{  

		InternalResultsResponse<Lffretecompra> response = getLffretecompraDAC().insertLffretecompra(lffretecompraRequest);

		if (!response.isInError())
		{  
			Lffretecompra lffretecompra = response.getFirstResult();
			lffretecompraRequest.setLffretecompra(lffretecompra);

			SearchParameter lffretecompraParameter = new SearchParameter(PropertyEnum.LFFRETECOMPRA_ID, String.valueOf(lffretecompra.getId()));
			lffretecompraRequest.getSearchLight().addSearchParameter(lffretecompraParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lffretecompraRequest, LCActionTypeEnum.INSERT_LFFRETECOMPRA, null);
			lffretecompraRequest.getSearchLight().getSearchParameters().remove(lffretecompraParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretecompra.bcl.ILffretecompraBCL#deleteLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)
	 */ 
	@Override 
	public InternalResponse deleteLffretecompra(LffretecompraRequest lffretecompraRequest)
	{
		InternalResultsResponse<Lffretecompra> lffretecompraResponse = getLffretecompraDAC().fetchLffretecompraById(lffretecompraRequest);
		InternalResponse response = new InternalResponse();

		if (lffretecompraResponse.isInError()) 
		{
			response.setStatus(lffretecompraResponse.getStatus());
			response.addMessages(lffretecompraResponse.getMessageInfoList());
			return response;
		} 

		response = getLffretecompraDAC().deleteLffretecompra(lffretecompraRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lffretecompra lffretecompra = lffretecompraResponse.getFirstResult();
		lffretecompraRequest.setLffretecompra(lffretecompra);

		SearchParameter lffretecompraParameter = new SearchParameter(PropertyEnum.LFFRETECOMPRA_ID, String.valueOf(lffretecompra.getId()));
		lffretecompraRequest.getSearchLight().addSearchParameter(lffretecompraParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lffretecompraRequest, LCActionTypeEnum.DELETE_TAG, null);
		lffretecompraRequest.getSearchLight().getSearchParameters().remove(lffretecompraParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretecompra.bcl.ILffretecompraBCL#updateLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lffretecompra> updateLffretecompra(LffretecompraRequest lffretecompraRequest)
	{ 
		InternalResultsResponse<Lffretecompra> response = getLffretecompraDAC().updateLffretecompra(lffretecompraRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lffretecompraRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lffretecompraRequest the lffretecompra request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LffretecompraRequest lffretecompraRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lffretecompraRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lffretecompraRequest the lffretecompra request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LffretecompraRequest lffretecompraRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lffretecompraRequest.getLffretecompra()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lffretecompra lffretecompra = lffretecompraRequest.getLffretecompra();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lffretecompra.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lffretecompra.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lffretecompraRequest);
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

		ProcessRequest processRequest = createProcessRequest(lffretecompraRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
