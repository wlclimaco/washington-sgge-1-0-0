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
import com.sensus.mlc.lfcsosn.bcl.ILfcsosnBCL;
import com.sensus.mlc.lfcsosn.dac.ILfcsosnDAC;
import com.sensus.mlc.lfcsosn.model.Lfcsosn;
import com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest;

/** 
 * The Class LfcsosnBCLImpl. 
 */ 
public class LfcsosnBCLImpl implements ILfcsosnBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfcsosn dac. */ 
	private ILfcsosnDAC lfcsosnDAC;



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
	 * Spring Sets the lfcsosn dac. 
	 *  
	 * @param iLfcsosnDAC the new lfcsosn dac 
	 */ 
	public void setLfcsosnDAC(ILfcsosnDAC iLfcsosnDAC)
	{ 
		lfcsosnDAC = iLfcsosnDAC;
	} 

	/** 
	 * Gets the lfcsosn dac. 
	 * 
	 * @return the lfcsosn dac 
	 */ 
	public ILfcsosnDAC getLfcsosnDAC()
	{
		return lfcsosnDAC;
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
	 * @see com.sensus.mlc.lfcsosn.bcl.ILfcsosnBCL#fetchAllLfcsosns(InquiryLfcsosnRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfcsosn> fetchAllLfcsosn(InquiryLfcsosnRequest inquiryLfcsosnRequest)
	{
		return getLfcsosnDAC().fetchAllLfcsosn(inquiryLfcsosnRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfcsosn.bcl.ILfcsosnBCL#fetchLfcsosnById(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfcsosn> fetchLfcsosnById(LfcsosnRequest lfcsosnRequest)
	{ 
		return getLfcsosnDAC().fetchLfcsosnById(lfcsosnRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfcsosn.bcl.ILfcsosnBCL#insertLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfcsosn> insertLfcsosn(LfcsosnRequest lfcsosnRequest) 
	{  

		InternalResultsResponse<Lfcsosn> response = getLfcsosnDAC().insertLfcsosn(lfcsosnRequest);

		if (!response.isInError())
		{  
			Lfcsosn lfcsosn = response.getFirstResult();
			lfcsosnRequest.setLfcsosn(lfcsosn);

			SearchParameter lfcsosnParameter = new SearchParameter(PropertyEnum.LFCSOSN_ID, String.valueOf(lfcsosn.getId()));
			lfcsosnRequest.getSearchLight().addSearchParameter(lfcsosnParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfcsosnRequest, LCActionTypeEnum.INSERT_LFCSOSN, null);
			lfcsosnRequest.getSearchLight().getSearchParameters().remove(lfcsosnParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfcsosn.bcl.ILfcsosnBCL#deleteLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfcsosn(LfcsosnRequest lfcsosnRequest)
	{
		InternalResultsResponse<Lfcsosn> lfcsosnResponse = getLfcsosnDAC().fetchLfcsosnById(lfcsosnRequest);
		InternalResponse response = new InternalResponse();

		if (lfcsosnResponse.isInError()) 
		{
			response.setStatus(lfcsosnResponse.getStatus());
			response.addMessages(lfcsosnResponse.getMessageInfoList());
			return response;
		} 

		response = getLfcsosnDAC().deleteLfcsosn(lfcsosnRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfcsosn lfcsosn = lfcsosnResponse.getFirstResult();
		lfcsosnRequest.setLfcsosn(lfcsosn);

		SearchParameter lfcsosnParameter = new SearchParameter(PropertyEnum.LFCSOSN_ID, String.valueOf(lfcsosn.getId()));
		lfcsosnRequest.getSearchLight().addSearchParameter(lfcsosnParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfcsosnRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfcsosnRequest.getSearchLight().getSearchParameters().remove(lfcsosnParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfcsosn.bcl.ILfcsosnBCL#updateLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfcsosn> updateLfcsosn(LfcsosnRequest lfcsosnRequest)
	{ 
		InternalResultsResponse<Lfcsosn> response = getLfcsosnDAC().updateLfcsosn(lfcsosnRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfcsosnRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfcsosnRequest the lfcsosn request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfcsosnRequest lfcsosnRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfcsosnRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfcsosnRequest the lfcsosn request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfcsosnRequest lfcsosnRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfcsosnRequest.getLfcsosn()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfcsosn lfcsosn = lfcsosnRequest.getLfcsosn();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfcsosn.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfcsosn.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfcsosnRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfcsosnRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
