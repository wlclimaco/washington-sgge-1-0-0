package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfncmnbmResponse extends InquiryResponse
{
    private List<Lfncmnbm> lfncmnbm ;


    public List<Lfncmnbm> getLfncmnbm() {
      return lfncmnbm;
    }


    public void setLfncmnbm(List<Lfncmnbm> lfncmnbm) {
        this.lfncmnbm = lfncmnbm;
}



    public String toString() {
    return "InquiryLfncmnbmResponse [lfncmnbm=" + lfncmnbm
      + ", getLfncmnbm()=" + getLfncmnbm()
    + "]";
    }

}
