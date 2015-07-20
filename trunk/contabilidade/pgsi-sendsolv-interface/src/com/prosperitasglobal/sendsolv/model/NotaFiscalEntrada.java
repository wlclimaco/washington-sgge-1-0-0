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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotaFiscal [getId()=" + getId() + ", getNotaType()=" + getNotaType() + ", getSerie()=" + getSerie()
				+ ", getOrdem()=" + getOrdem() + ", getNumero()=" + getNumero() + ", getTipo()=" + getTipo()
				+ ", getNfValor()=" + getNfValor() + ", getTransportador()=" + getTransportador()
				+ ", getConhecimentoTransporte()=" + getConhecimentoTransporte() + ", getEmpresa()=" + getEmpresa()
				+ ", getTributosList()=" + getTributosList() + ", getFormaPagList()=" + getFormaPagList()
				+ ", getNotaFiscalItens()=" + getNotaFiscalItens() + ", getNoteList()=" + getNoteList()
				+ ", getContaspagarList()=" + getContaspagarList() + ", getContasReceberList()="
				+ getContasReceberList() + ", getItensEspeciais()=" + getItensEspeciais()
				+ ", getDataEmissao()=" + getDataEmissao() + ", getDataSaida()=" + getDataSaida()
				+ ", getDataEntrada()=" + getDataEntrada() + ", getModelo()=" + getModelo() + ", getCfop()="
				+ getCfop() + ", getBxEstoque()=" + getBxEstoque() + ", getDescItens()=" + getDescItens()
				+ ", getPcCusto()=" + getPcCusto() + ", getServicosItensList()=" + getServicosItensList()
				+ ", getNfStatusList()=" + getNfStatusList() + ", getFornecedor()=" + getFornecedor()
				+ ", getPedidoCompra()=" + getPedidoCompra() + ", getCliente()=" + getCliente()
				+ ", getOrcamentoList()=" + getOrcamentoList() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
