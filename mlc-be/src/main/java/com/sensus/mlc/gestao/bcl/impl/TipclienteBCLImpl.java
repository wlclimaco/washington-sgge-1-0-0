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
import com.sensus.mlc.gestao.bcl.ITipclienteBCL;
import com.sensus.mlc.gestao.dac.ITipclienteDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Tipcliente;
import com.sensus.mlc.gestao.model.request.InquiryTipclienteRequest;
import com.sensus.mlc.gestao.model.request.TipclienteRequest;
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
 * The Class TipclienteBCLImpl.
 */
public class TipclienteBCLImpl implements ITipclienteBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The tipcli dac. */
	private ITipclienteDAC tipcliDAC;



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
	 * Spring Sets the tipcli dac.
	 *
	 * @param iTipclienteDAC the new tipcli dac
	 */
	public void setTipclienteDAC(ITipclienteDAC iTipclienteDAC)
	{
		tipcliDAC = iTipclienteDAC;
	}

	/**
	 * Gets the tipcli dac.
	 *
	 * @return the tipcli dac
	 */
	public ITipclienteDAC getTipclienteDAC()
	{
		return tipcliDAC;
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
	 * @see com.sensus.mlc.tipcli.bcl.ITipclienteBCL#fetchAllTipclientes(InquiryTipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> fetchAllTipcliente(InquiryTipclienteRequest inquiryTipclienteRequest)
	{
		return getTipclienteDAC().fetchAllTipcliente(inquiryTipclienteRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.bcl.ITipclienteBCL#fetchTipclienteById(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> fetchTipclienteById(TipclienteRequest tipcliRequest)
	{
		return getTipclienteDAC().fetchTipclienteById(tipcliRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.bcl.ITipclienteBCL#insertTipcliente(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> insertTipcliente(TipclienteRequest tipcliRequest)
	{

		InternalResultsResponse<Tipcliente> response = getTipclienteDAC().insertTipcliente(tipcliRequest);

		if (!response.isInError())
		{
			Tipcliente tipcli = response.getFirstResult();
			tipcliRequest.setTipcliente(tipcli);

			SearchParameter tipcliParameter = new SearchParameter(PropertyEnum.TIPCLIENTE_ID, String.valueOf(tipcli.getCodtipocli()));
			tipcliRequest.getSearchLight().addSearchParameter(tipcliParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(tipcliRequest, LCActionTypeEnum.INSERT_TAG, null);
			tipcliRequest.getSearchLight().getSearchParameters().remove(tipcliParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.bcl.ITipclienteBCL#deleteTipcliente(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResponse deleteTipcliente(TipclienteRequest tipcliRequest)
	{
		InternalResultsResponse<Tipcliente> tipcliResponse = getTipclienteDAC().fetchTipclienteById(tipcliRequest);
		InternalResponse response = new InternalResponse();

		if (tipcliResponse.isInError())
		{
			response.setStatus(tipcliResponse.getStatus());
			response.addMessages(tipcliResponse.getMessageInfoList());
			return response;
		}

		response = getTipclienteDAC().deleteTipcliente(tipcliRequest);

		if (response.isInError())
		{
			return response;
		}

		Tipcliente tipcli = tipcliResponse.getFirstResult();
		tipcliRequest.setTipcliente(tipcli);

		SearchParameter tipcliParameter = new SearchParameter(PropertyEnum.TIPCLIENTE_ID, String.valueOf(tipcli.getCodtipocli()));
		tipcliRequest.getSearchLight().addSearchParameter(tipcliParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(tipcliRequest, LCActionTypeEnum.DELETE_TAG, null);
		tipcliRequest.getSearchLight().getSearchParameters().remove(tipcliParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.bcl.ITipclienteBCL#updateTipcliente(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> updateTipcliente(TipclienteRequest tipcliRequest)
	{
		InternalResultsResponse<Tipcliente> response = getTipclienteDAC().updateTipcliente(tipcliRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(tipcliRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param tipcliRequest the tipcli request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TipclienteRequest tipcliRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(tipcliRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param tipcliRequest the tipcli request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TipclienteRequest tipcliRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(tipcliRequest.getTipcliente()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Tipcliente tipcli = tipcliRequest.getTipcliente();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(tipcli.getCodtipocli())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, tipcli.getCodtipocli().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(tipcliRequest);
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

		ProcessRequest processRequest = createProcessRequest(tipcliRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Tipcliente> fetchAllTipclienteTypes(
			InquiryTipclienteRequest inquirytipclienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Tipcliente> fetchAllTipclienteFilial(
			TipclienteRequest tipclienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




