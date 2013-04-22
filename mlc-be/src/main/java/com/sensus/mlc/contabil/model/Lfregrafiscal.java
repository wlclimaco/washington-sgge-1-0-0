package com.sensus.mlc.contabil.model;
public class Lfregrafiscal extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String codregra;
    private String descregra;
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
    * Gets the codregra.
    * 
    * @return the codregra 
    */
    	public String getCodregra() 
    { 
    	return this.codregra; 
    } 

    /** 
     * Sets the codregra.
     *
     * @param codregra the new codregra 
     */
    public void setCodregra(String codregra)
    {
    	this.codregra = codregra; 
    }  
    /** 
    * Gets the descregra.
    * 
    * @return the descregra 
    */
    	public String getDescregra() 
    { 
    	return this.descregra; 
    } 

    /** 
     * Sets the descregra.
     *
     * @param descregra the new descregra 
     */
    public void setDescregra(String descregra)
    {
    	this.descregra = descregra; 
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
return "Lfregrafiscal[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodregra()=" + getCodregra()+ ", getDescregra()=" + getDescregra()+ ", getListinsalt()=" 
+ getListinsalt()
+"]";
}
}
