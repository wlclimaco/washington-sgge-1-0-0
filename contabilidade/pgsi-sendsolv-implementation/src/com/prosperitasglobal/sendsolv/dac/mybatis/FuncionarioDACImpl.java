package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC;
import com.prosperitasglobal.sendsolv.dac.IDocumentoDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IEventosDAC;
import com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.ISalariosDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.BeneficiosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.DocumentosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EmailDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EnderecoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EventosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.SalarioDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.StatusDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.TelefoneDACD;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class FuncionarioDACImpl.
 */
public class FuncionarioDACImpl extends SqlSessionDaoSupport implements IFuncionarioDAC
{

	/** The Constant FUNCIONARIO_NAMESPACE. */
	private static final String FUNCIONARIO_NAMESPACE = "FuncionarioMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant FUNCIONARIO_STMT_FETCH_COUNT. */
	private static final String FUNCIONARIO_STMT_FETCH_COUNT = FUNCIONARIO_NAMESPACE + "fetchFuncionarioRowCount";

	/** The Constant FUNCIONARIO_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String FUNCIONARIO_STMT_FETCH_ALL_BY_REQUEST = FUNCIONARIO_NAMESPACE
			+ "fetchAllFuncionariosByRequest";

	/** The Constant FUNCIONARIO_STMT_FETCH_BY_ID. */
	private static final String FUNCIONARIO_STMT_FETCH_BY_ID = FUNCIONARIO_NAMESPACE + "fetchFuncionarioById";

	/** The Constant FUNCIONARIO_STMT_INSERT. */
	private static final String FUNCIONARIO_STMT_INSERT = FUNCIONARIO_NAMESPACE + "insertFuncionario";

	/** The Constant FUNCIONARIO_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String FUNCIONARIO_STMT_ASSOC_ORG_TO_CONTACT = FUNCIONARIO_NAMESPACE
			+ "associateFuncionarioWithContact";

	/** The Constant FUNCIONARIO_STMT_UPDATE. */
	private static final String FUNCIONARIO_STMT_UPDATE = FUNCIONARIO_NAMESPACE + "updateFuncionario";

	/** The Constant FUNCIONARIO_STMT_DELETE. */
	private static final String FUNCIONARIO_STMT_DELETE = FUNCIONARIO_NAMESPACE + "deleteFuncionarioById";

	/** The Constant FUNCIONARIO_DOCUMENT_STMT_UPDATE. */
	private static final String FUNCIONARIO_DOCUMENT_STMT_UPDATE = FUNCIONARIO_NAMESPACE
			+ "updateFuncionarioDocument";

	/** The Constant FUNCIONARIO_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String FUNCIONARIO_STMT_ASSOC_ORG_TO_DOCUMENT = FUNCIONARIO_NAMESPACE
			+ "associateFuncionarioWithDocument";

	/** The Constant FUNCIONARIO_STMT_DELETE_DOCUMENT. */
	private static final String FUNCIONARIO_STMT_DELETE_DOCUMENT = FUNCIONARIO_NAMESPACE
			+ "deleteFuncionarioDocument";

	/** The Constant STMT_VERSION. */
	private static final String FUNCIONARIO_STMT_VERSION = FUNCIONARIO_NAMESPACE + "fetchVersionNumber";

	/** The Constant FUNCIONARIO_STMT_UPDATE_FUNCIONARIO_STATUS. */
	private static final String FUNCIONARIO_STMT_UPDATE_FUNCIONARIO_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FuncionarioDACImpl.class);

	/** The valid sort fields for an funcionario inquiry. Will be injected by Spring. */
	private Map<String, String> funcionarioInquiryValidSortFields;

	IEnderecoDAC enderecoDAC;
	ITelefoneDAC telefoneDAC;
	IEmailDAC emailDAC;
	ISalariosDAC salarioDAC;
	IDocumentoDAC documentoDAC;
	IHistoricoDAC historicoDAC;
	IStatusDAC statusDAC;
	IBeneficiosDAC beneficiosDAC;
	IEventosDAC eventosDAC;

	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	public ITelefoneDAC getTelefoneDAC()
	{
		return telefoneDAC;
	}

	public void setTelefoneDAC(ITelefoneDAC telefoneDAC)
	{
		this.telefoneDAC = telefoneDAC;
	}

	public IEmailDAC getEmailDAC()
	{
		return emailDAC;
	}

	public void setEmailDAC(IEmailDAC emailDAC)
	{
		this.emailDAC = emailDAC;
	}

	public ISalariosDAC getSalarioDAC()
	{
		return salarioDAC;
	}

	public void setSalarioDAC(ISalariosDAC salarioDAC)
	{
		this.salarioDAC = salarioDAC;
	}

	public IDocumentoDAC getDocumentoDAC()
	{
		return documentoDAC;
	}

	public void setDocumentoDAC(IDocumentoDAC documentoDAC)
	{
		this.documentoDAC = documentoDAC;
	}

	public IHistoricoDAC getHistoricoDAC()
	{
		return historicoDAC;
	}

	public void setHistoricoDAC(IHistoricoDAC historicoDAC)
	{
		this.historicoDAC = historicoDAC;
	}

	public IStatusDAC getStatusDAC()
	{
		return statusDAC;
	}

	public void setStatusDAC(IStatusDAC statusDAC)
	{
		this.statusDAC = statusDAC;
	}

	public IBeneficiosDAC getBeneficiosDAC()
	{
		return beneficiosDAC;
	}

	public void setBeneficiosDAC(IBeneficiosDAC beneficiosDAC)
	{
		this.beneficiosDAC = beneficiosDAC;
	}

	public IEventosDAC getEventosDAC()
	{
		return eventosDAC;
	}

	public void setEventosDAC(IEventosDAC eventosDAC)
	{
		this.eventosDAC = eventosDAC;
	}

	/**
	 * Get the valid sort fields for the funcionario inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the funcionario inquiry.
	 */
	public Map<String, String> getFuncionarioInquiryValidSortFields()
	{
		return funcionarioInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the funcionario inquiry. Attribute injected by Spring.
	 *
	 * @param funcionarioInquiryValidSortFields The valid sort fields for the funcionario inquiry to set.
	 */
	public void setFuncionarioInquiryValidSortFields(Map<String, String> funcionarioInquiryValidSortFields)
	{
		this.funcionarioInquiryValidSortFields = funcionarioInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#insertFuncionario(com.prosperitasglobal.sendsolv.model
	 * .Funcionario)
	 */
	@Override
	public InternalResultsResponse<Funcionario> insertFuncionario(Funcionario funcionario)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), FUNCIONARIO_STMT_INSERT, funcionario, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations

		insertCount +=
				SalarioDACD.maintainSalarioAssociations(funcionario.getSalarios(), response, insertCount, null, null,
						null, getSalarioDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(funcionario.getEnderecos(), response, insertCount, null,
						null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		insertCount +=
				EmailDACD.maintainEmailAssociations(funcionario.getEmails(), response, insertCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(funcionario.getTelefones(), response, insertCount, null,
						null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(funcionario.getDocumentos(), response, insertCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());
		insertCount +=
				BeneficiosDACD.maintainBeneficiosAssociations(funcionario.getBeneficios(), response, insertCount, null,
						null,
						null, getBeneficiosDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		insertCount +=
				EventosDACD.maintainEventosAssociations(funcionario.getEventosList(), response, insertCount, null,
						null,
						null, getEventosDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, funcionario.getId(), null,
							AcaoEnum.INSERT, funcionario.getCreateUser(), funcionario.getEmprId(),
							TabelaEnum.FUNCIONARIO,
							getStatusDAC(), getHistoricoDAC());

		}

		// Finally, if something was inserted then add the Funcionario to the result.
		if (insertCount > 0)
		{
			response.addResult(funcionario);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#updateFuncionario(com.prosperitasglobal.sendsolv.model
	 * .Funcionario)
	 */
	@Override
	public InternalResultsResponse<Funcionario> updateFuncionario(Funcionario funcionario)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(funcionario.getModelAction())
				&& (funcionario.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), FUNCIONARIO_STMT_UPDATE, funcionario, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount +=
				SalarioDACD.maintainSalarioAssociations(funcionario.getSalarios(), response, updateCount, null, null,
						null, getSalarioDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(funcionario.getEnderecos(), response, updateCount, null,
						null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		updateCount +=
				EmailDACD.maintainEmailAssociations(funcionario.getEmails(), response, updateCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		updateCount +=
				TelefoneDACD.maintainTelefoneAssociations(funcionario.getTelefones(), response, updateCount, null,
						null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		updateCount +=
				DocumentosDACD.maintainDocumentoAssociations(funcionario.getDocumentos(), response, updateCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());
		updateCount +=
				BeneficiosDACD.maintainBeneficiosAssociations(funcionario.getBeneficios(), response, updateCount, null,
						null,
						null, getBeneficiosDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		updateCount +=
				EventosDACD.maintainEventosAssociations(funcionario.getEventosList(), response, updateCount, null,
						null,
						null, getEventosDAC(), getStatusDAC(), getHistoricoDAC(), funcionario.getEmprId(),
						funcionario.getCreateUser());

		if (updateCount > 0)
		{
			updateCount =
					StatusDACD.maintainStatusAssociations(funcionario.getStatusList(), response, funcionario.getId(),
							null, AcaoEnum.UPDATE, funcionario.getCreateUser(), funcionario.getEmprId(),
							TabelaEnum.FUNCIONARIO, getStatusDAC(), getHistoricoDAC());

		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(funcionario);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#deleteFuncionario(com.prosperitasglobal.sendsolv.model
	 * .Funcionario)
	 */
	@Override
	public InternalResponse deleteFuncionario(Funcionario funcionario)
	{
		InternalResponse response = new InternalResponse();
		// QATMyBatisDacHelper.doRemove(getSqlSession(), FUNCIONARIO_STMT_DELETE, funcionario, response);

		Status status = new Status();
		status.setStatus(StatusEnum.INACTIVE);
		List<Status> statusList = new ArrayList<Status>();

		StatusDACD.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, funcionario.getId(),
				null, AcaoEnum.DELETE,
				funcionario.getCreateUser(), funcionario.getEmprId(), TabelaEnum.FUNCIONARIO, getStatusDAC(),
				getHistoricoDAC());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#fetchFuncionarioById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FUNCIONARIO_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#fetchFuncionarioByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(FuncionarioInquiryRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getFuncionarioInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, FUNCIONARIO_STMT_FETCH_COUNT,
				FUNCIONARIO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}
}
