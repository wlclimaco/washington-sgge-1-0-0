package com.sensus.mlc.tabela.model
public class Atributos extends SensusModel
{
    private Integer cdtabela;
    private String nmtabela;
    private String nmatribu;
    private String dsatribu;
    private String tpatribu;
    private String dsmascar;
    private String dshelp;
    private Integer tmatribu;
    private Integer nrseqatr;
    private List<Auditoria> auditorias;
    private Integer cdtabela;
    /** 
    * Gets the cdtabela.
    * 
    * @return the cdtabela 
    */
    	public Integer getAtributos() 
    { 
    	return this.cdtabela; 
    } 

    /** 
     * Sets the cdtabela.
     *
     * @param cdtabela the new cdtabela 
     */
    public void setAtributos(Integer cdtabela)
    {
    	this.cdtabela = cdtabela; 
    }  
    private String nmtabela;
    /** 
    * Gets the nmtabela.
    * 
    * @return the nmtabela 
    */
    	public String getAtributos() 
    { 
    	return this.nmtabela; 
    } 

    /** 
     * Sets the nmtabela.
     *
     * @param nmtabela the new nmtabela 
     */
    public void setAtributos(String nmtabela)
    {
    	this.nmtabela = nmtabela; 
    }  
    private String nmatribu;
    /** 
    * Gets the nmatribu.
    * 
    * @return the nmatribu 
    */
    	public String getAtributos() 
    { 
    	return this.nmatribu; 
    } 

    /** 
     * Sets the nmatribu.
     *
     * @param nmatribu the new nmatribu 
     */
    public void setAtributos(String nmatribu)
    {
    	this.nmatribu = nmatribu; 
    }  
    private String dsatribu;
    /** 
    * Gets the dsatribu.
    * 
    * @return the dsatribu 
    */
    	public String getAtributos() 
    { 
    	return this.dsatribu; 
    } 

    /** 
     * Sets the dsatribu.
     *
     * @param dsatribu the new dsatribu 
     */
    public void setAtributos(String dsatribu)
    {
    	this.dsatribu = dsatribu; 
    }  
    private String tpatribu;
    /** 
    * Gets the tpatribu.
    * 
    * @return the tpatribu 
    */
    	public String getAtributos() 
    { 
    	return this.tpatribu; 
    } 

    /** 
     * Sets the tpatribu.
     *
     * @param tpatribu the new tpatribu 
     */
    public void setAtributos(String tpatribu)
    {
    	this.tpatribu = tpatribu; 
    }  
    private String dsmascar;
    /** 
    * Gets the dsmascar.
    * 
    * @return the dsmascar 
    */
    	public String getAtributos() 
    { 
    	return this.dsmascar; 
    } 

    /** 
     * Sets the dsmascar.
     *
     * @param dsmascar the new dsmascar 
     */
    public void setAtributos(String dsmascar)
    {
    	this.dsmascar = dsmascar; 
    }  
    private String dshelp;
    /** 
    * Gets the dshelp.
    * 
    * @return the dshelp 
    */
    	public String getAtributos() 
    { 
    	return this.dshelp; 
    } 

    /** 
     * Sets the dshelp.
     *
     * @param dshelp the new dshelp 
     */
    public void setAtributos(String dshelp)
    {
    	this.dshelp = dshelp; 
    }  
    private Integer tmatribu;
    /** 
    * Gets the tmatribu.
    * 
    * @return the tmatribu 
    */
    	public Integer getAtributos() 
    { 
    	return this.tmatribu; 
    } 

    /** 
     * Sets the tmatribu.
     *
     * @param tmatribu the new tmatribu 
     */
    public void setAtributos(Integer tmatribu)
    {
    	this.tmatribu = tmatribu; 
    }  
    private Integer nrseqatr;
    /** 
    * Gets the nrseqatr.
    * 
    * @return the nrseqatr 
    */
    	public Integer getAtributos() 
    { 
    	return this.nrseqatr; 
    } 

    /** 
     * Sets the nrseqatr.
     *
     * @param nrseqatr the new nrseqatr 
     */
    public void setAtributos(Integer nrseqatr)
    {
    	this.nrseqatr = nrseqatr; 
    }  
    private List<Auditoria> auditorias;
    /** 
    * Gets the auditorias.
    * 
    * @return the auditorias 
    */
    	public List<Auditoria> getAtributos() 
    { 
    	return this.auditorias; 
    } 

    /** 
     * Sets the auditorias.
     *
     * @param auditorias the new auditorias 
     */
    public void setAtributos(List<Auditoria> auditorias)
    {
    	this.auditorias = auditorias; 
    }  
/* 
* (non-Javadoc) 
* @see com.sensus.common.model.SensusModel#toString() 
*/ 
@Override 
public String toString()  
{
return "Atributos[
" getCdtabela()=" + getCdtabela()+ ", getNmtabela()=" + getNmtabela()+ ", getNmatribu()=" + getNmatribu()+ ", getDsatribu()=" + getDsatribu()+ ", getTpatribu()=" + getTpatribu()+ ", 
getDsmascar()=" + getDsmascar()+ ", getDshelp()=" + getDshelp()+ ", getTmatribu()=" + getTmatribu()+ ", getNrseqatr()=" + getNrseqatr()+ ", getAuditorias()=" + getAuditorias()
+"]";
}
}
