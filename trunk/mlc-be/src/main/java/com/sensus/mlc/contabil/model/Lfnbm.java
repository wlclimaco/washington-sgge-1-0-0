package com.sensus.mlc.contabil.model;
public class Lfnbm extends SensusModel
{
    private String codnbm;
    private String descnbm;
    private DATE dtins;
    private Date hins;
    private String idusuins;
    private DATE dtalt;
    private Date halt;
    private String idusualt;
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
    * Gets the descnbm.
    * 
    * @return the descnbm 
    */
    	public String getDescnbm() 
    { 
    	return this.descnbm; 
    } 

    /** 
     * Sets the descnbm.
     *
     * @param descnbm the new descnbm 
     */
    public void setDescnbm(String descnbm)
    {
    	this.descnbm = descnbm; 
    }  
    /** 
    * Gets the dtins.
    * 
    * @return the dtins 
    */
    	public DATE getDtins() 
    { 
    	return this.dtins; 
    } 

    /** 
     * Sets the dtins.
     *
     * @param dtins the new dtins 
     */
    public void setDtins(DATE dtins)
    {
    	this.dtins = dtins; 
    }  
    /** 
    * Gets the hins.
    * 
    * @return the hins 
    */
    	public Date getHins() 
    { 
    	return this.hins; 
    } 

    /** 
     * Sets the hins.
     *
     * @param hins the new hins 
     */
    public void setHins(Date hins)
    {
    	this.hins = hins; 
    }  
    /** 
    * Gets the idusuins.
    * 
    * @return the idusuins 
    */
    	public String getIdusuins() 
    { 
    	return this.idusuins; 
    } 

    /** 
     * Sets the idusuins.
     *
     * @param idusuins the new idusuins 
     */
    public void setIdusuins(String idusuins)
    {
    	this.idusuins = idusuins; 
    }  
    /** 
    * Gets the dtalt.
    * 
    * @return the dtalt 
    */
    	public DATE getDtalt() 
    { 
    	return this.dtalt; 
    } 

    /** 
     * Sets the dtalt.
     *
     * @param dtalt the new dtalt 
     */
    public void setDtalt(DATE dtalt)
    {
    	this.dtalt = dtalt; 
    }  
    /** 
    * Gets the halt.
    * 
    * @return the halt 
    */
    	public Date getHalt() 
    { 
    	return this.halt; 
    } 

    /** 
     * Sets the halt.
     *
     * @param halt the new halt 
     */
    public void setHalt(Date halt)
    {
    	this.halt = halt; 
    }  
    /** 
    * Gets the idusualt.
    * 
    * @return the idusualt 
    */
    	public String getIdusualt() 
    { 
    	return this.idusualt; 
    } 

    /** 
     * Sets the idusualt.
     *
     * @param idusualt the new idusualt 
     */
    public void setIdusualt(String idusualt)
    {
    	this.idusualt = idusualt; 
    }  
/* 
* (non-Javadoc) 
* @see com.sensus.common.model.SensusModel#toString() 
*/ 
@Override 
public String toString()  
{
return "Lfnbm[ getCodnbm()=" + getCodnbm()+ ", getDescnbm()=" + getDescnbm()+ ", getDtins()=" + getDtins()+ ", getHins()=" + getHins()+ ", getIdusuins()=" + getIdusuins()+ ", 
getDtalt()=" + getDtalt()+ ", getHalt()=" + getHalt()+ ", getIdusualt()=" + getIdusualt()
+"]";
}
}
