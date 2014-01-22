package com.sensus.lc.pessoa.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.pessoa.dac.IPessoaDAC;
import com.sensus.lc.pessoa.model.Pessoa;
import com.sensus.lc.pessoa.model.request.InquiryPessoaRequest;
import com.sensus.lc.pessoa.model.request.PessoaRequest;

/**
 * The Class PessoaDACImpl.
 * 
 * @author Washington
 */
public class PessoaDACImpl extends SqlSessionDaoSupport implements IPessoaDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant PESSOA_TYPE_VALUE. */
	private static final Integer PESSOA_TYPE_VALUE = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final int PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE10. */
	private static final Integer PARAMSIZE10 = 10;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant CDPESSOA. */
	private static final String CDPESSOA = "cdpessoa";
	/** The Constant CDSERIE. */
	private static final String CDSERIE = "cdserie";
	/** The Constant CDMED. */
	private static final String CDMED = "cdmed";
	/** The Constant CDDIETA. */
	private static final String CDDIETA = "cddieta";
	/** The Constant CDSUPLE. */
	private static final String CDSUPLE = "cdsuple";
	/** The Constant CDAMIGO. */
	private static final String CDAMIGO = "cdamigo";
	/** The Constant CDACADE. */
	private static final String CDACADE = "cdacade";
	/** The Constant OBJ. */
	private static final String OBJ = "obj";
	/** The Constant CDFOTO. */
	private static final String CDFOTO = "cdfoto";
	/** The Constant DTINICIO. */
	private static final String DTINICIO = "dtinicio";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant PESSOA_MAP. */
	private static final String PESSOA_MAP = "PessoaMap.";

	/** The Constant DELETE_PESSOA. */
	private static final String DELETE_PESSOA = PESSOA_MAP + "deletePessoa";

	/** The Constant FETCH_ALL_PESSOAS. */
	private static final String FETCH_ALL_PESSOAS = PESSOA_MAP + "fetchAllPessoas";

	/** The Constant FETCH_DEVICES_BY_PESSOAS. */
	private static final String FETCH_DEVICES_BY_PESSOAS = PESSOA_MAP + "fetchDevicesByName";

	/** The Constant INSERT_PESSOA. */
	private static final String INSERT_PESSOA = PESSOA_MAP + "insertPessoa";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = PESSOA_MAP + "paginationTotalRows";

	/** The Constant UPDATE_PESSOA. */
	private static final String UPDATE_PESSOA = PESSOA_MAP + "updatePessoa";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = PESSOA_MAP + "fetchDevicesByPessoas";

	private static final String CUSTOMER_ID = null;

	private static final String MODIFY_USER = null;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.dac.IPessoaDAC#deletePessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public InternalResponse deletePessoa(PessoaRequest pessoaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDPESSOA, pessoaRequest.getFirstPessoa().getCdpessoa());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_PESSOA, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.dac.IPessoaDAC#insertPessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> insertPessoa(PessoaRequest pessoaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(CDPESSOA, pessoaRequest.getFirstPessoa().getCdpessoa());
		paramMap.put(CDSERIE, pessoaRequest.getFirstPessoa().getFirstSerie().getCdserie());
		paramMap.put(CDMED, pessoaRequest.getFirstPessoa().getFirstMedida().getCdmed());
		paramMap.put(CDDIETA, pessoaRequest.getFirstPessoa().getFirstDieta().getCddiet());
		paramMap.put(CDSUPLE, pessoaRequest.getFirstPessoa().getFirstSuplemento().getCdsuple());
		paramMap.put(CDAMIGO, pessoaRequest.getFirstPessoa().getFirstPessoa().getCdpessoa());
		paramMap.put(CDACADE, pessoaRequest.getFirstPessoa().getFirstAcademia().getCdacad());
		paramMap.put(OBJ, pessoaRequest.getFirstPessoa().getObj());
		paramMap.put(CDFOTO, pessoaRequest.getFirstPessoa().getFirstFoto().getCdfoto());
		paramMap.put(DTINICIO, pessoaRequest.getFirstPessoa().getDtinicio());
		// paramMap.put(CUSTOMER_ID, pessoaRequest.getTenant().getKey());

		Integer pessoaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_PESSOA, paramMap);

		InternalResultsResponse<Pessoa> response = new InternalResultsResponse<Pessoa>();

		if (!ValidationUtil.isNull(pessoaId))
		{
			pessoaRequest.getFirstPessoa().setCdpessoa(pessoaId);
			response.addResult(pessoaRequest.getFirstPessoa());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.dac.IPessoaDAC#updatePessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> updatePessoa(PessoaRequest pessoaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CDPESSOA, pessoaRequest.getFirstPessoa().getCdpessoa());
		paramMap.put(CDSERIE, pessoaRequest.getFirstPessoa().getFirstSerie().getCdserie());
		paramMap.put(CDMED, pessoaRequest.getFirstPessoa().getFirstMedida().getCdmed());
		paramMap.put(CDDIETA, pessoaRequest.getFirstPessoa().getFirstDieta().getCddiet());
		paramMap.put(CDSUPLE, pessoaRequest.getFirstPessoa().getFirstSuplemento().getCdsuple());
		paramMap.put(CDAMIGO, pessoaRequest.getFirstPessoa().getFirstPessoa().getCdpessoa());
		paramMap.put(CDACADE, pessoaRequest.getFirstPessoa().getFirstAcademia().getCdacad());
		paramMap.put(OBJ, pessoaRequest.getFirstPessoa().getObj());
		paramMap.put(CDFOTO, pessoaRequest.getFirstPessoa().getFirstFoto().getCdfoto());
		paramMap.put(DTINICIO, pessoaRequest.getFirstPessoa().getDtinicio());
		paramMap.put(MODIFY_USER, pessoaRequest.getUserContext().getUserId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_PESSOA, paramMap);

		InternalResultsResponse<Pessoa> response = new InternalResultsResponse<Pessoa>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(pessoaRequest.getFirstPessoa());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.dac.IPessoaDAC#fetchAllPessoas(com.sensus.dm.common.pessoa.model.request.
	 * InquiryPessoaRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Pessoa> fetchAllPessoas(InquiryPessoaRequest inquiryPessoaRequest)
	{
		InternalResultsResponse<Pessoa> response = new InternalResultsResponse<Pessoa>();

		if (inquiryPessoaRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryPessoaRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_PESSOAS, inquiryPessoaRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Pessoa> fetchPessoasById(InquiryPessoaRequest pessoaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Pessoa> fetchPessoasByName(InquiryPessoaRequest pessoaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
