package com.sensus.mlc.contabil.model;
public class Lfnatoper extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String codnat;
    private String descnat;
    private String impdtsaidanat;
    private Double aliqenat;
    private Double aliqfnat;
    private String txtnat;
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
    * Gets the codnat.
    * 
    * @return the codnat 
    */
    	public String getCodnat() 
    { 
    	return this.codnat; 
    } 

    /** 
     * Sets the codnat.
     *
     * @param codnat the new codnat 
     */
    public void setCodnat(String codnat)
    {
    	this.codnat = codnat; 
    }  
    /** 
    * Gets the descnat.
    * 
    * @return the descnat 
    */
    	public String getDescnat() 
    { 
    	return this.descnat; 
    } 

    /** 
     * Sets the descnat.
     *
     * @param descnat the new descnat 
     */
    public void setDescnat(String descnat)
    {
    	this.descnat = descnat; 
    }  
    /** 
    * Gets the impdtsaidanat.
    * 
    * @return the impdtsaidanat 
    */
    	public String getImpdtsaidanat() 
    { 
    	return this.impdtsaidanat; 
    } 

    /** 
     * Sets the impdtsaidanat.
     *
     * @param impdtsaidanat the new impdtsaidanat 
     */
    public void setImpdtsaidanat(String impdtsaidanat)
    {
    	this.impdtsaidanat = impdtsaidanat; 
    }  
    /** 
    * Gets the aliqenat.
    * 
    * @return the aliqenat 
    */
    	public Double getAliqenat() 
    { 
    	return this.aliqenat; 
    } 

    /** 
     * Sets the aliqenat.
     *
     * @param aliqenat the new aliqenat 
     */
    public void setAliqenat(Double aliqenat)
    {
    	this.aliqenat = aliqenat; 
    }  
    /** 
    * Gets the aliqfnat.
    * 
    * @return the aliqfnat 
    */
    	public Double getAliqfnat() 
    { 
    	return this.aliqfnat; 
    } 

    /** 
     * Sets the aliqfnat.
     *
     * @param aliqfnat the new aliqfnat 
     */
    public void setAliqfnat(Double aliqfnat)
    {
    	this.aliqfnat = aliqfnat; 
    }  
    /** 
    * Gets the txtnat.
    * 
    * @return the txtnat 
    */
    	public String getTxtnat() 
    { 
    	return this.txtnat; 
    } 

    /** 
     * Sets the txtnat.
     *
     * @param txtnat the new txtnat 
     */
    public void setTxtnat(String txtnat)
    {
    	this.txtnat = txtnat; 
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
return "Lfnatoper[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodnat()=" + getCodnat()+ ", getDescnat()=" + getDescnat()+ ", getImpdtsaidanat()=" + 
getImpdtsaidanat()+ ", getAliqenat()=" + getAliqenat()+ ", getAliqfnat()=" + getAliqfnat()+ ", getTxtnat()=" + getTxtnat()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
