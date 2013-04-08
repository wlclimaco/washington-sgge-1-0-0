package com.sensus.mlc.embalagens.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.embalagens.model.Embalagens;


public class EmbalagensResponse extends Response
{
    
    private Integer parentRetry;
    
    private List<Embalagens> embalagens ;
}
