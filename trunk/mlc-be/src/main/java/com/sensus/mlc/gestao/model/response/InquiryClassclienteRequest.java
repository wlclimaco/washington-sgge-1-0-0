package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.classcliente.model.Classcliente;


public class InquiryClassclienteRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Classcliente> classcliente;
}