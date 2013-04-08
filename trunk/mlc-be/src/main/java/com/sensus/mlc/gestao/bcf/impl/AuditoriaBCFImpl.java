package com.sensus.mlc.gestao.bcf.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.gestao.bcf.IAuditoriaBCF;
import com.sensus.mlc.gestao.bcl.IAuditoriaBCL;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.request.AuditoriaRequest;
import com.sensus.mlc.gestao.model.request.InquiryAuditoriaRequest;
import com.sensus.mlc.gestao.model.response.AuditoriaResponse;
import com.sensus.mlc.gestao.model.response.InquiryAuditoriaResponse;


public class AuditoriaBCFImpl implements IAuditoriaBCF
{

	/** The Constant NAME_LENGTH. */
	private static final Integer NAME_LENGTH = 255;

	/** The Constant SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID. */
	private static final String SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID =
			"sensus.epm.actionvalidator.name.invalid";

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception";

	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BCL_EXCEPTION_MSG = "sensus.mlc.groupbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AuditoriaBCFImpl.class);

	private IAuditoriaBCL auditoriaBCL; // injected by Spring through setter

	@Override
	public AuditoriaResponse insertAuditoria(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();

		try
		{

			if (LCHelp.checkValidation(response, auditoriaRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Auditoria> internalResponse = getAuditoriaBCL().insertAuditoria(auditoriaRequest);
				response.setAuditoria(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public AuditoriaResponse updateAuditoria(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();
		try
		{
			if (LCHelp.checkValidation(response, auditoriaRequest, MLCPersistanceActionEnum.UPDATE))
			{
				InternalResultsResponse<Auditoria> internalResponse = getAuditoriaBCL().updateAuditoria(auditoriaRequest);
				response.setAuditoria(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public AuditoriaResponse deleteAuditoria(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();
		try
		{
			if (LCHelp.checkValidation(response, auditoriaRequest, MLCPersistanceActionEnum.DELETE))
			{
				InternalResponse internalResponse = getAuditoriaBCL().deleteAuditoria(auditoriaRequest);
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public InquiryAuditoriaResponse fetchAllAuditoria(InquiryAuditoriaRequest inquiryauditoriaRequest)
	{
		InquiryAuditoriaResponse response = new InquiryAuditoriaResponse();
		try
		{

				InternalResultsResponse<Auditoria> internalResponse =
						getAuditoriaBCL().fetchAllAuditoria(inquiryauditoriaRequest);
				response.setAuditoria(internalResponse.getResultsList());
				response.setResultsSetInfo(internalResponse.getResultsSetInfo());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public AuditoriaResponse fetchAuditoriaById(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();
		try
		{
			if (LCHelp.checkValidation(response, auditoriaRequest, MLCPersistanceActionEnum.FETCH_BY_ID))
			{
				InternalResultsResponse<Auditoria> internalResponse = getAuditoriaBCL().fetchAuditoriaById(auditoriaRequest);
				response.setAuditoria(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public AuditoriaResponse fetchAllAuditoriaFilial(AuditoriaRequest auditoriaRequest) {
		AuditoriaResponse response = new AuditoriaResponse();
		try
		{
			if (LCHelp.checkValidation(response, inquiryAuditoriaRequest, MLCPersistanceActionEnum.FETCH))
			{
				InternalResultsResponse<Auditoria> internalResponse =
						getAuditoriaBCL().fetchAllAuditoriaFilial(auditoriaRequest);
				response.setAuditoria(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}


	public IAuditoriaBCL getAuditoriaBCL()
	{
		return auditoriaBCL;
	}

	public void setAuditoriaBCL(IAuditoriaBCL auditoriaBCL)
	{
		this.auditoriaBCL = auditoriaBCL;
	}
}
