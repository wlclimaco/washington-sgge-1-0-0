package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLftipofisccliResponse extends InquiryResponse
{
    private List<Lftipofisccli> lftipofisccli ;


    public List<Lftipofisccli> getLftipofisccli() {
      return lftipofisccli;
    }


    public void setLftipofisccli(List<Lftipofisccli> lftipofisccli) {
        this.lftipofisccli = lftipofisccli;
}



    public String toString() {
    return "InquiryLftipofisccliResponse [lftipofisccli=" + lftipofisccli
      + ", getLftipofisccli()=" + getLftipofisccli()
    + "]";
    }

}
