package com.qat.samples.sysmgmt.bar.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.agencia.model.Agencia;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.BancoPessoa;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.CfopParentId;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.clinica.model.Exame;
import com.qat.samples.sysmgmt.clinica.model.ExamePessoa;
import com.qat.samples.sysmgmt.clinica.model.PlanoSaudePessoa;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.condpag.model.FormaPgPessoa;
import com.qat.samples.sysmgmt.condpag.model.TipoPag;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.ContatoItens;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.cotacao.model.Cotacao;
import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.Profissao;
import com.qat.samples.sysmgmt.dp.model.Salario;
import com.qat.samples.sysmgmt.entidade.model.Ajuda;
import com.qat.samples.sysmgmt.entidade.model.Boleto;
import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;
import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.EntidadeTypeEnum;
import com.qat.samples.sysmgmt.entidade.model.Field;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Menu;
import com.qat.samples.sysmgmt.entidade.model.Pagina;
import com.qat.samples.sysmgmt.entidade.model.Role;
import com.qat.samples.sysmgmt.entidade.model.Transaction;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.Validacao;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.TipoBaixa;
import com.qat.samples.sysmgmt.financeiro.model.Titulo;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.historico.model.HistoricoMovimento;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nfe.model.NFInfoModelo1Por1AReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFInfoProdutorRuralReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFInfoReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfo;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoAvulsa;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCana;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCanaDeducao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCanaFornecimentoDiario;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCartao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCobranca;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCompra;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoDestinatario;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoDuplicata;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoEmitente;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoExportacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoFatura;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoICMSTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoISSQNTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoIdentificacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoInformacoesAdicionais;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoLocal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoObservacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoPagamento;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoProcessoReferenciado;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoReboque;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoRetencaoICMSTransporte;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoRetencoesTributos;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoSuplementar;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTransportador;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTransporte;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoVeiculo;
import com.qat.samples.sysmgmt.nfe.model.NFPessoaAutorizadaDownloadNFe;
import com.qat.samples.sysmgmt.nfeItens.model.NFImpostoDevolvido;
import com.qat.samples.sysmgmt.nfeItens.model.NFInformacaoImpostoDevolvido;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItem;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemDetalheExportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemExportacaoIndireta;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImposto;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSAliquota;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSNaoTributavel;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSOutrasOperacoes;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSQuantidade;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS00;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS10;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS20;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS30;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS40;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS51;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS60;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS70;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS90;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSPartilhado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN101;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN102;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN201;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN202;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN500;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN900;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSUFDestino;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPI;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPINaoTributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPITributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoISSQN;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoImportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPIS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISAliquota;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISNaoTributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISOutrasOperacoes;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISQuantidade;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProduto;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoArmamento;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoCombustivel;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoCombustivelCIDE;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoDeclaracaoImportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoMedicamento;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoVeiculo;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoStatus;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.pessoa.model.PessoaTipo;
import com.qat.samples.sysmgmt.pessoa.model.PessoaTypeEnum;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.produto.model.Cofins;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.CustoItens;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.EstoqueTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Icms;
import com.qat.samples.sysmgmt.produto.model.Ipi;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.MarcaProduto;
import com.qat.samples.sysmgmt.produto.model.Pis;
import com.qat.samples.sysmgmt.produto.model.PlanoByServico;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.PorcaoItens;
import com.qat.samples.sysmgmt.produto.model.Preco;
import com.qat.samples.sysmgmt.produto.model.PrecoTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoEmpresa;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.site.model.PlanoByEmpresa;
import com.qat.samples.sysmgmt.site.model.ServicoAndPlano;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.DoisValorType;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.Telefone;

public class Objects {

	public static final String REST_SERVICE_URI = "http://localhost:8080/qat-sysmgmt-controller-rest/";

	public static final Integer EMPID = 10;

	public static Advogado insertAdvogado(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Advogado advogado = new Advogado();
		Date a = new Date();
		advogado.setId(id);
		advogado.setNome("nome_1 - " + action.toString());
		advogado.setNomePai("nomePai_2 - " + action.toString());
		advogado.setNomeMae("nomeMae_3 - " + action.toString());
		advogado.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		advogado.setEstadoCivil(1005);
		advogado.setDatanasc(a.getTime());
		advogado.setFoto("foto_8 - " + action.toString());
		advogado.setPessoaTipo(new ArrayList<PessoaTipo>());
		advogado.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		advogado.setSexo(1010);
		advogado.setEnderecos(new ArrayList<Endereco>());
		advogado.getEnderecos().add(insertEndereco(id, TabelaEnum.ADVOCACIA, action));
		advogado.setDocumentos(new ArrayList<Documento>());
		advogado.getDocumentos().add(insertDocumento(id, TabelaEnum.ADVOCACIA, action));
		advogado.setEmails(new ArrayList<Email>());
		advogado.getEmails().add(insertEmail(id, TabelaEnum.ADVOCACIA, action));
		advogado.setTelefones(new ArrayList<Telefone>());
		advogado.getTelefones().add(insertTelefone(id, TabelaEnum.ADVOCACIA, action));
		advogado.setBancos(new ArrayList<BancoPessoa>());
		advogado.getBancos().add(insertBancoPessoa(id, TabelaEnum.ADVOCACIA, action));
		advogado.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		advogado.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.ADVOCACIA, action));
		advogado.setCondPagList(new ArrayList<CondPagPessoa>());
		advogado.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.ADVOCACIA, action));
		advogado.setContatoList(new ArrayList<Contato>());
		advogado.getContatoList().add(insertContato(id, TabelaEnum.ADVOCACIA, action));
		advogado.setTabelaEnum(tabela);
		advogado.setParentId(id);
		advogado.setEmprId(EMPID);
		advogado.setModifyDateUTC(a.getTime());
		advogado.setCreateDateUTC(a.getTime());
		advogado.setCreateUser("system");
		advogado.setModifyUser("system");
		advogado.setProcessId(1);
		advogado.setModelAction(action);

		return advogado;
	}



	public static Cliente insertCliente(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Cliente cliente = new Cliente();
		Date a = new Date();
		cliente.setId(id);
		cliente.setNome("nome_1 - " + action.toString());
		cliente.setNomePai("nomePai_2 - " + action.toString());
		cliente.setNomeMae("nomeMae_3 - " + action.toString());
		cliente.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		cliente.setEstadoCivil(1005);
		cliente.setDatanasc(a.getTime());
		cliente.setFoto("foto_8 - " + action.toString());
		cliente.setPessoaTipo(new ArrayList<PessoaTipo>());
		cliente.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.CLIENTE,id,action));
		cliente.setSexo(1010);
		cliente.setEnderecos(new ArrayList<Endereco>());
		cliente.getEnderecos().add(insertEndereco(id, TabelaEnum.CLIENTE, action));
		cliente.setDocumentos(new ArrayList<Documento>());
		cliente.getDocumentos().add(insertDocumento(id, TabelaEnum.CLIENTE, action));
		cliente.setEmails(new ArrayList<Email>());
		cliente.getEmails().add(insertEmail(id, TabelaEnum.CLIENTE, action));
		cliente.setTelefones(new ArrayList<Telefone>());
		cliente.getTelefones().add(insertTelefone(id, TabelaEnum.CLIENTE, action));
		cliente.setBancos(new ArrayList<BancoPessoa>());
		cliente.getBancos().add(insertBancoPessoa(id, TabelaEnum.CLIENTE, action));
		cliente.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		cliente.getFormaPagamentoList().add(insertFormaPgPessoa(null, TabelaEnum.CLIENTE, action));
		cliente.setCondPagList(new ArrayList<CondPagPessoa>());
		cliente.getCondPagList().add(insertCondPagPessoa(null, TabelaEnum.CLIENTE, action));
		cliente.setContatoList(new ArrayList<Contato>());
		cliente.getContatoList().add(insertContato(null, TabelaEnum.CLIENTE, action));
		cliente.setTabelaEnum(tabela);
		cliente.setParentId(id);
		cliente.setEmprId(EMPID);
		cliente.setModifyDateUTC(a.getTime());
		cliente.setCreateDateUTC(a.getTime());
		cliente.setCreateUser("system");
		cliente.setModifyUser("system");
		cliente.setProcessId(1);
		cliente.setModelAction(action);

		return cliente;
	}

	public static Fornecedor insertFornecedor(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Fornecedor fornecedor = new Fornecedor();
		Date a = new Date();
		fornecedor.setId(id);
		fornecedor.setNome("nome_1 - " + action.toString());
		fornecedor.setNomePai("nomePai_2 - " + action.toString());
		fornecedor.setNomeMae("nomeMae_3 - " + action.toString());
		fornecedor.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		fornecedor.setEstadoCivil(1005);
		fornecedor.setDatanasc(a.getTime());
		fornecedor.setFoto("foto_8 - " + action.toString());
		fornecedor.setPessoaTipo(new ArrayList<PessoaTipo>());
		fornecedor.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		fornecedor.setSexo(1010);
		fornecedor.setEnderecos(new ArrayList<Endereco>());
		fornecedor.getEnderecos().add(insertEndereco(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setDocumentos(new ArrayList<Documento>());
		fornecedor.getDocumentos().add(insertDocumento(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setEmails(new ArrayList<Email>());
		fornecedor.getEmails().add(insertEmail(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setTelefones(new ArrayList<Telefone>());
		fornecedor.getTelefones().add(insertTelefone(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setBancos(new ArrayList<BancoPessoa>());
		fornecedor.getBancos().add(insertBancoPessoa(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		fornecedor.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setCondPagList(new ArrayList<CondPagPessoa>());
		fornecedor.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setContatoList(new ArrayList<Contato>());
		fornecedor.getContatoList().add(insertContato(id, TabelaEnum.FORNECEDOR, action));
		fornecedor.setTabelaEnum(tabela);
		fornecedor.setParentId(id);
		fornecedor.setEmprId(EMPID);
		fornecedor.setModifyDateUTC(a.getTime());
		fornecedor.setCreateDateUTC(a.getTime());
		fornecedor.setCreateUser("system");
		fornecedor.setModifyUser("system");
		fornecedor.setProcessId(1);
		fornecedor.setModelAction(action);

		return fornecedor;
	}

	public static Transportador insertTransportador(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Transportador transportador = new Transportador();
		Date a = new Date();
		transportador.setId(id);
		transportador.setNome("nome_1 - " + action.toString());
		transportador.setNomePai("nomePai_2 - " + action.toString());
		transportador.setNomeMae("nomeMae_3 - " + action.toString());
		transportador.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		transportador.setEstadoCivil(1005);
		transportador.setDatanasc(a.getTime());
		transportador.setFoto("foto_8 - " + action.toString());
		transportador.setPessoaTipo(new ArrayList<PessoaTipo>());
		transportador.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		transportador.setSexo(1010);
		transportador.setEnderecos(new ArrayList<Endereco>());
		transportador.getEnderecos().add(insertEndereco(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setDocumentos(new ArrayList<Documento>());
		transportador.getDocumentos().add(insertDocumento(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setEmails(new ArrayList<Email>());
		transportador.getEmails().add(insertEmail(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setTelefones(new ArrayList<Telefone>());
		transportador.getTelefones().add(insertTelefone(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setBancos(new ArrayList<BancoPessoa>());
		transportador.getBancos().add(insertBancoPessoa(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		transportador.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setCondPagList(new ArrayList<CondPagPessoa>());
		transportador.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setContatoList(new ArrayList<Contato>());
		transportador.getContatoList().add(insertContato(id, TabelaEnum.TRANSPORTADOR, action));
		transportador.setTabelaEnum(tabela);
		transportador.setParentId(id);
		transportador.setEmprId(EMPID);
		transportador.setModifyDateUTC(a.getTime());
		transportador.setCreateDateUTC(a.getTime());
		transportador.setCreateUser("system");
		transportador.setModifyUser("system");
		transportador.setProcessId(1);
		transportador.setModelAction(action);

		return transportador;
	}

	public static Medico insertMedico(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Medico medico = new Medico();
		Date a = new Date();
		medico.setId(id);
		medico.setNome("nome_1 - " + action.toString());
		medico.setNomePai("nomePai_2 - " + action.toString());
		medico.setNomeMae("nomeMae_3 - " + action.toString());
		medico.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		medico.setEstadoCivil(1005);
		medico.setDatanasc(a.getTime());
		medico.setFoto("foto_8 - " + action.toString());
		medico.setPessoaTipo(new ArrayList<PessoaTipo>());
		medico.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		medico.setSexo(1010);
		medico.setEnderecos(new ArrayList<Endereco>());
		medico.getEnderecos().add(insertEndereco(id, TabelaEnum.MEDICO, action));
		medico.setDocumentos(new ArrayList<Documento>());
		medico.getDocumentos().add(insertDocumento(id, TabelaEnum.MEDICO, action));
		medico.setEmails(new ArrayList<Email>());
		medico.getEmails().add(insertEmail(id, TabelaEnum.MEDICO, action));
		medico.setTelefones(new ArrayList<Telefone>());
		medico.getTelefones().add(insertTelefone(id, TabelaEnum.MEDICO, action));
		medico.setBancos(new ArrayList<BancoPessoa>());
		medico.getBancos().add(insertBancoPessoa(id, TabelaEnum.MEDICO, action));
		medico.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		medico.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.MEDICO, action));
		medico.setCondPagList(new ArrayList<CondPagPessoa>());
		medico.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.MEDICO, action));
		medico.setContatoList(new ArrayList<Contato>());
		medico.getContatoList().add(insertContato(id, TabelaEnum.MEDICO, action));
		medico.setTabelaEnum(tabela);
		medico.setParentId(id);
		medico.setEmprId(EMPID);
		medico.setModifyDateUTC(a.getTime());
		medico.setCreateDateUTC(a.getTime());
		medico.setCreateUser("system");
		medico.setModifyUser("system");
		medico.setProcessId(1);
		medico.setModelAction(action);

		return medico;
	}

	public static Paciente insertPaciente(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Paciente paciente = new Paciente();
		Date a = new Date();
		paciente.setId(id);
		paciente.setNome("nome_1 - " + action.toString());
		paciente.setNomePai("nomePai_2 - " + action.toString());
		paciente.setNomeMae("nomeMae_3 - " + action.toString());
		paciente.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		paciente.setEstadoCivil(1005);
		paciente.setDatanasc(a.getTime());
		paciente.setFoto("foto_8 - " + action.toString());
		paciente.setPessoaTipo(new ArrayList<PessoaTipo>());
		paciente.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		paciente.setSexo(1010);
		paciente.setEnderecos(new ArrayList<Endereco>());
		paciente.getEnderecos().add(insertEndereco(id, TabelaEnum.PACIENTE, action));
		paciente.setDocumentos(new ArrayList<Documento>());
		paciente.getDocumentos().add(insertDocumento(id, TabelaEnum.PACIENTE, action));
		paciente.setEmails(new ArrayList<Email>());
		paciente.getEmails().add(insertEmail(id, TabelaEnum.PACIENTE, action));
		paciente.setTelefones(new ArrayList<Telefone>());
		paciente.getTelefones().add(insertTelefone(id, TabelaEnum.PACIENTE, action));
		paciente.setBancos(new ArrayList<BancoPessoa>());
		paciente.getBancos().add(insertBancoPessoa(id, TabelaEnum.PACIENTE, action));
		paciente.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		paciente.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.PACIENTE, action));
		paciente.setCondPagList(new ArrayList<CondPagPessoa>());
		paciente.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.PACIENTE, action));
		paciente.setContatoList(new ArrayList<Contato>());
		paciente.getContatoList().add(insertContato(id, TabelaEnum.PACIENTE, action));
		paciente.setTabelaEnum(tabela);
		paciente.setParentId(id);
		paciente.setEmprId(EMPID);
		paciente.setModifyDateUTC(a.getTime());
		paciente.setCreateDateUTC(a.getTime());
		paciente.setCreateUser("system");
		paciente.setModifyUser("system");
		paciente.setProcessId(1);
		paciente.setModelAction(action);

		return paciente;
	}

	public static Sindico insertSindico(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Sindico sindico = new Sindico();
		Date a = new Date();
		sindico.setId(id);
		sindico.setNome("nome_1 - " + action.toString());
		sindico.setNomePai("nomePai_2 - " + action.toString());
		sindico.setNomeMae("nomeMae_3 - " + action.toString());
		sindico.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		sindico.setEstadoCivil(1005);
		sindico.setDatanasc(a.getTime());
		sindico.setFoto("foto_8 - " + action.toString());
		sindico.setPessoaTipo(new ArrayList<PessoaTipo>());
		sindico.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		sindico.setSexo(1010);
		sindico.setEnderecos(new ArrayList<Endereco>());
		sindico.getEnderecos().add(insertEndereco(id, TabelaEnum.SINDICO, action));
		sindico.setDocumentos(new ArrayList<Documento>());
		sindico.getDocumentos().add(insertDocumento(id, TabelaEnum.SINDICO, action));
		sindico.setEmails(new ArrayList<Email>());
		sindico.getEmails().add(insertEmail(id, TabelaEnum.SINDICO, action));
		sindico.setTelefones(new ArrayList<Telefone>());
		sindico.getTelefones().add(insertTelefone(id, TabelaEnum.SINDICO, action));
		sindico.setBancos(new ArrayList<BancoPessoa>());
		sindico.getBancos().add(insertBancoPessoa(id, TabelaEnum.SINDICO, action));
		sindico.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		sindico.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.SINDICO, action));
		sindico.setCondPagList(new ArrayList<CondPagPessoa>());
		sindico.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.SINDICO, action));
		sindico.setContatoList(new ArrayList<Contato>());
		sindico.getContatoList().add(insertContato(id, TabelaEnum.SINDICO, action));
		sindico.setTabelaEnum(tabela);
		sindico.setParentId(id);
		sindico.setEmprId(EMPID);
		sindico.setModifyDateUTC(a.getTime());
		sindico.setCreateDateUTC(a.getTime());
		sindico.setCreateUser("system");
		sindico.setModifyUser("system");
		sindico.setProcessId(1);
		sindico.setModelAction(action);

		return sindico;
	}

	public static Inquilino insertInquilino(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Inquilino inquilino = new Inquilino();
		Date a = new Date();
		inquilino.setId(id);
		inquilino.setNome("nome_1 - " + action.toString());
		inquilino.setNomePai("nomePai_2 - " + action.toString());
		inquilino.setNomeMae("nomeMae_3 - " + action.toString());
		inquilino.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		inquilino.setEstadoCivil(1005);
		inquilino.setDatanasc(a.getTime());
		inquilino.setFoto("foto_8 - " + action.toString());
		inquilino.setPessoaTipo(new ArrayList<PessoaTipo>());
		inquilino.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		inquilino.setSexo(1010);
		inquilino.setEnderecos(new ArrayList<Endereco>());
		inquilino.getEnderecos().add(insertEndereco(id, TabelaEnum.INQUILINO, action));
		inquilino.setDocumentos(new ArrayList<Documento>());
		inquilino.getDocumentos().add(insertDocumento(id, TabelaEnum.INQUILINO, action));
		inquilino.setEmails(new ArrayList<Email>());
		inquilino.getEmails().add(insertEmail(id, TabelaEnum.INQUILINO, action));
		inquilino.setTelefones(new ArrayList<Telefone>());
		inquilino.getTelefones().add(insertTelefone(id, TabelaEnum.INQUILINO, action));
		inquilino.setBancos(new ArrayList<BancoPessoa>());
		inquilino.getBancos().add(insertBancoPessoa(id, TabelaEnum.INQUILINO, action));
		inquilino.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		inquilino.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.INQUILINO, action));
		inquilino.setCondPagList(new ArrayList<CondPagPessoa>());
		inquilino.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.INQUILINO, action));
		inquilino.setContatoList(new ArrayList<Contato>());
		inquilino.getContatoList().add(insertContato(id, TabelaEnum.INQUILINO, action));
		inquilino.setTabelaEnum(tabela);
		inquilino.setParentId(id);
		inquilino.setEmprId(EMPID);
		inquilino.setModifyDateUTC(a.getTime());
		inquilino.setCreateDateUTC(a.getTime());
		inquilino.setCreateUser("system");
		inquilino.setModifyUser("system");
		inquilino.setProcessId(1);
		inquilino.setModelAction(action);

		return inquilino;
	}

	public static Funcionario insertFuncionario(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Funcionario funcionario = new Funcionario();
		Date a = new Date();
		funcionario.setId(id);
		funcionario.setNome("nome_1 - " + action.toString());
		funcionario.setNomePai("nomePai_2 - " + action.toString());
		funcionario.setNomeMae("nomeMae_3 - " + action.toString());
		funcionario.setNomeConjugue("nomeConjugue_4 - " + action.toString());
		funcionario.setEstadoCivil(1005);

		funcionario.setDatanasc(a.getTime());
		funcionario.setFoto("foto_8 - " + action.toString());
		funcionario.setPessoaTipo(new ArrayList<PessoaTipo>());
		funcionario.getPessoaTipo().add(new PessoaTipo(PessoaTypeEnum.ADVOGADO,id,action));
		funcionario.setSexo(1010);
		funcionario.setEnderecos(new ArrayList<Endereco>());
		funcionario.getEnderecos().add(insertEndereco(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setDocumentos(new ArrayList<Documento>());
		funcionario.getDocumentos().add(insertDocumento(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setEmails(new ArrayList<Email>());
		funcionario.getEmails().add(insertEmail(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setTelefones(new ArrayList<Telefone>());
		funcionario.getTelefones().add(insertTelefone(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setBancos(new ArrayList<BancoPessoa>());
		funcionario.getBancos().add(insertBancoPessoa(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
		funcionario.getFormaPagamentoList().add(insertFormaPgPessoa(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setCondPagList(new ArrayList<CondPagPessoa>());
		funcionario.getCondPagList().add(insertCondPagPessoa(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setContatoList(new ArrayList<Contato>());
		funcionario.getContatoList().add(insertContato(id, TabelaEnum.FUNCIONARIO, action));
		funcionario.setTabelaEnum(tabela);
		funcionario.setParentId(id);
		funcionario.setEmprId(EMPID);
		funcionario.setModifyDateUTC(a.getTime());
		funcionario.setCreateDateUTC(a.getTime());
		funcionario.setCreateUser("system");
		funcionario.setModifyUser("system");
		funcionario.setProcessId(1);
		funcionario.setModelAction(action);

		return funcionario;
	}


	public static Audiencia insertAudiencia(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Audiencia audiencia = new Audiencia();
		Date a = new Date();
		audiencia.setId(id);
		audiencia.setDataAudiencia(1001);
		audiencia.setDescricao("descricao_3 - " + action.toString());
		audiencia.setNotes(new ArrayList<Note>());
		audiencia.getNotes().add(insertNotes(id, TabelaEnum.AUDIENCIA, action));
		audiencia.setTabelaEnum(tabela);
		audiencia.setParentId(id);
		audiencia.setEmprId(EMPID);
		audiencia.setModifyDateUTC(a.getTime());
		audiencia.setCreateDateUTC(a.getTime());
		audiencia.setCreateUser("system");
		audiencia.setModifyUser("system");
		audiencia.setProcessId(1);
		audiencia.setModelAction(action);

		return audiencia;
	}

	public static Processo insertProcesso(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Processo processo = new Processo();
		Date a = new Date();
		processo.setId(id);
		processo.setDataProcess(a.getTime());
		processo.setValor(new Double(11.00));
		processo.setAdvogadoList(new ArrayList<Advogado>());
		processo.getAdvogadoList().add(insertAdvogado(id, TabelaEnum.PROCESSO, action));
		processo.setClienteList(new ArrayList<Cliente>());
		processo.getClienteList().add(insertCliente(id, TabelaEnum.PROCESSO, action));
		processo.setAudienciaList(new ArrayList<Audiencia>());
		processo.getAudienciaList().add(insertAudiencia(id, TabelaEnum.PROCESSO, action));
		processo.setTabelaEnum(tabela);
		processo.setParentId(id);
		processo.setEmprId(EMPID);
		processo.setModifyDateUTC(a.getTime());
		processo.setCreateDateUTC(a.getTime());
		processo.setCreateUser("system");
		processo.setModifyUser("system");
		processo.setProcessId(1);
		processo.setModelAction(action);

		return processo;
	}

	public static Convenio insertConvenio(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Convenio convenio = new Convenio();
		Date a = new Date();
		convenio.setId(id);
		convenio.setTabelaEnum(tabela);
		convenio.setParentId(id);
		convenio.setEmprId(EMPID);
		convenio.setModifyDateUTC(a.getTime());
		convenio.setCreateDateUTC(a.getTime());
		convenio.setCreateUser("system");
		convenio.setModifyUser("system");
		convenio.setProcessId(1);
		convenio.setModelAction(action);

		return convenio;
	}

	public static Cidade insertCidade(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Cidade cidade = new Cidade();
		Date a = new Date();
		cidade.setId(id);
		cidade.setCodigo("codigo_1 - " + action.toString());
		cidade.setNome("nome_2 - " + action.toString());
		cidade.setCdIBGE("cdIBGE_3 - " + action.toString());
		cidade.setCep("cep_4 - " + action.toString());
		cidade.setEstado(new Estado());
		cidade.setTabelaEnum(tabela);
		cidade.setParentId(id);
		cidade.setEmprId(EMPID);
		cidade.setModifyDateUTC(a.getTime());
		cidade.setCreateDateUTC(a.getTime());
		cidade.setCreateUser("system");
		cidade.setModifyUser("system");
		cidade.setProcessId(1);
		cidade.setModelAction(action);

		return cidade;
	}

	public static Estado insertEstado(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Estado estado = new Estado();
		Date a = new Date();
		estado.setId(id);
		estado.setNome("nome_1 - " + action.toString());
		estado.setAbreviacao("abreviacao_2 - " + action.toString());
		estado.setTabelaEnum(tabela);
		estado.setParentId(id);
		estado.setEmprId(EMPID);
		estado.setModifyDateUTC(a.getTime());
		estado.setCreateDateUTC(a.getTime());
		estado.setCreateUser("system");
		estado.setModifyUser("system");
		estado.setProcessId(1);
		estado.setModelAction(action);

		return estado;
	}

	public static Tarefa insertTarefa(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Tarefa tarefa = new Tarefa();
		Date a = new Date();
		tarefa.setId(id);
		tarefa.setTabelaEnum(tabela);
		tarefa.setParentId(id);
		tarefa.setEmprId(EMPID);
		tarefa.setModifyDateUTC(a.getTime());
		tarefa.setCreateDateUTC(a.getTime());
		tarefa.setCreateUser("system");
		tarefa.setModifyUser("system");
		tarefa.setProcessId(1);
		tarefa.setModelAction(action);

		return tarefa;
	}


	public static Consulta insertConsulta(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Consulta consulta = new Consulta();
		Date a = new Date();
		consulta.setId(id);
		consulta.setDataConsulta(a.getTime());
		consulta.setValor(new Double(11.00));
		consulta.setDataMarcacao(a.getTime());
		consulta.setMedico(insertMedico(id, TabelaEnum.CONSULTA, action));
		consulta.setPaciente(insertPaciente(id, TabelaEnum.CONSULTA, action));
		consulta.setPlanoSaude(insertPlanoSaude(id, TabelaEnum.CONSULTA, action));
		consulta.setExameList(new ArrayList<ExamePessoa>());
		consulta.getExameList().add(insertExamePessoa(id, TabelaEnum.CONSULTA, action));
		consulta.setTabelaEnum(tabela);
		consulta.setParentId(id);
		consulta.setEmprId(EMPID);
		consulta.setModifyDateUTC(a.getTime());
		consulta.setCreateDateUTC(a.getTime());
		consulta.setCreateUser("system");
		consulta.setModifyUser("system");
		consulta.setProcessId(1);
		consulta.setModelAction(action);

		return consulta;
	}



	public static Exame insertExame(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Exame exame = new Exame();
		Date a = new Date();
		exame.setId(id);
		exame.setTabelaEnum(tabela);
		exame.setParentId(id);
		exame.setEmprId(EMPID);
		exame.setModifyDateUTC(a.getTime());
		exame.setCreateDateUTC(a.getTime());
		exame.setCreateUser("system");
		exame.setModifyUser("system");
		exame.setProcessId(1);
		exame.setModelAction(action);

		return exame;
	}


	public static NotaFiscalEntrada insertNotaFiscalEntrada(Integer id, TabelaEnum tabela,
			PersistenceActionEnum action) {
		NotaFiscalEntrada notafiscalentrada = new NotaFiscalEntrada();
		Date a = new Date();
		notafiscalentrada.setId(id);
		notafiscalentrada.setTabelaEnum(tabela);
		notafiscalentrada.setParentId(id);
		notafiscalentrada.setEmprId(EMPID);
		notafiscalentrada.setModifyDateUTC(a.getTime());
		notafiscalentrada.setCreateDateUTC(a.getTime());
		notafiscalentrada.setCreateUser("system");
		notafiscalentrada.setModifyUser("system");
		notafiscalentrada.setProcessId(1);
		notafiscalentrada.setModelAction(action);

		return notafiscalentrada;
	}

	public static PedidoCompras insertPedidoCompras(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		PedidoCompras pedidocompras = new PedidoCompras();
		Date a = new Date();
		pedidocompras.setId(id);
		pedidocompras.setTabelaEnum(tabela);
		pedidocompras.setParentId(id);
		pedidocompras.setEmprId(EMPID);
		pedidocompras.setModifyDateUTC(a.getTime());
		pedidocompras.setCreateDateUTC(a.getTime());
		pedidocompras.setCreateUser("system");
		pedidocompras.setModifyUser("system");
		pedidocompras.setProcessId(1);
		pedidocompras.setModelAction(action);

		return pedidocompras;
	}

	public static Cotacao insertCotacao(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Cotacao cotacao = new Cotacao();
		Date a = new Date();
		cotacao.setId(id);
		cotacao.setTabelaEnum(tabela);
		cotacao.setParentId(id);
		cotacao.setEmprId(EMPID);
		cotacao.setModifyDateUTC(a.getTime());
		cotacao.setCreateDateUTC(a.getTime());
		cotacao.setCreateUser("system");
		cotacao.setModifyUser("system");
		cotacao.setProcessId(1);
		cotacao.setModelAction(action);

		return cotacao;
	}


	public static Avisos insertAvisos(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Avisos avisos = new Avisos();
		Date a = new Date();
		avisos.setId(id);
		avisos.setTabelaEnum(tabela);
		avisos.setParentId(id);
		avisos.setEmprId(EMPID);
		avisos.setModifyDateUTC(a.getTime());
		avisos.setCreateDateUTC(a.getTime());
		avisos.setCreateUser("system");
		avisos.setModifyUser("system");
		avisos.setProcessId(1);
		avisos.setModelAction(action);

		return avisos;
	}


	public static Eventos insertEventos(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Eventos eventos = new Eventos();
		Date a = new Date();
		eventos.setId(id);
		eventos.setTabelaEnum(tabela);
		eventos.setParentId(id);
		eventos.setEmprId(EMPID);
		eventos.setModifyDateUTC(a.getTime());
		eventos.setCreateDateUTC(a.getTime());
		eventos.setCreateUser("system");
		eventos.setModifyUser("system");
		eventos.setProcessId(1);
		eventos.setModelAction(action);

		return eventos;
	}

	public static Beneficios insertBeneficios(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Beneficios beneficios = new Beneficios();
		Date a = new Date();
		beneficios.setId(id);
		beneficios.setTabelaEnum(tabela);
		beneficios.setParentId(id);
		beneficios.setEmprId(EMPID);
		beneficios.setModifyDateUTC(a.getTime());
		beneficios.setCreateDateUTC(a.getTime());
		beneficios.setCreateUser("system");
		beneficios.setModifyUser("system");
		beneficios.setProcessId(1);
		beneficios.setModelAction(action);

		return beneficios;
	}

	public static HorarioFunc insertHoraFunc(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		HorarioFunc horafunc = new HorarioFunc();
		Date a = new Date();
		horafunc.setId(id);
		horafunc.setTabelaEnum(tabela);
		horafunc.setParentId(id);
		horafunc.setEmprId(EMPID);
		horafunc.setModifyDateUTC(a.getTime());
		horafunc.setCreateDateUTC(a.getTime());
		horafunc.setCreateUser("system");
		horafunc.setModifyUser("system");
		horafunc.setProcessId(1);
		horafunc.setModelAction(action);

		return horafunc;
	}


	public static Empresa insertEmpresa(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Empresa empresa = new Empresa();
		Date a = new Date();
		empresa.setId(id);
		empresa.setNome("nome_1 - " + action.toString());
		empresa.setRazao("razao_1 - " + action.toString());
		empresa.setEntidadeId(1002);
		empresa.setNumFunc(1003);
		empresa.setEntidadeEnumValue(1005);
		empresa.setRegime(insertRegime(id, TabelaEnum.EMPRESA, action));
		empresa.setDocumentos(new ArrayList<Documento>());
		empresa.getDocumentos().add(insertDocumento(id, TabelaEnum.EMPRESA, action));
		empresa.setEnderecos(new ArrayList<Endereco>());
		empresa.getEnderecos().add(insertEndereco(id, TabelaEnum.EMPRESA, action));
		empresa.setEmails(new ArrayList<Email>());
		empresa.getEmails().add(insertEmail(id, TabelaEnum.EMPRESA, action));
		empresa.setTelefones(new ArrayList<Telefone>());
		empresa.getTelefones().add(insertTelefone(id, TabelaEnum.EMPRESA, action));
		empresa.setCnaes(new ArrayList<CnaeEmpresa>());
		empresa.getCnaes().add(insertCnaeEmpresa(id, TabelaEnum.EMPRESA, action));
		empresa.setStatusList(new ArrayList<Status>());
		empresa.getStatusList().add(insertStatus(id, TabelaEnum.EMPRESA, action));
		empresa.setNotes(new ArrayList<Note>());
		empresa.getNotes().add(insertNotes(id, TabelaEnum.EMPRESA, action));
		empresa.setPlanosServicos(insertPlanoByEmpresa(id, tabela, action));
		empresa.setUsuarios(new ArrayList<Usuario>());
		empresa.getUsuarios().add(insertUsuario(id, TabelaEnum.EMPRESA, action));

		empresa.setConfiguracao(insertConfiguracao(id, TabelaEnum.EMPRESA, action));

		empresa.setSocios(new ArrayList<Socio>());
		empresa.getSocios().add(insertSocio(id, TabelaEnum.EMPRESA, action));

		empresa.setTabelaEnum(tabela);
		empresa.setParentId(id);
		empresa.setEntidadeEnum(EntidadeTypeEnum.EMPRESA);
		empresa.setEmprId(4595);
		empresa.setModifyDateUTC(a.getTime());
		empresa.setCreateDateUTC(a.getTime());
		empresa.setCreateUser("system");
		empresa.setModifyUser("system");
		empresa.setProcessId(1);
		empresa.setModelAction(action);

		return empresa;
	}

	private static PlanoByEmpresa insertPlanoByEmpresa(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {

		PlanoByEmpresa empresa = new PlanoByEmpresa();
		Date a = new Date();
		empresa.setId(id);

		empresa.setNumContrato(1000);
		empresa.setValor(2.2);
		empresa.setDataInicio(a.getTime());
		empresa.setDataFim(a.getTime());
		empresa.setPlanoServicoList(new ArrayList<ServicoAndPlano>());
		empresa.getPlanoServicoList().add(insertServicoAndPlano(id, tabela, action));
		empresa.setTabelaEnum(tabela);
		empresa.setParentId(id);
		empresa.setEmprId(EMPID);
		empresa.setModifyDateUTC(a.getTime());
		empresa.setCreateDateUTC(a.getTime());
		empresa.setCreateUser("system");
		empresa.setModifyUser("system");
		empresa.setProcessId(1);
		empresa.setModelAction(action);

		return empresa;
	}



	public static Filial insertFilial(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Filial filial = new Filial();
		Date a = new Date();
		filial.setId(id);
		filial.setEntidadeEnum(EntidadeTypeEnum.FILIAL);
		filial.setNome("nome_1 - " + action.toString());
		filial.setEntidadeId(1002);
		filial.setNumFunc(1003);
		filial.setEntidadeEnumValue(1005);
		filial.setRegime(insertRegime(id, TabelaEnum.FILIAL, action));
		filial.setDocumentos(new ArrayList<Documento>());
		filial.getDocumentos().add(insertDocumento(id, TabelaEnum.FILIAL, action));
		filial.setEnderecos(new ArrayList<Endereco>());
		filial.getEnderecos().add(insertEndereco(id, TabelaEnum.FILIAL, action));
		filial.setEmails(new ArrayList<Email>());
		filial.getEmails().add(insertEmail(id, TabelaEnum.FILIAL, action));
		filial.setTelefones(new ArrayList<Telefone>());
		filial.getTelefones().add(insertTelefone(id, TabelaEnum.FILIAL, action));
		filial.setCnaes(new ArrayList<CnaeEmpresa>());
		filial.getCnaes().add(insertCnaeEmpresa(id, TabelaEnum.FILIAL, action));
		filial.setStatusList(new ArrayList<Status>());
		filial.getStatusList().add(insertStatus(id, TabelaEnum.FILIAL, action));
		filial.setNotes(new ArrayList<Note>());
		filial.getNotes().add(insertNotes(id, TabelaEnum.FILIAL, action));
		filial.setTabelaEnum(tabela);
		filial.setParentId(id);
		filial.setEmprId(EMPID);
		filial.setModifyDateUTC(a.getTime());
		filial.setCreateDateUTC(a.getTime());
		filial.setCreateUser("system");
		filial.setModifyUser("system");
		filial.setProcessId(1);
		filial.setModelAction(action);

		return filial;
	}

	public static Deposito insertDeposito(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Deposito deposito = new Deposito();
		Date a = new Date();
		deposito.setId(id);
		deposito.setEntidadeEnum(EntidadeTypeEnum.DEPOSITO);
		deposito.setNome("nome_1 - " + action.toString());
		deposito.setEntidadeId(1002);
		deposito.setNumFunc(1003);
		deposito.setEntidadeEnumValue(1005);
		deposito.setRegime(insertRegime(id, TabelaEnum.DEPOSITO, action));
		deposito.setDocumentos(new ArrayList<Documento>());
		deposito.getDocumentos().add(insertDocumento(id, TabelaEnum.DEPOSITO, action));
		deposito.setEnderecos(new ArrayList<Endereco>());
		deposito.getEnderecos().add(insertEndereco(id, TabelaEnum.DEPOSITO, action));
		deposito.setEmails(new ArrayList<Email>());
		deposito.getEmails().add(insertEmail(id, TabelaEnum.DEPOSITO, action));
		deposito.setTelefones(new ArrayList<Telefone>());
		deposito.getTelefones().add(insertTelefone(id, TabelaEnum.DEPOSITO, action));
		deposito.setCnaes(new ArrayList<CnaeEmpresa>());
		deposito.getCnaes().add(insertCnaeEmpresa(id, TabelaEnum.DEPOSITO, action));
		deposito.setStatusList(new ArrayList<Status>());
		deposito.getStatusList().add(insertStatus(id, TabelaEnum.DEPOSITO, action));
		deposito.setNotes(new ArrayList<Note>());
		deposito.getNotes().add(insertNotes(id, TabelaEnum.DEPOSITO, action));
		deposito.setTabelaEnum(tabela);
		deposito.setParentId(id);
		deposito.setEmprId(EMPID);
		deposito.setModifyDateUTC(a.getTime());
		deposito.setCreateDateUTC(a.getTime());
		deposito.setCreateUser("system");
		deposito.setModifyUser("system");
		deposito.setProcessId(1);
		deposito.setModelAction(action);

		return deposito;
	}

	public static Usuario insertUsuario(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Usuario usuario = new Usuario();
		Date a = new Date();
		usuario.setId(id);

		usuario.setCpf(insertDocumento(id, TabelaEnum.USUARIO, action));
		usuario.setEmail("econtabilsistemas@gmail.com");
		usuario.setSenha("senha_4 - " + action.toString());
		usuario.setPergunta("pergunta_5 - " + action.toString());
		usuario.setRoles(new ArrayList<Role>());
		usuario.setLanguage("pt");
		usuario.setUltAcesso(a.getTime());
		usuario.setTabelaEnum(tabela);
		usuario.setParentId(id);
		usuario.setEmprId(EMPID);
		usuario.setModifyDateUTC(a.getTime());
		usuario.setCreateDateUTC(a.getTime());
		usuario.setCreateUser("system");
		usuario.setModifyUser("system");
		usuario.setProcessId(1);
		usuario.setModelAction(action);

		return usuario;
	}

	public static Advocacia insertAdvocacia(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Advocacia advocacia = new Advocacia();
		Date a = new Date();
		advocacia.setId(id);
		advocacia.setEntidadeEnum(EntidadeTypeEnum.ADVOCACIA);
		advocacia.setNome("nome_1 - " + action.toString());
		advocacia.setEntidadeId(1002);
		advocacia.setNumFunc(1003);
		advocacia.setEntidadeEnumValue(1005);
		advocacia.setRegime(insertRegime(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setDocumentos(new ArrayList<Documento>());
		advocacia.getDocumentos().add(insertDocumento(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setEnderecos(new ArrayList<Endereco>());
		advocacia.getEnderecos().add(insertEndereco(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setEmails(new ArrayList<Email>());
		advocacia.getEmails().add(insertEmail(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setTelefones(new ArrayList<Telefone>());
		advocacia.getTelefones().add(insertTelefone(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setCnaes(new ArrayList<CnaeEmpresa>());
		advocacia.getCnaes().add(insertCnaeEmpresa(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setStatusList(new ArrayList<Status>());
		advocacia.getStatusList().add(insertStatus(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setNotes(new ArrayList<Note>());
		advocacia.getNotes().add(insertNotes(id, TabelaEnum.ADVOCACIA, action));
		advocacia.setTabelaEnum(tabela);
		advocacia.setParentId(id);
		advocacia.setEmprId(EMPID);
		advocacia.setModifyDateUTC(a.getTime());
		advocacia.setCreateDateUTC(a.getTime());
		advocacia.setCreateUser("system");
		advocacia.setModifyUser("system");
		advocacia.setProcessId(1);
		advocacia.setModelAction(action);

		return advocacia;
	}

	public static Clinica insertClinica(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Clinica clinica = new Clinica();
		Date a = new Date();
		clinica.setId(id);
		clinica.setEntidadeEnum(EntidadeTypeEnum.CLINICA);
		clinica.setNome("nome_1 - " + action.toString());
		clinica.setEntidadeId(1002);
		clinica.setNumFunc(1003);
		clinica.setEntidadeEnumValue(1005);
		clinica.setRegime(insertRegime(id, TabelaEnum.CLINICA, action));
		clinica.setDocumentos(new ArrayList<Documento>());
		clinica.getDocumentos().add(insertDocumento(id, TabelaEnum.CLINICA, action));
		clinica.setEnderecos(new ArrayList<Endereco>());
		clinica.getEnderecos().add(insertEndereco(id, TabelaEnum.CLINICA, action));
		clinica.setEmails(new ArrayList<Email>());
		clinica.getEmails().add(insertEmail(id, TabelaEnum.CLINICA, action));
		clinica.setTelefones(new ArrayList<Telefone>());
		clinica.getTelefones().add(insertTelefone(id, TabelaEnum.CLINICA, action));
		clinica.setCnaes(new ArrayList<CnaeEmpresa>());
		clinica.getCnaes().add(insertCnaeEmpresa(id, TabelaEnum.CLINICA, action));
		clinica.setStatusList(new ArrayList<Status>());
		clinica.getStatusList().add(insertStatus(id, TabelaEnum.CLINICA, action));
		clinica.setNotes(new ArrayList<Note>());
		clinica.getNotes().add(insertNotes(id, TabelaEnum.CLINICA, action));
		clinica.setTabelaEnum(tabela);
		clinica.setParentId(id);
		clinica.setEmprId(EMPID);
		clinica.setModifyDateUTC(a.getTime());
		clinica.setCreateDateUTC(a.getTime());
		clinica.setCreateUser("system");
		clinica.setModifyUser("system");
		clinica.setProcessId(1);
		clinica.setModelAction(action);

		return clinica;
	}

	public static Condominio insertCondominio(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Condominio condominio = new Condominio();
		Date a = new Date();
		condominio.setId(id);
		condominio.setEntidadeEnum(EntidadeTypeEnum.CONDOMINIO);
		condominio.setNome("nome_1 - " + action.toString());
		condominio.setEntidadeId(1002);
		condominio.setNumFunc(1003);
		condominio.setEntidadeEnumValue(1005);
		condominio.setRegime(insertRegime(id, TabelaEnum.CONDOMINIO, action));
		condominio.setDocumentos(new ArrayList<Documento>());
		condominio.getDocumentos().add(insertDocumento(id, TabelaEnum.CONDOMINIO, action));
		condominio.setEnderecos(new ArrayList<Endereco>());
		condominio.getEnderecos().add(insertEndereco(id, TabelaEnum.CONDOMINIO, action));
		condominio.setEmails(new ArrayList<Email>());
		condominio.getEmails().add(insertEmail(id, TabelaEnum.CONDOMINIO, action));
		condominio.setTelefones(new ArrayList<Telefone>());
		condominio.getTelefones().add(insertTelefone(id, TabelaEnum.CONDOMINIO, action));
		condominio.setCnaes(new ArrayList<CnaeEmpresa>());
		condominio.getCnaes().add(insertCnaeEmpresa(id, TabelaEnum.CONDOMINIO, action));
		condominio.setStatusList(new ArrayList<Status>());
		condominio.getStatusList().add(insertStatus(id, TabelaEnum.CONDOMINIO, action));
		condominio.setNotes(new ArrayList<Note>());
		condominio.getNotes().add(insertNotes(id, TabelaEnum.CONDOMINIO, action));
		condominio.setTabelaEnum(tabela);
		condominio.setParentId(id);
		condominio.setEmprId(EMPID);
		condominio.setModifyDateUTC(a.getTime());
		condominio.setCreateDateUTC(a.getTime());
		condominio.setCreateUser("system");
		condominio.setModifyUser("system");
		condominio.setProcessId(1);
		condominio.setModelAction(action);

		return condominio;
	}

	public static ContasPagar insertContasPagar(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ContasPagar contaspagar = new ContasPagar();
		Date a = new Date();
		contaspagar.setId(id);
		contaspagar.setFornecedor(insertFornecedor(id, tabela, action));
		contaspagar.setNumero("numero_1 - " + action.toString());
		contaspagar.setDataEmissao(a.getTime());
		contaspagar.setDataVencimento(a.getTime());
		contaspagar.setDocId("5555");
		contaspagar.setValor(new Double(10.00));
		contaspagar.setObservacao("observacao_6 - " + action.toString());
		contaspagar.setFinanceiroEnumValue(1007);
		contaspagar.setListBaixa(new ArrayList<BaixaTitulo>());
		contaspagar.getListBaixa().add(insertBaixaTitulo(null, TabelaEnum.CONTASPAGAR, action));
		contaspagar.setTabelaEnum(tabela);
		contaspagar.setParentId(id);
		contaspagar.setEmprId(EMPID);
		contaspagar.setModifyDateUTC(a.getTime());
		contaspagar.setCreateDateUTC(a.getTime());
		contaspagar.setCreateUser("system");
		contaspagar.setModifyUser("system");
		contaspagar.setProcessId(1);
		contaspagar.setModelAction(action);

		return contaspagar;
	}

	public static Titulo insertTitulo(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Titulo titulo = new Titulo();
		Date a = new Date();
		titulo.setId(id);
		titulo.setNumero("numero_1 - " + action.toString());
		titulo.setDataEmissao(a.getTime());
		titulo.setDataVencimento(a.getTime());
		titulo.setDocId("8888");
		titulo.setValor(new Double(10.00));
		titulo.setObservacao("observacao_6 - " + action.toString());
		titulo.setFinanceiroEnumValue(1007);
		titulo.setListBaixa(new ArrayList<BaixaTitulo>());
		titulo.getListBaixa().add(insertBaixaTitulo(id, TabelaEnum.TITULO, action));
		titulo.setTabelaEnum(tabela);
		titulo.setParentId(id);
		titulo.setEmprId(EMPID);
		titulo.setModifyDateUTC(a.getTime());
		titulo.setCreateDateUTC(a.getTime());
		titulo.setCreateUser("system");
		titulo.setModifyUser("system");
		titulo.setProcessId(1);
		titulo.setModelAction(action);

		return titulo;
	}

	public static BaixaTitulo insertBaixaTitulo(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		BaixaTitulo baixatitulo = new BaixaTitulo();
		Date a = new Date();
		baixatitulo.setId(id);
		baixatitulo.setDataBaixa(a.getTime());
		baixatitulo.setObservacao("observacao_3 - " + action.toString());
		baixatitulo.setValor(new Double(10.00));
		baixatitulo.setJuros(new Double(10.00));
		baixatitulo.setMulta(new Double(10.00));
		baixatitulo.setDesconto(new Double(10.00));
		baixatitulo.setConta(new Conta(1001));
		baixatitulo.setTabelaEnum(tabela);
		baixatitulo.setParentId(id);
		baixatitulo.setEmprId(EMPID);
		baixatitulo.setModifyDateUTC(a.getTime());
		baixatitulo.setCreateDateUTC(a.getTime());
		baixatitulo.setCreateUser("system");
		baixatitulo.setModifyUser("system");
		baixatitulo.setProcessId(1);
		baixatitulo.setModelAction(action);

		return baixatitulo;
	}

	public static TipoBaixa insertTipoBaixa(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		TipoBaixa tipobaixa = new TipoBaixa();
		Date a = new Date();
		tipobaixa.setId(id);
		tipobaixa.setNome("nome_1 - " + action.toString());
		tipobaixa.setDescricao("Descricao - " + action.toString());
		tipobaixa.setTabelaEnum(tabela);
		tipobaixa.setParentId(id);
		tipobaixa.setEmprId(EMPID);
		tipobaixa.setModifyDateUTC(a.getTime());
		tipobaixa.setCreateDateUTC(a.getTime());
		tipobaixa.setCreateUser("system");
		tipobaixa.setModifyUser("system");
		tipobaixa.setProcessId(1);
		tipobaixa.setModelAction(action);

		return tipobaixa;
	}

	public static ContasReceber insertContasReceber(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ContasReceber contasreceber = new ContasReceber();
		Date a = new Date();
		contasreceber.setId(id);
		contasreceber.setCliente(insertCliente(null, tabela, action));
		contasreceber.setNumero("numero_1 - " + action.toString());
		contasreceber.setDataEmissao(a.getTime());
		contasreceber.setDataVencimento(a.getTime());
		contasreceber.setDocId("1002");
		contasreceber.setValor(new Double(10.00));
		contasreceber.setObservacao("observacao_6 - " + action.toString());
		contasreceber.setFinanceiroEnumValue(1007);
		contasreceber.setListBaixa(new ArrayList<BaixaTitulo>());
		contasreceber.getListBaixa().add(insertBaixaTitulo(id, TabelaEnum.CONTASRECEBER, action));
		contasreceber.setTabelaEnum(tabela);
		contasreceber.setParentId(id);
		contasreceber.setEmprId(EMPID);
		contasreceber.setModifyDateUTC(a.getTime());
		contasreceber.setCreateDateUTC(a.getTime());
		contasreceber.setCreateUser("system");
		contasreceber.setModifyUser("system");
		contasreceber.setProcessId(1);
		contasreceber.setModelAction(action);

		return contasreceber;
	}

	public static CondPag insertCondPag(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		CondPag condpag = new CondPag();
		Date a = new Date();
		condpag.setId(id);
		condpag.setNome("nome_1 - " + action.toString());
		condpag.setValorIni(new Double(10.00));
		condpag.setValorFin(new Double(10.00));
		condpag.setParcelas(1004);
		condpag.setListTipoPag(new ArrayList<TipoPag>());
		condpag.getListTipoPag().add(insertTipoPag(id, TabelaEnum.CONDPAG, action));
		condpag.setTabelaEnum(tabela);
		condpag.setParentId(id);
		condpag.setEmprId(EMPID);
		condpag.setModifyDateUTC(a.getTime());
		condpag.setCreateDateUTC(a.getTime());
		condpag.setCreateUser("system");
		condpag.setModifyUser("system");
		condpag.setProcessId(1);
		condpag.setModelAction(action);

		return condpag;
	}





	public static FormaPg insertFormaPg(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		FormaPg formapg = new FormaPg();
		Date a = new Date();
		formapg.setId(id);
		formapg.setDescricao("descricao_1 - " + action.toString());
		formapg.setDiasPg(1002);
		formapg.setEntrada(1003);
		formapg.setTabelaEnum(tabela);
		formapg.setParentId(id);
		formapg.setEmprId(EMPID);
		formapg.setModifyDateUTC(a.getTime());
		formapg.setCreateDateUTC(a.getTime());
		formapg.setCreateUser("system");
		formapg.setModifyUser("system");
		formapg.setProcessId(1);
		formapg.setModelAction(action);

		return formapg;
	}

	public static Banco insertBanco(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Banco banco = new Banco();
		Date a = new Date();
		banco.setId(id);
		banco.setNome("nome_1 - " + action.toString());
		banco.setTabelaEnum(tabela);
		banco.setParentId(id);
		banco.setEmprId(EMPID);
		banco.setModifyDateUTC(a.getTime());
		banco.setCreateDateUTC(a.getTime());
		banco.setCreateUser("system");
		banco.setModifyUser("system");
		banco.setProcessId(1);
		banco.setModelAction(action);

		return banco;
	}

	public static ContaCorrente insertContaCorrente(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ContaCorrente contacorrente = new ContaCorrente();
		Date a = new Date();
		contacorrente.setId(id);

		contacorrente.setSaldo(new Double(10.00));
		contacorrente.setStatusConta(10);
		contacorrente.setNumeroConta("numeroConta_3 - " + action.toString());
		contacorrente.setNossoNumero("nossoNumero_4 - " + action.toString());
		contacorrente.setHistoricoList(new ArrayList<HistoricoMovimento>());
		contacorrente.getHistoricoList().add(insertHistoricoMovimento(null, TabelaEnum.CONTACORRENTE, action));
		contacorrente.setTabelaEnum(tabela);
		contacorrente.setParentId(id);
		contacorrente.setEmprId(EMPID);
		contacorrente.setModifyDateUTC(a.getTime());
		contacorrente.setCreateDateUTC(a.getTime());
		contacorrente.setCreateUser("system");
		contacorrente.setModifyUser("system");
		contacorrente.setProcessId(1);
		contacorrente.setModelAction(action);

		return contacorrente;
	}




	public static Caixa insertCaixa(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Caixa caixa = new Caixa();
		Date a = new Date();
		caixa.setId(id);
		caixa.setSaldo(new Double(10.00));
		caixa.setBaixaTituloList(new ArrayList<BaixaTitulo>());
		caixa.getBaixaTituloList().add(insertBaixaTitulo(id, TabelaEnum.CAIXA, action));
		caixa.setTabelaEnum(tabela);
		caixa.setParentId(id);
		caixa.setEmprId(EMPID);
		caixa.setModifyDateUTC(a.getTime());
		caixa.setCreateDateUTC(a.getTime());
		caixa.setCreateUser("system");
		caixa.setModifyUser("system");
		caixa.setProcessId(1);
		caixa.setModelAction(action);

		return caixa;
	}

	public static Regime insertRegime(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Regime regime = new Regime();
		Date a = new Date();
		regime.setId(id);
		regime.setNome("nome_1 - " + action.toString());
		regime.setDescricao("descricao_2 - " + action.toString());
		regime.setTabelaEnum(tabela);
		regime.setParentId(id);
		regime.setEmprId(EMPID);
		regime.setModifyDateUTC(a.getTime());
		regime.setCreateDateUTC(a.getTime());
		regime.setCreateUser("system");
		regime.setModifyUser("system");
		regime.setProcessId(1);
		regime.setModelAction(action);

		return regime;
	}

	public static Cfop insertCfops(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Cfop cfop = new Cfop();
		Date a = new Date();
		cfop.setId(id);
		cfop.setCfop("cfop_1 - " + action.toString());
		cfop.setNatureza("natureza_2 - " + action.toString());
		cfop.setSimplificado("simplificado_3 - " + action.toString());
		cfop.setCfopTypeEnumValue(1);
		cfop.setIcms(new Double(10.00));
		cfop.setIcmsReduzido(new Double(10.00));
		cfop.setMargemAgregadaST(new Double(10.00));
		cfop.setCstPrincipal(new Double(10.00));
		cfop.setClassFiscal(new Double(10.00));
		cfop.setObservacao("observacao_10 - " + action.toString());
		cfop.setTabelaEnum(tabela);
		cfop.setParentId(id);
		cfop.setEmprId(EMPID);
		cfop.setModifyDateUTC(a.getTime());
		cfop.setCreateDateUTC(a.getTime());
		cfop.setCreateUser("system");
		cfop.setModifyUser("system");
		cfop.setProcessId(1);
		cfop.setModelAction(action);

		return cfop;
	}

	public static Cnae insertCnae(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Cnae cnae = new Cnae();
		Date a = new Date();
		if(tabela == TabelaEnum.CNAE)
		{
			cnae.setId(id);
		}
		cnae.setCodigo("codigo_1 - " + action.toString());
		cnae.setCnae("cnae_2 - " + action.toString());
		cnae.setDescricao("descricao_3 - " + action.toString());
		cnae.setAbreviado("abreviado_4 - " + action.toString());
		cnae.setTabelaEnum(tabela);
		cnae.setParentId(id);
		cnae.setEmprId(EMPID);
		cnae.setModifyDateUTC(a.getTime());
		cnae.setCreateDateUTC(a.getTime());
		cnae.setCreateUser("system");
		cnae.setModifyUser("system");
		cnae.setProcessId(1);
		cnae.setModelAction(action);

		return cnae;
	}

	public static CnaeEmpresa insertCnaeEmpresa(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		CnaeEmpresa cnaeempresa = new CnaeEmpresa();
		Date a = new Date();
		cnaeempresa.setIdCnae(insertCnae(id, TabelaEnum.CNAEPORRELACIONAMENTO, action));
		cnaeempresa.setTabelaEnum(tabela);
		cnaeempresa.setParentId(id);
		cnaeempresa.setEmprId(EMPID);
		cnaeempresa.setModifyDateUTC(a.getTime());
		cnaeempresa.setCreateDateUTC(a.getTime());
		cnaeempresa.setCreateUser("system");
		cnaeempresa.setModifyUser("system");
		cnaeempresa.setProcessId(1);
		cnaeempresa.setModelAction(action);

		return cnaeempresa;
	}


	public static ProdutoEmpresa insertProdutoParent(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ProdutoEmpresa produtoparent = new ProdutoEmpresa();
		Date a = new Date();
		produtoparent.setId(id);
		produtoparent.setProdId(insertProduto(id, TabelaEnum.PRODUTOPARENT, action));
		produtoparent.setTransactionId(insertTransaction(id, TabelaEnum.PRODUTOPARENT, action).getId());
		produtoparent.setTributacao(insertTributacao(id, TabelaEnum.PRODUTOPARENT, action));
		produtoparent.setEstoqueList(new ArrayList<Estoque>());
		produtoparent.getEstoqueList().add(insertEstoque(id, TabelaEnum.PRODUTOPARENT, action));
		produtoparent.setPrecoList(new ArrayList<Preco>());
		produtoparent.getPrecoList().add(insertPreco(id, TabelaEnum.PRODUTOPARENT, action));
		produtoparent.setCustoList(new ArrayList<Custo>());
		produtoparent.getCustoList().add(insertCusto(id, TabelaEnum.PRODUTOPARENT, action));
		produtoparent.setPorcaoList(new ArrayList<Porcao>());
		produtoparent.getPorcaoList().add(insertPorcao(id, TabelaEnum.PRODUTOPARENT, action));
		produtoparent.setRentabilidadeList(new ArrayList<Rentabilidade>());
		produtoparent.getRentabilidadeList().add(insertRentabilidade(id, TabelaEnum.PRODUTOPARENT, action));
		produtoparent.setTabelaEnum(tabela);
		produtoparent.setParentId(id);
		produtoparent.setEmprId(EMPID);
		produtoparent.setModifyDateUTC(a.getTime());
		produtoparent.setCreateDateUTC(a.getTime());
		produtoparent.setCreateUser("system");
		produtoparent.setModifyUser("system");
		produtoparent.setProcessId(1);
		produtoparent.setModelAction(action);
		produtoparent.setAplicacao("aplicacao_5 - " + action.toString());
		produtoparent.setFracao("fracao_6 - " + action.toString());


		produtoparent.setPesoBruto(new Double(10.00));
		produtoparent.setPesoLiquido(new Double(10.00));
		produtoparent.setModoUso("modoUso_15 - " + action.toString());
		produtoparent.setCodigo("codigo_1 - " + action.toString());

		return produtoparent;
	}





	public static Produto insertProduto(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Produto produto = new Produto();
		Date a = new Date();
		produto.setId(id);

		produto.setCdBarras("cdBarras_2 - " + action.toString());
		produto.setProduto("produto_3 - " + action.toString());
		produto.setDataCreate(a.getTime());

		produto.setTabelaEnum(tabela);
		produto.setParentId(id);
		produto.setEmprId(EMPID);
		produto.setMarca(insertMarca(id, TabelaEnum.PRODUTO, action));
		produto.setUniMed(insertUniMed(id, TabelaEnum.PRODUTO, action));
		produto.setModifyDateUTC(a.getTime());
		produto.setCreateDateUTC(a.getTime());
		produto.setCreateUser("system");
		produto.setModifyUser("system");
		produto.setProcessId(1);
		produto.setModelAction(action);

		return produto;
	}

	public static Cfop insertCfop(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Cfop cfop = new Cfop();
		Date a = new Date();
		cfop.setId(id);
		cfop.setCfop("cfop_1 - " + action.toString());
		cfop.setNatureza("natureza_2 - " + action.toString());
		cfop.setSimplificado("simplificado_3 - " + action.toString());
		cfop.setCfopTypeEnumValue(1);
		cfop.setIcms(new Double(10.00));
		cfop.setIcmsReduzido(new Double(10.00));
		cfop.setMargemAgregadaST(new Double(10.00));
		cfop.setCstPrincipal(new Double(10.00));
		cfop.setClassFiscal(new Double(10.00));
		cfop.setObservacao("observacao_10 - " + action.toString());
		cfop.setTabelaEnum(tabela);
		cfop.setParentId(id);
		cfop.setEmprId(EMPID);
		cfop.setModifyDateUTC(a.getTime());
		cfop.setCreateDateUTC(a.getTime());
		cfop.setCreateUser("system");
		cfop.setModifyUser("system");
		cfop.setProcessId(1);
		cfop.setModelAction(action);

		return cfop;
	}

	public static Marca insertMarca(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Marca marca = new Marca();
		Date a = new Date();
		marca.setId(id);
		marca.setMarca("marca_1 - " + action.toString());
		marca.setFabricante("fabricante_2 - " + action.toString());
		marca.setEmailList(new ArrayList<Email>());
		marca.getEmailList().add(insertEmail(id, TabelaEnum.MARCA, action));
		marca.setEnderecoList(new ArrayList<Endereco>());
		marca.getEnderecoList().add(insertEndereco(id, TabelaEnum.MARCA, action));
		marca.setTelefoneList(new ArrayList<Telefone>());
		marca.getTelefoneList().add(insertTelefone(id, TabelaEnum.MARCA, action));
		marca.setTabelaEnum(tabela);
		marca.setParentId(id);
		marca.setEmprId(EMPID);
		marca.setModifyDateUTC(a.getTime());
		marca.setCreateDateUTC(a.getTime());
		marca.setCreateUser("system");
		marca.setModifyUser("system");
		marca.setProcessId(1);
		marca.setModelAction(action);

		return marca;
	}

	public static MarcaProduto insertMarcaProduto(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		MarcaProduto marcaproduto = new MarcaProduto();
		Date a = new Date();
		marcaproduto.setId(id);
		marcaproduto.setMarcaId(insertMarca(id, TabelaEnum.PRODUTO, action));
		marcaproduto.setTabelaEnum(tabela);
		marcaproduto.setParentId(id);
		marcaproduto.setEmprId(EMPID);
		marcaproduto.setModifyDateUTC(a.getTime());
		marcaproduto.setCreateDateUTC(a.getTime());
		marcaproduto.setCreateUser("system");
		marcaproduto.setModifyUser("system");
		marcaproduto.setProcessId(1);
		marcaproduto.setModelAction(action);

		return marcaproduto;
	}

	public static Grupo insertGrupo(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Grupo grupo = new Grupo();
		Date a = new Date();
		grupo.setId(id);
		grupo.setGrupo("grupo_1 - " + action.toString());
		grupo.setDescricao("descricao_2 - " + action.toString());
		grupo.setSubGrupo(insertSubGrupo(id, TabelaEnum.PRODUTO, action));
		grupo.setTabelaEnum(tabela);
		grupo.setParentId(id);
		grupo.setEmprId(EMPID);
		grupo.setModifyDateUTC(a.getTime());
		grupo.setCreateDateUTC(a.getTime());
		grupo.setCreateUser("system");
		grupo.setModifyUser("system");
		grupo.setProcessId(1);
		grupo.setModelAction(action);

		return grupo;
	}

	public static SubGrupo insertSubGrupo(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		SubGrupo subgrupo = new SubGrupo();
		Date a = new Date();
		subgrupo.setId(id);
		subgrupo.setSubGrupo("subGrupo_1 - " + action.toString());
		subgrupo.setDescricao("descricao_2 - " + action.toString());
		subgrupo.setTabelaEnum(tabela);
		subgrupo.setParentId(id);
		subgrupo.setEmprId(EMPID);
		subgrupo.setModifyDateUTC(a.getTime());
		subgrupo.setCreateDateUTC(a.getTime());
		subgrupo.setCreateUser("system");
		subgrupo.setModifyUser("system");
		subgrupo.setProcessId(1);
		subgrupo.setModelAction(action);

		return subgrupo;
	}

	public static UniMed insertUniMed(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		UniMed unimed = new UniMed();
		Date a = new Date();
		unimed.setId(id);
		unimed.setUnimed("unimed_1 - " + action.toString());
		unimed.setSigla("sigla_2 - " + action.toString());
		unimed.setTabelaEnum(tabela);
		unimed.setParentId(id);
		unimed.setEmprId(EMPID);
		unimed.setModifyDateUTC(a.getTime());
		unimed.setCreateDateUTC(a.getTime());
		unimed.setCreateUser("system");
		unimed.setModifyUser("system");
		unimed.setProcessId(1);
		unimed.setModelAction(action);

		return unimed;
	}

	public static Custo insertCustos(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Custo custo = new Custo();
		Date a = new Date();
		custo.setId(id);
		custo.setValor(new Double(10.00));
		custo.setCustoItens(new ArrayList<CustoItens>());
		custo.getCustoItens().add(insertCustoItens(id, TabelaEnum.CUSTO, action));
		custo.setTabelaEnum(tabela);
		custo.setParentId(id);
		custo.setEmprId(EMPID);
		custo.setModifyDateUTC(a.getTime());
		custo.setCreateDateUTC(a.getTime());
		custo.setCreateUser("system");
		custo.setModifyUser("system");
		custo.setProcessId(1);
		custo.setModelAction(action);

		return custo;
	}

	public static CustoItens insertCustoItens(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		CustoItens custoitens = new CustoItens();
		Date a = new Date();
		custoitens.setId(id);
		custoitens.setCustoDesp(1002);
		custoitens.setTabelaEnum(tabela);
		custoitens.setParentId(id);
		custoitens.setEmprId(EMPID);
		custoitens.setModifyDateUTC(a.getTime());
		custoitens.setCreateDateUTC(a.getTime());
		custoitens.setCreateUser("system");
		custoitens.setModifyUser("system");
		custoitens.setProcessId(1);
		custoitens.setModelAction(action);

		return custoitens;
	}

	public static Estoque insertEstoques(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Estoque estoque = new Estoque();
		Date a = new Date();
		estoque.setId(id);
		estoque.setEstoqueTypeEnumValue(1);
		estoque.setUltimoMov(a.getTime());
		estoque.setQuant(new Double(10.00));
		estoque.setTabelaEnum(tabela);
		estoque.setParentId(id);
		estoque.setEmprId(EMPID);
		estoque.setModifyDateUTC(a.getTime());
		estoque.setCreateDateUTC(a.getTime());
		estoque.setCreateUser("system");
		estoque.setModifyUser("system");
		estoque.setProcessId(1);
		estoque.setModelAction(action);

		return estoque;
	}

	public static Porcao insertPorcaos(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Porcao porcao = new Porcao();
		Date a = new Date();
		porcao.setId(id);
		porcao.setValor(new Double(10.00));
		porcao.setPorcaoItens(new ArrayList<PorcaoItens>());
		porcao.getPorcaoItens().add(insertPorcaoItens(id, TabelaEnum.PORCAO, action));
		porcao.setTabelaEnum(tabela);
		porcao.setParentId(id);
		porcao.setEmprId(EMPID);
		porcao.setModifyDateUTC(a.getTime());
		porcao.setCreateDateUTC(a.getTime());
		porcao.setCreateUser("system");
		porcao.setModifyUser("system");
		porcao.setProcessId(1);
		porcao.setModelAction(action);

		return porcao;
	}

	public static PorcaoItens insertPorcaoItens(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		PorcaoItens porcaoitens = new PorcaoItens();
		Date a = new Date();
		porcaoitens.setId(id);
		porcaoitens.setPorcao(new Double(10.00));
		porcaoitens.setVd(new Double(10.00));
		porcaoitens.setUnimed(insertUniMed(id, TabelaEnum.PORCAOITENS, action));
		porcaoitens.setNome("nome_4 - " + action.toString());
		porcaoitens.setTabelaEnum(tabela);
		porcaoitens.setParentId(id);
		porcaoitens.setEmprId(EMPID);
		porcaoitens.setModifyDateUTC(a.getTime());
		porcaoitens.setCreateDateUTC(a.getTime());
		porcaoitens.setCreateUser("system");
		porcaoitens.setModifyUser("system");
		porcaoitens.setProcessId(1);
		porcaoitens.setModelAction(action);

		return porcaoitens;
	}

	public static Rentabilidade insertRentabilidades(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Rentabilidade rentabilidade = new Rentabilidade();
		Date a = new Date();
		rentabilidade.setId(id);
		//rentabilidade.setRentabilidadeList(new ArrayList<Rentabilidade>());
	//	rentabilidade.getRentabilidadeList().add(insertRentabilidadeList(id, TabelaEnum.RENTABILIDADE, action));
		rentabilidade.setTabelaEnum(tabela);
		rentabilidade.setParentId(id);
		rentabilidade.setEmprId(EMPID);
		rentabilidade.setModifyDateUTC(a.getTime());
		rentabilidade.setCreateDateUTC(a.getTime());
		rentabilidade.setCreateUser("system");
		rentabilidade.setModifyUser("system");
		rentabilidade.setProcessId(1);
		rentabilidade.setModelAction(action);

		return rentabilidade;
	}

	public static RentabilidadeItens insertRentabilidadeItens(Integer id, TabelaEnum tabela,
			PersistenceActionEnum action) {
		RentabilidadeItens rentabilidadeitens = new RentabilidadeItens();
		Date a = new Date();
		rentabilidadeitens.setId(id);
		rentabilidadeitens.setProduto(1001);
		rentabilidadeitens.setValor(new Double(10.00));
		rentabilidadeitens.setRentabilidadeTypeEnumValue(1);
		rentabilidadeitens.setTabelaEnum(tabela);
		rentabilidadeitens.setParentId(id);
		rentabilidadeitens.setEmprId(EMPID);
		rentabilidadeitens.setModifyDateUTC(a.getTime());
		rentabilidadeitens.setCreateDateUTC(a.getTime());
		rentabilidadeitens.setCreateUser("system");
		rentabilidadeitens.setModifyUser("system");
		rentabilidadeitens.setProcessId(1);
		rentabilidadeitens.setModelAction(action);

		return rentabilidadeitens;
	}



	public static Servico insertServicos(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Servico servico = new Servico();
		Date a = new Date();
		servico.setId(id);
		servico.setNome("nome_1 - " + action.toString());
		servico.setDescricao("descricao_2 - " + action.toString());
		servico.setPreco(new ArrayList<Preco>());
		servico.getPreco().add(insertPreco(id, TabelaEnum.SERVICO, action));
		servico.setTabelaEnum(tabela);
		servico.setParentId(id);
		servico.setEmprId(EMPID);
		servico.setModifyDateUTC(a.getTime());
		servico.setCreateDateUTC(a.getTime());
		servico.setCreateUser("system");
		servico.setModifyUser("system");
		servico.setProcessId(1);
		servico.setModelAction(action);

		return servico;
	}

	public static PlanoByServico insertPlanoByServico(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		PlanoByServico planobyservico = new PlanoByServico();
		Date a = new Date();
		planobyservico.setId(id);
	//	planobyservico.setServico(insertServico(id, TabelaEnum.CUSTO, action));
		planobyservico.setTabelaEnum(tabela);
		planobyservico.setParentId(id);
		planobyservico.setEmprId(EMPID);
		planobyservico.setModifyDateUTC(a.getTime());
		planobyservico.setCreateDateUTC(a.getTime());
		planobyservico.setCreateUser("system");
		planobyservico.setModifyUser("system");
		planobyservico.setProcessId(1);
		planobyservico.setModelAction(action);

		return planobyservico;
	}

	public static Site insertSite(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Site site = new Site();
		Date a = new Date();
		site.setId(id);
		site.setNome("nome_1 - " + action.toString());
		site.setUrl("url_2 - " + action.toString());
		site.setQuemSomos("quemSomos_3 - " + action.toString());
		site.setMissao("missao_4 - " + action.toString());
		site.setVisao("visao_5 - " + action.toString());
		site.setTitulo("titulo_6 - " + action.toString());
		site.setLogo("logo_7 - " + action.toString());
		site.setAtorization(true);
		site.setSiteTypeEnumValue(1);
		site.setEmpresa(insertEmpresa(id, TabelaEnum.SITE, action));
		site.setServicoList(new ArrayList<Servico>());
		site.getServicoList().add(insertServico(id, TabelaEnum.SITE, action));
		site.setPlanoList(new ArrayList<Plano>());
		site.getPlanoList().add(insertPlano(id, TabelaEnum.SITE, action));
		site.setTabelaEnum(tabela);
		site.setParentId(id);
		site.setEmprId(EMPID);
		site.setModifyDateUTC(a.getTime());
		site.setCreateDateUTC(a.getTime());
		site.setCreateUser("system");
		site.setModifyUser("system");
		site.setProcessId(1);
		site.setModelAction(action);

		return site;
	}

	public static Contato insertContato(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Contato contato = new Contato();
		Date a = new Date();
		contato.setId(id);
		contato.setDataContato(a.getTime());
		contato.setNome("nome_2 - " + action.toString());
		contato.setMotivoValue(1003);

		contato.setTabelaEnum(tabela);
		contato.setParentId(id);
		contato.setEmprId(EMPID);
		contato.setModifyDateUTC(a.getTime());
		contato.setCreateDateUTC(a.getTime());
		contato.setCreateUser("system");
		contato.setModifyUser("system");
		contato.setProcessId(1);
		contato.setModelAction(action);

		return contato;
	}

	public static ContatoItens insertContatoItens(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ContatoItens contatoitens = new ContatoItens();
		Date a = new Date();
		contatoitens.setId(id);
		contatoitens.setDataAlt(a.getTime());
		contatoitens.setTexto("texto_2 - " + action.toString());
		contatoitens.setTitulo("titulo_3 - " + action.toString());
		contatoitens.setContatoStatusValue(1);
		contatoitens.setVisto(true);
		contatoitens.setTabelaEnum(tabela);
		contatoitens.setParentId(id);
		contatoitens.setEmprId(EMPID);
		contatoitens.setModifyDateUTC(a.getTime());
		contatoitens.setCreateDateUTC(a.getTime());
		contatoitens.setCreateUser("system");
		contatoitens.setModifyUser("system");
		contatoitens.setProcessId(1);
		contatoitens.setModelAction(action);

		return contatoitens;
	}

	public static OrdemServico insertOrdemServicos(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		OrdemServico ordemservico = new OrdemServico();
		Date a = new Date();
		ordemservico.setId(id);
		ordemservico.setUserId("userId_1 - " + action.toString());
		ordemservico.setNome("nome_2 - " + action.toString());
		ordemservico.setData(a.getTime());
		ordemservico.setAssunto("assunto_4 - " + action.toString());
		ordemservico.setStatusValue(1005);
		ordemservico.setOrdemServicoItensList(new ArrayList<OrdemServicoItens>());
		ordemservico.getOrdemServicoItensList().add(insertOrdemServicoItens(id, TabelaEnum.ORDEMSERVICO, action));
		ordemservico.setTabelaEnum(tabela);
		ordemservico.setParentId(id);
		ordemservico.setEmprId(EMPID);
		ordemservico.setModifyDateUTC(a.getTime());
		ordemservico.setCreateDateUTC(a.getTime());
		ordemservico.setCreateUser("system");
		ordemservico.setModifyUser("system");
		ordemservico.setProcessId(1);
		ordemservico.setModelAction(action);

		return ordemservico;
	}

	public static OrdemServicoItens insertOrdemServicoItenss(Integer id, TabelaEnum tabela,
			PersistenceActionEnum action) {
		OrdemServicoItens ordemservicoitens = new OrdemServicoItens();
		Date a = new Date();
		ordemservicoitens.setId(id);
		ordemservicoitens.setData(a.getTime());
		ordemservicoitens.setTexto("texto_2 - " + action.toString());
		ordemservicoitens.setTabelaEnum(tabela);
		ordemservicoitens.setParentId(id);
		ordemservicoitens.setEmprId(EMPID);
		ordemservicoitens.setModifyDateUTC(a.getTime());
		ordemservicoitens.setCreateDateUTC(a.getTime());
		ordemservicoitens.setCreateUser("system");
		ordemservicoitens.setModifyUser("system");
		ordemservicoitens.setProcessId(1);
		ordemservicoitens.setModelAction(action);

		return ordemservicoitens;
	}

	public static Plano insertPlano(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Plano plano = new Plano();
		Date a = new Date();
		plano.setId(id);
		plano.setDataInicio(a.getTime());
		plano.setDataFinal(a.getTime());
		plano.setNumeroContrato(1003);
		plano.setDescricao("descricao_4 - " + action.toString());
		plano.setTitulo("titulo_5 - " + action.toString());
		plano.setCor("cor_6 - " + action.toString());
		plano.setPrecoList(new ArrayList<Preco>());
		plano.getPrecoList().add(insertPreco(id, TabelaEnum.PLANO, action));
	//	plano.setServicoList(new ArrayList<PlanoByServico>());
	//	plano.getServicoList().add(insertPlanoByServico(id, TabelaEnum.PLANO, action));
		plano.setTabelaEnum(tabela);
		plano.setParentId(id);
		plano.setEmprId(EMPID);
		plano.setModifyDateUTC(a.getTime());
		plano.setCreateDateUTC(a.getTime());
		plano.setCreateUser("system");
		plano.setModifyUser("system");
		plano.setProcessId(1);
		plano.setModelAction(action);

		return plano;
	}

	public static ServicoAndPlano insertServicoAndPlano(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ServicoAndPlano servicoandplano = new ServicoAndPlano();
		Date a = new Date();
		servicoandplano.setId(id);
		servicoandplano.setDataInicio(a.getTime());
		servicoandplano.setValor(new Double(10.00));
		servicoandplano.setServicoPlanoEnumValue(1003);
		servicoandplano.setServicoList(insertServico(id, TabelaEnum.PLANO, action));
		servicoandplano.setPlanoList(insertPlano(id, TabelaEnum.PLANO, action));
		servicoandplano.setTabelaEnum(tabela);
		servicoandplano.setParentId(id);
		servicoandplano.setEmprId(EMPID);
		servicoandplano.setModifyDateUTC(a.getTime());
		servicoandplano.setCreateDateUTC(a.getTime());
		servicoandplano.setCreateUser("system");
		servicoandplano.setModifyUser("system");
		servicoandplano.setProcessId(1);
		servicoandplano.setModelAction(action);

		return servicoandplano;
	}


	public static NotaFiscalSaida insertNotaFiscalSaida(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		NotaFiscalSaida notafiscalsaida = new NotaFiscalSaida();
		Date a = new Date();
		notafiscalsaida.setId(id);
		notafiscalsaida.setTabelaEnum(tabela);
		notafiscalsaida.setParentId(id);
		notafiscalsaida.setEmprId(EMPID);
		notafiscalsaida.setModifyDateUTC(a.getTime());
		notafiscalsaida.setCreateDateUTC(a.getTime());
		notafiscalsaida.setCreateUser("system");
		notafiscalsaida.setModifyUser("system");
		notafiscalsaida.setProcessId(1);
		notafiscalsaida.setModelAction(action);

		return notafiscalsaida;
	}

	public static Orcamento insertOrcamento(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Orcamento orcamento = new Orcamento();
		Date a = new Date();
		orcamento.setId(id);
		orcamento.setTabelaEnum(tabela);
		orcamento.setParentId(id);
		orcamento.setEmprId(EMPID);
		orcamento.setModifyDateUTC(a.getTime());
		orcamento.setCreateDateUTC(a.getTime());
		orcamento.setCreateUser("system");
		orcamento.setModifyUser("system");
		orcamento.setProcessId(1);
		orcamento.setModelAction(action);

		return orcamento;
	}

	public static OrdemServico insertOrdemServicoss(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		OrdemServico ordemservico = new OrdemServico();
		Date a = new Date();
		ordemservico.setId(id);
		ordemservico.setUserId("userId_1 - " + action.toString());
		ordemservico.setNome("nome_2 - " + action.toString());
		ordemservico.setData(a.getTime());
		ordemservico.setAssunto("assunto_4 - " + action.toString());
		ordemservico.setStatusValue(1005);
		//ordemservico.setOrdemServicoItensList(new ArrayList<OrdemServicoItensList>());
		//ordemservico.getOrdemServicoItensList().add(insertOrdemServicoItensList(id, TabelaEnum.ORDEMSERVICO, action));
		ordemservico.setTabelaEnum(tabela);
		ordemservico.setParentId(id);
		ordemservico.setEmprId(EMPID);
		ordemservico.setModifyDateUTC(a.getTime());
		ordemservico.setCreateDateUTC(a.getTime());
		ordemservico.setCreateUser("system");
		ordemservico.setModifyUser("system");
		ordemservico.setProcessId(1);
		ordemservico.setModelAction(action);

		return ordemservico;
	}



	public static Endereco insertEndereco(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Endereco endereco = new Endereco();
		Date a = new Date();
		//endereco.setId(id);
		endereco.setCodIbge("555555");
		endereco.setLogradouro("logradouro_2 - " + action.toString());
		endereco.setBairro("bairro_3 - " + action.toString());
		endereco.setNumero("numero_4 - " + action.toString());
		endereco.setEnderecoTypeValue(1005);
		endereco.setCep("cep_6 - " + action.toString());
		endereco.setLatitude(new Double(10.10));
		endereco.setLongitude(new Double(10.10));
		endereco.setComplemento("complemento_9 - " + action.toString());
		endereco.setCidade(insertCidade(id, TabelaEnum.ENDERECO, action));
		endereco.setTabelaEnum(tabela);
		endereco.setParentId(id);
		endereco.setEmprId(EMPID);
		endereco.setModifyDateUTC(a.getTime());
		endereco.setCreateDateUTC(a.getTime());
		endereco.setCreateUser("system");
		endereco.setModifyUser("system");
		endereco.setProcessId(1);
		endereco.setModelAction(action);

		return endereco;
	}


	public static Email insertEmail(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Email email = new Email();
		Date a = new Date();
		//email.setId(id);
		email.setTypeValue(1001);
		email.setEmail("econtabilsistemas@gmail.com");
		email.setEmailTypeEnumValue(1003);
		email.setTabelaEnum(tabela);
		email.setParentId(id);
		email.setEmprId(EMPID);
		email.setModifyDateUTC(a.getTime());
		email.setCreateDateUTC(a.getTime());
		email.setCreateUser("system");
		email.setModifyUser("system");
		email.setProcessId(1);
		email.setModelAction(action);

		return email;
	}



	public static Telefone insertTelefone(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Telefone telefone = new Telefone();
		Date a = new Date();

		telefone.setTypeValue(1001);
		telefone.setDdd("ddd_2 - " + action.toString());
		telefone.setNumero("numero_3 - " + action.toString());
		telefone.setTelefoneTypeEnumValue(1004);
		telefone.setTabelaEnum(tabela);
		telefone.setParentId(id);
		telefone.setEmprId(EMPID);
		telefone.setModifyDateUTC(a.getTime());
		telefone.setCreateDateUTC(a.getTime());
		telefone.setCreateUser("system");
		telefone.setModifyUser("system");
		telefone.setProcessId(1);
		telefone.setModelAction(action);

		return telefone;
	}



	public static Arquivo insertArquivo(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Arquivo arquivo = new Arquivo();
		Date a = new Date();
		arquivo.setId(id);
		arquivo.setTabelaEnum(tabela);
		arquivo.setParentId(id);
		arquivo.setEmprId(EMPID);
		arquivo.setModifyDateUTC(a.getTime());
		arquivo.setCreateDateUTC(a.getTime());
		arquivo.setCreateUser("system");
		arquivo.setModifyUser("system");
		arquivo.setProcessId(1);
		arquivo.setModelAction(action);

		return arquivo;
	}



	public static Classificacao insertClassificacao(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Classificacao classificacao = new Classificacao();
		Date a = new Date();
		classificacao.setId(id);
		classificacao.setDescricao("descricao_1 - " + action.toString());
		classificacao.setCodigo("codigo_2 - " + action.toString());
		classificacao.setTabelaEnum(tabela);
		classificacao.setParentId(id);
		classificacao.setEmprId(EMPID);
		classificacao.setModifyDateUTC(a.getTime());
		classificacao.setCreateDateUTC(a.getTime());
		classificacao.setCreateUser("system");
		classificacao.setModifyUser("system");
		classificacao.setProcessId(1);
		classificacao.setModelAction(action);

		return classificacao;
	}



	public static Documento insertDocumento(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Documento documento = new Documento();
		Date a = new Date();
		documento.setDocumentoTypeEnumValue(1001);
		documento.setNumero("numero_2 - " + action.toString());
		documento.setData(a.getTime());
		documento.setTabelaEnum(tabela);
		documento.setParentId(id);
		documento.setEmprId(EMPID);
		documento.setModifyDateUTC(a.getTime());
		documento.setCreateDateUTC(a.getTime());
		documento.setCreateUser("system");
		documento.setModifyUser("system");
		documento.setProcessId(1);
		documento.setModelAction(action);

		return documento;
	}


//	public static HistoricoUtil insertHistoricoUtil(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
//		HistoricoUtil historicoutil = new HistoricoUtil();
//		Date a = new Date();
//		historicoutil.setId(id);
//		historicoutil.setDataMovimento(a.getTime());
//		historicoutil.setHistoricoUtilType(1002);
//		historicoutil.setQuantidade(1003);
//		historicoutil.setTabelaEnumValue(1004);
//		historicoutil.setTabelaEnum(tabela);
//		historicoutil.setParentId(id);
//		historicoutil.setEmprId(EMPID);
//		historicoutil.setModifyDateUTC(a.getTime());
//		historicoutil.setCreateDateUTC(a.getTime());
//		historicoutil.setCreateUser("system");
//		historicoutil.setModifyUser("system");
//		historicoutil.setProcessId(1);
//		historicoutil.setModelAction(action);
//
//		return historicoutil;
//	}

	public static Historico insertHistorico(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Historico historico = new Historico();
		Date a = new Date();
		historico.setId(id);
		historico.setData(a.getTime());
		historico.setUserId("User");
		historico.setAcaoEnumValue(1003);
		historico.setTabelaEnum(tabela);
		historico.setParentId(id);
		historico.setEmprId(EMPID);
		historico.setModifyDateUTC(a.getTime());
		historico.setCreateDateUTC(a.getTime());
		historico.setCreateUser("system");
		historico.setModifyUser("system");
		historico.setProcessId(1);
		historico.setModelAction(action);

		return historico;
	}

	public static HistoricoItens insertHistoricoItens(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		HistoricoItens historicoitens = new HistoricoItens();
		Date a = new Date();
		historicoitens.setId(id);
		historicoitens.setAcaoEnumValue(1001);
		historicoitens.setTabelaEnum(tabela);
		historicoitens.setParentId(id);
		historicoitens.setEmprId(EMPID);
		historicoitens.setModifyDateUTC(a.getTime());
		historicoitens.setCreateDateUTC(a.getTime());
		historicoitens.setCreateUser("system");
		historicoitens.setModifyUser("system");
		historicoitens.setProcessId(1);
		historicoitens.setModelAction(action);

		return historicoitens;
	}


	public static Note insertNotes(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Note notes = new Note();
		Date a = new Date();
		notes.setNoteText("noteText_1 - " + action.toString());
		notes.setTabelaEnum(tabela);
		notes.setParentId(id);
		notes.setEmprId(EMPID);
		notes.setModifyDateUTC(a.getTime());
		notes.setCreateDateUTC(a.getTime());
		notes.setCreateUser("system");
		notes.setModifyUser("system");
		notes.setProcessId(1);
		notes.setModelAction(action);

		return notes;
	}



	public static Socio insertSocio(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Socio socio = new Socio();
		Date a = new Date();
		socio.setId(id);
		socio.setPessoa(insertCliente(id, tabela, action));
		socio.setCota(new Double(10));
		socio.setPorcentagem("10.00");
		socio.setTabelaEnum(tabela);
		socio.setParentId(id);
		socio.setEmprId(EMPID);
		socio.setModifyDateUTC(a.getTime());
		socio.setCreateDateUTC(a.getTime());
		socio.setCreateUser("system");
		socio.setModifyUser("system");
		socio.setProcessId(1);
		socio.setModelAction(action);

		return socio;
	}


	public static OrdemServico insertOrdemServico(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		OrdemServico ordemservico = new OrdemServico();
		Date a = new Date();
		ordemservico.setId(id);
		ordemservico.setUserId("userId_1 - " + action.toString());
		ordemservico.setNome("nome_2 - " + action.toString());
		ordemservico.setData(a.getTime());
		ordemservico.setAssunto("assunto_4 - " + action.toString());
		ordemservico.setStatusValue(1005);
		ordemservico.setOrdemServicoItensList(new ArrayList<OrdemServicoItens>());
		ordemservico.getOrdemServicoItensList().add(insertOrdemServicoItens(id, TabelaEnum.ORDEMSERVICO, action));
		ordemservico.setTabelaEnum(tabela);
		ordemservico.setParentId(id);
		ordemservico.setEmprId(EMPID);
		ordemservico.setModifyDateUTC(a.getTime());
		ordemservico.setCreateDateUTC(a.getTime());
		ordemservico.setCreateUser("system");
		ordemservico.setModifyUser("system");
		ordemservico.setProcessId(1);
		ordemservico.setModelAction(action);

		return ordemservico;
	}

//	public static OrdemServicoType insertOrdemServicoType(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
//		OrdemServicoType ordemservicotype = new OrdemServicoType();
//		Date a = new Date();
//		ordemservicotype.setId(id);
//		ordemservicotype.setTabelaEnum(tabela);
//		ordemservicotype.setParentId(id);
//		ordemservicotype.setEmprId(EMPID);
//		ordemservicotype.setModifyDateUTC(a.getTime());
//		ordemservicotype.setCreateDateUTC(a.getTime());
//		ordemservicotype.setCreateUser("system");
//		ordemservicotype.setModifyUser("system");
//		ordemservicotype.setProcessId(1);
//		ordemservicotype.setModelAction(action);
//
//		return ordemservicotype;
//	}

	public static OrdemServicoStatus insertOrdemServicoStatus(Integer id, TabelaEnum tabela,
			PersistenceActionEnum action) {
		OrdemServicoStatus ordemservicostatus = new OrdemServicoStatus();
		Date a = new Date();
		ordemservicostatus.setId(id);
		ordemservicostatus.setNome("Nome");
		ordemservicostatus.setTabelaEnum(tabela);
		ordemservicostatus.setParentId(id);
		ordemservicostatus.setEmprId(EMPID);
		ordemservicostatus.setModifyDateUTC(a.getTime());
		ordemservicostatus.setCreateDateUTC(a.getTime());
		ordemservicostatus.setCreateUser("system");
		ordemservicostatus.setModifyUser("system");
		ordemservicostatus.setProcessId(1);
		ordemservicostatus.setModelAction(action);

		return ordemservicostatus;
	}

	public static OrdemServicoItens insertOrdemServicoItens(Integer id, TabelaEnum tabela,
			PersistenceActionEnum action) {
		OrdemServicoItens ordemservicoitens = new OrdemServicoItens();
		Date a = new Date();
		ordemservicoitens.setId(id);
		ordemservicoitens.setData(a.getTime());
		ordemservicoitens.setTexto("texto_2 - " + action.toString());
		ordemservicoitens.setTabelaEnum(tabela);
		ordemservicoitens.setParentId(id);
		ordemservicoitens.setEmprId(EMPID);
		ordemservicoitens.setModifyDateUTC(a.getTime());
		ordemservicoitens.setCreateDateUTC(a.getTime());
		ordemservicoitens.setCreateUser("system");
		ordemservicoitens.setModifyUser("system");
		ordemservicoitens.setProcessId(1);
		ordemservicoitens.setModelAction(action);

		return ordemservicoitens;
	}


	public static Status insertStatus(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Status status = new Status();
		Date a = new Date();
		status.setId(id);
		status.setDataStatus(a.getTime());
		status.setStatusValue(1002);
		status.setAcaoEnumValue(1003);
		status.setNote("note_4 - " + action.toString());
		status.setTabelaEnum(tabela);
		status.setParentId(id);
		status.setEmprId(EMPID);
		status.setModifyDateUTC(a.getTime());
		status.setCreateDateUTC(a.getTime());
		status.setCreateUser("system");
		status.setModifyUser("system");
		status.setProcessId(1);
		status.setModelAction(action);

		return status;
	}


	public static Tributacao insertTributacao(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {

	Tributacao tributacao = new Tributacao();
    Date a = new Date();
    tributacao.setId(id);
    tributacao.setProdId(1001);
    tributacao.setCfop(insertCfop(id, tabela, action));
    tributacao.setImposto(insertNFNotaInfoItemImposto(id, tabela, action));
    tributacao.setImpostoDevolvido(insertNFImpostoDevolvido(id, tabela, action));
    tributacao.setTabelaEnum(tabela);
    tributacao.setParentId(id);
    tributacao.setEmprId(EMPID);
    tributacao.setModifyDateUTC(a.getTime());
    tributacao.setCreateDateUTC(a.getTime());
    tributacao.setCreateUser("system");
    tributacao.setModifyUser("system");
    tributacao.setProcessId(1);
    tributacao.setModelAction(action);

    return tributacao;
}


public static Icms insertIcms(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
{
    Icms icms = new Icms();
    Date a = new Date();
    icms.setId(id);
    icms.setProdId(1001);
    icms.setSitTributaria(insertDoisValor(id, tabela, action));
    icms.setOrigem(insertDoisValor(id, tabela, action));
    icms.setModalidadeBC(insertDoisValor(id, tabela, action));
    icms.setRedBase( "redBase_5 - " + action.toString());
    icms.setAliqICMS( "aliqICMS_6 - " + action.toString());
    icms.setMotDesoneracao(insertDoisValor(id, tabela, action));
    icms.setTabelaEnum(tabela);
    icms.setParentId(id);
    icms.setEmprId(EMPID);
    icms.setModifyDateUTC(a.getTime());
    icms.setCreateDateUTC(a.getTime());
    icms.setCreateUser("system");
    icms.setModifyUser("system");
    icms.setProcessId(1);
    icms.setModelAction(action);

    return icms;
}


public static Pis insertPis(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
{
    Pis pis = new Pis();
    Date a = new Date();
    pis.setId(id);
    pis.setProdId(1001);
    pis.setpISSituaTributaria(insertDoisValor(id, tabela, action));
    pis.setValorUnidtribPIS(10.00);
    pis.setTipocalculoSubstTrib(insertDoisValor(id, tabela, action));
    pis.setValorTribPISST(10.00);
    pis.setTabelaEnum(tabela);
    pis.setParentId(id);
    pis.setEmprId(EMPID);
    pis.setModifyDateUTC(a.getTime());
    pis.setCreateDateUTC(a.getTime());
    pis.setCreateUser("system");
    pis.setModifyUser("system");
    pis.setProcessId(1);
    pis.setModelAction(action);

    return pis;
}


public static Ipi insertIpi(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
{
    Ipi ipi = new Ipi();
    Date a = new Date();
    ipi.setId(id);
    ipi.setProdId(1001);
    ipi.setSitTributaria(insertDoisValor(id, tabela, action));
    ipi.setClasseCigarrosBebidas( "classeCigarrosBebidas_3 - " + action.toString());
    ipi.setcNPJProdutor( "cNPJProdutor_4 - " + action.toString());
    ipi.setCodControleIPI( "codControleIPI_5 - " + action.toString());
    ipi.setQtdSeloIPI( "qtdSeloIPI_6 - " + action.toString());
    ipi.setCodEnquadramento(insertDoisValor(id, tabela, action));
    ipi.setTipoCalculo(insertDoisValor(id, tabela, action));
    ipi.setAliquotaIPI(10.00);
    ipi.setTabelaEnum(tabela);
    ipi.setParentId(id);
    ipi.setEmprId(EMPID);
    ipi.setModifyDateUTC(a.getTime());
    ipi.setCreateDateUTC(a.getTime());
    ipi.setCreateUser("system");
    ipi.setModifyUser("system");
    ipi.setProcessId(1);
    ipi.setModelAction(action);

    return ipi;
}


public static Cofins insertCofins(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
{
    Cofins cofins = new Cofins();
    Date a = new Date();
    cofins.setId(id);
    cofins.setProdId(1001);
    cofins.setSitTributaria(insertDoisValor(id, tabela, action));
    cofins.setValorTribCOFINS(10.00);
    cofins.setTipoCalculoSubstTrib(insertDoisValor(id, tabela, action));
    cofins.setAliquotaCOFINSST(10.00);
    cofins.setTabelaEnum(tabela);
    cofins.setParentId(id);
    cofins.setEmprId(EMPID);
    cofins.setModifyDateUTC(a.getTime());
    cofins.setCreateDateUTC(a.getTime());
    cofins.setCreateUser("system");
    cofins.setModifyUser("system");
    cofins.setProcessId(1);
    cofins.setModelAction(action);

    return cofins;
}



	public static Agencia insertAgencia(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Agencia agencia = new Agencia();
		Date a = new Date();
		agencia.setId(id);
		agencia.setNome("nome_1 - " + action.toString());
		agencia.setGerente("gerente_2 - " + action.toString());
		agencia.setResponsavelConta("responsavelConta_3 - " + action.toString());
		//agencia.setNumeroAgencia("numeroAgencia_4 - " + action.toString());
		agencia.setParentId(1005);
		agencia.setEnderecos(new ArrayList<Endereco>());
		agencia.getEnderecos().add(insertEndereco(id, TabelaEnum.AGENCIA, action));
		agencia.setEmails(new ArrayList<Email>());
		agencia.getEmails().add(insertEmail(id, TabelaEnum.AGENCIA, action));
		agencia.setTelefones(new ArrayList<Telefone>());
		agencia.getTelefones().add(insertTelefone(id, TabelaEnum.AGENCIA, action));
	//	agencia.setContaList(new ArrayList<Conta>());
	//	agencia.getContaList().add(insertConta(id, TabelaEnum.AGENCIA, action));
		agencia.setTabelaEnum(tabela);
		agencia.setParentId(id);
		agencia.setEmprId(EMPID);
		agencia.setModifyDateUTC(a.getTime());
		agencia.setCreateDateUTC(a.getTime());
		agencia.setCreateUser("system");
		agencia.setModifyUser("system");
		agencia.setProcessId(1);
		agencia.setModelAction(action);

		return agencia;
	}


	public static Profissao insertProfissao(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Profissao profissao = new Profissao();
		Date a = new Date();
		profissao.setId(id);
		profissao.setTabelaEnum(tabela);
		profissao.setParentId(id);
		profissao.setEmprId(EMPID);
		profissao.setModifyDateUTC(a.getTime());
		profissao.setCreateDateUTC(a.getTime());
		profissao.setCreateUser("system");
		profissao.setModifyUser("system");
		profissao.setProcessId(1);
		profissao.setModelAction(action);

		return profissao;
	}

	public static Salario insertSalario(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Salario salario = new Salario();
		Date a = new Date();
		salario.setId(id);
		salario.setTabelaEnum(tabela);
		salario.setParentId(id);
		salario.setEmprId(EMPID);
		salario.setModifyDateUTC(a.getTime());
		salario.setCreateDateUTC(a.getTime());
		salario.setCreateUser("system");
		salario.setModifyUser("system");
		salario.setProcessId(1);
		salario.setModelAction(action);

		return salario;
	}

	public static Custo insertCusto(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Custo custo = new Custo();
		Date a = new Date();
		custo.setId(id);
		custo.setValor(new Double(10.00));
		custo.setCustoItens(new ArrayList<CustoItens>());
		custo.getCustoItens().add(insertCustoItens(id, TabelaEnum.CUSTO, action));
		custo.setTabelaEnum(tabela);
		custo.setParentId(id);
		custo.setEmprId(EMPID);
		custo.setModifyDateUTC(a.getTime());
		custo.setCreateDateUTC(a.getTime());
		custo.setCreateUser("system");
		custo.setModifyUser("system");
		custo.setProcessId(1);
		custo.setModelAction(action);

		return custo;
	}


	public static Estoque insertEstoque(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Estoque estoque = new Estoque();
		Date a = new Date();
		estoque.setId(id);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.ATUAL);
		estoque.setUltimoMov(a.getTime());
		estoque.setQuant(new Double(10.00));
		estoque.setTabelaEnum(tabela);
		estoque.setParentId(id);
		estoque.setEmprId(EMPID);
		estoque.setModifyDateUTC(a.getTime());
		estoque.setCreateDateUTC(a.getTime());
		estoque.setCreateUser("system");
		estoque.setModifyUser("system");
		estoque.setProcessId(1);
		estoque.setModelAction(action);

		return estoque;
	}

	public static Porcao insertPorcao(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Porcao porcao = new Porcao();
		Date a = new Date();
		porcao.setId(id);
		porcao.setValor(new Double(10.00));
		porcao.setPorcaoItens(new ArrayList<PorcaoItens>());
		porcao.getPorcaoItens().add(insertPorcaoItens(id, TabelaEnum.PORCAO, action));
		porcao.setTabelaEnum(tabela);
		porcao.setParentId(id);
		porcao.setEmprId(EMPID);
		porcao.setModifyDateUTC(a.getTime());
		porcao.setCreateDateUTC(a.getTime());
		porcao.setCreateUser("system");
		porcao.setModifyUser("system");
		porcao.setProcessId(1);
		porcao.setModelAction(action);

		return porcao;
	}


	public static Preco insertPreco(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Preco preco = new Preco();
		Date a = new Date();
		preco.setDataMarcacao(a.getTime());
		preco.setPrecoTypeEnum(PrecoTypeEnum.COMPRA);
		preco.setValor(new Double(10.00));
		preco.setDataProInicial(a.getTime());
		preco.setDataProFinal(a.getTime());
		preco.setTabelaEnum(tabela);
		preco.setParentId(id);
		preco.setEmprId(EMPID);
		preco.setModifyDateUTC(a.getTime());
		preco.setCreateDateUTC(a.getTime());
		preco.setCreateUser("system");
		preco.setModifyUser("system");
		preco.setProcessId(1);
		preco.setModelAction(action);

		return preco;
	}


	public static DoisValores insertDoisValor(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		DoisValores doisvalor = new DoisValores();
		Date a = new Date();
		doisvalor.setId(id);
		doisvalor.setNome("nome_1 - " + action.toString());
		doisvalor.setDescricao("descricao_2 - " + action.toString());
		doisvalor.setDoisValorType(new DoisValorType(1));
		doisvalor.setTabelaEnum(tabela);
		doisvalor.setParentId(id);
		doisvalor.setEmprId(EMPID);
		doisvalor.setModifyDateUTC(a.getTime());
		doisvalor.setCreateDateUTC(a.getTime());
		doisvalor.setCreateUser("system");
		doisvalor.setModifyUser("system");
		doisvalor.setProcessId(1);
		doisvalor.setModelAction(action);

		return doisvalor;
	}


	public static Configuracao insertConfiguracao(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Configuracao configuracao = new Configuracao();
		Date a = new Date();
		configuracao.setId(id);
		configuracao.setConfGeral(insertConfigGeral(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setConfNFe(insertConfiguracaoNFe(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setConfFiscal(insertConfigFiscal(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setConfProd(insertConfigProduto(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setConfVendas(insertConfigVendas(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setConfCMTP(insertConfigSMTP(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setConfEntrada(insertConfigEntrada(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setConfCarne(insertConfigCarne(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setBoletoList(new ArrayList<Boleto>());
		configuracao.getBoletoList().add(insertBoleto(id, TabelaEnum.CONFIGURACAO, action));
		configuracao.setTabelaEnum(tabela);
		configuracao.setParentId(id);
		configuracao.setEmprId(EMPID);
		configuracao.setModifyDateUTC(a.getTime());
		configuracao.setCreateDateUTC(a.getTime());
		configuracao.setCreateUser("system");
		configuracao.setModifyUser("system");
		configuracao.setProcessId(1);
		configuracao.setModelAction(action);

		return configuracao;
	}

	public static Boleto insertBoleto(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Boleto boleto = new Boleto();
		Date a = new Date();
		boleto.setId(id);
		boleto.setAtivarBolOnLine(1001);
		boleto.setTipoBoleto(insertDoisValor(id, TabelaEnum.BOLETO, action));
		boleto.setAgencia(1003);
		boleto.setCedente(1004);
		boleto.setJuros(new Double(10.00));
		boleto.setTipoCalcMora(insertDoisValor(id, TabelaEnum.BOLETO, action));
		boleto.setMora(new Double(10.00));
		boleto.setInstrucoes("instrucoes_8 - " + action.toString());
		boleto.setDemonstrativo("demonstrativo_9 - " + action.toString());
		boleto.setImpJuros(1010);
		boleto.setTabelaEnum(tabela);
		boleto.setParentId(id);
		boleto.setEmprId(EMPID);
		boleto.setModifyDateUTC(a.getTime());
		boleto.setCreateDateUTC(a.getTime());
		boleto.setCreateUser("system");
		boleto.setModifyUser("system");
		boleto.setProcessId(1);
		boleto.setModelAction(action);

		return boleto;
	}

	public static ConfigCarne insertConfigCarne(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigCarne configcarne = new ConfigCarne();
		Date a = new Date();
		configcarne.setId(id);
		configcarne.setCarneBotelo(1001);
		configcarne.setCarneNormal(1002);
		configcarne.setTabelaEnum(tabela);
		configcarne.setParentId(id);
		configcarne.setEmprId(EMPID);
		configcarne.setModifyDateUTC(a.getTime());
		configcarne.setCreateDateUTC(a.getTime());
		configcarne.setCreateUser("system");
		configcarne.setModifyUser("system");
		configcarne.setProcessId(1);
		configcarne.setModelAction(action);

		return configcarne;
	}

	public static ConfigEntrada insertConfigEntrada(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigEntrada configentrada = new ConfigEntrada();
		Date a = new Date();
		configentrada.setId(id);
		configentrada.setValorTotalFixo(1001);
		configentrada.setManterPrecoVendaProd(1002);
		configentrada.setTabelaEnum(tabela);
		configentrada.setParentId(id);
		configentrada.setEmprId(EMPID);
		configentrada.setModifyDateUTC(a.getTime());
		configentrada.setCreateDateUTC(a.getTime());
		configentrada.setCreateUser("system");
		configentrada.setModifyUser("system");
		configentrada.setProcessId(1);
		configentrada.setModelAction(action);

		return configentrada;
	}

	public static ConfigFiscal insertConfigFiscal(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigFiscal configfiscal = new ConfigFiscal();
		Date a = new Date();
		configfiscal.setId(id);
		//configfiscal.setPrincAtividade(insertDoisValor(id, TabelaEnum.CONFIGFISCAL, action));
		configfiscal.setRegime(insertRegime(id, TabelaEnum.BOLETO, action));
		configfiscal.setAliqSimples(new Double(10.00));
		configfiscal.setTabelaEnum(tabela);
		configfiscal.setParentId(id);
		configfiscal.setEmprId(EMPID);
		configfiscal.setModifyDateUTC(a.getTime());
		configfiscal.setCreateDateUTC(a.getTime());
		configfiscal.setCreateUser("system");
		configfiscal.setModifyUser("system");
		configfiscal.setProcessId(1);
		configfiscal.setModelAction(action);

		return configfiscal;
	}

	public static ConfigAlertas insertConfigAlertas(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigAlertas configalertas = new ConfigAlertas();
		Date a = new Date();
		configalertas.setId(id);
		configalertas.setEstoqMin(1001);
		configalertas.setEstoqMax(1002);
		configalertas.setErroNFe(1003);
		configalertas.setPdCompra(1004);
		configalertas.setTabelaEnum(tabela);
		configalertas.setParentId(id);
		configalertas.setEmprId(EMPID);
		configalertas.setModifyDateUTC(a.getTime());
		configalertas.setCreateDateUTC(a.getTime());
		configalertas.setCreateUser("system");
		configalertas.setModifyUser("system");
		configalertas.setProcessId(1);
		configalertas.setModelAction(action);

		return configalertas;
	}

	public static ConfigGeral insertConfigGeral(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigGeral configgeral = new ConfigGeral();
		Date a = new Date();
		configgeral.setId(id);
		configgeral.setFusoHorario(1001);
		configgeral.setCasasDecimais(1002);
		configgeral.setDiasCartaCobr(1003);
		configgeral.setInfPosicionarMouse(1004);
		configgeral.setCnpjCPFUnico(1005);
		configgeral.setImpCodPersonalizado(1006);
		configgeral.setLogListRelImp(1007);
		configgeral.setObsProdFinProd(1008);
		configgeral.setTabelaEnum(tabela);
		configgeral.setParentId(id);
		configgeral.setEmprId(EMPID);
		configgeral.setModifyDateUTC(a.getTime());
		configgeral.setCreateDateUTC(a.getTime());
		configgeral.setCreateUser("system");
		configgeral.setModifyUser("system");
		configgeral.setProcessId(1);
		configgeral.setModelAction(action);

		return configgeral;
	}

	public static ConfigProduto insertConfigProduto(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigProduto configproduto = new ConfigProduto();
		Date a = new Date();
		configproduto.setId(id);
		configproduto.setCfop(insertCfop(id, TabelaEnum.BOLETO, action));
		configproduto.setIcmsSitTrib(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setIcmsOrigem(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setIcmsModalidadeBC(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setIcmsRedBaseCalc(new Double(10.00));
		configproduto.setIcmsAliq(new Double(10.00));
		configproduto.setIcmsMotDesoneracao(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setIcmsModBCST(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setIcmsMargValAdic(new Double(10.00));
		configproduto.setIcmsRedBaseCalcST(new Double(10.00));
		configproduto.setIcmsPrecoUnitPautaST(new Double(10.00));
		configproduto.setIcmsAliqST(new Double(10.00));
		configproduto.setIpiSitTrib(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setIpiClasCigarroBebida(new Double(10.00));
		configproduto.setIpiCNPJProd("ipiCNPJProd_15 - " + action.toString());
		configproduto.setIpiCodSeloCont("ipiCodSeloCont_16 - " + action.toString());
		configproduto.setIpiQtdSelo(new Double(10.00));
		configproduto.setIpiCodEnquad(1018);
		configproduto.setIpiTipCalc(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setIpiAliq(new Double(10.00));
		configproduto.setPisSitTrib(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setPisAliq(new Double(10.00));
		configproduto.setPisValUnidtrib(new Double(10.00));
		configproduto.setPistipoCalcSubstTrib(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setPisAliqST(new Double(10.00));
		configproduto.setPisValorAliqST(new Double(10.00));
		configproduto.setCofinsSubstTrib(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setCofinsAliq(new Double(10.00));
		configproduto.setCofinsValorAliq(new Double(10.00));
		configproduto.setCofinsTipoCalcSubstTrib(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configproduto.setCofinsAliqST(new Double(10.00));
		configproduto.setCofinsValorAliqST(new Double(10.00));
		configproduto.setTabelaEnum(tabela);
		configproduto.setParentId(id);
		configproduto.setEmprId(EMPID);
		configproduto.setModifyDateUTC(a.getTime());
		configproduto.setCreateDateUTC(a.getTime());
		configproduto.setCreateUser("system");
		configproduto.setModifyUser("system");
		configproduto.setProcessId(1);
		configproduto.setModelAction(action);

		return configproduto;
	}

	public static ConfigSMTP insertConfigSMTP(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigSMTP configsmtp = new ConfigSMTP();
		Date a = new Date();
		configsmtp.setId(id);
		configsmtp.setServSMTP("smtp.gmail.com");
		configsmtp.setPorta("587");
		configsmtp.setEndEmail("wlclimaco@gmail.com");
		configsmtp.setUsuario("wlclimaco@gmail.com");
		configsmtp.setSenha("gugubravo");
		configsmtp.setSeguranca(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configsmtp.setTabelaEnum(tabela);
		configsmtp.setParentId(id);
		configsmtp.setEmprId(EMPID);
		configsmtp.setModifyDateUTC(a.getTime());
		configsmtp.setCreateDateUTC(a.getTime());
		configsmtp.setCreateUser("system");
		configsmtp.setModifyUser("system");
		configsmtp.setProcessId(1);
		configsmtp.setModelAction(action);

		return configsmtp;
	}

	public static ConfiguracaoNFe insertConfiguracaoNFe(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfiguracaoNFe configuracaonfe = new ConfiguracaoNFe();
		Date a = new Date();
		configuracaonfe.setId(id);
		configuracaonfe.setPresCompr(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configuracaonfe.setDestConsFinal(1002);
		configuracaonfe.setPreencherDataHora(1003);
		configuracaonfe.setIcmsPadrao(new Double(10.00));
		configuracaonfe.setIpiPadrao(new Double(10.00));
		configuracaonfe.setPisPadrao(new Double(10.00));
		configuracaonfe.setCofinsPadrao(new Double(10.00));
		configuracaonfe.setAmbienteEnvio(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configuracaonfe.setServMsmNota(insertDoisValor(id, TabelaEnum.BOLETO, action));
		configuracaonfe.setSerieEnvio("serieEnvio_10 - " + action.toString());
		configuracaonfe.setAnexarXmlEmail(1011);
		configuracaonfe.setIdCSC("idCSC_12 - " + action.toString());
		configuracaonfe.setcSC("cSC_13 - " + action.toString());
		configuracaonfe.setInformacaoAdd("informacaoAdd_14 - " + action.toString());
		configuracaonfe.setCertificado("certificado_15 - " + action.toString());
		configuracaonfe.setSenha("senha_16 - " + action.toString());
		configuracaonfe.setSalvarSenha(1017);
		configuracaonfe.setCfopPadrao(insertCfop(id, TabelaEnum.BOLETO, action));
	//	configuracaonfe.setConfSMTP(insertConfigSMTP(id, TabelaEnum.BOLETO, action));
		configuracaonfe.setTabelaEnum(tabela);
		configuracaonfe.setParentId(id);
		configuracaonfe.setEmprId(EMPID);
		configuracaonfe.setModifyDateUTC(a.getTime());
		configuracaonfe.setCreateDateUTC(a.getTime());
		configuracaonfe.setCreateUser("system");
		configuracaonfe.setModifyUser("system");
		configuracaonfe.setProcessId(1);
		configuracaonfe.setModelAction(action);

		return configuracaonfe;
	}

	public static ConfigVendas insertConfigVendas(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		ConfigVendas configvendas = new ConfigVendas();
		Date a = new Date();
		configvendas.setId(id);
		configvendas.setDescontoMaxVenda(new Double(10.00));
		configvendas.setObservacao("observacao_2 - " + action.toString());
		configvendas.setImprSegVia(1003);
		configvendas.setImprAssinatura(1004);
		configvendas.setImprResumoFinanc(1005);
		configvendas.setAtuaPrecoClonar(1006);
		configvendas.setImprColUnidade(1007);
		configvendas.setBloquearvendProdSemEstoq(1008);
		configvendas.setAddDespCalcImposto(1009);
		configvendas.setRetSubstTribICMS(1010);
		configvendas.setTabelaEnum(tabela);
		configvendas.setParentId(id);
		configvendas.setEmprId(EMPID);
		configvendas.setModifyDateUTC(a.getTime());
		configvendas.setCreateDateUTC(a.getTime());
		configvendas.setCreateUser("system");
		configvendas.setModifyUser("system");
		configvendas.setProcessId(1);
		configvendas.setModelAction(action);

		return configvendas;
	}


	public static Rentabilidade insertRentabilidade(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Rentabilidade rentabilidade = new Rentabilidade();
		Date a = new Date();
		rentabilidade.setId(id);
	//	rentabilidade.setRentabilidadeList(new ArrayList<RentabilidadeList>());
	//	rentabilidade.getRentabilidadeList().add(insertRentabilidadeList(id, TabelaEnum.RENTABILIDADE, action));
		rentabilidade.setTabelaEnum(tabela);
		rentabilidade.setParentId(id);
		rentabilidade.setEmprId(EMPID);
		rentabilidade.setModifyDateUTC(a.getTime());
		rentabilidade.setCreateDateUTC(a.getTime());
		rentabilidade.setCreateUser("system");
		rentabilidade.setModifyUser("system");
		rentabilidade.setProcessId(1);
		rentabilidade.setModelAction(action);

		return rentabilidade;
	}


	public static Servico insertServico(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Servico servico = new Servico();
		Date a = new Date();
		servico.setId(id);
		servico.setNome("nome_1 - " + action.toString());
		servico.setDescricao("descricao_2 - " + action.toString());
		servico.setPreco(new ArrayList<Preco>());
		servico.getPreco().add(insertPreco(id, TabelaEnum.SERVICO, action));
		servico.setTabelaEnum(tabela);
		servico.setParentId(id);
		servico.setEmprId(EMPID);
		servico.setModifyDateUTC(a.getTime());
		servico.setCreateDateUTC(a.getTime());
		servico.setCreateUser("system");
		servico.setModifyUser("system");
		servico.setProcessId(1);
		servico.setModelAction(action);

		return servico;
	}


	public static Classes insertClasses(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
		Classes classes = new Classes();
		Date a = new Date();
		classes.setId(id);
		classes.setNome("nome_1 - " + action.toString());
		classes.setTabelaEnum(tabela);
		classes.setParentId(id);
		classes.setEmprId(EMPID);
		classes.setModifyDateUTC(a.getTime());
		classes.setCreateDateUTC(a.getTime());
		classes.setCreateUser("system");
		classes.setModifyUser("system");
		classes.setProcessId(1);
		classes.setModelAction(action);

		return classes;
	}

	public static Interface insertInterface(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	     {
	         Interface interfaces = new Interface();
	         Date a = new Date();
	         interfaces.setId(id);
	         interfaces.setNome( "nome_1 - " + action.toString());
	         interfaces.setLocal( "local_2 - " + action.toString());
	         interfaces.setTabelaEnum(tabela);
	         interfaces.setParentId(id);
	         interfaces.setEmprId(EMPID);
	         interfaces.setModifyDateUTC(a.getTime());
	         interfaces.setCreateDateUTC(a.getTime());
	         interfaces.setCreateUser("system");
	         interfaces.setModifyUser("system");
	         interfaces.setProcessId(1);
	         interfaces.setModelAction(action);

	         return interfaces;
	     }


	public static ProdutoEmpresa insertProdutoEmpresa(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ProdutoEmpresa ProdutoEmpresa = new ProdutoEmpresa();
			Date a = new Date();
			ProdutoEmpresa.setId(id);
			ProdutoEmpresa.setParentId(id);
			ProdutoEmpresa.setEmprId(100);
			ProdutoEmpresa.setInformAdicionaisParaNFe("NATIVE INSERT UPDATE");
			ProdutoEmpresa.setTributacao(insertTributacao(id, tabela, action));
			ProdutoEmpresa.setEstoqueList(new ArrayList<Estoque>());
			ProdutoEmpresa.getEstoqueList().add(insertEstoque(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoEmpresa.setPrecoList(new ArrayList<Preco>());
			ProdutoEmpresa.getPrecoList().add(insertPreco(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoEmpresa.setCustoList(new ArrayList<Custo>());
			ProdutoEmpresa.getCustoList().add(insertCusto(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoEmpresa.setPorcaoList(new ArrayList<Porcao>());
			ProdutoEmpresa.getPorcaoList().add(insertPorcao(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoEmpresa.setRentabilidadeList(new ArrayList<Rentabilidade>());
			ProdutoEmpresa.getRentabilidadeList().add(insertRentabilidade(id,TabelaEnum.PRODUTOPARENT,action));


			ProdutoEmpresa.setParentId(id);
			ProdutoEmpresa.setEmprId(1);
			ProdutoEmpresa.setModifyDateUTC(a.getTime());
			ProdutoEmpresa.setCreateDateUTC(a.getTime());
			ProdutoEmpresa.setCreateUser("system");
			ProdutoEmpresa.setModifyUser("system");
			ProdutoEmpresa.setProcessId(1);
			ProdutoEmpresa.setModelAction(action);

			return ProdutoEmpresa;
		}








	public static MarcaProduto insertMarcaProd(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			MarcaProduto marcaproduto = new MarcaProduto();
			Date a = new Date();
			marcaproduto.setId(id);
			marcaproduto.setParentId(id);
			marcaproduto.setMarcaId(insertMarca(id, tabela, action));
			marcaproduto.setParentId(id);
			marcaproduto.setEmprId(1);
			marcaproduto.setModifyDateUTC(a.getTime());
			marcaproduto.setCreateDateUTC(a.getTime());
			marcaproduto.setCreateUser("system");
			marcaproduto.setModifyUser("system");
			marcaproduto.setProcessId(1);
			marcaproduto.setModelAction(action);

			return marcaproduto;
		}

	public static Transaction insertTransaction(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Transaction transaction = new Transaction();
		Date a = new Date();

		transaction.setId(id);
		transaction.setInicioSession(a.getTime());
		transaction.setFinalSession(a.getTime());
		transaction.setUserId("system");
		transaction.setEmprId(1);
		transaction.setModifyDateUTC(a.getTime());
		transaction.setCreateDateUTC(a.getTime());
		transaction.setCreateUser("system");
		transaction.setModifyUser("system");
		transaction.setProcessId(1);
		transaction.setModelAction(action);

		return transaction;
	}








	private static ExamePessoa insertExamePessoa(Integer id, TabelaEnum consulta, PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}

	private static PlanoSaudePessoa insertPlanoSaude(Integer id, TabelaEnum consulta, PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}
	private static CondPagPessoa insertCondPagPessoa(Integer id, TabelaEnum advocacia, PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}

	private static FormaPgPessoa insertFormaPgPessoa(Integer id, TabelaEnum advocacia, PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}

	private static BancoPessoa insertBancoPessoa(Integer id, TabelaEnum advocacia, PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}

	private static TipoPag insertTipoPag(Integer id, TabelaEnum condpag, PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}

	private static HistoricoMovimento insertHistoricoMovimento(Integer id, TabelaEnum contacorrente,
			PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}

	private static CfopParentId insertCfopParentId(Integer id, TabelaEnum produtoparent, PersistenceActionEnum action) {
		// TODO Auto-generated method stub
		return null;
	}




	public static UserRoles insertUserRoles(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		UserRoles userroles = new UserRoles();
		Date a = new Date();
		userroles.setUser_role_id(id);
		userroles.setUsername("NATIVE INSERT UPDATE");
		userroles.setRole(Objects.insertRole(id, tabela, action));
		userroles.setParentId(id);
		userroles.setEmprId(1);
		userroles.setModifyDateUTC(a.getTime());
		userroles.setCreateDateUTC(a.getTime());
		userroles.setCreateUser("system");
		userroles.setModifyUser("system");
		userroles.setProcessId(1);
		userroles.setModelAction(action);

		return userroles;
	}


public static Role insertRole(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Role role = new Role();
		Date a = new Date();
		role.setId(id);
		role.setRole("NATIVE INSERT UPDATE");
		role.setStatus(100);
		role.setPagina(insertPagina(id, tabela, action));
		role.setParentId(id);
		role.setEmprId(1);
		role.setModifyDateUTC(a.getTime());
		role.setCreateDateUTC(a.getTime());
		role.setCreateUser("system");
		role.setModifyUser("system");
		role.setProcessId(1);
		role.setModelAction(action);

		return role;
	}


public static Pagina insertPagina(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Pagina pagina = new Pagina();
		Date a = new Date();
		pagina.setId(id);
		pagina.setPagina("NATIVE INSERT UPDATE");
		pagina.setStatus(100);
		//pagina.setMenu(insertMenu(id, tabela, action));
		//pagina.setHelp(insertAjuda(id, tabela, action));
		//pagina.setFilds(insertField(id, tabela, action));
		pagina.setParentId(id);
		pagina.setEmprId(1);
		pagina.setModifyDateUTC(a.getTime());
		pagina.setCreateDateUTC(a.getTime());
		pagina.setCreateUser("system");
		pagina.setModifyUser("system");
		pagina.setProcessId(1);
		pagina.setModelAction(action);

		return pagina;
	}


public static Validacao insertValidacao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Validacao validacao = new Validacao();
		Date a = new Date();
		validacao.setId(id);
		validacao.setError("NATIVE INSERT UPDATE");
		validacao.setTipo(insertDoisValor(id, tabela, action));
		validacao.setParentId(id);
		validacao.setEmprId(1);
		validacao.setModifyDateUTC(a.getTime());
		validacao.setCreateDateUTC(a.getTime());
		validacao.setCreateUser("system");
		validacao.setModifyUser("system");
		validacao.setProcessId(1);
		validacao.setModelAction(action);

		return validacao;
	}


public static Field insertField(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Field field = new Field();
		Date a = new Date();
		field.setId(id);
		field.setNome("NATIVE INSERT UPDATE");
		field.setStatus(100);
		field.setObrigatorio(100);
		field.setCampoBanco("NATIVE INSERT UPDATE");
		field.setTabelaBanco("NATIVE INSERT UPDATE");
		field.setTipo(insertDoisValor(id, tabela, action));
		field.setLabel("NATIVE INSERT UPDATE");
		field.setTootip("NATIVE INSERT UPDATE");
		field.setHelp(insertAjuda(id, tabela, action));
		field.setValidacao(new ArrayList<Validacao>());
		field.getValidacao().add(insertValidacao(id, tabela, action));
	//	field.setRole(new ArrayList<Role>());
	//	field.getRole().add(insertRole(id, tabela, action));
		field.setParentId(id);
		field.setEmprId(1);
		field.setModifyDateUTC(a.getTime());
		field.setCreateDateUTC(a.getTime());
		field.setCreateUser("system");
		field.setModifyUser("system");
		field.setProcessId(1);
		field.setModelAction(action);

		return field;
	}


public static Ajuda insertAjuda(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Ajuda ajuda = new Ajuda();
		Date a = new Date();
		ajuda.setId(id);
		ajuda.setTitulo("NATIVE INSERT UPDATE");
		ajuda.setStatus(100);
		ajuda.setTexto("NATIVE INSERT UPDATE");
		ajuda.setParentId(id);
		ajuda.setEmprId(1);
		ajuda.setModifyDateUTC(a.getTime());
		ajuda.setCreateDateUTC(a.getTime());
		ajuda.setCreateUser("system");
		ajuda.setModifyUser("system");
		ajuda.setProcessId(1);
		ajuda.setModelAction(action);

		return ajuda;
	}


public static Menu insertMenu(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Menu menu = new Menu();
		Date a = new Date();
		menu.setId(id);
		menu.setNome("NATIVE INSERT UPDATE");
		menu.setLabel("NATIVE INSERT UPDATE");
		menu.setStatus(100);
		menu.setNivel(100);
		//menu.setPermissao(insertRole(id, tabela, action));
		//menu.setHelp(insertAjuda(id, tabela, action));
		menu.setParentId(id);
		menu.setEmprId(1);
		menu.setModifyDateUTC(a.getTime());
		menu.setCreateDateUTC(a.getTime());
		menu.setCreateUser("system");
		menu.setModifyUser("system");
		menu.setProcessId(1);
		menu.setModelAction(action);

		return menu;
	}
public static NotaFiscalItens insertNotaFiscalItens(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
{
	NotaFiscalItens notafiscalitens = new NotaFiscalItens();
	Date a = new Date();
	notafiscalitens.setId(id);
	notafiscalitens.setDataInicio(a.getTime());
	notafiscalitens.setValor(new Double(10.00));
	notafiscalitens.setServicoPlanoEnumValue(100);
//	notafiscalitens.setServicoList(10000);
//	notafiscalitens.setPlanoList(10000);
	notafiscalitens.setNumeroRegistro("NATIVE INSERT UPDATE");
	notafiscalitens.setDataRegistro(a.getTime());
	notafiscalitens.setLocalDesembaraco("NATIVE INSERT UPDATE");
//		notafiscalitens.setUfDesembaraco(new Estado("MG"));
	notafiscalitens.setDataDesembaraco(a.getTime());
	notafiscalitens.setTransporteInternacional(100);
	notafiscalitens.setValorAFRMM(new Double(10.00));
	notafiscalitens.setTpIntermedio(100);
	notafiscalitens.setCnpj("NATIVE INSERT UPDATE");
	notafiscalitens.setUfTerceiro(100);
	notafiscalitens.setCodigoExportador(100);
	notafiscalitens.setDescricao("NATIVE INSERT UPDATE");
//	notafiscalitens.setProduto(10000);
//	notafiscalitens.setCfop(10000);
	notafiscalitens.setServicoList(insertServico(id, tabela, action));
	notafiscalitens.setValorUnitario(new Double(10.00));
	notafiscalitens.setValorTotalBruto(new Double(10.00));
	notafiscalitens.setValorFrete(new Double(10.00));
	notafiscalitens.setValorSeguro(new Double(10.00));
	notafiscalitens.setValorDesconto(new Double(10.00));
	notafiscalitens.setValorOutrasDespesasAcessorias(new Double(10.00));
	notafiscalitens.setNumeroRECOPI(100);
	notafiscalitens.setParentId(id);
	notafiscalitens.setEmprId(1);
	notafiscalitens.setModifyDateUTC(a.getTime());
	notafiscalitens.setCreateDateUTC(a.getTime());
	notafiscalitens.setCreateUser("system");
	notafiscalitens.setModifyUser("system");
	notafiscalitens.setProcessId(1);
	notafiscalitens.setModelAction(action);

	return notafiscalitens;
}



public static ServicoAndPlano insertServicoByPlano(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {

	ServicoAndPlano notafiscalitens = new ServicoAndPlano();
	Date a = new Date();
	notafiscalitens.setId(id);

	notafiscalitens.setParentId(id);
	notafiscalitens.setEmprId(1);
	notafiscalitens.setValor(new Double(1.99));
	notafiscalitens.setModifyDateUTC(a.getTime());
	notafiscalitens.setCreateDateUTC(a.getTime());
	notafiscalitens.setCreateUser("system");
	notafiscalitens.setModifyUser("system");
	notafiscalitens.setProcessId(1);
	notafiscalitens.setModelAction(action);

	return notafiscalitens;
}


public static NFNota insertNFNota(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNota nfnota = new NFNota();
		Date a = new Date();
		nfnota.setId(id);
		nfnota.setIdentificadorLocal(a.getTime());
		nfnota.setInfo(insertNFNotaInfo(id,tabela,action));
		nfnota.setInfoSuplementar(insertNFNotaInfoSuplementar(id,tabela,action));
		nfnota.setAssinatura(Objects.insertDoisValor(id, tabela, action));
		nfnota.setParentId(id);
		nfnota.setEmprId(1);
		nfnota.setModifyDateUTC(a.getTime());
		nfnota.setCreateDateUTC(a.getTime());
		nfnota.setCreateUser("system");
		nfnota.setModifyUser("system");
		nfnota.setProcessId(1);
		nfnota.setModelAction(action);

		return nfnota;
	}


public static NFNotaInfo insertNFNotaInfo(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfo nfnotainfo = new NFNotaInfo();
		Date a = new Date();
		nfnotainfo.setId(id);
		nfnotainfo.setIdentificador("NATIVE INSERT UPDATE");
		nfnotainfo.setVersao("NATIVE INSERT UPDATE");
		nfnotainfo.setIdentificacao(insertNFNotaInfoIdentificacao(null, tabela, action));
		nfnotainfo.setEmitente(insertNFNotaInfoEmitente(null, tabela, action));
		nfnotainfo.setAvulsa(insertNFNotaInfoAvulsa(null, tabela, action));
		nfnotainfo.setDestinatario(insertNFNotaInfoDestinatario(null, tabela, action));
		nfnotainfo.setRetirada(insertNFNotaInfoLocal(null, tabela, action));
		nfnotainfo.setEntrega(insertNFNotaInfoLocal(null, tabela, action));
		nfnotainfo.setPessoasautorizadasdownloadnfe(new ArrayList<NFPessoaAutorizadaDownloadNFe>());
		nfnotainfo.getPessoasautorizadasdownloadnfe().add(insertNFPessoaAutorizadaDownloadNFe(null, tabela, action));
		nfnotainfo.setItens(new ArrayList<NFNotaInfoItem>());
		nfnotainfo.getItens().add(Objects.insertNFNotaInfoItem(null, tabela, action));
		nfnotainfo.setTotal(insertNFNotaInfoTotal(null, tabela, action));
		nfnotainfo.setTransporte(insertNFNotaInfoTransporte(null, tabela, action));
		nfnotainfo.setCobranca(insertNFNotaInfoCobranca(null, tabela, action));
		nfnotainfo.setPagamentos(new ArrayList<NFNotaInfoPagamento>());
		nfnotainfo.getPagamentos().add(insertNFNotaInfoPagamento(null, tabela, action));
		nfnotainfo.setInformacoesadicionais(insertNFNotaInfoInformacoesAdicionais(null, tabela, action));
		nfnotainfo.setExportacao(insertNFNotaInfoExportacao(null, tabela, action));
		nfnotainfo.setCompra(insertNFNotaInfoCompra(null, tabela, action));
		nfnotainfo.setCana(insertNFNotaInfoCana(null, tabela, action));
		nfnotainfo.setParentId(id);
		nfnotainfo.setEmprId(1);
		nfnotainfo.setModifyDateUTC(a.getTime());
		nfnotainfo.setCreateDateUTC(a.getTime());
		nfnotainfo.setCreateUser("system");
		nfnotainfo.setModifyUser("system");
		nfnotainfo.setProcessId(1);
		nfnotainfo.setModelAction(action);

		return nfnotainfo;
	}


public static NFNotaInfoIdentificacao insertNFNotaInfoIdentificacao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoIdentificacao nfnotainfoidentificacao = new NFNotaInfoIdentificacao();
		Date a = new Date();
		nfnotainfoidentificacao.setId(id);
		nfnotainfoidentificacao.setUf(Objects.insertEstado(null, tabela, action));
		nfnotainfoidentificacao.setCodigoRandomico("NATIVE INSERT UPDATE");
		nfnotainfoidentificacao.setNaturezaOperacao("NATIVE INSERT UPDATE");
	//	nfnotainfoidentificacao.setFormaPagamento(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setModelo(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setSerie(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setNumeroNota("NATIVE INSERT UPDATE");
		nfnotainfoidentificacao.setDataHoraEmissao(a.getTime());
		nfnotainfoidentificacao.setDataHoraSaidaOuEntrada(a.getTime());
		nfnotainfoidentificacao.setTipo(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setIdentificadorLocalDestinoOperacao(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setCodigoMunicipio("NATIVE INSERT UPDATE");
		nfnotainfoidentificacao.setTipoImpressao(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setTipoEmissao(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setDigitoVerificador(100);
		nfnotainfoidentificacao.setAmbiente(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setFinalidade(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setOperacaoConsumidorFinal(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setIndicadorPresencaComprador(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setProgramaEmissor(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoidentificacao.setVersaoEmissor("NATIVE INSERT UPDATE");
		nfnotainfoidentificacao.setDataHoraContigencia(a.getTime());
		nfnotainfoidentificacao.setJustificativaEntradaContingencia("NATIVE INSERT UPDATE");
		nfnotainfoidentificacao.setReferenciadas(new ArrayList<NFInfoReferenciada>());
		nfnotainfoidentificacao.getReferenciadas().add(insertNFInfoReferenciada(null, tabela, action));
		nfnotainfoidentificacao.setParentId(id);
		nfnotainfoidentificacao.setEmprId(1);
		nfnotainfoidentificacao.setModifyDateUTC(a.getTime());
		nfnotainfoidentificacao.setCreateDateUTC(a.getTime());
		nfnotainfoidentificacao.setCreateUser("system");
		nfnotainfoidentificacao.setModifyUser("system");
		nfnotainfoidentificacao.setProcessId(1);
		nfnotainfoidentificacao.setModelAction(action);

		return nfnotainfoidentificacao;
	}


public static NFInfoModelo1Por1AReferenciada insertNFInfoModelo1Por1AReferenciada(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada = new NFInfoModelo1Por1AReferenciada();
		Date a = new Date();
		nfinfomodelo1por1areferenciada.setId(id);
		nfinfomodelo1por1areferenciada.setUf(Objects.insertEstado(id, tabela, action));
		nfinfomodelo1por1areferenciada.setAnoMesEmissaoNFe("NATIVE INSERT UPDATE");
		nfinfomodelo1por1areferenciada.setCnpj("NATIVE INSERT UPDATE");
		nfinfomodelo1por1areferenciada.setModeloDocumentoFiscal("NATIVE INSERT UPDATE");
		nfinfomodelo1por1areferenciada.setSerie("NATIVE INSERT UPDATE");
		nfinfomodelo1por1areferenciada.setNumeroDocumentoFiscal("NATIVE INSERT UPDATE");
		nfinfomodelo1por1areferenciada.setParentId(id);
		nfinfomodelo1por1areferenciada.setEmprId(1);
		nfinfomodelo1por1areferenciada.setModifyDateUTC(a.getTime());
		nfinfomodelo1por1areferenciada.setCreateDateUTC(a.getTime());
		nfinfomodelo1por1areferenciada.setCreateUser("system");
		nfinfomodelo1por1areferenciada.setModifyUser("system");
		nfinfomodelo1por1areferenciada.setProcessId(1);
		nfinfomodelo1por1areferenciada.setModelAction(action);

		return nfinfomodelo1por1areferenciada;
	}


public static NFInfoReferenciada insertNFInfoReferenciada(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFInfoReferenciada nfinforeferenciada = new NFInfoReferenciada();
		Date a = new Date();
		nfinforeferenciada.setId(id);
		nfinforeferenciada.setChaveAcesso("NATIVE INSERT UPDATE");
		nfinforeferenciada.setModelo1por1Referenciada(100);
		nfinforeferenciada.setInfoNFProdutorRuralReferenciada(insertNFInfoProdutorRuralReferenciada(null, tabela, action));
		nfinforeferenciada.setChaveAcessoCTReferenciada("NATIVE INSERT UPDATE");
		//nfinforeferenciada.setCupomFiscalReferenciado(insertnfinfof);
		nfinforeferenciada.setParentId(id);
		nfinforeferenciada.setEmprId(1);
		nfinforeferenciada.setModifyDateUTC(a.getTime());
		nfinforeferenciada.setCreateDateUTC(a.getTime());
		nfinforeferenciada.setCreateUser("system");
		nfinforeferenciada.setModifyUser("system");
		nfinforeferenciada.setProcessId(1);
		nfinforeferenciada.setModelAction(action);

		return nfinforeferenciada;
	}


public static NFInfoProdutorRuralReferenciada insertNFInfoProdutorRuralReferenciada(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada = new NFInfoProdutorRuralReferenciada();
		Date a = new Date();
		nfinfoprodutorruralreferenciada.setId(id);
		nfinfoprodutorruralreferenciada.setUfEmitente(Objects.insertEstado(id, tabela, action));
		nfinfoprodutorruralreferenciada.setAnoMesEmissao("NATIVE INSERT UPDATE");
		nfinfoprodutorruralreferenciada.setCnpjEmitente("NATIVE INSERT UPDATE");
		nfinfoprodutorruralreferenciada.setCpfEmitente("NATIVE INSERT UPDATE");
		nfinfoprodutorruralreferenciada.setIeEmitente("NATIVE INSERT UPDATE");
		nfinfoprodutorruralreferenciada.setModeloDocumentoFiscal("NATIVE INSERT UPDATE");
		nfinfoprodutorruralreferenciada.setSerieDocumentoFiscal(100);
		nfinfoprodutorruralreferenciada.setNumeroDocumentoFiscal(100);
		nfinfoprodutorruralreferenciada.setParentId(id);
		nfinfoprodutorruralreferenciada.setEmprId(1);
		nfinfoprodutorruralreferenciada.setModifyDateUTC(a.getTime());
		nfinfoprodutorruralreferenciada.setCreateDateUTC(a.getTime());
		nfinfoprodutorruralreferenciada.setCreateUser("system");
		nfinfoprodutorruralreferenciada.setModifyUser("system");
		nfinfoprodutorruralreferenciada.setProcessId(1);
		nfinfoprodutorruralreferenciada.setModelAction(action);

		return nfinfoprodutorruralreferenciada;
	}


public static NFNotaInfoEmitente insertNFNotaInfoEmitente(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoEmitente nfnotainfoemitente = new NFNotaInfoEmitente();
		Date a = new Date();
		nfnotainfoemitente.setId(id);
		nfnotainfoemitente.setCnpj("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setCpf("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setRazaoSocial("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setNomeFantasia("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setEndereco(Objects.insertEndereco(id, tabela, action));
		nfnotainfoemitente.setInscricaoEstadual("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setInscricaoEstadualSubstituicaoTributaria("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setInscricaoMunicipal("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setClassificacaoNacionalAtividadesEconomicas("NATIVE INSERT UPDATE");
		nfnotainfoemitente.setRegimeTributario(Objects.insertRegime(id, tabela, action));
		nfnotainfoemitente.setParentId(id);
		nfnotainfoemitente.setEmprId(1);
		nfnotainfoemitente.setModifyDateUTC(a.getTime());
		nfnotainfoemitente.setCreateDateUTC(a.getTime());
		nfnotainfoemitente.setCreateUser("system");
		nfnotainfoemitente.setModifyUser("system");
		nfnotainfoemitente.setProcessId(1);
		nfnotainfoemitente.setModelAction(action);

		return nfnotainfoemitente;
	}


public static NFNotaInfoAvulsa insertNFNotaInfoAvulsa(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoAvulsa nfnotainfoavulsa = new NFNotaInfoAvulsa();
		Date a = new Date();
		nfnotainfoavulsa.setId(id);
		nfnotainfoavulsa.setCnpj("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setOrgaoEmitente("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setMatriculaAgente("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setNomeAgente("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setFone("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setUf("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setNumeroDocumentoArrecadacaoReceita("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setDataEmissaoDocumentoArrecadacao(a.getTime());
		nfnotainfoavulsa.setValorTotalConstanteDocumentoArrecadacaoReceita("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setReparticaoFiscalEmitente("NATIVE INSERT UPDATE");
		nfnotainfoavulsa.setDataPagamentoDocumentoArrecadacao(a.getTime());
		nfnotainfoavulsa.setParentId(id);
		nfnotainfoavulsa.setEmprId(1);
		nfnotainfoavulsa.setModifyDateUTC(a.getTime());
		nfnotainfoavulsa.setCreateDateUTC(a.getTime());
		nfnotainfoavulsa.setCreateUser("system");
		nfnotainfoavulsa.setModifyUser("system");
		nfnotainfoavulsa.setProcessId(1);
		nfnotainfoavulsa.setModelAction(action);

		return nfnotainfoavulsa;
	}


public static NFNotaInfoDestinatario insertNFNotaInfoDestinatario(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoDestinatario nfnotainfodestinatario = new NFNotaInfoDestinatario();
		Date a = new Date();
		nfnotainfodestinatario.setId(id);
		nfnotainfodestinatario.setCnpj("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setCpf("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setIdEstrangeiro("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setRazaoSocial("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setEndereco(Objects.insertEndereco(id, tabela, action));
		nfnotainfodestinatario.setIndicadorIEDestinatario(Objects.insertDoisValor(id, tabela, action));
		nfnotainfodestinatario.setInscricaoEstadual("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setInscricaoSuframa("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setInscricaoMunicipal("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setEmail("NATIVE INSERT UPDATE");
		nfnotainfodestinatario.setParentId(id);
		nfnotainfodestinatario.setEmprId(1);
		nfnotainfodestinatario.setModifyDateUTC(a.getTime());
		nfnotainfodestinatario.setCreateDateUTC(a.getTime());
		nfnotainfodestinatario.setCreateUser("system");
		nfnotainfodestinatario.setModifyUser("system");
		nfnotainfodestinatario.setProcessId(1);
		nfnotainfodestinatario.setModelAction(action);

		return nfnotainfodestinatario;
	}


public static NFNotaInfoLocal insertNFNotaInfoLocal(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoLocal nfnotainfolocal = new NFNotaInfoLocal();
		Date a = new Date();
		nfnotainfolocal.setId(id);
		nfnotainfolocal.setCnpj("NATIVE INSERT UPDATE");
		nfnotainfolocal.setCpf("NATIVE INSERT UPDATE");
		nfnotainfolocal.setLogradouro("NATIVE INSERT UPDATE");
		nfnotainfolocal.setNumero("NATIVE INSERT UPDATE");
		nfnotainfolocal.setComplemento("NATIVE INSERT UPDATE");
		nfnotainfolocal.setBairro("NATIVE INSERT UPDATE");
		nfnotainfolocal.setCodigoMunicipio("NATIVE INSERT UPDATE");
		nfnotainfolocal.setNomeMunicipio("NATIVE INSERT UPDATE");
		nfnotainfolocal.setUf("NATIVE INSERT UPDATE");
		nfnotainfolocal.setParentId(id);
		nfnotainfolocal.setEmprId(1);
		nfnotainfolocal.setModifyDateUTC(a.getTime());
		nfnotainfolocal.setCreateDateUTC(a.getTime());
		nfnotainfolocal.setCreateUser("system");
		nfnotainfolocal.setModifyUser("system");
		nfnotainfolocal.setProcessId(1);
		nfnotainfolocal.setModelAction(action);

		return nfnotainfolocal;
	}


public static NFPessoaAutorizadaDownloadNFe insertNFPessoaAutorizadaDownloadNFe(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe = new NFPessoaAutorizadaDownloadNFe();
		Date a = new Date();
		nfpessoaautorizadadownloadnfe.setId(id);
		nfpessoaautorizadadownloadnfe.setCnpj("NATIVE INSERT UPDATE");
		nfpessoaautorizadadownloadnfe.setCpf("NATIVE INSERT UPDATE");
		nfpessoaautorizadadownloadnfe.setParentId(id);
		nfpessoaautorizadadownloadnfe.setEmprId(1);
		nfpessoaautorizadadownloadnfe.setModifyDateUTC(a.getTime());
		nfpessoaautorizadadownloadnfe.setCreateDateUTC(a.getTime());
		nfpessoaautorizadadownloadnfe.setCreateUser("system");
		nfpessoaautorizadadownloadnfe.setModifyUser("system");
		nfpessoaautorizadadownloadnfe.setProcessId(1);
		nfpessoaautorizadadownloadnfe.setModelAction(action);

		return nfpessoaautorizadadownloadnfe;
	}


public static NFNotaInfoTotal insertNFNotaInfoTotal(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoTotal nfnotainfototal = new NFNotaInfoTotal();
		Date a = new Date();
		nfnotainfototal.setId(id);
		nfnotainfototal.setIcmsTotal(insertNFNotaInfoICMSTotal(null, tabela, action));
		nfnotainfototal.setIssqnTotal(insertNFNotaInfoISSQNTotal(null, tabela, action));
		nfnotainfototal.setRetencoesTributos(insertNFNotaInfoRetencoesTributos(null, tabela, action));
		nfnotainfototal.setParentId(id);
		nfnotainfototal.setEmprId(1);
		nfnotainfototal.setModifyDateUTC(a.getTime());
		nfnotainfototal.setCreateDateUTC(a.getTime());
		nfnotainfototal.setCreateUser("system");
		nfnotainfototal.setModifyUser("system");
		nfnotainfototal.setProcessId(1);
		nfnotainfototal.setModelAction(action);

		return nfnotainfototal;
	}


public static NFNotaInfoICMSTotal insertNFNotaInfoICMSTotal(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoICMSTotal nfnotainfoicmstotal = new NFNotaInfoICMSTotal();
		Date a = new Date();
		nfnotainfoicmstotal.setId(id);
		nfnotainfoicmstotal.setBaseCalculoICMS("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalICMS("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorICMSDesonerado("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorICMSFundoCombatePobreza("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorICMSPartilhaDestinatario("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorICMSPartilhaRementente("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setBaseCalculoICMSST("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalICMSST("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalDosProdutosServicos("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalFrete("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalSeguro("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalDesconto("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalII("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalIPI("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorPIS("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorCOFINS("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setOutrasDespesasAcessorias("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalNFe("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setValorTotalTributos("NATIVE INSERT UPDATE");
		nfnotainfoicmstotal.setParentId(id);
		nfnotainfoicmstotal.setEmprId(1);
		nfnotainfoicmstotal.setModifyDateUTC(a.getTime());
		nfnotainfoicmstotal.setCreateDateUTC(a.getTime());
		nfnotainfoicmstotal.setCreateUser("system");
		nfnotainfoicmstotal.setModifyUser("system");
		nfnotainfoicmstotal.setProcessId(1);
		nfnotainfoicmstotal.setModelAction(action);

		return nfnotainfoicmstotal;
	}


public static NFNotaInfoISSQNTotal insertNFNotaInfoISSQNTotal(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoISSQNTotal nfnotainfoissqntotal = new NFNotaInfoISSQNTotal();
		Date a = new Date();
		nfnotainfoissqntotal.setId(id);
		nfnotainfoissqntotal.setValorTotalServicosSobNaoIncidenciaNaoTributadosICMS("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setBaseCalculoISS("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setValorTotalISS("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setValorPISsobreServicos("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setValorCOFINSsobreServicos("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setDataPrestacaoServico(a.getTime());
		nfnotainfoissqntotal.setValorDeducao("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setValorOutros("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setValorTotalDescontoIncondicionado("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setValorTotalDescontoCondicionado("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setValorTotalRetencaoISS("NATIVE INSERT UPDATE");
		nfnotainfoissqntotal.setTributacao(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoissqntotal.setParentId(id);
		nfnotainfoissqntotal.setEmprId(1);
		nfnotainfoissqntotal.setModifyDateUTC(a.getTime());
		nfnotainfoissqntotal.setCreateDateUTC(a.getTime());
		nfnotainfoissqntotal.setCreateUser("system");
		nfnotainfoissqntotal.setModifyUser("system");
		nfnotainfoissqntotal.setProcessId(1);
		nfnotainfoissqntotal.setModelAction(action);

		return nfnotainfoissqntotal;
	}


public static NFNotaInfoRetencoesTributos insertNFNotaInfoRetencoesTributos(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoRetencoesTributos nfnotainforetencoestributos = new NFNotaInfoRetencoesTributos();
		Date a = new Date();
		nfnotainforetencoestributos.setId(id);
		nfnotainforetencoestributos.setValorRetidoPIS("NATIVE INSERT UPDATE");
		nfnotainforetencoestributos.setValorRetidoCOFINS("NATIVE INSERT UPDATE");
		nfnotainforetencoestributos.setValorRetidoCSLL("NATIVE INSERT UPDATE");
		nfnotainforetencoestributos.setBaseCalculoIRRF("NATIVE INSERT UPDATE");
		nfnotainforetencoestributos.setValorRetidoIRRF("NATIVE INSERT UPDATE");
		nfnotainforetencoestributos.setBaseCalculoRetencaoPrevidenciaSocial("NATIVE INSERT UPDATE");
		nfnotainforetencoestributos.setValorRetencaoPrevidenciaSocial("NATIVE INSERT UPDATE");
		nfnotainforetencoestributos.setParentId(id);
		nfnotainforetencoestributos.setEmprId(1);
		nfnotainforetencoestributos.setModifyDateUTC(a.getTime());
		nfnotainforetencoestributos.setCreateDateUTC(a.getTime());
		nfnotainforetencoestributos.setCreateUser("system");
		nfnotainforetencoestributos.setModifyUser("system");
		nfnotainforetencoestributos.setProcessId(1);
		nfnotainforetencoestributos.setModelAction(action);

		return nfnotainforetencoestributos;
	}


public static NFNotaInfoTransporte insertNFNotaInfoTransporte(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoTransporte nfnotainfotransporte = new NFNotaInfoTransporte();
		Date a = new Date();
		nfnotainfotransporte.setId(id);
		nfnotainfotransporte.setModalidadeFrete(Objects.insertDoisValor(id, tabela, action));
		nfnotainfotransporte.setTransportador(insertNFNotaInfoTransportador(null, tabela, action));
		nfnotainfotransporte.setIcmsTransporte(insertNFNotaInfoRetencaoICMSTransporte(null, tabela, action));
		nfnotainfotransporte.setVeiculo(insertNFNotaInfoVeiculo(null, tabela, action));
		nfnotainfotransporte.setReboques(new ArrayList<NFNotaInfoReboque>());
		nfnotainfotransporte.getReboques().add(insertNFNotaInfoReboque(null, tabela, action));
		nfnotainfotransporte.setVagao("NATIVE INSERT UPDATE");
		nfnotainfotransporte.setBalsa("NATIVE INSERT UPDATE");
		nfnotainfotransporte.setParentId(id);
		nfnotainfotransporte.setEmprId(1);
		nfnotainfotransporte.setModifyDateUTC(a.getTime());
		nfnotainfotransporte.setCreateDateUTC(a.getTime());
		nfnotainfotransporte.setCreateUser("system");
		nfnotainfotransporte.setModifyUser("system");
		nfnotainfotransporte.setProcessId(1);
		nfnotainfotransporte.setModelAction(action);

		return nfnotainfotransporte;
	}


public static NFNotaInfoRetencaoICMSTransporte insertNFNotaInfoRetencaoICMSTransporte(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte = new NFNotaInfoRetencaoICMSTransporte();
		Date a = new Date();
		nfnotainforetencaoicmstransporte.setId(id);
		nfnotainforetencaoicmstransporte.setValorServico("NATIVE INSERT UPDATE");
		nfnotainforetencaoicmstransporte.setBcRetencaoICMS("NATIVE INSERT UPDATE");
		nfnotainforetencaoicmstransporte.setAliquotaRetencao("NATIVE INSERT UPDATE");
		nfnotainforetencaoicmstransporte.setValorICMSRetido("NATIVE INSERT UPDATE");
		nfnotainforetencaoicmstransporte.setCfop("NATIVE INSERT UPDATE");
		nfnotainforetencaoicmstransporte.setCodigoMunicipioOcorrenciaFatoGeradorICMSTransporte("NATIVE INSERT UPDATE");
		nfnotainforetencaoicmstransporte.setParentId(id);
		nfnotainforetencaoicmstransporte.setEmprId(1);
		nfnotainforetencaoicmstransporte.setModifyDateUTC(a.getTime());
		nfnotainforetencaoicmstransporte.setCreateDateUTC(a.getTime());
		nfnotainforetencaoicmstransporte.setCreateUser("system");
		nfnotainforetencaoicmstransporte.setModifyUser("system");
		nfnotainforetencaoicmstransporte.setProcessId(1);
		nfnotainforetencaoicmstransporte.setModelAction(action);

		return nfnotainforetencaoicmstransporte;
	}


public static NFNotaInfoTransportador insertNFNotaInfoTransportador(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoTransportador nfnotainfotransportador = new NFNotaInfoTransportador();
		Date a = new Date();
		nfnotainfotransportador.setId(id);
		nfnotainfotransportador.setCnpj("NATIVE INSERT UPDATE");
		nfnotainfotransportador.setCpf("NATIVE INSERT UPDATE");
		nfnotainfotransportador.setRazaosocial("NATIVE INSERT UPDATE");
		nfnotainfotransportador.setInscricaoestadual("NATIVE INSERT UPDATE");
		nfnotainfotransportador.setEnderecocomplemento("NATIVE INSERT UPDATE");
		nfnotainfotransportador.setNomemunicipio("NATIVE INSERT UPDATE");
		nfnotainfotransportador.setUf("NATIVE INSERT UPDATE");
		nfnotainfotransportador.setParentId(id);
		nfnotainfotransportador.setEmprId(1);
		nfnotainfotransportador.setModifyDateUTC(a.getTime());
		nfnotainfotransportador.setCreateDateUTC(a.getTime());
		nfnotainfotransportador.setCreateUser("system");
		nfnotainfotransportador.setModifyUser("system");
		nfnotainfotransportador.setProcessId(1);
		nfnotainfotransportador.setModelAction(action);

		return nfnotainfotransportador;
	}


public static NFNotaInfoVeiculo insertNFNotaInfoVeiculo(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoVeiculo nfnotainfoveiculo = new NFNotaInfoVeiculo();
		Date a = new Date();
		nfnotainfoveiculo.setId(id);
		nfnotainfoveiculo.setPlacaVeiculo("NATIVE INSERT UPDATE");
		nfnotainfoveiculo.setUf("NATIVE INSERT UPDATE");
		nfnotainfoveiculo.setRegistroNacionalTransportadorCarga("NATIVE INSERT UPDATE");
		nfnotainfoveiculo.setParentId(id);
		nfnotainfoveiculo.setEmprId(1);
		nfnotainfoveiculo.setModifyDateUTC(a.getTime());
		nfnotainfoveiculo.setCreateDateUTC(a.getTime());
		nfnotainfoveiculo.setCreateUser("system");
		nfnotainfoveiculo.setModifyUser("system");
		nfnotainfoveiculo.setProcessId(1);
		nfnotainfoveiculo.setModelAction(action);

		return nfnotainfoveiculo;
	}


public static NFNotaInfoReboque insertNFNotaInfoReboque(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoReboque nfnotainforeboque = new NFNotaInfoReboque();
		Date a = new Date();
		nfnotainforeboque.setId(id);
		nfnotainforeboque.setPlacaVeiculo("NATIVE INSERT UPDATE");
		nfnotainforeboque.setUf("NATIVE INSERT UPDATE");
		nfnotainforeboque.setRegistroNacionalTransportadorCarga("NATIVE INSERT UPDATE");
		nfnotainforeboque.setParentId(id);
		nfnotainforeboque.setEmprId(1);
		nfnotainforeboque.setModifyDateUTC(a.getTime());
		nfnotainforeboque.setCreateDateUTC(a.getTime());
		nfnotainforeboque.setCreateUser("system");
		nfnotainforeboque.setModifyUser("system");
		nfnotainforeboque.setProcessId(1);
		nfnotainforeboque.setModelAction(action);

		return nfnotainforeboque;
	}


public static NFNotaInfoCobranca insertNFNotaInfoCobranca(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoCobranca nfnotainfocobranca = new NFNotaInfoCobranca();
		Date a = new Date();
		nfnotainfocobranca.setId(id);
		nfnotainfocobranca.setFatura(insertNFNotaInfoFatura(null, tabela, action));
		nfnotainfocobranca.setDuplicatas(insertNFNotaInfoDuplicata(null, tabela, action));
		nfnotainfocobranca.setParentId(id);
		nfnotainfocobranca.setEmprId(1);
		nfnotainfocobranca.setModifyDateUTC(a.getTime());
		nfnotainfocobranca.setCreateDateUTC(a.getTime());
		nfnotainfocobranca.setCreateUser("system");
		nfnotainfocobranca.setModifyUser("system");
		nfnotainfocobranca.setProcessId(1);
		nfnotainfocobranca.setModelAction(action);

		return nfnotainfocobranca;
	}


public static NFNotaInfoDuplicata insertNFNotaInfoDuplicata(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoDuplicata nfnotainfoduplicata = new NFNotaInfoDuplicata();
		Date a = new Date();
		nfnotainfoduplicata.setId(id);
		nfnotainfoduplicata.setNumeroDuplicata("NATIVE INSERT UPDATE");
		nfnotainfoduplicata.setDataVencimento(a.getTime());
		nfnotainfoduplicata.setValorDuplicata("NATIVE INSERT UPDATE");
		nfnotainfoduplicata.setParentId(id);
		nfnotainfoduplicata.setEmprId(1);
		nfnotainfoduplicata.setModifyDateUTC(a.getTime());
		nfnotainfoduplicata.setCreateDateUTC(a.getTime());
		nfnotainfoduplicata.setCreateUser("system");
		nfnotainfoduplicata.setModifyUser("system");
		nfnotainfoduplicata.setProcessId(1);
		nfnotainfoduplicata.setModelAction(action);

		return nfnotainfoduplicata;
	}


public static NFNotaInfoFatura insertNFNotaInfoFatura(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoFatura nfnotainfofatura = new NFNotaInfoFatura();
		Date a = new Date();
		nfnotainfofatura.setId(id);
		nfnotainfofatura.setNumeroFatura("NATIVE INSERT UPDATE");
		nfnotainfofatura.setValorOriginalFatura("NATIVE INSERT UPDATE");
		nfnotainfofatura.setValorDesconto("NATIVE INSERT UPDATE");
		nfnotainfofatura.setValorLiquidoFatura("NATIVE INSERT UPDATE");
		nfnotainfofatura.setParentId(id);
		nfnotainfofatura.setEmprId(1);
		nfnotainfofatura.setModifyDateUTC(a.getTime());
		nfnotainfofatura.setCreateDateUTC(a.getTime());
		nfnotainfofatura.setCreateUser("system");
		nfnotainfofatura.setModifyUser("system");
		nfnotainfofatura.setProcessId(1);
		nfnotainfofatura.setModelAction(action);

		return nfnotainfofatura;
	}


public static NFNotaInfoCartao insertNFNotaInfoCartao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoCartao nfnotainfocartao = new NFNotaInfoCartao();
		Date a = new Date();
		nfnotainfocartao.setId(id);
		nfnotainfocartao.setTipoIntegracao(Objects.insertDoisValor(id, tabela, action));
		nfnotainfocartao.setCnpj("NATIVE INSERT UPDATE");
		nfnotainfocartao.setOperadoraCartao(Objects.insertDoisValor(id, tabela, action));
		nfnotainfocartao.setNumeroAutorizacaoOperacaoCartao("NATIVE INSERT UPDATE");
		nfnotainfocartao.setParentId(id);
		nfnotainfocartao.setEmprId(1);
		nfnotainfocartao.setModifyDateUTC(a.getTime());
		nfnotainfocartao.setCreateDateUTC(a.getTime());
		nfnotainfocartao.setCreateUser("system");
		nfnotainfocartao.setModifyUser("system");
		nfnotainfocartao.setProcessId(1);
		nfnotainfocartao.setModelAction(action);

		return nfnotainfocartao;
	}


public static NFNotaInfoPagamento insertNFNotaInfoPagamento(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoPagamento nfnotainfopagamento = new NFNotaInfoPagamento();
		Date a = new Date();
		nfnotainfopagamento.setId(id);
		nfnotainfopagamento.setFormaPagamentoMoeda(Objects.insertDoisValor(id, tabela, action));
		nfnotainfopagamento.setValorPagamento("NATIVE INSERT UPDATE");
		nfnotainfopagamento.setCartao(insertNFNotaInfoCartao(null, tabela, action));
		nfnotainfopagamento.setParentId(id);
		nfnotainfopagamento.setEmprId(1);
		nfnotainfopagamento.setModifyDateUTC(a.getTime());
		nfnotainfopagamento.setCreateDateUTC(a.getTime());
		nfnotainfopagamento.setCreateUser("system");
		nfnotainfopagamento.setModifyUser("system");
		nfnotainfopagamento.setProcessId(1);
		nfnotainfopagamento.setModelAction(action);

		return nfnotainfopagamento;
	}


public static NFNotaInfoInformacoesAdicionais insertNFNotaInfoInformacoesAdicionais(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais = new NFNotaInfoInformacoesAdicionais();
		Date a = new Date();
		nfnotainfoinformacoesadicionais.setId(id);
		nfnotainfoinformacoesadicionais.setInformacoesadicionaisinteressefisco("NATIVE INSERT UPDATE");
		nfnotainfoinformacoesadicionais.setInformacoescomplementaresinteressecontribuinte("NATIVE INSERT UPDATE");
		nfnotainfoinformacoesadicionais.setObservacoescontribuinte(new ArrayList<NFNotaInfoObservacao>());
		nfnotainfoinformacoesadicionais.getObservacoescontribuinte().add(insertNFNotaInfoObservacao(null, tabela, action));
		nfnotainfoinformacoesadicionais.setObservacoesfisco(new ArrayList<NFNotaInfoObservacao>());
		nfnotainfoinformacoesadicionais.getObservacoesfisco().add(insertNFNotaInfoObservacao(null, tabela, action));
		nfnotainfoinformacoesadicionais.setProcessosrefenciado(new ArrayList<NFNotaInfoProcessoReferenciado>());
		nfnotainfoinformacoesadicionais.getProcessosrefenciado().add(insertNFNotaInfoProcessoReferenciado(null, tabela, action));
		nfnotainfoinformacoesadicionais.setParentId(id);
		nfnotainfoinformacoesadicionais.setEmprId(1);
		nfnotainfoinformacoesadicionais.setModifyDateUTC(a.getTime());
		nfnotainfoinformacoesadicionais.setCreateDateUTC(a.getTime());
		nfnotainfoinformacoesadicionais.setCreateUser("system");
		nfnotainfoinformacoesadicionais.setModifyUser("system");
		nfnotainfoinformacoesadicionais.setProcessId(1);
		nfnotainfoinformacoesadicionais.setModelAction(action);

		return nfnotainfoinformacoesadicionais;
	}


public static NFNotaInfoObservacao insertNFNotaInfoObservacao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoObservacao nfnotainfoobservacao = new NFNotaInfoObservacao();
		Date a = new Date();
		nfnotainfoobservacao.setId(id);
		nfnotainfoobservacao.setIdentificacaoCampo("NATIVE INSERT UPDATE");
		nfnotainfoobservacao.setConteudoCampo("NATIVE INSERT UPDATE");
		nfnotainfoobservacao.setParentId(id);
		nfnotainfoobservacao.setEmprId(1);
		nfnotainfoobservacao.setModifyDateUTC(a.getTime());
		nfnotainfoobservacao.setCreateDateUTC(a.getTime());
		nfnotainfoobservacao.setCreateUser("system");
		nfnotainfoobservacao.setModifyUser("system");
		nfnotainfoobservacao.setProcessId(1);
		nfnotainfoobservacao.setModelAction(action);

		return nfnotainfoobservacao;
	}


public static NFNotaInfoProcessoReferenciado insertNFNotaInfoProcessoReferenciado(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado = new NFNotaInfoProcessoReferenciado();
		Date a = new Date();
		nfnotainfoprocessoreferenciado.setId(id);
		nfnotainfoprocessoreferenciado.setIdentificadorProcessoOuAtoConcessorio("NATIVE INSERT UPDATE");
		nfnotainfoprocessoreferenciado.setIndicadorOrigemProcesso(Objects.insertDoisValor(id, tabela, action));
		nfnotainfoprocessoreferenciado.setParentId(id);
		nfnotainfoprocessoreferenciado.setEmprId(1);
		nfnotainfoprocessoreferenciado.setModifyDateUTC(a.getTime());
		nfnotainfoprocessoreferenciado.setCreateDateUTC(a.getTime());
		nfnotainfoprocessoreferenciado.setCreateUser("system");
		nfnotainfoprocessoreferenciado.setModifyUser("system");
		nfnotainfoprocessoreferenciado.setProcessId(1);
		nfnotainfoprocessoreferenciado.setModelAction(action);

		return nfnotainfoprocessoreferenciado;
	}


public static NFNotaInfoExportacao insertNFNotaInfoExportacao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoExportacao nfnotainfoexportacao = new NFNotaInfoExportacao();
		Date a = new Date();
		nfnotainfoexportacao.setId(id);
		nfnotainfoexportacao.setUfEmbarqueProduto("NATIVE INSERT UPDATE");
		nfnotainfoexportacao.setLocalEmbarqueProdutos("NATIVE INSERT UPDATE");
		nfnotainfoexportacao.setLocalDespachoProdutos("NATIVE INSERT UPDATE");
		nfnotainfoexportacao.setParentId(id);
		nfnotainfoexportacao.setEmprId(1);
		nfnotainfoexportacao.setModifyDateUTC(a.getTime());
		nfnotainfoexportacao.setCreateDateUTC(a.getTime());
		nfnotainfoexportacao.setCreateUser("system");
		nfnotainfoexportacao.setModifyUser("system");
		nfnotainfoexportacao.setProcessId(1);
		nfnotainfoexportacao.setModelAction(action);

		return nfnotainfoexportacao;
	}


public static NFNotaInfoCompra insertNFNotaInfoCompra(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoCompra nfnotainfocompra = new NFNotaInfoCompra();
		Date a = new Date();
		nfnotainfocompra.setId(id);
		nfnotainfocompra.setNotaDeEmpenho("NATIVE INSERT UPDATE");
		nfnotainfocompra.setPedido("NATIVE INSERT UPDATE");
		nfnotainfocompra.setContrato("NATIVE INSERT UPDATE");
		nfnotainfocompra.setParentId(id);
		nfnotainfocompra.setEmprId(1);
		nfnotainfocompra.setModifyDateUTC(a.getTime());
		nfnotainfocompra.setCreateDateUTC(a.getTime());
		nfnotainfocompra.setCreateUser("system");
		nfnotainfocompra.setModifyUser("system");
		nfnotainfocompra.setProcessId(1);
		nfnotainfocompra.setModelAction(action);

		return nfnotainfocompra;
	}


public static NFNotaInfoCana insertNFNotaInfoCana(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoCana nfnotainfocana = new NFNotaInfoCana();
		Date a = new Date();
		nfnotainfocana.setId(id);
		nfnotainfocana.setSafra("NATIVE INSERT UPDATE");
		nfnotainfocana.setReferencia("NATIVE INSERT UPDATE");
		nfnotainfocana.setFornecimentosDiario(new ArrayList<NFNotaInfoCanaFornecimentoDiario>());
		nfnotainfocana.getFornecimentosDiario().add(insertNFNotaInfoCanaFornecimentoDiario(null, tabela, action));
		nfnotainfocana.setDeducoes(new ArrayList<NFNotaInfoCanaDeducao>());
		nfnotainfocana.getDeducoes().add(insertNFNotaInfoCanaDeducao(null, tabela, action));
		nfnotainfocana.setQuantidadeTotalMes("NATIVE INSERT UPDATE");
		nfnotainfocana.setQuantidadeTotalAnterior("NATIVE INSERT UPDATE");
		nfnotainfocana.setQuantidadeTotalGeral("NATIVE INSERT UPDATE");
		nfnotainfocana.setValorFornecimento("NATIVE INSERT UPDATE");
		nfnotainfocana.setValorTotalDeducao("NATIVE INSERT UPDATE");
		nfnotainfocana.setValorLiquidoFornecimento("NATIVE INSERT UPDATE");
		nfnotainfocana.setParentId(id);
		nfnotainfocana.setEmprId(1);
		nfnotainfocana.setModifyDateUTC(a.getTime());
		nfnotainfocana.setCreateDateUTC(a.getTime());
		nfnotainfocana.setCreateUser("system");
		nfnotainfocana.setModifyUser("system");
		nfnotainfocana.setProcessId(1);
		nfnotainfocana.setModelAction(action);

		return nfnotainfocana;
	}


public static NFNotaInfoCanaFornecimentoDiario insertNFNotaInfoCanaFornecimentoDiario(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario = new NFNotaInfoCanaFornecimentoDiario();
		Date a = new Date();
		nfnotainfocanafornecimentodiario.setId(id);
		nfnotainfocanafornecimentodiario.setDia(100);
		nfnotainfocanafornecimentodiario.setQuantidade(new Double(10.00));
		nfnotainfocanafornecimentodiario.setParentId(id);
		nfnotainfocanafornecimentodiario.setEmprId(1);
		nfnotainfocanafornecimentodiario.setModifyDateUTC(a.getTime());
		nfnotainfocanafornecimentodiario.setCreateDateUTC(a.getTime());
		nfnotainfocanafornecimentodiario.setCreateUser("system");
		nfnotainfocanafornecimentodiario.setModifyUser("system");
		nfnotainfocanafornecimentodiario.setProcessId(1);
		nfnotainfocanafornecimentodiario.setModelAction(action);

		return nfnotainfocanafornecimentodiario;
	}


public static NFNotaInfoCanaDeducao insertNFNotaInfoCanaDeducao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoCanaDeducao nfnotainfocanadeducao = new NFNotaInfoCanaDeducao();
		Date a = new Date();
		nfnotainfocanadeducao.setId(id);
		nfnotainfocanadeducao.setDescricaoDeducao("NATIVE INSERT UPDATE");
		nfnotainfocanadeducao.setValorDeducao(new Double(10.00));
		nfnotainfocanadeducao.setParentId(id);
		nfnotainfocanadeducao.setEmprId(1);
		nfnotainfocanadeducao.setModifyDateUTC(a.getTime());
		nfnotainfocanadeducao.setCreateDateUTC(a.getTime());
		nfnotainfocanadeducao.setCreateUser("system");
		nfnotainfocanadeducao.setModifyUser("system");
		nfnotainfocanadeducao.setProcessId(1);
		nfnotainfocanadeducao.setModelAction(action);

		return nfnotainfocanadeducao;
	}


public static NFNotaInfoSuplementar insertNFNotaInfoSuplementar(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		NFNotaInfoSuplementar nfnotainfosuplementar = new NFNotaInfoSuplementar();
		Date a = new Date();
		nfnotainfosuplementar.setId(id);
		nfnotainfosuplementar.setQrCode("NATIVE INSERT UPDATE");
		nfnotainfosuplementar.setParentId(id);
		nfnotainfosuplementar.setEmprId(1);
		nfnotainfosuplementar.setModifyDateUTC(a.getTime());
		nfnotainfosuplementar.setCreateDateUTC(a.getTime());
		nfnotainfosuplementar.setCreateUser("system");
		nfnotainfosuplementar.setModifyUser("system");
		nfnotainfosuplementar.setProcessId(1);
		nfnotainfosuplementar.setModelAction(action);

		return nfnotainfosuplementar;
	}

public static NFNotaInfoItem insertNFNotaInfoItem(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItem nfnotainfoitem = new NFNotaInfoItem();
	Date a = new Date();
	nfnotainfoitem.setId(id);
	nfnotainfoitem.setNumeroItem(100);
	nfnotainfoitem.setProduto(insertNFNotaInfoItemProduto(id, tabela, action));
	nfnotainfoitem.setImposto(insertNFNotaInfoItemImposto(id, tabela, action));
	nfnotainfoitem.setImpostoDevolvido(insertNFImpostoDevolvido(id, tabela, action));
	nfnotainfoitem.setInformacoesAdicionais("NATIVE INSERT UPDATE");
	nfnotainfoitem.setParentId(id);
	nfnotainfoitem.setEmprId(1);
	nfnotainfoitem.setModifyDateUTC(a.getTime());
	nfnotainfoitem.setCreateDateUTC(a.getTime());
	nfnotainfoitem.setCreateUser("system");
	nfnotainfoitem.setModifyUser("system");
	nfnotainfoitem.setProcessId(1);
	nfnotainfoitem.setModelAction(action);

	return nfnotainfoitem;
}

public static NFNotaInfoItemProduto insertNFNotaInfoItemProduto(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemProduto nfnotainfoitemproduto = new NFNotaInfoItemProduto();
	Date a = new Date();
	nfnotainfoitemproduto.setId(id);
	nfnotainfoitemproduto.setCodigo("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setCodigoDeBarras("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setDescricao("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setNcm("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setNomeclaturaValorAduaneiroEstatistica("ddddd,ddd,ffff,fff");
	nfnotainfoitemproduto.setCodigoEspecificadorSituacaoTributaria("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setExtipi("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setCfop("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setUnidadeComercial("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setQuantidadeComercial("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setValorUnitario("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setValorTotalBruto("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setCodigoDeBarrasTributavel("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setUnidadeTributavel("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setQuantidadeTributavel("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setValorUnitarioTributavel("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setValorFrete("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setValorSeguro("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setValorDesconto("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setValorOutrasDespesasAcessorias("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setCompoeValorNota(insertDoisValor(id, tabela, action));
	nfnotainfoitemproduto.setDeclaracoesImportacao(new ArrayList<NFNotaInfoItemProdutoDeclaracaoImportacao>());
	nfnotainfoitemproduto.getDeclaracoesImportacao().add(insertNFNotaInfoItemProdutoDeclaracaoImportacao(id, tabela, action));
	nfnotainfoitemproduto.setDetalhesExportacao(new ArrayList<NFNotaInfoItemDetalheExportacao>());
	nfnotainfoitemproduto.getDetalhesExportacao().add(insertNFNotaInfoItemDetalheExportacao(id, tabela, action));
	nfnotainfoitemproduto.setNumeroPedidoCliente("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setNumeroPedidoItemCliente(100);
	nfnotainfoitemproduto.setNumeroControleFCI("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setVeiculo(insertNFNotaInfoItemProdutoVeiculo(id, tabela, action));
	nfnotainfoitemproduto.setMedicamentos(new ArrayList<NFNotaInfoItemProdutoMedicamento>());
	nfnotainfoitemproduto.getMedicamentos().add(insertNFNotaInfoItemProdutoMedicamento(id, tabela, action));
	nfnotainfoitemproduto.setArmamentos(new ArrayList<NFNotaInfoItemProdutoArmamento>());
	nfnotainfoitemproduto.getArmamentos().add(insertNFNotaInfoItemProdutoArmamento(id, tabela, action));
	nfnotainfoitemproduto.setCombustivel(insertNFNotaInfoItemProdutoCombustivel(id, tabela, action));
	nfnotainfoitemproduto.setNumeroRECOPI("NATIVE INSERT UPDATE");
	nfnotainfoitemproduto.setParentId(id);
	nfnotainfoitemproduto.setEmprId(1);
	nfnotainfoitemproduto.setModifyDateUTC(a.getTime());
	nfnotainfoitemproduto.setCreateDateUTC(a.getTime());
	nfnotainfoitemproduto.setCreateUser("system");
	nfnotainfoitemproduto.setModifyUser("system");
	nfnotainfoitemproduto.setProcessId(1);
	nfnotainfoitemproduto.setModelAction(action);

	return nfnotainfoitemproduto;
}

public static NFNotaInfoItemProdutoDeclaracaoImportacao insertNFNotaInfoItemProdutoDeclaracaoImportacao(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao = new NFNotaInfoItemProdutoDeclaracaoImportacao();
	Date a = new Date();
	nfnotainfoitemprodutodeclaracaoimportacao.setId(id);
	nfnotainfoitemprodutodeclaracaoimportacao.setNumeroRegistro("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacao.setDataRegistro(a.getTime());
	nfnotainfoitemprodutodeclaracaoimportacao.setLocalDesembaraco("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacao.setUfDesembaraco("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacao.setDataDesembaraco(a.getTime());
	nfnotainfoitemprodutodeclaracaoimportacao.setTransporteInternacional(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutodeclaracaoimportacao.setValorAFRMM("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacao.setFormaImportacaoIntermediacao(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutodeclaracaoimportacao.setCnpj("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacao.setUfTerceiro("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacao.setCodigoExportador("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacao.setAdicoes(new ArrayList<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao>());
	nfnotainfoitemprodutodeclaracaoimportacao.getAdicoes()
			.add(insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(null, tabela, action));
	nfnotainfoitemprodutodeclaracaoimportacao.setParentId(id);
	nfnotainfoitemprodutodeclaracaoimportacao.setEmprId(1);
	nfnotainfoitemprodutodeclaracaoimportacao.setModifyDateUTC(a.getTime());
	nfnotainfoitemprodutodeclaracaoimportacao.setCreateDateUTC(a.getTime());
	nfnotainfoitemprodutodeclaracaoimportacao.setCreateUser("system");
	nfnotainfoitemprodutodeclaracaoimportacao.setModifyUser("system");
	nfnotainfoitemprodutodeclaracaoimportacao.setProcessId(1);
	nfnotainfoitemprodutodeclaracaoimportacao.setModelAction(action);

	return nfnotainfoitemprodutodeclaracaoimportacao;
}

public static NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(
		Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao = new NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao();
	Date a = new Date();
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setId(id);
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setNumero(100);
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setSequencial(100);
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setCodigoFabricante("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setDesconto("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setNumeroAtoConcessorioDrawback(a.getTime());
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setParentId(id);
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setEmprId(1);
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setModifyDateUTC(a.getTime());
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setCreateDateUTC(a.getTime());
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setCreateUser("system");
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setModifyUser("system");
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setProcessId(1);
	nfnotainfoitemprodutodeclaracaoimportacaoadicao.setModelAction(action);

	return nfnotainfoitemprodutodeclaracaoimportacaoadicao;
}

public static NFNotaInfoItemDetalheExportacao insertNFNotaInfoItemDetalheExportacao(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao = new NFNotaInfoItemDetalheExportacao();
	Date a = new Date();
	nfnotainfoitemdetalheexportacao.setId(id);
	nfnotainfoitemdetalheexportacao.setAtoConcessorioDrawback(a.getTime());
	nfnotainfoitemdetalheexportacao.setExportacaoIndireta(insertNFNotaInfoItemExportacaoIndireta(null, tabela, action));
	nfnotainfoitemdetalheexportacao.setParentId(id);
	nfnotainfoitemdetalheexportacao.setEmprId(1);
	nfnotainfoitemdetalheexportacao.setModifyDateUTC(a.getTime());
	nfnotainfoitemdetalheexportacao.setCreateDateUTC(a.getTime());
	nfnotainfoitemdetalheexportacao.setCreateUser("system");
	nfnotainfoitemdetalheexportacao.setModifyUser("system");
	nfnotainfoitemdetalheexportacao.setProcessId(1);
	nfnotainfoitemdetalheexportacao.setModelAction(action);

	return nfnotainfoitemdetalheexportacao;
}

public static NFNotaInfoItemExportacaoIndireta insertNFNotaInfoItemExportacaoIndireta(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta = new NFNotaInfoItemExportacaoIndireta();
	Date a = new Date();
	nfnotainfoitemexportacaoindireta.setId(id);
	nfnotainfoitemexportacaoindireta.setNumeroRegistroExportacao(a.getTime());
	nfnotainfoitemexportacaoindireta.setChaveAcessoNFe("NATIVE INSERT UPDATE");
	nfnotainfoitemexportacaoindireta.setQuantidadeItemEfetivamenteExportado("NATIVE INSERT UPDATE");
	nfnotainfoitemexportacaoindireta.setParentId(id);
	nfnotainfoitemexportacaoindireta.setEmprId(1);
	nfnotainfoitemexportacaoindireta.setModifyDateUTC(a.getTime());
	nfnotainfoitemexportacaoindireta.setCreateDateUTC(a.getTime());
	nfnotainfoitemexportacaoindireta.setCreateUser("system");
	nfnotainfoitemexportacaoindireta.setModifyUser("system");
	nfnotainfoitemexportacaoindireta.setProcessId(1);
	nfnotainfoitemexportacaoindireta.setModelAction(action);

	return nfnotainfoitemexportacaoindireta;
}

public static NFNotaInfoItemProdutoVeiculo insertNFNotaInfoItemProdutoVeiculo(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo = new NFNotaInfoItemProdutoVeiculo();
	Date a = new Date();
	nfnotainfoitemprodutoveiculo.setId(id);
	nfnotainfoitemprodutoveiculo.setTipoOperacao(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoveiculo.setChassi("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setCodigoCor("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setDescricaoCor("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setPotencia("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setCilindrada("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setPesoLiquido("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setPesoBruto("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setNumeroSerie("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setTipoCombustivel(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoveiculo.setNumeroMotor("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setCapacidadeMaximaTracao("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setDistanciaEntreEixos(100);
	nfnotainfoitemprodutoveiculo.setAnoModeloFabricacao(100);
	nfnotainfoitemprodutoveiculo.setAnoFabricacao(100);
	nfnotainfoitemprodutoveiculo.setTipoPintura("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setTipoVeiculo(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoveiculo.setEspecieVeiculo(100);
	nfnotainfoitemprodutoveiculo.setCondicaoChassi(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoveiculo.setCondicao(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoveiculo.setCodigoMarcaModelo("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoveiculo.setCorDENATRAN(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoveiculo.setLotacao(100);
	nfnotainfoitemprodutoveiculo.setRestricao(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoveiculo.setParentId(id);
	nfnotainfoitemprodutoveiculo.setEmprId(1);
	nfnotainfoitemprodutoveiculo.setModifyDateUTC(a.getTime());
	nfnotainfoitemprodutoveiculo.setCreateDateUTC(a.getTime());
	nfnotainfoitemprodutoveiculo.setCreateUser("system");
	nfnotainfoitemprodutoveiculo.setModifyUser("system");
	nfnotainfoitemprodutoveiculo.setProcessId(1);
	nfnotainfoitemprodutoveiculo.setModelAction(action);

	return nfnotainfoitemprodutoveiculo;
}

public static NFNotaInfoItemProdutoMedicamento insertNFNotaInfoItemProdutoMedicamento(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento = new NFNotaInfoItemProdutoMedicamento();
	Date a = new Date();
	nfnotainfoitemprodutomedicamento.setId(id);
	nfnotainfoitemprodutomedicamento.setLote("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutomedicamento.setQuantidade("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutomedicamento.setDataFabricacao(a.getTime());
	nfnotainfoitemprodutomedicamento.setDataValidade(a.getTime());
	nfnotainfoitemprodutomedicamento.setPrecoMaximoConsumidor("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutomedicamento.setParentId(id);
	nfnotainfoitemprodutomedicamento.setEmprId(1);
	nfnotainfoitemprodutomedicamento.setModifyDateUTC(a.getTime());
	nfnotainfoitemprodutomedicamento.setCreateDateUTC(a.getTime());
	nfnotainfoitemprodutomedicamento.setCreateUser("system");
	nfnotainfoitemprodutomedicamento.setModifyUser("system");
	nfnotainfoitemprodutomedicamento.setProcessId(1);
	nfnotainfoitemprodutomedicamento.setModelAction(action);

	return nfnotainfoitemprodutomedicamento;
}

public static NFNotaInfoItemProdutoArmamento insertNFNotaInfoItemProdutoArmamento(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento = new NFNotaInfoItemProdutoArmamento();
	Date a = new Date();
	nfnotainfoitemprodutoarmamento.setId(id);
	nfnotainfoitemprodutoarmamento.setTipo(insertDoisValor(id, tabela, action));
	nfnotainfoitemprodutoarmamento.setNumeroSerieArma("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoarmamento.setNumeroSerieCano("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoarmamento.setDescricao("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutoarmamento.setParentId(id);
	nfnotainfoitemprodutoarmamento.setEmprId(1);
	nfnotainfoitemprodutoarmamento.setModifyDateUTC(a.getTime());
	nfnotainfoitemprodutoarmamento.setCreateDateUTC(a.getTime());
	nfnotainfoitemprodutoarmamento.setCreateUser("system");
	nfnotainfoitemprodutoarmamento.setModifyUser("system");
	nfnotainfoitemprodutoarmamento.setProcessId(1);
	nfnotainfoitemprodutoarmamento.setModelAction(action);

	return nfnotainfoitemprodutoarmamento;
}

public static NFNotaInfoItemProdutoCombustivel insertNFNotaInfoItemProdutoCombustivel(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel = new NFNotaInfoItemProdutoCombustivel();
	Date a = new Date();
	nfnotainfoitemprodutocombustivel.setId(id);
	nfnotainfoitemprodutocombustivel.setCodigoProdutoANP("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivel.setPercentualGasNatural("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivel.setCodigoAutorizacaoCOFIF("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivel.setQuantidade("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivel.setUf("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivel.setCide(insertNFNotaInfoItemProdutoCombustivelCIDE(null, tabela, action));
	nfnotainfoitemprodutocombustivel.setParentId(id);
	nfnotainfoitemprodutocombustivel.setEmprId(1);
	nfnotainfoitemprodutocombustivel.setModifyDateUTC(a.getTime());
	nfnotainfoitemprodutocombustivel.setCreateDateUTC(a.getTime());
	nfnotainfoitemprodutocombustivel.setCreateUser("system");
	nfnotainfoitemprodutocombustivel.setModifyUser("system");
	nfnotainfoitemprodutocombustivel.setProcessId(1);
	nfnotainfoitemprodutocombustivel.setModelAction(action);

	return nfnotainfoitemprodutocombustivel;
}

public static NFNotaInfoItemProdutoCombustivelCIDE insertNFNotaInfoItemProdutoCombustivelCIDE(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide = new NFNotaInfoItemProdutoCombustivelCIDE();
	Date a = new Date();
	nfnotainfoitemprodutocombustivelcide.setId(id);
	nfnotainfoitemprodutocombustivelcide.setQuantidadeBCCIDE("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivelcide.setValorAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivelcide.setValor("NATIVE INSERT UPDATE");
	nfnotainfoitemprodutocombustivelcide.setParentId(id);
	nfnotainfoitemprodutocombustivelcide.setEmprId(1);
	nfnotainfoitemprodutocombustivelcide.setModifyDateUTC(a.getTime());
	nfnotainfoitemprodutocombustivelcide.setCreateDateUTC(a.getTime());
	nfnotainfoitemprodutocombustivelcide.setCreateUser("system");
	nfnotainfoitemprodutocombustivelcide.setModifyUser("system");
	nfnotainfoitemprodutocombustivelcide.setProcessId(1);
	nfnotainfoitemprodutocombustivelcide.setModelAction(action);

	return nfnotainfoitemprodutocombustivelcide;
}

public static NFImpostoDevolvido insertNFImpostoDevolvido2(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
	NFImpostoDevolvido nfimpostodevolvido = new NFImpostoDevolvido();
	Date a = new Date();
	nfimpostodevolvido.setId(id);
	nfimpostodevolvido.setPercentualDevolucao("NATIVE INSERT UPDATE");
	nfimpostodevolvido.setInformacaoIPIDevolvido(insertNFInformacaoImpostoDevolvido(id, tabela, action));
	nfimpostodevolvido.setParentId(id);
	nfimpostodevolvido.setEmprId(1);
	nfimpostodevolvido.setModifyDateUTC(a.getTime());
	nfimpostodevolvido.setCreateDateUTC(a.getTime());
	nfimpostodevolvido.setCreateUser("system");
	nfimpostodevolvido.setModifyUser("system");
	nfimpostodevolvido.setProcessId(1);
	nfimpostodevolvido.setModelAction(action);

	return nfimpostodevolvido;
}

public static NFInformacaoImpostoDevolvido insertNFInformacaoImpostoDevolvido2(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = new NFInformacaoImpostoDevolvido();
	Date a = new Date();
	nfinformacaoimpostodevolvido.setId(id);
	nfinformacaoimpostodevolvido.setValorIPIDevolvido("NATIVE INSERT UPDATE");
	nfinformacaoimpostodevolvido.setParentId(id);
	nfinformacaoimpostodevolvido.setEmprId(1);
	nfinformacaoimpostodevolvido.setModifyDateUTC(a.getTime());
	nfinformacaoimpostodevolvido.setCreateDateUTC(a.getTime());
	nfinformacaoimpostodevolvido.setCreateUser("system");
	nfinformacaoimpostodevolvido.setModifyUser("system");
	nfinformacaoimpostodevolvido.setProcessId(1);
	nfinformacaoimpostodevolvido.setModelAction(action);

	return nfinformacaoimpostodevolvido;
}

public static NFNotaInfoItemImposto insertNFNotaInfoItemImposto(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImposto nfnotainfoitemimposto = new NFNotaInfoItemImposto();
	Date a = new Date();
	nfnotainfoitemimposto.setId(id);
	nfnotainfoitemimposto.setValorTotalTributos("NATIVE INSERT UPDATE");
	nfnotainfoitemimposto.setIcms(insertNFNotaInfoItemImpostoICMS(null, tabela, action));
	nfnotainfoitemimposto.setIpi(insertNFNotaInfoItemImpostoIPI(null, tabela, action));
	nfnotainfoitemimposto.setImpostoImportacao(insertNFNotaInfoItemImpostoImportacao(null, tabela, action));
	nfnotainfoitemimposto.setIssqn(insertNFNotaInfoItemImpostoISSQN(null, tabela, action));
	nfnotainfoitemimposto.setPis(insertNFNotaInfoItemImpostoPIS(null, tabela, action));
	nfnotainfoitemimposto.setPisst(insertNFNotaInfoItemImpostoPISST(null, tabela, action));
	nfnotainfoitemimposto.setCofins(insertNFNotaInfoItemImpostoCOFINS(null, tabela, action));
	nfnotainfoitemimposto.setCofinsst(insertNFNotaInfoItemImpostoCOFINSST(null, tabela, action));
	nfnotainfoitemimposto.setIcmsUfDestino(insertNFNotaInfoItemImpostoICMSUFDestino(null, tabela, action));
	nfnotainfoitemimposto.setParentId(id);
	nfnotainfoitemimposto.setEmprId(1);
	nfnotainfoitemimposto.setModifyDateUTC(a.getTime());
	nfnotainfoitemimposto.setCreateDateUTC(a.getTime());
	nfnotainfoitemimposto.setCreateUser("system");
	nfnotainfoitemimposto.setModifyUser("system");
	nfnotainfoitemimposto.setProcessId(1);
	nfnotainfoitemimposto.setModelAction(action);

	return nfnotainfoitemimposto;
}

public static NFNotaInfoItemImpostoICMS insertNFNotaInfoItemImpostoICMS(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms = new NFNotaInfoItemImpostoICMS();
	Date a = new Date();
	nfnotainfoitemimpostoicms.setId(id);
	nfnotainfoitemimpostoicms.setIcms00(insertNFNotaInfoItemImpostoICMS00(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms10(insertNFNotaInfoItemImpostoICMS10(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms20(insertNFNotaInfoItemImpostoICMS20(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms30(insertNFNotaInfoItemImpostoICMS30(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms40(insertNFNotaInfoItemImpostoICMS40(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms51(insertNFNotaInfoItemImpostoICMS51(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms60(insertNFNotaInfoItemImpostoICMS60(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms70(insertNFNotaInfoItemImpostoICMS70(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcms90(insertNFNotaInfoItemImpostoICMS90(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmsPartilhado(insertNFNotaInfoItemImpostoICMSPartilhado(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmsst(insertNFNotaInfoItemImpostoICMSST(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmssn101(insertNFNotaInfoItemImpostoICMSSN101(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmssn102(insertNFNotaInfoItemImpostoICMSSN102(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmssn201(insertNFNotaInfoItemImpostoICMSSN201(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmssn202(insertNFNotaInfoItemImpostoICMSSN202(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmssn500(insertNFNotaInfoItemImpostoICMSSN500(id, tabela, action));
	nfnotainfoitemimpostoicms.setIcmssn900(insertNFNotaInfoItemImpostoICMSSN900(id, tabela, action));
	nfnotainfoitemimpostoicms.setParentId(id);
	nfnotainfoitemimpostoicms.setEmprId(1);
	nfnotainfoitemimpostoicms.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms.setCreateUser("system");
	nfnotainfoitemimpostoicms.setModifyUser("system");
	nfnotainfoitemimpostoicms.setProcessId(1);
	nfnotainfoitemimpostoicms.setModelAction(action);

	return nfnotainfoitemimpostoicms;
}

public static NFNotaInfoItemImpostoICMS00 insertNFNotaInfoItemImpostoICMS00(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00 = new NFNotaInfoItemImpostoICMS00();
	Date a = new Date();
	nfnotainfoitemimpostoicms00.setId(id);
	nfnotainfoitemimpostoicms00.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms00.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms00.setModalidadeBCICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms00.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms00.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms00.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms00.setParentId(id);
	nfnotainfoitemimpostoicms00.setEmprId(1);
	nfnotainfoitemimpostoicms00.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms00.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms00.setCreateUser("system");
	nfnotainfoitemimpostoicms00.setModifyUser("system");
	nfnotainfoitemimpostoicms00.setProcessId(1);
	nfnotainfoitemimpostoicms00.setModelAction(action);

	return nfnotainfoitemimpostoicms00;
}

public static NFNotaInfoItemImpostoICMS10 insertNFNotaInfoItemImpostoICMS10(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10 = new NFNotaInfoItemImpostoICMS10();
	Date a = new Date();
	nfnotainfoitemimpostoicms10.setId(id);
	nfnotainfoitemimpostoicms10.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms10.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms10.setModalidadeBCICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms10.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setModalidadeBCICMSST(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms10.setPercentualMargemValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setValorBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms10.setParentId(id);
	nfnotainfoitemimpostoicms10.setEmprId(1);
	nfnotainfoitemimpostoicms10.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms10.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms10.setCreateUser("system");
	nfnotainfoitemimpostoicms10.setModifyUser("system");
	nfnotainfoitemimpostoicms10.setProcessId(1);
	nfnotainfoitemimpostoicms10.setModelAction(action);

	return nfnotainfoitemimpostoicms10;
}

public static NFNotaInfoItemImpostoICMS20 insertNFNotaInfoItemImpostoICMS20(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20 = new NFNotaInfoItemImpostoICMS20();
	Date a = new Date();
	nfnotainfoitemimpostoicms20.setId(id);
	nfnotainfoitemimpostoicms20.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms20.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms20.setModalidadeBCICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms20.setPercentualReducaoBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms20.setValorBCICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms20.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms20.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms20.setValorICMSDesoneracao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms20.setDesoneracao(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms20.setParentId(id);
	nfnotainfoitemimpostoicms20.setEmprId(1);
	nfnotainfoitemimpostoicms20.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms20.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms20.setCreateUser("system");
	nfnotainfoitemimpostoicms20.setModifyUser("system");
	nfnotainfoitemimpostoicms20.setProcessId(1);
	nfnotainfoitemimpostoicms20.setModelAction(action);

	return nfnotainfoitemimpostoicms20;
}

public static NFNotaInfoItemImpostoICMS30 insertNFNotaInfoItemImpostoICMS30(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30 = new NFNotaInfoItemImpostoICMS30();
	Date a = new Date();
	nfnotainfoitemimpostoicms30.setId(id);
	nfnotainfoitemimpostoicms30.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms30.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms30.setModalidadeBCICMSST(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms30.setPercentualMargemValorAdicionadoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms30.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms30.setValorBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms30.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms30.setValorImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms30.setValorICMSDesoneracao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms30.setDesoneracao(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms30.setParentId(id);
	nfnotainfoitemimpostoicms30.setEmprId(1);
	nfnotainfoitemimpostoicms30.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms30.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms30.setCreateUser("system");
	nfnotainfoitemimpostoicms30.setModifyUser("system");
	nfnotainfoitemimpostoicms30.setProcessId(1);
	nfnotainfoitemimpostoicms30.setModelAction(action);

	return nfnotainfoitemimpostoicms30;
}

public static NFNotaInfoItemImpostoICMS40 insertNFNotaInfoItemImpostoICMS40(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40 = new NFNotaInfoItemImpostoICMS40();
	Date a = new Date();
	nfnotainfoitemimpostoicms40.setId(id);
	nfnotainfoitemimpostoicms40.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms40.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms40.setValorICMSDesoneracao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms40.setMotivoDesoneracaoICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms40.setParentId(id);
	nfnotainfoitemimpostoicms40.setEmprId(1);
	nfnotainfoitemimpostoicms40.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms40.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms40.setCreateUser("system");
	nfnotainfoitemimpostoicms40.setModifyUser("system");
	nfnotainfoitemimpostoicms40.setProcessId(1);
	nfnotainfoitemimpostoicms40.setModelAction(action);

	return nfnotainfoitemimpostoicms40;
}

public static NFNotaInfoItemImpostoICMS51 insertNFNotaInfoItemImpostoICMS51(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51 = new NFNotaInfoItemImpostoICMS51();
	Date a = new Date();
	nfnotainfoitemimpostoicms51.setId(id);
	nfnotainfoitemimpostoicms51.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms51.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms51.setModalidadeBCICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms51.setPercentualReducaoBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms51.setValorBCICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms51.setPercentualICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms51.setValorICMSOperacao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms51.setPercentualDiferimento("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms51.setValorICMSDiferimento("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms51.setValorICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms51.setParentId(id);
	nfnotainfoitemimpostoicms51.setEmprId(1);
	nfnotainfoitemimpostoicms51.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms51.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms51.setCreateUser("system");
	nfnotainfoitemimpostoicms51.setModifyUser("system");
	nfnotainfoitemimpostoicms51.setProcessId(1);
	nfnotainfoitemimpostoicms51.setModelAction(action);

	return nfnotainfoitemimpostoicms51;
}

public static NFNotaInfoItemImpostoICMS60 insertNFNotaInfoItemImpostoICMS60(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60 = new NFNotaInfoItemImpostoICMS60();
	Date a = new Date();
	nfnotainfoitemimpostoicms60.setId(id);
	nfnotainfoitemimpostoicms60.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms60.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms60.setValorBCICMSSTRetido("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms60.setValorICMSSTRetido("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms60.setParentId(id);
	nfnotainfoitemimpostoicms60.setEmprId(1);
	nfnotainfoitemimpostoicms60.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms60.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms60.setCreateUser("system");
	nfnotainfoitemimpostoicms60.setModifyUser("system");
	nfnotainfoitemimpostoicms60.setProcessId(1);
	nfnotainfoitemimpostoicms60.setModelAction(action);

	return nfnotainfoitemimpostoicms60;
}

public static NFNotaInfoItemImpostoICMS70 insertNFNotaInfoItemImpostoICMS70(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70 = new NFNotaInfoItemImpostoICMS70();
	Date a = new Date();
	nfnotainfoitemimpostoicms70.setId(id);
	nfnotainfoitemimpostoicms70.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms70.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms70.setModalidadeBCICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms70.setPercentualReducaoBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setValorBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setModalidadeBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setPercentualMargemValorAdicionadoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setValorBCST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setValorICMSDesoneracao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setDesoneracao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms70.setParentId(id);
	nfnotainfoitemimpostoicms70.setEmprId(1);
	nfnotainfoitemimpostoicms70.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms70.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms70.setCreateUser("system");
	nfnotainfoitemimpostoicms70.setModifyUser("system");
	nfnotainfoitemimpostoicms70.setProcessId(1);
	nfnotainfoitemimpostoicms70.setModelAction(action);

	return nfnotainfoitemimpostoicms70;
}

public static NFNotaInfoItemImpostoICMS90 insertNFNotaInfoItemImpostoICMS90(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90 = new NFNotaInfoItemImpostoICMS90();
	Date a = new Date();
	nfnotainfoitemimpostoicms90.setId(id);
	nfnotainfoitemimpostoicms90.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms90.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms90.setModalidadeBCICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms90.setValorBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setPercentualReducaoBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setModalidadeBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setPercentualMargemValorAdicionadoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setValorBCST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setValorICMSDesoneracao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicms90.setDesoneracao(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicms90.setParentId(id);
	nfnotainfoitemimpostoicms90.setEmprId(1);
	nfnotainfoitemimpostoicms90.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicms90.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicms90.setCreateUser("system");
	nfnotainfoitemimpostoicms90.setModifyUser("system");
	nfnotainfoitemimpostoicms90.setProcessId(1);
	nfnotainfoitemimpostoicms90.setModelAction(action);

	return nfnotainfoitemimpostoicms90;
}

public static NFNotaInfoItemImpostoICMSPartilhado insertNFNotaInfoItemImpostoICMSPartilhado(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado = new NFNotaInfoItemImpostoICMSPartilhado();
	Date a = new Date();
	nfnotainfoitemimpostoicmspartilhado.setId(id);
	nfnotainfoitemimpostoicmspartilhado.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmspartilhado.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmspartilhado.setModalidadeBCICMS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmspartilhado.setValorBCICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setPercentualReducaoBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setPercentualAliquotaImposto("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setValorICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setModalidadeBCICMSST(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmspartilhado.setPercentualMargemValorAdicionadoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setValorBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setPercentualBCOperacaoPropria("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmspartilhado.setUfICMSST(insertEstado(id, tabela, action));
	nfnotainfoitemimpostoicmspartilhado.setParentId(id);
	nfnotainfoitemimpostoicmspartilhado.setEmprId(1);
	nfnotainfoitemimpostoicmspartilhado.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmspartilhado.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmspartilhado.setCreateUser("system");
	nfnotainfoitemimpostoicmspartilhado.setModifyUser("system");
	nfnotainfoitemimpostoicmspartilhado.setProcessId(1);
	nfnotainfoitemimpostoicmspartilhado.setModelAction(action);

	return nfnotainfoitemimpostoicmspartilhado;
}

public static NFNotaInfoItemImpostoICMSST insertNFNotaInfoItemImpostoICMSST(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst = new NFNotaInfoItemImpostoICMSST();
	Date a = new Date();
	nfnotainfoitemimpostoicmsst.setId(id);
	nfnotainfoitemimpostoicmsst.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmsst.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmsst.setValorBCICMSSTRetidoUFRemetente("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsst.setValorICMSSTRetidoUFRemetente("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsst.setValorBCICMSSTUFDestino("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsst.setValorICMSSTUFDestino("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsst.setParentId(id);
	nfnotainfoitemimpostoicmsst.setEmprId(1);
	nfnotainfoitemimpostoicmsst.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmsst.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmsst.setCreateUser("system");
	nfnotainfoitemimpostoicmsst.setModifyUser("system");
	nfnotainfoitemimpostoicmsst.setProcessId(1);
	nfnotainfoitemimpostoicmsst.setModelAction(action);

	return nfnotainfoitemimpostoicmsst;
}

public static NFNotaInfoItemImpostoICMSSN101 insertNFNotaInfoItemImpostoICMSSN101(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101 = new NFNotaInfoItemImpostoICMSSN101();
	Date a = new Date();
	nfnotainfoitemimpostoicmssn101.setId(id);
	nfnotainfoitemimpostoicmssn101.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn101.setSituacaoOperacaoSN(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn101.setPercentualAliquotaAplicavelCalculoCreditoSN("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn101.setValorCreditoICMSSN("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn101.setParentId(id);
	nfnotainfoitemimpostoicmssn101.setEmprId(1);
	nfnotainfoitemimpostoicmssn101.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn101.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn101.setCreateUser("system");
	nfnotainfoitemimpostoicmssn101.setModifyUser("system");
	nfnotainfoitemimpostoicmssn101.setProcessId(1);
	nfnotainfoitemimpostoicmssn101.setModelAction(action);

	return nfnotainfoitemimpostoicmssn101;
}

public static NFNotaInfoItemImpostoICMSSN102 insertNFNotaInfoItemImpostoICMSSN102(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102 = new NFNotaInfoItemImpostoICMSSN102();
	Date a = new Date();
	nfnotainfoitemimpostoicmssn102.setId(id);
	nfnotainfoitemimpostoicmssn102.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn102.setSituacaoOperacaoSN(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn102.setParentId(id);
	nfnotainfoitemimpostoicmssn102.setEmprId(1);
	nfnotainfoitemimpostoicmssn102.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn102.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn102.setCreateUser("system");
	nfnotainfoitemimpostoicmssn102.setModifyUser("system");
	nfnotainfoitemimpostoicmssn102.setProcessId(1);
	nfnotainfoitemimpostoicmssn102.setModelAction(action);

	return nfnotainfoitemimpostoicmssn102;
}

public static NFNotaInfoItemImpostoICMSSN201 insertNFNotaInfoItemImpostoICMSSN201(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201 = new NFNotaInfoItemImpostoICMSSN201();
	Date a = new Date();
	nfnotainfoitemimpostoicmssn201.setId(id);
	nfnotainfoitemimpostoicmssn201.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn201.setSituacaoOperacaoSN(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn201.setModalidadeBCICMSST(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn201.setPercentualMargemValorAdicionadoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn201.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn201.setValorBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn201.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn201.setValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn201.setPercentualAliquotaAplicavelCalculoCreditoSN("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn201.setValorCreditoICMSSN("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn201.setParentId(id);
	nfnotainfoitemimpostoicmssn201.setEmprId(1);
	nfnotainfoitemimpostoicmssn201.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn201.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn201.setCreateUser("system");
	nfnotainfoitemimpostoicmssn201.setModifyUser("system");
	nfnotainfoitemimpostoicmssn201.setProcessId(1);
	nfnotainfoitemimpostoicmssn201.setModelAction(action);

	return nfnotainfoitemimpostoicmssn201;
}

public static NFNotaInfoItemImpostoICMSSN202 insertNFNotaInfoItemImpostoICMSSN202(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202 = new NFNotaInfoItemImpostoICMSSN202();
	Date a = new Date();
	nfnotainfoitemimpostoicmssn202.setId(id);
	nfnotainfoitemimpostoicmssn202.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn202.setSituacaoOperacaoSN(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn202.setModalidadeBCICMSST(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn202.setPercentualMargemValorAdicionadoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn202.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn202.setValorBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn202.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn202.setValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn202.setParentId(id);
	nfnotainfoitemimpostoicmssn202.setEmprId(1);
	nfnotainfoitemimpostoicmssn202.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn202.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn202.setCreateUser("system");
	nfnotainfoitemimpostoicmssn202.setModifyUser("system");
	nfnotainfoitemimpostoicmssn202.setProcessId(1);
	nfnotainfoitemimpostoicmssn202.setModelAction(action);

	return nfnotainfoitemimpostoicmssn202;
}

public static NFNotaInfoItemImpostoICMSSN500 insertNFNotaInfoItemImpostoICMSSN500(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500 = new NFNotaInfoItemImpostoICMSSN500();
	Date a = new Date();
	nfnotainfoitemimpostoicmssn500.setId(id);
	nfnotainfoitemimpostoicmssn500.setOrigem(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn500.setSituacaoOperacaoSN(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoicmssn500.setValorBCICMSSTRetido("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn500.setValorICMSSTRetido("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn500.setParentId(id);
	nfnotainfoitemimpostoicmssn500.setEmprId(1);
	nfnotainfoitemimpostoicmssn500.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn500.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn500.setCreateUser("system");
	nfnotainfoitemimpostoicmssn500.setModifyUser("system");
	nfnotainfoitemimpostoicmssn500.setProcessId(1);
	nfnotainfoitemimpostoicmssn500.setModelAction(action);

	return nfnotainfoitemimpostoicmssn500;
}

public static NFNotaInfoItemImpostoICMSSN900 insertNFNotaInfoItemImpostoICMSSN900(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900 = new NFNotaInfoItemImpostoICMSSN900();
	Date a = new Date();
	nfnotainfoitemimpostoicmssn900.setId(id);
	nfnotainfoitemimpostoicmssn900.setOrigem("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setSituacaoOperacaoSN("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setModalidadeBCICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setValorBCICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setPercentualReducaoBC("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setPercentualAliquotaImposto("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setValorICMS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setModalidadeBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setPercentualMargemValorAdicionadoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setPercentualReducaoBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setValorBCICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setPercentualAliquotaImpostoICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setValorICMSST("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setPercentualAliquotaAplicavelCalculoCreditoSN("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmssn900.setParentId(id);
	nfnotainfoitemimpostoicmssn900.setEmprId(1);
	nfnotainfoitemimpostoicmssn900.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn900.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmssn900.setCreateUser("system");
	nfnotainfoitemimpostoicmssn900.setModifyUser("system");
	nfnotainfoitemimpostoicmssn900.setProcessId(1);
	nfnotainfoitemimpostoicmssn900.setModelAction(action);

	return nfnotainfoitemimpostoicmssn900;
}

public static NFNotaInfoItemImpostoIPI insertNFNotaInfoItemImpostoIPI(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi = new NFNotaInfoItemImpostoIPI();
	Date a = new Date();
	nfnotainfoitemimpostoipi.setId(id);
	nfnotainfoitemimpostoipi.setClasseEnquadramento("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipi.setCnpjProdutor("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipi.setCodigoSelo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipi.setQuantidadeSelo(new Integer("100"));
	nfnotainfoitemimpostoipi.setCodigoEnquadramento("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipi.setTributado(insertNFNotaInfoItemImpostoIPITributado(id, tabela, action));
	nfnotainfoitemimpostoipi.setNaoTributado(insertNFNotaInfoItemImpostoIPINaoTributado(id, tabela, action));
	nfnotainfoitemimpostoipi.setParentId(id);
	nfnotainfoitemimpostoipi.setEmprId(1);
	nfnotainfoitemimpostoipi.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoipi.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoipi.setCreateUser("system");
	nfnotainfoitemimpostoipi.setModifyUser("system");
	nfnotainfoitemimpostoipi.setProcessId(1);
	nfnotainfoitemimpostoipi.setModelAction(action);

	return nfnotainfoitemimpostoipi;
}

public static NFNotaInfoItemImpostoIPITributado insertNFNotaInfoItemImpostoIPITributado(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado = new NFNotaInfoItemImpostoIPITributado();
	Date a = new Date();
	nfnotainfoitemimpostoipitributado.setId(id);
	nfnotainfoitemimpostoipitributado.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoipitributado.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipitributado.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipitributado.setQuantidade("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipitributado.setValorUnidadeTributavel("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipitributado.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoipitributado.setParentId(id);
	nfnotainfoitemimpostoipitributado.setEmprId(1);
	nfnotainfoitemimpostoipitributado.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoipitributado.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoipitributado.setCreateUser("system");
	nfnotainfoitemimpostoipitributado.setModifyUser("system");
	nfnotainfoitemimpostoipitributado.setProcessId(1);
	nfnotainfoitemimpostoipitributado.setModelAction(action);

	return nfnotainfoitemimpostoipitributado;
}

public static NFNotaInfoItemImpostoIPINaoTributado insertNFNotaInfoItemImpostoIPINaoTributado(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado = new NFNotaInfoItemImpostoIPINaoTributado();
	Date a = new Date();
	nfnotainfoitemimpostoipinaotributado.setId(id);
	nfnotainfoitemimpostoipinaotributado.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoipinaotributado.setParentId(id);
	nfnotainfoitemimpostoipinaotributado.setEmprId(1);
	nfnotainfoitemimpostoipinaotributado.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoipinaotributado.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoipinaotributado.setCreateUser("system");
	nfnotainfoitemimpostoipinaotributado.setModifyUser("system");
	nfnotainfoitemimpostoipinaotributado.setProcessId(1);
	nfnotainfoitemimpostoipinaotributado.setModelAction(action);

	return nfnotainfoitemimpostoipinaotributado;
}

public static NFNotaInfoItemImpostoImportacao insertNFNotaInfoItemImpostoImportacao(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao = new NFNotaInfoItemImpostoImportacao();
	Date a = new Date();
	nfnotainfoitemimpostoimportacao.setId(id);
	nfnotainfoitemimpostoimportacao.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoimportacao.setValorDespesaAduaneira("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoimportacao.setValorImpostoImportacao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoimportacao.setValorIOF("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoimportacao.setParentId(id);
	nfnotainfoitemimpostoimportacao.setEmprId(1);
	nfnotainfoitemimpostoimportacao.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoimportacao.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoimportacao.setCreateUser("system");
	nfnotainfoitemimpostoimportacao.setModifyUser("system");
	nfnotainfoitemimpostoimportacao.setProcessId(1);
	nfnotainfoitemimpostoimportacao.setModelAction(action);

	return nfnotainfoitemimpostoimportacao;
}

public static NFNotaInfoItemImpostoISSQN insertNFNotaInfoItemImpostoISSQN(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn = new NFNotaInfoItemImpostoISSQN();
	Date a = new Date();
	nfnotainfoitemimpostoissqn.setId(id);
	nfnotainfoitemimpostoissqn.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setValorAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setValor("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setCodigoMunicipio(100);
	nfnotainfoitemimpostoissqn.setItemListaServicos("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setValorDeducao("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setValorOutro("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setValorDescontoIncondicionado("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setValorDescontoCondicionado("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setValorRetencaoISS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setIndicadorExigibilidadeISS(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoissqn.setCodigoServico("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setCodigoMunicipioIncidenciaImposto("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setCodigoPais("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setNumeroProcesso("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoissqn.setIndicadorIncentivoFiscal(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostoissqn.setParentId(id);
	nfnotainfoitemimpostoissqn.setEmprId(1);
	nfnotainfoitemimpostoissqn.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoissqn.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoissqn.setCreateUser("system");
	nfnotainfoitemimpostoissqn.setModifyUser("system");
	nfnotainfoitemimpostoissqn.setProcessId(1);
	nfnotainfoitemimpostoissqn.setModelAction(action);

	return nfnotainfoitemimpostoissqn;
}

public static NFNotaInfoItemImpostoPIS insertNFNotaInfoItemImpostoPIS(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis = new NFNotaInfoItemImpostoPIS();
	Date a = new Date();
	nfnotainfoitemimpostopis.setId(id);
	nfnotainfoitemimpostopis.setAliquota(insertNFNotaInfoItemImpostoPISAliquota(id, tabela, action));
	nfnotainfoitemimpostopis.setQuantidade(insertNFNotaInfoItemImpostoPISQuantidade(id, tabela, action));
	nfnotainfoitemimpostopis.setNaoTributado(insertNFNotaInfoItemImpostoPISNaoTributado(id, tabela, action));
	nfnotainfoitemimpostopis.setOutrasOperacoes(insertNFNotaInfoItemImpostoPISOutrasOperacoes(id, tabela, action));
	nfnotainfoitemimpostopis.setParentId(id);
	nfnotainfoitemimpostopis.setEmprId(1);
	nfnotainfoitemimpostopis.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostopis.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostopis.setCreateUser("system");
	nfnotainfoitemimpostopis.setModifyUser("system");
	nfnotainfoitemimpostopis.setProcessId(1);
	nfnotainfoitemimpostopis.setModelAction(action);

	return nfnotainfoitemimpostopis;
}

public static NFNotaInfoItemImpostoPISAliquota insertNFNotaInfoItemImpostoPISAliquota(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota = new NFNotaInfoItemImpostoPISAliquota();
	Date a = new Date();
	nfnotainfoitemimpostopisaliquota.setId(id);
	nfnotainfoitemimpostopisaliquota.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostopisaliquota.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisaliquota.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisaliquota.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisaliquota.setParentId(id);
	nfnotainfoitemimpostopisaliquota.setEmprId(1);
	nfnotainfoitemimpostopisaliquota.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostopisaliquota.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostopisaliquota.setCreateUser("system");
	nfnotainfoitemimpostopisaliquota.setModifyUser("system");
	nfnotainfoitemimpostopisaliquota.setProcessId(1);
	nfnotainfoitemimpostopisaliquota.setModelAction(action);

	return nfnotainfoitemimpostopisaliquota;
}

public static NFNotaInfoItemImpostoPISQuantidade insertNFNotaInfoItemImpostoPISQuantidade(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade = new NFNotaInfoItemImpostoPISQuantidade();
	Date a = new Date();
	nfnotainfoitemimpostopisquantidade.setId(id);
	nfnotainfoitemimpostopisquantidade.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostopisquantidade.setQuantidadeVendida("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisquantidade.setValorAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisquantidade.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisquantidade.setParentId(id);
	nfnotainfoitemimpostopisquantidade.setEmprId(1);
	nfnotainfoitemimpostopisquantidade.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostopisquantidade.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostopisquantidade.setCreateUser("system");
	nfnotainfoitemimpostopisquantidade.setModifyUser("system");
	nfnotainfoitemimpostopisquantidade.setProcessId(1);
	nfnotainfoitemimpostopisquantidade.setModelAction(action);

	return nfnotainfoitemimpostopisquantidade;
}

public static NFNotaInfoItemImpostoPISNaoTributado insertNFNotaInfoItemImpostoPISNaoTributado(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado = new NFNotaInfoItemImpostoPISNaoTributado();
	Date a = new Date();
	nfnotainfoitemimpostopisnaotributado.setId(id);
	nfnotainfoitemimpostopisnaotributado.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostopisnaotributado.setParentId(id);
	nfnotainfoitemimpostopisnaotributado.setEmprId(1);
	nfnotainfoitemimpostopisnaotributado.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostopisnaotributado.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostopisnaotributado.setCreateUser("system");
	nfnotainfoitemimpostopisnaotributado.setModifyUser("system");
	nfnotainfoitemimpostopisnaotributado.setProcessId(1);
	nfnotainfoitemimpostopisnaotributado.setModelAction(action);

	return nfnotainfoitemimpostopisnaotributado;
}

public static NFNotaInfoItemImpostoPISOutrasOperacoes insertNFNotaInfoItemImpostoPISOutrasOperacoes(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes = new NFNotaInfoItemImpostoPISOutrasOperacoes();
	Date a = new Date();
	nfnotainfoitemimpostopisoutrasoperacoes.setId(id);
	nfnotainfoitemimpostopisoutrasoperacoes.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostopisoutrasoperacoes.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisoutrasoperacoes.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisoutrasoperacoes.setQuantidadeVendida("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisoutrasoperacoes.setValorAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisoutrasoperacoes.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisoutrasoperacoes.setParentId(id);
	nfnotainfoitemimpostopisoutrasoperacoes.setEmprId(1);
	nfnotainfoitemimpostopisoutrasoperacoes.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostopisoutrasoperacoes.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostopisoutrasoperacoes.setCreateUser("system");
	nfnotainfoitemimpostopisoutrasoperacoes.setModifyUser("system");
	nfnotainfoitemimpostopisoutrasoperacoes.setProcessId(1);
	nfnotainfoitemimpostopisoutrasoperacoes.setModelAction(action);

	return nfnotainfoitemimpostopisoutrasoperacoes;
}

public static NFNotaInfoItemImpostoPISST insertNFNotaInfoItemImpostoPISST(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst = new NFNotaInfoItemImpostoPISST();
	Date a = new Date();
	nfnotainfoitemimpostopisst.setId(id);
	nfnotainfoitemimpostopisst.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisst.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisst.setQuantidadeVendida("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisst.setValorAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisst.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostopisst.setParentId(id);
	nfnotainfoitemimpostopisst.setEmprId(1);
	nfnotainfoitemimpostopisst.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostopisst.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostopisst.setCreateUser("system");
	nfnotainfoitemimpostopisst.setModifyUser("system");
	nfnotainfoitemimpostopisst.setProcessId(1);
	nfnotainfoitemimpostopisst.setModelAction(action);

	return nfnotainfoitemimpostopisst;
}

public static NFNotaInfoItemImpostoCOFINS insertNFNotaInfoItemImpostoCOFINS(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins = new NFNotaInfoItemImpostoCOFINS();
	Date a = new Date();
	nfnotainfoitemimpostocofins.setId(id);
	nfnotainfoitemimpostocofins.setAliquota(insertNFNotaInfoItemImpostoCOFINSAliquota(null, tabela, action));
	nfnotainfoitemimpostocofins.setQuantidade(insertNFNotaInfoItemImpostoCOFINSQuantidade(null, tabela, action));
	nfnotainfoitemimpostocofins.setNaoTributavel(insertNFNotaInfoItemImpostoCOFINSNaoTributavel(null, tabela, action));
	nfnotainfoitemimpostocofins.setOutrasOperacoes(insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(null, tabela, action));
	nfnotainfoitemimpostocofins.setParentId(id);
	nfnotainfoitemimpostocofins.setEmprId(1);
	nfnotainfoitemimpostocofins.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostocofins.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostocofins.setCreateUser("system");
	nfnotainfoitemimpostocofins.setModifyUser("system");
	nfnotainfoitemimpostocofins.setProcessId(1);
	nfnotainfoitemimpostocofins.setModelAction(action);

	return nfnotainfoitemimpostocofins;
}

public static NFNotaInfoItemImpostoCOFINSAliquota insertNFNotaInfoItemImpostoCOFINSAliquota(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota = new NFNotaInfoItemImpostoCOFINSAliquota();
	Date a = new Date();
	nfnotainfoitemimpostocofinsaliquota.setId(id);
	nfnotainfoitemimpostocofinsaliquota.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostocofinsaliquota.setValorBaseCalulo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsaliquota.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsaliquota.setValor("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsaliquota.setParentId(id);
	nfnotainfoitemimpostocofinsaliquota.setEmprId(1);
	nfnotainfoitemimpostocofinsaliquota.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsaliquota.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsaliquota.setCreateUser("system");
	nfnotainfoitemimpostocofinsaliquota.setModifyUser("system");
	nfnotainfoitemimpostocofinsaliquota.setProcessId(1);
	nfnotainfoitemimpostocofinsaliquota.setModelAction(action);

	return nfnotainfoitemimpostocofinsaliquota;
}

public static NFNotaInfoItemImpostoCOFINSQuantidade insertNFNotaInfoItemImpostoCOFINSQuantidade(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade = new NFNotaInfoItemImpostoCOFINSQuantidade();
	Date a = new Date();
	nfnotainfoitemimpostocofinsquantidade.setId(id);
	nfnotainfoitemimpostocofinsquantidade.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostocofinsquantidade.setQuantidadeVendida("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsquantidade.setValorAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsquantidade.setValorTributo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsquantidade.setParentId(id);
	nfnotainfoitemimpostocofinsquantidade.setEmprId(1);
	nfnotainfoitemimpostocofinsquantidade.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsquantidade.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsquantidade.setCreateUser("system");
	nfnotainfoitemimpostocofinsquantidade.setModifyUser("system");
	nfnotainfoitemimpostocofinsquantidade.setProcessId(1);
	nfnotainfoitemimpostocofinsquantidade.setModelAction(action);

	return nfnotainfoitemimpostocofinsquantidade;
}

public static NFNotaInfoItemImpostoCOFINSNaoTributavel insertNFNotaInfoItemImpostoCOFINSNaoTributavel(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel = new NFNotaInfoItemImpostoCOFINSNaoTributavel();
	Date a = new Date();
	nfnotainfoitemimpostocofinsnaotributavel.setId(id);
	nfnotainfoitemimpostocofinsnaotributavel.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostocofinsnaotributavel.setParentId(id);
	nfnotainfoitemimpostocofinsnaotributavel.setEmprId(1);
	nfnotainfoitemimpostocofinsnaotributavel.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsnaotributavel.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsnaotributavel.setCreateUser("system");
	nfnotainfoitemimpostocofinsnaotributavel.setModifyUser("system");
	nfnotainfoitemimpostocofinsnaotributavel.setProcessId(1);
	nfnotainfoitemimpostocofinsnaotributavel.setModelAction(action);

	return nfnotainfoitemimpostocofinsnaotributavel;
}

public static NFNotaInfoItemImpostoCOFINSOutrasOperacoes insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(Integer id,
		TabelaEnum tabela, PersistenceActionEnum action) {
	NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes = new NFNotaInfoItemImpostoCOFINSOutrasOperacoes();
	Date a = new Date();
	nfnotainfoitemimpostocofinsoutrasoperacoes.setId(id);
	nfnotainfoitemimpostocofinsoutrasoperacoes.setSituacaoTributaria(insertDoisValor(id, tabela, action));
	nfnotainfoitemimpostocofinsoutrasoperacoes.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsoutrasoperacoes.setPercentualCOFINS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsoutrasoperacoes.setQuantidadeVendida("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsoutrasoperacoes.setValorAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsoutrasoperacoes.setValorCOFINS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsoutrasoperacoes.setParentId(id);
	nfnotainfoitemimpostocofinsoutrasoperacoes.setEmprId(1);
	nfnotainfoitemimpostocofinsoutrasoperacoes.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsoutrasoperacoes.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsoutrasoperacoes.setCreateUser("system");
	nfnotainfoitemimpostocofinsoutrasoperacoes.setModifyUser("system");
	nfnotainfoitemimpostocofinsoutrasoperacoes.setProcessId(1);
	nfnotainfoitemimpostocofinsoutrasoperacoes.setModelAction(action);

	return nfnotainfoitemimpostocofinsoutrasoperacoes;
}

public static NFNotaInfoItemImpostoCOFINSST insertNFNotaInfoItemImpostoCOFINSST(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst = new NFNotaInfoItemImpostoCOFINSST();
	Date a = new Date();
	nfnotainfoitemimpostocofinsst.setId(id);
	nfnotainfoitemimpostocofinsst.setValorBaseCalculo("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsst.setPercentualAliquota("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsst.setQuantidadeVendida("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsst.setValorAliquotaCOFINS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsst.setValorCOFINS("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostocofinsst.setParentId(id);
	nfnotainfoitemimpostocofinsst.setEmprId(1);
	nfnotainfoitemimpostocofinsst.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsst.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostocofinsst.setCreateUser("system");
	nfnotainfoitemimpostocofinsst.setModifyUser("system");
	nfnotainfoitemimpostocofinsst.setProcessId(1);
	nfnotainfoitemimpostocofinsst.setModelAction(action);

	return nfnotainfoitemimpostocofinsst;
}

public static NFNotaInfoItemImpostoICMSUFDestino insertNFNotaInfoItemImpostoICMSUFDestino(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino = new NFNotaInfoItemImpostoICMSUFDestino();
	Date a = new Date();
	nfnotainfoitemimpostoicmsufdestino.setId(id);
	nfnotainfoitemimpostoicmsufdestino.setValorBaseCalculoDestino("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setPercentualRelativoFundoCombatePobrezaDestino("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setPercentualAliquotaInternaDestino("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setPercentualInterestadual("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setPercentualProvisorioPartilha("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setValorRelativoFundoCombatePobrezaDestino("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setValorICMSInterestadualDestino("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setValorICMSInterestadualRemetente("NATIVE INSERT UPDATE");
	nfnotainfoitemimpostoicmsufdestino.setParentId(id);
	nfnotainfoitemimpostoicmsufdestino.setEmprId(1);
	nfnotainfoitemimpostoicmsufdestino.setModifyDateUTC(a.getTime());
	nfnotainfoitemimpostoicmsufdestino.setCreateDateUTC(a.getTime());
	nfnotainfoitemimpostoicmsufdestino.setCreateUser("system");
	nfnotainfoitemimpostoicmsufdestino.setModifyUser("system");
	nfnotainfoitemimpostoicmsufdestino.setProcessId(1);
	nfnotainfoitemimpostoicmsufdestino.setModelAction(action);

	return nfnotainfoitemimpostoicmsufdestino;
}

public static NFImpostoDevolvido insertNFImpostoDevolvido(Integer id, TabelaEnum tabela, PersistenceActionEnum action) {
	NFImpostoDevolvido nfimpostodevolvido = new NFImpostoDevolvido();
	Date a = new Date();
	nfimpostodevolvido.setId(id);
	nfimpostodevolvido.setPercentualDevolucao("NATIVE INSERT UPDATE");
	nfimpostodevolvido.setInformacaoIPIDevolvido(insertNFInformacaoImpostoDevolvido(null, tabela, action));
	nfimpostodevolvido.setParentId(id);
	nfimpostodevolvido.setEmprId(1);
	nfimpostodevolvido.setModifyDateUTC(a.getTime());
	nfimpostodevolvido.setCreateDateUTC(a.getTime());
	nfimpostodevolvido.setCreateUser("system");
	nfimpostodevolvido.setModifyUser("system");
	nfimpostodevolvido.setProcessId(1);
	nfimpostodevolvido.setModelAction(action);

	return nfimpostodevolvido;
}

public static NFInformacaoImpostoDevolvido insertNFInformacaoImpostoDevolvido(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = new NFInformacaoImpostoDevolvido();
	Date a = new Date();
	nfinformacaoimpostodevolvido.setId(id);
	nfinformacaoimpostodevolvido.setValorIPIDevolvido("NATIVE INSERT UPDATE");
	nfinformacaoimpostodevolvido.setParentId(id);
	nfinformacaoimpostodevolvido.setEmprId(1);
	nfinformacaoimpostodevolvido.setModifyDateUTC(a.getTime());
	nfinformacaoimpostodevolvido.setCreateDateUTC(a.getTime());
	nfinformacaoimpostodevolvido.setCreateUser("system");
	nfinformacaoimpostodevolvido.setModifyUser("system");
	nfinformacaoimpostodevolvido.setProcessId(1);
	nfinformacaoimpostodevolvido.setModelAction(action);

	return nfinformacaoimpostodevolvido;
}

public static Conta insertConta(Integer id, TabelaEnum tabela,
		PersistenceActionEnum action) {
	Conta conta = new Conta();
	Date a = new Date();
	conta.setId(id);
	conta.setDescricao("NATIVE INSERT UPDATE");
	conta.setSaldo(new Double(1.99));
	conta.setDataUltLanc(a.getTime());
//	conta.setListBaixa(new ArrayList<BaixaTitulo>());
//	conta.getListBaixa().add(insertBaixaTitulo(id, tabela, action));
	conta.setTipoConta(insertDoisValor(id, tabela, action));
	conta.setEmprId(1);
	conta.setModifyDateUTC(a.getTime());
	conta.setCreateDateUTC(a.getTime());
	conta.setCreateUser("system");
	conta.setModifyUser("system");
	conta.setProcessId(1);
	conta.setModelAction(action);

	return conta;
}



public static Cnae cnae(int i, String string, PersistenceActionEnum action) {

	Cnae cnae = new Cnae();
	Date a = new Date();
	cnae.setId(i);
	cnae.setCodigo("5555");
	cnae.setCnae("5555");
	cnae.setDescricao("5555");
	cnae.setAbreviado("5555");
	cnae.setEmprId(1);
	cnae.setModifyDateUTC(a.getTime());
	cnae.setCreateDateUTC(a.getTime());
	cnae.setCreateUser("system");
	cnae.setModifyUser("system");
	cnae.setProcessId(1);
	cnae.setModelAction(action);

	return cnae;
}


}
