package com.sensus.mlc.contabil.model;
public class Lftabicms.txt extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String ufti;
    private Double aliqti;
    private Integer aliqintrati;
    private List<Alterinse> listinsalt;
    /** 
    * Gets the codemp.
    * 
    * @return the codemp 
    */
    	public Integer getCodemp() 
    { 
    	return this.codemp; 
    } 

    /** 
     * Sets the codemp.
     *
     * @param codemp the new codemp 
     */
    public void setCodemp(Integer codemp)
    {
    	this.codemp = codemp; 
    }  
    /** 
    * Gets the codfilial.
    * 
    * @return the codfilial 
    */
    	public Integer getCodfilial() 
    { 
    	return this.codfilial; 
    } 

    /** 
     * Sets the codfilial.
     *
     * @param codfilial the new codfilial 
     */
    public void setCodfilial(Integer codfilial)
    {
    	this.codfilial = codfilial; 
    }  
    /** 
    * Gets the ufti.
    * 
    * @return the ufti 
    */
    	public String getUfti() 
    { 
    	return this.ufti; 
    } 

    /** 
     * Sets the ufti.
     *
     * @param ufti the new ufti 
     */
    public void setUfti(String ufti)
    {
    	this.ufti = ufti; 
    }  
    /** 
    * Gets the aliqti.
    * 
    * @return the aliqti 
    */
    	public Double getAliqti() 
    { 
    	return this.aliqti; 
    } 

    /** 
     * Sets the aliqti.
     *
     * @param aliqti the new aliqti 
     */
    public void setAliqti(Double aliqti)
    {
    	this.aliqti = aliqti; 
    }  
    /** 
    * Gets the aliqintrati.
    * 
    * @return the aliqintrati 
    */
    	public Integer getAliqintrati() 
    { 
    	return this.aliqintrati; 
    } 

    /** 
     * Sets the aliqintrati.
     *
     * @param aliqintrati the new aliqintrati 
     */
    public void setAliqintrati(Integer aliqintrati)
    {
    	this.aliqintrati = aliqintrati; 
    }  
    /** 
    * Gets the listinsalt.
    * 
    * @return the listinsalt 
    */
    	public List<Alterinse> getListinsalt() 
    { 
    	return this.listinsalt; 
    } 

    /** 
     * Sets the listinsalt.
     *
     * @param listinsalt the new listinsalt 
     */
    public void setListinsalt(List<Alterinse> listinsalt)
    {
    	this.listinsalt = listinsalt; 
    }  
/* 
* (non-Javadoc) 
* @see com.sensus.common.model.SensusModel#toString() 
*/ 
@Override 
public String toString()  
{
return "Lftabicms.txt[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getUfti()=" + getUfti()+ ", getAliqti()=" + getAliqti()+ ", getAliqintrati()=" + getAliqintrati()+ ", 
getListinsalt()=" + getListinsalt()
+"]";
}
}
