package com.sensus.mlc.gestao.bcl.impl;

import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.gestao.bcl.IPaisBCL;
import com.sensus.mlc.gestao.dac.IPaisDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Pais;
import com.sensus.mlc.gestao.model.request.InquiryPaisRequest;
import com.sensus.mlc.gestao.model.request.PaisRequest;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

/**
 * The Class PaisBCLImpl.
 */
public class PaisBCLImpl implements IPaisBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The pais dac. */
	private IPaisDAC paisDAC;



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
	 * Spring Sets the pais dac.
	 *
	 * @param iPaisDAC the new pais dac
	 */
	public void setPaisDAC(IPaisDAC iPaisDAC)
	{
		paisDAC = iPaisDAC;
	}

	/**
	 * Gets the pais dac.
	 *
	 * @return the pais dac
	 */
	public IPaisDAC getPaisDAC()
	{
		return paisDAC;
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
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#fetchAllPaiss(InquiryPaisRequest)
	 */
	@Override
	public InternalResultsResponse<Pais> fetchAllPais(InquiryPaisRequest inquiryPaisRequest)
	{
		return getPaisDAC().fetchAllPais(inquiryPaisRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#fetchPaisById(com.sensus.mlc.pais.model.request.PaisRequest)
	 */
	@Override
	public InternalResultsResponse<Pais> fetchPaisById(PaisRequest paisRequest)
	{
		return getPaisDAC().fetchPaisById(paisRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#insertPais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */
	@Override
	public InternalResultsResponse<Pais> insertPais(PaisRequest paisRequest)
	{

		InternalResultsResponse<Pais> response = getPaisDAC().insertPais(paisRequest);

		if (!response.isInError())
		{
			Pais pais = response.getFirstResult();
			paisRequest.setPais(pais);

			SearchParameter paisParameter = new SearchParameter(PropertyEnum.PAIS_ID, String.valueOf(pais.getCodpais()));
			paisRequest.getSearchLight().addSearchParameter(paisParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(paisRequest, LCActionTypeEnum.INSERT_TAG, null);
			paisRequest.getSearchLight().getSearchParameters().remove(paisParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#deletePais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */
	@Override
	public InternalResponse deletePais(PaisRequest paisRequest)
	{
		InternalResultsResponse<Pais> paisResponse = getPaisDAC().fetchPaisById(paisRequest);
		InternalResponse response = new InternalResponse();

		if (paisResponse.isInError())
		{
			response.setStatus(paisResponse.getStatus());
			response.addMessages(paisResponse.getMessageInfoList());
			return response;
		}

		response = getPaisDAC().deletePais(paisRequest);

		if (response.isInError())
		{
			return response;
		}

		Pais pais = paisResponse.getFirstResult();
		paisRequest.setPais(pais);

		SearchParameter paisParameter = new SearchParameter(PropertyEnum.PAIS_ID, String.valueOf(pais.getCodpais()));
		paisRequest.getSearchLight().addSearchParameter(paisParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(paisRequest, LCActionTypeEnum.DELETE_TAG, null);
		paisRequest.getSearchLight().getSearchParameters().remove(paisParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#updatePais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */
	@Override
	public InternalResultsResponse<Pais> updatePais(PaisRequest paisRequest)
	{
		InternalResultsResponse<Pais> response = getPaisDAC().updatePais(paisRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(paisRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param paisRequest the pais request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(PaisRequest paisRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(paisRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param paisRequest the pais request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(PaisRequest paisRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(paisRequest.getPais()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Pais pais = paisRequest.getPais();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(pais.getCodpais())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, pais.getCodpais().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(paisRequest);
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

		ProcessRequest processRequest = createProcessRequest(paisRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Pais> fetchAllPaisTypes(
			InquiryPaisRequest inquirypaisRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Pais> fetchAllPaisFilial(
			PaisRequest paisRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




