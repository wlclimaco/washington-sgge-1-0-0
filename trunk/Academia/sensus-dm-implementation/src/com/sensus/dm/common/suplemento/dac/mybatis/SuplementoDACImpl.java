package com.sensus.dm.common.suplemento.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.suplemento.dac.ISuplementoDAC;
import com.sensus.dm.common.suplemento.model.Suplemento;
import com.sensus.dm.common.suplemento.model.request.InquirySuplementoRequest;
import com.sensus.dm.common.suplemento.model.request.SuplementoRequest;

/**
 * The Class SuplementoDACImpl.
 * 
 * @author Washington
 */
public class SuplementoDACImpl extends SqlSessionDaoSupport implements ISuplementoDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant SUPLEMENTO_TYPE_VALUE. */
	private static final Integer SUPLEMENTO_TYPE_VALUE = 1;

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

	/** The Constant CDSUPLE. */
	private static final String CDSUPLE = "cdsuple";
	/** The Constant SUPLEME. */
	private static final String SUPLEME = "supleme";
	/** The Constant MARCA. */
	private static final String MARCA = "marca";
	/** The Constant QNTDIA. */
	private static final String QNTDIA = "qntdia";
	/** The Constant DTINIC. */
	private static final String DTINIC = "dtinic";
	/** The Constant DTFINA. */
	private static final String DTFINA = "dtfina";
	/** The Constant OBSSUP. */
	private static final String OBSSUP = "obssup";
	/** The Constant COMENT. */
	private static final String COMENT = "coment";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant SUPLEMENTO_MAP. */
	private static final String SUPLEMENTO_MAP = "SuplementoMap.";

	/** The Constant DELETE_SUPLEMENTO. */
	private static final String DELETE_SUPLEMENTO = SUPLEMENTO_MAP + "deleteSuplemento";

	/** The Constant FETCH_ALL_SUPLEMENTOS. */
	private static final String FETCH_ALL_SUPLEMENTOS = SUPLEMENTO_MAP + "fetchAllSuplementos";

	/** The Constant FETCH_DEVICES_BY_SUPLEMENTOS. */
	private static final String FETCH_DEVICES_BY_SUPLEMENTOS = SUPLEMENTO_MAP + "fetchDevicesByName";

	/** The Constant INSERT_SUPLEMENTO. */
	private static final String INSERT_SUPLEMENTO = SUPLEMENTO_MAP + "insertSuplemento";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = SUPLEMENTO_MAP + "paginationTotalRows";

	/** The Constant UPDATE_SUPLEMENTO. */
	private static final String UPDATE_SUPLEMENTO = SUPLEMENTO_MAP + "updateSuplemento";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = SUPLEMENTO_MAP + "fetchDevicesBySuplementos";

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
	 * com.sensus.dm.common.suplemento.dac.ISuplementoDAC#deleteSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public InternalResponse deleteSuplemento(SuplementoRequest suplementoRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDSUPLE, suplementoRequest.getFirstSuplemento().getCdsuple());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_SUPLEMENTO, paramMap);

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
	 * com.sensus.dm.common.suplemento.dac.ISuplementoDAC#insertSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public InternalResultsResponse<Suplemento> insertSuplemento(SuplementoRequest suplementoRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(CDSUPLE, suplementoRequest.getFirstSuplemento().getCdsuple());
		paramMap.put(SUPLEME, suplementoRequest.getFirstSuplemento().getSupleme());
		paramMap.put(MARCA, suplementoRequest.getFirstSuplemento().getMarca());
		paramMap.put(QNTDIA, suplementoRequest.getFirstSuplemento().getQntdia());
		paramMap.put(DTINIC, suplementoRequest.getFirstSuplemento().getDtinic());
		paramMap.put(DTFINA, suplementoRequest.getFirstSuplemento().getDtfina());
		paramMap.put(OBSSUP, suplementoRequest.getFirstSuplemento().getObssup());
		paramMap.put(COMENT, suplementoRequest.getFirstSuplemento().getComent());
		paramMap.put(CUSTOMER_ID, suplementoRequest.getTenant().getKey());

		Integer suplementoId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_SUPLEMENTO, paramMap);

		InternalResultsResponse<Suplemento> response = new InternalResultsResponse<Suplemento>();

		if (!ValidationUtil.isNull(suplementoId))
		{
			suplementoRequest.getFirstSuplemento().setCdsuple(suplementoId);
			response.addResult(suplementoRequest.getFirstSuplemento());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.dac.ISuplementoDAC#updateSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public InternalResultsResponse<Suplemento> updateSuplemento(SuplementoRequest suplementoRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CDSUPLE, suplementoRequest.getFirstSuplemento().getCdsuple());
		paramMap.put(SUPLEME, suplementoRequest.getFirstSuplemento().getSupleme());
		paramMap.put(MARCA, suplementoRequest.getFirstSuplemento().getMarca());
		paramMap.put(QNTDIA, suplementoRequest.getFirstSuplemento().getQntdia());
		paramMap.put(DTINIC, suplementoRequest.getFirstSuplemento().getDtinic());
		paramMap.put(DTFINA, suplementoRequest.getFirstSuplemento().getDtfina());
		paramMap.put(OBSSUP, suplementoRequest.getFirstSuplemento().getObssup());
		paramMap.put(COMENT, suplementoRequest.getFirstSuplemento().getComent());
		paramMap.put(MODIFY_USER, suplementoRequest.getUserContext().getUserId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_SUPLEMENTO, paramMap);

		InternalResultsResponse<Suplemento> response = new InternalResultsResponse<Suplemento>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(suplementoRequest.getFirstSuplemento());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.dac.ISuplementoDAC#fetchAllSuplementos(com.sensus.dm.common.suplemento.model.request
	 * .InquirySuplementoRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Suplemento> fetchAllSuplementos(InquirySuplementoRequest inquirySuplementoRequest)
	{
		InternalResultsResponse<Suplemento> response = new InternalResultsResponse<Suplemento>();

		if (inquirySuplementoRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquirySuplementoRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_SUPLEMENTOS, inquirySuplementoRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Suplemento> fetchSuplementosById(InquirySuplementoRequest suplementoRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Suplemento> fetchDSuplementosByName(InquirySuplementoRequest suplementoRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
