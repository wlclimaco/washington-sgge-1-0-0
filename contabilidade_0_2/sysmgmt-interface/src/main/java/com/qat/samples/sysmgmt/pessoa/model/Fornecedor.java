package com.qat.samples.sysmgmt.pessoa.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.CfopParentId;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Fornecedor extends Pessoa
{

	private Integer id;

	private List<CfopParentId> listCfops;

	public Fornecedor()
	{
		super();
	}

	public Fornecedor(int i, String string) {
		setId(i);
		setNome(string);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CfopParentId> getListCfops() {
		return listCfops;
	}

	public void setListCfops(List<CfopParentId> listCfops) {
		this.listCfops = listCfops;
	}

	@Override
	public String toString() {
		return "Fornecedor [getId()=" + getId() + ", getListCfops()=" + getListCfops() + ", toString()="
				+ super.toString() + "]";
	}


}
