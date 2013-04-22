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
import com.sensus.mlc.lftabicms.txt.bcl.ILftabicms.txtBCL;
import com.sensus.mlc.lftabicms.txt.dac.ILftabicms.txtDAC;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txt;
import com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest;

/** 
 * The Class Lftabicms.txtBCLImpl. 
 */ 
public class Lftabicms.txtBCLImpl implements ILftabicms.txtBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lftabicms.txt dac. */ 
	private ILftabicms.txtDAC lftabicms.txtDAC;



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
	 * Spring Sets the lftabicms.txt dac. 
	 *  
	 * @param iLftabicms.txtDAC the new lftabicms.txt dac 
	 */ 
	public void setLftabicms.txtDAC(ILftabicms.txtDAC iLftabicms.txtDAC)
	{ 
		lftabicms.txtDAC = iLftabicms.txtDAC;
	} 

	/** 
	 * Gets the lftabicms.txt dac. 
	 * 
	 * @return the lftabicms.txt dac 
	 */ 
	public ILftabicms.txtDAC getLftabicms.txtDAC()
	{
		return lftabicms.txtDAC;
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
	 * @see com.sensus.mlc.lftabicms.txt.bcl.ILftabicms.txtBCL#fetchAllLftabicms.txts(InquiryLftabicms.txtRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lftabicms.txt> fetchAllLftabicms.txt(InquiryLftabicms.txtRequest inquiryLftabicms.txtRequest)
	{
		return getLftabicms.txtDAC().fetchAllLftabicms.txt(inquiryLftabicms.txtRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lftabicms.txt.bcl.ILftabicms.txtBCL#fetchLftabicms.txtById(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lftabicms.txt> fetchLftabicms.txtById(Lftabicms.txtRequest lftabicms.txtRequest)
	{ 
		return getLftabicms.txtDAC().fetchLftabicms.txtById(lftabicms.txtRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftabicms.txt.bcl.ILftabicms.txtBCL#insertLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lftabicms.txt> insertLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest) 
	{  

		InternalResultsResponse<Lftabicms.txt> response = getLftabicms.txtDAC().insertLftabicms.txt(lftabicms.txtRequest);

		if (!response.isInError())
		{  
			Lftabicms.txt lftabicms.txt = response.getFirstResult();
			lftabicms.txtRequest.setLftabicms.txt(lftabicms.txt);

			SearchParameter lftabicms.txtParameter = new SearchParameter(PropertyEnum.LFTABICMS.TXT_ID, String.valueOf(lftabicms.txt.getId()));
			lftabicms.txtRequest.getSearchLight().addSearchParameter(lftabicms.txtParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lftabicms.txtRequest, LCActionTypeEnum.INSERT_LFTABICMS.TXT, null);
			lftabicms.txtRequest.getSearchLight().getSearchParameters().remove(lftabicms.txtParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftabicms.txt.bcl.ILftabicms.txtBCL#deleteLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)
	 */ 
	@Override 
	public InternalResponse deleteLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest)
	{
		InternalResultsResponse<Lftabicms.txt> lftabicms.txtResponse = getLftabicms.txtDAC().fetchLftabicms.txtById(lftabicms.txtRequest);
		InternalResponse response = new InternalResponse();

		if (lftabicms.txtResponse.isInError()) 
		{
			response.setStatus(lftabicms.txtResponse.getStatus());
			response.addMessages(lftabicms.txtResponse.getMessageInfoList());
			return response;
		} 

		response = getLftabicms.txtDAC().deleteLftabicms.txt(lftabicms.txtRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lftabicms.txt lftabicms.txt = lftabicms.txtResponse.getFirstResult();
		lftabicms.txtRequest.setLftabicms.txt(lftabicms.txt);

		SearchParameter lftabicms.txtParameter = new SearchParameter(PropertyEnum.LFTABICMS.TXT_ID, String.valueOf(lftabicms.txt.getId()));
		lftabicms.txtRequest.getSearchLight().addSearchParameter(lftabicms.txtParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lftabicms.txtRequest, LCActionTypeEnum.DELETE_TAG, null);
		lftabicms.txtRequest.getSearchLight().getSearchParameters().remove(lftabicms.txtParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftabicms.txt.bcl.ILftabicms.txtBCL#updateLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lftabicms.txt> updateLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest)
	{ 
		InternalResultsResponse<Lftabicms.txt> response = getLftabicms.txtDAC().updateLftabicms.txt(lftabicms.txtRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lftabicms.txtRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lftabicms.txtRequest the lftabicms.txt request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(Lftabicms.txtRequest lftabicms.txtRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lftabicms.txtRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lftabicms.txtRequest the lftabicms.txt request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(Lftabicms.txtRequest lftabicms.txtRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lftabicms.txtRequest.getLftabicms.txt()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lftabicms.txt lftabicms.txt = lftabicms.txtRequest.getLftabicms.txt();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lftabicms.txt.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lftabicms.txt.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lftabicms.txtRequest);
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

		ProcessRequest processRequest = createProcessRequest(lftabicms.txtRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
