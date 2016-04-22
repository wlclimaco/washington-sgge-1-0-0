package com.qat.samples.sysmgmt.bar.mybatis.Endereco;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class EnderecoBARImpl extends SqlSessionDaoSupport implements IEnderecoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### ENDERECO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ENDERECO = "EnderecoMap.";

/** The Constant STMT_INSERT_ENDERECO. */
private static final String STMT_INSERT_ENDERECO = NAMESPACE_ENDERECO + "insertEndereco";

/** The Constant STMT_UPDATE_ENDERECO. */
private static final String STMT_UPDATE_ENDERECO = NAMESPACE_ENDERECO + "updateEndereco";

/** The Constant STMT_DELETE_ENDERECO. */
private static final String STMT_DELETE_ENDERECO = NAMESPACE_ENDERECO + "deleteEnderecoById";

	/** The Constant STMT_DELETE_ENDERECO_ALL. */
	private static final String STMT_DELETE_ENDERECO_ALL = NAMESPACE_ENDERECO + "deleteAllEnderecos";

	/** The Constant STMT_FETCH_ENDERECO. */
	private static final String STMT_FETCH_ENDERECO = NAMESPACE_ENDERECO + "fetchEnderecoById";

	/** The Constant STMT_FETCH_ENDERECO_ALL. */
	private static final String STMT_FETCH_ENDERECO_ALL = NAMESPACE_ENDERECO + "fetchAllEnderecos";

	/** The Constant STMT_FETCH_ENDERECO_COUNT. */
	private static final String STMT_FETCH_ENDERECO_COUNT = NAMESPACE_ENDERECO + "fetchEnderecoRowCount";

	/** The Constant STMT_FETCH_ENDERECO_ALL_REQUEST. */
	private static final String STMT_FETCH_ENDERECO_ALL_REQUEST = NAMESPACE_ENDERECO + "fetchAllEnderecosRequest";

//===================================### ENDERECO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEnderecoBAR#insertEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
 */
@Override
public InternalResponse insertEndereco(Endereco endereco)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENDERECO, endereco, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEnderecoBAR#updateEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
 */
@Override
public InternalResponse updateEndereco(Endereco endereco)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENDERECO, endereco, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEnderecoBAR#deleteEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
 */
@Override
public InternalResponse deleteEnderecoById(Endereco endereco)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENDERECO, endereco, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEnderecoBAR#deleteAllEnderecos()
 */
@Override
public InternalResponse deleteAllEnderecos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENDERECO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IEnderecoBAR#fetchEnderecoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Endereco fetchEnderecoById(FetchByIdRequest request)
{
return (Endereco)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ENDERECO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEnderecoBAR#fetchAllEnderecosCache()
 */
@Override
public InternalResultsResponse<Endereco> fetchAllEnderecos(Endereco endereco)
{
	InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ENDERECO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IEnderecoBAR#fetchEnderecosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Endereco> fetchEnderecosByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
	fetchEnderecosByRequest(getSqlSession(), request, STMT_FETCH_ENDERECO_COUNT, STMT_FETCH_ENDERECO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchEnderecosByRequest ####======================================

public static void fetchEnderecosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
