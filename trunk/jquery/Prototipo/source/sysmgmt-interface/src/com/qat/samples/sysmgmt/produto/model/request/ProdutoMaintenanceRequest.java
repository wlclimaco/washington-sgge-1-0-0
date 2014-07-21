package com.qat.samples.sysmgmt.produto.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * The Class ProdutoMaintenanceRequest.
 */
public class ProdutoMaintenanceRequest extends MaintenanceRequest
{

	/** The produto. */
	@XmlElement(nillable = true)
	private Produto produto;
	private List<Produto> produtos;

	/**
	 * The return list.
	 * Indicate true to return a list of objects or false not to return any objects
	 * */
	@XmlElement(nillable = true)
	private Boolean returnList;

	/**
	 * The return list paged.
	 * Indicate true to return the list of objects paged or false to return all the objects at once
	 * you must set returnList to true for this to work
	 * */
	@XmlElement(nillable = true)
	private Boolean returnListPaged;

	/**
	 * Instantiates a new produto maintenance request.
	 * 
	 * @param produto the produto
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public ProdutoMaintenanceRequest(Produto produto, Boolean returnList, Boolean returnListPaged)
	{
		this.produto = produto;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new produto maintenance request.
	 */
	public ProdutoMaintenanceRequest()
	{
	}

	/**
	 * Gets the produto.
	 * 
	 * @return the produto
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * Sets the produto.
	 * 
	 * @param produto the new produto
	 */
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	/**
	 * Gets the return list.
	 * 
	 * @return the return list
	 */
	public Boolean getReturnList()
	{
		return (returnList == null) ? false : returnList;
	}

	/**
	 * Sets the return list.
	 * 
	 * @param returnList the new return list
	 */
	public void setReturnList(Boolean returnList)
	{
		this.returnList = returnList;
	}

	/**
	 * Gets the return list paged.
	 * 
	 * @return the return list paged
	 */
	public Boolean getReturnListPaged()
	{
		return (returnListPaged == null) ? false : returnListPaged;
	}

	/**
	 * Sets the return list paged.
	 * 
	 * @param returnListPaged the new return list paged
	 */
	public void setReturnListPaged(Boolean returnListPaged)
	{
		this.returnListPaged = returnListPaged;
	}

	public List<Produto> getProdutos()
	{
		return produtos;
	}

	public void setProdutos(List<Produto> produtos)
	{
		this.produtos = produtos;
	}

	public void addProduto(Produto produto)
	{
		if (getProdutos() == null)
		{
			setProdutos(new ArrayList<Produto>());
		}

		getProdutos().add(produto);
	}

	@Override
	public String toString()
	{
		return "ProdutoMaintenanceRequest [getProduto()=" + getProduto() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged() + ", getProdutos()=" + getProdutos()
				+ ", toString()=" + super.toString() + "]";
	}

}
