Memo1
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryUnidmedResponse extends InquiryResponse

    private List<Unidmed> unidmed ;


    public List<Unidmed> getUnidmed() {
      return UnidMed;
    }


    public void setUnidmed(List<Unidmed> UnidMed) {
        this.UnidMed = UnidMed;
}



    public String toString() {
    return "InquiryUnidmedResponse [UnidMed=" + UnidMed
      + ", getUnidmed()=" + getUnidmed()
    + "]";


}
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryClasstitularesResponse extends InquiryResponse

    private List<Classtitulares> classtitulares ;


    public List<Classtitulares> getClasstitulares() {
      return ClassTitulares;
    }


    public void setClasstitulares(List<Classtitulares> ClassTitulares) {
        this.ClassTitulares = ClassTitulares;
}



    public String toString() {
    return "InquiryClasstitularesResponse [ClassTitulares=" + ClassTitulares
      + ", getClasstitulares()=" + getClasstitulares()
    + "]";


}
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryTipotitularesResponse extends InquiryResponse

    private List<Tipotitulares> tipotitulares ;


    public List<Tipotitulares> getTipotitulares() {
      return TipoTitulares;
    }


    public void setTipotitulares(List<Tipotitulares> TipoTitulares) {
        this.TipoTitulares = TipoTitulares;
}



    public String toString() {
    return "InquiryTipotitularesResponse [TipoTitulares=" + TipoTitulares
      + ", getTipotitulares()=" + getTipotitulares()
    + "]";


}
