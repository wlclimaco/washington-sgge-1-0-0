package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Menu extends ModelCosmeDamiao
{

	private Integer id;

	private String nome;

    private String label;

    private Integer status;

    private Integer nivel;
    private Role permissao;

    private Ajuda help;

	public Menu(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Role getPermissao() {
		return permissao;
	}

	public void setPermissao(Role permissao) {
		this.permissao = permissao;
	}

	public Ajuda getHelp() {
		return help;
	}

	public void setHelp(Ajuda help) {
		this.help = help;
	}

	@Override
	public String toString() {
		return "Menu [getId()=" + getId() + ", getNome()=" + getNome() + ", getLabel()=" + getLabel() + ", getStatus()="
				+ getStatus() + ", getNivel()=" + getNivel() + ", getPermissao()=" + getPermissao() + ", getHelp()="
				+ getHelp() + ", toString()=" + super.toString() + "]";
	}


}
