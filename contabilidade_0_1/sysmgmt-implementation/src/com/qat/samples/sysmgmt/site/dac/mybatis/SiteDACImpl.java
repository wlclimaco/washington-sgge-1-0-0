package com.qat.samples.sysmgmt.site.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.contato.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.site.Site;
import com.qat.samples.sysmgmt.site.dac.ISiteDAC;

/**
 * The Class SiteDACImpl. (Data Access Component - DAC)
 */
public class SiteDACImpl extends SqlSessionDaoSupport implements ISiteDAC
{

	private static final String CIDADE_STMT_FETCH_COUNT = "ContatoMap.fetchContatoRowCount";
	private static final String CIDADE_STMT_FETCH_ALL_BY_REQUEST = "ContatoMap.fetchAllContatosByRequest";

	@Override
	public InternalResponse insertSite(Site site)
	{
		InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
		QATMyBatisDacHelper.doInsert(getSqlSession(), "", site, response);

		return response;
	}

	@Override
	public InternalResponse updateSite(Site site)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(site.getModelAction())
				&& (site.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			QATMyBatisDacHelper.doUpdate(getSqlSession(), "", site, response);

		}

		return response;
	}

	@Override
	public InternalResponse deleteSite(Site site)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "", site, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "CIDADE_STMT_FETCH_COUNT",
				"CIDADE_STMT_FETCH_ALL_BY_REQUEST", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Site> fetchAllSites()
	{
		InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "", null, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request)
	{
		InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "", request, response);

		return response;
	}

	@Override
	public InternalResponse insertContato(Contato contato)
	{
		InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
		QATMyBatisDacHelper.doInsert(getSqlSession(), "", contato, response);

		return response;
	}

	@Override
	public InternalResponse updateContato(Contato site)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(site.getModelAction())
				&& (site.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			QATMyBatisDacHelper.doUpdate(getSqlSession(), "", site, response);

		}

		return response;
	}

	@Override
	public InternalResponse deleteContato(Contato site)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "", site, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Contato> fetchAllContatos()
	{
		InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "", null, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "", request, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request)
	{
		InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, CIDADE_STMT_FETCH_COUNT,
				CIDADE_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResponse insertOrdemServico(OrdemServico site)
	{
		InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
		QATMyBatisDacHelper.doInsert(getSqlSession(), "", site, response);

		return response;
	}

	@Override
	public InternalResponse updateOrdemServico(OrdemServico site)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(site.getModelAction())
				&& (site.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			QATMyBatisDacHelper.doUpdate(getSqlSession(), "", site, response);

		}

		return response;
	}

	@Override
	public InternalResponse deleteOrdemServico(OrdemServico site)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "", site, response);
		return response;
	}

	@Override
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos()
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "", null, response);

		return response;
	}

	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "", request, response);

		return response;
	}

	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "OrdemServicoMap.fetchOrdemServicoRowCount",
				"OrdemServicoMap.fetchAllOrdemServicosByRequest", response);
		return response;
	}

}
