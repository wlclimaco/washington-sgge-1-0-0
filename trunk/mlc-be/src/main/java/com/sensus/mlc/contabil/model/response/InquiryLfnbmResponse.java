package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfnbmResponse extends InquiryResponse
{
    private List<Lfnbm> lfnbm ;


    public List<Lfnbm> getLfnbm() {
      return lfnbm;
    }


    public void setLfnbm(List<Lfnbm> lfnbm) {
        this.lfnbm = lfnbm;
}



    public String toString() {
    return "InquiryLfnbmResponse [lfnbm=" + lfnbm
      + ", getLfnbm()=" + getLfnbm()
    + "]";
    }

}
