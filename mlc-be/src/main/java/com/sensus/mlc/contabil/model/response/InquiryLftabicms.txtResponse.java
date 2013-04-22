package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLftabicms.txtResponse extends InquiryResponse
{
    private List<Lftabicms.txt> lftabicms.txt ;


    public List<Lftabicms.txt> getLftabicms.txt() {
      return lftabicms.txt;
    }


    public void setLftabicms.txt(List<Lftabicms.txt> lftabicms.txt) {
        this.lftabicms.txt = lftabicms.txt;
}



    public String toString() {
    return "InquiryLftabicms.txtResponse [lftabicms.txt=" + lftabicms.txt
      + ", getLftabicms.txt()=" + getLftabicms.txt()
    + "]";
    }

}
