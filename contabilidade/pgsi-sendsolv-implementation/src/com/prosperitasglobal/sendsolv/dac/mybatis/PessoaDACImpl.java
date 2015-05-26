package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EnderecoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.StatusDACD;
import com.prosperitasglobal.sendsolv.model.Cliente;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PessoaDACImpl.
 */
public class PessoaDACImpl extends SqlSessionDaoSupport implements IPessoaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "PessoaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchPessoaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllPessoasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchPessoaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertPessoa";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associatePessoaWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updatePessoa";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deletePessoaById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updatePessoaDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associatePessoaWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deletePessoaDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PessoaDACImpl.class);

	/** The valid sort fields for an pessoa inquiry. Will be injected by Spring. */
	private Map<String, String> pessoaInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the pessoa inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the pessoa inquiry.
	 */
	public Map<String, String> getPessoaInquiryValidSortFields()
	{
		return pessoaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the pessoa inquiry. Attribute injected by Spring.
	 *
	 * @param pessoaInquiryValidSortFields The valid sort fields for the pessoa inquiry to set.
	 */
	public void setPessoaInquiryValidSortFields(Map<String, String> pessoaInquiryValidSortFields)
	{
		this.pessoaInquiryValidSortFields = pessoaInquiryValidSortFields;
	}

	@Override
	public InternalResultsResponse<Cliente> insertCliente(Cliente pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, pessoa, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainCnaeAssociations(pessoa.getCnaes, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainEmailAssociations(pessoa.getEmails, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainTelefoneAssociations(pessoa.getTelefones, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainDocumentoAssociations(pessoa.getDocumentos, response, insertCount, null, null,
						null);


		insertCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, insertCount, null, null,
						null);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was inserted then add the Cliente to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Cliente> updateCliente(Cliente pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, pessoa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainCnaeAssociations(pessoa.getCnaes, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainEmailAssociations(pessoa.getEmails, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainTelefoneAssociations(pessoa.getTelefones, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainDocumentoAssociations(pessoa.getDocumentos, response, pessoa.getId(), null, null,
						null);


		updateCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, pessoa.getId(), null, null,
						null);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteCliente(Cliente pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, pessoa, response);
		updateCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, insertCount, null, null,
						null);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IClienteDAC#fetchClienteById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cliente> fetchClienteByRequest(ClienteInquiryRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	// ========================Fornecedor=======================
	@Override
	public InternalResultsResponse<Fornecedor> insertFornecedor(Fornecedor pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, pessoa, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainCnaeAssociations(pessoa.getCnaes, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainEmailAssociations(pessoa.getEmails, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainTelefoneAssociations(pessoa.getTelefones, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainDocumentoAssociations(pessoa.getDocumentos, response, insertCount, null, null,
						null);


		insertCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, insertCount, null, null,
						null);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was inserted then add the Fornecedor to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Fornecedor> updateFornecedor(Fornecedor pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, pessoa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainCnaeAssociations(pessoa.getCnaes, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainEmailAssociations(pessoa.getEmails, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainTelefoneAssociations(pessoa.getTelefones, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainDocumentoAssociations(pessoa.getDocumentos, response, pessoa.getId(), null, null,
						null);


		updateCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, pessoa.getId(), null, null,
						null);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteFornecedor(Fornecedor pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, pessoa, response);
		updateCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, insertCount, null, null,
						null);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IFornecedorDAC#fetchFornecedorById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(FornecedorInquiryRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getFornecedorInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	// ==============================Transportador

	@Override
	public InternalResultsResponse<Transportador> insertTransportador(Transportador pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, pessoa, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainCnaeAssociations(pessoa.getCnaes, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainEmailAssociations(pessoa.getEmails, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainTelefoneAssociations(pessoa.getTelefones, response, insertCount, null, null,
						null);

		insertCount +=
				EnderecoDACD.maintainDocumentoAssociations(pessoa.getDocumentos, response, insertCount, null, null,
						null);


		insertCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, insertCount, null, null,
						null);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was inserted then add the Transportador to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Transportador> updateTransportador(Transportador pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, pessoa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainCnaeAssociations(pessoa.getCnaes, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainEmailAssociations(pessoa.getEmails, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainTelefoneAssociations(pessoa.getTelefones, response, pessoa.getId(), null, null,
						null);

		updateCount +=
				EnderecoDACD.maintainDocumentoAssociations(pessoa.getDocumentos, response, pessoa.getId(), null, null,
						null);


		updateCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, pessoa.getId(), null, null,
						null);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteTransportador(Transportador pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, pessoa, response);
		updateCount +=
				EnderecoDACD.maintainHistoricoAssociations(pessoa.getEStatus, response, insertCount, null, null,
						null);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ITransportadorDAC#fetchTransportadorById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorByRequest(TransportadorInquiryRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getTransportadorInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}
}
