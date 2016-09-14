package com.qat.samples.sysmgmt.entidade.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Field extends ModelCosmeDamiao
{

	private Integer id;

	private String nome;

    private Integer status;

    private Integer obrigatorio;

    private String campoBanco;

    private String tabelaBanco;

    private DoisValores tipo;

    private String label;

    private String tootip;

    private Ajuda help;

    private List<Validacao> validacao;

    private List<Role> role;

	public Field(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Field() {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(Integer obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public String getCampoBanco() {
		return campoBanco;
	}

	public void setCampoBanco(String campoBanco) {
		this.campoBanco = campoBanco;
	}

	public String getTabelaBanco() {
		return tabelaBanco;
	}

	public void setTabelaBanco(String tabelaBanco) {
		this.tabelaBanco = tabelaBanco;
	}

	public DoisValores getTipo() {
		return tipo;
	}

	public void setTipo(DoisValores tipo) {
		this.tipo = tipo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTootip() {
		return tootip;
	}

	public void setTootip(String tootip) {
		this.tootip = tootip;
	}

	public Ajuda getHelp() {
		return help;
	}

	public void setHelp(Ajuda help) {
		this.help = help;
	}

	public List<Validacao> getValidacao() {
		return validacao;
	}

	public void setValidacao(List<Validacao> validacao) {
		this.validacao = validacao;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Field [getId()=" + getId() + ", getNome()=" + getNome() + ", getStatus()=" + getStatus()
				+ ", getObrigatorio()=" + getObrigatorio() + ", getCampoBanco()=" + getCampoBanco()
				+ ", getTabelaBanco()=" + getTabelaBanco() + ", getTipo()=" + getTipo() + ", getLabel()=" + getLabel()
				+ ", getTootip()=" + getTootip() + ", getHelp()=" + getHelp() + ", getValidacao()=" + getValidacao()
				+ ", getRole()=" + getRole() + ", toString()=" + super.toString() + "]";
	}





}
