package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.financeiro.model.criteria.FinanceiroCriteria;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class FinanceiroInquiryRequest.
 */
public class ContasPagarInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private FinanceiroCriteria criteria;

	/** The member. */
	private String descricao;

	/** The business id. */
	private Integer emprId;

	private Integer id;

	/** The recipient id. */
	private Conta conta;

	/** The location name. */
	private Long dataInicial;

	private Long dataFinal;

	/** The organization name. */
	private DoisValores status;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public FinanceiroCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new FinanceiroCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(FinanceiroCriteria criteria)
	{
		this.criteria = criteria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getEmprId() {
		return emprId;
	}

	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Long dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Long getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Long dataFinal) {
		this.dataFinal = dataFinal;
	}

	public DoisValores getStatus() {
		return status;
	}

	public void setStatus(DoisValores status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ContasPagarInquiryRequest [getCriteria()=" + getCriteria() + ", getDescricao()=" + getDescricao()
				+ ", getEmprId()=" + getEmprId() + ", getId()=" + getId() + ", getConta()=" + getConta()
				+ ", getDataInicial()=" + getDataInicial() + ", getDataFinal()=" + getDataFinal() + ", getStatus()="
				+ getStatus() + ", toString()=" + super.toString() + "]";
	}



}
