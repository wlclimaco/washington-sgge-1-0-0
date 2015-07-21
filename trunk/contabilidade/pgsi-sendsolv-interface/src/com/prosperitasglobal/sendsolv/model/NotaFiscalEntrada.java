package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class NotaFiscalEntrada extends NotaFiscal
{
	/** The fornecedor. */
	private Fornecedor fornecedor;

	/**
	 * @return the fornecedor
	 */
	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor(Fornecedor fornecedor)
	{
		this.fornecedor = fornecedor;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotaFiscalEntrada [getFornecedor()=" + getFornecedor() + ", getId()=" + getId() + ", getNotaType()="
				+ getNotaType() + ", getSerie()=" + getSerie() + ", getOrdem()=" + getOrdem() + ", getNumero()="
				+ getNumero() + ", getTipo()=" + getTipo() + ", getNfValor()=" + getNfValor() + ", getTransportador()="
				+ getTransportador() + ", getConhecimentoTransporte()=" + getConhecimentoTransporte()
				+ ", getEmpresa()=" + getEmpresa() + ", getTributosList()=" + getTributosList()
				+ ", getFormaPagList()=" + getFormaPagList() + ", getNotaFiscalItens()=" + getNotaFiscalItens()
				+ ", getNoteList()=" + getNoteList() + ", getContaspagarList()=" + getContaspagarList()
				+ ", getItensEspeciais()=" + getItensEspeciais()
				+ ", getDataEmissao()=" + getDataEmissao() + ", getDataSaida()=" + getDataSaida()
				+ ", getDataEntrada()=" + getDataEntrada() + ", getModelo()=" + getModelo() + ", getCfop()="
				+ getCfop() + ", getBxEstoque()=" + getBxEstoque() + ", getDescItens()=" + getDescItens()
				+ ", getPcCusto()=" + getPcCusto() + ", getNfStatusList()=" + getNfStatusList()
				+ ", getHistoricoNFList()=" + getHistoricoNFList() + ", toString()=" + super.toString()
				+ ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue()
				+ ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite()
				+ ", getProcessId()=" + getProcessId() + ", getUserId()=" + getUserId() + ", getNotes()=" + getNotes()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
