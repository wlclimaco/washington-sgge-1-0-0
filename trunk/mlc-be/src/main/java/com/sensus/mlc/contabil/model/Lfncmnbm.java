package com.sensus.mlc.contabil.model;
public class Lfncmnbm extends SensusModel
{
    private String codncm;
    private String codnbm;
    private List<Alterinse> listinsalt;
    /** 
    * Gets the codncm.
    * 
    * @return the codncm 
    */
    	public String getCodncm() 
    { 
    	return this.codncm; 
    } 

    /** 
     * Sets the codncm.
     *
     * @param codncm the new codncm 
     */
    public void setCodncm(String codncm)
    {
    	this.codncm = codncm; 
    }  
    /** 
    * Gets the codnbm.
    * 
    * @return the codnbm 
    */
    	public String getCodnbm() 
    { 
    	return this.codnbm; 
    } 

    /** 
     * Sets the codnbm.
     *
     * @param codnbm the new codnbm 
     */
    public void setCodnbm(String codnbm)
    {
    	this.codnbm = codnbm; 
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
return "Lfncmnbm[ getCodncm()=" + getCodncm()+ ", getCodnbm()=" + getCodnbm()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
