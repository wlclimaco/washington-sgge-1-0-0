package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.Date;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
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
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Entidade;
import com.qat.samples.sysmgmt.nf.model.NotaFiscal;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
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
	public static Integer maintainInsertBasePessoa(Pessoa empresa,Integer historicoId,Integer processId,TabelaEnum tabela,IEnderecoBAR enderecoBAR,IStatusBAR statusBAR,IHistoricoBAR historicoBAR,
			ICadastrosBAR cadastroBAR,ITelefoneBAR telefoneBAR,IEmailBAR emailBAR,IDocumentoBAR documentoBAR,INotesBAR noteBAR, InternalResultsResponse<?> response)
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
//		if (!ValidationUtil.isNull(empresa.getConhecimentoTransporte()))
//		{
//			count +=
//					ConhecimentoTransporteBARD.maintainConhecimentoTransporteAssociations(empresa.getConhecimentoTransporte(), response, empresa.getId(), null,
//							null,
//							tabela, cadastroBAR, statusBAR, historicoBAR, empresa.getId(),
//							empresa.getCreateUser(), processId, historicoId);
//		}
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
	public static Empresa empresaConfigInicial(Empresa empresa)
	{

		ConfigGeral configgeral = new ConfigGeral();
		Date a = new Date();
		configgeral.setFusoHorario(1001);
		configgeral.setCasasDecimais(1002);
		configgeral.setDiasCartaCobr(1003);
		configgeral.setInfPosicionarMouse(1004);
		configgeral.setCnpjCPFUnico(1005);
		configgeral.setImpCodPersonalizado(1006);
		configgeral.setLogListRelImp(1007);
		configgeral.setObsProdFinProd(1008);
		configgeral.setParentId(empresa.getId());
		configgeral.setEmprId(empresa.getId());
		configgeral.setModifyDateUTC(a.getTime());
		configgeral.setCreateDateUTC(a.getTime());
		configgeral.setCreateUser("system");
		configgeral.setModifyUser("system");
		configgeral.setProcessId(1);
		configgeral.setModelAction(PersistenceActionEnum.INSERT);

		ConfiguracaoNFe configuracaonfe = new ConfiguracaoNFe();

		configuracaonfe.setDestConsFinal(new DoisValores(new Integer(500)));
		configuracaonfe.setPreencherDataHora(1003);
		configuracaonfe.setIcmsPadrao(new Double(10.00));
		configuracaonfe.setIpiPadrao(new Double(10.00));
		configuracaonfe.setPisPadrao(new Double(10.00));
		configuracaonfe.setCofinsPadrao(new Double(10.00));
		configuracaonfe.setAmbienteEnvio(new DoisValores(new Integer(500)));
		configuracaonfe.setServMsmNota(new DoisValores(new Integer(500)));
		configuracaonfe.setSerieEnvio(new DoisValores(new Integer(500)));
		configuracaonfe.setAnexarXmlEmail(1011);
		configuracaonfe.setIdCSC("idCSC_12");
		configuracaonfe.setcSC("cSC_13");
		configuracaonfe.setInformacaoAdd("informacaoAdd_14");
		configuracaonfe.setCertificado("certificado_15");
		configuracaonfe.setSenha("senha_16");
		configuracaonfe.setSalvarSenha(1017);
		configuracaonfe.setCfopPadrao(new Cfop("501"));

		configuracaonfe.setParentId(empresa.getId());
		configuracaonfe.setEmprId(empresa.getId());
		configuracaonfe.setModifyDateUTC(a.getTime());
		configuracaonfe.setCreateDateUTC(a.getTime());
		configuracaonfe.setCreateUser("system");
		configuracaonfe.setModifyUser("system");
		configuracaonfe.setProcessId(1);
		configuracaonfe.setModelAction(PersistenceActionEnum.INSERT);

		ConfigFiscal configfiscal = new ConfigFiscal();

		configfiscal.setPrincAtividade(empresa.getCnaes() != null ? empresa.getCnaes().get(0).getIdCnae() : null);
		configfiscal.setRegime(empresa.getRegime());
		configfiscal.setAliqSimples(new Double(10.00));
		configfiscal.setParentId(empresa.getId());
		configfiscal.setEmprId(empresa.getId());
		configfiscal.setModifyDateUTC(a.getTime());
		configfiscal.setCreateDateUTC(a.getTime());
		configfiscal.setCreateUser("system");
		configfiscal.setModifyUser("system");
		configfiscal.setProcessId(1);
		configfiscal.setModelAction(PersistenceActionEnum.INSERT);


		ConfigVendas configvendas = new ConfigVendas();

		configvendas.setDescontoMaxVenda(new Double(10.00));
		configvendas.setObservacao("observacao_2");
		configvendas.setImprSegVia(1);
		configvendas.setImprAssinatura(1);
		configvendas.setImprResumoFinanc(0);
		configvendas.setAtuaPrecoClonar(1);
		configvendas.setImprColUnidade(0);
		configvendas.setBloquearvendProdSemEstoq(1);
		configvendas.setAddDespCalcImposto(0);
		configvendas.setRetSubstTribICMS(0);
		configvendas.setParentId(empresa.getId());
		configvendas.setEmprId(empresa.getId());
		configvendas.setModifyDateUTC(a.getTime());
		configvendas.setCreateDateUTC(a.getTime());
		configvendas.setCreateUser("system");
		configvendas.setModifyUser("system");
		configvendas.setProcessId(1);
		configfiscal.setModelAction(PersistenceActionEnum.INSERT);

		ConfigEntrada configentrada = new ConfigEntrada();
		configentrada.setValorTotalFixo(1);
		configentrada.setManterPrecoVendaProd(1);
		configentrada.setParentId(empresa.getId());
		configentrada.setEmprId(empresa.getId());
		configentrada.setModifyDateUTC(a.getTime());
		configentrada.setCreateDateUTC(a.getTime());
		configentrada.setCreateUser("system");
		configentrada.setModifyUser("system");
		configentrada.setProcessId(1);
		configentrada.setModelAction(PersistenceActionEnum.INSERT);

		ConfigCarne configcarne = new ConfigCarne();
		configcarne.setCarneBotelo(1);
		configcarne.setCarneNormal(1);
		configcarne.setParentId(empresa.getId());
		configcarne.setEmprId(empresa.getId());
		configcarne.setModifyDateUTC(a.getTime());
		configcarne.setCreateDateUTC(a.getTime());
		configcarne.setCreateUser("system");
		configcarne.setModifyUser("system");
		configcarne.setProcessId(1);
		configcarne.setModelAction(PersistenceActionEnum.INSERT);


		Configuracao configuracao = new Configuracao();
		configuracao.setConfGeral(configgeral);
		configuracao.setConfNFe(configuracaonfe);
		configuracao.setConfFiscal(configfiscal);
		configuracao.setConfVendas(configvendas);
		configuracao.setConfEntrada(configentrada);
		configuracao.setConfCarne(configcarne);
		configuracao.setParentId(empresa.getId());
		configuracao.setEmprId(empresa.getId());
		configuracao.setModifyDateUTC(a.getTime());
		configuracao.setCreateDateUTC(a.getTime());
		configuracao.setCreateUser("system");
		configuracao.setModifyUser("system");
		configuracao.setProcessId(1);
		configuracao.setModelAction(PersistenceActionEnum.INSERT);

		empresa.setConfiguracao(configuracao);

		return empresa;

	}


}
