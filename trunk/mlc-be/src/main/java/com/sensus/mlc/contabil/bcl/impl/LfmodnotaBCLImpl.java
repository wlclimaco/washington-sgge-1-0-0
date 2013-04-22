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
import com.sensus.mlc.lfmodnota.bcl.ILfmodnotaBCL;
import com.sensus.mlc.lfmodnota.dac.ILfmodnotaDAC;
import com.sensus.mlc.lfmodnota.model.Lfmodnota;
import com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest;

/** 
 * The Class LfmodnotaBCLImpl. 
 */ 
public class LfmodnotaBCLImpl implements ILfmodnotaBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfmodnota dac. */ 
	private ILfmodnotaDAC lfmodnotaDAC;



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
	 * Spring Sets the lfmodnota dac. 
	 *  
	 * @param iLfmodnotaDAC the new lfmodnota dac 
	 */ 
	public void setLfmodnotaDAC(ILfmodnotaDAC iLfmodnotaDAC)
	{ 
		lfmodnotaDAC = iLfmodnotaDAC;
	} 

	/** 
	 * Gets the lfmodnota dac. 
	 * 
	 * @return the lfmodnota dac 
	 */ 
	public ILfmodnotaDAC getLfmodnotaDAC()
	{
		return lfmodnotaDAC;
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
	 * @see com.sensus.mlc.lfmodnota.bcl.ILfmodnotaBCL#fetchAllLfmodnotas(InquiryLfmodnotaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfmodnota> fetchAllLfmodnota(InquiryLfmodnotaRequest inquiryLfmodnotaRequest)
	{
		return getLfmodnotaDAC().fetchAllLfmodnota(inquiryLfmodnotaRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmodnota.bcl.ILfmodnotaBCL#fetchLfmodnotaById(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfmodnota> fetchLfmodnotaById(LfmodnotaRequest lfmodnotaRequest)
	{ 
		return getLfmodnotaDAC().fetchLfmodnotaById(lfmodnotaRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmodnota.bcl.ILfmodnotaBCL#insertLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfmodnota> insertLfmodnota(LfmodnotaRequest lfmodnotaRequest) 
	{  

		InternalResultsResponse<Lfmodnota> response = getLfmodnotaDAC().insertLfmodnota(lfmodnotaRequest);

		if (!response.isInError())
		{  
			Lfmodnota lfmodnota = response.getFirstResult();
			lfmodnotaRequest.setLfmodnota(lfmodnota);

			SearchParameter lfmodnotaParameter = new SearchParameter(PropertyEnum.LFMODNOTA_ID, String.valueOf(lfmodnota.getId()));
			lfmodnotaRequest.getSearchLight().addSearchParameter(lfmodnotaParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfmodnotaRequest, LCActionTypeEnum.INSERT_LFMODNOTA, null);
			lfmodnotaRequest.getSearchLight().getSearchParameters().remove(lfmodnotaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmodnota.bcl.ILfmodnotaBCL#deleteLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfmodnota(LfmodnotaRequest lfmodnotaRequest)
	{
		InternalResultsResponse<Lfmodnota> lfmodnotaResponse = getLfmodnotaDAC().fetchLfmodnotaById(lfmodnotaRequest);
		InternalResponse response = new InternalResponse();

		if (lfmodnotaResponse.isInError()) 
		{
			response.setStatus(lfmodnotaResponse.getStatus());
			response.addMessages(lfmodnotaResponse.getMessageInfoList());
			return response;
		} 

		response = getLfmodnotaDAC().deleteLfmodnota(lfmodnotaRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfmodnota lfmodnota = lfmodnotaResponse.getFirstResult();
		lfmodnotaRequest.setLfmodnota(lfmodnota);

		SearchParameter lfmodnotaParameter = new SearchParameter(PropertyEnum.LFMODNOTA_ID, String.valueOf(lfmodnota.getId()));
		lfmodnotaRequest.getSearchLight().addSearchParameter(lfmodnotaParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfmodnotaRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfmodnotaRequest.getSearchLight().getSearchParameters().remove(lfmodnotaParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmodnota.bcl.ILfmodnotaBCL#updateLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfmodnota> updateLfmodnota(LfmodnotaRequest lfmodnotaRequest)
	{ 
		InternalResultsResponse<Lfmodnota> response = getLfmodnotaDAC().updateLfmodnota(lfmodnotaRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfmodnotaRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfmodnotaRequest the lfmodnota request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfmodnotaRequest lfmodnotaRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfmodnotaRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfmodnotaRequest the lfmodnota request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfmodnotaRequest lfmodnotaRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfmodnotaRequest.getLfmodnota()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfmodnota lfmodnota = lfmodnotaRequest.getLfmodnota();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfmodnota.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfmodnota.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfmodnotaRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfmodnotaRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
