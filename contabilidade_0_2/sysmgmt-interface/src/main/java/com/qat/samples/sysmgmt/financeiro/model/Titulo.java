package com.qat.samples.sysmgmt.financeiro.model;

import java.util.List;

import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Titulo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String numero;

	private Integer parcela;

	/** The estado. */
	private Long dataEmissao;

	/** The bairro. */
	private Long dataVencimento;

	/** The numero. */
	private Integer docId;

	private String observacao;

	private Double valor;

	private FinanceiroStatusEnum financeiroEnum;

	private List<BaixaTitulo> listBaixa;

	public Integer getFinanceiroEnumValue()
	{
		if (financeiroEnum != null)
		{
			return financeiroEnum.getValue();
		}
		return null;
	}

	public void setFinanceiroEnumValue(Integer acaoTypeValue)
	{
		financeiroEnum = FinanceiroStatusEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Default constructor.
	 */
	public Titulo()
	{
		super();
	}

	public Titulo(int i, String string) {
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNumero()
	{
		return numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	public Integer getParcela()
	{
		return parcela;
	}

	public void setParcela(Integer parcela)
	{
		this.parcela = parcela;
	}

	public Long getDataEmissao()
	{
		return dataEmissao;
	}

	public void setDataEmissao(Long dataEmissao)
	{
		this.dataEmissao = dataEmissao;
	}

	public Long getDataVencimento()
	{
		return dataVencimento;
	}

	public void setDataVencimento(Long dataVencimento)
	{
		this.dataVencimento = dataVencimento;
	}

	public Integer getDocId()
	{
		return docId;
	}

	public void setDocId(Integer docId)
	{
		this.docId = docId;
	}



	public String getObservacao()
	{
		return observacao;
	}

	public void setObservacao(String observacao)
	{
		this.observacao = observacao;
	}

	public Double getValor()
	{
		return valor;
	}

	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	public FinanceiroStatusEnum getFinanceiroEnum()
	{
		return financeiroEnum;
	}

	public void setFinanceiroEnum(FinanceiroStatusEnum financeiroEnum)
	{
		this.financeiroEnum = financeiroEnum;
	}

	public List<BaixaTitulo> getListBaixa()
	{
		return listBaixa;
	}

	public void setListBaixa(List<BaixaTitulo> listBaixa)
	{
		this.listBaixa = listBaixa;
	}

	@Override
	public String toString() {
		return "Titulo [getId()=" + getId() + ", getNumero()=" + getNumero() + ", getParcela()=" + getParcela()
				+ ", getDataEmissao()=" + getDataEmissao() + ", getDataVencimento()=" + getDataVencimento()
				+ ", getDocId()=" + getDocId() + ", getObservacao()=" + getObservacao() + ", getValor()=" + getValor()
				+ ", getFinanceiroEnum()=" + getFinanceiroEnum() + ", getListBaixa()=" + getListBaixa()
				+ ", toString()=" + super.toString() + "]";
	}

}
