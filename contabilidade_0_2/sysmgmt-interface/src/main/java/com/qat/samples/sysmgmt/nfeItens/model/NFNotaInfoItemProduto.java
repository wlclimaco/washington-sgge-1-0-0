/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProduto extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemProduto. */
	private Integer id;

	/** The econtabil codigo for the NFNotaInfoItemProduto. */
	private String codigo;

	/** The econtabil codigoDeBarras for the NFNotaInfoItemProduto. */
	private String codigoDeBarras;

	/** The econtabil descricao for the NFNotaInfoItemProduto. */
	private String descricao;

	/** The econtabil ncm for the NFNotaInfoItemProduto. */
	private String ncm;

	/**
	 * The econtabil nomeclaturaValorAduaneiroEstatistica for the
	 * NFNotaInfoItemProduto.
	 */
	private String nomeclaturaValorAduaneiroEstatistica;

	/**
	 * The econtabil codigoEspecificadorSituacaoTributaria for the
	 * NFNotaInfoItemProduto.
	 */
	private String codigoEspecificadorSituacaoTributaria;

	/** The econtabil extipi for the NFNotaInfoItemProduto. */
	private String extipi;

	/** The econtabil cfop for the NFNotaInfoItemProduto. */
	private String cfop;

	/** The econtabil unidadeComercial for the NFNotaInfoItemProduto. */
	private String unidadeComercial;

	/** The econtabil quantidadeComercial for the NFNotaInfoItemProduto. */
	private String quantidadeComercial;

	/** The econtabil valorUnitario for the NFNotaInfoItemProduto. */
	private String valorUnitario;

	/** The econtabil valorTotalBruto for the NFNotaInfoItemProduto. */
	private String valorTotalBruto;

	/** The econtabil codigoDeBarrasTributavel for the NFNotaInfoItemProduto. */
	private String codigoDeBarrasTributavel;

	/** The econtabil unidadeTributavel for the NFNotaInfoItemProduto. */
	private String unidadeTributavel;

	/** The econtabil quantidadeTributavel for the NFNotaInfoItemProduto. */
	private String quantidadeTributavel;

	/** The econtabil valorUnitarioTributavel for the NFNotaInfoItemProduto. */
	private String valorUnitarioTributavel;

	/** The econtabil valorFrete for the NFNotaInfoItemProduto. */
	private String valorFrete;

	/** The econtabil valorSeguro for the NFNotaInfoItemProduto. */
	private String valorSeguro;

	/** The econtabil valorDesconto for the NFNotaInfoItemProduto. */
	private String valorDesconto;

	/**
	 * The econtabil valorOutrasDespesasAcessorias for the
	 * NFNotaInfoItemProduto.
	 */
	private String valorOutrasDespesasAcessorias;

	/** The econtabil compoeValorNota for the NFNotaInfoItemProduto. */
	private DoisValores compoeValorNota;

	/** The econtabil declaracoesImportacao for the NFNotaInfoItemProduto. */
	private List<NFNotaInfoItemProdutoDeclaracaoImportacao> declaracoesImportacao;

	/** The econtabil detalhesExportacao for the NFNotaInfoItemProduto. */
	private List<NFNotaInfoItemDetalheExportacao> detalhesExportacao;

	/** The econtabil numeroPedidoCliente for the NFNotaInfoItemProduto. */
	private String numeroPedidoCliente;

	/** The econtabil numeroPedidoItemCliente for the NFNotaInfoItemProduto. */
	private Integer numeroPedidoItemCliente;

	/** The econtabil numeroControleFCI for the NFNotaInfoItemProduto. */
	private String numeroControleFCI;

	/** The econtabil veiculo for the NFNotaInfoItemProduto. */
	private NFNotaInfoItemProdutoVeiculo veiculo;

	/** The econtabil medicamentos for the NFNotaInfoItemProduto. */
	private List<NFNotaInfoItemProdutoMedicamento> medicamentos;

	/** The econtabil armamentos for the NFNotaInfoItemProduto. */
	private List<NFNotaInfoItemProdutoArmamento> armamentos;

	/** The econtabil combustivel for the NFNotaInfoItemProduto. */
	private NFNotaInfoItemProdutoCombustivel combustivel;

	/** The econtabil numeroRECOPI for the NFNotaInfoItemProduto. */
	private String numeroRECOPI;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProduto() {
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param id
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codigoDeBarras.
	 *
	 * @return the codigoDeBarras
	 */
	/**
	 * Gets the codigoDeBarras.
	 *
	 * @return the codigoDeBarras
	 */
	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	/**
	 * Sets the codigodebarras.
	 *
	 * @param id
	 *            the codigodebarras to set
	 */
	public void setCodigoDeBarras(String codigodebarras) {
		this.codigoDeBarras = codigodebarras;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param id
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the ncm.
	 *
	 * @return the ncm
	 */
	/**
	 * Gets the ncm.
	 *
	 * @return the ncm
	 */
	public String getNcm() {
		return ncm;
	}

	/**
	 * Sets the ncm.
	 *
	 * @param id
	 *            the ncm to set
	 */
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	/**
	 * Gets the nomeclaturaValorAduaneiroEstatistica.
	 *
	 * @return the nomeclaturaValorAduaneiroEstatistica
	 */
	/**
	 * Gets the nomeclaturavaloraduaneiroestatistica.
	 *
	 * @return the nomeclaturavaloraduaneiroestatistica
	 */
	public String getNomeclaturaValorAduaneiroEstatistica() {
		return nomeclaturaValorAduaneiroEstatistica;
	}

	/**
	 * Sets the nomeclaturavaloraduaneiroestatistica.
	 *
	 * @param id
	 *            the nomeclaturavaloraduaneiroestatistica to set
	 */
	public void setNomeclaturaValorAduaneiroEstatistica(String nomeclaturavaloraduaneiroestatistica) {
		this.nomeclaturaValorAduaneiroEstatistica = nomeclaturavaloraduaneiroestatistica;
	}

	/**
	 * Gets the codigoEspecificadorSituacaoTributaria.
	 *
	 * @return the codigoEspecificadorSituacaoTributaria
	 */
	/**
	 * Gets the codigoEspecificadorSituacaoTributaria.
	 *
	 * @return the codigoEspecificadorSituacaoTributaria
	 */
	public String getCodigoEspecificadorSituacaoTributaria() {
		return codigoEspecificadorSituacaoTributaria;
	}

	/**
	 * Sets the codigoespecificadorsituacaotributaria.
	 *
	 * @param id
	 *            the codigoespecificadorsituacaotributaria to set
	 */
	public void setCodigoEspecificadorSituacaoTributaria(String codigoespecificadorsituacaotributaria) {
		this.codigoEspecificadorSituacaoTributaria = codigoespecificadorsituacaotributaria;
	}

	/**
	 * Gets the extipi.
	 *
	 * @return the extipi
	 */
	/**
	 * Gets the extipi.
	 *
	 * @return the extipi
	 */
	public String getExtipi() {
		return extipi;
	}

	/**
	 * Sets the extipi.
	 *
	 * @param id
	 *            the extipi to set
	 */
	public void setExtipi(String extipi) {
		this.extipi = extipi;
	}

	/**
	 * Gets the cfop.
	 *
	 * @return the cfop
	 */
	/**
	 * Gets the cfop.
	 *
	 * @return the cfop
	 */
	public String getCfop() {
		return cfop;
	}

	/**
	 * Sets the cfop.
	 *
	 * @param id
	 *            the cfop to set
	 */
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	/**
	 * Gets the unidadeComercial.
	 *
	 * @return the unidadeComercial
	 */
	/**
	 * Gets the unidadeComercial.
	 *
	 * @return the unidadeComercial
	 */
	public String getUnidadeComercial() {
		return unidadeComercial;
	}

	/**
	 * Sets the unidadecomercial.
	 *
	 * @param id
	 *            the unidadecomercial to set
	 */
	public void setUnidadeComercial(String unidadecomercial) {
		this.unidadeComercial = unidadecomercial;
	}

	/**
	 * Gets the quantidadeComercial.
	 *
	 * @return the quantidadeComercial
	 */
	/**
	 * Gets the quantidadeComercial.
	 *
	 * @return the quantidadeComercial
	 */
	public String getQuantidadeComercial() {
		return quantidadeComercial;
	}

	/**
	 * Sets the quantidadecomercial.
	 *
	 * @param id
	 *            the quantidadecomercial to set
	 */
	public void setQuantidadeComercial(String quantidadecomercial) {
		this.quantidadeComercial = quantidadecomercial;
	}

	/**
	 * Gets the valorUnitario.
	 *
	 * @return the valorUnitario
	 */
	/**
	 * Gets the valorUnitario.
	 *
	 * @return the valorUnitario
	 */
	public String getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * Sets the valorunitario.
	 *
	 * @param id
	 *            the valorunitario to set
	 */
	public void setValorUnitario(String valorunitario) {
		this.valorUnitario = valorunitario;
	}

	/**
	 * Gets the valorTotalBruto.
	 *
	 * @return the valorTotalBruto
	 */
	/**
	 * Gets the valorTotalBruto.
	 *
	 * @return the valorTotalBruto
	 */
	public String getValorTotalBruto() {
		return valorTotalBruto;
	}

	/**
	 * Sets the valortotalbruto.
	 *
	 * @param id
	 *            the valortotalbruto to set
	 */
	public void setValorTotalBruto(String valortotalbruto) {
		this.valorTotalBruto = valortotalbruto;
	}

	/**
	 * Gets the codigoDeBarrasTributavel.
	 *
	 * @return the codigoDeBarrasTributavel
	 */
	/**
	 * Gets the codigoDeBarrasTributavel.
	 *
	 * @return the codigoDeBarrasTributavel
	 */
	public String getCodigoDeBarrasTributavel() {
		return codigoDeBarrasTributavel;
	}

	/**
	 * Sets the codigodebarrastributavel.
	 *
	 * @param id
	 *            the codigodebarrastributavel to set
	 */
	public void setCodigoDeBarrasTributavel(String codigodebarrastributavel) {
		this.codigoDeBarrasTributavel = codigodebarrastributavel;
	}

	/**
	 * Gets the unidadeTributavel.
	 *
	 * @return the unidadeTributavel
	 */
	/**
	 * Gets the unidadeTributavel.
	 *
	 * @return the unidadeTributavel
	 */
	public String getUnidadeTributavel() {
		return unidadeTributavel;
	}

	/**
	 * Sets the unidadetributavel.
	 *
	 * @param id
	 *            the unidadetributavel to set
	 */
	public void setUnidadeTributavel(String unidadetributavel) {
		this.unidadeTributavel = unidadetributavel;
	}

	/**
	 * Gets the quantidadeTributavel.
	 *
	 * @return the quantidadeTributavel
	 */
	/**
	 * Gets the quantidadeTributavel.
	 *
	 * @return the quantidadeTributavel
	 */
	public String getQuantidadeTributavel() {
		return quantidadeTributavel;
	}

	/**
	 * Sets the quantidadetributavel.
	 *
	 * @param id
	 *            the quantidadetributavel to set
	 */
	public void setQuantidadeTributavel(String quantidadetributavel) {
		this.quantidadeTributavel = quantidadetributavel;
	}

	/**
	 * Gets the valorUnitarioTributavel.
	 *
	 * @return the valorUnitarioTributavel
	 */
	/**
	 * Gets the valorUnitarioTributavel.
	 *
	 * @return the valorUnitarioTributavel
	 */
	public String getValorUnitarioTributavel() {
		return valorUnitarioTributavel;
	}

	/**
	 * Sets the valorunitariotributavel.
	 *
	 * @param id
	 *            the valorunitariotributavel to set
	 */
	public void setValorUnitarioTributavel(String valorunitariotributavel) {
		this.valorUnitarioTributavel = valorunitariotributavel;
	}

	/**
	 * Gets the valorFrete.
	 *
	 * @return the valorFrete
	 */
	/**
	 * Gets the valorFrete.
	 *
	 * @return the valorFrete
	 */
	public String getValorFrete() {
		return valorFrete;
	}

	/**
	 * Sets the valorfrete.
	 *
	 * @param id
	 *            the valorfrete to set
	 */
	public void setValorFrete(String valorfrete) {
		this.valorFrete = valorfrete;
	}

	/**
	 * Gets the valorSeguro.
	 *
	 * @return the valorSeguro
	 */
	/**
	 * Gets the valorSeguro.
	 *
	 * @return the valorSeguro
	 */
	public String getValorSeguro() {
		return valorSeguro;
	}

	/**
	 * Sets the valorseguro.
	 *
	 * @param id
	 *            the valorseguro to set
	 */
	public void setValorSeguro(String valorseguro) {
		this.valorSeguro = valorseguro;
	}

	/**
	 * Gets the valorDesconto.
	 *
	 * @return the valorDesconto
	 */
	/**
	 * Gets the valorDesconto.
	 *
	 * @return the valorDesconto
	 */
	public String getValorDesconto() {
		return valorDesconto;
	}

	/**
	 * Sets the valordesconto.
	 *
	 * @param id
	 *            the valordesconto to set
	 */
	public void setValorDesconto(String valordesconto) {
		this.valorDesconto = valordesconto;
	}

	/**
	 * Gets the valorOutrasDespesasAcessorias.
	 *
	 * @return the valorOutrasDespesasAcessorias
	 */
	/**
	 * Gets the valorOutrasDespesasAcessorias.
	 *
	 * @return the valorOutrasDespesasAcessorias
	 */
	public String getValorOutrasDespesasAcessorias() {
		return valorOutrasDespesasAcessorias;
	}

	/**
	 * Sets the valoroutrasdespesasacessorias.
	 *
	 * @param id
	 *            the valoroutrasdespesasacessorias to set
	 */
	public void setValorOutrasDespesasAcessorias(String valoroutrasdespesasacessorias) {
		this.valorOutrasDespesasAcessorias = valoroutrasdespesasacessorias;
	}

	/**
	 * Gets the compoeValorNota.
	 *
	 * @return the compoeValorNota
	 */
	/**
	 * Gets the compoeValorNota.
	 *
	 * @return the compoeValorNota
	 */
	public DoisValores getCompoeValorNota() {
		return compoeValorNota;
	}

	/**
	 * Sets the compoevalornota.
	 *
	 * @param id
	 *            the compoevalornota to set
	 */
	public void setCompoeValorNota(DoisValores compoevalornota) {
		this.compoeValorNota = compoevalornota;
	}

	/**
	 * Gets the declaracoesImportacao.
	 *
	 * @return the declaracoesImportacao
	 */
	/**
	 * Gets the declaracoesimportacao.
	 *
	 * @return the declaracoesimportacao
	 */
	public List<NFNotaInfoItemProdutoDeclaracaoImportacao> getDeclaracoesImportacao() {
		return declaracoesImportacao;
	}

	/**
	 * Sets the declaracoesimportacao.
	 *
	 * @param id
	 *            the declaracoesimportacao to set
	 */
	public void setDeclaracoesImportacao(List<NFNotaInfoItemProdutoDeclaracaoImportacao> declaracoesimportacao) {
		this.declaracoesImportacao = declaracoesimportacao;
	}

	/**
	 * Gets the detalhesExportacao.
	 *
	 * @return the detalhesExportacao
	 */
	/**
	 * Gets the detalhesexportacao.
	 *
	 * @return the detalhesexportacao
	 */
	public List<NFNotaInfoItemDetalheExportacao> getDetalhesExportacao() {
		return detalhesExportacao;
	}

	/**
	 * Sets the detalhesexportacao.
	 *
	 * @param id
	 *            the detalhesexportacao to set
	 */
	public void setDetalhesExportacao(List<NFNotaInfoItemDetalheExportacao> detalhesexportacao) {
		this.detalhesExportacao = detalhesexportacao;
	}

	/**
	 * Gets the numeroPedidoCliente.
	 *
	 * @return the numeroPedidoCliente
	 */
	/**
	 * Gets the numeroPedidoCliente.
	 *
	 * @return the numeroPedidoCliente
	 */
	public String getNumeroPedidoCliente() {
		return numeroPedidoCliente;
	}

	/**
	 * Sets the numeropedidocliente.
	 *
	 * @param id
	 *            the numeropedidocliente to set
	 */
	public void setNumeroPedidoCliente(String numeropedidocliente) {
		this.numeroPedidoCliente = numeropedidocliente;
	}

	/**
	 * Gets the numeroPedidoItemCliente.
	 *
	 * @return the numeroPedidoItemCliente
	 */
	/**
	 * Gets the numeroPedidoItemCliente.
	 *
	 * @return the numeroPedidoItemCliente
	 */
	public Integer getNumeroPedidoItemCliente() {
		return numeroPedidoItemCliente;
	}

	/**
	 * Sets the numeropedidoitemcliente.
	 *
	 * @param id
	 *            the numeropedidoitemcliente to set
	 */
	public void setNumeroPedidoItemCliente(Integer numeropedidoitemcliente) {
		this.numeroPedidoItemCliente = numeropedidoitemcliente;
	}

	/**
	 * Gets the numeroControleFCI.
	 *
	 * @return the numeroControleFCI
	 */
	/**
	 * Gets the numeroControleFCI.
	 *
	 * @return the numeroControleFCI
	 */
	public String getNumeroControleFCI() {
		return numeroControleFCI;
	}

	/**
	 * Sets the numerocontrolefci.
	 *
	 * @param id
	 *            the numerocontrolefci to set
	 */
	public void setNumeroControleFCI(String numerocontrolefci) {
		this.numeroControleFCI = numerocontrolefci;
	}

	/**
	 * Gets the veiculo.
	 *
	 * @return the veiculo
	 */
	/**
	 * Gets the veiculo.
	 *
	 * @return the veiculo
	 */
	public NFNotaInfoItemProdutoVeiculo getVeiculo() {
		return veiculo;
	}

	/**
	 * Sets the veiculo.
	 *
	 * @param id
	 *            the veiculo to set
	 */
	public void setVeiculo(NFNotaInfoItemProdutoVeiculo veiculo) {
		this.veiculo = veiculo;
	}

	/**
	 * Gets the medicamentos.
	 *
	 * @return the medicamentos
	 */
	/**
	 * Gets the medicamentos.
	 *
	 * @return the medicamentos
	 */
	public List<NFNotaInfoItemProdutoMedicamento> getMedicamentos() {
		return medicamentos;
	}

	/**
	 * Sets the medicamentos.
	 *
	 * @param id
	 *            the medicamentos to set
	 */
	public void setMedicamentos(List<NFNotaInfoItemProdutoMedicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	/**
	 * Gets the armamentos.
	 *
	 * @return the armamentos
	 */
	/**
	 * Gets the armamentos.
	 *
	 * @return the armamentos
	 */
	public List<NFNotaInfoItemProdutoArmamento> getArmamentos() {
		return armamentos;
	}

	/**
	 * Sets the armamentos.
	 *
	 * @param id
	 *            the armamentos to set
	 */
	public void setArmamentos(List<NFNotaInfoItemProdutoArmamento> armamentos) {
		this.armamentos = armamentos;
	}

	/**
	 * Gets the combustivel.
	 *
	 * @return the combustivel
	 */
	/**
	 * Gets the combustivel.
	 *
	 * @return the combustivel
	 */
	public NFNotaInfoItemProdutoCombustivel getCombustivel() {
		return combustivel;
	}

	/**
	 * Sets the combustivel.
	 *
	 * @param id
	 *            the combustivel to set
	 */
	public void setCombustivel(NFNotaInfoItemProdutoCombustivel combustivel) {
		this.combustivel = combustivel;
	}

	/**
	 * Gets the numeroRECOPI.
	 *
	 * @return the numeroRECOPI
	 */
	/**
	 * Gets the numeroRECOPI.
	 *
	 * @return the numeroRECOPI
	 */
	public String getNumeroRECOPI() {
		return numeroRECOPI;
	}

	/**
	 * Sets the numerorecopi.
	 *
	 * @param id
	 *            the numerorecopi to set
	 */
	public void setNumeroRECOPI(String numerorecopi) {
		this.numeroRECOPI = numerorecopi;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProduto [getId()=" + getId() + ", getCodigo()=" + getCodigo() + ", getCodigoDeBarras()="
				+ getCodigoDeBarras() + ", getDescricao()=" + getDescricao() + ", getNcm()=" + getNcm()
				+ ", getNomeclaturaValorAduaneiroEstatistica()=" + getNomeclaturaValorAduaneiroEstatistica()
				+ ", getCodigoEspecificadorSituacaoTributaria()=" + getCodigoEspecificadorSituacaoTributaria()
				+ ", getExtipi()=" + getExtipi() + ", getCfop()=" + getCfop() + ", getUnidadeComercial()="
				+ getUnidadeComercial() + ", getQuantidadeComercial()=" + getQuantidadeComercial()
				+ ", getValorUnitario()=" + getValorUnitario() + ", getValorTotalBruto()=" + getValorTotalBruto()
				+ ", getCodigoDeBarrasTributavel()=" + getCodigoDeBarrasTributavel() + ", getUnidadeTributavel()="
				+ getUnidadeTributavel() + ", getQuantidadeTributavel()=" + getQuantidadeTributavel()
				+ ", getValorUnitarioTributavel()=" + getValorUnitarioTributavel() + ", getValorFrete()="
				+ getValorFrete() + ", getValorSeguro()=" + getValorSeguro() + ", getValorDesconto()="
				+ getValorDesconto() + ", getValorOutrasDespesasAcessorias()=" + getValorOutrasDespesasAcessorias()
				+ ", getNumeroPedidoCliente()=" + getNumeroPedidoCliente() + ", getNumeroPedidoItemCliente()="
				+ getNumeroPedidoItemCliente() + ", getNumeroControleFCI()=" + getNumeroControleFCI()
				+ ", getNumeroRECOPI()=" + getNumeroRECOPI() + ", toString()=" + super.toString() + "]";
	}

}
