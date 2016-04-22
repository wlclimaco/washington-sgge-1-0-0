package com.qat.samples.sysmgmt.bar.mybatis.Dp;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Dp.IDpBAR;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class DpBARImpl extends SqlSessionDaoSupport implements IDpBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### FUNCIONARIO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_FUNCIONARIO = "FuncionarioMap.";

/** The Constant STMT_INSERT_FUNCIONARIO. */
private static final String STMT_INSERT_FUNCIONARIO = NAMESPACE_FUNCIONARIO + "insertFuncionario";

/** The Constant STMT_UPDATE_FUNCIONARIO. */
private static final String STMT_UPDATE_FUNCIONARIO = NAMESPACE_FUNCIONARIO + "updateFuncionario";

/** The Constant STMT_DELETE_FUNCIONARIO. */
private static final String STMT_DELETE_FUNCIONARIO = NAMESPACE_FUNCIONARIO + "deleteFuncionarioById";

	/** The Constant STMT_DELETE_FUNCIONARIO_ALL. */
	private static final String STMT_DELETE_FUNCIONARIO_ALL = NAMESPACE_FUNCIONARIO + "deleteAllFuncionarios";

	/** The Constant STMT_FETCH_FUNCIONARIO. */
	private static final String STMT_FETCH_FUNCIONARIO = NAMESPACE_FUNCIONARIO + "fetchFuncionarioById";

	/** The Constant STMT_FETCH_FUNCIONARIO_ALL. */
	private static final String STMT_FETCH_FUNCIONARIO_ALL = NAMESPACE_FUNCIONARIO + "fetchAllFuncionarios";

	/** The Constant STMT_FETCH_FUNCIONARIO_COUNT. */
	private static final String STMT_FETCH_FUNCIONARIO_COUNT = NAMESPACE_FUNCIONARIO + "fetchFuncionarioRowCount";

	/** The Constant STMT_FETCH_FUNCIONARIO_ALL_REQUEST. */
	private static final String STMT_FETCH_FUNCIONARIO_ALL_REQUEST = NAMESPACE_FUNCIONARIO + "fetchAllFuncionariosRequest";

///===================================### EVENTOS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_EVENTOS = "EventosMap.";

/** The Constant STMT_INSERT_EVENTOS. */
private static final String STMT_INSERT_EVENTOS = NAMESPACE_EVENTOS + "insertEventos";

/** The Constant STMT_UPDATE_EVENTOS. */
private static final String STMT_UPDATE_EVENTOS = NAMESPACE_EVENTOS + "updateEventos";

/** The Constant STMT_DELETE_EVENTOS. */
private static final String STMT_DELETE_EVENTOS = NAMESPACE_EVENTOS + "deleteEventosById";

	/** The Constant STMT_DELETE_EVENTOS_ALL. */
	private static final String STMT_DELETE_EVENTOS_ALL = NAMESPACE_EVENTOS + "deleteAllEventoss";

	/** The Constant STMT_FETCH_EVENTOS. */
	private static final String STMT_FETCH_EVENTOS = NAMESPACE_EVENTOS + "fetchEventosById";

	/** The Constant STMT_FETCH_EVENTOS_ALL. */
	private static final String STMT_FETCH_EVENTOS_ALL = NAMESPACE_EVENTOS + "fetchAllEventoss";

	/** The Constant STMT_FETCH_EVENTOS_COUNT. */
	private static final String STMT_FETCH_EVENTOS_COUNT = NAMESPACE_EVENTOS + "fetchEventosRowCount";

	/** The Constant STMT_FETCH_EVENTOS_ALL_REQUEST. */
	private static final String STMT_FETCH_EVENTOS_ALL_REQUEST = NAMESPACE_EVENTOS + "fetchAllEventossRequest";

///===================================### BENEFICIOS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_BENEFICIOS = "BeneficiosMap.";

/** The Constant STMT_INSERT_BENEFICIOS. */
private static final String STMT_INSERT_BENEFICIOS = NAMESPACE_BENEFICIOS + "insertBeneficios";

/** The Constant STMT_UPDATE_BENEFICIOS. */
private static final String STMT_UPDATE_BENEFICIOS = NAMESPACE_BENEFICIOS + "updateBeneficios";

/** The Constant STMT_DELETE_BENEFICIOS. */
private static final String STMT_DELETE_BENEFICIOS = NAMESPACE_BENEFICIOS + "deleteBeneficiosById";

	/** The Constant STMT_DELETE_BENEFICIOS_ALL. */
	private static final String STMT_DELETE_BENEFICIOS_ALL = NAMESPACE_BENEFICIOS + "deleteAllBeneficioss";

	/** The Constant STMT_FETCH_BENEFICIOS. */
	private static final String STMT_FETCH_BENEFICIOS = NAMESPACE_BENEFICIOS + "fetchBeneficiosById";

	/** The Constant STMT_FETCH_BENEFICIOS_ALL. */
	private static final String STMT_FETCH_BENEFICIOS_ALL = NAMESPACE_BENEFICIOS + "fetchAllBeneficioss";

	/** The Constant STMT_FETCH_BENEFICIOS_COUNT. */
	private static final String STMT_FETCH_BENEFICIOS_COUNT = NAMESPACE_BENEFICIOS + "fetchBeneficiosRowCount";

	/** The Constant STMT_FETCH_BENEFICIOS_ALL_REQUEST. */
	private static final String STMT_FETCH_BENEFICIOS_ALL_REQUEST = NAMESPACE_BENEFICIOS + "fetchAllBeneficiossRequest";

///===================================### HORAFUNC ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_HORAFUNC = "HorafuncMap.";

/** The Constant STMT_INSERT_HORAFUNC. */
private static final String STMT_INSERT_HORAFUNC = NAMESPACE_HORAFUNC + "insertHorafunc";

/** The Constant STMT_UPDATE_HORAFUNC. */
private static final String STMT_UPDATE_HORAFUNC = NAMESPACE_HORAFUNC + "updateHorafunc";

/** The Constant STMT_DELETE_HORAFUNC. */
private static final String STMT_DELETE_HORAFUNC = NAMESPACE_HORAFUNC + "deleteHorafuncById";

	/** The Constant STMT_DELETE_HORAFUNC_ALL. */
	private static final String STMT_DELETE_HORAFUNC_ALL = NAMESPACE_HORAFUNC + "deleteAllHorafuncs";

	/** The Constant STMT_FETCH_HORAFUNC. */
	private static final String STMT_FETCH_HORAFUNC = NAMESPACE_HORAFUNC + "fetchHorafuncById";

	/** The Constant STMT_FETCH_HORAFUNC_ALL. */
	private static final String STMT_FETCH_HORAFUNC_ALL = NAMESPACE_HORAFUNC + "fetchAllHorafuncs";

	/** The Constant STMT_FETCH_HORAFUNC_COUNT. */
	private static final String STMT_FETCH_HORAFUNC_COUNT = NAMESPACE_HORAFUNC + "fetchHorafuncRowCount";

	/** The Constant STMT_FETCH_HORAFUNC_ALL_REQUEST. */
	private static final String STMT_FETCH_HORAFUNC_ALL_REQUEST = NAMESPACE_HORAFUNC + "fetchAllHorafuncsRequest";

//===================================### FUNCIONARIO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#insertFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
 */
@Override
public InternalResponse insertFuncionario(Funcionario county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_FUNCIONARIO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#updateFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
 */
@Override
public InternalResponse updateFuncionario(Funcionario county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_FUNCIONARIO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#deleteFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
 */
@Override
public InternalResponse deleteFuncionarioById(Funcionario county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FUNCIONARIO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#deleteAllFuncionarios()
 */
@Override
public InternalResponse deleteAllFuncionarios()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FUNCIONARIO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IFuncionarioBAR#fetchFuncionarioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	response.addResult((Funcionario)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FUNCIONARIO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#fetchAllFuncionariosCache()
 */
@Override
public InternalResultsResponse<Funcionario> fetchAllFuncionarios(Funcionario funcionario)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FUNCIONARIO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IFuncionarioBAR#fetchFuncionariosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(FuncionarioInquiryRequest request)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	fetchFuncionariosByRequest(getSqlSession(), request, STMT_FETCH_FUNCIONARIO_COUNT, STMT_FETCH_FUNCIONARIO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchFuncionariosByRequest ####======================================

public static void fetchFuncionariosByRequest(SqlSession sqlSession, FuncionarioInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### EVENTOS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEventosBAR#insertEventos(com.qat.samples.sysmgmt.base.model.Eventos)
 */
@Override
public InternalResponse insertEventos(Eventos county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_EVENTOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEventosBAR#updateEventos(com.qat.samples.sysmgmt.base.model.Eventos)
 */
@Override
public InternalResponse updateEventos(Eventos county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_EVENTOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEventosBAR#deleteEventos(com.qat.samples.sysmgmt.base.model.Eventos)
 */
@Override
public InternalResponse deleteEventosById(Eventos county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EVENTOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEventosBAR#deleteAllEventoss()
 */
@Override
public InternalResponse deleteAllEventos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EVENTOS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IEventosBAR#fetchEventosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request)
{
	InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
	response.addResult((Eventos)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_EVENTOS,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEventosBAR#fetchAllEventossCache()
 */
@Override
public InternalResultsResponse<Eventos> fetchAllEventos(Eventos eventos)
{
	InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_EVENTOS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IEventosBAR#fetchEventossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Eventos> fetchEventosByRequest(EventoInquiryRequest request)
{
	InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
	fetchEventosByRequest(getSqlSession(), request, STMT_FETCH_EVENTOS_COUNT, STMT_FETCH_EVENTOS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchEventossByRequest ####======================================

public static void fetchEventosByRequest(SqlSession sqlSession, EventoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### BENEFICIOS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBeneficiosBAR#insertBeneficios(com.qat.samples.sysmgmt.base.model.Beneficios)
 */
@Override
public InternalResponse insertBeneficios(Beneficios county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_BENEFICIOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBeneficiosBAR#updateBeneficios(com.qat.samples.sysmgmt.base.model.Beneficios)
 */
@Override
public InternalResponse updateBeneficios(Beneficios county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_BENEFICIOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBeneficiosBAR#deleteBeneficios(com.qat.samples.sysmgmt.base.model.Beneficios)
 */
@Override
public InternalResponse deleteBeneficiosById(Beneficios county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_BENEFICIOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBeneficiosBAR#deleteAllBeneficioss()
 */
@Override
public InternalResponse deleteAllBeneficioss()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_BENEFICIOS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IBeneficiosBAR#fetchBeneficiosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request)
{
	InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
	response.addResult((Beneficios)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_BENEFICIOS,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBeneficiosBAR#fetchAllBeneficiossCache()
 */
@Override
public InternalResultsResponse<Beneficios> fetchAllBeneficioss(Beneficios beneficios)
{
	InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_BENEFICIOS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IBeneficiosBAR#fetchBeneficiossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Beneficios> fetchBeneficiossByRequest(BeneficiosInquiryRequest request)
{
	InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
	fetchBeneficiossByRequest(getSqlSession(), request, STMT_FETCH_BENEFICIOS_COUNT, STMT_FETCH_BENEFICIOS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchBeneficiossByRequest ####======================================

public static void fetchBeneficiossByRequest(SqlSession sqlSession, BeneficiosInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### HORAFUNC ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHorafuncBAR#insertHorafunc(com.qat.samples.sysmgmt.base.model.Horafunc)
 */
@Override
public InternalResponse insertHorafunc(HorarioFunc county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_HORAFUNC, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHorafuncBAR#updateHorafunc(com.qat.samples.sysmgmt.base.model.Horafunc)
 */
@Override
public InternalResponse updateHorafunc(HorarioFunc county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_HORAFUNC, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHorafuncBAR#deleteHorafunc(com.qat.samples.sysmgmt.base.model.Horafunc)
 */
@Override
public InternalResponse deleteHorafuncById(HorarioFunc county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_HORAFUNC, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHorafuncBAR#deleteAllHorafuncs()
 */
@Override
public InternalResponse deleteAllHorafuncs()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_HORAFUNC_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IHorafuncBAR#fetchHorafuncById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<HorarioFunc> fetchHorafuncById(FetchByIdRequest request)
{
	InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();
	response.addResult((HorarioFunc)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_HORAFUNC,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHorafuncBAR#fetchAllHorafuncsCache()
 */
@Override
public InternalResultsResponse<HorarioFunc> fetchAllHorafuncs(HorarioFunc horafunc)
{
	InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_HORAFUNC_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IHorafuncBAR#fetchHorafuncsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<HorarioFunc> fetchHorafuncsByRequest(HoraFuncInquiryRequest request)
{
	InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();
	fetchHorafuncsByRequest(getSqlSession(), request, STMT_FETCH_HORAFUNC_COUNT, STMT_FETCH_HORAFUNC_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchHorafuncsByRequest ####======================================

public static void fetchHorafuncsByRequest(SqlSession sqlSession, HoraFuncInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

}
