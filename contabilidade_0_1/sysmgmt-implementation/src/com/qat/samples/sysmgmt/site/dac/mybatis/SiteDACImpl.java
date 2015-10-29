package com.qat.samples.sysmgmt.site.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.site.Site;
import com.qat.samples.sysmgmt.site.dac.ISiteDAC;

/**
 * The Class SiteDACImpl. (Data Access Component - DAC)
 */
public class SiteDACImpl extends SqlSessionDaoSupport implements ISiteDAC
{

	@Override
	public InternalResponse insertSite(Site site)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse updateSite(Site site)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteSite(Site site)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteAllSites()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> fetchAllSites()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site fetchSiteById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
	// /** The Constant ZERO. */
	// private static final int ZERO = 0;
	//
	// private static final Integer PARAMSIZE11 = 11;
	//
	// private static final String CDACAD = "cdacad";
	// /** The Constant ACADEM. */
	// private static final String ACADEM = "academ";
	// /** The Constant LOGRAD. */
	// private static final String LOGRAD = "lograd";
	// /** The Constant NUMEN. */
	// private static final String NUMEN = "numen";
	// /** The Constant BAIRR. */
	// private static final String BAIRR = "bairr";
	// /** The Constant CIDADE. */
	// private static final String CIDADE = "cidade";
	// /** The Constant CEP. */
	// private static final String CEP = "cep";
	// /** The Constant TELEF. */
	// private static final String TELEF = "telef";
	// /** The Constant LATLOG. */
	// private static final String LATLOG = "latlog";
	//
	// private static final String USER = "user";
	//
	// /** The Constant NAMESPACE. */
	// private static final String NAMESPACE = "SiteMap.";
	//
	// /** The Constant STMT_INSERT. */
	// private static final String STMT_INSERT = NAMESPACE + "insertSite";
	//
	// /** The Constant STMT_UPDATE. */
	// private static final String STMT_UPDATE = NAMESPACE + "updateSite";
	//
	// /** The Constant STMT_VERSION. */
	// private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";
	//
	// /** The Constant STMT_DELETE. */
	// private static final String STMT_DELETE = NAMESPACE + "deleteSiteById";
	//
	// /** The Constant STMT_DELETE_ALL. */
	// private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllSites";
	//
	// /** The Constant STMT_FETCH. */
	// private static final String STMT_FETCH = NAMESPACE + "fetchSiteById";
	//
	// /** The Constant STMT_FETCH_ALL. */
	// private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllSites";
	//
	// /** The Constant STMT_FETCH_COUNT. */
	// private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchSiteRowCount";
	//
	// /** The Constant STMT_FETCH_ALL_REQUEST. */
	// private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllSitesRequest";
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.base.dac.ISiteDAC#insertSite(com.qat.samples.sysmgmt.base.model.Site
	// * )
	// */
	// @Override
	// public InternalResponse insertSite(Site site)
	// {
	//
	// HashMap<String, Object> paramMap = new HashMap<String, Object>(16);
	// paramMap.put("usuario", site.getUsuario());
	// paramMap.put("email", site.getEmail());
	// paramMap.put("site", site.getSite());
	// paramMap.put("senha", site.getSenha());
	// paramMap.put("logradouro", site.getEnderecos().get(0).getLogradouro());
	// paramMap.put("bairro", site.getEnderecos().get(0).getBairro());
	// paramMap.put("estado", site.getEnderecos().get(0).getEstado());
	// paramMap.put("cidade", site.getEnderecos().get(0).getCidade());
	// paramMap.put("numero", site.getEnderecos().get(0).getNumero());
	// paramMap.put("cep", site.getEnderecos().get(0).getCep());
	// paramMap.put("nome", site.getEnderecos().get(0).getNome());
	// paramMap.put("complemento", site.getEnderecos().get(0).getComplemento());
	// paramMap.put("usuarioid", site.getDocumentos().get(0).getId());
	// paramMap.put("rginscmuni", site.getDocumentos().get(0).getRgInc());
	// paramMap.put("cpfcnpj", site.getDocumentos().get(0).getCpfCnpj());
	// paramMap.put("razao", site.getDocumentos().get(0).getRazao());
	//
	// InternalResponse response = new InternalResponse();
	//
	// Integer academiaId =
	// (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_INSERT, paramMap);
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.base.dac.ISiteDAC#updateSite(com.qat.samples.sysmgmt.base.model.Site
	// * )
	// */
	// @Override
	// public InternalResponse updateSite(Site site)
	// {
	// HashMap<String, Object> paramMap = new HashMap<String, Object>(20);
	// paramMap.put("siteid", site.getSuperId());
	// paramMap.put("usuario", site.getUsuario());
	// paramMap.put("email", site.getEmail());
	// paramMap.put("site", site.getSite());
	// paramMap.put("senha", site.getSenha());
	// paramMap.put("enderecoid", site.getEnderecos().get(0).getEnderecoid());
	// paramMap.put("id", site.getEnderecos().get(0).getId());
	// paramMap.put("logradouro", site.getEnderecos().get(0).getLogradouro());
	// paramMap.put("bairro", site.getEnderecos().get(0).getBairro());
	// paramMap.put("estado", site.getEnderecos().get(0).getEstado());
	// paramMap.put("cidade", site.getEnderecos().get(0).getCidade());
	// paramMap.put("numero", site.getEnderecos().get(0).getNumero());
	// paramMap.put("cep", site.getEnderecos().get(0).getCep());
	// paramMap.put("nome", site.getEnderecos().get(0).getNome());
	// paramMap.put("complemento", site.getEnderecos().get(0).getComplemento());
	// paramMap.put("documentoid", site.getDocumentos().get(0).getDocumenroid());
	// paramMap.put("usuarioid", site.getUserId());
	// paramMap.put("rginscmuni", site.getDocumentos().get(0).getRgInc());
	// paramMap.put("cpfcnpj", site.getDocumentos().get(0).getCpfCnpj());
	// paramMap.put("razao", site.getDocumentos().get(0).getRazao());
	//
	// InternalResponse response = new InternalResponse();
	// Integer academiaId =
	// (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_UPDATE, paramMap);
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.base.dac.ISiteDAC#deleteSite(com.qat.samples.sysmgmt.base.model.Site
	// * )
	// */
	// @Override
	// public InternalResponse deleteSite(Site site)
	// {
	// InternalResponse response = new InternalResponse();
	//
	// QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, site, response);
	// return response;
	//
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.qat.samples.sysmgmt.base.dac.ISiteDAC#deleteAllSites()
	// */
	// @Override
	// public InternalResponse deleteAllSites()
	// {
	// InternalResponse response = new InternalResponse();
	// QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.base.dac.ISiteDAC#fetchSiteById
	// * (com.qat.samples.sysmgmt.base.model.Site
	// * )
	// */
	// @Override
	// public Site fetchSiteById(FetchByIdRequest request)
	// {
	// return (Site)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.qat.samples.sysmgmt.base.dac.ISiteDAC#fetchAllSites()
	// */
	// @Override
	// public List<Site> fetchAllSites()
	// {
	// return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.dac.ISiteDAC#fetchSitesByRequest(com.qat.samples.sysmgmt.model.request.
	// * SiteInquiryRequest)
	// */
	// @Override
	// public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request)
	// {
	// InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	// PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
	// response);
	// return response;
	// }
}
