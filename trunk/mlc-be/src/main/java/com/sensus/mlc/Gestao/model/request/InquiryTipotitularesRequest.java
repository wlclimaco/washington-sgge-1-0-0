Memo1
package com.sensus.mlc.UnidMed.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.UnidMed.model.Unidmed;


public class InquiryUnidmedRequest extends InquiryPaginationRequest

    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Unidmed> unidmed;
}
package com.sensus.mlc.ClassTitulares.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.ClassTitulares.model.Classtitulares;


public class InquiryClasstitularesRequest extends InquiryPaginationRequest

    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Classtitulares> classtitulares;
}
package com.sensus.mlc.TipoTitulares.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.TipoTitulares.model.Tipotitulares;


public class InquiryTipotitularesRequest extends InquiryPaginationRequest

    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Tipotitulares> tipotitulares;
}
