package com.sensus.mlc.contabil.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.contabil.bcl.IEmpresaBCL;
import com.sensus.mlc.empresa.bcf.IEmpresaBCF;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.empresa.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.TagBCFImpl;

public class EmpresaBCFImpl implements IEmpresaBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(TagBCFImpl.class);

	private IEmpresaBCL empresaBCL; // injected by Spring through setter

	@Override
	public EmpresaResponse insertEmpresa(EmpresaRequest empresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{

//			if (LCHelp.checkValidation(response, empresaRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
//			{
				InternalResultsResponse<Empresa> internalResponse = getEmpresaBCL().insertEmpresa(empresaRequest);
				response.setEmpresa(internalResponse.getResultsList());
//				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
//			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public EmpresaResponse updateEmpresa(EmpresaRequest empresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
		try
		{
//			if (LCHelp.checkValidation(response, empresaRequest, MLCPersistanceActionEnum.UPDATE))
//			{
				InternalResultsResponse<Empresa> internalResponse = getEmpresaBCL().updateEmpresa(empresaRequest);
				response.setEmpresa(internalResponse.getResultsList());
//				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);
//			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public EmpresaResponse deleteEmpresa(EmpresaRequest empresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
		try
		{
//			if (LCHelp.checkValidation(response, empresaRequest, MLCPersistanceActionEnum.DELETE))
//			{
				InternalResponse internalResponse = getEmpresaBCL().deleteEmpresa(empresaRequest);
//				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
//			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public InquiryEmpresaResponse fetchAllEmpresa(InquiryEmpresaRequest inquiryempresaRequest)
	{
		InquiryEmpresaResponse response = new InquiryEmpresaResponse();
		try
		{

				InternalResultsResponse<Empresa> internalResponse =
						getEmpresaBCL().fetchAllEmpresa(inquiryempresaRequest);
				response.setEmpresa(internalResponse.getResultsList());
//				response.setResultsSetInfo(internalResponse.getResultsSetInfo());
//				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);

		}
		catch (Exception ex)
		{
//			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public EmpresaResponse fetchEmpresaById(EmpresaRequest empresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
		try
		{
//			if (LCHelp.checkValidation(response, empresaRequest, MLCPersistanceActionEnum.FETCH_BY_ID))
//			{
				InternalResultsResponse<Empresa> internalResponse = getEmpresaBCL().fetchEmpresaById(empresaRequest);
				response.setEmpresa(internalResponse.getResultsList());
//				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
//			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public EmpresaResponse fetchAllEmpresaFilial(EmpresaRequest empresaRequest) {
		EmpresaResponse response = new EmpresaResponse();
		try
		{
//			if (LCHelp.checkValidation(response, inquiryEmpresaRequest, MLCPersistanceActionEnum.FETCH))
//			{
				InternalResultsResponse<Empresa> internalResponse =
						getEmpresaBCL().fetchAllEmpresaFilial(empresaRequest);
				response.setEmpresa(internalResponse.getResultsList());
//				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
//			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public ProcessResponse generateFileCSV(InquiryEmpresaRequest inquiryEmpresaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryEmpresaRequest empresaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IEmpresaBCL getEmpresaBCL()
	{
		return empresaBCL;
	}

	public void setEmpresaBCL(IEmpresaBCL empresaBCL)
	{
		this.empresaBCL = empresaBCL;
	}

	@Override
	public EmpresaResponse fetchAllEmpresaTypes(EmpresaRequest empresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}


}
