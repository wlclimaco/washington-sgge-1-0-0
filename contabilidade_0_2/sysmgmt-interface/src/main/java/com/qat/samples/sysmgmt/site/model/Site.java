package com.qat.samples.sysmgmt.site.model;

import java.util.List;

import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Telefone;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Site extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private String url;

	private String quemSomos;

	private String missao;

	private String visao;

	private String titulo;

	private String logo;

	private boolean atorization;

	private List<Servico> servicoList;

	private List<Plano> planoList;

	private List<Endereco> enderecoList;

	private List<Email> emailList;

	private List<Telefone> telefoneList;

	public Site()
	{

	}

	public Site(int i, String string) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Servico> getServicoList() {
		return servicoList;
	}

	public void setServicoList(List<Servico> servicoList) {
		this.servicoList = servicoList;
	}

	public List<Plano> getPlanoList() {
		return planoList;
	}

	public void setPlanoList(List<Plano> planoList) {
		this.planoList = planoList;
	}

	public String getQuemSomos() {
		return quemSomos;
	}

	public void setQuemSomos(String quemSomos) {
		this.quemSomos = quemSomos;
	}

	public String getMissao() {
		return missao;
	}

	public void setMissao(String missao) {
		this.missao = missao;
	}

	public String getVisao() {
		return visao;
	}

	public void setVisao(String visao) {
		this.visao = visao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isAtorization() {
		return atorization;
	}

	public void setAtorization(boolean atorization) {
		this.atorization = atorization;
	}

	public List<Endereco> getEnderecoList() {
		return enderecoList;
	}

	public void setEnderecoList(List<Endereco> enderecoList) {
		this.enderecoList = enderecoList;
	}

	public List<Email> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<Email> emailList) {
		this.emailList = emailList;
	}

	public List<Telefone> getTelefoneList() {
		return telefoneList;
	}

	public void setTelefoneList(List<Telefone> telefoneList) {
		this.telefoneList = telefoneList;
	}

	@Override
	public String toString() {
		return "Site [getId()=" + getId() + ", getNome()=" + getNome() + ", getUrl()=" + getUrl()
				+ ", getServicoList()=" + getServicoList() + ", getPlanoList()=" + getPlanoList() + ", getQuemSomos()="
				+ getQuemSomos() + ", getMissao()=" + getMissao() + ", getVisao()=" + getVisao() + ", getTitulo()="
				+ getTitulo() + ", getLogo()=" + getLogo() + ", isAtorization()=" + isAtorization()
				+ ", getEnderecoList()=" + getEnderecoList() + ", getEmailList()=" + getEmailList()
				+ ", getTelefoneList()=" + getTelefoneList() + ", toString()=" + super.toString() + "]";
	}
}
