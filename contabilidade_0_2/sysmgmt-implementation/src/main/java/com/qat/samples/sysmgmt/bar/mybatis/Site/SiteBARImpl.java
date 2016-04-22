package com.qat.samples.sysmgmt.bar.mybatis.Site;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class SiteBARImpl extends SqlSessionDaoSupport implements ISiteBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### SITE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_SITE = "SiteMap.";

/** The Constant STMT_INSERT_SITE. */
private static final String STMT_INSERT_SITE = NAMESPACE_SITE + "insertSite";

/** The Constant STMT_UPDATE_SITE. */
private static final String STMT_UPDATE_SITE = NAMESPACE_SITE + "updateSite";

/** The Constant STMT_DELETE_SITE. */
private static final String STMT_DELETE_SITE = NAMESPACE_SITE + "deleteSiteById";

	/** The Constant STMT_DELETE_SITE_ALL. */
	private static final String STMT_DELETE_SITE_ALL = NAMESPACE_SITE + "deleteAllSites";

	/** The Constant STMT_FETCH_SITE. */
	private static final String STMT_FETCH_SITE = NAMESPACE_SITE + "fetchSiteById";

	/** The Constant STMT_FETCH_SITE_ALL. */
	private static final String STMT_FETCH_SITE_ALL = NAMESPACE_SITE + "fetchAllSites";

	/** The Constant STMT_FETCH_SITE_COUNT. */
	private static final String STMT_FETCH_SITE_COUNT = NAMESPACE_SITE + "fetchSiteRowCount";

	/** The Constant STMT_FETCH_SITE_ALL_REQUEST. */
	private static final String STMT_FETCH_SITE_ALL_REQUEST = NAMESPACE_SITE + "fetchAllSitesRequest";

///===================================### CONTATO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CONTATO = "ContatoMap.";

/** The Constant STMT_INSERT_CONTATO. */
private static final String STMT_INSERT_CONTATO = NAMESPACE_CONTATO + "insertContato";

/** The Constant STMT_UPDATE_CONTATO. */
private static final String STMT_UPDATE_CONTATO = NAMESPACE_CONTATO + "updateContato";

/** The Constant STMT_DELETE_CONTATO. */
private static final String STMT_DELETE_CONTATO = NAMESPACE_CONTATO + "deleteContatoById";

	/** The Constant STMT_DELETE_CONTATO_ALL. */
	private static final String STMT_DELETE_CONTATO_ALL = NAMESPACE_CONTATO + "deleteAllContatos";

	/** The Constant STMT_FETCH_CONTATO. */
	private static final String STMT_FETCH_CONTATO = NAMESPACE_CONTATO + "fetchContatoById";

	/** The Constant STMT_FETCH_CONTATO_ALL. */
	private static final String STMT_FETCH_CONTATO_ALL = NAMESPACE_CONTATO + "fetchAllContatos";

	/** The Constant STMT_FETCH_CONTATO_COUNT. */
	private static final String STMT_FETCH_CONTATO_COUNT = NAMESPACE_CONTATO + "fetchContatoRowCount";

	/** The Constant STMT_FETCH_CONTATO_ALL_REQUEST. */
	private static final String STMT_FETCH_CONTATO_ALL_REQUEST = NAMESPACE_CONTATO + "fetchAllContatosRequest";

///===================================### ORDEMSERVICO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ORDEMSERVICO = "OrdemServicoMap.";

/** The Constant STMT_INSERT_ORDEMSERVICO. */
private static final String STMT_INSERT_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "insertOrdemServico";

/** The Constant STMT_UPDATE_ORDEMSERVICO. */
private static final String STMT_UPDATE_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "updateOrdemServico";

/** The Constant STMT_DELETE_ORDEMSERVICO. */
private static final String STMT_DELETE_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "deleteOrdemServicoById";

	/** The Constant STMT_DELETE_ORDEMSERVICO_ALL. */
	private static final String STMT_DELETE_ORDEMSERVICO_ALL = NAMESPACE_ORDEMSERVICO + "deleteAllOrdemServicos";

	/** The Constant STMT_FETCH_ORDEMSERVICO. */
	private static final String STMT_FETCH_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "fetchOrdemServicoById";

	/** The Constant STMT_FETCH_ORDEMSERVICO_ALL. */
	private static final String STMT_FETCH_ORDEMSERVICO_ALL = NAMESPACE_ORDEMSERVICO + "fetchAllOrdemServicos";

	/** The Constant STMT_FETCH_ORDEMSERVICO_COUNT. */
	private static final String STMT_FETCH_ORDEMSERVICO_COUNT = NAMESPACE_ORDEMSERVICO + "fetchOrdemServicoRowCount";

	/** The Constant STMT_FETCH_ORDEMSERVICO_ALL_REQUEST. */
	private static final String STMT_FETCH_ORDEMSERVICO_ALL_REQUEST = NAMESPACE_ORDEMSERVICO + "fetchAllOrdemServicosRequest";

///===================================### PLANO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PLANO = "PlanoMap.";

/** The Constant STMT_INSERT_PLANO. */
private static final String STMT_INSERT_PLANO = NAMESPACE_PLANO + "insertPlano";

/** The Constant STMT_UPDATE_PLANO. */
private static final String STMT_UPDATE_PLANO = NAMESPACE_PLANO + "updatePlano";

/** The Constant STMT_DELETE_PLANO. */
private static final String STMT_DELETE_PLANO = NAMESPACE_PLANO + "deletePlanoById";

	/** The Constant STMT_DELETE_PLANO_ALL. */
	private static final String STMT_DELETE_PLANO_ALL = NAMESPACE_PLANO + "deleteAllPlanos";

	/** The Constant STMT_FETCH_PLANO. */
	private static final String STMT_FETCH_PLANO = NAMESPACE_PLANO + "fetchPlanoById";

	/** The Constant STMT_FETCH_PLANO_ALL. */
	private static final String STMT_FETCH_PLANO_ALL = NAMESPACE_PLANO + "fetchAllPlanos";

	/** The Constant STMT_FETCH_PLANO_COUNT. */
	private static final String STMT_FETCH_PLANO_COUNT = NAMESPACE_PLANO + "fetchPlanoRowCount";

	/** The Constant STMT_FETCH_PLANO_ALL_REQUEST. */
	private static final String STMT_FETCH_PLANO_ALL_REQUEST = NAMESPACE_PLANO + "fetchAllPlanosRequest";

//===================================### SITE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISiteBAR#insertSite(com.qat.samples.sysmgmt.base.model.Site)
 */
@Override
public InternalResponse insertSite(Site county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_SITE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISiteBAR#updateSite(com.qat.samples.sysmgmt.base.model.Site)
 */
@Override
public InternalResponse updateSite(Site county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_SITE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISiteBAR#deleteSite(com.qat.samples.sysmgmt.base.model.Site)
 */
@Override
public InternalResponse deleteSiteById(Site county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SITE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISiteBAR#deleteAllSites()
 */
@Override
public InternalResponse deleteAllSites()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SITE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ISiteBAR#fetchSiteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request)
{
	InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	response.addResult((Site)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_SITE,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISiteBAR#fetchAllSitesCache()
 */
@Override
public InternalResultsResponse<Site> fetchAllSites(Site site)
{
	InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_SITE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ISiteBAR#fetchSitesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Site> fetchSitesByRequest(SiteInquiryRequest request)
{
	InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	fetchSitesByRequest(getSqlSession(), request, STMT_FETCH_SITE_COUNT, STMT_FETCH_SITE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchSitesByRequest ####======================================

public static void fetchSitesByRequest(SqlSession sqlSession, SiteInquiryRequest request, String countStatement,
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


//===================================### CONTATO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContatoBAR#insertContato(com.qat.samples.sysmgmt.base.model.Contato)
 */
@Override
public InternalResponse insertContato(Contato county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONTATO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContatoBAR#updateContato(com.qat.samples.sysmgmt.base.model.Contato)
 */
@Override
public InternalResponse updateContato(Contato county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONTATO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContatoBAR#deleteContato(com.qat.samples.sysmgmt.base.model.Contato)
 */
@Override
public InternalResponse deleteContatoById(Contato county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTATO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContatoBAR#deleteAllContatos()
 */
@Override
public InternalResponse deleteAllContatos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTATO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IContatoBAR#fetchContatoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request)
{
	InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();
	response.addResult((Contato)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONTATO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContatoBAR#fetchAllContatosCache()
 */
@Override
public InternalResultsResponse<Contato> fetchAllContatos(Contato contato)
{
	InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONTATO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IContatoBAR#fetchContatosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request)
{
	InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();
	fetchContatosByRequest(getSqlSession(), request, STMT_FETCH_CONTATO_COUNT, STMT_FETCH_CONTATO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchContatosByRequest ####======================================

public static void fetchContatosByRequest(SqlSession sqlSession, ContatoInquiryRequest request, String countStatement,
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


//===================================### ORDEMSERVICO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#insertOrdemServico(com.qat.samples.sysmgmt.base.model.OrdemServico)
 */
@Override
public InternalResponse insertOrdemServico(OrdemServico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ORDEMSERVICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#updateOrdemServico(com.qat.samples.sysmgmt.base.model.OrdemServico)
 */
@Override
public InternalResponse updateOrdemServico(OrdemServico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ORDEMSERVICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#deleteOrdemServico(com.qat.samples.sysmgmt.base.model.OrdemServico)
 */
@Override
public InternalResponse deleteOrdemServicoById(OrdemServico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ORDEMSERVICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#deleteAllOrdemServicos()
 */
@Override
public InternalResponse deleteAllOrdemServicos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ORDEMSERVICO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IOrdemServicoBAR#fetchOrdemServicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	response.addResult((OrdemServico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ORDEMSERVICO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#fetchAllOrdemServicosCache()
 */
@Override
public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico ordemservico)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ORDEMSERVICO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IOrdemServicoBAR#fetchOrdemServicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	fetchOrdemServicosByRequest(getSqlSession(), request, STMT_FETCH_ORDEMSERVICO_COUNT, STMT_FETCH_ORDEMSERVICO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchOrdemServicosByRequest ####======================================

public static void fetchOrdemServicosByRequest(SqlSession sqlSession, OrdemServicoInquiryRequest request, String countStatement,
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


//===================================### PLANO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPlanoBAR#insertPlano(com.qat.samples.sysmgmt.base.model.Plano)
 */
@Override
public InternalResponse insertPlano(Plano county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PLANO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPlanoBAR#updatePlano(com.qat.samples.sysmgmt.base.model.Plano)
 */
@Override
public InternalResponse updatePlano(Plano county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PLANO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPlanoBAR#deletePlano(com.qat.samples.sysmgmt.base.model.Plano)
 */
@Override
public InternalResponse deletePlanoById(Plano county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PLANO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPlanoBAR#deleteAllPlanos()
 */
@Override
public InternalResponse deleteAllPlanos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PLANO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IPlanoBAR#fetchPlanoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Plano> fetchPlanoById(FetchByIdRequest request)
{
	InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();
	response.addResult((Plano)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PLANO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPlanoBAR#fetchAllPlanosCache()
 */
@Override
public InternalResultsResponse<Plano> fetchAllPlanos(Plano plano)
{
	InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PLANO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IPlanoBAR#fetchPlanosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Plano> fetchPlanosByRequest(PlanoInquiryRequest request)
{
	InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();
	fetchPlanosByRequest(getSqlSession(), request, STMT_FETCH_PLANO_COUNT, STMT_FETCH_PLANO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchPlanosByRequest ####======================================

public static void fetchPlanosByRequest(SqlSession sqlSession, PlanoInquiryRequest request, String countStatement,
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
