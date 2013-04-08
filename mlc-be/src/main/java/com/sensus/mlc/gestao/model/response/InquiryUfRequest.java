package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.uf.model.Uf;


public class InquiryUfRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Uf> uf;
}
