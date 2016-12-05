/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;


import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoCOFINS extends ModelCosmeDamiao
{

    /** The econtabil id for the NFNotaInfoItemImpostoCOFINS. */
    private Integer id;

    /** The econtabil aliquota for the NFNotaInfoItemImpostoCOFINS. */
    private NFNotaInfoItemImpostoCOFINSAliquota aliquota;

    /** The econtabil quantidade for the NFNotaInfoItemImpostoCOFINS. */
    private NFNotaInfoItemImpostoCOFINSQuantidade quantidade;

    /** The econtabil naoTributavel for the NFNotaInfoItemImpostoCOFINS. */
    private NFNotaInfoItemImpostoCOFINSNaoTributavel naoTributavel;

    /** The econtabil outrasOperacoes for the NFNotaInfoItemImpostoCOFINS. */
    private NFNotaInfoItemImpostoCOFINSOutrasOperacoes outrasOperacoes;



    /**
     * Default constructor.
     */
    public NFNotaInfoItemImpostoCOFINS()
    {
        super();
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Sets the id.
     *
* @param id the id to set
 */
public void setId(Integer id)
{
        this.id = id;
    }

    /**
     * Gets the aliquota.
     *
     * @return the aliquota
     */
    /**
     * Gets the aliquota.
     *
     * @return the aliquota
     */
    public NFNotaInfoItemImpostoCOFINSAliquota getAliquota()
    {
        return aliquota;
    }

    /**
     * Sets the aliquota.
     *
* @param id the aliquota to set
 */
public void setAliquota(NFNotaInfoItemImpostoCOFINSAliquota aliquota)
{
        this.aliquota = aliquota;
    }

    /**
     * Gets the quantidade.
     *
     * @return the quantidade
     */
    /**
     * Gets the quantidade.
     *
     * @return the quantidade
     */
    public NFNotaInfoItemImpostoCOFINSQuantidade getQuantidade()
    {
        return quantidade;
    }

    /**
     * Sets the quantidade.
     *
* @param id the quantidade to set
 */
public void setQuantidade(NFNotaInfoItemImpostoCOFINSQuantidade quantidade)
{
        this.quantidade = quantidade;
    }

    /**
     * Gets the naoTributavel.
     *
     * @return the naoTributavel
     */
    /**
     * Gets the naoTributavel.
     *
     * @return the naoTributavel
     */
    public NFNotaInfoItemImpostoCOFINSNaoTributavel getNaoTributavel()
    {
        return naoTributavel;
    }

    /**
     * Sets the naotributavel.
     *
* @param id the naotributavel to set
 */
public void setNaoTributavel(NFNotaInfoItemImpostoCOFINSNaoTributavel naotributavel)
{
        this.naoTributavel = naotributavel;
    }

    /**
     * Gets the outrasOperacoes.
     *
     * @return the outrasOperacoes
     */
    /**
     * Gets the outrasOperacoes.
     *
     * @return the outrasOperacoes
     */
    public NFNotaInfoItemImpostoCOFINSOutrasOperacoes getOutrasOperacoes()
    {
        return outrasOperacoes;
    }

    /**
     * Sets the outrasoperacoes.
     *
* @param id the outrasoperacoes to set
 */
public void setOutrasOperacoes(NFNotaInfoItemImpostoCOFINSOutrasOperacoes outrasoperacoes)
{
        this.outrasOperacoes = outrasoperacoes;
    }




 
 }
