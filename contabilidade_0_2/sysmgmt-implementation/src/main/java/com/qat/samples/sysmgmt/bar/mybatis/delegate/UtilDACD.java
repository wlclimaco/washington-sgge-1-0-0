package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class UtilDACD extends SqlSessionDaoSupport
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/**
	 * Fetch objects by request.
	 *
	 * @param sqlSession the sql session
	 * @param request the request
	 * @param countStatement the count statement
	 * @param fetchPagedStatement the fetch paged statement
	 * @param response the response
	 */
	@SuppressWarnings("unchecked")
	public static Integer insertPerson(Pessoa pessoa, Integer processId, Integer historicoId,
			InternalResultsResponse<?> response, IEnderecoBAR enderecoDac, IStatusBAR statusDac,
			IHistoricoBAR historicoDac)
	{
		// Integer insertCount = 0;
		// insertCount +=
		// EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, insertCount, null, null,
		// null, enderecoDac, getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
		// pessoa.getCreateUser(), processId, historicoId);
		//
		// insertCount +=
		// EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, insertCount, null, null,
		// null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
		// pessoa.getCreateUser(), processId, historicoId);
		//
		// insertCount +=
		// TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, insertCount, null, null,
		// null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
		// pessoa.getCreateUser(), processId, historicoId);
		//
		// insertCount +=
		// DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, insertCount, null,
		// null,
		// null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
		// pessoa.getCreateUser(), processId, historicoId);

		return 1;

	}
}
