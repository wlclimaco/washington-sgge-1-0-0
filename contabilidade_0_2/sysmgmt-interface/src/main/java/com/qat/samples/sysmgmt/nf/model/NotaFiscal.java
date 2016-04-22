package com.qat.samples.sysmgmt.nf.model;

import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.condpag.model.FormaPgPessoa;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.entidade.model.Entidade;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.produto.model.ItensEspeciais;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class NotaFiscal extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The description. */
	private String serie;

	/** The estado. */
	private String ordem;

	/** The bairro. */
	private String numero;

	/** The numero. */
	private String tipo;

	/** The nf valor. */
	private Double nfValor;

	/** The transportador. */
	private Transportador transportador;

	/** The conhecimento transporte. */
	private ConhecimentoTransporte conhecimentoTransporte;

	/** The empresa. */
	private Entidade empresa;

	private Pessoa pessoa;

	/** The tributos list. */
	private List<Tributacao> tributosList;

	/** The cep. */
	private List<FormaPgPessoa> formaPagList;

	/** The tipo endereco. */
	private List<NotaFiscalItens> notaFiscalItens;

	/** The note list. */
	private List<Note> noteList;

	/** The contaspagar list. */
	private List<Conta> contasList;

	/** The itens especiais. */
	private List<ItensEspeciais> itensEspeciais;

	/** The data emissao. */
	private Long dataEmissao;

	/** The data saida. */
	private Long dataSaida;

	/** The data entrada. */
	private Long dataEntrada;

	/** The modelo. */
	private Integer modelo;

	/** The cfop. */
	private Cfop cfop;

	/** The bx estoque. */
	private Integer bxEstoque;

	/** The desc itens. */
	private Integer descItens;

	/** The pc custo. */
	private Integer pcCusto;

	/** The nf status list. */
	private List<NFStatus> nfStatusList;

	private List<HistoricoNF> historicoNFList;

	/**
	 * Default constructor.
	 */
	public NotaFiscal()
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

	/**
	 * Gets the serie.
	 * 
	 * @return the serie
	 */
	public String getSerie()
	{
		return serie;
	}

	/**
	 * Sets the serie.
	 * 
	 * @param serie the serie to set
	 */
	public void setSerie(String serie)
	{
		this.serie = serie;
	}

	/**
	 * Gets the ordem.
	 * 
	 * @return the ordem
	 */
	public String getOrdem()
	{
		return ordem;
	}

	/**
	 * Sets the ordem.
	 * 
	 * @param ordem the ordem to set
	 */
	public void setOrdem(String ordem)
	{
		this.ordem = ordem;
	}

	/**
	 * Gets the numero.
	 * 
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero.
	 * 
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the tipo.
	 * 
	 * @return the tipo
	 */
	public String getTipo()
	{
		return tipo;
	}

	/**
	 * Sets the tipo.
	 * 
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * Gets the nf valor.
	 * 
	 * @return the nfValor
	 */
	public Double getNfValor()
	{
		return nfValor;
	}

	/**
	 * Sets the nf valor.
	 * 
	 * @param nfValor the nfValor to set
	 */
	public void setNfValor(Double nfValor)
	{
		this.nfValor = nfValor;
	}

	/**
	 * Gets the transportador.
	 * 
	 * @return the transportador
	 */
	public Transportador getTransportador()
	{
		return transportador;
	}

	/**
	 * Sets the transportador.
	 * 
	 * @param transportador the transportador to set
	 */
	public void setTransportador(Transportador transportador)
	{
		this.transportador = transportador;
	}

	/**
	 * Gets the conhecimento transporte.
	 * 
	 * @return the conhecimentoTransporte
	 */
	public ConhecimentoTransporte getConhecimentoTransporte()
	{
		return conhecimentoTransporte;
	}

	/**
	 * Sets the conhecimento transporte.
	 * 
	 * @param conhecimentoTransporte the conhecimentoTransporte to set
	 */
	public void setConhecimentoTransporte(ConhecimentoTransporte conhecimentoTransporte)
	{
		this.conhecimentoTransporte = conhecimentoTransporte;
	}

	/**
	 * Gets the empresa.
	 * 
	 * @return the empresa
	 */
	public Entidade getEmpresa()
	{
		return empresa;
	}

	/**
	 * Sets the empresa.
	 * 
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Entidade empresa)
	{
		this.empresa = empresa;
	}

	/**
	 * Gets the tributos list.
	 * 
	 * @return the tributosList
	 */
	public List<Tributacao> getTributosList()
	{
		return tributosList;
	}

	/**
	 * Sets the tributos list.
	 * 
	 * @param tributosList the tributosList to set
	 */
	public void setTributosList(List<Tributacao> tributosList)
	{
		this.tributosList = tributosList;
	}

	/**
	 * Gets the forma pag list.
	 * 
	 * @return the formaPagList
	 */
	public List<FormaPgPessoa> getFormaPagList()
	{
		return formaPagList;
	}

	/**
	 * Sets the forma pag list.
	 * 
	 * @param formaPagList the formaPagList to set
	 */
	public void setFormaPagList(List<FormaPgPessoa> formaPagList)
	{
		this.formaPagList = formaPagList;
	}

	/**
	 * Gets the nota fiscal itens.
	 * 
	 * @return the notaFiscalItens
	 */
	public List<NotaFiscalItens> getNotaFiscalItens()
	{
		return notaFiscalItens;
	}

	/**
	 * Sets the nota fiscal itens.
	 * 
	 * @param notaFiscalItens the notaFiscalItens to set
	 */
	public void setNotaFiscalItens(List<NotaFiscalItens> notaFiscalItens)
	{
		this.notaFiscalItens = notaFiscalItens;
	}

	/**
	 * Gets the note list.
	 * 
	 * @return the noteList
	 */
	public List<Note> getNoteList()
	{
		return noteList;
	}

	/**
	 * Sets the note list.
	 * 
	 * @param noteList the noteList to set
	 */
	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	/**
	 * Gets the contaspagar list.
	 * 
	 * @return the contaspagarList
	 */
	public List<Conta> getContaspagarList()
	{
		return contasList;
	}

	/**
	 * Sets the contaspagar list.
	 * 
	 * @param contaspagarList the contaspagarList to set
	 */
	public void setContaspagarList(List<Conta> contaspagarList)
	{
		contasList = contaspagarList;
	}

	/**
	 * Gets the itens especiais.
	 * 
	 * @return the itensEspeciais
	 */
	public List<ItensEspeciais> getItensEspeciais()
	{
		return itensEspeciais;
	}

	/**
	 * Sets the itens especiais.
	 * 
	 * @param itensEspeciais the itensEspeciais to set
	 */
	public void setItensEspeciais(List<ItensEspeciais> itensEspeciais)
	{
		this.itensEspeciais = itensEspeciais;
	}

	/**
	 * Gets the data emissao.
	 * 
	 * @return the dataEmissao
	 */
	public Long getDataEmissao()
	{
		return dataEmissao;
	}

	/**
	 * Sets the data emissao.
	 * 
	 * @param dataEmissao the dataEmissao to set
	 */
	public void setDataEmissao(Long dataEmissao)
	{
		this.dataEmissao = dataEmissao;
	}

	/**
	 * Gets the data saida.
	 * 
	 * @return the dataSaida
	 */
	public Long getDataSaida()
	{
		return dataSaida;
	}

	/**
	 * Sets the data saida.
	 * 
	 * @param dataSaida the dataSaida to set
	 */
	public void setDataSaida(Long dataSaida)
	{
		this.dataSaida = dataSaida;
	}

	/**
	 * Gets the data entrada.
	 * 
	 * @return the dataEntrada
	 */
	public Long getDataEntrada()
	{
		return dataEntrada;
	}

	/**
	 * Sets the data entrada.
	 * 
	 * @param dataEntrada the dataEntrada to set
	 */
	public void setDataEntrada(Long dataEntrada)
	{
		this.dataEntrada = dataEntrada;
	}

	/**
	 * Gets the modelo.
	 * 
	 * @return the modelo
	 */
	public Integer getModelo()
	{
		return modelo;
	}

	/**
	 * Sets the modelo.
	 * 
	 * @param modelo the modelo to set
	 */
	public void setModelo(Integer modelo)
	{
		this.modelo = modelo;
	}

	/**
	 * Gets the cfop.
	 * 
	 * @return the cfop
	 */
	public Cfop getCfop()
	{
		return cfop;
	}

	/**
	 * Sets the cfop.
	 * 
	 * @param cfop the cfop to set
	 */
	public void setCfop(Cfop cfop)
	{
		this.cfop = cfop;
	}

	/**
	 * Gets the bx estoque.
	 * 
	 * @return the bxEstoque
	 */
	public Integer getBxEstoque()
	{
		return bxEstoque;
	}

	/**
	 * Sets the bx estoque.
	 * 
	 * @param bxEstoque the bxEstoque to set
	 */
	public void setBxEstoque(Integer bxEstoque)
	{
		this.bxEstoque = bxEstoque;
	}

	/**
	 * Gets the desc itens.
	 * 
	 * @return the descItens
	 */
	public Integer getDescItens()
	{
		return descItens;
	}

	/**
	 * Sets the desc itens.
	 * 
	 * @param descItens the descItens to set
	 */
	public void setDescItens(Integer descItens)
	{
		this.descItens = descItens;
	}

	/**
	 * Gets the pc custo.
	 * 
	 * @return the pcCusto
	 */
	public Integer getPcCusto()
	{
		return pcCusto;
	}

	/**
	 * Sets the pc custo.
	 * 
	 * @param pcCusto the pcCusto to set
	 */
	public void setPcCusto(Integer pcCusto)
	{
		this.pcCusto = pcCusto;
	}

	/**
	 * @return the nfStatusList
	 */
	public List<NFStatus> getNfStatusList()
	{
		return nfStatusList;
	}

	/**
	 * @param nfStatusList the nfStatusList to set
	 */
	public void setNfStatusList(List<NFStatus> nfStatusList)
	{
		this.nfStatusList = nfStatusList;
	}

	/**
	 * @return the historicoNFList
	 */
	public List<HistoricoNF> getHistoricoNFList()
	{
		return historicoNFList;
	}

	/**
	 * @param historicoNFList the historicoNFList to set
	 */
	public void setHistoricoNFList(List<HistoricoNF> historicoNFList)
	{
		this.historicoNFList = historicoNFList;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa()
	{
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa)
	{
		this.pessoa = pessoa;
	}

	/**
	 * @return the contasList
	 */
	public List<Conta> getContasList()
	{
		return contasList;
	}

	/**
	 * @param contasList the contasList to set
	 */
	public void setContasList(List<Conta> contasList)
	{
		this.contasList = contasList;
	}

	@Override
	public String toString()
	{
		return "NotaFiscal [getId()=" + getId() + ", getSerie()=" + getSerie() + ", getOrdem()=" + getOrdem()
				+ ", getNumero()=" + getNumero() + ", getTipo()=" + getTipo() + ", getNfValor()=" + getNfValor()
				+ ", getTransportador()=" + getTransportador() + ", getConhecimentoTransporte()="
				+ getConhecimentoTransporte() + ", getEmpresa()=" + getEmpresa() + ", getTributosList()="
				+ getTributosList() + ", getFormaPagList()=" + getFormaPagList() + ", getNotaFiscalItens()="
				+ getNotaFiscalItens() + ", getNoteList()=" + getNoteList() + ", getContaspagarList()="
				+ getContaspagarList() + ", getItensEspeciais()=" + getItensEspeciais() + ", getDataEmissao()="
				+ getDataEmissao() + ", getDataSaida()=" + getDataSaida() + ", getDataEntrada()=" + getDataEntrada()
				+ ", getModelo()=" + getModelo() + ", getCfop()=" + getCfop() + ", getBxEstoque()=" + getBxEstoque()
				+ ", getDescItens()=" + getDescItens() + ", getPcCusto()=" + getPcCusto() + ", getNfStatusList()="
				+ getNfStatusList() + ", getHistoricoNFList()=" + getHistoricoNFList() + ", getPessoa()=" + getPessoa()
				+ ", getContasList()=" + getContasList() + ", toString()=" + super.toString() + "]";
	}

}
