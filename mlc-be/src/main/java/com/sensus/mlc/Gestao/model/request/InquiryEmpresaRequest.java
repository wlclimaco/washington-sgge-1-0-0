Memo1
package com.sensus.mlc.Empresa.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.Empresa.model.Empresa;


public class InquiryEmpresaRequest extends InquiryPaginationRequest

    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Empresa> empresa;
}
