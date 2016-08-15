package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigCarne extends ModelCosmeDamiao
{
	private Integer id;
	private Integer carneBotelo;
	private Integer carneNormal;
	private String localPag;
	private String instr1;
	private String instr2;
	private String instr3;
	private String instr4;


	public ConfigCarne()
	{

	}

	public ConfigCarne(Integer id)
	{
		setId(id);
	}

	public ConfigCarne(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getCarneBotelo() {
		return carneBotelo;
	}

	public void setCarneBotelo(Integer carneBotelo) {
		this.carneBotelo = carneBotelo;
	}

	public Integer getCarneNormal() {
		return carneNormal;
	}

	public void setCarneNormal(Integer carneNormal) {
		this.carneNormal = carneNormal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalPag() {
		return localPag;
	}

	public void setLocalPag(String localPag) {
		this.localPag = localPag;
	}

	public String getInstr1() {
		return instr1;
	}

	public void setInstr1(String instr1) {
		this.instr1 = instr1;
	}

	public String getInstr2() {
		return instr2;
	}

	public void setInstr2(String instr2) {
		this.instr2 = instr2;
	}

	public String getInstr3() {
		return instr3;
	}

	public void setInstr3(String instr3) {
		this.instr3 = instr3;
	}

	public String getInstr4() {
		return instr4;
	}

	public void setInstr4(String instr4) {
		this.instr4 = instr4;
	}

	@Override
	public String toString() {
		return "ConfigCarne [getCarneBotelo()=" + getCarneBotelo() + ", getCarneNormal()=" + getCarneNormal()
				+ ", getId()=" + getId() + ", getLocalPag()=" + getLocalPag() + ", getInstr1()=" + getInstr1()
				+ ", getInstr2()=" + getInstr2() + ", getInstr3()=" + getInstr3() + ", getInstr4()=" + getInstr4()
				+ ", toString()=" + super.toString() + "]";
	}



}
