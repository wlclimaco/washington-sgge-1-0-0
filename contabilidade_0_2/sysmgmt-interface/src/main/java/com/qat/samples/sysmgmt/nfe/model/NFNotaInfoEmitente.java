/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.util.model.Endereco;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoEmitente extends NFBase
{

    /** The econtabil cnpj for the NFNotaInfoEmitente. */
    private String cnpj;

    /** The econtabil cpf for the NFNotaInfoEmitente. */
    private String cpf;

    /** The econtabil razaoSocial for the NFNotaInfoEmitente. */
    private String razaosocial;

    /** The econtabil nomeFantasia for the NFNotaInfoEmitente. */
    private String nomefantasia;

    /** The econtabil endereco for the NFNotaInfoEmitente. */
    private Endereco endereco;

    /** The econtabil inscricaoEstadual for the NFNotaInfoEmitente. */
    private String inscricaoestadual;

    /** The econtabil inscricaoEstadualSubstituicaoTributaria for the NFNotaInfoEmitente. */
    private String inscricaoestadualsubstituicaotributaria;

    /** The econtabil inscricaoMunicipal for the NFNotaInfoEmitente. */
    private String inscricaomunicipal;

    /** The econtabil classificacaoNacionalAtividadesEconomicas for the NFNotaInfoEmitente. */
    private String classificacaonacionalatividadeseconomicas;

    /** The econtabil regimeTributario for the NFNotaInfoEmitente. */
    private Regime regimetributario;



    /**
     * Default constructor.
     */
    public NFNotaInfoEmitente()
    {
        super();
    }


    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj()
    {
        return cnpj;
    }

    /**
     * Sets the cnpj.
     *
* @param id the cnpj to set
 */
public void setCnpj(String cnpj)
{
        this.cnpj = cnpj;
    }

    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf()
    {
        return cpf;
    }

    /**
     * Sets the cpf.
     *
* @param id the cpf to set
 */
public void setCpf(String cpf)
{
        this.cpf = cpf;
    }

    /**
     * Gets the razaosocial.
     *
     * @return the razaosocial
     */
    /**
     * Gets the razaosocial.
     *
     * @return the razaosocial
     */
    public String getRazaoSocial()
    {
        return razaosocial;
    }

    /**
     * Sets the razaosocial.
     *
* @param id the razaosocial to set
 */
public void setRazaoSocial(String razaosocial)
{
        this.razaosocial = razaosocial;
    }

    /**
     * Gets the nomefantasia.
     *
     * @return the nomefantasia
     */
    /**
     * Gets the nomefantasia.
     *
     * @return the nomefantasia
     */
    public String getNomeFantasia()
    {
        return nomefantasia;
    }

    /**
     * Sets the nomefantasia.
     *
* @param id the nomefantasia to set
 */
public void setNomeFantasia(String nomefantasia)
{
        this.nomefantasia = nomefantasia;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public Endereco getEndereco()
    {
        return endereco;
    }

    /**
     * Sets the endereco.
     *
* @param id the endereco to set
 */
public void setEndereco(Endereco endereco)
{
        this.endereco = endereco;
    }

    /**
     * Gets the inscricaoestadual.
     *
     * @return the inscricaoestadual
     */
    /**
     * Gets the inscricaoestadual.
     *
     * @return the inscricaoestadual
     */
    public String getInscricaoEstadual()
    {
        return inscricaoestadual;
    }

    /**
     * Sets the inscricaoestadual.
     *
* @param id the inscricaoestadual to set
 */
public void setInscricaoEstadual(String inscricaoestadual)
{
        this.inscricaoestadual = inscricaoestadual;
    }

    /**
     * Gets the inscricaoestadualsubstituicaotributaria.
     *
     * @return the inscricaoestadualsubstituicaotributaria
     */
    /**
     * Gets the inscricaoestadualsubstituicaotributaria.
     *
     * @return the inscricaoestadualsubstituicaotributaria
     */
    public String getInscricaoEstadualSubstituicaoTributaria()
    {
        return inscricaoestadualsubstituicaotributaria;
    }

    /**
     * Sets the inscricaoestadualsubstituicaotributaria.
     *
* @param id the inscricaoestadualsubstituicaotributaria to set
 */
public void setInscricaoEstadualSubstituicaoTributaria(String inscricaoestadualsubstituicaotributaria)
{
        this.inscricaoestadualsubstituicaotributaria = inscricaoestadualsubstituicaotributaria;
    }

    /**
     * Gets the inscricaomunicipal.
     *
     * @return the inscricaomunicipal
     */
    /**
     * Gets the inscricaomunicipal.
     *
     * @return the inscricaomunicipal
     */
    public String getInscricaoMunicipal()
    {
        return inscricaomunicipal;
    }

    /**
     * Sets the inscricaomunicipal.
     *
* @param id the inscricaomunicipal to set
 */
public void setInscricaoMunicipal(String inscricaomunicipal)
{
        this.inscricaomunicipal = inscricaomunicipal;
    }

    /**
     * Gets the classificacaonacionalatividadeseconomicas.
     *
     * @return the classificacaonacionalatividadeseconomicas
     */
    /**
     * Gets the classificacaonacionalatividadeseconomicas.
     *
     * @return the classificacaonacionalatividadeseconomicas
     */
    public String getClassificacaoNacionalAtividadesEconomicas()
    {
        return classificacaonacionalatividadeseconomicas;
    }

    /**
     * Sets the classificacaonacionalatividadeseconomicas.
     *
* @param id the classificacaonacionalatividadeseconomicas to set
 */
public void setClassificacaoNacionalAtividadesEconomicas(String classificacaonacionalatividadeseconomicas)
{
        this.classificacaonacionalatividadeseconomicas = classificacaonacionalatividadeseconomicas;
    }

    /**
     * Gets the regimetributario.
     *
     * @return the regimetributario
     */
    /**
     * Gets the regimetributario.
     *
     * @return the regimetributario
     */
    public Regime getRegimeTributario()
    {
        return regimetributario;
    }

    /**
     * Sets the regimetributario.
     *
* @param id the regimetributario to set
 */
public void setRegimeTributario(Regime regimetributario)
{
        this.regimetributario = regimetributario;
    }


	public String getRazaosocial() {
		return razaosocial;
	}


	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}


	public String getNomefantasia() {
		return nomefantasia;
	}


	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}


	public String getInscricaoestadual() {
		return inscricaoestadual;
	}


	public void setInscricaoestadual(String inscricaoestadual) {
		this.inscricaoestadual = inscricaoestadual;
	}


	public String getInscricaoestadualsubstituicaotributaria() {
		return inscricaoestadualsubstituicaotributaria;
	}


	public void setInscricaoestadualsubstituicaotributaria(String inscricaoestadualsubstituicaotributaria) {
		this.inscricaoestadualsubstituicaotributaria = inscricaoestadualsubstituicaotributaria;
	}


	public String getInscricaomunicipal() {
		return inscricaomunicipal;
	}


	public void setInscricaomunicipal(String inscricaomunicipal) {
		this.inscricaomunicipal = inscricaomunicipal;
	}


	public String getClassificacaonacionalatividadeseconomicas() {
		return classificacaonacionalatividadeseconomicas;
	}


	public void setClassificacaonacionalatividadeseconomicas(String classificacaonacionalatividadeseconomicas) {
		this.classificacaonacionalatividadeseconomicas = classificacaonacionalatividadeseconomicas;
	}


	public Regime getRegimetributario() {
		return regimetributario;
	}


	public void setRegimetributario(Regime regimetributario) {
		this.regimetributario = regimetributario;
	}


	@Override
	public String toString() {
		return "NFNotaInfoEmitente [getCnpj()=" + getCnpj() + ", getCpf()=" + getCpf() + ", getRazaoSocial()="
				+ getRazaoSocial() + ", getNomeFantasia()=" + getNomeFantasia() + ", getEndereco()=" + getEndereco()
				+ ", getInscricaoEstadual()=" + getInscricaoEstadual()
				+ ", getInscricaoEstadualSubstituicaoTributaria()=" + getInscricaoEstadualSubstituicaoTributaria()
				+ ", getInscricaoMunicipal()=" + getInscricaoMunicipal()
				+ ", getClassificacaoNacionalAtividadesEconomicas()=" + getClassificacaoNacionalAtividadesEconomicas()
				+ ", getRegimeTributario()=" + getRegimeTributario() + ", getRazaosocial()=" + getRazaosocial()
				+ ", getNomefantasia()=" + getNomefantasia() + ", getInscricaoestadual()=" + getInscricaoestadual()
				+ ", getInscricaoestadualsubstituicaotributaria()=" + getInscricaoestadualsubstituicaotributaria()
				+ ", getInscricaomunicipal()=" + getInscricaomunicipal()
				+ ", getClassificacaonacionalatividadeseconomicas()=" + getClassificacaonacionalatividadeseconomicas()
				+ ", getRegimetributario()=" + getRegimetributario() + ", toString()=" + super.toString() + "]";
	}




 }
