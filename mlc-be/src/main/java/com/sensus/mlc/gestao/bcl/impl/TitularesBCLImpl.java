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
import com.sensus.mlc.gestao.bcl.ITitularesBCL;
import com.sensus.mlc.gestao.dac.ITitularesDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Titulares;
import com.sensus.mlc.gestao.model.request.InquiryTitularesRequest;
import com.sensus.mlc.gestao.model.request.TitularesRequest;
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
 * The Class TitularesBCLImpl.
 */
public class TitularesBCLImpl implements ITitularesBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The titulares dac. */
	private ITitularesDAC titularesDAC;



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
	 * Spring Sets the titulares dac.
	 *
	 * @param iTitularesDAC the new titulares dac
	 */
	public void setTitularesDAC(ITitularesDAC iTitularesDAC)
	{
		titularesDAC = iTitularesDAC;
	}

	/**
	 * Gets the titulares dac.
	 *
	 * @return the titulares dac
	 */
	public ITitularesDAC getTitularesDAC()
	{
		return titularesDAC;
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
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#fetchAllTitularess(InquiryTitularesRequest)
	 */
	@Override
	public InternalResultsResponse<Titulares> fetchAllTitulares(InquiryTitularesRequest inquiryTitularesRequest)
	{
		return getTitularesDAC().fetchAllTitulares(inquiryTitularesRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#fetchTitularesById(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */
	@Override
	public InternalResultsResponse<Titulares> fetchTitularesById(TitularesRequest titularesRequest)
	{
		return getTitularesDAC().fetchTitularesById(titularesRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#insertTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */
	@Override
	public InternalResultsResponse<Titulares> insertTitulares(TitularesRequest titularesRequest)
	{

		InternalResultsResponse<Titulares> response = getTitularesDAC().insertTitulares(titularesRequest);

		if (!response.isInError())
		{
			Titulares titulares = response.getFirstResult();
			titularesRequest.setTitulares(titulares);

			SearchParameter titularesParameter = new SearchParameter(PropertyEnum.TITULARE_ID, String.valueOf(titulares.getCodtitular()));
			titularesRequest.getSearchLight().addSearchParameter(titularesParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(titularesRequest, LCActionTypeEnum.INSERT_TAG, null);
			titularesRequest.getSearchLight().getSearchParameters().remove(titularesParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#deleteTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */
	@Override
	public InternalResponse deleteTitulares(TitularesRequest titularesRequest)
	{
		InternalResultsResponse<Titulares> titularesResponse = getTitularesDAC().fetchTitularesById(titularesRequest);
		InternalResponse response = new InternalResponse();

		if (titularesResponse.isInError())
		{
			response.setStatus(titularesResponse.getStatus());
			response.addMessages(titularesResponse.getMessageInfoList());
			return response;
		}

		response = getTitularesDAC().deleteTitulares(titularesRequest);

		if (response.isInError())
		{
			return response;
		}

		Titulares titulares = titularesResponse.getFirstResult();
		titularesRequest.setTitulares(titulares);

		SearchParameter titularesParameter = new SearchParameter(PropertyEnum.TITULARE_ID, String.valueOf(titulares.getCodtitular()));
		titularesRequest.getSearchLight().addSearchParameter(titularesParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(titularesRequest, LCActionTypeEnum.DELETE_TAG, null);
		titularesRequest.getSearchLight().getSearchParameters().remove(titularesParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#updateTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */
	@Override
	public InternalResultsResponse<Titulares> updateTitulares(TitularesRequest titularesRequest)
	{
		InternalResultsResponse<Titulares> response = getTitularesDAC().updateTitulares(titularesRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(titularesRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param titularesRequest the titulares request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TitularesRequest titularesRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(titularesRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param titularesRequest the titulares request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TitularesRequest titularesRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(titularesRequest.getTitulares()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Titulares titulares = titularesRequest.getTitulares();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(titulares.getCodtitular())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, titulares.getCodtitular().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(titularesRequest);
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

		ProcessRequest processRequest = createProcessRequest(titularesRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Titulares> fetchAllTitularesTypes(
			InquiryTitularesRequest inquirytitularesRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Titulares> fetchAllTitularesFilial(
			TitularesRequest titularesRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




