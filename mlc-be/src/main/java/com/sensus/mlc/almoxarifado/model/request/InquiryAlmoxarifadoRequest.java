package com.sensus.mlc.almoxarifado.model.request;

import java.util.List;

import com.sensus.mlc.almoxarifado.model.Almoxarifado;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.filial.model.Filial;



// TODO: Auto-generated Javadoc
/**
 * The Class InquiryActionRequest.
 * 
 * @author QAT Brazil.
 */
public class InquiryAlmoxarifadoRequest extends InquiryPaginationRequest
{

	/** The base search. */
	private BaseSearch baseSearch;

	/** The file name. */
	private String fileName;

	/** The process id. */
	private Integer processId;

	/** The actions. */
	private List<Almoxarifado> almoxarifado;

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
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the almoxarifado.
	 *
	 * @return the almoxarifado
	 */
	public List<Almoxarifado> getAlmoxarifado() {
		return almoxarifado;
	}

	/**
	 * Sets the almoxarifado.
	 *
	 * @param almoxarifado the new almoxarifado
	 */
	public void setAlmoxarifado(List<Almoxarifado> almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryAlmoxarifadoRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", almoxarifado=" + almoxarifado + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getAlmoxarifado()="
				+ getAlmoxarifado() + "]";
	}



}
