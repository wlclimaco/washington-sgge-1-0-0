package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.bar.Vendas.IVendasBAR;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.entidade.model.Entidade;
import com.qat.samples.sysmgmt.nf.model.NotaFiscal;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.Telefone;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class BaseBARD extends SqlSessionDaoSupport
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
	public static Integer maintainInsertBase(Entidade empresa,Integer historicoId,Integer processId,TabelaEnum tabela,IEnderecoBAR enderecoBAR,IStatusBAR statusBAR,IHistoricoBAR historicoBAR,
			ICadastrosBAR cadastroBAR,IFiscalBAR fiscalBAR,ITelefoneBAR telefoneBAR,IEmailBAR emailBAR,IDocumentoBAR documentoBAR,INotesBAR noteBAR, InternalResultsResponse<?> response)
	{
		Integer count = 0;
		Boolean count1 = false;
		if (!ValidationUtil.isNullOrEmpty(empresa.getEnderecos()))
		{
			count +=
					EnderecoBARD.maintainEnderecoAssociations(empresa.getEnderecos(), response, empresa.getId(), null,
							null,
							tabela, enderecoBAR, statusBAR, historicoBAR, empresa.getId(),
							empresa.getCreateUser(), processId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getCnaes()))
		{
			count +=
					CnaeBARD.maintainCnaeAssociations(empresa.getCnaes(), response, empresa.getId(), null, null,
							tabela, fiscalBAR, statusBAR, historicoBAR, empresa.getId(),
							empresa.getCreateUser(), processId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getEmails()))
		{
			count +=
					EmailBARD.maintainEmailAssociations(empresa.getEmails(), response, empresa.getId(), null, null,
							tabela, emailBAR, statusBAR, historicoBAR, empresa.getId(),
							empresa.getCreateUser(), processId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getTelefones()))
		{
			count +=
					TelefoneBARD.maintainTelefoneAssociations(empresa.getTelefones(), response, empresa.getId(), null,
							null,
							tabela, telefoneBAR, statusBAR, historicoBAR, empresa.getId(),
							empresa.getCreateUser(), processId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getDocumentos()))
		{
			count +=
					DocumentosBARD.maintainDocumentoAssociations(empresa.getDocumentos(), response, empresa.getId(),
							null,
							null,
							tabela, documentoBAR, statusBAR, historicoBAR, empresa.getId(),
							empresa.getCreateUser(), processId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getNotes()))
		{
			count +=
					NotesBARD.maintainNoteAssociations(empresa.getNotes(), response, empresa.getId(), null,
							null,
							tabela, noteBAR, statusBAR, historicoBAR, empresa.getEmprId(),
							empresa.getCreateUser(), processId, historicoId);
		}



		return 0;

	}

	@SuppressWarnings("unchecked")
	public static Integer maintainInsertBaseNF(NotaFiscal empresa,Integer historicoId,Integer processId,TabelaEnum tabela,IEnderecoBAR enderecoBAR,IStatusBAR statusBAR,IHistoricoBAR historicoBAR,
			IVendasBAR cadastroBAR,IFinanceiroBAR financeiroBAR, InternalResultsResponse<?> response)
	{
		Integer count = 0;
		Boolean count1 = false;
		if (!ValidationUtil.isNull(empresa.getConhecimentoTransporte()))
		{
			count +=
					ConhecimentoTransporteBARD.maintainConhecimentoTransporteAssociations(empresa.getConhecimentoTransporte(), response, empresa.getId(), null,
							null,
							tabela, cadastroBAR, statusBAR, historicoBAR, empresa.getId(),
							empresa.getCreateUser(), processId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getNotaFiscalItens()))
		{
			count +=
					NotaFiscalItensBARD.maintainNotaFiscalItensAssociations(empresa.getNotaFiscalItens(), response, empresa.getId(), null, null,
							tabela, cadastroBAR, statusBAR, historicoBAR, empresa.getId(),
							empresa.getCreateUser(), processId, historicoId);
		}

//		if (!ValidationUtil.isNull(empresa.getContaspagarList()))
//		{
//			count +=
//					ContasBARD.maintainContasPagarAssociations(empresa.getContaspagarList(), response, empresa.getId(), null,
//							null,
//							tabela, financeiroBAR, statusBAR, historicoBAR, empresa.getId(),
//							empresa.getCreateUser(), processId, historicoId);
//		}


		return 0;

	}
	public static Boolean maintainDeleteBase(Entidade empresa,Integer historicoId,Integer processId,TabelaEnum tabela,IEnderecoBAR enderecoBAR,IStatusBAR statusBAR,IHistoricoBAR historicoBAR,
			ICadastrosBAR cadastroBAR,IFiscalBAR fiscalBAR,ITelefoneBAR telefoneBAR,IEmailBAR emailBAR,IDocumentoBAR documentoBAR,INotesBAR noteBAR, InternalResultsResponse<?> response)
	{
		Integer count = 0;
		Boolean count1 = false;
		if (!ValidationUtil.isNullOrEmpty(empresa.getEnderecos()))
		{
			for (Endereco endereco : empresa.getEnderecos())
			{
				count1 =  enderecoBAR.deleteEnderecoById(endereco).hasSystemError();
			}
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getCnaes()))
		{
			for (CnaeEmpresa cnae : empresa.getCnaes())
			{
				count1 =  fiscalBAR.deleteCnaeEmpresaById(cnae).hasSystemError();
			}
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getEmails()))
		{
			for (Email email : empresa.getEmails())
			{
				count1 =  emailBAR.deleteEmailById(email).hasSystemError();
			}
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getTelefones()))
		{
			for (Telefone telefone : empresa.getTelefones())
			{
				count1 =  telefoneBAR.deleteTelefoneById(telefone).hasSystemError();
			}
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getDocumentos()))
		{
			for (Documento documento : empresa.getDocumentos())
			{
				count1 =  documentoBAR.deleteDocumentoById(documento).hasSystemError();
			}
		}

		return count1;

	}


}
