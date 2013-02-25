package com.sensus.mlc.endereco.model.request;

import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.endereco.model.Endereco;



// TODO: Auto-generated Javadoc
/**
 * The Class InquiryActionRequest.
 * 
 * @author QAT Brazil.
 */
public class InquiryEnderecoRequest extends InquiryPaginationRequest
{

	/** The base search. */
	private BaseSearch baseSearch;

	/** The file name. */
	private String fileName;

	/** The process id. */
	private Integer processId;

	/** The actions. */
	private List<Endereco> enderecos;

	/**
	 * Gets the base search.
	 * 
	 * @return the base search
	 */
	public BaseSearch getBaseSearch()
	{
		return baseSearch;
	}

	/**
	 * Sets the base search.
	 * 
	 * @param baseSearch the new base search
	 */
	public void setBaseSearch(BaseSearch baseSearch)
	{
		this.baseSearch = baseSearch;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Gets the enderecos.
	 *
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 *
	 * @param enderecos the new enderecos
	 */
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#setProcessId(java.lang.Integer)
	 */
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryEnderecoRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", enderecos=" + enderecos + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getEnderecos()="
				+ getEnderecos() + "]";
	}

	

}
