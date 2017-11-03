package com.qat.samples.sysmgmt.util.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

// TODO: Auto-generated Javadoc
/**
 * The Enum RiskLevelEnum.
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:07 AM
 */
public enum TabelaEnum implements IIntegerEnum, II18nEnum
{

	/**
	 * The high.
	 *
	 * REGIME
	 * EMPRESA
	 * DOCUMENTO
	 * EMAIL
	 * ENDERECO
	 * TELEFONE
	 * SOCIO
	 * HISTORICO
	 * CNAE
	 * CSOSN
	 * NCM
	 * CIDADE
	 * CNAEPORRELACIONAMENTO
	 * ESTADO
	 * UNIMED
	 * CFOP
	 * FUNCIONARIO
	 * SALARIO
	 */

	REGIME(0, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	EMPRESA(1, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	DOCUMENTO(2, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	EMAIL(3, "sendsolv.base.model.risklevelenum.unknown"),

	/** The endereco. */
	ENDERECO(4, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	TELEFONE(5, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	SOCIO(6, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	HISTORICO(7, "sendsolv.base.model.risklevelenum.unknown"),

	/** The cnae. */
	CNAE(8, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	CSOSN(9, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	NCM(10, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	CIDADE(11, "sendsolv.base.model.risklevelenum.unknown"),

	/** The cnaeporrelacionamento. */
	CNAEPORRELACIONAMENTO(12, "sendsolv.base.model.risklevelenum.high"),

	/** The estado. */
	ESTADO(13, "sendsolv.base.model.risklevelenum.unknown"),

	/** The unimed. */
	UNIMED(14, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	CFOP(15, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	FUNCIONARIO(16, "sendsolv.base.model.risklevelenum.medium"),

	CLIENTE(17, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	FORNECEDOR(18, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	TRANSPORTADOR(19, "sendsolv.base.model.risklevelenum.medium"),

	CONDPAG(20, "sendsolv.base.model.risklevelenum.medium"),

	CONVENIO(21, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	SALARIO(22, "sendsolv.base.model.risklevelenum.unknown"),

	PESSOA(23, "sendsolv.base.model.risklevelenum.medium"),

	PROFISSAO(24, "sendsolv.base.model.risklevelenum.medium"),

	CONVENIOPESSOA(25, "sendsolv.base.model.risklevelenum.medium"),

	CONDPAGPESSOA(26, "sendsolv.base.model.risklevelenum.medium"),

	TIPOPAG(27, "sendsolv.base.model.risklevelenum.medium"),

	TIPOPAGREG(28, "sendsolv.base.model.risklevelenum.medium"),

	BANCO(29, "sendsolv.base.model.risklevelenum.medium"),

	AGENCIA(30, "sendsolv.base.model.risklevelenum.medium"),

	BANCOPESOA(31, "sendsolv.base.model.risklevelenum.medium"),

	CONTATO(32, "sendsolv.base.model.risklevelenum.medium"),

	CONTATOITENS(33, "sendsolv.base.model.risklevelenum.medium"),

	ORDEMSERVICO(34, "sendsolv.base.model.risklevelenum.medium"),

	ORDEMSERVICOITENS(35, "sendsolv.base.model.risklevelenum.medium"),

	ORDEMSERVICOTYPES(36, "sendsolv.base.model.risklevelenum.medium"),

	ORDEMSERVICOSTATUS(37, "sendsolv.base.model.risklevelenum.medium"),

	TABELA(38, "sendsolv.base.model.risklevelenum.medium"),

	ATRIBUTOS(39, "sendsolv.base.model.risklevelenum.medium"),

	EVENTOS(40, "sendsolv.base.model.risklevelenum.medium"),

	BENEFICIOS(41, "sendsolv.base.model.risklevelenum.medium"),

	CUSTO(42, "sendsolv.base.model.risklevelenum.medium"),

	NOTE(43, "sendsolv.base.model.risklevelenum.medium"),

	HORAFUNC(44, "sendsolv.base.model.risklevelenum.medium"),

	PRODUTO(45, "sendsolv.base.model.risklevelenum.medium"),

	NOTAFISCAL(46, "sendsolv.base.model.risklevelenum.medium"),

	ORCAMENTO(47, "sendsolv.base.model.risklevelenum.medium"),

	PEDIDOCOMPRAS(48, "sendsolv.base.model.risklevelenum.medium"),

	CONTAS(49, "sendsolv.base.model.risklevelenum.medium"),

	MARCA(50, "sendsolv.base.model.risklevelenum.medium"),

	DEPOSITO(51, "sendsolv.base.model.risklevelenum.medium"),

	FILIAL(52, "sendsolv.base.model.risklevelenum.medium"),

	SERVICO(53, "sendsolv.base.model.risklevelenum.medium"),

	PLANO(54, "sendsolv.base.model.risklevelenum.medium"),
	MEDICO(55, "sendsolv.base.model.risklevelenum.medium"),
	PACIENTE(56, "sendsolv.base.model.risklevelenum.medium"),
	EXAME(57, "sendsolv.base.model.risklevelenum.medium"),
	PLANOSAUDE(58, "sendsolv.base.model.risklevelenum.medium"),
	CONSULTA(59, "sendsolv.base.model.risklevelenum.medium"),

	USUARIO(60, "sendsolv.base.model.risklevelenum.medium"),

	CONDOMINIO(61, "sendsolv.base.model.risklevelenum.medium"),

	ADVOCACIA(62, "sendsolv.base.model.risklevelenum.medium"),

	CLINICA(63, "sendsolv.base.model.risklevelenum.medium"),

	SITE(64, "sendsolv.base.model.risklevelenum.medium"),

	CONTASPAGAR(65, "sendsolv.base.model.risklevelenum.medium"),

	CONTASRECEBER(66, "sendsolv.base.model.risklevelenum.medium"),

	BAIXARTITULO(67, "sendsolv.base.model.risklevelenum.medium"),

	TIPOBAIXA(68, "sendsolv.base.model.risklevelenum.medium"),

	TITULO(69, "sendsolv.base.model.risklevelenum.medium"),

	FORMAPG(70, "sendsolv.base.model.risklevelenum.medium"),

	CONTACORRENTE(71, "sendsolv.base.model.risklevelenum.medium"),

	CAIXA(72, "sendsolv.base.model.risklevelenum.medium"),
	PRODUTOPARENT(72, "sendsolv.base.model.risklevelenum.medium"),

	ADVOGADO(74, "sendsolv.base.model.risklevelenum.medium"),

	MARCAPRODUTO(73, "sendsolv.base.model.risklevelenum.medium"),

	GRUPO(74, "sendsolv.base.model.risklevelenum.medium"),

	SUBGRUPO(75, "sendsolv.base.model.risklevelenum.medium"),

	CUSTOITENS(76, "sendsolv.base.model.risklevelenum.medium"),

	ESTOQUE(77, "sendsolv.base.model.risklevelenum.medium"),

	PORCAO(78, "sendsolv.base.model.risklevelenum.medium"),

	PORCAOITENS(79, "sendsolv.base.model.risklevelenum.medium"),

	RENTABILIDADE(80, "sendsolv.base.model.risklevelenum.medium"),

	RENTABILIDADEITENS(81, "sendsolv.base.model.risklevelenum.medium"),

	TRIBUTACAO(82, "sendsolv.base.model.risklevelenum.medium"),


	BOLETO(83, "sendsolv.base.model.risklevelenum.medium"),

	GRUPOTRABALHO(85, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGALERTAS(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGCARNE(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGENTRADA(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGFISCAL(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGGERAL(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGPRODUTO(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGSMTP(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGURACAO(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGURACAONFE(73, "sendsolv.base.model.risklevelenum.medium"),

	DOISVALOR(73, "sendsolv.base.model.risklevelenum.medium"),

	SINDICO(73, "sendsolv.base.model.risklevelenum.medium"),

	INQUILINO(73, "sendsolv.base.model.risklevelenum.medium"),

	AUDIENCIA(73, "sendsolv.base.model.risklevelenum.medium"),

	PROCESSO(73, "sendsolv.base.model.risklevelenum.medium"),

	BAIXATITULO(73, "sendsolv.base.model.risklevelenum.medium"),

	COTACAO(73, "sendsolv.base.model.risklevelenum.medium"),

	AVISO(73, "sendsolv.base.model.risklevelenum.medium"),

	CLASSES(73, "sendsolv.base.model.risklevelenum.medium"),

	INTERFACE(73, "sendsolv.base.model.risklevelenum.medium"),

	FIELD(73, "sendsolv.base.model.risklevelenum.medium"),

	PLANOBYEMPRESA(73, "sendsolv.base.model.risklevelenum.medium"),

	CONFIGVENDAS(73, "sendsolv.base.model.risklevelenum.medium"),

	ICMS(73, "sendsolv.base.model.risklevelenum.medium"),

	PIS(73, "sendsolv.base.model.risklevelenum.medium"),

	IPI(73, "sendsolv.base.model.risklevelenum.medium"),

	COFINS(73, "sendsolv.base.model.risklevelenum.medium"),

	CATEGORIA(73, "sendsolv.base.model.risklevelenum.medium"),

	PRODUTOEMPRESA(73, "sendsolv.base.model.risklevelenum.medium"),

	ROLE(73, "sendsolv.base.model.risklevelenum.medium"),

	PAGINA(73, "sendsolv.base.model.risklevelenum.medium"),

	VALIDACAO(73, "sendsolv.base.model.risklevelenum.medium"),

	AJUDA(73, "sendsolv.base.model.risklevelenum.medium"),

	MENU(73, "sendsolv.base.model.risklevelenum.medium"),

	USERROLES(73, "sendsolv.base.model.risklevelenum.medium"),

	CONHECIMENTOTRANSPORTE(73, "sendsolv.base.model.risklevelenum.medium"),

	NOTAFISCALITENS(73, "sendsolv.base.model.risklevelenum.medium"),

	NFNOTA(73, "sendsolv.base.model.risklevelenum.medium"),

	NFNOTAINFO(73, "sendsolv.base.model.risklevelenum.medium"),

	NFNOTAINFOIDENTIFICACAO(73, "sendsolv.base.model.risklevelenum.medium"),

	NFINFOMODELO1POR1AREFERENCIADA(73, "sendsolv.base.model.risklevelenum.medium"),

	NFINFOREFERENCIADA(73, "sendsolv.base.model.risklevelenum.medium"),

	NFINFOPRODUTORRURALREFERENCIADA(73, "sendsolv.base.model.risklevelenum.medium"),

	NFNOTAINFOEMITENTE(73, "sendsolv.base.model.risklevelenum.medium"),

	NFNOTAINFOAVULSA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFODESTINATARIO(73, "sendsolv.base.model.risklevelenum.medium"),

	NFNOTAINFOLOCAL(73, "sendsolv.base.model.risklevelenum.medium"),
	NFPESSOAAUTORIZADADOWNLOADNFE(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOTOTAL(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOICMSTOTAL(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOISSQNTOTAL(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFORETENCOESTRIBUTOS(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOTRANSPORTE(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFORETENCAOICMSTRANSPORTE(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOTRANSPORTADOR(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOVEICULO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOREBOQUE(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOCOBRANCA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFODUPLICATA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOFATURA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOCARTAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOPAGAMENTO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOINFORMACOESADICIONAIS(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOOBSERVACAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOPROCESSOREFERENCIADO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOEXPORTACAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOCOMPRA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOCANAFORNECIMENTODIARIO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOCANA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOCANADEDUCAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOSUPLEMENTAR(73, "sendsolv.base.model.risklevelenum.medium"),
	NFINFOCUPOMFISCALREFERENCIADO(73, "sendsolv.base.model.risklevelenum.medium"),

	NOTAFISCALSAIDA(73, "sendsolv.base.model.risklevelenum.medium"),
	ICMSOPINTER(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEM(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMDETALHEEXPORTACAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMEXPORTACAOINDIRETA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTOVEICULO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTOMEDICAMENTO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTOARMAMENTO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE(73, "sendsolv.base.model.risklevelenum.medium"),
	NFINFORMACAOIMPOSTODEVOLVIDO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS00(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS10(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS20(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS30(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS40(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS51(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS60(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS70(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMS90(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSPARTILHADO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSST(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSSN101(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSSN102(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSSN201(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSSN202(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSSN500(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSSN900(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOIPI(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOIPITRIBUTADO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOIMPORTACAO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOISSQN(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOPIS(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOPISALIQUOTA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOPISQUANTIDADE(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOPISST(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOCOFINS(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOCOFINSST(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMIMPOSTOICMSUFDESTINO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFIMPOSTODEVOLVIDO(73, "sendsolv.base.model.risklevelenum.medium"),
	NFNOTAINFOITEMPRODUTOCOMBUSTIVEL(73, "sendsolv.base.model.risklevelenum.medium"),
	PROCESSOSTATUS(73, "sendsolv.base.model.risklevelenum.medium"),
	DIASHORAS(73, "sendsolv.base.model.risklevelenum.medium"),
	ESPECIALIDADE(86, "sendsolv.base.model.risklevelenum.medium"),
	COMPROMISSO(73, "sendsolv.base.model.risklevelenum.medium"),
	ADVOGADOS(73, "sendsolv.base.model.risklevelenum.medium"),
	ENVOLVIDOS(73, "sendsolv.base.model.risklevelenum.medium"),
	PARTICIPANTE(73, "sendsolv.base.model.risklevelenum.medium"),
	PARTICIPANTEEXTERNO(73, "sendsolv.base.model.risklevelenum.medium"),
	CLIENTECOMPROMISSO(73, "sendsolv.base.model.risklevelenum.medium"),
	ARQUIVO(73, "sendsolv.base.model.risklevelenum.medium"),
	PROCESSOUSUARIOS(73, "sendsolv.base.model.risklevelenum.medium"),
	PROCESSOCLIENTE(73, "sendsolv.base.model.risklevelenum.medium"),
	CONTA(103, "sendsolv.base.model.risklevelenum.medium"),
	DIASSEMANA(104, "sendsolv.base.model.risklevelenum.medium");


	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value the value
	 * @param labelKeyParam the label key param
	 */
	private TabelaEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the process status enum
	 */
	public static TabelaEnum enumForValue(Integer value)
	{
		for (TabelaEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 *
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (TabelaEnum i : TabelaEnum.class.getEnumConstants())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.II18nEnum#getLabelKey()
	 */
	/**
	 * Gets the label key.
	 *
	 * @return the label key
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}
}