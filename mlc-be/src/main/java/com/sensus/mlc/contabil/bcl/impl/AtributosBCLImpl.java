package com.sensus.mlc.tabela.bcl.impl;

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
import com.sensus.mlc.atributos.bcl.IAtributosBCL;
import com.sensus.mlc.atributos.dac.IAtributosDAC;
import com.sensus.mlc.atributos.model.Atributos;
import com.sensus.mlc.atributos.model.request.InquiryAtributosRequest;
import com.sensus.mlc.atributos.model.request.AtributosRequest;

/** 
 * The Class AtributosBCLImpl. 
 */ 
public class AtributosBCLImpl implements IAtributosBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The atributos dac. */ 
	private IAtributosDAC atributosDAC;



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
	 * Spring Sets the atributos dac. 
	 *  
	 * @param iAtributosDAC the new atributos dac 
	 */ 
	public void setAtributosDAC(IAtributosDAC iAtributosDAC)
	{ 
		atributosDAC = iAtributosDAC;
	} 

	/** 
	 * Gets the atributos dac. 
	 * 
	 * @return the atributos dac 
	 */ 
	public IAtributosDAC getAtributosDAC()
	{
		return atributosDAC;
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
	 * @see com.sensus.mlc.atributos.bcl.IAtributosBCL#fetchAllAtributoss(InquiryAtributosRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Atributos> fetchAllAtributos(InquiryAtributosRequest inquiryAtributosRequest)
	{
		return getAtributosDAC().fetchAllAtributos(inquiryAtributosRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.atributos.bcl.IAtributosBCL#fetchAtributosById(com.sensus.mlc.atributos.model.request.AtributosRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Atributos> fetchAtributosById(AtributosRequest atributosRequest)
	{ 
		return getAtributosDAC().fetchAtributosById(atributosRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.atributos.bcl.IAtributosBCL#insertAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Atributos> insertAtributos(AtributosRequest atributosRequest) 
	{  

		InternalResultsResponse<Atributos> response = getAtributosDAC().insertAtributos(atributosRequest);

		if (!response.isInError())
		{  
			Atributos atributos = response.getFirstResult();
			atributosRequest.setAtributos(atributos);

			SearchParameter atributosParameter = new SearchParameter(PropertyEnum.ATRIBUTOS_ID, String.valueOf(atributos.getId()));
			atributosRequest.getSearchLight().addSearchParameter(atributosParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(atributosRequest, LCActionTypeEnum.INSERT_ATRIBUTOS, null);
			atributosRequest.getSearchLight().getSearchParameters().remove(atributosParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.atributos.bcl.IAtributosBCL#deleteAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */ 
	@Override 
	public InternalResponse deleteAtributos(AtributosRequest atributosRequest)
	{
		InternalResultsResponse<Atributos> atributosResponse = getAtributosDAC().fetchAtributosById(atributosRequest);
		InternalResponse response = new InternalResponse();

		if (atributosResponse.isInError()) 
		{
			response.setStatus(atributosResponse.getStatus());
			response.addMessages(atributosResponse.getMessageInfoList());
			return response;
		} 

		response = getAtributosDAC().deleteAtributos(atributosRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Atributos atributos = atributosResponse.getFirstResult();
		atributosRequest.setAtributos(atributos);

		SearchParameter atributosParameter = new SearchParameter(PropertyEnum.ATRIBUTOS_ID, String.valueOf(atributos.getId()));
		atributosRequest.getSearchLight().addSearchParameter(atributosParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(atributosRequest, LCActionTypeEnum.DELETE_TAG, null);
		atributosRequest.getSearchLight().getSearchParameters().remove(atributosParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.atributos.bcl.IAtributosBCL#updateAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Atributos> updateAtributos(AtributosRequest atributosRequest)
	{ 
		InternalResultsResponse<Atributos> response = getAtributosDAC().updateAtributos(atributosRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(atributosRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param atributosRequest the atributos request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(AtributosRequest atributosRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(atributosRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param atributosRequest the atributos request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(AtributosRequest atributosRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(atributosRequest.getAtributos()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Atributos atributos = atributosRequest.getAtributos();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(atributos.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, atributos.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(atributosRequest);
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

		ProcessRequest processRequest = createProcessRequest(atributosRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
