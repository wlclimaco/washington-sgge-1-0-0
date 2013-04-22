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
import com.sensus.mlc.lfserie.bcl.ILfserieBCL;
import com.sensus.mlc.lfserie.dac.ILfserieDAC;
import com.sensus.mlc.lfserie.model.Lfserie;
import com.sensus.mlc.lfserie.model.request.InquiryLfserieRequest;
import com.sensus.mlc.lfserie.model.request.LfserieRequest;

/** 
 * The Class LfserieBCLImpl. 
 */ 
public class LfserieBCLImpl implements ILfserieBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfserie dac. */ 
	private ILfserieDAC lfserieDAC;



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
	 * Spring Sets the lfserie dac. 
	 *  
	 * @param iLfserieDAC the new lfserie dac 
	 */ 
	public void setLfserieDAC(ILfserieDAC iLfserieDAC)
	{ 
		lfserieDAC = iLfserieDAC;
	} 

	/** 
	 * Gets the lfserie dac. 
	 * 
	 * @return the lfserie dac 
	 */ 
	public ILfserieDAC getLfserieDAC()
	{
		return lfserieDAC;
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
	 * @see com.sensus.mlc.lfserie.bcl.ILfserieBCL#fetchAllLfseries(InquiryLfserieRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfserie> fetchAllLfserie(InquiryLfserieRequest inquiryLfserieRequest)
	{
		return getLfserieDAC().fetchAllLfserie(inquiryLfserieRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfserie.bcl.ILfserieBCL#fetchLfserieById(com.sensus.mlc.lfserie.model.request.LfserieRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfserie> fetchLfserieById(LfserieRequest lfserieRequest)
	{ 
		return getLfserieDAC().fetchLfserieById(lfserieRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfserie.bcl.ILfserieBCL#insertLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfserie> insertLfserie(LfserieRequest lfserieRequest) 
	{  

		InternalResultsResponse<Lfserie> response = getLfserieDAC().insertLfserie(lfserieRequest);

		if (!response.isInError())
		{  
			Lfserie lfserie = response.getFirstResult();
			lfserieRequest.setLfserie(lfserie);

			SearchParameter lfserieParameter = new SearchParameter(PropertyEnum.LFSERIE_ID, String.valueOf(lfserie.getId()));
			lfserieRequest.getSearchLight().addSearchParameter(lfserieParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfserieRequest, LCActionTypeEnum.INSERT_LFSERIE, null);
			lfserieRequest.getSearchLight().getSearchParameters().remove(lfserieParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfserie.bcl.ILfserieBCL#deleteLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfserie(LfserieRequest lfserieRequest)
	{
		InternalResultsResponse<Lfserie> lfserieResponse = getLfserieDAC().fetchLfserieById(lfserieRequest);
		InternalResponse response = new InternalResponse();

		if (lfserieResponse.isInError()) 
		{
			response.setStatus(lfserieResponse.getStatus());
			response.addMessages(lfserieResponse.getMessageInfoList());
			return response;
		} 

		response = getLfserieDAC().deleteLfserie(lfserieRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfserie lfserie = lfserieResponse.getFirstResult();
		lfserieRequest.setLfserie(lfserie);

		SearchParameter lfserieParameter = new SearchParameter(PropertyEnum.LFSERIE_ID, String.valueOf(lfserie.getId()));
		lfserieRequest.getSearchLight().addSearchParameter(lfserieParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfserieRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfserieRequest.getSearchLight().getSearchParameters().remove(lfserieParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfserie.bcl.ILfserieBCL#updateLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfserie> updateLfserie(LfserieRequest lfserieRequest)
	{ 
		InternalResultsResponse<Lfserie> response = getLfserieDAC().updateLfserie(lfserieRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfserieRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfserieRequest the lfserie request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfserieRequest lfserieRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfserieRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfserieRequest the lfserie request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfserieRequest lfserieRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfserieRequest.getLfserie()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfserie lfserie = lfserieRequest.getLfserie();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfserie.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfserie.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfserieRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfserieRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
