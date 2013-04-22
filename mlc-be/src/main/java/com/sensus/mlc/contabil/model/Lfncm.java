package com.sensus.mlc.contabil.model;
public class Lfncm extends SensusModel
{
    private String codncm;
    private String descncm;
    private String aliqncm;
    private String textoncm;
    private String excecaoncm;
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
    * Gets the descncm.
    * 
    * @return the descncm 
    */
    	public String getDescncm() 
    { 
    	return this.descncm; 
    } 

    /** 
     * Sets the descncm.
     *
     * @param descncm the new descncm 
     */
    public void setDescncm(String descncm)
    {
    	this.descncm = descncm; 
    }  
    /** 
    * Gets the aliqncm.
    * 
    * @return the aliqncm 
    */
    	public String getAliqncm() 
    { 
    	return this.aliqncm; 
    } 

    /** 
     * Sets the aliqncm.
     *
     * @param aliqncm the new aliqncm 
     */
    public void setAliqncm(String aliqncm)
    {
    	this.aliqncm = aliqncm; 
    }  
    /** 
    * Gets the textoncm.
    * 
    * @return the textoncm 
    */
    	public String getTextoncm() 
    { 
    	return this.textoncm; 
    } 

    /** 
     * Sets the textoncm.
     *
     * @param textoncm the new textoncm 
     */
    public void setTextoncm(String textoncm)
    {
    	this.textoncm = textoncm; 
    }  
    /** 
    * Gets the excecaoncm.
    * 
    * @return the excecaoncm 
    */
    	public String getExcecaoncm() 
    { 
    	return this.excecaoncm; 
    } 

    /** 
     * Sets the excecaoncm.
     *
     * @param excecaoncm the new excecaoncm 
     */
    public void setExcecaoncm(String excecaoncm)
    {
    	this.excecaoncm = excecaoncm; 
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
return "Lfncm[ getCodncm()=" + getCodncm()+ ", getDescncm()=" + getDescncm()+ ", getAliqncm()=" + getAliqncm()+ ", getTextoncm()=" + getTextoncm()+ ", getExcecaoncm()=" + 
getExcecaoncm()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
