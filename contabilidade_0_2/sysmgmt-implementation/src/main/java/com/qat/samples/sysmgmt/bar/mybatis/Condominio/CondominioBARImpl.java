package com.qat.samples.sysmgmt.bar.mybatis.Condominio;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Condominio.ICondominioBAR;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class CondominioBARImpl extends SqlSessionDaoSupport implements ICondominioBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### SINDICO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_SINDICO = "SindicoMap.";

/** The Constant STMT_INSERT_SINDICO. */
private static final String STMT_INSERT_SINDICO = NAMESPACE_SINDICO + "insertSindico";

/** The Constant STMT_UPDATE_SINDICO. */
private static final String STMT_UPDATE_SINDICO = NAMESPACE_SINDICO + "updateSindico";

/** The Constant STMT_DELETE_SINDICO. */
private static final String STMT_DELETE_SINDICO = NAMESPACE_SINDICO + "deleteSindicoById";

	/** The Constant STMT_DELETE_SINDICO_ALL. */
	private static final String STMT_DELETE_SINDICO_ALL = NAMESPACE_SINDICO + "deleteAllSindicos";

	/** The Constant STMT_FETCH_SINDICO. */
	private static final String STMT_FETCH_SINDICO = NAMESPACE_SINDICO + "fetchSindicoById";

	/** The Constant STMT_FETCH_SINDICO_ALL. */
	private static final String STMT_FETCH_SINDICO_ALL = NAMESPACE_SINDICO + "fetchAllSindicos";

	/** The Constant STMT_FETCH_SINDICO_COUNT. */
	private static final String STMT_FETCH_SINDICO_COUNT = NAMESPACE_SINDICO + "fetchSindicoRowCount";

	/** The Constant STMT_FETCH_SINDICO_ALL_REQUEST. */
	private static final String STMT_FETCH_SINDICO_ALL_REQUEST = NAMESPACE_SINDICO + "fetchAllSindicosRequest";

///===================================### INQUILINO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_INQUILINO = "InquilinoMap.";

/** The Constant STMT_INSERT_INQUILINO. */
private static final String STMT_INSERT_INQUILINO = NAMESPACE_INQUILINO + "insertInquilino";

/** The Constant STMT_UPDATE_INQUILINO. */
private static final String STMT_UPDATE_INQUILINO = NAMESPACE_INQUILINO + "updateInquilino";

/** The Constant STMT_DELETE_INQUILINO. */
private static final String STMT_DELETE_INQUILINO = NAMESPACE_INQUILINO + "deleteInquilinoById";

	/** The Constant STMT_DELETE_INQUILINO_ALL. */
	private static final String STMT_DELETE_INQUILINO_ALL = NAMESPACE_INQUILINO + "deleteAllInquilinos";

	/** The Constant STMT_FETCH_INQUILINO. */
	private static final String STMT_FETCH_INQUILINO = NAMESPACE_INQUILINO + "fetchInquilinoById";

	/** The Constant STMT_FETCH_INQUILINO_ALL. */
	private static final String STMT_FETCH_INQUILINO_ALL = NAMESPACE_INQUILINO + "fetchAllInquilinos";

	/** The Constant STMT_FETCH_INQUILINO_COUNT. */
	private static final String STMT_FETCH_INQUILINO_COUNT = NAMESPACE_INQUILINO + "fetchInquilinoRowCount";

	/** The Constant STMT_FETCH_INQUILINO_ALL_REQUEST. */
	private static final String STMT_FETCH_INQUILINO_ALL_REQUEST = NAMESPACE_INQUILINO + "fetchAllInquilinosRequest";

///===================================### AVISOS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_AVISOS = "AvisosMap.";

/** The Constant STMT_INSERT_AVISOS. */
private static final String STMT_INSERT_AVISOS = NAMESPACE_AVISOS + "insertAvisos";

/** The Constant STMT_UPDATE_AVISOS. */
private static final String STMT_UPDATE_AVISOS = NAMESPACE_AVISOS + "updateAvisos";

/** The Constant STMT_DELETE_AVISOS. */
private static final String STMT_DELETE_AVISOS = NAMESPACE_AVISOS + "deleteAvisosById";

	/** The Constant STMT_DELETE_AVISOS_ALL. */
	private static final String STMT_DELETE_AVISOS_ALL = NAMESPACE_AVISOS + "deleteAllAvisoss";

	/** The Constant STMT_FETCH_AVISOS. */
	private static final String STMT_FETCH_AVISOS = NAMESPACE_AVISOS + "fetchAvisosById";

	/** The Constant STMT_FETCH_AVISOS_ALL. */
	private static final String STMT_FETCH_AVISOS_ALL = NAMESPACE_AVISOS + "fetchAllAvisoss";

	/** The Constant STMT_FETCH_AVISOS_COUNT. */
	private static final String STMT_FETCH_AVISOS_COUNT = NAMESPACE_AVISOS + "fetchAvisosRowCount";

	/** The Constant STMT_FETCH_AVISOS_ALL_REQUEST. */
	private static final String STMT_FETCH_AVISOS_ALL_REQUEST = NAMESPACE_AVISOS + "fetchAllAvisossRequest";

//===================================### SINDICO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#insertSindico(com.qat.samples.sysmgmt.base.model.Sindico)
 */
@Override
public InternalResponse insertSindico(Sindico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_SINDICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#updateSindico(com.qat.samples.sysmgmt.base.model.Sindico)
 */
@Override
public InternalResponse updateSindico(Sindico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_SINDICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#deleteSindico(com.qat.samples.sysmgmt.base.model.Sindico)
 */
@Override
public InternalResponse deleteSindicoById(Sindico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SINDICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#deleteAllSindicos()
 */
@Override
public InternalResponse deleteAllSindicos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SINDICO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ISindicoBAR#fetchSindicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Sindico> fetchSindicoById(FetchByIdRequest request)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	response.addResult((Sindico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_SINDICO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#fetchAllSindicosCache()
 */
@Override
public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico sindico)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_SINDICO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ISindicoBAR#fetchSindicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	fetchSindicosByRequest(getSqlSession(), request, STMT_FETCH_SINDICO_COUNT, STMT_FETCH_SINDICO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchSindicosByRequest ####======================================

public static void fetchSindicosByRequest(SqlSession sqlSession, SindicoInquiryRequest request, String countStatement,
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


//===================================### INQUILINO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#insertInquilino(com.qat.samples.sysmgmt.base.model.Inquilino)
 */
@Override
public InternalResponse insertInquilino(Inquilino county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_INQUILINO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#updateInquilino(com.qat.samples.sysmgmt.base.model.Inquilino)
 */
@Override
public InternalResponse updateInquilino(Inquilino county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_INQUILINO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#deleteInquilino(com.qat.samples.sysmgmt.base.model.Inquilino)
 */
@Override
public InternalResponse deleteInquilinoById(Inquilino county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_INQUILINO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#deleteAllInquilinos()
 */
@Override
public InternalResponse deleteAllInquilinos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_INQUILINO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IInquilinoBAR#fetchInquilinoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Inquilino> fetchInquilinoById(FetchByIdRequest request)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	response.addResult((Inquilino)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_INQUILINO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#fetchAllInquilinosCache()
 */
@Override
public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino inquilino)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_INQUILINO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IInquilinoBAR#fetchInquilinosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	fetchInquilinosByRequest(getSqlSession(), request, STMT_FETCH_INQUILINO_COUNT, STMT_FETCH_INQUILINO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchInquilinosByRequest ####======================================

public static void fetchInquilinosByRequest(SqlSession sqlSession, InquilinoInquiryRequest request, String countStatement,
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


//===================================### AVISOS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAvisosBAR#insertAvisos(com.qat.samples.sysmgmt.base.model.Avisos)
 */
@Override
public InternalResponse insertAvisos(Avisos county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_AVISOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAvisosBAR#updateAvisos(com.qat.samples.sysmgmt.base.model.Avisos)
 */
@Override
public InternalResponse updateAvisos(Avisos county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_AVISOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAvisosBAR#deleteAvisos(com.qat.samples.sysmgmt.base.model.Avisos)
 */
@Override
public InternalResponse deleteAvisosById(Avisos county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_AVISOS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAvisosBAR#deleteAllAvisoss()
 */
@Override
public InternalResponse deleteAllAvisoss()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_AVISOS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IAvisosBAR#fetchAvisosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Avisos> fetchAvisosById(FetchByIdRequest request)
{
	InternalResultsResponse<Avisos> response = new InternalResultsResponse<Avisos>();
	response.addResult((Avisos)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_AVISOS,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAvisosBAR#fetchAllAvisossCache()
 */
@Override
public InternalResultsResponse<Avisos> fetchAllAvisos(Avisos avisos)
{
	InternalResultsResponse<Avisos> response = new InternalResultsResponse<Avisos>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_AVISOS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IAvisosBAR#fetchAvisossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Avisos> fetchAvisosByRequest(AvisoInquiryRequest request)
{
	InternalResultsResponse<Avisos> response = new InternalResultsResponse<Avisos>();
	fetchAvisossByRequest(getSqlSession(), request, STMT_FETCH_AVISOS_COUNT, STMT_FETCH_AVISOS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchAvisossByRequest ####======================================

public static void fetchAvisossByRequest(SqlSession sqlSession, AvisoInquiryRequest request, String countStatement,
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

@Override
public InternalResultsResponse<Avisos> fetchAvisosByRequest(Avisos request) {
	// TODO Auto-generated method stub
	return null;
}

}
