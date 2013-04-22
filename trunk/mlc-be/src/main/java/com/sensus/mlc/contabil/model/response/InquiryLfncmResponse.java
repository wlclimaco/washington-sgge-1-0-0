package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfncmResponse extends InquiryResponse
{
    private List<Lfncm> lfncm ;


    public List<Lfncm> getLfncm() {
      return lfncm;
    }


    public void setLfncm(List<Lfncm> lfncm) {
        this.lfncm = lfncm;
}



    public String toString() {
    return "InquiryLfncmResponse [lfncm=" + lfncm
      + ", getLfncm()=" + getLfncm()
    + "]";
    }

}
