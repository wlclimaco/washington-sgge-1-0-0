package com.qat.samples.sysmgmt.util.dac.mybatis;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.supermercado.dac.ISupermercadoDAC;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * The Class SupermercadoDACImpl. (Data Access Component - DAC)
 */
public class SupermercadoDACImpl extends SqlSessionDaoSupport implements ISupermercadoDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	private static final Integer PARAMSIZE11 = 11;

	private static final String CDACAD = "cdacad";
	/** The Constant ACADEM. */
	private static final String ACADEM = "academ";
	/** The Constant LOGRAD. */
	private static final String LOGRAD = "lograd";
	/** The Constant NUMEN. */
	private static final String NUMEN = "numen";
	/** The Constant BAIRR. */
	private static final String BAIRR = "bairr";
	/** The Constant CIDADE. */
	private static final String CIDADE = "cidade";
	/** The Constant CEP. */
	private static final String CEP = "cep";
	/** The Constant TELEF. */
	private static final String TELEF = "telef";
	/** The Constant LATLOG. */
	private static final String LATLOG = "latlog";

	private static final String USER = "user";

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "SupermercadoMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertSupermercado";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateSupermercado";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteSupermercadoById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllSupermercados";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchSupermercadoById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllSupermercados";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchSupermercadoRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllSupermercadosRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#insertSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse insertSupermercado(Supermercado supermercado)
	{

		HashMap<String, Object> paramMap = new HashMap<String, Object>(16);
		// paramMap.put("usuario", supermercado.getUsuario());
		// paramMap.put("email", supermercado.getEmail());
		// paramMap.put("site", supermercado.getSite());
		// paramMap.put("senha", supermercado.getSenha());
		// paramMap.put("logradouro", supermercado.getEnderecos().get(0).getLogradouro());
		// paramMap.put("bairro", supermercado.getEnderecos().get(0).getBairro());
		// paramMap.put("estado", supermercado.getEnderecos().get(0).getEstado());
		// paramMap.put("cidade", supermercado.getEnderecos().get(0).getCidade());
		// paramMap.put("numero", supermercado.getEnderecos().get(0).getNumero());
		// paramMap.put("cep", supermercado.getEnderecos().get(0).getCep());
		// paramMap.put("nome", supermercado.getEnderecos().get(0).getNome());
		// paramMap.put("complemento", supermercado.getEnderecos().get(0).getComplemento());
		// paramMap.put("usuarioid", supermercado.getDocumentos().get(0).getId());
		// paramMap.put("rginscmuni", supermercado.getDocumentos().get(0).getRgInc());
		// paramMap.put("cpfcnpj", supermercado.getDocumentos().get(0).getCpfCnpj());
		// paramMap.put("razao", supermercado.getDocumentos().get(0).getRazao());

		InternalResponse response = new InternalResponse();

		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_INSERT, paramMap);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#updateSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse updateSupermercado(Supermercado supermercado)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(20);
		// paramMap.put("supermercadoid", supermercado.getSuperId());
		// paramMap.put("usuario", supermercado.getUsuario());
		// paramMap.put("email", supermercado.getEmail());
		// paramMap.put("site", supermercado.getSite());
		// paramMap.put("senha", supermercado.getSenha());
		// paramMap.put("enderecoid", supermercado.getEnderecos().get(0).getEnderecoid());
		// paramMap.put("id", supermercado.getEnderecos().get(0).getId());
		// paramMap.put("logradouro", supermercado.getEnderecos().get(0).getLogradouro());
		// paramMap.put("bairro", supermercado.getEnderecos().get(0).getBairro());
		// paramMap.put("estado", supermercado.getEnderecos().get(0).getEstado());
		// paramMap.put("cidade", supermercado.getEnderecos().get(0).getCidade());
		// paramMap.put("numero", supermercado.getEnderecos().get(0).getNumero());
		// paramMap.put("cep", supermercado.getEnderecos().get(0).getCep());
		// paramMap.put("nome", supermercado.getEnderecos().get(0).getNome());
		// paramMap.put("complemento", supermercado.getEnderecos().get(0).getComplemento());
		// paramMap.put("documentoid", supermercado.getDocumentos().get(0).getDocumenroid());
		// paramMap.put("usuarioid", supermercado.getUserId());
		// paramMap.put("rginscmuni", supermercado.getDocumentos().get(0).getRgInc());
		// paramMap.put("cpfcnpj", supermercado.getDocumentos().get(0).getCpfCnpj());
		// paramMap.put("razao", supermercado.getDocumentos().get(0).getRazao());

		InternalResponse response = new InternalResponse();
		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_UPDATE, paramMap);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#deleteSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse deleteSupermercado(Supermercado supermercado)
	{
		InternalResponse response = new InternalResponse();

		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, supermercado, response);
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#deleteAllSupermercados()
	 */
	@Override
	public InternalResponse deleteAllSupermercados()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#fetchSupermercadoById
	 * (com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public Supermercado fetchSupermercadoById(FetchByIdRequest request)
	{
		return (Supermercado)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#fetchAllSupermercados()
	 */
	@Override
	public List<Supermercado> fetchAllSupermercados()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.dac.ISupermercadoDAC#fetchSupermercadosByRequest(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Supermercado> fetchSupermercadosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Supermercado> response = new InternalResultsResponse<Supermercado>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
