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
import com.sensus.mlc.gestao.bcl.IEmbalagensBCL;
import com.sensus.mlc.gestao.dac.IEmbalagensDAC;
import com.sensus.mlc.gestao.model.Embalagens;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.request.EmbalagensRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmbalagensRequest;
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
 * The Class EmbalagensBCLImpl.
 */
public class EmbalagensBCLImpl implements IEmbalagensBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The embalagens dac. */
	private IEmbalagensDAC embalagensDAC;



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
	 * Spring Sets the embalagens dac.
	 *
	 * @param iEmbalagensDAC the new embalagens dac
	 */
	public void setEmbalagensDAC(IEmbalagensDAC iEmbalagensDAC)
	{
		embalagensDAC = iEmbalagensDAC;
	}

	/**
	 * Gets the embalagens dac.
	 *
	 * @return the embalagens dac
	 */
	public IEmbalagensDAC getEmbalagensDAC()
	{
		return embalagensDAC;
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
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#fetchAllEmbalagenss(InquiryEmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> fetchAllEmbalagens(InquiryEmbalagensRequest inquiryEmbalagensRequest)
	{
		return getEmbalagensDAC().fetchAllEmbalagens(inquiryEmbalagensRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#fetchEmbalagensById(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> fetchEmbalagensById(EmbalagensRequest embalagensRequest)
	{
		return getEmbalagensDAC().fetchEmbalagensById(embalagensRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#insertEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> insertEmbalagens(EmbalagensRequest embalagensRequest)
	{

		InternalResultsResponse<Embalagens> response = getEmbalagensDAC().insertEmbalagens(embalagensRequest);

		if (!response.isInError())
		{
			Embalagens embalagens = response.getFirstResult();
			embalagensRequest.setEmbalagens(embalagens);

			SearchParameter embalagensParameter = new SearchParameter(PropertyEnum.EMBALAGEM_ID, String.valueOf(embalagens.getCdembala()));
			embalagensRequest.getSearchLight().addSearchParameter(embalagensParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(embalagensRequest, LCActionTypeEnum.INSERT_EMPRESA, null);
			embalagensRequest.getSearchLight().getSearchParameters().remove(embalagensParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#deleteEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResponse deleteEmbalagens(EmbalagensRequest embalagensRequest)
	{
		InternalResultsResponse<Embalagens> embalagensResponse = getEmbalagensDAC().fetchEmbalagensById(embalagensRequest);
		InternalResponse response = new InternalResponse();

		if (embalagensResponse.isInError())
		{
			response.setStatus(embalagensResponse.getStatus());
			response.addMessages(embalagensResponse.getMessageInfoList());
			return response;
		}

		response = getEmbalagensDAC().deleteEmbalagens(embalagensRequest);

		if (response.isInError())
		{
			return response;
		}

		Embalagens embalagens = embalagensResponse.getFirstResult();
		embalagensRequest.setEmbalagens(embalagens);

		SearchParameter embalagensParameter = new SearchParameter(PropertyEnum.EMBALAGEM_ID, String.valueOf(embalagens.getCdembala()));
		embalagensRequest.getSearchLight().addSearchParameter(embalagensParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(embalagensRequest, LCActionTypeEnum.DELETE_TAG, null);
		embalagensRequest.getSearchLight().getSearchParameters().remove(embalagensParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#updateEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> updateEmbalagens(EmbalagensRequest embalagensRequest)
	{
		InternalResultsResponse<Embalagens> response = getEmbalagensDAC().updateEmbalagens(embalagensRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(embalagensRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param embalagensRequest the embalagens request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EmbalagensRequest embalagensRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(embalagensRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param embalagensRequest the embalagens request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EmbalagensRequest embalagensRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(embalagensRequest.getEmbalagens()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Embalagens embalagens = embalagensRequest.getEmbalagens();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(embalagens.getCdembala())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, embalagens.getCdembala().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(embalagensRequest);
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

		ProcessRequest processRequest = createProcessRequest(embalagensRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Embalagens> fetchAllEmbalagensTypes(
			InquiryEmbalagensRequest inquiryembalagensRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Embalagens> fetchAllEmbalagensFilial(
			EmbalagensRequest embalagensRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




