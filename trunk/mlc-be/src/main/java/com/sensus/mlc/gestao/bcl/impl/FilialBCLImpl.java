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
import com.sensus.mlc.gestao.bcl.IFilialBCL;
import com.sensus.mlc.gestao.dac.IFilialDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Filial;
import com.sensus.mlc.gestao.model.request.FilialRequest;
import com.sensus.mlc.gestao.model.request.InquiryFilialRequest;
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
 * The Class FilialBCLImpl.
 */
public class FilialBCLImpl implements IFilialBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The filial dac. */
	private IFilialDAC filialDAC;



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
	 * Spring Sets the filial dac.
	 *
	 * @param iFilialDAC the new filial dac
	 */
	public void setFilialDAC(IFilialDAC iFilialDAC)
	{
		filialDAC = iFilialDAC;
	}

	/**
	 * Gets the filial dac.
	 *
	 * @return the filial dac
	 */
	public IFilialDAC getFilialDAC()
	{
		return filialDAC;
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
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#fetchAllFilials(InquiryFilialRequest)
	 */
	@Override
	public InternalResultsResponse<Filial> fetchAllFilial(InquiryFilialRequest inquiryFilialRequest)
	{
		return getFilialDAC().fetchAllFilial(inquiryFilialRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#fetchFilialById(com.sensus.mlc.filial.model.request.FilialRequest)
	 */
	@Override
	public InternalResultsResponse<Filial> fetchFilialById(FilialRequest filialRequest)
	{
		return getFilialDAC().fetchFilialById(filialRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#insertFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */
	@Override
	public InternalResultsResponse<Filial> insertFilial(FilialRequest filialRequest)
	{

		InternalResultsResponse<Filial> response = getFilialDAC().insertFilial(filialRequest);

		if (!response.isInError())
		{
			Filial filial = response.getFirstResult();
			filialRequest.setFilial(filial);

			SearchParameter filialParameter = new SearchParameter(PropertyEnum.FILIAL_ID, String.valueOf(filial.getCodfilial()));
			filialRequest.getSearchLight().addSearchParameter(filialParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(filialRequest, LCActionTypeEnum.INSERT_FILIAL, null);
			filialRequest.getSearchLight().getSearchParameters().remove(filialParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#deleteFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */
	@Override
	public InternalResponse deleteFilial(FilialRequest filialRequest)
	{
		InternalResultsResponse<Filial> filialResponse = getFilialDAC().fetchFilialById(filialRequest);
		InternalResponse response = new InternalResponse();

		if (filialResponse.isInError())
		{
			response.setStatus(filialResponse.getStatus());
			response.addMessages(filialResponse.getMessageInfoList());
			return response;
		}

		response = getFilialDAC().deleteFilial(filialRequest);

		if (response.isInError())
		{
			return response;
		}

		Filial filial = filialResponse.getFirstResult();
		filialRequest.setFilial(filial);

		SearchParameter filialParameter = new SearchParameter(PropertyEnum.FILIAL_ID, String.valueOf(filial.getCodfilial()));
		filialRequest.getSearchLight().addSearchParameter(filialParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(filialRequest, LCActionTypeEnum.DELETE_TAG, null);
		filialRequest.getSearchLight().getSearchParameters().remove(filialParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#updateFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */
	@Override
	public InternalResultsResponse<Filial> updateFilial(FilialRequest filialRequest)
	{
		InternalResultsResponse<Filial> response = getFilialDAC().updateFilial(filialRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(filialRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param filialRequest the filial request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(FilialRequest filialRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(filialRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param filialRequest the filial request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(FilialRequest filialRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(filialRequest.getFilial()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Filial filial = filialRequest.getFilial();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(filial.getCodfilial())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, filial.getCodfilial().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(filialRequest);
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

		ProcessRequest processRequest = createProcessRequest(filialRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Filial> fetchAllFilialTypes(
			InquiryFilialRequest inquiryfilialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Filial> fetchAllFilialFilial(
			FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




