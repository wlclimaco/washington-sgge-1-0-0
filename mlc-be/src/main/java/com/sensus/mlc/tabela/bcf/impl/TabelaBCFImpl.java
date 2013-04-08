package com.sensus.mlc.tabela.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.tabela.bcf.ITabelaBCF;
import com.sensus.mlc.tabela.bcl.ITabelaBCL;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tabela.model.request.TabelaRequest;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
import com.sensus.mlc.tabela.model.response.TabelaResponse;
import com.sensus.mlc.tabela.model.response.InquiryTabelaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;

public class TabelaBCFImpl implements ITabelaBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(TabelaBCFImpl.class);

	private ITabelaBCL TabelaBCL; // injected by Spring through setter

	@Override
	public TabelaResponse insertTabela(TabelaRequest TabelaRequest)
	{
		TabelaResponse response = new TabelaResponse();

		try
		{

			if (LCHelp.checkValidation(response, TabelaRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Tabela> internalResponse = getTabelaBCL().insertTabela(TabelaRequest);
				response.setTabela(internalResponse.getResultsList());
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
	public TabelaResponse updateTabela(TabelaRequest TabelaRequest)
	{
		TabelaResponse response = new TabelaResponse();
		try
		{
			if (LCHelp.checkValidation(response, TabelaRequest, MLCPersistanceActionEnum.UPDATE))
			{
				InternalResultsResponse<Tabela> internalResponse = getTabelaBCL().updateTabela(TabelaRequest);
				response.setTabela(internalResponse.getResultsList());
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
	public TabelaResponse deleteTabela(TabelaRequest TabelaRequest)
	{
		TabelaResponse response = new TabelaResponse();
		try
		{
			if (LCHelp.checkValidation(response, TabelaRequest, MLCPersistanceActionEnum.DELETE))
			{
				InternalResponse internalResponse = getTabelaBCL().deleteTabela(TabelaRequest);
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
	public InquiryTabelaResponse fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest)
	{
		InquiryTabelaResponse response = new InquiryTabelaResponse();
		try
		{

				InternalResultsResponse<Tabela> internalResponse =
						getTabelaBCL().fetchAllTabela(inquiryTabelaRequest);
				response.setTabela(internalResponse.getResultsList());
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
	public TabelaResponse fetchTabelaById(TabelaRequest TabelaRequest)
	{
		TabelaResponse response = new TabelaResponse();
		try
		{
			if (LCHelp.checkValidation(response, TabelaRequest, MLCPersistanceActionEnum.FETCH_BY_ID))
			{
				InternalResultsResponse<Tabela> internalResponse = getTabelaBCL().fetchTabelaById(TabelaRequest);
				response.setTabela(internalResponse.getResultsList());
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
	public TabelaResponse fetchAllTabelaFilial(TabelaRequest TabelaRequest) {
		TabelaResponse response = new TabelaResponse();
		try
		{
			if (LCHelp.checkValidation(response, inquiryTabelaRequest, MLCPersistanceActionEnum.FETCH))
			{
				InternalResultsResponse<Tabela> internalResponse =
						getTabelaBCL().fetchAllTabelaFilial(TabelaRequest);
				response.setTabela(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}


	public ITabelaBCL getTabelaBCL()
	{
		return TabelaBCL;
	}

	public void setTabelaBCL(ITabelaBCL TabelaBCL)
	{
		this.TabelaBCL = TabelaBCL;
	}
}
