package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfmodnotaResponse extends InquiryResponse
{
    private List<Lfmodnota> lfmodnota ;


    public List<Lfmodnota> getLfmodnota() {
      return lfmodnota;
    }


    public void setLfmodnota(List<Lfmodnota> lfmodnota) {
        this.lfmodnota = lfmodnota;
}



    public String toString() {
    return "InquiryLfmodnotaResponse [lfmodnota=" + lfmodnota
      + ", getLfmodnota()=" + getLfmodnota()
    + "]";
    }

}
