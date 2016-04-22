package com.qat.samples.sysmgmt.beneficios.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;

public class BeneficiosResponse extends InquiryResponse
{

	/** Attributes */
	private List<Beneficios> beneficiosList;

	/**
	 * The Constructor.
	 */
	public BeneficiosResponse()
	{

	}

	/**
	 * @return the beneficiosList
	 */
	public List<Beneficios> getBeneficiosList()
	{
		return beneficiosList;
	}

	/**
	 * @param beneficiosList the beneficiosList to set
	 */
	public void setBeneficiosList(List<Beneficios> beneficiosList)
	{
		this.beneficiosList = beneficiosList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setBeneficiosList((List<Beneficios>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getBeneficiosList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}