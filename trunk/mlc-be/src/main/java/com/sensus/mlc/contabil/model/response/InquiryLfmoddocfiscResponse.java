package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfmoddocfiscResponse extends InquiryResponse
{
    private List<Lfmoddocfisc> lfmoddocfisc ;


    public List<Lfmoddocfisc> getLfmoddocfisc() {
      return lfmoddocfisc;
    }


    public void setLfmoddocfisc(List<Lfmoddocfisc> lfmoddocfisc) {
        this.lfmoddocfisc = lfmoddocfisc;
}



    public String toString() {
    return "InquiryLfmoddocfiscResponse [lfmoddocfisc=" + lfmoddocfisc
      + ", getLfmoddocfisc()=" + getLfmoddocfisc()
    + "]";
    }

}
