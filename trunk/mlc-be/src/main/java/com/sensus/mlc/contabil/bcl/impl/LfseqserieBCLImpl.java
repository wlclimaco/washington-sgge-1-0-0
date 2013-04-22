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
import com.sensus.mlc.lfseqserie.bcl.ILfseqserieBCL;
import com.sensus.mlc.lfseqserie.dac.ILfseqserieDAC;
import com.sensus.mlc.lfseqserie.model.Lfseqserie;
import com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest;

/** 
 * The Class LfseqserieBCLImpl. 
 */ 
public class LfseqserieBCLImpl implements ILfseqserieBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The lfseqserie dac. */ 
	private ILfseqserieDAC lfseqserieDAC;



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
	 * Spring Sets the lfseqserie dac. 
	 *  
	 * @param iLfseqserieDAC the new lfseqserie dac 
	 */ 
	public void setLfseqserieDAC(ILfseqserieDAC iLfseqserieDAC)
	{ 
		lfseqserieDAC = iLfseqserieDAC;
	} 

	/** 
	 * Gets the lfseqserie dac. 
	 * 
	 * @return the lfseqserie dac 
	 */ 
	public ILfseqserieDAC getLfseqserieDAC()
	{
		return lfseqserieDAC;
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
	 * @see com.sensus.mlc.lfseqserie.bcl.ILfseqserieBCL#fetchAllLfseqseries(InquiryLfseqserieRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfseqserie> fetchAllLfseqserie(InquiryLfseqserieRequest inquiryLfseqserieRequest)
	{
		return getLfseqserieDAC().fetchAllLfseqserie(inquiryLfseqserieRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.lfseqserie.bcl.ILfseqserieBCL#fetchLfseqserieById(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Lfseqserie> fetchLfseqserieById(LfseqserieRequest lfseqserieRequest)
	{ 
		return getLfseqserieDAC().fetchLfseqserieById(lfseqserieRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfseqserie.bcl.ILfseqserieBCL#insertLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Lfseqserie> insertLfseqserie(LfseqserieRequest lfseqserieRequest) 
	{  

		InternalResultsResponse<Lfseqserie> response = getLfseqserieDAC().insertLfseqserie(lfseqserieRequest);

		if (!response.isInError())
		{  
			Lfseqserie lfseqserie = response.getFirstResult();
			lfseqserieRequest.setLfseqserie(lfseqserie);

			SearchParameter lfseqserieParameter = new SearchParameter(PropertyEnum.LFSEQSERIE_ID, String.valueOf(lfseqserie.getId()));
			lfseqserieRequest.getSearchLight().addSearchParameter(lfseqserieParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(lfseqserieRequest, LCActionTypeEnum.INSERT_LFSEQSERIE, null);
			lfseqserieRequest.getSearchLight().getSearchParameters().remove(lfseqserieParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfseqserie.bcl.ILfseqserieBCL#deleteLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)
	 */ 
	@Override 
	public InternalResponse deleteLfseqserie(LfseqserieRequest lfseqserieRequest)
	{
		InternalResultsResponse<Lfseqserie> lfseqserieResponse = getLfseqserieDAC().fetchLfseqserieById(lfseqserieRequest);
		InternalResponse response = new InternalResponse();

		if (lfseqserieResponse.isInError()) 
		{
			response.setStatus(lfseqserieResponse.getStatus());
			response.addMessages(lfseqserieResponse.getMessageInfoList());
			return response;
		} 

		response = getLfseqserieDAC().deleteLfseqserie(lfseqserieRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Lfseqserie lfseqserie = lfseqserieResponse.getFirstResult();
		lfseqserieRequest.setLfseqserie(lfseqserie);

		SearchParameter lfseqserieParameter = new SearchParameter(PropertyEnum.LFSEQSERIE_ID, String.valueOf(lfseqserie.getId()));
		lfseqserieRequest.getSearchLight().addSearchParameter(lfseqserieParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(lfseqserieRequest, LCActionTypeEnum.DELETE_TAG, null);
		lfseqserieRequest.getSearchLight().getSearchParameters().remove(lfseqserieParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfseqserie.bcl.ILfseqserieBCL#updateLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfseqserie> updateLfseqserie(LfseqserieRequest lfseqserieRequest)
	{ 
		InternalResultsResponse<Lfseqserie> response = getLfseqserieDAC().updateLfseqserie(lfseqserieRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(lfseqserieRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param lfseqserieRequest the lfseqserie request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfseqserieRequest lfseqserieRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(lfseqserieRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param lfseqserieRequest the lfseqserie request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(LfseqserieRequest lfseqserieRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(lfseqserieRequest.getLfseqserie()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Lfseqserie lfseqserie = lfseqserieRequest.getLfseqserie();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(lfseqserie.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, lfseqserie.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(lfseqserieRequest);
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

		ProcessRequest processRequest = createProcessRequest(lfseqserieRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
