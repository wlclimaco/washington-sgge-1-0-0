package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfserieResponse extends InquiryResponse
{
    private List<Lfserie> lfserie ;


    public List<Lfserie> getLfserie() {
      return lfserie;
    }


    public void setLfserie(List<Lfserie> lfserie) {
        this.lfserie = lfserie;
}



    public String toString() {
    return "InquiryLfserieResponse [lfserie=" + lfserie
      + ", getLfserie()=" + getLfserie()
    + "]";
    }

}
