package com.sensus.dm.common.medida.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.medida.dac.IMedidaDAC;
import com.sensus.dm.common.medida.model.Medida;
import com.sensus.dm.common.medida.model.request.InquiryMedidaRequest;
import com.sensus.dm.common.medida.model.request.MedidaRequest;

/**
 * The Class MedidaDACImpl.
 * 
 * @author Washington
 */
public class MedidaDACImpl extends SqlSessionDaoSupport implements IMedidaDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant MEDIDA_TYPE_VALUE. */
	private static final Integer MEDIDA_TYPE_VALUE = 1;

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

	/** The Constant CDMED. */
	private static final String CDMED = "cdmed";
	/** The Constant ALTURA. */
	private static final String ALTURA = "altura";
	/** The Constant PESO. */
	private static final String PESO = "peso";
	/** The Constant BUSTO. */
	private static final String BUSTO = "busto";
	/** The Constant BRACOESQ. */
	private static final String BRACOESQ = "bracoesq";
	/** The Constant BRACODIR. */
	private static final String BRACODIR = "bracodir";
	/** The Constant ABDOMEN. */
	private static final String ABDOMEN = "abdomen";
	/** The Constant COSTA. */
	private static final String COSTA = "costa";
	/** The Constant CINTURA. */
	private static final String CINTURA = "cintura";
	/** The Constant QUADRIL. */
	private static final String QUADRIL = "quadril";
	/** The Constant CULOTE. */
	private static final String CULOTE = "culote";
	/** The Constant COXDIR. */
	private static final String COXDIR = "coxdir";
	/** The Constant COXESQ. */
	private static final String COXESQ = "coxesq";
	/** The Constant PANTUESQ. */
	private static final String PANTUESQ = "pantuesq";
	/** The Constant PANTUDIR. */
	private static final String PANTUDIR = "pantudir";
	/** The Constant DTMEDIDA. */
	private static final String DTMEDIDA = "dtmedida";
	/** The Constant IMC. */
	private static final String IMC = "imc";
	/** The Constant BS. */
	private static final String BS = "bs";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant MEDIDA_MAP. */
	private static final String MEDIDA_MAP = "MedidaMap.";

	/** The Constant DELETE_MEDIDA. */
	private static final String DELETE_MEDIDA = MEDIDA_MAP + "deleteMedida";

	/** The Constant FETCH_ALL_MEDIDAS. */
	private static final String FETCH_ALL_MEDIDAS = MEDIDA_MAP + "fetchAllMedidas";

	/** The Constant FETCH_DEVICES_BY_MEDIDAS. */
	private static final String FETCH_DEVICES_BY_MEDIDAS = MEDIDA_MAP + "fetchDevicesByName";

	/** The Constant INSERT_MEDIDA. */
	private static final String INSERT_MEDIDA = MEDIDA_MAP + "insertMedida";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = MEDIDA_MAP + "paginationTotalRows";

	/** The Constant UPDATE_MEDIDA. */
	private static final String UPDATE_MEDIDA = MEDIDA_MAP + "updateMedida";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = MEDIDA_MAP + "fetchDevicesByMedidas";

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
	 * com.sensus.dm.common.medida.dac.IMedidaDAC#deleteMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public InternalResponse deleteMedida(MedidaRequest medidaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDMED, medidaRequest.getFirstMedida().getCdmed());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_MEDIDA, paramMap);

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
	 * com.sensus.dm.common.medida.dac.IMedidaDAC#insertMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public InternalResultsResponse<Medida> insertMedida(MedidaRequest medidaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(CDMED, medidaRequest.getFirstMedida().getCdmed());
		paramMap.put(ALTURA, medidaRequest.getFirstMedida().getAltura());
		paramMap.put(PESO, medidaRequest.getFirstMedida().getPeso());
		paramMap.put(BUSTO, medidaRequest.getFirstMedida().getBusto());
		paramMap.put(BRACOESQ, medidaRequest.getFirstMedida().getBracoesq());
		paramMap.put(BRACODIR, medidaRequest.getFirstMedida().getBracodir());
		paramMap.put(ABDOMEN, medidaRequest.getFirstMedida().getAbdomen());
		paramMap.put(COSTA, medidaRequest.getFirstMedida().getCosta());
		paramMap.put(CINTURA, medidaRequest.getFirstMedida().getCintura());
		paramMap.put(QUADRIL, medidaRequest.getFirstMedida().getQuadril());
		paramMap.put(CULOTE, medidaRequest.getFirstMedida().getCulote());
		paramMap.put(COXDIR, medidaRequest.getFirstMedida().getCoxdir());
		paramMap.put(COXESQ, medidaRequest.getFirstMedida().getCoxesq());
		paramMap.put(PANTUESQ, medidaRequest.getFirstMedida().getPantuesq());
		paramMap.put(PANTUDIR, medidaRequest.getFirstMedida().getPantudir());
		paramMap.put(DTMEDIDA, medidaRequest.getFirstMedida().getDtmedida());
		paramMap.put(IMC, medidaRequest.getFirstMedida().getImc());
		paramMap.put(BS, medidaRequest.getFirstMedida().getBs());
		paramMap.put(CUSTOMER_ID, medidaRequest.getTenant().getKey());

		Integer medidaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_MEDIDA, paramMap);

		InternalResultsResponse<Medida> response = new InternalResultsResponse<Medida>();

		if (!ValidationUtil.isNull(medidaId))
		{
			medidaRequest.getFirstMedida().setCdmed(medidaId);
			response.addResult(medidaRequest.getFirstMedida());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.dac.IMedidaDAC#updateMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public InternalResultsResponse<Medida> updateMedida(MedidaRequest medidaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CDMED, medidaRequest.getFirstMedida().getCdmed());
		paramMap.put(ALTURA, medidaRequest.getFirstMedida().getAltura());
		paramMap.put(PESO, medidaRequest.getFirstMedida().getPeso());
		paramMap.put(BUSTO, medidaRequest.getFirstMedida().getBusto());
		paramMap.put(BRACOESQ, medidaRequest.getFirstMedida().getBracoesq());
		paramMap.put(BRACODIR, medidaRequest.getFirstMedida().getBracodir());
		paramMap.put(ABDOMEN, medidaRequest.getFirstMedida().getAbdomen());
		paramMap.put(COSTA, medidaRequest.getFirstMedida().getCosta());
		paramMap.put(CINTURA, medidaRequest.getFirstMedida().getCintura());
		paramMap.put(QUADRIL, medidaRequest.getFirstMedida().getQuadril());
		paramMap.put(CULOTE, medidaRequest.getFirstMedida().getCulote());
		paramMap.put(COXDIR, medidaRequest.getFirstMedida().getCoxdir());
		paramMap.put(COXESQ, medidaRequest.getFirstMedida().getCoxesq());
		paramMap.put(PANTUESQ, medidaRequest.getFirstMedida().getPantuesq());
		paramMap.put(PANTUDIR, medidaRequest.getFirstMedida().getPantudir());
		paramMap.put(DTMEDIDA, medidaRequest.getFirstMedida().getDtmedida());
		paramMap.put(IMC, medidaRequest.getFirstMedida().getImc());
		paramMap.put(BS, medidaRequest.getFirstMedida().getBs());
		paramMap.put(MODIFY_USER, medidaRequest.getUserContext().getUserId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_MEDIDA, paramMap);

		InternalResultsResponse<Medida> response = new InternalResultsResponse<Medida>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(medidaRequest.getFirstMedida());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.dac.IMedidaDAC#fetchAllMedidas(com.sensus.dm.common.medida.model.request.
	 * InquiryMedidaRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Medida> fetchAllMedidas(InquiryMedidaRequest inquiryMedidaRequest)
	{
		InternalResultsResponse<Medida> response = new InternalResultsResponse<Medida>();

		if (inquiryMedidaRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryMedidaRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_MEDIDAS, inquiryMedidaRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Medida> fetchMedidasById(InquiryMedidaRequest medidaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Medida> fetchMedidasByName(InquiryMedidaRequest medidaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
