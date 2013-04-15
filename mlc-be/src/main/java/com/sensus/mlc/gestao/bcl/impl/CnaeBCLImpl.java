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
import com.sensus.mlc.gestao.bcl.ICnaeBCL;
import com.sensus.mlc.gestao.dac.ICnaeDAC;
import com.sensus.mlc.gestao.model.Cnae;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.request.CnaeRequest;
import com.sensus.mlc.gestao.model.request.InquiryCnaeRequest;
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
 * The Class CnaeBCLImpl.
 */
public class CnaeBCLImpl implements ICnaeBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The cnae dac. */
	private ICnaeDAC cnaeDAC;



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
	 * Spring Sets the cnae dac.
	 *
	 * @param iCnaeDAC the new cnae dac
	 */
	public void setCnaeDAC(ICnaeDAC iCnaeDAC)
	{
		cnaeDAC = iCnaeDAC;
	}

	/**
	 * Gets the cnae dac.
	 *
	 * @return the cnae dac
	 */
	public ICnaeDAC getCnaeDAC()
	{
		return cnaeDAC;
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
	 * @see com.sensus.mlc.cnae.bcl.ICnaeBCL#fetchAllCnaes(InquiryCnaeRequest)
	 */
	@Override
	public InternalResultsResponse<Cnae> fetchAllCnae(InquiryCnaeRequest inquiryCnaeRequest)
	{
		return getCnaeDAC().fetchAllCnae(inquiryCnaeRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcl.ICnaeBCL#fetchCnaeById(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */
	@Override
	public InternalResultsResponse<Cnae> fetchCnaeById(CnaeRequest cnaeRequest)
	{
		return getCnaeDAC().fetchCnaeById(cnaeRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcl.ICnaeBCL#insertCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */
	@Override
	public InternalResultsResponse<Cnae> insertCnae(CnaeRequest cnaeRequest)
	{

		InternalResultsResponse<Cnae> response = getCnaeDAC().insertCnae(cnaeRequest);

		if (!response.isInError())
		{
			Cnae cnae = response.getFirstResult();
			cnaeRequest.setCnae(cnae);

			SearchParameter cnaeParameter = new SearchParameter(PropertyEnum.CNAE_ID, String.valueOf(cnae.getCodcnae()));
			cnaeRequest.getSearchLight().addSearchParameter(cnaeParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(cnaeRequest, LCActionTypeEnum.INSERT_FILIAL, null);
			cnaeRequest.getSearchLight().getSearchParameters().remove(cnaeParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcl.ICnaeBCL#deleteCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */
	@Override
	public InternalResponse deleteCnae(CnaeRequest cnaeRequest)
	{
		InternalResultsResponse<Cnae> cnaeResponse = getCnaeDAC().fetchCnaeById(cnaeRequest);
		InternalResponse response = new InternalResponse();

		if (cnaeResponse.isInError())
		{
			response.setStatus(cnaeResponse.getStatus());
			response.addMessages(cnaeResponse.getMessageInfoList());
			return response;
		}

		response = getCnaeDAC().deleteCnae(cnaeRequest);

		if (response.isInError())
		{
			return response;
		}

		Cnae cnae = cnaeResponse.getFirstResult();
		cnaeRequest.setCnae(cnae);

		SearchParameter cnaeParameter = new SearchParameter(PropertyEnum.CNAE_ID, String.valueOf(cnae.getCodcnae()));
		cnaeRequest.getSearchLight().addSearchParameter(cnaeParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(cnaeRequest, LCActionTypeEnum.DELETE_TAG, null);
		cnaeRequest.getSearchLight().getSearchParameters().remove(cnaeParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcl.ICnaeBCL#updateCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */
	@Override
	public InternalResultsResponse<Cnae> updateCnae(CnaeRequest cnaeRequest)
	{
		InternalResultsResponse<Cnae> response = getCnaeDAC().updateCnae(cnaeRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(cnaeRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param cnaeRequest the cnae request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(CnaeRequest cnaeRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(cnaeRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param cnaeRequest the cnae request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(CnaeRequest cnaeRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(cnaeRequest.getCnae()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Cnae cnae = cnaeRequest.getCnae();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(cnae.getCodcnae())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, cnae.getCodcnae().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(cnaeRequest);
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

		ProcessRequest processRequest = createProcessRequest(cnaeRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Cnae> fetchAllCnaeTypes(
			InquiryCnaeRequest inquirycnaeRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cnae> fetchAllCnaeFilial(
			CnaeRequest cnaeRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




