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
import com.sensus.mlc.gestao.bcl.IUfBCL;
import com.sensus.mlc.gestao.dac.IUfDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Uf;
import com.sensus.mlc.gestao.model.request.InquiryUfRequest;
import com.sensus.mlc.gestao.model.request.UfRequest;
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
 * The Class UfBCLImpl.
 */
public class UfBCLImpl implements IUfBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The uf dac. */
	private IUfDAC ufDAC;



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
	 * Spring Sets the uf dac.
	 *
	 * @param iUfDAC the new uf dac
	 */
	public void setUfDAC(IUfDAC iUfDAC)
	{
		ufDAC = iUfDAC;
	}

	/**
	 * Gets the uf dac.
	 *
	 * @return the uf dac
	 */
	public IUfDAC getUfDAC()
	{
		return ufDAC;
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
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#fetchAllUfs(InquiryUfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> fetchAllUf(InquiryUfRequest inquiryUfRequest)
	{
		return getUfDAC().fetchAllUf(inquiryUfRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#fetchUfById(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> fetchUfById(UfRequest ufRequest)
	{
		return getUfDAC().fetchUfById(ufRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#insertUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> insertUf(UfRequest ufRequest)
	{

		InternalResultsResponse<Uf> response = getUfDAC().insertUf(ufRequest);

		if (!response.isInError())
		{
			Uf uf = response.getFirstResult();
			ufRequest.setUf(uf);

			SearchParameter ufParameter = new SearchParameter(PropertyEnum.UF_ID, String.valueOf(uf.getCoduf()));
			ufRequest.getSearchLight().addSearchParameter(ufParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(ufRequest, LCActionTypeEnum.INSERT_TAG, null);
			ufRequest.getSearchLight().getSearchParameters().remove(ufParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#deleteUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResponse deleteUf(UfRequest ufRequest)
	{
		InternalResultsResponse<Uf> ufResponse = getUfDAC().fetchUfById(ufRequest);
		InternalResponse response = new InternalResponse();

		if (ufResponse.isInError())
		{
			response.setStatus(ufResponse.getStatus());
			response.addMessages(ufResponse.getMessageInfoList());
			return response;
		}

		response = getUfDAC().deleteUf(ufRequest);

		if (response.isInError())
		{
			return response;
		}

		Uf uf = ufResponse.getFirstResult();
		ufRequest.setUf(uf);

		SearchParameter ufParameter = new SearchParameter(PropertyEnum.UF_ID, String.valueOf(uf.getCoduf()));
		ufRequest.getSearchLight().addSearchParameter(ufParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(ufRequest, LCActionTypeEnum.DELETE_TAG, null);
		ufRequest.getSearchLight().getSearchParameters().remove(ufParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#updateUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> updateUf(UfRequest ufRequest)
	{
		InternalResultsResponse<Uf> response = getUfDAC().updateUf(ufRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(ufRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param ufRequest the uf request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(UfRequest ufRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(ufRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param ufRequest the uf request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(UfRequest ufRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(ufRequest.getUf()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Uf uf = ufRequest.getUf();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(uf.getCoduf())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, uf.getCoduf().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(ufRequest);
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

		ProcessRequest processRequest = createProcessRequest(ufRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Uf> fetchAllUfTypes(
			InquiryUfRequest inquiryufRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Uf> fetchAllUfFilial(UfRequest ufRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




