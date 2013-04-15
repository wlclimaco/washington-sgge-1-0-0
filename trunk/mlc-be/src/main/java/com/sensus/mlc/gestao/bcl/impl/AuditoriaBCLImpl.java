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
import com.sensus.mlc.gestao.bcl.IAuditoriaBCL;
import com.sensus.mlc.gestao.dac.IAuditoriaDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.request.AuditoriaRequest;
import com.sensus.mlc.gestao.model.request.InquiryAuditoriaRequest;
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
 * The Class AuditoriaBCLImpl.
 */
public class AuditoriaBCLImpl implements IAuditoriaBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The auditoria dac. */
	private IAuditoriaDAC auditoriaDAC;



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
	 * Spring Sets the auditoria dac.
	 *
	 * @param iAuditoriaDAC the new auditoria dac
	 */
	public void setAuditoriaDAC(IAuditoriaDAC iAuditoriaDAC)
	{
		auditoriaDAC = iAuditoriaDAC;
	}

	/**
	 * Gets the auditoria dac.
	 *
	 * @return the auditoria dac
	 */
	public IAuditoriaDAC getAuditoriaDAC()
	{
		return auditoriaDAC;
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
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#fetchAllAuditorias(InquiryAuditoriaRequest)
	 */
	@Override
	public InternalResultsResponse<Auditoria> fetchAllAuditoria(InquiryAuditoriaRequest inquiryAuditoriaRequest)
	{
		return getAuditoriaDAC().fetchAllAuditoria(inquiryAuditoriaRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#fetchAuditoriaById(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public InternalResultsResponse<Auditoria> fetchAuditoriaById(AuditoriaRequest auditoriaRequest)
	{
		return getAuditoriaDAC().fetchAuditoriaById(auditoriaRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#insertAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public InternalResultsResponse<Auditoria> insertAuditoria(AuditoriaRequest auditoriaRequest)
	{

		InternalResultsResponse<Auditoria> response = getAuditoriaDAC().insertAuditoria(auditoriaRequest);

		if (!response.isInError())
		{
			Auditoria auditoria = response.getFirstResult();
			auditoriaRequest.setAuditoria(auditoria);

			SearchParameter auditoriaParameter = new SearchParameter(PropertyEnum.AUDITORIA_ID, String.valueOf(auditoria.getCodaltins()));
			auditoriaRequest.getSearchLight().addSearchParameter(auditoriaParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(auditoriaRequest, LCActionTypeEnum.INSERT_EMPRESA, null);
			auditoriaRequest.getSearchLight().getSearchParameters().remove(auditoriaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#deleteAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public InternalResponse deleteAuditoria(AuditoriaRequest auditoriaRequest)
	{
		InternalResultsResponse<Auditoria> auditoriaResponse = getAuditoriaDAC().fetchAuditoriaById(auditoriaRequest);
		InternalResponse response = new InternalResponse();

		if (auditoriaResponse.isInError())
		{
			response.setStatus(auditoriaResponse.getStatus());
			response.addMessages(auditoriaResponse.getMessageInfoList());
			return response;
		}

		response = getAuditoriaDAC().deleteAuditoria(auditoriaRequest);

		if (response.isInError())
		{
			return response;
		}

		Auditoria auditoria = auditoriaResponse.getFirstResult();
		auditoriaRequest.setAuditoria(auditoria);

		SearchParameter auditoriaParameter = new SearchParameter(PropertyEnum.AUDITORIA_ID, String.valueOf(auditoria.getCodaltins()));
		auditoriaRequest.getSearchLight().addSearchParameter(auditoriaParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(auditoriaRequest, LCActionTypeEnum.DELETE_TAG, null);
		auditoriaRequest.getSearchLight().getSearchParameters().remove(auditoriaParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#updateAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public InternalResultsResponse<Auditoria> updateAuditoria(AuditoriaRequest auditoriaRequest)
	{

		InternalResultsResponse<Auditoria> auditoriaResponse = getAuditoriaDAC().fetchAuditoriaById(auditoriaRequest);
		InternalResultsResponse response = new InternalResultsResponse();

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(auditoriaRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param auditoriaRequest the auditoria request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(AuditoriaRequest auditoriaRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(auditoriaRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param auditoriaRequest the auditoria request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(AuditoriaRequest auditoriaRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(auditoriaRequest.getAuditoria()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Auditoria auditoria = auditoriaRequest.getAuditoria();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(auditoria.getCodaltins())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, auditoria.getCodaltins().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(auditoriaRequest);
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

		ProcessRequest processRequest = createProcessRequest(auditoriaRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Auditoria> fetchAllAuditoriaTypes(
			InquiryAuditoriaRequest inquiryauditoriaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Auditoria> fetchAllAuditoriaFilial(
			AuditoriaRequest auditoriaRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




