package com.qat.samples.sysmgmt.financeiro.model;

import java.util.List;

import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Financeiro extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String numero;

	private Integer parcela;

	/** The type of an account. */
	private Fornecedor fornecedor;

	/** The description. */
	private Cliente cliente;

	/** The estado. */
	private Long dataEmissao;

	/** The bairro. */
	private Long dataVencimento;

	/** The numero. */
	private Integer docId;

	/** The cep. */
	private NotaFiscalEntrada notaFiscalEntrada;

	private NotaFiscalSaida notaFiscalSaida;

	/** The tipo endereco. */
	private String observacao;

	private Double valor;

	private FinanceiroStatusEnum financeiroEnum;

	private List<Baixa> listBaixa;

	/**
	 * Default constructor.
	 */
	public Financeiro()
	{
		super();
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

	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor)
	{
		this.fornecedor = fornecedor;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
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

	public NotaFiscalEntrada getNotaFiscalEntrada()
	{
		return notaFiscalEntrada;
	}

	public void setNotaFiscalEntrada(NotaFiscalEntrada notaFiscalEntrada)
	{
		this.notaFiscalEntrada = notaFiscalEntrada;
	}

	public NotaFiscalSaida getNotaFiscalSaida()
	{
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida)
	{
		this.notaFiscalSaida = notaFiscalSaida;
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

	public List<Baixa> getListBaixa()
	{
		return listBaixa;
	}

	public void setListBaixa(List<Baixa> listBaixa)
	{
		this.listBaixa = listBaixa;
	}

	@Override
	public String toString()
	{
		return "Financeiro [getId()=" + getId() + ", getNumero()=" + getNumero() + ", getParcela()=" + getParcela()
				+ ", getFornecedor()=" + getFornecedor() + ", getCliente()=" + getCliente() + ", getDataEmissao()="
				+ getDataEmissao() + ", getDataVencimento()=" + getDataVencimento() + ", getDocId()=" + getDocId()
				+ ", getNotaFiscalEntrada()=" + getNotaFiscalEntrada() + ", getNotaFiscalSaida()="
				+ getNotaFiscalSaida() + ", getObservacao()=" + getObservacao() + ", getValor()=" + getValor()
				+ ", getFinanceiroEnum()=" + getFinanceiroEnum() + ", getListBaixa()=" + getListBaixa()
				+ ", toString()=" + super.toString() + "]";
	}

}
