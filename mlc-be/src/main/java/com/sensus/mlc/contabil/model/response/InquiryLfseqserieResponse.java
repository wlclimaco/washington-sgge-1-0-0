package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfseqserieResponse extends InquiryResponse
{
    private List<Lfseqserie> lfseqserie ;


    public List<Lfseqserie> getLfseqserie() {
      return lfseqserie;
    }


    public void setLfseqserie(List<Lfseqserie> lfseqserie) {
        this.lfseqserie = lfseqserie;
}



    public String toString() {
    return "InquiryLfseqserieResponse [lfseqserie=" + lfseqserie
      + ", getLfseqserie()=" + getLfseqserie()
    + "]";
    }

}
