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
import com.sensus.mlc.lfmensagem.bcl.ILfmensagemBCL;
import com.sensus.mlc.lfmensagem.dac.ILfmensagemDAC;
import com.sensus.mlc.lfmensagem.model.Lfmensagem;
import com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest;

/** 
 * The Class LfmensagemBCLImpl. 
 */ 
public class LfmensagemBCLImpl implements ILfmensagemBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfmensagem dac. */ 
	private ILfmensagemDAC lfmensagemDAC;



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
	 * Spring Sets the lfmensagem dac. 
	 *  
	 * @param iLfmensagemDAC the new lfmensagem dac 
	 */ 
	public void setLfmensagemDAC(ILfmensagemDAC iLfmensagemDAC)
	{ 
		lfmensagemDAC = iLfmensagemDAC;
	} 

	/** 
	 * Gets the lfmensagem dac. 
	 * 
	 * @return the lfmensagem dac 
	 */ 
	public ILfmensagemDAC getLfmensagemDAC()
	{
		return lfmensagemDAC;
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
	 * @see com.sensus.mlc.lfmensagem.bcl.ILfmensagemBCL#fetchAllLfmensagems(InquiryLfmensagemRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfmensagem> fetchAllLfmensagem(InquiryLfmensagemRequest inquiryLfmensagemRequest)
	{
		return getLfmensagemDAC().fetchAllLfmensagem(inquiryLfmensagemRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfmensagem.bcl.ILfmensagemBCL#fetchLfmensagemById(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfmensagem> fetchLfmensagemById(LfmensagemRequest lfmensagemRequest)
	{ 
		return getLfmensagemDAC().fetchLfmensagemById(lfmensagemRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmensagem.bcl.ILfmensagemBCL#insertLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfmensagem> insertLfmensagem(LfmensagemRequest lfmensagemRequest) 
	{  

		InternalResultsResponse<Lfmensagem> response = getLfmensagemDAC().insertLfmensagem(lfmensagemRequest);

		if (!response.isInError())
		{  
			Lfmensagem lfmensagem = response.getFirstResult();
			lfmensagemRequest.setLfmensagem(lfmensagem);

			SearchParameter lfmensagemParameter = new SearchParameter(PropertyEnum.LFMENSAGEM_ID, String.valueOf(lfmensagem.getId()));
			lfmensagemRequest.getSearchLight().addSearchParameter(lfmensagemParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfmensagemRequest, LCActionTypeEnum.INSERT_LFMENSAGEM, null);
			lfmensagemRequest.getSearchLight().getSearchParameters().remove(lfmensagemParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmensagem.bcl.ILfmensagemBCL#deleteLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfmensagem(LfmensagemRequest lfmensagemRequest)
	{
		InternalResultsResponse<Lfmensagem> lfmensagemResponse = getLfmensagemDAC().fetchLfmensagemById(lfmensagemRequest);
		InternalResponse response = new InternalResponse();

		if (lfmensagemResponse.isInError()) 
		{
			response.setStatus(lfmensagemResponse.getStatus());
			response.addMessages(lfmensagemResponse.getMessageInfoList());
			return response;
		} 

		response = getLfmensagemDAC().deleteLfmensagem(lfmensagemRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfmensagem lfmensagem = lfmensagemResponse.getFirstResult();
		lfmensagemRequest.setLfmensagem(lfmensagem);

		SearchParameter lfmensagemParameter = new SearchParameter(PropertyEnum.LFMENSAGEM_ID, String.valueOf(lfmensagem.getId()));
		lfmensagemRequest.getSearchLight().addSearchParameter(lfmensagemParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfmensagemRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfmensagemRequest.getSearchLight().getSearchParameters().remove(lfmensagemParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmensagem.bcl.ILfmensagemBCL#updateLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfmensagem> updateLfmensagem(LfmensagemRequest lfmensagemRequest)
	{ 
		InternalResultsResponse<Lfmensagem> response = getLfmensagemDAC().updateLfmensagem(lfmensagemRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfmensagemRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfmensagemRequest the lfmensagem request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfmensagemRequest lfmensagemRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfmensagemRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfmensagemRequest the lfmensagem request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfmensagemRequest lfmensagemRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfmensagemRequest.getLfmensagem()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfmensagem lfmensagem = lfmensagemRequest.getLfmensagem();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfmensagem.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfmensagem.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfmensagemRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfmensagemRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
