package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Model Object ProdutoResponse.
 */
public class ProdutoResponse extends InquiryResponse
{

	/** The produto. */
	@XmlElement(nillable = true)
	private List<Produto> produto;

	/** The criteria. */
	private Criteria criteria;

	/**
	 * Gets the produto.
	 * 
	 * @return the produto
	 */
	public List<Produto> getProdutos()
	{
		return produto;
	}

	/**
	 * Sets the produto.
	 * 
	 * @param produto the new produto
	 */
	public void setProdutos(List<Produto> produto)
	{
		this.produto = produto;
	}

	public List<Produto> getProduto()
	{
		return produto;
	}

	public void setProduto(List<Produto> produto)
	{
		this.produto = produto;
	}

	public Criteria getCriteria()
	{
		return criteria;
	}

	public void setCriteria(Criteria criteria)
	{
		this.criteria = criteria;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setProdutos((List<Produto>)coll);
	}

	@Override
	public String toString()
	{
		return "ProdutoResponse [getProdutos()=" + getProdutos() + ", getProduto()=" + getProduto()
				+ ", getCriteria()=" + getCriteria() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ ", toString()=" + super.toString() + "]";
	}

}
