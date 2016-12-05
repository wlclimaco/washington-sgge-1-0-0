/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.condpag.model.FormaPgPessoa;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.entidade.model.Entidade;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.produto.model.ItensEspeciais;
import com.qat.samples.sysmgmt.produto.model.Tributacao;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemExportacaoIndireta extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemExportacaoIndireta. */
	private Integer id;

	/**
	 * The econtabil numeroRegistroExportacao for the
	 * NFNotaInfoItemExportacaoIndireta.
	 */
	private Long numeroRegistroExportacao;

	/**
	 * The econtabil chaveAcessoNFe for the NFNotaInfoItemExportacaoIndireta.
	 */
	private String chaveAcessoNFe;

	/**
	 * The econtabil quantidadeItemEfetivamenteExportado for the
	 * NFNotaInfoItemExportacaoIndireta.
	 */
	private String quantidadeItemEfetivamenteExportado;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemExportacaoIndireta() {
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
	 * Gets the numeroRegistroExportacao.
	 *
	 * @return the numeroRegistroExportacao
	 */
	/**
	 * Gets the numeroRegistroExportacao.
	 *
	 * @return the numeroRegistroExportacao
	 */
	public Long getNumeroRegistroExportacao() {
		return numeroRegistroExportacao;
	}

	/**
	 * Sets the numeroregistroexportacao.
	 *
	 * @param id
	 *            the numeroregistroexportacao to set
	 */
	public void setNumeroRegistroExportacao(Long numeroregistroexportacao) {
		this.numeroRegistroExportacao = numeroregistroexportacao;
	}

	/**
	 * Gets the chaveAcessoNFe.
	 *
	 * @return the chaveAcessoNFe
	 */
	/**
	 * Gets the chaveAcessoNFe.
	 *
	 * @return the chaveAcessoNFe
	 */
	public String getChaveAcessoNFe() {
		return chaveAcessoNFe;
	}

	/**
	 * Sets the chaveacessonfe.
	 *
	 * @param id
	 *            the chaveacessonfe to set
	 */
	public void setChaveAcessoNFe(String chaveacessonfe) {
		this.chaveAcessoNFe = chaveacessonfe;
	}

	/**
	 * Gets the quantidadeItemEfetivamenteExportado.
	 *
	 * @return the quantidadeItemEfetivamenteExportado
	 */
	/**
	 * Gets the quantidadeItemEfetivamenteExportado.
	 *
	 * @return the quantidadeItemEfetivamenteExportado
	 */
	public String getQuantidadeItemEfetivamenteExportado() {
		return quantidadeItemEfetivamenteExportado;
	}

	/**
	 * Sets the quantidadeitemefetivamenteexportado.
	 *
	 * @param id
	 *            the quantidadeitemefetivamenteexportado to set
	 */
	public void setQuantidadeItemEfetivamenteExportado(String quantidadeitemefetivamenteexportado) {
		this.quantidadeItemEfetivamenteExportado = quantidadeitemefetivamenteexportado;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemExportacaoIndireta [getId()=" + getId() + ", getNumeroRegistroExportacao()="
				+ getNumeroRegistroExportacao() + ", getChaveAcessoNFe()=" + getChaveAcessoNFe()
				+ ", getQuantidadeItemEfetivamenteExportado()=" + getQuantidadeItemEfetivamenteExportado()
				+ ", toString()=" + super.toString() + "]";
	}

}
