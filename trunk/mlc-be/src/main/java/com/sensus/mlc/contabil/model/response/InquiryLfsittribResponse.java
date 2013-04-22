package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfsittribResponse extends InquiryResponse
{
    private List<Lfsittrib> lfsittrib ;


    public List<Lfsittrib> getLfsittrib() {
      return lfsittrib;
    }


    public void setLfsittrib(List<Lfsittrib> lfsittrib) {
        this.lfsittrib = lfsittrib;
}



    public String toString() {
    return "InquiryLfsittribResponse [lfsittrib=" + lfsittrib
      + ", getLfsittrib()=" + getLfsittrib()
    + "]";
    }

}
