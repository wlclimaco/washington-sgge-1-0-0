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
import com.sensus.mlc.tabela.bcl.ITabelaBCL;
import com.sensus.mlc.tabela.dac.ITabelaDAC;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
import com.sensus.mlc.tabela.model.request.TabelaRequest;

/** 
 * The Class TabelaBCLImpl. 
 */ 
public class TabelaBCLImpl implements ITabelaBCL
{

	/**  The Constant SEPARATOR. */ 
	private static final String SEPARATOR = " - ";

	/**  The tabela dac. */ 
	private ITabelaDAC tabelaDAC;



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
	 * Spring Sets the tabela dac. 
	 *  
	 * @param iTabelaDAC the new tabela dac 
	 */ 
	public void setTabelaDAC(ITabelaDAC iTabelaDAC)
	{ 
		tabelaDAC = iTabelaDAC;
	} 

	/** 
	 * Gets the tabela dac. 
	 * 
	 * @return the tabela dac 
	 */ 
	public ITabelaDAC getTabelaDAC()
	{
		return tabelaDAC;
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
	 * @see com.sensus.mlc.tabela.bcl.ITabelaBCL#fetchAllTabelas(InquiryTabelaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Tabela> fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest)
	{
		return getTabelaDAC().fetchAllTabela(inquiryTabelaRequest);
	}

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.tabela.bcl.ITabelaBCL#fetchTabelaById(com.sensus.mlc.tabela.model.request.TabelaRequest)  
	 */ 
	@Override
	public InternalResultsResponse<Tabela> fetchTabelaById(TabelaRequest tabelaRequest)
	{ 
		return getTabelaDAC().fetchTabelaById(tabelaRequest);
	}
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tabela.bcl.ITabelaBCL#insertTabela(com.sensus.mlc.tabela.model.request.TabelaRequest) 
	 */ 
	@Override 
	public InternalResultsResponse<Tabela> insertTabela(TabelaRequest tabelaRequest) 
	{  

		InternalResultsResponse<Tabela> response = getTabelaDAC().insertTabela(tabelaRequest);

		if (!response.isInError())
		{  
			Tabela tabela = response.getFirstResult();
			tabelaRequest.setTabela(tabela);

			SearchParameter tabelaParameter = new SearchParameter(PropertyEnum.TABELA_ID, String.valueOf(tabela.getId()));
			tabelaRequest.getSearchLight().addSearchParameter(tabelaParameter);
			InternalResultsResponse<Process> processResponse = 
					this.insertProcess(tabelaRequest, LCActionTypeEnum.INSERT_TABELA, null);
			tabelaRequest.getSearchLight().getSearchParameters().remove(tabelaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;

	}  

	/* 
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tabela.bcl.ITabelaBCL#deleteTabela(com.sensus.mlc.tabela.model.request.TabelaRequest)
	 */ 
	@Override 
	public InternalResponse deleteTabela(TabelaRequest tabelaRequest)
	{
		InternalResultsResponse<Tabela> tabelaResponse = getTabelaDAC().fetchTabelaById(tabelaRequest);
		InternalResponse response = new InternalResponse();

		if (tabelaResponse.isInError()) 
		{
			response.setStatus(tabelaResponse.getStatus());
			response.addMessages(tabelaResponse.getMessageInfoList());
			return response;
		} 

		response = getTabelaDAC().deleteTabela(tabelaRequest);

		if (response.isInError()) 
		{
			return response;
		}

		Tabela tabela = tabelaResponse.getFirstResult();
		tabelaRequest.setTabela(tabela);

		SearchParameter tabelaParameter = new SearchParameter(PropertyEnum.TABELA_ID, String.valueOf(tabela.getId()));
		tabelaRequest.getSearchLight().addSearchParameter(tabelaParameter);

		InternalResultsResponse<Process> processResponse =  
				this.insertProcess(tabelaRequest, LCActionTypeEnum.DELETE_TAG, null);
		tabelaRequest.getSearchLight().getSearchParameters().remove(tabelaParameter);

		if (processResponse.isInError())  
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		} 

		return response;
	}  
   
	/*  
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tabela.bcl.ITabelaBCL#updateTabela(com.sensus.mlc.tabela.model.request.TabelaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Tabela> updateTabela(TabelaRequest tabelaRequest)
	{ 
		InternalResultsResponse<Empresa> response = getTabelaDAC().updateTabela(tabelaRequest);

		if (!response.isInError()) 
		{ 
			InternalResponse processResponse = insertProcess(tabelaRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	} 

	/** 
	 * Insert process.
	 * 
	 * @param tabelaRequest the tabela request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(TabelaRequest tabelaRequest, LCActionTypeEnum lcActionType,
			String processDescription) 
			{  
		return insertProcess(tabelaRequest, lcActionType, processDescription, null);
			}

	/** 
	 * Insert process. 
	 *  
	 * @param tabelaRequest the tabela request 
	 * @param lcActionType the lc action type 
	 * @param processDescription the process description 
	 * @param deactivatedLights the deactivated lights   
	 * @return the internal results response 
	 */ 
	private InternalResultsResponse<Process> insertProcess(TabelaRequest tabelaRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)  
	{
		if(ValidationUtil.isNull(tabelaRequest.getTabela()))
		{ 
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		} 

		Tabela tabela = tabelaRequest.getTabela();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getCodemp())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, empresa.getCodemp().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(empresaRequest);
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

		ProcessRequest processRequest = createProcessRequest(tabelaRequest, process);
		processRequest.setProcessItemFailureList(  
				createProcessItemWithFailure(  
						deactivatedLights,   
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	} 
} 


 
 
