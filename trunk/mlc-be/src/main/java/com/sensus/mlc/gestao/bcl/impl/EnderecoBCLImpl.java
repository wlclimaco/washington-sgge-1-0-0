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
import com.sensus.mlc.gestao.bcl.IEnderecoBCL;
import com.sensus.mlc.gestao.dac.IEnderecoDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Endereco;
import com.sensus.mlc.gestao.model.request.EnderecoRequest;
import com.sensus.mlc.gestao.model.request.InquiryEnderecoRequest;
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
 * The Class EnderecoBCLImpl.
 */
public class EnderecoBCLImpl implements IEnderecoBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The endereco dac. */
	private IEnderecoDAC enderecoDAC;



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
	 * Spring Sets the endereco dac.
	 *
	 * @param iEnderecoDAC the new endereco dac
	 */
	public void setEnderecoDAC(IEnderecoDAC iEnderecoDAC)
	{
		enderecoDAC = iEnderecoDAC;
	}

	/**
	 * Gets the endereco dac.
	 *
	 * @return the endereco dac
	 */
	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
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
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#fetchAllEnderecos(InquiryEnderecoRequest)
	 */
	@Override
	public InternalResultsResponse<Endereco> fetchAllEndereco(InquiryEnderecoRequest inquiryEnderecoRequest)
	{
		return getEnderecoDAC().fetchAllEndereco(inquiryEnderecoRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#fetchEnderecoById(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */
	@Override
	public InternalResultsResponse<Endereco> fetchEnderecoById(EnderecoRequest enderecoRequest)
	{
		return getEnderecoDAC().fetchEnderecoById(enderecoRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#insertEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */
	@Override
	public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest)
	{

		InternalResultsResponse<Endereco> response = getEnderecoDAC().insertEndereco(enderecoRequest);

		if (!response.isInError())
		{
			Endereco endereco = response.getFirstResult();
			enderecoRequest.setEndereco(endereco);

			SearchParameter enderecoParameter = new SearchParameter(PropertyEnum.ENDERECO_ID, String.valueOf(endereco.getCodend()));
			enderecoRequest.getSearchLight().addSearchParameter(enderecoParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(enderecoRequest, LCActionTypeEnum.INSERT_FILIAL, null);
			enderecoRequest.getSearchLight().getSearchParameters().remove(enderecoParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#deleteEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */
	@Override
	public InternalResponse deleteEndereco(EnderecoRequest enderecoRequest)
	{
		InternalResultsResponse<Endereco> enderecoResponse = getEnderecoDAC().fetchEnderecoById(enderecoRequest);
		InternalResponse response = new InternalResponse();

		if (enderecoResponse.isInError())
		{
			response.setStatus(enderecoResponse.getStatus());
			response.addMessages(enderecoResponse.getMessageInfoList());
			return response;
		}

		response = getEnderecoDAC().deleteEndereco(enderecoRequest);

		if (response.isInError())
		{
			return response;
		}

		Endereco endereco = enderecoResponse.getFirstResult();
		enderecoRequest.setEndereco(endereco);

		SearchParameter enderecoParameter = new SearchParameter(PropertyEnum.ENDERECO_ID, String.valueOf(endereco.getCodend()));
		enderecoRequest.getSearchLight().addSearchParameter(enderecoParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(enderecoRequest, LCActionTypeEnum.DELETE_TAG, null);
		enderecoRequest.getSearchLight().getSearchParameters().remove(enderecoParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#updateEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */
	@Override
	public InternalResultsResponse<Endereco> updateEndereco(EnderecoRequest enderecoRequest)
	{
		InternalResultsResponse<Endereco> response = getEnderecoDAC().updateEndereco(enderecoRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(enderecoRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param enderecoRequest the endereco request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EnderecoRequest enderecoRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(enderecoRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param enderecoRequest the endereco request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EnderecoRequest enderecoRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(enderecoRequest.getEndereco()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Endereco endereco = enderecoRequest.getEndereco();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(endereco.getCodend())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, endereco.getCodend().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(enderecoRequest);
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

		ProcessRequest processRequest = createProcessRequest(enderecoRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Endereco> fetchAllEnderecoTypes(
			InquiryEnderecoRequest inquiryenderecoRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Endereco> fetchAllEnderecoFilial(
			EnderecoRequest enderecoRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




