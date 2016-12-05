/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProdutoMedicamento extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemProdutoMedicamento. */
	private Integer id;

	/** The econtabil lote for the NFNotaInfoItemProdutoMedicamento. */
	private String lote;

	/** The econtabil quantidade for the NFNotaInfoItemProdutoMedicamento. */
	private String quantidade;

	/**
	 * The econtabil dataFabricacao for the NFNotaInfoItemProdutoMedicamento.
	 */
	private Long dataFabricacao;

	/** The econtabil dataValidade for the NFNotaInfoItemProdutoMedicamento. */
	private Long dataValidade;

	/**
	 * The econtabil precoMaximoConsumidor for the
	 * NFNotaInfoItemProdutoMedicamento.
	 */
	private String precoMaximoConsumidor;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProdutoMedicamento() {
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
	 * Gets the lote.
	 *
	 * @return the lote
	 */
	/**
	 * Gets the lote.
	 *
	 * @return the lote
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * Sets the lote.
	 *
	 * @param id
	 *            the lote to set
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * Gets the quantidade.
	 *
	 * @return the quantidade
	 */
	/**
	 * Gets the quantidade.
	 *
	 * @return the quantidade
	 */
	public String getQuantidade() {
		return quantidade;
	}

	/**
	 * Sets the quantidade.
	 *
	 * @param id
	 *            the quantidade to set
	 */
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Gets the dataFabricacao.
	 *
	 * @return the dataFabricacao
	 */
	/**
	 * Gets the dataFabricacao.
	 *
	 * @return the dataFabricacao
	 */
	public Long getDataFabricacao() {
		return dataFabricacao;
	}

	/**
	 * Sets the datafabricacao.
	 *
	 * @param id
	 *            the datafabricacao to set
	 */
	public void setDataFabricacao(Long datafabricacao) {
		this.dataFabricacao = datafabricacao;
	}

	/**
	 * Gets the dataValidade.
	 *
	 * @return the dataValidade
	 */
	/**
	 * Gets the dataValidade.
	 *
	 * @return the dataValidade
	 */
	public Long getDataValidade() {
		return dataValidade;
	}

	/**
	 * Sets the datavalidade.
	 *
	 * @param id
	 *            the datavalidade to set
	 */
	public void setDataValidade(Long datavalidade) {
		this.dataValidade = datavalidade;
	}

	/**
	 * Gets the precoMaximoConsumidor.
	 *
	 * @return the precoMaximoConsumidor
	 */
	/**
	 * Gets the precoMaximoConsumidor.
	 *
	 * @return the precoMaximoConsumidor
	 */
	public String getPrecoMaximoConsumidor() {
		return precoMaximoConsumidor;
	}

	/**
	 * Sets the precomaximoconsumidor.
	 *
	 * @param id
	 *            the precomaximoconsumidor to set
	 */
	public void setPrecoMaximoConsumidor(String precomaximoconsumidor) {
		this.precoMaximoConsumidor = precomaximoconsumidor;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProdutoMedicamento [getId()=" + getId() + ", getLote()=" + getLote()
				+ ", getQuantidade()=" + getQuantidade() + ", getDataFabricacao()=" + getDataFabricacao()
				+ ", getDataValidade()=" + getDataValidade() + ", getPrecoMaximoConsumidor()="
				+ getPrecoMaximoConsumidor() + ", toString()=" + super.toString() + "]";
	}

}
