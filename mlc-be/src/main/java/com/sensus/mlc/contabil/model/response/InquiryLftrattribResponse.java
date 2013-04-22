package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLftrattribResponse extends InquiryResponse
{
    private List<Lftrattrib> lftrattrib ;


    public List<Lftrattrib> getLftrattrib() {
      return lftrattrib;
    }


    public void setLftrattrib(List<Lftrattrib> lftrattrib) {
        this.lftrattrib = lftrattrib;
}



    public String toString() {
    return "InquiryLftrattribResponse [lftrattrib=" + lftrattrib
      + ", getLftrattrib()=" + getLftrattrib()
    + "]";
    }

}
