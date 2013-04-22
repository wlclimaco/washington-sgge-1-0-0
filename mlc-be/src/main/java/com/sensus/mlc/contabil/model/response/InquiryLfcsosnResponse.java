package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfcsosnResponse extends InquiryResponse
{
    private List<Lfcsosn> lfcsosn ;


    public List<Lfcsosn> getLfcsosn() {
      return lfcsosn;
    }


    public void setLfcsosn(List<Lfcsosn> lfcsosn) {
        this.lfcsosn = lfcsosn;
}



    public String toString() {
    return "InquiryLfcsosnResponse [lfcsosn=" + lfcsosn
      + ", getLfcsosn()=" + getLfcsosn()
    + "]";
    }

}
