package com.qat.samples.sysmgmt.bar.mybatis.Fiscal;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class FiscalBARImpl extends SqlSessionDaoSupport implements IFiscalBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### REGIME ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_REGIME = "RegimeMap.";

/** The Constant STMT_INSERT_REGIME. */
private static final String STMT_INSERT_REGIME = NAMESPACE_REGIME + "insertRegime";

/** The Constant STMT_UPDATE_REGIME. */
private static final String STMT_UPDATE_REGIME = NAMESPACE_REGIME + "updateRegime";

/** The Constant STMT_DELETE_REGIME. */
private static final String STMT_DELETE_REGIME = NAMESPACE_REGIME + "deleteRegimeById";

	/** The Constant STMT_DELETE_REGIME_ALL. */
	private static final String STMT_DELETE_REGIME_ALL = NAMESPACE_REGIME + "deleteAllRegimes";

	/** The Constant STMT_FETCH_REGIME. */
	private static final String STMT_FETCH_REGIME = NAMESPACE_REGIME + "fetchRegimeById";

	/** The Constant STMT_FETCH_REGIME_ALL. */
	private static final String STMT_FETCH_REGIME_ALL = NAMESPACE_REGIME + "fetchAllRegimes";

	/** The Constant STMT_FETCH_REGIME_COUNT. */
	private static final String STMT_FETCH_REGIME_COUNT = NAMESPACE_REGIME + "fetchRegimeRowCount";

	/** The Constant STMT_FETCH_REGIME_ALL_REQUEST. */
	private static final String STMT_FETCH_REGIME_ALL_REQUEST = NAMESPACE_REGIME + "fetchAllRegimesRequest";

///===================================### CFOP ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CFOP = "CfopMap.";

/** The Constant STMT_INSERT_CFOP. */
private static final String STMT_INSERT_CFOP = NAMESPACE_CFOP + "insertCfop";

/** The Constant STMT_UPDATE_CFOP. */
private static final String STMT_UPDATE_CFOP = NAMESPACE_CFOP + "updateCfop";

/** The Constant STMT_DELETE_CFOP. */
private static final String STMT_DELETE_CFOP = NAMESPACE_CFOP + "deleteCfopById";

	/** The Constant STMT_DELETE_CFOP_ALL. */
	private static final String STMT_DELETE_CFOP_ALL = NAMESPACE_CFOP + "deleteAllCfops";

	/** The Constant STMT_FETCH_CFOP. */
	private static final String STMT_FETCH_CFOP = NAMESPACE_CFOP + "fetchCfopById";

	/** The Constant STMT_FETCH_CFOP_ALL. */
	private static final String STMT_FETCH_CFOP_ALL = NAMESPACE_CFOP + "fetchAllCfops";

	/** The Constant STMT_FETCH_CFOP_COUNT. */
	private static final String STMT_FETCH_CFOP_COUNT = NAMESPACE_CFOP + "fetchCfopRowCount";

	/** The Constant STMT_FETCH_CFOP_ALL_REQUEST. */
	private static final String STMT_FETCH_CFOP_ALL_REQUEST = NAMESPACE_CFOP + "fetchAllCfopsRequest";

///===================================### CNAE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CNAE = "CnaeMap.";

/** The Constant STMT_INSERT_CNAE. */
private static final String STMT_INSERT_CNAE = NAMESPACE_CNAE + "insertCnae";

/** The Constant STMT_UPDATE_CNAE. */
private static final String STMT_UPDATE_CNAE = NAMESPACE_CNAE + "updateCnae";

/** The Constant STMT_DELETE_CNAE. */
private static final String STMT_DELETE_CNAE = NAMESPACE_CNAE + "deleteCnaeById";

	/** The Constant STMT_DELETE_CNAE_ALL. */
	private static final String STMT_DELETE_CNAE_ALL = NAMESPACE_CNAE + "deleteAllCnaes";

	/** The Constant STMT_FETCH_CNAE. */
	private static final String STMT_FETCH_CNAE = NAMESPACE_CNAE + "fetchCnaeById";

	/** The Constant STMT_FETCH_CNAE_ALL. */
	private static final String STMT_FETCH_CNAE_ALL = NAMESPACE_CNAE + "fetchAllCnaes";

	/** The Constant STMT_FETCH_CNAE_COUNT. */
	private static final String STMT_FETCH_CNAE_COUNT = NAMESPACE_CNAE + "fetchCnaeRowCount";

	/** The Constant STMT_FETCH_CNAE_ALL_REQUEST. */
	private static final String STMT_FETCH_CNAE_ALL_REQUEST = NAMESPACE_CNAE + "fetchAllCnaesRequest";

///===================================### CNAEEMPRESA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CNAEEMPRESA = "CnaeempresaMap.";

/** The Constant STMT_INSERT_CNAEEMPRESA. */
private static final String STMT_INSERT_CNAEEMPRESA = NAMESPACE_CNAEEMPRESA + "insertCnaeempresa";

/** The Constant STMT_UPDATE_CNAEEMPRESA. */
private static final String STMT_UPDATE_CNAEEMPRESA = NAMESPACE_CNAEEMPRESA + "updateCnaeempresa";

/** The Constant STMT_DELETE_CNAEEMPRESA. */
private static final String STMT_DELETE_CNAEEMPRESA = NAMESPACE_CNAEEMPRESA + "deleteCnaeempresaById";

	/** The Constant STMT_DELETE_CNAEEMPRESA_ALL. */
	private static final String STMT_DELETE_CNAEEMPRESA_ALL = NAMESPACE_CNAEEMPRESA + "deleteAllCnaeempresas";

	/** The Constant STMT_FETCH_CNAEEMPRESA. */
	private static final String STMT_FETCH_CNAEEMPRESA = NAMESPACE_CNAEEMPRESA + "fetchCnaeempresaById";

	/** The Constant STMT_FETCH_CNAEEMPRESA_ALL. */
	private static final String STMT_FETCH_CNAEEMPRESA_ALL = NAMESPACE_CNAEEMPRESA + "fetchAllCnaeempresas";

	/** The Constant STMT_FETCH_CNAEEMPRESA_COUNT. */
	private static final String STMT_FETCH_CNAEEMPRESA_COUNT = NAMESPACE_CNAEEMPRESA + "fetchCnaeempresaRowCount";

	/** The Constant STMT_FETCH_CNAEEMPRESA_ALL_REQUEST. */
	private static final String STMT_FETCH_CNAEEMPRESA_ALL_REQUEST = NAMESPACE_CNAEEMPRESA + "fetchAllCnaeempresasRequest";

//===================================### REGIME ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRegimeBAR#insertRegime(com.qat.samples.sysmgmt.base.model.Regime)
 */
@Override
public InternalResponse insertRegime(Regime regime)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_REGIME, regime, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRegimeBAR#updateRegime(com.qat.samples.sysmgmt.base.model.Regime)
 */
@Override
public InternalResponse updateRegime(Regime regime)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_REGIME, regime, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRegimeBAR#deleteRegime(com.qat.samples.sysmgmt.base.model.Regime)
 */
@Override
public InternalResponse deleteRegimeById(Regime regime)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_REGIME, regime, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRegimeBAR#deleteAllRegimes()
 */
@Override
public InternalResponse deleteAllRegimes()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_REGIME_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IRegimeBAR#fetchRegimeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Regime fetchRegimeById(FetchByIdRequest request)
{
return (Regime)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_REGIME, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRegimeBAR#fetchAllRegimesCache()
 */
@Override
public InternalResultsResponse<Regime> fetchAllRegimes(Regime regime)
{
	InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_REGIME_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IRegimeBAR#fetchRegimesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Regime> fetchRegimesByRequest(RegimeInquiryRequest request)
{
	InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();
	fetchRegimesByRequest(getSqlSession(), request, STMT_FETCH_REGIME_COUNT, STMT_FETCH_REGIME_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchRegimesByRequest ####======================================

public static void fetchRegimesByRequest(SqlSession sqlSession, RegimeInquiryRequest request, String countStatement,
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


//===================================### CFOP ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#insertCfop(com.qat.samples.sysmgmt.base.model.Cfop)
 */
@Override
public InternalResponse insertCfop(Cfop cfop)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CFOP, cfop, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#updateCfop(com.qat.samples.sysmgmt.base.model.Cfop)
 */
@Override
public InternalResponse updateCfop(Cfop cfop)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CFOP, cfop, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#deleteCfop(com.qat.samples.sysmgmt.base.model.Cfop)
 */
@Override
public InternalResponse deleteCfopById(Cfop cfop)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CFOP, cfop, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#deleteAllCfops()
 */
@Override
public InternalResponse deleteAllCfops()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CFOP_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICfopBAR#fetchCfopById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Cfop fetchCfopById(FetchByIdRequest request)
{
return (Cfop)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CFOP, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#fetchAllCfopsCache()
 */
@Override
public InternalResultsResponse<Cfop> fetchAllCfops(Cfop cfop)
{
	InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CFOP_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICfopBAR#fetchCfopsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request)
{
	InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
	fetchCfopsByRequest(getSqlSession(), request, STMT_FETCH_CFOP_COUNT, STMT_FETCH_CFOP_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCfopsByRequest ####======================================

public static void fetchCfopsByRequest(SqlSession sqlSession, CfopInquiryRequest request, String countStatement,
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


//===================================### CNAE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeBAR#insertCnae(com.qat.samples.sysmgmt.base.model.Cnae)
 */
@Override
public InternalResponse insertCnae(Cnae cnae)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CNAE, cnae, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeBAR#updateCnae(com.qat.samples.sysmgmt.base.model.Cnae)
 */
@Override
public InternalResponse updateCnae(Cnae cnae)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CNAE, cnae, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeBAR#deleteCnae(com.qat.samples.sysmgmt.base.model.Cnae)
 */
@Override
public InternalResponse deleteCnaeById(Cnae cnae)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CNAE, cnae, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeBAR#deleteAllCnaes()
 */
@Override
public InternalResponse deleteAllCnaes()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CNAE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICnaeBAR#fetchCnaeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Cnae fetchCnaeById(FetchByIdRequest request)
{
return (Cnae)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CNAE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeBAR#fetchAllCnaesCache()
 */
@Override
public InternalResultsResponse<Cnae> fetchAllCnaes(Cnae cnae)
{
	InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CNAE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICnaeBAR#fetchCnaesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cnae> fetchCnaesByRequest(CnaeInquiryRequest request)
{
	InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
	fetchCnaesByRequest(getSqlSession(), request, STMT_FETCH_CNAE_COUNT, STMT_FETCH_CNAE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCnaesByRequest ####======================================

public static void fetchCnaesByRequest(SqlSession sqlSession, CnaeInquiryRequest request, String countStatement,
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


//===================================### CNAEEMPRESA ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeEmpresaBAR#insertCnaeEmpresa(com.qat.samples.sysmgmt.base.model.CnaeEmpresa)
 */
@Override
public InternalResponse insertCnaeEmpresa(CnaeEmpresa cnaeempresa)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CNAEEMPRESA, cnaeempresa, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeEmpresaBAR#updateCnaeEmpresa(com.qat.samples.sysmgmt.base.model.CnaeEmpresa)
 */
@Override
public InternalResponse updateCnaeEmpresa(CnaeEmpresa cnaeempresa)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CNAEEMPRESA, cnaeempresa, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeEmpresaBAR#deleteCnaeEmpresa(com.qat.samples.sysmgmt.base.model.CnaeEmpresa)
 */
@Override
public InternalResponse deleteCnaeEmpresaById(CnaeEmpresa cnaeempresa)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CNAEEMPRESA, cnaeempresa, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeEmpresaBAR#deleteAllCnaeEmpresas()
 */
@Override
public InternalResponse deleteAllCnaeEmpresas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CNAEEMPRESA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICnaeEmpresaBAR#fetchCnaeEmpresaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public CnaeEmpresa fetchCnaeEmpresaById(FetchByIdRequest request)
{
return (CnaeEmpresa)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CNAEEMPRESA, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICnaeEmpresaBAR#fetchAllCnaeEmpresasCache()
 */
@Override
public InternalResultsResponse<CnaeEmpresa> fetchAllCnaeEmpresas(CnaeEmpresa cnaeempresa)
{
	InternalResultsResponse<CnaeEmpresa> response = new InternalResultsResponse<CnaeEmpresa>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CNAEEMPRESA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICnaeEmpresaBAR#fetchCnaeEmpresasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<CnaeEmpresa> fetchCnaeEmpresasByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<CnaeEmpresa> response = new InternalResultsResponse<CnaeEmpresa>();
	fetchCnaeEmpresasByRequest(getSqlSession(), request, STMT_FETCH_CNAEEMPRESA_COUNT, STMT_FETCH_CNAEEMPRESA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCnaeEmpresasByRequest ####======================================

public static void fetchCnaeEmpresasByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
