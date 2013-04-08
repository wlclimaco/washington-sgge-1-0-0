package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.classcliente.model.Classcliente;


public class ClassclienteRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Classcliente  classcliente;
}
