package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class EmpresaBARD extends SqlSessionDaoSupport
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
	public static Integer maintainInsertEntidade(Empresa empresa,Integer historicoId,TabelaEnum tabelaEnum,
			InternalResultsResponse<?> response)
	{
//		Integer count = 0;
//		if (!ValidationUtil.isNullOrEmpty(empresa.getEnderecos()))
//		{
//			count +=
//					EnderecoBARD.maintainEnderecoAssociations(empresa.getEnderecos(), response, empresa.getId(), null,
//							null,
//							tabela, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
//							empresa.getCreateUser(), processId, historicoId, getCidadeDAC());
//		}
//		if (!ValidationUtil.isNullOrEmpty(empresa.getCnaes()))
//		{
//			count +=
//					CnaeBARD.maintainCnaeAssociations(empresa.getCnaes(), response, empresa.getId(), null, null,
//							tabela, getCnaeDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
//							empresa.getCreateUser(), processId, historicoId);
//		}
//		if (!ValidationUtil.isNullOrEmpty(empresa.getEmails()))
//		{
//			count +=
//					EmailBARD.maintainEmailAssociations(empresa.getEmails(), response, empresa.getId(), null, null,
//							tabela, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
//							empresa.getCreateUser(), processId, historicoId);
//		}
//		if (!ValidationUtil.isNullOrEmpty(empresa.getTelefones()))
//		{
//			count +=
//					TelefoneBARD.maintainTelefoneAssociations(empresa.getTelefones(), response, empresa.getId(), null,
//							null,
//							tabela, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
//							empresa.getCreateUser(), processId, historicoId);
//		}
//		if (!ValidationUtil.isNullOrEmpty(empresa.getDocumentos()))
//		{
//			count +=
//					DocumentosBARD.maintainDocumentoAssociations(empresa.getDocumentos(), response, empresa.getId(),
//							null,
//							null,
//							tabela, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
//							empresa.getCreateUser(), processId, historicoId);
//		}
//		if (!ValidationUtil.isNullOrEmpty(empresa.getNotes()))
//		{
//			count +=
//					NotesBARD.maintainNoteAssociations(empresa.getNotes(), response, empresa.getId(), null,
//							null,
//							tabela, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getEmprId(),
//							empresa.getCreateUser(), processId, historicoId);
//		}
//
//		if (count > 0)
//		{
//			Status status = new Status();
//			status.setStatus(CdStatusTypeEnum.ANALISANDO);
//			List<Status> statusList = new ArrayList<Status>();
//			statusList.add(status);
//			count +=
//					StatusBARD.maintainStatusAssociations(statusList, response, empresa.getId(), null, AcaoEnum.INSERT,
//							empresa.getCreateUser(), empresa.getId(), tabela, getStatusDAC(),
//							getHistoricoDAC(), processId, historicoId);
//
//		}

		return 0;

	}
}
