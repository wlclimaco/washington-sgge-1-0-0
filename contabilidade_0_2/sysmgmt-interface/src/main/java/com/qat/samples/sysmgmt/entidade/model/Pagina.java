package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Pagina extends ModelCosmeDamiao
{

	private Integer id;


	private String pagina;

    private Integer status;

    private Menu menu;

    private Ajuda help;

    private Field filds;

	public Pagina(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Pagina() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Ajuda getHelp() {
		return help;
	}

	public void setHelp(Ajuda help) {
		this.help = help;
	}

	public Field getFilds() {
		return filds;
	}

	public void setFilds(Field filds) {
		this.filds = filds;
	}

	@Override
	public String toString() {
		return "Pagina [getId()=" + getId() + ", getPagina()=" + getPagina() + ", getStatus()=" + getStatus()
				+ ", getMenu()=" + getMenu() + ", getHelp()=" + getHelp() + ", getFilds()=" + getFilds()
				+ ", toString()=" + super.toString() + "]";
	}



}
