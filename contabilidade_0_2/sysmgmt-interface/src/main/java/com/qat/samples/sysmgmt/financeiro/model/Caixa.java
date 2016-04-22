package com.qat.samples.sysmgmt.financeiro.model;

import com.qat.samples.sysmgmt.conta.model.ContasTypeEnum;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Caixa extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private Integer pessoaId;

	/** The parent key. */
	private Integer docId;

	/** The parent key type. */
	private ContasTypeEnum contasTypeEnum;

	/** The document type. */
	private Integer numeroParc;

	/** The keyword text. */
	private Integer parcela;

	/** The filing status. */
	private Double valorOriginal;

	/** The is action required. */
	private Long dataVencimento;

	private Long dataGeracao;

	private Long dataPagamento;

	/** The note text. */
	private Double juros;

	/** The issue country. */
	private Double taxa;

	/** The expiration date. */
	private Double valorTotal;

	public Integer getContasTypeEnumValue()
	{
		if (contasTypeEnum != null)
		{
			return contasTypeEnum.getValue();
		}
		return null;
	}

	public void setContasTypeEnumValue(Integer acaoTypeValue)
	{
		contasTypeEnum = ContasTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * The Constructor.
	 */
	public Caixa()
	{

	}

	public Caixa(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the pessoaId
	 */
	public Integer getPessoaId()
	{
		return pessoaId;
	}

	/**
	 * @param pessoaId the pessoaId to set
	 */
	public void setPessoaId(Integer pessoaId)
	{
		this.pessoaId = pessoaId;
	}

	/**
	 * @return the docId
	 */
	public Integer getDocId()
	{
		return docId;
	}

	/**
	 * @param docId the docId to set
	 */
	public void setDocId(Integer docId)
	{
		this.docId = docId;
	}

	/**
	 * @return the contasTypeEnum
	 */
	public ContasTypeEnum getContasTypeEnum()
	{
		return contasTypeEnum;
	}

	/**
	 * @param contasTypeEnum the contasTypeEnum to set
	 */
	public void setContasTypeEnum(ContasTypeEnum contasTypeEnum)
	{
		this.contasTypeEnum = contasTypeEnum;
	}

	/**
	 * @return the numeroParc
	 */
	public Integer getNumeroParc()
	{
		return numeroParc;
	}

	/**
	 * @param numeroParc the numeroParc to set
	 */
	public void setNumeroParc(Integer numeroParc)
	{
		this.numeroParc = numeroParc;
	}

	/**
	 * @return the parcela
	 */
	public Integer getParcela()
	{
		return parcela;
	}

	/**
	 * @param parcela the parcela to set
	 */
	public void setParcela(Integer parcela)
	{
		this.parcela = parcela;
	}

	/**
	 * @return the valorOriginal
	 */
	public Double getValorOriginal()
	{
		return valorOriginal;
	}

	/**
	 * @param valorOriginal the valorOriginal to set
	 */
	public void setValorOriginal(Double valorOriginal)
	{
		this.valorOriginal = valorOriginal;
	}

	/**
	 * @return the dataVencimento
	 */
	public Long getDataVencimento()
	{
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(Long dataVencimento)
	{
		this.dataVencimento = dataVencimento;
	}

	/**
	 * @return the dataGeracao
	 */
	public Long getDataGeracao()
	{
		return dataGeracao;
	}

	/**
	 * @param dataGeracao the dataGeracao to set
	 */
	public void setDataGeracao(Long dataGeracao)
	{
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return the dataPagamento
	 */
	public Long getDataPagamento()
	{
		return dataPagamento;
	}

	/**
	 * @param dataPagamento the dataPagamento to set
	 */
	public void setDataPagamento(Long dataPagamento)
	{
		this.dataPagamento = dataPagamento;
	}

	/**
	 * @return the juros
	 */
	public Double getJuros()
	{
		return juros;
	}

	/**
	 * @param juros the juros to set
	 */
	public void setJuros(Double juros)
	{
		this.juros = juros;
	}

	/**
	 * @return the taxa
	 */
	public Double getTaxa()
	{
		return taxa;
	}

	/**
	 * @param taxa the taxa to set
	 */
	public void setTaxa(Double taxa)
	{
		this.taxa = taxa;
	}

	/**
	 * @return the valorTotal
	 */
	public Double getValorTotal()
	{
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(Double valorTotal)
	{
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString()
	{
		return "Caixa [getContasTypeEnumValue()=" + getContasTypeEnumValue() + ", getId()=" + getId()
				+ ", getPessoaId()=" + getPessoaId() + ", getDocId()=" + getDocId() + ", getContasTypeEnum()="
				+ getContasTypeEnum() + ", getNumeroParc()=" + getNumeroParc() + ", getParcela()=" + getParcela()
				+ ", getValorOriginal()=" + getValorOriginal() + ", getDataVencimento()=" + getDataVencimento()
				+ ", getDataGeracao()=" + getDataGeracao() + ", getDataPagamento()=" + getDataPagamento()
				+ ", getJuros()=" + getJuros() + ", getTaxa()=" + getTaxa() + ", getValorTotal()=" + getValorTotal()
				+ ", toString()=" + super.toString() + "]";
	}

}